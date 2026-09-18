package com.cloudoptimizer.repository;

import com.cloudoptimizer.entity.SoftwareLicense;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SoftwareLicenseRepository extends JpaRepository<SoftwareLicense, Long> {
}
