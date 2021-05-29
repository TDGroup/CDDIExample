package com.tdgroup.cicdexample.movies

import androidx.lifecycle.MutableLiveData
import com.tdgroup.cicdexample.data.MovieRepository
import io.reactivex.schedulers.Schedulers

open class MainActivityViewModel(private val repository: MovieRepository) : BaseViewModel() {
    private val movieListLiveData = MutableLiveData<List<MovieModel>>()

    open fun getMovieLiveData(): MutableLiveData<List<MovieModel>> {
        return movieListLiveData
    }

    open fun setMovieLiveData(movieModelList: List<MovieModel>) {
        getMovieLiveData().postValue(movieModelList)
    }

    init {
        //fetchMovies()
    }

    fun fetchMovies() {
//        repository.getMovieList()
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe(object : SingleObserver<MovieResponse> {
//                override fun onSubscribe(d: Disposable) {
//                }
//
//                override fun onSuccess(t: MovieResponse) {
//                    //setMovieLiveData(it.movieModelList)
//                    setMovieLiveData(t.movieModelList)
//                }
//
//                override fun onError(e: Throwable) {
//                }
//
//            })
        subscribe(
            repository.getMovieList()
                .subscribeOn(Schedulers.newThread())
//            .doOnSubscribe({ disposable -> getLoadDataStatus().postValue(true) })
//            .doAfterTerminate({ getLoadDataStatus().postValue(false) })
                .subscribe({ response ->
                    setMovieLiveData(response.movieModelList)
                }, { throwable ->
                    handleError(throwable)
                })
        )
    }

}