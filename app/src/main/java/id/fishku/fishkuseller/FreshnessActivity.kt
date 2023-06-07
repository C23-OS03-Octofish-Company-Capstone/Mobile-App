package id.fishku.fishkuseller

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import id.fishku.fishkuseller.databinding.ActivityFreshnessBinding

class FreshnessActivity : AppCompatActivity() {

    private lateinit var binding : ActivityFreshnessBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFreshnessBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

    }
}