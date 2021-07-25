package com.news.newsApp.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.news.newsApp.R
import com.news.newsApp.ui.utils.RecyclerViewAdapter
import com.news.newsApp.viewmodels.NewsApiViewModel
import kotlinx.android.synthetic.main.fragment_home.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private val viewModel by viewModel<NewsApiViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        newsView.layoutManager = LinearLayoutManager(requireContext())
        val adapter = RecyclerViewAdapter(listOf(), Navigation.findNavController(view))
        newsView.adapter = adapter
        if(!viewModel.newsData.hasActiveObservers()) {
            viewModel.newsData.observe(requireActivity(), {
                it?.let {
                    Log.d("CHECK_SIZE","${adapter.articles.size} --- ${it.size}")
                    adapter.articles = it
                    adapter.notifyDataSetChanged()
                }
            })
        }
    }

    override fun onStop() {
        super.onStop()
        viewModel.newsData.removeObservers(requireActivity())
    }
}