package br.com.fiap.supplychainapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.fiap.supplychainapi.exception.ResourceNotFoundException;
import br.com.fiap.supplychainapi.model.CarbonFootprint;
import br.com.fiap.supplychainapi.repository.CarbonFootprintRepository;
import br.com.fiap.supplychainapi.repository.SupplierRepository;

@Service
public class CarbonFootprintService {

    private final CarbonFootprintRepository carbonFootprintRepository;
    private final SupplierRepository supplierRepository;

    @Autowired
    public CarbonFootprintService(CarbonFootprintRepository carbonFootprintRepository, 
                                SupplierRepository supplierRepository) {
        this.carbonFootprintRepository = carbonFootprintRepository;
        this.supplierRepository = supplierRepository;
    }

    @Transactional
    public CarbonFootprint createCarbonFootprint(CarbonFootprint carbonFootprint) {
        if (!supplierRepository.existsById(carbonFootprint.getSupplier().getId())) {
            throw new ResourceNotFoundException("Supplier not found with id: " + carbonFootprint.getSupplier().getId());
        }
        return carbonFootprintRepository.save(carbonFootprint);
    }

    public List<CarbonFootprint> getCarbonFootprintsBySupplier(Long supplierId) {
        if (!supplierRepository.existsById(supplierId)) {
            throw new ResourceNotFoundException("Supplier not found with id: " + supplierId);
        }
        return carbonFootprintRepository.findBySupplierId(supplierId);
    }

    public CarbonFootprint getCarbonFootprintById(Long id) {
        return carbonFootprintRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Carbon footprint not found with id: " + id));
    }

    @Transactional
    public CarbonFootprint updateCarbonFootprint(Long id, CarbonFootprint carbonFootprintDetails) {
        CarbonFootprint carbonFootprint = getCarbonFootprintById(id);
        carbonFootprint.setEmissionDate(carbonFootprintDetails.getEmissionDate());
        carbonFootprint.setProductionEmissions(carbonFootprintDetails.getProductionEmissions());
        carbonFootprint.setTransportationEmissions(carbonFootprintDetails.getTransportationEmissions());
        carbonFootprint.setEmissionSource(carbonFootprintDetails.getEmissionSource());
        carbonFootprint.setMitigationMeasures(carbonFootprintDetails.getMitigationMeasures());
        return carbonFootprintRepository.save(carbonFootprint);
    }

    @Transactional
    public void deleteCarbonFootprint(Long id) {
        if (!carbonFootprintRepository.existsById(id)) {
            throw new ResourceNotFoundException("Carbon footprint not found with id: " + id);
        }
        carbonFootprintRepository.deleteById(id);
    }

    public Double calculateTotalEmissionsBySupplier(Long supplierId) {
        if (!supplierRepository.existsById(supplierId)) {
            throw new ResourceNotFoundException("Supplier not found with id: " + supplierId);
        }
        return carbonFootprintRepository.findBySupplierId(supplierId).stream()
                .mapToDouble(cf -> cf.getProductionEmissions() + cf.getTransportationEmissions())
                .sum();
    }
} 