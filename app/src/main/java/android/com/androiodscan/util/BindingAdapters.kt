package android.com.androiodscan.util

import android.graphics.Color
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
}