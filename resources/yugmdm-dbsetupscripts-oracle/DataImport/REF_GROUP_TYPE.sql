SET DEFINE OFF;
Insert into YUG_MSP.REF_GROUP_TYPE
   (ID_PK, VERSION, CREATED_TS, UPDATED_TS, UPDATED_BY_USER, UPDATED_BY_TXN_ID, CONFIG_LANGUAGE_CODE_KEY, KEY, VALUE, DESCRIPTION)
 Values
   ('1', 0, TO_TIMESTAMP('7/4/2017 5:30:17.431000 PM','fmMMfm/fmDDfm/YYYY fmHH12fm:MI:SS.FF AM'), TO_TIMESTAMP('7/4/2017 5:30:17.431000 PM','fmMMfm/fmDDfm/YYYY fmHH12fm:MI:SS.FF AM'), 
    'admin', '000000', '1', '1', 'LEPERSON', 'Grouping of Persons');
Insert into YUG_MSP.REF_GROUP_TYPE
   (ID_PK, VERSION, CREATED_TS, UPDATED_TS, UPDATED_BY_USER, UPDATED_BY_TXN_ID, CONFIG_LANGUAGE_CODE_KEY, KEY, VALUE, DESCRIPTION)
 Values
   ('2', 0, TO_TIMESTAMP('7/4/2017 5:30:17.469000 PM','fmMMfm/fmDDfm/YYYY fmHH12fm:MI:SS.FF AM'), TO_TIMESTAMP('7/4/2017 5:30:17.469000 PM','fmMMfm/fmDDfm/YYYY fmHH12fm:MI:SS.FF AM'), 
    'admin', '000000', '1', '2', 'LECORPORATION', 'grouping of corporation');
Insert into YUG_MSP.REF_GROUP_TYPE
   (ID_PK, VERSION, CREATED_TS, UPDATED_TS, UPDATED_BY_USER, UPDATED_BY_TXN_ID, CONFIG_LANGUAGE_CODE_KEY, KEY, VALUE, DESCRIPTION)
 Values
   ('3', 0, TO_TIMESTAMP('7/4/2017 5:30:17.495000 PM','fmMMfm/fmDDfm/YYYY fmHH12fm:MI:SS.FF AM'), TO_TIMESTAMP('7/4/2017 5:30:17.495000 PM','fmMMfm/fmDDfm/YYYY fmHH12fm:MI:SS.FF AM'), 
    'admin', '000000', '1', '3', 'ACCOUNT', 'grouping of account');
COMMIT;