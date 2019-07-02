package com.example.taskgithub.View.UI

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.widget.SearchView
import com.example.taskgithub.Data.Models.Repo
import com.example.taskgithub.R
import com.example.taskgithub.View.RepoAdapter
import com.example.taskgithub.ViewModels.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {

    lateinit var mainViewModel: MainViewModel
    lateinit var layoutManager: LinearLayoutManager
    lateinit var adapter: RepoAdapter
    var isLastPage: Boolean = false
    var isLoading: Boolean = false
    var currentPage: Int = 1
    private val TOTAL_PAGES = 5

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        adapter = RepoAdapter()
        adapter.addContext(this@MainActivity)
        layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
        mainViewModel =
            ViewModelProvider.AndroidViewModelFactory.getInstance(this.application).create(MainViewModel::class.java)

        sv_search.setOnQueryTextListener(onSearchListener)
    }

    private var onSearchListener = object : SearchView.OnQueryTextListener {

        override fun onQueryTextSubmit(text: String?): Boolean {
            tv_noData.visibility = View.GONE
            pb_progressbar.visibility = View.VISIBLE
            if (text?.isNotEmpty()!!) {

                loadFirstPage(text)
                rv_repos.addOnScrollListener(object : PaginationRecycler(layoutManager) {
                    override fun getTotalPageCount(): Int = TOTAL_PAGES

                    override fun isLastPage(): Boolean = isLastPage

                    override fun isLoading(): Boolean = isLoading

                    override fun loadMoreItems() {
                        isLoading = true

                        loadNextPage(text)
                    }
                })
            } else {
                tv_noData.visibility = View.VISIBLE
                pb_progressbar.visibility = View.GONE
            }
            return false
        }

        override fun onQueryTextChange(text: String?): Boolean {
            tv_noData.visibility = View.GONE
            return false
        }
    }

    fun loadFirstPage(text: String) {
        mainViewModel.getAllRepos(text, currentPage).observe(this@MainActivity,
            Observer { t ->
                if (t != null) {
                    pb_progressbar.visibility = View.GONE
                    adapter.addAll(t!!)
                    rv_repos.adapter = adapter
                    rv_repos.layoutManager = layoutManager
                    rv_repos.itemAnimator = DefaultItemAnimator()
                } else {
                    tv_noData.visibility = View.VISIBLE
                    pb_progressbar.visibility = View.GONE
                }
                if (currentPage <= TOTAL_PAGES)
                    adapter.addLoadingFooter()
                else
                    isLastPage = true
            }
        )
    }

    fun loadNextPage(text: String) {
        mainViewModel.getAllRepos(text, ++currentPage).observe(this@MainActivity,
            Observer { t ->
                if (t != null) {
                    adapter.removeLoadingFooter()
                    isLoading = false
                    adapter.addAll(t!!)
                } else {
                    tv_noData.visibility = View.VISIBLE
                    pb_progressbar.visibility = View.GONE
                }
                if (currentPage <= TOTAL_PAGES)
                    adapter.addLoadingFooter()
                else
                    isLastPage = true
            }
        )
    }
}
