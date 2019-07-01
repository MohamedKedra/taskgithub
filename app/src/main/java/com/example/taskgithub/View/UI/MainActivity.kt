package com.example.taskgithub.View.UI

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.widget.SearchView
import android.widget.Toast
import com.example.taskgithub.R
import com.example.taskgithub.View.RepoAdapter
import com.example.taskgithub.ViewModels.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {

    lateinit var repoAdapter: RepoAdapter
    lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        mainViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(this.application).create(MainViewModel::class.java)

        sv_search.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(text: String?): Boolean {
                if (text?.isNotEmpty()!!) {
                    mainViewModel.getAllRepos(text).observe(this@MainActivity, Observer { t ->
                        repoAdapter = RepoAdapter(t!!,this@MainActivity)
                        rv_repos.adapter = repoAdapter
                        rv_repos.layoutManager =
                            LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
                    })
                } else {
                    Toast.makeText(this@MainActivity, "No data found", Toast.LENGTH_SHORT)
                }
                return false
            }

            override fun onQueryTextChange(text: String?): Boolean {
                return false
            }

        })
    }
}
