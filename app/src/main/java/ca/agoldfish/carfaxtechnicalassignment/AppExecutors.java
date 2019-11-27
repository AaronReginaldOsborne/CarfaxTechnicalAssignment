package ca.agoldfish.carfaxtechnicalassignment;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class AppExecutors {

    private static AppExecutors instance;

    public  static AppExecutors getInstance(){
        if(instance == null)
            instance = new AppExecutors();
        return instance;
    }

    //this is a way we can send requests to the network api
    private final ScheduledExecutorService mNetworkIO = Executors.newScheduledThreadPool(3);

    private Executor mBackgroundExecutor = Executors.newSingleThreadExecutor();

    public ScheduledExecutorService networkIO(){
        return mNetworkIO;
    }
}
