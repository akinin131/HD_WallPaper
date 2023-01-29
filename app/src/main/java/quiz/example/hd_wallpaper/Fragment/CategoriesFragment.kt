package quiz.example.hd_wallpaper.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import quiz.example.hd_wallpaper.APPQ
import quiz.example.hd_wallpaper.Fragment.WallpaperOneFragment.MyClass.Companion.Qury
import quiz.example.hd_wallpaper.R
import quiz.example.hd_wallpaper.databinding.FragmentCategoriesBinding


class CategoriesFragment : Fragment() {

    lateinit var binding: FragmentCategoriesBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCategoriesBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    fun init() {
        binding.imageModa.setOnClickListener {
            APPQ.navController.navigate(R.id.action_blankFragment_to_modaFragment)
            Qury = "moda"
        }
        binding.imageViewmountains.setOnClickListener {
            APPQ.navController.navigate(R.id.action_blankFragment_to_modaFragment)
            Qury = "mountains"
        }
        binding.imageViewForest.setOnClickListener {
            APPQ.navController.navigate(R.id.action_blankFragment_to_modaFragment)
            Qury = "Forest"
        }
        binding.imageViewSportCar.setOnClickListener {
            APPQ.navController.navigate(R.id.action_blankFragment_to_modaFragment)
            Qury = "Sport Car"
        }
        binding.imageViewMegapolis.setOnClickListener {
            APPQ.navController.navigate(R.id.action_blankFragment_to_modaFragment)
            Qury = "metropolis"
        }
        binding.imageViewLake.setOnClickListener {
            APPQ.navController.navigate(R.id.action_blankFragment_to_modaFragment)
            Qury = "Lake"
        }
    }
}