package id.fishku.fishkuseller.notification

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class NewsNotification(
    val title: String,
    val desc: String,
    val photo: String

) : Parcelable

@Parcelize
data class OrderNotification(
    val orderId: String,
    val orderTotal: String
): Parcelable
