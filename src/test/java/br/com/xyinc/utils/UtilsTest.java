package br.com.xyinc.utils;

import java.util.Arrays;
import java.util.List;

import br.com.xyinc.domain.model.PointOfInterest;

public class UtilsTest {

    public static PointOfInterest getPointOfInterest() {

        PointOfInterest poi = new PointOfInterest();
        poi.setNome("POI 1");
        poi.setCoordenadaX(15);
        poi.setCoordenadaY(25);

        return poi;
    }

    public static List<PointOfInterest> getPois() {

        PointOfInterest poi = getPointOfInterest();

        PointOfInterest poi2 = new PointOfInterest();
        poi2.setNome("POI 2");
        poi2.setCoordenadaX(28);
        poi2.setCoordenadaY(2);

        PointOfInterest poi3 = new PointOfInterest();
        poi3.setNome("POI 3");
        poi3.setCoordenadaX(15);
        poi3.setCoordenadaY(12);

        return Arrays.asList(poi, poi2, poi3);
    }

}
