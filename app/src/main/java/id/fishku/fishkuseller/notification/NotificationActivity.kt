package id.fishku.fishkuseller.notification

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import id.fishku.fishkuseller.R
import id.fishku.fishkuseller.databinding.ActivityNotificationBinding
import id.fishku.fishkuseller.notification.adapter.MethodNotif
import id.fishku.fishkuseller.notification.adapter.NotificationOrderAdapter

class NotificationActivity : AppCompatActivity() {

    private lateinit var notifBinding: ActivityNotificationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        notifBinding = ActivityNotificationBinding.inflate(layoutInflater)
        setContentView(notifBinding.root)

        notifBinding.apply {
            rvNotifOrder.layoutManager = LinearLayoutManager(this@NotificationActivity)
        }

        val itemNotif: List<MethodNotif> = listOf(
            MethodNotif(R.drawable.ikan_bandeng, "ID 234QYYY33", "Order Baru dari Octofish Memesan 3 Kg Ikan Bandeng. Lihat Detailnya Sekarang!"),
            MethodNotif(R.drawable.ikan_lele, "ID 531QKTU12", "Order Baru dari Caramel Memesan 5 Kg Ikan Lele. Lihat Detailnya Sekarang!"),
            MethodNotif(R.drawable.logo, "PENGINGAT", "Terdapat 2 Pesanan yang Belum Dikirim, Segera Kemas Pesananmu"),
            MethodNotif(R.drawable.ikan_gurame, "ID 920HWBF99", "Order Baru dari Sinta Memesan 1 Kg Ikan Gurame. Lihat Detailnya Sekarang!"),
            MethodNotif(R.drawable.ikan_nila, "ID 578HHNZ05", "Order Baru dari Reyhan Memesan 7 Kg Ikan Nila. Lihat Detailnya Sekarang!"),
        )

        notifBinding.rvNotifOrder.layoutManager = LinearLayoutManager(this@NotificationActivity)
        notifBinding.rvNotifOrder.adapter = NotificationOrderAdapter(itemNotif)

    }
}