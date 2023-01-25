package quiz.example.hd_wallpaper

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import quiz.example.hd_wallpaper.Adapter.PixabayImagesRecyclerView
import quiz.example.hd_wallpaper.api.PixabayImagesApi
import quiz.example.hd_wallpaper.databinding.ActivityMainBinding
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity() {


    lateinit var binding: ActivityMainBinding
    lateinit var adapter: PixabayImagesRecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        initRecyclerView()
        APPQ=this
        val baseUrl = "https://pixabay.com/api/"

        val retrofitBuilder = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val api = retrofitBuilder.create(PixabayImagesApi::class.java)

        pixabayImageQuery(api, "moda")

    }

    private fun initRecyclerView() {
        adapter = PixabayImagesRecyclerView()
        val layouManager = GridLayoutManager(this, 3)
        binding.recyclerView.layoutManager = layouManager
        binding.recyclerView.adapter = adapter
        binding.recyclerView.setHasFixedSize(true)

    }

    private fun pixabayImageQuery(api: PixabayImagesApi, query: String) {
        api.getWebFormatImages(query)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(::handleData) {

                Log.e("MainActivity", "eror")
            }
    }

    private fun handleData(response: Response<PixabayImages>) {
        if (response.isSuccessful) {
            val body = response.body()
            body?.hits?.let {webFormatImage->
                adapter.addItems(webFormatImage)
            }
        }
    }
}