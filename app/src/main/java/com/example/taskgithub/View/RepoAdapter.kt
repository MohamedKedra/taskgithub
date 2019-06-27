package com.example.taskgithub.View

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.taskgithub.Data.Models.Repo
import com.example.taskgithub.R

class RepoAdapter(val repos: List<Repo>) : RecyclerView.Adapter<RepoAdapter.RepoHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): RepoHolder {
        var view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_repo, parent, false)
        return RepoHolder(view)
    }

    override fun getItemCount(): Int = repos.size

    override fun onBindViewHolder(holder: RepoHolder, position: Int) {
        Glide.with(holder.itemView.context).load(repos.get(position).owner.avatar).into(holder.itemView.findViewById<ImageView>(
            R.id.iv_userAvatar
        ))
        holder.itemView.findViewById<TextView>(R.id.tv_username).text = repos.get(position).name
        holder.itemView.findViewById<TextView>(R.id.tv_repoName).text = repos.get(position).name
    }

    class RepoHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}