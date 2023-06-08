package id.fishku.fishkuseller.dashboard

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import id.fishku.fishkuseller.R
import id.fishku.fishkuseller.dashboard.adapter.MethodTransactionHistory
import id.fishku.fishkuseller.dashboard.adapter.TransactionHistoryAdapter
import id.fishku.fishkuseller.databinding.ActivityNotificationBinding
import id.fishku.fishkuseller.databinding.ActivityTransactionHistoryBinding
import id.fishku.fishkuseller.notification.adapter.MethodNotif
import id.fishku.fishkuseller.notification.adapter.NotificationOrderAdapter

class TransactionHistoryActivity : AppCompatActivity() {

    private lateinit var historyBinding: ActivityTransactionHistoryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transaction_history)

        historyBinding = ActivityTransactionHistoryBinding.inflate(layoutInflater)
        setContentView(historyBinding.root)

        val itemHistory: List<MethodTransactionHistory> = listOf(
            MethodTransactionHistory("Ikan Nila", "3 Kg", "Rp 25.000","30-05-2023", "Rp 75.000"),
            MethodTransactionHistory("Ikan Lele", "5 Kg", "Rp 20.000","19-05-2023", "Rp 100.000"),
            MethodTransactionHistory("Ikan Bandeng", "1 Kg", "Rp 27.000","01-05-2023", "Rp 27.000"),
        )

        historyBinding.rvTransactionhistory.layoutManager = LinearLayoutManager(this@TransactionHistoryActivity)
        historyBinding.rvTransactionhistory.adapter = TransactionHistoryAdapter(itemHistory)
    }
}