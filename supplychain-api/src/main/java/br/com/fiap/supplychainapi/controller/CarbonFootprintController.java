package br.com.fiap.supplychainapi.controller;

import br.com.fiap.supplychainapi.model.CarbonFootprint;
import br.com.fiap.supplychainapi.service.CarbonFootprintService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/carbon-footprints")
public class CarbonFootprintController {

    private final CarbonFootprintService carbonFootprintService;

    public CarbonFootprintController(CarbonFootprintService carbonFootprintService) {
        this.carbonFootprintService = carbonFootprintService;
    }

    @PostMapping
    public ResponseEntity<CarbonFootprint> createCarbonFootprint(@Valid @RequestBody CarbonFootprint carbonFootprint) {
        return ResponseEntity.ok(carbonFootprintService.createCarbonFootprint(carbonFootprint));
    }

    @GetMapping("/supplier/{supplierId}")
    public ResponseEntity<List<CarbonFootprint>> getCarbonFootprintsBySupplier(@PathVariable Long supplierId) {
        return ResponseEntity.ok(carbonFootprintService.getCarbonFootprintsBySupplier(supplierId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarbonFootprint> getCarbonFootprintById(@PathVariable Long id) {
        return ResponseEntity.ok(carbonFootprintService.getCarbonFootprintById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CarbonFootprint> updateCarbonFootprint(@PathVariable Long id, @Valid @RequestBody CarbonFootprint carbonFootprint) {
        return ResponseEntity.ok(carbonFootprintService.updateCarbonFootprint(id, carbonFootprint));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCarbonFootprint(@PathVariable Long id) {
        carbonFootprintService.deleteCarbonFootprint(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/total-emissions/supplier/{supplierId}")
    public ResponseEntity<Double> getTotalEmissionsBySupplier(@PathVariable Long supplierId) {
        return ResponseEntity.ok(carbonFootprintService.calculateTotalEmissionsBySupplier(supplierId));
    }
} 