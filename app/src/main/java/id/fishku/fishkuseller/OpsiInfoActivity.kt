package id.fishku.fishkuseller

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class OpsiInfoActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_opsi_info)

        var btnfresh = findViewById<Button>(R.id.btnfresh)
        btnfresh.setOnClickListener {
            val intent = Intent(this, FreshnessActivity::class.java)
            startActivity(intent)
        }

        var btnpack = findViewById<Button>(R.id.btnpack)
        btnpack.setOnClickListener {
            val intent = Intent(this, PackingActivity::class.java)
            startActivity(intent)
        }
    }
}