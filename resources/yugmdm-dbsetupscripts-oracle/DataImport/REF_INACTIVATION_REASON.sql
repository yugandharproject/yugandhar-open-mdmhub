SET DEFINE OFF;
Insert into YUG_OWNER.REF_INACTIVATION_REASON
   (ID_PK, VERSION, CREATED_TS, UPDATED_TS, UPDATED_BY_USER, UPDATED_BY_TXN_ID, CONFIG_LANGUAGE_CODE_KEY, KEY, VALUE, DESCRIPTION)
 Values
   ('1', 1, TO_TIMESTAMP('10/30/2017 3:41:09.750000 PM','fmMMfm/fmDDfm/YYYY fmHH12fm:MI:SS.FF AM'), TO_TIMESTAMP('10/30/2017 3:42:12.229000 PM','fmMMfm/fmDDfm/YYYY fmHH12fm:MI:SS.FF AM'), 
    'admin', '00000000', '1', '1', 'MATCH AND MERGED', 'MATCH AND MERGED');
Insert into YUG_OWNER.REF_INACTIVATION_REASON
   (ID_PK, VERSION, CREATED_TS, UPDATED_TS, UPDATED_BY_USER, UPDATED_BY_TXN_ID, CONFIG_LANGUAGE_CODE_KEY, KEY, VALUE, DESCRIPTION)
 Values
   ('2', 0, TO_TIMESTAMP('10/30/2017 3:46:03.053000 PM','fmMMfm/fmDDfm/YYYY fmHH12fm:MI:SS.FF AM'), TO_TIMESTAMP('10/30/2017 3:46:03.053000 PM','fmMMfm/fmDDfm/YYYY fmHH12fm:MI:SS.FF AM'), 
    'admin', '00000000', '1', '2', 'EXPIRED', 'EXPIRED');
Insert into YUG_OWNER.REF_INACTIVATION_REASON
   (ID_PK, VERSION, CREATED_TS, UPDATED_TS, UPDATED_BY_USER, UPDATED_BY_TXN_ID, CONFIG_LANGUAGE_CODE_KEY, KEY, VALUE, DESCRIPTION)
 Values
   ('3', 0, TO_TIMESTAMP('10/30/2017 3:46:14.913000 PM','fmMMfm/fmDDfm/YYYY fmHH12fm:MI:SS.FF AM'), TO_TIMESTAMP('10/30/2017 3:46:14.913000 PM','fmMMfm/fmDDfm/YYYY fmHH12fm:MI:SS.FF AM'), 
    'admin', '00000000', '1', '3', 'DECEASED', 'DECEASED');
COMMIT;
