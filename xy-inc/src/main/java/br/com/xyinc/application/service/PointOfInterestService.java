package br.com.xyinc.application.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.xyinc.domain.model.PointOfInterest;
import br.com.xyinc.domain.model.PointOfInterestResponse;
import br.com.xyinc.domain.port.PointOfInterestRepository;
import br.com.xyinc.infrastructure.exception.EmptyResultException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PointOfInterestService {

    private final PointOfInterestRepository pointOfInterestRepository;

    public PointOfInterest createPointOfInterest(PointOfInterest pointOfInterest) {

        return pointOfInterestRepository.save(pointOfInterest);
    }

    public List<PointOfInterest> listAllPointsOfInterest() {

        List<PointOfInterest> pois = pointOfInterestRepository.findAll();

        if (pois.isEmpty()) {
            throw new EmptyResultException("Nenhum ponto de interesse encontrado.");
        }

        return pois;
    }

    public List<PointOfInterest> getPOIsByProximity(PointOfInterestResponse pointResponse) {

        List<PointOfInterest> pointOfInterestList = pointOfInterestRepository.findAll().stream()
                .filter(poi -> calculateDistance(poi, pointResponse))
                .toList();

        if (pointOfInterestList.isEmpty()) {
            throw new EmptyResultException("Nenhum ponto de interesse encontrado.");
        }

        return pointOfInterestList;
    }

    private boolean calculateDistance(PointOfInterest poi, PointOfInterestResponse pointResponse) {

        return Math.sqrt(
                Math.pow(pointResponse.coordenadaX() - poi.getCoordenadaX(), 2)
                        + Math.pow(pointResponse.coordenadaY() - poi.getCoordenadaY(), 2)
        ) <= pointResponse.distancia();
    }
}
