package id.fishku.fishkuseller.freshness

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import id.fishku.fishkuseller.R
import id.fishku.fishkuseller.databinding.ActivityFreshnessBinding
import id.fishku.fishkuseller.databinding.ActivityFreshnessDetectionBinding
import id.fishku.fishkuseller.freshness.adapter.DetectionMenuAdapter
import id.fishku.fishkuseller.freshness.adapter.MethodMenu

class FreshnessDetectionActivity : AppCompatActivity() {

    private lateinit var binding : ActivityFreshnessDetectionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFreshnessDetectionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        if (!allPermissionsGranted()) {
            ActivityCompat.requestPermissions(
                this,
                REQUIRED_PERMISSIONS,
                REQUEST_CODE_PERMISSIONS
            )
        }

        val itemList: List<MethodMenu> = listOf(
            MethodMenu("Mata", R.drawable.fish_eye),
            MethodMenu("Insang", R.drawable.fishgills),
            // Add more items as needed
        )

        binding.rvFreshnessMetod.layoutManager = GridLayoutManager(this@FreshnessDetectionActivity,2)
        binding.rvFreshnessMetod.adapter = DetectionMenuAdapter(itemList).apply {
            setOnButtonClickCallback(object : DetectionMenuAdapter.OnButtonClickCallback{
                override fun onButtonClicked(data: MethodMenu) {
                    val camera = Intent(this@FreshnessDetectionActivity, DetectionCameraActivity::class.java)
                    camera.putExtra(FISH_PART, data.text)
                    startActivity(camera)
                    finish()
                }
            })
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_CODE_PERMISSIONS) {
            if (!allPermissionsGranted()) {
                Toast.makeText(
                    this,
                    R.string.no_permit,
                    Toast.LENGTH_SHORT
                ).show()
                finish()
            }
        }
    }

    private fun allPermissionsGranted() = REQUIRED_PERMISSIONS.all {
        ContextCompat.checkSelfPermission(baseContext, it) == PackageManager.PERMISSION_GRANTED
    }

    companion object{
        const val FISH_PART = "fish_part"
        private val REQUIRED_PERMISSIONS = arrayOf(Manifest.permission.CAMERA)
        private const val REQUEST_CODE_PERMISSIONS = 10
    }
}