set lines 1000;
set pages 2000;
set scan off;
set serveroutput on;

spool 'CreateSequence.log'
-----------------------------------------------------------------------
CREATE SEQUENCE MDM_OWNER.YUG_ERRORCODE_SEQ
  START WITH 100000
  MAXVALUE 1000000
  MINVALUE 1
  NOCYCLE
  CACHE 10
  NOORDER;


CREATE SEQUENCE MDM_OWNER.YUG_REGISTRY_SEQ
  START WITH 100000
  MAXVALUE 1000000
  MINVALUE 1
  NOCYCLE
  CACHE 10
  NOORDER;
