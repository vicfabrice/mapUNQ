package repo;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class PointOfInterest {

    //minima info necesaria de cada punto de interés

    @PrimaryKey
    private String pointId;

    @ColumnInfo(name = "nombre")
    private String nombrePOI;

    @ColumnInfo(name = "info")
    private String infoPOI;


    @ColumnInfo(name = "floor")
    private String floorOfPOI;

    @ColumnInfo(name = "building")
    private String buildingOfPOI;


    public String getPointId() {
        return pointId;
    }

    public void setPointId(String pointId) {
        this.pointId = pointId;
    }

    public String getInfoPOI() {
        return infoPOI;
    }

    public void setInfoPOI(String infoPOI) {
        this.infoPOI = infoPOI;
    }

    public String getFloorOfPOI() {
        return floorOfPOI;
    }

    public void setFloorOfPOI(String floorOfPOI) {
        this.floorOfPOI = floorOfPOI;
    }

    public String getBuildingOfPOI() {
        return buildingOfPOI;
    }

    public void setBuildingOfPOI(String buildingOfPOI) {
        this.buildingOfPOI = buildingOfPOI;
    }

    public String getNombrePOI() {
        return nombrePOI;
    }

    public void setNombrePOI(String nombrePOI) {
        this.nombrePOI = nombrePOI;
    }
}
