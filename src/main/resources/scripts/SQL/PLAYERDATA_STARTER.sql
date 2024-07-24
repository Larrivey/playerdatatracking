CREATE DATABASE playerdata;
-- DROP SCHEMA public;

--CREATE SCHEMA public AUTHORIZATION pg_database_owner;

-- DROP SEQUENCE public.manual_tracked_data_id_seq;

CREATE SEQUENCE public.manual_tracked_data_id_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 2147483647
	START 1
	CACHE 1
	NO CYCLE;-- public.manual_tracked_player definition

-- Drop table

-- DROP TABLE public.manual_tracked_player;

CREATE TABLE public.manual_tracked_player (
	id int8 NOT NULL DEFAULT nextval('manual_tracked_data_id_seq'::regclass),
	nombre varchar(255) NOT NULL,
	nota float8 NOT NULL,
	club varchar(255) NOT NULL,
	posicion varchar(255) NULL,
	most_like_destination varchar(255) NULL,
	likeable varchar(255) NULL,
	"date" date NULL,
	CONSTRAINT manual_tracked_data_nombre_key UNIQUE (nombre),
	CONSTRAINT manual_tracked_data_pkey PRIMARY KEY (id),
	CONSTRAINT uk83r8jckpgnch8asi2lxqec55u UNIQUE (nombre),
	CONSTRAINT ukir9985ps18y6788r096vgq39j UNIQUE (nombre)
);


-- public.player_qualities definition

-- Drop table

-- DROP TABLE public.player_qualities;

CREATE TABLE public.player_qualities (
	player_id int8 NOT NULL,
	quality varchar(255) NOT NULL,
	CONSTRAINT player_qualities_player_id_fkey FOREIGN KEY (player_id) REFERENCES public.manual_tracked_player(id)
);


ALTER TABLE public.player_qualities ADD CONSTRAINT player_qualities_player_id_fkey FOREIGN KEY (player_id) REFERENCES public.manual_tracked_player(id);




INSERT INTO public.manual_tracked_player (nombre,nota,club,posicion,most_like_destination,likeable,"date") VALUES
	 ('Juan Perez',8.5,'FC Barcelona','Delantero','Real Madrid','Sí','2023-07-23'),
	 ('Juana Perez',8.5,'FC Barcelona','Delantero','Real Madrid','Sí','2023-07-23');
INSERT INTO public.player_qualities (player_id,quality) VALUES
	 (1,'Rápido'),
	 (1,'Técnico'),
	 (2,'Rápido'),
	 (2,'Técnico');
