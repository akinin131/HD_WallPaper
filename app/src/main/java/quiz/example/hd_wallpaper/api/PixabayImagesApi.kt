package quiz.example.hd_wallpaper.api


import io.reactivex.rxjava3.core.Single
import quiz.example.hd_wallpaper.PixabayImages
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PixabayImagesApi {

    @GET("/api/?key=33106230-b104905cd7ff74ed17e2229af&q=yellow+flowers&image_type=photo\n")
    fun getWebFormatImages(@Query("q") query: String): Single<Response<PixabayImages>>
}