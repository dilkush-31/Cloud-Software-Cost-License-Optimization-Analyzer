package com.cloudoptimizer.controller;

import com.cloudoptimizer.entity.SoftwareLicense;
import com.cloudoptimizer.repository.SoftwareLicenseRepository;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/licenses")
@CrossOrigin(origins = "http://localhost:5173")
public class SoftwareLicenseController {
    private final SoftwareLicenseRepository repository;

    public SoftwareLicenseController(SoftwareLicenseRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<SoftwareLicense> getAll() {
        return repository.findAll();
    }

    @PostMapping
    public SoftwareLicense create(@RequestBody SoftwareLicense license) {
        return repository.save(license);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
