package quiz.example.hd_wallpaper

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class PixabayImages {
    @SerializedName("hits")
    @Expose
    val hits: ArrayList<PixabayWebFormatImages>? = null
}

class PixabayWebFormatImages {
    @SerializedName("webformatURL")
    @Expose
    val webformatImage: String? = null
}