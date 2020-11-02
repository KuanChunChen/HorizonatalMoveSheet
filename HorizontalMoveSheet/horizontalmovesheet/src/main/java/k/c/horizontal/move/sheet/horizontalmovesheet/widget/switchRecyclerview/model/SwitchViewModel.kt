package k.c.horizontal.move.sheet.horizontalmovesheet.widget.switchRecyclerview.model

import androidx.fragment.app.Fragment
import com.google.gson.annotations.SerializedName

data class SwitchViewModel (

    @SerializedName("title")
    var textTitle: String? = null,

    @SerializedName("image_icon")
    var imageIcon: Int? = null,

    @SerializedName("url")
    var url: String? = null
)
