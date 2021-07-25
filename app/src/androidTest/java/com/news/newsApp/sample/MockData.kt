package com.news.newsApp.sample

import com.news.newsApp.data.models.ArticleData

val dummyArticleAndroidTest = ArticleData.Article(
    "JohnDoe",
    "Some content",
    "Some Desc",
    "2021-06-12T01:53:53Z",
    "Some title",
    "Some url",
    "some other url",
    ArticleData.Article.Source(
        "someId",
        "Some Name"
    )
)

// represents data from API
val dummyArticlesAndroidTest = arrayListOf(1, 2, 3, 4).map {
    dummyArticleAndroidTest.copy(title = "Title $it")
}
