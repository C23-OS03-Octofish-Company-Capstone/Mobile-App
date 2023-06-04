package id.fishku.fishkuseller.dashboard.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DecodeFormat
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import id.fishku.fishkuseller.api.InventoryItem
import id.fishku.fishkuseller.databinding.ListItemInventoryBinding
import id.fishku.fishkuseller.formatPrice

class InventoryAdapter(private val listInventory: List<InventoryItem>) : RecyclerView.Adapter<InventoryAdapter.ListViewHolder>() {

    private var onEditButtonClickCallback: OnEditButtonClickCallback? = null
    private var onDeleteButtonClickCallback: OnDeleteButtonClickCallback? = null

    fun setOnEditButtonClickCallback(callback: OnEditButtonClickCallback) {
        onEditButtonClickCallback = callback
    }

    fun setOnDeleteButtonClickCallback(callback: OnDeleteButtonClickCallback) {
        onDeleteButtonClickCallback = callback
    }

    interface OnEditButtonClickCallback {
        fun onEditButtonClicked(data: InventoryItem)
    }
    interface OnDeleteButtonClickCallback {
        fun onDeleteButtonClicked(data: InventoryItem)
    }
    class ListViewHolder(var binding: ListItemInventoryBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ListItemInventoryBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ListViewHolder(binding)
    }

    override fun getItemCount(): Int = listInventory.size


    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {

        Glide.with(holder.itemView.context)
            .load(listInventory[position].photoUrl)
            .transition(DrawableTransitionOptions.withCrossFade())
            .centerCrop()
            .format(DecodeFormat.PREFER_RGB_565)
            .into(holder.binding.ivImgItem)
        holder.binding.tvFishName.text = listInventory[position].name
        val price = listInventory[position].price.toDouble()
        val formattedPrice = formatPrice(price)
        val priceText = "Rp. $formattedPrice/kg"
        holder.binding.tvFishPrice.text = priceText

        holder.binding.btnEdit.setOnClickListener {
            onEditButtonClickCallback?.onEditButtonClicked(listInventory[position])
        }

        holder.binding.btnDelete.setOnClickListener {
            onDeleteButtonClickCallback?.onDeleteButtonClicked(listInventory[position])
        }

    }



}