package id.fishku.fishkuseller.dashboard

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModelProvider
import id.fishku.fishkuseller.R
import id.fishku.fishkuseller.databinding.FragmentTransactionBinding
import id.fishku.fishkuseller.databinding.FragmentUserBinding
import id.fishku.fishkuseller.datastore.LoginPref
import id.fishku.fishkuseller.login.LoginActivity
import id.fishku.fishkuseller.login.LoginViewModel
import id.fishku.fishkuseller.login.ViewModelFactory


class UserFragment : Fragment() {

    private var _binding: FragmentUserBinding? = null
    private val binding get() = _binding!!
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "login")
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

        binding.apply {
            btnLogout.setOnClickListener {
                loginViewModel.removeSellerId()
                val logout = Intent(requireActivity(), LoginActivity::class.java)
                startActivity(logout)
                requireActivity().finish()

            }
        }
    }

}