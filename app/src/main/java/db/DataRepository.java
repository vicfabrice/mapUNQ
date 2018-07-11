package db;

import java.util.ArrayList;
import java.util.List;
import android.arch.lifecycle.LiveData;

import model.PointOfInterest;

//Repositorio con la data de los POI
public class DataRepository {

    private static DataRepository sInstance;

    private final AppDatabase mDatabase;
    private List<PointOfInterestEntity> mPois;

    //Constructor
    private DataRepository(final AppDatabase database) {
        mDatabase = database;
        mPois = new ArrayList<>();
        mPois.addAll(mDatabase.pointOfInterestDao().loadAllPointsOfInterest()); //no estoy segura d esta linea
    }

    public static DataRepository getInstance(final AppDatabase database) {
        if (sInstance == null) {
            synchronized (DataRepository.class) {
                if (sInstance == null) {
                    sInstance = new DataRepository(database);
                }
            }
        }
        return sInstance;
    }


    public List<PointOfInterestEntity> getPois() {
        return mPois;
    }

    public PointOfInterestEntity loadPoi(final String poiId) {
        return mDatabase.pointOfInterestDao().loadPointOfInterestByPointId(poiId);
    }
}