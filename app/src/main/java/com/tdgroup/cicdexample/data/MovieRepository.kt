package com.tdgroup.cicdexample.data

import com.tdgroup.cicdexample.movies.MovieResponse
import io.reactivex.Single

open class MovieRepository {
    companion object {
        private var instance: MovieRepository? = null

        @Synchronized
        fun getInstance(): MovieRepository? {
            if (instance == null) {
                instance = MovieRepository()
            }
            return instance
        }
    }

    open fun getMovieList(): Single<MovieResponse> {
        return APIClient.client.create(ApiInterface::class.java)
            .getPopularMovies("933418731c191cca07179922815903c3")
    }


}