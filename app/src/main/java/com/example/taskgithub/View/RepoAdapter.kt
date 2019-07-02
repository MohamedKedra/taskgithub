package com.example.taskgithub.View

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.taskgithub.Data.Models.Repo
import com.example.taskgithub.R
import com.example.taskgithub.View.UI.ProfileActivity
import com.example.taskgithub.View.UI.RepoActivity

class RepoAdapter :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var isLoadingAdded: Boolean = false
    private var repos: MutableList<Repo>
    lateinit var context: Context
    private val ITEM = 0
    private val LOADING = 1

    init {
        repos = mutableListOf()
    }

    fun addRepos(repos: MutableList<Repo>) {
        this.repos = repos
    }

    fun addContext(context: Context) {
        this.context = context
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        var inflater = LayoutInflater.from(parent.context)
        var holder: RecyclerView.ViewHolder? = null
        when (viewType) {
            ITEM -> {
                val viewItem = inflater.inflate(R.layout.item_repo, parent, false)
                holder = RepoHolder(viewItem)
            }
            LOADING -> {
                val viewLoading = inflater.inflate(R.layout.item_progress, parent, false)
                holder = LoadHolder(viewLoading)
            }
        }
        return holder!!
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == repos.size - 1 && isLoadingAdded) LOADING else ITEM
    }

    override fun getItemCount(): Int = repos.size

    fun removeLoadingFooter() {
        isLoadingAdded = false

        val position = repos.size - 1
        val result = getItem(position)

        if (result != null) {
            repos.removeAt(position)
            notifyItemRemoved(position)
        }
    }

    private fun getItem(position: Int): Repo {
        return repos[position]
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        when (getItemViewType(position)) {

            ITEM -> {
                val repoHolder = holder as RepoHolder
                holder.setRepoData(repos)
                Glide.with(repoHolder.itemView.context).load(repos[position].owner?.avatar).into(holder.avatar)
                repoHolder.username.text = repos[position].owner?.name
                repoHolder.repoName.text = repos[position].name
                repoHolder.langauge.text = repos[position].language
            }

            LOADING -> {
                val loadHolder = holder as LoadHolder
                loadHolder.loading.visibility = View.VISIBLE
            }
        }
    }

    private fun add(r: Repo) {
        repos.add(r)
        notifyItemInserted(repos.size - 1)
    }

    fun addLoadingFooter() {
        isLoadingAdded = true
        add(Repo())
    }

    fun addAll(repoResults: List<Repo>) {
        for (repo in repoResults) {
            add(repo)
        }
    }

    class RepoHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

        lateinit var intent: Intent
        lateinit var allRepos: MutableList<Repo>
        val avatar: ImageView = itemView.findViewById(R.id.iv_userAvatar)
        val username: TextView = itemView.findViewById(R.id.tv_username)
        val repoName: TextView = itemView.findViewById(R.id.tv_repoName)
        val langauge: TextView = itemView.findViewById(R.id.tv_language)

        init {
            itemView.setOnClickListener(this)
            avatar.setOnClickListener(this)
        }

        override fun onClick(view: View?) {

            when (view?.id) {
                R.id.iv_userAvatar -> {
                    avatar.setOnClickListener {
                        intent = Intent(itemView.context, ProfileActivity::class.java)
                        intent.putExtra("username", allRepos[adapterPosition].owner?.name)
                        itemView.context.startActivity(intent)
                    }
                }

                itemView.id -> {
                    intent = Intent(itemView.context, RepoActivity::class.java)
                    intent.putExtra("repo", allRepos[adapterPosition])
                    itemView.context.startActivity(intent)
                }
            }
        }

        fun setRepoData(repos: MutableList<Repo>) {
            allRepos = repos
        }
    }

    class LoadHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val loading: ProgressBar = itemView.findViewById(R.id.pb_loadMore)
    }
}