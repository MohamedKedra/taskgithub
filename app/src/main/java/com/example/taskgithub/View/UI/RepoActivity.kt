package com.example.taskgithub.View.UI

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity;
import com.bumptech.glide.Glide
import com.example.taskgithub.Data.Models.Repo
import com.example.taskgithub.R

import kotlinx.android.synthetic.main.activity_repo.*
import kotlinx.android.synthetic.main.content_repo.*
import kotlinx.android.synthetic.main.content_repo.iv_userAvatar
import kotlinx.android.synthetic.main.item_repo.*

class RepoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repo)
        setSupportActionBar(toolbar)

        if (intent.hasExtra("repo")) {
            var repo: Repo = intent.extras.getParcelable<Repo>("repo")
            Glide.with(this@RepoActivity).load(repo.owner.avatar).into(iv_userAvatar)
            tv_repo.text = repo.name
        }
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }

}
