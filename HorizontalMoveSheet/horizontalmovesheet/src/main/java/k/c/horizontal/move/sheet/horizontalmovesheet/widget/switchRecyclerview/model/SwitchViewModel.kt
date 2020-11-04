/**
 * Created by Elegant Access's KC on 11/4/20 12:13 PM.
 * Copyright (c) 2020 All rights reserved.
 * Your support is my biggest motivation , please follow my Instagram  : https://www.instagram.com/eleg.aces.kc/
 * See more project on github : https://github.com/KuanChunChen
 * See tutorial on my site : https://medium.com/@elegant-access-kc
 */

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
