package br.com.fiap.supplychainapi.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "CARBON_FOOTPRINTS")
public class CarbonFootprint {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "carbon_seq")
    @SequenceGenerator(name = "carbon_seq", sequenceName = "CARBON_SEQ", allocationSize = 1)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "supplier_id", nullable = false)
    private Supplier supplier;

    @NotNull
    @Column(name = "emission_date", nullable = false)
    private LocalDateTime emissionDate;

    @NotNull
    @Positive
    @Column(name = "production_emissions", nullable = false)
    private Double productionEmissions;

    @NotNull
    @Positive
    @Column(name = "transportation_emissions", nullable = false)
    private Double transportationEmissions;

    @Column(name = "emission_source")
    private String emissionSource;

    @Column(name = "mitigation_measures")
    private String mitigationMeasures;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
    
    // Explicit getter and setter methods
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public LocalDateTime getEmissionDate() {
        return emissionDate;
    }

    public void setEmissionDate(LocalDateTime emissionDate) {
        this.emissionDate = emissionDate;
    }

    public Double getProductionEmissions() {
        return productionEmissions;
    }

    public void setProductionEmissions(Double productionEmissions) {
        this.productionEmissions = productionEmissions;
    }

    public Double getTransportationEmissions() {
        return transportationEmissions;
    }

    public void setTransportationEmissions(Double transportationEmissions) {
        this.transportationEmissions = transportationEmissions;
    }

    public String getEmissionSource() {
        return emissionSource;
    }

    public void setEmissionSource(String emissionSource) {
        this.emissionSource = emissionSource;
    }

    public String getMitigationMeasures() {
        return mitigationMeasures;
    }

    public void setMitigationMeasures(String mitigationMeasures) {
        this.mitigationMeasures = mitigationMeasures;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
} 