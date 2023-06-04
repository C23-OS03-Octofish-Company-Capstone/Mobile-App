package id.fishku.fishkuseller.dashboard

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
import id.fishku.fishkuseller.R
import id.fishku.fishkuseller.api.ProfileItem
import id.fishku.fishkuseller.databinding.FragmentHomeBinding
import kotlin.properties.Delegates

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var priceFilter : String
    private lateinit var pBandeng: String
    private lateinit var pGurame: String
    private lateinit var pNila: String
    private lateinit var pLele: String
    private lateinit var pTongkol: String



    private var sellerId by Delegates.notNull<Long>()
    private val dashboardViewModel by viewModels<DashboardViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sellerId = requireActivity().intent.getLongExtra(DashboardActivity.SELLER_ID, -1L)

        dashboardViewModel.getSellerData(sellerId)

        dashboardViewModel.sellerProfile.observe(requireActivity()){
            setData(it)
        }

        binding.btnNotification.setOnClickListener {
            view.findNavController().navigate(R.id.action_homeFragment_to_notificationActivity)
        }

        val adapter = ArrayAdapter.createFromResource(requireContext(),
            R.array.filter_harga_ikan, R.layout.dropdown_item)

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        binding.spnFilterHarga.adapter = adapter
        binding.spnFilterHarga.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

                priceFilter = parent?.getItemAtPosition(position).toString()
                when(priceFilter) {
                    "Gurame" -> {
                        binding.ivPriceFishImage.setImageResource(R.drawable.ikan_gurame)
                    }
                    "Bandeng" -> {
                        binding.ivPriceFishImage.setImageResource(R.drawable.ikan_bandeng)
                    }
                    "Nila" -> {
                        binding.ivPriceFishImage.setImageResource(R.drawable.ikan_nila)
                    }
                    "Lele" -> {
                        binding.ivPriceFishImage.setImageResource(R.drawable.ikan_lele)
                    }
                    "Tongkol" -> {
                        binding.ivPriceFishImage.setImageResource(R.drawable.ikan_tongkol)
                    }
                }
                Log.d("RegisterActivity", "Item Selected : $priceFilter")
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                Log.d("RegisterActivity", "Nothing Selected")
            }
        }

    }

    private fun setData(it: List<ProfileItem>) {
        binding.tvSellerName.text = it[0].name
    }


}