CREATE TABLE user_type
(
  user_type_id bigint NOT NULL,
  created timestamp without time zone,
  created_by character varying(255),
  updated timestamp without time zone,
  updated_by character varying(255),
  user_type_description character varying(255),
  user_type_name character varying(255),
  CONSTRAINT pk_user_type_id PRIMARY KEY (user_type_id),
  CONSTRAINT uk_user_type_name UNIQUE (user_type_name)
)
CREATE SEQUENCE user_type_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 2
  CACHE 1;
  ===================================
CREATE TABLE user_role
(
  role_id bigint NOT NULL,
  created timestamp without time zone,
  created_by character varying(255),
  updated timestamp without time zone,
  updated_by character varying(255),
  role_description character varying(255),
  role_name character varying(255),
  CONSTRAINT pk_role_id PRIMARY KEY (role_id),
  CONSTRAINT uk_role_name UNIQUE (role_name)
)
CREATE SEQUENCE user_role_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 3
  CACHE 1;
===========================================

CREATE TABLE app_permission
(
  app_permission_id bigint NOT NULL,
  created timestamp without time zone,
  created_by character varying(255),
  updated timestamp without time zone,
  updated_by character varying(255),
  is_read boolean,
  is_write boolean,
  ser_role_id bigint,
  CONSTRAINT pk_app_permission_id PRIMARY KEY (app_permission_id),
  CONSTRAINT fk_role_id FOREIGN KEY (ser_role_id)
      REFERENCES public.user_role (role_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)

CREATE SEQUENCE app_permission_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 3
  CACHE 1;
==================================================
CREATE TABLE users
(
  user_id bigint NOT NULL,
  registered_email character varying(255),
  first_name character varying(255),
  is_enabled boolean DEFAULT false,
  password character varying(255),
  user_type_id bigint,
  CONSTRAINT pk_users_id PRIMARY KEY (user_id),
  CONSTRAINT pk_user_type_id FOREIGN KEY (user_type_id)
      REFERENCES public.user_type (user_type_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT uk_registered_email UNIQUE (registered_email)
)
CREATE SEQUENCE user_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 2
  CACHE 1;
========================================================

CREATE TABLE country_ref
(
  country_id bigint NOT NULL,
  created timestamp without time zone,
  created_by character varying(255),
  updated timestamp without time zone,
  updated_by character varying(255),
  country_name character varying(255),
  CONSTRAINT country_ref_pkey PRIMARY KEY (country_id)
)

CREATE SEQUENCE country_ref_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
========================================================
CREATE TABLE state_ref
(
  state_id bigint NOT NULL,
  created timestamp without time zone,
  created_by character varying(255),
  updated timestamp without time zone,
  updated_by character varying(255),
  state_name character varying(255),
  country_id bigint,
  CONSTRAINT pk_state_id PRIMARY KEY (state_id),
  CONSTRAINT fk_country_id FOREIGN KEY (country_id)
      REFERENCES public.country_ref (country_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
CREATE SEQUENCE state_ref_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
=============================================================
CREATE TABLE city_ref
(
  city_id bigint NOT NULL,
  created timestamp without time zone,
  created_by character varying(255),
  updated timestamp without time zone,
  updated_by character varying(255),
  city_name character varying(255),
  state_id bigint,
  CONSTRAINT pk_city_id PRIMARY KEY (city_id),
  CONSTRAINT fk_state_id FOREIGN KEY (state_id)
      REFERENCES public.state_ref (state_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
CREATE SEQUENCE city_ref_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
  ==========================================================
