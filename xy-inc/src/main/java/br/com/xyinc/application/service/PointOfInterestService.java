package br.com.xyinc.application.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.xyinc.domain.model.PointOfInterest;
import br.com.xyinc.domain.model.PointOfInterestResponse;
import br.com.xyinc.domain.port.PointOfInterestRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PointOfInterestService {

    private final PointOfInterestRepository pointOfInterestRepository;

    public PointOfInterest createPointOfInterest(PointOfInterest pointOfInterest) {

        return pointOfInterestRepository.save(pointOfInterest);
    }

    public ResponseEntity<List<PointOfInterest>> listAllPointsOfInterest() {

        List<PointOfInterest> pois = pointOfInterestRepository.findAll();

        if (pois.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(pois);
    }

    public List<PointOfInterest> getPOIsByProximity(PointOfInterestResponse pointResponse) {

        return pointOfInterestRepository.findAll().stream()
                .filter(poi -> calculateDistance(poi, pointResponse))
                .collect(Collectors.toList());
    }

    private boolean calculateDistance(PointOfInterest poi, PointOfInterestResponse pointResponse) {

        return Math.sqrt(
                Math.pow(pointResponse.coordenadaX() - poi.coordenadaX(), 2)
                        + Math.pow(pointResponse.coordenadaY() - poi.coordenadaX(), 2)
        ) <= pointResponse.distancia();
    }
}
