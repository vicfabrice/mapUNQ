package db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import java.util.List;

//Para hacer la parte de la db, me basé en el ejemplo de Android - Basic Sample
// https://github.com/googlesamples/android-architecture-components/tree/master/BasicSample

@Database(entities = {PointOfInterestEntity.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase sInstance;

    public static final String DATABASE_NAME = "pointofinterest-db";

    public abstract PointOfInterestDao pointOfInterestDao();

    //flag para saber si está creada
    private Boolean mIsDatabaseCreated = Boolean.FALSE;


    public static AppDatabase getInstance(final Context context) {

        if (sInstance == null) {
            synchronized (AppDatabase.class) {
                if (sInstance == null) {
                    sInstance = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, "pointofinterest-db")
                            .allowMainThreadQueries() //allow queries on the main thread
                            .build();
                    sInstance.updateDatabaseCreated(context.getApplicationContext());
                }
            }
        }
        return sInstance;
    }

    public Boolean getDatabaseCreated(){
        return mIsDatabaseCreated;
    }

    private void setDatabaseCreated(){
        mIsDatabaseCreated = true;
    }

    private static void insertData(final AppDatabase database, final List<PointOfInterestEntity> pois){
        database.pointOfInterestDao().insertAll(pois);
    }

    private void updateDatabaseCreated(final Context context) {
        if (context.getDatabasePath(DATABASE_NAME).exists()) {
            setDatabaseCreated();
        }
    }

    private static AppDatabase buildDatabase(final Context appContext) {

        // Genera la data para llenarla
        AppDatabase database = AppDatabase.getInstance(appContext);
        List<PointOfInterestEntity> pointsofinterest = DataGenerator.generatePointOfInterest();

        insertData(database, pointsofinterest);
        // notifica que fue creada y está lista para ser usada
        database.setDatabaseCreated();

        return Room.databaseBuilder(appContext, AppDatabase.class, DATABASE_NAME).build();

    }

}