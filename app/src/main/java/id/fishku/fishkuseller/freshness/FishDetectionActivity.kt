package id.fishku.fishkuseller.freshness

import android.app.AlertDialog
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.view.ContextThemeWrapper
import androidx.exifinterface.media.ExifInterface
import id.fishku.fishkuseller.R
import id.fishku.fishkuseller.api.DetectionResponse
import id.fishku.fishkuseller.databinding.ActivityFishDetectionBinding
import id.fishku.fishkuseller.reduceFileImage
import id.fishku.fishkuseller.rotateFile
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import retrofit2.Call
import java.io.File
import java.io.FileOutputStream

class FishDetectionActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFishDetectionBinding
    private var getFile: File? = null
    private lateinit var fishPart: String

    private val viewModel by viewModels<FishDetectionViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFishDetectionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        val myFile = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getSerializableExtra("picture", File::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getSerializableExtra("picture")
        } as? File

        val isBackCamera = intent.getBooleanExtra("isBackCamera", true)
        val isImported = intent.getBooleanExtra("isImported", false)
        fishPart = intent.getStringExtra(FreshnessDetectionActivity.FISH_PART).toString()
        if (!isImported){
            myFile?.let { file ->
                getFile = file
                rotateFile(myFile, isBackCamera)
                binding.ivImagePreview.setImageBitmap(BitmapFactory.decodeFile(file.path))
            }
        } else {
            myFile?.let { file ->
                getFile = file
                val exif = ExifInterface(file.path)

                val rotationAngle = when (exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_UNDEFINED)){
                    ExifInterface.ORIENTATION_ROTATE_90 -> 90
                    ExifInterface.ORIENTATION_ROTATE_180 -> 180
                    ExifInterface.ORIENTATION_ROTATE_270 -> 270
                    else -> 0
                }

                val bitmap = BitmapFactory.decodeFile(file.path)
                val rotatedBitmap = if (rotationAngle != 0) {
                    val matrix = Matrix().apply { postRotate(rotationAngle.toFloat()) }
                    Bitmap.createBitmap(bitmap, 0, 0, bitmap.width, bitmap.height, matrix, true)
                } else {
                    bitmap
                }
                rotatedBitmap.compress(Bitmap.CompressFormat.JPEG, 100, FileOutputStream(file))
                binding.ivImagePreview.setImageBitmap(rotatedBitmap)
            }

        }
        binding.btnResult.setOnClickListener {
            if (fishPart.isNotEmpty()) {
                uploadImage(fishPart)
                Toast.makeText(this, fishPart, Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(this, "Part is null", Toast.LENGTH_SHORT).show()
            }

        }

        binding.btnCancel.setOnClickListener {
            cancelApiCallbacks()
        }

        binding.btnRetake.setOnClickListener {
            AlertDialog.Builder(ContextThemeWrapper(this, R.style.CustomAlertDialogStyle))
                .setTitle(R.string.discard_image)
                .setMessage(R.string.discard_desc)
                .setPositiveButton(R.string.btn_yes) { _, _ ->
                    val intent = Intent(this@FishDetectionActivity, DetectionCameraActivity::class.java)
                    intent.putExtra(FreshnessDetectionActivity.FISH_PART, fishPart)
                    startActivity(intent)
                    finish()
                }
                .setNegativeButton(R.string.btn_no, null)
                .show()
        }

        binding.btnMenu.setOnClickListener {
            AlertDialog.Builder(ContextThemeWrapper(this, R.style.CustomAlertDialogStyle))
                .setTitle(R.string.back_to_menu)
                .setMessage(R.string.back_to_menu_desc)
                .setPositiveButton(R.string.btn_yes) { _, _ ->
                    val intent = Intent(this@FishDetectionActivity, FreshnessDetectionActivity::class.java)
                    intent.putExtra(FreshnessDetectionActivity.FISH_PART, fishPart)
                    startActivity(intent)
                    finish()
                }
                .setNegativeButton(R.string.btn_no, null)
                .show()
        }

        viewModel.isLoading.observe(this){
            showLoading(it)
        }

        viewModel.detectionResult.observe(this){
            showResult(it)
        }
    }

    private fun showResult(it: String) {
        binding.tvGettingResult.text = getString(R.string.detect_result)
        binding.tvGettingResult.visibility = View.VISIBLE
        binding.btnCancel.visibility = View.GONE
        binding.btnResult.visibility = View.INVISIBLE
        binding.tvHasilDetect.visibility = View.VISIBLE
        binding.tvHasilDetect.text = it
        when (it){
            "Tidak Segar" -> {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    binding.tvHasilDetect.setTextColor(getColor(R.color.red))
                }
            }
            "Segar" -> {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    binding.tvHasilDetect.setTextColor(getColor(R.color.green))
                }
            }
            "Kurang Segar" -> {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    binding.tvHasilDetect.setTextColor(getColor(R.color.orange))
                }
            }
        }
    }

    private fun showLoading(it: Boolean) {
        if (it) {
            binding.pbLoading.visibility = View.VISIBLE
            binding.btnCancel.visibility = View.VISIBLE
            binding.tvGettingResult.visibility = View.VISIBLE
            binding.btnResult.visibility = View.INVISIBLE
            binding.btnRetake.visibility = View.INVISIBLE
            binding.btnMenu.visibility = View.INVISIBLE
        }else{
            binding.pbLoading.visibility = View.INVISIBLE
            binding.tvGettingResult.visibility = View.INVISIBLE
            binding.btnCancel.visibility = View.INVISIBLE
            binding.btnMenu.visibility = View.VISIBLE
            binding.btnRetake.visibility = View.VISIBLE
            binding.tvGettingResult.visibility = View.INVISIBLE
        }
    }

    private fun cancelApiCallbacks() {
        viewModel.cancelFishEye()
        viewModel.cancelFishGill()
        binding.btnResult.visibility = View.VISIBLE
        binding.pbLoading.visibility = View.INVISIBLE
        binding.btnCancel.visibility = View.INVISIBLE
    }
    private fun uploadImage(part: String) {
        if (getFile != null) {
            val file = reduceFileImage(getFile as File)
            val requestImageFile = file.asRequestBody("multipart/form-data".toMediaType())
            val imageMultipart: MultipartBody.Part = MultipartBody.Part.createFormData(
                "file",
                file.name,
                requestImageFile
            )
            when (part){
                "Mata" -> {
                    viewModel.postFishEye(imageMultipart)

                }
                "Insang" -> {
                    viewModel.postFishGill(imageMultipart)
                }
            }

        }
    }

    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        AlertDialog.Builder(ContextThemeWrapper(this, R.style.CustomAlertDialogStyle))
            .setTitle(R.string.discard_image)
            .setMessage(R.string.discard_desc)
            .setPositiveButton(R.string.btn_yes) { _, _ ->
                val intent = Intent(this@FishDetectionActivity, DetectionCameraActivity::class.java)
                intent.putExtra(FreshnessDetectionActivity.FISH_PART, fishPart)
                startActivity(intent)
                finish()
                cancelApiCallbacks()
            }
            .setNegativeButton(R.string.btn_no, null)
            .show()
    }

    companion object{
        const val CAMERA_X_RESULT = 200
    }
}