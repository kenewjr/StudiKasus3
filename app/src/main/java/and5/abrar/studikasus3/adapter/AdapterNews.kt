package and5.abrar.studikasus3.adapter

import and5.abrar.studikasus3.R
import and5.abrar.studikasus3.model.RespondNewsItem
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_news.view.*

class AdapterNews(private val listDataNews: List<RespondNewsItem>,private var onClik : (RespondNewsItem)->Unit):RecyclerView.Adapter<AdapterNews.ViewHolder>() {
    class ViewHolder(itemView : View):RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewitem = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_news,parent, false)
        return ViewHolder(viewitem)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.cardNews.setOnClickListener {
            onClik(listDataNews[position])
        }

        holder.itemView.namaNews.text = listDataNews[position].title

        Glide.with(holder.itemView.context)
            .load(listDataNews[position].image)
            .into(holder.itemView.gambarNews)

        holder.itemView.Director.text = listDataNews[position].author

        holder.itemView.createAt.text = listDataNews[position].createdAt
    }

    override fun getItemCount(): Int {
        return listDataNews.size
    }
}