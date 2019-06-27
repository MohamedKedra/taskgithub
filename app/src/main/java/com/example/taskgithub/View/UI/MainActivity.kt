package com.example.taskgithub.View.UI

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.SearchView
import android.widget.Toast
import com.example.taskgithub.Client
import com.example.taskgithub.Data.ApiServices
import com.example.taskgithub.Data.Models.RepoResponse
import com.example.taskgithub.R
import com.example.taskgithub.View.RepoAdapter

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    lateinit var repoAdapter: RepoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        var apiServices: ApiServices = Client.getInstance()
        sv_search.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(text: String?): Boolean {
                if (text?.isNotEmpty()!!) {
                    apiServices.searchInRepos(text).enqueue(object : Callback<RepoResponse> {
                        override fun onFailure(call: Call<RepoResponse>?, t: Throwable?) {
                            Log.d("response : ", t?.message)
                        }

                        override fun onResponse(call: Call<RepoResponse>?, response: Response<RepoResponse>?) {

                            if (response?.isSuccessful!!) {

                                repoAdapter = RepoAdapter(response?.body()?.repos!!)
                                rv_repos.adapter = repoAdapter
                                rv_repos.layoutManager =
                                    LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)

                            } else {
                                Toast.makeText(this@MainActivity, response.message(), Toast.LENGTH_SHORT)
                            }
                        }

                    })
                }
                else{
                    Toast.makeText(this@MainActivity,"No data found",Toast.LENGTH_SHORT)
                }
                return true
            }

            override fun onQueryTextChange(text: String?): Boolean {
                return false
            }

        })

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
