package br.com.fiap.supplychainapi.controller;

import br.com.fiap.supplychainapi.model.ComplianceReport;
import br.com.fiap.supplychainapi.service.ComplianceReportService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/compliance-reports")
public class ComplianceReportController {

    private final ComplianceReportService complianceReportService;

    public ComplianceReportController(ComplianceReportService complianceReportService) {
        this.complianceReportService = complianceReportService;
    }

    @PostMapping
    public ResponseEntity<ComplianceReport> createComplianceReport(@Valid @RequestBody ComplianceReport report) {
        return ResponseEntity.ok(complianceReportService.createComplianceReport(report));
    }

    @GetMapping("/supplier/{supplierId}")
    public ResponseEntity<List<ComplianceReport>> getComplianceReportsBySupplier(@PathVariable Long supplierId) {
        return ResponseEntity.ok(complianceReportService.getComplianceReportsBySupplier(supplierId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ComplianceReport> getComplianceReportById(@PathVariable Long id) {
        return ResponseEntity.ok(complianceReportService.getComplianceReportById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ComplianceReport> updateComplianceReport(@PathVariable Long id, @Valid @RequestBody ComplianceReport report) {
        return ResponseEntity.ok(complianceReportService.updateComplianceReport(id, report));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComplianceReport(@PathVariable Long id) {
        complianceReportService.deleteComplianceReport(id);
        return ResponseEntity.noContent().build();
    }
} 