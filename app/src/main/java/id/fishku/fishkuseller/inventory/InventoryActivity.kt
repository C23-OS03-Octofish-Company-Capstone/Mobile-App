package id.fishku.fishkuseller.inventory

import android.app.AlertDialog
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.view.ContextThemeWrapper
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DecodeFormat
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import id.fishku.fishkuseller.R
import id.fishku.fishkuseller.dashboard.DashboardActivity
import id.fishku.fishkuseller.dashboard.DashboardViewModel
import id.fishku.fishkuseller.databinding.ActivityInventoryBinding
import kotlin.properties.Delegates

class InventoryActivity : AppCompatActivity() {

    private lateinit var invBinding : ActivityInventoryBinding
    private lateinit var fName: String
    private var fPrice by Delegates.notNull<Long>()
    private var fWeight by Delegates.notNull<Long>()
    private lateinit var fPhoto: String
    private var fishId by Delegates.notNull<Long>()
    private var sellerId by Delegates.notNull<Long>()
    private var isEditing: Boolean = false
    private val inventoryViewModel by viewModels<InventoryViewModel>()
    private val dashboardViewModel by viewModels<DashboardViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        invBinding = ActivityInventoryBinding.inflate(layoutInflater)
        setContentView(invBinding.root)

        supportActionBar?.hide()

        fishId = intent.getLongExtra(ID_FISH, -1L)
        sellerId = intent.getLongExtra(DashboardActivity.SELLER_ID, -1L)

        isEditing = fishId != -1L

        if (isEditing){
            invBinding.apply {
                btnAdd.text = getString(R.string.save_change)
                etName.setText(intent.getStringExtra(NAME_FISH))
                etWeight.setText(intent.getLongExtra(WEIGHT_FISH, 0L).toString())
                etPrice.setText(intent.getLongExtra(PRICE_FISH, 0L).toString())
                Glide.with(this@InventoryActivity)
                    .load(intent.getStringExtra(PHOTO_FISH))
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .centerCrop()
                    .format(DecodeFormat.PREFER_RGB_565)
                    .into(invBinding.ivItem)
            }
        }


        invBinding.btnAdd.setOnClickListener {
            invBinding.apply {
                fName = etName.text.toString().trim()
                val stringPrice = etPrice.text.toString().trim()
                if(stringPrice.isNotEmpty()) {
                    fPrice = stringPrice.toLong()
                }
                val stringWeight = etWeight.text.toString().trim()
                if (stringWeight.isNotEmpty()) {
                    fWeight = stringWeight.toLong()
                }
                fPhoto = "https://images.solopos.com/2021/11/lele-jumbo.jpg"

                val isValid = fName.isNotEmpty() && stringPrice.isNotEmpty() && stringWeight.isNotEmpty()
                if(isValid) {
                    if (isEditing) {
                        inventoryViewModel.postEditFish(fishId, fName, fPrice, fWeight, fName)

                    } else {
                        inventoryViewModel.postFish(sellerId, fName, fPrice, fWeight, fName, fPhoto)

                    }
                    dashboardViewModel.getInventory(sellerId)
                    finish()
                }else{
                    AlertDialog.Builder(ContextThemeWrapper(this@InventoryActivity, R.style.CustomAlertDialogStyle))
                        .setTitle("Error")
                        .setMessage(R.string.input_empty)
                        .setPositiveButton(R.string.btn_ok) { dialog, _ ->
                            dialog.dismiss()
                        }
                        .show()
                }
            }
        }

        inventoryViewModel.fishInputResponse.observe(this){
            successInput(it)
        }



    }

    private fun successInput(it: String) {
        Toast.makeText(this , it, Toast.LENGTH_SHORT).show()
        finish()
    }

    companion object{
        const val ID_FISH = "id_fish"
        const val NAME_FISH = "name_fish"
        const val WEIGHT_FISH = "weight_fish"
        const val PHOTO_FISH = "photo_fish"
        const val PRICE_FISH = "price_fish"
    }

}