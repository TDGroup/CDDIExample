package com.tdgroup.cicdexample.data

import android.annotation.SuppressLint
import android.app.Application
import androidx.annotation.VisibleForTesting
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

import com.tdgroup.cicdexample.movies.MainActivityViewModel


class ViewModelFactory private constructor(val tasksRepository: MovieRepository) :
    ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainActivityViewModel::class.java)) {
            return MainActivityViewModel(tasksRepository) as T
        }
//        if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
//            return DetailViewModel(tasksRepository) as T
//        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }

    companion object {
        @SuppressLint("StaticFieldLeak")
        @Volatile
        private var INSTANCE: ViewModelFactory? = null
        fun getInstance(application: Application): ViewModelFactory? {
            if (INSTANCE == null) {
                synchronized(ViewModelFactory::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = ViewModelFactory(
                            InjectRepository.provideRepository(application.applicationContext)
                        )
                    }
                }
            }
            return INSTANCE
        }

        @VisibleForTesting
        fun destroyInstance() {
            INSTANCE = null
        }
    }
}