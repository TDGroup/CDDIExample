package com.tdgroup.cicdexample.movies

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.tdgroup.cicdexample.R
import com.tdgroup.cicdexample.data.ViewModelFactory
import dev.idee.cicdandroid.MovieListAdapter
import kotlinx.android.synthetic.main.activity_movie_list.*

class MovieListActivity : AppCompatActivity() {
    private lateinit var viewModel: MainActivityViewModel
    private val movieAdapter = MovieListAdapter(DiffUtilCallback())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_list)
        setupRecyclerView()
        setupViewModel()
        viewModel.fetchMovies()
    }

    private fun setupRecyclerView() {
        with(movieRecyclerView) {
            layoutManager = LinearLayoutManager(this@MovieListActivity)
            adapter = movieAdapter
        }
    }

    private fun setupViewModel() {
        val factory: ViewModelFactory? = ViewModelFactory.getInstance(application)
        viewModel = ViewModelProviders.of(this, factory).get(MainActivityViewModel::class.java)
        viewModel.getMovieLiveData().observe(this, Observer {
            progressBar.visibility = View.GONE
            movieAdapter.submitList(it)
        })
    }

}
