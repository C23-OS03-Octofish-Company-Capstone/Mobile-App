package id.fishku.fishkuseller

import java.text.NumberFormat
import java.util.*

fun formatPrice(price: Double): String {
    val million = 1000000
    val numberFormat = NumberFormat.getNumberInstance(Locale.getDefault())

    return when {
        price >= million -> {
            val formattedPrice = price / million
            "${numberFormat.format(formattedPrice)}m"
        }
        else -> {
            numberFormat.format(price)
        }
    }
}