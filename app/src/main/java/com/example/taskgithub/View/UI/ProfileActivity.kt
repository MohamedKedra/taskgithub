package com.example.taskgithub.View.UI

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.ProgressBar
import com.bumptech.glide.Glide
import com.example.taskgithub.Data.Models.Repo
import com.example.taskgithub.R
import com.example.taskgithub.View.RepoAdapter
import com.example.taskgithub.ViewModels.ProfileViewModel
import kotlinx.android.synthetic.main.activity_profile.*
import kotlinx.android.synthetic.main.content_profile.*

class ProfileActivity : AppCompatActivity() {

    lateinit var profileViewModel: ProfileViewModel
    lateinit var adapter: RepoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        setSupportActionBar(toolbar)
        adapter = RepoAdapter()
        adapter.addContext(this@ProfileActivity)
        profileViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(this.application).create(
            ProfileViewModel::class.java
        )
        var username = intent.extras.getString("username")

        profileViewModel.getUserInfo(username).observe(this@ProfileActivity, Observer { t ->
            var progressBar = ProgressBar(this)
            if (t != null) {
                progressBar.visibility = View.GONE
                tv_username.text = t?.name
                Glide.with(this).load(t?.avatar).into(iv_userAvatar)
            }else{
                progressBar.visibility = View.VISIBLE
            }
        })

        profileViewModel.getUserRepos(username).observe(this@ProfileActivity, Observer { t ->
            adapter.addRepos(t!! as MutableList<Repo>)
            rv_repos.adapter = adapter
            rv_repos.layoutManager = LinearLayoutManager(this@ProfileActivity, LinearLayoutManager.VERTICAL, false)
        })
    }

}
