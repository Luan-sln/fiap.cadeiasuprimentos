package br.com.fiap.supplychainapi.repository;

import br.com.fiap.supplychainapi.model.CarbonFootprint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CarbonFootprintRepository extends JpaRepository<CarbonFootprint, Long> {
    List<CarbonFootprint> findBySupplierId(Long supplierId);
} 