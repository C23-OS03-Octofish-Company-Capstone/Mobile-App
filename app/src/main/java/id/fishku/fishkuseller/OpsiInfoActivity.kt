package id.fishku.fishkuseller

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import id.fishku.fishkuseller.databinding.ActivityOpsiInfoBinding

class OpsiInfoActivity : AppCompatActivity() {

    private lateinit var binding : ActivityOpsiInfoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOpsiInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        binding.btnfresh.setOnClickListener {
            val intent = Intent(this, FreshnessActivity::class.java)
            startActivity(intent)
        }

        binding.btnpack.setOnClickListener {
            val intent = Intent(this, PackingActivity::class.java)
            startActivity(intent)
        }
    }
}