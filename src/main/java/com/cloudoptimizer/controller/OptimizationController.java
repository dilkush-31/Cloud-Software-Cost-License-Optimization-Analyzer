package com.cloudoptimizer.controller;

import com.cloudoptimizer.entity.*;
import com.cloudoptimizer.repository.*;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;

@RestController
@RequestMapping("/api/optimization")
@CrossOrigin(origins = "http://localhost:5173")
public class OptimizationController {
    private final CloudResourceRepository resourceRepository;
    private final SoftwareLicenseRepository licenseRepository;
    private final OptimizationFindingRepository findingRepository;

    public OptimizationController(CloudResourceRepository resourceRepository,
                                   SoftwareLicenseRepository licenseRepository,
                                   OptimizationFindingRepository findingRepository) {
        this.resourceRepository = resourceRepository;
        this.licenseRepository = licenseRepository;
        this.findingRepository = findingRepository;
    }

    @PostMapping("/analyze")
    public List<OptimizationFinding> analyze() {
        List<OptimizationFinding> findings = new ArrayList<>();
        findingRepository.deleteAll();

        for (SoftwareLicense l : licenseRepository.findAll()) {
            int unused = Math.max(0, l.getPurchasedQuantity() - l.getActiveUsers());
            l.setUnusedLicenses(unused);
            int excess = Math.max(0, l.getActiveUsers() - l.getPurchasedQuantity());
            l.setExcessUsage(excess);

            if (l.getExcessUsage() > 0) {
                double monthlyRisk = l.getExcessUsage() * l.getCostPerLicense();

                findings.add(new OptimizationFinding(
                        null,
                        "LICENSE_COMPLIANCE",
                        l.getSoftwareName(),
                        l.getExcessUsage() + " license(s) potentially overused",
                        "HIGH",
                        0.0,
                        monthlyRisk * 12,
                        "Review license entitlement and purchase additional licenses if required.",
                        "OPEN"
                ));
            }
            if (l.getUnusedLicenses() > 0) {

                double monthly = unused * l.getCostPerLicense();
                findings.add(new OptimizationFinding(
                        null, "SOFTWARE_LICENSE", l.getSoftwareName(),
                        unused + " unused license(s)", unused >= 20 ? "HIGH" : "MEDIUM",
                        monthly, monthly * 12,
                        "Review unused licenses and remove or reassign them before renewal.",
                        "OPEN"
                ));
            }
            long daysUntilRenewal = ChronoUnit.DAYS.between(
                    LocalDate.now(),
                    l.getRenewalDate()
            );

            if (daysUntilRenewal <= 90 && daysUntilRenewal >= 0) {
                findings.add(new OptimizationFinding(
                        null,
                        "RENEWAL_RISK",
                        l.getSoftwareName(),
                        "License renewal due in " + daysUntilRenewal + " day(s)",
                        daysUntilRenewal <= 30 ? "HIGH" : "MEDIUM",
                        0.0,
                        0.0,
                        "Review usage and license requirements before renewal to avoid unnecessary purchases.",
                        "OPEN"
                ));
            }
            findingRepository.saveAll(findings);
            return findings;
        }


        for (CloudResource r : resourceRepository.findAll()) {
            if (r.getUtilizationPercentage() != null && r.getUtilizationPercentage() < 20
                    && r.getMonthlyCost() != null && r.getMonthlyCost() > 0) {
                double monthly = r.getMonthlyCost() * 0.30;
                findings.add(new OptimizationFinding(
                    null, "CLOUD_RESOURCE", r.getResourceId(),
                    "Underutilized resource (" + r.getUtilizationPercentage() + "% usage)",
                    "MEDIUM", monthly, monthly * 12,
                    "Review resource sizing and consider downsizing or stopping it if business requirements allow.",
                    "OPEN"
                ));
            }
        }

        return findingRepository.saveAll(findings);
    }

    @GetMapping("/findings")
    public List<OptimizationFinding> findings() {
        return findingRepository.findAll();
    }

    @GetMapping("/summary")
    public Map<String, Object> summary() {
        List<OptimizationFinding> findings = findingRepository.findAll();
        double monthly = findings.stream()
                .mapToDouble(f -> Optional.ofNullable(f.getEstimatedMonthlySaving()).orElse(0.0))
                .sum();
        double annual = monthly * 12;

        Map<String, Object> result = new LinkedHashMap<>();
        result.put("cloudResources", resourceRepository.count());
        result.put("softwareLicenses", licenseRepository.count());
        result.put("optimizationFindings", findings.size());
        result.put("potentialMonthlySavings", monthly);
        result.put("potentialAnnualSavings", annual);
        return result;
    }
}
