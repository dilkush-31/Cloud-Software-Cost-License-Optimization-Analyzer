package com.cloudoptimizer.repository;

import com.cloudoptimizer.entity.CloudResource;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CloudResourceRepository extends JpaRepository<CloudResource, Long> {
}
