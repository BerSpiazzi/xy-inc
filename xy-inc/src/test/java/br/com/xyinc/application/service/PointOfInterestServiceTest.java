package br.com.xyinc.application.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.xyinc.domain.model.PointOfInterest;
import br.com.xyinc.domain.model.PointOfInterestResponse;
import br.com.xyinc.domain.port.PointOfInterestRepository;
import br.com.xyinc.infrastructure.exception.EmptyResultException;

class PointOfInterestServiceTest {

    @Mock
    private PointOfInterestRepository pointOfInterestRepository;

    @InjectMocks
    private PointOfInterestService pointOfInterestService;

    public PointOfInterestServiceTest() {

        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreatePointOfInterest() {

        PointOfInterest poi = new PointOfInterest();
        poi.setNome("POI 1");
        poi.setCoordenadaX(10);
        poi.setCoordenadaY(20);

        when(pointOfInterestRepository.save(any(PointOfInterest.class))).thenReturn(poi);

        PointOfInterest createdPOI = pointOfInterestService.createPointOfInterest(poi);

        assertNotNull(createdPOI);
        assertEquals("POI 1", createdPOI.getNome());
    }

    @Test
    void testListAllPointsOfInterest() {

        PointOfInterest poi1 = new PointOfInterest();
        poi1.setNome("POI 1");
        poi1.setCoordenadaX(10);
        poi1.setCoordenadaY(20);

        PointOfInterest poi2 = new PointOfInterest();
        poi2.setNome("POI 2");
        poi2.setCoordenadaX(30);
        poi2.setCoordenadaY(40);

        List<PointOfInterest> poiList = Arrays.asList(poi1, poi2);

        when(pointOfInterestRepository.findAll()).thenReturn(poiList);

        List<PointOfInterest> returnedList = pointOfInterestService.listAllPointsOfInterest();

        assertNotNull(returnedList);
        assertEquals(2, returnedList.size());
        assertEquals("POI 1", returnedList.get(0).getNome());
        assertEquals("POI 2", returnedList.get(1).getNome());
    }

    @Test
    void testListAllPointsOfInterestEmptyResult() {

        when(pointOfInterestRepository.findAll()).thenReturn(Collections.emptyList());

        EmptyResultException thrown = assertThrows(
                EmptyResultException.class,
                () -> pointOfInterestService.listAllPointsOfInterest(),
                "Era esperado que listAllPointsOfInterest lançasse uma exceção, mas não lançou."
        );

        assertTrue(thrown.getMessage().contains("Nenhum ponto de interesse encontrado."));
    }

    @Test
    void testGetPOIsByProximity() {

        PointOfInterestResponse response = new PointOfInterestResponse(10, 20, 10);

        PointOfInterest poi = new PointOfInterest();
        poi.setNome("POI 1");
        poi.setCoordenadaX(15);
        poi.setCoordenadaY(25);

        when(pointOfInterestRepository.findAll()).thenReturn(List.of(poi));

        List<PointOfInterest> pois = pointOfInterestService.getPOIsByProximity(response);

        assertNotNull(pois);
        assertEquals(1, pois.size());
        assertEquals("POI 1", pois.getFirst().getNome());
    }

    @Test
    void testGetPOIsByProximityWithResultFalse() {

        PointOfInterestResponse response = new PointOfInterestResponse(10, 20, 10);

        PointOfInterest poi = new PointOfInterest();
        poi.setNome("POI 1");
        poi.setCoordenadaX(15);
        poi.setCoordenadaY(25);

        PointOfInterest poi2 = new PointOfInterest();
        poi.setNome("POI 2");
        poi.setCoordenadaX(28);
        poi.setCoordenadaY(2);

        PointOfInterest poi3 = new PointOfInterest();
        poi.setNome("POI 3");
        poi.setCoordenadaX(15);
        poi.setCoordenadaY(12);

        when(pointOfInterestRepository.findAll()).thenReturn(List.of(poi, poi2, poi3));

        List<PointOfInterest> pois = pointOfInterestService.getPOIsByProximity(response);

        assertNotNull(pois);
        assertEquals(1, pois.size());
        assertEquals("POI 3", pois.getFirst().getNome());
    }

    @Test
    void testGetPOIsByProximityEmptyResult() {

        PointOfInterestResponse response = new PointOfInterestResponse(10, 20, 10);

        when(pointOfInterestRepository.findAll()).thenReturn(Collections.emptyList());

        EmptyResultException thrown = assertThrows(
                EmptyResultException.class,
                () -> pointOfInterestService.getPOIsByProximity(response),
                "Era esperado que getPOIsByProximity lançasse uma exceção, mas não lançou."
        );

        assertTrue(thrown.getMessage().contains("Nenhum ponto de interesse encontrado."));
    }

}