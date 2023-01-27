package quiz.example.hd_wallpaper

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import quiz.example.hd_wallpaper.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var navController: NavController
    private lateinit var binding: ActivityMainBinding

    //lateinit var adapter: PixabayImagesRecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        APPQ = this
        navController = Navigation.findNavController(this, R.id.nav_fragment)

        //supportActionBar?.hide()

        //initRecyclerView()
        //APPQ=this
        //val baseUrl = "https://pixabay.com/api/"

        /*val retrofitBuilder = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()*/

        //val api = retrofitBuilder.create(PixabayImagesApi::class.java)

        //pixabayImageQuery(api, "moda")

    }

    /* private fun initRecyclerView() {
         adapter = PixabayImagesRecyclerView()
         val layouManager = GridLayoutManager(this, 3)
         binding.recyclerView.layoutManager = layouManager
         binding.recyclerView.adapter = adapter
         binding.recyclerView.setHasFixedSize(true)

     }*/

    /* private fun pixabayImageQuery(api: PixabayImagesApi, query: String) {
         api.getWebFormatImages(query)
             .subscribeOn(Schedulers.io())
             .observeOn(AndroidSchedulers.mainThread())
             .subscribe(::handleData) {

                 Log.e("MainActivity", "eror")
             }
     */
}

/*rivate fun handleData(response: Response<PixabayImages>) {
    if (response.isSuccessful) {
        val body = response.body()
        body?.hits?.let {webFormatImage->
            adapter.addItems(webFormatImage)
        }
    }
}*/

