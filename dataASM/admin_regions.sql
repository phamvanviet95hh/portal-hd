PGDMP  &        
            |            asm    16.6 - Percona Distribution    16.4     G           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            H           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            I           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            J           1262    16389    asm    DATABASE     o   CREATE DATABASE asm WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'en_US.UTF-8';
    DROP DATABASE asm;
                postgres    false            �            1259    16882    administrative_regions    TABLE     �   CREATE TABLE public.administrative_regions (
    id bigint NOT NULL,
    code_name character varying(255),
    code_name_en character varying(255),
    name character varying(255),
    name_en character varying(255)
);
 *   DROP TABLE public.administrative_regions;
       public         heap    postgres    false            D          0    16882    administrative_regions 
   TABLE DATA           \   COPY public.administrative_regions (id, code_name, code_name_en, name, name_en) FROM stdin;
    public          postgres    false    215   K       �           2606    16990 2   administrative_regions administrative_regions_pkey 
   CONSTRAINT     p   ALTER TABLE ONLY public.administrative_regions
    ADD CONSTRAINT administrative_regions_pkey PRIMARY KEY (id);
 \   ALTER TABLE ONLY public.administrative_regions DROP CONSTRAINT administrative_regions_pkey;
       public            postgres    false    215            D   '  x�mQ;N�0�ǧ�	��S�-��˖H�[ID֑�a�[ Q�B��UR���&x��ь�����1�F,�9�P���4~��oU������_ +n,9���)��= ���F?~�~�����Qj&dm���gݗL0�`!] ��K� ��v(���RY�k�7>%LI���C�	D�s�����b��6��q�3�c�Vє�����'~zR:@�*����f�+-�(D�<���T��y��x��8��\�ל;�j�:���]�)9�Goy+�����!����D     