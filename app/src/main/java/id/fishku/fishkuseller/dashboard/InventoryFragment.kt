package id.fishku.fishkuseller.dashboard

import android.content.Intent
import android.os.Bundle
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
import kotlin.properties.Delegates

class InventoryFragment : Fragment() {

    private var _binding: FragmentInventoryBinding? = null
    private val binding get() = _binding!!

    private val dashboardViewModel by viewModels<DashboardViewModel>()
    private var sellerId by Delegates.notNull<Long>()



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

        binding.btnAdd.setOnClickListener {
            val addInventory = Intent(requireActivity(), InventoryActivity::class.java)
            addInventory.putExtra(DashboardActivity.SELLER_ID, sellerId)
            startActivity(addInventory)
        }
    }


    override fun onResume() {
        super.onResume()
        dashboardViewModel.getInventory(sellerId)
    }

    private fun setInventory(it: List<InventoryItem>) {
        binding.apply {
            rvInventory.layoutManager = LinearLayoutManager(requireActivity())
            val adapter = InventoryAdapter(it)
            rvInventory.adapter = adapter

            adapter.setOnEditButtonClickCallback(object :
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

            adapter.setOnDeleteButtonClickCallback(object :
                InventoryAdapter.OnDeleteButtonClickCallback {
                override fun onDeleteButtonClicked(data: InventoryItem) {
                    dashboardViewModel.deleteItem(data.idFish)
                    dashboardViewModel.getInventory(sellerId)
                }
            })


        }
    }

}