SET DEFINE OFF;
Insert into YUG_MSP.REF_MERGE_REASON
   (ID_PK, VERSION, CREATED_TS, UPDATED_TS, UPDATED_BY_USER, UPDATED_BY_TXN_ID, CONFIG_LANGUAGE_CODE_KEY, KEY, VALUE, DESCRIPTION)
 Values
   ('1', 0, TO_TIMESTAMP('10/30/2017 5:20:50.839000 PM','fmMMfm/fmDDfm/YYYY fmHH12fm:MI:SS.FF AM'), TO_TIMESTAMP('10/30/2017 5:20:50.839000 PM','fmMMfm/fmDDfm/YYYY fmHH12fm:MI:SS.FF AM'), 
    'admin', '00000000', '1', '1', 'AUTO_MERGED', 'AUTO_MERGED');
Insert into YUG_MSP.REF_MERGE_REASON
   (ID_PK, VERSION, CREATED_TS, UPDATED_TS, UPDATED_BY_USER, UPDATED_BY_TXN_ID, CONFIG_LANGUAGE_CODE_KEY, KEY, VALUE, DESCRIPTION)
 Values
   ('2', 1, TO_TIMESTAMP('10/30/2017 5:21:08.559000 PM','fmMMfm/fmDDfm/YYYY fmHH12fm:MI:SS.FF AM'), TO_TIMESTAMP('10/30/2017 5:21:18.256000 PM','fmMMfm/fmDDfm/YYYY fmHH12fm:MI:SS.FF AM'), 
    'admin', '00000000', '1', '2', 'MERGED_AFTER_REVIEW', 'MERGED_AFTER_REVIEW');
COMMIT;