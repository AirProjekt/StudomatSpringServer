CREATE SCHEMA IF NOT EXISTS STUDOMAT;
CREATE TABLE STUDOMAT.VRSTA_KOLEGIJA(
    VRSTA_KOLEGIJA_ID INT GENERATED ALWAYS AS IDENTITY,
    NAZIV VARCHAR(30) NOT NULL
);                 
        
INSERT INTO STUDOMAT.VRSTA_KOLEGIJA(VRSTA_KOLEGIJA_ID, NAZIV) VALUES(1, 'OBVEZNI');           
INSERT INTO STUDOMAT.VRSTA_KOLEGIJA(VRSTA_KOLEGIJA_ID, NAZIV) VALUES(2, 'IZBORNI');           
CREATE TABLE STUDOMAT.KOLEGIJ(
    KOLEGIJ_ID INT GENERATED ALWAYS AS IDENTITY,
    NAZIV VARCHAR(30) NOT NULL,
    ECTS INT NOT NULL,
    VRSTA_KOLEGIJA INT NOT NULL
);                                
       
CREATE TABLE STUDOMAT.KORISNIK(
    KORISNIK_ID INT GENERATED ALWAYS AS IDENTITY,
    KORISNICKO_IME VARCHAR(15) NOT NULL,
    LOZINKA VARCHAR(32) NOT NULL,
    IME VARCHAR(20) NOT NULL,
    PREZIME VARCHAR(20) NOT NULL
);           
              
INSERT INTO STUDOMAT.KORISNIK(KORISNIK_ID, KORISNICKO_IME, LOZINKA, IME, PREZIME) VALUES(1, 'ivo.ivic', '5f4dcc3b5aa765d61d8327deb882cf99', 'Ivo', STRINGDECODE('Ivi\u0107'));
INSERT INTO STUDOMAT.KORISNIK(KORISNIK_ID, KORISNICKO_IME, LOZINKA, IME, PREZIME) VALUES(2, 'pero.peric', '5f4dcc3b5aa765d61d8327deb882cf99', 'Pero', STRINGDECODE('Peri\u0107'));            
INSERT INTO STUDOMAT.KORISNIK(KORISNIK_ID, KORISNICKO_IME, LOZINKA, IME, PREZIME) VALUES(3, 'admin', '21232f297a57a5a743894a0e4a801fc3', 'Mirko', STRINGDECODE('Miri\u0107'));               
             
CREATE TABLE STUDOMAT.UPIS(
    KOLEGIJ_ID INT NOT NULL,
    KORISNIK_ID INT NOT NULL
);            
ALTER TABLE STUDOMAT.UPIS ADD CONSTRAINT STUDOMAT.CONSTRAINT_27 PRIMARY KEY(KOLEGIJ_ID, KORISNIK_ID);          
             
ALTER TABLE STUDOMAT.UPIS ADD CONSTRAINT STUDOMAT.CONSTRAINT_27D9 FOREIGN KEY(KORISNIK_ID) REFERENCES STUDOMAT.KORISNIK(KORISNIK_ID) NOCHECK; 
ALTER TABLE STUDOMAT.UPIS ADD CONSTRAINT STUDOMAT.CONSTRAINT_27D FOREIGN KEY(KOLEGIJ_ID) REFERENCES STUDOMAT.KOLEGIJ(KOLEGIJ_ID) NOCHECK;     
ALTER TABLE STUDOMAT.KOLEGIJ ADD CONSTRAINT STUDOMAT.CONSTRAINT_A9 FOREIGN KEY(VRSTA_KOLEGIJA) REFERENCES STUDOMAT.VRSTA_KOLEGIJA(VRSTA_KOLEGIJA_ID) NOCHECK; 
