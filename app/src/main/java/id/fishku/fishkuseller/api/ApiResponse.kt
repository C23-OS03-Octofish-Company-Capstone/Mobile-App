package id.fishku.fishkuseller.api

import com.google.gson.annotations.SerializedName
import java.util.*


data class RegisterResponse(

	@field:SerializedName("message")
	val message: String
)
data class LoginResponse(

	@field:SerializedName("data")
	val data: List<DataItem>,

	@field:SerializedName("token")
	val token: String
)

data class DataItem(

	@field:SerializedName("password")
	val password: String,

	@field:SerializedName("nomor_rekening")
	val nomorRekening: Any,

	@field:SerializedName("roles")
	val roles: String,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("phone_number")
	val phoneNumber: String,

	@field:SerializedName("location")
	val location: String,

	@field:SerializedName("nama_pemilik_rekening")
	val namaPemilikRekening: Any,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("nama_bank")
	val namaBank: Any,

	@field:SerializedName("photo_url")
	val photoUrl: Any,

	@field:SerializedName("email")
	val email: String
)

data class ProfileResponse(

	@field:SerializedName("banyak")
	val banyak: Int,

	@field:SerializedName("data")
	val data: List<ProfileItem>
)

data class ProfileItem(

	@field:SerializedName("roles")
	val roles: String,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("phone_number")
	val phoneNumber: String,

	@field:SerializedName("location")
	val location: String,

	@field:SerializedName("photo_url")
	val photoUrl: Any
)

data class OrderResponse(
	@field:SerializedName("banyak")
	val banyak: Int,

	@field:SerializedName("data")
	val data: List<OrderItem>
)

data class OrderItem(
	@field:SerializedName("id_order")
	val idOrder: Int,

	@field:SerializedName("date")
	val date: Date,

	@field:SerializedName("notes")
	val notes: String,

	@field:SerializedName("status")
	val status: String,

	@field:SerializedName("total_price")
	val totalPrice: String,

	@field:SerializedName("photo_url")
	val photoUrl: Any
)