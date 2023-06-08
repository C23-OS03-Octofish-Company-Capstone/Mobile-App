package id.fishku.fishkuseller.prediction.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.fishku.fishkuseller.databinding.ListItemPriceBinding
import id.fishku.fishkuseller.formatPrice
import java.util.*

class PriceAdapter(private val listGrosir: List<Double>, private val listEceran: List<Double>) : RecyclerView.Adapter<PriceAdapter.ListViewHolder>() {

    private val monthNames = arrayOf(
        "Jan", "Feb", "Mar", "Apr", "Mei", "Jun",
        "Jul", "Agu", "Sep", "Okt", "Nov", "Des"
    )

    class ListViewHolder(var binding: ListItemPriceBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ListItemPriceBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ListViewHolder(binding)
    }

    override fun getItemCount(): Int = listGrosir.size

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.MONTH, position+1)
        val month = calendar.get(Calendar.MONTH)
        val currentMonth = monthNames[month % 12]
        val priceEceran = formatPrice(listEceran[position].toInt().toDouble())
        val priceGrosir = formatPrice(listGrosir[position].toInt().toDouble())
        holder.binding.tvBulan.text = currentMonth
        holder.binding.tvHargaEceran.text = "Rp. $priceEceran/kg"
        holder.binding.tvHargaGrosir.text = "Rp. $priceGrosir/kg"
    }


}