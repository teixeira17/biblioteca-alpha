--
-- PostgreSQL database dump
--

-- Dumped from database version 17.5
-- Dumped by pg_dump version 17.5

-- Started on 2025-06-21 02:29:29

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET transaction_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 217 (class 1259 OID 16496)
-- Name: livros; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.livros (
    titulo text NOT NULL,
    autores text,
    data_publicacao text,
    isbn text NOT NULL,
    editora text,
    id integer NOT NULL
);


ALTER TABLE public.livros OWNER TO postgres;

--
-- TOC entry 218 (class 1259 OID 16505)
-- Name: livros_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.livros_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.livros_id_seq OWNER TO postgres;

--
-- TOC entry 4798 (class 0 OID 0)
-- Dependencies: 218
-- Name: livros_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.livros_id_seq OWNED BY public.livros.id;


--
-- TOC entry 4641 (class 2604 OID 16506)
-- Name: livros id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.livros ALTER COLUMN id SET DEFAULT nextval('public.livros_id_seq'::regclass);


--
-- TOC entry 4791 (class 0 OID 16496)
-- Dependencies: 217
-- Data for Name: livros; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.livros (titulo, autores, data_publicacao, isbn, editora, id) FROM stdin;
The Great Gatsby	F. Scott Fitzgerald	2004	9780743273565	Scribner	67
1984	George Orwell	1950	9780451524935	Signet Classic	68
To Kill a Mockingbird	Harper Lee	2006	9780061120084	Harper Perennial Modern Classics	69
Brave New World	Aldous Huxley	2006	9780060850524	Harper Perennial Modern Classics	70
Cem Anos de Solidão	Gabriel García Márquez	1967	9780060883287	Harper Perennial	75
A Revolução dos Bichos	George Orwell	1945	9788535909550	Companhia das Letras	77
A Metamorfose	Franz Kafka	1915	9788532522615	LPM	78
corinthians	João Guimarães Rosa	1956	9788571645083	Nova Fronteira	76
The Alchemist	lucas teixeira	1993	9780061122415	HarperCollins	66
\.


--
-- TOC entry 4799 (class 0 OID 0)
-- Dependencies: 218
-- Name: livros_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.livros_id_seq', 80, true);


--
-- TOC entry 4643 (class 2606 OID 16518)
-- Name: livros isbn_unico; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.livros
    ADD CONSTRAINT isbn_unico UNIQUE (isbn);


--
-- TOC entry 4645 (class 2606 OID 16508)
-- Name: livros livros_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.livros
    ADD CONSTRAINT livros_pkey PRIMARY KEY (id);


-- Completed on 2025-06-21 02:29:29

--
-- PostgreSQL database dump complete
--

