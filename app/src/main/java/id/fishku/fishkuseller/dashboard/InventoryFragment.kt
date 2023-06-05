package id.fishku.fishkuseller.dashboard

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import id.fishku.fishkuseller.api.InventoryItem
import id.fishku.fishkuseller.dashboard.adapter.InventoryAdapter
import id.fishku.fishkuseller.databinding.FragmentInventoryBinding
import id.fishku.fishkuseller.inventory.InventoryActivity
import java.io.File
import kotlin.properties.Delegates

class InventoryFragment : Fragment() {

    private var _binding: FragmentInventoryBinding? = null
    private val binding get() = _binding!!

    private val dashboardViewModel by viewModels<DashboardViewModel>()
    private var sellerId by Delegates.notNull<Long>()

    private var inventoryList: List<InventoryItem> = emptyList()

    private var getFile: File? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentInventoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sellerId = requireActivity().intent.getLongExtra(DashboardActivity.SELLER_ID, -1L)

        dashboardViewModel.getInventory(sellerId)

        dashboardViewModel.sellerInventory.observe(requireActivity()){
            setInventory(it)
        }
        dashboardViewModel.deleteResponse.observe(requireActivity()){
            dashboardViewModel.getInventory(sellerId)
        }

        dashboardViewModel.isLoading.observe(requireActivity()){
            setLoading(it)
        }


        binding.btnAdd.setOnClickListener {
            val addInventory = Intent(requireActivity(), InventoryActivity::class.java)
            addInventory.putExtra(DashboardActivity.SELLER_ID, sellerId)
            startActivity(addInventory)
            binding.edtSearch.clearFocus()
            binding.edtSearch.text?.clear()
        }

        binding.edtSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // No action needed
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // No action needed
            }

            override fun afterTextChanged(s: Editable?) {
                filterInventory(s.toString())
            }
        })
    }

    override fun onResume() {
        super.onResume()
        dashboardViewModel.getInventory(sellerId)
    }

    private fun setLoading(it: Boolean) {
        if(it){
            binding.pbLoading.visibility = View.VISIBLE
        }else{
            binding.pbLoading.visibility = View.GONE
        }
    }


    private fun setInventory(it: List<InventoryItem>) {
        inventoryList = it
        binding.apply {
            rvInventory.layoutManager  = LinearLayoutManager(requireActivity())
            rvInventory.adapter = InventoryAdapter(it).apply {
                setOnEditButtonClickCallback(object :
                    InventoryAdapter.OnEditButtonClickCallback {
                    override fun onEditButtonClicked(data: InventoryItem) {
                        val editInventory = Intent(requireActivity(), InventoryActivity::class.java)
                        editInventory.putExtra(InventoryActivity.ID_FISH, data.idFish)
                        editInventory.putExtra(InventoryActivity.NAME_FISH, data.name)
                        editInventory.putExtra(InventoryActivity.WEIGHT_FISH, data.weight)
                        editInventory.putExtra(InventoryActivity.PRICE_FISH, data.price)
                        editInventory.putExtra(InventoryActivity.PHOTO_FISH, data.photoUrl)
                        startActivity(editInventory)
                    }
                })

                setOnDeleteButtonClickCallback(object :
                    InventoryAdapter.OnDeleteButtonClickCallback {
                    override fun onDeleteButtonClicked(data: InventoryItem) {
                        dashboardViewModel.deleteItem(data.idFish)
                    }
                })
            }


        }
    }

    private fun filterInventory(query: String) {
        val filteredList = inventoryList.filter { item ->
            item.name.contains(query, ignoreCase = true)
        }
        binding.rvInventory.adapter = InventoryAdapter(filteredList).apply {
            setOnEditButtonClickCallback(object :
                InventoryAdapter.OnEditButtonClickCallback {
                override fun onEditButtonClicked(data: InventoryItem) {
                    val editInventory = Intent(requireActivity(), InventoryActivity::class.java)
                    editInventory.putExtra(InventoryActivity.ID_FISH, data.idFish)
                    editInventory.putExtra(InventoryActivity.NAME_FISH, data.name)
                    editInventory.putExtra(InventoryActivity.WEIGHT_FISH, data.weight)
                    editInventory.putExtra(InventoryActivity.PRICE_FISH, data.price)
                    editInventory.putExtra(InventoryActivity.PHOTO_FISH, data.photoUrl)
                    startActivity(editInventory)
                    binding.edtSearch.clearFocus()
                }
            })

            setOnDeleteButtonClickCallback(object :
                InventoryAdapter.OnDeleteButtonClickCallback {
                override fun onDeleteButtonClicked(data: InventoryItem) {
                    dashboardViewModel.deleteItem(data.idFish)
                }
            })
        }
    }

}