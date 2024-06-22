package br.com.xyinc.infrastructure.adapter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.xyinc.domain.model.PointOfInterest;
import br.com.xyinc.domain.port.PointOfInterestRepository;

@Repository
public interface JPAPOIRepository extends JpaRepository<PointOfInterest, Long>, PointOfInterestRepository {

}
