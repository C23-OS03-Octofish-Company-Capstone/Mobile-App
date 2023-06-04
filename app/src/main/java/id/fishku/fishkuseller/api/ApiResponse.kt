package id.fishku.fishkuseller.api

import com.google.gson.annotations.SerializedName



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
	val id: Long,

	@field:SerializedName("nama_bank")
	val namaBank: Any,

	@field:SerializedName("photo_url")
	val photoUrl: Any,

	@field:SerializedName("email")
	val email: String
)

data class ProfileResponse(

	@field:SerializedName("banyak")
	val banyak: Long,

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

data class InventoryResponse(

	@field:SerializedName("banyak")
	val banyak: Long,

	@field:SerializedName("data")
	val data: List<InventoryItem>
)

data class InventoryItem(

	@field:SerializedName("id_fish")
	val idFish: Long,

	@field:SerializedName("price")
	val price: Long,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("weight")
	val weight: Long,

	@field:SerializedName("photo_url")
	val photoUrl: String
)
