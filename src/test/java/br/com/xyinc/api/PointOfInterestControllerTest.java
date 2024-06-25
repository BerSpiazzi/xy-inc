package br.com.xyinc.api;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import br.com.xyinc.application.service.PointOfInterestService;
import br.com.xyinc.domain.model.PointOfInterest;
import br.com.xyinc.domain.model.PointOfInterestResponse;
import br.com.xyinc.infrastructure.exception.EmptyResultException;

@WebMvcTest(PointOfInterestController.class)
class PointOfInterestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PointOfInterestService pointOfInterestService;

    @Test
    void testCreatePOI() throws Exception {

        PointOfInterest poi = new PointOfInterest();
        poi.setNome("POI 1");
        poi.setCoordenadaX(10);
        poi.setCoordenadaY(20);

        when(pointOfInterestService.createPointOfInterest(any(PointOfInterest.class))).thenReturn(poi);

        mockMvc.perform(post("/poi")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nome\": \"POI 1\", \"coordenadaX\": 10, \"coordenadaY\": 20}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("POI 1"));
    }

    @Test
    void testListAllPointsOfInterest() throws Exception {

        PointOfInterest poi1 = new PointOfInterest();
        poi1.setNome("POI 1");
        poi1.setCoordenadaX(10);
        poi1.setCoordenadaY(20);

        PointOfInterest poi2 = new PointOfInterest();
        poi2.setNome("POI 2");
        poi2.setCoordenadaX(30);
        poi2.setCoordenadaY(40);

        List<PointOfInterest> poiList = Arrays.asList(poi1, poi2);

        when(pointOfInterestService.listAllPointsOfInterest()).thenReturn(poiList);

        mockMvc.perform(get("/poi")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nome").value("POI 1"))
                .andExpect(jsonPath("$[1].nome").value("POI 2"));
    }

    @Test
    void testGetPOIsByProximity() throws Exception {

        PointOfInterestResponse response = new PointOfInterestResponse(10, 20, 10);

        PointOfInterest poi = new PointOfInterest();
        poi.setNome("POI 1");
        poi.setCoordenadaX(15);
        poi.setCoordenadaY(25);

        when(pointOfInterestService.getPOIsByProximity(response)).thenReturn(Arrays.asList(poi));

        mockMvc.perform(get("/poi/distancia")
                        .param("coordenadaX", "10")
                        .param("coordenadaY", "20")
                        .param("distancia", "10")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nome").value("POI 1"));
    }

    @Test
    void testGetPOIsByProximityEmptyResult() throws Exception {

        PointOfInterestResponse response = new PointOfInterestResponse(10, 20, 10);

        when(pointOfInterestService.getPOIsByProximity(response)).thenThrow(new EmptyResultException("Nenhum ponto de interesse encontrado."));

        mockMvc.perform(get("/poi/distancia")
                        .param("coordenadaX", "10")
                        .param("coordenadaY", "20")
                        .param("distancia", "10")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent())
                .andExpect(jsonPath("$.errors[0]").value("Nenhum ponto de interesse encontrado."));
    }
}