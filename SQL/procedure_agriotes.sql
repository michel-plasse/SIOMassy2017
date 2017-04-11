DELIMITER $$

DROP PROCEDURE IF EXISTS agriotes2017_reset $$

CREATE PROCEDURE `agriotes2017_reset`()
BEGIN
  -- Désactiver contraintes de clé étrangère
  SET FOREIGN_KEY_CHECKS = 0;
  -- Vider les tables en repositionnant à 1 leur auto-incrément
  TRUNCATE TABLE candidature;
  TRUNCATE TABLE etat_candidature;
  TRUNCATE TABLE formation;
  TRUNCATE TABLE formateur;
  TRUNCATE TABLE projet;
  TRUNCATE TABLE equipe;
  TRUNCATE TABLE membre_equipe;
  TRUNCATE TABLE personne;
  TRUNCATE TABLE session_formation;
  TRUNCATE TABLE module;
  TRUNCATE TABLE evaluation;
  TRUNCATE TABLE note;
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
	(1, 'Leto', 'Elias', 'leto@yahoo.com', '12', 'rue du chat', '75001', 'Paris', 'France', 'Jesuisunchat123!', 'plop', 'Leto_Elias.jpg', 'plop', 1),
	(2, 'Yuuki', 'Gertrude', 'gyuuki@wanadoo.fr', '16', 'avenue des papillons', '69000', 'Lyon', 'France', 'Jaimelaneige345?', 'plop', 'Yuuki_Gertrude.jpg', 'plop', 1),
	(3, 'Scout', 'Luke', 'vader@lycos.fr', '29', 'boulevard du tank', '89000', 'Auxerre', 'France', 'Jesuistonpère99/', 'plop', 'Scout_Luke.jpg', 'plop', 1),
	(4, 'Bond', 'Julie', 'pasjamesbond@free.fr', '5', 'chemin de la voiture', '89100', 'Sens', 'France', 'Jesuis1espion!', 'plop', 'Bond_Julie.jpg', 'plop', 1),
	(5, 'Loganlapointe', 'Bob', 'bob@viril.com', '26', 'rue des chats', '75029', 'Meow', 'Irlande', 'Jaimeleschats777-', 'plop', 'Loganlapointe_Bob.jpg', 'plop', 1),
	(6, 'Titteh', 'Serena', 'des@tits.com', '8', 'chemin des voisins', '59000', 'Lille', 'France', 'adri1cestpourTOI?', 'plop', 'Titteh_Serena.jpg', 'plop', 1),
	(7, 'Luc', 'Lana', 'blblblbl@essef.com', '18', 'avenue du derrière', '14360', 'Trouville', 'Thailande', 'Jaimelestomates888!', 'plop', 'Luc_Lana.jpg', 'plop', 1),
	(8, 'Dicoco', 'Margeriti', 'bgpenne@caramail.fr', '69', 'boulevard du chemin', '40100', 'Bologne', 'Italie', '+grandde14cm', 'plop', 'Dicoco_Margeriti.jpg', 'plop', 1),
	(10, 'Aaron', 'Castillo', 'acastillo0@google.com.br', '15', 'Golf Course', '5603', 'Dagupan', 'Philippines', 'aze123', '0256356584', 'Yuuki_Gertrude.jpg', '0', 1),
	(11, 'Roy', 'Jacobs', 'rjacobs1@webnode.com', '72703', 'Macpherson', '', 'Zhamog', 'China', 'aze123', '0256356584', 'Yuuki_Gertrude.jpg', '0', 1),
	(12, 'Brian', 'Harris', 'bharris2@wisc.edu', '896', 'Delaware', '37-12', 'Husów', 'Poland', 'aze123', '0256356584', 'Yuuki_Gertrude.jpg', '0', 1),
	(13, 'Laura', 'Marshall', 'lmarshall3@goodreads.com', '7', 'Susan', '78840', 'Campo Verde', 'Brazil', 'aze123', '0256356584', 'Yuuki_Gertrude.jpg', '0', 1),
	(14, 'Barbara', 'Smith', 'bsmith4@aol.com', '164', 'Fair Oaks', '', 'Zhudian', 'China', 'aze123', '0256356584', 'Yuuki_Gertrude.jpg', '0', 1),
	(15, 'Frank', 'Frazier', 'ffrazier5@state.gov', '002', 'Lukken', '69902', 'Lyon', 'France', 'aze123', '0256356584', 'Yuuki_Gertrude.jpg', '0', 1),
	(16, 'Christine', 'Oliver', 'coliver6@plala.or.jp', '191', 'Florence', '90141', 'Palermo', 'Italy', 'aze123', '0256356584', 'Yuuki_Gertrude.jpg', '0', 1),
	(17, 'Aaron', 'Johnson', 'ajohnson7@webmd.com', '4', 'Montana', '79400', 'Coxim', 'Brazil', 'aze123', '0256356584', 'Yuuki_Gertrude.jpg', '0', 1),
	(18, 'Louis', 'Holmes', 'lholmes8@yolasite.com', '517', 'Little Fleur', '', 'Martakert', 'Azerbaijan', 'aze123', '0256356584', 'Yuuki_Gertrude.jpg', '0', 1),
	(19, 'Todd', 'Stewart', 'tstewart9@devhub.com', '5', 'Weeping Birch', '', 'Tacuatí', 'Paraguay', 'aze123', '0256356584', 'Yuuki_Gertrude.jpg', '0', 1),
	(20, 'Johnny', 'Richards', 'jrichardsa@tuttocitta.it', '58199', 'Oak', '3776', 'Vidin', 'Bulgaria', 'aze123', '0256356584', 'Yuuki_Gertrude.jpg', '0', 1),
	(21, 'Shirley', 'Wallace', 'swallaceb@e-recht24.de', '945', 'Walton', '9401', 'Saguing', 'Philippines', 'aze123', '0256356584', 'Yuuki_Gertrude.jpg', '0', 1),
	(22, 'Jean', 'Simpson', 'jsimpsonc@tripadvisor.com', '6278', 'Rigney', '319-0', 'Mito-shi', 'Japan', 'aze123', '0256356584', 'Yuuki_Gertrude.jpg', '0', 1),
	(23, 'Kathy', 'Perry', 'kperryd@japanpost.jp', '8', 'Fremont', '671 6', 'Čejkovice', 'Czech Republic', 'aze123', '0256356584', 'Yuuki_Gertrude.jpg', '0', 1),
	(24, 'Andrea', 'Fox', 'afoxe@livejournal.com', '89', 'Heffernan', '', 'Puerto Francisco de Orellana', 'Ecuador', 'aze123', '0256356584', 'Yuuki_Gertrude.jpg', '0', 1),
	(25, 'Lisa', 'Banks', 'lbanksf@hugedomains.com', '5298', 'East', '', 'Chervonopartyzans’k', 'Ukraine', 'aze123', '0256356584', 'Yuuki_Gertrude.jpg', '0', 1),
	(26, 'Aaron', 'Morris', 'amorrisg@webnode.com', '721', 'Corry', '', 'Gogosuket', 'Indonesia', 'aze123', '0256356584', 'Yuuki_Gertrude.jpg', '0', 1),
	(27, 'Andrew', 'Johnson', 'ajohnsonh@dot.gov', '2', 'Valley Edge', '', 'To’rtko’l Shahri', 'Uzbekistan', 'aze123', '0256356584', 'Yuuki_Gertrude.jpg', '0', 1),
	(28, 'Michael', 'Black', 'mblacki@ftc.gov', '8', 'Clarendon', '', 'Mengxi', 'China', 'aze123', '0256356584', 'Yuuki_Gertrude.jpg', '0', 1),
	(29, 'Roger', 'Gilbert', 'rgilbertj@howstuffworks.com', '53', 'Norway Maple', '73250', 'Cajamarca', 'Colombia', 'aze123', '0256356584', 'Yuuki_Gertrude.jpg', '0', 1),
	(30, 'Phillip', 'Sanders', 'psandersk@merriam-webster.com', '77941', 'Sauthoff', '', 'Kuaidamao', 'China', 'aze123', '0256356584', 'Yuuki_Gertrude.jpg', '0', 1),
	(31, 'Billy', 'Nichols', 'bnicholsl@tmall.com', '29', 'Hudson', '', 'Kiuteta', 'Indonesia', 'aze123', '0256356584', 'Yuuki_Gertrude.jpg', '0', 1),
	(32, 'Stephanie', 'Montgomery', 'smontgomerym@narod.ru', '43', 'Magdeline', '4950-', 'Lavandeira', 'Portugal', 'aze123', '0256356584', 'Yuuki_Gertrude.jpg', '0', 1),
	(33, 'Martin', 'Gonzales', 'mgonzalesn@ox.ac.uk', '14469', 'Sheridan', '', 'Dolod', 'China', 'aze123', '0256356584', 'Yuuki_Gertrude.jpg', '0', 1),
	(34, 'Martin', 'Henry', 'mhenryo@paginegialle.it', '597', 'Talisman', '94039', 'Créteil', 'France', 'aze123', '0256356584', 'Yuuki_Gertrude.jpg', '0', 1),
	(35, 'Norma', 'Allen', 'nallenp@unesco.org', '7077', 'Fisk', '', 'Tanjungsari Barat', 'Indonesia', 'aze123', '0256356584', 'Yuuki_Gertrude.jpg', '0', 1),
	(36, 'Peter', 'Parker', 'pparkerq@spotify.com', '3084', 'Eliot', '3540', 'Villa Ángela', 'Argentina', 'aze123', '0256356584', 'Yuuki_Gertrude.jpg', '0', 1),
	(37, 'Russell', 'Gray', 'rgrayr@loc.gov', '3177', 'Forest', '', 'Kalapadua', 'Indonesia', 'aze123', '0256356584', 'Yuuki_Gertrude.jpg', '0', 1),
	(38, 'Frank', 'Evans', 'fevanss@time.com', '320', 'Pearson', '62350', 'Baraba', 'Russia', 'aze123', '0256356584', 'Yuuki_Gertrude.jpg', '0', 1),
	(39, 'Eugene', 'Arnold', 'earnoldt@delicious.com', '0', 'Starling', '40000', 'Šenkovec', 'Croatia', 'aze123', '0256356584', 'Yuuki_Gertrude.jpg', '0', 1),
	(40, 'Doris', 'Lawrence', 'dlawrenceu@flavors.me', '76', 'Gateway', '65805', 'Sannikovo', 'Russia', 'aze123', '0256356584', 'Yuuki_Gertrude.jpg', '0', 1),
	(41, 'Fred', 'Perez', 'fperezv@acquirethisname.com', '99499', 'Merry', '', 'Zehak', 'Iran', 'aze123', '0256356584', 'Yuuki_Gertrude.jpg', '0', 1),
	(42, 'Shirley', 'Cox', 'scoxw@surveymonkey.com', '2', 'Evergreen', '35301', 'Orivesi', 'Finland', 'aze123', '0256356584', 'Yuuki_Gertrude.jpg', '0', 1),
	(43, 'Tina', 'Mills', 'tmillsx@youku.com', '0', 'Sunbrook', '4765-', 'Delães', 'Portugal', 'aze123', '0256356584', 'Yuuki_Gertrude.jpg', '0', 1),
	(44, 'Deborah', 'Gibson', 'dgibsony@cisco.com', '53038', 'Melrose', '', 'Jiulong', 'China', 'aze123', '0256356584', 'Yuuki_Gertrude.jpg', '0', 1),
	(45, 'Brandon', 'Lynch', 'blynchz@delicious.com', '18', 'Prairieview', '', 'Qingban', 'China', 'aze123', '0256356584', 'Yuuki_Gertrude.jpg', '0', 1),
	(46, 'Jose', 'Taylor', 'jtaylor10@usgs.gov', '5489', 'Blaine', '75669', 'Paris 14', 'France', 'aze123', '0256356584', 'Yuuki_Gertrude.jpg', '0', 1),
	(47, 'Harold', 'Harris', 'hharris11@netvibes.com', '64', 'Bluestem', '894-2', 'Miura', 'Japan', 'aze123', '0256356584', 'Yuuki_Gertrude.jpg', '0', 1),
	(48, 'Russell', 'Howard', 'rhoward12@slashdot.org', '12', 'Kenwood', '6413', 'Molde', 'Norway', 'aze123', '0256356584', 'Yuuki_Gertrude.jpg', '0', 1),
	(49, 'Ronald', 'Richardson', 'rrichardson13@imgur.com', '51970', 'Bellgrove', '30925', 'Novaya Tavolzhanka', 'Russia', 'aze123', '0256356584', 'Yuuki_Gertrude.jpg', '0', 1),
	(50, 'David', 'Cole', 'dcole14@wp.com', '865', 'Sullivan', '32-08', 'Wielka Wieś', 'Poland', 'aze123', '0256356584', 'Yuuki_Gertrude.jpg', '0', 1),
	(51, 'Victor', 'Flores', 'vflores15@woothemes.com', '157', 'Mayfield', '', 'Kwakoa', 'Tanzania', 'aze123', '0256356584', 'Yuuki_Gertrude.jpg', '0', 1),
	(52, 'Earl', 'Griffin', 'egriffin16@nifty.com', '1987', 'Golf View', '', 'Oropéndolas', 'Honduras', 'aze123', '0256356584', 'Yuuki_Gertrude.jpg', '0', 1),
	(53, 'Eugene', 'Harper', 'eharper17@arstechnica.com', '8', 'Jana', '', 'Mbanga', 'Cameroon', 'aze123', '0256356584', 'Yuuki_Gertrude.jpg', '0', 1),
	(54, 'Angela', 'Mitchell', 'amitchell18@ycombinator.com', '0', 'Packers', '', 'Chenzui', 'China', 'aze123', '0256356584', 'Yuuki_Gertrude.jpg', '0', 1),
	(55, 'Lois', 'Jones', 'ljones19@51.la', '4', 'Anderson', '', 'Sidomulyo', 'Indonesia', 'aze123', '0256356584', 'Yuuki_Gertrude.jpg', '0', 1),
	(56, 'Katherine', 'Harrison', 'kharrison1a@europa.eu', '6', 'Anderson', '', 'Mērsrags', 'Latvia', 'aze123', '0256356584', 'Yuuki_Gertrude.jpg', '0', 1),
	(57, 'Steve', 'Bradley', 'sbradley1b@ustream.tv', '45254', 'Oak Valley', '', 'Dayuanhuizu', 'China', 'aze123', '0256356584', 'Yuuki_Gertrude.jpg', '0', 1),
	(58, 'Wanda', 'Russell', 'wrussell1c@dell.com', '499', 'Havey', '', 'Ganhe', 'China', 'aze123', '0256356584', 'Yuuki_Gertrude.jpg', '0', 1),
	(59, 'Catherine', 'Wright', 'cwright1d@chronoengine.com', '2', 'Meadow Ridge', '08-10', 'Przesmyki', 'Poland', 'aze123', '0256356584', 'Yuuki_Gertrude.jpg', '0', 1),
	(60, 'Teresa', 'Edwards', 'tedwards1e@wordpress.org', '9197', 'Arizona', '36151', 'Nizhniy Kurkuzhin', 'Russia', 'aze123', '0256356584', 'Yuuki_Gertrude.jpg', '0', 1),
	(61, 'Jeremy', 'Wood', 'jwood1f@devhub.com', '05104', 'Boyd', 'F52', 'Boyle', 'Ireland', 'aze123', '0256356584', 'Yuuki_Gertrude.jpg', '0', 1),
	(62, 'Todd', 'Clark', 'tclark1g@economist.com', '17246', 'Warbler', '92127', 'San Diego', 'United States', 'aze123', '0256356584', 'Yuuki_Gertrude.jpg', '0', 1),
	(63, 'Tammy', 'Martinez', 'tmartinez1h@sina.com.cn', '69', 'Myrtle', '', 'As Suwaydā’', 'Syria', 'aze123', '0256356584', 'Yuuki_Gertrude.jpg', '0', 1),
	(64, 'Karen', 'Tucker', 'ktucker1i@wp.com', '78227', 'Hintze', '2820', 'Gualeguaychú', 'Argentina', 'aze123', '0256356584', 'Yuuki_Gertrude.jpg', '0', 1),
	(65, 'Lawrence', 'Hawkins', 'lhawkins1j@guardian.co.uk', '99293', 'Toban', '', 'Kebonan', 'Indonesia', 'aze123', '0256356584', 'Yuuki_Gertrude.jpg', '0', 1),
	(66, 'Scott', 'Sanders', 'ssanders1k@whitehouse.gov', '9', 'Monterey', '', 'Jiujie', 'China', 'aze123', '0256356584', 'Yuuki_Gertrude.jpg', '0', 1),
	(67, 'Sarah', 'Williams', 'swilliams1l@disqus.com', '325', 'Judy', '', 'Banjar Penyalin', 'Indonesia', 'aze123', '0256356584', 'Yuuki_Gertrude.jpg', '0', 1),
	(68, 'Nicholas', 'Ramos', 'nramos1m@google.cn', '9101', 'Sunfield', '', 'Bambor', 'Indonesia', 'aze123', '0256356584', 'Yuuki_Gertrude.jpg', '0', 1),
	(69, 'Joseph', 'Boyd', 'jboyd1n@dion.ne.jp', '5088', 'Vernon', '46801', 'Petaling Jaya', 'Malaysia', 'aze123', '0256356584', 'Yuuki_Gertrude.jpg', '0', 1),
	(70, 'Martha', 'Watkins', 'mwatkins1o@flickr.com', '2709', 'Kenwood', 'J7A', 'Lanigan', 'Canada', 'aze123', '0256356584', 'Yuuki_Gertrude.jpg', '0', 1),
	(71, 'Stephen', 'Hayes', 'shayes1p@webeden.co.uk', '3', 'Grover', '36280', 'Carandaí', 'Brazil', 'aze123', '0256356584', 'Yuuki_Gertrude.jpg', '0', 1),
	(72, 'Alan', 'Garcia', 'agarcia1q@qq.com', '2199', 'Oak Valley', '', 'Upper Hell''s Gate', 'Bonaire, Saint Eustatius and Saba ', 'aze123', '0256356584', 'Yuuki_Gertrude.jpg', '0', 1),
	(73, 'Alan', 'Knight', 'aknight1r@ovh.net', '6704', 'School', '118 6', 'Stockholm', 'Sweden', 'aze123', '0256356584', 'Yuuki_Gertrude.jpg', '0', 1),
	(74, 'Linda', 'Davis', 'ldavis1s@com.com', '415', 'Old Gate', '687 0', 'Buchlovice', 'Czech Republic', 'aze123', '0256356584', 'Yuuki_Gertrude.jpg', '0', 1),
	(75, 'Mildred', 'Reed', 'mreed1t@dedecms.com', '363', '2nd', '', 'Bajiao', 'China', 'aze123', '0256356584', 'Yuuki_Gertrude.jpg', '0', 1),
	(76, 'Howard', 'Crawford', 'hcrawford1u@samsung.com', '13759', 'Bartillon', '', 'Simajia', 'China', 'aze123', '0256356584', 'Yuuki_Gertrude.jpg', '0', 1),
	(77, 'Kevin', 'Powell', 'kpowell1v@engadget.com', '2562', 'Alpine', '', 'Jiangti', 'China', 'aze123', '0256356584', 'Yuuki_Gertrude.jpg', '0', 1),
	(78, 'Ann', 'Burns', 'aburns1w@phpbb.com', '64152', 'Logan', '', 'Zaoshi', 'China', 'aze123', '0256356584', 'Yuuki_Gertrude.jpg', '0', 1),
	(79, 'Susan', 'Henderson', 'shenderson1x@nydailynews.com', '8', 'Sugar', 'V2X', 'Walnut Grove', 'Canada', 'aze123', '0256356584', 'Yuuki_Gertrude.jpg', '0', 1),
	(80, 'Nicholas', 'Carpenter', 'ncarpenter1y@paypal.com', '66088', 'Sommers', '2418', 'Elverum', 'Norway', 'aze123', '0256356584', 'Yuuki_Gertrude.jpg', '0', 1),
	(81, 'Cheryl', 'Wells', 'cwells1z@utexas.edu', '0128', 'Sutteridge', '17352', 'Pankovka', 'Russia', 'aze123', '0256356584', 'Yuuki_Gertrude.jpg', '0', 1),
	(82, 'Matthew', 'Hanson', 'mhanson20@cloudflare.com', '27942', 'Carioca', '32868', 'Orlando', 'United States', 'aze123', '0256356584', 'Yuuki_Gertrude.jpg', '0', 1),
	(83, 'Bobby', 'Fox', 'bfox21@prnewswire.com', '12', 'Schlimgen', '3405-', 'Bobadela', 'Portugal', 'aze123', '0256356584', 'Yuuki_Gertrude.jpg', '0', 1),
	(84, 'Ryan', 'Howell', 'rhowell22@si.edu', '8', 'Bobwhite', '16370', 'Promissão', 'Brazil', 'aze123', '0256356584', 'Yuuki_Gertrude.jpg', '0', 1),
	(85, 'Christine', 'Dunn', 'cdunn23@flavors.me', '98', 'Hauk', '', 'Bibis', 'Indonesia', 'aze123', '0256356584', 'Yuuki_Gertrude.jpg', '0', 1),
	(86, 'Douglas', 'Mcdonald', 'dmcdonald24@usa.gov', '6677', 'Ryan', '', 'Bijaepasu', 'Indonesia', 'aze123', '0256356584', 'Yuuki_Gertrude.jpg', '0', 1),
	(87, 'Annie', 'Gardner', 'agardner25@cisco.com', '7', 'Harbort', '', 'Shiraz', 'Iran', 'aze123', '0256356584', 'Yuuki_Gertrude.jpg', '0', 1),
	(88, 'Eric', 'Lewis', 'elewis26@wsj.com', '06737', 'Oak Valley', '34664', 'Krasnomayskiy', 'Russia', 'aze123', '0256356584', 'Yuuki_Gertrude.jpg', '0', 1),
	(89, 'Juan', 'Peterson', 'jpeterson27@diigo.com', '5', 'Clove', '', 'Banjar Swastika', 'Indonesia', 'aze123', '0256356584', 'Yuuki_Gertrude.jpg', '0', 1),
	(90, 'Donald', 'Day', 'dday28@icio.us', '93', 'Waubesa', '', 'Yeosu', 'South Korea', 'aze123', '0256356584', 'Yuuki_Gertrude.jpg', '0', 1),
	(91, 'Lillian', 'Reid', 'lreid29@ucla.edu', '0422', 'Dwight', '', 'Huoshaoping', 'China', 'aze123', '0256356584', 'Yuuki_Gertrude.jpg', '0', 1),
	(92, 'Jacqueline', 'Morgan', 'jmorgan2a@discovery.com', '4170', 'Redwing', '', 'Tân Châu', 'Vietnam', 'aze123', '0256356584', 'Yuuki_Gertrude.jpg', '0', 1),
	(93, 'Bonnie', 'Ellis', 'bellis2b@clickbank.net', '732', 'Surrey', '', 'Qingkenpao', 'China', 'aze123', '0256356584', 'Yuuki_Gertrude.jpg', '0', 1),
	(94, 'Timothy', 'Simpson', 'tsimpson2c@nymag.com', '60', 'Aberg', '', 'Dzüünkharaa', 'Mongolia', 'aze123', '0256356584', 'Yuuki_Gertrude.jpg', '0', 1),
	(95, 'Melissa', 'George', 'mgeorge2d@discovery.com', '013', 'Warrior', '3047', 'Drammen', 'Norway', 'aze123', '0256356584', 'Yuuki_Gertrude.jpg', '0', 1),
	(96, 'Carlos', 'Adams', 'cadams2e@ft.com', '5413', 'Ohio', '', 'Petite Rivière de Nippes', 'Haiti', 'aze123', '0256356584', 'Yuuki_Gertrude.jpg', '0', 1),
	(97, 'Irene', 'Hall', 'ihall2f@github.com', '4', 'Carberry', '23890', 'Seropédica', 'Brazil', 'aze123', '0256356584', 'Yuuki_Gertrude.jpg', '0', 1),
	(98, 'Annie', 'Clark', 'aclark2g@bravesites.com', '9', 'Bultman', '', 'Tianzishan', 'China', 'aze123', '0256356584', 'Yuuki_Gertrude.jpg', '0', 1),
	(99, 'Christine', 'Fox', 'cfox2h@goodreads.com', '8792', 'Lakewood Gardens', '', 'Gotputuk', 'Indonesia', 'aze123', '0256356584', 'Yuuki_Gertrude.jpg', '0', 1),
	(100, 'Karen', 'Roberts', 'kroberts2i@va.gov', '32', 'Manufacturers', '', 'Cihambali', 'Indonesia', 'aze123', '0256356584', 'Yuuki_Gertrude.jpg', '0', 1),
	(101, 'Sean', 'Dixon', 'sdixon2j@bing.com', '0638', 'Barnett', '375 0', 'Malá Strana', 'Czech Republic', 'aze123', '0256356584', 'Yuuki_Gertrude.jpg', '0', 1),
	(102, 'Charles', 'Day', 'cday2k@netlog.com', '0239', 'Dawn', '08-22', 'Sarnaki', 'Poland', 'aze123', '0256356584', 'Yuuki_Gertrude.jpg', '0', 1),
	(103, 'Laura', 'Medina', 'lmedina2l@google.ca', '9', 'Fair Oaks', '2350-', 'Lapas', 'Portugal', 'aze123', '0256356584', 'Yuuki_Gertrude.jpg', '0', 1),
	(104, 'Evelyn', 'Sims', 'esims2m@miibeian.gov.cn', '9818', 'Green Ridge', '', 'Ashtarak', 'Armenia', 'aze123', '0256356584', 'Yuuki_Gertrude.jpg', '0', 1),
	(105, 'Howard', 'Wells', 'hwells2n@squarespace.com', '326', 'Mallory', '', 'Cipasung', 'Indonesia', 'aze123', '0256356584', 'Yuuki_Gertrude.jpg', '0', 1),
	(106, 'Randy', 'Wheeler', 'rwheeler2o@nytimes.com', '80', 'Old Gate', '19099', 'Saint Petersburg', 'Russia', 'aze123', '0256356584', 'Yuuki_Gertrude.jpg', '0', 1),
	(107, 'Shirley', 'Crawford', 'scrawford2p@ca.gov', '3093', 'Roth', '62-70', 'Kawęczyn', 'Poland', 'aze123', '0256356584', 'Yuuki_Gertrude.jpg', '0', 1),
	(108, 'Rachel', 'Castillo', 'rcastillo2q@people.com.cn', '6286', 'Bashford', 'H9X', 'Notre-Dame-de-l''Île-Perrot', 'Canada', 'aze123', '0256356584', 'Yuuki_Gertrude.jpg', '0', 1),
	(109, 'Mildred', 'Wood', 'mwood2r@people.com.cn', '515', 'Thompson', '', 'Kigali', 'Rwanda', 'aze123', '0256356584', 'Yuuki_Gertrude.jpg', '0', 1);

	INSERT INTO formateur (id_personne) VALUES
    (7),(8),(12),(15);
    
	INSERT INTO etat_candidature (id_etat_candidature, libelle) VALUES
	(1, 'reçu'),
	(2, 'convoqué'),
	(3, 'accepté'),
	(4, 'refusé'),
	(5, 'liste d''attente'),
	(6, 'inscrit');

	INSERT INTO formation (id_formation, nom, description) VALUES 
	(1, 'BTS SIO', 'le bts de l''amour'),
	(2, 'BTS IRIS', 'la formation qu''il te faut !'),
    (3, 'BTS AG', 'Aller Cousin la vien fr la compta la'),
	(4, 'LICENCE CROUTE', 'Viens gouter mon miel'),
    (5, 'AVENIR MEULLEUR', 'Aller Cousin la vien fr la compta la'),
	(6, 'LECHEC', 'Viens gouter mon miel');

	INSERT INTO session_formation (id_session, id_formation, date_debut, date_fin, est_ouverte) VALUES 
	(1, 1, '2017-05-01', '2018-04-15', 1),
	(2, 1, '2016-06-01', '2017-05-08', 0),
	(3, 2, '2017-04-03', '2018-04-02', 1),
    (4, 1, '2016-06-01', '2017-05-08', 1),
	(5, 4, '2017-06-03', '2017-08-08', 1),
    (6, 2, '2017-07-03', '2018-05-02', 1),
    (7, 3, '2016-05-01', '2017-05-08', 1),
	(8, 4, '2017-04-03', '2018-04-02', 1);
    
    INSERT INTO projet (id_projet, id_formateur, id_session, sujet, description, date_limite, date_creation) VALUES
    (1,7,1,"on va tous crever", "bla bla bla", '2017-08-02','2017-04-03'),
    (2,7,1,"quittez le navire", "bla bla bla", '2017-08-02','2017-04-03'),
    (3,8,1,"on va tous crever", "bla bla bla", '2017-08-02','2017-04-03'),
    (4,8,1,"quittez le navire", "bla bla bla", '2017-08-02','2017-04-03');
    
    INSERT INTO equipe (id_equipe,id_createur,id_projet,date_creation) VALUES
    (1,1,1,'2017-04-03'),
    (2,2,1,'2017-04-03');
    
    INSERT INTO membre_equipe (id_equipe, id_personne) VALUES
    (1,3),
    (1,4),
    (1,5),
    (2,6);
	
	INSERT INTO candidature (id_personne, id_session, id_etat_candidature, date_effet) VALUES 
	(1, 1, 1, '2018-01-15 00:00:00'),
	(1, 3, 4, '2018-02-26 00:00:00'),
	(2, 1, 2, '2018-01-18 00:00:00'),
	(3, 1, 3, '2018-02-03 00:00:00'),
	(4, 1, 4, '2018-02-05 00:00:00'),
	(5, 1, 5, '2018-02-25 00:00:00'),
	(6, 1, 6, '2018-02-26 00:00:00'),
	(17, 1, 6, '2017-03-31 00:00:00'),
	(18, 1, 6, '2017-01-24 00:00:00'),
	(19, 2, 6, '2017-03-29 00:00:00'),
	(20, 1, 6, '2017-01-14 00:00:00'),
	(21, 1, 6, '2017-02-12 00:00:00'),
	(22, 2, 6, '2017-03-04 00:00:00'),
	(23, 1, 6, '2017-03-28 00:00:00'),
	(24, 1, 6, '2017-03-13 00:00:00'),
	(25, 3, 6, '2017-03-28 00:00:00'),
	(26, 1, 6, '2017-02-27 00:00:00'),
	(27, 1, 6, '2017-01-05 00:00:00'),
	(28, 3, 6, '2017-01-10 00:00:00'),
	(29, 4, 6, '2017-02-22 00:00:00'),
	(30, 1, 6, '2017-03-11 00:00:00'),
	(31, 2, 6, '2017-03-21 00:00:00'),
	(32, 2, 6, '2017-03-28 00:00:00'),
	(33, 2, 6, '2017-01-26 00:00:00'),
	(34, 3, 6, '2017-01-27 00:00:00'),
	(35, 1, 6, '2017-01-05 00:00:00'),
	(36, 1, 6, '2017-02-06 00:00:00'),
	(37, 3, 6, '2017-03-30 00:00:00'),
	(38, 1, 6, '2017-01-29 00:00:00'),
	(39, 2, 6, '2017-01-05 00:00:00'),
	(40, 3, 6, '2017-01-05 00:00:00'),
	(41, 3, 6, '2017-02-01 00:00:00'),
	(42, 4, 6, '2017-02-22 00:00:00'),
	(43, 3, 6, '2017-02-23 00:00:00'),
	(44, 3, 6, '2017-03-08 00:00:00'),
	(45, 3, 6, '2017-02-20 00:00:00'),
	(46, 1, 6, '2017-03-24 00:00:00'),
	(47, 3, 6, '2017-02-05 00:00:00'),
	(48, 1, 6, '2017-03-18 00:00:00'),
	(49, 3, 6, '2017-03-15 00:00:00'),
	(50, 1, 6, '2017-01-01 00:00:00'),
	(51, 1, 6, '2017-03-22 00:00:00'),
	(52, 3, 6, '2017-02-06 00:00:00'),
	(53, 4, 6, '2017-02-16 00:00:00'),
	(54, 2, 6, '2017-01-03 00:00:00'),
	(55, 1, 6, '2017-01-07 00:00:00'),
	(56, 1, 6, '2017-03-10 00:00:00'),
	(57, 4, 6, '2017-02-08 00:00:00'),
	(58, 2, 6, '2017-02-23 00:00:00'),
	(59, 2, 6, '2017-03-30 00:00:00'),
	(60, 1, 6, '2017-01-16 00:00:00'),
	(61, 4, 6, '2017-03-05 00:00:00'),
	(62, 1, 6, '2017-02-25 00:00:00'),
	(63, 1, 6, '2017-01-18 00:00:00'),
	(64, 3, 6, '2017-01-29 00:00:00'),
	(65, 4, 6, '2017-01-05 00:00:00'),
	(66, 3, 6, '2017-03-02 00:00:00');

        INSERT INTO module (id_module, nom) VALUES
	('1', 'Mathématiques'),
    ('2', 'Français'),
    ('3', 'Histoire'),
    ('4', 'Informatique');
        
	INSERT INTO evaluation (id_evaluation, id_module, id_formateur, id_session, intitule, date_effet) VALUES
    ('1', '1', '7', '1', 'Evaluation Maths', '2017-03-17 14:00:00'),
    ('2', '4', '7', '1', 'Informatique E4', '2017-05-18 09:30:00'),
    ('3', '2', '8', '1', 'Français Eval-1', '2017-04-08 08:30:00'),
    ('4', '3', '8', '1', 'Histoire DST', '2017-03-05 15:00:00');
    
    INSERT INTO note (id_note, id_evaluation, id_personne, commentaire) VALUES
    ('1', '2', '1', '','à venir.'),
    ('2', '1', '1', '15', 'Bon travail.'),
    ('3', '3', '1', '12', 'Assez bien.'),
    ('4', '4', '1', '8', 'Peut mieux faire.');
        
    COMMIT;
  END;
END$$