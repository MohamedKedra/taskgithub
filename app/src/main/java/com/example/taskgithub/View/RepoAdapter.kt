package com.example.taskgithub.View

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.taskgithub.Data.Models.Repo
import com.example.taskgithub.R
import com.example.taskgithub.View.UI.ProfileActivity
import com.example.taskgithub.View.UI.RepoActivity

class RepoAdapter(val repos: List<Repo>, val context: Context) :
    RecyclerView.Adapter<RepoAdapter.RepoHolder>() {

    lateinit var intent: Intent

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): RepoHolder {
        var view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_repo, parent, false)
        return RepoHolder(view)
    }

    override fun getItemCount(): Int = repos.size

    override fun onBindViewHolder(holder: RepoHolder, position: Int) {
        Glide.with(holder.itemView.context).load(repos[position].owner.avatar).into(holder.avatar)
        holder.username.text = repos[position].owner.name
        holder.repoName.text = repos[position].name

        holder.avatar.setOnClickListener {
            intent = Intent(context, ProfileActivity::class.java)
            intent.putExtra("username", repos[position].owner.name)
            context.startActivity(intent)
        }
        holder.itemView.setOnClickListener {
            intent = Intent(context, RepoActivity::class.java)
            intent.putExtra("repo", repos[position])
            context.startActivity(intent)
        }
    }

    class RepoHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val avatar = itemView.findViewById<ImageView>(R.id.iv_userAvatar)
        val username = itemView.findViewById<TextView>(R.id.tv_username)
        val repoName = itemView.findViewById<TextView>(R.id.tv_repoName)
    }
}