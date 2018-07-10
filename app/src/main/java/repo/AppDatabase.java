package repo;

import android.arch.lifecycle.MutableLiveData;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import java.util.List;


@Database(entities = {PointOfInterest.class}, version = 1)

public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase sInstance;


    public static final String DATABASE_NAME = "database-pointofinterest";

    public abstract PointOfInterestDao pointOfInterestDao();

    //private final MutableLiveData<Boolean> mIsDatabaseCreated = new MutableLiveData<>();
    private Boolean mIsDatabaseCreated = Boolean.FALSE;


    public static AppDatabase getInstance(final Context context) {
        if (sInstance == null) {
            synchronized (AppDatabase.class) {
                if (sInstance == null) {
                    AppDatabase sInstance = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, "database-pointofinterest").build();
                    //sInstance = buildDatabase(context.getApplicationContext(), executors);
                    sInstance.updateDatabaseCreated(context.getApplicationContext());
                }
            }
        }
        return sInstance;
    }

    public Boolean getDatabaseCreated(){
        return mIsDatabaseCreated;
    }

    private static void addDelay() {
        try {
            Thread.sleep(4000);
        } catch (InterruptedException ignored) {
        }
    }

    private static void insertData(final AppDatabase database, final List<PointOfInterest> pois){
        database.pointOfInterestDao().insertAll(pois);
    }

    private void setDatabaseCreated(){
        mIsDatabaseCreated = true;
    }

    private void updateDatabaseCreated(final Context context) {
        if (context.getDatabasePath(DATABASE_NAME).exists()) {
            setDatabaseCreated();
        }
    }

    /**
     * Build the database. {@link Builder#build()} only sets up the database configuration and
     * creates a new instance of the database.
     * The SQLite database is only created when it's accessed for the first time.
     */
    private static AppDatabase buildDatabase(final Context appContext) {

        // Generate the data for pre-population
        AppDatabase database = AppDatabase.getInstance(appContext);
        List<PointOfInterest> pointofinterests = DataGenerator.generatePointOfInterest();


        insertData(database, pointofinterests);
        // notify that the database was created and it's ready to be used
        database.setDatabaseCreated();
        
        return Room.databaseBuilder(appContext, AppDatabase.class, DATABASE_NAME).build();

    }

}