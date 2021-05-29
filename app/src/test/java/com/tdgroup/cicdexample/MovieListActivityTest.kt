package com.tdgroup.cicdexample

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.nhaarman.mockito_kotlin.any
import com.tdgroup.cicdexample.data.MovieRepository
import com.tdgroup.cicdexample.movies.MainActivityViewModel
import com.tdgroup.cicdexample.movies.MovieModel
import com.tdgroup.cicdexample.movies.MovieResponse
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.internal.schedulers.ExecutorScheduler.ExecutorWorker
import io.reactivex.plugins.RxJavaPlugins
import org.junit.*
import org.junit.runner.RunWith
import org.mockito.*
import org.mockito.junit.MockitoJUnitRunner
import java.util.concurrent.Callable


@RunWith(MockitoJUnitRunner.Silent::class)
class MovieListActivityTest {

    @Mock
    private var movieRepository: MovieRepository = MovieRepository()

    @Mock
    private var movieListLiveData: MutableLiveData<List<MovieModel>> = MutableLiveData()

    @Spy
    private var movieViewModel: MainActivityViewModel = MainActivityViewModel(movieRepository)

    @get:Rule
    public val instantExecutorRule = InstantTaskExecutorRule()

    @Captor
    lateinit var captor : ArgumentCaptor<List<MovieModel>>

    @Before
    fun init() {
        val immediate: Scheduler = object : Scheduler() {
            override fun createWorker(): Worker {
                return ExecutorWorker({ obj: Runnable -> obj.run() }, false)
            }
        }

        RxJavaPlugins.setInitIoSchedulerHandler { scheduler: Callable<Scheduler?>? -> immediate }
        RxJavaPlugins.setInitComputationSchedulerHandler { scheduler: Callable<Scheduler?>? -> immediate }
        RxJavaPlugins.setInitNewThreadSchedulerHandler { scheduler: Callable<Scheduler?>? -> immediate }
        RxJavaPlugins.setInitSingleSchedulerHandler { scheduler: Callable<Scheduler?>? -> immediate }
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { scheduler: Callable<Scheduler?>? -> immediate }

    }

    @Test
    fun test_getMovieList() {
        var movieResponse = MovieResponse(arrayListOf<MovieModel>())
        Mockito.`when`(movieViewModel.getMovieLiveData()).thenReturn(movieListLiveData)
        Mockito.`when`(movieRepository.getMovieList()).thenReturn(Single.just(movieResponse))
        movieViewModel.fetchMovies()
        Mockito.verify(movieViewModel).setMovieLiveData(any())
        Mockito.verify(movieListLiveData).postValue(captor.capture())
        Assert.assertEquals(captor.value.size,20)
    }

    companion object {
        @AfterClass
        fun tearDownClass() {
            RxJavaPlugins.reset()
            RxAndroidPlugins.reset()
            
        }

    }


    @After
    fun tearDownClass2() {
        RxJavaPlugins.reset()
        RxAndroidPlugins.reset()
    }


}