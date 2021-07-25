package com.news.newsApp.ui.utils

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.news.newsApp.R
import com.news.newsApp.data.models.ArticleData
import com.news.newsApp.ui.HomeFragmentDirections
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.news_individual_card.view.*
import java.text.SimpleDateFormat
import java.util.*

class RecyclerViewAdapter(
    var articles: List<ArticleData.Article>,
    val navController: NavController
): RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.news_individual_card, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val title = articles[position].title
        val source = articles[position].source?.name ?: ""
        val img = if(articles[position].urlToImage == "") null else articles[position].urlToImage
        val content = articles[position].description ?: ""

        val date: String = try {
            val formatter = SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss'Z'", Locale.getDefault())
            val unformattedDate = formatter.parse(articles[position].publishedAt ?: "") as Date
            val reqFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            reqFormat.format(unformattedDate) ?: ""
        } catch (e: Exception) { "" }

        holder.date.text = date
        holder.source.text = source
        Picasso.get()
            .load(img)
            .placeholder(R.color.gray)
            .into(holder.img)
        holder.title.text = title

        val action = HomeFragmentDirections
            .actionHomeFragmentToArticleFragment(title, source, date, content, img)

        holder.card.setOnClickListener {
            it.isClickable = false
            navController.navigate(action)
        }
    }

    override fun getItemCount() = articles.size

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val card: CardView = itemView.card
        val img: ImageView = itemView.img
        val title: TextView = itemView.title
        val source: TextView = itemView.source
        val date: TextView = itemView.date
    }
}