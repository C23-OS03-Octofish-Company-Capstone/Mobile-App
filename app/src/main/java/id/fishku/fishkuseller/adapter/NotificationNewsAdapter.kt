package id.fishku.fishkuseller.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DecodeFormat
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import id.fishku.fishkuseller.api.ArticlesItem
import id.fishku.fishkuseller.databinding.NotificationNewsItemBinding
import id.fishku.fishkuseller.notification.NewsNotification

class NotificationNewsAdapter(private val listNewsNotification: List<ArticlesItem>) : RecyclerView.Adapter<NotificationNewsAdapter.ListViewHolder>() {

    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback){
        this.onItemClickCallback = onItemClickCallback
    }

    interface OnItemClickCallback{
        fun onItemClicked(data: NewsNotification)
    }

    class ListViewHolder(var binding: NotificationNewsItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = NotificationNewsItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ListViewHolder(binding)
    }

    override fun getItemCount(): Int = listNewsNotification.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.binding.tvNewsTitle.text = listNewsNotification[position].title
        holder.binding.tvNewsDesc.text = listNewsNotification[position].description
        Glide.with(holder.itemView.context)
            .load(listNewsNotification[position].urlToImage)
            .transition(DrawableTransitionOptions.withCrossFade())
            .format(DecodeFormat.PREFER_RGB_565)
            .into(holder.binding.ivImgNews)
    }


}