package br.com.xyinc.domain.port;

import java.util.List;

import br.com.xyinc.domain.model.PointOfInterest;

public interface PointOfInterestRepository {

    PointOfInterest save(PointOfInterest poi);

    List<PointOfInterest> findAll();

}
