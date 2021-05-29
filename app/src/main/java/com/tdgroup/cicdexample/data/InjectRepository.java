package com.tdgroup.cicdexample.data;

import android.annotation.SuppressLint;
import android.content.Context;


import androidx.annotation.NonNull;

import static kotlin.jvm.internal.Intrinsics.checkNotNull;

/**
 * This is useful for testing, since it allows us to use
 *  a fake instance of the class to isolate the dependencies and run a test hermetically.
 */
public class InjectRepository {
    @SuppressLint("RestrictedApi")
    public static MovieRepository provideRepository(@NonNull Context context) {
        checkNotNull(context);
//        ToDoDatabase database = ToDoDatabase.getInstance(context);
        return MovieRepository.Companion.getInstance();
    }
}
