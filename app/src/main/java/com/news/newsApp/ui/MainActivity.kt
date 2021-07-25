package com.news.newsApp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.news.newsApp.R
import com.news.newsApp.viewmodels.NewsApiViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val newsApiViewModel by viewModel<NewsApiViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        newsApiViewModel.errorMessage.observe(this, {
            it?.let {
                Toast.makeText(this, it, Toast.LENGTH_LONG).show() }
            }
        )
    }

    override fun onStart() {
        super.onStart()
        CoroutineScope(IO).launch { newsApiViewModel.getNewsHeadlines() }
    }
}