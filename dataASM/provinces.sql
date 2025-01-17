PGDMP          
            |            asm    16.6 - Percona Distribution    16.4 	    I           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            J           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            K           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            L           1262    16389    asm    DATABASE     o   CREATE DATABASE asm WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'en_US.UTF-8';
    DROP DATABASE asm;
                postgres    false            �            1259    16966 	   provinces    TABLE     N  CREATE TABLE public.provinces (
    code character varying(255) NOT NULL,
    code_name character varying(255),
    full_name character varying(255),
    full_name_en character varying(255),
    name character varying(255),
    name_en character varying(255),
    administrative_region_id bigint,
    administrative_unit_id bigint
);
    DROP TABLE public.provinces;
       public         heap    postgres    false            F          0    16966 	   provinces 
   TABLE DATA           �   COPY public.provinces (code, code_name, full_name, full_name_en, name, name_en, administrative_region_id, administrative_unit_id) FROM stdin;
    public          postgres    false    243   �
       �           2606    17020    provinces provinces_pkey 
   CONSTRAINT     X   ALTER TABLE ONLY public.provinces
    ADD CONSTRAINT provinces_pkey PRIMARY KEY (code);
 B   ALTER TABLE ONLY public.provinces DROP CONSTRAINT provinces_pkey;
       public            postgres    false    243            �           2606    17055 %   provinces fk9t4owb4c6jpeva3rs6u4erttd    FK CONSTRAINT     �   ALTER TABLE ONLY public.provinces
    ADD CONSTRAINT fk9t4owb4c6jpeva3rs6u4erttd FOREIGN KEY (administrative_region_id) REFERENCES public.administrative_regions(id);
 O   ALTER TABLE ONLY public.provinces DROP CONSTRAINT fk9t4owb4c6jpeva3rs6u4erttd;
       public          postgres    false    243            �           2606    17115 %   provinces fkqenrpv1j8q01yhmvol8avi09s    FK CONSTRAINT     �   ALTER TABLE ONLY public.provinces
    ADD CONSTRAINT fkqenrpv1j8q01yhmvol8avi09s FOREIGN KEY (administrative_unit_id) REFERENCES public.administrative_units(id);
 O   ALTER TABLE ONLY public.provinces DROP CONSTRAINT fkqenrpv1j8q01yhmvol8avi09s;
       public          postgres    false    243            F     x��X�n�F]_}��$��%5`Epb�@�ĘDB6�b /�.��(�"��;Yi$Mڍ�Ȃ����s��I�]�͹s4s�c.�l���0
h�'���\���g�(�u&��U@GʙD�3
V7T��=j5�mf�*��4�� ��JO�N��e�,�U1jQ��쐧"��`����?1��M(<=:W��PaN���s���B*�TX�ԀB3�U|3�obcG��&�-t��2.�vYh�V�.qFO9�8��p΀�J?�2Bm&h�E�M��_Nq�"H7�Cg����3�;�����x�%�b�'�c����MU0a�зQ�^�����.�F�(���K,�y��Sp�����j�R���O~� J�o0B��?0s)�sEVA�٧�5�9�m�s��L�ڱH%L�w�h�����܅D[�9��Z&(ɣȠ�C��M:��8��8]��ф�$'�qwcGg�������������K�~��(�:�5L7?щc<ULHz�w���4g�o32���� L�0J���x�\�}- �ޖ��<{M����#��A��9|� 7q-Øa#�����	�}Mh^�
���-:a����b���#2��=z����2t���P6֑0�I�[F��N���u�ݢ��{a��|{�+�N(�em��н0��K�_��1��Cku�0���:�v�lY�6U*TV\�7�:J�쓢l)S�*JPu����[�S�9�sF3<��!����,{�q���JN��W����US��`�R1l�vYg7�\-�2�t	e�ˠ�X2�T�qR��>?�ҧ��q�o6���r�gf<13�_��]���-�������p�Q��d@]$dg?��E�o+1��BlfA-�v����檢�d��	�sU#j�B8��N�2L�s�E��v��0.�F�%^���V��@BѣE9��Q^thTt\M�ZX���;����{�~R��lwh�^S$�E�|Ű�r��p�i��io������qT�/�.o�^۰�}�=e�by	��=���E;�;p���#��d��&
�2��$���Ⱥka��aB�7���k��/17��nq�����y��N����O<r�e�՘P�Y۴R7V_1M^�H�0U7����	K'�@���/λ�0���e!�]b�Pu���I@Z��u�0q��.\ϸ�XoCT�'� ���9��-W��6F��<	�7S�����/�G;�W���Ғ����%���s�}Ǉ@��i�Vc�V��)�w�G�}�=���`g��� �]����0k��|���¸G� \-gF�鍉�L?��Ԇ��N2cq	����R9gv,V0a���K#��f��[aX�
U_B��u9�W��8C;t]Ò��*�~��xP��i��-*�;ֽ��x��6yؔ~�1t���o��;�P�����΃���Gۮ�ҏ'�&��O$q���:X�į��� ;M>y����1u��"�����_�.�Yl���!J�_����謠�ԃT�UQ?F��G�8R�0~ұ��گ�l4�6k��     