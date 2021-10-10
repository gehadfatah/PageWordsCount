package com.goda.pageword.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import com.goda.data.local.models.Word
import com.goda.data.models.WordPresentationModel
import com.goda.pageword.R
import com.goda.pageword.core.app.PageCountWordsApplication
import com.goda.pageword.core.app.di.AppDi
import com.goda.pageword.core.app.di.PresentationDI
import com.goda.pageword.databinding.ActivityMainBinding
import com.goda.pageword.presentation.common.WordListUtil
import com.goda.pageword.presentation.common.showMessage
import com.goda.pageword.presentation.common.toggleVisibility
import com.goda.pageword.presentation.mappers.toWordsList
import com.goda.pageword.presentation.models.WordsListEventData
import com.goda.pageword.presentation.viewmodels.WordsFragmentViewModel
import com.goda.pageword.ui.adapter.WordsListAdapter

class PageWordsCountActivity : AppCompatActivity(), SearchView.OnQueryTextListener {

    private lateinit var appDi: AppDi
    private lateinit var viewModel: WordsFragmentViewModel
    private val wordsAdapter = WordsListAdapter()
    private var binding: ActivityMainBinding? = null
    var listWord = arrayListOf<Word>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        setListner()
        appDi = (application as PageCountWordsApplication).appDi
        appDi.presentationDi = PresentationDI(appDi.dataDi)
        appDi.presentationDi?.viewModelFactory?.create()?.let {
            viewModel = it
        }

        viewModel.fetchMappedResponse()
        viewModel.receivedData.observe(this, Observer {
            when (it) {
                WordsListEventData.LoadingState -> loadingStatus(true)

                is WordsListEventData.SuccessState -> handleSuccessState(it.data)

                is WordsListEventData.ErrorState -> handleErrorState(it.throwable)
            }
        })
    }

    private fun setListner() {
        binding?.let {
            with(it) {

            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        val searchViewMenuItem = menu.findItem(R.id.action_search)
        val searchView = searchViewMenuItem.actionView as SearchView
        searchView.setOnQueryTextListener(this)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_sort) {
            wordsAdapter.reSort(
                WordListUtil.reSortList(list = ArrayList(listWord), isAscending = wordsAdapter.isAscending)

            )
            binding?.wordsRV?.smoothScrollToPosition(0)

        }
        return super.onOptionsItemSelected(item)
    }

    private fun loadingStatus(status: Boolean) {
        when (status) {
            true -> binding?.progressBar?.visibility = View.VISIBLE

            false -> binding?.progressBar?.visibility = View.GONE
        }
    }

    private fun handleSuccessState(data: WordPresentationModel) {
        loadingStatus(false)
        binding?.wordsRV?.adapter = wordsAdapter
        listWord= ArrayList(data.data.toWordsList())
        wordsAdapter.submitList(data.data.toWordsList())
        binding?.let {
            with(it) {
                emptyState.toggleVisibility(data.data.toWordsList().isEmpty())
                wordsRV.toggleVisibility(data.data.toWordsList().isNotEmpty())
            }

        }
    }

    private fun handleErrorState(throwable: Throwable) {
        loadingStatus(false)
        this.showMessage(throwable)

    }


    override fun onQueryTextSubmit(query: String?): Boolean {
        val newList =
            if (query.isNullOrEmpty()) listWord else listWord.filter { item ->
                item.content.contains(query, true)
            }
        wordsAdapter.search( newList)
        return true
    }

    override fun onQueryTextChange(query: String?): Boolean {
        val newList =
            if (query.isNullOrEmpty()) listWord else listWord.filter { item ->
                item.content.contains(query, true)
            }
        wordsAdapter.search( newList)
        return true
    }
}
