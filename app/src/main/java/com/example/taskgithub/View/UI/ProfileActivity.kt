package com.example.taskgithub.View.UI

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.taskgithub.R
import com.example.taskgithub.View.RepoAdapter
import com.example.taskgithub.ViewModels.MainViewModel
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

        profileViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(this.application).create(
            ProfileViewModel::class.java)
        var username = intent.extras.getString("username")

        profileViewModel.getUserInfo(username).observe(this@ProfileActivity, Observer {
            t -> tv_username.text = t?.name
            Glide.with(this).load(t?.avatar).into(iv_userAvatar)
        })

        profileViewModel.getUserRepos(username).observe(this@ProfileActivity, Observer {
            t ->  adapter = RepoAdapter(t!!,this@ProfileActivity)
            rv_repos.adapter = adapter
            rv_repos.layoutManager = LinearLayoutManager(this@ProfileActivity,LinearLayoutManager.VERTICAL,false)
        })
    }

}
