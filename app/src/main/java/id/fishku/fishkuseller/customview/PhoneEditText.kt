package id.fishku.fishkuseller.customview

import android.content.Context
import android.graphics.Canvas
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import android.text.InputType
import android.util.AttributeSet
import android.view.View
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.widget.addTextChangedListener
import id.fishku.fishkuseller.R

class PhoneEditText : AppCompatEditText, View.OnFocusChangeListener {


    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init()
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        hint = context.getString(R.string.phone_number)
    }

    private fun init() {
        inputType = InputType.TYPE_CLASS_PHONE
        typeface = Typeface.DEFAULT
        onFocusChangeListener = this
        addTextChangedListener {
            validatePhone()
        }
    }

    /**private fun setButtonDrawables(
        startOfTheText: Drawable? = null,
        topOfTheText: Drawable? = null,
        endOfTheText: Drawable? = null,
        bottomOfTheText: Drawable? = null
    ) {
        setCompoundDrawablesWithIntrinsicBounds(
            startOfTheText,
            topOfTheText,
            endOfTheText,
            bottomOfTheText
        )
    }*/

    override fun onFocusChange(v: View?, hasFocus: Boolean) {
        if (!hasFocus) {
            validatePhone()
        }
    }

    private fun validatePhone() {
        val phonePattern = Regex("^\\+?(62|0)\\d{9,11}$")
        val phone = text?.toString()?.trim()
        if (phone.isNullOrEmpty() || !phone.matches(phonePattern)) {
            showError()
        } else {
            error = null
        }
    }

    private fun showError() {
        error = context.getString(R.string.phone_error_message)
    }

}