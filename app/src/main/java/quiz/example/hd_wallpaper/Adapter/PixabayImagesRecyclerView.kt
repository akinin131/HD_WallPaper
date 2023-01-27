package quiz.example.hd_wallpaper.Adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_image.view.*
import quiz.example.hd_wallpaper.PhotoActivity
import quiz.example.hd_wallpaper.PixabayWebFormatImages
import quiz.example.hd_wallpaper.R


class PixabayImagesRecyclerView : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val items = ArrayList<PixabayWebFormatImages>()

    @SuppressLint("NotifyDataSetChanged")
    fun addItems(items: ArrayList<PixabayWebFormatImages>) {
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        //val context = parent?.context
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_image, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ViewHolder) {
            val url = items[position].webformatImage
            Glide.with(holder.image.context).load(url).into(holder.image)
            holder.itemView.setOnClickListener {
                PhotoActivity.start.start1(caller = holder.image.context, url = url)

            }
        }

    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {

        val image = itemView.pixabay_webformat_image

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(view: View) {
            val position = adapterPosition
        }
    }
}



