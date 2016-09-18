/*================================================================================*/
/* DDL SCRIPT                                                                     */
/*================================================================================*/
/*  Title    :                                                                    */
/*  FileName : Model BD Drizzle v-01.ecm                                          */
/*  Platform : MySQL 5                                                            */
/*  Version  : Concept                                                            */
/*  Date     : Jueves, 15 de Septiembre de 2016                                   */
/*================================================================================*/
/*================================================================================*/
/* CREATE TABLES                                                                  */
/*================================================================================*/

CREATE TABLE ACCOUNT (
  id_account VARCHAR(40) NOT NULL,
  name VARCHAR(40) NOT NULL,
  last_name VARCHAR(40) NOT NULL,
  email VARCHAR(40) NOT NULL,
  password VARCHAR(40) NOT NULL,
  /*user VARCHAR(40),*/
  birth_date VARCHAR(40) NOT NULL,
  number_phone VARCHAR(40),
  CONSTRAINT PK_ACCOUNT PRIMARY KEY (id_account)
);

CREATE TABLE NEIGHBORHOOD (
  id_neighborhood VARCHAR(40) NOT NULL,
  name VARCHAR(40) NOT NULL,
  commune VARCHAR(40) NOT NULL,
  point_cardinal VARCHAR(40) NOT NULL,
  limite_a VARCHAR(40) NOT NULL,
  limite_b VARCHAR(40) NOT NULL,
  CONSTRAINT PK_NEIGHBORHOOD PRIMARY KEY (id_neighborhood),
  CONSTRAINT FK_NEIGHBORHOOD_POINT_CARDINAL FOREIGN KEY (point_cardinal) REFERENCES POINT_CARDINAL (id_point_cardinal),
  CONSTRAINT FK_NEIGHBORHOOD_COMMUNE FOREIGN KEY (commune) REFERENCES COMMUNE (id_commune)
);

CREATE TABLE UBICATION (
  id_ubication VARCHAR(40) NOT NULL,
  neighborhood VARCHAR(40) NOT NULL,
  CONSTRAINT PK_UBICATION PRIMARY KEY (id_ubication),
  CONSTRAINT FK_UBICATION_NEIGHBORHOOD FOREIGN KEY (neighborhood) REFERENCES NEIGHBORHOOD (id_neighborhood)
);

CREATE TABLE PROFILE (
  profile_account VARCHAR(40) NOT NULL,
  photo BLOB,
  ubication VARCHAR(40) NOT NULL,
  auto_ubication BOOL NOT NULL,
  status BOOL NOT NULL,
  reputation NUMERIC NOT NULL,
  CONSTRAINT PK_PROFILE PRIMARY KEY (profile_account),
  CONSTRAINT FK_PROFILE_ACCOUNT FOREIGN KEY (profile_account) REFERENCES ACCOUNT (id_account),
  CONSTRAINT FK_PROFILE_UBICATION FOREIGN KEY (ubication) REFERENCES UBICATION (id_ubication)
);

CREATE TABLE WEATHER (
  id_weather VARCHAR(40) NOT NULL,
  name VARCHAR(40) NOT NULL,
  description VARCHAR(40) NOT NULL,
  type NUMERIC NOT NULL,
  CONSTRAINT PK_WEATHER PRIMARY KEY (id_weather)
);

CREATE TABLE PUBLICATION (MONGO) (
  id_publication NUMERIC AUTO_INCREMENT NOT NULL,
  date DATE NOT NULL,
  author VARCHAR(40) NOT NULL,
  weather VARCHAR(40) NOT NULL,
  CONSTRAINT PK_PUBLICATION (MONGO) PRIMARY KEY (id_publication),
  CONSTRAINT FK_PUBLICATION_WEATHER FOREIGN KEY (weather) REFERENCES WEATHER (id_weather),
  CONSTRAINT FK_PUBLICATION_PROFILE FOREIGN KEY (author) REFERENCES PROFILE (profile_account)
);

CREATE TABLE COMMENTARY (MONGO) (
  id_commentary VARCHAR(40) NOT NULL,
  date VARCHAR(40),
  publication NUMERIC NOT NULL,
  author VARCHAR(40) NOT NULL,
  CONSTRAINT PK_COMMENTARY (MONGO) PRIMARY KEY (id_commentary),
  CONSTRAINT FK_COMMENTARY_PUBLICATION FOREIGN KEY (publication) REFERENCES PUBLICATION (MONGO) (id_publication),
  CONSTRAINT FK_COMMENTARY_PROFILE FOREIGN KEY (author) REFERENCES PROFILE (profile_account)
);

CREATE TABLE COMMUNE (
  id_commune VARCHAR(40) NOT NULL,
  name VARCHAR(40) NOT NULL,
  limite_a VARCHAR(40),
  limite_b VARCHAR(40),
  CONSTRAINT PK_COMMUNE PRIMARY KEY (id_commune)
);

CREATE TABLE POINT_CARDINAL (
  id_point_cardinal VARCHAR(40) NOT NULL,
  name VARCHAR(40),
  CONSTRAINT PK_POINT_CARDINAL PRIMARY KEY (id_point_cardinal)
);
