
INSERT INTO unite_oeuvre (libele) VALUES ('Tonne');
INSERT INTO unite_oeuvre (libele) VALUES ('Kilometre Carre');
INSERT INTO unite_oeuvre (libele) VALUES ('H Machine');
INSERT INTO unite_oeuvre (libele) VALUES ('HTravail');
INSERT INTO unite_oeuvre (libele) VALUES ('Nombre');
INSERT INTO unite_oeuvre (libele) VALUES ('Campagne');
INSERT INTO unite_oeuvre (libele) VALUES ('Cons periodique');
INSERT INTO unite_oeuvre (libele) VALUES ('Litre');
INSERT INTO unite_oeuvre (libele) VALUES ('KW');
INSERT INTO unite_oeuvre (libele) VALUES ('Loyer Mensuel');
INSERT INTO unite_oeuvre (libele) VALUES ('Heure');
INSERT INTO unite_oeuvre (libele) VALUES ('Metre Carre');

INSERT INTO type_centre (id, libele) VALUES(1, 'Structurel');
INSERT INTO type_centre (id, libele) VALUES(2, 'Operationnel');


INSERT INTO centre (libele, id_type) VALUES ('Centre Administratif', 1);
INSERT INTO centre (libele, id_type) VALUES ('Centre de Production (Usine)', 2);
INSERT INTO centre (libele, id_type) VALUES ('Centre de Test (Laboratoire)', 2);