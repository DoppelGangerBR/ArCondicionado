# ArCondicionado

SQL PARA CRIAÇÃO DO BANCO:

CREATE SEQUENCE public."ArCondicionado_id_ar_seq"
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE public."ArCondicionado_id_ar_seq"
  OWNER TO postgres;
  
CREATE SEQUENCE public.salas_id_sala_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE public.salas_id_sala_seq
  OWNER TO postgres;

CREATE SEQUENCE public.usuarios_id_usuario_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 2
  CACHE 1;
ALTER TABLE public.usuarios_id_usuario_seq
  OWNER TO postgres;

CREATE TABLE public.ar_condicionado
(
  temperatura integer,
  turbo boolean,
  modo character varying,
  ventilacao integer,
  auto_blowing boolean,
  id_ar integer NOT NULL DEFAULT nextval('"ArCondicionado_id_ar_seq"'::regclass),
  CONSTRAINT "ArCondicionado_pkey" PRIMARY KEY (id_ar)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.ar_condicionado
  OWNER TO postgres;
  
  
  CREATE TABLE public.associadosassalas
(
  id_salapk integer,
  id_usuariopk integer,
  id_associados integer NOT NULL,
  CONSTRAINT associadosassalas_pkey PRIMARY KEY (id_associados)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.associadosassalas
  OWNER TO postgres;
  
  
  CREATE TABLE public.salas
(
  numero_sala integer,
  bloco character varying,
  id_sala integer NOT NULL DEFAULT nextval('salas_id_sala_seq'::regclass),
  ip_arduino character varying,
  CONSTRAINT salas_pkey PRIMARY KEY (id_sala)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.salas
  OWNER TO postgres;
  
  CREATE TABLE public.usuarios
(
  nome character varying,
  nivel_acesso integer,
  login character varying,
  senha character varying,
  id_usuario integer NOT NULL DEFAULT nextval('usuarios_id_usuario_seq'::regclass),
  CONSTRAINT usuarios_pkey PRIMARY KEY (id_usuario)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.usuarios
  OWNER TO postgres;
