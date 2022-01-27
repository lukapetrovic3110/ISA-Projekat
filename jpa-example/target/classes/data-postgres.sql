INSERT INTO public.address(
	address_id, city, country, latitude, longitude, street, street_number)
	VALUES ('1', 'Novi Sad', 'Srbija', 48, 44, 'Danila Kisa', 30);

INSERT INTO public.address(
	address_id, city, country, latitude, longitude, street, street_number)
	VALUES ('2', 'Novi Sad', 'Srbija', 48, 44, 'Branka Bajića', 20);	
	
INSERT INTO public.address(
	address_id, city, country, latitude, longitude, street, street_number)
	VALUES ('3', 'Novi Sad', 'Srbija', 48, 44, 'Bulevar kralja Petra I', 25);	

INSERT INTO public.address(
	address_id, city, country, latitude, longitude, street, street_number)
	VALUES ('4', 'Novi Sad','Srbija', 48, 44, 'Alekse Santica', 46);
	
INSERT INTO public.address(address_id, city, country, latitude, longitude, street, street_number)
	VALUES ('5', 'Novi Sad','Srbija', 48, 44, 'Alekse Santica', 11);
	
INSERT INTO public.address(
	address_id, city, country, latitude, longitude, street, street_number)
	VALUES ('6', 'Novi Sad','Srbija', 48, 44, 'Kisacka', 3);

INSERT INTO public.address(
	address_id, city, country, latitude, longitude, street, street_number)
	VALUES ('7', 'Novi Sad','Srbija', 48, 44, 'Maksima Gorkog', 11);

INSERT INTO public.address(
	address_id, city, country, latitude, longitude, street, street_number)
	VALUES ('8', 'Novi Sad','Srbija', 48, 44, 'Sremska', 98);

INSERT INTO public.address(
	address_id, city, country, latitude, longitude, street, street_number)
	VALUES (9, 'Novi Sad','Srbija', 48, 44, 'Kosovska', 18);
	
INSERT INTO public.address(
	address_id, city, country, latitude, longitude, street, street_number)
	VALUES (10, 'Backi Jarak','Srbija', 48, 44, 'Veljka Vlahovica', 10);

INSERT INTO public.pharmacy(
	pharmacy_id, name, rating, description, adress_id)
	VALUES (1, 'BENU', 8.1, 'First Pharmacy', 7);

INSERT INTO public.pharmacy(
	pharmacy_id, name, rating, description, adress_id)
	VALUES (2, 'Jankovic', 9.6, 'Second Pharmacy', 8);
	
INSERT INTO public.medicine_specification(
	med_spec_id, dosage)
	VALUES (1, 4);
	INSERT INTO public.medicine_specification(
	med_spec_id, dosage)
	VALUES (2, 3);
	INSERT INTO public.medicine_specification(
	med_spec_id, dosage)
	VALUES (3, 3);
	
INSERT INTO public.medicine(
	medicine_id, loyalty_points, manufacturer, code, medicine_form, name, precautions, prescription_type, type, medicine_specification_med_spec_id)
	VALUES (1, 10, 'Hemofarm', 'TR9NF','PILL', 'brufen', 'nema', 'NO_PRESCRIPTION', 'analgetik', 1);
	
INSERT INTO public.medicine(
	medicine_id, loyalty_points, manufacturer, code, medicine_form, name, precautions, prescription_type, type, medicine_specification_med_spec_id)
	VALUES (2, 5, 'Hemofarm', 'E23TR', 'PILL', 'aspirin', 'nema', 'NO_PRESCRIPTION', 'analgetik', 2);
	
INSERT INTO public.medicine(
	medicine_id, loyalty_points, manufacturer, code, medicine_form, name, precautions, prescription_type, type, medicine_specification_med_spec_id)
	VALUES (3, 10, 'Galenika', 'G567H', 'PILL', 'kafetin', 'nema', 'NO_PRESCRIPTION', 'analgetik', 3);
	
INSERT INTO public.allergy(
	allergy_id)
	VALUES (1);
	
INSERT INTO public.allergy_medicine(
	allergy_allergy_id, medicine_medicine_id)
	VALUES (1, 1);

INSERT INTO public.user(
	dtype, user_id, email, first_name, last_name, password, phone_number, username, user_type, rating, penalty, address_id, pharmacy_pharmacy_id, allergy_allergy_id)
	VALUES ('Patient', '1', 'milica98@gmail.com', 'Milica', 'Lukic', 'm_98', '065/897-87-65', 'mimi', 'PATIENT', null, 0, 1, null, 1);
	
INSERT INTO public.loyalty_card(
	card_id, loyalty_category, points, patient_user_id)
	VALUES (1, 'GOLD', 40, 1);

INSERT INTO public.user(
	dtype, user_id, email, first_name, last_name, password, phone_number, username, user_type, rating, penalty, address_id, pharmacy_pharmacy_id, allergy_allergy_id)
	VALUES ('Supplier', '2', 'isateam39@gmail.com', 'Pera', 'Peric', 'pera', '065/789-41-23', 'null', 'SUPPLIER', null, 0, 2, null, null);

INSERT INTO public.user(
	dtype, user_id, email, first_name, last_name, password, phone_number, username, user_type, rating, penalty, address_id, pharmacy_pharmacy_id, allergy_allergy_id)
	VALUES ('Supplier', '3', 'isateam39@gmail.com', 'Mirko', 'Mirkovic', 'mm', '065/789-41-00', 'null', 'SUPPLIER', null, 0, 4, null, null);

INSERT INTO public.user(
	dtype, user_id, email, first_name, last_name, password, phone_number, username, user_type, rating, penalty, address_id, pharmacy_pharmacy_id, allergy_allergy_id)
	VALUES ('Supplier', '4', 'isateam39@gmail.com', 'Luka', 'Lukovic', 'lukal', '061/333-11-00', 'null', 'SUPPLIER', null, 0, 5, null, null);

INSERT INTO public.user(
	dtype, user_id, email, first_name, last_name, password, phone_number, username, user_type, rating, penalty, address_id, pharmacy_pharmacy_id, allergy_allergy_id)
	VALUES ('Pharmacist', '5', 'isateam39@gmail.com', 'Nikola', 'Nikolic', '123', '061/000-25-10', 'nikola123', 'PHARMACIST', 7.2, null, 3, 1, null);
	
INSERT INTO public.user(
	dtype, user_id, email, first_name, last_name, password, phone_number, username, user_type, rating, penalty, address_id, pharmacy_pharmacy_id, allergy_allergy_id)
	VALUES ('PharmacyAdministrator', 6, 'isateam39@gmail.com', 'Marko', 'Markovic', 'marko', '064/789-11-10', 'marko123', 'PH_ADMINISTRATOR', null, null, 6, 1, null);	

INSERT INTO public.user(
	dtype, user_id, email, first_name, last_name, password, phone_number, username, user_type, rating, penalty, address_id, pharmacy_pharmacy_id, allergy_allergy_id)
	VALUES ('Dermatologist', 11, 'isateam39@gmail.com', 'Katarina', 'Majkic', '123', '060/588-15-85', 'katarina123', 'DERMATOLOGIST', 9.6, null, 9, 2, null);
	
INSERT INTO public.order(
	order_id, offer_deadline, order_status, pharmacy_pharmacy_id, pharmacy_administrator_user_id)
	VALUES ('1', '2021/04/04', 'PROCESSED', '2', '6');

INSERT INTO public.order(
	order_id, offer_deadline, order_status, pharmacy_pharmacy_id, pharmacy_administrator_user_id)
	VALUES ('2', '2021/04/11', 'PROCESSED', '2', '6');
	
INSERT INTO public.order(
	order_id, offer_deadline, order_status, pharmacy_pharmacy_id, pharmacy_administrator_user_id)
	VALUES ('3', '2021/04/21', 'PROCESSED', '2', '6');

INSERT INTO public.offer(
	offer_id, delivery_deadline, price, status, order_order_id, supplier_user_id)
	VALUES ('1', '2021/05/05', '22000', 'ACCEPTED', '1', '3');
	
INSERT INTO public.offer(
	offer_id, delivery_deadline, price, status, order_order_id, supplier_user_id)
	VALUES ('2', '2021/06/06', '8000', 'ACCEPTED', '2', '3');

INSERT INTO public.offer(
	offer_id, delivery_deadline, price, status, order_order_id, supplier_user_id)
	VALUES ('3', '2021/06/05', '12000', 'DECLINED', '3', '3');	
	
INSERT INTO public.offer(
	offer_id, delivery_deadline, price, status, order_order_id, supplier_user_id)
	VALUES ('4', '2021/03/05', '15000', 'WAITING', '1', '3');
	
INSERT INTO public.offer(
	offer_id, delivery_deadline, price, status, order_order_id, supplier_user_id)
	VALUES ('5', '2021/02/03', '100000', 'DECLINED', '2', '3');

INSERT INTO public.offer(
	offer_id, delivery_deadline, price, status, order_order_id, supplier_user_id)
	VALUES ('6', '2021/02/15', '2000', 'WAITING', '3', '3');
	
INSERT INTO public.offer(
	offer_id, delivery_deadline, price, status, order_order_id, supplier_user_id)
	VALUES ('7', '2021/02/01', '54000', 'DECLINED', '1', '2');