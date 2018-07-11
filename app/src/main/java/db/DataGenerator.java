package db;

import java.util.ArrayList;
import java.util.List;


/**
 * Genera data para llenar la db
 */
public class DataGenerator {

    private static final String[] pointId = new String[]{
            "afuera de oficina pablo", "afuera oficina pablo",
            "aula 13", "aula 37b","aula 60", "aula 83", "aula 213", "aula 30", "aula 121",
            "aula 36", "aula 28 música", "aula 101", "aula 38",
            "box 4 alumnos", "box 5 asuntos estudiantiles", "box 7 asuntos estudiantiles",
            "box 11 secretaria de extensión universitaria", "box 13 salud y discapacidad",
            "box 9 secretaria de extensión universitaria", "box 3 asuntos estudiantiles",
            "bathroom 2nd floor", "baños frente a fotocopiadora cyt",
            "cyt1", "cyt - secretaria", "oficina 37",
            "oficina 35", "oficina 37", "oficina mara",
            "rosa de los vientos",
               };
    private static final String[] nombrePOI = new String[]{
            "Puerta de la oficina de Pablo Suarez", "Puerta de la oficina de Pablo Suarez",
            "Aula 13", "Aula 37b","Aula 60", "Aula 83", "Aula 213", "Aula 30", "Aula 121",
            "Aula 36", "Aula 28 de música", "Aula 101", "Aula 38",
            "Box 4", "Box 5", "Box 7",
            "Box 11", "Box 13",
            "Box 9", "Box 3",
            "Baños Primer Piso", "Baños frente a fotocopiadora C Y T",
            "Aula C y T 1", "C y T - Secretaria", "Oficina 37",
            "Oficina 35", "Oficina 37", "Oficina de Mara",
            "Rosa de los Vientos"};
    private static final String[] infoPOI = new String[]{
            "Pablo Suárez es el director de T.P.I", "Pablo Suárez es el director de T.P.I",
            "Aula 13", "Aula con acceso a computadors","Aula 60", "Aula 83", "Aula 213",
            "Aula 30", "Aula 121",
            "Aula 36", "Clases de música", "Aula 101", "Aula 38",
            "Inscripciones y otros trámites de alumnos", "Asuntos Estudiantiles", "Asuntos Estudiantiles",
            "Secretaria de Extensión Universitaria", "Salud y Discapacidad",
            "Secretaria de Extensión Universitaria", "Asuntos Estudiantiles",
            "Baños", "Baños",
            "Aula C y T 1", "C y T - Secretaria", "Oficina 37",
            "oficina 35", "oficina 37", "Oficina de la profesora Mara Dalponte",
            "Rosa de los Vientos"};
    private static final String[] floor = new String[]{
            "Primer piso", "Primer piso",
            "Planta baja", "Planta baja","Planta baja", "Segundo Piso", "Segundo Piso",
            "Planta baja", "Aula 121",
            "Planta baja", "Primer piso", "Aula 101", "Planta baja",
            "Planta baja", "Planta baja", "Planta baja",
            "Planta baja", "Planta baja",
            "Planta baja", "Planta baja",
            "Primer piso", "Planta baja",
            "Edificio C y T", "Edificio C y T", "Planta Baja",
            "Planta baja", "Planta baja", "Primer piso",
            "Primer piso"};

    private static final String[] building = new String[]{
            "Principal", "Principal",
            "Principal", "Principal","Principal", "Principal", "Principal",
            "Principal", "Principal",
            "Principal", "Principal", "Principal", "Principal",
            "Principal", "Principal", "Principal",
            "Principal", "Principal",
            "Principal", "Principal",
            "Principal", "Principal",
            "Edificio C y T", "Edificio C y T", "Principal",
            "Principal", "Principal", "Principal",
            "Principal"};

    public static List<PointOfInterestEntity> generatePointOfInterest() {
        List<PointOfInterestEntity> pois = new ArrayList<PointOfInterestEntity>(pointId.length * nombrePOI.length);

        for (int i = 0; i < pointId.length; i++) {
                PointOfInterestEntity poi = new PointOfInterestEntity();
                poi.setPointId(pointId[i]);
                poi.setNombrePOI(nombrePOI[i]);
                poi.setInfoPOI(infoPOI[i]);
                poi.setFloorOfPOI(floor[i]);
                poi.setBuildingOfPOI(building[i]);
                pois.add(poi);
            }

        return pois;
    }

}
