package id.fishku.fishkuseller.dashboard

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import id.fishku.fishkuseller.api.ProfileItem
import id.fishku.fishkuseller.databinding.FragmentUserBinding
import id.fishku.fishkuseller.datastore.LoginPref
import id.fishku.fishkuseller.login.LoginActivity
import id.fishku.fishkuseller.login.LoginViewModel
import id.fishku.fishkuseller.login.ViewModelFactory
import kotlin.properties.Delegates


class UserFragment : Fragment() {

    private var _binding: FragmentUserBinding? = null
    private val binding get() = _binding!!
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "login")
    private val dashboardViewModel by viewModels<DashboardViewModel>()
    private var sellerId by Delegates.notNull<Long>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUserBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val pref = LoginPref.getInstance(requireContext().dataStore)
        val loginViewModel = ViewModelProvider(this, ViewModelFactory(pref))[LoginViewModel::class.java]

        sellerId = requireActivity().intent.getLongExtra(DashboardActivity.SELLER_ID, -1L)

        dashboardViewModel.getSellerData(sellerId)

        dashboardViewModel.sellerProfile.observe(requireActivity()){
            setSellerData(it)
        }

        binding.apply {
            btnLogOut.setOnClickListener {
                loginViewModel.removeSellerId()
                val logout = Intent(requireActivity(), LoginActivity::class.java)
                logout.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                requireActivity().startActivity(logout)
                requireActivity().finishAffinity()
            }
        }
    }

    private fun setSellerData(it: List<ProfileItem>) {
        binding.tvSellerEmail.text = "email@email.com"
        binding.tvSellerName.text = it[0].name
        val phoneNumberString = "+${it[0].phoneNumber}"
        binding.tvPhoneNumber.text = phoneNumberString
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}