package android.com.androiodscan.util

import android.com.androiodscan.data.Criteria
import android.com.androiodscan.data.MessageDetail
import android.com.androiodscan.data.Variable
import android.com.androiodscan.ui.module.main.MainActivity
import android.content.Intent
import android.graphics.Color
import android.text.SpannableString
import android.text.Spanned
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.View
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.databinding.BindingConversion
import org.greenrobot.eventbus.EventBus

object BindingAdapters {

    @JvmStatic
    @BindingConversion
    fun convertToCapital(string: String?): String?{
        return string?.toUpperCase()
    }

    @JvmStatic
    @BindingConversion
    fun convertToCapitalize(string: String?): String?{
        return string?.capitalize()
    }

    @JvmStatic
    @BindingConversion
    fun isIndicator(variable: Variable?): Boolean{
        return variable?.let {
            variable.type == "indicator"
        } ?: false
    }


    @JvmStatic
    @BindingConversion
    fun convertStringToColor(colorString: String): Int{
        return when (colorString){
            "red"->{
                Color.RED
            }
            "green"->{
                Color.GREEN
            }
            else->{
                Color.WHITE
            }
        }
    }

    @JvmStatic
    @BindingAdapter("app:specialText")
    fun setLinks(view: TextView, criteria: Criteria){
        if (criteria.type == "plain_text"){
            view.text = criteria.text
        }else{
            var criteriaText = criteria.text
            criteria.variable?.forEach {entry ->

                criteriaText = when (entry.value.type){
                    "indicator" -> {
                        entry.value.firstIndex = criteriaText?.indexOf(entry.key)
                        entry.value.spanSize = entry.value.default_value.toString().length
                        criteriaText?.replace(entry.key,"(${entry.value.default_value})")
                    }
                    "value" -> {
                        entry.value.firstIndex = criteriaText?.indexOf(entry.key)
                        entry.value.values?.get(0)?.let {firstValue ->
                            if(firstValue%1 == 0f) {
                                entry.value.spanSize = firstValue.toInt().toString().length
                                criteriaText?.replace(entry.key, "(${firstValue.toInt()})")
                            } else {
                                entry.value.spanSize = firstValue.toString().length
                                criteriaText?.replace(entry.key, "($firstValue)")
                            }
                        }
                    }
                    else -> ""
                }
            }
            var spannableString = SpannableString(criteriaText)
            criteria.variable?.forEach { entry ->
                spannableString.setSpan(object : ClickableSpan(){
                    override fun updateDrawState(ds: TextPaint) {
                        super.updateDrawState(ds)
                        ds.isUnderlineText = false
                    }

                    override fun onClick(widget: View) {
                        EventBus.getDefault().post(MessageDetail(entry.value))
                    }
                }, entry.value.firstIndex?.let { it } ?: return, entry.value.firstIndex?.let { it+2+entry.value.spanSize } ?: return, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
            }

            view.movementMethod = LinkMovementMethod.getInstance()
            view.text = spannableString
        }
    }
}