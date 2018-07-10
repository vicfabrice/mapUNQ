package repo;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface PointOfInterestDao {

    @Query("SELECT * FROM pointofinterest")
    List<PointOfInterest> getAll();

    @Query("SELECT * FROM pointofinterest WHERE pointId IN (:pointIds)")
    List<PointOfInterest> loadAllByIds(int[] pointIds);

    @Query("SELECT info FROM pointofinterest WHERE pointId LIKE :first LIMIT 1")
    String getInfoByPointId(String first);

    @Query("SELECT * FROM pointofinterest WHERE pointId LIKE :first LIMIT 1")
    PointOfInterest findByPointId(String first);

    @Insert
    void insertAll(PointOfInterest pois);

    @Delete
    void delete(PointOfInterest poi);
}
