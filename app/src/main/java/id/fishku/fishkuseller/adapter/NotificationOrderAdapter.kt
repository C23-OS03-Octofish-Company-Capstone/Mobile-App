package id.fishku.fishkuseller.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DecodeFormat
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import id.fishku.fishkuseller.api.ArticlesItem
import id.fishku.fishkuseller.databinding.NotificationNewsItemBinding
import id.fishku.fishkuseller.databinding.NotificationOrderItemBinding
import id.fishku.fishkuseller.notification.NewsNotification

class NotificationOrderAdapter(/**private val listNewsNotification: List<ArticlesItem>*/) : RecyclerView.Adapter<NotificationOrderAdapter.ListViewHolder>() {

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

    override fun getItemCount(): Int = 8

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
      holder.binding.tvOrderTitle.text = "Order ke-$position"
    }


}