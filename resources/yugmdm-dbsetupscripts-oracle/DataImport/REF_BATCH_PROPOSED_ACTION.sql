SET DEFINE OFF;
Insert into YUG_MSP.REF_BATCH_PROPOSED_ACTION
   (ID_PK, VERSION, CREATED_TS, UPDATED_TS, UPDATED_BY_USER, UPDATED_BY_TXN_ID, CONFIG_LANGUAGE_CODE_KEY, KEY, VALUE, DESCRIPTION)
 Values
   ('1', 1, TO_TIMESTAMP('12/12/2017 7:11:44.687000 PM','fmMMfm/fmDDfm/YYYY fmHH12fm:MI:SS.FF AM'), TO_TIMESTAMP('12/12/2017 7:12:38.517000 PM','fmMMfm/fmDDfm/YYYY fmHH12fm:MI:SS.FF AM'), 
    'admin', '00000000', '1', '2', 'SEARCH MATCH CANDIDATE', 'SEARCH THE MATCH CANDIDATES');
Insert into YUG_MSP.REF_BATCH_PROPOSED_ACTION
   (ID_PK, VERSION, CREATED_TS, UPDATED_TS, UPDATED_BY_USER, UPDATED_BY_TXN_ID, CONFIG_LANGUAGE_CODE_KEY, KEY, VALUE, DESCRIPTION)
 Values
   ('2', 0, TO_TIMESTAMP('12/12/2017 7:15:29.114000 PM','fmMMfm/fmDDfm/YYYY fmHH12fm:MI:SS.FF AM'), TO_TIMESTAMP('12/12/2017 7:15:29.114000 PM','fmMMfm/fmDDfm/YYYY fmHH12fm:MI:SS.FF AM'), 
    'admin', '00000000', '1', '1', 'NO ACTION', 'NO ACTION');
COMMIT;
