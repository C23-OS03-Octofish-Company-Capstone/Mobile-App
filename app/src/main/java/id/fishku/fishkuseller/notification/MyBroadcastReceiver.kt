package id.fishku.fishkuseller.notification

import android.annotation.SuppressLint
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import id.fishku.fishkuseller.R
import id.fishku.fishkuseller.dashboard.DashboardActivity

class MyBroadcastReceiver : BroadcastReceiver() {
    @RequiresApi(Build.VERSION_CODES.M)
    @SuppressLint("MissingPermission", "UnsafeProtectedBroadcastReceiver")
    override fun onReceive(context: Context, intent: Intent) {
        val repeatingIntent = Intent(context, DashboardActivity::class.java)
        repeatingIntent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP

        val pendingIntent = PendingIntent.getActivity(context, 0, repeatingIntent, PendingIntent.FLAG_IMMUTABLE)

        val builder = NotificationCompat.Builder(context, "Notification")
            .setContentIntent(pendingIntent)
            .setSmallIcon(R.drawable.logo)
            .setContentTitle("FISHKU")
            .setContentText("Order Baru dari Octofish Memesan 3 Kg Ikan Bandeng. Lihat Detailnya Sekarang!")
            .setAutoCancel(true)

        val notificationManagerCompat = NotificationManagerCompat.from(context)
        notificationManagerCompat.notify(200, builder.build())

    }
}