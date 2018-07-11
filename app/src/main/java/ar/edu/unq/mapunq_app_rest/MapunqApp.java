package ar.edu.unq.mapunq_app_rest;

import android.app.Application;

import db.AppDatabase;
import db.DataRepository;

/**
 * Android Application class. Usada p/acceder a los singletons.
 */
public class MapunqApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public AppDatabase getDatabase() {
        return AppDatabase.getInstance(this);
    }

    public DataRepository getRepository() {
        return DataRepository.getInstance(getDatabase());
    }
}
