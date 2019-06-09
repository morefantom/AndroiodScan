package android.com.androiodscan.util

import android.com.androiodscan.data.Criteria
import android.com.androiodscan.ui.module.main.MainActivity
import android.content.Intent
import android.graphics.Color
import android.text.SpannableString
import android.text.Spanned
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.databinding.BindingConversion

object BindingAdapters {

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
                        criteriaText?.replace(entry.key,"(${entry.value.default_value})")
                    }
                    "value" -> {
                        entry.value.values?.get(0)?.let {firstValue ->
                            if(firstValue%1 == 0f)
                                criteriaText?.replace(entry.key,"(${firstValue.toInt()})")
                            else
                                criteriaText?.replace(entry.key,"($firstValue)")
                        }
                    }
                    else -> ""
                }
            }
            var spannableString = SpannableString(criteriaText)
            criteria.variable?.forEach { entry ->
                val startIndex = criteriaText?.indexOf("(") ?: return
                spannableString.setSpan(object : ClickableSpan(){
                    override fun updateDrawState(ds: TextPaint) {
                        super.updateDrawState(ds)
                        ds.isUnderlineText = false
                    }

                    override fun onClick(widget: View) {
                        widget.context.startActivity(Intent(widget.context, MainActivity::class.java))
                    }
                }, startIndex, startIndex+entry.key.length+2, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
            }

            view.movementMethod = LinkMovementMethod.getInstance()
            view.text = spannableString
        }
    }
}