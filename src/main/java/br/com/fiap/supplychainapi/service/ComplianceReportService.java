package br.com.fiap.supplychainapi.service;

import br.com.fiap.supplychainapi.exception.ResourceNotFoundException;
import br.com.fiap.supplychainapi.model.ComplianceReport;
import br.com.fiap.supplychainapi.repository.ComplianceReportRepository;
import br.com.fiap.supplychainapi.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ComplianceReportService {

    private final ComplianceReportRepository complianceReportRepository;
    private final SupplierRepository supplierRepository;

    @Autowired
    public ComplianceReportService(ComplianceReportRepository complianceReportRepository,
                                 SupplierRepository supplierRepository) {
        this.complianceReportRepository = complianceReportRepository;
        this.supplierRepository = supplierRepository;
    }

    @Transactional
    public ComplianceReport createComplianceReport(ComplianceReport complianceReport) {
        if (!supplierRepository.existsById(complianceReport.getSupplier().getId())) {
            throw new ResourceNotFoundException("Supplier not found with id: " + complianceReport.getSupplier().getId());
        }
        return complianceReportRepository.save(complianceReport);
    }

    public List<ComplianceReport> getComplianceReportsBySupplier(Long supplierId) {
        if (!supplierRepository.existsById(supplierId)) {
            throw new ResourceNotFoundException("Supplier not found with id: " + supplierId);
        }
        return complianceReportRepository.findBySupplierId(supplierId);
    }

    public ComplianceReport getComplianceReportById(Long id) {
        return complianceReportRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Compliance report not found with id: " + id));
    }

    @Transactional
    public ComplianceReport updateComplianceReport(Long id, ComplianceReport complianceReportDetails) {
        ComplianceReport complianceReport = getComplianceReportById(id);
        complianceReport.setReportDate(complianceReportDetails.getReportDate());
        complianceReport.setComplianceStatus(complianceReportDetails.getComplianceStatus());
        complianceReport.setRegulatoryRequirements(complianceReportDetails.getRegulatoryRequirements());
        complianceReport.setAuditFindings(complianceReportDetails.getAuditFindings());
        complianceReport.setCorrectiveActions(complianceReportDetails.getCorrectiveActions());
        return complianceReportRepository.save(complianceReport);
    }

    @Transactional
    public void deleteComplianceReport(Long id) {
        if (!complianceReportRepository.existsById(id)) {
            throw new ResourceNotFoundException("Compliance report not found with id: " + id);
        }
        complianceReportRepository.deleteById(id);
    }

    public List<ComplianceReport> getNonCompliantReports() {
        return complianceReportRepository.findByComplianceStatus("NON_COMPLIANT");
    }
} 