PGDMP  9        
            |            asm    16.6 - Percona Distribution    16.4     G           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            H           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            I           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            J           1262    16389    asm    DATABASE     o   CREATE DATABASE asm WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'en_US.UTF-8';
    DROP DATABASE asm;
                postgres    false            �            1259    16888    administrative_units    TABLE     4  CREATE TABLE public.administrative_units (
    id bigint NOT NULL,
    code_name character varying(255),
    code_name_en character varying(255),
    full_name character varying(255),
    full_name_en character varying(255),
    short_name character varying(255),
    short_name_en character varying(255)
);
 (   DROP TABLE public.administrative_units;
       public         heap    postgres    false            D          0    16888    administrative_units 
   TABLE DATA              COPY public.administrative_units (id, code_name, code_name_en, full_name, full_name_en, short_name, short_name_en) FROM stdin;
    public          postgres    false    217   �       �           2606    16992 .   administrative_units administrative_units_pkey 
   CONSTRAINT     l   ALTER TABLE ONLY public.administrative_units
    ADD CONSTRAINT administrative_units_pkey PRIMARY KEY (id);
 X   ALTER TABLE ONLY public.administrative_units DROP CONSTRAINT administrative_units_pkey;
       public            postgres    false    217            D   G  x����JC1�ד��(ֻ��P��!�Ch�Ӥ�'q�҅��K�n�e��7�ެ]e�����3�*E%
�k�V�\�!6�˱-�Z�"mjۇ����
��[�����3�e�o�u��ʰ]�&�h�Ԃ ]ݠ��*���B�����Ѱ����oş���W�y�ܹ���E]�����O��2�׳�s�	P�^
���u���yiU���7z_�ߚV�Z�F��r����������%�1jz�nj�PU�A�/$�b�N��֐Q���YLJ�~ $�z�:���m�V�r>��$�cv��� ��B�     