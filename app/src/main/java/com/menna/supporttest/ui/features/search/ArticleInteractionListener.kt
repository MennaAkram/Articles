package com.menna.supporttest.ui.features.search

interface ArticleInteractionListener {
    fun onClickFavorite(id: String)
    fun onChangeSearchingText(query: String)
    fun onClickTryAgain()
}