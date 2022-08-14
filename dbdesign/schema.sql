/*
CREATE DATABASE drugstore;
*/

DROP TABLE IF EXISTS orderstatuses CASCADE;
DROP TABLE IF EXISTS dosage_form CASCADE;
DROP TABLE IF EXISTS route_administration CASCADE;
DROP TABLE IF EXISTS clients CASCADE;
DROP TABLE IF EXISTS doctors CASCADE;
DROP TABLE IF EXISTS recipes CASCADE;
DROP TABLE IF EXISTS orders CASCADE;
DROP TABLE IF EXISTS drugs CASCADE;
DROP TABLE IF EXISTS pharmacists CASCADE;
DROP TABLE IF EXISTS order_infos CASCADE;
DROP TABLE IF EXISTS recipe_infos CASCADE;


--enam
CREATE TABLE orderstatuses (
	id BIGSERIAL PRIMARY KEY,
	"name" VARCHAR(25) UNIQUE NOT NULL 
);

--enam
CREATE TABLE dosage_form (
	id BIGSERIAL PRIMARY KEY,
	"name" VARCHAR(25) UNIQUE NOT NULL 
);

--enam
CREATE TABLE route_administration (
	id BIGSERIAL PRIMARY KEY,
	"name" VARCHAR(25) UNIQUE NOT NULL 
);



CREATE TABLE clients (
	id BIGSERIAL NOT NULL,
	first_name VARCHAR(25) NOT NULL,
	last_name VARCHAR(25) NOT NULL,
	email TEXT NOT NULL,
	"password" TEXT NOT NULL,
	deleted BOOLEAN NOT NULL DEFAULT FALSE,
	CONSTRAINT clients_pk PRIMARY KEY (id)
);

CREATE TABLE doctors (
	id BIGSERIAL NOT NULL,
	first_name VARCHAR(25) NOT NULL,
	last_name VARCHAR(25) NOT NULL,
	email TEXT NOT NULL,
	"password" TEXT NOT NULL,
	deleted BOOLEAN NOT NULL DEFAULT FALSE,
	CONSTRAINT doctors_pk PRIMARY KEY (id)
);

CREATE TABLE recipes (
	id BIGSERIAL NOT NULL,
	client_id BIGINT NOT NULL,
	doctor_id BIGINT NOT NULL,
	start_date TIMESTAMP NOT NULL,
	end_date TIMESTAMP NOT NULL,
	deleted BOOLEAN NOT NULL DEFAULT FALSE,
	CONSTRAINT recipes_pk PRIMARY KEY (id)
);

CREATE TABLE orders (
	id BIGSERIAL NOT NULL,
	pharmacist_id BIGINT,
	client_id BIGINT NOT NULL,
	total_coast DECIMAL NOT NULL,
	orderstatus_id BIGINT NOT NULL REFERENCES orderstatuses,
	deleted BOOLEAN NOT NULL DEFAULT FALSE,
	CONSTRAINT orders_pk PRIMARY KEY (id)
);



CREATE TABLE drugs (
	id BIGSERIAL NOT NULL,
	"name" VARCHAR(25) NOT NULL,
	release_form VARCHAR(50) NOT NULL,
	dosage_form_id BIGINT NOT NULL REFERENCES dosage_form,
    route_administration_id BIGINT NOT NULL REFERENCES route_administration,
	is_recipe BOOLEAN NOT NULL DEFAULT 'false',
	price DECIMAL NOT NULL,
	quantity_in_stock BIGINT NOT NULL,
	deleted BOOLEAN NOT NULL DEFAULT FALSE,
	CONSTRAINT drugs_pk PRIMARY KEY (id)
);



CREATE TABLE pharmacists (
	id BIGSERIAL NOT NULL,
	first_name VARCHAR(25) NOT NULL,
	last_name VARCHAR(25) NOT NULL,
	email TEXT NOT NULL,
	password TEXT NOT NULL,
	deleted BOOLEAN NOT NULL DEFAULT FALSE,
	CONSTRAINT pharmacists_pk PRIMARY KEY (id)
);



CREATE TABLE order_infos (
	id BIGSERIAL NOT NULL,
	drug_id BIGINT NOT NULL,
	order_id BIGINT NOT NULL,
	drug_quantity INTEGER NOT NULL,
	drug_price DECIMAL NOT NULL,
	deleted BOOLEAN NOT NULL DEFAULT FALSE,
	CONSTRAINT order_infos_pk PRIMARY KEY (id)
);



CREATE TABLE recipe_infos (
	id BIGSERIAL NOT NULL,
	drug_id BIGINT NOT NULL,
	recipe_id BIGINT NOT NULL,
	deleted BOOLEAN NOT NULL DEFAULT FALSE,
	CONSTRAINT recipe_infos_pk PRIMARY KEY (id)
);





ALTER TABLE recipes ADD CONSTRAINT recipes_fk0 FOREIGN KEY (doctor_id) REFERENCES doctors(id);
ALTER TABLE recipes ADD CONSTRAINT recipes_fk1 FOREIGN KEY (client_id) REFERENCES clients(id);

ALTER TABLE orders ADD CONSTRAINT orders_fk0 FOREIGN KEY (pharmacist_id) REFERENCES pharmacists(id);
ALTER TABLE orders ADD CONSTRAINT orders_fk1 FOREIGN KEY (client_id) REFERENCES clients(id);


ALTER TABLE order_infos ADD CONSTRAINT order_infos_fk0 FOREIGN KEY (drug_id) REFERENCES drugs(id);
ALTER TABLE order_infos ADD CONSTRAINT order_infos_fk1 FOREIGN KEY (order_id) REFERENCES orders(id);

ALTER TABLE recipe_infos ADD CONSTRAINT recipe_infos_fk0 FOREIGN KEY (drug_id) REFERENCES drugs(id);
ALTER TABLE recipe_infos ADD CONSTRAINT recipe_infos_fk1 FOREIGN KEY (recipe_id) REFERENCES recipes(id);








