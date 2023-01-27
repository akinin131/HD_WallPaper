package quiz.example.hd_wallpaper.Category

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import quiz.example.hd_wallpaper.APPQ
import quiz.example.hd_wallpaper.Category.ModaFragment.MyClass.Companion.Qury
import quiz.example.hd_wallpaper.R
import quiz.example.hd_wallpaper.databinding.FragmentBlankBinding


class BlankFragment : Fragment() {

    lateinit var binding: FragmentBlankBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentBlankBinding.inflate(layoutInflater, container, false)
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
    }
}