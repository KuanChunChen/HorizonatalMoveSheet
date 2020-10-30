package k.c.horizontal.move.sheet.horizontalmovesheet.widget.switchRecyclerview.model

import com.google.gson.annotations.SerializedName

data class SwitchViewModel (

    @SerializedName("title")
    var textTitle: String? = null,

    @SerializedName("button_text")
    var buttonText: String? = null,

    @SerializedName("image_icon")
    var imageIcon: Int? = null
)
