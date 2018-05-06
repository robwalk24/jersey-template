-- Sequence: public.hero_id_seq

-- DROP SEQUENCE public.hero_id_seq;

CREATE SEQUENCE public.hero_id_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 4
  CACHE 1;
ALTER TABLE public.hero_id_seq
  OWNER TO hero;


-- Table: public.hero

-- DROP TABLE public.hero;

CREATE TABLE public.hero
(
  id integer NOT NULL DEFAULT nextval('hero_id_seq'::regclass),
  name character varying NOT NULL,
  CONSTRAINT pk_hero_id PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.hero
  OWNER TO hero;
i
