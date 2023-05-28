package id.fishku.fishkuseller.notification

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import id.fishku.fishkuseller.adapter.NotificationNewsAdapter
import id.fishku.fishkuseller.api.ArticlesItem
import id.fishku.fishkuseller.databinding.FragmentNewsBinding

class NewsFragment : Fragment() {

    private var _binding: FragmentNewsBinding? = null
    private val binding get() = _binding!!
    private val notificationViewModel by lazy{
        ViewModelProvider(this)[NotificationViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentNewsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        notificationViewModel.setNewsData()

        notificationViewModel.listNews.observe(viewLifecycleOwner){
            showNotification(it)
        }

    }

    private fun showNotification(news : List<ArticlesItem>) {
        binding.apply {
            rvNotifNews.layoutManager = LinearLayoutManager(requireActivity())
            rvNotifNews.adapter = NotificationNewsAdapter(news)
        }
    }
}