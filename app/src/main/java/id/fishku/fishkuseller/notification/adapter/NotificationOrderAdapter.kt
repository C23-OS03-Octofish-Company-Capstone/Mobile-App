package id.fishku.fishkuseller.notification.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.fishku.fishkuseller.databinding.NotificationOrderItemBinding
import id.fishku.fishkuseller.notification.NewsNotification

class NotificationOrderAdapter(private val listNewsNotification: List<MethodNotif>) : RecyclerView.Adapter<NotificationOrderAdapter.ListViewHolder>() {

    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback){
        this.onItemClickCallback = onItemClickCallback
    }

    interface OnItemClickCallback{
        fun onItemClicked(data: NewsNotification)
    }

    class ListViewHolder(var binding: NotificationOrderItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = NotificationOrderItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ListViewHolder(binding)
    }

    override fun getItemCount(): Int = 5

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        //holder.binding.tvOrderTitle.text = "Order"
        holder.binding.ivImg.setImageResource(listNewsNotification[position].imageNotif)
        holder.binding.tvOrderTitle.text = listNewsNotification[position].title
        holder.binding.tvOrderTotal.text = listNewsNotification[position].desc
    }


}