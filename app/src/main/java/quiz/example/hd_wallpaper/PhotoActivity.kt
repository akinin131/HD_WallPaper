package quiz.example.hd_wallpaper

import android.Manifest
import android.annotation.SuppressLint
import android.app.WallpaperManager
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.view.*
import android.view.View.OnTouchListener
import android.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.davemorrissey.labs.subscaleview.ImageSource
import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView
import com.google.android.material.snackbar.Snackbar
import com.nostra13.universalimageloader.core.ImageLoader
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener
import kotlinx.android.synthetic.main.activity_splashe_screen.*
import quiz.example.hd_wallpaper.PhotoActivity.MyClass.Companion.EXTRA_URL
import quiz.example.hd_wallpaper.databinding.ActivityPhotoBinding

import java.io.IOException

@Suppress("DEPRECATED_IDENTITY_EQUALS")
class PhotoActivity : AppCompatActivity() {

    lateinit var binding: ActivityPhotoBinding

    private val REQUEST_PERMISSION_WRITE_STORAGE = 1111


    class MyClass {
        companion object {
            val EXTRA_URL = "PhotoActivity.EXTRA_URL"

        }
    }

    private var imageView: SubsamplingScaleImageView? = null
    private var toolbar: Toolbar? = null

    private var isToolbarVisible = false

    private var photo: Bitmap? = null

    class start() {

        companion object {

            fun start1(caller: Context, url: String?) {

                val intent = Intent(caller, PhotoActivity::class.java)
                intent.putExtra(EXTRA_URL, url)
                caller.startActivity(intent)
            }
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
        binding = ActivityPhotoBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_photo)
        imageView = findViewById(R.id.image)
        toolbar = findViewById(R.id.toolbar)
        toolbar?.setTitle("")
        // setSupportActionBar(toolbar.)

        ImageLoader.getInstance()
            .loadImage(intent.getStringExtra(EXTRA_URL), object : SimpleImageLoadingListener() {
                override fun onLoadingComplete(imageUri: String, view: View?, loadedImage: Bitmap) {
                    if (!isFinishing) {
                        photo = loadedImage
                        imageView?.setImage(ImageSource.cachedBitmap(loadedImage))

                    }
                }
            })
        toolbar?.post {
            if (!isFinishing) {
                hideActionBar()
            }
        }
        imageView?.setOnClickListener(View.OnClickListener { v: View? ->
            if (photo != null && !isToolbarVisible) {
                showActionBar()
            }
        })
        imageView?.setOnTouchListener(OnTouchListener { view: View?, motionEvent: MotionEvent ->
            if (motionEvent.action != MotionEvent.ACTION_DOWN
                && motionEvent.action != MotionEvent.ACTION_UP
            ) {
                if (isToolbarVisible) {
                    hideActionBar()
                }
            }
            false
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_photo, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_set_wallpaper -> {
                setWallpaper()
                hideActionBar()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    private fun hideActionBar() {
        toolbar!!.animate().translationY((-toolbar!!.height).toFloat()).setDuration(300).start()
        isToolbarVisible = false
    }

    private fun showActionBar() {
        toolbar!!.animate().translationY(0F).setDuration(300).start()
        isToolbarVisible = true
    }

    private fun performSharing() {
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            ) === PackageManager.PERMISSION_GRANTED
        ) {
            val path = MediaStore.Images.Media.insertImage(
                contentResolver,
                photo,
                intent.getStringExtra(EXTRA_URL),
                ""
            )
            val uri = Uri.parse(path)
            val intent = Intent(Intent.ACTION_SEND)
            intent.type = "image/jpeg"
            intent.putExtra(Intent.EXTRA_STREAM, uri)
            //startActivity(Intent.createChooser(intent, getString(R.string.share)))
        } else {
            requestPermissions(
                arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                REQUEST_PERMISSION_WRITE_STORAGE
            )
        }
    }

    private fun setWallpaper() {
        val wallpaperManager = WallpaperManager.getInstance(applicationContext)
        try {
            wallpaperManager.setBitmap(photo)
            imageView?.let {
                Snackbar.make(
                    it,
                    getString(R.string.set_as_wallpaper_completed),
                    Snackbar.LENGTH_SHORT
                ).show()
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String?>,
        grantResults: IntArray,
    ) {
        if (requestCode == REQUEST_PERMISSION_WRITE_STORAGE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                performSharing()
            }
        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        }
    }
}


