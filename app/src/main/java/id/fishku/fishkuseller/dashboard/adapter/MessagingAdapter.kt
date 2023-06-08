package id.fishku.fishkuseller.dashboard.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.fishku.fishkuseller.databinding.ListItemMessageBinding
import id.fishku.fishkuseller.notification.NewsNotification

class MessagingAdapter(private val listNewsNotification: List<MethodMessaging>) : RecyclerView.Adapter<MessagingAdapter.ListViewHolder>() {

    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback){
        this.onItemClickCallback = onItemClickCallback
    }

    interface OnItemClickCallback{
        fun onItemClicked(data: NewsNotification)
    }

    class ListViewHolder(var binding: ListItemMessageBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ListItemMessageBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ListViewHolder(binding)
    }

    override fun getItemCount(): Int = 3

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.binding.imageView.setImageResource(listNewsNotification[position].image)
        holder.binding.textView18.text = listNewsNotification[position].name
        holder.binding.textView19.text = listNewsNotification[position].massage
        holder.binding.tvTime.text = listNewsNotification[position].time
        holder.binding.textView21.text = listNewsNotification[position].count
    }

}