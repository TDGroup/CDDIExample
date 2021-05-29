package com.tdgroup.cicdexample.movies;


import androidx.lifecycle.ViewModel;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class BaseViewModel extends ViewModel {

    private CompositeDisposable disposables = new CompositeDisposable();

    public BaseViewModel() {
    }

    public Disposable subscribe(Disposable disposable) {
        disposables.add(disposable);
        return disposable;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        stop();
    }

    public void stop() {
        if (disposables != null && !disposables.isDisposed()) {
            disposables.clear();
        }
    }

    public void handleError(Throwable exception){
        exception.printStackTrace();
    }
}
