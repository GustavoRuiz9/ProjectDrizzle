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
/* DROP FOREIGN KEYS                                                              */
/*================================================================================*/

ALTER TABLE NEIGHBORHOOD DROP FOREIGN KEY FK_NEIGHBORHOOD_POINT_CARDINAL;

ALTER TABLE NEIGHBORHOOD DROP FOREIGN KEY FK_NEIGHBORHOOD_COMMUNE;

ALTER TABLE UBICATION DROP FOREIGN KEY FK_UBICATION_NEIGHBORHOOD;

ALTER TABLE PROFILE DROP FOREIGN KEY FK_PROFILE_ACCOUNT;

ALTER TABLE PROFILE DROP FOREIGN KEY FK_PROFILE_UBICATION;

ALTER TABLE PUBLICATION (MONGO) DROP FOREIGN KEY FK_PUBLICATION_WEATHER;

ALTER TABLE PUBLICATION (MONGO) DROP FOREIGN KEY FK_PUBLICATION_PROFILE;

ALTER TABLE COMMENTARY (MONGO) DROP FOREIGN KEY FK_COMMENTARY_PUBLICATION;

ALTER TABLE COMMENTARY (MONGO) DROP FOREIGN KEY FK_COMMENTARY_PROFILE;

/*================================================================================*/
/* DROP TABLES                                                                    */
/*================================================================================*/

ALTER TABLE ACCOUNT DROP PRIMARY KEY;

DROP TABLE ACCOUNT;

ALTER TABLE NEIGHBORHOOD DROP PRIMARY KEY;

DROP TABLE NEIGHBORHOOD;

ALTER TABLE UBICATION DROP PRIMARY KEY;

DROP TABLE UBICATION;

ALTER TABLE PROFILE DROP PRIMARY KEY;

DROP TABLE PROFILE;

ALTER TABLE WEATHER DROP PRIMARY KEY;

DROP TABLE WEATHER;

ALTER TABLE PUBLICATION (MONGO) DROP PRIMARY KEY;

DROP TABLE PUBLICATION (MONGO);

ALTER TABLE COMMENTARY (MONGO) DROP PRIMARY KEY;

DROP TABLE COMMENTARY (MONGO);

ALTER TABLE COMMUNE DROP PRIMARY KEY;

DROP TABLE COMMUNE;

ALTER TABLE POINT_CARDINAL DROP PRIMARY KEY;

DROP TABLE POINT_CARDINAL;
