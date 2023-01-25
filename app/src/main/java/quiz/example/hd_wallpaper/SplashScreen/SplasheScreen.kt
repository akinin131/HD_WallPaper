package quiz.example.hd_wallpaper.SplashScreen

import android.animation.ObjectAnimator
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import quiz.example.hd_wallpaper.MainActivity
import quiz.example.hd_wallpaper.databinding.ActivitySplasheScreenBinding

class SplasheScreen : AppCompatActivity() {
    lateinit var binding:ActivitySplasheScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplasheScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
       // https://pixabay.com/api/?key=33106230-b104905cd7ff74ed17e2229af&category=moda
        CoroutineScope(Dispatchers.Main).launch {
            binding.progressBar.max = 1000
            val value = 1000
            ObjectAnimator.ofInt(binding.progressBar,"progress",value).setDuration(1000).start()
            delay(1000)
            startActivity(Intent(this@SplasheScreen, MainActivity::class.java))
        }
    }
}