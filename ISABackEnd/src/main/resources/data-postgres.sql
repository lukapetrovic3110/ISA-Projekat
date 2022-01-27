INSERT INTO public.address(
	address_id, city, country, latitude, longitude, street, street_number)
	VALUES (default, 'Novi Sad', 'Srbija', 48, 44, 'Danila Kisa', 30);

INSERT INTO public.address(
	address_id, city, country, latitude, longitude, street, street_number)
	VALUES (default, 'Novi Sad', 'Srbija', 48, 44, 'Branka Bajica', 20);	
	
INSERT INTO public.address(
	address_id, city, country, latitude, longitude, street, street_number)
	VALUES (default, 'Novi Sad', 'Srbija', 48, 44, 'Bulevar kralja Petra I', 25);	

INSERT INTO public.address(
	address_id, city, country, latitude, longitude, street, street_number)
	VALUES (default, 'Novi Sad','Srbija', 48, 44, 'Alekse Santica', 46);
	
INSERT INTO public.address(address_id, city, country, latitude, longitude, street, street_number)
	VALUES (default, 'Novi Sad','Srbija', 48, 44, 'Alekse Santica', 11);
	
INSERT INTO public.address(
	address_id, city, country, latitude, longitude, street, street_number)
	VALUES (default, 'Novi Sad','Srbija', 48, 44, 'Kisacka', 3);

-- Benu APOTEKA
INSERT INTO public.address(
	address_id, city, country, latitude, longitude, street, street_number)
	VALUES (default, 'Temerin','Srbija', 45.408292, 19.88796, 'Novosadska', 383);

-- Jankovic APOTEKA
INSERT INTO public.address(
	address_id, city, country, latitude, longitude, street, street_number)
	VALUES (default, 'Sremski Karlovci','Srbija', 45.204627, 19.931912, 'Karadjordjeva', 18);

INSERT INTO public.address(
	address_id, city, country, latitude, longitude, street, street_number)
	VALUES (default, 'Novi Sad','Srbija', 48, 44, 'Kosovska', 18);
	
INSERT INTO public.address(
	address_id, city, country, latitude, longitude, street, street_number)
	VALUES (default, 'Backi Jarak','Srbija', 48, 44, 'Veljka Vlahovica', 7);

-- Higija APOTEKA
INSERT INTO public.address(
	address_id, city, country, latitude, longitude, street, street_number)
	VALUES (default, 'Temerin','Srbija', 45.407919, 19.886008, ' Petefi Sandora', 32);

INSERT INTO public.address(
	address_id, city, country, latitude, longitude, street, street_number)
	VALUES (default, 'Curug','Srbija', 48, 44, 'Cara Dusana', 9);
	
INSERT INTO public.address(
	address_id, city, country, latitude, longitude, street, street_number)
	VALUES (default, 'Petrovaradin','Srbija', 48, 44, 'Preradoviceva', 62);

INSERT INTO public.address(
	address_id, city, country, latitude, longitude, street, street_number)
	VALUES (default, 'Backi Jarak','Srbija', 48, 44, 'Nikole Tesle', 10);
	
INSERT INTO public.address(
	address_id, city, country, latitude, longitude, street, street_number)
	VALUES (default, 'Kikinda','Srbija', 48, 44, 'Generala Drapsina', 31);

INSERT INTO public.address(
	address_id, city, country, latitude, longitude, street, street_number)
	VALUES (default, 'Begec','Srbija', 48, 44, 'Kralja Petra I', 31);

INSERT INTO public.pharmacy(
	pharmacy_id, name, rating, description,consultation_price, address_id)
	VALUES (default, 'BENU', 8.4, 'First Pharmacy',700, 7);

INSERT INTO public.pharmacy(
	pharmacy_id, name, rating, description,consultation_price, address_id)
	VALUES (default, 'Jankovic', 9.6, 'Second Pharmacy',1000, 8);

INSERT INTO public.pharmacy(
	pharmacy_id, name, rating, description,consultation_price, address_id)
	VALUES (default, 'Higija', 10.0, 'Third Pharmacy',600, 11);
	
INSERT INTO public.medicine_specification(
	med_spec_id, dosage)
	VALUES (default, 4);
INSERT INTO public.medicine_specification(
	med_spec_id, dosage)
	VALUES (default, 3);
INSERT INTO public.medicine_specification(
	med_spec_id, dosage)
	VALUES (default, 3);
INSERT INTO public.medicine_specification(
	med_spec_id, dosage)
	VALUES (default, 1);
INSERT INTO public.medicine_specification(
	med_spec_id, dosage)
	VALUES (default, 3);
INSERT INTO public.medicine_specification(
	med_spec_id, dosage)
	VALUES (default, 2);
INSERT INTO public.medicine_specification(
	med_spec_id, dosage)
	VALUES (default, 1);
INSERT INTO public.medicine_specification(
	med_spec_id, dosage)
	VALUES (default, 2);
INSERT INTO public.medicine_specification(
	med_spec_id, dosage)
	VALUES (default, 2);
INSERT INTO public.medicine_specification(
	med_spec_id, dosage)
	VALUES (default, 3);
INSERT INTO public.medicine_specification(
	med_spec_id, dosage)
	VALUES (default, 3);
INSERT INTO public.medicine_specification(
	med_spec_id, dosage)
	VALUES (default, 1);	
    
INSERT INTO public.medicine(
	medicine_id, loyalty_points, manufacturer, code, medicine_form, name, precautions, prescription_type, type, medicine_specification_med_spec_id, rating)
	VALUES (default, 10, 'Hemofarm', 'TR9NF','PILL', 'brufen', 'nema', 'NO_PRESCRIPTION', 'analgetik', 1, 10);
	
INSERT INTO public.medicine(
	medicine_id, loyalty_points, manufacturer, code, medicine_form, name, precautions, prescription_type, type, medicine_specification_med_spec_id, rating)
	VALUES (default, 5, 'Hemofarm', 'E23TR', 'PILL', 'aspirin', 'nema', 'NO_PRESCRIPTION', 'analgetik', 2, 7);
	
INSERT INTO public.medicine(
	medicine_id, loyalty_points, manufacturer, code, medicine_form, name, precautions, prescription_type, type, medicine_specification_med_spec_id, rating)
	VALUES (default, 10, 'Galenika', 'G567H', 'PILL', 'kafetin', 'nema', 'NO_PRESCRIPTION', 'analgetik', 3, 9);
	
INSERT INTO public.medicine(
	medicine_id, loyalty_points, manufacturer, code, medicine_form, name, precautions, prescription_type, type, medicine_specification_med_spec_id, rating)
	VALUES (default, 10, 'Hemofarm', 'M01AB05', 'PILL', 'diklofenak', 'nema', 'NO_PRESCRIPTION', 'analgetik', 4, 8);

INSERT INTO public.medicine(
	medicine_id, loyalty_points, manufacturer, code, medicine_form, name, precautions, prescription_type, type, medicine_specification_med_spec_id, rating)
	VALUES (default, 6, 'BENU', 'R05CA12', 'SOLUTION', 'prospan', 'nema', 'NO_PRESCRIPTION', 'biljni_lek', 5, 8);

INSERT INTO public.medicine(
	medicine_id, loyalty_points, manufacturer, code, medicine_form, name, precautions, prescription_type, type, medicine_specification_med_spec_id, rating)
	VALUES (default, 6, 'BENU', 'R65B12', 'PILL', 'febricit', 'nema', 'NO_PRESCRIPTION', 'antibiotik', 6, 7);

INSERT INTO public.medicine(
	medicine_id, loyalty_points, manufacturer, code, medicine_form, name, precautions, prescription_type, type, medicine_specification_med_spec_id, rating)
	VALUES (default, 9, 'BENU', 'B94656', 'PILL', 'penicilin', 'nema', 'NO_PRESCRIPTION', 'antibiotik', 7, 10);

INSERT INTO public.medicine(
	medicine_id, loyalty_points, manufacturer, code, medicine_form, name, precautions, prescription_type, type, medicine_specification_med_spec_id, rating)
	VALUES (default, 4, 'Hemofarm', 'B31P33', 'PILL', 'ampicilin', 'nema', 'NO_PRESCRIPTION', 'antibiotik', 8, 8);

INSERT INTO public.medicine(
	medicine_id, loyalty_points, manufacturer, code, medicine_form, name, precautions, prescription_type, type, medicine_specification_med_spec_id, rating)
	VALUES (default, 6, 'BENU', 'P12M01', 'POWDER', 'azaran', 'nema', 'NO_PRESCRIPTION', 'antibiotik', 9, 9);

INSERT INTO public.medicine(
	medicine_id, loyalty_points, manufacturer, code, medicine_form, name, precautions, prescription_type, type, medicine_specification_med_spec_id, rating)
	VALUES (default, 6, 'Galenika', 'V08O41', 'POWDER', 'lendacin', 'nema', 'NO_PRESCRIPTION', 'antibiotik', 10, 8);

INSERT INTO public.medicine(
	medicine_id, loyalty_points, manufacturer, code, medicine_form, name, precautions, prescription_type, type, medicine_specification_med_spec_id, rating)
	VALUES (default, 6, 'Hemofarm', 'C142T4', 'SOLUTION', 'ceftriakson', 'nema', 'NO_PRESCRIPTION', 'antibiotik', 11, 9);

INSERT INTO public.medicine(
	medicine_id, loyalty_points, manufacturer, code, medicine_form, name, precautions, prescription_type, type, medicine_specification_med_spec_id, rating)
	VALUES (default, 6, 'Galenika', 'C16T05', 'SOLUTION', 'longacef', 'nema', 'NO_PRESCRIPTION', 'antibiotik', 12, 10);

INSERT INTO public.allergy(
	allergy_id)
	VALUES (default);
	
INSERT INTO public.allergy_medicine(
	allergy_allergy_id, medicine_medicine_id)
	VALUES (1, 1);
	
-- password Milica123! 
INSERT INTO public.user(
	dtype, user_id, email, enabled, first_login, first_name, last_name, last_reset_pass_date, password, salt, phone_number, user_type, rating, penalty, address_id, pharmacy_pharmacy_id, allergy_allergy_id)
	VALUES ('Patient', default, 'milica98@gmail.com', true, false, 'Milica', 'Lukic', '2020-11-11', '$2a$12$npxcTCqr.HeAJL6YaFDfruts5K9VCYusMwk3XFL0IwE0qGSiMe51G', 'ML896k0z0oWyHZC5i5v761==', '065/897-87-65', 'PATIENT', null, 0, 1, null, 1);
	
INSERT INTO public.loyalty_card(
	card_id, loyalty_category, points, patient_user_id) 
	VALUES (default, 'GOLD', 40, 1);
	
-- password Ivana1234! - ivanagavric@gmail.com - TEST - lukait31@gmail.com
-- My trial email address is lukait31@gmail.com, put a trial email at the supplier so you can receive messages from the pharmacy for 3.26 task
-- At Pera, Mirko and Luna put your test email
INSERT INTO public.user(
	dtype, user_id, email, enabled, first_login, first_name, last_name, last_reset_pass_date, password, salt, phone_number, user_type, rating, penalty, address_id, pharmacy_pharmacy_id, allergy_allergy_id)
	VALUES ('Supplier', default, 'ivanagavric@gmail.com', true, false, 'Pera', 'Peric', '2020-11-11', '$2a$12$oI9p3iPsjtJ3y.0DEGo2T.zWkB8RKdtHRrzMyvSDx2JN8q3jYekGS', 'AN777Ivx9owXayu67jL012==', '065/789-41-23', 'SUPPLIER', null, 0, 2, null, null);

-- password Dobavljac17! - isateam3921@gmail.com - TEST - lukait31@gmail.com
INSERT INTO public.user(
	dtype, user_id, email, enabled, first_login, first_name, last_name, last_reset_pass_date, password, salt, phone_number, user_type, rating, penalty, address_id, pharmacy_pharmacy_id, allergy_allergy_id)
	VALUES ('Supplier', default, 'isateam3921@gmail.com', true, false, 'Mirko', 'Mirkovic', '2020-11-11', '$2a$12$pdxtJczjkp.hLRLosRub/eYxQ.VZE8D/BhhmHbcqw7BYePkUVqAO.', 'BV1010SsRNlpMB1021Og44==', '065/789-41-00', 'SUPPLIER', null, 0, 4, null, null);

-- password Luna123! - luna21@gmail.com - TEST  - lukait31@gmail.com  
INSERT INTO public.user(
	dtype, user_id, email, enabled, first_login, first_name, last_name, last_reset_pass_date, password, salt, phone_number, user_type, rating, penalty, address_id, pharmacy_pharmacy_id, allergy_allergy_id)
	VALUES ('Supplier', default, 'luna21@gmail.com', true, false, 'Luna', 'Lukovic', '2020-11-11', '$2a$12$6//4tJzqynBMvPO0CrIhwOiRucoj3wGMm49k9pX0eVgrO14Pls1di', 'Lxw2ena1sRNlpMB10oO456==', '065/789-41-00', 'SUPPLIER', null, 0, 5, null, null);

-- password Llukic123@
INSERT INTO public.user(
	dtype, user_id, email, enabled, first_login, first_name, last_name, last_reset_pass_date, password, salt, phone_number, user_type, rating, penalty, address_id, pharmacy_pharmacy_id, allergy_allergy_id)
	VALUES ('Supplier', default, 'luka10@gmail.com', true, false, 'Luka', 'Lukovic', '2020-11-11', '$2a$12$OiJS32BGySotXVIb99d3cua6bJ2GdIVEnyMjzgM/RR3KwXObqdYG.', 'L10Labvsfsw2312fsf22a8==', '061/333-11-00', 'SUPPLIER', null, 0, 5, null, null);

-- password Marko123!	
INSERT INTO public.user(
	dtype, user_id, email, enabled, first_login, first_name, last_name, last_reset_pass_date, password, salt, phone_number,  user_type, rating, penalty, address_id, pharmacy_pharmacy_id, allergy_allergy_id)
	VALUES ('PharmacyAdministrator', default, 'markovicm@gmail.com', true, false, 'Marko', 'Markovic', '2020-11-11', '$2a$12$bj4hnB7C50TPVVTZMjY0pOhL.3CImLOMztQXKxmqKaQDHvW9TmwRS', 'MaRa1324ZYw2012XxV22aR==', '065/789-41-23', 'PH_ADMINISTRATOR', null, 0, 2, 3, null);

-- password Kaca1234!
INSERT INTO public.user(
	dtype, user_id, email, enabled, first_login, first_name, last_name, last_reset_pass_date, password, salt, phone_number, user_type, rating, penalty, address_id, pharmacy_pharmacy_id, allergy_allergy_id)
	VALUES ('Dermatologist', default, 'kaca39@gmail.com', true, false, 'Katarina', 'Majkic', '2020-11-11', '$2a$12$abB8v5qvozbo5eEegktY5e.lh0Xw3VeoA8XYPsc8rmbvWrEQ9gTte', 'KaA4cA100w2OpVr23HaxVa==', '060/588-15-85',  'DERMATOLOGIST', 7.2, null, 9, null, null);	

-- password Ivana123!
INSERT INTO public.user(
	dtype, user_id, email, enabled, first_login, first_name, last_name, last_reset_pass_date, password, salt, phone_number, user_type, rating, penalty, address_id, pharmacy_pharmacy_id, allergy_allergy_id)
	VALUES ('SystemAdministrator', default, 'ivanagavric05@gmail.com', true, false, 'Ivana', 'Gavric', '2020-11-11', '$2a$12$8PpQEGJYFqTMAgdvoPi1Uu0ILjKVNMGaJeQ1G5CaS8sREwJTvkKJi', '650dg1MXBhE5yby/44jsRw==', '065/789-41-23', 'SYS_ADMINISTRATOR', null, 0, 2, null, null);

-- password Nikola1234!
INSERT INTO public.user(
	dtype, user_id, email, enabled, first_login, first_name, last_name, last_reset_pass_date, password, salt, phone_number, user_type, rating, penalty, address_id, pharmacy_pharmacy_id, allergy_allergy_id)
	VALUES ('Pharmacist', default, 'nikola7@gmail.com', true, false, 'Nikola', 'Spasic', '2020-11-11', '$2a$12$0ZQ9aHaXtbFp0c03gRFHNOTMd6BQiNAcjlVK.KSAscOlotnSpXQM.', '62l3V017w2ISV4pJ9uHTIx==', '061/333-66-10',  'PHARMACIST', 9.2, null, 6, 2, null);

-- password Luka3456!
INSERT INTO public.user(
	dtype, user_id, email, enabled, first_login, first_name, last_name, last_reset_pass_date, password, salt, phone_number, user_type, rating, penalty, address_id, pharmacy_pharmacy_id, allergy_allergy_id)
	VALUES ('PharmacyAdministrator', default, 'lukait@gmail.com', true, false, 'Luka', 'Petrovic', '2020-11-19', '$2a$12$iFtunNf69vM1mAfDfp.zQ.3r9OVZNKvtCf8oUzUiyOyW6ee6Ipa8O', 'BolZV6e7PoImVOpZIxuHi7==', '064/225-34-49',  'PH_ADMINISTRATOR', null, 0, 10, 2, null);

-- password Maja366!
INSERT INTO public.user(
	dtype, user_id, email, enabled, first_login, first_name, last_name, last_reset_pass_date, password, salt, phone_number, user_type, rating, penalty, address_id, pharmacy_pharmacy_id, allergy_allergy_id) 
	VALUES ('Pharmacist', default, 'maja36@gmail.com', true, false, 'Maja', 'Lakic', '2021-10-11', '$2a$12$GSlMPBf8Ow9ZvbD0ci4tYuranBxuldFavPiMpfb6LH2pozD8aX1Ly','MoJZ7YXSup34xO4ZIxuHi7==','061/585-10-10', 'PHARMACIST', 8.1, null, 12, 3, null);	

-- password Sara123!
INSERT INTO public.user(
	dtype, user_id, email, enabled, first_login, first_name, last_name, last_reset_pass_date, password, salt, phone_number, user_type, rating, penalty, address_id, pharmacy_pharmacy_id, allergy_allergy_id)
	VALUES ('Dermatologist', default, 'sara20@gmail.com', true, false, 'Sara', 'Jovanovic', '2020-11-11', '$2a$12$ZJyJxPRFZGgw6t5nKDDsqOboD/H2pfLmoKADcu7LbVozp6WZ3UCMS', 'aB5ScB102w2XpVr3SIzpOl==', '061/444-01-99', 'DERMATOLOGIST', 8.9, null, 9, null, null);		

-- password Branko12!
INSERT INTO public.user(
	dtype, user_id, email, enabled, first_login, first_name, last_name, last_reset_pass_date, password, salt, phone_number,  user_type, rating, penalty, address_id, pharmacy_pharmacy_id, allergy_allergy_id)
	VALUES ('Dermatologist', default, 'branko12@gmail.com', true, false, 'Branko', 'Sudic', '2020-11-11', '$2a$12$QSz8Nyp6jzwWbR0Rxlp/bOyp/PAcCw200HIYp7nOz0NC1R823M42e', 'rB5acN122w2XOVr3sIDpx1==', '064/333-66-39', 'DERMATOLOGIST', 9.8, null, 13, null, null);		

-- password Milan123!
INSERT INTO public.user(
	dtype, user_id, email, enabled, first_login, first_name, last_name, last_reset_pass_date, password, salt, phone_number, user_type, rating, penalty, address_id, pharmacy_pharmacy_id, allergy_allergy_id)
	VALUES ('Dermatologist', default, 'milan81@gmail.com', true, false, 'Milan', 'Lakic', '2021-03-11', '$2a$12$syxpqo4ncnY0GpT99kfM3.TSo/kZL/1qbPO1Nl0SkT1sMHmmet.m2', '100cNAmjo2IODkesxlDpx1==', '061/000-16-19', 'DERMATOLOGIST', 9.8, null, 12, null, null);		

-- password Luka22! 
INSERT INTO public.user(
	dtype, user_id, email, enabled, first_login, first_name, last_name, last_reset_pass_date, password, salt, phone_number,  user_type, rating, penalty, address_id, pharmacy_pharmacy_id, allergy_allergy_id)
	VALUES ('Patient', default, 'lukait31@gmail.com', true, false, 'Luka', 'Tomic', '2021-05-11', '$2a$12$XDR3iRfQvVtFMBGBcVv9u.kSDM6I3da97eXy7Jeedsl9dGbZWp0hu', 't89kMz0s2c51dssd3oW761==', '064/292-21-10', 'PATIENT', null, 0, 14, null, null);	

INSERT INTO public.loyalty_card(
	card_id, loyalty_category, points, patient_user_id) 
	VALUES (default, 'GOLD', 40, 15);

-- password Luka678!
INSERT INTO public.user(
	dtype, user_id, email, enabled, first_login, first_name, last_name, last_reset_pass_date, password, salt, phone_number, user_type, rating, penalty, address_id, pharmacy_pharmacy_id, allergy_allergy_id)
	VALUES ('Pharmacist', default, 'lukait31@gmail.com', true, false, 'Luka', 'Lolic', '2020-11-11', '$2a$12$PMWEMnAEXIKiojFMHl2Lj.e/hh1a9Mcp4JkG6Up0vEBFSKBTk9KiK', '10l3i01XCw2ISV4Ju1D31x==', '064/336-23-77' , 'PHARMACIST', 8.6, null, 15, 2, null);

-- password Luka123!
INSERT INTO public.user(
	dtype, user_id, email, enabled, first_login, first_name, last_name, last_reset_pass_date, password, salt, phone_number, user_type, rating, penalty, address_id, pharmacy_pharmacy_id, allergy_allergy_id)
	VALUES ('Pharmacist', default, 'lukait31@gmail.com', true, false, 'Luka', 'Pavkov', '2020-11-11', '$2a$12$pxBaiz4IN6MjRcVAblBJA.8cfpv0pU7G6TGqNnqLIjyvkotAu3.JS', '0QWE1XACxxWsSV4Ju1D31x==', '061/300-23-77',  'PHARMACIST', 7.6, null, 16, 2, null);

-- password Luka333! - Test dermatolog with email lukait31@gmail.com - for vacation 
INSERT INTO public.user(
	dtype, user_id, email, enabled, first_login, first_name, last_name, last_reset_pass_date, password, salt, phone_number, user_type, rating, penalty, address_id, pharmacy_pharmacy_id, allergy_allergy_id)
	VALUES ('Dermatologist', default, 'lukait31@gmail.com', true, false, 'Luka', 'Sudic', '2020-11-11', '$2a$12$wVIYc9fi1qcZujO18Jg1J.p9N3XtmisC/1g27FZjrdSv9TX.It8e.', 'CCSac2wewe2XOEEEsIDp22==', '063/111-22-39', 'DERMATOLOGIST', 6.8, null, 13, null, null);		

-- password Sara3456!
INSERT INTO public.user(
	dtype, user_id, email, enabled, first_login, first_name, last_name, last_reset_pass_date, password, salt, phone_number, user_type, rating, penalty, address_id, pharmacy_pharmacy_id, allergy_allergy_id)
	VALUES ('PharmacyAdministrator', default, 'admin7@gmail.com', true, false, 'Sara', 'Petrovic', '2020-11-11', '$2a$12$MIVjrgnhb9.m.ctkLly5oOA995IsCazjk6JWYUVUUVoMOR4YyRnOm', 'S342a3rar20PoIm12pZIi7==', '061/333-23-71', 'PH_ADMINISTRATOR', null, 0, 10, 2, null);
	
INSERT INTO public.authority(name) VALUES ('ROLE_PATIENT');	
INSERT INTO public.authority(name) VALUES ('ROLE_SYS_ADMIN');	
INSERT INTO public.authority(name) VALUES ('ROLE_SUPPLIER');	
INSERT INTO public.authority(name) VALUES ('ROLE_PH_ADMIN');
INSERT INTO public.authority(name) VALUES ('ROLE_PHARMACIST');	
INSERT INTO public.authority(name) VALUES ('ROLE_DERMATOLOGIST');

INSERT INTO public.user_authorities(user_user_id, authorities_id) VALUES (1, 1);	
INSERT INTO public.user_authorities(user_user_id, authorities_id) VALUES (2, 3);
INSERT INTO public.user_authorities(user_user_id, authorities_id) VALUES (3, 3);
INSERT INTO public.user_authorities(user_user_id, authorities_id) VALUES (4, 3);
INSERT INTO public.user_authorities(user_user_id, authorities_id) VALUES (5, 3);
INSERT INTO public.user_authorities(user_user_id, authorities_id) VALUES (6, 4);
INSERT INTO public.user_authorities(user_user_id, authorities_id) VALUES (7, 6);	
INSERT INTO public.user_authorities(user_user_id, authorities_id) VALUES (8, 2);
INSERT INTO public.user_authorities(user_user_id, authorities_id) VALUES (9, 5);
INSERT INTO public.user_authorities(user_user_id, authorities_id) VALUES (10, 4);
INSERT INTO public.user_authorities(user_user_id, authorities_id) VALUES (11, 5);
INSERT INTO public.user_authorities(user_user_id, authorities_id) VALUES (12, 6);
INSERT INTO public.user_authorities(user_user_id, authorities_id) VALUES (13, 6);
INSERT INTO public.user_authorities(user_user_id, authorities_id) VALUES (14, 6);
INSERT INTO public.user_authorities(user_user_id, authorities_id) VALUES (15, 1);
INSERT INTO public.user_authorities(user_user_id, authorities_id) VALUES (16, 5);
INSERT INTO public.user_authorities(user_user_id, authorities_id) VALUES (17, 5);
INSERT INTO public.user_authorities(user_user_id, authorities_id) VALUES (18, 6);
INSERT INTO public.user_authorities(user_user_id, authorities_id) VALUES (19, 4);

-- ORDER - OFFER TEST DATA

INSERT INTO public.medicine_item(
	medicine_item_id, quantity, medicine_medicine_id, version)
	VALUES (101, 10, 1, 0);
INSERT INTO public.medicine_item(
	medicine_item_id, quantity, medicine_medicine_id,  version)
	VALUES (102, 5, 4, 0);
INSERT INTO public.medicine_item(
	medicine_item_id, quantity, medicine_medicine_id,  version)
	VALUES (103, 5, 10, 0);

INSERT INTO public.order(order_id, offer_deadline, order_status, pharmacy_pharmacy_id, pharmacy_administrator_user_id)
	VALUES (default,'2021/09/02', 'WAITING_OFFER', 2, 10);

INSERT INTO public.order_medicine_item(order_order_id, medicine_item_medicine_item_id)
	VALUES (1, 101);

INSERT INTO public.order_medicine_item(order_order_id, medicine_item_medicine_item_id)
	VALUES (1, 102);

INSERT INTO public.order_medicine_item(order_order_id, medicine_item_medicine_item_id)
	VALUES (1, 103);

INSERT INTO public.offer(
	offer_id, delivery_deadline, price, status, order_order_id, supplier_user_id)
	VALUES (default, '2021/09/12', 5000, 'WAITING', 1, 2);
	

INSERT INTO public.offer(
	offer_id, delivery_deadline, price, status, order_order_id, supplier_user_id)
	VALUES (default, '2021/09/16', 4300, 'WAITING', 1, 4);
	

INSERT INTO public.offer(
	offer_id, delivery_deadline, price, status, order_order_id, supplier_user_id)
	VALUES (default, '2021/09/14', 4800, 'WAITING', 1, 3);
	
INSERT INTO public.user_medicine_item(
	supplier_user_id, medicine_item_medicine_item_id) 
	values (2, 101);

INSERT INTO public.user_medicine_item(
	supplier_user_id, medicine_item_medicine_item_id) 
	values (2, 102);
	
INSERT INTO public.user_medicine_item(
	supplier_user_id, medicine_item_medicine_item_id) 
	values (2, 103);
	
INSERT INTO public.medicine_item(
	medicine_item_id, quantity, medicine_medicine_id, version)
	VALUES (104, 10, 2, 0);
INSERT INTO public.medicine_item(
	medicine_item_id, quantity, medicine_medicine_id, version)
	VALUES (105, 5, 3, 0);
INSERT INTO public.medicine_item(
	medicine_item_id, quantity, medicine_medicine_id, version)
	VALUES (106, 3, 4, 0);
	

INSERT INTO public.order(order_id, offer_deadline, order_status, pharmacy_pharmacy_id, pharmacy_administrator_user_id)
	VALUES (default,'2021/08/31', 'PROCESSED', 2, 19);

INSERT INTO public.order_medicine_item(order_order_id, medicine_item_medicine_item_id)
	VALUES (2, 104);

INSERT INTO public.order_medicine_item(order_order_id, medicine_item_medicine_item_id)
	VALUES (2, 105);

INSERT INTO public.order_medicine_item(order_order_id, medicine_item_medicine_item_id)
	VALUES (2, 106);

INSERT INTO public.offer(
	offer_id, delivery_deadline, price, status, order_order_id, supplier_user_id)
	VALUES (default, '2021/09/05', 6000, 'WAITING', 2, 2);
	

INSERT INTO public.offer(
	offer_id, delivery_deadline, price, status, order_order_id, supplier_user_id)
	VALUES (default, '2021/09/09', 5300, 'WAITING', 2, 4);
	

INSERT INTO public.offer(
	offer_id, delivery_deadline, price, status, order_order_id, supplier_user_id)
	VALUES (default, '2021/09/06', 5600, 'WAITING', 2, 3);

INSERT INTO public.user_medicine_item(
	supplier_user_id, medicine_item_medicine_item_id) 
	values (2, 104);

INSERT INTO public.user_medicine_item(
	supplier_user_id, medicine_item_medicine_item_id) 
	values (2, 105);
	
INSERT INTO public.user_medicine_item(
	supplier_user_id, medicine_item_medicine_item_id) 
	values (2, 106);

INSERT INTO public.order(
	order_id, offer_deadline, order_status, pharmacy_pharmacy_id, pharmacy_administrator_user_id)
	VALUES (default, '2021/08/26', 'FINISHED', 2, 10);
	
INSERT INTO public.offer(
	offer_id, delivery_deadline, price, status, order_order_id, supplier_user_id)
	VALUES (default, '2021/08/31', 52000, 'ACCEPTED', 3, 2);
	
INSERT INTO public.offer(
	offer_id, delivery_deadline, price, status, order_order_id, supplier_user_id)
	VALUES (default, '2021/08/30', 54000, 'DECLINED', 3, 2);
	
INSERT INTO public.order(
	order_id, offer_deadline, order_status, pharmacy_pharmacy_id, pharmacy_administrator_user_id)
	VALUES (default, '2021/08/20', 'FINISHED', 3, 6);
	
INSERT INTO public.offer(
	offer_id, delivery_deadline, price, status, order_order_id, supplier_user_id)
	VALUES (default, '2021/08/23', 8000, 'ACCEPTED', 4, 2);
INSERT INTO public.offer(
	offer_id, delivery_deadline, price, status, order_order_id, supplier_user_id)
	VALUES (default, '2021/08/21', 15000, 'WAITING', 4, 2);
	
INSERT INTO public.offer(
	offer_id, delivery_deadline, price, status, order_order_id, supplier_user_id)
	VALUES (default, '2021/08/25', 10000, 'DECLINED', 4, 2);	
	
INSERT INTO public.order(
	order_id, offer_deadline, order_status, pharmacy_pharmacy_id, pharmacy_administrator_user_id)
	VALUES (default, '2021/09/30', 'WAITING_OFFER', 2, 10);
	
INSERT INTO public.offer(
	offer_id, delivery_deadline, price, status, order_order_id, supplier_user_id)
	VALUES (default, '2021/10/10', 12000, 'WAITING', 5, 2);	
	
INSERT INTO public.offer(
	offer_id, delivery_deadline, price, status, order_order_id, supplier_user_id)
	VALUES (default, '2021/10/15', 14000, 'WAITING', 5, 2);
	
--- Pharmacies - Dermatologists - TABLE INSERT DATA ---
-- Sara dermatologist
 INSERT INTO public.pharmacy_dermatologist(
	pharmacy_pharmacy_id, dermatologist_user_id)
	VALUES (1, 12);
 INSERT INTO public.pharmacy_dermatologist(
	pharmacy_pharmacy_id, dermatologist_user_id)
	VALUES (2, 12); 
-- Katarina dermatologist
 INSERT INTO public.pharmacy_dermatologist(
	pharmacy_pharmacy_id, dermatologist_user_id)
	VALUES (2, 7);
 INSERT INTO public.pharmacy_dermatologist(
	pharmacy_pharmacy_id, dermatologist_user_id)
	VALUES (3, 7);
-- Branko dermatologist
 INSERT INTO public.pharmacy_dermatologist(
	pharmacy_pharmacy_id, dermatologist_user_id)
	VALUES (2, 13);
-- Milan dermatologist
 INSERT INTO public.pharmacy_dermatologist(
	pharmacy_pharmacy_id, dermatologist_user_id)
	VALUES (3, 14);
-- Luka dermatologist
 INSERT INTO public.pharmacy_dermatologist(
	pharmacy_pharmacy_id, dermatologist_user_id)
	VALUES (3, 18);
 INSERT INTO public.pharmacy_dermatologist(
	pharmacy_pharmacy_id, dermatologist_user_id)
	VALUES (2, 18);

-- PROMOTION - TEST DATA - my mail lukait31@gmail.com for Patient Luka Tomic 
INSERT INTO promotion_notification (patient_id, pharmacy_id) VALUES (15, 2);
INSERT INTO promotion_notification (patient_id, pharmacy_id) VALUES (1, 2);
INSERT INTO promotion_notification (patient_id, pharmacy_id) VALUES (1, 1);

-- PHARMACIST-VACATION - TEST DATA
insert into pharmacist_vacation(vacation_id, start_date, end_date, pharmacist_user_id, status) values (default, '20210912 07:00:00 AM','20210922 07:00:00 PM', 11, 2); 
insert into pharmacist_vacation(vacation_id, start_date, end_date, pharmacist_user_id, status) values (default, '20210912 07:00:00 AM','20210922 07:00:00 PM', 16, 2); 

insert into pharmacist_vacation(vacation_id, start_date, end_date, pharmacist_user_id, status) values (default, '20210905 07:00:00 AM','20210915 07:00:00 PM', 16, 2); 
insert into pharmacist_vacation(vacation_id, start_date, end_date, pharmacist_user_id, status) values (default, '20210905 07:00:00 AM','20210915 07:00:00 PM', 17, 2); 

insert into pharmacist_vacation(vacation_id, start_date, end_date, pharmacist_user_id, status) values (default, '20211112 07:00:00 AM','20211115 07:00:00 PM', 9, 0); 

-- DERMATOLOGIST-VACATION - TEST DATA - Luka Dermatolog user_id : 18

insert into dermatologist_vacation(vacation_id, start_date, end_date, dermatologist_user_id, status) values (default, '20211012 07:00:00 AM','20211022 07:00:00 PM', 18, 2); 
insert into dermatologist_vacation(vacation_id, start_date, end_date, dermatologist_user_id, status) values (default, '20211022 07:00:00 AM','20211101 07:00:00 PM', 18, 2); 

INSERT INTO public.examination(appointment_id, appointment_status, cancelled, date_and_time,
duration, points, price, dermatologist_user_id, patient_user_id, pharmacy_pharmacy_id, version)
VALUES (default, 'FINISHED', false, '2021-01-01 11:00:00', 30, 5,1500, 12, 1, 1, 0);

INSERT INTO public.examination(appointment_id, appointment_status, cancelled, date_and_time,
duration, points, price, dermatologist_user_id, patient_user_id, pharmacy_pharmacy_id, version)
VALUES (default, 'NONE', false, '2021-09-04 11:00:00', 30, 5,1500, 12, 1, 1, 0);

INSERT INTO public.examination(appointment_id, appointment_status, cancelled, date_and_time,
duration, points, price, dermatologist_user_id, patient_user_id, pharmacy_pharmacy_id, version)
VALUES (default, 'NONE', false, '2021-09-05 12:00:00', 30, 5,1500, 12, 15, 1, 0);

INSERT INTO public.examination(appointment_id, appointment_status, cancelled, date_and_time,
duration, points, price, dermatologist_user_id, patient_user_id, pharmacy_pharmacy_id, version)
VALUES (default, 'NONE', false, '2021-09-06 13:00:00', 30, 5,1500, 12, 1, 1, 0);

INSERT INTO public.examination(appointment_id, appointment_status, cancelled, date_and_time,
duration, points, price, dermatologist_user_id, patient_user_id, pharmacy_pharmacy_id, version)
VALUES (default, 'NONE', false, '2021-09-07 11:00:00', 30, 5,1500, 12, 1, 1, 0);

INSERT INTO public.examination(appointment_id, appointment_status, cancelled, date_and_time,
duration, points, price, dermatologist_user_id, patient_user_id, pharmacy_pharmacy_id, version)
VALUES (default, 'NONE', false, '2021-09-08 11:00:00', 30, 5,1500, 12, 1, 1, 0);

INSERT INTO public.examination(appointment_id, appointment_status, cancelled, date_and_time,
duration, points, price, dermatologist_user_id, patient_user_id, pharmacy_pharmacy_id, version)
VALUES (default, 'NONE', false, '2021-09-08 12:00:00', 30, 5,1500, 12, 1, 2, 0);

INSERT INTO public.examination(appointment_id, appointment_status, cancelled, date_and_time,
duration, points, price, dermatologist_user_id, patient_user_id, pharmacy_pharmacy_id, version)
VALUES (default, 'NONE', false, '2021-09-09 12:00:00', 30, 5,1500, 12, 1, 2, 0);

INSERT INTO public.examination(appointment_id, appointment_status, cancelled, date_and_time,
duration, points, price, dermatologist_user_id, patient_user_id, pharmacy_pharmacy_id, version)
VALUES (default, 'NONE', false, '2021-09-09 16:00:00', 30, 5,1500, 12, 1, 2, 0);

INSERT INTO public.examination(appointment_id, appointment_status, cancelled, date_and_time,
duration, points, price, dermatologist_user_id, patient_user_id, pharmacy_pharmacy_id, version)
VALUES (default, 'FINISHED', false, '2021-01-01 11:00:00', 30, 5,1500, 12, 1, 1, 0);


INSERT INTO public.examination(appointment_id, appointment_status, cancelled, date_and_time,
duration, points, price, dermatologist_user_id, patient_user_id, pharmacy_pharmacy_id, version)
VALUES (default, 'FINISHED', false, '2021-09-01 12:35:00', 30, 5,1500, 12, 1, 1, 0);

INSERT INTO public.examination(appointment_id, appointment_status, cancelled, date_and_time,
duration, points, price, dermatologist_user_id, patient_user_id, pharmacy_pharmacy_id, version)
VALUES (default, 'NONE', false, '2021-11-11 11:30:00', 30, 5,2000, 12, null, 1, 0);

INSERT INTO public.examination(appointment_id, appointment_status, cancelled, date_and_time,
duration, points, price, dermatologist_user_id, patient_user_id, pharmacy_pharmacy_id, version)
VALUES (default, 'NONE', false, '2021-11-11 12:00:00', 30, 5,2000, 12, null, 1, 0);

INSERT INTO public.examination(appointment_id, appointment_status, cancelled, date_and_time,
duration, points, price, dermatologist_user_id, patient_user_id, pharmacy_pharmacy_id, version)
VALUES (default, 'NONE', false, '2021-11-11 13:00:00', 30, 5,2000, 12, 1, 1, 0);

INSERT INTO public.examination(appointment_id, appointment_status, cancelled, date_and_time,
duration, points, price, dermatologist_user_id, patient_user_id, pharmacy_pharmacy_id, version)
VALUES (default, 'NONE', false, '2021-11-11 16:00:00', 30, 5,2000, 12, null, 2, 0);

INSERT INTO public.examination(appointment_id, appointment_status, cancelled, date_and_time,
duration, points, price, dermatologist_user_id, patient_user_id, pharmacy_pharmacy_id, version)
VALUES (default, 'NONE', false, '2021-11-11 17:00:00', 30, 5,2000, 12, 15, 2, 0);

INSERT INTO public.examination(appointment_id, appointment_status, cancelled, date_and_time,
duration, points, price, dermatologist_user_id, patient_user_id, pharmacy_pharmacy_id, version)
VALUES (default, 'NONE', true, '2021-11-11 15:00:00', 30, 5,2000, 13, 1, 2, 0);

INSERT INTO public.examination(appointment_id, appointment_status, cancelled, date_and_time,
duration, points, price, dermatologist_user_id, patient_user_id, pharmacy_pharmacy_id, version)
VALUES (default, 'NONE', false, '2021-12-12 15:00:00', 30, 5,2100, 13, null, 2, 0);

INSERT INTO public.examination(appointment_id, appointment_status, cancelled, date_and_time,
duration, points, price, dermatologist_user_id, patient_user_id, pharmacy_pharmacy_id, version)
VALUES (default, 'NONE', false, '2021-08-21 16:00:00', 30, 5,2100, 13, 1, 2, 0);

INSERT INTO public.examination(appointment_id, appointment_status, cancelled, date_and_time,
duration, points, price, dermatologist_user_id, patient_user_id, pharmacy_pharmacy_id, version)
VALUES (default, 'NONE', false, '2021-09-09 17:00:00', 30, 5, 2100, 13, 1, 2, 0);

INSERT INTO public.examination(appointment_id, appointment_status, cancelled, date_and_time,
duration, points, price, dermatologist_user_id, patient_user_id, pharmacy_pharmacy_id, version)
VALUES (default, 'NONE', false, '2021-09-02 12:10:00', 30, 5, 2100, 12, 1, 2, 0);

INSERT INTO public.examination(appointment_id, appointment_status, cancelled, date_and_time,
duration, points, price, dermatologist_user_id, patient_user_id, pharmacy_pharmacy_id, version)
VALUES (default, 'NONE', false, '2021-09-02 13:10:00', 30, 5, 2100, 12, 15, 2, 0);

INSERT INTO public.examination(appointment_id, appointment_status, cancelled, date_and_time,
duration, points, price, dermatologist_user_id, patient_user_id, pharmacy_pharmacy_id, version)
VALUES (default, 'NONE', false, '2021-09-02 14:10:00', 30, 5, 2100, 12, 15, 3, 0);


INSERT INTO public.examination(appointment_id, appointment_status, cancelled, date_and_time,
duration, points, price, dermatologist_user_id, patient_user_id, pharmacy_pharmacy_id, version)
VALUES (default, 'NONE', false, '2021-09-06 08:30:00', 30, 5,2400, 18, null, 2, 0);

INSERT INTO public.examination(appointment_id, appointment_status, cancelled, date_and_time,
duration, points, price, dermatologist_user_id, patient_user_id, pharmacy_pharmacy_id, version)
VALUES (default, 'NONE', false, '2021-09-04 09:30:00', 30, 5,2400, 18, null, 2, 0);

INSERT INTO public.examination(appointment_id, appointment_status, cancelled, date_and_time,
duration, points, price, dermatologist_user_id, patient_user_id, pharmacy_pharmacy_id, version)
VALUES (default, 'NONE', false, '2021-10-10 10:30:00', 30, 5,2200, 18, null, 2, 0);

INSERT INTO public.examination(appointment_id, appointment_status, cancelled, date_and_time,
duration, points, price, dermatologist_user_id, patient_user_id, pharmacy_pharmacy_id, version)
VALUES (default, 'NONE', false, '2021-09-07 17:00:00', 30, 5,1800, 13, null, 2, 0);

INSERT INTO public.examination(appointment_id, appointment_status, cancelled, date_and_time,
duration, points, price, dermatologist_user_id, patient_user_id, pharmacy_pharmacy_id, version)
VALUES (default, 'NONE', false, '2021-09-09 09:40:00', 20, 5,1600, 7, null, 2, 0);

INSERT INTO public.medicine_replacement_medicine(
	medicine_medicine_id, replacement_medicine_medicine_id)
	VALUES (1, 6);
INSERT INTO public.medicine_replacement_medicine(
	medicine_medicine_id, replacement_medicine_medicine_id)
	VALUES (7, 8);
INSERT INTO public.medicine_replacement_medicine(
	medicine_medicine_id, replacement_medicine_medicine_id)
	VALUES (9, 10);
INSERT INTO public.medicine_replacement_medicine(
	medicine_medicine_id, replacement_medicine_medicine_id)
	VALUES (11, 12);

INSERT INTO public.medicine_item(
	medicine_item_id, quantity, medicine_medicine_id,  version)
	VALUES (default, 10, 1, 0);
INSERT INTO public.medicine_item(
	medicine_item_id, quantity, medicine_medicine_id,  version)
	VALUES (default, 20, 2, 0);
INSERT INTO public.medicine_item(
	medicine_item_id, quantity, medicine_medicine_id, version)
	VALUES (default, 3, 3, 0);
INSERT INTO public.medicine_item(
	medicine_item_id, quantity, medicine_medicine_id, version)
	VALUES (default, 10, 4, 0);
INSERT INTO public.medicine_item(
	medicine_item_id, quantity, medicine_medicine_id, version)
	VALUES (default, 20, 5, 0);
INSERT INTO public.medicine_item(
	medicine_item_id, quantity, medicine_medicine_id, version)
	VALUES (default, 3, 6, 0);

INSERT INTO public.medicine_item(
	medicine_item_id, quantity, medicine_medicine_id, version)
	VALUES (default, 3, 6, 0);

INSERT INTO public.medicine_item(
	medicine_item_id, quantity, medicine_medicine_id, version)
	VALUES (default, 3, 8, 0);  

INSERT INTO public.medicine_item(
	medicine_item_id, quantity, medicine_medicine_id, version)
	VALUES (default, 10, 1, 0);
INSERT INTO public.medicine_item(
	medicine_item_id, quantity, medicine_medicine_id, version)
	VALUES (default, 20, 2, 0);
INSERT INTO public.medicine_item(
	medicine_item_id, quantity, medicine_medicine_id, version)
	VALUES (default, 20, 2, 0);
INSERT INTO public.medicine_item(
	medicine_item_id, quantity, medicine_medicine_id, version)
	VALUES (default, 1, 3, 0);

INSERT INTO public.medicine_item(
	medicine_item_id, quantity, medicine_medicine_id, version)
	VALUES (default, 1, 4, 0);

INSERT INTO public.medicine_item(
	medicine_item_id, quantity, medicine_medicine_id, version)
	VALUES (default, 1, 5, 0);

INSERT INTO public.medicine_item(
	medicine_item_id, quantity, medicine_medicine_id, version)
	VALUES (default, 1, 4, 0);
    
INSERT INTO public.time_interval(
	time_interval_id, start_date, end_date)
	VALUES (default, '2021-11-11 07:00:00', '2021-11-11 15:00:00');

INSERT INTO public.time_interval(
	time_interval_id, start_date, end_date)
	VALUES (default, '2021-11-01 00:00:00', '2021-11-30 23:59:59');

INSERT INTO public.time_interval(
	time_interval_id, start_date, end_date)
	VALUES (default, '2021-11-11 15:00:00', '2021-11-11 20:00:00');

INSERT INTO public.time_interval(
	time_interval_id, start_date, end_date)
	VALUES (default, '2021-08-01 00:00:00', '2021-11-30 23:59:59');

INSERT INTO public.time_interval(
	time_interval_id, start_date, end_date)
	VALUES (default, '2021-11-11 07:00:00', '2021-11-11 15:00:00');

INSERT INTO public.time_interval(
	time_interval_id, start_date, end_date)
	VALUES (default, '2021-11-01 00:00:00', '2021-11-30 23:59:59');
	
INSERT INTO public.time_interval(
	time_interval_id, start_date, end_date)
	VALUES (default, '2021-08-01 07:00:00', '2021-11-30 07:00:00');

INSERT INTO public.time_interval(
	time_interval_id, start_date, end_date)
	VALUES (default, '2021-08-01 07:00:00', '2021-11-30 07:00:00');
	
INSERT INTO public.time_interval(
	time_interval_id, start_date, end_date)
	VALUES (default, '2021-08-31 07:00:00', '2021-12-31 07:00:00');
	
INSERT INTO public.time_interval(
	time_interval_id, start_date, end_date)
	VALUES (default, '2021-08-31 07:00:00', '2021-12-31 15:00:00');
	
INSERT INTO public.time_interval(
	time_interval_id, start_date, end_date)
	VALUES (default, '2021-08-31 15:00:00', '2021-12-31 20:00:00');


INSERT INTO public.time_interval(
	time_interval_id, start_date, end_date)
	VALUES (default, '2021-08-01 00:00:00', '2021-08-31 23:59:59');

INSERT INTO public.time_interval(
	time_interval_id, start_date, end_date)
	VALUES (default, '2021-09-01 00:00:00', '2021-09-30 23:59:59');

INSERT INTO public.work_schedule_pharmacist(
	work_schedule_id, pharmacist_user_id, pharmacy_pharmacy_id, shift_time_interval_id, valid_for_time_interval_id)
	VALUES (default, 9, 2, 1, 2);

INSERT INTO public.work_schedule_pharmacist(
	work_schedule_id, pharmacist_user_id, pharmacy_pharmacy_id, shift_time_interval_id, valid_for_time_interval_id)
	VALUES (default, 9, 2, 1, 7);

INSERT INTO public.work_schedule_pharmacist(
	work_schedule_id, pharmacist_user_id, pharmacy_pharmacy_id, shift_time_interval_id, valid_for_time_interval_id)
	VALUES (default, 9, 2, 1, 8);   
   
INSERT INTO public.work_schedule_pharmacist(
	work_schedule_id, pharmacist_user_id, pharmacy_pharmacy_id, shift_time_interval_id, valid_for_time_interval_id)
	VALUES (default, 11, 3, 3, 4);

-- Branko - work in Jankovic Pharmacy Second Shift - 01.08.2021. - 30.11.2021.
INSERT INTO public.work_schedule_dermatologist(
	work_schedule_id, dermatologist_user_id, pharmacy_pharmacy_id, shift_time_interval_id, valid_for_time_interval_id)
	VALUES (default, 13, 2, 3, 4);

-- Sara - work in Benu Pharmacy First Shift Valid for time 01.11.2021 - 30.11.2021.
INSERT INTO public.work_schedule_dermatologist(
	work_schedule_id, dermatologist_user_id, pharmacy_pharmacy_id, shift_time_interval_id, valid_for_time_interval_id)
	VALUES (default, 12, 1, 1, 2);
	
-- Sara - work in Jankovic Pharmacy Second Shift Valid for time 01.08.2021 - 30.11.2021.
INSERT INTO public.work_schedule_dermatologist(
	work_schedule_id, dermatologist_user_id, pharmacy_pharmacy_id, shift_time_interval_id, valid_for_time_interval_id)
	VALUES (default, 12, 2, 3, 8);
	
-- Luka - work in Jankovic First Shift Valid for time 31.08.2021. - 31.12.2021.
INSERT INTO public.work_schedule_dermatologist(
	work_schedule_id, dermatologist_user_id, pharmacy_pharmacy_id, shift_time_interval_id, valid_for_time_interval_id)
	VALUES (default, 18, 2, 10, 9);

-- Luka - also work in Higija Second Shift Valid for time 30.08.2021. - 31.12.2021.
INSERT INTO public.work_schedule_dermatologist(
	work_schedule_id, dermatologist_user_id, pharmacy_pharmacy_id, shift_time_interval_id, valid_for_time_interval_id)
	VALUES (default, 18, 3, 11, 9);
	
-- Milan - also work in Higija First Shift Valid for time 30.08.2021. - 31.12.2021.
INSERT INTO public.work_schedule_dermatologist(
	work_schedule_id, dermatologist_user_id, pharmacy_pharmacy_id, shift_time_interval_id, valid_for_time_interval_id)
	VALUES (default, 14, 3, 10, 9);
	
-- Katarina - work in Jankovic First Shift Valid for time 30.08.2021. - 31.12.2021.
INSERT INTO public.work_schedule_dermatologist(
	work_schedule_id, dermatologist_user_id, pharmacy_pharmacy_id, shift_time_interval_id, valid_for_time_interval_id)
	VALUES (default, 7, 2, 10, 9);
	
-- Katarina - work in Higija Second Shift Valid for time 30.08.2021. - 31.12.2021.
INSERT INTO public.work_schedule_dermatologist(
	work_schedule_id, dermatologist_user_id, pharmacy_pharmacy_id, shift_time_interval_id, valid_for_time_interval_id)
	VALUES (default, 7, 3, 11, 9);

INSERT INTO public.consultation(
	appointment_id, appointment_status, cancelled, date_and_time, duration, points, price, patient_user_id, pharmacist_user_id, pharmacy_pharmacy_id)
	VALUES (default, 'NONE', false, '2021-11-11 08:15:00', 30, 5, 700, 1, 9, 2);

INSERT INTO public.consultation(
	appointment_id, appointment_status, cancelled, date_and_time, duration, points, price, patient_user_id, pharmacist_user_id, pharmacy_pharmacy_id)
	VALUES (default, 'NONE', false, '2021-09-01 00:50:00', 30, 5, 700, 1, 9, 2);

INSERT INTO public.consultation(
	appointment_id, appointment_status, cancelled, date_and_time, duration, points, price, patient_user_id, pharmacist_user_id, pharmacy_pharmacy_id)
	VALUES (default, 'NONE', false, '2021-11-11 09:00:00', 30, 5, 700, 1, 9, 2);

INSERT INTO public.consultation(
	appointment_id, appointment_status, cancelled, date_and_time, duration, points, price, patient_user_id, pharmacist_user_id, pharmacy_pharmacy_id)
	VALUES (default, 'NONE', false, '2021-11-11 16:15:00', 30, 5, 700, 1, 11, 3);

INSERT INTO public.consultation(
	appointment_id, appointment_status, cancelled, date_and_time, duration, points, price, patient_user_id, pharmacist_user_id, pharmacy_pharmacy_id)
	VALUES (default, 'NONE', false, '2021-08-01 12:15:00', 30, 5, 700, 1, 9, 2);

INSERT INTO public.consultation(
	appointment_id, appointment_status, cancelled, date_and_time, duration, points, price, patient_user_id, pharmacist_user_id, pharmacy_pharmacy_id)
	VALUES (default, 'NONE', false, '2021-08-02 12:15:00', 30, 5, 700, 1, 9, 2);

INSERT INTO public.consultation(
	appointment_id, appointment_status, cancelled, date_and_time, duration, points, price, patient_user_id, pharmacist_user_id, pharmacy_pharmacy_id)
	VALUES (default, 'NONE', false, '2021-08-03 12:15:00', 30, 5, 700, 1, 9, 2);

INSERT INTO public.consultation(
	appointment_id, appointment_status, cancelled, date_and_time, duration, points, price, patient_user_id, pharmacist_user_id, pharmacy_pharmacy_id)
	VALUES (default, 'NONE', false, '2021-09-01 12:15:00', 30, 5, 700, 1, 9, 2);

INSERT INTO public.consultation(
	appointment_id, appointment_status, cancelled, date_and_time, duration, points, price, patient_user_id, pharmacist_user_id, pharmacy_pharmacy_id)
	VALUES (default, 'NONE', false, '2021-09-02 12:15:00', 30, 5, 700, 1, 9, 2);

INSERT INTO public.consultation(
	appointment_id, appointment_status, cancelled, date_and_time, duration, points, price, patient_user_id, pharmacist_user_id, pharmacy_pharmacy_id)
	VALUES (default, 'NONE', false, '2021-09-03 12:15:00', 30, 5, 700, 1, 9, 2);

INSERT INTO public.consultation(
	appointment_id, appointment_status, cancelled, date_and_time, duration, points, price, patient_user_id, pharmacist_user_id, pharmacy_pharmacy_id)
	VALUES (default, 'NONE', false, '2021-11-11 17:15:00', 30, 5, 700, 1, 11, 3);
    
INSERT INTO public.consultation(
	appointment_id, appointment_status, cancelled, date_and_time, duration, points, price, patient_user_id, pharmacist_user_id, pharmacy_pharmacy_id)
	VALUES (default, 'NONE', false, '2021-09-02 13:05:00', 30, 5, 700, 15, 11, 3);
   
INSERT INTO public.consultation(
	appointment_id, appointment_status, cancelled, date_and_time, duration, points, price, patient_user_id, pharmacist_user_id, pharmacy_pharmacy_id)
	VALUES (default, 'NONE', false, '2021-09-04 13:05:00', 30, 5, 700, 15, 9, 2);
    
    INSERT INTO public.consultation(
	appointment_id, appointment_status, cancelled, date_and_time, duration, points, price, patient_user_id, pharmacist_user_id, pharmacy_pharmacy_id)
	VALUES (default, 'NONE', false, '2021-09-04 14:05:00', 30, 5, 700, 15, 9, 2);
    
    INSERT INTO public.consultation(
	appointment_id, appointment_status, cancelled, date_and_time, duration, points, price, patient_user_id, pharmacist_user_id, pharmacy_pharmacy_id)
	VALUES (default, 'NONE', false, '2021-09-05 11:00:00', 30, 5, 700, 1, 9, 2);
    
    INSERT INTO public.consultation(
	appointment_id, appointment_status, cancelled, date_and_time, duration, points, price, patient_user_id, pharmacist_user_id, pharmacy_pharmacy_id)
	VALUES (default, 'NONE', false, '2021-09-05 16:05:00', 30, 5, 700, 1, 9, 2);
    
    INSERT INTO public.consultation(
	appointment_id, appointment_status, cancelled, date_and_time, duration, points, price, patient_user_id, pharmacist_user_id, pharmacy_pharmacy_id)
	VALUES (default, 'NONE', false, '2021-09-06 11:05:00', 30, 5, 700, 1, 9, 2);
    
    INSERT INTO public.consultation(
	appointment_id, appointment_status, cancelled, date_and_time, duration, points, price, patient_user_id, pharmacist_user_id, pharmacy_pharmacy_id)
	VALUES (default, 'NONE', false, '2021-09-07 10:05:00', 30, 5, 700, 1, 9, 2);
    
    INSERT INTO public.consultation(
	appointment_id, appointment_status, cancelled, date_and_time, duration, points, price, patient_user_id, pharmacist_user_id, pharmacy_pharmacy_id)
	VALUES (default, 'NONE', false, '2021-09-08 13:05:00', 30, 5, 700, 1, 9, 2);
    
    INSERT INTO public.consultation(
	appointment_id, appointment_status, cancelled, date_and_time, duration, points, price, patient_user_id, pharmacist_user_id, pharmacy_pharmacy_id)
	VALUES (default, 'NONE', false, '2021-09-08 18:05:00', 30, 5, 700, 1, 9, 2);
    
    INSERT INTO public.consultation(
	appointment_id, appointment_status, cancelled, date_and_time, duration, points, price, patient_user_id, pharmacist_user_id, pharmacy_pharmacy_id)
	VALUES (default, 'NONE', false, '2021-09-08 14:30:00', 30, 5, 700, 1, 9, 2);
    

INSERT INTO public.work_schedule_pharmacist_scheduled_consultations(
	work_schedule_pharmacist_work_schedule_id, scheduled_consultations_appointment_id)
	VALUES (1, 1);

INSERT INTO public.work_schedule_pharmacist_scheduled_consultations(
	work_schedule_pharmacist_work_schedule_id, scheduled_consultations_appointment_id)
	VALUES (1, 2);

INSERT INTO public.work_schedule_pharmacist_scheduled_consultations(
	work_schedule_pharmacist_work_schedule_id, scheduled_consultations_appointment_id)
	VALUES (2, 3);

INSERT INTO public.work_schedule_pharmacist_scheduled_consultations(
	work_schedule_pharmacist_work_schedule_id, scheduled_consultations_appointment_id)
	VALUES (2, 4);

INSERT INTO public.pharmacy_medicine_item(
	pharmacy_pharmacy_id, medicine_item_medicine_item_id)
	 values(1, 1);

INSERT INTO public.pharmacy_medicine_item(
	pharmacy_pharmacy_id, medicine_item_medicine_item_id)
	 values(1, 10);

INSERT INTO public.pharmacy_medicine_item(
	pharmacy_pharmacy_id, medicine_item_medicine_item_id) 
	values(3, 2);

INSERT INTO public.pharmacy_medicine_item(
	pharmacy_pharmacy_id, medicine_item_medicine_item_id) 
	values(2, 4);

INSERT INTO public.pharmacy_medicine_item(
	pharmacy_pharmacy_id, medicine_item_medicine_item_id) 
	values(2, 9);

INSERT INTO public.pharmacy_medicine_item(
	pharmacy_pharmacy_id, medicine_item_medicine_item_id) 
	values(2, 11);

INSERT INTO public.pharmacy_medicine_item(
	pharmacy_pharmacy_id, medicine_item_medicine_item_id) 
	values(1, 3);

INSERT INTO public.user_medicine_item(
	supplier_user_id, medicine_item_medicine_item_id) 
	values (2, 3);

INSERT INTO public.user_medicine_item(
	supplier_user_id, medicine_item_medicine_item_id)
	 values (1, 4);

INSERT INTO public.price_tag(
	price_tag_id, price, medicine_medicine_id, time_interval_time_interval_id)
	VALUES (default, 400, 1, 7);
	
INSERT INTO public.price_tag(
	price_tag_id, price, medicine_medicine_id, time_interval_time_interval_id)
	VALUES (default, 500, 2, 7);

INSERT INTO public.price_tag(
	price_tag_id, price, medicine_medicine_id, time_interval_time_interval_id)
	VALUES (default, 580, 2, 8);
    
    INSERT INTO public.price_tag(
	price_tag_id, price, medicine_medicine_id, time_interval_time_interval_id)
	VALUES (default, 500, 3, 7);
INSERT INTO public.price_tag(
	price_tag_id, price, medicine_medicine_id, time_interval_time_interval_id)
	VALUES (default, 500, 4, 7);
INSERT INTO public.price_tag(
	price_tag_id, price, medicine_medicine_id, time_interval_time_interval_id)
	VALUES (default, 500, 8, 7);

INSERT INTO public.price_tag(
	price_tag_id, price, medicine_medicine_id, time_interval_time_interval_id)
	VALUES (default, 350, 1, 8);
INSERT INTO public.pharmacy_price_tags(
	pharmacy_pharmacy_id, price_tags_price_tag_id)
	VALUES (1, 1);

INSERT INTO public.pharmacy_price_tags(
	pharmacy_pharmacy_id, price_tags_price_tag_id)
	VALUES (1, 2);

INSERT INTO public.pharmacy_price_tags(
	pharmacy_pharmacy_id, price_tags_price_tag_id)
	VALUES (2, 3);

INSERT INTO public.pharmacy_price_tags(
	pharmacy_pharmacy_id, price_tags_price_tag_id)
	VALUES (2, 4);
    
INSERT INTO public.pharmacy_price_tags(
	pharmacy_pharmacy_id, price_tags_price_tag_id)
	VALUES (2, 5);
INSERT INTO public.pharmacy_price_tags(
	pharmacy_pharmacy_id, price_tags_price_tag_id)
	VALUES (2, 6);
INSERT INTO public.pharmacy_price_tags(
	pharmacy_pharmacy_id, price_tags_price_tag_id)
	VALUES (2, 7);
	
INSERT INTO public.examination(appointment_id, appointment_status, cancelled, date_and_time,
duration, points, price, dermatologist_user_id, patient_user_id, pharmacy_pharmacy_id, version)
VALUES (default, 'FINISHED', false, '2021-08-09 15:00:00', 30, 5, 2100, 13, 1, 2, 0);

INSERT INTO public.examination(appointment_id, appointment_status, cancelled, date_and_time,
duration, points, price, dermatologist_user_id, patient_user_id, pharmacy_pharmacy_id, version)
VALUES (default, 'FINISHED', false, '2021-08-09 17:00:00', 30, 5, 2100, 13, 1, 2, 0);

INSERT INTO public.examination(appointment_id, appointment_status, cancelled, date_and_time,
duration, points, price, dermatologist_user_id, patient_user_id, pharmacy_pharmacy_id, version)
VALUES (default, 'FINISHED', false, '2021-08-20 16:00:00', 20, 5, 1900, 13, 1, 2, 0);

INSERT INTO public.consultation(
	appointment_id, appointment_status, cancelled, date_and_time, duration, points, price, patient_user_id, pharmacist_user_id, pharmacy_pharmacy_id)
	VALUES (default, 'FINISHED', false, '2021-06-11 08:15:00', 15, 5, 700, 1, 9, 2);

INSERT INTO public.consultation(
	appointment_id, appointment_status, cancelled, date_and_time, duration, points, price, patient_user_id, pharmacist_user_id, pharmacy_pharmacy_id)
	VALUES (default, 'FINISHED', false, '2021-07-07 10:15:00', 30, 5, 780, 1, 9, 2);

INSERT INTO public.consultation(
	appointment_id, appointment_status, cancelled, date_and_time, duration, points, price, patient_user_id, pharmacist_user_id, pharmacy_pharmacy_id)
	VALUES (default, 'FINISHED', false, '2021-08-13 07:15:00', 20, 5, 1000, 1, 9, 2);

INSERT INTO public.complaint_employee(
	comp_emp_id,version, text_employee, patient_user_id, pharmacy_employee_user_id)
	VALUES (default, 0, 'Organizacija pregleda nije dobra.', 1, 13);
	

INSERT INTO public.complaint_employee(
	comp_emp_id, version, text_employee, patient_user_id, pharmacy_employee_user_id)
	VALUES (default, 0, 'Kasnjenje.', 1, 9);
	
INSERT INTO public.complaint_pharmacy(
	comp_ph_id, version, text_ph, patient_user_id, pharmacy_pharmacy_id)
	VALUES (default,0,  'Radi samo jedna kasa sto stvara veliku guzvu.', 1, 1);

INSERT INTO public.complaint_pharmacy(
	comp_ph_id, version, text_ph, patient_user_id, pharmacy_pharmacy_id)
	VALUES (default,0,  'Izdat pogresan lijek', 1, 2);

INSERT INTO public.reservation(
	reservation_id, cancelled, deadline, penalty, recieved, reservation_code, medicine_item_medicine_item_id, patient_user_id, pharmacy_pharmacy_id,version)
	VALUES (default, false, '2021-11-11 00:00:00', false, true, 'ghgy32uu1', 12, 1, 1, 0);
INSERT INTO public.reservation(
	reservation_id, cancelled, deadline, penalty, recieved, reservation_code, medicine_item_medicine_item_id, patient_user_id, pharmacy_pharmacy_id, version)
	VALUES (default, false, '2021-11-11 00:00:00', false, false, 'jhi3d7g2G', 13, 1, 2, 0);
INSERT INTO public.reservation(
	reservation_id, cancelled, deadline, penalty, recieved, reservation_code, medicine_item_medicine_item_id, patient_user_id, pharmacy_pharmacy_id, version)
	VALUES (default, false, '2021-11-11 00:00:00', false, false, 'KDU3BB1', 14, 1, 1, 0);
INSERT INTO public.reservation(
	reservation_id, cancelled, deadline, penalty, recieved, reservation_code, medicine_item_medicine_item_id, patient_user_id, pharmacy_pharmacy_id, version)
	VALUES (default, false, '2021-08-08 00:00:00', false, false, 'gNJHgyoej1', 15, 1, 1, 0);

-- TEST RESERVATION
INSERT INTO public.medicine_item(
	medicine_item_id, quantity, medicine_medicine_id)
	VALUES (216, 3, 2);
	
INSERT INTO public.reservation(
	reservation_id, cancelled, deadline, penalty, recieved, reservation_code, medicine_item_medicine_item_id, patient_user_id, pharmacy_pharmacy_id, version)
	VALUES (default, false, '2021-09-30 00:00:00', false, false, 'ResQodX07', 216, 15, 2, 0);

INSERT INTO public.eprescription(
	prescription_id, code, date, status, patient_user_id, pharmacy_pharmacy_id)
	VALUES (default, '22', '2021-09-01','PROCESSED', 1, 2);
	
INSERT INTO public.eprescription(
	prescription_id, code, date, status, patient_user_id, pharmacy_pharmacy_id)
	VALUES (default, '222', '2021-06-01', 'PROCESSED', 1, 2);

INSERT INTO public.eprescription(
	prescription_id, code, date, status, patient_user_id, pharmacy_pharmacy_id)
	VALUES (default, '123', '2021-08-01', 'DECLINED', 1, 2);

INSERT INTO public.eprescription_medicine(
	eprescription_prescription_id, medicine_medicine_id)
	VALUES (1, 1);
INSERT INTO public.eprescription_medicine(
	eprescription_prescription_id, medicine_medicine_id)
	VALUES (1, 3);

INSERT INTO public.order(order_id, offer_deadline, order_status, pharmacy_pharmacy_id, pharmacy_administrator_user_id)
	VALUES (default,'2021/10/28', 'WAITING_OFFER', 2, 10);

INSERT INTO public.order(order_id, offer_deadline, order_status, pharmacy_pharmacy_id, pharmacy_administrator_user_id)
	VALUES (default,'2021/11/28', 'WAITING_OFFER', 2, 10);

INSERT INTO public.order(order_id, offer_deadline, order_status, pharmacy_pharmacy_id, pharmacy_administrator_user_id)
	VALUES (default,'2021/12/28', 'WAITING_OFFER', 2, 10);
	
INSERT INTO public.order_medicine_item(order_order_id, medicine_item_medicine_item_id)
	VALUES (5, 101);
INSERT INTO public.order_medicine_item(order_order_id, medicine_item_medicine_item_id)
	VALUES (5, 102);
INSERT INTO public.order_medicine_item(order_order_id, medicine_item_medicine_item_id)
	VALUES (5, 103);
INSERT INTO public.order_medicine_item(order_order_id, medicine_item_medicine_item_id)
	VALUES (5, 104);
INSERT INTO public.order_medicine_item(order_order_id, medicine_item_medicine_item_id)
	VALUES (5, 105);
INSERT INTO public.order_medicine_item(order_order_id, medicine_item_medicine_item_id)
	VALUES (6, 101);
INSERT INTO public.order_medicine_item(order_order_id, medicine_item_medicine_item_id)
	VALUES (6, 102);
INSERT INTO public.order_medicine_item(order_order_id, medicine_item_medicine_item_id)
	VALUES (6, 103);
INSERT INTO public.order_medicine_item(order_order_id, medicine_item_medicine_item_id)
	VALUES (6, 104);
INSERT INTO public.order_medicine_item(order_order_id, medicine_item_medicine_item_id)
	VALUES (6, 105);

INSERT INTO public.order_medicine_item(order_order_id, medicine_item_medicine_item_id)
	VALUES (7, 101);
INSERT INTO public.order_medicine_item(order_order_id, medicine_item_medicine_item_id)
	VALUES (7, 102);
INSERT INTO public.order_medicine_item(order_order_id, medicine_item_medicine_item_id)
	VALUES (7, 103);
INSERT INTO public.order_medicine_item(order_order_id, medicine_item_medicine_item_id)
	VALUES (7, 104);
INSERT INTO public.order_medicine_item(order_order_id, medicine_item_medicine_item_id)
	VALUES (7, 105);
	
INSERT INTO public.order_medicine_item(order_order_id, medicine_item_medicine_item_id)
	VALUES (8, 101);
INSERT INTO public.order_medicine_item(order_order_id, medicine_item_medicine_item_id)
	VALUES (8, 102);
INSERT INTO public.order_medicine_item(order_order_id, medicine_item_medicine_item_id)
	VALUES (8, 103);
INSERT INTO public.order_medicine_item(order_order_id, medicine_item_medicine_item_id)
	VALUES (8, 104);
INSERT INTO public.order_medicine_item(order_order_id, medicine_item_medicine_item_id)
	VALUES (8, 105);
	
INSERT INTO public.eprescription_medicine(
	eprescription_prescription_id, medicine_medicine_id)
	VALUES (2, 5);
	