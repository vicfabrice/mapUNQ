package db;

import java.util.ArrayList;
import java.util.List;
import android.arch.lifecycle.LiveData;

//Repositorio con la data de los POI
public class DataRepository {

    private static DataRepository sInstance;

    private final AppDatabase mDatabase;
    private List<PointOfInterestEntity> mObservableProducts;

    //Constructor
    private DataRepository(final AppDatabase database) {
        mDatabase = database;
        mObservableProducts = new ArrayList<>();

        if (mDatabase.)
        mObservableProducts.addAll(mDatabase.pointOfInterestDao().getAll(),
                pointOfInterestEntities -> {
                    if (mDatabase.getDatabaseCreated().getValue() != null) {
                        mObservableProducts.addAll(pointOfInterestEntities);
                    }
                });
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

    /**
     * Get the list of products from the database and get notified when the data changes.
     */
    public LiveData<List<ProductEntity>> getProducts() {
        return mObservableProducts;
    }

    public LiveData<ProductEntity> loadProduct(final int productId) {
        return mDatabase.productDao().loadProduct(productId);
    }

    public LiveData<List<CommentEntity>> loadComments(final int productId) {
        return mDatabase.commentDao().loadComments(productId);
    }
}