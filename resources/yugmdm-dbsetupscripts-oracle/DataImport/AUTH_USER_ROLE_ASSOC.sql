SET DEFINE OFF;
Insert into YUG_MSP.AUTH_USER_ROLE_ASSOC
   (ID_PK, VERSION, CREATED_TS, UPDATED_TS, UPDATED_BY_USER, UPDATED_BY_TXN_ID, AUTH_USER_REGISTRY_IDPK, AUTH_ROLES_REGISTRY_IDPK, DESCRIPTION)
 Values
   ('c03dbf6e-5808-49b9-9cb8-384457499671', 0, TO_TIMESTAMP('10/3/2017 4:57:29.655000 PM','fmMMfm/fmDDfm/YYYY fmHH12fm:MI:SS.FF AM'), TO_TIMESTAMP('10/3/2017 4:57:29.655000 PM','fmMMfm/fmDDfm/YYYY fmHH12fm:MI:SS.FF AM'), 
    'admin', '1234567890123', '83474f01-6efa-4c0c-af30-afd9057278b4', 'ff142ad4-33d8-4827-a094-e7506c732536', 'ROLE to USER Mapping');
Insert into YUG_MSP.AUTH_USER_ROLE_ASSOC
   (ID_PK, VERSION, CREATED_TS, UPDATED_TS, UPDATED_BY_USER, UPDATED_BY_TXN_ID, AUTH_USER_REGISTRY_IDPK, AUTH_ROLES_REGISTRY_IDPK, DESCRIPTION)
 Values
   ('f693c974-3962-449c-8e8d-71be0946d9da', 0, TO_TIMESTAMP('10/3/2017 4:57:57.170000 PM','fmMMfm/fmDDfm/YYYY fmHH12fm:MI:SS.FF AM'), TO_TIMESTAMP('10/3/2017 4:57:57.170000 PM','fmMMfm/fmDDfm/YYYY fmHH12fm:MI:SS.FF AM'), 
    'admin', '1234567890123', '83474f01-6efa-4c0c-af30-afd9057278b4', '7d96d881-257b-4862-b82a-cc517b0a6891', 'ROLE to USER Mapping');
Insert into YUG_MSP.AUTH_USER_ROLE_ASSOC
   (ID_PK, VERSION, CREATED_TS, UPDATED_TS, UPDATED_BY_USER, UPDATED_BY_TXN_ID, AUTH_USER_REGISTRY_IDPK, AUTH_ROLES_REGISTRY_IDPK, DESCRIPTION)
 Values
   ('02b352b6-12f9-48d2-8fa8-6139811815ff', 3, TO_TIMESTAMP('10/3/2017 4:58:06.785000 PM','fmMMfm/fmDDfm/YYYY fmHH12fm:MI:SS.FF AM'), TO_TIMESTAMP('10/3/2017 4:58:54.132000 PM','fmMMfm/fmDDfm/YYYY fmHH12fm:MI:SS.FF AM'), 
    'admin', '1234567890123', '103d98d2-b558-405b-9574-b6bdffa82bd5', '7d96d881-257b-4862-b82a-cc517b0a6891', 'ROLE to USER Mapping');
COMMIT;
