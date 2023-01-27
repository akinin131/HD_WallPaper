package quiz.example.hd_wallpaper.Category

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import quiz.example.hd_wallpaper.Adapter.PixabayImagesRecyclerView
import quiz.example.hd_wallpaper.PixabayImages
import quiz.example.hd_wallpaper.api.PixabayImagesApi
import quiz.example.hd_wallpaper.databinding.FragmentModaBinding
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


@Suppress("UNREACHABLE_CODE")
class ModaFragment : Fragment() {


    lateinit var binding: FragmentModaBinding
    lateinit var adapter: PixabayImagesRecyclerView

    class MyClass {
        companion object {
            var Qury = ""

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentModaBinding.inflate(layoutInflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val baseUrl = "https://pixabay.com/api/"
        val retrofitBuilder = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val api = retrofitBuilder.create(PixabayImagesApi::class.java)
        pixabayImageQuery(api, "Qury")

        init()

    }

    private fun init() {

        adapter = PixabayImagesRecyclerView()
        val layouManager = GridLayoutManager(context, 3)
        binding.recyclerView.layoutManager = layouManager
        binding.recyclerView.adapter = adapter
        binding.recyclerView.setHasFixedSize(true)

        val itemCount = adapter.itemCount
        if (itemCount > 0) {
            binding.emptyImage.visibility = View.GONE
        } else {
            binding.emptyImage.visibility = View.VISIBLE
        }
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
            body?.hits?.let { webFormatImage ->
                adapter.addItems(webFormatImage)
            }
        }
    }
}