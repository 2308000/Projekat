INSERT INTO KORISNIK (korisnicko_ime, ime, prezime, password, email, datum_rodjenja, telefon, uloga, active) VALUES ('Woox', 'Nikola', 'Vukic', 'admin', 'nikolavukic333@gmail.com', '2000-08-23', '0665762600', 'korisnik', '1');
INSERT INTO KORISNIK (korisnicko_ime, ime, prezime, password, email, datum_rodjenja, telefon, uloga, active) VALUES ('CarolynJones', 'Jasmina', 'Milosevic', 'admin', 'jasmina.milosevic13@gmail.com', '1996-09-12', '0665115133', 'korisnik', '1');
INSERT INTO KORISNIK (korisnicko_ime, ime, prezime, password, email, datum_rodjenja, telefon, uloga, active) VALUES ('Insanyus', 'Andrej', 'Cokic', 'admin', 'andrejcokic12@gmail.com', '1996-08-23', '0665769420', 'korisnik', '1');

INSERT INTO FITNESS_CENTAR(naziv_centra, adresa, broj_telefona_centra, email_centra) VALUES ('EFitness Centar', 'Novosadskog Sajma 4', ' 0213004771', 'office@efitnesscentar.rs');

INSERT INTO ADMINISTRATOR(korisnicko_ime, ime, prezime, password, email, datum_rodjenja, telefon, uloga, active) VALUES ('WooxGG', 'Nikola', 'Vukic', 'admin', 'nikolavukic33@gmail.com', '2000-08-23', '0655762600', 'administrator', '1');

INSERT INTO CLAN(korisnicko_ime, ime, prezime, password, email, datum_rodjenja, telefon, uloga, active) VALUES ('Ins4nyus', 'Andrej', 'Cokic', 'clan', 'acokic12@gmail.com', '1996-08-23', '066111111', 'clan', '1');
INSERT INTO CLAN(korisnicko_ime, ime, prezime, password, email, datum_rodjenja, telefon, uloga, active) VALUES ('Scaryride', 'Damir', 'Jasminovic', 'clan', 'damirjasminovic333@gmail.com', '2000-08-23', '066111112', 'clan', '1');
INSERT INTO CLAN(korisnicko_ime, ime, prezime, password, email, datum_rodjenja, telefon, uloga, active) VALUES ('Namox', 'Damir', 'Jasminovic2', 'clan', 'djasminovic@gmail.com', '2003-04-02', '066111113', 'clan', '1');

INSERT INTO TRENER(korisnicko_ime, ime, prezime, password, email, datum_rodjenja, telefon, uloga, active, obrisan, prosecna_ocena, fitness_centar_id) VALUES ('CarolynJ', 'Jasminko', 'Milosevic', 'pt', 'jasminkomilo@gmail.com', '1999-05-22', '066520123', 'trener', '1', '1', '9.0', 1);
INSERT INTO TRENER(korisnicko_ime, ime, prezime, password, email, datum_rodjenja, telefon, uloga, active, obrisan, prosecna_ocena, fitness_centar_id) VALUES ('Woooox', 'Nikolina', 'Vukic', 'pt', 'nikovu@gmail.com', '1997-05-17', '066314215', 'trener', '1', '0', '8.0', 1);
INSERT INTO TRENER(korisnicko_ime, ime, prezime, password, email, datum_rodjenja, telefon, uloga, active, obrisan, prosecna_ocena, fitness_centar_id) VALUES ('CarolynJO', 'Jasminako', 'Milosevic', 'pt', 'jasminkomilo1@gmail.com', '1999-05-22', '066520123', 'trener', '0', '0',  '0.0', 1);
INSERT INTO TRENER(korisnicko_ime, ime, prezime, password, email, datum_rodjenja, telefon, uloga, active, obrisan, prosecna_ocena, fitness_centar_id) VALUES ('CarolynJON', 'Jasminbko', 'Milosevic', 'pt', 'jasminkomilo2@gmail.com', '1999-05-22', '066520123', 'trener', '0', '0',  '0.0', 1);
INSERT INTO TRENER(korisnicko_ime, ime, prezime, password, email, datum_rodjenja, telefon, uloga, active, obrisan, prosecna_ocena, fitness_centar_id) VALUES ('Voooox', 'Nikolija', 'Vukic', 'pt', 'nikovu1@gmail.com', '1997-05-17', '0663142115', 'trener', '0', '0',  '0.0', 1);
INSERT INTO TRENER(korisnicko_ime, ime, prezime, password, email, datum_rodjenja, telefon, uloga, active, obrisan, prosecna_ocena, fitness_centar_id) VALUES ('Woo000oox', 'Nikolina', 'Vukic', 'pt', 'nikovu2@gmail.com', '1997-05-17', '0663142115', 'trener', '0', '0',  '0.0', 1);

INSERT INTO SALA(kapacitet, oznaka_sale, fitness_centar_id) VALUES ('50', 'Broj1', 1);
INSERT INTO SALA(kapacitet, oznaka_sale, fitness_centar_id) VALUES ('50', 'Broj2', 1);

INSERT INTO TRENING(naziv_treninga, opis, tip) VALUES ('STRENGTH', 'WEIGHT LIFTING', 'FOR MEN AND WOMEN'); 
INSERT INTO TRENING(naziv_treninga, opis, tip) VALUES ('FUNCTIONAL', 'CROSSFIT', 'FOR BOYS AND GIRLS');
INSERT INTO TRENING(naziv_treninga, opis, tip) VALUES ('CARDIO', 'CARDIO ROOM', 'FOR KIDS AND CATS');

INSERT INTO TERMIN(pocetak_termina, kraj_termina, trajanje_termina, cena_termina, trening_id, trener_id, sala_id) VALUES ('2021-05-15 10:00:00', '2021-05-15 11:00:00', '60', '300', 1, 1, 1); 
INSERT INTO TERMIN(pocetak_termina, kraj_termina, trajanje_termina, cena_termina, trening_id, trener_id, sala_id) VALUES ('2021-05-16 14:00:00', '2021-05-16 15:00:00', '60', '500', 2, 1, 1);
INSERT INTO TERMIN(pocetak_termina, kraj_termina, trajanje_termina, cena_termina, trening_id, trener_id, sala_id) VALUES ('2021-05-17 19:00:00', '2021-05-17 20:00:00', '60', '400', 3, 1, 1);
INSERT INTO TERMIN(pocetak_termina, kraj_termina, trajanje_termina, cena_termina, trening_id, trener_id, sala_id) VALUES ('2021-05-15 12:00:00', '2021-05-15 13:00:00', '60', '1000', 1, 2, 1); 
INSERT INTO TERMIN(pocetak_termina, kraj_termina, trajanje_termina, cena_termina, trening_id, trener_id, sala_id) VALUES ('2021-05-16 15:00:00', '2021-05-16 16:30:00', '90', '500', 2, 2, 1);
INSERT INTO TERMIN(pocetak_termina, kraj_termina, trajanje_termina, cena_termina, trening_id, trener_id, sala_id) VALUES ('2021-05-17 19:00:00', '2021-05-17 20:30:00', '90', '1100', 3, 2, 2);
INSERT INTO TERMIN(pocetak_termina, kraj_termina, trajanje_termina, cena_termina, trening_id, trener_id, sala_id) VALUES ('2021-05-15 11:00:00', '2021-05-15 12:30:00', '90', '300', 1, 2, 2); 
INSERT INTO TERMIN(pocetak_termina, kraj_termina, trajanje_termina, cena_termina, trening_id, trener_id, sala_id) VALUES ('2021-05-16 13:00:00', '2021-05-16 14:30:00', '90', '500', 2, 1, 2);
INSERT INTO TERMIN(pocetak_termina, kraj_termina, trajanje_termina, cena_termina, trening_id, trener_id, sala_id) VALUES ('2021-05-17 18:00:00', '2021-05-17 19:30:00', '90', '400', 3, 1, 2);

INSERT INTO OCENA(ocena, termin_id, clan_id) VALUES ('8.0', 1, 1);
INSERT INTO OCENA(ocena, termin_id, clan_id) VALUES ('9.0', 2, 2);
INSERT INTO OCENA(ocena, termin_id, clan_id) VALUES ('8.8', 3, 3);
INSERT INTO OCENA(ocena, termin_id, clan_id) VALUES ('7.7', 4, 1);
INSERT INTO OCENA(ocena, termin_id, clan_id) VALUES ('9.9', 5, 2);
INSERT INTO OCENA(ocena, termin_id, clan_id) VALUES ('6.6', 6, 3);
INSERT INTO OCENA(ocena, termin_id, clan_id) VALUES ('7.7', 7, 1);
INSERT INTO OCENA(ocena, termin_id, clan_id) VALUES ('8.8', 8, 2);
INSERT INTO OCENA(ocena, termin_id, clan_id) VALUES ('9.9', 9, 3);

INSERT INTO NEODRADJENI_TERMINI(termin_id, clan_id) VALUES (1, 3);
INSERT INTO NEODRADJENI_TERMINI(termin_id, clan_id) VALUES (2, 1);
INSERT INTO NEODRADJENI_TERMINI(termin_id, clan_id) VALUES (3, 2);

INSERT INTO ODRADJENI_TERMINI(termin_id, clan_id) VALUES (1, 1);
INSERT INTO ODRADJENI_TERMINI(termin_id, clan_id) VALUES (2, 2);
INSERT INTO ODRADJENI_TERMINI(termin_id, clan_id) VALUES (3, 3);
INSERT INTO ODRADJENI_TERMINI(termin_id, clan_id) VALUES (4, 1);
INSERT INTO ODRADJENI_TERMINI(termin_id, clan_id) VALUES (5, 2);
INSERT INTO ODRADJENI_TERMINI(termin_id, clan_id) VALUES (6, 3);
INSERT INTO ODRADJENI_TERMINI(termin_id, clan_id) VALUES (7, 1);
INSERT INTO ODRADJENI_TERMINI(termin_id, clan_id) VALUES (8, 2);
INSERT INTO ODRADJENI_TERMINI(termin_id, clan_id) VALUES (9, 3);
