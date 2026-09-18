# Cloud Software Cost & License Optimization Analyzer

A KPMG-oriented analytics project for analyzing cloud resources and software subscriptions, identifying unused/underutilized assets, and estimating potential savings.

## Stack
- Java 17
- Spring Boot
- Spring Data JPA
- MySQL
- React (frontend planned)
- Python/Pandas (analysis extension planned)
- AWS concepts

## Backend setup

1. Create MySQL database:
   CREATE DATABASE cloud_optimizer;

2. Open:
   src/main/resources/application.properties

3. Change:
   spring.datasource.password=YOUR_MYSQL_PASSWORD

4. Run:
   mvn spring-boot:run

## APIs

GET  /api/resources
POST /api/resources
DELETE /api/resources/{id}

GET  /api/licenses
POST /api/licenses
DELETE /api/licenses/{id}

POST /api/optimization/analyze
GET  /api/optimization/findings
GET  /api/optimization/summary

## Sample optimization rules
- Software: purchased licenses minus active users > 0 => unused/excess license finding.
- Cloud: utilization below 20% => underutilized resource finding.
- Savings are estimates for analysis/demo purposes and should not be presented as actual financial savings without validation.

## Next upgrades
- React dashboard
- Python/Pandas CSV ingestion
- Excel export
- AWS Cost Explorer integration
- Renewal alerts
- Power BI dashboard
