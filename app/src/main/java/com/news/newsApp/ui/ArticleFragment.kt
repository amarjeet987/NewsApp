package com.news.newsApp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.news.newsApp.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_article.*

class ArticleFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_article, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        back.setOnClickListener { requireActivity().onBackPressed() }

        val args = ArticleFragmentArgs.fromBundle(requireArguments())

        Picasso.get()
            .load(args.image)
            .placeholder(R.color.gray)
            .into(image)
        title.text = args.title
        source.text = args.source
        date.text = args.date
        content.text = args.body
    }
}