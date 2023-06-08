package id.fishku.fishkuseller.prediction

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import id.fishku.fishkuseller.R
import id.fishku.fishkuseller.databinding.ActivityPriceBinding
import id.fishku.fishkuseller.prediction.adapter.PriceAdapter
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody

class PriceActivity : AppCompatActivity() {

    private lateinit var binding : ActivityPriceBinding
    private lateinit var priceFilter : String
    private val viewModel by viewModels<PriceViewModel>()

    private lateinit var listEceran: List<Double>
    private lateinit var listGrosir: List<Double>

    private var grosirAvail: Boolean = false
    private var eceranAvail: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPriceBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvPrice.layoutManager = LinearLayoutManager(this)

        val gson = Gson()

        val adapter = ArrayAdapter.createFromResource(this,
            R.array.filter_harga_ikan, R.layout.dropdown_item)

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        val requestEceranInit = gson.toJson(MyRequestObject("Gurame", "Eceran", 5))
            .toRequestBody("application/json".toMediaType())
        val requestGrosirInit = gson.toJson(MyRequestObject("Gurame","Grosir", 5))
            .toRequestBody("application/json".toMediaType())
        viewModel.getPriceEceran(requestEceranInit)
        viewModel.getPriceGrosir(requestGrosirInit)

        binding.spnPriceikan.adapter = adapter
        binding.spnPriceikan.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

                priceFilter = parent?.getItemAtPosition(position).toString()
                when(priceFilter) {
                    "Gurame" -> {
                        val requestEceran = gson.toJson(MyRequestObject(priceFilter, "Eceran", 5))
                            .toRequestBody("application/json".toMediaType())
                        val requestGrosir = gson.toJson(MyRequestObject(priceFilter, "Grosir", 5))
                            .toRequestBody("application/json".toMediaType())
                        viewModel.getPriceEceran(requestEceran)
                        viewModel.getPriceGrosir(requestGrosir)
                    }
                    "Bandeng" -> {
                        val requestEceran = gson.toJson(MyRequestObject(priceFilter, "Eceran", 5))
                            .toRequestBody("application/json".toMediaType())
                        val requestGrosir = gson.toJson(MyRequestObject(priceFilter, "Grosir", 5))
                            .toRequestBody("application/json".toMediaType())
                        viewModel.getPriceEceran(requestEceran)
                        viewModel.getPriceGrosir(requestGrosir)
                    }
                    "Nila" -> {
                        val requestEceran = gson.toJson(MyRequestObject(priceFilter, "Eceran", 5))
                            .toRequestBody("application/json".toMediaType())
                        val requestGrosir = gson.toJson(MyRequestObject(priceFilter, "Grosir", 5))
                            .toRequestBody("application/json".toMediaType())
                        viewModel.getPriceEceran(requestEceran)
                        viewModel.getPriceGrosir(requestGrosir)
                    }
                    "Lele" -> {
                        val requestEceran = gson.toJson(MyRequestObject(priceFilter, "Eceran", 5))
                            .toRequestBody("application/json".toMediaType())
                        val requestGrosir = gson.toJson(MyRequestObject(priceFilter, "Grosir", 5))
                            .toRequestBody("application/json".toMediaType())
                        viewModel.getPriceEceran(requestEceran)
                        viewModel.getPriceGrosir(requestGrosir)
                    }
                    "Tongkol" -> {
                        val requestEceran = gson.toJson(MyRequestObject(priceFilter, "Eceran", 5))
                            .toRequestBody("application/json".toMediaType())
                        val requestGrosir = gson.toJson(MyRequestObject(priceFilter, "Grosir", 5))
                            .toRequestBody("application/json".toMediaType())
                        viewModel.getPriceEceran(requestEceran)
                        viewModel.getPriceGrosir(requestGrosir)
                    }
                }
                Log.d("RegisterActivity", "Item Selected : $priceFilter")
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                Log.d("RegisterActivity", "Nothing Selected")
            }
        }

        viewModel.priceGrosir.observe(this){
            listGrosir = it
            grosirAvail = true
            viewModel.isPriceCollected(grosirAvail, eceranAvail)
        }

        viewModel.priceEceran.observe(this){
            listEceran = it
            eceranAvail = true
            viewModel.isPriceCollected(grosirAvail, eceranAvail)
        }

        viewModel.priceAvail.observe(this){
            setPriceData(it)
        }

        viewModel.isLoading.observe(this){
            setLoading(it)
        }
    }

    private fun setPriceData(it: Boolean){
        if(it) {
            binding.rvPrice.adapter = PriceAdapter(listGrosir, listEceran)
        }
    }

    private fun setLoading(it: Boolean) {
        if(it){
            binding.pbLoading.visibility = View.VISIBLE
            binding.rvPrice.visibility = View.INVISIBLE
        }else{
            binding.pbLoading.visibility = View.GONE
            binding.rvPrice.visibility = View.VISIBLE
        }
    }
}