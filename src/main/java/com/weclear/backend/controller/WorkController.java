package com.weclear.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.weclear.backend.service.WorkService;

import com.weclear.backend.model.Category;

import com.weclear.backend.model.Work;

import java.util.*;

@RestController
@RequestMapping("/api1")
public class WorkController {

    @Autowired
    private WorkService workService;

    // Get all services
    @GetMapping("/services")
    public List<Work> getALlWorks() {
        return workService.getALlWorks();
    }

    // Get service by ID
    @GetMapping("/serviceid/{id}")
    public ResponseEntity<Work> getServiceById(@PathVariable Long id) {
        Optional<Work> service = workService.getWorkById(id);
        return service.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/services/{category}/{subCategory}")
    public ResponseEntity<List<Work>> getServiceBySubCategory(@PathVariable String category, @PathVariable String subCategory) {
        try {
            Category.WorkCategory categoryEnum = Category.WorkCategory.valueOf(category.trim().toUpperCase());
            Category.WorkSubCategory subCategoryEnum = Category.WorkSubCategory.valueOf(subCategory.trim().toUpperCase());
            List<Work> services = workService.getWorksBySubCategory(categoryEnum, subCategoryEnum);
            if (services.isEmpty()) {
                return ResponseEntity.noContent().build(); // No services found
            }

            return ResponseEntity.ok(services);
        } catch (IllegalArgumentException e) {
            // Handle invalid category error
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            // Handle unexpected errors
            return ResponseEntity.status(500).build();
        }
    }


    // Create a new service
    @PostMapping("/addservice")
    @PreAuthorize("hasRole('ADMIN')")
    public Work createService(@RequestBody Work service) {
        return workService.saveWork(service);
    }

    // Update an existing service
    @PutMapping("updateservice/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Work> updateService(@PathVariable Long id, @RequestBody Work serviceDetails) {
        Optional<Work> optionalService = workService.getWorkById(id);
        if (optionalService.isPresent()) {
            Work service = optionalService.get();
            service.setName(serviceDetails.getName());
            service.setDescription(serviceDetails.getDescription());
            service.setPrice(serviceDetails.getPrice());
            service.setImageUrl(serviceDetails.getImageUrl());
            Work updatedService = workService.saveWork(service);
            return ResponseEntity.ok(updatedService);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Delete a service
    @DeleteMapping("deleteservice/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteService(@PathVariable Long id) {
        Optional<Work> optionalService = workService.getWorkById(id);
        if (optionalService.isPresent()) {
            workService.deleteWork(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
