package id.fishku.fishkuseller.notification

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import androidx.core.app.NotificationManagerCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.google.firebase.messaging.ktx.remoteMessage
import id.fishku.fishkuseller.R

class PushNotificationService : FirebaseMessagingService() {

    private lateinit var title : String
    private lateinit var body : String
    private lateinit var channel: NotificationChannel

    companion object{
        const val CHANNEL_ID = "HEADS_UP_NOTIFICATION"
    }

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)

        title = message.notification?.title.toString()
        body = message.notification?.body.toString()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            channel = NotificationChannel(CHANNEL_ID, "MyNotification", NotificationManager.IMPORTANCE_HIGH)
            val notificationManager = getSystemService(NotificationManager::class.java)
            notificationManager.createNotificationChannel(channel)
            val notification = Notification.Builder(this, CHANNEL_ID)
                .setContentTitle(title)
                .setContentText(body)
                .setSmallIcon(R.drawable.logo)
                .setAutoCancel(true)
        }






    }

}