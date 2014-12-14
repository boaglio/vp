# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table termo (
  id_termo                  bigint auto_increment not null,
  ds_original               varchar(255),
  ds_traduzido              varchar(255),
  ds_contexto               varchar(255),
  constraint pk_termo primary key (id_termo))
;

create table usuario (
  id_usuario                bigint auto_increment not null,
  nm_usuario                varchar(255),
  ds_senha                  varchar(255),
  constraint pk_usuario primary key (id_usuario))
;




# --- !Downs

SET FOREIGN_KEY_CHECKS=0;

drop table termo;

drop table usuario;

SET FOREIGN_KEY_CHECKS=1;

