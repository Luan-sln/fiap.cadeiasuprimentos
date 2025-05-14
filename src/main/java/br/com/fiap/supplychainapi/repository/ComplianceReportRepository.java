package br.com.fiap.supplychainapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.fiap.supplychainapi.model.ComplianceReport;

@Repository
public interface ComplianceReportRepository extends JpaRepository<ComplianceReport, Long> {
    List<ComplianceReport> findBySupplierId(Long supplierId);
    List<ComplianceReport> findByComplianceStatus(String complianceStatus);
} 