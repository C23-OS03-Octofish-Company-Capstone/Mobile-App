package id.fishku.fishkuseller.notification

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import id.fishku.fishkuseller.databinding.ActivityNotificationBinding
import id.fishku.fishkuseller.notification.adapter.NotificationOrderAdapter

class NotificationActivity : AppCompatActivity() {

    private lateinit var notifBinding: ActivityNotificationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        notifBinding = ActivityNotificationBinding.inflate(layoutInflater)
        setContentView(notifBinding.root)

        notifBinding.apply {
            rvNotifOrder.layoutManager = LinearLayoutManager(this@NotificationActivity)
            rvNotifOrder.adapter = NotificationOrderAdapter()
        }

    }
}