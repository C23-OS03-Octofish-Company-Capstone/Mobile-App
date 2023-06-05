package id.fishku.fishkuseller.dashboard.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.fishku.fishkuseller.api.OrderItem
import id.fishku.fishkuseller.databinding.ListItemTransactionBinding
import id.fishku.fishkuseller.notification.NewsNotification

class TransactionAdapter(private val listOrderItem: List<OrderItem>) : RecyclerView.Adapter<TransactionAdapter.ListViewHolder>() {

    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback){
        this.onItemClickCallback = onItemClickCallback
    }

    interface OnItemClickCallback{
        fun onItemClicked(data: NewsNotification)
    }

    class ListViewHolder(var binding: ListItemTransactionBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ListItemTransactionBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ListViewHolder(binding)
    }

    override fun getItemCount(): Int = 8

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
    }


}