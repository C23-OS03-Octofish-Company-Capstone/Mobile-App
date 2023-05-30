package id.fishku.fishkuseller.notification.adapter

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import id.fishku.fishkuseller.notification.NewsFragment
import id.fishku.fishkuseller.notification.OrderFragment

class NotificationPagerAdapter(activity: AppCompatActivity): FragmentStateAdapter(activity) {


    override fun getItemCount(): Int = 2


    override fun createFragment(position: Int): Fragment {
        val fragment =
            when(position){
                0 -> OrderFragment()
                1 -> NewsFragment()
                else -> OrderFragment()
            }
        return fragment
    }
}