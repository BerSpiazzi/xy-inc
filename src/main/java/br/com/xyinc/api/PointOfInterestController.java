package br.com.xyinc.api;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.xyinc.application.service.PointOfInterestService;
import br.com.xyinc.domain.model.PointOfInterest;
import br.com.xyinc.domain.model.PointOfInterestResponse;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/poi")
@RequiredArgsConstructor
@Validated
public class PointOfInterestController {

    private final PointOfInterestService pointOfInterestService;

    @PostMapping
    public ResponseEntity<PointOfInterest> createPOI(@RequestBody @Valid PointOfInterest poi) {

        return ResponseEntity.ok(pointOfInterestService.createPointOfInterest(poi));
    }

    @GetMapping
    public ResponseEntity<List<PointOfInterest>> listAllPointsOfInterest() {

        return ResponseEntity.ok(pointOfInterestService.listAllPointsOfInterest());
    }

    @GetMapping("/distancia")
    public ResponseEntity<List<PointOfInterest>> getPOIsByProximity(@Valid PointOfInterestResponse pointResponse) {

        return ResponseEntity.ok(pointOfInterestService.getPOIsByProximity(pointResponse));
    }

}
