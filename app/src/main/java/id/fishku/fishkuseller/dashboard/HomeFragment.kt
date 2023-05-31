package id.fishku.fishkuseller.dashboard

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DecodeFormat
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import id.fishku.fishkuseller.R
import id.fishku.fishkuseller.databinding.FragmentHomeBinding
import id.fishku.fishkuseller.datastore.LoginPref
import id.fishku.fishkuseller.login.LoginViewModel
import id.fishku.fishkuseller.login.ViewModelFactory
import java.util.*

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "login")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val pref = LoginPref.getInstance(requireContext().dataStore)
        val loginViewModel = ViewModelProvider(this, ViewModelFactory(pref))[LoginViewModel::class.java]

        loginViewModel.getSellerId().observe(requireActivity()){
            loginViewModel.getSellerData(it)
        }

        loginViewModel.profileResult.observe(requireActivity()){
            binding.apply {
                tvSellerName.text = it[0].name
                if (it[0].photoUrl != null) {
                    Glide.with(this@HomeFragment)
                        .load(it[0].photoUrl)
                        .transition(DrawableTransitionOptions.withCrossFade())
                        .centerCrop()
                        .format(DecodeFormat.PREFER_RGB_565)
                        .into(binding.ivSellerAvatar)
                }
            }
        }

        setupChart()

        binding.btnNotification.setOnClickListener {
            view.findNavController().navigate(R.id.action_homeFragment_to_notificationActivity)
        }
    }

    private fun setupChart() {
        binding.lcPricePrediction.subjectColor = "0C7DBF"
        binding.lcPricePrediction.setXAxis(mutableListOf<String>().apply {
            add("Jan")
            add("Feb")
            add("Mar")
            add("Apr")
            add("May")
            add("Jun")
            add("Jul")
            add("Aug")
        })
        binding.lcPricePrediction.setYAxis(mutableListOf<String>().apply {
            add("0")
            add("10")
            add("20")
            add("30")
            add("40")
            add("50")
        })
        binding.lcPricePrediction.yAxisData = LinkedList<Float>().apply {
            add(10F)
            add(15F)
            add(6F)
            add(13F)
            add(21F)
            add(17F)
            add(22F)
            add(27F)
        }
        binding.lcPricePrediction.max = 50f

        binding.lcPricePrediction1.max = 50f
        binding.lcPricePrediction1.subjectColor = "FF5722"
        binding.lcPricePrediction1.yAxisData = LinkedList<Float>().apply {
            add(12F)
            add(16F)
            add(4F)
            add(16F)
            add(27F)
            add(36F)
            add(30F)
            add(37F)
        }

        binding.lcPricePrediction1.setXAxis(mutableListOf<String>().apply {
            add("")
            add("")
            add("")
            add("")
            add("")
            add("")
            add("")
            add("")
        })
        binding.lcPricePrediction1.setYAxis(mutableListOf<String>().apply {
            add("")
            add("")
            add("")
            add("")
            add("")
            add("")
        })
        binding.lcPricePrediction.yAxisData = LinkedList<Float>().apply {
            add(10F)
            add(15F)
            add(6F)
            add(13F)
            add(21F)
            add(17F)
            add(22F)
            add(27F)
        }
    }
}