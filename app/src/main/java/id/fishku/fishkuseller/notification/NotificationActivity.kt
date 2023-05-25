package id.fishku.fishkuseller.notification

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import id.fishku.fishkuseller.R
import id.fishku.fishkuseller.databinding.ActivityNotificationBinding

class NotificationActivity : AppCompatActivity() {

    private lateinit var notifBinding : ActivityNotificationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        notifBinding = ActivityNotificationBinding.inflate(layoutInflater)
        setContentView(notifBinding.root)
    }
}