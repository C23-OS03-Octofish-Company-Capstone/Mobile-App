package id.fishku.fishkuseller.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import id.fishku.fishkuseller.R
import id.fishku.fishkuseller.api.OrderItem
import id.fishku.fishkuseller.dashboard.adapter.MessagingAdapter
import id.fishku.fishkuseller.dashboard.adapter.MethodMessaging
import id.fishku.fishkuseller.dashboard.adapter.MethodTransaction
import id.fishku.fishkuseller.dashboard.adapter.TransactionAdapter
import id.fishku.fishkuseller.databinding.FragmentTransactionBinding
import kotlin.properties.Delegates

class TransactionFragment : Fragment() {

    private var _binding: FragmentTransactionBinding? = null
    private val binding get() = _binding!!

    private var allOrder by Delegates.notNull<Long>()
    private val dashboardViewModel by viewModels<DashboardViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTransactionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        allOrder = requireActivity().intent.getIntExtra(DashboardActivity.ALL_ORDER, 0).toLong()

        dashboardViewModel.getAllOrders(allOrder)

        dashboardViewModel.sellerOrder.observe(requireActivity()){
            setOrder(it)
        }

        val itemTransaction: List<MethodTransaction> = listOf(
            MethodTransaction("Ikan Nila", "Rp 25.000"),
            MethodTransaction("Ikan Lele", "Rp 20.000"),
            MethodTransaction("Ikan Bandeng", "Rp 27.000"),
        )

        binding.rvTransaction.layoutManager = LinearLayoutManager(requireContext())
        binding.rvTransaction.adapter = TransactionAdapter(itemTransaction)
    }

    private fun setOrder(it: List<OrderItem>) {
        binding.apply {
            rvTransaction.layoutManager = LinearLayoutManager(requireActivity())

        }
    }
}