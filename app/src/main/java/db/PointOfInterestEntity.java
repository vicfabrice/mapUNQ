package db;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import model.PointOfInterest;

//Los puntos de interés poseen Id (el nombre con el que van a llegar desde el server), nombre (el nombre real),
//info (qué se puede hacer ahí), floor (en qué piso quedan), building (en qué edificio de la UNQ quedan)
//Sería importante también sumar características de orientación (norte, sur, etc)
@Entity(tableName = "pointsofinterest")
public class PointOfInterestEntity implements PointOfInterest {

    //minima info necesaria de cada punto de interés

    @PrimaryKey @NonNull
    private String pointId;

    @ColumnInfo(name = "nombre")
    private String nombrePOI;

    @ColumnInfo(name = "info")
    private String infoPOI;


    @ColumnInfo(name = "floor")
    private String floorOfPOI;

    @ColumnInfo(name = "building")
    private String buildingOfPOI;

    @Override
    public String getPointId() {
        return pointId;
    }

    public void setPointId(String pointId) {
        this.pointId = pointId;
    }

    @Override
    public String getInfoPOI() {
        return infoPOI;
    }

    public void setInfoPOI(String infoPOI) {
        this.infoPOI = infoPOI;
    }

    @Override
    public String getFloorOfPOI() {
        return floorOfPOI;
    }

    public void setFloorOfPOI(String floorOfPOI) {
        this.floorOfPOI = floorOfPOI;
    }

    @Override
    public String getBuildingOfPOI() {
        return buildingOfPOI;
    }

    public void setBuildingOfPOI(String buildingOfPOI) {
        this.buildingOfPOI = buildingOfPOI;
    }

    @Override
    public String getNombrePOI() {
        return nombrePOI;
    }

    public void setNombrePOI(String nombrePOI) {
        this.nombrePOI = nombrePOI;
    }

    public PointOfInterestEntity(String pointId, String nombrePOI, String infoPOI, String floorOfPOI, String buildingOfPOI) {
        this.pointId = pointId;
        this.nombrePOI = nombrePOI;
        this.infoPOI = infoPOI;
        this.floorOfPOI = floorOfPOI;
        this.buildingOfPOI = buildingOfPOI;
    }

    public PointOfInterestEntity(PointOfInterest poi){
        this.pointId = poi.getPointId();
        this.nombrePOI = poi.getNombrePOI();
        this.infoPOI = poi.getInfoPOI();
        this.floorOfPOI = poi.getFloorOfPOI();
        this.buildingOfPOI = poi.getBuildingOfPOI();

    }

    public PointOfInterestEntity(){

    }
}
