package id.fishku.fishkuseller.notification

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.tabs.TabLayoutMediator
import id.fishku.fishkuseller.databinding.ActivityNotificationBinding
import id.fishku.fishkuseller.notification.adapter.NotificationPagerAdapter

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
                    0 -> tabs.text = "Orders"
                    1 -> tabs.text = "News"
                }
            }.attach()
        }
    }
}