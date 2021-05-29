package com.tdgroup.cicdexample.data

import com.tdgroup.cicdexample.movies.MovieResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET("/3/movie/popular")
    fun getPopularMovies(@Query("api_key") apiKey: String): Single<MovieResponse>
}