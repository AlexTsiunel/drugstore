/*
TRUNCATE TABLE orderstatuses CASCADE;
TRUNCATE TABLE dosage_form CASCADE;
TRUNCATE TABLE clients CASCADE;
TRUNCATE TABLE doctors CASCADE;
TRUNCATE TABLE recipes CASCADE;
TRUNCATE TABLE orders CASCADE;
TRUNCATE TABLE drugs CASCADE;
TRUNCATE TABLE pharmacists CASCADE;
TRUNCATE TABLE order_infos CASCADE;
TRUNCATE TABLE recipe_infos CASCADE;
*/


INSERT INTO orderstatuses (name)
VALUES ('PROCESSING'),
	   ('PENDING'),
	   ('PAID'),
	   ('CANCELED');

INSERT INTO dosage_form (name)
VALUES ('TABLET'),
		('CAPSULE'),
	   ('SUSPENSION'),
	   ('INJECTION');
	  
INSERT INTO route_administration (name)
VALUES ('ORALLY'),
		('PARENTERAL');
	
INSERT INTO clients (first_name, last_name, email, password)
VALUES ('Neena', 'Kochar','kochhar@gmail.com', 'kochhar022005'),
	('Lex', 'De Haan','haan@gmail.com', 'haan1986'),
	('Alexander', 'Hunold','hunold@gmail.com', 'hunol02051786'),
	('Bruce', 'Ernst','ernst@gmail.com', 'ernst2022'),
	('David', 'Austin','austin@gmail.com', 'austin2006'),
	('Valli', 'Pataballa','pataballa@gmail.com', 'pataballa071983'),
	('Diana', 'Lorentz','lorentz@gmail.com', 'lorentz12345'),
	('Nancy', 'Greenberg','greenberg@gmail.com', 'greenberg121314'),
	('Daniel', 'Faviet','faviet@gmail.com', 'faviet1812'),
	('Johnr', 'Chen','chen@gmail.com', 'chen654321'),
	('Ismael', 'Sciarra','sciarra@gmail.com', 'sciarra262823'),
	('Jose Manuel', 'Urman','urman@gmail.com', 'urman1985'),
	('Luis', 'Popp','popp@gmail.com', 'popp19450905'),
	('Den', 'Raphaely','raphaely@gmail.com', 'raphaely22061941'),
	('Alexander', 'Khoo','khoo@gmail.com', 'khoo01091939'),
	('Shelli', 'Baida','baida@gmail.com', 'baida10051945'),
	('Sigal', 'Tobias','tobias@gmail.com', 'tobias2007'),
	('Guy', 'Himuro','himuro@gmail.com', 'himuro12311986'),
	('Karen', 'Colmenares','colmenares@gmail.com', 'colmenares1816');
   


INSERT INTO doctors (first_name, last_name, email, password)
VALUES ('Payam', 'Kaufling','kaufling@gmail.com', 'kaufling112'),
	('Shanta', 'Vollman','vollman@gmail.com', 'vollman911'),
	('Kevin', 'Mourgos','mourgos@gmail.com', 'mourgos5957'),
	('Julia', 'Nayer','nayer@gmail.com', 'nayer02031982');

INSERT INTO recipes (doctor_id, client_id, start_date, end_date)
VALUES (1, 1, now()+interval '1 day', now()+interval '8 day'),
(2, 2, now()+interval '1 day', now()+interval '8 day'),
(1, 1, now()+interval '1 day', now()+interval '8 day'),
(4, 19, now()+interval '1 day', now()+interval '8 day'),
(1, 4, now()+interval '1 day', now()+interval '8 day'),
(3, 17, now()+interval '1 day', now()+interval '8 day'),
(1, 5, now()+interval '1 day', now()+interval '8 day'),
(2, 1, now()+interval '5 day', now()+interval '13 day'),
(4, 1, now()+interval '1 day', now()+interval '8 day'),
(3, 1, now()+interval '3 day', now()+interval '10 day'),
(2, 3, now()+interval '1 day', now()+interval '8 day'),
(1, 11, now()+interval '2 day', now()+interval '9 day'),
(3, 9, now()+interval '1 day', now()+interval '9 day'),
(2, 10, now()+interval '4 day', now()+interval '13 day'),
(1, 11, now()+interval '1 day', now()+interval '8 day'),
(4, 12, now()+interval '2 day', now()+interval '9 day'),
(1, 9, now(), now()+interval '8 day');

INSERT INTO pharmacists (first_name, last_name, email, password)
VALUES ('Matthew', 'Weiss','weiss@gmail.com', 'weiss101'),
    ('Adam', 'Fripp','fripp@gmail.com', 'fripp103');
   
INSERT INTO orders (pharmacist_id, client_id, total_coast, orderstatus_id)
VALUES (1, 1, 20.8,(SELECT id FROM orderstatuses WHERE name = 'PROCESSING')),
	(1, 2, 20.8,(SELECT id FROM orderstatuses WHERE name = 'PENDING')),
	(2, 1, 20.8,(SELECT id FROM orderstatuses WHERE name = 'PENDING')),
	(1, 4, 20.8,(SELECT id FROM orderstatuses WHERE name = 'PENDING')),
	(1, 3, 20.8,(SELECT id FROM orderstatuses WHERE name = 'PAID')),
	(2, 2, 20.8,(SELECT id FROM orderstatuses WHERE name = 'PAID')),
	(1, 12, 20.8,(SELECT id FROM orderstatuses WHERE name = 'PENDING')),
	(1, 14, 20.8,(SELECT id FROM orderstatuses WHERE name = 'PENDING')),
	(2, 19, 20.8,(SELECT id FROM orderstatuses WHERE name = 'PAID')),
	(1, 1, 20.8,(SELECT id FROM orderstatuses WHERE name = 'PAID')),
	(1, 17, 20.8,(SELECT id FROM orderstatuses WHERE name = 'PAID')),
	(2, 9, 20.8,(SELECT id FROM orderstatuses WHERE name = 'PAID')),
	(1, 1, 20.8,(SELECT id FROM orderstatuses WHERE name = 'PENDING')),
	(2, 1, 20.8,(SELECT id FROM orderstatuses WHERE name = 'CANCELED')),
	(1, 12, 20.8,(SELECT id FROM orderstatuses WHERE name = 'PENDING')),
	(1, 1, 20.8,(SELECT id FROM orderstatuses WHERE name = 'PENDING')),
	(2, 19, 20.8,(SELECT id FROM orderstatuses WHERE name = 'PAID')),
	(1, 15, 20.8,(SELECT id FROM orderstatuses WHERE name = 'PAID')),
	(1, 17, 20.8,(SELECT id FROM orderstatuses WHERE name = 'PAID')),
	(2, 9, 20.8,(SELECT id FROM orderstatuses WHERE name = 'PAID')),
	(1, 1, 20.8,(SELECT id FROM orderstatuses WHERE name = 'PENDING')),
	(2, 5, 20.8,(SELECT id FROM orderstatuses WHERE name = 'CANCELED')),
	(1, 6, 20.8,(SELECT id FROM orderstatuses WHERE name = 'PENDING'));

--HELGA!!!!!!!
INSERT INTO drugs (name, release_form, dosage_form_id, route_administration_id, is_recipe, price, quantity_in_stock)
VALUES ('Thiotriazolin', 'Solution for injections 2.5% 4ml N10',(SELECT id FROM dosage_form WHERE name = 'INJECTION'), (SELECT id FROM route_administration WHERE name = 'PARENTERAL'), FALSE , 48.62, 50), 
       ('Thiotriazolin', 'Solution for injections  2.5% 2ml N10',(SELECT id FROM dosage_form WHERE name = 'INJECTION'), (SELECT id FROM route_administration WHERE name = 'PARENTERAL'), FALSE, 20.90, 50),	
       ('Thiotriazolin', 'Tablets 200mg N90',(SELECT id FROM dosage_form WHERE name = 'TABLET'), (SELECT id FROM route_administration WHERE name = 'ORALLY'), FALSE, 65.42, 50),
      ('MiLDronate', 'Capsules 500mg N90',(SELECT id FROM dosage_form WHERE name = 'CAPSULE'), (SELECT id FROM route_administration WHERE name = 'ORALLY'), FALSE, 79.24, 50),--4
      ('MiLDronate', 'Capsules 500mg N60',(SELECT id FROM dosage_form WHERE name = 'CAPSULE'), (SELECT id FROM route_administration WHERE name = 'ORALLY'), FALSE, 53.01, 50),--5
      ('MiLDronate', 'Capsules 250mg N40',(SELECT id FROM dosage_form WHERE name = 'CAPSULE'), (SELECT id FROM route_administration WHERE name = 'ORALLY'), FALSE, 37.94, 50),--6
      ('MiLDronate', 'Capsules 250mg  N20',(SELECT id FROM dosage_form WHERE name = 'CAPSULE'), (SELECT id FROM route_administration WHERE name = 'ORALLY'), FALSE, 24.12, 50),--7
      ('MiLDronate', 'Solution for injections 10% 5ml N20',(SELECT id FROM dosage_form WHERE name = 'INJECTION'), (SELECT id FROM route_administration WHERE name = 'PARENTERAL'), FALSE, 39.20, 50),--8
      ('MiLDronate', 'Solution for injections 10% 5ml N10',(SELECT id FROM dosage_form WHERE name = 'INJECTION'), (SELECT id FROM route_administration WHERE name = 'PARENTERAL'), FALSE, 39.20, 50),--9
      ('Irbesartan', 'Coated tablets 75mg N30',(SELECT id FROM dosage_form WHERE name = 'TABLET'), (SELECT id FROM route_administration WHERE name = 'ORALLY'), FALSE, 8.94, 50),--10
      ('Doxycycline', 'Capsules 100mg N10',(SELECT id FROM dosage_form WHERE name = 'CAPSULE'), (SELECT id FROM route_administration WHERE name = 'ORALLY'), FALSE, 3.35, 50),--11
      ('Medomycin', 'Capsules 100mg N10',(SELECT id FROM dosage_form WHERE name = 'CAPSULE'), (SELECT id FROM route_administration WHERE name = 'ORALLY'), FALSE, 3.92, 50),--12
      ('Butorphanoli tartras', 'Solution for injections 0.2% 1ml N1',(SELECT id FROM dosage_form WHERE name = 'INJECTION'), (SELECT id FROM route_administration WHERE name = 'PARENTERAL'), TRUE, 45.20, 50),--13
      ('Butorphanoli tartras', 'Solution for injections 0.2% 1ml N20',(SELECT id FROM dosage_form WHERE name = 'INJECTION'), (SELECT id FROM route_administration WHERE name = 'PARENTERAL'), TRUE, 46.30, 50),--14
      ('Sodium chloride', 'Solution for injections 0.9% 5ml N10',(SELECT id FROM dosage_form WHERE name = 'INJECTION'), (SELECT id FROM route_administration WHERE name = 'PARENTERAL'), FALSE, 3.10, 50),--15
      ('Sodium chloride', 'Solution for injections 0.9% 2ml N10',(SELECT id FROM dosage_form WHERE name = 'INJECTION'), (SELECT id FROM route_administration WHERE name = 'PARENTERAL'), FALSE, 1.20, 50),--16
      ('Dicloberl', 'Solution for injections 75mg 3ml N5',(SELECT id FROM dosage_form WHERE name = 'INJECTION'), (SELECT id FROM route_administration WHERE name = 'PARENTERAL'), FALSE, 8.88, 50),--17
      ('Analgin', 'Solution for injections 50% 2ml N10',(SELECT id FROM dosage_form WHERE name = 'INJECTION'), (SELECT id FROM route_administration WHERE name = 'PARENTERAL'), FALSE, 4.60, 50),--18
      ('Analgin', 'Tablets 500mg N20',(SELECT id FROM dosage_form WHERE name = 'TABLET'), (SELECT id FROM route_administration WHERE name = 'ORALLY'), FALSE, 0.96, 50),--19
      ('Dexamethasone', 'Solution for injections 4mg 1ml N25',(SELECT id FROM dosage_form WHERE name = 'INJECTION'), (SELECT id FROM route_administration WHERE name = 'PARENTERAL'), FALSE, 17.74, 50),--20
      ('Dexamethasone', 'Solution for injections 4mg 1ml N10',(SELECT id FROM dosage_form WHERE name = 'INJECTION'), (SELECT id FROM route_administration WHERE name = 'PARENTERAL'), FALSE, 4.70, 50),--21
      ('Dexamethasone', 'Tablets 0.5mg N50',(SELECT id FROM dosage_form WHERE name = 'TABLET'), (SELECT id FROM route_administration WHERE name = 'ORALLY'), TRUE , 14.78, 50),--22
      ('Dexamethasone', 'Tablets 0.5mg N10',(SELECT id FROM dosage_form WHERE name = 'TABLET'), (SELECT id FROM route_administration WHERE name = 'ORALLY'), TRUE, 2.91, 50),--23
      ('Paracetamol', 'Tablets 200mg N20',(SELECT id FROM dosage_form WHERE name = 'TABLET'), (SELECT id FROM route_administration WHERE name = 'ORALLY'), FALSE, 1.05, 50),--24
      ('Paracetamol', 'Tablets 200mg N20',(SELECT id FROM dosage_form WHERE name = 'TABLET'), (SELECT id FROM route_administration WHERE name = 'ORALLY'), FALSE, 0.75, 50),--25
      ('Spasmalgon', 'Tablets N20',(SELECT id FROM dosage_form WHERE name = 'TABLET'), (SELECT id FROM route_administration WHERE name = 'ORALLY'), FALSE, 7.40, 50),--26
      ('Spasmalgon', 'Tablets N10',(SELECT id FROM dosage_form WHERE name = 'TABLET'), (SELECT id FROM route_administration WHERE name = 'ORALLY'), FALSE, 4.53, 50),--27
      ('Spasmalgon', 'Solution for injections 5ml N10',(SELECT id FROM dosage_form WHERE name = 'INJECTION'), (SELECT id FROM route_administration WHERE name = 'PARENTERAL'), FALSE, 12.25, 50),--28
      ('Spasmalgon', 'Solution for injections 2ml N10',(SELECT id FROM dosage_form WHERE name = 'INJECTION'), (SELECT id FROM route_administration WHERE name = 'PARENTERAL'), FALSE, 6.80, 50),--29
      ('Validol', 'Capsules 100mg N30',(SELECT id FROM dosage_form WHERE name = 'CAPSULE'), (SELECT id FROM route_administration WHERE name = 'ORALLY'), FALSE, 3.03, 50),--30
      ('Validol', 'Capsules 100mg N50',(SELECT id FROM dosage_form WHERE name = 'CAPSULE'), (SELECT id FROM route_administration WHERE name = 'ORALLY'), FALSE, 2.30, 50),--31
      ('Validol', 'Tablets 60mg N10',(SELECT id FROM dosage_form WHERE name = 'TABLET'), (SELECT id FROM route_administration WHERE name = 'ORALLY'), FALSE, 1.47, 50),--32
      ('Validol', 'Capsules 100mg N20',(SELECT id FROM dosage_form WHERE name = 'CAPSULE'), (SELECT id FROM route_administration WHERE name = 'ORALLY'), FALSE, 3.63, 50),--33
      ('Ibuprofen Caps', 'Capsules 200mg N20',(SELECT id FROM dosage_form WHERE name = 'CAPSULE'), (SELECT id FROM route_administration WHERE name = 'ORALLY'), FALSE, 4.04, 50),--34
      ('Theraflex Advance', 'Capsules N120',(SELECT id FROM dosage_form WHERE name = 'CAPSULE'), (SELECT id FROM route_administration WHERE name = 'ORALLY'), FALSE, 58.04, 50),--35
      ('Theraflex Advance', 'Capsules N60',(SELECT id FROM dosage_form WHERE name = 'CAPSULE'), (SELECT id FROM route_administration WHERE name = 'ORALLY'), FALSE, 39.73, 50),--36
      ('No-Spa', 'Tablets 40mg N24',(SELECT id FROM dosage_form WHERE name = 'TABLET'), (SELECT id FROM route_administration WHERE name = 'ORALLY'), FALSE, 7.45, 50),--37
      ('No-Spa', 'Tablets 40mg N100',(SELECT id FROM dosage_form WHERE name = 'TABLET'), (SELECT id FROM route_administration WHERE name = 'ORALLY'), FALSE, 15.65, 50),--38
      ('No-Spa', 'Tablets 40mg N10',(SELECT id FROM dosage_form WHERE name = 'TABLET'), (SELECT id FROM route_administration WHERE name = 'ORALLY'), FALSE, 3.75, 50),--39
      ('Drotaverine hydrochloride', 'Solution for injections 2% 2ml N5',(SELECT id FROM dosage_form WHERE name = 'INJECTION'), (SELECT id FROM route_administration WHERE name = 'PARENTERAL'), FALSE, 0.90, 50),--40
      ('Drotaverine hydrochloride', 'Tablets 40mg N20',(SELECT id FROM dosage_form WHERE name = 'TABLET'), (SELECT id FROM route_administration WHERE name = 'ORALLY'), FALSE, 0.88, 50),--41
      ('Amoxicillin', 'Capsules 500mg N20',(SELECT id FROM dosage_form WHERE name = 'CAPSULE'), (SELECT id FROM route_administration WHERE name = 'ORALLY'), FALSE, 7.97, 50),--42
      ('Amoxicillin', 'Capsules 250mg N30',(SELECT id FROM dosage_form WHERE name = 'CAPSULE'), (SELECT id FROM route_administration WHERE name = 'ORALLY'), FALSE, 3.21, 50),--43
      ('Baralgin M', 'Solution for injections 50% 5ml N5',(SELECT id FROM dosage_form WHERE name = 'INJECTION'), (SELECT id FROM route_administration WHERE name = 'PARENTERAL'), FALSE, 12.90, 50),--44
      ('Baralgin M', 'Tablets 500mg N10',(SELECT id FROM dosage_form WHERE name = 'TABLET'), (SELECT id FROM route_administration WHERE name = 'ORALLY'), FALSE, 6.88, 50),--45
      ('Parascofen', 'Tablets N20',(SELECT id FROM dosage_form WHERE name = 'TABLET'), (SELECT id FROM route_administration WHERE name = 'ORALLY'), FALSE, 1.70, 50),--46
      ('Baclofen', 'Tablets 10mg N50',(SELECT id FROM dosage_form WHERE name = 'TABLET'), (SELECT id FROM route_administration WHERE name = 'ORALLY'), FALSE, 8.08, 50),--47
      ('Baclofen', 'Tablets 25mg N50',(SELECT id FROM dosage_form WHERE name = 'TABLET'), (SELECT id FROM route_administration WHERE name = 'ORALLY'), FALSE, 13.09, 50),--48
      ('Dopamine', 'Solution for injections 4% 5ml N10',(SELECT id FROM dosage_form WHERE name = 'INJECTION'), (SELECT id FROM route_administration WHERE name = 'PARENTERAL'), TRUE, 17.16, 50),--49
      ('Dopamine', 'Solution for injections 0.5% 5ml N10',(SELECT id FROM dosage_form WHERE name = 'INJECTION'), (SELECT id FROM route_administration WHERE name = 'PARENTERAL'), TRUE, 5.63, 50);


   
INSERT INTO order_infos (order_id, drug_id, drug_quantity, drug_price)
VALUES (1, 1, 1, 48.62),
		(1, 10, 2, 8.94),
		(1, 25, 1, 0.75),
		(2, 11, 2, 3.35),
		(2, 8, 1, 39.2),
		(3, 1, 1, 48.62),
		(3, 15, 2, 3.1),
		(4, 40, 1, 0.9),
		(5, 15, 1, 3.1),
		(5, 10, 2, 8.94),
		(5, 2, 1, 20.9),
		(6, 19, 1, 0.96),
		(6, 14, 2, 46.3),
		(6, 45, 1, 6.88),
		(7, 11, 1, 3.35),
		(7, 10, 2, 8.94),
		(7, 25, 1, 0.75),
		(7, 1, 1, 48.62),
		(7, 10, 2, 8.94),
		(7, 18, 1, 4.6),
		(8, 1, 1, 48.62),
		(8, 10, 2, 8.94),
		(8, 36, 1, 39.73),
		(9, 1, 5, 39.2),
		(9, 35, 2, 58.04),
		(9, 25, 1, 0.75),
		(10, 14, 1, 46.3),
		(10, 20, 2, 17.74),
		(10, 25, 1, 0.75),
		(10, 49, 1, 17.16),
		(11, 2, 2, 3.35),
		(11, 10, 1, 8.94),
		(12, 15, 1, 3.1),
		(12, 30, 2, 3.03),
		(12, 25, 1, 3.92),
		(13, 1, 1, 45.2),
		(14, 10, 2, 46.23),
		(15, 5, 1, 3.1),
		(16, 1, 1, 48.62),
		(16, 10, 2, 8.94),
		(17, 25, 1, 0.75),
		(18, 1, 1, 48.62),
		(18, 10, 2, 8.94),
		(18, 25, 1, 4.6),
		(19, 1, 1, 0.96),
		(20, 10, 2, 8.94),
		(20, 25, 1, 17.74),
		(21, 21, 1, 4.7),
		(21, 10, 2, 8.94),
		(22, 30, 1, 3.03),
		(23, 11, 1, 3.35),
		(23, 10, 2, 8.94),
		(23, 19, 1, 0.96);
      
INSERT INTO recipe_infos (drug_id, recipe_id)
VALUES (1, 1),
		(10, 1),
		(15, 1),
		(13, 1),
		(9, 2),
		(8, 2),
		(4, 2),
        (2, 2),
        (31, 3),
		(50, 3),
		(44, 3),
        (33, 3),
        (27, 4),
		(18, 4),
		(41, 4),
        (23, 4),
        (8, 5),
		(11, 5),
        (1, 5),
        (10, 6),
        (2, 6),
        (29, 7),
		(48, 7),
        (5, 7),
        (8, 8),
		(26, 8),
        (20, 8),
        (18, 9),
		(14, 9),
        (22, 9),
        (8, 10),
		(7, 10),
        (10, 10),
        (40, 11),
		(3, 11),
        (26, 11),
        (39, 12),
		(15, 12),
        (26, 12),
        (7, 13),
		(36, 13),
        (42, 13),
        (7, 14),
		(6, 14),
        (20, 14),
        (17, 14),
		(15, 16),
        (26, 16),
        (37, 17),
		(40, 17),
        (20, 17);


