INSERT INTO cloud_resources
(resource_id, provider, resource_type, region, department, owner, monthly_cost, utilization_percentage, status)
VALUES
('i-1001','AWS','EC2','ap-south-1','Engineering','Rahul',8500,18,'RUNNING'),
('i-1002','AWS','EC2','ap-south-1','Finance','Priya',6200,72,'RUNNING'),
('rds-2001','AWS','RDS','ap-south-1','Engineering','Amit',12000,15,'RUNNING'),
('s3-3001','AWS','S3','ap-south-1','Marketing','Neha',1800,82,'ACTIVE');

INSERT INTO software_licenses
(software_name, vendor, license_type, purchased_quantity, assigned_quantity, active_users, cost_per_license, renewal_date, department)
VALUES
('Microsoft 365','Microsoft','Subscription',100,100,63,1200,'2026-12-15','Finance'),
('Adobe Acrobat Pro','Adobe','Subscription',50,48,38,900,'2027-01-03','Design'),
('JetBrains All Products','JetBrains','Subscription',30,25,19,1100,'2026-11-20','Engineering'),
('Tableau Creator','Salesforce','Subscription',20,18,8,2200,'2027-02-10','Analytics');
