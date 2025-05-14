package br.com.fiap.supplychainapi.service;

import br.com.fiap.supplychainapi.exception.ResourceNotFoundException;
import br.com.fiap.supplychainapi.model.Supplier;
import br.com.fiap.supplychainapi.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SupplierService {

    private final SupplierRepository supplierRepository;

    @Autowired
    public SupplierService(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    @Transactional
    public Supplier createSupplier(Supplier supplier) {
        if (supplierRepository.existsByEmail(supplier.getEmail())) {
            throw new IllegalArgumentException("Email already exists");
        }
        return supplierRepository.save(supplier);
    }

    public List<Supplier> getAllSuppliers() {
        return supplierRepository.findAll();
    }

    public Supplier getSupplierById(Long id) {
        return supplierRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Supplier not found with id: " + id));
    }

    @Transactional
    public Supplier updateSupplier(Long id, Supplier supplierDetails) {
        Supplier supplier = getSupplierById(id);
        supplier.setName(supplierDetails.getName());
        supplier.setEmail(supplierDetails.getEmail());
        supplier.setSustainablePracticesScore(supplierDetails.getSustainablePracticesScore());
        supplier.setFairLaborScore(supplierDetails.getFairLaborScore());
        supplier.setCertificationStatus(supplierDetails.getCertificationStatus());
        supplier.setLastAuditDate(supplierDetails.getLastAuditDate());
        return supplierRepository.save(supplier);
    }

    @Transactional
    public void deleteSupplier(Long id) {
        if (!supplierRepository.existsById(id)) {
            throw new ResourceNotFoundException("Supplier not found with id: " + id);
        }
        supplierRepository.deleteById(id);
    }

    public List<Supplier> getSuppliersBySustainabilityScore(Integer minScore) {
        return supplierRepository.findAll().stream()
                .filter(supplier -> supplier.getSustainablePracticesScore() != null 
                        && supplier.getSustainablePracticesScore() >= minScore)
                .toList();
    }
} 