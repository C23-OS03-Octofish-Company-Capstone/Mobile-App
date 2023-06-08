package id.fishku.fishkuseller.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import id.fishku.fishkuseller.R
import id.fishku.fishkuseller.dashboard.adapter.MessagingAdapter
import id.fishku.fishkuseller.dashboard.adapter.MethodMessaging
import id.fishku.fishkuseller.databinding.FragmentMessagingBinding


class MessagingFragment : Fragment() {

    private var _binding: FragmentMessagingBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMessagingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            rvChat.layoutManager = LinearLayoutManager(requireActivity())
        }

        val itemMessaging: List<MethodMessaging> = listOf(
            MethodMessaging(R.drawable.ava, "Ethan", "Hai, saya sudah pesan ikan bandeng", "20.20", "2"),
            MethodMessaging(R.drawable.avadua, "Kinara P", "Oke terima kasih kak", "13.53", "4",),
            MethodMessaging(R.drawable.ava, "Leo", "Tolong dikemas rapi, terima kasih!", "10.39", "3"),
        )

        binding.rvChat.layoutManager = LinearLayoutManager(requireContext())
        binding.rvChat.adapter = MessagingAdapter(itemMessaging)
    }
}