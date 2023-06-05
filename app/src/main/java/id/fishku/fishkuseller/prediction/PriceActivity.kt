package id.fishku.fishkuseller.prediction

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import id.fishku.fishkuseller.databinding.ActivityPriceBinding

class PriceActivity : AppCompatActivity() {

    private lateinit var binding : ActivityPriceBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPriceBinding.inflate(layoutInflater)
        setContentView(binding.root)




    }
}