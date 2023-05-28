package id.fishku.fishkuseller.notification

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import id.fishku.fishkuseller.R
import id.fishku.fishkuseller.adapter.NotificationNewsAdapter
import id.fishku.fishkuseller.adapter.NotificationOrderAdapter
import id.fishku.fishkuseller.databinding.FragmentNewsBinding
import id.fishku.fishkuseller.databinding.FragmentOrderBinding


class OrderFragment : Fragment() {

    private var _binding: FragmentOrderBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentOrderBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            rvNotifOrder.layoutManager = LinearLayoutManager(requireActivity())
            rvNotifOrder.adapter = NotificationOrderAdapter()
        }
    }
}