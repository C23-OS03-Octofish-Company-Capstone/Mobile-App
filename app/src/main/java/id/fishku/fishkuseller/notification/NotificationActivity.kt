package id.fishku.fishkuseller.notification

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.tabs.TabLayoutMediator
import id.fishku.fishkuseller.R
import id.fishku.fishkuseller.adapter.NotificationPagerAdapter
import id.fishku.fishkuseller.databinding.ActivityNotificationBinding

class NotificationActivity : AppCompatActivity() {

    private lateinit var notifBinding : ActivityNotificationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        notifBinding = ActivityNotificationBinding.inflate(layoutInflater)
        setContentView(notifBinding.root)

        val pagerAdapter = NotificationPagerAdapter(this)
        with(notifBinding){
            viewPager.adapter = pagerAdapter
            TabLayoutMediator(tabs, viewPager){tabs,position ->
                when (position){
                    0 -> tabs.text = "News"
                    1 -> tabs.text = "Orders"
                }
            }.attach()
        }
    }
}