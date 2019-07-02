package com.example.taskgithub.View.UI

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.taskgithub.Data.Models.Repo
import com.example.taskgithub.R
import kotlinx.android.synthetic.main.activity_repo.*
import kotlinx.android.synthetic.main.content_repo.*

class RepoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repo)
        setSupportActionBar(toolbar)

        if (intent.hasExtra("repo")) {
            var repo: Repo = intent.extras.getParcelable("repo")
            tv_username.text = repo.owner?.name
            Glide.with(this@RepoActivity).load(repo.owner?.avatar).into(iv_userAvatar)
            tv_repo.text = repo.name
            tv_fork.text = repo.forks.toString()+" Forks"
            tv_lang.text = repo.language
            tv_branch.text = repo.branch
        }
    }

}
