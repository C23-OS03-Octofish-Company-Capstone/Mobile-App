package id.fishku.fishkuseller.dashboard

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.google.gson.Gson
import id.fishku.fishkuseller.R
import id.fishku.fishkuseller.api.ProfileItem
import id.fishku.fishkuseller.databinding.FragmentHomeBinding
import id.fishku.fishkuseller.formatPrice
import id.fishku.fishkuseller.login.LoginActivity
import id.fishku.fishkuseller.prediction.MyRequestObject
import id.fishku.fishkuseller.prediction.PriceViewModel
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody
import kotlin.properties.Delegates

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var priceFilter : String


    private var sellerId by Delegates.notNull<Long>()
    private val dashboardViewModel by viewModels<DashboardViewModel>()
    private val priceViewModel by viewModels<PriceViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sellerId = requireActivity().intent.getLongExtra(DashboardActivity.SELLER_ID, -1L)

        dashboardViewModel.getSellerData(sellerId)

        dashboardViewModel.sellerProfile.observe(requireActivity()){
            setData(it)
        }

        val gson = Gson()

        binding.btnNotification.setOnClickListener {
            view.findNavController().navigate(R.id.action_homeFragment_to_notificationActivity)
        }

        binding.btnInfostatis.setOnClickListener {
            view.findNavController().navigate(R.id.action_navigation_home_to_opsiInfoActivity)
        }

        binding.btnPredictionDetail.setOnClickListener {
            view.findNavController().navigate(R.id.action_navigation_home_to_priceActivity)
        }

        binding.btnFreshnessActivity.setOnClickListener {
            view.findNavController().navigate(R.id.action_navigation_home_to_freshnessDetectionActivity)
        }

        binding.btnTransactionDetail.setOnClickListener {
            view.findNavController().navigate(R.id.action_navigation_home_to_transactionHistoryActivity)
        }

        val adapter = ArrayAdapter.createFromResource(requireContext(),
            R.array.filter_harga_ikan, R.layout.dropdown_item)

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        val requestEceranInit = gson.toJson(MyRequestObject("Gurame", "Eceran", 5))
            .toRequestBody("application/json".toMediaType())
        val requestGrosirInit = gson.toJson(MyRequestObject("Gurame","Grosir", 5))
            .toRequestBody("application/json".toMediaType())
        priceViewModel.getPriceEceran(requestEceranInit)
        priceViewModel.getPriceGrosir(requestGrosirInit)
        binding.tvNextPrice.text = "Memperoleh data..."
        binding.tvNextPrice2.text = "Memperoleh data..."

        binding.spnFilterHarga.adapter = adapter
        binding.spnFilterHarga.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

                priceFilter = parent?.getItemAtPosition(position).toString()
                when(priceFilter) {
                    "Gurame" -> {
                        binding.ivPriceFishImage.setImageResource(R.drawable.ikan_gurame)
                        val requestEceran = gson.toJson(MyRequestObject(priceFilter, "Eceran", 5))
                            .toRequestBody("application/json".toMediaType())
                        val requestGrosir = gson.toJson(MyRequestObject(priceFilter, "Grosir", 5))
                            .toRequestBody("application/json".toMediaType())
                        priceViewModel.getPriceEceran(requestEceran)
                        priceViewModel.getPriceGrosir(requestGrosir)
                        binding.tvNextPrice.text = "Memperoleh data..."
                        binding.tvNextPrice2.text = "Memperoleh data..."

                    }
                    "Bandeng" -> {
                        binding.ivPriceFishImage.setImageResource(R.drawable.ikan_bandeng)
                        val requestEceran = gson.toJson(MyRequestObject(priceFilter, "Eceran", 5))
                            .toRequestBody("application/json".toMediaType())
                        val requestGrosir = gson.toJson(MyRequestObject(priceFilter, "Grosir", 5))
                            .toRequestBody("application/json".toMediaType())
                        priceViewModel.getPriceEceran(requestEceran)
                        priceViewModel.getPriceGrosir(requestGrosir)
                        binding.tvNextPrice.text = "Memperoleh data..."
                        binding.tvNextPrice2.text = "Memperoleh data..."
                    }
                    "Nila" -> {
                        binding.ivPriceFishImage.setImageResource(R.drawable.ikan_nila)
                        val requestEceran = gson.toJson(MyRequestObject(priceFilter, "Eceran", 5))
                            .toRequestBody("application/json".toMediaType())
                        val requestGrosir = gson.toJson(MyRequestObject(priceFilter, "Grosir", 5))
                            .toRequestBody("application/json".toMediaType())
                        priceViewModel.getPriceEceran(requestEceran)
                        priceViewModel.getPriceGrosir(requestGrosir)
                        binding.tvNextPrice.text = "Memperoleh data..."
                        binding.tvNextPrice2.text = "Memperoleh data..."
                    }
                    "Lele" -> {
                        binding.ivPriceFishImage.setImageResource(R.drawable.ikan_lele)
                        val requestEceran = gson.toJson(MyRequestObject(priceFilter, "Eceran", 5))
                            .toRequestBody("application/json".toMediaType())
                        val requestGrosir = gson.toJson(MyRequestObject(priceFilter, "Grosir", 5))
                            .toRequestBody("application/json".toMediaType())
                        priceViewModel.getPriceEceran(requestEceran)
                        priceViewModel.getPriceGrosir(requestGrosir)
                        binding.tvNextPrice.text = "Memperoleh data..."
                        binding.tvNextPrice2.text = "Memperoleh data..."
                    }
                    "Tongkol" -> {
                        binding.ivPriceFishImage.setImageResource(R.drawable.ikan_tongkol)
                        val requestEceran = gson.toJson(MyRequestObject(priceFilter, "Eceran", 5))
                            .toRequestBody("application/json".toMediaType())
                        val requestGrosir = gson.toJson(MyRequestObject(priceFilter, "Grosir", 5))
                            .toRequestBody("application/json".toMediaType())
                        priceViewModel.getPriceEceran(requestEceran)
                        priceViewModel.getPriceGrosir(requestGrosir)
                        binding.tvNextPrice.text = "Memperoleh data..."
                        binding.tvNextPrice2.text = "Memperoleh data..."
                    }
                }
                Log.d("RegisterActivity", "Item Selected : $priceFilter")
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                Log.d("RegisterActivity", "Nothing Selected")
            }
        }

        priceViewModel.priceGrosir.observe(requireActivity()){
            val price = formatPrice(it[0].toInt().toDouble())
            binding.tvNextPrice.text = "Rp. $price/kg"
        }
        priceViewModel.priceEceran.observe(requireActivity()){
            val price = formatPrice(it[0].toInt().toDouble())
            binding.tvNextPrice2.text = "Rp. $price/kg"
        }


    }


    private fun setData(it: List<ProfileItem>) {
        if(it[0].name.isNotEmpty()) {
            binding.tvSellerName.text = it[0].name
        }else{
            val intent = Intent(requireActivity(), LoginActivity::class.java)
            startActivity(intent)
            requireActivity().finish()
        }
    }


}