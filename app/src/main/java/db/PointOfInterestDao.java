package db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface PointOfInterestDao {

    @Query("SELECT * FROM pointsofinterest")
    List<PointOfInterestEntity> loadAllPointsOfInterest();

    @Query("SELECT * FROM pointsofinterest WHERE pointId IN (:pointIds)")
    List<PointOfInterestEntity> loadAllByIds(int[] pointIds);

    @Query("SELECT info FROM pointsofinterest WHERE pointId LIKE :first LIMIT 1")
    String getInfoByPointId(String first);

    @Query("SELECT * FROM pointsofinterest WHERE pointId LIKE :first LIMIT 1")
    PointOfInterestEntity loadPointOfInterestByPointId(String first);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<PointOfInterestEntity> pois);

    @Delete
    void delete(PointOfInterestEntity poi);
}
