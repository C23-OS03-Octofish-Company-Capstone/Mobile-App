package id.fishku.fishkuseller.freshness

import android.app.AlertDialog
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.view.ContextThemeWrapper
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.content.ContextCompat
import id.fishku.fishkuseller.R
import id.fishku.fishkuseller.createFile
import id.fishku.fishkuseller.databinding.ActivityDetectionCameraBinding
import id.fishku.fishkuseller.uriToFile
import java.nio.file.Files.createFile

class DetectionCameraActivity : AppCompatActivity() {

    private lateinit var binding : ActivityDetectionCameraBinding
    private var imageCapture: ImageCapture? = null
    private var cameraSelector: CameraSelector = CameraSelector.DEFAULT_BACK_CAMERA
    private lateinit var fishPart: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetectionCameraBinding.inflate(layoutInflater)
        setContentView(binding.root)

        fishPart = intent.getStringExtra(FreshnessDetectionActivity.FISH_PART).toString()

        startCamera()

        val titleColor = ContextCompat.getColor(this, R.color.white)
        val title = SpannableString(getString(R.string.take_photo) + " " + fishPart + " Ikan")
        title.setSpan(ForegroundColorSpan(titleColor), 0, title.length, Spannable.SPAN_INCLUSIVE_INCLUSIVE)
        supportActionBar?.title = title

        binding.btnCapture.setOnClickListener{
            takePhoto()
        }

        binding.btnGallery.setOnClickListener{
            startGallery()
        }
    }

    private fun startCamera() {
        val cameraProviderFuture = ProcessCameraProvider.getInstance(this)
        cameraProviderFuture.addListener({
            val cameraProvider: ProcessCameraProvider = cameraProviderFuture.get()
            val preview = Preview.Builder()
                .build()
                .also {
                    it.setSurfaceProvider(binding.viewFinder.surfaceProvider)
                }
            imageCapture = ImageCapture.Builder().build()

            try {
                cameraProvider.unbindAll()
                cameraProvider.bindToLifecycle(
                    this,
                    cameraSelector,
                    preview,
                    imageCapture
                )
            } catch (exc: java.lang.Exception){
                Toast.makeText(
                    this@DetectionCameraActivity,
                    R.string.camera_failed,
                    Toast.LENGTH_SHORT
                ).show()
            }
        }, ContextCompat.getMainExecutor(this))
    }

    private fun takePhoto(){
        val imageCapture = imageCapture?: return

        val photoFile = createFile(application)

        val captureButton = binding.btnCapture
        val captureEffectAnimation = AnimationUtils.loadAnimation(this, R.anim.capture_effect)
        captureButton.startAnimation(captureEffectAnimation)

        val flashOverlay = binding.flashOverlay
        val flashAnimation = AnimationUtils.loadAnimation(this, R.anim.flash_animation)
        flashAnimation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation?) {
                flashOverlay.visibility = View.VISIBLE
            }

            override fun onAnimationEnd(animation: Animation?) {
                flashOverlay.visibility = View.GONE
            }

            override fun onAnimationRepeat(animation: Animation?) {
                // Not needed, but required for AnimationListener
            }
        })
        flashOverlay.startAnimation(flashAnimation)

        val outputOptions = ImageCapture.OutputFileOptions.Builder(photoFile).build()
        imageCapture.takePicture(
            outputOptions,
            ContextCompat.getMainExecutor(this),
            object: ImageCapture.OnImageSavedCallback {
                override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {
                    Toast.makeText(
                        this@DetectionCameraActivity,
                        R.string.capture_success,
                        Toast.LENGTH_SHORT
                    ).show()

                    val intent = Intent(this@DetectionCameraActivity, FishDetectionActivity::class.java)
                    intent.putExtra("picture", photoFile)
                    intent.putExtra(
                        "isBackCamera",
                        cameraSelector == CameraSelector.DEFAULT_BACK_CAMERA
                    )
                    intent.putExtra(FreshnessDetectionActivity.FISH_PART, fishPart)
                    finish()
                    setResult(FishDetectionActivity.CAMERA_X_RESULT, intent)
                    startActivity(intent)
                }

                override fun onError(exception: ImageCaptureException) {
                    Toast.makeText(
                        this@DetectionCameraActivity,
                        R.string.capture_failed,
                        Toast.LENGTH_SHORT
                    ).show()
                }

            }
        )
    }

    private fun startGallery() {
        val intent = Intent()
        intent.action = Intent.ACTION_GET_CONTENT
        intent.type = "image/*"
        val chooser = Intent.createChooser(intent, "Choose a Picture")
        launcherIntentGallery.launch(chooser)
    }

    private val launcherIntentGallery = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            val selectedImg = result.data?.data as Uri


            selectedImg.let { uri ->
                val myFile = uriToFile(uri, this@DetectionCameraActivity)
                val previewIntent = Intent(this@DetectionCameraActivity, FishDetectionActivity::class.java)
                previewIntent.putExtra("picture", myFile)
                previewIntent.putExtra("isImported", true)
                previewIntent.putExtra(FreshnessDetectionActivity.FISH_PART, fishPart)
                startActivity(previewIntent)
                finish()
            }
        }
    }

    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        val intent = Intent(this@DetectionCameraActivity, FreshnessDetectionActivity::class.java)
        startActivity(intent)
        finish()
    }
}