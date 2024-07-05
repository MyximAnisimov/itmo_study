SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '',false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

CREATE TYPE public.country AS ENUM (
    'INDIA',
    'UNITED_KINGDOM',
    'VATICAN',
    'ITALY',
    'JAPAN'
    );


ALTER TYPE public.country OWNER TO s379791;

SET default_tablespace = '';

SET default_table_access_method = heap;

CREATE TABLE public.person (
                                      id integer NOT NULL,
                                      name character varying(255) NOT NULL,
                                      coordinates_id integer NOT NULL,
                                      creation_date date NOT NULL,
                                      height integer NOT NULL,
                                     birthday date,
                                      passportID character varying(255) NOT NULL,
                                      nationality_id integer NOT NULL,
                                      location_id integer NOT NULL,
                                      creator_id integer NOT NULL
);


ALTER TABLE public.person OWNER TO s379791;

CREATE SEQUENCE public.person_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.person_id_seq OWNER TO s379791;

ALTER SEQUENCE public.person_id_seq OWNED BY public.person.id;


CREATE TABLE public.location (
                                 id integer not null,
                                 x bigint not null,
                                 y integer,
                                 z decimal not null,
                                 name character varying(255) not null,

);


ALTER TABLE public.location OWNER TO s379791;

CREATE SEQUENCE public.location_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.location_id_seq OWNER TO s379791;

ALTER SEQUENCE public.location_id_seq OWNED BY public.location.id;

CREATE TABLE public.users (
                              id integer NOT NULL,
                              name character varying(40) NOT NULL,
                              password_digest character varying(64) NOT NULL,
                              salt character varying(10) NOT NULL
);


ALTER TABLE public.users OWNER TO s379791;

--
-- Name: users_id_seq; Type: SEQUENCE; Schema: public; Owner: s367081
--

CREATE SEQUENCE public.users_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.users_id_seq OWNER TO s379791;

--
-- Name: users_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: s367081
--

ALTER SEQUENCE public.users_id_seq OWNED BY public.users.id;


--
-- Name: organizations id; Type: DEFAULT; Schema: public; Owner: s367081
--

ALTER TABLE ONLY public.person ALTER COLUMN id SET DEFAULT nextval('public.person_id_seq'::regclass);


--
-- Name: products id; Type: DEFAULT; Schema: public; Owner: s367081
--

ALTER TABLE ONLY public.location ALTER COLUMN id SET DEFAULT nextval('public.location_id_seq'::regclass);

ALTER TABLE ONLY public.coordinates ALTER COLUMN id SET DEFAULT nextval('public.coordinates_id_seq'::regclass);
--
-- Name: users id; Type: DEFAULT; Schema: public; Owner: s367081
--

ALTER TABLE ONLY public.users ALTER COLUMN id SET DEFAULT nextval('public.users_id_seq'::regclass);


--
-- Data for Name: organizations; Type: TABLE DATA; Schema: public; Owner: s367081
--

COPY public.person (id, name, coordinates_id, height, birthday, passsportID, nationality_id, location_id) FROM stdin;
\.


--
-- Data for Name: products; Type: TABLE DATA; Schema: public; Owner: s367081
--

COPY public.location (id, x, y, name) FROM stdin;
1	2023-03-29	123	12	1	\N	123	12	1	\N
\.


--
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: s367081
--

COPY public.users (id, name, password_digest, salt) FROM stdin;
1	a	193f50a746f1247db2800f2aa73894f9faaa6385337a238c8e0dbb39604993b0	PWmy3oKw05
\.


--
-- Name: organizations_id_seq; Type: SEQUENCE SET; Schema: public; Owner: s367081
--

SELECT pg_catalog.setval('public.organizations_id_seq', 1, false);


--
-- Name: products_id_seq; Type: SEQUENCE SET; Schema: public; Owner: s367081
--

SELECT pg_catalog.setval('public.products_id_seq', 1, true);


--
-- Name: users_id_seq; Type: SEQUENCE SET; Schema: public; Owner: s367081
--

SELECT pg_catalog.setval('public.users_id_seq', 1, true);


--
-- Name: organizations organizations_pkey; Type: CONSTRAINT; Schema: public; Owner: s367081
--

ALTER TABLE ONLY public.organizations
    ADD CONSTRAINT organizations_pkey PRIMARY KEY (id);


--
-- Name: products products_pkey; Type: CONSTRAINT; Schema: public; Owner: s367081
--

ALTER TABLE ONLY public.products
    ADD CONSTRAINT products_pkey PRIMARY KEY (id);


--
-- Name: users uk_3g1j96g94xpk3lpxl2qbl985x; Type: CONSTRAINT; Schema: public; Owner: s367081
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT uk_3g1j96g94xpk3lpxl2qbl985x UNIQUE (name);


--
-- Name: users users_pkey; Type: CONSTRAINT; Schema: public; Owner: s367081
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);


--
-- Name: products fkid1quonr11ajt1rq1xbvx9p47; Type: FK CONSTRAINT; Schema: public; Owner: s367081
--

ALTER TABLE ONLY public.products
    ADD CONSTRAINT fkid1quonr11ajt1rq1xbvx9p47 FOREIGN KEY (creator_id) REFERENCES public.users(id);


--
-- Name: organizations fkm1est60p6bxfm3jdira1o7j9c; Type: FK CONSTRAINT; Schema: public; Owner: s367081
--

ALTER TABLE ONLY public.organizations
    ADD CONSTRAINT fkm1est60p6bxfm3jdira1o7j9c FOREIGN KEY (creator_id) REFERENCES public.users(id);


--
-- Name: products fkosamd2d5l9l0x9j0spa4s649w; Type: FK CONSTRAINT; Schema: public; Owner: s367081
--

ALTER TABLE ONLY public.products
    ADD CONSTRAINT fkosamd2d5l9l0x9j0spa4s649w FOREIGN KEY (manufacturer_id) REFERENCES public.organizations(id);