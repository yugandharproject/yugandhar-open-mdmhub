SET DEFINE OFF;
Insert into YUG_MSP.REF_IDENTIFICATION_TYPE
   (ID_PK, VERSION, CREATED_TS, UPDATED_TS, UPDATED_BY_USER, UPDATED_BY_TXN_ID, CONFIG_LANGUAGE_CODE_KEY, KEY, VALUE)
 Values
   ('1', 0, TO_TIMESTAMP('7/4/2017 5:42:15.138000 PM','fmMMfm/fmDDfm/YYYY fmHH12fm:MI:SS.FF AM'), TO_TIMESTAMP('7/4/2017 5:42:15.138000 PM','fmMMfm/fmDDfm/YYYY fmHH12fm:MI:SS.FF AM'), 
    'admin', '000000', '1', '1', 'SOCIAL SECURITY NUMBER');
Insert into YUG_MSP.REF_IDENTIFICATION_TYPE
   (ID_PK, VERSION, CREATED_TS, UPDATED_TS, UPDATED_BY_USER, UPDATED_BY_TXN_ID, CONFIG_LANGUAGE_CODE_KEY, KEY, VALUE)
 Values
   ('2', 0, TO_TIMESTAMP('7/4/2017 5:42:15.183000 PM','fmMMfm/fmDDfm/YYYY fmHH12fm:MI:SS.FF AM'), TO_TIMESTAMP('7/4/2017 5:42:15.183000 PM','fmMMfm/fmDDfm/YYYY fmHH12fm:MI:SS.FF AM'), 
    'admin', '000000', '1', '2', 'DRIVING LICENCE NUMBER');
Insert into YUG_MSP.REF_IDENTIFICATION_TYPE
   (ID_PK, VERSION, CREATED_TS, UPDATED_TS, UPDATED_BY_USER, UPDATED_BY_TXN_ID, CONFIG_LANGUAGE_CODE_KEY, KEY, VALUE)
 Values
   ('3', 0, TO_TIMESTAMP('7/4/2017 5:42:15.225000 PM','fmMMfm/fmDDfm/YYYY fmHH12fm:MI:SS.FF AM'), TO_TIMESTAMP('7/4/2017 5:42:15.225000 PM','fmMMfm/fmDDfm/YYYY fmHH12fm:MI:SS.FF AM'), 
    'admin', '000000', '1', '3', 'TAX IDENTIFICATION NUMBER');
COMMIT;
