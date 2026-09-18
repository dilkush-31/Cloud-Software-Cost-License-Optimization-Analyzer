package com.cloudoptimizer.controller;

import com.cloudoptimizer.entity.CloudResource;
import com.cloudoptimizer.repository.CloudResourceRepository;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/resources")
@CrossOrigin(origins = "http://localhost:5173")
public class CloudResourceController {
    private final CloudResourceRepository repository;

    public CloudResourceController(CloudResourceRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<CloudResource> getAll() {
        return repository.findAll();
    }

    @PostMapping
    public CloudResource create(@RequestBody CloudResource resource) {
        return repository.save(resource);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
