package id.fishku.fishkuseller.freshness.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.fishku.fishkuseller.databinding.DetectMethodItemBinding

class DetectionMenuAdapter(private val listMenu: List<MethodMenu>) : RecyclerView.Adapter<DetectionMenuAdapter.ListViewHolder>() {

    private var onButtonClickCallback: OnButtonClickCallback? = null

    fun setOnButtonClickCallback(callback: OnButtonClickCallback) {
        onButtonClickCallback = callback
    }


    interface OnButtonClickCallback {
        fun onButtonClicked(data: MethodMenu)
    }
    class ListViewHolder(var binding: DetectMethodItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = DetectMethodItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ListViewHolder(binding)
    }

    override fun getItemCount(): Int = listMenu.size


    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {

        holder.binding.imageView2.setImageResource(listMenu[position].imageResId)
        holder.binding.tvMenuName.text = listMenu[position].text
        holder.binding.root.setOnClickListener{
            onButtonClickCallback?.onButtonClicked(listMenu[position])
        }
    }



}