package quiz.example.hd_wallpaper

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import io.reactivex.rxjava3.schedulers.Schedulers.start
import kotlinx.android.synthetic.main.item_image.view.*


class PixabayImagesRecyclerView(val context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val items = ArrayList<PixabayWebFormatImages>()

    fun addItems(items: ArrayList<PixabayWebFormatImages>) {
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_image, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ViewHolder) {
            val url = items[position].webformatImage
            Glide.with(holder.image.context).load(url).into(holder.image)

        }

    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

        val image = itemView.pixabay_webformat_image
        val image1: PixabayWebFormatImages = TODO()

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(view: View) {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                val dw = items[position]
                val intent = Intent(context, PhotoActivity::class.java).apply {
                    putExtra(SunsetPhotoActivity.EXTRA_SUNSET_PHOTO, 2)

                }
                //startActivity(intent)
            }


        }
    }
}



