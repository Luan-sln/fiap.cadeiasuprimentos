package br.com.fiap.supplychainapi.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.supplychainapi.model.Supplier;
import br.com.fiap.supplychainapi.service.SupplierService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/suppliers")
@Tag(name = "Supplier", description = "Supplier Management APIs")
public class SupplierController {

    private final SupplierService supplierService;

    public SupplierController(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    @PostMapping
    @Operation(
        summary = "Create a new supplier",
        description = "Creates a new supplier with the provided information",
        responses = {
            @ApiResponse(responseCode = "200", description = "Supplier created successfully", 
                content = @Content(schema = @Schema(implementation = Supplier.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input provided")
        }
    )
    public ResponseEntity<Supplier> createSupplier(@Valid @RequestBody Supplier supplier) {
        return ResponseEntity.ok(supplierService.createSupplier(supplier));
    }

    @GetMapping
    @Operation(
        summary = "Get all suppliers",
        description = "Retrieves a list of all registered suppliers",
        responses = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved the list of suppliers")
        }
    )
    public ResponseEntity<List<Supplier>> getAllSuppliers() {
        return ResponseEntity.ok(supplierService.getAllSuppliers());
    }

    @GetMapping("/{id}")
    @Operation(
        summary = "Get supplier by ID",
        description = "Retrieves a specific supplier based on the provided ID",
        responses = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved the supplier"),
            @ApiResponse(responseCode = "404", description = "Supplier not found")
        }
    )
    public ResponseEntity<Supplier> getSupplierById(
            @Parameter(description = "ID of the supplier to retrieve", required = true) 
            @PathVariable Long id) {
        return ResponseEntity.ok(supplierService.getSupplierById(id));
    }

    @PutMapping("/{id}")
    @Operation(
        summary = "Update a supplier",
        description = "Updates an existing supplier with the provided information",
        responses = {
            @ApiResponse(responseCode = "200", description = "Supplier updated successfully"),
            @ApiResponse(responseCode = "404", description = "Supplier not found")
        }
    )
    public ResponseEntity<Supplier> updateSupplier(
            @Parameter(description = "ID of the supplier to update", required = true) 
            @PathVariable Long id, 
            @Valid @RequestBody Supplier supplier) {
        return ResponseEntity.ok(supplierService.updateSupplier(id, supplier));
    }

    @DeleteMapping("/{id}")
    @Operation(
        summary = "Delete a supplier",
        description = "Deletes a supplier based on the provided ID",
        responses = {
            @ApiResponse(responseCode = "204", description = "Supplier deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Supplier not found")
        }
    )
    public ResponseEntity<Void> deleteSupplier(
            @Parameter(description = "ID of the supplier to delete", required = true) 
            @PathVariable Long id) {
        supplierService.deleteSupplier(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/sustainable")
    @Operation(
        summary = "Get suppliers by sustainability score",
        description = "Retrieves suppliers with a sustainability score above the specified minimum",
        responses = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved suppliers")
        }
    )
    public ResponseEntity<List<Supplier>> getSuppliersBySustainabilityScore(
            @Parameter(description = "Minimum sustainability score threshold", required = true) 
            @RequestParam Integer minScore) {
        return ResponseEntity.ok(supplierService.getSuppliersBySustainabilityScore(minScore));
    }
} 