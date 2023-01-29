package quiz.example.hd_wallpaper

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import quiz.example.hd_wallpaper.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    lateinit var navController: NavController
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        APPQ = this
        navController = Navigation.findNavController(this, R.id.nav_fragment)



    }

}

