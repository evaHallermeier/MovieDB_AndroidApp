package com.example.moviedb;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

//for background operations
public class AppExecutors {
//singleton

    private final ScheduledExecutorService networkIO = Executors.newScheduledThreadPool(3);

    private static AppExecutors instance;

    public static AppExecutors getInstance(){
        if(instance ==null) {
            instance = new AppExecutors();
        }
        return instance;
    }

    public ScheduledExecutorService networkIO() {
        return networkIO;
    }
}
