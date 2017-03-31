DELIMITER $$

DROP PROCEDURE IF EXISTS agriotes2017_reset $$

CREATE PROCEDURE agriotes2017_reset()
BEGIN
  -- Désactiver contraintes de clé étrangère
  SET FOREIGN_KEY_CHECKS = 0;
  -- Vider les tables en repositionnant à 1 leur auto-incrément
  TRUNCATE TABLE candidature;
  TRUNCATE TABLE etat_candidature;
  TRUNCATE TABLE formation;
  TRUNCATE TABLE personne;
  TRUNCATE TABLE session_formation;
  -- Réasactiver contraintes de clé étrangère
  SET FOREIGN_KEY_CHECKS = 1;

  BEGIN
    -- Recuperation en cas d'exception
    DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
      -- Annuler la transaction
      ROLLBACK;
	  -- Afficher la cause de l'échec
      SHOW ERRORS;
    END;  
    START TRANSACTION;
	-- Insérer les données dans l'ordre des contraintes
	-- d'intégrité référentielle
	INSERT INTO personne (id_personne, nom, prenom, email, no_rue, rue, code_postal, ville, pays, mot_de_passe, no_tel, photo, token, est_valide) VALUES
	(1, 'Leto','Elias','leto@yahoo.com','12','rue du chat','75001','Paris','France','Jesuisunchat123!', 'plop', 'plop', 'plop' ,1),
	(2, 'Yuuki', 'Gertrude', 'gyuuki@wanadoo.fr', '16', 'avenue des papillons', '69000', 'Lyon', 'France', 'Jaimelaneige345?', 'plop', 'plop', 'plop' ,1),
	(3, 'Scout', 'Luke', 'vader@lycos.fr', '29', 'boulevard du tank', '89000', 'Auxerre', 'France', 'Jesuistonpère99/', 'plop', 'plop', 'plop' ,1),
	(4, 'Bond', 'Julie', 'pasjamesbond@free.fr', '5', 'chemin de la voiture', '89100', 'Sens', 'France', 'Jesuis1espion!', 'plop', 'plop', 'plop' ,1),
	(5, 'Loganlapointe', 'Bob', 'bob@viril.com', '26','rue des chats', '75029', 'Meow', 'Irlande', 'Jaimeleschats777-', 'plop', 'plop', 'plop' ,1),
	(6, 'Titteh', 'Serena', 'des@tits.com', '8', 'chemin des voisins', '59000', 'Lille', 'France', 'adri1cestpourTOI?', 'plop', 'plop', 'plop' ,1),
	(7, 'Luc', 'Lana', 'blblblbl@essef.com', '18','avenue du derrière', '14360', 'Trouville', 'Thailande', 'Jaimelestomates888!', 'plop', 'plop', 'plop' ,1),
	(8, 'Dicoco', 'Margeriti', 'bgpenne@caramail.fr', '69', 'boulevard du chemin', '40100', 'Bologne', 'Italie', '+grandde14cm', 'plop', 'plop', 'plop' ,1);

	INSERT INTO formateur (id_personne) VALUES
    (7),
    (8);
    
	INSERT INTO etat_candidature (id_etat_candidature, libelle) VALUES
	(1, 'reçu'),
	(2, 'convoqué'),
	(3, 'accepté'),
	(4, 'refusé'),
	(5, 'liste d''attente'),
	(6, 'inscrit');

	INSERT INTO formation (id_formation, nom, description) VALUES 
	(1, 'BTS SIO', 'le bts de l''amour'),
	(2, 'BTS IRIS', 'la formation qu''il te faut !');

	INSERT INTO session_formation (id_session, id_formation, date_debut, date_fin, est_ouverte) VALUES 
	(1, 1, '2017-05-01', '2018-04-15', 1),
	(2, 1, '2016-06-01', '2017-05-08', 0),
	(3, 2, '2017-04-03', '2018-04-02', 1);
	
	INSERT INTO candidature (id_personne, id_session, id_etat_candidature, date_effet) VALUES 
	(1, 1, 1, '2018-01-15'), 
        (2, 1, 2, '2018-01-18'), 
        (3, 1, 3, '2018-02-03'),
        (4, 1, 4, '2018-02-05'),
        (5, 1, 5, '2018-02-25'),
        (6, 1, 6, '2018-02-26'),
        (1, 3, 4, '2018-02-26');
    COMMIT;
  END;
END $$

CALL agriotes2017_reset() $$	personne