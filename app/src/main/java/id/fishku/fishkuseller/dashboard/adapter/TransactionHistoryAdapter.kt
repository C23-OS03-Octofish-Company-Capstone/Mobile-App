package id.fishku.fishkuseller.dashboard.adapter


import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.fishku.fishkuseller.databinding.ListItemTransactionHistoryBinding
import id.fishku.fishkuseller.notification.NewsNotification


class TransactionHistoryAdapter(private val listHistory: List<MethodTransactionHistory>) : RecyclerView.Adapter<TransactionHistoryAdapter.ListViewHolder>() {

    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback){
        this.onItemClickCallback = onItemClickCallback
    }

    interface OnItemClickCallback{
        fun onItemClicked(data: NewsNotification)
    }

    class ListViewHolder(var binding: ListItemTransactionHistoryBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ListItemTransactionHistoryBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ListViewHolder(binding)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.binding.textView30.text = listHistory[position].title
        holder.binding.textView42.text = listHistory[position].quantity
        holder.binding.textView44.text = listHistory[position].price
        holder.binding.textView40.text = listHistory[position].date
        holder.binding.textView39.text = listHistory[position].total
    }

    override fun getItemCount(): Int = 3
    }