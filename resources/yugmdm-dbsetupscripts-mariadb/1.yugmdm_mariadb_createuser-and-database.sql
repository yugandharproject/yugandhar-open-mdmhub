-- --------------------------------------------------------
-- Yugandhar Microservices Platform V1.0.0.RELEASE
-- Database scripts for MariaDB
-- --------------------------------------------------------

CREATE USER 'YUG_OWNER'@'%' IDENTIFIED BY 'YUG_OWNER';
GRANT EXECUTE, PROCESS, SELECT, SHOW DATABASES, SHOW VIEW, ALTER, ALTER ROUTINE, CREATE, CREATE ROUTINE, CREATE TABLESPACE, CREATE TEMPORARY TABLES, CREATE VIEW, DELETE, DROP, EVENT, INDEX, INSERT, REFERENCES, TRIGGER, UPDATE, CREATE USER, FILE, LOCK TABLES, RELOAD, REPLICATION CLIENT, REPLICATION SLAVE  ON *.* TO 'YUG_OWNER'@'%' WITH GRANT OPTION;
FLUSH PRIVILEGES;

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Dumping database structure for yug_owner
CREATE DATABASE IF NOT EXISTS `yug_owner` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `yug_owner`;

-- Dumping structure for table yug_owner.account
CREATE TABLE IF NOT EXISTS `account` (
  `ID_PK` varchar(50) COLLATE utf8_bin NOT NULL,
  `VERSION` decimal(22,0) NOT NULL,
  `CREATED_TS` datetime(6) NOT NULL,
  `DELETED_TS` datetime(6) DEFAULT NULL,
  `UPDATED_TS` datetime(6) NOT NULL,
  `UPDATED_BY_USER` varchar(50) COLLATE utf8_bin NOT NULL,
  `UPDATED_BY_TXN_ID` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `CONTRACT_SIGNED_LANG_REFKEY` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `CURRENCY_REFKEY` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `BILLING_MODE_TYPE_REFKEY` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `FREQUENCY_OF_PAYMENT` decimal(19,0) DEFAULT NULL,
  `LOBTYPE_REFKEY` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `LOB_DESCRIPTION` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `SOURCE_SYSTEM_REFKEY` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `SOURCE_ACCOUNT_ID` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `MANAGEDBY_BU_CODE` varchar(30) COLLATE utf8_bin DEFAULT NULL,
  `MANAGEDBY_BU_ID` varchar(30) COLLATE utf8_bin DEFAULT NULL,
  `BRANCH_CODE_REFKEY` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `ACCOUNT_NAME` varchar(50) COLLATE utf8_bin NOT NULL,
  `ACCOUNT_NAME2` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `ACCOUNT_DESCRIPTION` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `ACCOUNT_SOURCE_STATUS_REFKEY` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `ACCOUNT_MDM_STATUS_REFKEY` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `SIGNED_DATE` datetime DEFAULT NULL,
  `SIGNED_PLACE` datetime DEFAULT NULL,
  `EXECUTED_DATE` datetime DEFAULT NULL,
  `TERMINATED_DATE` datetime DEFAULT NULL,
  `TERMINATION_REASON_REFKEY` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_PK`),
  KEY `INDX_ACCOUNT_1` (`SOURCE_SYSTEM_REFKEY`),
  KEY `INDX_ACCOUNT_2` (`ACCOUNT_NAME`),
  KEY `INDX_ACCOUNT_3` (`ACCOUNT_NAME2`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- Dumping data for table yug_owner.account: ~0 rows (approximately)
/*!40000 ALTER TABLE `account` DISABLE KEYS */;
/*!40000 ALTER TABLE `account` ENABLE KEYS */;

-- Dumping structure for table yug_owner.account_address_assoc
CREATE TABLE IF NOT EXISTS `account_address_assoc` (
  `ID_PK` varchar(50) COLLATE utf8_bin NOT NULL,
  `VERSION` decimal(22,0) NOT NULL,
  `CREATED_TS` datetime(6) NOT NULL,
  `DELETED_TS` datetime(6) DEFAULT NULL,
  `UPDATED_TS` datetime(6) NOT NULL,
  `UPDATED_BY_USER` varchar(50) COLLATE utf8_bin NOT NULL,
  `UPDATED_BY_TXN_ID` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `ACCOUNT_IDPK` varchar(50) COLLATE utf8_bin NOT NULL,
  `ADDRESS_IDPK` varchar(50) COLLATE utf8_bin NOT NULL,
  `ADDRESS_TYPE_REFKEY` varchar(50) COLLATE utf8_bin NOT NULL,
  `ADDRESS_SUBTYPE_REFKEY` varchar(50) COLLATE utf8_bin NOT NULL,
  `PREFERRED_FLAG` varchar(1) COLLATE utf8_bin DEFAULT NULL,
  `SOLICITATION_FLAG` varchar(1) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_PK`),
  UNIQUE KEY `ACCOUNTADDRESSASSOC_UNIQUEKEY` (`ADDRESS_TYPE_REFKEY`,`ADDRESS_IDPK`,`ACCOUNT_IDPK`),
  KEY `INDX_ACCOUNT_ADDRESS_ASSOC_1` (`ACCOUNT_IDPK`),
  KEY `INDX_ACCOUNT_ADDRESS_ASSOC_2` (`ADDRESS_IDPK`),
  KEY `INDX_ACCOUNT_ADDRESS_ASSOC_3` (`ADDRESS_TYPE_REFKEY`),
  KEY `INDX_ACCOUNT_ADDRESS_ASSOC_4` (`ADDRESS_SUBTYPE_REFKEY`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- Dumping data for table yug_owner.account_address_assoc: ~0 rows (approximately)
/*!40000 ALTER TABLE `account_address_assoc` DISABLE KEYS */;
/*!40000 ALTER TABLE `account_address_assoc` ENABLE KEYS */;

-- Dumping structure for table yug_owner.account_phone_assoc
CREATE TABLE IF NOT EXISTS `account_phone_assoc` (
  `ID_PK` varchar(50) COLLATE utf8_bin NOT NULL,
  `VERSION` decimal(22,0) NOT NULL,
  `CREATED_TS` datetime(6) NOT NULL,
  `DELETED_TS` datetime(6) DEFAULT NULL,
  `UPDATED_TS` datetime(6) NOT NULL,
  `UPDATED_BY_USER` varchar(50) COLLATE utf8_bin NOT NULL,
  `UPDATED_BY_TXN_ID` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `ACCOUNT_IDPK` varchar(50) COLLATE utf8_bin NOT NULL,
  `PHONE_TYPE_REFKEY` varchar(50) COLLATE utf8_bin NOT NULL,
  `PHONE_SUBTYPE_REFKEY` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `PREFERRED_FLAG` varchar(1) COLLATE utf8_bin DEFAULT NULL,
  `PHONE_NUMBER` varchar(30) COLLATE utf8_bin NOT NULL,
  `PHONE_STANDARDIZED_IDPK` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_PK`),
  UNIQUE KEY `ACCOUNTPHONEASSOC_UNIQUEKEY` (`PHONE_TYPE_REFKEY`,`ACCOUNT_IDPK`),
  KEY `INDX_ACCOUNT_PHONE_ASSOC_5` (`PHONE_STANDARDIZED_IDPK`),
  KEY `INDX_ACCOUNT_PHONE_ASSOC_4` (`PHONE_NUMBER`),
  KEY `INDX_ACCOUNT_PHONE_ASSOC_3` (`PHONE_SUBTYPE_REFKEY`),
  KEY `INDX_ACCOUNT_PHONE_ASSOC_2` (`PHONE_TYPE_REFKEY`),
  KEY `INDX_ACCOUNT_PHONE_ASSOC_1` (`ACCOUNT_IDPK`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- Dumping data for table yug_owner.account_phone_assoc: ~0 rows (approximately)
/*!40000 ALTER TABLE `account_phone_assoc` DISABLE KEYS */;
/*!40000 ALTER TABLE `account_phone_assoc` ENABLE KEYS */;

-- Dumping structure for table yug_owner.address
CREATE TABLE IF NOT EXISTS `address` (
  `ID_PK` varchar(50) COLLATE utf8_bin NOT NULL,
  `VERSION` decimal(22,0) NOT NULL,
  `CREATED_TS` datetime(6) NOT NULL,
  `DELETED_TS` datetime(6) DEFAULT NULL,
  `UPDATED_TS` datetime(6) NOT NULL,
  `UPDATED_BY_USER` varchar(50) COLLATE utf8_bin NOT NULL,
  `UPDATED_BY_TXN_ID` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `ADDRESS_LINE_ONE` varchar(100) COLLATE utf8_bin NOT NULL,
  `ADDRESS_LINE_TWO` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `ADDRESS_LINE_THREE` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `ADDRESS_LINE_FOUR` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `STREET_NUMBER` varchar(30) COLLATE utf8_bin DEFAULT NULL,
  `STREET_NAME` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `HOUSE_NUMBER` varchar(30) COLLATE utf8_bin DEFAULT NULL,
  `BUILDING_NUMBER` varchar(30) COLLATE utf8_bin DEFAULT NULL,
  `COUNTY` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `CITY` varchar(100) COLLATE utf8_bin NOT NULL,
  `DISTRICT_ZONE` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `STATE_PROVINCE_REFKEY` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `COUNTRY_REFKEY` varchar(50) COLLATE utf8_bin NOT NULL,
  `POSTAL_CODE` varchar(30) COLLATE utf8_bin DEFAULT NULL,
  `NEAREST_LANDMARK` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `BOX_DESIGNATOR` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `BOX_IDENTIFIER` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `NEAREST_RAILWAY_STATION` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `NEAREST_AIRPORT` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `PHONETIC_ADDRESS_LINE_ONE` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `PHONETIC_ADDRESS_LINE_TWO` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `PHONETIC_ADDRESS_LINE_THREE` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `PHONETIC_ADDRESS_LINE_FOUR` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `PHONETIC_STREET_NAME` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `PHONETIC_COUNTY` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `PHONETIC_CITY` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `PHONETIC_DISTRICT_ZONE` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_PK`),
  KEY `INDX_ADDRESS_9` (`COUNTRY_REFKEY`),
  KEY `INDX_ADDRESS_8` (`STATE_PROVINCE_REFKEY`),
  KEY `INDX_ADDRESS_7` (`DISTRICT_ZONE`),
  KEY `INDX_ADDRESS_6` (`CITY`),
  KEY `INDX_ADDRESS_5` (`COUNTY`),
  KEY `INDX_ADDRESS_4` (`ADDRESS_LINE_FOUR`),
  KEY `INDX_ADDRESS_3` (`ADDRESS_LINE_THREE`),
  KEY `INDX_ADDRESS_2` (`ADDRESS_LINE_TWO`),
  KEY `INDX_ADDRESS_19` (`STREET_NAME`),
  KEY `INDX_ADDRESS_18` (`PHONETIC_DISTRICT_ZONE`),
  KEY `INDX_ADDRESS_17` (`PHONETIC_CITY`),
  KEY `INDX_ADDRESS_16` (`PHONETIC_COUNTY`),
  KEY `INDX_ADDRESS_15` (`PHONETIC_STREET_NAME`),
  KEY `INDX_ADDRESS_14` (`PHONETIC_ADDRESS_LINE_FOUR`),
  KEY `INDX_ADDRESS_13` (`PHONETIC_ADDRESS_LINE_THREE`),
  KEY `INDX_ADDRESS_12` (`PHONETIC_ADDRESS_LINE_TWO`),
  KEY `INDX_ADDRESS_11` (`PHONETIC_ADDRESS_LINE_ONE`),
  KEY `INDX_ADDRESS_10` (`POSTAL_CODE`),
  KEY `INDX_ADDRESS_1` (`ADDRESS_LINE_ONE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- Dumping data for table yug_owner.address: ~0 rows (approximately)
/*!40000 ALTER TABLE `address` DISABLE KEYS */;
/*!40000 ALTER TABLE `address` ENABLE KEYS */;

-- Dumping structure for table yug_owner.al_account
CREATE TABLE IF NOT EXISTS `al_account` (
  `AUDITLOG_ID_PK` varchar(50) COLLATE utf8_bin NOT NULL,
  `AUDITLOG_CREATED_TS` datetime(6) NOT NULL,
  `AUDITLOG_ACTION_CODE` varchar(1) COLLATE utf8_bin NOT NULL,
  `ID_PK` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `VERSION` decimal(22,0) NOT NULL,
  `CREATED_TS` datetime(6) NOT NULL,
  `DELETED_TS` datetime(6) DEFAULT NULL,
  `UPDATED_TS` datetime(6) NOT NULL,
  `UPDATED_BY_USER` varchar(50) COLLATE utf8_bin NOT NULL,
  `UPDATED_BY_TXN_ID` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `CONTRACT_SIGNED_LANG_REFKEY` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `CURRENCY_REFKEY` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `BILLING_MODE_TYPE_REFKEY` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `FREQUENCY_OF_PAYMENT` decimal(19,0) DEFAULT NULL,
  `LOBTYPE_REFKEY` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `LOB_DESCRIPTION` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `SOURCE_SYSTEM_REFKEY` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `SOURCE_ACCOUNT_ID` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `MANAGEDBY_BU_CODE` varchar(30) COLLATE utf8_bin DEFAULT NULL,
  `MANAGEDBY_BU_ID` varchar(30) COLLATE utf8_bin DEFAULT NULL,
  `BRANCH_CODE_REFKEY` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `ACCOUNT_NAME` varchar(50) COLLATE utf8_bin NOT NULL,
  `ACCOUNT_NAME2` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `ACCOUNT_DESCRIPTION` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `ACCOUNT_SOURCE_STATUS_REFKEY` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `ACCOUNT_MDM_STATUS_REFKEY` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `SIGNED_DATE` datetime DEFAULT NULL,
  `SIGNED_PLACE` datetime DEFAULT NULL,
  `EXECUTED_DATE` datetime DEFAULT NULL,
  `TERMINATED_DATE` datetime DEFAULT NULL,
  `TERMINATION_REASON_REFKEY` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`AUDITLOG_ID_PK`),
  KEY `IDXAL_ACCOUNT` (`ID_PK`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- Dumping data for table yug_owner.al_account: ~0 rows (approximately)
/*!40000 ALTER TABLE `al_account` DISABLE KEYS */;
/*!40000 ALTER TABLE `al_account` ENABLE KEYS */;

-- Dumping structure for table yug_owner.al_account_address_assoc
CREATE TABLE IF NOT EXISTS `al_account_address_assoc` (
  `AUDITLOG_ID_PK` varchar(50) COLLATE utf8_bin NOT NULL,
  `AUDITLOG_CREATED_TS` datetime(6) NOT NULL,
  `AUDITLOG_ACTION_CODE` varchar(1) COLLATE utf8_bin NOT NULL,
  `ID_PK` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `VERSION` decimal(22,0) NOT NULL,
  `CREATED_TS` datetime(6) NOT NULL,
  `DELETED_TS` datetime(6) DEFAULT NULL,
  `UPDATED_TS` datetime(6) NOT NULL,
  `UPDATED_BY_USER` varchar(50) COLLATE utf8_bin NOT NULL,
  `UPDATED_BY_TXN_ID` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `ACCOUNT_IDPK` varchar(50) COLLATE utf8_bin NOT NULL,
  `ADDRESS_IDPK` varchar(50) COLLATE utf8_bin NOT NULL,
  `ADDRESS_TYPE_REFKEY` varchar(50) COLLATE utf8_bin NOT NULL,
  `ADDRESS_SUBTYPE_REFKEY` varchar(50) COLLATE utf8_bin NOT NULL,
  `PREFERRED_FLAG` varchar(1) COLLATE utf8_bin DEFAULT NULL,
  `SOLICITATION_FLAG` varchar(1) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`AUDITLOG_ID_PK`),
  KEY `IDXAL_ACCOUNT_ADDRESS_ASSOC` (`ID_PK`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- Dumping data for table yug_owner.al_account_address_assoc: ~0 rows (approximately)
/*!40000 ALTER TABLE `al_account_address_assoc` DISABLE KEYS */;
/*!40000 ALTER TABLE `al_account_address_assoc` ENABLE KEYS */;

-- Dumping structure for table yug_owner.al_account_phone_assoc
CREATE TABLE IF NOT EXISTS `al_account_phone_assoc` (
  `AUDITLOG_ID_PK` varchar(50) COLLATE utf8_bin NOT NULL,
  `AUDITLOG_CREATED_TS` datetime(6) NOT NULL,
  `AUDITLOG_ACTION_CODE` varchar(1) COLLATE utf8_bin NOT NULL,
  `ID_PK` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `VERSION` decimal(22,0) NOT NULL,
  `CREATED_TS` datetime(6) NOT NULL,
  `DELETED_TS` datetime(6) DEFAULT NULL,
  `UPDATED_TS` datetime(6) NOT NULL,
  `UPDATED_BY_USER` varchar(50) COLLATE utf8_bin NOT NULL,
  `UPDATED_BY_TXN_ID` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `ACCOUNT_IDPK` varchar(50) COLLATE utf8_bin NOT NULL,
  `PHONE_TYPE_REFKEY` varchar(50) COLLATE utf8_bin NOT NULL,
  `PHONE_SUBTYPE_REFKEY` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `PREFERRED_FLAG` varchar(1) COLLATE utf8_bin DEFAULT NULL,
  `PHONE_NUMBER` varchar(30) COLLATE utf8_bin NOT NULL,
  `PHONE_STANDARDIZED_IDPK` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`AUDITLOG_ID_PK`),
  KEY `IDXAL_ACCOUNT_PHONE_ASSOC` (`ID_PK`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- Dumping data for table yug_owner.al_account_phone_assoc: ~0 rows (approximately)
/*!40000 ALTER TABLE `al_account_phone_assoc` DISABLE KEYS */;
/*!40000 ALTER TABLE `al_account_phone_assoc` ENABLE KEYS */;

-- Dumping structure for table yug_owner.al_address
CREATE TABLE IF NOT EXISTS `al_address` (
  `AUDITLOG_ID_PK` varchar(50) COLLATE utf8_bin NOT NULL,
  `AUDITLOG_CREATED_TS` datetime(6) NOT NULL,
  `AUDITLOG_ACTION_CODE` varchar(1) COLLATE utf8_bin NOT NULL,
  `ID_PK` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `VERSION` decimal(22,0) NOT NULL,
  `CREATED_TS` datetime(6) NOT NULL,
  `DELETED_TS` datetime(6) DEFAULT NULL,
  `UPDATED_TS` datetime(6) NOT NULL,
  `UPDATED_BY_USER` varchar(50) COLLATE utf8_bin NOT NULL,
  `UPDATED_BY_TXN_ID` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `ADDRESS_LINE_ONE` varchar(100) COLLATE utf8_bin NOT NULL,
  `ADDRESS_LINE_TWO` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `ADDRESS_LINE_THREE` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `ADDRESS_LINE_FOUR` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `STREET_NUMBER` varchar(30) COLLATE utf8_bin DEFAULT NULL,
  `STREET_NAME` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `HOUSE_NUMBER` varchar(30) COLLATE utf8_bin DEFAULT NULL,
  `BUILDING_NUMBER` varchar(30) COLLATE utf8_bin DEFAULT NULL,
  `COUNTY` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `CITY` varchar(100) COLLATE utf8_bin NOT NULL,
  `DISTRICT_ZONE` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `STATE_PROVINCE_REFKEY` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `COUNTRY_REFKEY` varchar(50) COLLATE utf8_bin NOT NULL,
  `POSTAL_CODE` varchar(30) COLLATE utf8_bin DEFAULT NULL,
  `NEAREST_LANDMARK` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `BOX_DESIGNATOR` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `BOX_IDENTIFIER` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `NEAREST_RAILWAY_STATION` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `NEAREST_AIRPORT` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `PHONETIC_ADDRESS_LINE_ONE` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `PHONETIC_ADDRESS_LINE_TWO` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `PHONETIC_ADDRESS_LINE_THREE` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `PHONETIC_ADDRESS_LINE_FOUR` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `PHONETIC_STREET_NAME` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `PHONETIC_COUNTY` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `PHONETIC_CITY` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `PHONETIC_DISTRICT_ZONE` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`AUDITLOG_ID_PK`),
  KEY `IDXAL_ADDRESS` (`ID_PK`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- Dumping data for table yug_owner.al_address: ~0 rows (approximately)
/*!40000 ALTER TABLE `al_address` DISABLE KEYS */;
/*!40000 ALTER TABLE `al_address` ENABLE KEYS */;

-- Dumping structure for table yug_owner.al_auth_roles_registry
CREATE TABLE IF NOT EXISTS `al_auth_roles_registry` (
  `AUDITLOG_ID_PK` varchar(50) COLLATE utf8_bin NOT NULL,
  `AUDITLOG_CREATED_TS` datetime(6) NOT NULL,
  `AUDITLOG_ACTION_CODE` varchar(1) COLLATE utf8_bin NOT NULL,
  `ID_PK` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `VERSION` decimal(22,0) NOT NULL,
  `CREATED_TS` datetime(6) NOT NULL,
  `DELETED_TS` datetime(6) DEFAULT NULL,
  `UPDATED_TS` datetime(6) NOT NULL,
  `UPDATED_BY_USER` varchar(50) COLLATE utf8_bin NOT NULL,
  `UPDATED_BY_TXN_ID` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `ROLE_NAME` varchar(400) COLLATE utf8_bin NOT NULL,
  `DESCRIPTION` varchar(150) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`AUDITLOG_ID_PK`),
  KEY `IDXAL_AUTH_ROLES_REGISTRY` (`ID_PK`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- Dumping data for table yug_owner.al_auth_roles_registry: ~0 rows (approximately)
/*!40000 ALTER TABLE `al_auth_roles_registry` DISABLE KEYS */;
/*!40000 ALTER TABLE `al_auth_roles_registry` ENABLE KEYS */;

-- Dumping structure for table yug_owner.al_auth_userrole_accesscontrol
CREATE TABLE IF NOT EXISTS `al_auth_userrole_accesscontrol` (
  `AUDITLOG_ID_PK` varchar(50) COLLATE utf8_bin NOT NULL,
  `AUDITLOG_CREATED_TS` datetime(6) NOT NULL,
  `AUDITLOG_ACTION_CODE` varchar(1) COLLATE utf8_bin NOT NULL,
  `ID_PK` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `VERSION` decimal(22,0) NOT NULL,
  `CREATED_TS` datetime(6) NOT NULL,
  `DELETED_TS` datetime(6) DEFAULT NULL,
  `UPDATED_TS` datetime(6) NOT NULL,
  `UPDATED_BY_USER` varchar(50) COLLATE utf8_bin NOT NULL,
  `UPDATED_BY_TXN_ID` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `PROFILE_TYPE` varchar(80) COLLATE utf8_bin NOT NULL,
  `AUTH_USER_ROLE_REGISTRY_IDPK` varchar(200) COLLATE utf8_bin NOT NULL,
  `CONFIG_TXN_REGISTRY_IDPK` varchar(200) COLLATE utf8_bin NOT NULL,
  `DESCRIPTION` varchar(600) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`AUDITLOG_ID_PK`),
  KEY `IDXAL_AUTH_USERROLE_ACCESSCONT` (`ID_PK`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- Dumping data for table yug_owner.al_auth_userrole_accesscontrol: ~0 rows (approximately)
/*!40000 ALTER TABLE `al_auth_userrole_accesscontrol` DISABLE KEYS */;
/*!40000 ALTER TABLE `al_auth_userrole_accesscontrol` ENABLE KEYS */;

-- Dumping structure for table yug_owner.al_auth_user_registry
CREATE TABLE IF NOT EXISTS `al_auth_user_registry` (
  `AUDITLOG_ID_PK` varchar(50) COLLATE utf8_bin NOT NULL,
  `AUDITLOG_CREATED_TS` datetime(6) NOT NULL,
  `AUDITLOG_ACTION_CODE` varchar(1) COLLATE utf8_bin NOT NULL,
  `ID_PK` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `VERSION` decimal(22,0) NOT NULL,
  `CREATED_TS` datetime(6) NOT NULL,
  `DELETED_TS` datetime(6) DEFAULT NULL,
  `UPDATED_TS` datetime(6) NOT NULL,
  `UPDATED_BY_USER` varchar(50) COLLATE utf8_bin NOT NULL,
  `UPDATED_BY_TXN_ID` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `USER_NAME` varchar(400) COLLATE utf8_bin NOT NULL,
  `DESCRIPTION` varchar(150) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`AUDITLOG_ID_PK`),
  KEY `IDXAL_AUTH_USER_REGISTRY` (`ID_PK`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- Dumping data for table yug_owner.al_auth_user_registry: ~0 rows (approximately)
/*!40000 ALTER TABLE `al_auth_user_registry` DISABLE KEYS */;
/*!40000 ALTER TABLE `al_auth_user_registry` ENABLE KEYS */;

-- Dumping structure for table yug_owner.al_auth_user_role_assoc
CREATE TABLE IF NOT EXISTS `al_auth_user_role_assoc` (
  `AUDITLOG_ID_PK` varchar(50) COLLATE utf8_bin NOT NULL,
  `AUDITLOG_CREATED_TS` datetime(6) NOT NULL,
  `AUDITLOG_ACTION_CODE` varchar(1) COLLATE utf8_bin NOT NULL,
  `ID_PK` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `VERSION` decimal(22,0) NOT NULL,
  `CREATED_TS` datetime(6) NOT NULL,
  `DELETED_TS` datetime(6) DEFAULT NULL,
  `UPDATED_TS` datetime(6) NOT NULL,
  `UPDATED_BY_USER` varchar(50) COLLATE utf8_bin NOT NULL,
  `UPDATED_BY_TXN_ID` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `AUTH_USER_REGISTRY_IDPK` varchar(50) COLLATE utf8_bin NOT NULL,
  `AUTH_ROLES_REGISTRY_IDPK` varchar(50) COLLATE utf8_bin NOT NULL,
  `DESCRIPTION` varchar(150) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`AUDITLOG_ID_PK`),
  KEY `IDXAL_AUTH_USER_ROLE_ASSOC` (`ID_PK`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- Dumping data for table yug_owner.al_auth_user_role_assoc: ~0 rows (approximately)
/*!40000 ALTER TABLE `al_auth_user_role_assoc` DISABLE KEYS */;
/*!40000 ALTER TABLE `al_auth_user_role_assoc` ENABLE KEYS */;

-- Dumping structure for table yug_owner.al_batch_entity_to_process
CREATE TABLE IF NOT EXISTS `al_batch_entity_to_process` (
  `AUDITLOG_ID_PK` varchar(50) COLLATE utf8_bin NOT NULL,
  `AUDITLOG_CREATED_TS` datetime(6) NOT NULL,
  `AUDITLOG_ACTION_CODE` varchar(1) COLLATE utf8_bin NOT NULL,
  `ID_PK` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `VERSION` decimal(22,0) NOT NULL,
  `CREATED_TS` datetime(6) NOT NULL,
  `DELETED_TS` datetime(6) DEFAULT NULL,
  `UPDATED_TS` datetime(6) NOT NULL,
  `UPDATED_BY_USER` varchar(50) COLLATE utf8_bin NOT NULL,
  `UPDATED_BY_TXN_ID` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `ENTITY_OBJECT_TYPE_REFKEY` varchar(50) COLLATE utf8_bin NOT NULL,
  `ENTITY_IDPK` varchar(50) COLLATE utf8_bin NOT NULL,
  `BATCH_PROPOSED_ACTION_REFKEY` varchar(50) COLLATE utf8_bin NOT NULL,
  `BATCH_ACTION_STATUS_REFKEY` varchar(50) COLLATE utf8_bin NOT NULL,
  `PROCESS_AFTER_TIMESTAMP` datetime(6) DEFAULT NULL,
  `PROCESS_BEFORE_TIMESTAMP` datetime(6) DEFAULT NULL,
  `ENTRY_MADE_BY_SUBSYSTEM_NAME` varchar(50) COLLATE utf8_bin NOT NULL,
  `COMMENTS` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`AUDITLOG_ID_PK`),
  KEY `IDXAL_BATCH_ENTITY_TO_PROCESS` (`ID_PK`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- Dumping data for table yug_owner.al_batch_entity_to_process: ~0 rows (approximately)
/*!40000 ALTER TABLE `al_batch_entity_to_process` DISABLE KEYS */;
/*!40000 ALTER TABLE `al_batch_entity_to_process` ENABLE KEYS */;

-- Dumping structure for table yug_owner.al_config_app_properties
CREATE TABLE IF NOT EXISTS `al_config_app_properties` (
  `AUDITLOG_ID_PK` varchar(50) COLLATE utf8_bin NOT NULL,
  `AUDITLOG_CREATED_TS` datetime(6) NOT NULL,
  `AUDITLOG_ACTION_CODE` varchar(1) COLLATE utf8_bin NOT NULL,
  `ID_PK` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `VERSION` decimal(22,0) NOT NULL,
  `CREATED_TS` datetime(6) NOT NULL,
  `DELETED_TS` datetime(6) DEFAULT NULL,
  `UPDATED_TS` datetime(6) NOT NULL,
  `UPDATED_BY_USER` varchar(50) COLLATE utf8_bin NOT NULL,
  `UPDATED_BY_TXN_ID` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `KEY` varchar(150) COLLATE utf8_bin NOT NULL,
  `VALUE` varchar(150) COLLATE utf8_bin NOT NULL,
  `VALUE_DEFAULT` varchar(150) COLLATE utf8_bin DEFAULT NULL,
  `DESCRIPTION` varchar(250) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`AUDITLOG_ID_PK`),
  KEY `IDXAL_CONFIG_APP_PROPERTIES` (`ID_PK`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- Dumping data for table yug_owner.al_config_app_properties: ~0 rows (approximately)
/*!40000 ALTER TABLE `al_config_app_properties` DISABLE KEYS */;
/*!40000 ALTER TABLE `al_config_app_properties` ENABLE KEYS */;

-- Dumping structure for table yug_owner.al_config_errorcode_registry
CREATE TABLE IF NOT EXISTS `al_config_errorcode_registry` (
  `AUDITLOG_ID_PK` varchar(50) COLLATE utf8_bin NOT NULL,
  `AUDITLOG_CREATED_TS` datetime(6) NOT NULL,
  `AUDITLOG_ACTION_CODE` varchar(1) COLLATE utf8_bin NOT NULL,
  `ID_PK` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `VERSION` decimal(22,0) NOT NULL,
  `CONFIG_LANGUAGE_CODE_KEY` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `ERROR_CODE` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `ERROR_MESSAGE` varchar(200) COLLATE utf8_bin DEFAULT NULL,
  `DESCRIPTION` varchar(200) COLLATE utf8_bin DEFAULT NULL,
  `CREATED_TS` datetime(6) DEFAULT NULL,
  `DELETED_TS` datetime(6) DEFAULT NULL,
  `UPDATED_TS` datetime(6) NOT NULL,
  `UPDATED_BY_USER` varchar(50) COLLATE utf8_bin NOT NULL,
  `UPDATED_BY_TXN_ID` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`AUDITLOG_ID_PK`),
  KEY `IDXAL_CONFIG_ERRORCODE_REGIS` (`ID_PK`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- Dumping data for table yug_owner.al_config_errorcode_registry: ~0 rows (approximately)
/*!40000 ALTER TABLE `al_config_errorcode_registry` DISABLE KEYS */;
/*!40000 ALTER TABLE `al_config_errorcode_registry` ENABLE KEYS */;

-- Dumping structure for table yug_owner.al_config_inquiry_levels
CREATE TABLE IF NOT EXISTS `al_config_inquiry_levels` (
  `AUDITLOG_ID_PK` varchar(50) COLLATE utf8_bin NOT NULL,
  `AUDITLOG_CREATED_TS` datetime(6) NOT NULL,
  `AUDITLOG_ACTION_CODE` varchar(1) COLLATE utf8_bin NOT NULL,
  `ID_PK` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `VERSION` decimal(22,0) NOT NULL,
  `CREATED_TS` datetime(6) NOT NULL,
  `DELETED_TS` datetime(6) DEFAULT NULL,
  `UPDATED_TS` datetime(6) NOT NULL,
  `UPDATED_BY_USER` varchar(50) COLLATE utf8_bin NOT NULL,
  `UPDATED_BY_TXN_ID` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `INQUIRY_LEVEL` varchar(20) COLLATE utf8_bin NOT NULL,
  `APPLICABLE_DOBJ` varchar(100) COLLATE utf8_bin NOT NULL,
  `CHILD_DOBJ` varchar(100) COLLATE utf8_bin NOT NULL,
  `DESCRIPTION` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`AUDITLOG_ID_PK`),
  KEY `IDXAL_CONFIG_INQUIRY_LEVELS` (`ID_PK`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- Dumping data for table yug_owner.al_config_inquiry_levels: ~0 rows (approximately)
/*!40000 ALTER TABLE `al_config_inquiry_levels` DISABLE KEYS */;
/*!40000 ALTER TABLE `al_config_inquiry_levels` ENABLE KEYS */;

-- Dumping structure for table yug_owner.al_config_language_code
CREATE TABLE IF NOT EXISTS `al_config_language_code` (
  `AUDITLOG_ID_PK` varchar(50) COLLATE utf8_bin NOT NULL,
  `AUDITLOG_CREATED_TS` datetime(6) NOT NULL,
  `AUDITLOG_ACTION_CODE` varchar(1) COLLATE utf8_bin NOT NULL,
  `ID_PK` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `VERSION` decimal(22,0) NOT NULL,
  `CREATED_TS` datetime(6) NOT NULL,
  `DELETED_TS` datetime(6) DEFAULT NULL,
  `UPDATED_TS` datetime(6) NOT NULL,
  `UPDATED_BY_USER` varchar(50) COLLATE utf8_bin NOT NULL,
  `UPDATED_BY_TXN_ID` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `KEY` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `VALUE` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `DESCRIPTION` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`AUDITLOG_ID_PK`),
  KEY `IDXAL_CONFIG_LANGUAGE_CODE` (`ID_PK`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- Dumping data for table yug_owner.al_config_language_code: ~0 rows (approximately)
/*!40000 ALTER TABLE `al_config_language_code` DISABLE KEYS */;
/*!40000 ALTER TABLE `al_config_language_code` ENABLE KEYS */;

-- Dumping structure for table yug_owner.al_config_txn_registry
CREATE TABLE IF NOT EXISTS `al_config_txn_registry` (
  `AUDITLOG_ID_PK` varchar(50) COLLATE utf8_bin NOT NULL,
  `AUDITLOG_CREATED_TS` datetime(6) NOT NULL,
  `AUDITLOG_ACTION_CODE` varchar(1) COLLATE utf8_bin NOT NULL,
  `ID_PK` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `VERSION` decimal(22,0) NOT NULL,
  `TXNSERVICE_NAME` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `TXNSERVICE_CLASS` varchar(200) COLLATE utf8_bin DEFAULT NULL,
  `TXNSERVICE_CLASSMETHOD` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `DESCRIPTION` varchar(200) COLLATE utf8_bin DEFAULT NULL,
  `CREATED_TS` datetime(6) NOT NULL,
  `DELETED_TS` datetime(6) DEFAULT NULL,
  `UPDATED_TS` datetime(6) NOT NULL,
  `UPDATED_BY_USER` varchar(50) COLLATE utf8_bin NOT NULL,
  `UPDATED_TXN_REF_ID` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`AUDITLOG_ID_PK`),
  KEY `IDXAL_CONFIG_TXN_REGISTRY` (`ID_PK`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- Dumping data for table yug_owner.al_config_txn_registry: ~0 rows (approximately)
/*!40000 ALTER TABLE `al_config_txn_registry` DISABLE KEYS */;
/*!40000 ALTER TABLE `al_config_txn_registry` ENABLE KEYS */;

-- Dumping structure for table yug_owner.al_corporationnames
CREATE TABLE IF NOT EXISTS `al_corporationnames` (
  `AUDITLOG_ID_PK` varchar(50) COLLATE utf8_bin NOT NULL,
  `AUDITLOG_CREATED_TS` datetime(6) NOT NULL,
  `AUDITLOG_ACTION_CODE` varchar(1) COLLATE utf8_bin NOT NULL,
  `ID_PK` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `VERSION` decimal(22,0) NOT NULL,
  `CREATED_TS` datetime(6) NOT NULL,
  `DELETED_TS` datetime(6) DEFAULT NULL,
  `UPDATED_TS` datetime(6) NOT NULL,
  `UPDATED_BY_USER` varchar(50) COLLATE utf8_bin NOT NULL,
  `UPDATED_BY_TXN_ID` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `LEGALENTITY_IDPK` varchar(50) COLLATE utf8_bin NOT NULL,
  `CORPORATION_NAME_TYPE_REFKEY` varchar(50) COLLATE utf8_bin NOT NULL,
  `CORPORATION_NAME` varchar(100) COLLATE utf8_bin NOT NULL,
  `SOURCE_SYSTEM_REFKEY` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `PHONETIC_CORPORATION_NAME` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`AUDITLOG_ID_PK`),
  KEY `IDXAL_CORPORATIONNAMES` (`ID_PK`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- Dumping data for table yug_owner.al_corporationnames: ~0 rows (approximately)
/*!40000 ALTER TABLE `al_corporationnames` DISABLE KEYS */;
/*!40000 ALTER TABLE `al_corporationnames` ENABLE KEYS */;

-- Dumping structure for table yug_owner.al_entity_group
CREATE TABLE IF NOT EXISTS `al_entity_group` (
  `AUDITLOG_ID_PK` varchar(50) COLLATE utf8_bin NOT NULL,
  `AUDITLOG_CREATED_TS` datetime(6) NOT NULL,
  `AUDITLOG_ACTION_CODE` varchar(1) COLLATE utf8_bin NOT NULL,
  `ID_PK` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `VERSION` decimal(22,0) NOT NULL,
  `CREATED_TS` datetime(6) NOT NULL,
  `DELETED_TS` datetime(6) DEFAULT NULL,
  `UPDATED_TS` datetime(6) NOT NULL,
  `UPDATED_BY_USER` varchar(50) COLLATE utf8_bin NOT NULL,
  `UPDATED_BY_TXN_ID` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `GROUP_TYPE_REFKEY` varchar(50) COLLATE utf8_bin NOT NULL,
  `GROUP_SUBTYPE_REFKEY` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `GROUP_NAME` varchar(50) COLLATE utf8_bin NOT NULL,
  `DESCRIPTION` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`AUDITLOG_ID_PK`),
  KEY `IDXAL_ENTITY_GROUP` (`ID_PK`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- Dumping data for table yug_owner.al_entity_group: ~0 rows (approximately)
/*!40000 ALTER TABLE `al_entity_group` DISABLE KEYS */;
/*!40000 ALTER TABLE `al_entity_group` ENABLE KEYS */;

-- Dumping structure for table yug_owner.al_entity_group_assoc
CREATE TABLE IF NOT EXISTS `al_entity_group_assoc` (
  `AUDITLOG_ID_PK` varchar(50) COLLATE utf8_bin NOT NULL,
  `AUDITLOG_CREATED_TS` datetime(6) NOT NULL,
  `AUDITLOG_ACTION_CODE` varchar(1) COLLATE utf8_bin NOT NULL,
  `ID_PK` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `VERSION` decimal(22,0) NOT NULL,
  `CREATED_TS` datetime(6) NOT NULL,
  `DELETED_TS` datetime(6) DEFAULT NULL,
  `UPDATED_TS` datetime(6) NOT NULL,
  `UPDATED_BY_USER` varchar(50) COLLATE utf8_bin NOT NULL,
  `UPDATED_BY_TXN_ID` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `ENTITY_OBJECT_TYPE_REFKEY` varchar(50) COLLATE utf8_bin NOT NULL,
  `ENTITY_IDPK` varchar(50) COLLATE utf8_bin NOT NULL,
  `ENTITY_GROUP_IDPK` varchar(50) COLLATE utf8_bin NOT NULL,
  `ASSOC_TYPE_REFKEY` varchar(50) COLLATE utf8_bin NOT NULL,
  `DESCRIPTION` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`AUDITLOG_ID_PK`),
  KEY `IDXAL_ENTITY_GROUP_ASSOC` (`ID_PK`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- Dumping data for table yug_owner.al_entity_group_assoc: ~0 rows (approximately)
/*!40000 ALTER TABLE `al_entity_group_assoc` DISABLE KEYS */;
/*!40000 ALTER TABLE `al_entity_group_assoc` ENABLE KEYS */;

-- Dumping structure for table yug_owner.al_inactive_le_registry
CREATE TABLE IF NOT EXISTS `al_inactive_le_registry` (
  `AUDITLOG_ID_PK` varchar(50) COLLATE utf8_bin NOT NULL,
  `AUDITLOG_CREATED_TS` datetime(6) NOT NULL,
  `AUDITLOG_ACTION_CODE` varchar(1) COLLATE utf8_bin NOT NULL,
  `ID_PK` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `VERSION` decimal(22,0) NOT NULL,
  `CREATED_TS` datetime(6) NOT NULL,
  `DELETED_TS` datetime(6) DEFAULT NULL,
  `UPDATED_TS` datetime(6) NOT NULL,
  `UPDATED_BY_USER` varchar(50) COLLATE utf8_bin NOT NULL,
  `UPDATED_BY_TXN_ID` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `LEGALENTITY_IDPK` varchar(50) COLLATE utf8_bin NOT NULL,
  `INACTIVATED_TS` datetime(6) NOT NULL,
  `INACTIVATION_REASON_REFKEY` varchar(50) COLLATE utf8_bin NOT NULL,
  `COMMENTS` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`AUDITLOG_ID_PK`),
  KEY `IDXAL_INACTIVE_LE_REGISTRY` (`ID_PK`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- Dumping data for table yug_owner.al_inactive_le_registry: ~0 rows (approximately)
/*!40000 ALTER TABLE `al_inactive_le_registry` DISABLE KEYS */;
/*!40000 ALTER TABLE `al_inactive_le_registry` ENABLE KEYS */;

-- Dumping structure for table yug_owner.al_legalentity
CREATE TABLE IF NOT EXISTS `al_legalentity` (
  `AUDITLOG_ID_PK` varchar(50) COLLATE utf8_bin NOT NULL,
  `AUDITLOG_CREATED_TS` datetime(6) NOT NULL,
  `AUDITLOG_ACTION_CODE` varchar(1) COLLATE utf8_bin NOT NULL,
  `ID_PK` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `VERSION` decimal(22,0) NOT NULL,
  `CREATED_TS` datetime(6) NOT NULL,
  `DELETED_TS` datetime(6) DEFAULT NULL,
  `UPDATED_TS` datetime(6) NOT NULL,
  `UPDATED_BY_USER` varchar(50) COLLATE utf8_bin NOT NULL,
  `UPDATED_BY_TXN_ID` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `DISPLAY_NAME` varchar(100) COLLATE utf8_bin NOT NULL,
  `ENTITY_OBJECT_TYPE_REFKEY` varchar(50) COLLATE utf8_bin NOT NULL,
  `CLASSIFICATION_CODE_REFKEY` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `IMPORTANCE_TYPE_REFKEY` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `LE_RATING_REFKEY` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `STATUS_TYPE_REFKEY` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `SOURCE_SYSTEM_REFKEY` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `ONBOARDING_DATE` datetime DEFAULT NULL,
  `OFFBOARDING_DATE` datetime DEFAULT NULL,
  `KYC_VERIFICATION_FLAG` varchar(1) COLLATE utf8_bin DEFAULT NULL,
  `DESCRIPTION` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `PHONETIC_DISPLAY_NAME` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`AUDITLOG_ID_PK`),
  KEY `IDXAL_LEGALENTITY` (`ID_PK`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- Dumping data for table yug_owner.al_legalentity: ~0 rows (approximately)
/*!40000 ALTER TABLE `al_legalentity` DISABLE KEYS */;
/*!40000 ALTER TABLE `al_legalentity` ENABLE KEYS */;

-- Dumping structure for table yug_owner.al_le_account_assoc
CREATE TABLE IF NOT EXISTS `al_le_account_assoc` (
  `AUDITLOG_ID_PK` varchar(50) COLLATE utf8_bin NOT NULL,
  `AUDITLOG_CREATED_TS` datetime(6) NOT NULL,
  `AUDITLOG_ACTION_CODE` varchar(1) COLLATE utf8_bin NOT NULL,
  `ID_PK` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `VERSION` decimal(22,0) NOT NULL,
  `CREATED_TS` datetime(6) NOT NULL,
  `DELETED_TS` datetime(6) DEFAULT NULL,
  `UPDATED_TS` datetime(6) NOT NULL,
  `UPDATED_BY_USER` varchar(50) COLLATE utf8_bin NOT NULL,
  `UPDATED_BY_TXN_ID` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `LEGALENTITY_IDPK` varchar(50) COLLATE utf8_bin NOT NULL,
  `LE_ROLETYPE_REFKEY` varchar(50) COLLATE utf8_bin NOT NULL,
  `ACCOUNT_IDPK` varchar(50) COLLATE utf8_bin NOT NULL,
  `ROLE_ACTIVATION_DATE` datetime(6) DEFAULT NULL,
  `ROLE_DEACTIVATION_DATE` datetime(6) DEFAULT NULL,
  `DEACTIVATION_REASON_REFKEY` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `AGREEMENT_TYPE_REFKEY` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `AGREEMENT_TYPE_DESCRIPTION` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`AUDITLOG_ID_PK`),
  KEY `IDXAL_LE_ACCOUNT_ASSOC` (`ID_PK`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- Dumping data for table yug_owner.al_le_account_assoc: ~0 rows (approximately)
/*!40000 ALTER TABLE `al_le_account_assoc` DISABLE KEYS */;
/*!40000 ALTER TABLE `al_le_account_assoc` ENABLE KEYS */;

-- Dumping structure for table yug_owner.al_le_address_assoc
CREATE TABLE IF NOT EXISTS `al_le_address_assoc` (
  `AUDITLOG_ID_PK` varchar(50) COLLATE utf8_bin NOT NULL,
  `AUDITLOG_CREATED_TS` datetime(6) NOT NULL,
  `AUDITLOG_ACTION_CODE` varchar(1) COLLATE utf8_bin NOT NULL,
  `ID_PK` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `VERSION` decimal(22,0) NOT NULL,
  `CREATED_TS` datetime(6) NOT NULL,
  `DELETED_TS` datetime(6) DEFAULT NULL,
  `UPDATED_TS` datetime(6) NOT NULL,
  `UPDATED_BY_USER` varchar(50) COLLATE utf8_bin NOT NULL,
  `UPDATED_BY_TXN_ID` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `LEGALENTITY_IDPK` varchar(50) COLLATE utf8_bin NOT NULL,
  `ADDRESS_IDPK` varchar(50) COLLATE utf8_bin NOT NULL,
  `ADDRESS_TYPE_REFKEY` varchar(50) COLLATE utf8_bin NOT NULL,
  `ADDRESS_SUBTYPE_REFKEY` varchar(50) COLLATE utf8_bin NOT NULL,
  `PREFERRED_FLAG` varchar(1) COLLATE utf8_bin DEFAULT NULL,
  `SOLICITATION_FLAG` varchar(1) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`AUDITLOG_ID_PK`),
  KEY `IDXAL_LE_ADDRESS_ASSOC` (`ID_PK`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- Dumping data for table yug_owner.al_le_address_assoc: ~0 rows (approximately)
/*!40000 ALTER TABLE `al_le_address_assoc` DISABLE KEYS */;
/*!40000 ALTER TABLE `al_le_address_assoc` ENABLE KEYS */;

-- Dumping structure for table yug_owner.al_le_corporation
CREATE TABLE IF NOT EXISTS `al_le_corporation` (
  `AUDITLOG_ID_PK` varchar(50) COLLATE utf8_bin NOT NULL,
  `AUDITLOG_CREATED_TS` datetime(6) NOT NULL,
  `AUDITLOG_ACTION_CODE` varchar(1) COLLATE utf8_bin NOT NULL,
  `LEGALENTITY_IDPK` varchar(50) COLLATE utf8_bin NOT NULL,
  `VERSION` decimal(22,0) NOT NULL,
  `CREATED_TS` datetime(6) NOT NULL,
  `DELETED_TS` datetime(6) DEFAULT NULL,
  `UPDATED_TS` datetime(6) NOT NULL,
  `UPDATED_BY_USER` varchar(50) COLLATE utf8_bin NOT NULL,
  `UPDATED_BY_TXN_ID` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `CLASSIFICATION_CODE_REFKEY` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `INDUSTRY_CODE_REFKEY` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `GOVT_REGISTRATION_DATE` datetime DEFAULT NULL,
  `COUNTRY_REGISTRATION_REFKEY` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `NOTPROFIT_FLAG` varchar(1) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`AUDITLOG_ID_PK`),
  KEY `IDXAL_LE_CORPORATION` (`LEGALENTITY_IDPK`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- Dumping data for table yug_owner.al_le_corporation: ~0 rows (approximately)
/*!40000 ALTER TABLE `al_le_corporation` DISABLE KEYS */;
/*!40000 ALTER TABLE `al_le_corporation` ENABLE KEYS */;

-- Dumping structure for table yug_owner.al_le_identifier_kyc_registry
CREATE TABLE IF NOT EXISTS `al_le_identifier_kyc_registry` (
  `AUDITLOG_ID_PK` varchar(50) COLLATE utf8_bin NOT NULL,
  `AUDITLOG_CREATED_TS` datetime(6) NOT NULL,
  `AUDITLOG_ACTION_CODE` varchar(1) COLLATE utf8_bin NOT NULL,
  `ID_PK` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `VERSION` decimal(22,0) NOT NULL,
  `CREATED_TS` datetime(6) NOT NULL,
  `DELETED_TS` datetime(6) DEFAULT NULL,
  `UPDATED_TS` datetime(6) NOT NULL,
  `UPDATED_BY_USER` varchar(50) COLLATE utf8_bin NOT NULL,
  `UPDATED_BY_TXN_ID` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `IDENTIFICATION_TYPE_REFKEY` varchar(50) COLLATE utf8_bin NOT NULL,
  `IDENTIFICATION_NUMBER` varchar(50) COLLATE utf8_bin NOT NULL,
  `LEGALENTITY_IDPK` varchar(50) COLLATE utf8_bin NOT NULL,
  `DOCUMENT` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `ISSUED_BY` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `ID_CONSIDERED_FOR_KYC_FLAG` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `ISSUED_DATE` datetime DEFAULT NULL,
  `SOURCE_SYSTEM_REFKEY` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `IDENTITY_DESCRIPTION` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`AUDITLOG_ID_PK`),
  KEY `IDXAL_LE_IDENTIFIER_KYC_REGIS` (`ID_PK`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- Dumping data for table yug_owner.al_le_identifier_kyc_registry: ~0 rows (approximately)
/*!40000 ALTER TABLE `al_le_identifier_kyc_registry` DISABLE KEYS */;
/*!40000 ALTER TABLE `al_le_identifier_kyc_registry` ENABLE KEYS */;

-- Dumping structure for table yug_owner.al_le_person
CREATE TABLE IF NOT EXISTS `al_le_person` (
  `AUDITLOG_ID_PK` varchar(50) COLLATE utf8_bin NOT NULL,
  `AUDITLOG_CREATED_TS` datetime(6) NOT NULL,
  `AUDITLOG_ACTION_CODE` varchar(1) COLLATE utf8_bin NOT NULL,
  `LEGALENTITY_IDPK` varchar(50) COLLATE utf8_bin NOT NULL,
  `VERSION` decimal(22,0) NOT NULL,
  `CREATED_TS` datetime(6) NOT NULL,
  `DELETED_TS` datetime(6) DEFAULT NULL,
  `UPDATED_TS` datetime(6) NOT NULL,
  `UPDATED_BY_USER` varchar(50) COLLATE utf8_bin NOT NULL,
  `UPDATED_BY_TXN_ID` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `PERSON_TYPE_REFKEY` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `GENDER_REFKEY` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `DATE_OF_BIRTH` datetime(6) DEFAULT NULL,
  `COUNTRY_OF_BIRTH__REFKEY` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `COUNTRY_CITIZENSHIP_REFKEY` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `COUNTRY_OF_DOMICILE__REFKEY` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `MARITAL_STATUS` varchar(30) COLLATE utf8_bin DEFAULT NULL,
  `HIGHEST_EDU_QUAL_REFKEY` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `IS_DECEASED_FLAG` varchar(1) COLLATE utf8_bin DEFAULT NULL,
  `DECEASED_DATE` datetime DEFAULT NULL,
  `IS_HANDICAPPED_FLAG` varchar(1) COLLATE utf8_bin DEFAULT NULL,
  `HANDICAPPED_SINCE_DATE` datetime DEFAULT NULL,
  `NUMBER_OF_DEPENDENTS` decimal(22,0) DEFAULT NULL,
  `NUMBER_OF_CHILDREN` decimal(22,0) DEFAULT NULL,
  `PREFERRED_LANGUAGE_REFKEY` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`AUDITLOG_ID_PK`),
  KEY `IDXAL_LE_PERSON` (`LEGALENTITY_IDPK`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- Dumping data for table yug_owner.al_le_person: ~0 rows (approximately)
/*!40000 ALTER TABLE `al_le_person` DISABLE KEYS */;
/*!40000 ALTER TABLE `al_le_person` ENABLE KEYS */;

-- Dumping structure for table yug_owner.al_le_phone_assoc
CREATE TABLE IF NOT EXISTS `al_le_phone_assoc` (
  `AUDITLOG_ID_PK` varchar(50) COLLATE utf8_bin NOT NULL,
  `AUDITLOG_CREATED_TS` datetime(6) NOT NULL,
  `AUDITLOG_ACTION_CODE` varchar(1) COLLATE utf8_bin NOT NULL,
  `ID_PK` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `VERSION` decimal(22,0) NOT NULL,
  `CREATED_TS` datetime(6) NOT NULL,
  `DELETED_TS` datetime(6) DEFAULT NULL,
  `UPDATED_TS` datetime(6) NOT NULL,
  `UPDATED_BY_USER` varchar(50) COLLATE utf8_bin NOT NULL,
  `UPDATED_BY_TXN_ID` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `LEGALENTITY_IDPK` varchar(50) COLLATE utf8_bin NOT NULL,
  `PHONE_TYPE_REFKEY` varchar(50) COLLATE utf8_bin NOT NULL,
  `PHONE_SUBTYPE_REFKEY` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `PREFERRED_FLAG` varchar(1) COLLATE utf8_bin DEFAULT NULL,
  `PHONE_NUMBER` varchar(30) COLLATE utf8_bin NOT NULL,
  `PHONE_STANDARDIZED_IDPK` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`AUDITLOG_ID_PK`),
  KEY `IDXAL_LE_PHONE_ASSOC` (`ID_PK`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- Dumping data for table yug_owner.al_le_phone_assoc: ~0 rows (approximately)
/*!40000 ALTER TABLE `al_le_phone_assoc` DISABLE KEYS */;
/*!40000 ALTER TABLE `al_le_phone_assoc` ENABLE KEYS */;

-- Dumping structure for table yug_owner.al_le_preferences
CREATE TABLE IF NOT EXISTS `al_le_preferences` (
  `AUDITLOG_ID_PK` varchar(50) COLLATE utf8_bin NOT NULL,
  `AUDITLOG_CREATED_TS` datetime(6) NOT NULL,
  `AUDITLOG_ACTION_CODE` varchar(1) COLLATE utf8_bin NOT NULL,
  `ID_PK` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `VERSION` decimal(22,0) NOT NULL,
  `CREATED_TS` datetime(6) NOT NULL,
  `DELETED_TS` datetime(6) DEFAULT NULL,
  `UPDATED_TS` datetime(6) NOT NULL,
  `UPDATED_BY_USER` varchar(50) COLLATE utf8_bin NOT NULL,
  `UPDATED_BY_TXN_ID` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `LEGALENTITY_IDPK` varchar(50) COLLATE utf8_bin NOT NULL,
  `PREFERENCE_TYPE_REFKEY` varchar(50) COLLATE utf8_bin NOT NULL,
  `PREF_FLAG` varchar(1) COLLATE utf8_bin DEFAULT NULL,
  `PREF_DESCRIPTION` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`AUDITLOG_ID_PK`),
  KEY `IDXAL_LE_PREFERENCES` (`ID_PK`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- Dumping data for table yug_owner.al_le_preferences: ~0 rows (approximately)
/*!40000 ALTER TABLE `al_le_preferences` DISABLE KEYS */;
/*!40000 ALTER TABLE `al_le_preferences` ENABLE KEYS */;

-- Dumping structure for table yug_owner.al_le_property_assoc
CREATE TABLE IF NOT EXISTS `al_le_property_assoc` (
  `AUDITLOG_ID_PK` varchar(50) COLLATE utf8_bin NOT NULL,
  `AUDITLOG_CREATED_TS` datetime(6) NOT NULL,
  `AUDITLOG_ACTION_CODE` varchar(1) COLLATE utf8_bin NOT NULL,
  `ID_PK` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `VERSION` decimal(22,0) NOT NULL,
  `CREATED_TS` datetime(6) NOT NULL,
  `DELETED_TS` datetime(6) DEFAULT NULL,
  `UPDATED_TS` datetime(6) NOT NULL,
  `UPDATED_BY_USER` varchar(50) COLLATE utf8_bin NOT NULL,
  `UPDATED_BY_TXN_ID` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `PROPERTY_IDPK` varchar(50) COLLATE utf8_bin NOT NULL,
  `LEGALENTITY_IDPK` varchar(50) COLLATE utf8_bin NOT NULL,
  `PROPERTY_LE_RELTYPE_REFKEY` varchar(50) COLLATE utf8_bin NOT NULL,
  `DESCRIPTION` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`AUDITLOG_ID_PK`),
  KEY `IDXAL_LE_PROPERTY_ASSOC` (`ID_PK`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- Dumping data for table yug_owner.al_le_property_assoc: ~0 rows (approximately)
/*!40000 ALTER TABLE `al_le_property_assoc` DISABLE KEYS */;
/*!40000 ALTER TABLE `al_le_property_assoc` ENABLE KEYS */;

-- Dumping structure for table yug_owner.al_le_system_keys_registry
CREATE TABLE IF NOT EXISTS `al_le_system_keys_registry` (
  `AUDITLOG_ID_PK` varchar(50) COLLATE utf8_bin NOT NULL,
  `AUDITLOG_CREATED_TS` datetime(6) NOT NULL,
  `AUDITLOG_ACTION_CODE` varchar(1) COLLATE utf8_bin NOT NULL,
  `ID_PK` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `VERSION` decimal(22,0) NOT NULL,
  `CREATED_TS` datetime(6) NOT NULL,
  `DELETED_TS` datetime(6) DEFAULT NULL,
  `UPDATED_TS` datetime(6) NOT NULL,
  `UPDATED_BY_USER` varchar(50) COLLATE utf8_bin NOT NULL,
  `UPDATED_BY_TXN_ID` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `SOURCE_SYSTEM_REFKEY` varchar(50) COLLATE utf8_bin NOT NULL,
  `REFERENCE_ID` varchar(50) COLLATE utf8_bin NOT NULL,
  `LEGALENTITY_IDPK` varchar(50) COLLATE utf8_bin NOT NULL,
  `STATUS_IN_SOURCE_REFKEY` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `DESCRIPTION` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`AUDITLOG_ID_PK`),
  KEY `IDXAL_LE_SYSTEM_KEYS_REGISTRY` (`ID_PK`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- Dumping data for table yug_owner.al_le_system_keys_registry: ~0 rows (approximately)
/*!40000 ALTER TABLE `al_le_system_keys_registry` DISABLE KEYS */;
/*!40000 ALTER TABLE `al_le_system_keys_registry` ENABLE KEYS */;

-- Dumping structure for table yug_owner.al_le_to_le_relationship
CREATE TABLE IF NOT EXISTS `al_le_to_le_relationship` (
  `AUDITLOG_ID_PK` varchar(50) COLLATE utf8_bin NOT NULL,
  `AUDITLOG_CREATED_TS` datetime(6) NOT NULL,
  `AUDITLOG_ACTION_CODE` varchar(1) COLLATE utf8_bin NOT NULL,
  `ID_PK` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `VERSION` decimal(22,0) NOT NULL,
  `CREATED_TS` datetime(6) NOT NULL,
  `DELETED_TS` datetime(6) DEFAULT NULL,
  `UPDATED_TS` datetime(6) NOT NULL,
  `UPDATED_BY_USER` varchar(50) COLLATE utf8_bin NOT NULL,
  `UPDATED_BY_TXN_ID` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `FROM_LEGALENTITY_IDPK` varchar(50) COLLATE utf8_bin NOT NULL,
  `TO_LEGALENTITY_IDPK` varchar(50) COLLATE utf8_bin NOT NULL,
  `LE_RELATIONSHIP_TYPE_REFKEY` varchar(50) COLLATE utf8_bin NOT NULL,
  `RELATIONSHIP_STATUS_REFKEY` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `RELATIONSHIP_DESCRIPTION` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`AUDITLOG_ID_PK`),
  KEY `IDXAL_LE_TO_LE_RELATIONSHIP` (`ID_PK`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- Dumping data for table yug_owner.al_le_to_le_relationship: ~0 rows (approximately)
/*!40000 ALTER TABLE `al_le_to_le_relationship` DISABLE KEYS */;
/*!40000 ALTER TABLE `al_le_to_le_relationship` ENABLE KEYS */;

-- Dumping structure for table yug_owner.al_le_vehicle_assoc
CREATE TABLE IF NOT EXISTS `al_le_vehicle_assoc` (
  `AUDITLOG_ID_PK` varchar(50) COLLATE utf8_bin NOT NULL,
  `AUDITLOG_CREATED_TS` datetime(6) NOT NULL,
  `AUDITLOG_ACTION_CODE` varchar(1) COLLATE utf8_bin NOT NULL,
  `ID_PK` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `VERSION` decimal(22,0) NOT NULL,
  `CREATED_TS` datetime(6) NOT NULL,
  `DELETED_TS` datetime(6) DEFAULT NULL,
  `UPDATED_TS` datetime(6) NOT NULL,
  `UPDATED_BY_USER` varchar(50) COLLATE utf8_bin NOT NULL,
  `UPDATED_BY_TXN_ID` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `LEGALENTITY_IDPK` varchar(50) COLLATE utf8_bin NOT NULL,
  `LE_ROLETYPE_REFKEY` varchar(50) COLLATE utf8_bin NOT NULL,
  `ACCOUNT_IDPK` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `ROLE_ACTIVATION_DATE` datetime(6) DEFAULT NULL,
  `ROLE_DEACTIVATION_DATE` datetime(6) DEFAULT NULL,
  `DEACTIVATION_REASON_REFKEY` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `AGREEMENT_TYPE_REFKEY` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `AGREEMENT_TYPE_DESCRIPTION` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `VEHICLE_IDPK` varchar(50) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`AUDITLOG_ID_PK`),
  KEY `IDXAL_LE_VEHICLE_ASSOC` (`ID_PK`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- Dumping data for table yug_owner.al_le_vehicle_assoc: ~0 rows (approximately)
/*!40000 ALTER TABLE `al_le_vehicle_assoc` DISABLE KEYS */;
/*!40000 ALTER TABLE `al_le_vehicle_assoc` ENABLE KEYS */;

-- Dumping structure for table yug_owner.al_match_candidate_le_registry
CREATE TABLE IF NOT EXISTS `al_match_candidate_le_registry` (
  `AUDITLOG_ID_PK` varchar(50) COLLATE utf8_bin NOT NULL,
  `AUDITLOG_CREATED_TS` datetime(6) NOT NULL,
  `AUDITLOG_ACTION_CODE` varchar(1) COLLATE utf8_bin NOT NULL,
  `ID_PK` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `VERSION` decimal(22,0) NOT NULL,
  `CREATED_TS` datetime(6) NOT NULL,
  `DELETED_TS` datetime(6) DEFAULT NULL,
  `UPDATED_TS` datetime(6) NOT NULL,
  `UPDATED_BY_USER` varchar(50) COLLATE utf8_bin NOT NULL,
  `UPDATED_BY_TXN_ID` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `LEGALENTITY_IDPK` varchar(50) COLLATE utf8_bin NOT NULL,
  `CANDIDATE_LEGALENTITYIDPK` varchar(50) COLLATE utf8_bin NOT NULL,
  `MATCH_PATTERN` varchar(50) COLLATE utf8_bin NOT NULL,
  `MATCH_PROPOSED_ACTION_REFKEY` varchar(50) COLLATE utf8_bin NOT NULL,
  `MATCH_ACTIONSTATUS_REFKEY` varchar(50) COLLATE utf8_bin NOT NULL,
  `MATCH_PERCENTAGE_DESCRIPTION` varchar(500) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`AUDITLOG_ID_PK`),
  KEY `IDXAL_MATCH_CANDIDATE_LE_REGIS` (`ID_PK`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- Dumping data for table yug_owner.al_match_candidate_le_registry: ~0 rows (approximately)
/*!40000 ALTER TABLE `al_match_candidate_le_registry` DISABLE KEYS */;
/*!40000 ALTER TABLE `al_match_candidate_le_registry` ENABLE KEYS */;

-- Dumping structure for table yug_owner.al_match_merged_le_assoc
CREATE TABLE IF NOT EXISTS `al_match_merged_le_assoc` (
  `AUDITLOG_ID_PK` varchar(50) COLLATE utf8_bin NOT NULL,
  `AUDITLOG_CREATED_TS` datetime(6) NOT NULL,
  `AUDITLOG_ACTION_CODE` varchar(1) COLLATE utf8_bin NOT NULL,
  `ID_PK` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `VERSION` decimal(22,0) NOT NULL,
  `CREATED_TS` datetime(6) NOT NULL,
  `DELETED_TS` datetime(6) DEFAULT NULL,
  `UPDATED_TS` datetime(6) NOT NULL,
  `UPDATED_BY_USER` varchar(50) COLLATE utf8_bin NOT NULL,
  `UPDATED_BY_TXN_ID` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `SURVIVOR_LEGALENTITY_IDPK` varchar(50) COLLATE utf8_bin NOT NULL,
  `MERGED_LEGALENTITY_IDPK` varchar(50) COLLATE utf8_bin NOT NULL,
  `MERGE_REASON_REFKEY` varchar(50) COLLATE utf8_bin NOT NULL,
  `COMMENTS` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`AUDITLOG_ID_PK`),
  KEY `IDXAL_MATCH_MERGED_LE_ASSOC` (`ID_PK`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- Dumping data for table yug_owner.al_match_merged_le_assoc: ~0 rows (approximately)
/*!40000 ALTER TABLE `al_match_merged_le_assoc` DISABLE KEYS */;
/*!40000 ALTER TABLE `al_match_merged_le_assoc` ENABLE KEYS */;

-- Dumping structure for table yug_owner.al_miscellaneous_info
CREATE TABLE IF NOT EXISTS `al_miscellaneous_info` (
  `AUDITLOG_ID_PK` varchar(50) COLLATE utf8_bin NOT NULL,
  `AUDITLOG_CREATED_TS` datetime(6) NOT NULL,
  `AUDITLOG_ACTION_CODE` varchar(1) COLLATE utf8_bin NOT NULL,
  `ID_PK` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `VERSION` decimal(22,0) NOT NULL,
  `CREATED_TS` datetime(6) NOT NULL,
  `DELETED_TS` datetime(6) DEFAULT NULL,
  `UPDATED_TS` datetime(6) NOT NULL,
  `UPDATED_BY_USER` varchar(50) COLLATE utf8_bin NOT NULL,
  `UPDATED_BY_TXN_ID` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `ENTITY_OBJECT_TYPE_REFKEY` varchar(50) COLLATE utf8_bin NOT NULL,
  `ENTITY_IDPK` varchar(50) COLLATE utf8_bin NOT NULL,
  `NAME1` varchar(50) COLLATE utf8_bin NOT NULL,
  `VALUE1` varchar(50) COLLATE utf8_bin NOT NULL,
  `NAME2` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `VALUE2` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `NAME3` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `VALUE3` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `NAME4` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `VALUE4` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `NAME5` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `VALUE5` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `NAME6` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `VALUE6` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `NAME7` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `VALUE7` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `NAME8` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `VALUE8` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `NAME9` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `VALUE9` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `NAME10` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `VALUE10` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`AUDITLOG_ID_PK`),
  KEY `IDXAL_MISCELLANEOUS_INFO` (`ID_PK`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- Dumping data for table yug_owner.al_miscellaneous_info: ~0 rows (approximately)
/*!40000 ALTER TABLE `al_miscellaneous_info` DISABLE KEYS */;
/*!40000 ALTER TABLE `al_miscellaneous_info` ENABLE KEYS */;

-- Dumping structure for table yug_owner.al_personnames
CREATE TABLE IF NOT EXISTS `al_personnames` (
  `AUDITLOG_ID_PK` varchar(50) COLLATE utf8_bin NOT NULL,
  `AUDITLOG_CREATED_TS` datetime(6) NOT NULL,
  `AUDITLOG_ACTION_CODE` varchar(1) COLLATE utf8_bin NOT NULL,
  `ID_PK` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `VERSION` decimal(22,0) NOT NULL,
  `CREATED_TS` datetime(6) NOT NULL,
  `DELETED_TS` datetime(6) DEFAULT NULL,
  `UPDATED_TS` datetime(6) NOT NULL,
  `UPDATED_BY_USER` varchar(50) COLLATE utf8_bin NOT NULL,
  `UPDATED_BY_TXN_ID` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `LEGALENTITY_IDPK` varchar(50) COLLATE utf8_bin NOT NULL,
  `PERSONNAME_TYPE_REFKEY` varchar(50) COLLATE utf8_bin NOT NULL,
  `PREFIX_NAME_REFKEY` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `PREFIX_MISC` varchar(30) COLLATE utf8_bin DEFAULT NULL,
  `NAME_ONE` varchar(50) COLLATE utf8_bin NOT NULL,
  `NAME_TWO` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `NAME_THREE` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `NAME_FOUR` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `LAST_NAME` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `NICK_NAME` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `POPULAR_NAME` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `SUFFIX_NAME_REFKEY` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `SUFFIX_MISC` varchar(30) COLLATE utf8_bin DEFAULT NULL,
  `NAME_STANDARDISED_FLAG` varchar(1) COLLATE utf8_bin DEFAULT NULL,
  `SOURCE_SYSTEM_REFKEY` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `PHONETIC_NAME_ONE` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `PHONETIC_NAME_TWO` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `PHONETIC_NAME_THREE` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `PHONETIC_LAST_NAME` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`AUDITLOG_ID_PK`),
  KEY `IDXAL_PERSONNAMES` (`ID_PK`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- Dumping data for table yug_owner.al_personnames: ~0 rows (approximately)
/*!40000 ALTER TABLE `al_personnames` DISABLE KEYS */;
/*!40000 ALTER TABLE `al_personnames` ENABLE KEYS */;

-- Dumping structure for table yug_owner.al_phone_standardized
CREATE TABLE IF NOT EXISTS `al_phone_standardized` (
  `AUDITLOG_ID_PK` varchar(50) COLLATE utf8_bin NOT NULL,
  `AUDITLOG_CREATED_TS` datetime(6) NOT NULL,
  `AUDITLOG_ACTION_CODE` varchar(1) COLLATE utf8_bin NOT NULL,
  `ID_PK` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `VERSION` decimal(22,0) NOT NULL,
  `CREATED_TS` datetime(6) NOT NULL,
  `DELETED_TS` datetime(6) DEFAULT NULL,
  `UPDATED_TS` datetime(6) NOT NULL,
  `UPDATED_BY_USER` varchar(50) COLLATE utf8_bin NOT NULL,
  `UPDATED_BY_TXN_ID` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `ITU_COUNTRY_CALLING_CODE` varchar(4) COLLATE utf8_bin DEFAULT NULL,
  `AREA_CODE` varchar(6) COLLATE utf8_bin DEFAULT NULL,
  `EXCHANGE` varchar(6) COLLATE utf8_bin DEFAULT NULL,
  `PHONE_NUMBER` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `EXTENSION` varchar(8) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`AUDITLOG_ID_PK`),
  KEY `IDXAL_PHONE_STANDARDIZED` (`ID_PK`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- Dumping data for table yug_owner.al_phone_standardized: ~0 rows (approximately)
/*!40000 ALTER TABLE `al_phone_standardized` DISABLE KEYS */;
/*!40000 ALTER TABLE `al_phone_standardized` ENABLE KEYS */;

-- Dumping structure for table yug_owner.al_property
CREATE TABLE IF NOT EXISTS `al_property` (
  `AUDITLOG_ID_PK` varchar(50) COLLATE utf8_bin NOT NULL,
  `AUDITLOG_CREATED_TS` datetime(6) NOT NULL,
  `AUDITLOG_ACTION_CODE` varchar(1) COLLATE utf8_bin NOT NULL,
  `ID_PK` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `VERSION` decimal(22,0) NOT NULL,
  `CREATED_TS` datetime(6) NOT NULL,
  `DELETED_TS` datetime(6) DEFAULT NULL,
  `UPDATED_TS` datetime(6) NOT NULL,
  `UPDATED_BY_USER` varchar(50) COLLATE utf8_bin NOT NULL,
  `UPDATED_BY_TXN_ID` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `PROPERTY_NAME` varchar(100) COLLATE utf8_bin NOT NULL,
  `ADDRESS_IDPK` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`AUDITLOG_ID_PK`),
  KEY `IDXAL_PROPERTY` (`ID_PK`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- Dumping data for table yug_owner.al_property: ~0 rows (approximately)
/*!40000 ALTER TABLE `al_property` DISABLE KEYS */;
/*!40000 ALTER TABLE `al_property` ENABLE KEYS */;

-- Dumping structure for table yug_owner.al_ref_account_mdm_status
CREATE TABLE IF NOT EXISTS `al_ref_account_mdm_status` (
  `AUDITLOG_ID_PK` varchar(50) COLLATE utf8_bin NOT NULL,
  `AUDITLOG_CREATED_TS` datetime(6) NOT NULL,
  `AUDITLOG_ACTION_CODE` varchar(1) COLLATE utf8_bin NOT NULL,
  `ID_PK` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `VERSION` decimal(22,0) NOT NULL,
  `CREATED_TS` datetime(6) NOT NULL,
  `DELETED_TS` datetime(6) DEFAULT NULL,
  `UPDATED_TS` datetime(6) NOT NULL,
  `UPDATED_BY_USER` varchar(50) COLLATE utf8_bin NOT NULL,
  `UPDATED_BY_TXN_ID` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `CONFIG_LANGUAGE_CODE_KEY` varchar(50) COLLATE utf8_bin NOT NULL,
  `KEY` varchar(50) COLLATE utf8_bin NOT NULL,
  `VALUE` varchar(50) COLLATE utf8_bin NOT NULL,
  `DESCRIPTION` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`AUDITLOG_ID_PK`),
  KEY `IDXAL_REF_ACCOUNT_MDM_STATUS` (`ID_PK`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- Dumping data for table yug_owner.al_ref_account_mdm_status: ~0 rows (approximately)
/*!40000 ALTER TABLE `al_ref_account_mdm_status` DISABLE KEYS */;
/*!40000 ALTER TABLE `al_ref_account_mdm_status` ENABLE KEYS */;

-- Dumping structure for table yug_owner.al_ref_account_source_status
CREATE TABLE IF NOT EXISTS `al_ref_account_source_status` (
  `AUDITLOG_ID_PK` varchar(50) COLLATE utf8_bin NOT NULL,
  `AUDITLOG_CREATED_TS` datetime(6) NOT NULL,
  `AUDITLOG_ACTION_CODE` varchar(1) COLLATE utf8_bin NOT NULL,
  `ID_PK` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `VERSION` decimal(22,0) NOT NULL,
  `CREATED_TS` datetime(6) NOT NULL,
  `DELETED_TS` datetime(6) DEFAULT NULL,
  `UPDATED_TS` datetime(6) NOT NULL,
  `UPDATED_BY_USER` varchar(50) COLLATE utf8_bin NOT NULL,
  `UPDATED_BY_TXN_ID` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `CONFIG_LANGUAGE_CODE_KEY` varchar(50) COLLATE utf8_bin NOT NULL,
  `KEY` varchar(50) COLLATE utf8_bin NOT NULL,
  `VALUE` varchar(50) COLLATE utf8_bin NOT NULL,
  `DESCRIPTION` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`AUDITLOG_ID_PK`),
  KEY `IDXAL_REF_ACCOUNT_SOURCE_STA` (`ID_PK`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- Dumping data for table yug_owner.al_ref_account_source_status: ~0 rows (approximately)
/*!40000 ALTER TABLE `al_ref_account_source_status` DISABLE KEYS */;
/*!40000 ALTER TABLE `al_ref_account_source_status` ENABLE KEYS */;

-- Dumping structure for table yug_owner.al_ref_address_subtype
CREATE TABLE IF NOT EXISTS `al_ref_address_subtype` (
  `AUDITLOG_ID_PK` varchar(50) COLLATE utf8_bin NOT NULL,
  `AUDITLOG_CREATED_TS` datetime(6) NOT NULL,
  `AUDITLOG_ACTION_CODE` varchar(1) COLLATE utf8_bin NOT NULL,
  `ID_PK` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `VERSION` decimal(22,0) NOT NULL,
  `CREATED_TS` datetime(6) NOT NULL,
  `DELETED_TS` datetime(6) DEFAULT NULL,
  `UPDATED_TS` datetime(6) NOT NULL,
  `UPDATED_BY_USER` varchar(50) COLLATE utf8_bin NOT NULL,
  `UPDATED_BY_TXN_ID` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `CONFIG_LANGUAGE_CODE_KEY` varchar(50) COLLATE utf8_bin NOT NULL,
  `KEY` varchar(50) COLLATE utf8_bin NOT NULL,
  `VALUE` varchar(50) COLLATE utf8_bin NOT NULL,
  `DESCRIPTION` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`AUDITLOG_ID_PK`),
  KEY `IDXAL_REF_ADDRESS_SUBTYPE` (`ID_PK`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- Dumping data for table yug_owner.al_ref_address_subtype: ~0 rows (approximately)
/*!40000 ALTER TABLE `al_ref_address_subtype` DISABLE KEYS */;
/*!40000 ALTER TABLE `al_ref_address_subtype` ENABLE KEYS */;

-- Dumping structure for table yug_owner.al_ref_address_type
CREATE TABLE IF NOT EXISTS `al_ref_address_type` (
  `AUDITLOG_ID_PK` varchar(50) COLLATE utf8_bin NOT NULL,
  `AUDITLOG_CREATED_TS` datetime(6) NOT NULL,
  `AUDITLOG_ACTION_CODE` varchar(1) COLLATE utf8_bin NOT NULL,
  `ID_PK` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `VERSION` decimal(22,0) NOT NULL,
  `CREATED_TS` datetime(6) NOT NULL,
  `DELETED_TS` datetime(6) DEFAULT NULL,
  `UPDATED_TS` datetime(6) NOT NULL,
  `UPDATED_BY_USER` varchar(50) COLLATE utf8_bin NOT NULL,
  `UPDATED_BY_TXN_ID` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `CONFIG_LANGUAGE_CODE_KEY` varchar(50) COLLATE utf8_bin NOT NULL,
  `KEY` varchar(50) COLLATE utf8_bin NOT NULL,
  `VALUE` varchar(50) COLLATE utf8_bin NOT NULL,
  `DESCRIPTION` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`AUDITLOG_ID_PK`),
  KEY `IDXAL_REF_ADDRESS_TYPE` (`ID_PK`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- Dumping data for table yug_owner.al_ref_address_type: ~0 rows (approximately)
/*!40000 ALTER TABLE `al_ref_address_type` DISABLE KEYS */;
/*!40000 ALTER TABLE `al_ref_address_type` ENABLE KEYS */;

-- Dumping structure for table yug_owner.al_ref_agreement_type
CREATE TABLE IF NOT EXISTS `al_ref_agreement_type` (
  `AUDITLOG_ID_PK` varchar(50) COLLATE utf8_bin NOT NULL,
  `AUDITLOG_CREATED_TS` datetime(6) NOT NULL,
  `AUDITLOG_ACTION_CODE` varchar(1) COLLATE utf8_bin NOT NULL,
  `ID_PK` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `VERSION` decimal(22,0) NOT NULL,
  `CREATED_TS` datetime(6) NOT NULL,
  `DELETED_TS` datetime(6) DEFAULT NULL,
  `UPDATED_TS` datetime(6) NOT NULL,
  `UPDATED_BY_USER` varchar(50) COLLATE utf8_bin NOT NULL,
  `UPDATED_BY_TXN_ID` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `CONFIG_LANGUAGE_CODE_KEY` varchar(50) COLLATE utf8_bin NOT NULL,
  `KEY` varchar(50) COLLATE utf8_bin NOT NULL,
  `VALUE` varchar(50) COLLATE utf8_bin NOT NULL,
  `DESCRIPTION` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`AUDITLOG_ID_PK`),
  KEY `IDXAL_REF_AGREEMENT_TYPE` (`ID_PK`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- Dumping data for table yug_owner.al_ref_agreement_type: ~0 rows (approximately)
/*!40000 ALTER TABLE `al_ref_agreement_type` DISABLE KEYS */;
/*!40000 ALTER TABLE `al_ref_agreement_type` ENABLE KEYS */;

-- Dumping structure for table yug_owner.al_ref_assoc_type
CREATE TABLE IF NOT EXISTS `al_ref_assoc_type` (
  `AUDITLOG_ID_PK` varchar(50) COLLATE utf8_bin NOT NULL,
  `AUDITLOG_CREATED_TS` datetime(6) NOT NULL,
  `AUDITLOG_ACTION_CODE` varchar(1) COLLATE utf8_bin NOT NULL,
  `ID_PK` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `VERSION` decimal(22,0) NOT NULL,
  `CREATED_TS` datetime(6) NOT NULL,
  `DELETED_TS` datetime(6) DEFAULT NULL,
  `UPDATED_TS` datetime(6) NOT NULL,
  `UPDATED_BY_USER` varchar(50) COLLATE utf8_bin NOT NULL,
  `UPDATED_BY_TXN_ID` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `CONFIG_LANGUAGE_CODE_KEY` varchar(50) COLLATE utf8_bin NOT NULL,
  `KEY` varchar(50) COLLATE utf8_bin NOT NULL,
  `VALUE` varchar(50) COLLATE utf8_bin NOT NULL,
  `DESCRIPTION` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`AUDITLOG_ID_PK`),
  KEY `IDXAL_REF_ASSOC_TYPE` (`ID_PK`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- Dumping data for table yug_owner.al_ref_assoc_type: ~0 rows (approximately)
/*!40000 ALTER TABLE `al_ref_assoc_type` DISABLE KEYS */;
/*!40000 ALTER TABLE `al_ref_assoc_type` ENABLE KEYS */;

-- Dumping structure for table yug_owner.al_ref_batch_action_status
CREATE TABLE IF NOT EXISTS `al_ref_batch_action_status` (
  `AUDITLOG_ID_PK` varchar(50) COLLATE utf8_bin NOT NULL,
  `AUDITLOG_CREATED_TS` datetime(6) NOT NULL,
  `AUDITLOG_ACTION_CODE` varchar(1) COLLATE utf8_bin NOT NULL,
  `ID_PK` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `VERSION` decimal(22,0) NOT NULL,
  `CREATED_TS` datetime(6) NOT NULL,
  `DELETED_TS` datetime(6) DEFAULT NULL,
  `UPDATED_TS` datetime(6) NOT NULL,
  `UPDATED_BY_USER` varchar(50) COLLATE utf8_bin NOT NULL,
  `UPDATED_BY_TXN_ID` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `CONFIG_LANGUAGE_CODE_KEY` varchar(50) COLLATE utf8_bin NOT NULL,
  `KEY` varchar(50) COLLATE utf8_bin NOT NULL,
  `VALUE` varchar(50) COLLATE utf8_bin NOT NULL,
  `DESCRIPTION` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`AUDITLOG_ID_PK`),
  KEY `IDXAL_REF_BATCH_ACTION_STATUS` (`ID_PK`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- Dumping data for table yug_owner.al_ref_batch_action_status: ~0 rows (approximately)
/*!40000 ALTER TABLE `al_ref_batch_action_status` DISABLE KEYS */;
/*!40000 ALTER TABLE `al_ref_batch_action_status` ENABLE KEYS */;

-- Dumping structure for table yug_owner.al_ref_batch_proposed_action
CREATE TABLE IF NOT EXISTS `al_ref_batch_proposed_action` (
  `AUDITLOG_ID_PK` varchar(50) COLLATE utf8_bin NOT NULL,
  `AUDITLOG_CREATED_TS` datetime(6) NOT NULL,
  `AUDITLOG_ACTION_CODE` varchar(1) COLLATE utf8_bin NOT NULL,
  `ID_PK` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `VERSION` decimal(22,0) NOT NULL,
  `CREATED_TS` datetime(6) NOT NULL,
  `DELETED_TS` datetime(6) DEFAULT NULL,
  `UPDATED_TS` datetime(6) NOT NULL,
  `UPDATED_BY_USER` varchar(50) COLLATE utf8_bin NOT NULL,
  `UPDATED_BY_TXN_ID` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `CONFIG_LANGUAGE_CODE_KEY` varchar(50) COLLATE utf8_bin NOT NULL,
  `KEY` varchar(50) COLLATE utf8_bin NOT NULL,
  `VALUE` varchar(50) COLLATE utf8_bin NOT NULL,
  `DESCRIPTION` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`AUDITLOG_ID_PK`),
  KEY `IDXAL_REF_BATCH_PROPOSED_ACT` (`ID_PK`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- Dumping data for table yug_owner.al_ref_batch_proposed_action: ~0 rows (approximately)
/*!40000 ALTER TABLE `al_ref_batch_proposed_action` DISABLE KEYS */;
/*!40000 ALTER TABLE `al_ref_batch_proposed_action` ENABLE KEYS */;

-- Dumping structure for table yug_owner.al_ref_billing_mode_type
CREATE TABLE IF NOT EXISTS `al_ref_billing_mode_type` (
  `AUDITLOG_ID_PK` varchar(50) COLLATE utf8_bin NOT NULL,
  `AUDITLOG_CREATED_TS` datetime(6) NOT NULL,
  `AUDITLOG_ACTION_CODE` varchar(1) COLLATE utf8_bin NOT NULL,
  `ID_PK` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `VERSION` decimal(22,0) NOT NULL,
  `CREATED_TS` datetime(6) NOT NULL,
  `DELETED_TS` datetime(6) DEFAULT NULL,
  `UPDATED_TS` datetime(6) NOT NULL,
  `UPDATED_BY_USER` varchar(50) COLLATE utf8_bin NOT NULL,
  `UPDATED_BY_TXN_ID` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `CONFIG_LANGUAGE_CODE_KEY` varchar(50) COLLATE utf8_bin NOT NULL,
  `KEY` varchar(50) COLLATE utf8_bin NOT NULL,
  `VALUE` varchar(50) COLLATE utf8_bin NOT NULL,
  `DESCRIPTION` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`AUDITLOG_ID_PK`),
  KEY `IDXAL_REF_BILLING_MODE_TYPE` (`ID_PK`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- Dumping data for table yug_owner.al_ref_billing_mode_type: ~0 rows (approximately)
/*!40000 ALTER TABLE `al_ref_billing_mode_type` DISABLE KEYS */;
/*!40000 ALTER TABLE `al_ref_billing_mode_type` ENABLE KEYS */;

-- Dumping structure for table yug_owner.al_ref_branch_code
CREATE TABLE IF NOT EXISTS `al_ref_branch_code` (
  `AUDITLOG_ID_PK` varchar(50) COLLATE utf8_bin NOT NULL,
  `AUDITLOG_CREATED_TS` datetime(6) NOT NULL,
  `AUDITLOG_ACTION_CODE` varchar(1) COLLATE utf8_bin NOT NULL,
  `ID_PK` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `VERSION` decimal(22,0) NOT NULL,
  `CREATED_TS` datetime(6) NOT NULL,
  `DELETED_TS` datetime(6) DEFAULT NULL,
  `UPDATED_TS` datetime(6) NOT NULL,
  `UPDATED_BY_USER` varchar(50) COLLATE utf8_bin NOT NULL,
  `UPDATED_BY_TXN_ID` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `CONFIG_LANGUAGE_CODE_KEY` varchar(50) COLLATE utf8_bin NOT NULL,
  `KEY` varchar(50) COLLATE utf8_bin NOT NULL,
  `VALUE` varchar(50) COLLATE utf8_bin NOT NULL,
  `DESCRIPTION` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`AUDITLOG_ID_PK`),
  KEY `IDXAL_REF_BRANCH_CODE` (`ID_PK`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- Dumping data for table yug_owner.al_ref_branch_code: ~0 rows (approximately)
/*!40000 ALTER TABLE `al_ref_branch_code` DISABLE KEYS */;
/*!40000 ALTER TABLE `al_ref_branch_code` ENABLE KEYS */;

-- Dumping structure for table yug_owner.al_ref_classification_code
CREATE TABLE IF NOT EXISTS `al_ref_classification_code` (
  `AUDITLOG_ID_PK` varchar(50) COLLATE utf8_bin NOT NULL,
  `AUDITLOG_CREATED_TS` datetime(6) NOT NULL,
  `AUDITLOG_ACTION_CODE` varchar(1) COLLATE utf8_bin NOT NULL,
  `ID_PK` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `VERSION` decimal(22,0) NOT NULL,
  `CREATED_TS` datetime(6) NOT NULL,
  `DELETED_TS` datetime(6) DEFAULT NULL,
  `UPDATED_TS` datetime(6) NOT NULL,
  `UPDATED_BY_USER` varchar(50) COLLATE utf8_bin NOT NULL,
  `UPDATED_BY_TXN_ID` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `CONFIG_LANGUAGE_CODE_KEY` varchar(50) COLLATE utf8_bin NOT NULL,
  `KEY` varchar(50) COLLATE utf8_bin NOT NULL,
  `VALUE` varchar(50) COLLATE utf8_bin NOT NULL,
  `DESCRIPTION` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`AUDITLOG_ID_PK`),
  KEY `IDXAL_REF_CLASSIFICATION_CODE` (`ID_PK`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- Dumping data for table yug_owner.al_ref_classification_code: ~0 rows (approximately)
/*!40000 ALTER TABLE `al_ref_classification_code` DISABLE KEYS */;
/*!40000 ALTER TABLE `al_ref_classification_code` ENABLE KEYS */;

-- Dumping structure for table yug_owner.al_ref_corporation_name_typ
CREATE TABLE IF NOT EXISTS `al_ref_corporation_name_typ` (
  `AUDITLOG_ID_PK` varchar(50) COLLATE utf8_bin NOT NULL,
  `AUDITLOG_CREATED_TS` datetime(6) NOT NULL,
  `AUDITLOG_ACTION_CODE` varchar(1) COLLATE utf8_bin NOT NULL,
  `ID_PK` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `VERSION` decimal(22,0) NOT NULL,
  `CREATED_TS` datetime(6) NOT NULL,
  `DELETED_TS` datetime(6) DEFAULT NULL,
  `UPDATED_TS` datetime(6) NOT NULL,
  `UPDATED_BY_USER` varchar(50) COLLATE utf8_bin NOT NULL,
  `UPDATED_BY_TXN_ID` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `CONFIG_LANGUAGE_CODE_KEY` varchar(50) COLLATE utf8_bin NOT NULL,
  `KEY` varchar(50) COLLATE utf8_bin NOT NULL,
  `VALUE` varchar(50) COLLATE utf8_bin NOT NULL,
  `DESCRIPTION` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`AUDITLOG_ID_PK`),
  KEY `IDXAL_REF_CORPORATION_NAME_TYP` (`ID_PK`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- Dumping data for table yug_owner.al_ref_corporation_name_typ: ~0 rows (approximately)
/*!40000 ALTER TABLE `al_ref_corporation_name_typ` DISABLE KEYS */;
/*!40000 ALTER TABLE `al_ref_corporation_name_typ` ENABLE KEYS */;

-- Dumping structure for table yug_owner.al_ref_corporation_name_type
CREATE TABLE IF NOT EXISTS `al_ref_corporation_name_type` (
  `AUDITLOG_ID_PK` varchar(50) COLLATE utf8_bin NOT NULL,
  `AUDITLOG_CREATED_TS` datetime(6) NOT NULL,
  `AUDITLOG_ACTION_CODE` varchar(1) COLLATE utf8_bin NOT NULL,
  `ID_PK` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `VERSION` decimal(22,0) NOT NULL,
  `CREATED_TS` datetime(6) NOT NULL,
  `DELETED_TS` datetime(6) DEFAULT NULL,
  `UPDATED_TS` datetime(6) NOT NULL,
  `UPDATED_BY_USER` varchar(50) COLLATE utf8_bin NOT NULL,
  `UPDATED_BY_TXN_ID` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `CONFIG_LANGUAGE_CODE_KEY` varchar(50) COLLATE utf8_bin NOT NULL,
  `KEY` varchar(50) COLLATE utf8_bin NOT NULL,
  `VALUE` varchar(50) COLLATE utf8_bin NOT NULL,
  `DESCRIPTION` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`AUDITLOG_ID_PK`),
  KEY `IDXAL_REF_CORPORATION_NAME_T` (`ID_PK`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- Dumping data for table yug_owner.al_ref_corporation_name_type: ~0 rows (approximately)
/*!40000 ALTER TABLE `al_ref_corporation_name_type` DISABLE KEYS */;
/*!40000 ALTER TABLE `al_ref_corporation_name_type` ENABLE KEYS */;

-- Dumping structure for table yug_owner.al_ref_corporation_type
CREATE TABLE IF NOT EXISTS `al_ref_corporation_type` (
  `AUDITLOG_ID_PK` varchar(50) COLLATE utf8_bin NOT NULL,
  `AUDITLOG_CREATED_TS` datetime(6) NOT NULL,
  `AUDITLOG_ACTION_CODE` varchar(1) COLLATE utf8_bin NOT NULL,
  `ID_PK` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `VERSION` decimal(22,0) NOT NULL,
  `CREATED_TS` datetime(6) NOT NULL,
  `DELETED_TS` datetime(6) DEFAULT NULL,
  `UPDATED_TS` datetime(6) NOT NULL,
  `UPDATED_BY_USER` varchar(50) COLLATE utf8_bin NOT NULL,
  `UPDATED_BY_TXN_ID` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `CONFIG_LANGUAGE_CODE_KEY` varchar(50) COLLATE utf8_bin NOT NULL,
  `KEY` varchar(50) COLLATE utf8_bin NOT NULL,
  `VALUE` varchar(50) COLLATE utf8_bin NOT NULL,
  `DESCRIPTION` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`AUDITLOG_ID_PK`),
  KEY `IDXAL_REF_CORPORATION_TYPE` (`ID_PK`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- Dumping data for table yug_owner.al_ref_corporation_type: ~0 rows (approximately)
/*!40000 ALTER TABLE `al_ref_corporation_type` DISABLE KEYS */;
/*!40000 ALTER TABLE `al_ref_corporation_type` ENABLE KEYS */;

-- Dumping structure for table yug_owner.al_ref_country_iso
CREATE TABLE IF NOT EXISTS `al_ref_country_iso` (
  `AUDITLOG_ID_PK` varchar(50) COLLATE utf8_bin NOT NULL,
  `AUDITLOG_CREATED_TS` datetime(6) NOT NULL,
  `AUDITLOG_ACTION_CODE` varchar(1) COLLATE utf8_bin NOT NULL,
  `ID_PK` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `VERSION` decimal(22,0) NOT NULL,
  `CREATED_TS` datetime(6) NOT NULL,
  `DELETED_TS` datetime(6) DEFAULT NULL,
  `UPDATED_TS` datetime(6) NOT NULL,
  `UPDATED_BY_USER` varchar(50) COLLATE utf8_bin NOT NULL,
  `UPDATED_BY_TXN_ID` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `CONFIG_LANGUAGE_CODE_KEY` varchar(50) COLLATE utf8_bin NOT NULL,
  `KEY` varchar(50) COLLATE utf8_bin NOT NULL,
  `VALUE` varchar(50) COLLATE utf8_bin NOT NULL,
  `DESCRIPTION` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`AUDITLOG_ID_PK`),
  KEY `IDXAL_REF_COUNTRY_ISO` (`ID_PK`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- Dumping data for table yug_owner.al_ref_country_iso: ~0 rows (approximately)
/*!40000 ALTER TABLE `al_ref_country_iso` DISABLE KEYS */;
/*!40000 ALTER TABLE `al_ref_country_iso` ENABLE KEYS */;

-- Dumping structure for table yug_owner.al_ref_currency
CREATE TABLE IF NOT EXISTS `al_ref_currency` (
  `AUDITLOG_ID_PK` varchar(50) COLLATE utf8_bin NOT NULL,
  `AUDITLOG_CREATED_TS` datetime(6) NOT NULL,
  `AUDITLOG_ACTION_CODE` varchar(1) COLLATE utf8_bin NOT NULL,
  `ID_PK` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `VERSION` decimal(22,0) NOT NULL,
  `CREATED_TS` datetime(6) NOT NULL,
  `DELETED_TS` datetime(6) DEFAULT NULL,
  `UPDATED_TS` datetime(6) NOT NULL,
  `UPDATED_BY_USER` varchar(50) COLLATE utf8_bin NOT NULL,
  `UPDATED_BY_TXN_ID` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `CONFIG_LANGUAGE_CODE_KEY` varchar(50) COLLATE utf8_bin NOT NULL,
  `KEY` varchar(50) COLLATE utf8_bin NOT NULL,
  `VALUE` varchar(50) COLLATE utf8_bin NOT NULL,
  `DESCRIPTION` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`AUDITLOG_ID_PK`),
  KEY `IDXAL_REF_CURRENCY` (`ID_PK`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- Dumping data for table yug_owner.al_ref_currency: ~0 rows (approximately)
/*!40000 ALTER TABLE `al_ref_currency` DISABLE KEYS */;
/*!40000 ALTER TABLE `al_ref_currency` ENABLE KEYS */;

-- Dumping structure for table yug_owner.al_ref_deactivation_reason
CREATE TABLE IF NOT EXISTS `al_ref_deactivation_reason` (
  `AUDITLOG_ID_PK` varchar(50) COLLATE utf8_bin NOT NULL,
  `AUDITLOG_CREATED_TS` datetime(6) NOT NULL,
  `AUDITLOG_ACTION_CODE` varchar(1) COLLATE utf8_bin NOT NULL,
  `ID_PK` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `VERSION` decimal(22,0) NOT NULL,
  `CREATED_TS` datetime(6) NOT NULL,
  `DELETED_TS` datetime(6) DEFAULT NULL,
  `UPDATED_TS` datetime(6) NOT NULL,
  `UPDATED_BY_USER` varchar(50) COLLATE utf8_bin NOT NULL,
  `UPDATED_BY_TXN_ID` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `CONFIG_LANGUAGE_CODE_KEY` varchar(50) COLLATE utf8_bin NOT NULL,
  `KEY` varchar(50) COLLATE utf8_bin NOT NULL,
  `VALUE` varchar(50) COLLATE utf8_bin NOT NULL,
  `DESCRIPTION` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`AUDITLOG_ID_PK`),
  KEY `IDXAL_REF_DEACTIVATION_REASON` (`ID_PK`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- Dumping data for table yug_owner.al_ref_deactivation_reason: ~0 rows (approximately)
/*!40000 ALTER TABLE `al_ref_deactivation_reason` DISABLE KEYS */;
/*!40000 ALTER TABLE `al_ref_deactivation_reason` ENABLE KEYS */;

-- Dumping structure for table yug_owner.al_ref_entity_object_type
CREATE TABLE IF NOT EXISTS `al_ref_entity_object_type` (
  `AUDITLOG_ID_PK` varchar(50) COLLATE utf8_bin NOT NULL,
  `AUDITLOG_CREATED_TS` datetime(6) NOT NULL,
  `AUDITLOG_ACTION_CODE` varchar(1) COLLATE utf8_bin NOT NULL,
  `ID_PK` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `VERSION` decimal(22,0) NOT NULL,
  `CREATED_TS` datetime(6) NOT NULL,
  `DELETED_TS` datetime(6) DEFAULT NULL,
  `UPDATED_TS` datetime(6) NOT NULL,
  `UPDATED_BY_USER` varchar(50) COLLATE utf8_bin NOT NULL,
  `UPDATED_BY_TXN_ID` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `CONFIG_LANGUAGE_CODE_KEY` varchar(50) COLLATE utf8_bin NOT NULL,
  `KEY` varchar(50) COLLATE utf8_bin NOT NULL,
  `VALUE` varchar(50) COLLATE utf8_bin NOT NULL,
  `DESCRIPTION` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`AUDITLOG_ID_PK`),
  KEY `IDXAL_REF_ENTITY_OBJECT_TYPE` (`ID_PK`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- Dumping data for table yug_owner.al_ref_entity_object_type: ~0 rows (approximately)
/*!40000 ALTER TABLE `al_ref_entity_object_type` DISABLE KEYS */;
/*!40000 ALTER TABLE `al_ref_entity_object_type` ENABLE KEYS */;

-- Dumping structure for table yug_owner.al_ref_gender
CREATE TABLE IF NOT EXISTS `al_ref_gender` (
  `AUDITLOG_ID_PK` varchar(50) COLLATE utf8_bin NOT NULL,
  `AUDITLOG_CREATED_TS` datetime(6) NOT NULL,
  `AUDITLOG_ACTION_CODE` varchar(1) COLLATE utf8_bin NOT NULL,
  `ID_PK` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `VERSION` decimal(22,0) NOT NULL,
  `CREATED_TS` datetime(6) NOT NULL,
  `DELETED_TS` datetime(6) DEFAULT NULL,
  `UPDATED_TS` datetime(6) NOT NULL,
  `UPDATED_BY_USER` varchar(50) COLLATE utf8_bin NOT NULL,
  `UPDATED_BY_TXN_ID` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `CONFIG_LANGUAGE_CODE_KEY` varchar(50) COLLATE utf8_bin NOT NULL,
  `KEY` varchar(50) COLLATE utf8_bin NOT NULL,
  `VALUE` varchar(50) COLLATE utf8_bin NOT NULL,
  `DESCRIPTION` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`AUDITLOG_ID_PK`),
  KEY `IDXAL_REF_GENDER` (`ID_PK`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- Dumping data for table yug_owner.al_ref_gender: ~0 rows (approximately)
/*!40000 ALTER TABLE `al_ref_gender` DISABLE KEYS */;
/*!40000 ALTER TABLE `al_ref_gender` ENABLE KEYS */;

-- Dumping structure for table yug_owner.al_ref_group_subtype
CREATE TABLE IF NOT EXISTS `al_ref_group_subtype` (
  `AUDITLOG_ID_PK` varchar(50) COLLATE utf8_bin NOT NULL,
  `AUDITLOG_CREATED_TS` datetime(6) NOT NULL,
  `AUDITLOG_ACTION_CODE` varchar(1) COLLATE utf8_bin NOT NULL,
  `ID_PK` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `VERSION` decimal(22,0) NOT NULL,
  `CREATED_TS` datetime(6) NOT NULL,
  `DELETED_TS` datetime(6) DEFAULT NULL,
  `UPDATED_TS` datetime(6) NOT NULL,
  `UPDATED_BY_USER` varchar(50) COLLATE utf8_bin NOT NULL,
  `UPDATED_BY_TXN_ID` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `CONFIG_LANGUAGE_CODE_KEY` varchar(50) COLLATE utf8_bin NOT NULL,
  `KEY` varchar(50) COLLATE utf8_bin NOT NULL,
  `VALUE` varchar(50) COLLATE utf8_bin NOT NULL,
  `DESCRIPTION` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`AUDITLOG_ID_PK`),
  KEY `IDXAL_REF_GROUP_SUBTYPE` (`ID_PK`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- Dumping data for table yug_owner.al_ref_group_subtype: ~0 rows (approximately)
/*!40000 ALTER TABLE `al_ref_group_subtype` DISABLE KEYS */;
/*!40000 ALTER TABLE `al_ref_group_subtype` ENABLE KEYS */;

-- Dumping structure for table yug_owner.al_ref_group_type
CREATE TABLE IF NOT EXISTS `al_ref_group_type` (
  `AUDITLOG_ID_PK` varchar(50) COLLATE utf8_bin NOT NULL,
  `AUDITLOG_CREATED_TS` datetime(6) NOT NULL,
  `AUDITLOG_ACTION_CODE` varchar(1) COLLATE utf8_bin NOT NULL,
  `ID_PK` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `VERSION` decimal(22,0) NOT NULL,
  `CREATED_TS` datetime(6) NOT NULL,
  `DELETED_TS` datetime(6) DEFAULT NULL,
  `UPDATED_TS` datetime(6) NOT NULL,
  `UPDATED_BY_USER` varchar(50) COLLATE utf8_bin NOT NULL,
  `UPDATED_BY_TXN_ID` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `CONFIG_LANGUAGE_CODE_KEY` varchar(50) COLLATE utf8_bin NOT NULL,
  `KEY` varchar(50) COLLATE utf8_bin NOT NULL,
  `VALUE` varchar(50) COLLATE utf8_bin NOT NULL,
  `DESCRIPTION` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`AUDITLOG_ID_PK`),
  KEY `IDXAL_REF_GROUP_TYPE` (`ID_PK`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- Dumping data for table yug_owner.al_ref_group_type: ~0 rows (approximately)
/*!40000 ALTER TABLE `al_ref_group_type` DISABLE KEYS */;
/*!40000 ALTER TABLE `al_ref_group_type` ENABLE KEYS */;

-- Dumping structure for table yug_owner.al_ref_highest_edu_qual
CREATE TABLE IF NOT EXISTS `al_ref_highest_edu_qual` (
  `AUDITLOG_ID_PK` varchar(50) COLLATE utf8_bin NOT NULL,
  `AUDITLOG_CREATED_TS` datetime(6) NOT NULL,
  `AUDITLOG_ACTION_CODE` varchar(1) COLLATE utf8_bin NOT NULL,
  `ID_PK` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `VERSION` decimal(22,0) NOT NULL,
  `CREATED_TS` datetime(6) NOT NULL,
  `DELETED_TS` datetime(6) DEFAULT NULL,
  `UPDATED_TS` datetime(6) NOT NULL,
  `UPDATED_BY_USER` varchar(50) COLLATE utf8_bin NOT NULL,
  `UPDATED_BY_TXN_ID` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `CONFIG_LANGUAGE_CODE_KEY` varchar(50) COLLATE utf8_bin NOT NULL,
  `KEY` varchar(50) COLLATE utf8_bin NOT NULL,
  `VALUE` varchar(50) COLLATE utf8_bin NOT NULL,
  `DESCRIPTION` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`AUDITLOG_ID_PK`),
  KEY `IDXAL_REF_HIGHEST_EDU_QUAL` (`ID_PK`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- Dumping data for table yug_owner.al_ref_highest_edu_qual: ~0 rows (approximately)
/*!40000 ALTER TABLE `al_ref_highest_edu_qual` DISABLE KEYS */;
/*!40000 ALTER TABLE `al_ref_highest_edu_qual` ENABLE KEYS */;

-- Dumping structure for table yug_owner.al_ref_identification_type
CREATE TABLE IF NOT EXISTS `al_ref_identification_type` (
  `AUDITLOG_ID_PK` varchar(50) COLLATE utf8_bin NOT NULL,
  `AUDITLOG_CREATED_TS` datetime(6) NOT NULL,
  `AUDITLOG_ACTION_CODE` varchar(1) COLLATE utf8_bin NOT NULL,
  `ID_PK` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `VERSION` decimal(22,0) NOT NULL,
  `CREATED_TS` datetime(6) NOT NULL,
  `DELETED_TS` datetime(6) DEFAULT NULL,
  `UPDATED_TS` datetime(6) NOT NULL,
  `UPDATED_BY_USER` varchar(50) COLLATE utf8_bin NOT NULL,
  `UPDATED_BY_TXN_ID` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `CONFIG_LANGUAGE_CODE_KEY` varchar(50) COLLATE utf8_bin NOT NULL,
  `KEY` varchar(50) COLLATE utf8_bin NOT NULL,
  `VALUE` varchar(50) COLLATE utf8_bin NOT NULL,
  `DESCRIPTION` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`AUDITLOG_ID_PK`),
  KEY `IDXAL_REF_IDENTIFICATION_TYPE` (`ID_PK`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- Dumping data for table yug_owner.al_ref_identification_type: ~0 rows (approximately)
/*!40000 ALTER TABLE `al_ref_identification_type` DISABLE KEYS */;
/*!40000 ALTER TABLE `al_ref_identification_type` ENABLE KEYS */;

-- Dumping structure for table yug_owner.al_ref_importance_type
CREATE TABLE IF NOT EXISTS `al_ref_importance_type` (
  `AUDITLOG_ID_PK` varchar(50) COLLATE utf8_bin NOT NULL,
  `AUDITLOG_CREATED_TS` datetime(6) NOT NULL,
  `AUDITLOG_ACTION_CODE` varchar(1) COLLATE utf8_bin NOT NULL,
  `ID_PK` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `VERSION` decimal(22,0) NOT NULL,
  `CREATED_TS` datetime(6) NOT NULL,
  `DELETED_TS` datetime(6) DEFAULT NULL,
  `UPDATED_TS` datetime(6) NOT NULL,
  `UPDATED_BY_USER` varchar(50) COLLATE utf8_bin NOT NULL,
  `UPDATED_BY_TXN_ID` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `CONFIG_LANGUAGE_CODE_KEY` varchar(50) COLLATE utf8_bin NOT NULL,
  `KEY` varchar(50) COLLATE utf8_bin NOT NULL,
  `VALUE` varchar(50) COLLATE utf8_bin NOT NULL,
  `DESCRIPTION` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`AUDITLOG_ID_PK`),
  KEY `IDXAL_REF_IMPORTANCE_TYPE` (`ID_PK`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- Dumping data for table yug_owner.al_ref_importance_type: ~0 rows (approximately)
/*!40000 ALTER TABLE `al_ref_importance_type` DISABLE KEYS */;
/*!40000 ALTER TABLE `al_ref_importance_type` ENABLE KEYS */;

-- Dumping structure for table yug_owner.al_ref_inactivation_reason
CREATE TABLE IF NOT EXISTS `al_ref_inactivation_reason` (
  `AUDITLOG_ID_PK` varchar(50) COLLATE utf8_bin NOT NULL,
  `AUDITLOG_CREATED_TS` datetime(6) NOT NULL,
  `AUDITLOG_ACTION_CODE` varchar(1) COLLATE utf8_bin NOT NULL,
  `ID_PK` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `VERSION` decimal(22,0) NOT NULL,
  `CREATED_TS` datetime(6) NOT NULL,
  `DELETED_TS` datetime(6) DEFAULT NULL,
  `UPDATED_TS` datetime(6) NOT NULL,
  `UPDATED_BY_USER` varchar(50) COLLATE utf8_bin NOT NULL,
  `UPDATED_BY_TXN_ID` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `CONFIG_LANGUAGE_CODE_KEY` varchar(50) COLLATE utf8_bin NOT NULL,
  `KEY` varchar(50) COLLATE utf8_bin NOT NULL,
  `VALUE` varchar(50) COLLATE utf8_bin NOT NULL,
  `DESCRIPTION` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`AUDITLOG_ID_PK`),
  KEY `IDXAL_REF_INACTIVATION_REASON` (`ID_PK`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- Dumping data for table yug_owner.al_ref_inactivation_reason: ~0 rows (approximately)
/*!40000 ALTER TABLE `al_ref_inactivation_reason` DISABLE KEYS */;
/*!40000 ALTER TABLE `al_ref_inactivation_reason` ENABLE KEYS */;

-- Dumping structure for table yug_owner.al_ref_industry_code
CREATE TABLE IF NOT EXISTS `al_ref_industry_code` (
  `AUDITLOG_ID_PK` varchar(50) COLLATE utf8_bin NOT NULL,
  `AUDITLOG_CREATED_TS` datetime(6) NOT NULL,
  `AUDITLOG_ACTION_CODE` varchar(1) COLLATE utf8_bin NOT NULL,
  `ID_PK` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `VERSION` decimal(22,0) NOT NULL,
  `CREATED_TS` datetime(6) NOT NULL,
  `DELETED_TS` datetime(6) DEFAULT NULL,
  `UPDATED_TS` datetime(6) NOT NULL,
  `UPDATED_BY_USER` varchar(50) COLLATE utf8_bin NOT NULL,
  `UPDATED_BY_TXN_ID` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `CONFIG_LANGUAGE_CODE_KEY` varchar(50) COLLATE utf8_bin NOT NULL,
  `KEY` varchar(50) COLLATE utf8_bin NOT NULL,
  `VALUE` varchar(50) COLLATE utf8_bin NOT NULL,
  `DESCRIPTION` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`AUDITLOG_ID_PK`),
  KEY `IDXAL_REF_INDUSTRY_CODE` (`ID_PK`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- Dumping data for table yug_owner.al_ref_industry_code: ~0 rows (approximately)
/*!40000 ALTER TABLE `al_ref_industry_code` DISABLE KEYS */;
/*!40000 ALTER TABLE `al_ref_industry_code` ENABLE KEYS */;

-- Dumping structure for table yug_owner.al_ref_language_code
CREATE TABLE IF NOT EXISTS `al_ref_language_code` (
  `AUDITLOG_ID_PK` varchar(50) COLLATE utf8_bin NOT NULL,
  `AUDITLOG_CREATED_TS` datetime(6) NOT NULL,
  `AUDITLOG_ACTION_CODE` varchar(1) COLLATE utf8_bin NOT NULL,
  `ID_PK` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `VERSION` decimal(22,0) NOT NULL,
  `CREATED_TS` datetime(6) NOT NULL,
  `DELETED_TS` datetime(6) DEFAULT NULL,
  `UPDATED_TS` datetime(6) NOT NULL,
  `UPDATED_BY_USER` varchar(50) COLLATE utf8_bin NOT NULL,
  `UPDATED_BY_TXN_ID` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `CONFIG_LANGUAGE_CODE_KEY` varchar(50) COLLATE utf8_bin NOT NULL,
  `KEY` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `VALUE` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `DESCRIPTION` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`AUDITLOG_ID_PK`),
  KEY `IDXAL_REF_LANGUAGE_CODE` (`ID_PK`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- Dumping data for table yug_owner.al_ref_language_code: ~0 rows (approximately)
/*!40000 ALTER TABLE `al_ref_language_code` DISABLE KEYS */;
/*!40000 ALTER TABLE `al_ref_language_code` ENABLE KEYS */;

-- Dumping structure for table yug_owner.al_ref_le_rating
CREATE TABLE IF NOT EXISTS `al_ref_le_rating` (
  `AUDITLOG_ID_PK` varchar(50) COLLATE utf8_bin NOT NULL,
  `AUDITLOG_CREATED_TS` datetime(6) NOT NULL,
  `AUDITLOG_ACTION_CODE` varchar(1) COLLATE utf8_bin NOT NULL,
  `ID_PK` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `VERSION` decimal(22,0) NOT NULL,
  `CREATED_TS` datetime(6) NOT NULL,
  `DELETED_TS` datetime(6) DEFAULT NULL,
  `UPDATED_TS` datetime(6) NOT NULL,
  `UPDATED_BY_USER` varchar(50) COLLATE utf8_bin NOT NULL,
  `UPDATED_BY_TXN_ID` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `CONFIG_LANGUAGE_CODE_KEY` varchar(50) COLLATE utf8_bin NOT NULL,
  `KEY` varchar(50) COLLATE utf8_bin NOT NULL,
  `VALUE` varchar(50) COLLATE utf8_bin NOT NULL,
  `DESCRIPTION` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`AUDITLOG_ID_PK`),
  KEY `IDXAL_REF_LE_RATING` (`ID_PK`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- Dumping data for table yug_owner.al_ref_le_rating: ~0 rows (approximately)
/*!40000 ALTER TABLE `al_ref_le_rating` DISABLE KEYS */;
/*!40000 ALTER TABLE `al_ref_le_rating` ENABLE KEYS */;

-- Dumping structure for table yug_owner.al_ref_le_relationship_type
CREATE TABLE IF NOT EXISTS `al_ref_le_relationship_type` (
  `AUDITLOG_ID_PK` varchar(50) COLLATE utf8_bin NOT NULL,
  `AUDITLOG_CREATED_TS` datetime(6) NOT NULL,
  `AUDITLOG_ACTION_CODE` varchar(1) COLLATE utf8_bin NOT NULL,
  `ID_PK` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `VERSION` decimal(22,0) NOT NULL,
  `CREATED_TS` datetime(6) NOT NULL,
  `DELETED_TS` datetime(6) DEFAULT NULL,
  `UPDATED_TS` datetime(6) NOT NULL,
  `UPDATED_BY_USER` varchar(50) COLLATE utf8_bin NOT NULL,
  `UPDATED_BY_TXN_ID` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `CONFIG_LANGUAGE_CODE_KEY` varchar(50) COLLATE utf8_bin NOT NULL,
  `KEY` varchar(50) COLLATE utf8_bin NOT NULL,
  `VALUE` varchar(50) COLLATE utf8_bin NOT NULL,
  `DESCRIPTION` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`AUDITLOG_ID_PK`),
  KEY `IDXAL_REF_LE_RELATIONSHIP_TYPE` (`ID_PK`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- Dumping data for table yug_owner.al_ref_le_relationship_type: ~0 rows (approximately)
/*!40000 ALTER TABLE `al_ref_le_relationship_type` DISABLE KEYS */;
/*!40000 ALTER TABLE `al_ref_le_relationship_type` ENABLE KEYS */;

-- Dumping structure for table yug_owner.al_ref_le_roletype
CREATE TABLE IF NOT EXISTS `al_ref_le_roletype` (
  `AUDITLOG_ID_PK` varchar(50) COLLATE utf8_bin NOT NULL,
  `AUDITLOG_CREATED_TS` datetime(6) NOT NULL,
  `AUDITLOG_ACTION_CODE` varchar(1) COLLATE utf8_bin NOT NULL,
  `ID_PK` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `VERSION` decimal(22,0) NOT NULL,
  `CREATED_TS` datetime(6) NOT NULL,
  `DELETED_TS` datetime(6) DEFAULT NULL,
  `UPDATED_TS` datetime(6) NOT NULL,
  `UPDATED_BY_USER` varchar(50) COLLATE utf8_bin NOT NULL,
  `UPDATED_BY_TXN_ID` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `CONFIG_LANGUAGE_CODE_KEY` varchar(50) COLLATE utf8_bin NOT NULL,
  `KEY` varchar(50) COLLATE utf8_bin NOT NULL,
  `VALUE` varchar(50) COLLATE utf8_bin NOT NULL,
  `DESCRIPTION` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`AUDITLOG_ID_PK`),
  KEY `IDXAL_REF_LE_ROLETYPE` (`ID_PK`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- Dumping data for table yug_owner.al_ref_le_roletype: ~0 rows (approximately)
/*!40000 ALTER TABLE `al_ref_le_roletype` DISABLE KEYS */;
/*!40000 ALTER TABLE `al_ref_le_roletype` ENABLE KEYS */;

-- Dumping structure for table yug_owner.al_ref_lobtype
CREATE TABLE IF NOT EXISTS `al_ref_lobtype` (
  `AUDITLOG_ID_PK` varchar(50) COLLATE utf8_bin NOT NULL,
  `AUDITLOG_CREATED_TS` datetime(6) NOT NULL,
  `AUDITLOG_ACTION_CODE` varchar(1) COLLATE utf8_bin NOT NULL,
  `ID_PK` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `VERSION` decimal(22,0) NOT NULL,
  `CREATED_TS` datetime(6) NOT NULL,
  `DELETED_TS` datetime(6) DEFAULT NULL,
  `UPDATED_TS` datetime(6) NOT NULL,
  `UPDATED_BY_USER` varchar(50) COLLATE utf8_bin NOT NULL,
  `UPDATED_BY_TXN_ID` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `CONFIG_LANGUAGE_CODE_KEY` varchar(50) COLLATE utf8_bin NOT NULL,
  `KEY` varchar(50) COLLATE utf8_bin NOT NULL,
  `VALUE` varchar(50) COLLATE utf8_bin NOT NULL,
  `DESCRIPTION` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`AUDITLOG_ID_PK`),
  KEY `IDXAL_REF_LOBTYPE` (`ID_PK`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- Dumping data for table yug_owner.al_ref_lobtype: ~0 rows (approximately)
/*!40000 ALTER TABLE `al_ref_lobtype` DISABLE KEYS */;
/*!40000 ALTER TABLE `al_ref_lobtype` ENABLE KEYS */;

-- Dumping structure for table yug_owner.al_ref_match_actionstatus
CREATE TABLE IF NOT EXISTS `al_ref_match_actionstatus` (
  `AUDITLOG_ID_PK` varchar(50) COLLATE utf8_bin NOT NULL,
  `AUDITLOG_CREATED_TS` datetime(6) NOT NULL,
  `AUDITLOG_ACTION_CODE` varchar(1) COLLATE utf8_bin NOT NULL,
  `ID_PK` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `VERSION` decimal(22,0) NOT NULL,
  `CREATED_TS` datetime(6) NOT NULL,
  `DELETED_TS` datetime(6) DEFAULT NULL,
  `UPDATED_TS` datetime(6) NOT NULL,
  `UPDATED_BY_USER` varchar(50) COLLATE utf8_bin NOT NULL,
  `UPDATED_BY_TXN_ID` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `CONFIG_LANGUAGE_CODE_KEY` varchar(50) COLLATE utf8_bin NOT NULL,
  `KEY` varchar(50) COLLATE utf8_bin NOT NULL,
  `VALUE` varchar(50) COLLATE utf8_bin NOT NULL,
  `DESCRIPTION` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`AUDITLOG_ID_PK`),
  KEY `IDXAL_REF_MATCH_ACTIONSTATUS` (`ID_PK`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- Dumping data for table yug_owner.al_ref_match_actionstatus: ~0 rows (approximately)
/*!40000 ALTER TABLE `al_ref_match_actionstatus` DISABLE KEYS */;
/*!40000 ALTER TABLE `al_ref_match_actionstatus` ENABLE KEYS */;

-- Dumping structure for table yug_owner.al_ref_match_proposed_action
CREATE TABLE IF NOT EXISTS `al_ref_match_proposed_action` (
  `AUDITLOG_ID_PK` varchar(50) COLLATE utf8_bin NOT NULL,
  `AUDITLOG_CREATED_TS` datetime(6) NOT NULL,
  `AUDITLOG_ACTION_CODE` varchar(1) COLLATE utf8_bin NOT NULL,
  `ID_PK` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `VERSION` decimal(22,0) NOT NULL,
  `CREATED_TS` datetime(6) NOT NULL,
  `DELETED_TS` datetime(6) DEFAULT NULL,
  `UPDATED_TS` datetime(6) NOT NULL,
  `UPDATED_BY_USER` varchar(50) COLLATE utf8_bin NOT NULL,
  `UPDATED_BY_TXN_ID` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `CONFIG_LANGUAGE_CODE_KEY` varchar(50) COLLATE utf8_bin NOT NULL,
  `KEY` varchar(50) COLLATE utf8_bin NOT NULL,
  `VALUE` varchar(50) COLLATE utf8_bin NOT NULL,
  `DESCRIPTION` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`AUDITLOG_ID_PK`),
  KEY `IDXAL_REF_MATCH_PROPOSED_ACT` (`ID_PK`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- Dumping data for table yug_owner.al_ref_match_proposed_action: ~0 rows (approximately)
/*!40000 ALTER TABLE `al_ref_match_proposed_action` DISABLE KEYS */;
/*!40000 ALTER TABLE `al_ref_match_proposed_action` ENABLE KEYS */;

-- Dumping structure for table yug_owner.al_ref_match_result
CREATE TABLE IF NOT EXISTS `al_ref_match_result` (
  `AUDITLOG_ID_PK` varchar(50) COLLATE utf8_bin NOT NULL,
  `AUDITLOG_CREATED_TS` datetime(6) NOT NULL,
  `AUDITLOG_ACTION_CODE` varchar(1) COLLATE utf8_bin NOT NULL,
  `ID_PK` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `VERSION` decimal(22,0) NOT NULL,
  `CREATED_TS` datetime(6) NOT NULL,
  `DELETED_TS` datetime(6) DEFAULT NULL,
  `UPDATED_TS` datetime(6) NOT NULL,
  `UPDATED_BY_USER` varchar(50) COLLATE utf8_bin NOT NULL,
  `UPDATED_BY_TXN_ID` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `CONFIG_LANGUAGE_CODE_KEY` varchar(50) COLLATE utf8_bin NOT NULL,
  `KEY` varchar(50) COLLATE utf8_bin NOT NULL,
  `VALUE` varchar(50) COLLATE utf8_bin NOT NULL,
  `DESCRIPTION` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`AUDITLOG_ID_PK`),
  KEY `IDXAL_REF_MATCH_RESULT` (`ID_PK`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- Dumping data for table yug_owner.al_ref_match_result: ~0 rows (approximately)
/*!40000 ALTER TABLE `al_ref_match_result` DISABLE KEYS */;
/*!40000 ALTER TABLE `al_ref_match_result` ENABLE KEYS */;

-- Dumping structure for table yug_owner.al_ref_match_score
CREATE TABLE IF NOT EXISTS `al_ref_match_score` (
  `AUDITLOG_ID_PK` varchar(50) COLLATE utf8_bin NOT NULL,
  `AUDITLOG_CREATED_TS` datetime(6) NOT NULL,
  `AUDITLOG_ACTION_CODE` varchar(1) COLLATE utf8_bin NOT NULL,
  `ID_PK` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `VERSION` decimal(22,0) NOT NULL,
  `CREATED_TS` datetime(6) NOT NULL,
  `DELETED_TS` datetime(6) DEFAULT NULL,
  `UPDATED_TS` datetime(6) NOT NULL,
  `UPDATED_BY_USER` varchar(50) COLLATE utf8_bin NOT NULL,
  `UPDATED_BY_TXN_ID` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `MATCH_ENTITY_OBJECT_NAME` varchar(100) COLLATE utf8_bin NOT NULL,
  `MATCH_ATTR_PATTERN` varchar(50) COLLATE utf8_bin NOT NULL,
  `MATCH_RESULT_REFKEY` varchar(50) COLLATE utf8_bin NOT NULL,
  `MATCH_PROPOSED_ACTION_REFKEY` varchar(50) COLLATE utf8_bin NOT NULL,
  `MATCH_ATTR_PATTERN_DESCR` varchar(500) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`AUDITLOG_ID_PK`),
  KEY `IDXAL_REF_MATCH_SCORE` (`ID_PK`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- Dumping data for table yug_owner.al_ref_match_score: ~0 rows (approximately)
/*!40000 ALTER TABLE `al_ref_match_score` DISABLE KEYS */;
/*!40000 ALTER TABLE `al_ref_match_score` ENABLE KEYS */;

-- Dumping structure for table yug_owner.al_ref_match_threshold
CREATE TABLE IF NOT EXISTS `al_ref_match_threshold` (
  `AUDITLOG_ID_PK` varchar(50) COLLATE utf8_bin NOT NULL,
  `AUDITLOG_CREATED_TS` datetime(6) NOT NULL,
  `AUDITLOG_ACTION_CODE` varchar(1) COLLATE utf8_bin NOT NULL,
  `ID_PK` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `VERSION` decimal(22,0) NOT NULL,
  `CREATED_TS` datetime(6) NOT NULL,
  `DELETED_TS` datetime(6) DEFAULT NULL,
  `UPDATED_TS` datetime(6) NOT NULL,
  `UPDATED_BY_USER` varchar(50) COLLATE utf8_bin NOT NULL,
  `UPDATED_BY_TXN_ID` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `ATTR_BLOCK_NAME` varchar(100) COLLATE utf8_bin NOT NULL,
  `MATCH_THRESHOLD` decimal(22,0) NOT NULL,
  `DECAY_THRESHOLD_IN_DAYS` decimal(22,0) NOT NULL,
  `DECAY_PERCENTAGE` double(11,8) NOT NULL,
  `MAX_DECAY_PERCENTAGE` decimal(22,0) NOT NULL,
  PRIMARY KEY (`AUDITLOG_ID_PK`),
  KEY `IDXAL_REF_MATCH_THRESHOLD` (`ID_PK`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- Dumping data for table yug_owner.al_ref_match_threshold: ~0 rows (approximately)
/*!40000 ALTER TABLE `al_ref_match_threshold` DISABLE KEYS */;
/*!40000 ALTER TABLE `al_ref_match_threshold` ENABLE KEYS */;

-- Dumping structure for table yug_owner.al_ref_merge_reason
CREATE TABLE IF NOT EXISTS `al_ref_merge_reason` (
  `AUDITLOG_ID_PK` varchar(50) COLLATE utf8_bin NOT NULL,
  `AUDITLOG_CREATED_TS` datetime(6) NOT NULL,
  `AUDITLOG_ACTION_CODE` varchar(1) COLLATE utf8_bin NOT NULL,
  `ID_PK` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `VERSION` decimal(22,0) NOT NULL,
  `CREATED_TS` datetime(6) NOT NULL,
  `DELETED_TS` datetime(6) DEFAULT NULL,
  `UPDATED_TS` datetime(6) NOT NULL,
  `UPDATED_BY_USER` varchar(50) COLLATE utf8_bin NOT NULL,
  `UPDATED_BY_TXN_ID` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `CONFIG_LANGUAGE_CODE_KEY` varchar(50) COLLATE utf8_bin NOT NULL,
  `KEY` varchar(50) COLLATE utf8_bin NOT NULL,
  `VALUE` varchar(50) COLLATE utf8_bin NOT NULL,
  `DESCRIPTION` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`AUDITLOG_ID_PK`),
  KEY `IDXAL_REF_MERGE_REASON` (`ID_PK`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- Dumping data for table yug_owner.al_ref_merge_reason: ~0 rows (approximately)
/*!40000 ALTER TABLE `al_ref_merge_reason` DISABLE KEYS */;
/*!40000 ALTER TABLE `al_ref_merge_reason` ENABLE KEYS */;

-- Dumping structure for table yug_owner.al_ref_personname_type
CREATE TABLE IF NOT EXISTS `al_ref_personname_type` (
  `AUDITLOG_ID_PK` varchar(50) COLLATE utf8_bin NOT NULL,
  `AUDITLOG_CREATED_TS` datetime(6) NOT NULL,
  `AUDITLOG_ACTION_CODE` varchar(1) COLLATE utf8_bin NOT NULL,
  `ID_PK` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `VERSION` decimal(22,0) NOT NULL,
  `CREATED_TS` datetime(6) NOT NULL,
  `DELETED_TS` datetime(6) DEFAULT NULL,
  `UPDATED_TS` datetime(6) NOT NULL,
  `UPDATED_BY_USER` varchar(50) COLLATE utf8_bin NOT NULL,
  `UPDATED_BY_TXN_ID` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `CONFIG_LANGUAGE_CODE_KEY` varchar(50) COLLATE utf8_bin NOT NULL,
  `KEY` varchar(50) COLLATE utf8_bin NOT NULL,
  `VALUE` varchar(50) COLLATE utf8_bin NOT NULL,
  `DESCRIPTION` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`AUDITLOG_ID_PK`),
  KEY `IDXAL_REF_PERSONNAME_TYPE` (`ID_PK`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- Dumping data for table yug_owner.al_ref_personname_type: ~0 rows (approximately)
/*!40000 ALTER TABLE `al_ref_personname_type` DISABLE KEYS */;
/*!40000 ALTER TABLE `al_ref_personname_type` ENABLE KEYS */;

-- Dumping structure for table yug_owner.al_ref_person_type
CREATE TABLE IF NOT EXISTS `al_ref_person_type` (
  `AUDITLOG_ID_PK` varchar(50) COLLATE utf8_bin NOT NULL,
  `AUDITLOG_CREATED_TS` datetime(6) NOT NULL,
  `AUDITLOG_ACTION_CODE` varchar(1) COLLATE utf8_bin NOT NULL,
  `ID_PK` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `VERSION` decimal(22,0) NOT NULL,
  `CREATED_TS` datetime(6) NOT NULL,
  `DELETED_TS` datetime(6) DEFAULT NULL,
  `UPDATED_TS` datetime(6) NOT NULL,
  `UPDATED_BY_USER` varchar(50) COLLATE utf8_bin NOT NULL,
  `UPDATED_BY_TXN_ID` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `CONFIG_LANGUAGE_CODE_KEY` varchar(50) COLLATE utf8_bin NOT NULL,
  `KEY` varchar(50) COLLATE utf8_bin NOT NULL,
  `VALUE` varchar(50) COLLATE utf8_bin NOT NULL,
  `DESCRIPTION` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`AUDITLOG_ID_PK`),
  KEY `IDXAL_REF_PERSON_TYPE` (`ID_PK`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- Dumping data for table yug_owner.al_ref_person_type: ~0 rows (approximately)
/*!40000 ALTER TABLE `al_ref_person_type` DISABLE KEYS */;
/*!40000 ALTER TABLE `al_ref_person_type` ENABLE KEYS */;

-- Dumping structure for table yug_owner.al_ref_phone_subtype
CREATE TABLE IF NOT EXISTS `al_ref_phone_subtype` (
  `AUDITLOG_ID_PK` varchar(50) COLLATE utf8_bin NOT NULL,
  `AUDITLOG_CREATED_TS` datetime(6) NOT NULL,
  `AUDITLOG_ACTION_CODE` varchar(1) COLLATE utf8_bin NOT NULL,
  `ID_PK` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `VERSION` decimal(22,0) NOT NULL,
  `CREATED_TS` datetime(6) NOT NULL,
  `DELETED_TS` datetime(6) DEFAULT NULL,
  `UPDATED_TS` datetime(6) NOT NULL,
  `UPDATED_BY_USER` varchar(50) COLLATE utf8_bin NOT NULL,
  `UPDATED_BY_TXN_ID` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `CONFIG_LANGUAGE_CODE_KEY` varchar(50) COLLATE utf8_bin NOT NULL,
  `KEY` varchar(50) COLLATE utf8_bin NOT NULL,
  `VALUE` varchar(50) COLLATE utf8_bin NOT NULL,
  `DESCRIPTION` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`AUDITLOG_ID_PK`),
  KEY `IDXAL_REF_PHONE_SUBTYPE` (`ID_PK`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- Dumping data for table yug_owner.al_ref_phone_subtype: ~0 rows (approximately)
/*!40000 ALTER TABLE `al_ref_phone_subtype` DISABLE KEYS */;
/*!40000 ALTER TABLE `al_ref_phone_subtype` ENABLE KEYS */;

-- Dumping structure for table yug_owner.al_ref_phone_type
CREATE TABLE IF NOT EXISTS `al_ref_phone_type` (
  `AUDITLOG_ID_PK` varchar(50) COLLATE utf8_bin NOT NULL,
  `AUDITLOG_CREATED_TS` datetime(6) NOT NULL,
  `AUDITLOG_ACTION_CODE` varchar(1) COLLATE utf8_bin NOT NULL,
  `ID_PK` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `VERSION` decimal(22,0) NOT NULL,
  `CREATED_TS` datetime(6) NOT NULL,
  `DELETED_TS` datetime(6) DEFAULT NULL,
  `UPDATED_TS` datetime(6) NOT NULL,
  `UPDATED_BY_USER` varchar(50) COLLATE utf8_bin NOT NULL,
  `UPDATED_BY_TXN_ID` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `CONFIG_LANGUAGE_CODE_KEY` varchar(50) COLLATE utf8_bin NOT NULL,
  `KEY` varchar(50) COLLATE utf8_bin NOT NULL,
  `VALUE` varchar(50) COLLATE utf8_bin NOT NULL,
  `DESCRIPTION` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`AUDITLOG_ID_PK`),
  KEY `IDXAL_REF_PHONE_TYPE` (`ID_PK`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- Dumping data for table yug_owner.al_ref_phone_type: ~0 rows (approximately)
/*!40000 ALTER TABLE `al_ref_phone_type` DISABLE KEYS */;
/*!40000 ALTER TABLE `al_ref_phone_type` ENABLE KEYS */;

-- Dumping structure for table yug_owner.al_ref_preference_type
CREATE TABLE IF NOT EXISTS `al_ref_preference_type` (
  `AUDITLOG_ID_PK` varchar(50) COLLATE utf8_bin NOT NULL,
  `AUDITLOG_CREATED_TS` datetime(6) NOT NULL,
  `AUDITLOG_ACTION_CODE` varchar(1) COLLATE utf8_bin NOT NULL,
  `ID_PK` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `VERSION` decimal(22,0) NOT NULL,
  `CREATED_TS` datetime(6) NOT NULL,
  `DELETED_TS` datetime(6) DEFAULT NULL,
  `UPDATED_TS` datetime(6) NOT NULL,
  `UPDATED_BY_USER` varchar(50) COLLATE utf8_bin NOT NULL,
  `UPDATED_BY_TXN_ID` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `CONFIG_LANGUAGE_CODE_KEY` varchar(50) COLLATE utf8_bin NOT NULL,
  `KEY` varchar(50) COLLATE utf8_bin NOT NULL,
  `VALUE` varchar(50) COLLATE utf8_bin NOT NULL,
  `DESCRIPTION` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`AUDITLOG_ID_PK`),
  KEY `IDXAL_REF_PREFERENCE_TYPE` (`ID_PK`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- Dumping data for table yug_owner.al_ref_preference_type: ~0 rows (approximately)
/*!40000 ALTER TABLE `al_ref_preference_type` DISABLE KEYS */;
/*!40000 ALTER TABLE `al_ref_preference_type` ENABLE KEYS */;

-- Dumping structure for table yug_owner.al_ref_prefix_name
CREATE TABLE IF NOT EXISTS `al_ref_prefix_name` (
  `AUDITLOG_ID_PK` varchar(50) COLLATE utf8_bin NOT NULL,
  `AUDITLOG_CREATED_TS` datetime(6) NOT NULL,
  `AUDITLOG_ACTION_CODE` varchar(1) COLLATE utf8_bin NOT NULL,
  `ID_PK` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `VERSION` decimal(22,0) NOT NULL,
  `CREATED_TS` datetime(6) NOT NULL,
  `DELETED_TS` datetime(6) DEFAULT NULL,
  `UPDATED_TS` datetime(6) NOT NULL,
  `UPDATED_BY_USER` varchar(50) COLLATE utf8_bin NOT NULL,
  `UPDATED_BY_TXN_ID` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `CONFIG_LANGUAGE_CODE_KEY` varchar(50) COLLATE utf8_bin NOT NULL,
  `KEY` varchar(50) COLLATE utf8_bin NOT NULL,
  `VALUE` varchar(50) COLLATE utf8_bin NOT NULL,
  `DESCRIPTION` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`AUDITLOG_ID_PK`),
  KEY `IDXAL_REF_PREFIX_NAME` (`ID_PK`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- Dumping data for table yug_owner.al_ref_prefix_name: ~0 rows (approximately)
/*!40000 ALTER TABLE `al_ref_prefix_name` DISABLE KEYS */;
/*!40000 ALTER TABLE `al_ref_prefix_name` ENABLE KEYS */;

-- Dumping structure for table yug_owner.al_ref_property_le_reltype
CREATE TABLE IF NOT EXISTS `al_ref_property_le_reltype` (
  `AUDITLOG_ID_PK` varchar(50) COLLATE utf8_bin NOT NULL,
  `AUDITLOG_CREATED_TS` datetime(6) NOT NULL,
  `AUDITLOG_ACTION_CODE` varchar(1) COLLATE utf8_bin NOT NULL,
  `ID_PK` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `VERSION` decimal(22,0) NOT NULL,
  `CREATED_TS` datetime(6) NOT NULL,
  `DELETED_TS` datetime(6) DEFAULT NULL,
  `UPDATED_TS` datetime(6) NOT NULL,
  `UPDATED_BY_USER` varchar(50) COLLATE utf8_bin NOT NULL,
  `UPDATED_BY_TXN_ID` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `CONFIG_LANGUAGE_CODE_KEY` varchar(50) COLLATE utf8_bin NOT NULL,
  `KEY` varchar(50) COLLATE utf8_bin NOT NULL,
  `VALUE` varchar(50) COLLATE utf8_bin NOT NULL,
  `DESCRIPTION` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`AUDITLOG_ID_PK`),
  KEY `IDXAL_REF_PROPERTY_LE_RELTYPE` (`ID_PK`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- Dumping data for table yug_owner.al_ref_property_le_reltype: ~0 rows (approximately)
/*!40000 ALTER TABLE `al_ref_property_le_reltype` DISABLE KEYS */;
/*!40000 ALTER TABLE `al_ref_property_le_reltype` ENABLE KEYS */;

-- Dumping structure for table yug_owner.al_ref_relationship_status
CREATE TABLE IF NOT EXISTS `al_ref_relationship_status` (
  `AUDITLOG_ID_PK` varchar(50) COLLATE utf8_bin NOT NULL,
  `AUDITLOG_CREATED_TS` datetime(6) NOT NULL,
  `AUDITLOG_ACTION_CODE` varchar(1) COLLATE utf8_bin NOT NULL,
  `ID_PK` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `VERSION` decimal(22,0) NOT NULL,
  `CREATED_TS` datetime(6) NOT NULL,
  `DELETED_TS` datetime(6) DEFAULT NULL,
  `UPDATED_TS` datetime(6) NOT NULL,
  `UPDATED_BY_USER` varchar(50) COLLATE utf8_bin NOT NULL,
  `UPDATED_BY_TXN_ID` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `CONFIG_LANGUAGE_CODE_KEY` varchar(50) COLLATE utf8_bin NOT NULL,
  `KEY` varchar(50) COLLATE utf8_bin NOT NULL,
  `VALUE` varchar(50) COLLATE utf8_bin NOT NULL,
  `DESCRIPTION` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`AUDITLOG_ID_PK`),
  KEY `IDXAL_REF_RELATIONSHIP_STATUS` (`ID_PK`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- Dumping data for table yug_owner.al_ref_relationship_status: ~0 rows (approximately)
/*!40000 ALTER TABLE `al_ref_relationship_status` DISABLE KEYS */;
/*!40000 ALTER TABLE `al_ref_relationship_status` ENABLE KEYS */;

-- Dumping structure for table yug_owner.al_ref_source_system
CREATE TABLE IF NOT EXISTS `al_ref_source_system` (
  `AUDITLOG_ID_PK` varchar(50) COLLATE utf8_bin NOT NULL,
  `AUDITLOG_CREATED_TS` datetime(6) NOT NULL,
  `AUDITLOG_ACTION_CODE` varchar(1) COLLATE utf8_bin NOT NULL,
  `ID_PK` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `VERSION` decimal(22,0) NOT NULL,
  `CREATED_TS` datetime(6) NOT NULL,
  `DELETED_TS` datetime(6) DEFAULT NULL,
  `UPDATED_TS` datetime(6) NOT NULL,
  `UPDATED_BY_USER` varchar(50) COLLATE utf8_bin NOT NULL,
  `UPDATED_BY_TXN_ID` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `CONFIG_LANGUAGE_CODE_KEY` varchar(50) COLLATE utf8_bin NOT NULL,
  `KEY` varchar(50) COLLATE utf8_bin NOT NULL,
  `VALUE` varchar(50) COLLATE utf8_bin NOT NULL,
  `DESCRIPTION` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`AUDITLOG_ID_PK`),
  KEY `IDXAL_REF_SOURCE_SYSTEM` (`ID_PK`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- Dumping data for table yug_owner.al_ref_source_system: ~0 rows (approximately)
/*!40000 ALTER TABLE `al_ref_source_system` DISABLE KEYS */;
/*!40000 ALTER TABLE `al_ref_source_system` ENABLE KEYS */;

-- Dumping structure for table yug_owner.al_ref_state_province
CREATE TABLE IF NOT EXISTS `al_ref_state_province` (
  `AUDITLOG_ID_PK` varchar(50) COLLATE utf8_bin NOT NULL,
  `AUDITLOG_CREATED_TS` datetime(6) NOT NULL,
  `AUDITLOG_ACTION_CODE` varchar(1) COLLATE utf8_bin NOT NULL,
  `ID_PK` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `VERSION` decimal(22,0) NOT NULL,
  `CREATED_TS` datetime(6) NOT NULL,
  `DELETED_TS` datetime(6) DEFAULT NULL,
  `UPDATED_TS` datetime(6) NOT NULL,
  `UPDATED_BY_USER` varchar(50) COLLATE utf8_bin NOT NULL,
  `UPDATED_BY_TXN_ID` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `CONFIG_LANGUAGE_CODE_KEY` varchar(50) COLLATE utf8_bin NOT NULL,
  `KEY` varchar(50) COLLATE utf8_bin NOT NULL,
  `VALUE` varchar(50) COLLATE utf8_bin NOT NULL,
  `DESCRIPTION` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `COUNTRY_ISO_REFKEY` varchar(50) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`AUDITLOG_ID_PK`),
  KEY `IDXAL_REF_STATE_PROVINCE` (`ID_PK`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- Dumping data for table yug_owner.al_ref_state_province: ~0 rows (approximately)
/*!40000 ALTER TABLE `al_ref_state_province` DISABLE KEYS */;
/*!40000 ALTER TABLE `al_ref_state_province` ENABLE KEYS */;

-- Dumping structure for table yug_owner.al_ref_status_in_source
CREATE TABLE IF NOT EXISTS `al_ref_status_in_source` (
  `AUDITLOG_ID_PK` varchar(50) COLLATE utf8_bin NOT NULL,
  `AUDITLOG_CREATED_TS` datetime(6) NOT NULL,
  `AUDITLOG_ACTION_CODE` varchar(1) COLLATE utf8_bin NOT NULL,
  `ID_PK` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `VERSION` decimal(22,0) NOT NULL,
  `CREATED_TS` datetime(6) NOT NULL,
  `DELETED_TS` datetime(6) DEFAULT NULL,
  `UPDATED_TS` datetime(6) NOT NULL,
  `UPDATED_BY_USER` varchar(50) COLLATE utf8_bin NOT NULL,
  `UPDATED_BY_TXN_ID` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `CONFIG_LANGUAGE_CODE_KEY` varchar(50) COLLATE utf8_bin NOT NULL,
  `KEY` varchar(50) COLLATE utf8_bin NOT NULL,
  `VALUE` varchar(50) COLLATE utf8_bin NOT NULL,
  `DESCRIPTION` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`AUDITLOG_ID_PK`),
  KEY `IDXAL_REF_STATUS_IN_SOURCE` (`ID_PK`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- Dumping data for table yug_owner.al_ref_status_in_source: ~0 rows (approximately)
/*!40000 ALTER TABLE `al_ref_status_in_source` DISABLE KEYS */;
/*!40000 ALTER TABLE `al_ref_status_in_source` ENABLE KEYS */;

-- Dumping structure for table yug_owner.al_ref_status_type
CREATE TABLE IF NOT EXISTS `al_ref_status_type` (
  `AUDITLOG_ID_PK` varchar(50) COLLATE utf8_bin NOT NULL,
  `AUDITLOG_CREATED_TS` datetime(6) NOT NULL,
  `AUDITLOG_ACTION_CODE` varchar(1) COLLATE utf8_bin NOT NULL,
  `ID_PK` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `VERSION` decimal(22,0) NOT NULL,
  `CREATED_TS` datetime(6) NOT NULL,
  `DELETED_TS` datetime(6) DEFAULT NULL,
  `UPDATED_TS` datetime(6) NOT NULL,
  `UPDATED_BY_USER` varchar(50) COLLATE utf8_bin NOT NULL,
  `UPDATED_BY_TXN_ID` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `CONFIG_LANGUAGE_CODE_KEY` varchar(50) COLLATE utf8_bin NOT NULL,
  `KEY` varchar(50) COLLATE utf8_bin NOT NULL,
  `VALUE` varchar(50) COLLATE utf8_bin NOT NULL,
  `DESCRIPTION` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`AUDITLOG_ID_PK`),
  KEY `IDXAL_REF_STATUS_TYPE` (`ID_PK`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- Dumping data for table yug_owner.al_ref_status_type: ~0 rows (approximately)
/*!40000 ALTER TABLE `al_ref_status_type` DISABLE KEYS */;
/*!40000 ALTER TABLE `al_ref_status_type` ENABLE KEYS */;

-- Dumping structure for table yug_owner.al_ref_suffix_name
CREATE TABLE IF NOT EXISTS `al_ref_suffix_name` (
  `AUDITLOG_ID_PK` varchar(50) COLLATE utf8_bin NOT NULL,
  `AUDITLOG_CREATED_TS` datetime(6) NOT NULL,
  `AUDITLOG_ACTION_CODE` varchar(1) COLLATE utf8_bin NOT NULL,
  `ID_PK` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `VERSION` decimal(22,0) NOT NULL,
  `CREATED_TS` datetime(6) NOT NULL,
  `DELETED_TS` datetime(6) DEFAULT NULL,
  `UPDATED_TS` datetime(6) NOT NULL,
  `UPDATED_BY_USER` varchar(50) COLLATE utf8_bin NOT NULL,
  `UPDATED_BY_TXN_ID` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `CONFIG_LANGUAGE_CODE_KEY` varchar(50) COLLATE utf8_bin NOT NULL,
  `KEY` varchar(50) COLLATE utf8_bin NOT NULL,
  `VALUE` varchar(50) COLLATE utf8_bin NOT NULL,
  `DESCRIPTION` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`AUDITLOG_ID_PK`),
  KEY `IDXAL_REF_SUFFIX_NAME` (`ID_PK`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- Dumping data for table yug_owner.al_ref_suffix_name: ~0 rows (approximately)
/*!40000 ALTER TABLE `al_ref_suffix_name` DISABLE KEYS */;
/*!40000 ALTER TABLE `al_ref_suffix_name` ENABLE KEYS */;

-- Dumping structure for table yug_owner.al_ref_termination_reason
CREATE TABLE IF NOT EXISTS `al_ref_termination_reason` (
  `AUDITLOG_ID_PK` varchar(50) COLLATE utf8_bin NOT NULL,
  `AUDITLOG_CREATED_TS` datetime(6) NOT NULL,
  `AUDITLOG_ACTION_CODE` varchar(1) COLLATE utf8_bin NOT NULL,
  `ID_PK` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `VERSION` decimal(22,0) NOT NULL,
  `CREATED_TS` datetime(6) NOT NULL,
  `DELETED_TS` datetime(6) DEFAULT NULL,
  `UPDATED_TS` datetime(6) NOT NULL,
  `UPDATED_BY_USER` varchar(50) COLLATE utf8_bin NOT NULL,
  `UPDATED_BY_TXN_ID` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `CONFIG_LANGUAGE_CODE_KEY` varchar(50) COLLATE utf8_bin NOT NULL,
  `KEY` varchar(50) COLLATE utf8_bin NOT NULL,
  `VALUE` varchar(50) COLLATE utf8_bin NOT NULL,
  `DESCRIPTION` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`AUDITLOG_ID_PK`),
  KEY `IDXAL_REF_TERMINATION_REASON` (`ID_PK`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- Dumping data for table yug_owner.al_ref_termination_reason: ~0 rows (approximately)
/*!40000 ALTER TABLE `al_ref_termination_reason` DISABLE KEYS */;
/*!40000 ALTER TABLE `al_ref_termination_reason` ENABLE KEYS */;

-- Dumping structure for table yug_owner.al_vehicle
CREATE TABLE IF NOT EXISTS `al_vehicle` (
  `AUDITLOG_ID_PK` varchar(50) COLLATE utf8_bin NOT NULL,
  `AUDITLOG_CREATED_TS` datetime(6) NOT NULL,
  `AUDITLOG_ACTION_CODE` varchar(1) COLLATE utf8_bin NOT NULL,
  `ID_PK` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `VERSION` decimal(22,0) NOT NULL,
  `CREATED_TS` datetime(6) NOT NULL,
  `DELETED_TS` datetime(6) DEFAULT NULL,
  `UPDATED_TS` datetime(6) NOT NULL,
  `UPDATED_BY_USER` varchar(50) COLLATE utf8_bin NOT NULL,
  `UPDATED_BY_TXN_ID` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `VIN_NUMBER` varchar(100) COLLATE utf8_bin NOT NULL,
  `CHASSIS_NUMBER` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `MAKE` varchar(30) COLLATE utf8_bin DEFAULT NULL,
  `MODEL` varchar(30) COLLATE utf8_bin DEFAULT NULL,
  `YEAR` varchar(30) COLLATE utf8_bin DEFAULT NULL,
  `INTERIOR_COLOR` varchar(30) COLLATE utf8_bin DEFAULT NULL,
  `EXTERIOR_COLOR` varchar(30) COLLATE utf8_bin DEFAULT NULL,
  `REGISTRATION_NUMBER` varchar(30) COLLATE utf8_bin DEFAULT NULL,
  `COUNTRY_OF_REGISTRATION_REFKEY` varchar(30) COLLATE utf8_bin DEFAULT NULL,
  `INSURANCE_ISSUED_BY` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `INSURANCE_ISSUED_DATE` datetime DEFAULT NULL,
  `INSURANCE_EXPIRY_DATE` datetime DEFAULT NULL,
  PRIMARY KEY (`AUDITLOG_ID_PK`),
  KEY `IDXAL_VEHICLE` (`ID_PK`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- Dumping data for table yug_owner.al_vehicle: ~0 rows (approximately)
/*!40000 ALTER TABLE `al_vehicle` DISABLE KEYS */;
/*!40000 ALTER TABLE `al_vehicle` ENABLE KEYS */;

-- Dumping structure for table yug_owner.auth_roles_registry
CREATE TABLE IF NOT EXISTS `auth_roles_registry` (
  `ID_PK` varchar(50) COLLATE utf8_bin NOT NULL,
  `VERSION` decimal(22,0) NOT NULL,
  `CREATED_TS` datetime(6) NOT NULL,
  `DELETED_TS` datetime(6) DEFAULT NULL,
  `UPDATED_TS` datetime(6) NOT NULL,
  `UPDATED_BY_USER` varchar(50) COLLATE utf8_bin NOT NULL,
  `UPDATED_BY_TXN_ID` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `ROLE_NAME` varchar(400) COLLATE utf8_bin NOT NULL,
  `DESCRIPTION` varchar(150) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_PK`),
  UNIQUE KEY `AUTH_ROLES_REGISTRY_UNIQUEKEY` (`ROLE_NAME`(255))
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- Dumping data for table yug_owner.auth_roles_registry: ~2 rows (approximately)
/*!40000 ALTER TABLE `auth_roles_registry` DISABLE KEYS */;
INSERT INTO `auth_roles_registry` (`ID_PK`, `VERSION`, `CREATED_TS`, `DELETED_TS`, `UPDATED_TS`, `UPDATED_BY_USER`, `UPDATED_BY_TXN_ID`, `ROLE_NAME`, `DESCRIPTION`) VALUES
	('7d96d881-257b-4862-b82a-cc517b0a6891', 1, '2017-10-02 16:06:39.000000', NULL, '2017-10-02 16:07:28.000000', 'admin', '1234567890123', 'GUEST', 'GUEST'),
	('ff142ad4-33d8-4827-a094-e7506c732536', 0, '2017-10-02 16:06:22.000000', NULL, '2017-10-02 16:06:22.000000', 'admin', '1234567890123', 'admin', 'admin');
/*!40000 ALTER TABLE `auth_roles_registry` ENABLE KEYS */;

-- Dumping structure for table yug_owner.auth_userrole_accesscontrol
CREATE TABLE IF NOT EXISTS `auth_userrole_accesscontrol` (
  `ID_PK` varchar(50) COLLATE utf8_bin NOT NULL,
  `VERSION` decimal(22,0) NOT NULL,
  `CREATED_TS` datetime(6) NOT NULL,
  `DELETED_TS` datetime(6) DEFAULT NULL,
  `UPDATED_TS` datetime(6) NOT NULL,
  `UPDATED_BY_USER` varchar(50) COLLATE utf8_bin NOT NULL,
  `UPDATED_BY_TXN_ID` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `PROFILE_TYPE` varchar(80) COLLATE utf8_bin NOT NULL,
  `AUTH_USER_ROLE_REGISTRY_IDPK` varchar(200) COLLATE utf8_bin NOT NULL,
  `CONFIG_TXN_REGISTRY_IDPK` varchar(200) COLLATE utf8_bin NOT NULL,
  `DESCRIPTION` varchar(600) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_PK`),
  UNIQUE KEY `SYS_C0019271` (`CONFIG_TXN_REGISTRY_IDPK`,`AUTH_USER_ROLE_REGISTRY_IDPK`,`PROFILE_TYPE`),
  KEY `INDX_AUTH_USERROLE_ACCNTRL1` (`PROFILE_TYPE`),
  KEY `INDX_AUTH_USERROLE_ACCNTRL2` (`AUTH_USER_ROLE_REGISTRY_IDPK`),
  KEY `INDX_AUTH_USERROLE_ACCNTRL3` (`CONFIG_TXN_REGISTRY_IDPK`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- Dumping data for table yug_owner.auth_userrole_accesscontrol: ~8 rows (approximately)
/*!40000 ALTER TABLE `auth_userrole_accesscontrol` DISABLE KEYS */;
INSERT INTO `auth_userrole_accesscontrol` (`ID_PK`, `VERSION`, `CREATED_TS`, `DELETED_TS`, `UPDATED_TS`, `UPDATED_BY_USER`, `UPDATED_BY_TXN_ID`, `PROFILE_TYPE`, `AUTH_USER_ROLE_REGISTRY_IDPK`, `CONFIG_TXN_REGISTRY_IDPK`, `DESCRIPTION`) VALUES
	('04cab634-bf3c-47ac-92d2-ea57e75fa173', 0, '2017-10-03 17:34:04.000000', NULL, '2017-10-03 17:34:04.000000', 'admin', '1234567890123', 'USER', '83474f01-6efa-4c0c-af30-afd9057278b4', '314', 'USER access control'),
	('4d5f4974-8d59-42ad-9052-7e5d945df1d9', 0, '2017-10-03 17:33:58.000000', NULL, '2017-10-03 17:33:58.000000', 'admin', '1234567890123', 'USER', '83474f01-6efa-4c0c-af30-afd9057278b4', '494', 'USER access control'),
	('5bf6c3f8-ccf2-4e42-b9f1-adfc48a2c3c5', 0, '2017-10-03 17:34:01.000000', NULL, '2017-10-03 17:34:01.000000', 'admin', '1234567890123', 'USER', '83474f01-6efa-4c0c-af30-afd9057278b4', '317', 'USER access control'),
	('b0e8ec88-06ae-41be-9b79-e854736ae8e4', 0, '2017-10-08 16:52:53.000000', NULL, '2017-10-08 16:52:53.000000', 'admin', '1234567890123', 'ROLE', 'ff142ad4-33d8-4827-a094-e7506c732536', '317', 'USER access control'),
	('bd4c0395-4677-4e37-855f-d141245f7909', 1, '2017-10-03 17:21:43.000000', NULL, '2017-10-03 17:33:13.000000', 'admin', '1234567890123', 'USER', '83474f01-6efa-4c0c-af30-afd9057278b4', '495', 'user access control'),
	('fdfddde1-ff9f-49d1-9f3a-e0478e4a17ed', 0, '2017-10-11 14:34:56.000000', NULL, '2017-10-11 14:34:56.000000', 'admin', '1234567890123', 'USER', '83474f01-6efa-4c0c-af30-afd9057278b4', '311', 'createLegalEntity For User RAKESH'),
	('fdfddde1-ff9f-49d1-9f3a-e0478e4a17ee', 0, '2017-10-11 14:34:56.000000', NULL, '2017-10-11 14:34:56.000000', 'admin', '1234567890123', 'ROLE', 'ff142ad4-33d8-4827-a094-e7506c732536', '311', 'createLegalEntity For role admin'),
	('fdfddde1-ff9f-49d1-9f3a-e0478e4a17ef', 0, '2017-10-11 14:34:56.000000', NULL, '2017-10-11 14:34:56.000000', 'admin', '1234567890123', 'ROLE', 'ff142ad4-33d8-4827-a094-e7506c732536', '314', 'createLegalEntity For role admin');
/*!40000 ALTER TABLE `auth_userrole_accesscontrol` ENABLE KEYS */;

-- Dumping structure for table yug_owner.auth_user_registry
CREATE TABLE IF NOT EXISTS `auth_user_registry` (
  `ID_PK` varchar(50) COLLATE utf8_bin NOT NULL,
  `VERSION` decimal(22,0) NOT NULL,
  `CREATED_TS` datetime(6) NOT NULL,
  `DELETED_TS` datetime(6) DEFAULT NULL,
  `UPDATED_TS` datetime(6) NOT NULL,
  `UPDATED_BY_USER` varchar(50) COLLATE utf8_bin NOT NULL,
  `UPDATED_BY_TXN_ID` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `USER_NAME` varchar(400) COLLATE utf8_bin NOT NULL,
  `DESCRIPTION` varchar(150) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_PK`),
  UNIQUE KEY `AUTH_USER_ROLE_REGISTRY_UKEY` (`USER_NAME`(255))
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- Dumping data for table yug_owner.auth_user_registry: ~2 rows (approximately)
/*!40000 ALTER TABLE `auth_user_registry` DISABLE KEYS */;
INSERT INTO `auth_user_registry` (`ID_PK`, `VERSION`, `CREATED_TS`, `DELETED_TS`, `UPDATED_TS`, `UPDATED_BY_USER`, `UPDATED_BY_TXN_ID`, `USER_NAME`, `DESCRIPTION`) VALUES
	('103d98d2-b558-405b-9574-b6bdffa82bd5', 1, '2017-10-03 16:50:04.000000', NULL, '2017-10-03 16:50:35.000000', 'admin', '1234567890123', 'SNEHA', 'TEST USER'),
	('83474f01-6efa-4c0c-af30-afd9057278b4', 0, '2017-10-03 16:49:45.000000', NULL, '2017-10-03 16:49:45.000000', 'admin', '1234567890123', 'RAKESH', 'TEST USER');
/*!40000 ALTER TABLE `auth_user_registry` ENABLE KEYS */;

-- Dumping structure for table yug_owner.auth_user_role_assoc
CREATE TABLE IF NOT EXISTS `auth_user_role_assoc` (
  `ID_PK` varchar(50) COLLATE utf8_bin NOT NULL,
  `VERSION` decimal(22,0) NOT NULL,
  `CREATED_TS` datetime(6) NOT NULL,
  `DELETED_TS` datetime(6) DEFAULT NULL,
  `UPDATED_TS` datetime(6) NOT NULL,
  `UPDATED_BY_USER` varchar(50) COLLATE utf8_bin NOT NULL,
  `UPDATED_BY_TXN_ID` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `AUTH_USER_REGISTRY_IDPK` varchar(50) COLLATE utf8_bin NOT NULL,
  `AUTH_ROLES_REGISTRY_IDPK` varchar(50) COLLATE utf8_bin NOT NULL,
  `DESCRIPTION` varchar(150) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_PK`),
  UNIQUE KEY `AUTH_USER_ROLE_ASSOC_UKEY` (`AUTH_ROLES_REGISTRY_IDPK`,`AUTH_USER_REGISTRY_IDPK`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- Dumping data for table yug_owner.auth_user_role_assoc: ~3 rows (approximately)
/*!40000 ALTER TABLE `auth_user_role_assoc` DISABLE KEYS */;
INSERT INTO `auth_user_role_assoc` (`ID_PK`, `VERSION`, `CREATED_TS`, `DELETED_TS`, `UPDATED_TS`, `UPDATED_BY_USER`, `UPDATED_BY_TXN_ID`, `AUTH_USER_REGISTRY_IDPK`, `AUTH_ROLES_REGISTRY_IDPK`, `DESCRIPTION`) VALUES
	('02b352b6-12f9-48d2-8fa8-6139811815ff', 3, '2017-10-03 16:58:06.000000', NULL, '2017-10-03 16:58:54.000000', 'admin', '1234567890123', '103d98d2-b558-405b-9574-b6bdffa82bd5', '7d96d881-257b-4862-b82a-cc517b0a6891', 'ROLE to USER Mapping'),
	('c03dbf6e-5808-49b9-9cb8-384457499671', 0, '2017-10-03 16:57:29.000000', NULL, '2017-10-03 16:57:29.000000', 'admin', '1234567890123', '83474f01-6efa-4c0c-af30-afd9057278b4', 'ff142ad4-33d8-4827-a094-e7506c732536', 'ROLE to USER Mapping'),
	('f693c974-3962-449c-8e8d-71be0946d9da', 0, '2017-10-03 16:57:57.000000', NULL, '2017-10-03 16:57:57.000000', 'admin', '1234567890123', '83474f01-6efa-4c0c-af30-afd9057278b4', '7d96d881-257b-4862-b82a-cc517b0a6891', 'ROLE to USER Mapping');
/*!40000 ALTER TABLE `auth_user_role_assoc` ENABLE KEYS */;

-- Dumping structure for table yug_owner.batch_entity_to_process
CREATE TABLE IF NOT EXISTS `batch_entity_to_process` (
  `ID_PK` varchar(50) COLLATE utf8_bin NOT NULL,
  `VERSION` decimal(22,0) NOT NULL,
  `CREATED_TS` datetime(6) NOT NULL,
  `DELETED_TS` datetime(6) DEFAULT NULL,
  `UPDATED_TS` datetime(6) NOT NULL,
  `UPDATED_BY_USER` varchar(50) COLLATE utf8_bin NOT NULL,
  `UPDATED_BY_TXN_ID` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `ENTITY_OBJECT_TYPE_REFKEY` varchar(50) COLLATE utf8_bin NOT NULL,
  `ENTITY_IDPK` varchar(50) COLLATE utf8_bin NOT NULL,
  `BATCH_PROPOSED_ACTION_REFKEY` varchar(50) COLLATE utf8_bin NOT NULL,
  `BATCH_ACTION_STATUS_REFKEY` varchar(50) COLLATE utf8_bin NOT NULL,
  `PROCESS_AFTER_TIMESTAMP` datetime(6) DEFAULT NULL,
  `PROCESS_BEFORE_TIMESTAMP` datetime(6) DEFAULT NULL,
  `ENTRY_MADE_BY_SUBSYSTEM_NAME` varchar(50) COLLATE utf8_bin NOT NULL,
  `COMMENTS` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_PK`),
  KEY `INDX_BATCH_ENTPROCESS_7` (`ENTRY_MADE_BY_SUBSYSTEM_NAME`),
  KEY `INDX_BATCH_ENTPROCESS_6` (`PROCESS_BEFORE_TIMESTAMP`),
  KEY `INDX_BATCH_ENTPROCESS_5` (`PROCESS_AFTER_TIMESTAMP`),
  KEY `INDX_BATCH_ENTPROCESS_4` (`BATCH_ACTION_STATUS_REFKEY`),
  KEY `INDX_BATCH_ENTPROCESS_3` (`BATCH_PROPOSED_ACTION_REFKEY`),
  KEY `INDX_BATCH_ENTPROCESS_2` (`ENTITY_IDPK`),
  KEY `INDX_BATCH_ENTPROCESS_1` (`ENTITY_OBJECT_TYPE_REFKEY`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- Dumping data for table yug_owner.batch_entity_to_process: ~0 rows (approximately)
/*!40000 ALTER TABLE `batch_entity_to_process` DISABLE KEYS */;
/*!40000 ALTER TABLE `batch_entity_to_process` ENABLE KEYS */;

-- Dumping structure for table yug_owner.config_app_properties
CREATE TABLE IF NOT EXISTS `config_app_properties` (
  `ID_PK` varchar(50) COLLATE utf8_bin NOT NULL,
  `VERSION` decimal(22,0) NOT NULL,
  `CREATED_TS` datetime(6) NOT NULL,
  `DELETED_TS` datetime(6) DEFAULT NULL,
  `UPDATED_TS` datetime(6) NOT NULL,
  `UPDATED_BY_USER` varchar(50) COLLATE utf8_bin NOT NULL,
  `UPDATED_BY_TXN_ID` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `KEY` varchar(150) COLLATE utf8_bin NOT NULL,
  `VALUE` varchar(150) COLLATE utf8_bin NOT NULL,
  `VALUE_DEFAULT` varchar(150) COLLATE utf8_bin DEFAULT NULL,
  `DESCRIPTION` varchar(250) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_PK`),
  UNIQUE KEY `CONFIG_APPPROP_UNIQUEKEY` (`KEY`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- Dumping data for table yug_owner.config_app_properties: ~27 rows (approximately)
/*!40000 ALTER TABLE `config_app_properties` DISABLE KEYS */;
INSERT INTO `config_app_properties` (`ID_PK`, `VERSION`, `CREATED_TS`, `DELETED_TS`, `UPDATED_TS`, `UPDATED_BY_USER`, `UPDATED_BY_TXN_ID`, `KEY`, `VALUE`, `VALUE_DEFAULT`, `DESCRIPTION`) VALUES
	('063b151b-7f63-47df-aae9-c7e44af1a392', 0, '2017-09-18 12:16:42.000000', NULL, '2017-09-18 12:16:42.000000', 'admin', '00000000', 'com_yugandhar_inqlevel_defaultvalue_search_LegalentityDO', '101', '101', 'Default inquiry level for search results, to be used if inquiry level is not provided in request'),
	('0936c718-d02d-47fc-86fd-83bc3d1a1b8d', 0, '2017-08-24 12:45:12.000000', NULL, '2017-08-24 12:45:12.000000', 'admin', '1234567890123', 'com_yugandhar_inqlevel_defaultvalue_retrieve_LegalentityDO', '102', '102', 'Default inquiry level for retrieve result, to be used if inquiry level is not provided in request'),
	('0b33b5da-9737-4c4c-8f91-ebe64be258ea', 0, '2017-11-07 14:53:09.000000', NULL, '2017-11-07 14:53:09.000000', 'admin', '00000000', 'com_yugandhar_match_le_Fuzzy_LePerson_RuleClass', 'com.yugandhar.mdm.match.rules.LePersonFuzzyMatchRule', 'com.yugandhar.mdm.match.rules.LePersonFuzzyMatchRule', 'LE Matching rule class name for fuzzy matching of Person type LE'),
	('14b554b4-ec71-4eff-b669-3db1417ebe55', 0, '2017-11-14 17:52:12.000000', NULL, '2017-11-14 17:52:12.000000', 'admin', '00000000', 'com_yugandhar_match_le_Deterministic_LeCorporation_inquiryLevel_default', '108', '108', 'com yugandhar match le Deterministic LeCorporation  inquiryLevel default'),
	('28b9e81e-cf62-43fd-a987-6196933b1e4e', 0, '2017-11-07 14:52:47.000000', NULL, '2017-11-07 14:52:47.000000', 'admin', '00000000', 'com_yugandhar_match_le_Deterministic_LePerson_RuleClass', 'com.yugandhar.mdm.match.rules.LePersonDeterministicMatchRule', 'com.yugandhar.mdm.match.rules.LePersonDeterministicMatchRule', 'LE Matching rule class name for deterministic matching of Person type LE'),
	('2f8ca313-b276-41e2-91d6-ebfed7fb32b6', 0, '2017-10-01 14:00:59.000000', NULL, '2017-10-01 14:00:59.000000', 'admin', '00000000', 'com_yugandhar_phonetic_framework_enabled', 'true', 'true', 'phonetic framework is enabled by default, set it to false to disable'),
	('31adee64-e858-436f-9ebd-dfce384acda3', 0, '2017-10-01 14:17:16.000000', NULL, '2017-10-01 14:17:16.000000', 'admin', '00000000', 'com_yugandhar_phonetic_algorithm_class', 'org.apache.commons.codec.language.Nysiis', 'org.apache.commons.codec.language.Nysiis', 'phonetic algorithm class, default uses the apache nysiis implementation from commons-codec distribution'),
	('327ad737-e7d7-43cc-b48e-fff3a2555572', 0, '2017-11-07 14:52:57.000000', NULL, '2017-11-07 14:52:57.000000', 'admin', '00000000', 'com_yugandhar_match_le_Deterministic_LePerson_RuleClassMethod', 'process', 'process', 'LE Matching rule class method-name for deterministic matching of Person type LE'),
	('4d55bf83-6e9c-4e9e-b160-2978542f3921', 0, '2017-11-07 14:53:45.000000', NULL, '2017-11-07 14:53:45.000000', 'admin', '00000000', 'com_yugandhar_match_le_Fuzzy_LeCorporation_RuleClassMethod', 'process', 'process', 'LE Matching rule class method-name for fuzzy matching of Corporation type LE'),
	('5e565ee6-b176-4813-ab2f-4822223e2bed', 0, '2017-08-29 14:42:31.000000', NULL, '2017-08-29 14:42:31.000000', 'admin', '00000000', 'com_yugandhar_authorization_framework_enabled', 'false', 'true', 'Enables or disable the authorization framework, must be enabled in production environment. In test or developement environment this can be disabled. Valid values are (true, false)'),
	('5e565ee6-b176-4813-ab2f-4830283e2bed', 0, '2017-08-29 14:42:31.000000', NULL, '2017-08-29 14:42:31.000000', 'admin', '00000000', 'com_yugandhar_inqlevel_defaultvalue_retrieve_AccountDO', '101', '101', 'Default inquiry level for retrieve result, to be used if inquiry level is not provided in request'),
	('6021932d-12b4-4a8b-a56a-8024a73d8442', 0, '2017-11-14 17:52:05.000000', NULL, '2017-11-14 17:52:05.000000', 'admin', '00000000', 'com_yugandhar_match_le_Fuzzy_LePerson_inquiryLevel_default', '108', '108', 'com yugandhar match le Fuzzy LePerson  inquiryLevel default'),
	('65285157-e0bc-4ebc-885c-08ed4a1c3b8e', 0, '2017-11-07 14:52:21.000000', NULL, '2017-11-07 14:52:21.000000', 'admin', '00000000', 'com_yugandhar_match_le_engine_type', 'deterministic', 'deterministic', 'match engine type to be used while performing matching of LEs (valid values are fuzzy or deterministic)'),
	('780352cf-6da7-4e29-97c8-65e1cd7cc91e', 0, '2017-09-13 15:48:18.000000', NULL, '2017-09-13 15:48:18.000000', 'admin', '00000000', 'com_yugandhar_pagination_default_pagesize_search', '25', '25', 'default page size for the data table search results'),
	('781f93ec-f322-42ad-a7c1-5d995e5f1dd3', 0, '2017-11-07 14:53:26.000000', NULL, '2017-11-07 14:53:26.000000', 'admin', '00000000', 'com_yugandhar_match_le_Deterministic_LeCorporation_RuleClass', 'com.yugandhar.mdm.match.rules.LeCorporationDeterministicMatchRule', 'com.yugandhar.mdm.match.rules.LeCorporationDeterministicMatchRule', 'LE Matching rule class name for deterministic matching of Corporation type LE'),
	('7ffb427d-51bd-4dda-ac2c-25ce62f52c5b', 0, '2017-11-07 14:53:32.000000', NULL, '2017-11-07 14:53:32.000000', 'admin', '00000000', 'com_yugandhar_match_le_Deterministic_LeCorporation_RuleClassMethod', 'process', 'process', 'LE Matching rule class method-name for deterministic matching of Corporation type LE'),
	('8935a039-0e71-4964-bf2b-cc814f447288', 0, '2017-11-14 17:51:45.000000', NULL, '2017-11-14 17:51:45.000000', 'admin', '00000000', 'com_yugandhar_match_le_Deterministic_LePerson_inquiryLevel_default', '108', '108', 'com yugandhar match le Deterministic LePerson  inquiryLevel default'),
	('930f891a-2851-4153-ba60-c53f6e8d2432e', 0, '2017-11-07 14:52:03.000000', NULL, '2017-11-07 14:52:03.000000', 'admin', '00000000', 'com_yugandhar_match_le_candidateSearch_processing_mode', 'near-realtime', 'near-realtime', 'define how the candidate search process runs, valid values are realtime,near-realtime or batch'),
	('930f891a-2851-4153-ba60-c53f6e8dd69e', 0, '2017-11-07 14:52:03.000000', NULL, '2017-11-07 14:52:03.000000', 'admin', '00000000', 'com_yugandhar_match_le_framework_enabled', 'false', 'false', 'flag to enable the match engine while LE create and upgrade transaction'),
	('9fabebab-effd-4772-8ff3-97e1bddd09a2', 0, '2017-10-01 14:18:46.000000', NULL, '2017-10-01 14:18:46.000000', 'admin', '00000000', 'com_yugandhar_phonetic_algorithm_class_method', 'encode', 'encode', 'method name to invoke from the algorithm class, by default encode method from commons-codec distribution is invoked '),
	('aa1f5170-574b-4700-827f-5b017eaccc47', 0, '2017-11-07 14:53:20.000000', NULL, '2017-11-07 14:53:20.000000', 'admin', '00000000', 'com_yugandhar_match_le_Fuzzy_LePerson_RuleClassMethod', 'process', 'process', 'LE Matching rule class method-name for fuzzy matching of Person type LE'),
	('aa264f9d-099c-4087-bb01-d65b38a87465', 0, '2017-08-24 12:46:26.000000', NULL, '2017-08-24 12:46:26.000000', 'admin', '1234567890123', 'com_yugandhar_dateFormat', 'yyyy-MM-dd\'T\'HH:mm:ss.SSSZ', 'yyyy-MM-dd\'T\'HH:mm:ss.SSSZ', 'default date format'),
	('b59fbc76-e093-4389-ae9a-c037118f0bd1', 0, '2017-09-08 14:32:43.000000', NULL, '2017-09-08 14:32:43.000000', 'admin', '00000000', 'com_yugandhar_pagination_referencelov_default_pagesize', '50', '50', 'default page size for the reference lov'),
	('c94b89a6-2538-4e26-bc4d-0f6423b74471', 0, '2017-11-14 17:52:18.000000', NULL, '2017-11-14 17:52:18.000000', 'admin', '00000000', 'com_yugandhar_match_le_Fuzzy_LeCorporation_inquiryLevel_default', '108', '108', 'com yugandhar match le Fuzzy LeCorporation inquiryLevel default'),
	('e2602295-4fdb-4a4a-a9c4-3987da2d4483', 0, '2017-09-18 12:17:03.000000', NULL, '2017-09-18 12:17:03.000000', 'admin', '00000000', 'com_yugandhar_inqlevel_defaultvalue_search_AccountDO', '101', '101', 'Default inquiry level for search results, to be used if inquiry level is not provided in request'),
	('f2807a0e-9553-4931-a88b-ac8d07af6f36', 0, '2017-09-08 14:33:32.000000', NULL, '2017-09-08 14:33:32.000000', 'admin', '00000000', 'com_yugandhar_pagination_datatables_default_pagesize', '50', '50', 'default page size for the data table find/retrieve results'),
	('fb7690ed-8643-47f2-9640-86d06f4e8d5e', 0, '2017-11-07 14:53:37.000000', NULL, '2017-11-07 14:53:37.000000', 'admin', '00000000', 'com_yugandhar_match_le_Fuzzy_LeCorporation_RuleClass', 'com.yugandhar.mdm.match.rules.LeCorporationFuzzyMatchRule', 'com.yugandhar.mdm.match.rules.LeCorporationFuzzyMatchRule', 'LE Matching rule class name for fuzzy matching of Corporation type LE');
/*!40000 ALTER TABLE `config_app_properties` ENABLE KEYS */;

-- Dumping structure for table yug_owner.config_errorcode_registry
CREATE TABLE IF NOT EXISTS `config_errorcode_registry` (
  `ID_PK` varchar(50) COLLATE utf8_bin NOT NULL,
  `VERSION` decimal(22,0) NOT NULL,
  `CONFIG_LANGUAGE_CODE_KEY` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `ERROR_CODE` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `ERROR_MESSAGE` varchar(200) COLLATE utf8_bin DEFAULT NULL,
  `DESCRIPTION` varchar(200) COLLATE utf8_bin DEFAULT NULL,
  `CREATED_TS` datetime(6) DEFAULT NULL,
  `DELETED_TS` datetime(6) DEFAULT NULL,
  `UPDATED_TS` datetime(6) NOT NULL,
  `UPDATED_BY_USER` varchar(50) COLLATE utf8_bin NOT NULL,
  `UPDATED_BY_TXN_ID` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_PK`),
  UNIQUE KEY `CONFERRCODEREG_UNIQUEKEY` (`ERROR_CODE`,`CONFIG_LANGUAGE_CODE_KEY`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- Dumping data for table yug_owner.config_errorcode_registry: ~218 rows (approximately)
/*!40000 ALTER TABLE `config_errorcode_registry` DISABLE KEYS */;
INSERT INTO `config_errorcode_registry` (`ID_PK`, `VERSION`, `CONFIG_LANGUAGE_CODE_KEY`, `ERROR_CODE`, `ERROR_MESSAGE`, `DESCRIPTION`, `CREATED_TS`, `DELETED_TS`, `UPDATED_TS`, `UPDATED_BY_USER`, `UPDATED_BY_TXN_ID`) VALUES
	('009f6d41-a6f3-4958-9776-6d05dba75216', 0, '1', '10082', 'searchAuthAccessControlDO.txnserviceName is required', 'searchAuthAccessControlDO.txnserviceName is required', '2018-04-16 18:31:32.000000', NULL, '2018-04-16 18:31:32.000000', 'admin', '00000000'),
	('05bf6751-4c81-40fa-bbdb-56182b86fd34', 0, '1', '10098', 'inactiveLeRegistryDO.legalentityIdpk is needed in the request', 'inactiveLeRegistryDO.legalentityIdpk is needed in the request', '2018-04-16 18:31:31.000000', NULL, '2018-04-16 18:31:31.000000', 'admin', '00000000'),
	('05d33aa8-3415-49c1-9bc7-b7542f11518b', 0, '1', '10072', 'accountDO.sourceAccountId is needed in the request if sourceSystemRefkey is provided', 'accountDO.sourceAccountId is needed in the request if sourceSystemRefkey is provided', '2018-04-16 18:31:32.000000', NULL, '2018-04-16 18:31:32.000000', 'admin', '12312311115999'),
	('07a62c33-9cb9-41a5-8adb-b2453abcdb8f', 0, '1', '10090', 'refMatchScoreDO.matchProposedActionRefkey is needed in the request', 'refMatchScoreDO.matchProposedActionRefkey is needed in the request', '2018-04-16 18:31:31.000000', NULL, '2018-04-16 18:31:31.000000', 'admin', '00000000'),
	('07c365bd-98dd-4974-8d28-5770276c2c12', 0, '1', '11067', 'batchEntityToProcessDO.batchProposedActionRefkey is invalid or Refkey-RefValue pair is invalid', 'batchEntityToProcessDO.batchProposedActionRefkey is invalid or Refkey-RefValue pair is invalid', '2018-04-16 18:31:32.000000', NULL, '2018-04-16 18:31:32.000000', 'admin', '00000000'),
	('0cbd0a2a-aa40-4c7a-a429-8ed1d571181b', 0, '1', '10097', 'refMatchThresholdDO.maxDecayPercentage is needed in the request', 'refMatchThresholdDO.maxDecayPercentage is needed in the request', '2018-04-16 18:31:31.000000', NULL, '2018-04-16 18:31:31.000000', 'admin', '00000000'),
	('0dc7ed3e-272b-49f9-8be6-ef982c3ea602', 0, '1', '10120', 'batchEntityToProcessDO.batchProposedActionRefkey is needed in the request', 'batchEntityToProcessDO.batchProposedActionRefkey is needed in the request', '2018-04-16 18:31:32.000000', NULL, '2018-04-16 18:31:32.000000', 'admin', '00000000'),
	('0df69451-29c0-4c81-b7c5-d001547feea3', 0, '1', '10093', 'refMatchThresholdDO.attrBlockName is needed in the request', 'refMatchThresholdDO.attrBlockName is needed in the request', '2018-04-16 18:31:31.000000', NULL, '2018-04-16 18:31:31.000000', 'admin', '00000000'),
	('1', 0, '1', '1', 'Transaction Failed due to unknown error,  please check statck trace', NULL, '2018-04-16 18:31:33.000000', NULL, '2018-04-16 18:31:33.000000', 'admin', '000000'),
	('10', 0, '1', '1002', 'Transaction Service Name not found in configuration', NULL, '2018-04-16 18:31:33.000000', NULL, '2018-04-16 18:31:33.000000', 'admin', '000000'),
	('100', 0, '1', '11010', 'AccountDO.accountMdmStatusRefkey is invalid or Refkey-RefValue pair is invalid', NULL, '2018-04-16 18:31:30.000000', NULL, '2018-04-16 18:31:30.000000', 'admin', '000000'),
	('101', 0, '1', '11011', 'AccountDO.terminationReasonRefkey is invalid or Refkey-RefValue pair is invalid', NULL, '2018-04-16 18:31:30.000000', NULL, '2018-04-16 18:31:30.000000', 'admin', '000000'),
	('102', 0, '1', '11012', 'AccountPhoneAssocDO.phoneTypeRefkey is invalid or Refkey-RefValue pair is invalid', NULL, '2018-04-16 18:31:30.000000', NULL, '2018-04-16 18:31:30.000000', 'admin', '000000'),
	('103', 0, '1', '11013', 'AccountPhoneAssocDO.phoneSubtypeRefkey is invalid or Refkey-RefValue pair is invalid', NULL, '2018-04-16 18:31:30.000000', NULL, '2018-04-16 18:31:30.000000', 'admin', '000000'),
	('104', 0, '1', '11014', 'AddressDO.stateProvinceRefkey is invalid or Refkey-RefValue pair is invalid', NULL, '2018-04-16 18:31:30.000000', NULL, '2018-04-16 18:31:30.000000', 'admin', '000000'),
	('105', 0, '1', '11015', 'AddressDO.countryRefkey is invalid or Refkey-RefValue pair is invalid', NULL, '2018-04-16 18:31:30.000000', NULL, '2018-04-16 18:31:30.000000', 'admin', '000000'),
	('106', 0, '1', '11016', 'CorporationnamesDO.corporationNameTypeRefkey is invalid or Refkey-RefValue pair is invalid', NULL, '2018-04-16 18:31:30.000000', NULL, '2018-04-16 18:31:30.000000', 'admin', '000000'),
	('107', 0, '1', '11017', 'CorporationnamesDO.sourceSystemRefkey is invalid or Refkey-RefValue pair is invalid', NULL, '2018-04-16 18:31:30.000000', NULL, '2018-04-16 18:31:30.000000', 'admin', '000000'),
	('108', 0, '1', '11018', 'EntityGroupAssocDO.entityObjectTypeRefkey is invalid or Refkey-RefValue pair is invalid', NULL, '2018-04-16 18:31:30.000000', NULL, '2018-04-16 18:31:30.000000', 'admin', '000000'),
	('109', 0, '1', '11019', 'EntityGroupAssocDO.assocTypeRefkey is invalid or Refkey-RefValue pair is invalid', NULL, '2018-04-16 18:31:30.000000', NULL, '2018-04-16 18:31:30.000000', 'admin', '000000'),
	('11', 0, '1', '1003', 'Transaction Service Name is present but not configured properly', NULL, '2018-04-16 18:31:33.000000', NULL, '2018-04-16 18:31:33.000000', 'admin', '000000'),
	('110', 0, '1', '11020', 'EntityGroupDO.groupTypeRefkey is invalid or Refkey-RefValue pair is invalid', NULL, '2018-04-16 18:31:30.000000', NULL, '2018-04-16 18:31:30.000000', 'admin', '000000'),
	('111', 0, '1', '11021', 'EntityGroupDO.groupSubtypeRefkey is invalid or Refkey-RefValue pair is invalid', NULL, '2018-04-16 18:31:30.000000', NULL, '2018-04-16 18:31:30.000000', 'admin', '000000'),
	('112', 0, '1', '11022', 'LeAccountAssocDO.leRoletypeRefkey is invalid or Refkey-RefValue pair is invalid', NULL, '2018-04-16 18:31:30.000000', NULL, '2018-04-16 18:31:30.000000', 'admin', '000000'),
	('113', 0, '1', '11023', 'LeAccountAssocDO.deactivationReasonRefkey is invalid or Refkey-RefValue pair is invalid', NULL, '2018-04-16 18:31:30.000000', NULL, '2018-04-16 18:31:30.000000', 'admin', '000000'),
	('114', 0, '1', '11024', 'LeAccountAssocDO.agreementTypeRefkey is invalid or Refkey-RefValue pair is invalid', NULL, '2018-04-16 18:31:30.000000', NULL, '2018-04-16 18:31:30.000000', 'admin', '000000'),
	('115', 0, '1', '11025', 'LeAddressAssocDO.addressTypeRefkey is invalid or Refkey-RefValue pair is invalid', NULL, '2018-04-16 18:31:30.000000', NULL, '2018-04-16 18:31:30.000000', 'admin', '000000'),
	('116', 0, '1', '11026', 'LeAddressAssocDO.addressSubtypeRefkey is invalid or Refkey-RefValue pair is invalid', NULL, '2018-04-16 18:31:30.000000', NULL, '2018-04-16 18:31:30.000000', 'admin', '000000'),
	('117', 0, '1', '11027', 'LeCorporationDO.classificationCodeRefkey is invalid or Refkey-RefValue pair is invalid', NULL, '2018-04-16 18:31:30.000000', NULL, '2018-04-16 18:31:30.000000', 'admin', '000000'),
	('118', 0, '1', '11028', 'LeCorporationDO.industryCodeRefkey is invalid or Refkey-RefValue pair is invalid', NULL, '2018-04-16 18:31:30.000000', NULL, '2018-04-16 18:31:30.000000', 'admin', '000000'),
	('119', 0, '1', '11029', 'LeCorporationDO.countryRegistrationRefkey is invalid or Refkey-RefValue pair is invalid', NULL, '2018-04-16 18:31:30.000000', NULL, '2018-04-16 18:31:30.000000', 'admin', '000000'),
	('11ce6c0c-5ff4-4839-9f56-8cd307b6abc5', 0, '1', '1019', 'configInquiryLevelsDO.childDobj should not be null', 'configInquiryLevelsDO.childDobj should not be null', '2018-04-16 18:31:32.000000', NULL, '2018-04-16 18:31:32.000000', 'admin', '12312311115999'),
	('12', 0, '1', '1004', 'Idpk should not be null', NULL, '2018-04-16 18:31:33.000000', NULL, '2018-04-16 18:31:33.000000', 'admin', '000000'),
	('120', 0, '1', '11030', 'LegalentityDO.entityObjectTypeRefkey is invalid or Refkey-RefValue pair is invalid', NULL, '2018-04-16 18:31:30.000000', NULL, '2018-04-16 18:31:30.000000', 'admin', '000000'),
	('121', 0, '1', '11031', 'LegalentityDO.classificationCodeRefkey is invalid or Refkey-RefValue pair is invalid', NULL, '2018-04-16 18:31:30.000000', NULL, '2018-04-16 18:31:30.000000', 'admin', '000000'),
	('122', 0, '1', '11032', 'LegalentityDO.importanceTypeRefkey is invalid or Refkey-RefValue pair is invalid', NULL, '2018-04-16 18:31:30.000000', NULL, '2018-04-16 18:31:30.000000', 'admin', '000000'),
	('123', 0, '1', '11033', 'LegalentityDO.leRatingRefkey is invalid or Refkey-RefValue pair is invalid', NULL, '2018-04-16 18:31:30.000000', NULL, '2018-04-16 18:31:30.000000', 'admin', '000000'),
	('124', 0, '1', '11034', 'LegalentityDO.statusTypeRefkey is invalid or Refkey-RefValue pair is invalid', NULL, '2018-04-16 18:31:30.000000', NULL, '2018-04-16 18:31:30.000000', 'admin', '000000'),
	('125', 0, '1', '11035', 'LegalentityDO.sourceSystemRefkey is invalid or Refkey-RefValue pair is invalid', NULL, '2018-04-16 18:31:30.000000', NULL, '2018-04-16 18:31:30.000000', 'admin', '000000'),
	('126', 0, '1', '11036', 'LeIdentifierKycRegistryDO.identificationTypeRefkey is invalid or Refkey-RefValue pair is invalid', NULL, '2018-04-16 18:31:30.000000', NULL, '2018-04-16 18:31:30.000000', 'admin', '000000'),
	('127', 0, '1', '11037', 'LeIdentifierKycRegistryDO.sourceSystemRefkey is invalid or Refkey-RefValue pair is invalid', NULL, '2018-04-16 18:31:30.000000', NULL, '2018-04-16 18:31:30.000000', 'admin', '000000'),
	('128', 0, '1', '11038', 'LePersonDO.personTypeRefkey is invalid or Refkey-RefValue pair is invalid', NULL, '2018-04-16 18:31:31.000000', NULL, '2018-04-16 18:31:31.000000', 'admin', '000000'),
	('129', 0, '1', '11039', 'LePersonDO.genderRefkey is invalid or Refkey-RefValue pair is invalid', NULL, '2018-04-16 18:31:31.000000', NULL, '2018-04-16 18:31:31.000000', 'admin', '000000'),
	('13', 0, '1', '1005', 'Error code should not be null', NULL, '2018-04-16 18:31:33.000000', NULL, '2018-04-16 18:31:33.000000', 'admin', '000000'),
	('130', 0, '1', '11040', 'LePersonDO.countryOfBirthRefkey is invalid or Refkey-RefValue pair is invalid', NULL, '2018-04-16 18:31:31.000000', NULL, '2018-04-16 18:31:31.000000', 'admin', '000000'),
	('131', 0, '1', '11041', 'LePersonDO.countryCitizenshipRefkey is invalid or Refkey-RefValue pair is invalid', NULL, '2018-04-16 18:31:31.000000', NULL, '2018-04-16 18:31:31.000000', 'admin', '000000'),
	('132', 0, '1', '11042', 'LePersonDO.countryOfDomicileRefkey is invalid or Refkey-RefValue pair is invalid', NULL, '2018-04-16 18:31:31.000000', NULL, '2018-04-16 18:31:31.000000', 'admin', '000000'),
	('133', 0, '1', '11043', 'LePersonDO.highestEduQualRefkey is invalid or Refkey-RefValue pair is invalid', NULL, '2018-04-16 18:31:31.000000', NULL, '2018-04-16 18:31:31.000000', 'admin', '000000'),
	('134', 0, '1', '11044', 'LePersonDO.prefereedLanguageRefkey is invalid or Refkey-RefValue pair is invalid', NULL, '2018-04-16 18:31:31.000000', NULL, '2018-04-16 18:31:31.000000', 'admin', '000000'),
	('135', 0, '1', '11045', 'LePhoneAssocDO.phoneTypeRefkey is invalid or Refkey-RefValue pair is invalid', NULL, '2018-04-16 18:31:31.000000', NULL, '2018-04-16 18:31:31.000000', 'admin', '000000'),
	('136', 0, '1', '11046', 'LePhoneAssocDO.phoneSubtypeRefkey is invalid or Refkey-RefValue pair is invalid', NULL, '2018-04-16 18:31:31.000000', NULL, '2018-04-16 18:31:31.000000', 'admin', '000000'),
	('137', 0, '1', '11047', 'LePreferencesDO.preferenceTypeRefkey is invalid or Refkey-RefValue pair is invalid', NULL, '2018-04-16 18:31:31.000000', NULL, '2018-04-16 18:31:31.000000', 'admin', '000000'),
	('138', 0, '1', '11048', 'LePropertyAssocDO.propertyLeReltypeRefkey is invalid or Refkey-RefValue pair is invalid', NULL, '2018-04-16 18:31:31.000000', NULL, '2018-04-16 18:31:31.000000', 'admin', '000000'),
	('139', 0, '1', '11049', 'LeSystemKeysRegistryDO.sourceSystemRefkey is invalid or Refkey-RefValue pair is invalid', NULL, '2018-04-16 18:31:31.000000', NULL, '2018-04-16 18:31:31.000000', 'admin', '000000'),
	('14', 0, '1', '1006', 'Reference list key should not be null ', NULL, '2018-04-16 18:31:33.000000', NULL, '2018-04-16 18:31:33.000000', 'admin', '000000'),
	('140', 0, '1', '11050', 'LeSystemKeysRegistryDO.statusInSourceRefkey is invalid or Refkey-RefValue pair is invalid', NULL, '2018-04-16 18:31:31.000000', NULL, '2018-04-16 18:31:31.000000', 'admin', '000000'),
	('141', 0, '1', '11051', 'LeToLeRelationshipDO.leRelationshipTypeRefkey is invalid or Refkey-RefValue pair is invalid', NULL, '2018-04-16 18:31:31.000000', NULL, '2018-04-16 18:31:31.000000', 'admin', '000000'),
	('142', 0, '1', '11052', 'LeToLeRelationshipDO.relationshipStatusRefkey is invalid or Refkey-RefValue pair is invalid', NULL, '2018-04-16 18:31:31.000000', NULL, '2018-04-16 18:31:31.000000', 'admin', '000000'),
	('143', 0, '1', '11053', 'LeVehicleAssocDO.leRoletypeRefkey is invalid or Refkey-RefValue pair is invalid', NULL, '2018-04-16 18:31:31.000000', NULL, '2018-04-16 18:31:31.000000', 'admin', '000000'),
	('144', 0, '1', '11054', 'LeVehicleAssocDO.deactivationReasonRefkey is invalid or Refkey-RefValue pair is invalid', NULL, '2018-04-16 18:31:31.000000', NULL, '2018-04-16 18:31:31.000000', 'admin', '000000'),
	('145', 0, '1', '11055', 'LeVehicleAssocDO.agreementTypeRefkey is invalid or Refkey-RefValue pair is invalid', NULL, '2018-04-16 18:31:31.000000', NULL, '2018-04-16 18:31:31.000000', 'admin', '000000'),
	('146', 0, '1', '11056', 'MiscellaneousInfoDO.entityObjectTypeRefkey is invalid or Refkey-RefValue pair is invalid', NULL, '2018-04-16 18:31:31.000000', NULL, '2018-04-16 18:31:31.000000', 'admin', '000000'),
	('147', 0, '1', '11057', 'PersonnamesDO.personnameTypeRefkey is invalid or Refkey-RefValue pair is invalid', NULL, '2018-04-16 18:31:31.000000', NULL, '2018-04-16 18:31:31.000000', 'admin', '000000'),
	('148', 0, '1', '11058', 'PersonnamesDO.prefixNameRefkey is invalid or Refkey-RefValue pair is invalid', NULL, '2018-04-16 18:31:31.000000', NULL, '2018-04-16 18:31:31.000000', 'admin', '000000'),
	('149', 0, '1', '11059', 'PersonnamesDO.suffixNameRefkey is invalid or Refkey-RefValue pair is invalid', NULL, '2018-04-16 18:31:31.000000', NULL, '2018-04-16 18:31:31.000000', 'admin', '000000'),
	('15', 0, '1', '1007', 'configLanguageCodeKey Should not be Null', NULL, '2018-04-16 18:31:33.000000', NULL, '2018-04-16 18:31:33.000000', 'admin', '000000'),
	('150', 0, '1', '11060', 'PersonnamesDO.sourceSystemRefkey is invalid or Refkey-RefValue pair is invalid', NULL, '2018-04-16 18:31:31.000000', NULL, '2018-04-16 18:31:31.000000', 'admin', '000000'),
	('151', 0, '1', '11061', 'VehicleDO.countryOfRegistrationRefkey is invalid or Refkey-RefValue pair is invalid', NULL, '2018-04-16 18:31:31.000000', NULL, '2018-04-16 18:31:31.000000', 'admin', '000000'),
	('16', 0, '1', '1008', 'Filter Value not Valid', NULL, '2018-04-16 18:31:33.000000', NULL, '2018-04-16 18:31:33.000000', 'admin', '000000'),
	('17', 0, '1', '1009', 'PersonnamesDO.legalentityIdpk should not be null', NULL, '2018-04-16 18:31:33.000000', NULL, '2018-04-16 18:31:33.000000', 'admin', '000000'),
	('18', 0, '1', '1010', 'version attribute should not be null', NULL, '2018-04-16 18:31:33.000000', NULL, '2018-04-16 18:31:33.000000', 'admin', '000000'),
	('19', 0, '1', '10001', 'DisplayName is required', NULL, '2018-04-16 18:31:33.000000', NULL, '2018-04-16 18:31:33.000000', 'admin', '000000'),
	('1de0ee4b-7d32-4259-98b0-0c7f1faec1a6', 0, '1', '11064', 'matchCandidateLeRegistryDO.matchActionstatusRefkey is invalid or Refkey-RefValue pair is invalid', 'matchCandidateLeRegistryDO.matchActionstatusRefkey is invalid or Refkey-RefValue pair is invalid', '2018-04-16 18:31:31.000000', NULL, '2018-04-16 18:31:31.000000', 'admin', '00000000'),
	('1e581fa8-591d-4754-a521-c5ec6461642d', 0, '1', '10085', 'lePersonDO.genderRefkey  is needed in the request', 'lePersonDO.genderRefkey  is needed in the request', '2018-04-16 18:31:32.000000', NULL, '2018-04-16 18:31:32.000000', 'admin', '00000000'),
	('1e822aab-4943-4fc5-be30-469089aa4941', 0, '1', '10100', 'inactiveLeRegistryDO.inactivationReasonRefkey is needed in the request', 'inactiveLeRegistryDO.inactivationReasonRefkey is needed in the request', '2018-04-16 18:31:31.000000', NULL, '2018-04-16 18:31:31.000000', 'admin', '00000000'),
	('2', 0, '1', '2', 'OptimisticLockException, Row was updated or deleted by another transaction', NULL, '2018-04-16 18:31:32.000000', NULL, '2018-04-16 18:31:32.000000', 'admin', '000000'),
	('20', 0, '1', '10002', 'EntityObjectTypeRefkey is required', NULL, '2018-04-16 18:31:33.000000', NULL, '2018-04-16 18:31:33.000000', 'admin', '000000'),
	('20c56ef7-4cd1-4de4-ab0e-045b648dd8f1', 0, '1', '10078', 'authUserRoleAssocDO.authRolesRegistryIdpk should not be null', 'authUserRoleAssocDO.authRolesRegistryIdpk should not be null', '2018-04-16 18:31:32.000000', NULL, '2018-04-16 18:31:32.000000', 'admin', '12312311115999'),
	('21', 0, '1', '10003', 'LegalEntityIdPk is required', NULL, '2018-04-16 18:31:33.000000', NULL, '2018-04-16 18:31:33.000000', 'admin', '000000'),
	('22', 0, '1', '10004', 'PersonNameType is required', NULL, '2018-04-16 18:31:33.000000', NULL, '2018-04-16 18:31:33.000000', 'admin', '000000'),
	('23', 0, '1', '10005', 'LegalEntityIdPk is required', NULL, '2018-04-16 18:31:33.000000', NULL, '2018-04-16 18:31:33.000000', 'admin', '000000'),
	('24', 0, '1', '10006', 'corporationNameTypeRefkey is required', NULL, '2018-04-16 18:31:33.000000', NULL, '2018-04-16 18:31:33.000000', 'admin', '000000'),
	('25', 0, '1', '10007', 'corporationName is required', NULL, '2018-04-16 18:31:33.000000', NULL, '2018-04-16 18:31:33.000000', 'admin', '000000'),
	('26', 0, '1', '10008', 'addressTypeRefkey is required', NULL, '2018-04-16 18:31:33.000000', NULL, '2018-04-16 18:31:33.000000', 'admin', '000000'),
	('27', 0, '1', '10009', 'addressLineOne is required', NULL, '2018-04-16 18:31:33.000000', NULL, '2018-04-16 18:31:33.000000', 'admin', '000000'),
	('28', 0, '1', '10010', 'phoneTypeRefkey is required', NULL, '2018-04-16 18:31:33.000000', NULL, '2018-04-16 18:31:33.000000', 'admin', '000000'),
	('29', 0, '1', '10011', 'phoneNumber is required', NULL, '2018-04-16 18:31:33.000000', NULL, '2018-04-16 18:31:33.000000', 'admin', '000000'),
	('2af06802-1b70-488b-a947-b23544c11b0f', 0, '1', '11068', 'batchEntityToProcessDO.batchActionStatusRefkey is invalid or Refkey-RefValue pair is invalid', 'batchEntityToProcessDO.batchActionStatusRefkey is invalid or Refkey-RefValue pair is invalid', '2018-04-16 18:31:32.000000', NULL, '2018-04-16 18:31:32.000000', 'admin', '00000000'),
	('2e57ecf3-c704-47d3-ba55-98281f6d72be', 0, '1', '10084', 'userName Provided in the request is not part of role given in roleName', 'userName Provided in the request is not part of role given in roleName', '2018-04-16 18:31:32.000000', NULL, '2018-04-16 18:31:32.000000', 'admin', '00000000'),
	('3', 0, '1', '101', 'Record already present for given Idpk', NULL, '2018-04-16 18:31:32.000000', NULL, '2018-04-16 18:31:32.000000', 'admin', '000000'),
	('30', 0, '1', '10012', 'Person Name One is required', NULL, '2018-04-16 18:31:33.000000', NULL, '2018-04-16 18:31:33.000000', 'admin', '000000'),
	('302f6556-acba-4363-b471-14866a4802e2', 0, '1', '11062', 'inactiveLeRegistryDO.inactivationReasonRefkey is invalid or Refkey-RefValue pair is invalid', 'inactiveLeRegistryDO.inactivationReasonRefkey is invalid or Refkey-RefValue pair is invalid', '2018-04-16 18:31:31.000000', NULL, '2018-04-16 18:31:31.000000', 'admin', '00000000'),
	('31', 0, '1', '10013', 'roletypeRefkey is required', NULL, '2018-04-16 18:31:33.000000', NULL, '2018-04-16 18:31:33.000000', 'admin', '000000'),
	('32', 0, '1', '10014', 'accountIdpk is required', NULL, '2018-04-16 18:31:33.000000', NULL, '2018-04-16 18:31:33.000000', 'admin', '000000'),
	('33', 0, '1', '10015', 'accountName is required', NULL, '2018-04-16 18:31:33.000000', NULL, '2018-04-16 18:31:33.000000', 'admin', '000000'),
	('34', 0, '1', '10017', 'addressIdpk is required', NULL, '2018-04-16 18:31:33.000000', NULL, '2018-04-16 18:31:33.000000', 'admin', '000000'),
	('343330a0-7511-47f3-83d6-451a99177efa', 0, '1', '10094', 'refMatchThresholdDO.matchThreshold is needed in the request', 'refMatchThresholdDO.matchThreshold is needed in the request', '2018-04-16 18:31:31.000000', NULL, '2018-04-16 18:31:31.000000', 'admin', '00000000'),
	('35', 0, '1', '10019', 'phoneTypeRefkeyis required', NULL, '2018-04-16 18:31:33.000000', NULL, '2018-04-16 18:31:33.000000', 'admin', '000000'),
	('36', 0, '1', '10020', 'phoneNumber is required', NULL, '2018-04-16 18:31:33.000000', NULL, '2018-04-16 18:31:33.000000', 'admin', '000000'),
	('37', 0, '1', '10021', 'Vehicle Identification Number (VIN)  is required', NULL, '2018-04-16 18:31:33.000000', NULL, '2018-04-16 18:31:33.000000', 'admin', '000000'),
	('374bd4d2-c948-4738-a22e-bed7c0513dcd', 0, '1', '10069', 'leToLeRelationshipDO.fromLegalentityIdpk should not be null', NULL, '2018-04-16 18:31:30.000000', NULL, '2018-04-16 18:31:30.000000', 'admin', '12312311115999'),
	('38', 0, '1', '10022', 'Legal EntityIdpk is required', NULL, '2018-04-16 18:31:33.000000', NULL, '2018-04-16 18:31:33.000000', 'admin', '000000'),
	('38802be6-40dd-4bec-9f93-87261bcb718f', 0, '1', '10110', 'matchCandidateLeRegistryDO.candidateLegalentityidpk or matchPattern or matchProposedActionRefkey or matchActionstatusRefkey is needed in the request', 'matchCandidateLeRegistryDO.candidateLegalentityidpk or matchPattern or matchProposedActionRefkey or matchActionstatusRefkey is needed in the request', '2018-04-16 18:31:32.000000', NULL, '2018-04-16 18:31:32.000000', 'admin', '00000000'),
	('39', 0, '1', '10023', 'Legal entity role type leRoletypeRefkey is required', NULL, '2018-04-16 18:31:33.000000', NULL, '2018-04-16 18:31:33.000000', 'admin', '000000'),
	('3ae855fe-8577-4002-8dac-b64896945457', 0, '1', '10068', 'EntityGroupAssocDO.EntityGroupIdpk should not be null', NULL, '2018-04-16 18:31:34.000000', NULL, '2018-04-16 18:31:34.000000', 'admin', '12312311115999'),
	('4', 0, '1', '102', 'Record not found for given Idpk', NULL, '2018-04-16 18:31:33.000000', NULL, '2018-04-16 18:31:33.000000', 'admin', '000000'),
	('40', 0, '1', '10024', 'propertyName is required', NULL, '2018-04-16 18:31:33.000000', NULL, '2018-04-16 18:31:33.000000', 'admin', '000000'),
	('41', 0, '1', '10025', 'PropertyIdPk is required', NULL, '2018-04-16 18:31:33.000000', NULL, '2018-04-16 18:31:33.000000', 'admin', '000000'),
	('42', 0, '1', '10027', 'propertyLeReltypeRefkey is required', NULL, '2018-04-16 18:31:33.000000', NULL, '2018-04-16 18:31:33.000000', 'admin', '000000'),
	('43', 0, '1', '10028', 'FromlegalEntity is required', NULL, '2018-04-16 18:31:33.000000', NULL, '2018-04-16 18:31:33.000000', 'admin', '000000'),
	('44', 0, '1', '10029', 'ToLegalEntity is required', NULL, '2018-04-16 18:31:33.000000', NULL, '2018-04-16 18:31:33.000000', 'admin', '000000'),
	('45', 0, '1', '10030', 'RelationshipType  is required', NULL, '2018-04-16 18:31:33.000000', NULL, '2018-04-16 18:31:33.000000', 'admin', '000000'),
	('46', 0, '1', '10031', 'IdentificationTypeRefkey is required', NULL, '2018-04-16 18:31:34.000000', NULL, '2018-04-16 18:31:34.000000', 'admin', '000000'),
	('47', 0, '1', '10032', 'IdentificationNumber  is required', NULL, '2018-04-16 18:31:34.000000', NULL, '2018-04-16 18:31:34.000000', 'admin', '000000'),
	('48', 0, '1', '10034', 'sourceSystemRefkey  is required', NULL, '2018-04-16 18:31:34.000000', NULL, '2018-04-16 18:31:34.000000', 'admin', '000000'),
	('49', 0, '1', '10035', 'referenceId  is required', NULL, '2018-04-16 18:31:34.000000', NULL, '2018-04-16 18:31:34.000000', 'admin', '000000'),
	('4964f771-ffb2-4c1c-82ee-c35b939221c6', 0, '1', '1018', 'configInquiryLevelsDO.applicableDobj should not be null', 'configInquiryLevelsDO.applicableDobj should not be null', '2018-04-16 18:31:32.000000', NULL, '2018-04-16 18:31:32.000000', 'admin', '12312311115999'),
	('4ada11f2-58a7-46e1-9c10-575bd3b0ff71', 0, '1', '10117', 'performLeMatchRequestDO.legalentityDO.entityObjectTypeRefkey is needed in the request', 'performLeMatchRequestDO.legalentityDO.entityObjectTypeRefkey is needed in the request', '2018-04-16 18:31:32.000000', NULL, '2018-04-16 18:31:32.000000', 'admin', '00000000'),
	('4be798ac-a9bb-46df-8052-a9b91ec215b8', 0, '1', '1011', 'Reference list value attribute should not be null ', 'Reference list value attribute should not be null ', '2018-04-16 18:31:31.000000', NULL, '2018-04-16 18:31:31.000000', 'admin', '12312311115999'),
	('4e4cb5be-8aba-4d9b-bc4d-2fb587237d32', 0, '1', '10104', 'matchCandidateLeRegistryDO.matchProposedActionRefkey is needed in the request', 'matchCandidateLeRegistryDO.matchProposedActionRefkey is needed in the request', '2018-04-16 18:31:31.000000', NULL, '2018-04-16 18:31:31.000000', 'admin', '00000000'),
	('5', 0, '1', '103', 'No records found for given reference list', NULL, '2018-04-16 18:31:33.000000', NULL, '2018-04-16 18:31:33.000000', 'admin', '000000'),
	('50', 0, '1', '10038', 'preferenceTypeRefkey is required', NULL, '2018-04-16 18:31:34.000000', NULL, '2018-04-16 18:31:34.000000', 'admin', '000000'),
	('51', 0, '1', '10039', 'entityObjectTypeRefkey is required', NULL, '2018-04-16 18:31:34.000000', NULL, '2018-04-16 18:31:34.000000', 'admin', '000000'),
	('52', 0, '1', '10040', 'entityIdpk is required', NULL, '2018-04-16 18:31:34.000000', NULL, '2018-04-16 18:31:34.000000', 'admin', '000000'),
	('53', 0, '1', '10041', 'Name1 is required', NULL, '2018-04-16 18:31:34.000000', NULL, '2018-04-16 18:31:34.000000', 'admin', '000000'),
	('54', 0, '1', '10042', 'Value1  is required', NULL, '2018-04-16 18:31:34.000000', NULL, '2018-04-16 18:31:34.000000', 'admin', '000000'),
	('55', 0, '1', '10043', 'groupTypeRefkey is required', NULL, '2018-04-16 18:31:34.000000', NULL, '2018-04-16 18:31:34.000000', 'admin', '000000'),
	('56', 0, '1', '10044', 'groupName is required', NULL, '2018-04-16 18:31:34.000000', NULL, '2018-04-16 18:31:34.000000', 'admin', '000000'),
	('568ec50a-d7f6-42a0-b81a-c9bf6d24b527', 0, '1', '10071', 'accountDO.sourceSystemRefkey is needed in the request if sourceAccountId is provided', 'accountDO.sourceSystemRefkey is needed in the request if sourceAccountId is provided', '2018-04-16 18:31:32.000000', NULL, '2018-04-16 18:31:32.000000', 'admin', '12312311115999'),
	('568ff82e-8849-4a27-b878-0488b9cc39c2', 0, '1', '1014', 'ConfigTxnRegistryDO.txnserviceName should not be null', 'ConfigTxnRegistryDO.txnserviceName should not be null', '2018-04-16 18:31:32.000000', NULL, '2018-04-16 18:31:32.000000', 'admin', '12312311115999'),
	('56a64410-d6ad-4220-b69e-48f05303681b', 0, '1', '10105', 'matchCandidateLeRegistryDO.matchActionstatusRefkey is needed in the request', 'matchCandidateLeRegistryDO.matchActionstatusRefkey is needed in the request', '2018-04-16 18:31:31.000000', NULL, '2018-04-16 18:31:31.000000', 'admin', '00000000'),
	('57', 0, '1', '10045', 'entityObjectTypeRefkey is required', NULL, '2018-04-16 18:31:34.000000', NULL, '2018-04-16 18:31:34.000000', 'admin', '000000'),
	('58', 0, '1', '10046', 'entityGroupIdpk is required', NULL, '2018-04-16 18:31:34.000000', NULL, '2018-04-16 18:31:34.000000', 'admin', '000000'),
	('59', 0, '1', '10047', 'assocTypeRefkey is required', NULL, '2018-04-16 18:31:34.000000', NULL, '2018-04-16 18:31:34.000000', 'admin', '000000'),
	('5bb8ff35-b8cf-47f3-9e85-1e19282ea851', 0, '1', '10066', 'EntityGroupAssocDO.EntityIdpk should not be null', NULL, '2018-04-16 18:31:34.000000', NULL, '2018-04-16 18:31:34.000000', 'admin', '12312311115999'),
	('5cd2498a-9504-45d7-9c56-9268b5b5feb2', 0, '1', '10079', 'authUserRoleAssocDO.authUserRegistryIdpk should not be null', 'authUserRoleAssocDO.authUserRegistryIdpk should not be null', '2018-04-16 18:31:32.000000', NULL, '2018-04-16 18:31:32.000000', 'admin', '12312311115999'),
	('5e157d09-977b-43b3-9405-29ef496efc78', 0, '1', '10119', 'batchEntityToProcessDO.entityIdpk is needed in the request', 'batchEntityToProcessDO.entityIdpk is needed in the request', '2018-04-16 18:31:32.000000', NULL, '2018-04-16 18:31:32.000000', 'admin', '00000000'),
	('5e855d8a-c5b5-440b-aed8-39d202e3a676', 0, '1', '1021', 'Invalid page number in the request. Note- Pages Index starts with 0', 'Invalid page number in the request. Note- Pages Index starts with 0', '2018-04-16 18:31:32.000000', NULL, '2018-04-16 18:31:32.000000', 'admin', '12312311115999'),
	('6', 0, '1', '104', 'requesterLanguage is Needed  in the request txnHeader', NULL, '2018-04-16 18:31:33.000000', NULL, '2018-04-16 18:31:33.000000', 'admin', '000000'),
	('60', 0, '1', '10050', 'addressSubtypeRefkey is required', NULL, '2018-04-16 18:31:34.000000', NULL, '2018-04-16 18:31:34.000000', 'admin', '000000'),
	('61', 0, '1', '10051', 'countryRefkey is required', NULL, '2018-04-16 18:31:34.000000', NULL, '2018-04-16 18:31:34.000000', 'admin', '000000'),
	('6179997b-9ec8-4881-aa3f-68fc0a00ffe5', 0, '1', '10060', 'accountPhoneAssocDO.AccountIdPk is required in the request', NULL, '2018-04-16 18:31:34.000000', NULL, '2018-04-16 18:31:34.000000', 'admin', '12312311115999'),
	('62', 0, '1', '10052', 'city is required', NULL, '2018-04-16 18:31:34.000000', NULL, '2018-04-16 18:31:34.000000', 'admin', '000000'),
	('627d3aaa-e956-46cc-8d54-91b2a30a2569', 0, '1', '10108', 'matchMergedLeAssocDO.mergeReasonRefkey is needed in the request', 'matchMergedLeAssocDO.mergeReasonRefkey is needed in the request', '2018-04-16 18:31:32.000000', NULL, '2018-04-16 18:31:32.000000', 'admin', '00000000'),
	('64c00c6a-ccca-4144-a692-18c1ab637160', 0, '1', '10073', 'authRolesRegistryDO.roleName should not be null', 'authRolesRegistryDO.roleName should not be null', '2018-04-16 18:31:32.000000', NULL, '2018-04-16 18:31:32.000000', 'admin', '12312311115999'),
	('694483f3-05f2-45a1-ae1e-b6e7229cc41e', 0, '1', '107', 'txnHeader Object is needed in the request', NULL, '2018-04-16 18:31:30.000000', NULL, '2018-04-16 18:31:30.000000', 'admin', '12312311115999'),
	('6ba616c5-54bc-446a-a65b-35bb31e6e160', 0, '1', '10077', 'authUserroleAccesscontrolDO.configTxnRegistryIdpk should not be null', 'authUserroleAccesscontrolDO.configTxnRegistryIdpk should not be null', '2018-04-16 18:31:32.000000', NULL, '2018-04-16 18:31:32.000000', 'admin', '12312311115999'),
	('6bd79b22-e526-42cf-a28e-20d6f234ac4c', 0, '1', '10061', 'addressDO.idpk and accountAddressAssocDO.addressIdpk does not match', NULL, '2018-04-16 18:31:34.000000', NULL, '2018-04-16 18:31:34.000000', 'admin', '12312311115999'),
	('6c8dde19-cdf3-4767-9b8d-42d6f904f627', 0, '1', '10113', 'The attributes searchMatchCandidateLERequestDO.identificationNumberList, IdentificationTypeRefkeyList, phoneticCorporationNameList or ( phoneticPersonNameOneList, phoneticPersonNameList) is required', 'The attributes searchMatchCandidateLERequestDO.identificationNumberList, IdentificationTypeRefkeyList, phoneticCorporationNameList or ( phoneticPersonNameOneList, phoneticPersonNameList) is required', '2018-04-16 18:31:32.000000', NULL, '2018-04-16 18:31:32.000000', 'admin', '00000000'),
	('7', 0, '1', '105', 'userName is Needed in the request txnHeader', NULL, '2018-04-16 18:31:33.000000', NULL, '2018-04-16 18:31:33.000000', 'admin', '000000'),
	('705b0cb1-90e2-43b2-95ba-5eb2da0a9800', 0, '1', '10114', 'performLeMatchRequestDO.matchEngineType is needed in the request', 'performLeMatchRequestDO.matchEngineType is needed in the request', '2018-04-16 18:31:32.000000', NULL, '2018-04-16 18:31:32.000000', 'admin', '00000000'),
	('71', 0, '1', '3', 'Constraint Violation Exception, either Unique Key or other constraint violated', NULL, '2018-04-16 18:31:34.000000', NULL, '2018-04-16 18:31:34.000000', 'admin', '000000'),
	('7174883d-b171-42c4-b4c6-4d205aa4f2aa', 0, '1', '11065', 'matchMergedLeAssocDO.mergeReasonRefkey is invalid or Refkey-RefValue pair is invalid', 'matchMergedLeAssocDO.mergeReasonRefkey is invalid or Refkey-RefValue pair is invalid', '2018-04-16 18:31:31.000000', NULL, '2018-04-16 18:31:31.000000', 'admin', '00000000'),
	('72', 0, '1', '4', 'Persistence Exception', NULL, '2018-04-16 18:31:34.000000', NULL, '2018-04-16 18:31:34.000000', 'admin', '000000'),
	('73', 0, '1', '5', 'Composite service failed unexpectedly', NULL, '2018-04-16 18:31:34.000000', NULL, '2018-04-16 18:31:34.000000', 'admin', '000000'),
	('735a9ebc-db91-4c9c-8986-afca03e65905', 0, '1', '10116', 'performLeMatchRequestDO.legalentityDO is needed in the request', 'performLeMatchRequestDO.legalentityDO is needed in the request', '2018-04-16 18:31:32.000000', NULL, '2018-04-16 18:31:32.000000', 'admin', '00000000'),
	('74', 0, '1', '10048', 'Either lePersonDO or leCorporationDO is required in the request', NULL, '2018-04-16 18:31:34.000000', NULL, '2018-04-16 18:31:34.000000', 'admin', '000000'),
	('77', 0, '1', '10049', 'lePersonDO should not be null when entityObjectTypeRefkey is 1 and leCorporationDO should not be null when entityObjectTypeRefkey is 2', NULL, '2018-04-16 18:31:34.000000', NULL, '2018-04-16 18:31:34.000000', 'admin', '000000'),
	('78', 0, '1', '10026', 'EntityObjectTypeRefkey is not valid', NULL, '2018-04-16 18:31:34.000000', NULL, '2018-04-16 18:31:34.000000', 'admin', '000000'),
	('79', 0, '1', '10036', 'AddressDO is required in the request while creating LE to Address association', NULL, '2018-04-16 18:31:34.000000', NULL, '2018-04-16 18:31:34.000000', 'admin', '000000'),
	('7974befe-082b-46c7-a9b5-514db46e9041', 0, '1', '10074', 'authUserRegistryDO.userName should not be null', 'authUserRegistryDO.userName should not be null', '2018-04-16 18:31:32.000000', NULL, '2018-04-16 18:31:32.000000', 'admin', '12312311115999'),
	('8', 0, '1', '106', 'userRole is Needed in the request txnHeader', NULL, '2018-04-16 18:31:33.000000', NULL, '2018-04-16 18:31:33.000000', 'admin', '000000'),
	('80', 0, '1', '10037', 'AddressDO.idpk and leAddressAssocDO.addressIdpk does not match', NULL, '2018-04-16 18:31:34.000000', NULL, '2018-04-16 18:31:34.000000', 'admin', '000000'),
	('81', 0, '1', '10053', 'Cannot Create association without AccountDO or AccountIdPk of existing Account', NULL, '2018-04-16 18:31:34.000000', NULL, '2018-04-16 18:31:34.000000', 'admin', '000000'),
	('82', 0, '1', '10054', 'Cannot Create association without PropertyDO or PropertyIdPk of existing Property', NULL, '2018-04-16 18:31:34.000000', NULL, '2018-04-16 18:31:34.000000', 'admin', '000000'),
	('83', 0, '1', '10055', 'Cannot Create association without VehicleDO or VehicleIdPk of existing Vehicle', NULL, '2018-04-16 18:31:34.000000', NULL, '2018-04-16 18:31:34.000000', 'admin', '000000'),
	('84', 0, '1', '10056', 'AccountDO.idpk and leAccountAssocDO.AccountIdpk does not match', NULL, '2018-04-16 18:31:34.000000', NULL, '2018-04-16 18:31:34.000000', 'admin', '000000'),
	('85', 0, '1', '10057', 'PropertyDO.idpk and lePropertyAssocDO.PropertyIdpk does not match', NULL, '2018-04-16 18:31:34.000000', NULL, '2018-04-16 18:31:34.000000', 'admin', '000000'),
	('86', 0, '1', '10058', 'VehicleDO.idpk and leVehicleAssocDO.VehicleIdpk does not match', NULL, '2018-04-16 18:31:34.000000', NULL, '2018-04-16 18:31:34.000000', 'admin', '000000'),
	('86bdead2-4551-440b-a298-60e5dfbaaebf', 0, '1', '10099', 'inactiveLeRegistryDO.inactivatedTs is needed in the request', 'inactiveLeRegistryDO.inactivatedTs is needed in the request', '2018-04-16 18:31:31.000000', NULL, '2018-04-16 18:31:31.000000', 'admin', '00000000'),
	('86ca1754-9593-4c1e-a20b-dcefff7b98ff', 0, '1', '10059', 'AccountAdressAssocDO.idPk is needed in the request', NULL, '2018-04-16 18:31:34.000000', NULL, '2018-04-16 18:31:34.000000', 'admin', '12312311115999'),
	('9', 0, '1', '1001', 'Required Data Object is not present in the request', NULL, '2018-04-16 18:31:33.000000', NULL, '2018-04-16 18:31:33.000000', 'admin', '000000'),
	('91', 0, '1', '11001', 'AccountAddressAssocDO.addressTypeRefkey is invalid or Refkey-RefValue pair is invalid', NULL, '2018-04-16 18:31:30.000000', NULL, '2018-04-16 18:31:30.000000', 'admin', '000000'),
	('92', 0, '1', '11002', 'AccountAddressAssocDO.addressSubtypeRefkey is invalid or Refkey-RefValue pair is invalid', NULL, '2018-04-16 18:31:30.000000', NULL, '2018-04-16 18:31:30.000000', 'admin', '000000'),
	('93', 0, '1', '11003', 'AccountDO.contractSignedLangRefkey is invalid or Refkey-RefValue pair is invalid', NULL, '2018-04-16 18:31:30.000000', NULL, '2018-04-16 18:31:30.000000', 'admin', '000000'),
	('9318b198-a753-4e71-a893-ff0777cb3c9a', 0, '1', '10106', 'matchMergedLeAssocDO.survivorLegalentityIdpk is needed in the request', 'matchMergedLeAssocDO.survivorLegalentityIdpk is needed in the request', '2018-04-16 18:31:32.000000', NULL, '2018-04-16 18:31:32.000000', 'admin', '00000000'),
	('94', 0, '1', '11004', 'AccountDO.currencyRefkey is invalid or Refkey-RefValue pair is invalid', NULL, '2018-04-16 18:31:30.000000', NULL, '2018-04-16 18:31:30.000000', 'admin', '000000'),
	('95', 0, '1', '11005', 'AccountDO.billingModeTypeRefkey is invalid or Refkey-RefValue pair is invalid', NULL, '2018-04-16 18:31:30.000000', NULL, '2018-04-16 18:31:30.000000', 'admin', '000000'),
	('96', 0, '1', '11006', 'AccountDO.lobtypeRefkey is invalid or Refkey-RefValue pair is invalid', NULL, '2018-04-16 18:31:30.000000', NULL, '2018-04-16 18:31:30.000000', 'admin', '000000'),
	('97', 0, '1', '11007', 'AccountDO.sourceSystemRefkey is invalid or Refkey-RefValue pair is invalid', NULL, '2018-04-16 18:31:30.000000', NULL, '2018-04-16 18:31:30.000000', 'admin', '000000'),
	('98', 0, '1', '11008', 'AccountDO.branchCodeRefkey is invalid or Refkey-RefValue pair is invalid', NULL, '2018-04-16 18:31:30.000000', NULL, '2018-04-16 18:31:30.000000', 'admin', '000000'),
	('99', 0, '1', '11009', 'AccountDO.accountSourceStatusRefkey is invalid or Refkey-RefValue pair is invalid', NULL, '2018-04-16 18:31:30.000000', NULL, '2018-04-16 18:31:30.000000', 'admin', '000000'),
	('9c8a0c6d-978f-4edc-ba44-bd470e49aaf6', 0, '1', '10095', 'refMatchThresholdDO.decayThresholdInDays is needed in the request', 'refMatchThresholdDO.decayThresholdInDays is needed in the request', '2018-04-16 18:31:31.000000', NULL, '2018-04-16 18:31:31.000000', 'admin', '00000000'),
	('9e276606-bb22-4a63-878f-a4c47d36e300', 0, '1', '10109', 'inactiveLeRegistryDO.legalentityIdpk or inactivationReasonRefkey is needed in the request', 'inactiveLeRegistryDO.legalentityIdpk or inactivationReasonRefkey is needed in the request', '2018-04-16 18:31:32.000000', NULL, '2018-04-16 18:31:32.000000', 'admin', '00000000'),
	('a51a5d28-f1e7-4ba2-8f5b-1af78c69311a', 0, '1', '10065', 'MiscellaneousInfoDO.entityObjectTypeRefkey should not be null', NULL, '2018-04-16 18:31:34.000000', NULL, '2018-04-16 18:31:34.000000', 'admin', '12312311115999'),
	('a78609d9-8e9d-4bcb-8d5f-7deee17d7a79', 0, '1', '10062', 'accountAddressAssocDO.idPk is needed in the request', NULL, '2018-04-16 18:31:34.000000', NULL, '2018-04-16 18:31:34.000000', 'admin', '12312311115999'),
	('a7a91430-0c81-4a67-b536-cc007109dcd1', 0, '1', '10091', 'refMatchScoreDO.matchResultRefkey is not valid', 'refMatchScoreDO.matchResultRefkey is not valid', '2018-04-16 18:31:31.000000', NULL, '2018-04-16 18:31:31.000000', 'admin', '00000000'),
	('a7b3aa19-37cb-4bbd-a660-38c8205d651f', 0, '1', '10122', 'batchEntityToProcessDO.entryMadeBySubsystemName is needed in the request', 'batchEntityToProcessDO.entryMadeBySubsystemName is needed in the request', '2018-04-16 18:31:32.000000', NULL, '2018-04-16 18:31:32.000000', 'admin', '00000000'),
	('a889486f-beac-43e5-abee-937d93c4aed2', 0, '1', '1015', 'ConfigTxnRegistryDO.txnserviceClass should not be null', 'ConfigTxnRegistryDO.txnserviceClass should not be null', '2018-04-16 18:31:32.000000', NULL, '2018-04-16 18:31:32.000000', 'admin', '12312311115999'),
	('ad4e5732-39ec-473b-9cd0-e44ad4e08dbb', 0, '1', '1017', 'configInquiryLevelsDO.inquiryLevel should not be null', 'configInquiryLevelsDO.inquiryLevel should not be null', '2018-04-16 18:31:32.000000', NULL, '2018-04-16 18:31:32.000000', 'admin', '12312311115999'),
	('ae0ec2f7-117d-4eb9-b896-54f74e3bc0f4', 0, '1', '10096', 'refMatchThresholdDO.decayPercentage is needed in the request', 'refMatchThresholdDO.decayPercentage is needed in the request', '2018-04-16 18:31:31.000000', NULL, '2018-04-16 18:31:31.000000', 'admin', '00000000'),
	('b02ff834-298d-4251-8596-9c367efb1399', 0, '1', '10111', 'matchMergedLeAssocDO.MatchMergedLeAssocDO.survivorLegalentityIdpk or mergedLegalentityIdpk or mergeReasonRefkey is needed in the request', 'matchMergedLeAssocDO.MatchMergedLeAssocDO.survivorLegalentityIdpk or mergedLegalentityIdpk or mergeReasonRefkey is needed in the request', '2018-04-16 18:31:32.000000', NULL, '2018-04-16 18:31:32.000000', 'admin', '00000000'),
	('b205ba6f-26ca-4534-9864-ada111a0c1e3', 0, '1', '10064', 'MiscellaneousInfoDO.EntityIdpk should not be null', NULL, '2018-04-16 18:31:34.000000', NULL, '2018-04-16 18:31:34.000000', 'admin', '12312311115999'),
	('b417950f-3bb5-4a4c-b761-5cd2498b81c8', 0, '1', '10118', 'batchEntityToProcessDO.entityObjectTypeRefkey is needed in the request', 'batchEntityToProcessDO.entityObjectTypeRefkey is needed in the request', '2018-04-16 18:31:32.000000', NULL, '2018-04-16 18:31:32.000000', 'admin', '00000000'),
	('b6001f67-d80c-4a42-bad0-055ee6119738', 0, '1', '1020', 'Invalid inquiry level provided in the request', 'Invalid inquiry level provided in the request', '2018-04-16 18:31:32.000000', NULL, '2018-04-16 18:31:32.000000', 'admin', '12312311115999'),
	('b7146d6d-0c34-4951-b27b-3f38b4667852', 0, '1', '10067', 'EntityGroupAssocDO.entityObjectTypeRefkey should not be null', NULL, '2018-04-16 18:31:34.000000', NULL, '2018-04-16 18:31:34.000000', 'admin', '12312311115999'),
	('b75d4319-3b14-4a68-aa63-7bf7c17ac94c', 0, '1', '10086', 'Recieved empty string for lePersonDO.genderRefkey, this attribute cannot be updated to null', 'Recieved empty string for lePersonDO.genderRefkey, this attribute cannot be updated to null', '2018-04-16 18:31:32.000000', NULL, '2018-04-16 18:31:32.000000', 'admin', '00000000'),
	('b8095b96-ddd0-4dfe-a61d-f7daea29a5b9', 0, '1', '1012', 'configLanguageCodeKey is not valid', 'configLanguageCodeKey is not valid', '2018-04-16 18:31:32.000000', NULL, '2018-04-16 18:31:32.000000', 'admin', '12312311115999'),
	('bc0e55ea-9fca-441a-9f71-b4f31a870d60', 0, '1', '10121', 'batchEntityToProcessDO.batchActionStatusRefkey is needed in the request', 'batchEntityToProcessDO.batchActionStatusRefkey is needed in the request', '2018-04-16 18:31:32.000000', NULL, '2018-04-16 18:31:32.000000', 'admin', '00000000'),
	('bcaf5653-4943-4e6c-94fa-c7cdf96ebcec', 0, '1', '10087', 'refMatchScoreDO.matchEntityObjectName is needed in the request', 'refMatchScoreDO.matchEntityObjectName is needed in the request', '2018-04-16 18:31:31.000000', NULL, '2018-04-16 18:31:31.000000', 'admin', '00000000'),
	('bf6bf8e8-11c9-457e-87a1-975b2498a1ed', 0, '1', '10075', 'authUserroleAccesscontrolDO.profileType should not be null', 'authUserroleAccesscontrolDO.profileType should not be null', '2018-04-16 18:31:32.000000', NULL, '2018-04-16 18:31:32.000000', 'admin', '12312311115999'),
	('bfd2c41e-6f99-4ce7-bb32-d2dca80a20cf', 0, '1', '10112', 'The attributes searchMatchCandidateLERequestDO.identificationNumberList, IdentificationTypeRefkeyList, corporationNameList or (nameOneList and lastNameList) is required', 'The attributes searchMatchCandidateLERequestDO.identificationNumberList, IdentificationTypeRefkeyList, corporationNameList or (nameOneList and lastNameList) is required', '2018-04-16 18:31:32.000000', NULL, '2018-04-16 18:31:32.000000', 'admin', '00000000'),
	('c03ad3a0-7c19-4f28-8f9c-64252da149fe', 0, '1', '10076', 'authUserroleAccesscontrolDO.authUserRoleRegistryIdpk should not be null', 'authUserroleAccesscontrolDO.authUserRoleRegistryIdpk should not be null', '2018-04-16 18:31:32.000000', NULL, '2018-04-16 18:31:32.000000', 'admin', '12312311115999'),
	('c1bd554d-0f36-4e97-8a11-65e782e05e5e', 0, '1', '10092', 'refMatchScoreDO.matchProposedActionRefkey is not valid', 'refMatchScoreDO.matchProposedActionRefkey is not valid', '2018-04-16 18:31:31.000000', NULL, '2018-04-16 18:31:31.000000', 'admin', '00000000'),
	('c97f1dc7-2e09-4956-a35e-690261129899', 0, '1', '10089', 'refMatchScoreDO.matchResultRefkey is needed in the request', 'refMatchScoreDO.matchResultRefkey is needed in the request', '2018-04-16 18:31:31.000000', NULL, '2018-04-16 18:31:31.000000', 'admin', '00000000'),
	('d4da19b3-2039-4380-8bd4-cc063a60559f', 0, '1', '1016', 'ConfigTxnRegistryDO.txnserviceClassmethod should not be null', 'ConfigTxnRegistryDO.txnserviceClassmethod should not be null', '2018-04-16 18:31:32.000000', NULL, '2018-04-16 18:31:32.000000', 'admin', '12312311115999'),
	('db56f872-ea59-4646-b691-78a1a5167d70', 0, '1', '10088', 'refMatchScoreDO.matchAttrPattern is needed in the request', 'refMatchScoreDO.matchAttrPattern is needed in the request', '2018-04-16 18:31:31.000000', NULL, '2018-04-16 18:31:31.000000', 'admin', '00000000'),
	('dd7108a6-1ea2-4fc8-a04a-7257c71ca813', 0, '1', '10101', 'matchCandidateLeRegistryDO.legalentityIdpk is needed in the request', 'matchCandidateLeRegistryDO.legalentityIdpk is needed in the request', '2018-04-16 18:31:31.000000', NULL, '2018-04-16 18:31:31.000000', 'admin', '00000000'),
	('de8912ff-2324-4cde-b1d2-fc0831f9896e', 0, '1', '10063', 'accountDO.idpk is needed in the request', NULL, '2018-04-16 18:31:34.000000', NULL, '2018-04-16 18:31:34.000000', 'admin', '12312311115999'),
	('e234d760-285e-42c2-9d24-19fa49028996', 0, '1', '11063', 'matchCandidateLeRegistryDO.matchProposedActionRefkey is invalid or Refkey-RefValue pair is invalid', 'matchCandidateLeRegistryDO.matchProposedActionRefkey is invalid or Refkey-RefValue pair is invalid', '2018-04-16 18:31:31.000000', NULL, '2018-04-16 18:31:31.000000', 'admin', '00000000'),
	('e4e521c8-ea8c-40b0-8a28-1020b92e6974', 0, '1', '10080', 'authUserroleAccesscontrolDO.profileType value is not valid, valid values are USER and ROLE', 'authUserroleAccesscontrolDO.profileType value is not valid, valid values are USER and ROLE', '2018-04-16 18:31:32.000000', NULL, '2018-04-16 18:31:32.000000', 'admin', '00000000'),
	('e54fff28-06cd-4435-9746-765ea345bed3', 0, '1', '10103', 'matchCandidateLeRegistryDO.matchPattern is needed in the request', 'matchCandidateLeRegistryDO.matchPattern is needed in the request', '2018-04-16 18:31:31.000000', NULL, '2018-04-16 18:31:31.000000', 'admin', '00000000'),
	('ebe718e5-8894-4118-a55e-792c048f875a', 0, '1', '11066', 'batchEntityToProcessDO.entityObjectTypeRefkey is invalid or Refkey-RefValue pair is invalid', 'batchEntityToProcessDO.entityObjectTypeRefkey is invalid or Refkey-RefValue pair is invalid', '2018-04-16 18:31:32.000000', NULL, '2018-04-16 18:31:32.000000', 'admin', '00000000'),
	('ed8fb7a2-e51b-4a6e-8500-5e35b6bbc90c', 0, '1', '10102', 'matchCandidateLeRegistryDO.candidateLegalentityidpk is needed in the request', 'matchCandidateLeRegistryDO.candidateLegalentityidpk is needed in the request', '2018-04-16 18:31:31.000000', NULL, '2018-04-16 18:31:31.000000', 'admin', '00000000'),
	('f13c566d-a66c-44f8-a8a0-a95839042d20', 0, '1', '10107', 'matchMergedLeAssocDO.mergedLegalentityIdpk is needed in the request', 'matchMergedLeAssocDO.mergedLegalentityIdpk is needed in the request', '2018-04-16 18:31:32.000000', NULL, '2018-04-16 18:31:32.000000', 'admin', '00000000'),
	('f4b38eda-78fb-41ca-b19d-7ad3c551a809', 0, '1', '10083', 'user or role is not authorized to execute given transaction', 'user or role is not authorized to execute given transaction', '2018-04-16 18:31:32.000000', NULL, '2018-04-16 18:31:32.000000', 'admin', '00000000'),
	('f80df490-6da7-41b2-9beb-03bfc83b7307', 0, '1', '10115', 'performLeMatchRequestDO.matchEngineType is not valid', 'performLeMatchRequestDO.matchEngineType is not valid', '2018-04-16 18:31:32.000000', NULL, '2018-04-16 18:31:32.000000', 'admin', '00000000'),
	('fd10aee6-4783-400d-ad33-59ef7da727a4', 0, '1', '10081', 'One of the attributes searchAuthAccessControlDO.rolename or userName is required', 'One of the attributes searchAuthAccessControlDO.rolename or userName is required', '2018-04-16 18:31:32.000000', NULL, '2018-04-16 18:31:32.000000', 'admin', '00000000');
/*!40000 ALTER TABLE `config_errorcode_registry` ENABLE KEYS */;

-- Dumping structure for table yug_owner.config_inquiry_levels
CREATE TABLE IF NOT EXISTS `config_inquiry_levels` (
  `ID_PK` varchar(50) COLLATE utf8_bin NOT NULL,
  `VERSION` decimal(22,0) NOT NULL,
  `CREATED_TS` datetime(6) NOT NULL,
  `DELETED_TS` datetime(6) DEFAULT NULL,
  `UPDATED_TS` datetime(6) NOT NULL,
  `UPDATED_BY_USER` varchar(50) COLLATE utf8_bin NOT NULL,
  `UPDATED_BY_TXN_ID` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `INQUIRY_LEVEL` varchar(20) COLLATE utf8_bin NOT NULL,
  `APPLICABLE_DOBJ` varchar(100) COLLATE utf8_bin NOT NULL,
  `CHILD_DOBJ` varchar(100) COLLATE utf8_bin NOT NULL,
  `DESCRIPTION` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_PK`),
  UNIQUE KEY `CONFIGINQLEVELS_UNIQUEKEY` (`CHILD_DOBJ`,`APPLICABLE_DOBJ`,`INQUIRY_LEVEL`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- Dumping data for table yug_owner.config_inquiry_levels: ~54 rows (approximately)
/*!40000 ALTER TABLE `config_inquiry_levels` DISABLE KEYS */;
INSERT INTO `config_inquiry_levels` (`ID_PK`, `VERSION`, `CREATED_TS`, `DELETED_TS`, `UPDATED_TS`, `UPDATED_BY_USER`, `UPDATED_BY_TXN_ID`, `INQUIRY_LEVEL`, `APPLICABLE_DOBJ`, `CHILD_DOBJ`, `DESCRIPTION`) VALUES
	('1', 0, '2018-04-16 18:33:53.000000', NULL, '2018-04-16 18:33:53.000000', 'admin', '00000000', '101', 'LegalentityDO', 'LeSystemKeysRegistryDO', 'LeSystemKeysRegistryDO'),
	('10', 0, '2018-04-16 18:33:53.000000', NULL, '2018-04-16 18:33:53.000000', 'admin', '00000000', '103', 'LegalentityDO', 'LePreferencesDO', 'LePreferencesDO'),
	('11', 0, '2018-04-16 18:33:53.000000', NULL, '2018-04-16 18:33:53.000000', 'admin', '00000000', '104', 'LegalentityDO', 'LeSystemKeysRegistryDO', 'LeSystemKeysRegistryDO'),
	('12', 0, '2018-04-16 18:33:53.000000', NULL, '2018-04-16 18:33:53.000000', 'admin', '00000000', '104', 'LegalentityDO', 'LePersonDO', 'LePersonDO'),
	('13', 0, '2018-04-16 18:33:53.000000', NULL, '2018-04-16 18:33:53.000000', 'admin', '00000000', '104', 'LegalentityDO', 'LeCorporationDO', 'LeCorporationDO'),
	('14', 0, '2018-04-16 18:33:53.000000', NULL, '2018-04-16 18:33:53.000000', 'admin', '00000000', '104', 'LegalentityDO', 'LeAddressAssocDO', 'LeAddressAssocDO'),
	('15', 0, '2018-04-16 18:33:53.000000', NULL, '2018-04-16 18:33:53.000000', 'admin', '00000000', '104', 'LegalentityDO', 'LePhoneAssocDO', 'LePhoneAssocDO'),
	('16', 0, '2018-04-16 18:33:53.000000', NULL, '2018-04-16 18:33:53.000000', 'admin', '00000000', '104', 'LegalentityDO', 'LePreferencesDO', 'LePreferencesDO'),
	('17', 0, '2018-04-16 18:33:53.000000', NULL, '2018-04-16 18:33:53.000000', 'admin', '00000000', '104', 'LegalentityDO', 'LeIdentifierKycRegistryDO', 'LeIdentifierKycRegistryDO'),
	('18', 0, '2018-04-16 18:33:53.000000', NULL, '2018-04-16 18:33:53.000000', 'admin', '00000000', '104', 'LegalentityDO', 'MiscellaneousInfoDO', 'MiscellaneousInfoDO'),
	('19', 0, '2018-04-16 18:33:53.000000', NULL, '2018-04-16 18:33:53.000000', 'admin', '00000000', '105', 'LegalentityDO', 'LeSystemKeysRegistryDO', 'LeSystemKeysRegistryDO'),
	('2', 0, '2018-04-16 18:33:53.000000', NULL, '2018-04-16 18:33:53.000000', 'admin', '00000000', '102', 'LegalentityDO', 'LeSystemKeysRegistryDO', 'LeSystemKeysRegistryDO'),
	('20', 0, '2018-04-16 18:33:53.000000', NULL, '2018-04-16 18:33:53.000000', 'admin', '00000000', '105', 'LegalentityDO', 'LePersonDO', 'LePersonDO'),
	('21', 0, '2018-04-16 18:33:53.000000', NULL, '2018-04-16 18:33:53.000000', 'admin', '00000000', '105', 'LegalentityDO', 'LeCorporationDO', 'LeCorporationDO'),
	('22', 0, '2018-04-16 18:33:53.000000', NULL, '2018-04-16 18:33:53.000000', 'admin', '00000000', '105', 'LegalentityDO', 'LeAddressAssocDO', 'LeAddressAssocDO'),
	('23', 0, '2018-04-16 18:33:53.000000', NULL, '2018-04-16 18:33:53.000000', 'admin', '00000000', '105', 'LegalentityDO', 'LePhoneAssocDO', 'LePhoneAssocDO'),
	('24', 0, '2018-04-16 18:33:53.000000', NULL, '2018-04-16 18:33:53.000000', 'admin', '00000000', '105', 'LegalentityDO', 'LePreferencesDO', 'LePreferencesDO'),
	('25', 0, '2018-04-16 18:33:54.000000', NULL, '2018-04-16 18:33:54.000000', 'admin', '00000000', '105', 'LegalentityDO', 'LeIdentifierKycRegistryDO', 'LeIdentifierKycRegistryDO'),
	('26', 0, '2018-04-16 18:33:54.000000', NULL, '2018-04-16 18:33:54.000000', 'admin', '00000000', '105', 'LegalentityDO', 'MiscellaneousInfoDO', 'MiscellaneousInfoDO'),
	('27', 0, '2018-04-16 18:33:54.000000', NULL, '2018-04-16 18:33:54.000000', 'admin', '00000000', '105', 'LegalentityDO', 'LeAccountAssocDO', 'LeAccountAssocDO'),
	('28', 0, '2018-04-16 18:33:54.000000', NULL, '2018-04-16 18:33:54.000000', 'admin', '00000000', '106', 'LegalentityDO', 'LeSystemKeysRegistryDO', 'LeSystemKeysRegistryDO'),
	('29', 0, '2018-04-16 18:33:54.000000', NULL, '2018-04-16 18:33:54.000000', 'admin', '00000000', '106', 'LegalentityDO', 'LePersonDO', 'LePersonDO'),
	('3', 0, '2018-04-16 18:33:53.000000', NULL, '2018-04-16 18:33:53.000000', 'admin', '00000000', '102', 'LegalentityDO', 'LePersonDO', 'LePersonDO'),
	('30', 0, '2018-04-16 18:33:54.000000', NULL, '2018-04-16 18:33:54.000000', 'admin', '00000000', '106', 'LegalentityDO', 'LeCorporationDO', 'LeCorporationDO'),
	('31', 0, '2018-04-16 18:33:54.000000', NULL, '2018-04-16 18:33:54.000000', 'admin', '00000000', '106', 'LegalentityDO', 'LeAddressAssocDO', 'LeAddressAssocDO'),
	('32', 0, '2018-04-16 18:33:54.000000', NULL, '2018-04-16 18:33:54.000000', 'admin', '00000000', '106', 'LegalentityDO', 'LePhoneAssocDO', 'LePhoneAssocDO'),
	('33', 0, '2018-04-16 18:33:54.000000', NULL, '2018-04-16 18:33:54.000000', 'admin', '00000000', '106', 'LegalentityDO', 'LePreferencesDO', 'LePreferencesDO'),
	('34', 0, '2018-04-16 18:33:54.000000', NULL, '2018-04-16 18:33:54.000000', 'admin', '00000000', '106', 'LegalentityDO', 'LeIdentifierKycRegistryDO', 'LeIdentifierKycRegistryDO'),
	('35', 0, '2018-04-16 18:33:54.000000', NULL, '2018-04-16 18:33:54.000000', 'admin', '00000000', '106', 'LegalentityDO', 'MiscellaneousInfoDO', 'MiscellaneousInfoDO'),
	('36', 0, '2018-04-16 18:33:54.000000', NULL, '2018-04-16 18:33:54.000000', 'admin', '00000000', '106', 'LegalentityDO', 'LeAccountAssocDO', 'LeAccountAssocDO'),
	('37', 0, '2018-04-16 18:33:54.000000', NULL, '2018-04-16 18:33:54.000000', 'admin', '00000000', '106', 'LegalentityDO', 'LePropertyAssocDO', 'LePropertyAssocDO'),
	('38', 0, '2018-04-16 18:33:54.000000', NULL, '2018-04-16 18:33:54.000000', 'admin', '00000000', '106', 'LegalentityDO', 'LeVehicleAssocDO', 'LeVehicleAssocDO'),
	('39', 0, '2018-04-16 18:33:54.000000', NULL, '2018-04-16 18:33:54.000000', 'admin', '00000000', '106', 'LegalentityDO', 'LeToLeRelationshipDO', 'LeToLeRelationshipDO'),
	('4', 0, '2018-04-16 18:33:53.000000', NULL, '2018-04-16 18:33:53.000000', 'admin', '00000000', '102', 'LegalentityDO', 'LeCorporationDO', 'LeCorporationDO'),
	('40', 0, '2018-04-16 18:33:54.000000', NULL, '2018-04-16 18:33:54.000000', 'admin', '00000000', '107', 'LegalentityDO', 'LeSystemKeysRegistryDO', 'LeSystemKeysRegistryDO'),
	('41', 0, '2018-04-16 18:33:53.000000', NULL, '2018-04-16 18:33:53.000000', 'admin', '00000000', '107', 'LegalentityDO', 'LePersonDO', 'LePersonDO'),
	('42', 0, '2018-04-16 18:33:53.000000', NULL, '2018-04-16 18:33:53.000000', 'admin', '00000000', '107', 'LegalentityDO', 'LeCorporationDO', 'LeCorporationDO'),
	('43', 0, '2018-04-16 18:33:53.000000', NULL, '2018-04-16 18:33:53.000000', 'admin', '00000000', '107', 'LegalentityDO', 'LeAccountAssocDO', 'LeAccountAssocDO'),
	('44', 0, '2018-04-16 18:33:53.000000', NULL, '2018-04-16 18:33:53.000000', 'admin', '00000000', '101', 'AccountDO', 'AccountDO', 'AccountDO'),
	('45', 0, '2018-04-16 18:33:53.000000', NULL, '2018-04-16 18:33:53.000000', 'admin', '00000000', '102', 'AccountDO', 'AccountAddressAssocDO', 'AccountAddressAssocDO'),
	('46', 0, '2018-04-16 18:33:53.000000', NULL, '2018-04-16 18:33:53.000000', 'admin', '00000000', '102', 'AccountDO', 'AccountPhoneAssocDO', 'AccountPhoneAssocDO'),
	('47', 0, '2018-04-16 18:33:53.000000', NULL, '2018-04-16 18:33:53.000000', 'admin', '00000000', '103', 'AccountDO', 'AccountAddressAssocDO', 'AccountAddressAssocDO'),
	('48', 0, '2018-04-16 18:33:53.000000', NULL, '2018-04-16 18:33:53.000000', 'admin', '00000000', '103', 'AccountDO', 'AccountPhoneAssocDO', 'AccountPhoneAssocDO'),
	('49', 0, '2018-04-16 18:33:53.000000', NULL, '2018-04-16 18:33:53.000000', 'admin', '00000000', '103', 'AccountDO', 'LeAccountAssocDO', 'LeAccountAssocDO'),
	('5', 0, '2018-04-16 18:33:53.000000', NULL, '2018-04-16 18:33:53.000000', 'admin', '00000000', '103', 'LegalentityDO', 'LeSystemKeysRegistryDO', 'LeSystemKeysRegistryDO'),
	('50', 0, '2018-04-16 18:33:53.000000', NULL, '2018-04-16 18:33:53.000000', 'admin', '00000000', '108', 'LegalentityDO', 'LePersonDO', 'This inquiry level is used by default Match and Merge framework so must not be customized'),
	('51', 0, '2018-04-16 18:33:53.000000', NULL, '2018-04-16 18:33:53.000000', 'admin', '00000000', '108', 'LegalentityDO', 'LeCorporationDO', 'This inquiry level is used by default Match and Merge framework so must not be customized'),
	('52', 0, '2018-04-16 18:33:53.000000', NULL, '2018-04-16 18:33:53.000000', 'admin', '00000000', '108', 'LegalentityDO', 'LeAddressAssocDO', 'This inquiry level is used by default Match and Merge framework so must not be customized'),
	('53', 0, '2018-04-16 18:33:54.000000', NULL, '2018-04-16 18:33:54.000000', 'admin', '00000000', '108', 'LegalentityDO', 'LePhoneAssocDO', 'This inquiry level is used by default Match and Merge framework so must not be customized'),
	('54', 0, '2018-04-16 18:33:54.000000', NULL, '2018-04-16 18:33:54.000000', 'admin', '00000000', '108', 'LegalentityDO', 'LeIdentifierKycRegistryDO', 'This inquiry level is used by default Match and Merge framework so must not be customized'),
	('6', 0, '2018-04-16 18:33:53.000000', NULL, '2018-04-16 18:33:53.000000', 'admin', '00000000', '103', 'LegalentityDO', 'LePersonDO', 'LePersonDO'),
	('7', 0, '2018-04-16 18:33:53.000000', NULL, '2018-04-16 18:33:53.000000', 'admin', '00000000', '103', 'LegalentityDO', 'LeCorporationDO', 'LeCorporationDO'),
	('8', 0, '2018-04-16 18:33:53.000000', NULL, '2018-04-16 18:33:53.000000', 'admin', '00000000', '103', 'LegalentityDO', 'LeAddressAssocDO', 'LeAddressAssocDO'),
	('9', 0, '2018-04-16 18:33:53.000000', NULL, '2018-04-16 18:33:53.000000', 'admin', '00000000', '103', 'LegalentityDO', 'LePhoneAssocDO', 'LePhoneAssocDO');
/*!40000 ALTER TABLE `config_inquiry_levels` ENABLE KEYS */;

-- Dumping structure for table yug_owner.config_language_code
CREATE TABLE IF NOT EXISTS `config_language_code` (
  `ID_PK` varchar(50) COLLATE utf8_bin NOT NULL,
  `VERSION` decimal(22,0) NOT NULL,
  `CREATED_TS` datetime(6) NOT NULL,
  `DELETED_TS` datetime(6) DEFAULT NULL,
  `UPDATED_TS` datetime(6) NOT NULL,
  `UPDATED_BY_USER` varchar(50) COLLATE utf8_bin NOT NULL,
  `UPDATED_BY_TXN_ID` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `KEY` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `VALUE` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `DESCRIPTION` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_PK`),
  UNIQUE KEY `CONFIG_LANGCODE_UNIQUEKEY` (`KEY`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- Dumping data for table yug_owner.config_language_code: ~2 rows (approximately)
/*!40000 ALTER TABLE `config_language_code` DISABLE KEYS */;
INSERT INTO `config_language_code` (`ID_PK`, `VERSION`, `CREATED_TS`, `DELETED_TS`, `UPDATED_TS`, `UPDATED_BY_USER`, `UPDATED_BY_TXN_ID`, `KEY`, `VALUE`, `DESCRIPTION`) VALUES
	('7b18a1f2-3df1-4660-81b6-0ad29bda70ee', 1, '2017-08-08 16:01:57.000000', NULL, '2017-08-08 16:02:41.000000', 'admin', '1234567890123', '1', 'ENGLISH', 'English International'),
	('8dfadeae-acea-48a5-b7cf-f3cefd05fcd3', 0, '2017-08-08 16:14:35.000000', NULL, '2017-08-08 16:14:35.000000', 'admin', '1234567890123', '2', 'HINDI', 'Hindi');
/*!40000 ALTER TABLE `config_language_code` ENABLE KEYS */;

-- Dumping structure for table yug_owner.config_txn_registry
CREATE TABLE IF NOT EXISTS `config_txn_registry` (
  `ID_PK` varchar(50) COLLATE utf8_bin NOT NULL,
  `VERSION` decimal(22,0) NOT NULL,
  `TXNSERVICE_NAME` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `TXNSERVICE_CLASS` varchar(200) COLLATE utf8_bin DEFAULT NULL,
  `TXNSERVICE_CLASSMETHOD` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `DESCRIPTION` varchar(200) COLLATE utf8_bin DEFAULT NULL,
  `CREATED_TS` datetime(6) NOT NULL,
  `DELETED_TS` datetime(6) DEFAULT NULL,
  `UPDATED_TS` datetime(6) NOT NULL,
  `UPDATED_BY_USER` varchar(50) COLLATE utf8_bin NOT NULL,
  `UPDATED_TXN_REF_ID` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  UNIQUE KEY `CONFIG_APPPROPERTIES_PK` (`ID_PK`),
  UNIQUE KEY `TXNSERVICE_UNIQUE` (`TXNSERVICE_CLASSMETHOD`,`TXNSERVICE_CLASS`,`TXNSERVICE_NAME`),
  UNIQUE KEY `UNIQUESERVICENAME` (`TXNSERVICE_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- Dumping data for table yug_owner.config_txn_registry: ~451 rows (approximately)
/*!40000 ALTER TABLE `config_txn_registry` DISABLE KEYS */;
INSERT INTO `config_txn_registry` (`ID_PK`, `VERSION`, `TXNSERVICE_NAME`, `TXNSERVICE_CLASS`, `TXNSERVICE_CLASSMETHOD`, `DESCRIPTION`, `CREATED_TS`, `DELETED_TS`, `UPDATED_TS`, `UPDATED_BY_USER`, `UPDATED_TXN_REF_ID`) VALUES
	('1', 0, 'createConfigTxnRegistryBase', 'com.yugandhar.mdm.config.txnregistry.ConfigTxnRegistryService', 'add', 'find by business key method search properties based on transaction Name', '2018-04-16 18:35:32.000000', NULL, '2018-04-16 18:35:32.000000', 'Generator', '000000000'),
	('100', 0, 'retrieveVehicleBase', 'com.yugandhar.mdm.corecomponent.VehicleService', 'findById', 'find by business key method search properties based on transaction Name', '2018-04-16 18:35:31.000000', NULL, '2018-04-16 18:35:31.000000', 'Generator', '000000000'),
	('101', 0, 'createRefIdentificationTypeBase', 'com.yugandhar.mdm.corecomponentref.RefIdentificationTypeService', 'add', 'find by business key method search properties based on transaction Name', '2018-04-16 18:35:31.000000', NULL, '2018-04-16 18:35:31.000000', 'Generator', '000000000'),
	('102', 0, 'updateRefIdentificationTypeBase', 'com.yugandhar.mdm.corecomponentref.RefIdentificationTypeService', 'merge', 'find by business key method search properties based on transaction Name', '2018-04-16 18:35:31.000000', NULL, '2018-04-16 18:35:31.000000', 'Generator', '000000000'),
	('103', 0, 'retrieveRefIdentificationTypeBase', 'com.yugandhar.mdm.corecomponentref.RefIdentificationTypeService', 'findById', 'find by business key method search properties based on transaction Name', '2018-04-16 18:35:32.000000', NULL, '2018-04-16 18:35:32.000000', 'Generator', '000000000'),
	('104', 0, 'findRefIdentificationTypeByBusinessKeyBase', 'com.yugandhar.mdm.corecomponentref.RefIdentificationTypeService', 'findByBusinessKey', 'find by business key method search properties based on transaction Name', '2018-04-16 18:35:32.000000', NULL, '2018-04-16 18:35:32.000000', 'Generator', '000000000'),
	('105', 0, 'findAllRefIdentificationTypeByLanguageCodeBase', 'com.yugandhar.mdm.corecomponentref.RefIdentificationTypeService', 'findAllRecordsByLanguageCode', 'find All records by language code', '2018-04-16 18:35:32.000000', NULL, '2018-04-16 18:35:32.000000', 'Generator', '000000000'),
	('106', 0, 'createRefImportanceTypeBase', 'com.yugandhar.mdm.corecomponentref.RefImportanceTypeService', 'add', 'find by business key method search properties based on transaction Name', '2018-04-16 18:35:32.000000', NULL, '2018-04-16 18:35:32.000000', 'Generator', '000000000'),
	('107', 0, 'updateRefImportanceTypeBase', 'com.yugandhar.mdm.corecomponentref.RefImportanceTypeService', 'merge', 'find by business key method search properties based on transaction Name', '2018-04-16 18:35:32.000000', NULL, '2018-04-16 18:35:32.000000', 'Generator', '000000000'),
	('108', 0, 'retrieveRefImportanceTypeBase', 'com.yugandhar.mdm.corecomponentref.RefImportanceTypeService', 'findById', 'find by business key method search properties based on transaction Name', '2018-04-16 18:35:32.000000', NULL, '2018-04-16 18:35:32.000000', 'Generator', '000000000'),
	('109', 0, 'findRefImportanceTypeByBusinessKeyBase', 'com.yugandhar.mdm.corecomponentref.RefImportanceTypeService', 'findByBusinessKey', 'find by business key method search properties based on transaction Name', '2018-04-16 18:35:32.000000', NULL, '2018-04-16 18:35:32.000000', 'Generator', '000000000'),
	('110', 0, 'findAllRefImportanceTypeByLanguageCodeBase', 'com.yugandhar.mdm.corecomponentref.RefImportanceTypeService', 'findAllRecordsByLanguageCode', 'find All records by language code', '2018-04-16 18:35:32.000000', NULL, '2018-04-16 18:35:32.000000', 'Generator', '000000000'),
	('111', 0, 'createRefIndustryCodeBase', 'com.yugandhar.mdm.corecomponentref.RefIndustryCodeService', 'add', 'find by business key method search properties based on transaction Name', '2018-04-16 18:35:32.000000', NULL, '2018-04-16 18:35:32.000000', 'Generator', '000000000'),
	('113', 0, 'updateRefIndustryCodeBase', 'com.yugandhar.mdm.corecomponentref.RefIndustryCodeService', 'merge', 'find by business key method search properties based on transaction Name', '2018-04-16 18:35:32.000000', NULL, '2018-04-16 18:35:32.000000', 'Generator', '000000000'),
	('114', 0, 'retrieveRefIndustryCodeBase', 'com.yugandhar.mdm.corecomponentref.RefIndustryCodeService', 'findById', 'find by business key method search properties based on transaction Name', '2018-04-16 18:35:32.000000', NULL, '2018-04-16 18:35:32.000000', 'Generator', '000000000'),
	('115', 0, 'findRefIndustryCodeByBusinessKeyBase', 'com.yugandhar.mdm.corecomponentref.RefIndustryCodeService', 'findByBusinessKey', 'find by business key method search properties based on transaction Name', '2018-04-16 18:35:31.000000', NULL, '2018-04-16 18:35:31.000000', 'Generator', '000000000'),
	('116', 0, 'findAllRefIndustryCodeByLanguageCodeBase', 'com.yugandhar.mdm.corecomponentref.RefIndustryCodeService', 'findAllRecordsByLanguageCode', 'find All records by language code', '2018-04-16 18:35:31.000000', NULL, '2018-04-16 18:35:31.000000', 'Generator', '000000000'),
	('117', 0, 'createRefLanguageCodeBase', 'com.yugandhar.mdm.corecomponentref.RefLanguageCodeService', 'add', 'find by business key method search properties based on transaction Name', '2018-04-16 18:35:31.000000', NULL, '2018-04-16 18:35:31.000000', 'Generator', '000000000'),
	('118', 0, 'updateRefLanguageCodeBase', 'com.yugandhar.mdm.corecomponentref.RefLanguageCodeService', 'merge', 'find by business key method search properties based on transaction Name', '2018-04-16 18:35:31.000000', NULL, '2018-04-16 18:35:31.000000', 'Generator', '000000000'),
	('119', 0, 'retrieveRefLanguageCodeBase', 'com.yugandhar.mdm.corecomponentref.RefLanguageCodeService', 'findById', 'find by business key method search properties based on transaction Name', '2018-04-16 18:35:31.000000', NULL, '2018-04-16 18:35:31.000000', 'Generator', '000000000'),
	('120', 0, 'findRefLanguageCodeByBusinessKeyBase', 'com.yugandhar.mdm.corecomponentref.RefLanguageCodeService', 'findByBusinessKey', 'find by business key method search properties based on transaction Name', '2018-04-16 18:35:31.000000', NULL, '2018-04-16 18:35:31.000000', 'Generator', '000000000'),
	('121', 0, 'findAllRefLanguageCodeByLanguageCodeBase', 'com.yugandhar.mdm.corecomponentref.RefLanguageCodeService', 'findAllRecordsByLanguageCode', 'find All records by language code', '2018-04-16 18:35:31.000000', NULL, '2018-04-16 18:35:31.000000', 'Generator', '000000000'),
	('122', 0, 'createRefLeRatingBase', 'com.yugandhar.mdm.corecomponentref.RefLeRatingService', 'add', 'find by business key method search properties based on transaction Name', '2018-04-16 18:35:31.000000', NULL, '2018-04-16 18:35:31.000000', 'Generator', '000000000'),
	('123', 0, 'updateRefLeRatingBase', 'com.yugandhar.mdm.corecomponentref.RefLeRatingService', 'merge', 'find by business key method search properties based on transaction Name', '2018-04-16 18:35:31.000000', NULL, '2018-04-16 18:35:31.000000', 'Generator', '000000000'),
	('124', 0, 'retrieveRefLeRatingBase', 'com.yugandhar.mdm.corecomponentref.RefLeRatingService', 'findById', 'find by business key method search properties based on transaction Name', '2018-04-16 18:35:31.000000', NULL, '2018-04-16 18:35:31.000000', 'Generator', '000000000'),
	('125', 0, 'findRefLeRatingByBusinessKeyBase', 'com.yugandhar.mdm.corecomponentref.RefLeRatingService', 'findByBusinessKey', 'find by business key method search properties based on transaction Name', '2018-04-16 18:35:31.000000', NULL, '2018-04-16 18:35:31.000000', 'Generator', '000000000'),
	('126', 0, 'findAllRefLeRatingByLanguageCodeBase', 'com.yugandhar.mdm.corecomponentref.RefLeRatingService', 'findAllRecordsByLanguageCode', 'find All records by language code', '2018-04-16 18:35:31.000000', NULL, '2018-04-16 18:35:31.000000', 'Generator', '000000000'),
	('127', 0, 'createRefLeRelationshipTypeBase', 'com.yugandhar.mdm.corecomponentref.RefLeRelationshipTypeService', 'add', 'find by business key method search properties based on transaction Name', '2018-04-16 18:35:31.000000', NULL, '2018-04-16 18:35:31.000000', 'Generator', '000000000'),
	('128', 0, 'updateRefLeRelationshipTypeBase', 'com.yugandhar.mdm.corecomponentref.RefLeRelationshipTypeService', 'merge', 'find by business key method search properties based on transaction Name', '2018-04-16 18:35:31.000000', NULL, '2018-04-16 18:35:31.000000', 'Generator', '000000000'),
	('129', 0, 'retrieveRefLeRelationshipTypeBase', 'com.yugandhar.mdm.corecomponentref.RefLeRelationshipTypeService', 'findById', 'find by business key method search properties based on transaction Name', '2018-04-16 18:35:31.000000', NULL, '2018-04-16 18:35:31.000000', 'Generator', '000000000'),
	('130', 0, 'findRefLeRelationshipTypeByBusinessKeyBase', 'com.yugandhar.mdm.corecomponentref.RefLeRelationshipTypeService', 'findByBusinessKey', 'find by business key method search properties based on transaction Name', '2018-04-16 18:35:31.000000', NULL, '2018-04-16 18:35:31.000000', 'Generator', '000000000'),
	('131', 0, 'findAllRefLeRelationshipTypeByLanguageCodeBase', 'com.yugandhar.mdm.corecomponentref.RefLeRelationshipTypeService', 'findAllRecordsByLanguageCode', 'find All records by language code', '2018-04-16 18:35:31.000000', NULL, '2018-04-16 18:35:31.000000', 'Generator', '000000000'),
	('132', 0, 'createRefLeRoletypeBase', 'com.yugandhar.mdm.corecomponentref.RefLeRoletypeService', 'add', 'find by business key method search properties based on transaction Name', '2018-04-16 18:35:31.000000', NULL, '2018-04-16 18:35:31.000000', 'Generator', '000000000'),
	('133', 0, 'updateRefLeRoletypeBase', 'com.yugandhar.mdm.corecomponentref.RefLeRoletypeService', 'merge', 'find by business key method search properties based on transaction Name', '2018-04-16 18:35:31.000000', NULL, '2018-04-16 18:35:31.000000', 'Generator', '000000000'),
	('134', 0, 'retrieveRefLeRoletypeBase', 'com.yugandhar.mdm.corecomponentref.RefLeRoletypeService', 'findById', 'find by business key method search properties based on transaction Name', '2018-04-16 18:35:31.000000', NULL, '2018-04-16 18:35:31.000000', 'Generator', '000000000'),
	('135', 0, 'findRefLeRoletypeByBusinessKeyBase', 'com.yugandhar.mdm.corecomponentref.RefLeRoletypeService', 'findByBusinessKey', 'find by business key method search properties based on transaction Name', '2018-04-16 18:35:31.000000', NULL, '2018-04-16 18:35:31.000000', 'Generator', '000000000'),
	('136', 0, 'findAllRefLeRoletypeByLanguageCodeBase', 'com.yugandhar.mdm.corecomponentref.RefLeRoletypeService', 'findAllRecordsByLanguageCode', 'find All records by language code', '2018-04-16 18:35:31.000000', NULL, '2018-04-16 18:35:31.000000', 'Generator', '000000000'),
	('137', 0, 'createRefLobtypeBase', 'com.yugandhar.mdm.corecomponentref.RefLobtypeService', 'add', 'find by business key method search properties based on transaction Name', '2018-04-16 18:35:31.000000', NULL, '2018-04-16 18:35:31.000000', 'Generator', '000000000'),
	('138', 0, 'updateRefLobtypeBase', 'com.yugandhar.mdm.corecomponentref.RefLobtypeService', 'merge', 'find by business key method search properties based on transaction Name', '2018-04-16 18:35:31.000000', NULL, '2018-04-16 18:35:31.000000', 'Generator', '000000000'),
	('139', 0, 'retrieveRefLobtypeBase', 'com.yugandhar.mdm.corecomponentref.RefLobtypeService', 'findById', 'find by business key method search properties based on transaction Name', '2018-04-16 18:35:31.000000', NULL, '2018-04-16 18:35:31.000000', 'Generator', '000000000'),
	('14', 0, 'createLegalentityBase', 'com.yugandhar.mdm.corecomponent.LegalentityService', 'add', 'find by business key method search properties based on transaction Name', '2018-04-16 18:35:32.000000', NULL, '2018-04-16 18:35:32.000000', 'Generator', '000000000'),
	('140', 0, 'findRefLobtypeByBusinessKeyBase', 'com.yugandhar.mdm.corecomponentref.RefLobtypeService', 'findByBusinessKey', 'find by business key method search properties based on transaction Name', '2018-04-16 18:35:31.000000', NULL, '2018-04-16 18:35:31.000000', 'Generator', '000000000'),
	('141', 0, 'findAllRefLobtypeByLanguageCodeBase', 'com.yugandhar.mdm.corecomponentref.RefLobtypeService', 'findAllRecordsByLanguageCode', 'find All records by language code', '2018-04-16 18:35:31.000000', NULL, '2018-04-16 18:35:31.000000', 'Generator', '000000000'),
	('142', 0, 'createRefPersonnameTypeBase', 'com.yugandhar.mdm.corecomponentref.RefPersonnameTypeService', 'add', 'find by business key method search properties based on transaction Name', '2018-04-16 18:35:31.000000', NULL, '2018-04-16 18:35:31.000000', 'Generator', '000000000'),
	('143', 0, 'updateRefPersonnameTypeBase', 'com.yugandhar.mdm.corecomponentref.RefPersonnameTypeService', 'merge', 'find by business key method search properties based on transaction Name', '2018-04-16 18:35:31.000000', NULL, '2018-04-16 18:35:31.000000', 'Generator', '000000000'),
	('144', 0, 'retrieveRefPersonnameTypeBase', 'com.yugandhar.mdm.corecomponentref.RefPersonnameTypeService', 'findById', 'find by business key method search properties based on transaction Name', '2018-04-16 18:35:31.000000', NULL, '2018-04-16 18:35:31.000000', 'Generator', '000000000'),
	('145', 0, 'findRefPersonnameTypeByBusinessKeyBase', 'com.yugandhar.mdm.corecomponentref.RefPersonnameTypeService', 'findByBusinessKey', 'find by business key method search properties based on transaction Name', '2018-04-16 18:35:31.000000', NULL, '2018-04-16 18:35:31.000000', 'Generator', '000000000'),
	('146', 0, 'findAllRefPersonnameTypeByLanguageCodeBase', 'com.yugandhar.mdm.corecomponentref.RefPersonnameTypeService', 'findAllRecordsByLanguageCode', 'find All records by language code', '2018-04-16 18:35:32.000000', NULL, '2018-04-16 18:35:32.000000', 'Generator', '000000000'),
	('147', 0, 'createRefPersonTypeBase', 'com.yugandhar.mdm.corecomponentref.RefPersonTypeService', 'add', 'find by business key method search properties based on transaction Name', '2018-04-16 18:35:32.000000', NULL, '2018-04-16 18:35:32.000000', 'Generator', '000000000'),
	('148', 0, 'updateRefPersonTypeBase', 'com.yugandhar.mdm.corecomponentref.RefPersonTypeService', 'merge', 'find by business key method search properties based on transaction Name', '2018-04-16 18:35:32.000000', NULL, '2018-04-16 18:35:32.000000', 'Generator', '000000000'),
	('149', 0, 'retrieveRefPersonTypeBase', 'com.yugandhar.mdm.corecomponentref.RefPersonTypeService', 'findById', 'find by business key method search properties based on transaction Name', '2018-04-16 18:35:32.000000', NULL, '2018-04-16 18:35:32.000000', 'Generator', '000000000'),
	('15', 0, 'updateLegalentityBase', 'com.yugandhar.mdm.corecomponent.LegalentityService', 'merge', 'find by business key method search properties based on transaction Name', '2018-04-16 18:35:32.000000', NULL, '2018-04-16 18:35:32.000000', 'Generator', '000000000'),
	('150', 0, 'findRefPersonTypeByBusinessKeyBase', 'com.yugandhar.mdm.corecomponentref.RefPersonTypeService', 'findByBusinessKey', 'find by business key method search properties based on transaction Name', '2018-04-16 18:35:32.000000', NULL, '2018-04-16 18:35:32.000000', 'Generator', '000000000'),
	('151', 0, 'findAllRefPersonTypeByLanguageCodeBase', 'com.yugandhar.mdm.corecomponentref.RefPersonTypeService', 'findAllRecordsByLanguageCode', 'find All records by language code', '2018-04-16 18:35:32.000000', NULL, '2018-04-16 18:35:32.000000', 'Generator', '000000000'),
	('152', 0, 'createRefPhoneSubtypeBase', 'com.yugandhar.mdm.corecomponentref.RefPhoneSubtypeService', 'add', 'find by business key method search properties based on transaction Name', '2018-04-16 18:35:32.000000', NULL, '2018-04-16 18:35:32.000000', 'Generator', '000000000'),
	('153', 0, 'updateRefPhoneSubtypeBase', 'com.yugandhar.mdm.corecomponentref.RefPhoneSubtypeService', 'merge', 'find by business key method search properties based on transaction Name', '2018-04-16 18:35:32.000000', NULL, '2018-04-16 18:35:32.000000', 'Generator', '000000000'),
	('154', 0, 'retrieveRefPhoneSubtypeBase', 'com.yugandhar.mdm.corecomponentref.RefPhoneSubtypeService', 'findById', 'find by business key method search properties based on transaction Name', '2018-04-16 18:35:32.000000', NULL, '2018-04-16 18:35:32.000000', 'Generator', '000000000'),
	('155', 0, 'findRefPhoneSubtypeByBusinessKeyBase', 'com.yugandhar.mdm.corecomponentref.RefPhoneSubtypeService', 'findByBusinessKey', 'find by business key method search properties based on transaction Name', '2018-04-16 18:35:32.000000', NULL, '2018-04-16 18:35:32.000000', 'Generator', '000000000'),
	('156', 0, 'findAllRefPhoneSubtypeByLanguageCodeBase', 'com.yugandhar.mdm.corecomponentref.RefPhoneSubtypeService', 'findAllRecordsByLanguageCode', 'find All records by language code', '2018-04-16 18:35:32.000000', NULL, '2018-04-16 18:35:32.000000', 'Generator', '000000000'),
	('157', 0, 'createRefPhoneTypeBase', 'com.yugandhar.mdm.corecomponentref.RefPhoneTypeService', 'add', 'find by business key method search properties based on transaction Name', '2018-04-16 18:35:32.000000', NULL, '2018-04-16 18:35:32.000000', 'Generator', '000000000'),
	('158', 0, 'updateRefPhoneTypeBase', 'com.yugandhar.mdm.corecomponentref.RefPhoneTypeService', 'merge', 'find by business key method search properties based on transaction Name', '2018-04-16 18:35:32.000000', NULL, '2018-04-16 18:35:32.000000', 'Generator', '000000000'),
	('159', 0, 'retrieveRefPhoneTypeBase', 'com.yugandhar.mdm.corecomponentref.RefPhoneTypeService', 'findById', 'find by business key method search properties based on transaction Name', '2018-04-16 18:35:32.000000', NULL, '2018-04-16 18:35:32.000000', 'Generator', '000000000'),
	('16', 0, 'retrieveLegalentityBase', 'com.yugandhar.mdm.corecomponent.LegalentityService', 'findById', 'find by business key method search properties based on transaction Name', '2018-04-16 18:35:32.000000', NULL, '2018-04-16 18:35:32.000000', 'Generator', '000000000'),
	('160', 0, 'findRefPhoneTypeByBusinessKeyBase', 'com.yugandhar.mdm.corecomponentref.RefPhoneTypeService', 'findByBusinessKey', 'find by business key method search properties based on transaction Name', '2018-04-16 18:35:32.000000', NULL, '2018-04-16 18:35:32.000000', 'Generator', '000000000'),
	('161', 0, 'findAllRefPhoneTypeByLanguageCodeBase', 'com.yugandhar.mdm.corecomponentref.RefPhoneTypeService', 'findAllRecordsByLanguageCode', 'find All records by language code', '2018-04-16 18:35:32.000000', NULL, '2018-04-16 18:35:32.000000', 'Generator', '000000000'),
	('162', 0, 'createRefPrefixNameBase', 'com.yugandhar.mdm.corecomponentref.RefPrefixNameService', 'add', 'find by business key method search properties based on transaction Name', '2018-04-16 18:35:32.000000', NULL, '2018-04-16 18:35:32.000000', 'Generator', '000000000'),
	('163', 0, 'updateRefPrefixNameBase', 'com.yugandhar.mdm.corecomponentref.RefPrefixNameService', 'merge', 'find by business key method search properties based on transaction Name', '2018-04-16 18:35:32.000000', NULL, '2018-04-16 18:35:32.000000', 'Generator', '000000000'),
	('164', 0, 'retrieveRefPrefixNameBase', 'com.yugandhar.mdm.corecomponentref.RefPrefixNameService', 'findById', 'find by business key method search properties based on transaction Name', '2018-04-16 18:35:32.000000', NULL, '2018-04-16 18:35:32.000000', 'Generator', '000000000'),
	('165', 0, 'findRefPrefixNameByBusinessKeyBase', 'com.yugandhar.mdm.corecomponentref.RefPrefixNameService', 'findByBusinessKey', 'find by business key method search properties based on transaction Name', '2018-04-16 18:35:32.000000', NULL, '2018-04-16 18:35:32.000000', 'Generator', '000000000'),
	('166', 0, 'findAllRefPrefixNameByLanguageCodeBase', 'com.yugandhar.mdm.corecomponentref.RefPrefixNameService', 'findAllRecordsByLanguageCode', 'find All records by language code', '2018-04-16 18:35:32.000000', NULL, '2018-04-16 18:35:32.000000', 'Generator', '000000000'),
	('17', 0, 'createRefEntityObjectTypeBase', 'com.yugandhar.mdm.corecomponentref.RefEntityObjectTypeService', 'add', 'find by business key method search properties based on transaction Name', '2018-04-16 18:35:32.000000', NULL, '2018-04-16 18:35:32.000000', 'Generator', '000000000'),
	('172', 0, 'createRefPropertyLeReltypeBase', 'com.yugandhar.mdm.corecomponentref.RefPropertyLeReltypeService', 'add', 'find by business key method search properties based on transaction Name', '2018-04-16 18:35:32.000000', NULL, '2018-04-16 18:35:32.000000', 'Generator', '000000000'),
	('173', 0, 'updateRefPropertyLeReltypeBase', 'com.yugandhar.mdm.corecomponentref.RefPropertyLeReltypeService', 'merge', 'find by business key method search properties based on transaction Name', '2018-04-16 18:35:32.000000', NULL, '2018-04-16 18:35:32.000000', 'Generator', '000000000'),
	('174', 0, 'retrieveRefPropertyLeReltypeBase', 'com.yugandhar.mdm.corecomponentref.RefPropertyLeReltypeService', 'findById', 'find by business key method search properties based on transaction Name', '2018-04-16 18:35:32.000000', NULL, '2018-04-16 18:35:32.000000', 'Generator', '000000000'),
	('175', 0, 'findRefPropertyLeReltypeByBusinessKeyBase', 'com.yugandhar.mdm.corecomponentref.RefPropertyLeReltypeService', 'findByBusinessKey', 'find by business key method search properties based on transaction Name', '2018-04-16 18:35:32.000000', NULL, '2018-04-16 18:35:32.000000', 'Generator', '000000000'),
	('176', 0, 'findAllRefPropertyLeReltypeByLanguageCodeBase', 'com.yugandhar.mdm.corecomponentref.RefPropertyLeReltypeService', 'findAllRecordsByLanguageCode', 'find All records by language code', '2018-04-16 18:35:32.000000', NULL, '2018-04-16 18:35:32.000000', 'Generator', '000000000'),
	('177', 0, 'createRefRelationshipStatusBase', 'com.yugandhar.mdm.corecomponentref.RefRelationshipStatusService', 'add', 'find by business key method search properties based on transaction Name', '2018-04-16 18:35:32.000000', NULL, '2018-04-16 18:35:32.000000', 'Generator', '000000000'),
	('178', 0, 'updateRefRelationshipStatusBase', 'com.yugandhar.mdm.corecomponentref.RefRelationshipStatusService', 'merge', 'find by business key method search properties based on transaction Name', '2018-04-16 18:35:32.000000', NULL, '2018-04-16 18:35:32.000000', 'Generator', '000000000'),
	('179', 0, 'retrieveRefRelationshipStatusBase', 'com.yugandhar.mdm.corecomponentref.RefRelationshipStatusService', 'findById', 'find by business key method search properties based on transaction Name', '2018-04-16 18:35:32.000000', NULL, '2018-04-16 18:35:32.000000', 'Generator', '000000000'),
	('18', 0, 'updateRefEntityObjectTypeBase', 'com.yugandhar.mdm.corecomponentref.RefEntityObjectTypeService', 'merge', 'find by business key method search properties based on transaction Name', '2018-04-16 18:35:32.000000', NULL, '2018-04-16 18:35:32.000000', 'Generator', '000000000'),
	('180', 0, 'findRefRelationshipStatusByBusinessKeyBase', 'com.yugandhar.mdm.corecomponentref.RefRelationshipStatusService', 'findByBusinessKey', 'find by business key method search properties based on transaction Name', '2018-04-16 18:35:32.000000', NULL, '2018-04-16 18:35:32.000000', 'Generator', '000000000'),
	('181', 0, 'findAllRefRelationshipStatusByLanguageCodeBase', 'com.yugandhar.mdm.corecomponentref.RefRelationshipStatusService', 'findAllRecordsByLanguageCode', 'find All records by language code', '2018-04-16 18:35:32.000000', NULL, '2018-04-16 18:35:32.000000', 'Generator', '000000000'),
	('182', 0, 'createRefSourceSystemBase', 'com.yugandhar.mdm.corecomponentref.RefSourceSystemService', 'add', 'find by business key method search properties based on transaction Name', '2018-04-16 18:35:32.000000', NULL, '2018-04-16 18:35:32.000000', 'Generator', '000000000'),
	('183', 0, 'updateRefSourceSystemBase', 'com.yugandhar.mdm.corecomponentref.RefSourceSystemService', 'merge', 'find by business key method search properties based on transaction Name', '2018-04-16 18:35:34.000000', NULL, '2018-04-16 18:35:34.000000', 'Generator', '000000000'),
	('184', 0, 'retrieveRefSourceSystemBase', 'com.yugandhar.mdm.corecomponentref.RefSourceSystemService', 'findById', 'find by business key method search properties based on transaction Name', '2018-04-16 18:35:34.000000', NULL, '2018-04-16 18:35:34.000000', 'Generator', '000000000'),
	('185', 0, 'findRefSourceSystemByBusinessKeyBase', 'com.yugandhar.mdm.corecomponentref.RefSourceSystemService', 'findByBusinessKey', 'find by business key method search properties based on transaction Name', '2018-04-16 18:35:34.000000', NULL, '2018-04-16 18:35:34.000000', 'Generator', '000000000'),
	('186', 0, 'findAllRefSourceSystemByLanguageCodeBase', 'com.yugandhar.mdm.corecomponentref.RefSourceSystemService', 'findAllRecordsByLanguageCode', 'find All records by language code', '2018-04-16 18:35:34.000000', NULL, '2018-04-16 18:35:34.000000', 'Generator', '000000000'),
	('187', 0, 'createRefStateProvinceBase', 'com.yugandhar.mdm.corecomponentref.RefStateProvinceService', 'add', 'find by business key method search properties based on transaction Name', '2018-04-16 18:35:34.000000', NULL, '2018-04-16 18:35:34.000000', 'Generator', '000000000'),
	('188', 0, 'updateRefStateProvinceBase', 'com.yugandhar.mdm.corecomponentref.RefStateProvinceService', 'merge', 'find by business key method search properties based on transaction Name', '2018-04-16 18:35:34.000000', NULL, '2018-04-16 18:35:34.000000', 'Generator', '000000000'),
	('189', 0, 'retrieveRefStateProvinceBase', 'com.yugandhar.mdm.corecomponentref.RefStateProvinceService', 'findById', 'find by business key method search properties based on transaction Name', '2018-04-16 18:35:34.000000', NULL, '2018-04-16 18:35:34.000000', 'Generator', '000000000'),
	('19', 0, 'retrieveRefEntityObjectTypeBase', 'com.yugandhar.mdm.corecomponentref.RefEntityObjectTypeService', 'findById', 'find by business key method search properties based on transaction Name', '2018-04-16 18:35:32.000000', NULL, '2018-04-16 18:35:32.000000', 'Generator', '000000000'),
	('190', 0, 'findRefStateProvinceByBusinessKeyBase', 'com.yugandhar.mdm.corecomponentref.RefStateProvinceService', 'findByBusinessKey', 'find by business key method search properties based on transaction Name', '2018-04-16 18:35:34.000000', NULL, '2018-04-16 18:35:34.000000', 'Generator', '000000000'),
	('191', 0, 'findAllRefStateProvinceByLanguageCodeBase', 'com.yugandhar.mdm.corecomponentref.RefStateProvinceService', 'findAllRecordsByLanguageCode', 'find All records by language code', '2018-04-16 18:35:34.000000', NULL, '2018-04-16 18:35:34.000000', 'Generator', '000000000'),
	('192', 0, 'createRefStatusInSourceBase', 'com.yugandhar.mdm.corecomponentref.RefStatusInSourceService', 'add', 'find by business key method search properties based on transaction Name', '2018-04-16 18:35:34.000000', NULL, '2018-04-16 18:35:34.000000', 'Generator', '000000000'),
	('193', 0, 'updateRefStatusInSourceBase', 'com.yugandhar.mdm.corecomponentref.RefStatusInSourceService', 'merge', 'find by business key method search properties based on transaction Name', '2018-04-16 18:35:34.000000', NULL, '2018-04-16 18:35:34.000000', 'Generator', '000000000'),
	('194', 0, 'retrieveRefStatusInSourceBase', 'com.yugandhar.mdm.corecomponentref.RefStatusInSourceService', 'findById', 'find by business key method search properties based on transaction Name', '2018-04-16 18:35:35.000000', NULL, '2018-04-16 18:35:35.000000', 'Generator', '000000000'),
	('195', 0, 'findRefStatusInSourceByBusinessKeyBase', 'com.yugandhar.mdm.corecomponentref.RefStatusInSourceService', 'findByBusinessKey', 'find by business key method search properties based on transaction Name', '2018-04-16 18:35:35.000000', NULL, '2018-04-16 18:35:35.000000', 'Generator', '000000000'),
	('196', 0, 'findAllRefStatusInSourceByLanguageCodeBase', 'com.yugandhar.mdm.corecomponentref.RefStatusInSourceService', 'findAllRecordsByLanguageCode', 'find All records by language code', '2018-04-16 18:35:35.000000', NULL, '2018-04-16 18:35:35.000000', 'Generator', '000000000'),
	('197', 0, 'createRefStatusTypeBase', 'com.yugandhar.mdm.corecomponentref.RefStatusTypeService', 'add', 'find by business key method search properties based on transaction Name', '2018-04-16 18:35:35.000000', NULL, '2018-04-16 18:35:35.000000', 'Generator', '000000000'),
	('198', 0, 'updateRefStatusTypeBase', 'com.yugandhar.mdm.corecomponentref.RefStatusTypeService', 'merge', 'find by business key method search properties based on transaction Name', '2018-04-16 18:35:35.000000', NULL, '2018-04-16 18:35:35.000000', 'Generator', '000000000'),
	('199', 0, 'retrieveRefStatusTypeBase', 'com.yugandhar.mdm.corecomponentref.RefStatusTypeService', 'findById', 'find by business key method search properties based on transaction Name', '2018-04-16 18:35:35.000000', NULL, '2018-04-16 18:35:35.000000', 'Generator', '000000000'),
	('2', 0, 'updateConfigTxnRegistryBase', 'com.yugandhar.mdm.config.txnregistry.ConfigTxnRegistryService', 'merge', 'find by business key method search properties based on transaction Name', '2018-04-16 18:35:32.000000', NULL, '2018-04-16 18:35:32.000000', 'Generator', '000000000'),
	('20', 0, 'findRefEntityObjectTypeByBusinessKeyBase', 'com.yugandhar.mdm.corecomponentref.RefEntityObjectTypeService', 'findByBusinessKey', 'find by business key method search properties based on transaction Name', '2018-04-16 18:35:32.000000', NULL, '2018-04-16 18:35:32.000000', 'Generator', '000000000'),
	('200', 0, 'findRefStatusTypeByBusinessKeyBase', 'com.yugandhar.mdm.corecomponentref.RefStatusTypeService', 'findByBusinessKey', 'find by business key method search properties based on transaction Name', '2018-04-16 18:35:35.000000', NULL, '2018-04-16 18:35:35.000000', 'Generator', '000000000'),
	('201', 0, 'findAllRefStatusTypeByLanguageCodeBase', 'com.yugandhar.mdm.corecomponentref.RefStatusTypeService', 'findAllRecordsByLanguageCode', 'find All records by language code', '2018-04-16 18:35:35.000000', NULL, '2018-04-16 18:35:35.000000', 'Generator', '000000000'),
	('202', 0, 'createRefSuffixNameBase', 'com.yugandhar.mdm.corecomponentref.RefSuffixNameService', 'add', 'find by business key method search properties based on transaction Name', '2018-04-16 18:35:35.000000', NULL, '2018-04-16 18:35:35.000000', 'Generator', '000000000'),
	('203', 0, 'updateRefSuffixNameBase', 'com.yugandhar.mdm.corecomponentref.RefSuffixNameService', 'merge', 'find by business key method search properties based on transaction Name', '2018-04-16 18:35:35.000000', NULL, '2018-04-16 18:35:35.000000', 'Generator', '000000000'),
	('204', 0, 'retrieveRefSuffixNameBase', 'com.yugandhar.mdm.corecomponentref.RefSuffixNameService', 'findById', 'find by business key method search properties based on transaction Name', '2018-04-16 18:35:35.000000', NULL, '2018-04-16 18:35:35.000000', 'Generator', '000000000'),
	('205', 0, 'findRefSuffixNameByBusinessKeyBase', 'com.yugandhar.mdm.corecomponentref.RefSuffixNameService', 'findByBusinessKey', 'find by business key method search properties based on transaction Name', '2018-04-16 18:35:35.000000', NULL, '2018-04-16 18:35:35.000000', 'Generator', '000000000'),
	('206', 0, 'findAllRefSuffixNameByLanguageCodeBase', 'com.yugandhar.mdm.corecomponentref.RefSuffixNameService', 'findAllRecordsByLanguageCode', 'find All records by language code', '2018-04-16 18:35:35.000000', NULL, '2018-04-16 18:35:35.000000', 'Generator', '000000000'),
	('207', 0, 'createRefTerminationReasonBase', 'com.yugandhar.mdm.corecomponentref.RefTerminationReasonService', 'add', 'find by business key method search properties based on transaction Name', '2018-04-16 18:35:35.000000', NULL, '2018-04-16 18:35:35.000000', 'Generator', '000000000'),
	('208', 0, 'updateRefTerminationReasonBase', 'com.yugandhar.mdm.corecomponentref.RefTerminationReasonService', 'merge', 'find by business key method search properties based on transaction Name', '2018-04-16 18:35:35.000000', NULL, '2018-04-16 18:35:35.000000', 'Generator', '000000000'),
	('209', 0, 'retrieveRefTerminationReasonBase', 'com.yugandhar.mdm.corecomponentref.RefTerminationReasonService', 'findById', 'find by business key method search properties based on transaction Name', '2018-04-16 18:35:35.000000', NULL, '2018-04-16 18:35:35.000000', 'Generator', '000000000'),
	('21', 0, 'findAllRefEntityObjectTypeByLanguageCodeBase', 'com.yugandhar.mdm.corecomponentref.RefEntityObjectTypeService', 'findAllRecordsByLanguageCode', 'find All records by language code', '2018-04-16 18:35:32.000000', NULL, '2018-04-16 18:35:32.000000', 'Generator', '000000000'),
	('210', 0, 'findRefTerminationReasonByBusinessKeyBase', 'com.yugandhar.mdm.corecomponentref.RefTerminationReasonService', 'findByBusinessKey', 'find by business key method search properties based on transaction Name', '2018-04-16 18:35:35.000000', NULL, '2018-04-16 18:35:35.000000', 'Generator', '000000000'),
	('211', 0, 'findAllRefTerminationReasonByLanguageCodeBase', 'com.yugandhar.mdm.corecomponentref.RefTerminationReasonService', 'findAllRecordsByLanguageCode', 'find All records by language code', '2018-04-16 18:35:35.000000', NULL, '2018-04-16 18:35:35.000000', 'Generator', '000000000'),
	('212', 0, 'createRefAccountMdmStatusBase', 'com.yugandhar.mdm.corecomponentref.RefAccountMdmStatusService', 'add', 'find by business key method search properties based on transaction Name', '2018-04-16 18:35:35.000000', NULL, '2018-04-16 18:35:35.000000', 'Generator', '000000000'),
	('213', 0, 'updateRefAccountMdmStatusBase', 'com.yugandhar.mdm.corecomponentref.RefAccountMdmStatusService', 'merge', 'find by business key method search properties based on transaction Name', '2018-04-16 18:35:35.000000', NULL, '2018-04-16 18:35:35.000000', 'Generator', '000000000'),
	('214', 0, 'retrieveRefAccountMdmStatusBase', 'com.yugandhar.mdm.corecomponentref.RefAccountMdmStatusService', 'findById', 'find by business key method search properties based on transaction Name', '2018-04-16 18:35:36.000000', NULL, '2018-04-16 18:35:36.000000', 'Generator', '000000000'),
	('215', 0, 'findRefAccountMdmStatusByBusinessKeyBase', 'com.yugandhar.mdm.corecomponentref.RefAccountMdmStatusService', 'findByBusinessKey', 'find by business key method search properties based on transaction Name', '2018-04-16 18:35:35.000000', NULL, '2018-04-16 18:35:35.000000', 'Generator', '000000000'),
	('216', 0, 'findAllRefAccountMdmStatusByLanguageCodeBase', 'com.yugandhar.mdm.corecomponentref.RefAccountMdmStatusService', 'findAllRecordsByLanguageCode', 'find All records by language code', '2018-04-16 18:35:35.000000', NULL, '2018-04-16 18:35:35.000000', 'Generator', '000000000'),
	('217', 0, 'createRefAccountSourceStatusBase', 'com.yugandhar.mdm.corecomponentref.RefAccountSourceStatusService', 'add', 'find by business key method search properties based on transaction Name', '2018-04-16 18:35:35.000000', NULL, '2018-04-16 18:35:35.000000', 'Generator', '000000000'),
	('218', 0, 'updateRefAccountSourceStatusBase', 'com.yugandhar.mdm.corecomponentref.RefAccountSourceStatusService', 'merge', 'find by business key method search properties based on transaction Name', '2018-04-16 18:35:35.000000', NULL, '2018-04-16 18:35:35.000000', 'Generator', '000000000'),
	('219', 0, 'retrieveRefAccountSourceStatusBase', 'com.yugandhar.mdm.corecomponentref.RefAccountSourceStatusService', 'findById', 'find by business key method search properties based on transaction Name', '2018-04-16 18:35:35.000000', NULL, '2018-04-16 18:35:35.000000', 'Generator', '000000000'),
	('220', 0, 'findRefAccountSourceStatusByBusinessKeyBase', 'com.yugandhar.mdm.corecomponentref.RefAccountSourceStatusService', 'findByBusinessKey', 'find by business key method search properties based on transaction Name', '2018-04-16 18:35:35.000000', NULL, '2018-04-16 18:35:35.000000', 'Generator', '000000000'),
	('221', 0, 'findAllRefAccountSourceStatusByLanguageCodeBase', 'com.yugandhar.mdm.corecomponentref.RefAccountSourceStatusService', 'findAllRecordsByLanguageCode', 'find All records by language code', '2018-04-16 18:35:35.000000', NULL, '2018-04-16 18:35:35.000000', 'Generator', '000000000'),
	('222', 0, 'createRefAddressSubtypeBase', 'com.yugandhar.mdm.corecomponentref.RefAddressSubtypeService', 'add', 'find by business key method search properties based on transaction Name', '2018-04-16 18:35:35.000000', NULL, '2018-04-16 18:35:35.000000', 'Generator', '000000000'),
	('223', 0, 'updateRefAddressSubtypeBase', 'com.yugandhar.mdm.corecomponentref.RefAddressSubtypeService', 'merge', 'find by business key method search properties based on transaction Name', '2018-04-16 18:35:35.000000', NULL, '2018-04-16 18:35:35.000000', 'Generator', '000000000'),
	('224', 0, 'retrieveRefAddressSubtypeBase', 'com.yugandhar.mdm.corecomponentref.RefAddressSubtypeService', 'findById', 'find by business key method search properties based on transaction Name', '2018-04-16 18:35:35.000000', NULL, '2018-04-16 18:35:35.000000', 'Generator', '000000000'),
	('225', 0, 'findRefAddressSubtypeByBusinessKeyBase', 'com.yugandhar.mdm.corecomponentref.RefAddressSubtypeService', 'findByBusinessKey', 'find by business key method search properties based on transaction Name', '2018-04-16 18:35:35.000000', NULL, '2018-04-16 18:35:35.000000', 'Generator', '000000000'),
	('226', 0, 'findAllRefAddressSubtypeByLanguageCodeBase', 'com.yugandhar.mdm.corecomponentref.RefAddressSubtypeService', 'findAllRecordsByLanguageCode', 'find All records by language code', '2018-04-16 18:35:35.000000', NULL, '2018-04-16 18:35:35.000000', 'Generator', '000000000'),
	('227', 0, 'createRefAddressTypeBase', 'com.yugandhar.mdm.corecomponentref.RefAddressTypeService', 'add', 'find by business key method search properties based on transaction Name', '2018-04-16 18:35:35.000000', NULL, '2018-04-16 18:35:35.000000', 'Generator', '000000000'),
	('228', 0, 'updateRefAddressTypeBase', 'com.yugandhar.mdm.corecomponentref.RefAddressTypeService', 'merge', 'find by business key method search properties based on transaction Name', '2018-04-16 18:35:35.000000', NULL, '2018-04-16 18:35:35.000000', 'Generator', '000000000'),
	('229', 0, 'retrieveRefAddressTypeBase', 'com.yugandhar.mdm.corecomponentref.RefAddressTypeService', 'findById', 'find by business key method search properties based on transaction Name', '2018-04-16 18:35:35.000000', NULL, '2018-04-16 18:35:35.000000', 'Generator', '000000000'),
	('230', 0, 'findRefAddressTypeByBusinessKeyBase', 'com.yugandhar.mdm.corecomponentref.RefAddressTypeService', 'findByBusinessKey', 'find by business key method search properties based on transaction Name', '2018-04-16 18:35:35.000000', NULL, '2018-04-16 18:35:35.000000', 'Generator', '000000000'),
	('231', 0, 'findAllRefAddressTypeByLanguageCodeBase', 'com.yugandhar.mdm.corecomponentref.RefAddressTypeService', 'findAllRecordsByLanguageCode', 'find All records by language code', '2018-04-16 18:35:35.000000', NULL, '2018-04-16 18:35:35.000000', 'Generator', '000000000'),
	('232', 0, 'createRefAgreementTypeBase', 'com.yugandhar.mdm.corecomponentref.RefAgreementTypeService', 'add', 'find by business key method search properties based on transaction Name', '2018-04-16 18:35:35.000000', NULL, '2018-04-16 18:35:35.000000', 'Generator', '000000000'),
	('233', 0, 'updateRefAgreementTypeBase', 'com.yugandhar.mdm.corecomponentref.RefAgreementTypeService', 'merge', 'find by business key method search properties based on transaction Name', '2018-04-16 18:35:35.000000', NULL, '2018-04-16 18:35:35.000000', 'Generator', '000000000'),
	('234', 0, 'retrieveRefAgreementTypeBase', 'com.yugandhar.mdm.corecomponentref.RefAgreementTypeService', 'findById', 'find by business key method search properties based on transaction Name', '2018-04-16 18:35:35.000000', NULL, '2018-04-16 18:35:35.000000', 'Generator', '000000000'),
	('235', 0, 'findRefAgreementTypeByBusinessKeyBase', 'com.yugandhar.mdm.corecomponentref.RefAgreementTypeService', 'findByBusinessKey', 'find by business key method search properties based on transaction Name', '2018-04-16 18:35:35.000000', NULL, '2018-04-16 18:35:35.000000', 'Generator', '000000000'),
	('236', 0, 'findAllRefAgreementTypeByLanguageCodeBase', 'com.yugandhar.mdm.corecomponentref.RefAgreementTypeService', 'findAllRecordsByLanguageCode', 'find All records by language code', '2018-04-16 18:35:35.000000', NULL, '2018-04-16 18:35:35.000000', 'Generator', '000000000'),
	('237', 0, 'createRefAssocTypeBase', 'com.yugandhar.mdm.corecomponentref.RefAssocTypeService', 'add', 'find by business key method search properties based on transaction Name', '2018-04-16 18:35:35.000000', NULL, '2018-04-16 18:35:35.000000', 'Generator', '000000000'),
	('238', 0, 'updateRefAssocTypeBase', 'com.yugandhar.mdm.corecomponentref.RefAssocTypeService', 'merge', 'find by business key method search properties based on transaction Name', '2018-04-16 18:35:35.000000', NULL, '2018-04-16 18:35:35.000000', 'Generator', '000000000'),
	('239', 0, 'retrieveRefAssocTypeBase', 'com.yugandhar.mdm.corecomponentref.RefAssocTypeService', 'findById', 'find by business key method search properties based on transaction Name', '2018-04-16 18:35:35.000000', NULL, '2018-04-16 18:35:35.000000', 'Generator', '000000000'),
	('240', 0, 'findRefAssocTypeByBusinessKeyBase', 'com.yugandhar.mdm.corecomponentref.RefAssocTypeService', 'findByBusinessKey', 'find by business key method search properties based on transaction Name', '2018-04-16 18:35:35.000000', NULL, '2018-04-16 18:35:35.000000', 'Generator', '000000000'),
	('241', 0, 'findAllRefAssocTypeByLanguageCodeBase', 'com.yugandhar.mdm.corecomponentref.RefAssocTypeService', 'findAllRecordsByLanguageCode', 'find All records by language code', '2018-04-16 18:35:35.000000', NULL, '2018-04-16 18:35:35.000000', 'Generator', '000000000'),
	('242', 0, 'createRefBillingModeTypeBase', 'com.yugandhar.mdm.corecomponentref.RefBillingModeTypeService', 'add', 'find by business key method search properties based on transaction Name', '2018-04-16 18:35:35.000000', NULL, '2018-04-16 18:35:35.000000', 'Generator', '000000000'),
	('243', 0, 'updateRefBillingModeTypeBase', 'com.yugandhar.mdm.corecomponentref.RefBillingModeTypeService', 'merge', 'find by business key method search properties based on transaction Name', '2018-04-16 18:35:35.000000', NULL, '2018-04-16 18:35:35.000000', 'Generator', '000000000'),
	('244', 0, 'retrieveRefBillingModeTypeBase', 'com.yugandhar.mdm.corecomponentref.RefBillingModeTypeService', 'findById', 'find by business key method search properties based on transaction Name', '2018-04-16 18:35:35.000000', NULL, '2018-04-16 18:35:35.000000', 'Generator', '000000000'),
	('245', 0, 'findRefBillingModeTypeByBusinessKeyBase', 'com.yugandhar.mdm.corecomponentref.RefBillingModeTypeService', 'findByBusinessKey', 'find by business key method search properties based on transaction Name', '2018-04-16 18:35:35.000000', NULL, '2018-04-16 18:35:35.000000', 'Generator', '000000000'),
	('246', 0, 'findAllRefBillingModeTypeByLanguageCodeBase', 'com.yugandhar.mdm.corecomponentref.RefBillingModeTypeService', 'findAllRecordsByLanguageCode', 'find All records by language code', '2018-04-16 18:35:35.000000', NULL, '2018-04-16 18:35:35.000000', 'Generator', '000000000'),
	('247', 0, 'createRefBranchCodeBase', 'com.yugandhar.mdm.corecomponentref.RefBranchCodeService', 'add', 'find by business key method search properties based on transaction Name', '2018-04-16 18:35:35.000000', NULL, '2018-04-16 18:35:35.000000', 'Generator', '000000000'),
	('248', 0, 'updateRefBranchCodeBase', 'com.yugandhar.mdm.corecomponentref.RefBranchCodeService', 'merge', 'find by business key method search properties based on transaction Name', '2018-04-16 18:35:33.000000', NULL, '2018-04-16 18:35:33.000000', 'Generator', '000000000'),
	('249', 0, 'retrieveRefBranchCodeBase', 'com.yugandhar.mdm.corecomponentref.RefBranchCodeService', 'findById', 'find by business key method search properties based on transaction Name', '2018-04-16 18:35:33.000000', NULL, '2018-04-16 18:35:33.000000', 'Generator', '000000000'),
	('250', 0, 'findRefBranchCodeByBusinessKeyBase', 'com.yugandhar.mdm.corecomponentref.RefBranchCodeService', 'findByBusinessKey', 'find by business key method search properties based on transaction Name', '2018-04-16 18:35:33.000000', NULL, '2018-04-16 18:35:33.000000', 'Generator', '000000000'),
	('251', 0, 'findAllRefBranchCodeByLanguageCodeBase', 'com.yugandhar.mdm.corecomponentref.RefBranchCodeService', 'findAllRecordsByLanguageCode', 'find All records by language code', '2018-04-16 18:35:33.000000', NULL, '2018-04-16 18:35:33.000000', 'Generator', '000000000'),
	('252', 0, 'createRefClassificationCodeBase', 'com.yugandhar.mdm.corecomponentref.RefClassificationCodeService', 'add', 'find by business key method search properties based on transaction Name', '2018-04-16 18:35:33.000000', NULL, '2018-04-16 18:35:33.000000', 'Generator', '000000000'),
	('253', 0, 'updateRefClassificationCodeBase', 'com.yugandhar.mdm.corecomponentref.RefClassificationCodeService', 'merge', 'find by business key method search properties based on transaction Name', '2018-04-16 18:35:33.000000', NULL, '2018-04-16 18:35:33.000000', 'Generator', '000000000'),
	('254', 0, 'retrieveRefClassificationCodeBase', 'com.yugandhar.mdm.corecomponentref.RefClassificationCodeService', 'findById', 'find by business key method search properties based on transaction Name', '2018-04-16 18:35:33.000000', NULL, '2018-04-16 18:35:33.000000', 'Generator', '000000000'),
	('255', 0, 'findRefClassificationCodeByBusinessKeyBase', 'com.yugandhar.mdm.corecomponentref.RefClassificationCodeService', 'findByBusinessKey', 'find by business key method search properties based on transaction Name', '2018-04-16 18:35:33.000000', NULL, '2018-04-16 18:35:33.000000', 'Generator', '000000000'),
	('256', 0, 'findAllRefClassificationCodeByLanguageCodeBase', 'com.yugandhar.mdm.corecomponentref.RefClassificationCodeService', 'findAllRecordsByLanguageCode', 'find All records by language code', '2018-04-16 18:35:33.000000', NULL, '2018-04-16 18:35:33.000000', 'Generator', '000000000'),
	('257', 0, 'createRefCorporationNameTypeBase', 'com.yugandhar.mdm.corecomponentref.RefCorporationNameTypeService', 'add', 'find by business key method search properties based on transaction Name', '2018-04-16 18:35:33.000000', NULL, '2018-04-16 18:35:33.000000', 'Generator', '000000000'),
	('258', 0, 'updateRefCorporationNameTypeBase', 'com.yugandhar.mdm.corecomponentref.RefCorporationNameTypeService', 'merge', 'find by business key method search properties based on transaction Name', '2018-04-16 18:35:33.000000', NULL, '2018-04-16 18:35:33.000000', 'Generator', '000000000'),
	('259', 0, 'retrieveRefCorporationNameTypeBase', 'com.yugandhar.mdm.corecomponentref.RefCorporationNameTypeService', 'findById', 'find by business key method search properties based on transaction Name', '2018-04-16 18:35:33.000000', NULL, '2018-04-16 18:35:33.000000', 'Generator', '000000000'),
	('260', 0, 'findRefCorporationNameTypeByBusinessKeyBase', 'com.yugandhar.mdm.corecomponentref.RefCorporationNameTypeService', 'findByBusinessKey', 'find by business key method search properties based on transaction Name', '2018-04-16 18:35:33.000000', NULL, '2018-04-16 18:35:33.000000', 'Generator', '000000000'),
	('261', 0, 'findAllRefCorporationNameTypeByLanguageCodeBase', 'com.yugandhar.mdm.corecomponentref.RefCorporationNameTypeService', 'findAllRecordsByLanguageCode', 'find All records by language code', '2018-04-16 18:35:33.000000', NULL, '2018-04-16 18:35:33.000000', 'Generator', '000000000'),
	('262', 0, 'createRefCorporationTypeBase', 'com.yugandhar.mdm.corecomponentref.RefCorporationTypeService', 'add', 'find by business key method search properties based on transaction Name', '2018-04-16 18:35:33.000000', NULL, '2018-04-16 18:35:33.000000', 'Generator', '000000000'),
	('263', 0, 'updateRefCorporationTypeBase', 'com.yugandhar.mdm.corecomponentref.RefCorporationTypeService', 'merge', 'find by business key method search properties based on transaction Name', '2018-04-16 18:35:33.000000', NULL, '2018-04-16 18:35:33.000000', 'Generator', '000000000'),
	('264', 0, 'retrieveRefCorporationTypeBase', 'com.yugandhar.mdm.corecomponentref.RefCorporationTypeService', 'findById', 'find by business key method search properties based on transaction Name', '2018-04-16 18:35:33.000000', NULL, '2018-04-16 18:35:33.000000', 'Generator', '000000000'),
	('265', 0, 'findRefCorporationTypeByBusinessKeyBase', 'com.yugandhar.mdm.corecomponentref.RefCorporationTypeService', 'findByBusinessKey', 'find by business key method search properties based on transaction Name', '2018-04-16 18:35:33.000000', NULL, '2018-04-16 18:35:33.000000', 'Generator', '000000000'),
	('266', 0, 'findAllRefCorporationTypeByLanguageCodeBase', 'com.yugandhar.mdm.corecomponentref.RefCorporationTypeService', 'findAllRecordsByLanguageCode', 'find All records by language code', '2018-04-16 18:35:33.000000', NULL, '2018-04-16 18:35:33.000000', 'Generator', '000000000'),
	('267', 0, 'createRefCountryIsoBase', 'com.yugandhar.mdm.corecomponentref.RefCountryIsoService', 'add', 'find by business key method search properties based on transaction Name', '2018-04-16 18:35:33.000000', NULL, '2018-04-16 18:35:33.000000', 'Generator', '000000000'),
	('268', 0, 'updateRefCountryIsoBase', 'com.yugandhar.mdm.corecomponentref.RefCountryIsoService', 'merge', 'find by business key method search properties based on transaction Name', '2018-04-16 18:35:33.000000', NULL, '2018-04-16 18:35:33.000000', 'Generator', '000000000'),
	('269', 0, 'retrieveRefCountryIsoBase', 'com.yugandhar.mdm.corecomponentref.RefCountryIsoService', 'findById', 'find by business key method search properties based on transaction Name', '2018-04-16 18:35:33.000000', NULL, '2018-04-16 18:35:33.000000', 'Generator', '000000000'),
	('270', 0, 'findRefCountryIsoByBusinessKeyBase', 'com.yugandhar.mdm.corecomponentref.RefCountryIsoService', 'findByBusinessKey', 'find by business key method search properties based on transaction Name', '2018-04-16 18:35:33.000000', NULL, '2018-04-16 18:35:33.000000', 'Generator', '000000000'),
	('271', 0, 'findAllRefCountryIsoByLanguageCodeBase', 'com.yugandhar.mdm.corecomponentref.RefCountryIsoService', 'findAllRecordsByLanguageCode', 'find All records by language code', '2018-04-16 18:35:33.000000', NULL, '2018-04-16 18:35:33.000000', 'Generator', '000000000'),
	('272', 0, 'createRefCurrencyBase', 'com.yugandhar.mdm.corecomponentref.RefCurrencyService', 'add', 'find by business key method search properties based on transaction Name', '2018-04-16 18:35:33.000000', NULL, '2018-04-16 18:35:33.000000', 'Generator', '000000000'),
	('273', 0, 'updateRefCurrencyBase', 'com.yugandhar.mdm.corecomponentref.RefCurrencyService', 'merge', 'find by business key method search properties based on transaction Name', '2018-04-16 18:35:33.000000', NULL, '2018-04-16 18:35:33.000000', 'Generator', '000000000'),
	('274', 0, 'retrieveRefCurrencyBase', 'com.yugandhar.mdm.corecomponentref.RefCurrencyService', 'findById', 'find by business key method search properties based on transaction Name', '2018-04-16 18:35:33.000000', NULL, '2018-04-16 18:35:33.000000', 'Generator', '000000000'),
	('275', 0, 'findRefCurrencyByBusinessKeyBase', 'com.yugandhar.mdm.corecomponentref.RefCurrencyService', 'findByBusinessKey', 'find by business key method search properties based on transaction Name', '2018-04-16 18:35:33.000000', NULL, '2018-04-16 18:35:33.000000', 'Generator', '000000000'),
	('276', 0, 'findAllRefCurrencyByLanguageCodeBase', 'com.yugandhar.mdm.corecomponentref.RefCurrencyService', 'findAllRecordsByLanguageCode', 'find All records by language code', '2018-04-16 18:35:33.000000', NULL, '2018-04-16 18:35:33.000000', 'Generator', '000000000'),
	('277', 0, 'createRefDeactivationReasonBase', 'com.yugandhar.mdm.corecomponentref.RefDeactivationReasonService', 'add', 'find by business key method search properties based on transaction Name', '2018-04-16 18:35:33.000000', NULL, '2018-04-16 18:35:33.000000', 'Generator', '000000000'),
	('278', 0, 'updateRefDeactivationReasonBase', 'com.yugandhar.mdm.corecomponentref.RefDeactivationReasonService', 'merge', 'find by business key method search properties based on transaction Name', '2018-04-16 18:35:33.000000', NULL, '2018-04-16 18:35:33.000000', 'Generator', '000000000'),
	('279', 0, 'retrieveRefDeactivationReasonBase', 'com.yugandhar.mdm.corecomponentref.RefDeactivationReasonService', 'findById', 'find by business key method search properties based on transaction Name', '2018-04-16 18:35:33.000000', NULL, '2018-04-16 18:35:33.000000', 'Generator', '000000000'),
	('280', 0, 'findRefDeactivationReasonByBusinessKeyBase', 'com.yugandhar.mdm.corecomponentref.RefDeactivationReasonService', 'findByBusinessKey', 'find by business key method search properties based on transaction Name', '2018-04-16 18:35:33.000000', NULL, '2018-04-16 18:35:33.000000', 'Generator', '000000000'),
	('281', 0, 'findAllRefDeactivationReasonByLanguageCodeBase', 'com.yugandhar.mdm.corecomponentref.RefDeactivationReasonService', 'findAllRecordsByLanguageCode', 'find All records by language code', '2018-04-16 18:35:33.000000', NULL, '2018-04-16 18:35:33.000000', 'Generator', '000000000'),
	('282', 0, 'createRefGenderBase', 'com.yugandhar.mdm.corecomponentref.RefGenderService', 'add', 'find by business key method search properties based on transaction Name', '2018-04-16 18:35:33.000000', NULL, '2018-04-16 18:35:33.000000', 'Generator', '000000000'),
	('283', 0, 'updateRefGenderBase', 'com.yugandhar.mdm.corecomponentref.RefGenderService', 'merge', 'find by business key method search properties based on transaction Name', '2018-04-16 18:35:34.000000', NULL, '2018-04-16 18:35:34.000000', 'Generator', '000000000'),
	('284', 0, 'retrieveRefGenderBase', 'com.yugandhar.mdm.corecomponentref.RefGenderService', 'findById', 'find by business key method search properties based on transaction Name', '2018-04-16 18:35:34.000000', NULL, '2018-04-16 18:35:34.000000', 'Generator', '000000000'),
	('285', 0, 'findRefGenderByBusinessKeyBase', 'com.yugandhar.mdm.corecomponentref.RefGenderService', 'findByBusinessKey', 'find by business key method search properties based on transaction Name', '2018-04-16 18:35:34.000000', NULL, '2018-04-16 18:35:34.000000', 'Generator', '000000000'),
	('286', 0, 'findAllRefGenderByLanguageCodeBase', 'com.yugandhar.mdm.corecomponentref.RefGenderService', 'findAllRecordsByLanguageCode', 'find All records by language code', '2018-04-16 18:35:34.000000', NULL, '2018-04-16 18:35:34.000000', 'Generator', '000000000'),
	('287', 0, 'createRefGroupSubtypeBase', 'com.yugandhar.mdm.corecomponentref.RefGroupSubtypeService', 'add', 'find by business key method search properties based on transaction Name', '2018-04-16 18:35:34.000000', NULL, '2018-04-16 18:35:34.000000', 'Generator', '000000000'),
	('288', 0, 'updateRefGroupSubtypeBase', 'com.yugandhar.mdm.corecomponentref.RefGroupSubtypeService', 'merge', 'find by business key method search properties based on transaction Name', '2018-04-16 18:35:34.000000', NULL, '2018-04-16 18:35:34.000000', 'Generator', '000000000'),
	('289', 0, 'retrieveRefGroupSubtypeBase', 'com.yugandhar.mdm.corecomponentref.RefGroupSubtypeService', 'findById', 'find by business key method search properties based on transaction Name', '2018-04-16 18:35:34.000000', NULL, '2018-04-16 18:35:34.000000', 'Generator', '000000000'),
	('290', 0, 'findRefGroupSubtypeByBusinessKeyBase', 'com.yugandhar.mdm.corecomponentref.RefGroupSubtypeService', 'findByBusinessKey', 'find by business key method search properties based on transaction Name', '2018-04-16 18:35:34.000000', NULL, '2018-04-16 18:35:34.000000', 'Generator', '000000000'),
	('291', 0, 'findAllRefGroupSubtypeByLanguageCodeBase', 'com.yugandhar.mdm.corecomponentref.RefGroupSubtypeService', 'findAllRecordsByLanguageCode', 'find All records by language code', '2018-04-16 18:35:34.000000', NULL, '2018-04-16 18:35:34.000000', 'Generator', '000000000'),
	('292', 0, 'createRefGroupTypeBase', 'com.yugandhar.mdm.corecomponentref.RefGroupTypeService', 'add', 'find by business key method search properties based on transaction Name', '2018-04-16 18:35:34.000000', NULL, '2018-04-16 18:35:34.000000', 'Generator', '000000000'),
	('293', 0, 'updateRefGroupTypeBase', 'com.yugandhar.mdm.corecomponentref.RefGroupTypeService', 'merge', 'find by business key method search properties based on transaction Name', '2018-04-16 18:35:34.000000', NULL, '2018-04-16 18:35:34.000000', 'Generator', '000000000'),
	('294', 0, 'retrieveRefGroupTypeBase', 'com.yugandhar.mdm.corecomponentref.RefGroupTypeService', 'findById', 'find by business key method search properties based on transaction Name', '2018-04-16 18:35:34.000000', NULL, '2018-04-16 18:35:34.000000', 'Generator', '000000000'),
	('295', 0, 'findRefGroupTypeByBusinessKeyBase', 'com.yugandhar.mdm.corecomponentref.RefGroupTypeService', 'findByBusinessKey', 'find by business key method search properties based on transaction Name', '2018-04-16 18:35:34.000000', NULL, '2018-04-16 18:35:34.000000', 'Generator', '000000000'),
	('296', 0, 'findAllRefGroupTypeByLanguageCodeBase', 'com.yugandhar.mdm.corecomponentref.RefGroupTypeService', 'findAllRecordsByLanguageCode', 'find All records by language code', '2018-04-16 18:35:34.000000', NULL, '2018-04-16 18:35:34.000000', 'Generator', '000000000'),
	('297', 0, 'createRefHighestEduQualBase', 'com.yugandhar.mdm.corecomponentref.RefHighestEduQualService', 'add', 'find by business key method search properties based on transaction Name', '2018-04-16 18:35:34.000000', NULL, '2018-04-16 18:35:34.000000', 'Generator', '000000000'),
	('298', 0, 'updateRefHighestEduQualBase', 'com.yugandhar.mdm.corecomponentref.RefHighestEduQualService', 'merge', 'find by business key method search properties based on transaction Name', '2018-04-16 18:35:34.000000', NULL, '2018-04-16 18:35:34.000000', 'Generator', '000000000'),
	('299', 0, 'retrieveRefHighestEduQualBase', 'com.yugandhar.mdm.corecomponentref.RefHighestEduQualService', 'findById', 'find by business key method search properties based on transaction Name', '2018-04-16 18:35:34.000000', NULL, '2018-04-16 18:35:34.000000', 'Generator', '000000000'),
	('3', 0, 'retrieveConfigTxnRegistryBase', 'com.yugandhar.mdm.config.txnregistry.ConfigTxnRegistryService', 'findById', 'find by business key method search properties based on transaction Name', '2018-04-16 18:35:32.000000', NULL, '2018-04-16 18:35:32.000000', 'Generator', '000000000'),
	('300', 0, 'findRefHighestEduQualByBusinessKeyBase', 'com.yugandhar.mdm.corecomponentref.RefHighestEduQualService', 'findByBusinessKey', 'find by business key method search properties based on transaction Name', '2018-04-16 18:35:34.000000', NULL, '2018-04-16 18:35:34.000000', 'Generator', '000000000'),
	('301', 0, 'findAllRefHighestEduQualByLanguageCodeBase', 'com.yugandhar.mdm.corecomponentref.RefHighestEduQualService', 'findAllRecordsByLanguageCode', 'find All records by language code', '2018-04-16 18:35:34.000000', NULL, '2018-04-16 18:35:34.000000', 'Generator', '000000000'),
	('31', 0, 'createAccountBase', 'com.yugandhar.mdm.corecomponent.AccountService', 'add', 'find by business key method search properties based on transaction Name', '2018-04-16 18:35:32.000000', NULL, '2018-04-16 18:35:32.000000', 'Generator', '000000000'),
	('311', 0, 'createLegalEntity', 'com.yugandhar.mdm.composite.service.CreateLegalEntityService', 'process', 'create Legal Entity composite Service', '2018-04-16 18:35:34.000000', NULL, '2018-04-16 18:35:34.000000', 'Generator', '000000000'),
	('312', 0, 'createLePerson', 'com.yugandhar.mdm.composite.service.CreateLePersonService', 'process', 'create Legal Entity of person type', '2018-04-16 18:35:34.000000', NULL, '2018-04-16 18:35:34.000000', 'Generator', '000000000'),
	('313', 0, 'retrieveLePersonByLegalEntityId', 'com.yugandhar.mdm.composite.service.RetrieveLePersonByLegalEntityIdService', 'process', 'Retrieve Person by Legal Entity Id', '2018-04-16 18:35:34.000000', NULL, '2018-04-16 18:35:34.000000', 'Generator', '000000000'),
	('314', 0, 'retrieveLegalEntityByLegalEntityId', 'com.yugandhar.mdm.composite.service.RetrieveLegalEntityByLegalEntityIdService', 'process', 'Retrieve Legal Entity by Legal Entity Id', '2018-04-16 18:35:34.000000', NULL, '2018-04-16 18:35:34.000000', 'Generator', '000000000'),
	('315', 0, 'findPersonnamesByLegalEntityIdBase', 'com.yugandhar.mdm.corecomponent.PersonnamesService', 'findByLegalEntityIdPk', 'Retrieve Legal Entity by Legal Entity Id', '2018-04-16 18:35:34.000000', NULL, '2018-04-16 18:35:34.000000', 'Generator', '000000000'),
	('316', 0, 'updateLePerson', 'com.yugandhar.mdm.composite.service.UpdateLePersonService', 'process', 'update LE Person by Legal Entity Id', '2018-04-16 18:35:34.000000', NULL, '2018-04-16 18:35:34.000000', 'Generator', '000000000'),
	('317', 0, 'updateLegalEntity', 'com.yugandhar.mdm.composite.service.UpdateLegalEntityService', 'process', 'update LE by Legal Entity Id', '2018-04-16 18:35:34.000000', NULL, '2018-04-16 18:35:34.000000', 'Generator', '000000000'),
	('318', 0, 'findCorporationnamesByLegalEntityIdBase', 'com.yugandhar.mdm.corecomponent.CorporationnamesService', 'findByLegalEntityIdPk', 'find Corporation names By Legal Entity Idpk', '2018-04-16 18:35:34.000000', NULL, '2018-04-16 18:35:34.000000', 'Generator', '000000000'),
	('319', 0, 'updateLeCorporation', 'com.yugandhar.mdm.composite.service.UpdateLeCorporationService', 'process', 'update Corporation type of Legal Entity', '2018-04-16 18:35:34.000000', NULL, '2018-04-16 18:35:34.000000', 'Generator', '000000000'),
	('32', 0, 'updateAccountBase', 'com.yugandhar.mdm.corecomponent.AccountService', 'merge', 'find by business key method search properties based on transaction Name', '2018-04-16 18:35:32.000000', NULL, '2018-04-16 18:35:32.000000', 'Generator', '000000000'),
	('320', 0, 'createLeCorporation', 'com.yugandhar.mdm.composite.service.CreateLeCorporationService', 'process', 'create Corporation type of Legal Entity', '2018-04-16 18:35:34.000000', NULL, '2018-04-16 18:35:34.000000', 'Generator', '000000000'),
	('321', 0, 'retrieveLeCorporationByLegalEntityId', 'com.yugandhar.mdm.composite.service.RetrieveLeCorporationByLegalEntityIdService', 'process', 'Retrieve Corporation type of Legal Entity', '2018-04-16 18:35:34.000000', NULL, '2018-04-16 18:35:34.000000', 'Generator', '000000000'),
	('322', 0, 'createLeAddressAssociation', 'com.yugandhar.mdm.composite.service.CreateLeAddressService', 'process', 'create an address association for Legal Entity', '2018-04-16 18:35:34.000000', NULL, '2018-04-16 18:35:34.000000', 'Generator', '000000000'),
	('323', 0, 'updateLeAddressAssociation', 'com.yugandhar.mdm.composite.service.UpdateLeAddressService', 'process', 'updates an address association for Legal Entity', '2018-04-16 18:35:34.000000', NULL, '2018-04-16 18:35:34.000000', 'Generator', '000000000'),
	('324', 0, 'retrieveLeAddressByLeAddressAssocId', 'com.yugandhar.mdm.composite.service.RetrieveLeAddressByLeAddressAssocIdService', 'process', 'Retrieve LE address association by LeAddressAssocIdPk', '2018-04-16 18:35:34.000000', NULL, '2018-04-16 18:35:34.000000', 'Generator', '000000000'),
	('325', 0, 'findLeAddressByLegalEntityIdPkBase', 'com.yugandhar.mdm.corecomponent.LeAddressAssocService', 'findByLegalEntityIdPk', 'Retrieve LE address association by LegalentityIdPk', '2018-04-16 18:35:34.000000', NULL, '2018-04-16 18:35:34.000000', 'Generator', '000000000'),
	('326', 0, 'findAllLeAddressAssocByLegalEntityId', 'com.yugandhar.mdm.composite.service.FindAllLeAddressByLegalEntityIdService', 'process', 'Find All address association for the LE by LegalentityIdPk', '2018-04-16 18:35:34.000000', NULL, '2018-04-16 18:35:34.000000', 'Generator', '000000000'),
	('327', 0, 'createLePhoneAssociation', 'com.yugandhar.mdm.composite.service.CreateLePhoneService', 'process', 'create LE to phone association also stores standardized phone number if present', '2018-04-16 18:35:34.000000', NULL, '2018-04-16 18:35:34.000000', 'Generator', '000000000'),
	('329', 0, 'retrieveLePhoneByLePhoneAssocId', 'com.yugandhar.mdm.composite.service.RetrieveLePhoneByLePhoneAssocIdService', 'process', 'Retrieve LE to phone association also retrieves standardized phone number if present', '2018-04-16 18:35:34.000000', NULL, '2018-04-16 18:35:34.000000', 'Generator', '000000000'),
	('33', 0, 'retrieveAccountBase', 'com.yugandhar.mdm.corecomponent.AccountService', 'findById', 'find by business key method search properties based on transaction Name', '2018-04-16 18:35:32.000000', NULL, '2018-04-16 18:35:32.000000', 'Generator', '000000000'),
	('330', 0, 'findLePhoneAssocByLegalEntityIdPkBase', 'com.yugandhar.mdm.corecomponent.LePhoneAssocService', 'findByLegalEntityIdPk', 'Find all LE to phone association by legal entity Id', '2018-04-16 18:35:34.000000', NULL, '2018-04-16 18:35:34.000000', 'Generator', '000000000'),
	('331', 0, 'findAllLePhoneAssocByLegalEntityId', 'com.yugandhar.mdm.composite.service.FindAllLePhoneByLegalEntityIdService', 'process', 'Find all LE to phone association by legal entity Id and retrieve the PhoneStandardized DO if present', '2018-04-16 18:35:34.000000', NULL, '2018-04-16 18:35:34.000000', 'Generator', '000000000'),
	('333', 0, 'findLeSystemKeysRegistryByLegalEntityIdPkBase', 'com.yugandhar.mdm.corecomponent.LeSystemKeysRegistryService', 'findByLegalEntityIdPk', 'Retrieve LE to phone association also retrieves standardized phone number if present', '2018-04-16 18:35:34.000000', NULL, '2018-04-16 18:35:34.000000', 'Generator', '000000000'),
	('337', 0, 'findLePreferencesByLegalEntityIdPkBase', 'com.yugandhar.mdm.corecomponent.LePreferencesService', 'findByLegalEntityIdPk', 'Retrieve LE preferences by Legal Entity Id', '2018-04-16 18:35:34.000000', NULL, '2018-04-16 18:35:34.000000', 'Generator', '000000000'),
	('338', 0, 'findLeIdentifierKycRegistryByLegalEntityIdPkBase', 'com.yugandhar.mdm.corecomponent.LeIdentifierKycRegistryService', 'findByLegalEntityIdPk', 'Retrieve LeIdentifierKycRegistry by Legal Entity Id', '2018-04-16 18:35:34.000000', NULL, '2018-04-16 18:35:34.000000', 'Generator', '000000000'),
	('339', 0, 'findLeAccountAssocByLegalEntityIdPkBase', 'com.yugandhar.mdm.corecomponent.LeAccountAssocService', 'findByLegalEntityIdPk', 'find LeAccountAssoc by Legal Entity Id', '2018-04-16 18:35:34.000000', NULL, '2018-04-16 18:35:34.000000', 'Generator', '000000000'),
	('34', 0, 'createAccountAddressAssocBase', 'com.yugandhar.mdm.corecomponent.AccountAddressAssocService', 'add', 'find by business key method search properties based on transaction Name', '2018-04-16 18:35:32.000000', NULL, '2018-04-16 18:35:32.000000', 'Generator', '000000000'),
	('340', 0, 'findLePropertyAssocByLegalEntityIdPkBase', 'com.yugandhar.mdm.corecomponent.LePropertyAssocService', 'findByLegalEntityIdPk', 'find LePropertyAssoc by Legal Entity Id', '2018-04-16 18:35:34.000000', NULL, '2018-04-16 18:35:34.000000', 'Generator', '000000000'),
	('341', 0, 'findLeVehicleAssocByLegalEntityIdPkBase', 'com.yugandhar.mdm.corecomponent.LeVehicleAssocService', 'findByLegalEntityIdPk', 'find LeVehicleAssoc by Legal Entity Id', '2018-04-16 18:35:34.000000', NULL, '2018-04-16 18:35:34.000000', 'Generator', '000000000'),
	('342', 0, 'retrieveLeAccountLeAccountAssocId', 'com.yugandhar.mdm.composite.service.RetrieveLeAccountByLeAccountAssocIdService', 'process', 'retrieve LE account association with AccountDO object', '2018-04-16 18:35:34.000000', NULL, '2018-04-16 18:35:34.000000', 'Generator', '000000000'),
	('343', 0, 'retrieveLePropertyByLePropertyAssocId', 'com.yugandhar.mdm.composite.service.RetrieveLePropertyByLePropertyAssocIdService', 'process', 'retrieve LE property association with PropertyDO object', '2018-04-16 18:35:34.000000', NULL, '2018-04-16 18:35:34.000000', 'Generator', '000000000'),
	('347', 0, 'retrieveLeVehicleLeVehicleAssocId', 'com.yugandhar.mdm.composite.service.RetrieveLeVehicleLeVehicleAssocIdService', 'process', 'retrieve LeVehicle association with VehicleDO object', '2018-04-16 18:35:34.000000', NULL, '2018-04-16 18:35:34.000000', 'Generator', '000000000'),
	('35', 0, 'updateAccountAddressAssocBase', 'com.yugandhar.mdm.corecomponent.AccountAddressAssocService', 'merge', 'find by business key method search properties based on transaction Name', '2018-04-16 18:35:32.000000', NULL, '2018-04-16 18:35:32.000000', 'Generator', '000000000'),
	('357', 0, 'findAllLeAccountAssocByLegalEntityId', 'com.yugandhar.mdm.composite.service.FindAllLeAccountByLegalEntityIdService', 'process', 'find All LeAccount By LegalEntityIdPk', '2018-04-16 18:35:34.000000', NULL, '2018-04-16 18:35:34.000000', 'Generator', '000000000'),
	('358', 0, 'findAllLePropertyAssocByLegalEntityId', 'com.yugandhar.mdm.composite.service.FindAllLePropertyByLegalEntityIdService', 'process', 'find All LeProperty By LegalEntityIdPk', '2018-04-16 18:35:34.000000', NULL, '2018-04-16 18:35:34.000000', 'Generator', '000000000'),
	('359', 0, 'findAllLeVehicleAssocByLegalEntityId', 'com.yugandhar.mdm.composite.service.FindAllLeVehicleByLegalEntityIdService', 'process', 'find All LeVehicle By LegalEntityIdPk', '2018-04-16 18:35:34.000000', NULL, '2018-04-16 18:35:34.000000', 'Generator', '000000000'),
	('36', 0, 'retrieveAccountAddressAssocBase', 'com.yugandhar.mdm.corecomponent.AccountAddressAssocService', 'findById', 'find by business key method search properties based on transaction Name', '2018-04-16 18:35:32.000000', NULL, '2018-04-16 18:35:32.000000', 'Generator', '000000000'),
	('360', 0, 'createLeAccountAssociation', 'com.yugandhar.mdm.composite.service.CreateLeAccountService', 'process', 'create LeAccount association along with AccountDO record if present', '2018-04-16 18:35:34.000000', NULL, '2018-04-16 18:35:34.000000', 'Generator', '000000000'),
	('361', 0, 'createLeVehicleAssociation', 'com.yugandhar.mdm.composite.service.CreateLeVehicleService', 'process', 'create LeVehicle association along withVehicleDO record if present', '2018-04-16 18:35:34.000000', NULL, '2018-04-16 18:35:34.000000', 'Generator', '000000000'),
	('362', 0, 'createLePropertyAssociation', 'com.yugandhar.mdm.composite.service.CreateLePropertyService', 'process', 'create LeProperty association along with PropertyDO record if present', '2018-04-16 18:35:34.000000', NULL, '2018-04-16 18:35:34.000000', 'Generator', '000000000'),
	('364', 0, 'updateLeAccountAssociation', 'com.yugandhar.mdm.composite.service.UpdateLeAccountService', 'process', 'updates LeAccount association along with AccountDO record if present', '2018-04-16 18:35:34.000000', NULL, '2018-04-16 18:35:34.000000', 'Generator', '000000000'),
	('365', 0, 'updateLeVehicleAssociation', 'com.yugandhar.mdm.composite.service.UpdateLeVehicleService', 'process', 'updates LeVehicle association along withVehicleDO record if present', '2018-04-16 18:35:34.000000', NULL, '2018-04-16 18:35:34.000000', 'Generator', '000000000'),
	('366', 0, 'updateLePropertyAssociation', 'com.yugandhar.mdm.composite.service.UpdateLePropertyService', 'process', 'updates LeProperty association along with PropertyDO record if present', '2018-04-16 18:35:34.000000', NULL, '2018-04-16 18:35:34.000000', 'Generator', '000000000'),
	('367', 0, 'createAccountAddressAssociation', 'com.yugandhar.mdm.composite.service.CreateAccountAddressService', 'process', 'create account address', '2018-04-16 18:35:34.000000', NULL, '2018-04-16 18:35:34.000000', 'Generator', '000000000'),
	('368', 0, 'createAccountPhoneAssociation', 'com.yugandhar.mdm.composite.service.CreateAccountPhoneService', 'process', 'create account phone association', '2018-04-16 18:35:34.000000', NULL, '2018-04-16 18:35:34.000000', 'Generator', '000000000'),
	('369', 0, 'retrieveAccountAddressByAccountAddressAssocId', 'com.yugandhar.mdm.composite.service.RetrieveAccountAddressByAccountAddressAssocIdService', 'process', 'retrieve account Address association', '2018-04-16 18:35:34.000000', NULL, '2018-04-16 18:35:34.000000', 'Generator', '000000000'),
	('370', 0, 'retrieveAccountPhoneByAccountPhoneAssocId', 'com.yugandhar.mdm.composite.service.RetrieveAccountPhoneByAccountPhoneAssocIdService', 'process', 'retrieve account Phone association', '2018-04-16 18:35:34.000000', NULL, '2018-04-16 18:35:34.000000', 'Generator', '000000000'),
	('371', 0, 'updateLePhoneAssociation', 'com.yugandhar.mdm.composite.service.UpdateLePhoneService', 'process', 'update Le Phone association', '2018-04-16 18:35:34.000000', NULL, '2018-04-16 18:35:34.000000', 'Generator', '000000000'),
	('373', 0, 'updateAccountPhoneAssociation', 'com.yugandhar.mdm.composite.service.UpdateAccountPhoneService', 'process', 'update Account Phone association', '2018-04-16 18:35:34.000000', NULL, '2018-04-16 18:35:34.000000', 'Generator', '000000000'),
	('374', 0, 'updateAccountAddressAssociation', 'com.yugandhar.mdm.composite.service.UpdateAccountAddressService', 'process', 'update Account Address association', '2018-04-16 18:35:34.000000', NULL, '2018-04-16 18:35:34.000000', 'Generator', '000000000'),
	('377', 0, 'findAllAccountPhoneAssocByAccountIdPkBase', 'com.yugandhar.mdm.corecomponent.AccountPhoneAssocService', 'findByAccountIdPk', 'find all account phone associations based on accountIdPk', '2018-04-16 18:35:35.000000', NULL, '2018-04-16 18:35:35.000000', 'Generator', '000000000'),
	('378', 0, 'findAllAccountAddressAssocByAccountIdPkBase', 'com.yugandhar.mdm.corecomponent.AccountAddressAssocService', 'findByAccountIdPk', 'find all account address associations based on accountIdPk', '2018-04-16 18:35:35.000000', NULL, '2018-04-16 18:35:35.000000', 'Generator', '000000000'),
	('379', 0, 'findAllAccountAddressAssocByAccountId', 'com.yugandhar.mdm.composite.service.FindAllAccountAddressAssocByAccountIdService', 'process', 'find all account address associations alongwith addressDO based on accountIdPk', '2018-04-16 18:35:35.000000', NULL, '2018-04-16 18:35:35.000000', 'Generator', '000000000'),
	('38', 0, 'createAccountPhoneAssocBase', 'com.yugandhar.mdm.corecomponent.AccountPhoneAssocService', 'add', 'find by business key method search properties based on transaction Name', '2018-04-16 18:35:32.000000', NULL, '2018-04-16 18:35:32.000000', 'Generator', '000000000'),
	('380', 0, 'findAllAccountPhoneAssocByAccountId', 'com.yugandhar.mdm.composite.service.FindAllAccountPhoneAssocByAccountIdService', 'process', 'find all account phone associations alongwith phoneStandardizedDO based on accountIdPk', '2018-04-16 18:35:35.000000', NULL, '2018-04-16 18:35:35.000000', 'Generator', '000000000'),
	('381', 0, 'retrieveAccountByAccountId', 'com.yugandhar.mdm.composite.service.RetrieveAccountByAccountIdService', 'process', 'retrieve the account alongwith its associated address and Phones', '2018-04-16 18:35:35.000000', NULL, '2018-04-16 18:35:35.000000', 'Generator', '000000000'),
	('387', 0, 'createAccount', 'com.yugandhar.mdm.composite.service.CreateAccountService', 'process', 'create account along with address and phone association', '2018-04-16 18:35:35.000000', NULL, '2018-04-16 18:35:35.000000', 'Generator', '000000000'),
	('388', 0, 'updateAccount', 'com.yugandhar.mdm.composite.service.UpdateAccountService', 'process', 'update account along with address and phone association', '2018-04-16 18:35:35.000000', NULL, '2018-04-16 18:35:35.000000', 'Generator', '000000000'),
	('389', 0, 'findAllMiscellaneousInfoByEntityIdPkBase', 'com.yugandhar.mdm.corecomponent.MiscellaneousInfoService', 'findByEntityIdPk', 'find All MiscellaneousInfo By EntityIdPk', '2018-04-16 18:35:35.000000', NULL, '2018-04-16 18:35:35.000000', 'Generator', '000000000'),
	('39', 0, 'updateAccountPhoneAssocBase', 'com.yugandhar.mdm.corecomponent.AccountPhoneAssocService', 'merge', 'find by business key method search properties based on transaction Name', '2018-04-16 18:35:33.000000', NULL, '2018-04-16 18:35:33.000000', 'Generator', '000000000'),
	('390', 0, 'findEntityGroupAssocByEntityIdPkBase', 'com.yugandhar.mdm.corecomponent.EntityGroupAssocService', 'findByEntityIdPk', 'find All EntityGroupAssoc By EntityIdPk', '2018-04-16 18:35:35.000000', NULL, '2018-04-16 18:35:35.000000', 'Generator', '000000000'),
	('391', 0, 'findEntityGroupAssocByEntityGroupIdpkBase', 'com.yugandhar.mdm.corecomponent.EntityGroupAssocService', 'findByEntityGroupIdpk', 'find All EntityGroupAssoc By entityGroupIdpk', '2018-04-16 18:35:35.000000', NULL, '2018-04-16 18:35:35.000000', 'Generator', '000000000'),
	('397', 0, 'createEntityGroup', 'com.yugandhar.mdm.composite.service.CreateEntityGroupService', 'process', 'create EntityGroup alongwith entityGroupAssociations if present', '2018-04-16 18:35:35.000000', NULL, '2018-04-16 18:35:35.000000', 'Generator', '000000000'),
	('398', 0, 'updateEntityGroup', 'com.yugandhar.mdm.composite.service.UpdateEntityGroupService', 'process', 'update EntityGroup alongwith entityGroupAssociations if present', '2018-04-16 18:35:35.000000', NULL, '2018-04-16 18:35:35.000000', 'Generator', '000000000'),
	('399', 0, 'retrieveEntityGroupByEntityGroupId', 'com.yugandhar.mdm.composite.service.RetrieveEntityGroupByEntityGroupIdService', 'process', 'retrieve EntityGroup alongwith entityGroupAssociations if present', '2018-04-16 18:35:35.000000', NULL, '2018-04-16 18:35:35.000000', 'Generator', '000000000'),
	('4', 0, 'findConfigTxnRegistryByBusinessKeyBase', 'com.yugandhar.mdm.config.txnregistry.ConfigTxnRegistryService', 'findByBusinessKey', 'find by business key method search properties based on transaction Name', '2018-04-16 18:35:32.000000', NULL, '2018-04-16 18:35:32.000000', 'Generator', '000000000'),
	('40', 0, 'retrieveAccountPhoneAssocBase', 'com.yugandhar.mdm.corecomponent.AccountPhoneAssocService', 'findById', 'find by business key method search properties based on transaction Name', '2018-04-16 18:35:33.000000', NULL, '2018-04-16 18:35:33.000000', 'Generator', '000000000'),
	('400', 0, 'findLeToLeRelationshipByLegalentityIdpkBase', 'com.yugandhar.mdm.corecomponent.LeToLeRelationshipService', 'findByLegalentityIdpk', 'findLeToLeRelationshipByLegalentityIdpk', '2018-04-16 18:35:35.000000', NULL, '2018-04-16 18:35:35.000000', 'Generator', '000000000'),
	('407', 0, 'createConfigAppPropertiesBase', 'com.yugandhar.mdm.config.app.properties.ConfigAppPropertiesService', 'add', 'find by business key method search properties based on transaction Name', '2018-04-16 18:35:35.000000', NULL, '2018-04-16 18:35:35.000000', 'Generator', '000000000'),
	('408', 0, 'updateConfigAppPropertiesBase', 'com.yugandhar.mdm.config.app.properties.ConfigAppPropertiesService', 'merge', 'find by business key method search properties based on transaction Name', '2018-04-16 18:35:35.000000', NULL, '2018-04-16 18:35:35.000000', 'Generator', '000000000'),
	('409', 0, 'retrieveConfigAppPropertiesBase', 'com.yugandhar.mdm.config.app.properties.ConfigAppPropertiesService', 'findById', 'find by business key method search properties based on transaction Name', '2018-04-16 18:35:35.000000', NULL, '2018-04-16 18:35:35.000000', 'Generator', '000000000'),
	('41', 0, 'createAddressBase', 'com.yugandhar.mdm.corecomponent.AddressService', 'add', 'find by business key method search properties based on transaction Name', '2018-04-16 18:35:33.000000', NULL, '2018-04-16 18:35:33.000000', 'Generator', '000000000'),
	('410', 0, 'findConfigAppPropertiesByBusinessKeyBase', 'com.yugandhar.mdm.config.app.properties.ConfigAppPropertiesService', 'findByBusinessKey', 'find by business key method search properties based on transaction Name', '2018-04-16 18:35:35.000000', NULL, '2018-04-16 18:35:35.000000', 'Generator', '000000000'),
	('417', 0, 'createConfigLanguageCodeBase', 'com.yugandhar.mdm.config.langcode.ConfigLanguageCodeService', 'add', 'find by business key method search properties based on transaction Name', '2018-04-16 18:35:35.000000', NULL, '2018-04-16 18:35:35.000000', 'Generator', '000000000'),
	('418', 0, 'updateConfigLanguageCodeBase', 'com.yugandhar.mdm.config.langcode.ConfigLanguageCodeService', 'merge', 'find by business key method search properties based on transaction Name', '2018-04-16 18:35:35.000000', NULL, '2018-04-16 18:35:35.000000', 'Generator', '000000000'),
	('419', 0, 'retrieveConfigLanguageCodeBase', 'com.yugandhar.mdm.config.langcode.ConfigLanguageCodeService', 'findById', 'find by business key method search properties based on transaction Name', '2018-04-16 18:35:35.000000', NULL, '2018-04-16 18:35:35.000000', 'Generator', '000000000'),
	('42', 0, 'updateAddressBase', 'com.yugandhar.mdm.corecomponent.AddressService', 'merge', 'find by business key method search properties based on transaction Name', '2018-04-16 18:35:33.000000', NULL, '2018-04-16 18:35:33.000000', 'Generator', '000000000'),
	('420', 0, 'findConfigLanguageCodeByBusinessKeyBase', 'com.yugandhar.mdm.config.langcode.ConfigLanguageCodeService', 'findByBusinessKey', 'find by business key method search properties based on transaction Name', '2018-04-16 18:35:35.000000', NULL, '2018-04-16 18:35:35.000000', 'Generator', '000000000'),
	('421', 0, 'findAllConfigLanguageCodesBase', 'com.yugandhar.mdm.config.langcode.ConfigLanguageCodeService', 'findAllRecords', 'find All records by language code', '2018-04-16 18:35:35.000000', NULL, '2018-04-16 18:35:35.000000', 'Generator', '000000000'),
	('428', 0, 'createRefPreferenceTypeBase', 'com.yugandhar.mdm.corecomponentref.RefPreferenceTypeService', 'add', 'find by business key method search properties based on transaction Name', '2018-04-16 18:35:35.000000', NULL, '2018-04-16 18:35:35.000000', 'Generator', '000000000'),
	('429', 0, 'updateRefPreferenceTypeBase', 'com.yugandhar.mdm.corecomponentref.RefPreferenceTypeService', 'merge', 'find by business key method search properties based on transaction Name', '2018-04-16 18:35:35.000000', NULL, '2018-04-16 18:35:35.000000', 'Generator', '000000000'),
	('43', 0, 'retrieveAddressBase', 'com.yugandhar.mdm.corecomponent.AddressService', 'findById', 'find by business key method search properties based on transaction Name', '2018-04-16 18:35:33.000000', NULL, '2018-04-16 18:35:33.000000', 'Generator', '000000000'),
	('430', 0, 'retrieveRefPreferenceTypeBase', 'com.yugandhar.mdm.corecomponentref.RefPreferenceTypeService', 'findById', 'find by business key method search properties based on transaction Name', '2018-04-16 18:35:36.000000', NULL, '2018-04-16 18:35:36.000000', 'Generator', '000000000'),
	('431', 0, 'findRefPreferenceTypeByBusinessKeyBase', 'com.yugandhar.mdm.corecomponentref.RefPreferenceTypeService', 'findByBusinessKey', 'find by business key method search properties based on transaction Name', '2018-04-16 18:35:36.000000', NULL, '2018-04-16 18:35:36.000000', 'Generator', '000000000'),
	('432', 0, 'findAllRefPreferenceTypeByLanguageCodeBase', 'com.yugandhar.mdm.corecomponentref.RefPreferenceTypeService', 'findAllRecordsByLanguageCode', 'find All records by language code', '2018-04-16 18:35:36.000000', NULL, '2018-04-16 18:35:36.000000', 'Generator', '000000000'),
	('437', 0, 'createConfigInquiryLevelsBase', 'com.yugandhar.mdm.config.inqlevel.ConfigInquiryLevelsService', 'add', 'find by business key method search properties based on transaction Name', '2018-04-16 18:35:36.000000', NULL, '2018-04-16 18:35:36.000000', 'Generator', '000000000'),
	('438', 0, 'updateConfigInquiryLevelsBase', 'com.yugandhar.mdm.config.inqlevel.ConfigInquiryLevelsService', 'merge', 'find by business key method search properties based on transaction Name', '2018-04-16 18:35:36.000000', NULL, '2018-04-16 18:35:36.000000', 'Generator', '000000000'),
	('439', 0, 'retrieveConfigInquiryLevelsBase', 'com.yugandhar.mdm.config.inqlevel.ConfigInquiryLevelsService', 'findById', 'find by business key method search properties based on transaction Name', '2018-04-16 18:35:36.000000', NULL, '2018-04-16 18:35:36.000000', 'Generator', '000000000'),
	('44', 0, 'createCorporationnamesBase', 'com.yugandhar.mdm.corecomponent.CorporationnamesService', 'add', 'find by business key method search properties based on transaction Name', '2018-04-16 18:35:33.000000', NULL, '2018-04-16 18:35:33.000000', 'Generator', '000000000'),
	('440', 0, 'findAllConfigInquiryLevelsByBusinessKeyBase', 'com.yugandhar.mdm.config.inqlevel.ConfigInquiryLevelsService', 'findAllByBusinessKey', 'find by business key method search properties based on transaction Name', '2018-04-16 18:35:36.000000', NULL, '2018-04-16 18:35:36.000000', 'Generator', '000000000'),
	('441', 0, 'findAllConfigInquiryLevelsBase', 'com.yugandhar.mdm.config.inqlevel.ConfigInquiryLevelsService', 'findAllRecords', 'find All records', '2018-04-16 18:35:33.000000', NULL, '2018-04-16 18:35:33.000000', 'Generator', '000000000'),
	('447', 0, 'findLeAccountAssocByAccountIdPkBase', 'com.yugandhar.mdm.corecomponent.LeAccountAssocService', 'findByAccountIdPk', 'find LeAccountAssoc by accountIdpk', '2018-04-16 18:35:33.000000', NULL, '2018-04-16 18:35:33.000000', 'Generator', '000000000'),
	('45', 0, 'updateCorporationnamesBase', 'com.yugandhar.mdm.corecomponent.CorporationnamesService', 'merge', 'find by business key method search properties based on transaction Name', '2018-04-16 18:35:33.000000', NULL, '2018-04-16 18:35:33.000000', 'Generator', '000000000'),
	('457', 0, 'searchLegalEntityByLEAttributes', 'com.yugandhar.mdm.composite.service.SearchLegalEntityByLEAttributesService', 'process', 'Search Legal Entity Service', '2018-04-16 18:35:33.000000', NULL, '2018-04-16 18:35:33.000000', 'Generator', '000000000'),
	('46', 0, 'retrieveCorporationnamesBase', 'com.yugandhar.mdm.corecomponent.CorporationnamesService', 'findById', 'find by business key method search properties based on transaction Name', '2018-04-16 18:35:33.000000', NULL, '2018-04-16 18:35:33.000000', 'Generator', '000000000'),
	('467', 0, 'searchLegalEntityByAccountAttributes', 'com.yugandhar.mdm.composite.service.SearchLegalEntityByAccountAttributesService', 'process', 'search LegalEntity By Account Attributes', '2018-04-16 18:35:33.000000', NULL, '2018-04-16 18:35:33.000000', 'Generator', '000000000'),
	('47', 0, 'createEntityGroupBase', 'com.yugandhar.mdm.corecomponent.EntityGroupService', 'add', 'find by business key method search properties based on transaction Name', '2018-04-16 18:35:30.000000', NULL, '2018-04-16 18:35:30.000000', 'Generator', '000000000'),
	('477', 0, 'searchAccountByLEAttributes', 'com.yugandhar.mdm.composite.service.SearchAccountByLEAttributesService', 'process', 'search Account By LE Attributes', '2018-04-16 18:35:33.000000', NULL, '2018-04-16 18:35:33.000000', 'Generator', '000000000'),
	('478', 0, 'searchAccountByAccountAttributes', 'com.yugandhar.mdm.composite.service.SearchAccountByAccountAttributesService', 'process', 'search Account By Account Attributes', '2018-04-16 18:35:33.000000', NULL, '2018-04-16 18:35:33.000000', 'Generator', '000000000'),
	('479', 0, 'createAuthRolesRegistryBase', 'com.yugandhar.mdm.auth.AuthRolesRegistryService', 'add', 'create record in database', '2018-04-16 18:35:33.000000', NULL, '2018-04-16 18:35:33.000000', 'Generator', '000000000'),
	('48', 0, 'updateEntityGroupBase', 'com.yugandhar.mdm.corecomponent.EntityGroupService', 'merge', 'find by business key method search properties based on transaction Name', '2018-04-16 18:35:30.000000', NULL, '2018-04-16 18:35:30.000000', 'Generator', '000000000'),
	('480', 0, 'updateAuthRolesRegistryBase', 'com.yugandhar.mdm.auth.AuthRolesRegistryService', 'merge', 'update the record in database based on idpk', '2018-04-16 18:35:33.000000', NULL, '2018-04-16 18:35:33.000000', 'Generator', '000000000'),
	('481', 0, 'retrieveAuthRolesRegistryBase', 'com.yugandhar.mdm.auth.AuthRolesRegistryService', 'findById', 'find the record based on primary key i.e. idpk', '2018-04-16 18:35:33.000000', NULL, '2018-04-16 18:35:33.000000', 'Generator', '000000000'),
	('482', 0, 'findAuthRolesRegistryByBusinessKeyBase', 'com.yugandhar.mdm.auth.AuthRolesRegistryService', 'findByBusinessKey', 'find by business key method search properties based on roleName', '2018-04-16 18:35:33.000000', NULL, '2018-04-16 18:35:33.000000', 'Generator', '000000000'),
	('483', 0, 'createAuthUserRegistryBase', 'com.yugandhar.mdm.auth.AuthUserRegistryService', 'add', 'create record in database', '2018-04-16 18:35:33.000000', NULL, '2018-04-16 18:35:33.000000', 'Generator', '000000000'),
	('484', 0, 'updateAuthUserRegistryBase', 'com.yugandhar.mdm.auth.AuthUserRegistryService', 'merge', 'update the record in database based on idpk', '2018-04-16 18:35:33.000000', NULL, '2018-04-16 18:35:33.000000', 'Generator', '000000000'),
	('485', 0, 'retrieveAuthUserRegistryBase', 'com.yugandhar.mdm.auth.AuthUserRegistryService', 'findById', 'find the record based on primary key i.e. idpk', '2018-04-16 18:35:33.000000', NULL, '2018-04-16 18:35:33.000000', 'Generator', '000000000'),
	('486', 0, 'findAuthUserRegistryByBusinessKeyBase', 'com.yugandhar.mdm.auth.AuthUserRegistryService', 'findByBusinessKey', 'find by business key method search properties based on UserName', '2018-04-16 18:35:33.000000', NULL, '2018-04-16 18:35:33.000000', 'Generator', '000000000'),
	('487', 0, 'createAuthUserroleAccesscontrolBase', 'com.yugandhar.mdm.auth.AuthUserroleAccesscontrolService', 'add', 'create record in database. if profiletype is USER then provide the AuthUserRegistryIdpk else if profile type is ROLE then provide AuthRolesRegistryIdpk', '2018-04-16 18:35:33.000000', NULL, '2018-04-16 18:35:33.000000', 'Generator', '000000000'),
	('488', 0, 'updateAuthUserroleAccesscontrolBase', 'com.yugandhar.mdm.auth.AuthUserroleAccesscontrolService', 'merge', 'update the record in database based on idpk. if profiletype is USER then provide the AuthUserRegistryIdpk else if profile type is ROLE then provide AuthRolesRegistryIdpk', '2018-04-16 18:35:33.000000', NULL, '2018-04-16 18:35:33.000000', 'Generator', '000000000'),
	('489', 0, 'retrieveAuthUserroleAccesscontrolBase', 'com.yugandhar.mdm.auth.AuthUserroleAccesscontrolService', 'findById', 'find the record based on primary key i.e. idpk. if profiletype is USER then provide the AuthUserRegistryIdpk else if profile type is ROLE then provide AuthRolesRegistryIdpk', '2018-04-16 18:35:33.000000', NULL, '2018-04-16 18:35:33.000000', 'Generator', '000000000'),
	('49', 0, 'retrieveEntityGroupBase', 'com.yugandhar.mdm.corecomponent.EntityGroupService', 'findById', 'find by business key method search properties based on transaction Name', '2018-04-16 18:35:30.000000', NULL, '2018-04-16 18:35:30.000000', 'Generator', '000000000'),
	('494', 0, 'findAuthUserroleAccesscontrolByBusinessKeyBase', 'com.yugandhar.mdm.auth.AuthUserroleAccesscontrolService', 'findByBusinessKey', 'find by business key method search properties based on profileType, authUserRoleRegistryIdpk and configTxnRegistryIdpk', '2018-04-16 18:35:33.000000', NULL, '2018-04-16 18:35:33.000000', 'Generator', '000000000'),
	('495', 0, 'findAuthUserroleAccesscontrolByRegistryIdpkBase', 'com.yugandhar.mdm.auth.AuthUserroleAccesscontrolService', 'findAuthUserroleAccesscontrolByRegistryIdpk', 'find All records by ProfileType and AuthUserRoleRegistryIdpk. if profiletype is USER then provide the AuthUserRegistryIdpk else if profile type is ROLE then provide AuthRolesRegistryIdpk', '2018-04-16 18:35:33.000000', NULL, '2018-04-16 18:35:33.000000', 'Generator', '000000000'),
	('496', 0, 'createAuthUserRoleAssocBase', 'com.yugandhar.mdm.auth.AuthUserRoleAssocService', 'add', 'create record in database', '2018-04-16 18:35:33.000000', NULL, '2018-04-16 18:35:33.000000', 'Generator', '000000000'),
	('497', 0, 'updateAuthUserRoleAssocBase', 'com.yugandhar.mdm.auth.AuthUserRoleAssocService', 'merge', 'update the record in database based on idpk', '2018-04-16 18:35:33.000000', NULL, '2018-04-16 18:35:33.000000', 'Generator', '000000000'),
	('498', 0, 'retrieveAuthUserRoleAssocBase', 'com.yugandhar.mdm.auth.AuthUserRoleAssocService', 'findById', 'find the record based on primary key i.e. idpk', '2018-04-16 18:35:33.000000', NULL, '2018-04-16 18:35:33.000000', 'Generator', '000000000'),
	('499', 0, 'findAuthUserRoleAssocByBusinessKeyBase', 'com.yugandhar.mdm.auth.AuthUserRoleAssocService', 'findByBusinessKey', 'find by business key method search properties based on authRolesRegistryIdpk and authUserRoleAssocIdpk', '2018-04-16 18:35:33.000000', NULL, '2018-04-16 18:35:33.000000', 'Generator', '000000000'),
	('5', 0, 'createConfigErrorcodeRegistryBase', 'com.yugandhar.mdm.config.errorcoderegistry.ConfigErrorcodeRegistryService', 'add', 'find by business key method search properties based on transaction Name', '2018-04-16 18:35:32.000000', NULL, '2018-04-16 18:35:32.000000', 'Generator', '000000000'),
	('50', 0, 'createEntityGroupAssocBase', 'com.yugandhar.mdm.corecomponent.EntityGroupAssocService', 'add', 'find by business key method search properties based on transaction Name', '2018-04-16 18:35:30.000000', NULL, '2018-04-16 18:35:30.000000', 'Generator', '000000000'),
	('500', 0, 'findAllRecordsByAuthUserRegistryIdpkBase', 'com.yugandhar.mdm.auth.AuthUserRoleAssocService', 'findAllRecordsByAuthUserRegistryIdpk', 'find All records by authUserRegistryIdpkBase', '2018-04-16 18:35:33.000000', NULL, '2018-04-16 18:35:33.000000', 'Generator', '000000000'),
	('501', 0, 'findAllRecordsByAuthRolesRegistryIdpkBase', 'com.yugandhar.mdm.auth.AuthUserRoleAssocService', 'findAllRecordsByAuthRolesRegistryIdpk', 'find All records by AuthRolesRegistryIdpk', '2018-04-16 18:35:33.000000', NULL, '2018-04-16 18:35:33.000000', 'Generator', '000000000'),
	('509', 0, 'searchAuthRoles', 'com.yugandhar.mdm.auth.SearchAuthRolesService', 'process', 'find the Auth roles based on userName or roleName', '2018-04-16 18:35:33.000000', NULL, '2018-04-16 18:35:33.000000', 'Generator', '000000000'),
	('51', 0, 'updateEntityGroupAssocBase', 'com.yugandhar.mdm.corecomponent.EntityGroupAssocService', 'merge', 'find by business key method search properties based on transaction Name', '2018-04-16 18:35:30.000000', NULL, '2018-04-16 18:35:30.000000', 'Generator', '000000000'),
	('510', 0, 'searchAuthUsers', 'com.yugandhar.mdm.auth.searchAuthUsersService', 'process', 'find the Auth users based on userName or roleName', '2018-04-16 18:35:33.000000', NULL, '2018-04-16 18:35:33.000000', 'Generator', '000000000'),
	('519', 0, 'searchAuthAccessControl', 'com.yugandhar.mdm.auth.searchAuthAccessControlService', 'process', 'searches if user is authorized to for given transaction. Wildcard searches are not allowed in this transaction considering the fact that this transaction is used for performing authorization', '2018-04-16 18:35:33.000000', NULL, '2018-04-16 18:35:33.000000', 'Generator', '000000000'),
	('52', 0, 'retrieveEntityGroupAssocBase', 'com.yugandhar.mdm.corecomponent.EntityGroupAssocService', 'findById', 'find by business key method search properties based on transaction Name', '2018-04-16 18:35:30.000000', NULL, '2018-04-16 18:35:30.000000', 'Generator', '000000000'),
	('529', 0, 'createRefMatchScoreBase', 'com.yugandhar.mdm.match.componentref.RefMatchScoreService', 'add', 'create record in the database', '2018-04-16 18:35:33.000000', NULL, '2018-04-16 18:35:33.000000', 'Generator', '000000000'),
	('53', 0, 'createLeAccountAssocBase', 'com.yugandhar.mdm.corecomponent.LeAccountAssocService', 'add', 'find by business key method search properties based on transaction Name', '2018-04-16 18:35:30.000000', NULL, '2018-04-16 18:35:30.000000', 'Generator', '000000000'),
	('530', 0, 'updateRefMatchScoreBase', 'com.yugandhar.mdm.match.componentref.RefMatchScoreService', 'merge', 'update the database record based on primary key i.e. idpk', '2018-04-16 18:35:33.000000', NULL, '2018-04-16 18:35:33.000000', 'Generator', '000000000'),
	('531', 0, 'retrieveRefMatchScoreBase', 'com.yugandhar.mdm.match.componentref.RefMatchScoreService', 'findById', 'retrieve the record from database based on primary key i.e. idpk', '2018-04-16 18:35:33.000000', NULL, '2018-04-16 18:35:33.000000', 'Generator', '000000000'),
	('532', 0, 'findRefMatchScoreByBusinessKeyBase', 'com.yugandhar.mdm.match.componentref.RefMatchScoreService', 'findByBusinessKey', 'find the unique record from dababase based on by business key', '2018-04-16 18:35:33.000000', NULL, '2018-04-16 18:35:33.000000', 'Generator', '000000000'),
	('533', 0, 'findAllRefMatchScoreByMatchEntityObjectName', 'com.yugandhar.mdm.match.componentref.RefMatchScoreService', 'findAllRecordsByMatchEntityObjectName', 'find All records by language code', '2018-04-16 18:35:36.000000', NULL, '2018-04-16 18:35:36.000000', 'Generator', '000000000'),
	('534', 0, 'createRefMatchThresholdBase', 'com.yugandhar.mdm.match.componentref.RefMatchThresholdService', 'add', 'create record in the database', '2018-04-16 18:35:36.000000', NULL, '2018-04-16 18:35:36.000000', 'Generator', '000000000'),
	('535', 0, 'updateRefMatchThresholdBase', 'com.yugandhar.mdm.match.componentref.RefMatchThresholdService', 'merge', 'update the database record based on primary key i.e. idpk', '2018-04-16 18:35:36.000000', NULL, '2018-04-16 18:35:36.000000', 'Generator', '000000000'),
	('536', 0, 'retrieveRefMatchThresholdBase', 'com.yugandhar.mdm.match.componentref.RefMatchThresholdService', 'findById', 'retrieve the record from database based on primary key i.e. idpk', '2018-04-16 18:35:36.000000', NULL, '2018-04-16 18:35:36.000000', 'Generator', '000000000'),
	('537', 0, 'findRefMatchThresholdByBusinessKeyBase', 'com.yugandhar.mdm.match.componentref.RefMatchThresholdService', 'findByBusinessKey', 'find the unique record from dababase based on by business key', '2018-04-16 18:35:36.000000', NULL, '2018-04-16 18:35:36.000000', 'Generator', '000000000'),
	('538', 0, 'findAllRefMatchThresholdBase', 'com.yugandhar.mdm.match.componentref.RefMatchThresholdService', 'findAllRecords', 'find All records by language code', '2018-04-16 18:35:36.000000', NULL, '2018-04-16 18:35:36.000000', 'Generator', '000000000'),
	('539', 0, 'createRefInactivationReasonBase', 'com.yugandhar.mdm.match.componentref.RefInactivationReasonService', 'add', 'create record in the database', '2018-04-16 18:35:36.000000', NULL, '2018-04-16 18:35:36.000000', 'Generator', '000000000'),
	('54', 0, 'updateLeAccountAssocBase', 'com.yugandhar.mdm.corecomponent.LeAccountAssocService', 'merge', 'find by business key method search properties based on transaction Name', '2018-04-16 18:35:30.000000', NULL, '2018-04-16 18:35:30.000000', 'Generator', '000000000'),
	('540', 0, 'updateRefInactivationReasonBase', 'com.yugandhar.mdm.match.componentref.RefInactivationReasonService', 'merge', 'update the database record based on primary key i.e. idpk', '2018-04-16 18:35:36.000000', NULL, '2018-04-16 18:35:36.000000', 'Generator', '000000000'),
	('541', 0, 'retrieveRefInactivationReasonBase', 'com.yugandhar.mdm.match.componentref.RefInactivationReasonService', 'findById', 'retrieve the record from database based on primary key i.e. idpk', '2018-04-16 18:35:36.000000', NULL, '2018-04-16 18:35:36.000000', 'Generator', '000000000'),
	('542', 0, 'findRefInactivationReasonByBusinessKeyBase', 'com.yugandhar.mdm.match.componentref.RefInactivationReasonService', 'findByBusinessKey', 'find the unique record from dababase based on by business key', '2018-04-16 18:35:36.000000', NULL, '2018-04-16 18:35:36.000000', 'Generator', '000000000'),
	('543', 0, 'findAllRefInactivationReasonByLanguageCodeBase', 'com.yugandhar.mdm.match.componentref.RefInactivationReasonService', 'findAllRecordsByLanguageCode', 'find All records by language code', '2018-04-16 18:35:36.000000', NULL, '2018-04-16 18:35:36.000000', 'Generator', '000000000'),
	('544', 0, 'createRefMatchActionstatusBase', 'com.yugandhar.mdm.match.componentref.RefMatchActionstatusService', 'add', 'create record in the database', '2018-04-16 18:35:36.000000', NULL, '2018-04-16 18:35:36.000000', 'Generator', '000000000'),
	('545', 0, 'updateRefMatchActionstatusBase', 'com.yugandhar.mdm.match.componentref.RefMatchActionstatusService', 'merge', 'update the database record based on primary key i.e. idpk', '2018-04-16 18:35:36.000000', NULL, '2018-04-16 18:35:36.000000', 'Generator', '000000000'),
	('546', 0, 'retrieveRefMatchActionstatusBase', 'com.yugandhar.mdm.match.componentref.RefMatchActionstatusService', 'findById', 'retrieve the record from database based on primary key i.e. idpk', '2018-04-16 18:35:36.000000', NULL, '2018-04-16 18:35:36.000000', 'Generator', '000000000'),
	('547', 0, 'findRefMatchActionstatusByBusinessKeyBase', 'com.yugandhar.mdm.match.componentref.RefMatchActionstatusService', 'findByBusinessKey', 'find the unique record from dababase based on by business key', '2018-04-16 18:35:36.000000', NULL, '2018-04-16 18:35:36.000000', 'Generator', '000000000'),
	('548', 0, 'findAllRefMatchActionstatusByLanguageCodeBase', 'com.yugandhar.mdm.match.componentref.RefMatchActionstatusService', 'findAllRecordsByLanguageCode', 'find All records by language code', '2018-04-16 18:35:36.000000', NULL, '2018-04-16 18:35:36.000000', 'Generator', '000000000'),
	('549', 0, 'createRefMatchProposedActionBase', 'com.yugandhar.mdm.match.componentref.RefMatchProposedActionService', 'add', 'create record in the database', '2018-04-16 18:35:36.000000', NULL, '2018-04-16 18:35:36.000000', 'Generator', '000000000'),
	('55', 0, 'retrieveLeAccountAssocBase', 'com.yugandhar.mdm.corecomponent.LeAccountAssocService', 'findById', 'find by business key method search properties based on transaction Name', '2018-04-16 18:35:30.000000', NULL, '2018-04-16 18:35:30.000000', 'Generator', '000000000'),
	('550', 0, 'updateRefMatchProposedActionBase', 'com.yugandhar.mdm.match.componentref.RefMatchProposedActionService', 'merge', 'update the database record based on primary key i.e. idpk', '2018-04-16 18:35:36.000000', NULL, '2018-04-16 18:35:36.000000', 'Generator', '000000000'),
	('551', 0, 'retrieveRefMatchProposedActionBase', 'com.yugandhar.mdm.match.componentref.RefMatchProposedActionService', 'findById', 'retrieve the record from database based on primary key i.e. idpk', '2018-04-16 18:35:36.000000', NULL, '2018-04-16 18:35:36.000000', 'Generator', '000000000'),
	('552', 0, 'findRefMatchProposedActionByBusinessKeyBase', 'com.yugandhar.mdm.match.componentref.RefMatchProposedActionService', 'findByBusinessKey', 'find the unique record from dababase based on by business key', '2018-04-16 18:35:36.000000', NULL, '2018-04-16 18:35:36.000000', 'Generator', '000000000'),
	('553', 0, 'findAllRefMatchProposedActionByLanguageCodeBase', 'com.yugandhar.mdm.match.componentref.RefMatchProposedActionService', 'findAllRecordsByLanguageCode', 'find All records by language code', '2018-04-16 18:35:36.000000', NULL, '2018-04-16 18:35:36.000000', 'Generator', '000000000'),
	('554', 0, 'createRefMatchResultBase', 'com.yugandhar.mdm.match.componentref.RefMatchResultService', 'add', 'create record in the database', '2018-04-16 18:35:36.000000', NULL, '2018-04-16 18:35:36.000000', 'Generator', '000000000'),
	('555', 0, 'updateRefMatchResultBase', 'com.yugandhar.mdm.match.componentref.RefMatchResultService', 'merge', 'update the database record based on primary key i.e. idpk', '2018-04-16 18:35:36.000000', NULL, '2018-04-16 18:35:36.000000', 'Generator', '000000000'),
	('556', 0, 'retrieveRefMatchResultBase', 'com.yugandhar.mdm.match.componentref.RefMatchResultService', 'findById', 'retrieve the record from database based on primary key i.e. idpk', '2018-04-16 18:35:36.000000', NULL, '2018-04-16 18:35:36.000000', 'Generator', '000000000'),
	('557', 0, 'findRefMatchResultByBusinessKeyBase', 'com.yugandhar.mdm.match.componentref.RefMatchResultService', 'findByBusinessKey', 'find the unique record from dababase based on by business key', '2018-04-16 18:35:36.000000', NULL, '2018-04-16 18:35:36.000000', 'Generator', '000000000'),
	('558', 0, 'findAllRefMatchResultByLanguageCodeBase', 'com.yugandhar.mdm.match.componentref.RefMatchResultService', 'findAllRecordsByLanguageCode', 'find All records by language code', '2018-04-16 18:35:36.000000', NULL, '2018-04-16 18:35:36.000000', 'Generator', '000000000'),
	('559', 0, 'createRefMergeReasonBase', 'com.yugandhar.mdm.match.componentref.RefMergeReasonService', 'add', 'create record in the database', '2018-04-16 18:35:36.000000', NULL, '2018-04-16 18:35:36.000000', 'Generator', '000000000'),
	('56', 0, 'createLeAddressAssocBase', 'com.yugandhar.mdm.corecomponent.LeAddressAssocService', 'add', 'find by business key method search properties based on transaction Name', '2018-04-16 18:35:30.000000', NULL, '2018-04-16 18:35:30.000000', 'Generator', '000000000'),
	('560', 0, 'updateRefMergeReasonBase', 'com.yugandhar.mdm.match.componentref.RefMergeReasonService', 'merge', 'update the database record based on primary key i.e. idpk', '2018-04-16 18:35:36.000000', NULL, '2018-04-16 18:35:36.000000', 'Generator', '000000000'),
	('561', 0, 'retrieveRefMergeReasonBase', 'com.yugandhar.mdm.match.componentref.RefMergeReasonService', 'findById', 'retrieve the record from database based on primary key i.e. idpk', '2018-04-16 18:35:36.000000', NULL, '2018-04-16 18:35:36.000000', 'Generator', '000000000'),
	('562', 0, 'findRefMergeReasonByBusinessKeyBase', 'com.yugandhar.mdm.match.componentref.RefMergeReasonService', 'findByBusinessKey', 'find the unique record from dababase based on by business key', '2018-04-16 18:35:36.000000', NULL, '2018-04-16 18:35:36.000000', 'Generator', '000000000'),
	('563', 0, 'findAllRefMergeReasonByLanguageCodeBase', 'com.yugandhar.mdm.match.componentref.RefMergeReasonService', 'findAllRecordsByLanguageCode', 'find All records by language code', '2018-04-16 18:35:36.000000', NULL, '2018-04-16 18:35:36.000000', 'Generator', '000000000'),
	('564', 0, 'createInactiveLeRegistryBase', 'com.yugandhar.mdm.match.component.InactiveLeRegistryService', 'add', 'create record in the database', '2018-04-16 18:35:36.000000', NULL, '2018-04-16 18:35:36.000000', 'Generator', '000000000'),
	('565', 0, 'updateInactiveLeRegistryBase', 'com.yugandhar.mdm.match.component.InactiveLeRegistryService', 'merge', 'update the database record based on primary key i.e. idpk', '2018-04-16 18:35:36.000000', NULL, '2018-04-16 18:35:36.000000', 'Generator', '000000000'),
	('566', 0, 'retrieveInactiveLeRegistryBase', 'com.yugandhar.mdm.match.component.InactiveLeRegistryService', 'findById', 'retrieve the record from database based on primary key i.e. idpk', '2018-04-16 18:35:36.000000', NULL, '2018-04-16 18:35:36.000000', 'Generator', '000000000'),
	('567', 0, 'createMatchCandidateLeRegistryBase', 'com.yugandhar.mdm.match.component.MatchCandidateLeRegistryService', 'add', 'create record in the database', '2018-04-16 18:35:36.000000', NULL, '2018-04-16 18:35:36.000000', 'Generator', '000000000'),
	('568', 0, 'updateMatchCandidateLeRegistryBase', 'com.yugandhar.mdm.match.component.MatchCandidateLeRegistryService', 'merge', 'update the database record based on primary key i.e. idpk', '2018-04-16 18:35:36.000000', NULL, '2018-04-16 18:35:36.000000', 'Generator', '000000000'),
	('569', 0, 'retrieveMatchCandidateLeRegistryBase', 'com.yugandhar.mdm.match.component.MatchCandidateLeRegistryService', 'findById', 'retrieve the record from database based on primary key i.e. idpk', '2018-04-16 18:35:36.000000', NULL, '2018-04-16 18:35:36.000000', 'Generator', '000000000'),
	('57', 0, 'updateLeAddressAssocBase', 'com.yugandhar.mdm.corecomponent.LeAddressAssocService', 'merge', 'find by business key method search properties based on transaction Name', '2018-04-16 18:35:30.000000', NULL, '2018-04-16 18:35:30.000000', 'Generator', '000000000'),
	('570', 0, 'createMatchMergedLeAssocBase', 'com.yugandhar.mdm.match.component.MatchMergedLeAssocService', 'add', 'create record in the database', '2018-04-16 18:35:36.000000', NULL, '2018-04-16 18:35:36.000000', 'Generator', '000000000'),
	('571', 0, 'updateMatchMergedLeAssocBase', 'com.yugandhar.mdm.match.component.MatchMergedLeAssocService', 'merge', 'update the database record based on primary key i.e. idpk', '2018-04-16 18:35:36.000000', NULL, '2018-04-16 18:35:36.000000', 'Generator', '000000000'),
	('572', 0, 'retrieveMatchMergedLeAssocBase', 'com.yugandhar.mdm.match.component.MatchMergedLeAssocService', 'findById', 'retrieve the record from database based on primary key i.e. idpk', '2018-04-16 18:35:36.000000', NULL, '2018-04-16 18:35:36.000000', 'Generator', '000000000'),
	('573', 0, 'searchInactiveLeRegistryBase', 'com.yugandhar.mdm.composite.service.SearchInactiveLeRegistryBase', 'process', 'search all records of INACTIVE_LE_REGISTRY table based on LegalentityIdpk or inactivationReasonRefkey or both ', '2018-04-16 18:35:36.000000', NULL, '2018-04-16 18:35:36.000000', 'Generator', '000000000'),
	('574', 0, 'searchMatchCandidateLeRegistryBase', 'com.yugandhar.mdm.composite.service.SearchMatchCandidateLeRegistryBase', 'process', 'search all records of MATCH_CANDIDATE_LE_REGISTRY table based on legalentityidpk candidateLegalentityidpk or matchPattern or matchProposedActionRefkey or matchActionstatusRefkey or all attributes', '2018-04-16 18:35:36.000000', NULL, '2018-04-16 18:35:36.000000', 'Generator', '000000000'),
	('575', 0, 'searchMatchMergedLeAssocBase', 'com.yugandhar.mdm.composite.service.SearchMatchMergedLeAssocBase', 'process', 'search all records of MATCH_MERGED_LE_ASSOC table based on survivorLegalentityIdpk or mergedLegalentityIdpk or mergeReasonRefkey or all ', '2018-04-16 18:35:36.000000', NULL, '2018-04-16 18:35:36.000000', 'Generator', '000000000'),
	('576', 0, 'searchMatchCandidateLE', 'com.yugandhar.mdm.composite.service.SearchMatchCandidateLEService', 'process', 'searchMatchCandidateLEService', '2018-04-16 18:35:36.000000', NULL, '2018-04-16 18:35:36.000000', 'Generator', '000000000'),
	('577', 0, 'performLeMatch', 'com.yugandhar.mdm.composite.service.PerformLeMatchService', 'process', 'Performs the Legal Entity matching and create candidates', '2018-04-16 18:35:36.000000', NULL, '2018-04-16 18:35:36.000000', 'Generator', '000000000'),
	('579', 0, 'createBatchEntityToProcessBase', 'com.yugandhar.mdm.batch.component.BatchEntityToProcessService', 'add', 'create record in the database', '2018-04-16 18:35:36.000000', NULL, '2018-04-16 18:35:36.000000', 'Generator', '000000000'),
	('58', 0, 'retrieveLeAddressAssocBase', 'com.yugandhar.mdm.corecomponent.LeAddressAssocService', 'findById', 'find by business key method search properties based on transaction Name', '2018-04-16 18:35:31.000000', NULL, '2018-04-16 18:35:31.000000', 'Generator', '000000000'),
	('580', 0, 'updateBatchEntityToProcessBase', 'com.yugandhar.mdm.batch.component.BatchEntityToProcessService', 'merge', 'update the database record based on primary key i.e. idpk', '2018-04-16 18:35:36.000000', NULL, '2018-04-16 18:35:36.000000', 'Generator', '000000000'),
	('581', 0, 'retrieveBatchEntityToProcessBase', 'com.yugandhar.mdm.batch.component.BatchEntityToProcessService', 'findById', 'retrieve the record from database based on primary key i.e. idpk', '2018-04-16 18:35:36.000000', NULL, '2018-04-16 18:35:36.000000', 'Generator', '000000000'),
	('582', 0, 'createRefBatchActionStatusBase', 'com.yugandhar.mdm.batch.componentref.RefBatchActionStatusService', 'add', 'create record in the database', '2018-04-16 18:35:36.000000', NULL, '2018-04-16 18:35:36.000000', 'Generator', '000000000'),
	('583', 0, 'updateRefBatchActionStatusBase', 'com.yugandhar.mdm.batch.componentref.RefBatchActionStatusService', 'merge', 'update the database record based on primary key i.e. idpk', '2018-04-16 18:35:36.000000', NULL, '2018-04-16 18:35:36.000000', 'Generator', '000000000'),
	('584', 0, 'retrieveRefBatchActionStatusBase', 'com.yugandhar.mdm.batch.componentref.RefBatchActionStatusService', 'findById', 'retrieve the record from database based on primary key i.e. idpk', '2018-04-16 18:35:36.000000', NULL, '2018-04-16 18:35:36.000000', 'Generator', '000000000'),
	('585', 0, 'findRefBatchActionStatusByBusinessKeyBase', 'com.yugandhar.mdm.batch.componentref.RefBatchActionStatusService', 'findByBusinessKey', 'find the unique record from dababase based on by business key', '2018-04-16 18:35:36.000000', NULL, '2018-04-16 18:35:36.000000', 'Generator', '000000000'),
	('586', 0, 'findAllRefBatchActionStatusByLanguageCodeBase', 'com.yugandhar.mdm.batch.componentref.RefBatchActionStatusService', 'findAllRecordsByLanguageCode', 'find All records by language code', '2018-04-16 18:35:36.000000', NULL, '2018-04-16 18:35:36.000000', 'Generator', '000000000'),
	('587', 0, 'createRefBatchProposedActionBase', 'com.yugandhar.mdm.batch.componentref.RefBatchProposedActionService', 'add', 'create record in the database', '2018-04-16 18:35:36.000000', NULL, '2018-04-16 18:35:36.000000', 'Generator', '000000000'),
	('588', 0, 'updateRefBatchProposedActionBase', 'com.yugandhar.mdm.batch.componentref.RefBatchProposedActionService', 'merge', 'update the database record based on primary key i.e. idpk', '2018-04-16 18:35:36.000000', NULL, '2018-04-16 18:35:36.000000', 'Generator', '000000000'),
	('589', 0, 'retrieveRefBatchProposedActionBase', 'com.yugandhar.mdm.batch.componentref.RefBatchProposedActionService', 'findById', 'retrieve the record from database based on primary key i.e. idpk', '2018-04-16 18:35:36.000000', NULL, '2018-04-16 18:35:36.000000', 'Generator', '000000000'),
	('59', 0, 'createLeCorporationBase', 'com.yugandhar.mdm.corecomponent.LeCorporationService', 'add', 'find by business key method search properties based on transaction Name', '2018-04-16 18:35:31.000000', NULL, '2018-04-16 18:35:31.000000', 'Generator', '000000000'),
	('590', 0, 'findRefBatchProposedActionByBusinessKeyBase', 'com.yugandhar.mdm.batch.componentref.RefBatchProposedActionService', 'findByBusinessKey', 'find the unique record from dababase based on by business key', '2018-04-16 18:35:36.000000', NULL, '2018-04-16 18:35:36.000000', 'Generator', '000000000'),
	('591', 0, 'findAllRefBatchProposedActionByLanguageCodeBase', 'com.yugandhar.mdm.batch.componentref.RefBatchProposedActionService', 'findAllRecordsByLanguageCode', 'find All records by language code', '2018-04-16 18:35:36.000000', NULL, '2018-04-16 18:35:36.000000', 'Generator', '000000000'),
	('6', 0, 'updateConfigErrorcodeRegistryBase', 'com.yugandhar.mdm.config.errorcoderegistry.ConfigErrorcodeRegistryService', 'merge', 'find by business key method search properties based on transaction Name', '2018-04-16 18:35:32.000000', NULL, '2018-04-16 18:35:32.000000', 'Generator', '000000000'),
	('60', 0, 'updateLeCorporationBase', 'com.yugandhar.mdm.corecomponent.LeCorporationService', 'merge', 'find by business key method search properties based on transaction Name', '2018-04-16 18:35:31.000000', NULL, '2018-04-16 18:35:31.000000', 'Generator', '000000000'),
	('61', 0, 'retrieveLeCorporationBase', 'com.yugandhar.mdm.corecomponent.LeCorporationService', 'findById', 'find by business key method search properties based on transaction Name', '2018-04-16 18:35:31.000000', NULL, '2018-04-16 18:35:31.000000', 'Generator', '000000000'),
	('62', 0, 'createLeIdentifierKycRegistryBase', 'com.yugandhar.mdm.corecomponent.LeIdentifierKycRegistryService', 'add', 'find by business key method search properties based on transaction Name', '2018-04-16 18:35:31.000000', NULL, '2018-04-16 18:35:31.000000', 'Generator', '000000000'),
	('63', 0, 'updateLeIdentifierKycRegistryBase', 'com.yugandhar.mdm.corecomponent.LeIdentifierKycRegistryService', 'merge', 'find by business key method search properties based on transaction Name', '2018-04-16 18:35:31.000000', NULL, '2018-04-16 18:35:31.000000', 'Generator', '000000000'),
	('64', 0, 'retrieveLeIdentifierKycRegistryBase', 'com.yugandhar.mdm.corecomponent.LeIdentifierKycRegistryService', 'findById', 'find by business key method search properties based on transaction Name', '2018-04-16 18:35:31.000000', NULL, '2018-04-16 18:35:31.000000', 'Generator', '000000000'),
	('65', 0, 'createLePersonBase', 'com.yugandhar.mdm.corecomponent.LePersonService', 'add', 'find by business key method search properties based on transaction Name', '2018-04-16 18:35:31.000000', NULL, '2018-04-16 18:35:31.000000', 'Generator', '000000000'),
	('66', 0, 'updateLePersonBase', 'com.yugandhar.mdm.corecomponent.LePersonService', 'merge', 'find by business key method search properties based on transaction Name', '2018-04-16 18:35:31.000000', NULL, '2018-04-16 18:35:31.000000', 'Generator', '000000000'),
	('67', 0, 'retrieveLePersonBase', 'com.yugandhar.mdm.corecomponent.LePersonService', 'findById', 'find by business key method search properties based on transaction Name', '2018-04-16 18:35:31.000000', NULL, '2018-04-16 18:35:31.000000', 'Generator', '000000000'),
	('68', 0, 'createLePhoneAssocBase', 'com.yugandhar.mdm.corecomponent.LePhoneAssocService', 'add', 'find by business key method search properties based on transaction Name', '2018-04-16 18:35:31.000000', NULL, '2018-04-16 18:35:31.000000', 'Generator', '000000000'),
	('69', 0, 'updateLePhoneAssocBase', 'com.yugandhar.mdm.corecomponent.LePhoneAssocService', 'merge', 'find by business key method search properties based on transaction Name', '2018-04-16 18:35:31.000000', NULL, '2018-04-16 18:35:31.000000', 'Generator', '000000000'),
	('7', 0, 'retrieveConfigErrorcodeRegistryBase', 'com.yugandhar.mdm.config.errorcoderegistry.ConfigErrorcodeRegistryService', 'findById', 'find by business key method search properties based on transaction Name', '2018-04-16 18:35:32.000000', NULL, '2018-04-16 18:35:32.000000', 'Generator', '000000000'),
	('70', 0, 'retrieveLePhoneAssocBase', 'com.yugandhar.mdm.corecomponent.LePhoneAssocService', 'findById', 'find by business key method search properties based on transaction Name', '2018-04-16 18:35:31.000000', NULL, '2018-04-16 18:35:31.000000', 'Generator', '000000000'),
	('71', 0, 'createLePreferencesBase', 'com.yugandhar.mdm.corecomponent.LePreferencesService', 'add', 'find by business key method search properties based on transaction Name', '2018-04-16 18:35:31.000000', NULL, '2018-04-16 18:35:31.000000', 'Generator', '000000000'),
	('72', 0, 'updateLePreferencesBase', 'com.yugandhar.mdm.corecomponent.LePreferencesService', 'merge', 'find by business key method search properties based on transaction Name', '2018-04-16 18:35:31.000000', NULL, '2018-04-16 18:35:31.000000', 'Generator', '000000000'),
	('73', 0, 'retrieveLePreferencesBase', 'com.yugandhar.mdm.corecomponent.LePreferencesService', 'findById', 'find by business key method search properties based on transaction Name', '2018-04-16 18:35:31.000000', NULL, '2018-04-16 18:35:31.000000', 'Generator', '000000000'),
	('74', 0, 'createLePropertyAssocBase', 'com.yugandhar.mdm.corecomponent.LePropertyAssocService', 'add', 'find by business key method search properties based on transaction Name', '2018-04-16 18:35:31.000000', NULL, '2018-04-16 18:35:31.000000', 'Generator', '000000000'),
	('75', 0, 'updateLePropertyAssocBase', 'com.yugandhar.mdm.corecomponent.LePropertyAssocService', 'merge', 'find by business key method search properties based on transaction Name', '2018-04-16 18:35:31.000000', NULL, '2018-04-16 18:35:31.000000', 'Generator', '000000000'),
	('76', 0, 'retrieveLePropertyAssocBase', 'com.yugandhar.mdm.corecomponent.LePropertyAssocService', 'findById', 'find by business key method search properties based on transaction Name', '2018-04-16 18:35:31.000000', NULL, '2018-04-16 18:35:31.000000', 'Generator', '000000000'),
	('77', 0, 'createLeSystemKeysRegistryBase', 'com.yugandhar.mdm.corecomponent.LeSystemKeysRegistryService', 'add', 'find by business key method search properties based on transaction Name', '2018-04-16 18:35:31.000000', NULL, '2018-04-16 18:35:31.000000', 'Generator', '000000000'),
	('78', 0, 'updateLeSystemKeysRegistryBase', 'com.yugandhar.mdm.corecomponent.LeSystemKeysRegistryService', 'merge', 'find by business key method search properties based on transaction Name', '2018-04-16 18:35:31.000000', NULL, '2018-04-16 18:35:31.000000', 'Generator', '000000000'),
	('79', 0, 'retrieveLeSystemKeysRegistryBase', 'com.yugandhar.mdm.corecomponent.LeSystemKeysRegistryService', 'findById', 'find by business key method search properties based on transaction Name', '2018-04-16 18:35:31.000000', NULL, '2018-04-16 18:35:31.000000', 'Generator', '000000000'),
	('8', 0, 'findConfigErrorcodeRegistryByBusinessKeyBase', 'com.yugandhar.mdm.config.errorcoderegistry.ConfigErrorcodeRegistryService', 'findByBusinessKey', 'find by business key method search properties based on transaction Name', '2018-04-16 18:35:32.000000', NULL, '2018-04-16 18:35:32.000000', 'Generator', '000000000'),
	('80', 0, 'createLeToLeRelationshipBase', 'com.yugandhar.mdm.corecomponent.LeToLeRelationshipService', 'add', 'find by business key method search properties based on transaction Name', '2018-04-16 18:35:31.000000', NULL, '2018-04-16 18:35:31.000000', 'Generator', '000000000'),
	('81', 0, 'updateLeToLeRelationshipBase', 'com.yugandhar.mdm.corecomponent.LeToLeRelationshipService', 'merge', 'find by business key method search properties based on transaction Name', '2018-04-16 18:35:31.000000', NULL, '2018-04-16 18:35:31.000000', 'Generator', '000000000'),
	('82', 0, 'retrieveLeToLeRelationshipBase', 'com.yugandhar.mdm.corecomponent.LeToLeRelationshipService', 'findById', 'find by business key method search properties based on transaction Name', '2018-04-16 18:35:31.000000', NULL, '2018-04-16 18:35:31.000000', 'Generator', '000000000'),
	('83', 0, 'createLeVehicleAssocBase', 'com.yugandhar.mdm.corecomponent.LeVehicleAssocService', 'add', 'find by business key method search properties based on transaction Name', '2018-04-16 18:35:31.000000', NULL, '2018-04-16 18:35:31.000000', 'Generator', '000000000'),
	('84', 0, 'updateLeVehicleAssocBase', 'com.yugandhar.mdm.corecomponent.LeVehicleAssocService', 'merge', 'find by business key method search properties based on transaction Name', '2018-04-16 18:35:31.000000', NULL, '2018-04-16 18:35:31.000000', 'Generator', '000000000'),
	('85', 0, 'retrieveLeVehicleAssocBase', 'com.yugandhar.mdm.corecomponent.LeVehicleAssocService', 'findById', 'find by business key method search properties based on transaction Name', '2018-04-16 18:35:31.000000', NULL, '2018-04-16 18:35:31.000000', 'Generator', '000000000'),
	('86', 0, 'createMiscellaneousInfoBase', 'com.yugandhar.mdm.corecomponent.MiscellaneousInfoService', 'add', 'find by business key method search properties based on transaction Name', '2018-04-16 18:35:31.000000', NULL, '2018-04-16 18:35:31.000000', 'Generator', '000000000'),
	('87', 0, 'updateMiscellaneousInfoBase', 'com.yugandhar.mdm.corecomponent.MiscellaneousInfoService', 'merge', 'find by business key method search properties based on transaction Name', '2018-04-16 18:35:31.000000', NULL, '2018-04-16 18:35:31.000000', 'Generator', '000000000'),
	('88', 0, 'retrieveMiscellaneousInfoBase', 'com.yugandhar.mdm.corecomponent.MiscellaneousInfoService', 'findById', 'find by business key method search properties based on transaction Name', '2018-04-16 18:35:31.000000', NULL, '2018-04-16 18:35:31.000000', 'Generator', '000000000'),
	('89', 0, 'createPersonnamesBase', 'com.yugandhar.mdm.corecomponent.PersonnamesService', 'add', 'find by business key method search properties based on transaction Name', '2018-04-16 18:35:31.000000', NULL, '2018-04-16 18:35:31.000000', 'Generator', '000000000'),
	('9', 0, 'findAllConfigErrorcodeRegistryByLanguageCodeBase', 'com.yugandhar.mdm.config.errorcoderegistry.ConfigErrorcodeRegistryService', 'findAllRecordsByLanguageCode', 'find All records by language code', '2018-04-16 18:35:32.000000', NULL, '2018-04-16 18:35:32.000000', 'Generator', '000000000'),
	('90', 0, 'updatePersonnamesBase', 'com.yugandhar.mdm.corecomponent.PersonnamesService', 'merge', 'find by business key method search properties based on transaction Name', '2018-04-16 18:35:31.000000', NULL, '2018-04-16 18:35:31.000000', 'Generator', '000000000'),
	('91', 0, 'retrievePersonnamesBase', 'com.yugandhar.mdm.corecomponent.PersonnamesService', 'findById', 'find by business key method search properties based on transaction Name', '2018-04-16 18:35:31.000000', NULL, '2018-04-16 18:35:31.000000', 'Generator', '000000000'),
	('92', 0, 'createPhoneStandardizedBase', 'com.yugandhar.mdm.corecomponent.PhoneStandardizedService', 'add', 'find by business key method search properties based on transaction Name', '2018-04-16 18:35:31.000000', NULL, '2018-04-16 18:35:31.000000', 'Generator', '000000000'),
	('93', 0, 'updatePhoneStandardizedBase', 'com.yugandhar.mdm.corecomponent.PhoneStandardizedService', 'merge', 'find by business key method search properties based on transaction Name', '2018-04-16 18:35:31.000000', NULL, '2018-04-16 18:35:31.000000', 'Generator', '000000000'),
	('94', 0, 'retrievePhoneStandardizedBase', 'com.yugandhar.mdm.corecomponent.PhoneStandardizedService', 'findById', 'find by business key method search properties based on transaction Name', '2018-04-16 18:35:31.000000', NULL, '2018-04-16 18:35:31.000000', 'Generator', '000000000'),
	('95', 0, 'createPropertyBase', 'com.yugandhar.mdm.corecomponent.PropertyService', 'add', 'find by business key method search properties based on transaction Name', '2018-04-16 18:35:31.000000', NULL, '2018-04-16 18:35:31.000000', 'Generator', '000000000'),
	('96', 0, 'updatePropertyBase', 'com.yugandhar.mdm.corecomponent.PropertyService', 'merge', 'find by business key method search properties based on transaction Name', '2018-04-16 18:35:31.000000', NULL, '2018-04-16 18:35:31.000000', 'Generator', '000000000'),
	('97', 0, 'retrievePropertyBase', 'com.yugandhar.mdm.corecomponent.PropertyService', 'findById', 'find by business key method search properties based on transaction Name', '2018-04-16 18:35:31.000000', NULL, '2018-04-16 18:35:31.000000', 'Generator', '000000000'),
	('98', 0, 'createVehicleBase', 'com.yugandhar.mdm.corecomponent.VehicleService', 'add', 'find by business key method search properties based on transaction Name', '2018-04-16 18:35:31.000000', NULL, '2018-04-16 18:35:31.000000', 'Generator', '000000000'),
	('99', 0, 'updateVehicleBase', 'com.yugandhar.mdm.corecomponent.VehicleService', 'merge', 'find by business key method search properties based on transaction Name', '2018-04-16 18:35:31.000000', NULL, '2018-04-16 18:35:31.000000', 'Generator', '000000000');
/*!40000 ALTER TABLE `config_txn_registry` ENABLE KEYS */;

-- Dumping structure for table yug_owner.corporationnames
CREATE TABLE IF NOT EXISTS `corporationnames` (
  `ID_PK` varchar(50) COLLATE utf8_bin NOT NULL,
  `VERSION` decimal(22,0) NOT NULL,
  `CREATED_TS` datetime(6) NOT NULL,
  `DELETED_TS` datetime(6) DEFAULT NULL,
  `UPDATED_TS` datetime(6) NOT NULL,
  `UPDATED_BY_USER` varchar(50) COLLATE utf8_bin NOT NULL,
  `UPDATED_BY_TXN_ID` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `LEGALENTITY_IDPK` varchar(50) COLLATE utf8_bin NOT NULL,
  `CORPORATION_NAME_TYPE_REFKEY` varchar(50) COLLATE utf8_bin NOT NULL,
  `CORPORATION_NAME` varchar(100) COLLATE utf8_bin NOT NULL,
  `SOURCE_SYSTEM_REFKEY` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `PHONETIC_CORPORATION_NAME` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_PK`),
  UNIQUE KEY `CORPORATIONNAMES_UNIQKEY` (`CORPORATION_NAME_TYPE_REFKEY`,`LEGALENTITY_IDPK`),
  KEY `INDX_CORPORATIONNAMES_5` (`PHONETIC_CORPORATION_NAME`),
  KEY `INDX_CORPORATIONNAMES_4` (`SOURCE_SYSTEM_REFKEY`),
  KEY `INDX_CORPORATIONNAMES_3` (`CORPORATION_NAME`),
  KEY `INDX_CORPORATIONNAMES_2` (`CORPORATION_NAME_TYPE_REFKEY`),
  KEY `INDX_CORPORATIONNAMES_1` (`LEGALENTITY_IDPK`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- Dumping data for table yug_owner.corporationnames: ~0 rows (approximately)
/*!40000 ALTER TABLE `corporationnames` DISABLE KEYS */;
/*!40000 ALTER TABLE `corporationnames` ENABLE KEYS */;

-- Dumping structure for table yug_owner.entity_group
CREATE TABLE IF NOT EXISTS `entity_group` (
  `ID_PK` varchar(50) COLLATE utf8_bin NOT NULL,
  `VERSION` decimal(22,0) NOT NULL,
  `CREATED_TS` datetime(6) NOT NULL,
  `DELETED_TS` datetime(6) DEFAULT NULL,
  `UPDATED_TS` datetime(6) NOT NULL,
  `UPDATED_BY_USER` varchar(50) COLLATE utf8_bin NOT NULL,
  `UPDATED_BY_TXN_ID` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `GROUP_TYPE_REFKEY` varchar(50) COLLATE utf8_bin NOT NULL,
  `GROUP_SUBTYPE_REFKEY` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `GROUP_NAME` varchar(50) COLLATE utf8_bin NOT NULL,
  `DESCRIPTION` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_PK`),
  KEY `INDX_ENTITY_GROUP_1` (`GROUP_TYPE_REFKEY`),
  KEY `INDX_ENTITY_GROUP_2` (`GROUP_SUBTYPE_REFKEY`),
  KEY `INDX_ENTITY_GROUP_3` (`GROUP_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- Dumping data for table yug_owner.entity_group: ~0 rows (approximately)
/*!40000 ALTER TABLE `entity_group` DISABLE KEYS */;
/*!40000 ALTER TABLE `entity_group` ENABLE KEYS */;

-- Dumping structure for table yug_owner.entity_group_assoc
CREATE TABLE IF NOT EXISTS `entity_group_assoc` (
  `ID_PK` varchar(50) COLLATE utf8_bin NOT NULL,
  `VERSION` decimal(22,0) NOT NULL,
  `CREATED_TS` datetime(6) NOT NULL,
  `DELETED_TS` datetime(6) DEFAULT NULL,
  `UPDATED_TS` datetime(6) NOT NULL,
  `UPDATED_BY_USER` varchar(50) COLLATE utf8_bin NOT NULL,
  `UPDATED_BY_TXN_ID` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `ENTITY_OBJECT_TYPE_REFKEY` varchar(50) COLLATE utf8_bin NOT NULL,
  `ENTITY_IDPK` varchar(50) COLLATE utf8_bin NOT NULL,
  `ENTITY_GROUP_IDPK` varchar(50) COLLATE utf8_bin NOT NULL,
  `ASSOC_TYPE_REFKEY` varchar(50) COLLATE utf8_bin NOT NULL,
  `DESCRIPTION` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_PK`),
  UNIQUE KEY `ENTITYGROUPASSOC_UNIQUEKEY` (`ASSOC_TYPE_REFKEY`,`ENTITY_GROUP_IDPK`,`ENTITY_IDPK`,`ENTITY_OBJECT_TYPE_REFKEY`),
  KEY `INDX_ENTITY_GROUP_ASSOC_1` (`ENTITY_OBJECT_TYPE_REFKEY`),
  KEY `INDX_ENTITY_GROUP_ASSOC_2` (`ENTITY_IDPK`),
  KEY `INDX_ENTITY_GROUP_ASSOC_3` (`ENTITY_GROUP_IDPK`),
  KEY `INDX_ENTITY_GROUP_ASSOC_4` (`ASSOC_TYPE_REFKEY`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- Dumping data for table yug_owner.entity_group_assoc: ~0 rows (approximately)
/*!40000 ALTER TABLE `entity_group_assoc` DISABLE KEYS */;
/*!40000 ALTER TABLE `entity_group_assoc` ENABLE KEYS */;

-- Dumping structure for table yug_owner.inactive_le_registry
CREATE TABLE IF NOT EXISTS `inactive_le_registry` (
  `ID_PK` varchar(50) COLLATE utf8_bin NOT NULL,
  `VERSION` decimal(22,0) NOT NULL,
  `CREATED_TS` datetime(6) NOT NULL,
  `DELETED_TS` datetime(6) DEFAULT NULL,
  `UPDATED_TS` datetime(6) NOT NULL,
  `UPDATED_BY_USER` varchar(50) COLLATE utf8_bin NOT NULL,
  `UPDATED_BY_TXN_ID` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `LEGALENTITY_IDPK` varchar(50) COLLATE utf8_bin NOT NULL,
  `INACTIVATED_TS` datetime(6) NOT NULL,
  `INACTIVATION_REASON_REFKEY` varchar(50) COLLATE utf8_bin NOT NULL,
  `COMMENTS` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_PK`),
  KEY `INDX_INACTIVE_LE_REGISTRY_1` (`LEGALENTITY_IDPK`),
  KEY `INDX_INACTIVE_LE_REGISTRY_2` (`INACTIVATED_TS`),
  KEY `INDX_INACTIVE_LE_REGISTRY_3` (`INACTIVATION_REASON_REFKEY`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- Dumping data for table yug_owner.inactive_le_registry: ~0 rows (approximately)
/*!40000 ALTER TABLE `inactive_le_registry` DISABLE KEYS */;
/*!40000 ALTER TABLE `inactive_le_registry` ENABLE KEYS */;

-- Dumping structure for table yug_owner.legalentity
CREATE TABLE IF NOT EXISTS `legalentity` (
  `ID_PK` varchar(50) COLLATE utf8_bin NOT NULL,
  `VERSION` decimal(22,0) NOT NULL,
  `CREATED_TS` datetime(6) NOT NULL,
  `DELETED_TS` datetime(6) DEFAULT NULL,
  `UPDATED_TS` datetime(6) NOT NULL,
  `UPDATED_BY_USER` varchar(50) COLLATE utf8_bin NOT NULL,
  `UPDATED_BY_TXN_ID` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `DISPLAY_NAME` varchar(100) COLLATE utf8_bin NOT NULL,
  `ENTITY_OBJECT_TYPE_REFKEY` varchar(50) COLLATE utf8_bin NOT NULL,
  `CLASSIFICATION_CODE_REFKEY` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `IMPORTANCE_TYPE_REFKEY` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `LE_RATING_REFKEY` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `STATUS_TYPE_REFKEY` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `SOURCE_SYSTEM_REFKEY` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `ONBOARDING_DATE` datetime(6) DEFAULT NULL,
  `OFFBOARDING_DATE` datetime(6) DEFAULT NULL,
  `KYC_VERIFICATION_FLAG` varchar(1) COLLATE utf8_bin DEFAULT NULL,
  `DESCRIPTION` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `PHONETIC_DISPLAY_NAME` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_PK`),
  KEY `INDX_LEGALENTITY_9` (`OFFBOARDING_DATE`),
  KEY `INDX_LEGALENTITY_8` (`ONBOARDING_DATE`),
  KEY `INDX_LEGALENTITY_7` (`SOURCE_SYSTEM_REFKEY`),
  KEY `INDX_LEGALENTITY_6` (`STATUS_TYPE_REFKEY`),
  KEY `INDX_LEGALENTITY_5` (`LE_RATING_REFKEY`),
  KEY `INDX_LEGALENTITY_4` (`IMPORTANCE_TYPE_REFKEY`),
  KEY `INDX_LEGALENTITY_3` (`CLASSIFICATION_CODE_REFKEY`),
  KEY `INDX_LEGALENTITY_2` (`ENTITY_OBJECT_TYPE_REFKEY`),
  KEY `INDX_LEGALENTITY_11` (`PHONETIC_DISPLAY_NAME`),
  KEY `INDX_LEGALENTITY_10` (`KYC_VERIFICATION_FLAG`),
  KEY `INDX_LEGALENTITY_1` (`DISPLAY_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- Dumping data for table yug_owner.legalentity: ~0 rows (approximately)
/*!40000 ALTER TABLE `legalentity` DISABLE KEYS */;
/*!40000 ALTER TABLE `legalentity` ENABLE KEYS */;

-- Dumping structure for table yug_owner.le_account_assoc
CREATE TABLE IF NOT EXISTS `le_account_assoc` (
  `ID_PK` varchar(50) COLLATE utf8_bin NOT NULL,
  `VERSION` decimal(22,0) NOT NULL,
  `CREATED_TS` datetime(6) NOT NULL,
  `DELETED_TS` datetime(6) DEFAULT NULL,
  `UPDATED_TS` datetime(6) NOT NULL,
  `UPDATED_BY_USER` varchar(50) COLLATE utf8_bin NOT NULL,
  `UPDATED_BY_TXN_ID` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `LEGALENTITY_IDPK` varchar(50) COLLATE utf8_bin NOT NULL,
  `LE_ROLETYPE_REFKEY` varchar(50) COLLATE utf8_bin NOT NULL,
  `ACCOUNT_IDPK` varchar(50) COLLATE utf8_bin NOT NULL,
  `ROLE_ACTIVATION_DATE` datetime(6) DEFAULT NULL,
  `ROLE_DEACTIVATION_DATE` datetime(6) DEFAULT NULL,
  `DEACTIVATION_REASON_REFKEY` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `AGREEMENT_TYPE_REFKEY` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `AGREEMENT_TYPE_DESCRIPTION` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_PK`),
  UNIQUE KEY `LEACCOUNTASSOC__UNIQUEKEY` (`ACCOUNT_IDPK`,`LE_ROLETYPE_REFKEY`,`LEGALENTITY_IDPK`),
  KEY `INDX_LE_ACCOUNT_ASSOC_7` (`AGREEMENT_TYPE_REFKEY`),
  KEY `INDX_LE_ACCOUNT_ASSOC_6` (`DEACTIVATION_REASON_REFKEY`),
  KEY `INDX_LE_ACCOUNT_ASSOC_5` (`ROLE_DEACTIVATION_DATE`),
  KEY `INDX_LE_ACCOUNT_ASSOC_4` (`ROLE_ACTIVATION_DATE`),
  KEY `INDX_LE_ACCOUNT_ASSOC_3` (`ACCOUNT_IDPK`),
  KEY `INDX_LE_ACCOUNT_ASSOC_2` (`LE_ROLETYPE_REFKEY`),
  KEY `INDX_LE_ACCOUNT_ASSOC_1` (`LEGALENTITY_IDPK`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- Dumping data for table yug_owner.le_account_assoc: ~0 rows (approximately)
/*!40000 ALTER TABLE `le_account_assoc` DISABLE KEYS */;
/*!40000 ALTER TABLE `le_account_assoc` ENABLE KEYS */;

-- Dumping structure for table yug_owner.le_address_assoc
CREATE TABLE IF NOT EXISTS `le_address_assoc` (
  `ID_PK` varchar(50) COLLATE utf8_bin NOT NULL,
  `VERSION` decimal(22,0) NOT NULL,
  `CREATED_TS` datetime(6) NOT NULL,
  `DELETED_TS` datetime(6) DEFAULT NULL,
  `UPDATED_TS` datetime(6) NOT NULL,
  `UPDATED_BY_USER` varchar(50) COLLATE utf8_bin NOT NULL,
  `UPDATED_BY_TXN_ID` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `LEGALENTITY_IDPK` varchar(50) COLLATE utf8_bin NOT NULL,
  `ADDRESS_IDPK` varchar(50) COLLATE utf8_bin NOT NULL,
  `ADDRESS_TYPE_REFKEY` varchar(50) COLLATE utf8_bin NOT NULL,
  `ADDRESS_SUBTYPE_REFKEY` varchar(50) COLLATE utf8_bin NOT NULL,
  `PREFERRED_FLAG` varchar(1) COLLATE utf8_bin DEFAULT NULL,
  `SOLICITATION_FLAG` varchar(1) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_PK`),
  UNIQUE KEY `LEADDRESSASSOC_UNIQKEY` (`LEGALENTITY_IDPK`,`ADDRESS_SUBTYPE_REFKEY`,`ADDRESS_TYPE_REFKEY`),
  KEY `INDX_LE_ADDRESS_ASSOC_4` (`ADDRESS_SUBTYPE_REFKEY`),
  KEY `INDX_LE_ADDRESS_ASSOC_3` (`ADDRESS_TYPE_REFKEY`),
  KEY `INDX_LE_ADDRESS_ASSOC_2` (`ADDRESS_IDPK`),
  KEY `INDX_LE_ADDRESS_ASSOC_1` (`LEGALENTITY_IDPK`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- Dumping data for table yug_owner.le_address_assoc: ~0 rows (approximately)
/*!40000 ALTER TABLE `le_address_assoc` DISABLE KEYS */;
/*!40000 ALTER TABLE `le_address_assoc` ENABLE KEYS */;

-- Dumping structure for table yug_owner.le_corporation
CREATE TABLE IF NOT EXISTS `le_corporation` (
  `LEGALENTITY_IDPK` varchar(50) COLLATE utf8_bin NOT NULL,
  `VERSION` decimal(22,0) NOT NULL,
  `CREATED_TS` datetime(6) NOT NULL,
  `DELETED_TS` datetime(6) DEFAULT NULL,
  `UPDATED_TS` datetime(6) NOT NULL,
  `UPDATED_BY_USER` varchar(50) COLLATE utf8_bin NOT NULL,
  `UPDATED_BY_TXN_ID` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `CLASSIFICATION_CODE_REFKEY` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `INDUSTRY_CODE_REFKEY` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `GOVT_REGISTRATION_DATE` datetime(6) DEFAULT NULL,
  `COUNTRY_REGISTRATION_REFKEY` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `NOTPROFIT_FLAG` varchar(1) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`LEGALENTITY_IDPK`),
  KEY `INDX_LE_CORPORATION_1` (`CLASSIFICATION_CODE_REFKEY`),
  KEY `INDX_LE_CORPORATION_2` (`INDUSTRY_CODE_REFKEY`),
  KEY `INDX_LE_CORPORATION_3` (`GOVT_REGISTRATION_DATE`),
  KEY `INDX_LE_CORPORATION_4` (`COUNTRY_REGISTRATION_REFKEY`),
  KEY `INDX_LE_CORPORATION_5` (`NOTPROFIT_FLAG`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- Dumping data for table yug_owner.le_corporation: ~0 rows (approximately)
/*!40000 ALTER TABLE `le_corporation` DISABLE KEYS */;
/*!40000 ALTER TABLE `le_corporation` ENABLE KEYS */;

-- Dumping structure for table yug_owner.le_identifier_kyc_registry
CREATE TABLE IF NOT EXISTS `le_identifier_kyc_registry` (
  `ID_PK` varchar(50) COLLATE utf8_bin NOT NULL,
  `VERSION` decimal(22,0) NOT NULL,
  `CREATED_TS` datetime(6) NOT NULL,
  `DELETED_TS` datetime(6) DEFAULT NULL,
  `UPDATED_TS` datetime(6) NOT NULL,
  `UPDATED_BY_USER` varchar(50) COLLATE utf8_bin NOT NULL,
  `UPDATED_BY_TXN_ID` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `IDENTIFICATION_TYPE_REFKEY` varchar(50) COLLATE utf8_bin NOT NULL,
  `IDENTIFICATION_NUMBER` varchar(50) COLLATE utf8_bin NOT NULL,
  `LEGALENTITY_IDPK` varchar(50) COLLATE utf8_bin NOT NULL,
  `DOCUMENT` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `ISSUED_BY` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `ID_CONSIDERED_FOR_KYC_FLAG` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `ISSUED_DATE` datetime(6) DEFAULT NULL,
  `SOURCE_SYSTEM_REFKEY` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `IDENTITY_DESCRIPTION` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_PK`),
  UNIQUE KEY `LEIDENTKYCREG_UNIQUEKEY` (`LEGALENTITY_IDPK`,`IDENTIFICATION_TYPE_REFKEY`),
  KEY `INDX_LE_IDENTKYC_REG_1` (`IDENTIFICATION_TYPE_REFKEY`),
  KEY `INDX_LE_IDENTKYC_REG_2` (`IDENTIFICATION_NUMBER`),
  KEY `INDX_LE_IDENTKYC_REG_3` (`LEGALENTITY_IDPK`),
  KEY `INDX_LE_IDENTKYC_REG_4` (`SOURCE_SYSTEM_REFKEY`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- Dumping data for table yug_owner.le_identifier_kyc_registry: ~0 rows (approximately)
/*!40000 ALTER TABLE `le_identifier_kyc_registry` DISABLE KEYS */;
/*!40000 ALTER TABLE `le_identifier_kyc_registry` ENABLE KEYS */;

-- Dumping structure for table yug_owner.le_person
CREATE TABLE IF NOT EXISTS `le_person` (
  `LEGALENTITY_IDPK` varchar(50) COLLATE utf8_bin NOT NULL,
  `VERSION` decimal(22,0) NOT NULL,
  `CREATED_TS` datetime(6) NOT NULL,
  `DELETED_TS` datetime(6) DEFAULT NULL,
  `UPDATED_TS` datetime(6) NOT NULL,
  `UPDATED_BY_USER` varchar(50) COLLATE utf8_bin NOT NULL,
  `UPDATED_BY_TXN_ID` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `PERSON_TYPE_REFKEY` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `GENDER_REFKEY` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `DATE_OF_BIRTH` datetime DEFAULT NULL,
  `COUNTRY_OF_BIRTH__REFKEY` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `COUNTRY_CITIZENSHIP_REFKEY` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `COUNTRY_OF_DOMICILE__REFKEY` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `MARITAL_STATUS` varchar(30) COLLATE utf8_bin DEFAULT NULL,
  `HIGHEST_EDU_QUAL_REFKEY` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `IS_DECEASED_FLAG` varchar(1) COLLATE utf8_bin DEFAULT NULL,
  `DECEASED_DATE` datetime(6) DEFAULT NULL,
  `IS_HANDICAPPED_FLAG` varchar(1) COLLATE utf8_bin DEFAULT NULL,
  `HANDICAPPED_SINCE_DATE` datetime(6) DEFAULT NULL,
  `NUMBER_OF_DEPENDENTS` decimal(22,0) DEFAULT NULL,
  `NUMBER_OF_CHILDREN` decimal(22,0) DEFAULT NULL,
  `PREFERRED_LANGUAGE_REFKEY` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`LEGALENTITY_IDPK`),
  KEY `INDX_LE_PERSON_1` (`PERSON_TYPE_REFKEY`),
  KEY `INDX_LE_PERSON_2` (`COUNTRY_OF_BIRTH__REFKEY`),
  KEY `INDX_LE_PERSON_3` (`COUNTRY_CITIZENSHIP_REFKEY`),
  KEY `INDX_LE_PERSON_4` (`COUNTRY_OF_DOMICILE__REFKEY`),
  KEY `INDX_LE_PERSON_5` (`PREFERRED_LANGUAGE_REFKEY`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- Dumping data for table yug_owner.le_person: ~0 rows (approximately)
/*!40000 ALTER TABLE `le_person` DISABLE KEYS */;
/*!40000 ALTER TABLE `le_person` ENABLE KEYS */;

-- Dumping structure for table yug_owner.le_phone_assoc
CREATE TABLE IF NOT EXISTS `le_phone_assoc` (
  `ID_PK` varchar(50) COLLATE utf8_bin NOT NULL,
  `VERSION` decimal(22,0) NOT NULL,
  `CREATED_TS` datetime(6) NOT NULL,
  `DELETED_TS` datetime(6) DEFAULT NULL,
  `UPDATED_TS` datetime(6) NOT NULL,
  `UPDATED_BY_USER` varchar(50) COLLATE utf8_bin NOT NULL,
  `UPDATED_BY_TXN_ID` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `LEGALENTITY_IDPK` varchar(50) COLLATE utf8_bin NOT NULL,
  `PHONE_TYPE_REFKEY` varchar(50) COLLATE utf8_bin NOT NULL,
  `PHONE_SUBTYPE_REFKEY` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `PREFERRED_FLAG` varchar(1) COLLATE utf8_bin DEFAULT NULL,
  `PHONE_NUMBER` varchar(30) COLLATE utf8_bin NOT NULL,
  `PHONE_STANDARDIZED_IDPK` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_PK`),
  UNIQUE KEY `LEPHONEASSOC_UNIQUEKEY` (`PHONE_TYPE_REFKEY`,`LEGALENTITY_IDPK`),
  KEY `INDX_LE_PHONE_ASSOC_5` (`PHONE_STANDARDIZED_IDPK`),
  KEY `INDX_LE_PHONE_ASSOC_4` (`PHONE_NUMBER`),
  KEY `INDX_LE_PHONE_ASSOC_3` (`PHONE_SUBTYPE_REFKEY`),
  KEY `INDX_LE_PHONE_ASSOC_2` (`PHONE_TYPE_REFKEY`),
  KEY `INDX_LE_PHONE_ASSOC_1` (`LEGALENTITY_IDPK`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- Dumping data for table yug_owner.le_phone_assoc: ~0 rows (approximately)
/*!40000 ALTER TABLE `le_phone_assoc` DISABLE KEYS */;
/*!40000 ALTER TABLE `le_phone_assoc` ENABLE KEYS */;

-- Dumping structure for table yug_owner.le_preferences
CREATE TABLE IF NOT EXISTS `le_preferences` (
  `ID_PK` varchar(50) COLLATE utf8_bin NOT NULL,
  `VERSION` decimal(22,0) NOT NULL,
  `CREATED_TS` datetime(6) NOT NULL,
  `DELETED_TS` datetime(6) DEFAULT NULL,
  `UPDATED_TS` datetime(6) NOT NULL,
  `UPDATED_BY_USER` varchar(50) COLLATE utf8_bin NOT NULL,
  `UPDATED_BY_TXN_ID` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `LEGALENTITY_IDPK` varchar(50) COLLATE utf8_bin NOT NULL,
  `PREFERENCE_TYPE_REFKEY` varchar(50) COLLATE utf8_bin NOT NULL,
  `PREF_FLAG` varchar(1) COLLATE utf8_bin DEFAULT NULL,
  `PREF_DESCRIPTION` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_PK`),
  UNIQUE KEY `LEPREFERENCES_UNIQUEKEY` (`PREFERENCE_TYPE_REFKEY`,`LEGALENTITY_IDPK`),
  KEY `INDX_LE_PREFERENCES_1` (`LEGALENTITY_IDPK`),
  KEY `INDX_LE_PREFERENCES_2` (`PREFERENCE_TYPE_REFKEY`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- Dumping data for table yug_owner.le_preferences: ~0 rows (approximately)
/*!40000 ALTER TABLE `le_preferences` DISABLE KEYS */;
/*!40000 ALTER TABLE `le_preferences` ENABLE KEYS */;

-- Dumping structure for table yug_owner.le_property_assoc
CREATE TABLE IF NOT EXISTS `le_property_assoc` (
  `ID_PK` varchar(50) COLLATE utf8_bin NOT NULL,
  `VERSION` decimal(22,0) NOT NULL,
  `CREATED_TS` datetime(6) NOT NULL,
  `DELETED_TS` datetime(6) DEFAULT NULL,
  `UPDATED_TS` datetime(6) NOT NULL,
  `UPDATED_BY_USER` varchar(50) COLLATE utf8_bin NOT NULL,
  `UPDATED_BY_TXN_ID` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `PROPERTY_IDPK` varchar(50) COLLATE utf8_bin NOT NULL,
  `LEGALENTITY_IDPK` varchar(50) COLLATE utf8_bin NOT NULL,
  `PROPERTY_LE_RELTYPE_REFKEY` varchar(50) COLLATE utf8_bin NOT NULL,
  `DESCRIPTION` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_PK`),
  UNIQUE KEY `LEPROPERTYASSOC_UNIQUEKEY` (`PROPERTY_LE_RELTYPE_REFKEY`,`LEGALENTITY_IDPK`,`PROPERTY_IDPK`),
  KEY `INDX_LE_PROPERTY_ASSOC_1` (`PROPERTY_IDPK`),
  KEY `INDX_LE_PROPERTY_ASSOC_2` (`LEGALENTITY_IDPK`),
  KEY `INDX_LE_PROPERTY_ASSOC_3` (`PROPERTY_LE_RELTYPE_REFKEY`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- Dumping data for table yug_owner.le_property_assoc: ~0 rows (approximately)
/*!40000 ALTER TABLE `le_property_assoc` DISABLE KEYS */;
/*!40000 ALTER TABLE `le_property_assoc` ENABLE KEYS */;

-- Dumping structure for table yug_owner.le_system_keys_registry
CREATE TABLE IF NOT EXISTS `le_system_keys_registry` (
  `ID_PK` varchar(50) COLLATE utf8_bin NOT NULL,
  `VERSION` decimal(22,0) NOT NULL,
  `CREATED_TS` datetime(6) NOT NULL,
  `DELETED_TS` datetime(6) DEFAULT NULL,
  `UPDATED_TS` datetime(6) NOT NULL,
  `UPDATED_BY_USER` varchar(50) COLLATE utf8_bin NOT NULL,
  `UPDATED_BY_TXN_ID` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `SOURCE_SYSTEM_REFKEY` varchar(50) COLLATE utf8_bin NOT NULL,
  `REFERENCE_ID` varchar(50) COLLATE utf8_bin NOT NULL,
  `LEGALENTITY_IDPK` varchar(50) COLLATE utf8_bin NOT NULL,
  `STATUS_IN_SOURCE_REFKEY` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `DESCRIPTION` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_PK`),
  UNIQUE KEY `LESYSKEYREG_UNIQUEKEY` (`LEGALENTITY_IDPK`,`SOURCE_SYSTEM_REFKEY`),
  KEY `INDX_LE_SYSTEM_KEYS_REGISTRY_4` (`STATUS_IN_SOURCE_REFKEY`),
  KEY `INDX_LE_SYSTEM_KEYS_REGISTRY_3` (`LEGALENTITY_IDPK`),
  KEY `INDX_LE_SYSTEM_KEYS_REGISTRY_2` (`REFERENCE_ID`),
  KEY `INDX_LE_SYSTEM_KEYS_REGISTRY_1` (`SOURCE_SYSTEM_REFKEY`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- Dumping data for table yug_owner.le_system_keys_registry: ~0 rows (approximately)
/*!40000 ALTER TABLE `le_system_keys_registry` DISABLE KEYS */;
/*!40000 ALTER TABLE `le_system_keys_registry` ENABLE KEYS */;

-- Dumping structure for table yug_owner.le_to_le_relationship
CREATE TABLE IF NOT EXISTS `le_to_le_relationship` (
  `ID_PK` varchar(50) COLLATE utf8_bin NOT NULL,
  `VERSION` decimal(22,0) NOT NULL,
  `CREATED_TS` datetime(6) NOT NULL,
  `DELETED_TS` datetime(6) DEFAULT NULL,
  `UPDATED_TS` datetime(6) NOT NULL,
  `UPDATED_BY_USER` varchar(50) COLLATE utf8_bin NOT NULL,
  `UPDATED_BY_TXN_ID` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `FROM_LEGALENTITY_IDPK` varchar(50) COLLATE utf8_bin NOT NULL,
  `TO_LEGALENTITY_IDPK` varchar(50) COLLATE utf8_bin NOT NULL,
  `LE_RELATIONSHIP_TYPE_REFKEY` varchar(50) COLLATE utf8_bin NOT NULL,
  `RELATIONSHIP_STATUS_REFKEY` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `RELATIONSHIP_DESCRIPTION` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_PK`),
  UNIQUE KEY `LETOLERELATIONSHIP_UNIQUEKEY` (`LE_RELATIONSHIP_TYPE_REFKEY`,`TO_LEGALENTITY_IDPK`,`FROM_LEGALENTITY_IDPK`),
  KEY `INDX_LE_TO_LE_RELATIONSHIP_4` (`RELATIONSHIP_STATUS_REFKEY`),
  KEY `INDX_LE_TO_LE_RELATIONSHIP_3` (`LE_RELATIONSHIP_TYPE_REFKEY`),
  KEY `INDX_LE_TO_LE_RELATIONSHIP_2` (`TO_LEGALENTITY_IDPK`),
  KEY `INDX_LE_TO_LE_RELATIONSHIP_1` (`FROM_LEGALENTITY_IDPK`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- Dumping data for table yug_owner.le_to_le_relationship: ~0 rows (approximately)
/*!40000 ALTER TABLE `le_to_le_relationship` DISABLE KEYS */;
/*!40000 ALTER TABLE `le_to_le_relationship` ENABLE KEYS */;

-- Dumping structure for table yug_owner.le_vehicle_assoc
CREATE TABLE IF NOT EXISTS `le_vehicle_assoc` (
  `ID_PK` varchar(50) COLLATE utf8_bin NOT NULL,
  `VERSION` decimal(22,0) NOT NULL,
  `CREATED_TS` datetime(6) NOT NULL,
  `DELETED_TS` datetime(6) DEFAULT NULL,
  `UPDATED_TS` datetime(6) NOT NULL,
  `UPDATED_BY_USER` varchar(50) COLLATE utf8_bin NOT NULL,
  `UPDATED_BY_TXN_ID` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `LEGALENTITY_IDPK` varchar(50) COLLATE utf8_bin NOT NULL,
  `LE_ROLETYPE_REFKEY` varchar(50) COLLATE utf8_bin NOT NULL,
  `ACCOUNT_IDPK` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `ROLE_ACTIVATION_DATE` datetime(6) DEFAULT NULL,
  `ROLE_DEACTIVATION_DATE` datetime(6) DEFAULT NULL,
  `DEACTIVATION_REASON_REFKEY` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `AGREEMENT_TYPE_REFKEY` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `AGREEMENT_TYPE_DESCRIPTION` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `VEHICLE_IDPK` varchar(50) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`ID_PK`),
  KEY `INDX_LE_VEHICLE_ASSOC_6` (`VEHICLE_IDPK`),
  KEY `INDX_LE_VEHICLE_ASSOC_5` (`AGREEMENT_TYPE_REFKEY`),
  KEY `INDX_LE_VEHICLE_ASSOC_4` (`DEACTIVATION_REASON_REFKEY`),
  KEY `INDX_LE_VEHICLE_ASSOC_3` (`ACCOUNT_IDPK`),
  KEY `INDX_LE_VEHICLE_ASSOC_2` (`LE_ROLETYPE_REFKEY`),
  KEY `INDX_LE_VEHICLE_ASSOC_1` (`LEGALENTITY_IDPK`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- Dumping data for table yug_owner.le_vehicle_assoc: ~0 rows (approximately)
/*!40000 ALTER TABLE `le_vehicle_assoc` DISABLE KEYS */;
/*!40000 ALTER TABLE `le_vehicle_assoc` ENABLE KEYS */;

-- Dumping structure for table yug_owner.match_candidate_le_registry
CREATE TABLE IF NOT EXISTS `match_candidate_le_registry` (
  `ID_PK` varchar(50) COLLATE utf8_bin NOT NULL,
  `VERSION` decimal(22,0) NOT NULL,
  `CREATED_TS` datetime(6) NOT NULL,
  `DELETED_TS` datetime(6) DEFAULT NULL,
  `UPDATED_TS` datetime(6) NOT NULL,
  `UPDATED_BY_USER` varchar(50) COLLATE utf8_bin NOT NULL,
  `UPDATED_BY_TXN_ID` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `LEGALENTITY_IDPK` varchar(50) COLLATE utf8_bin NOT NULL,
  `CANDIDATE_LEGALENTITYIDPK` varchar(50) COLLATE utf8_bin NOT NULL,
  `MATCH_PATTERN` varchar(50) COLLATE utf8_bin NOT NULL,
  `MATCH_PROPOSED_ACTION_REFKEY` varchar(50) COLLATE utf8_bin NOT NULL,
  `MATCH_ACTIONSTATUS_REFKEY` varchar(50) COLLATE utf8_bin NOT NULL,
  `MATCH_PERCENTAGE_DESCRIPTION` varchar(500) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_PK`),
  KEY `INDX_MATCH_CAND_LE_REG_6` (`MATCH_PERCENTAGE_DESCRIPTION`(255)),
  KEY `INDX_MATCH_CAND_LE_REG_5` (`MATCH_ACTIONSTATUS_REFKEY`),
  KEY `INDX_MATCH_CAND_LE_REG_4` (`MATCH_PROPOSED_ACTION_REFKEY`),
  KEY `INDX_MATCH_CAND_LE_REG_3` (`MATCH_PATTERN`),
  KEY `INDX_MATCH_CAND_LE_REG_2` (`CANDIDATE_LEGALENTITYIDPK`),
  KEY `INDX_MATCH_CAND_LE_REG_1` (`LEGALENTITY_IDPK`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- Dumping data for table yug_owner.match_candidate_le_registry: ~0 rows (approximately)
/*!40000 ALTER TABLE `match_candidate_le_registry` DISABLE KEYS */;
/*!40000 ALTER TABLE `match_candidate_le_registry` ENABLE KEYS */;

-- Dumping structure for table yug_owner.match_merged_le_assoc
CREATE TABLE IF NOT EXISTS `match_merged_le_assoc` (
  `ID_PK` varchar(50) COLLATE utf8_bin NOT NULL,
  `VERSION` decimal(22,0) NOT NULL,
  `CREATED_TS` datetime(6) NOT NULL,
  `DELETED_TS` datetime(6) DEFAULT NULL,
  `UPDATED_TS` datetime(6) NOT NULL,
  `UPDATED_BY_USER` varchar(50) COLLATE utf8_bin NOT NULL,
  `UPDATED_BY_TXN_ID` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `SURVIVOR_LEGALENTITY_IDPK` varchar(50) COLLATE utf8_bin NOT NULL,
  `MERGED_LEGALENTITY_IDPK` varchar(50) COLLATE utf8_bin NOT NULL,
  `MERGE_REASON_REFKEY` varchar(50) COLLATE utf8_bin NOT NULL,
  `COMMENTS` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_PK`),
  KEY `INDX_MATCH_MERG_LE_ASSO_1` (`SURVIVOR_LEGALENTITY_IDPK`),
  KEY `INDX_MATCH_MERG_LE_ASSO_2` (`MERGED_LEGALENTITY_IDPK`),
  KEY `INDX_MATCH_MERG_LE_ASSO_3` (`MERGE_REASON_REFKEY`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- Dumping data for table yug_owner.match_merged_le_assoc: ~0 rows (approximately)
/*!40000 ALTER TABLE `match_merged_le_assoc` DISABLE KEYS */;
/*!40000 ALTER TABLE `match_merged_le_assoc` ENABLE KEYS */;

-- Dumping structure for table yug_owner.miscellaneous_info
CREATE TABLE IF NOT EXISTS `miscellaneous_info` (
  `ID_PK` varchar(50) COLLATE utf8_bin NOT NULL,
  `VERSION` decimal(22,0) NOT NULL,
  `CREATED_TS` datetime(6) NOT NULL,
  `DELETED_TS` datetime(6) DEFAULT NULL,
  `UPDATED_TS` datetime(6) NOT NULL,
  `UPDATED_BY_USER` varchar(50) COLLATE utf8_bin NOT NULL,
  `UPDATED_BY_TXN_ID` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `ENTITY_OBJECT_TYPE_REFKEY` varchar(50) COLLATE utf8_bin NOT NULL,
  `ENTITY_IDPK` varchar(50) COLLATE utf8_bin NOT NULL,
  `NAME1` varchar(50) COLLATE utf8_bin NOT NULL,
  `VALUE1` varchar(50) COLLATE utf8_bin NOT NULL,
  `NAME2` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `VALUE2` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `NAME3` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `VALUE3` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `NAME4` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `VALUE4` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `NAME5` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `VALUE5` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `NAME6` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `VALUE6` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `NAME7` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `VALUE7` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `NAME8` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `VALUE8` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `NAME9` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `VALUE9` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `NAME10` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `VALUE10` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_PK`),
  KEY `INDX_MISCELLANEOUS_INFO_1` (`ENTITY_OBJECT_TYPE_REFKEY`),
  KEY `INDX_MISCELLANEOUS_INFO_2` (`ENTITY_IDPK`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- Dumping data for table yug_owner.miscellaneous_info: ~0 rows (approximately)
/*!40000 ALTER TABLE `miscellaneous_info` DISABLE KEYS */;
/*!40000 ALTER TABLE `miscellaneous_info` ENABLE KEYS */;

-- Dumping structure for table yug_owner.personnames
CREATE TABLE IF NOT EXISTS `personnames` (
  `ID_PK` varchar(50) COLLATE utf8_bin NOT NULL,
  `VERSION` decimal(22,0) NOT NULL,
  `CREATED_TS` datetime(6) NOT NULL,
  `DELETED_TS` datetime(6) DEFAULT NULL,
  `UPDATED_TS` datetime(6) NOT NULL,
  `UPDATED_BY_USER` varchar(50) COLLATE utf8_bin NOT NULL,
  `UPDATED_BY_TXN_ID` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `LEGALENTITY_IDPK` varchar(50) COLLATE utf8_bin NOT NULL,
  `PERSONNAME_TYPE_REFKEY` varchar(50) COLLATE utf8_bin NOT NULL,
  `PREFIX_NAME_REFKEY` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `PREFIX_MISC` varchar(30) COLLATE utf8_bin DEFAULT NULL,
  `NAME_ONE` varchar(50) COLLATE utf8_bin NOT NULL,
  `NAME_TWO` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `NAME_THREE` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `NAME_FOUR` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `LAST_NAME` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `NICK_NAME` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `POPULAR_NAME` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `SUFFIX_NAME_REFKEY` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `SUFFIX_MISC` varchar(30) COLLATE utf8_bin DEFAULT NULL,
  `NAME_STANDARDISED_FLAG` varchar(1) COLLATE utf8_bin DEFAULT NULL,
  `SOURCE_SYSTEM_REFKEY` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `PHONETIC_NAME_ONE` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `PHONETIC_NAME_TWO` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `PHONETIC_NAME_THREE` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `PHONETIC_LAST_NAME` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_PK`),
  UNIQUE KEY `PERSONNAMES_UNIQKEY` (`LEGALENTITY_IDPK`,`PERSONNAME_TYPE_REFKEY`),
  KEY `INDX_PERSONNAMES_9` (`NICK_NAME`),
  KEY `INDX_PERSONNAMES_8` (`LAST_NAME`),
  KEY `INDX_PERSONNAMES_7` (`NAME_FOUR`),
  KEY `INDX_PERSONNAMES_6` (`NAME_THREE`),
  KEY `INDX_PERSONNAMES_5` (`NAME_TWO`),
  KEY `INDX_PERSONNAMES_4` (`NAME_ONE`),
  KEY `INDX_PERSONNAMES_3` (`PREFIX_NAME_REFKEY`),
  KEY `INDX_PERSONNAMES_16` (`PHONETIC_LAST_NAME`),
  KEY `INDX_PERSONNAMES_15` (`PHONETIC_NAME_THREE`),
  KEY `INDX_PERSONNAMES_14` (`PHONETIC_NAME_TWO`),
  KEY `INDX_PERSONNAMES_13` (`PHONETIC_NAME_ONE`),
  KEY `INDX_PERSONNAMES_12` (`SOURCE_SYSTEM_REFKEY`),
  KEY `INDX_PERSONNAMES_11` (`SUFFIX_NAME_REFKEY`),
  KEY `INDX_PERSONNAMES_10` (`POPULAR_NAME`),
  KEY `INDX_PERSONNAMES_1` (`LEGALENTITY_IDPK`),
  KEY `INDX_PERSONNAMES_2` (`PERSONNAME_TYPE_REFKEY`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- Dumping data for table yug_owner.personnames: ~0 rows (approximately)
/*!40000 ALTER TABLE `personnames` DISABLE KEYS */;
/*!40000 ALTER TABLE `personnames` ENABLE KEYS */;

-- Dumping structure for table yug_owner.phone_standardized
CREATE TABLE IF NOT EXISTS `phone_standardized` (
  `ID_PK` varchar(50) COLLATE utf8_bin NOT NULL,
  `VERSION` decimal(22,0) NOT NULL,
  `CREATED_TS` datetime(6) NOT NULL,
  `DELETED_TS` datetime(6) DEFAULT NULL,
  `UPDATED_TS` datetime(6) NOT NULL,
  `UPDATED_BY_USER` varchar(50) COLLATE utf8_bin NOT NULL,
  `UPDATED_BY_TXN_ID` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `ITU_COUNTRY_CALLING_CODE` varchar(4) COLLATE utf8_bin DEFAULT NULL,
  `AREA_CODE` varchar(6) COLLATE utf8_bin DEFAULT NULL,
  `EXCHANGE` varchar(6) COLLATE utf8_bin DEFAULT NULL,
  `PHONE_NUMBER` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `EXTENSION` varchar(8) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_PK`),
  KEY `INDX_PHONE_STANDARDIZED_5` (`EXTENSION`),
  KEY `INDX_PHONE_STANDARDIZED_1` (`ITU_COUNTRY_CALLING_CODE`),
  KEY `INDX_PHONE_STANDARDIZED_2` (`AREA_CODE`),
  KEY `INDX_PHONE_STANDARDIZED_3` (`EXCHANGE`),
  KEY `INDX_PHONE_STANDARDIZED_4` (`PHONE_NUMBER`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- Dumping data for table yug_owner.phone_standardized: ~0 rows (approximately)
/*!40000 ALTER TABLE `phone_standardized` DISABLE KEYS */;
/*!40000 ALTER TABLE `phone_standardized` ENABLE KEYS */;

-- Dumping structure for table yug_owner.property
CREATE TABLE IF NOT EXISTS `property` (
  `ID_PK` varchar(50) COLLATE utf8_bin NOT NULL,
  `VERSION` decimal(22,0) NOT NULL,
  `CREATED_TS` datetime(6) NOT NULL,
  `DELETED_TS` datetime(6) DEFAULT NULL,
  `UPDATED_TS` datetime(6) NOT NULL,
  `UPDATED_BY_USER` varchar(50) COLLATE utf8_bin NOT NULL,
  `UPDATED_BY_TXN_ID` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `PROPERTY_NAME` varchar(100) COLLATE utf8_bin NOT NULL,
  `ADDRESS_IDPK` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_PK`),
  KEY `INDX_PROPERTY_1` (`PROPERTY_NAME`),
  KEY `INDX_PROPERTY_2` (`ADDRESS_IDPK`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- Dumping data for table yug_owner.property: ~0 rows (approximately)
/*!40000 ALTER TABLE `property` DISABLE KEYS */;
/*!40000 ALTER TABLE `property` ENABLE KEYS */;

-- Dumping structure for table yug_owner.ref_account_mdm_status
CREATE TABLE IF NOT EXISTS `ref_account_mdm_status` (
  `ID_PK` varchar(50) COLLATE utf8_bin NOT NULL,
  `VERSION` decimal(22,0) NOT NULL,
  `CREATED_TS` datetime(6) NOT NULL,
  `DELETED_TS` datetime(6) DEFAULT NULL,
  `UPDATED_TS` datetime(6) NOT NULL,
  `UPDATED_BY_USER` varchar(50) COLLATE utf8_bin NOT NULL,
  `UPDATED_BY_TXN_ID` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `CONFIG_LANGUAGE_CODE_KEY` varchar(50) COLLATE utf8_bin NOT NULL,
  `KEY` varchar(50) COLLATE utf8_bin NOT NULL,
  `VALUE` varchar(50) COLLATE utf8_bin NOT NULL,
  `DESCRIPTION` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_PK`),
  UNIQUE KEY `REF_ACCMDMSTATUS_UNIQUEKEY` (`KEY`,`CONFIG_LANGUAGE_CODE_KEY`),
  KEY `INDX_REF_ACCOUNT_MDM_STATUS` (`KEY`),
  KEY `INDX_REF_ACCOUNT_MDM_STATUS_1` (`VALUE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- Dumping data for table yug_owner.ref_account_mdm_status: ~4 rows (approximately)
/*!40000 ALTER TABLE `ref_account_mdm_status` DISABLE KEYS */;
INSERT INTO `ref_account_mdm_status` (`ID_PK`, `VERSION`, `CREATED_TS`, `DELETED_TS`, `UPDATED_TS`, `UPDATED_BY_USER`, `UPDATED_BY_TXN_ID`, `CONFIG_LANGUAGE_CODE_KEY`, `KEY`, `VALUE`, `DESCRIPTION`) VALUES
	('1', 0, '2017-07-04 13:23:32.000000', NULL, '2017-07-04 13:23:32.000000', 'admin', '000000', '1', '0', 'UNSPECIFIED', 'Unspecified status'),
	('2', 0, '2017-07-04 13:23:32.000000', NULL, '2017-07-04 13:23:32.000000', 'admin', '000000', '1', '1', 'ACTIVE', 'Active status'),
	('3', 0, '2017-07-04 13:23:32.000000', NULL, '2017-07-04 13:23:32.000000', 'admin', '000000', '1', '2', 'INACTIVE', 'Inactive status'),
	('4', 0, '2017-07-04 13:23:32.000000', NULL, '2017-07-04 13:23:32.000000', 'admin', '000000', '1', '3', 'PHYSICALLY DELETED', 'Physically Deleted status');
/*!40000 ALTER TABLE `ref_account_mdm_status` ENABLE KEYS */;

-- Dumping structure for table yug_owner.ref_account_source_status
CREATE TABLE IF NOT EXISTS `ref_account_source_status` (
  `ID_PK` varchar(50) COLLATE utf8_bin NOT NULL,
  `VERSION` decimal(22,0) NOT NULL,
  `CREATED_TS` datetime(6) NOT NULL,
  `DELETED_TS` datetime(6) DEFAULT NULL,
  `UPDATED_TS` datetime(6) NOT NULL,
  `UPDATED_BY_USER` varchar(50) COLLATE utf8_bin NOT NULL,
  `UPDATED_BY_TXN_ID` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `CONFIG_LANGUAGE_CODE_KEY` varchar(50) COLLATE utf8_bin NOT NULL,
  `KEY` varchar(50) COLLATE utf8_bin NOT NULL,
  `VALUE` varchar(50) COLLATE utf8_bin NOT NULL,
  `DESCRIPTION` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_PK`),
  UNIQUE KEY `REFACCSOURCESTATUS_UNIQUEKEY` (`KEY`,`CONFIG_LANGUAGE_CODE_KEY`),
  KEY `INDX_REF_ACCOUNT_SOURCE_STAT_1` (`KEY`),
  KEY `INDX_REF_ACCOUNT_SOURCE_STAT_2` (`VALUE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- Dumping data for table yug_owner.ref_account_source_status: ~4 rows (approximately)
/*!40000 ALTER TABLE `ref_account_source_status` DISABLE KEYS */;
INSERT INTO `ref_account_source_status` (`ID_PK`, `VERSION`, `CREATED_TS`, `DELETED_TS`, `UPDATED_TS`, `UPDATED_BY_USER`, `UPDATED_BY_TXN_ID`, `CONFIG_LANGUAGE_CODE_KEY`, `KEY`, `VALUE`, `DESCRIPTION`) VALUES
	('1', 0, '2017-07-04 13:22:26.000000', NULL, '2017-07-04 13:22:26.000000', 'admin', '000000', '1', '0', 'UNSPECIFIED', 'Unspecified status'),
	('2', 0, '2017-07-04 13:22:26.000000', NULL, '2017-07-04 13:22:26.000000', 'admin', '000000', '1', '1', 'ACTIVE', 'Active status'),
	('3', 0, '2017-07-04 13:22:26.000000', NULL, '2017-07-04 13:22:26.000000', 'admin', '000000', '1', '2', 'INACTIVE', 'Inactive status'),
	('4', 0, '2017-07-04 13:22:26.000000', NULL, '2017-07-04 13:22:26.000000', 'admin', '000000', '1', '3', 'PHYSICALLY DELETED', 'physically deleted status');
/*!40000 ALTER TABLE `ref_account_source_status` ENABLE KEYS */;

-- Dumping structure for table yug_owner.ref_address_subtype
CREATE TABLE IF NOT EXISTS `ref_address_subtype` (
  `ID_PK` varchar(50) COLLATE utf8_bin NOT NULL,
  `VERSION` decimal(22,0) NOT NULL,
  `CREATED_TS` datetime(6) NOT NULL,
  `DELETED_TS` datetime(6) DEFAULT NULL,
  `UPDATED_TS` datetime(6) NOT NULL,
  `UPDATED_BY_USER` varchar(50) COLLATE utf8_bin NOT NULL,
  `UPDATED_BY_TXN_ID` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `CONFIG_LANGUAGE_CODE_KEY` varchar(50) COLLATE utf8_bin NOT NULL,
  `KEY` varchar(50) COLLATE utf8_bin NOT NULL,
  `VALUE` varchar(50) COLLATE utf8_bin NOT NULL,
  `DESCRIPTION` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_PK`),
  UNIQUE KEY `REF_ADDRESS_SUBTYPE_UNIQUEKEY` (`KEY`,`CONFIG_LANGUAGE_CODE_KEY`),
  KEY `INDX_REF_ADDRESS_SUBTYPE_1` (`KEY`),
  KEY `INDX_REF_ADDRESS_SUBTYPE_2` (`VALUE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- Dumping data for table yug_owner.ref_address_subtype: ~5 rows (approximately)
/*!40000 ALTER TABLE `ref_address_subtype` DISABLE KEYS */;
INSERT INTO `ref_address_subtype` (`ID_PK`, `VERSION`, `CREATED_TS`, `DELETED_TS`, `UPDATED_TS`, `UPDATED_BY_USER`, `UPDATED_BY_TXN_ID`, `CONFIG_LANGUAGE_CODE_KEY`, `KEY`, `VALUE`, `DESCRIPTION`) VALUES
	('1', 0, '2017-07-04 13:21:10.000000', NULL, '2017-07-04 13:21:10.000000', 'admin', '000000', '1', '0', 'UNSPECIFIED', 'Unspecified Address'),
	('2', 0, '2017-07-04 13:21:10.000000', NULL, '2017-07-04 13:21:10.000000', 'admin', '000000', '1', '1', 'REGISTERED', 'Registered Address'),
	('3', 0, '2017-07-04 13:21:10.000000', NULL, '2017-07-04 13:21:10.000000', 'admin', '000000', '1', '2', 'MAILING', 'Mailing Address'),
	('4', 0, '2017-07-04 13:21:10.000000', NULL, '2017-07-04 13:21:10.000000', 'admin', '000000', '1', '3', 'PERMANENT', 'Permanent Address'),
	('5', 0, '2017-07-04 13:21:11.000000', NULL, '2017-07-04 13:21:11.000000', 'admin', '000000', '1', '4', 'POSTAL', 'Postal Address');
/*!40000 ALTER TABLE `ref_address_subtype` ENABLE KEYS */;

-- Dumping structure for table yug_owner.ref_address_type
CREATE TABLE IF NOT EXISTS `ref_address_type` (
  `ID_PK` varchar(50) COLLATE utf8_bin NOT NULL,
  `VERSION` decimal(22,0) NOT NULL,
  `CREATED_TS` datetime(6) NOT NULL,
  `DELETED_TS` datetime(6) DEFAULT NULL,
  `UPDATED_TS` datetime(6) NOT NULL,
  `UPDATED_BY_USER` varchar(50) COLLATE utf8_bin NOT NULL,
  `UPDATED_BY_TXN_ID` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `CONFIG_LANGUAGE_CODE_KEY` varchar(50) COLLATE utf8_bin NOT NULL,
  `KEY` varchar(50) COLLATE utf8_bin NOT NULL,
  `VALUE` varchar(50) COLLATE utf8_bin NOT NULL,
  `DESCRIPTION` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_PK`),
  UNIQUE KEY `REF_ADDRESS_TYPE_UNIQUEKEY` (`KEY`,`CONFIG_LANGUAGE_CODE_KEY`),
  KEY `INDX_REF_ADDRESS_TYPE_1` (`KEY`),
  KEY `INDX_REF_ADDRESS_TYPE_2` (`VALUE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- Dumping data for table yug_owner.ref_address_type: ~4 rows (approximately)
/*!40000 ALTER TABLE `ref_address_type` DISABLE KEYS */;
INSERT INTO `ref_address_type` (`ID_PK`, `VERSION`, `CREATED_TS`, `DELETED_TS`, `UPDATED_TS`, `UPDATED_BY_USER`, `UPDATED_BY_TXN_ID`, `CONFIG_LANGUAGE_CODE_KEY`, `KEY`, `VALUE`, `DESCRIPTION`) VALUES
	('1', 0, '2017-07-04 13:19:53.000000', NULL, '2017-07-04 13:19:53.000000', 'admin', '000000', '1', '0', 'UNSPECIFIED', 'Unspecific Address'),
	('2', 0, '2017-07-04 13:19:53.000000', NULL, '2017-07-04 13:19:53.000000', 'admin', '000000', '1', '1', 'Regular', 'Regular Address'),
	('3', 0, '2017-07-04 13:19:53.000000', NULL, '2017-07-04 13:19:53.000000', 'admin', '000000', '1', '2', 'SUMMER', 'Summer Address'),
	('4', 0, '2017-07-04 13:19:53.000000', NULL, '2017-07-04 13:19:53.000000', 'admin', '000000', '1', '3', 'WINTER', 'Winter Address');
/*!40000 ALTER TABLE `ref_address_type` ENABLE KEYS */;

-- Dumping structure for table yug_owner.ref_agreement_type
CREATE TABLE IF NOT EXISTS `ref_agreement_type` (
  `ID_PK` varchar(50) COLLATE utf8_bin NOT NULL,
  `VERSION` decimal(22,0) NOT NULL,
  `CREATED_TS` datetime(6) NOT NULL,
  `DELETED_TS` datetime(6) DEFAULT NULL,
  `UPDATED_TS` datetime(6) NOT NULL,
  `UPDATED_BY_USER` varchar(50) COLLATE utf8_bin NOT NULL,
  `UPDATED_BY_TXN_ID` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `CONFIG_LANGUAGE_CODE_KEY` varchar(50) COLLATE utf8_bin NOT NULL,
  `KEY` varchar(50) COLLATE utf8_bin NOT NULL,
  `VALUE` varchar(50) COLLATE utf8_bin NOT NULL,
  `DESCRIPTION` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_PK`),
  UNIQUE KEY `REF_AGREEMENT_TYPE_UNIQUEKEY` (`KEY`,`CONFIG_LANGUAGE_CODE_KEY`),
  KEY `INDX_REF_AGREEMENT_TYPE_1` (`KEY`),
  KEY `INDX_REF_AGREEMENT_TYPE_2` (`VALUE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- Dumping data for table yug_owner.ref_agreement_type: ~4 rows (approximately)
/*!40000 ALTER TABLE `ref_agreement_type` DISABLE KEYS */;
INSERT INTO `ref_agreement_type` (`ID_PK`, `VERSION`, `CREATED_TS`, `DELETED_TS`, `UPDATED_TS`, `UPDATED_BY_USER`, `UPDATED_BY_TXN_ID`, `CONFIG_LANGUAGE_CODE_KEY`, `KEY`, `VALUE`, `DESCRIPTION`) VALUES
	('1', 0, '2017-07-04 13:37:27.000000', NULL, '2017-07-04 13:37:27.000000', 'admin', '000000', '1', '0', 'UNSPECIFIED', 'Unspecified'),
	('2', 0, '2017-07-04 13:37:27.000000', NULL, '2017-07-04 13:37:27.000000', 'admin', '000000', '1', '1', 'PHYSICAL', 'Physical agreement'),
	('3', 0, '2017-07-04 13:37:27.000000', NULL, '2017-07-04 13:37:27.000000', 'admin', '000000', '1', '2', 'DIGITAL', 'Digital agreement'),
	('4', 0, '2017-07-04 13:37:27.000000', NULL, '2017-07-04 13:37:27.000000', 'admin', '000000', '1', '3', 'MULTIFORM', 'Multiform agreement, mix of physical and digital ');
/*!40000 ALTER TABLE `ref_agreement_type` ENABLE KEYS */;

-- Dumping structure for table yug_owner.ref_assoc_type
CREATE TABLE IF NOT EXISTS `ref_assoc_type` (
  `ID_PK` varchar(50) COLLATE utf8_bin NOT NULL,
  `VERSION` decimal(22,0) NOT NULL,
  `CREATED_TS` datetime(6) NOT NULL,
  `DELETED_TS` datetime(6) DEFAULT NULL,
  `UPDATED_TS` datetime(6) NOT NULL,
  `UPDATED_BY_USER` varchar(50) COLLATE utf8_bin NOT NULL,
  `UPDATED_BY_TXN_ID` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `CONFIG_LANGUAGE_CODE_KEY` varchar(50) COLLATE utf8_bin NOT NULL,
  `KEY` varchar(50) COLLATE utf8_bin NOT NULL,
  `VALUE` varchar(50) COLLATE utf8_bin NOT NULL,
  `DESCRIPTION` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_PK`),
  UNIQUE KEY `REF_ASSOC_TYPE_UNIQUEKEY` (`KEY`,`CONFIG_LANGUAGE_CODE_KEY`),
  KEY `INDX_REF_ASSOC_TYPE_1` (`KEY`),
  KEY `INDX_REF_ASSOC_TYPE_2` (`VALUE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- Dumping data for table yug_owner.ref_assoc_type: ~4 rows (approximately)
/*!40000 ALTER TABLE `ref_assoc_type` DISABLE KEYS */;
INSERT INTO `ref_assoc_type` (`ID_PK`, `VERSION`, `CREATED_TS`, `DELETED_TS`, `UPDATED_TS`, `UPDATED_BY_USER`, `UPDATED_BY_TXN_ID`, `CONFIG_LANGUAGE_CODE_KEY`, `KEY`, `VALUE`, `DESCRIPTION`) VALUES
	('1', 0, '2017-07-04 13:43:49.000000', NULL, '2017-07-04 13:43:49.000000', 'admin', '000000', '1', '0', 'UNSPECIFIED', 'Unspecified'),
	('2', 0, '2017-07-04 13:43:49.000000', NULL, '2017-07-04 13:43:49.000000', 'admin', '000000', '1', '1', 'OWNER', 'Owner of- association'),
	('3', 0, '2017-07-04 13:43:49.000000', NULL, '2017-07-04 13:43:49.000000', 'admin', '000000', '1', '2', 'PARTNER', 'Partner of- association'),
	('4', 0, '2017-07-04 13:43:49.000000', NULL, '2017-07-04 13:43:49.000000', 'admin', '000000', '1', '3', 'TRUSTEE', 'Trustee of - association');
/*!40000 ALTER TABLE `ref_assoc_type` ENABLE KEYS */;

-- Dumping structure for table yug_owner.ref_batch_action_status
CREATE TABLE IF NOT EXISTS `ref_batch_action_status` (
  `ID_PK` varchar(50) COLLATE utf8_bin NOT NULL,
  `VERSION` decimal(22,0) NOT NULL,
  `CREATED_TS` datetime(6) NOT NULL,
  `DELETED_TS` datetime(6) DEFAULT NULL,
  `UPDATED_TS` datetime(6) NOT NULL,
  `UPDATED_BY_USER` varchar(50) COLLATE utf8_bin NOT NULL,
  `UPDATED_BY_TXN_ID` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `CONFIG_LANGUAGE_CODE_KEY` varchar(50) COLLATE utf8_bin NOT NULL,
  `KEY` varchar(50) COLLATE utf8_bin NOT NULL,
  `VALUE` varchar(50) COLLATE utf8_bin NOT NULL,
  `DESCRIPTION` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_PK`),
  KEY `INDX_REF_BATCH_ACTION_STATUS_1` (`KEY`),
  KEY `INDX_REF_BATCH_ACTION_STATUS_2` (`VALUE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- Dumping data for table yug_owner.ref_batch_action_status: ~5 rows (approximately)
/*!40000 ALTER TABLE `ref_batch_action_status` DISABLE KEYS */;
INSERT INTO `ref_batch_action_status` (`ID_PK`, `VERSION`, `CREATED_TS`, `DELETED_TS`, `UPDATED_TS`, `UPDATED_BY_USER`, `UPDATED_BY_TXN_ID`, `CONFIG_LANGUAGE_CODE_KEY`, `KEY`, `VALUE`, `DESCRIPTION`) VALUES
	('1', 1, '2017-12-12 19:16:27.000000', NULL, '2017-12-12 19:16:46.000000', 'admin', '00000000', '1', '1', 'CREATED', 'CREATED'),
	('2', 0, '2017-12-12 19:17:37.000000', NULL, '2017-12-12 19:17:37.000000', 'admin', '00000000', '1', '2', 'IN PROGRESS', 'IN PROGRESS'),
	('3', 0, '2017-12-12 19:18:17.000000', NULL, '2017-12-12 19:18:17.000000', 'admin', '00000000', '1', '3', 'PROCESSING COMPLETED', 'PROCESSING COMPLETED'),
	('4', 0, '2017-12-13 14:46:59.000000', NULL, '2017-12-13 14:46:59.000000', 'admin', '00000000', '1', '4', 'ON HOLD', 'ON HOLD'),
	('5', 0, '2017-12-13 14:47:10.000000', NULL, '2017-12-13 14:47:10.000000', 'admin', '00000000', '1', '4', 'SUSPENDED', 'SUSPENDED');
/*!40000 ALTER TABLE `ref_batch_action_status` ENABLE KEYS */;

-- Dumping structure for table yug_owner.ref_batch_proposed_action
CREATE TABLE IF NOT EXISTS `ref_batch_proposed_action` (
  `ID_PK` varchar(50) COLLATE utf8_bin NOT NULL,
  `VERSION` decimal(22,0) NOT NULL,
  `CREATED_TS` datetime(6) NOT NULL,
  `DELETED_TS` datetime(6) DEFAULT NULL,
  `UPDATED_TS` datetime(6) NOT NULL,
  `UPDATED_BY_USER` varchar(50) COLLATE utf8_bin NOT NULL,
  `UPDATED_BY_TXN_ID` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `CONFIG_LANGUAGE_CODE_KEY` varchar(50) COLLATE utf8_bin NOT NULL,
  `KEY` varchar(50) COLLATE utf8_bin NOT NULL,
  `VALUE` varchar(50) COLLATE utf8_bin NOT NULL,
  `DESCRIPTION` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_PK`),
  KEY `INDX_REF_BATCH_PROPOSED_ACTI_1` (`KEY`),
  KEY `INDX_REF_BATCH_PROPOSED_ACTI_2` (`VALUE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- Dumping data for table yug_owner.ref_batch_proposed_action: ~2 rows (approximately)
/*!40000 ALTER TABLE `ref_batch_proposed_action` DISABLE KEYS */;
INSERT INTO `ref_batch_proposed_action` (`ID_PK`, `VERSION`, `CREATED_TS`, `DELETED_TS`, `UPDATED_TS`, `UPDATED_BY_USER`, `UPDATED_BY_TXN_ID`, `CONFIG_LANGUAGE_CODE_KEY`, `KEY`, `VALUE`, `DESCRIPTION`) VALUES
	('1', 1, '2017-12-12 19:11:44.000000', NULL, '2017-12-12 19:12:38.000000', 'admin', '00000000', '1', '2', 'SEARCH MATCH CANDIDATE', 'SEARCH THE MATCH CANDIDATES'),
	('2', 0, '2017-12-12 19:15:29.000000', NULL, '2017-12-12 19:15:29.000000', 'admin', '00000000', '1', '1', 'NO ACTION', 'NO ACTION');
/*!40000 ALTER TABLE `ref_batch_proposed_action` ENABLE KEYS */;

-- Dumping structure for table yug_owner.ref_billing_mode_type
CREATE TABLE IF NOT EXISTS `ref_billing_mode_type` (
  `ID_PK` varchar(50) COLLATE utf8_bin NOT NULL,
  `VERSION` decimal(22,0) NOT NULL,
  `CREATED_TS` datetime(6) NOT NULL,
  `DELETED_TS` datetime(6) DEFAULT NULL,
  `UPDATED_TS` datetime(6) NOT NULL,
  `UPDATED_BY_USER` varchar(50) COLLATE utf8_bin NOT NULL,
  `UPDATED_BY_TXN_ID` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `CONFIG_LANGUAGE_CODE_KEY` varchar(50) COLLATE utf8_bin NOT NULL,
  `KEY` varchar(50) COLLATE utf8_bin NOT NULL,
  `VALUE` varchar(50) COLLATE utf8_bin NOT NULL,
  `DESCRIPTION` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_PK`),
  UNIQUE KEY `REFBILLINGMODETYPE_UNIQUEKEY` (`KEY`,`CONFIG_LANGUAGE_CODE_KEY`),
  KEY `INDX_REF_BILLING_MODE_TYPE_1` (`KEY`),
  KEY `INDX_REF_BILLING_MODE_TYPE_2` (`VALUE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- Dumping data for table yug_owner.ref_billing_mode_type: ~4 rows (approximately)
/*!40000 ALTER TABLE `ref_billing_mode_type` DISABLE KEYS */;
INSERT INTO `ref_billing_mode_type` (`ID_PK`, `VERSION`, `CREATED_TS`, `DELETED_TS`, `UPDATED_TS`, `UPDATED_BY_USER`, `UPDATED_BY_TXN_ID`, `CONFIG_LANGUAGE_CODE_KEY`, `KEY`, `VALUE`, `DESCRIPTION`) VALUES
	('1', 0, '2017-07-04 14:06:00.000000', NULL, '2017-07-04 14:06:00.000000', 'admin', '000000', '1', '1', 'MONTHLY', 'Monthly'),
	('2', 0, '2017-07-04 14:06:00.000000', NULL, '2017-07-04 14:06:00.000000', 'admin', '000000', '1', '2', 'Quarterly', 'Quarterly billing'),
	('3', 0, '2017-07-04 14:06:00.000000', NULL, '2017-07-04 14:06:00.000000', 'admin', '000000', '1', '3', 'Half Yearly', 'Half Yearly'),
	('4', 0, '2017-07-04 14:06:00.000000', NULL, '2017-07-04 14:06:00.000000', 'admin', '000000', '1', '4', 'Annualy', 'Annual');
/*!40000 ALTER TABLE `ref_billing_mode_type` ENABLE KEYS */;

-- Dumping structure for table yug_owner.ref_branch_code
CREATE TABLE IF NOT EXISTS `ref_branch_code` (
  `ID_PK` varchar(50) COLLATE utf8_bin NOT NULL,
  `VERSION` decimal(22,0) NOT NULL,
  `CREATED_TS` datetime(6) NOT NULL,
  `DELETED_TS` datetime(6) DEFAULT NULL,
  `UPDATED_TS` datetime(6) NOT NULL,
  `UPDATED_BY_USER` varchar(50) COLLATE utf8_bin NOT NULL,
  `UPDATED_BY_TXN_ID` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `CONFIG_LANGUAGE_CODE_KEY` varchar(50) COLLATE utf8_bin NOT NULL,
  `KEY` varchar(50) COLLATE utf8_bin NOT NULL,
  `VALUE` varchar(50) COLLATE utf8_bin NOT NULL,
  `DESCRIPTION` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_PK`),
  UNIQUE KEY `REF_BRANCH_CODE_UNIQUEKEY` (`KEY`,`CONFIG_LANGUAGE_CODE_KEY`),
  KEY `INDX_REF_BRANCH_CODE_1` (`KEY`),
  KEY `INDX_REF_BRANCH_CODE_2` (`VALUE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- Dumping data for table yug_owner.ref_branch_code: ~3 rows (approximately)
/*!40000 ALTER TABLE `ref_branch_code` DISABLE KEYS */;
INSERT INTO `ref_branch_code` (`ID_PK`, `VERSION`, `CREATED_TS`, `DELETED_TS`, `UPDATED_TS`, `UPDATED_BY_USER`, `UPDATED_BY_TXN_ID`, `CONFIG_LANGUAGE_CODE_KEY`, `KEY`, `VALUE`, `DESCRIPTION`) VALUES
	('1', 0, '2017-07-04 14:04:51.000000', NULL, '2017-07-04 14:04:51.000000', 'admin', '000000', '1', '1', 'Branch1', NULL),
	('2', 0, '2017-07-04 14:04:51.000000', NULL, '2017-07-04 14:04:51.000000', 'admin', '000000', '1', '2', 'Branch2', NULL),
	('3', 0, '2017-07-04 14:04:51.000000', NULL, '2017-07-04 14:04:51.000000', 'admin', '000000', '1', '3', 'Branch3', NULL);
/*!40000 ALTER TABLE `ref_branch_code` ENABLE KEYS */;

-- Dumping structure for table yug_owner.ref_classification_code
CREATE TABLE IF NOT EXISTS `ref_classification_code` (
  `ID_PK` varchar(50) COLLATE utf8_bin NOT NULL,
  `VERSION` decimal(22,0) NOT NULL,
  `CREATED_TS` datetime(6) NOT NULL,
  `DELETED_TS` datetime(6) DEFAULT NULL,
  `UPDATED_TS` datetime(6) NOT NULL,
  `UPDATED_BY_USER` varchar(50) COLLATE utf8_bin NOT NULL,
  `UPDATED_BY_TXN_ID` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `CONFIG_LANGUAGE_CODE_KEY` varchar(50) COLLATE utf8_bin NOT NULL,
  `KEY` varchar(50) COLLATE utf8_bin NOT NULL,
  `VALUE` varchar(50) COLLATE utf8_bin NOT NULL,
  `DESCRIPTION` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_PK`),
  UNIQUE KEY `REFCLASSCODE_UNIQUEKEY` (`KEY`,`CONFIG_LANGUAGE_CODE_KEY`),
  KEY `INDX_REF_CLASSIFICATION_CODE_1` (`KEY`),
  KEY `INDX_REF_CLASSIFICATION_CODE_2` (`VALUE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- Dumping data for table yug_owner.ref_classification_code: ~2 rows (approximately)
/*!40000 ALTER TABLE `ref_classification_code` DISABLE KEYS */;
INSERT INTO `ref_classification_code` (`ID_PK`, `VERSION`, `CREATED_TS`, `DELETED_TS`, `UPDATED_TS`, `UPDATED_BY_USER`, `UPDATED_BY_TXN_ID`, `CONFIG_LANGUAGE_CODE_KEY`, `KEY`, `VALUE`, `DESCRIPTION`) VALUES
	('1', 0, '2017-07-04 14:08:16.000000', NULL, '2017-07-04 14:08:16.000000', 'admin', '000000', '1', '1', 'CUSTOMER', 'Customer'),
	('2', 0, '2017-07-04 14:08:16.000000', NULL, '2017-07-04 14:08:16.000000', 'admin', '000000', '1', '2', 'PROSPECT', 'Prospect');
/*!40000 ALTER TABLE `ref_classification_code` ENABLE KEYS */;

-- Dumping structure for table yug_owner.ref_corporation_name_type
CREATE TABLE IF NOT EXISTS `ref_corporation_name_type` (
  `ID_PK` varchar(50) COLLATE utf8_bin NOT NULL,
  `VERSION` decimal(22,0) NOT NULL,
  `CREATED_TS` datetime(6) NOT NULL,
  `DELETED_TS` datetime(6) DEFAULT NULL,
  `UPDATED_TS` datetime(6) NOT NULL,
  `UPDATED_BY_USER` varchar(50) COLLATE utf8_bin NOT NULL,
  `UPDATED_BY_TXN_ID` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `CONFIG_LANGUAGE_CODE_KEY` varchar(50) COLLATE utf8_bin NOT NULL,
  `KEY` varchar(50) COLLATE utf8_bin NOT NULL,
  `VALUE` varchar(50) COLLATE utf8_bin NOT NULL,
  `DESCRIPTION` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_PK`),
  UNIQUE KEY `REFCORPNAMETYPE_UNIQUEKEY` (`KEY`,`CONFIG_LANGUAGE_CODE_KEY`),
  KEY `INDX_REF_CORPORATION_NAME_TY_1` (`KEY`),
  KEY `INDX_REF_CORPORATION_NAME_TY_2` (`VALUE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- Dumping data for table yug_owner.ref_corporation_name_type: ~4 rows (approximately)
/*!40000 ALTER TABLE `ref_corporation_name_type` DISABLE KEYS */;
INSERT INTO `ref_corporation_name_type` (`ID_PK`, `VERSION`, `CREATED_TS`, `DELETED_TS`, `UPDATED_TS`, `UPDATED_BY_USER`, `UPDATED_BY_TXN_ID`, `CONFIG_LANGUAGE_CODE_KEY`, `KEY`, `VALUE`, `DESCRIPTION`) VALUES
	('1', 0, '2017-07-04 14:11:43.000000', NULL, '2017-07-04 14:11:43.000000', 'admin', '000000', '1', '1', 'LEGAL', 'Legal Name'),
	('2', 0, '2017-07-04 14:11:43.000000', NULL, '2017-07-04 14:11:43.000000', 'admin', '000000', '1', '2', 'REGISTERED', 'Registered Name'),
	('3', 0, '2017-07-04 14:11:44.000000', NULL, '2017-07-04 14:11:44.000000', 'admin', '000000', '1', '3', 'TRADING', 'Trading Name'),
	('4', 0, '2017-07-04 14:11:44.000000', NULL, '2017-07-04 14:11:44.000000', 'admin', '000000', '1', '4', 'BRAND', 'Known to common people by Brand Name');
/*!40000 ALTER TABLE `ref_corporation_name_type` ENABLE KEYS */;

-- Dumping structure for table yug_owner.ref_corporation_type
CREATE TABLE IF NOT EXISTS `ref_corporation_type` (
  `ID_PK` varchar(50) COLLATE utf8_bin NOT NULL,
  `VERSION` decimal(22,0) NOT NULL,
  `CREATED_TS` datetime(6) NOT NULL,
  `DELETED_TS` datetime(6) DEFAULT NULL,
  `UPDATED_TS` datetime(6) NOT NULL,
  `UPDATED_BY_USER` varchar(50) COLLATE utf8_bin NOT NULL,
  `UPDATED_BY_TXN_ID` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `CONFIG_LANGUAGE_CODE_KEY` varchar(50) COLLATE utf8_bin NOT NULL,
  `KEY` varchar(50) COLLATE utf8_bin NOT NULL,
  `VALUE` varchar(50) COLLATE utf8_bin NOT NULL,
  `DESCRIPTION` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_PK`),
  UNIQUE KEY `REF_CORPORATION_TYPE_UNIQUEKEY` (`KEY`,`CONFIG_LANGUAGE_CODE_KEY`),
  KEY `INDX_REF_CORPORATION_TYPE_1` (`KEY`),
  KEY `INDX_REF_CORPORATION_TYPE_2` (`VALUE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- Dumping data for table yug_owner.ref_corporation_type: ~4 rows (approximately)
/*!40000 ALTER TABLE `ref_corporation_type` DISABLE KEYS */;
INSERT INTO `ref_corporation_type` (`ID_PK`, `VERSION`, `CREATED_TS`, `DELETED_TS`, `UPDATED_TS`, `UPDATED_BY_USER`, `UPDATED_BY_TXN_ID`, `CONFIG_LANGUAGE_CODE_KEY`, `KEY`, `VALUE`, `DESCRIPTION`) VALUES
	('1', 0, '2017-07-04 14:13:49.000000', NULL, '2017-07-04 14:13:49.000000', 'admin', '000000', '1', '1', 'COMPANY', 'Company'),
	('2', 0, '2017-07-04 14:13:49.000000', NULL, '2017-07-04 14:13:49.000000', 'admin', '000000', '1', '2', 'INSTITUTION', 'Institution'),
	('3', 0, '2017-07-04 14:13:49.000000', NULL, '2017-07-04 14:13:49.000000', 'admin', '000000', '1', '3', 'TRUST', 'Trust'),
	('4', 0, '2017-07-04 14:13:49.000000', NULL, '2017-07-04 14:13:49.000000', 'admin', '000000', '1', '4', 'CHARITY', 'Charitable Trusts or institutions');
/*!40000 ALTER TABLE `ref_corporation_type` ENABLE KEYS */;

-- Dumping structure for table yug_owner.ref_country_iso
CREATE TABLE IF NOT EXISTS `ref_country_iso` (
  `ID_PK` varchar(50) COLLATE utf8_bin NOT NULL,
  `VERSION` decimal(22,0) NOT NULL,
  `CREATED_TS` datetime(6) NOT NULL,
  `DELETED_TS` datetime(6) DEFAULT NULL,
  `UPDATED_TS` datetime(6) NOT NULL,
  `UPDATED_BY_USER` varchar(50) COLLATE utf8_bin NOT NULL,
  `UPDATED_BY_TXN_ID` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `CONFIG_LANGUAGE_CODE_KEY` varchar(50) COLLATE utf8_bin NOT NULL,
  `KEY` varchar(50) COLLATE utf8_bin NOT NULL,
  `VALUE` varchar(50) COLLATE utf8_bin NOT NULL,
  `DESCRIPTION` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_PK`),
  UNIQUE KEY `REF_COUNTRY_ISO_UNIQUEKEY` (`KEY`,`CONFIG_LANGUAGE_CODE_KEY`),
  KEY `INDX_REF_COUNTRY_ISO_1` (`KEY`),
  KEY `INDX_REF_COUNTRY_ISO_2` (`VALUE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- Dumping data for table yug_owner.ref_country_iso: ~247 rows (approximately)
/*!40000 ALTER TABLE `ref_country_iso` DISABLE KEYS */;
INSERT INTO `ref_country_iso` (`ID_PK`, `VERSION`, `CREATED_TS`, `DELETED_TS`, `UPDATED_TS`, `UPDATED_BY_USER`, `UPDATED_BY_TXN_ID`, `CONFIG_LANGUAGE_CODE_KEY`, `KEY`, `VALUE`, `DESCRIPTION`) VALUES
	('1', 0, '2018-04-16 18:27:14.000000', NULL, '2018-04-16 18:27:14.000000', 'admin', '000000', '1', '4', 'AFG', 'Afghanistan'),
	('10', 0, '2018-04-16 18:27:14.000000', NULL, '2018-04-16 18:27:14.000000', 'admin', '000000', '1', '32', 'ARG', 'Argentina'),
	('100', 0, '2018-04-16 18:27:16.000000', NULL, '2018-04-16 18:27:16.000000', 'admin', '000000', '1', '344', 'HKG', 'Hong Kong, SAR China'),
	('101', 0, '2018-04-16 18:27:16.000000', NULL, '2018-04-16 18:27:16.000000', 'admin', '000000', '1', '348', 'HUN', 'Hungary'),
	('102', 0, '2018-04-16 18:27:16.000000', NULL, '2018-04-16 18:27:16.000000', 'admin', '000000', '1', '352', 'ISL', 'Iceland'),
	('103', 0, '2018-04-16 18:27:16.000000', NULL, '2018-04-16 18:27:16.000000', 'admin', '000000', '1', '356', 'IND', 'India'),
	('104', 0, '2018-04-16 18:27:14.000000', NULL, '2018-04-16 18:27:14.000000', 'admin', '000000', '1', '360', 'IDN', 'Indonesia'),
	('105', 0, '2018-04-16 18:27:16.000000', NULL, '2018-04-16 18:27:16.000000', 'admin', '000000', '1', '364', 'IRN', 'Iran, Islamic Republic of'),
	('106', 0, '2018-04-16 18:27:16.000000', NULL, '2018-04-16 18:27:16.000000', 'admin', '000000', '1', '368', 'IRQ', 'Iraq'),
	('107', 0, '2018-04-16 18:27:16.000000', NULL, '2018-04-16 18:27:16.000000', 'admin', '000000', '1', '372', 'IRL', 'Ireland'),
	('108', 0, '2018-04-16 18:27:16.000000', NULL, '2018-04-16 18:27:16.000000', 'admin', '000000', '1', '376', 'ISR', 'Israel'),
	('109', 0, '2018-04-16 18:27:16.000000', NULL, '2018-04-16 18:27:16.000000', 'admin', '000000', '1', '380', 'ITA', 'Italy'),
	('11', 0, '2018-04-16 18:27:14.000000', NULL, '2018-04-16 18:27:14.000000', 'admin', '000000', '1', '36', 'AUS', 'Australia'),
	('110', 0, '2018-04-16 18:27:16.000000', NULL, '2018-04-16 18:27:16.000000', 'admin', '000000', '1', '384', 'CIV', 'Cote dIvoire'),
	('111', 0, '2018-04-16 18:27:16.000000', NULL, '2018-04-16 18:27:16.000000', 'admin', '000000', '1', '388', 'JAM', 'Jamaica'),
	('112', 0, '2018-04-16 18:27:16.000000', NULL, '2018-04-16 18:27:16.000000', 'admin', '000000', '1', '392', 'JPN', 'Japan'),
	('113', 0, '2018-04-16 18:27:16.000000', NULL, '2018-04-16 18:27:16.000000', 'admin', '000000', '1', '398', 'KAZ', 'Kazakhstan'),
	('114', 0, '2018-04-16 18:27:16.000000', NULL, '2018-04-16 18:27:16.000000', 'admin', '000000', '1', '400', 'JOR', 'Jordan'),
	('115', 0, '2018-04-16 18:27:16.000000', NULL, '2018-04-16 18:27:16.000000', 'admin', '000000', '1', '404', 'KEN', 'Kenya'),
	('116', 0, '2018-04-16 18:27:16.000000', NULL, '2018-04-16 18:27:16.000000', 'admin', '000000', '1', '408', 'PRK', 'KoreaÃ‚Â (North)'),
	('117', 0, '2018-04-16 18:27:16.000000', NULL, '2018-04-16 18:27:16.000000', 'admin', '000000', '1', '410', 'KOR', 'KoreaÃ‚Â (South)'),
	('118', 0, '2018-04-16 18:27:16.000000', NULL, '2018-04-16 18:27:16.000000', 'admin', '000000', '1', '414', 'KWT', 'Kuwait'),
	('119', 0, '2018-04-16 18:27:16.000000', NULL, '2018-04-16 18:27:16.000000', 'admin', '000000', '1', '417', 'KGZ', 'Kyrgyzstan'),
	('12', 0, '2018-04-16 18:27:14.000000', NULL, '2018-04-16 18:27:14.000000', 'admin', '000000', '1', '40', 'AUT', 'Austria'),
	('120', 0, '2018-04-16 18:27:16.000000', NULL, '2018-04-16 18:27:16.000000', 'admin', '000000', '1', '418', 'LAO', 'Lao PDR'),
	('121', 0, '2018-04-16 18:27:16.000000', NULL, '2018-04-16 18:27:16.000000', 'admin', '000000', '1', '422', 'LBN', 'Lebanon'),
	('122', 0, '2018-04-16 18:27:16.000000', NULL, '2018-04-16 18:27:16.000000', 'admin', '000000', '1', '426', 'LSO', 'Lesotho'),
	('123', 0, '2018-04-16 18:27:16.000000', NULL, '2018-04-16 18:27:16.000000', 'admin', '000000', '1', '428', 'LVA', 'Latvia'),
	('124', 0, '2018-04-16 18:27:16.000000', NULL, '2018-04-16 18:27:16.000000', 'admin', '000000', '1', '430', 'LBR', 'Liberia'),
	('125', 0, '2018-04-16 18:27:16.000000', NULL, '2018-04-16 18:27:16.000000', 'admin', '000000', '1', '434', 'LBY', 'Libya'),
	('126', 0, '2018-04-16 18:27:16.000000', NULL, '2018-04-16 18:27:16.000000', 'admin', '000000', '1', '438', 'LIE', 'Liechtenstein'),
	('127', 0, '2018-04-16 18:27:16.000000', NULL, '2018-04-16 18:27:16.000000', 'admin', '000000', '1', '440', 'LTU', 'Lithuania'),
	('128', 0, '2018-04-16 18:27:16.000000', NULL, '2018-04-16 18:27:16.000000', 'admin', '000000', '1', '442', 'LUX', 'Luxembourg'),
	('129', 0, '2018-04-16 18:27:16.000000', NULL, '2018-04-16 18:27:16.000000', 'admin', '000000', '1', '446', 'MAC', 'Macao, SAR China'),
	('13', 0, '2018-04-16 18:27:14.000000', NULL, '2018-04-16 18:27:14.000000', 'admin', '000000', '1', '44', 'BHS', 'Bahamas'),
	('130', 0, '2018-04-16 18:27:16.000000', NULL, '2018-04-16 18:27:16.000000', 'admin', '000000', '1', '450', 'MDG', 'Madagascar'),
	('131', 0, '2018-04-16 18:27:16.000000', NULL, '2018-04-16 18:27:16.000000', 'admin', '000000', '1', '454', 'MWI', 'Malawi'),
	('132', 0, '2018-04-16 18:27:17.000000', NULL, '2018-04-16 18:27:17.000000', 'admin', '000000', '1', '458', 'MYS', 'Malaysia'),
	('133', 0, '2018-04-16 18:27:17.000000', NULL, '2018-04-16 18:27:17.000000', 'admin', '000000', '1', '462', 'MDV', 'Maldives'),
	('134', 0, '2018-04-16 18:27:17.000000', NULL, '2018-04-16 18:27:17.000000', 'admin', '000000', '1', '466', 'MLI', 'Mali'),
	('135', 0, '2018-04-16 18:27:17.000000', NULL, '2018-04-16 18:27:17.000000', 'admin', '000000', '1', '470', 'MLT', 'Malta'),
	('136', 0, '2018-04-16 18:27:17.000000', NULL, '2018-04-16 18:27:17.000000', 'admin', '000000', '1', '474', 'MTQ', 'Martinique'),
	('137', 0, '2018-04-16 18:27:17.000000', NULL, '2018-04-16 18:27:17.000000', 'admin', '000000', '1', '478', 'MRT', 'Mauritania'),
	('138', 0, '2018-04-16 18:27:17.000000', NULL, '2018-04-16 18:27:17.000000', 'admin', '000000', '1', '480', 'MUS', 'Mauritius'),
	('139', 0, '2018-04-16 18:27:17.000000', NULL, '2018-04-16 18:27:17.000000', 'admin', '000000', '1', '484', 'MEX', 'Mexico'),
	('14', 0, '2018-04-16 18:27:14.000000', NULL, '2018-04-16 18:27:14.000000', 'admin', '000000', '1', '48', 'BHR', 'Bahrain'),
	('140', 0, '2018-04-16 18:27:17.000000', NULL, '2018-04-16 18:27:17.000000', 'admin', '000000', '1', '492', 'MCO', 'Monaco'),
	('141', 0, '2018-04-16 18:27:17.000000', NULL, '2018-04-16 18:27:17.000000', 'admin', '000000', '1', '496', 'MNG', 'Mongolia'),
	('142', 0, '2018-04-16 18:27:17.000000', NULL, '2018-04-16 18:27:17.000000', 'admin', '000000', '1', '498', 'MDA', 'Moldova'),
	('143', 0, '2018-04-16 18:27:17.000000', NULL, '2018-04-16 18:27:17.000000', 'admin', '000000', '1', '499', 'MNE', 'Montenegro'),
	('144', 0, '2018-04-16 18:27:17.000000', NULL, '2018-04-16 18:27:17.000000', 'admin', '000000', '1', '500', 'MSR', 'Montserrat'),
	('145', 0, '2018-04-16 18:27:17.000000', NULL, '2018-04-16 18:27:17.000000', 'admin', '000000', '1', '504', 'MAR', 'Morocco'),
	('146', 0, '2018-04-16 18:27:17.000000', NULL, '2018-04-16 18:27:17.000000', 'admin', '000000', '1', '508', 'MOZ', 'Mozambique'),
	('147', 0, '2018-04-16 18:27:17.000000', NULL, '2018-04-16 18:27:17.000000', 'admin', '000000', '1', '512', 'OMN', 'Oman'),
	('148', 0, '2018-04-16 18:27:17.000000', NULL, '2018-04-16 18:27:17.000000', 'admin', '000000', '1', '516', 'NAM', 'Namibia'),
	('149', 0, '2018-04-16 18:27:17.000000', NULL, '2018-04-16 18:27:17.000000', 'admin', '000000', '1', '520', 'NRU', 'Nauru'),
	('15', 0, '2018-04-16 18:27:14.000000', NULL, '2018-04-16 18:27:14.000000', 'admin', '000000', '1', '50', 'BGD', 'Bangladesh'),
	('150', 0, '2018-04-16 18:27:17.000000', NULL, '2018-04-16 18:27:17.000000', 'admin', '000000', '1', '524', 'NPL', 'Nepal'),
	('151', 0, '2018-04-16 18:27:17.000000', NULL, '2018-04-16 18:27:17.000000', 'admin', '000000', '1', '528', 'NLD', 'Netherlands'),
	('152', 0, '2018-04-16 18:27:17.000000', NULL, '2018-04-16 18:27:17.000000', 'admin', '000000', '1', '530', 'ANT', 'Netherlands Antilles'),
	('153', 0, '2018-04-16 18:27:17.000000', NULL, '2018-04-16 18:27:17.000000', 'admin', '000000', '1', '533', 'ABW', 'Aruba'),
	('154', 0, '2018-04-16 18:27:17.000000', NULL, '2018-04-16 18:27:17.000000', 'admin', '000000', '1', '540', 'NCL', 'New Caledonia'),
	('155', 0, '2018-04-16 18:27:17.000000', NULL, '2018-04-16 18:27:17.000000', 'admin', '000000', '1', '548', 'VUT', 'Vanuatu'),
	('156', 0, '2018-04-16 18:27:17.000000', NULL, '2018-04-16 18:27:17.000000', 'admin', '000000', '1', '554', 'NZL', 'New Zealand'),
	('157', 0, '2018-04-16 18:27:17.000000', NULL, '2018-04-16 18:27:17.000000', 'admin', '000000', '1', '558', 'NIC', 'Nicaragua'),
	('158', 0, '2018-04-16 18:27:17.000000', NULL, '2018-04-16 18:27:17.000000', 'admin', '000000', '1', '562', 'NER', 'Niger'),
	('159', 0, '2018-04-16 18:27:17.000000', NULL, '2018-04-16 18:27:17.000000', 'admin', '000000', '1', '566', 'NGA', 'Nigeria'),
	('16', 0, '2018-04-16 18:27:14.000000', NULL, '2018-04-16 18:27:14.000000', 'admin', '000000', '1', '51', 'ARM', 'Armenia'),
	('160', 0, '2018-04-16 18:27:17.000000', NULL, '2018-04-16 18:27:17.000000', 'admin', '000000', '1', '570', 'NIU', 'Niue'),
	('161', 0, '2018-04-16 18:27:17.000000', NULL, '2018-04-16 18:27:17.000000', 'admin', '000000', '1', '574', 'NFK', 'Norfolk Island'),
	('162', 0, '2018-04-16 18:27:17.000000', NULL, '2018-04-16 18:27:17.000000', 'admin', '000000', '1', '578', 'NOR', 'Norway'),
	('163', 0, '2018-04-16 18:27:17.000000', NULL, '2018-04-16 18:27:17.000000', 'admin', '000000', '1', '580', 'MNP', 'Northern Mariana Islands'),
	('164', 0, '2018-04-16 18:27:17.000000', NULL, '2018-04-16 18:27:17.000000', 'admin', '000000', '1', '581', 'UMI', 'US Minor Outlying Islands'),
	('165', 0, '2018-04-16 18:27:17.000000', NULL, '2018-04-16 18:27:17.000000', 'admin', '000000', '1', '583', 'FSM', 'Micronesia, Federated States of'),
	('166', 0, '2018-04-16 18:27:17.000000', NULL, '2018-04-16 18:27:17.000000', 'admin', '000000', '1', '584', 'MHL', 'Marshall Islands'),
	('167', 0, '2018-04-16 18:27:17.000000', NULL, '2018-04-16 18:27:17.000000', 'admin', '000000', '1', '585', 'PLW', 'Palau'),
	('168', 0, '2018-04-16 18:27:17.000000', NULL, '2018-04-16 18:27:17.000000', 'admin', '000000', '1', '586', 'PAK', 'Pakistan'),
	('169', 0, '2018-04-16 18:27:17.000000', NULL, '2018-04-16 18:27:17.000000', 'admin', '000000', '1', '591', 'PAN', 'Panama'),
	('17', 0, '2018-04-16 18:27:14.000000', NULL, '2018-04-16 18:27:14.000000', 'admin', '000000', '1', '52', 'BRB', 'Barbados'),
	('170', 0, '2018-04-16 18:27:17.000000', NULL, '2018-04-16 18:27:17.000000', 'admin', '000000', '1', '598', 'PNG', 'Papua New Guinea'),
	('171', 0, '2018-04-16 18:27:17.000000', NULL, '2018-04-16 18:27:17.000000', 'admin', '000000', '1', '600', 'PRY', 'Paraguay'),
	('172', 0, '2018-04-16 18:27:17.000000', NULL, '2018-04-16 18:27:17.000000', 'admin', '000000', '1', '604', 'PER', 'Peru'),
	('173', 0, '2018-04-16 18:27:17.000000', NULL, '2018-04-16 18:27:17.000000', 'admin', '000000', '1', '608', 'PHL', 'Philippines'),
	('174', 0, '2018-04-16 18:27:17.000000', NULL, '2018-04-16 18:27:17.000000', 'admin', '000000', '1', '612', 'PCN', 'Pitcairn'),
	('175', 0, '2018-04-16 18:27:17.000000', NULL, '2018-04-16 18:27:17.000000', 'admin', '000000', '1', '616', 'POL', 'Poland'),
	('176', 0, '2018-04-16 18:27:17.000000', NULL, '2018-04-16 18:27:17.000000', 'admin', '000000', '1', '620', 'PRT', 'Portugal'),
	('177', 0, '2018-04-16 18:27:17.000000', NULL, '2018-04-16 18:27:17.000000', 'admin', '000000', '1', '624', 'GNB', 'Guinea-Bissau'),
	('178', 0, '2018-04-16 18:27:17.000000', NULL, '2018-04-16 18:27:17.000000', 'admin', '000000', '1', '626', 'TLS', 'Timor-Leste'),
	('179', 0, '2018-04-16 18:27:17.000000', NULL, '2018-04-16 18:27:17.000000', 'admin', '000000', '1', '630', 'PRI', 'Puerto Rico'),
	('18', 0, '2018-04-16 18:27:14.000000', NULL, '2018-04-16 18:27:14.000000', 'admin', '000000', '1', '56', 'BEL', 'Belgium'),
	('180', 0, '2018-04-16 18:27:17.000000', NULL, '2018-04-16 18:27:17.000000', 'admin', '000000', '1', '634', 'QAT', 'Qatar'),
	('181', 0, '2018-04-16 18:27:17.000000', NULL, '2018-04-16 18:27:17.000000', 'admin', '000000', '1', '638', 'REU', 'RÃƒÂ©union'),
	('182', 0, '2018-04-16 18:27:17.000000', NULL, '2018-04-16 18:27:17.000000', 'admin', '000000', '1', '642', 'ROU', 'Romania'),
	('183', 0, '2018-04-16 18:27:17.000000', NULL, '2018-04-16 18:27:17.000000', 'admin', '000000', '1', '643', 'RUS', 'Russian Federation'),
	('184', 0, '2018-04-16 18:27:17.000000', NULL, '2018-04-16 18:27:17.000000', 'admin', '000000', '1', '646', 'RWA', 'Rwanda'),
	('185', 0, '2018-04-16 18:27:17.000000', NULL, '2018-04-16 18:27:17.000000', 'admin', '000000', '1', '652', 'BLM', 'Saint-BarthÃƒÂ©lemy'),
	('186', 0, '2018-04-16 18:27:18.000000', NULL, '2018-04-16 18:27:18.000000', 'admin', '000000', '1', '654', 'SHN', 'Saint Helena'),
	('187', 0, '2018-04-16 18:27:18.000000', NULL, '2018-04-16 18:27:18.000000', 'admin', '000000', '1', '659', 'KNA', 'Saint Kitts and Nevis'),
	('188', 0, '2018-04-16 18:27:18.000000', NULL, '2018-04-16 18:27:18.000000', 'admin', '000000', '1', '660', 'AIA', 'Anguilla'),
	('189', 0, '2018-04-16 18:27:18.000000', NULL, '2018-04-16 18:27:18.000000', 'admin', '000000', '1', '662', 'LCA', 'Saint Lucia'),
	('19', 0, '2018-04-16 18:27:14.000000', NULL, '2018-04-16 18:27:14.000000', 'admin', '000000', '1', '60', 'BMU', 'Bermuda'),
	('190', 0, '2018-04-16 18:27:18.000000', NULL, '2018-04-16 18:27:18.000000', 'admin', '000000', '1', '663', 'MAF', 'Saint-Martin (French part)'),
	('191', 0, '2018-04-16 18:27:18.000000', NULL, '2018-04-16 18:27:18.000000', 'admin', '000000', '1', '666', 'SPM', 'Saint Pierre and Miquelon'),
	('192', 0, '2018-04-16 18:27:18.000000', NULL, '2018-04-16 18:27:18.000000', 'admin', '000000', '1', '670', 'VCT', 'Saint Vincent and Grenadines'),
	('193', 0, '2018-04-16 18:27:18.000000', NULL, '2018-04-16 18:27:18.000000', 'admin', '000000', '1', '674', 'SMR', 'San Marino'),
	('194', 0, '2018-04-16 18:27:18.000000', NULL, '2018-04-16 18:27:18.000000', 'admin', '000000', '1', '678', 'STP', 'Sao Tome and Principe'),
	('195', 0, '2018-04-16 18:27:18.000000', NULL, '2018-04-16 18:27:18.000000', 'admin', '000000', '1', '682', 'SAU', 'Saudi Arabia'),
	('196', 0, '2018-04-16 18:27:18.000000', NULL, '2018-04-16 18:27:18.000000', 'admin', '000000', '1', '686', 'SEN', 'Senegal'),
	('197', 0, '2018-04-16 18:27:18.000000', NULL, '2018-04-16 18:27:18.000000', 'admin', '000000', '1', '688', 'SRB', 'Serbia'),
	('198', 0, '2018-04-16 18:27:18.000000', NULL, '2018-04-16 18:27:18.000000', 'admin', '000000', '1', '690', 'SYC', 'Seychelles'),
	('199', 0, '2018-04-16 18:27:18.000000', NULL, '2018-04-16 18:27:18.000000', 'admin', '000000', '1', '694', 'SLE', 'Sierra Leone'),
	('2', 0, '2018-04-16 18:27:14.000000', NULL, '2018-04-16 18:27:14.000000', 'admin', '000000', '1', '8', 'ALB', 'Albania'),
	('20', 0, '2018-04-16 18:27:14.000000', NULL, '2018-04-16 18:27:14.000000', 'admin', '000000', '1', '64', 'BTN', 'Bhutan'),
	('200', 0, '2018-04-16 18:27:18.000000', NULL, '2018-04-16 18:27:18.000000', 'admin', '000000', '1', '702', 'SGP', 'Singapore'),
	('201', 0, '2018-04-16 18:27:18.000000', NULL, '2018-04-16 18:27:18.000000', 'admin', '000000', '1', '703', 'SVK', 'Slovakia'),
	('202', 0, '2018-04-16 18:27:18.000000', NULL, '2018-04-16 18:27:18.000000', 'admin', '000000', '1', '704', 'VNM', 'Viet Nam'),
	('203', 0, '2018-04-16 18:27:18.000000', NULL, '2018-04-16 18:27:18.000000', 'admin', '000000', '1', '705', 'SVN', 'Slovenia'),
	('204', 0, '2018-04-16 18:27:18.000000', NULL, '2018-04-16 18:27:18.000000', 'admin', '000000', '1', '706', 'SOM', 'Somalia'),
	('205', 0, '2018-04-16 18:27:18.000000', NULL, '2018-04-16 18:27:18.000000', 'admin', '000000', '1', '710', 'ZAF', 'South Africa'),
	('206', 0, '2018-04-16 18:27:18.000000', NULL, '2018-04-16 18:27:18.000000', 'admin', '000000', '1', '716', 'ZWE', 'Zimbabwe'),
	('207', 0, '2018-04-16 18:27:18.000000', NULL, '2018-04-16 18:27:18.000000', 'admin', '000000', '1', '724', 'ESP', 'Spain'),
	('208', 0, '2018-04-16 18:27:13.000000', NULL, '2018-04-16 18:27:13.000000', 'admin', '000000', '1', '728', 'SSD', 'South Sudan'),
	('209', 0, '2018-04-16 18:27:13.000000', NULL, '2018-04-16 18:27:13.000000', 'admin', '000000', '1', '732', 'ESH', 'Western Sahara'),
	('21', 0, '2018-04-16 18:27:14.000000', NULL, '2018-04-16 18:27:14.000000', 'admin', '000000', '1', '68', 'BOL', 'Bolivia'),
	('210', 0, '2018-04-16 18:27:13.000000', NULL, '2018-04-16 18:27:13.000000', 'admin', '000000', '1', '736', 'SDN', 'Sudan'),
	('211', 0, '2018-04-16 18:27:13.000000', NULL, '2018-04-16 18:27:13.000000', 'admin', '000000', '1', '740', 'SUR', 'Suriname'),
	('212', 0, '2018-04-16 18:27:13.000000', NULL, '2018-04-16 18:27:13.000000', 'admin', '000000', '1', '744', 'SJM', 'Svalbard and Jan Mayen Islands'),
	('213', 0, '2018-04-16 18:27:13.000000', NULL, '2018-04-16 18:27:13.000000', 'admin', '000000', '1', '748', 'SWZ', 'Swaziland'),
	('214', 0, '2018-04-16 18:27:13.000000', NULL, '2018-04-16 18:27:13.000000', 'admin', '000000', '1', '752', 'SWE', 'Sweden'),
	('215', 0, '2018-04-16 18:27:13.000000', NULL, '2018-04-16 18:27:13.000000', 'admin', '000000', '1', '756', 'CHE', 'Switzerland'),
	('216', 0, '2018-04-16 18:27:13.000000', NULL, '2018-04-16 18:27:13.000000', 'admin', '000000', '1', '760', 'SYR', 'Syrian Arab RepublicÃ‚Â (Syria)'),
	('217', 0, '2018-04-16 18:27:13.000000', NULL, '2018-04-16 18:27:13.000000', 'admin', '000000', '1', '762', 'TJK', 'Tajikistan'),
	('218', 0, '2018-04-16 18:27:13.000000', NULL, '2018-04-16 18:27:13.000000', 'admin', '000000', '1', '764', 'THA', 'Thailand'),
	('219', 0, '2018-04-16 18:27:13.000000', NULL, '2018-04-16 18:27:13.000000', 'admin', '000000', '1', '768', 'TGO', 'Togo'),
	('22', 0, '2018-04-16 18:27:14.000000', NULL, '2018-04-16 18:27:14.000000', 'admin', '000000', '1', '70', 'BIH', 'Bosnia and Herzegovina'),
	('220', 0, '2018-04-16 18:27:13.000000', NULL, '2018-04-16 18:27:13.000000', 'admin', '000000', '1', '772', 'TKL', 'Tokelau'),
	('221', 0, '2018-04-16 18:27:13.000000', NULL, '2018-04-16 18:27:13.000000', 'admin', '000000', '1', '776', 'TON', 'Tonga'),
	('222', 0, '2018-04-16 18:27:13.000000', NULL, '2018-04-16 18:27:13.000000', 'admin', '000000', '1', '780', 'TTO', 'Trinidad and Tobago'),
	('223', 0, '2018-04-16 18:27:13.000000', NULL, '2018-04-16 18:27:13.000000', 'admin', '000000', '1', '784', 'ARE', 'United Arab Emirates'),
	('224', 0, '2018-04-16 18:27:13.000000', NULL, '2018-04-16 18:27:13.000000', 'admin', '000000', '1', '788', 'TUN', 'Tunisia'),
	('225', 0, '2018-04-16 18:27:14.000000', NULL, '2018-04-16 18:27:14.000000', 'admin', '000000', '1', '792', 'TUR', 'Turkey'),
	('226', 0, '2018-04-16 18:27:14.000000', NULL, '2018-04-16 18:27:14.000000', 'admin', '000000', '1', '795', 'TKM', 'Turkmenistan'),
	('227', 0, '2018-04-16 18:27:14.000000', NULL, '2018-04-16 18:27:14.000000', 'admin', '000000', '1', '796', 'TCA', 'Turks and Caicos Islands'),
	('228', 0, '2018-04-16 18:27:14.000000', NULL, '2018-04-16 18:27:14.000000', 'admin', '000000', '1', '798', 'TUV', 'Tuvalu'),
	('229', 0, '2018-04-16 18:27:14.000000', NULL, '2018-04-16 18:27:14.000000', 'admin', '000000', '1', '800', 'UGA', 'Uganda'),
	('23', 0, '2018-04-16 18:27:14.000000', NULL, '2018-04-16 18:27:14.000000', 'admin', '000000', '1', '72', 'BWA', 'Botswana'),
	('230', 0, '2018-04-16 18:27:14.000000', NULL, '2018-04-16 18:27:14.000000', 'admin', '000000', '1', '804', 'UKR', 'Ukraine'),
	('231', 0, '2018-04-16 18:27:14.000000', NULL, '2018-04-16 18:27:14.000000', 'admin', '000000', '1', '807', 'MKD', 'Macedonia, Republic of'),
	('232', 0, '2018-04-16 18:27:14.000000', NULL, '2018-04-16 18:27:14.000000', 'admin', '000000', '1', '818', 'EGY', 'Egypt'),
	('233', 0, '2018-04-16 18:27:14.000000', NULL, '2018-04-16 18:27:14.000000', 'admin', '000000', '1', '826', 'GBR', 'United Kingdom'),
	('234', 0, '2018-04-16 18:27:14.000000', NULL, '2018-04-16 18:27:14.000000', 'admin', '000000', '1', '831', 'GGY', 'Guernsey'),
	('235', 0, '2018-04-16 18:27:14.000000', NULL, '2018-04-16 18:27:14.000000', 'admin', '000000', '1', '832', 'JEY', 'Jersey'),
	('236', 0, '2018-04-16 18:27:14.000000', NULL, '2018-04-16 18:27:14.000000', 'admin', '000000', '1', '833', 'IMN', 'Isle of Man'),
	('237', 0, '2018-04-16 18:27:14.000000', NULL, '2018-04-16 18:27:14.000000', 'admin', '000000', '1', '834', 'TZA', 'Tanzania, United Republic of'),
	('238', 0, '2018-04-16 18:27:14.000000', NULL, '2018-04-16 18:27:14.000000', 'admin', '000000', '1', '840', 'USA', 'United States of America'),
	('239', 0, '2018-04-16 18:27:14.000000', NULL, '2018-04-16 18:27:14.000000', 'admin', '000000', '1', '850', 'VIR', 'Virgin Islands, US'),
	('24', 0, '2018-04-16 18:27:14.000000', NULL, '2018-04-16 18:27:14.000000', 'admin', '000000', '1', '74', 'BVT', 'Bouvet Island'),
	('240', 0, '2018-04-16 18:27:14.000000', NULL, '2018-04-16 18:27:14.000000', 'admin', '000000', '1', '854', 'BFA', 'Burkina Faso'),
	('241', 0, '2018-04-16 18:27:14.000000', NULL, '2018-04-16 18:27:14.000000', 'admin', '000000', '1', '858', 'URY', 'Uruguay'),
	('242', 0, '2018-04-16 18:27:14.000000', NULL, '2018-04-16 18:27:14.000000', 'admin', '000000', '1', '860', 'UZB', 'Uzbekistan'),
	('243', 0, '2018-04-16 18:27:14.000000', NULL, '2018-04-16 18:27:14.000000', 'admin', '000000', '1', '862', 'VEN', 'VenezuelaÃ‚Â (Bolivarian Republic)'),
	('244', 0, '2018-04-16 18:27:14.000000', NULL, '2018-04-16 18:27:14.000000', 'admin', '000000', '1', '876', 'WLF', 'Wallis and Futuna Islands'),
	('245', 0, '2018-04-16 18:27:14.000000', NULL, '2018-04-16 18:27:14.000000', 'admin', '000000', '1', '882', 'WSM', 'Samoa'),
	('246', 0, '2018-04-16 18:27:14.000000', NULL, '2018-04-16 18:27:14.000000', 'admin', '000000', '1', '887', 'YEM', 'Yemen'),
	('247', 0, '2018-04-16 18:27:14.000000', NULL, '2018-04-16 18:27:14.000000', 'admin', '000000', '1', '894', 'ZMB', 'Zambia'),
	('25', 0, '2018-04-16 18:27:14.000000', NULL, '2018-04-16 18:27:14.000000', 'admin', '000000', '1', '76', 'BRA', 'Brazil'),
	('26', 0, '2018-04-16 18:27:14.000000', NULL, '2018-04-16 18:27:14.000000', 'admin', '000000', '1', '84', 'BLZ', 'Belize'),
	('27', 0, '2018-04-16 18:27:14.000000', NULL, '2018-04-16 18:27:14.000000', 'admin', '000000', '1', '86', 'IOT', 'British Indian Ocean Territory'),
	('28', 0, '2018-04-16 18:27:14.000000', NULL, '2018-04-16 18:27:14.000000', 'admin', '000000', '1', '90', 'SLB', 'Solomon Islands'),
	('29', 0, '2018-04-16 18:27:14.000000', NULL, '2018-04-16 18:27:14.000000', 'admin', '000000', '1', '92', 'VGB', 'British Virgin Islands'),
	('3', 0, '2018-04-16 18:27:14.000000', NULL, '2018-04-16 18:27:14.000000', 'admin', '000000', '1', '10', 'ATA', 'Antarctica'),
	('30', 0, '2018-04-16 18:27:15.000000', NULL, '2018-04-16 18:27:15.000000', 'admin', '000000', '1', '96', 'BRN', 'Brunei Darussalam'),
	('31', 0, '2018-04-16 18:27:15.000000', NULL, '2018-04-16 18:27:15.000000', 'admin', '000000', '1', '100', 'BGR', 'Bulgaria'),
	('32', 0, '2018-04-16 18:27:15.000000', NULL, '2018-04-16 18:27:15.000000', 'admin', '000000', '1', '104', 'MMR', 'Myanmar'),
	('33', 0, '2018-04-16 18:27:15.000000', NULL, '2018-04-16 18:27:15.000000', 'admin', '000000', '1', '108', 'BDI', 'Burundi'),
	('34', 0, '2018-04-16 18:27:15.000000', NULL, '2018-04-16 18:27:15.000000', 'admin', '000000', '1', '112', 'BLR', 'Belarus'),
	('35', 0, '2018-04-16 18:27:15.000000', NULL, '2018-04-16 18:27:15.000000', 'admin', '000000', '1', '116', 'KHM', 'Cambodia'),
	('36', 0, '2018-04-16 18:27:15.000000', NULL, '2018-04-16 18:27:15.000000', 'admin', '000000', '1', '120', 'CMR', 'Cameroon'),
	('37', 0, '2018-04-16 18:27:15.000000', NULL, '2018-04-16 18:27:15.000000', 'admin', '000000', '1', '124', 'CAN', 'Canada'),
	('38', 0, '2018-04-16 18:27:15.000000', NULL, '2018-04-16 18:27:15.000000', 'admin', '000000', '1', '132', 'CPV', 'Cape Verde'),
	('39', 0, '2018-04-16 18:27:15.000000', NULL, '2018-04-16 18:27:15.000000', 'admin', '000000', '1', '136', 'CYM', 'Cayman Islands'),
	('4', 0, '2018-04-16 18:27:14.000000', NULL, '2018-04-16 18:27:14.000000', 'admin', '000000', '1', '12', 'DZA', 'Algeria'),
	('40', 0, '2018-04-16 18:27:15.000000', NULL, '2018-04-16 18:27:15.000000', 'admin', '000000', '1', '140', 'CAF', 'Central African Republic'),
	('41', 0, '2018-04-16 18:27:15.000000', NULL, '2018-04-16 18:27:15.000000', 'admin', '000000', '1', '144', 'LKA', 'Sri Lanka'),
	('42', 0, '2018-04-16 18:27:15.000000', NULL, '2018-04-16 18:27:15.000000', 'admin', '000000', '1', '148', 'TCD', 'Chad'),
	('43', 0, '2018-04-16 18:27:15.000000', NULL, '2018-04-16 18:27:15.000000', 'admin', '000000', '1', '152', 'CHL', 'Chile'),
	('44', 0, '2018-04-16 18:27:15.000000', NULL, '2018-04-16 18:27:15.000000', 'admin', '000000', '1', '156', 'CHN', 'China'),
	('45', 0, '2018-04-16 18:27:15.000000', NULL, '2018-04-16 18:27:15.000000', 'admin', '000000', '1', '158', 'TWN', 'Taiwan, Republic of China'),
	('46', 0, '2018-04-16 18:27:15.000000', NULL, '2018-04-16 18:27:15.000000', 'admin', '000000', '1', '162', 'CXR', 'Christmas Island'),
	('47', 0, '2018-04-16 18:27:15.000000', NULL, '2018-04-16 18:27:15.000000', 'admin', '000000', '1', '166', 'CCK', 'Cocos (Keeling) Islands'),
	('48', 0, '2018-04-16 18:27:15.000000', NULL, '2018-04-16 18:27:15.000000', 'admin', '000000', '1', '170', 'COL', 'Colombia'),
	('49', 0, '2018-04-16 18:27:15.000000', NULL, '2018-04-16 18:27:15.000000', 'admin', '000000', '1', '174', 'COM', 'Comoros'),
	('5', 0, '2018-04-16 18:27:14.000000', NULL, '2018-04-16 18:27:14.000000', 'admin', '000000', '1', '16', 'ASM', 'American Samoa'),
	('50', 0, '2018-04-16 18:27:15.000000', NULL, '2018-04-16 18:27:15.000000', 'admin', '000000', '1', '175', 'MYT', 'Mayotte'),
	('51', 0, '2018-04-16 18:27:15.000000', NULL, '2018-04-16 18:27:15.000000', 'admin', '000000', '1', '178', 'COG', 'CongoÃ‚Â (Brazzaville)'),
	('52', 0, '2018-04-16 18:27:15.000000', NULL, '2018-04-16 18:27:15.000000', 'admin', '000000', '1', '180', 'COD', 'Congo, (Kinshasa)'),
	('53', 0, '2018-04-16 18:27:15.000000', NULL, '2018-04-16 18:27:15.000000', 'admin', '000000', '1', '184', 'COK', 'Cook Islands'),
	('54', 0, '2018-04-16 18:27:15.000000', NULL, '2018-04-16 18:27:15.000000', 'admin', '000000', '1', '188', 'CRI', 'Costa Rica'),
	('55', 0, '2018-04-16 18:27:15.000000', NULL, '2018-04-16 18:27:15.000000', 'admin', '000000', '1', '191', 'HRV', 'Croatia'),
	('56', 0, '2018-04-16 18:27:15.000000', NULL, '2018-04-16 18:27:15.000000', 'admin', '000000', '1', '192', 'CUB', 'Cuba'),
	('57', 0, '2018-04-16 18:27:15.000000', NULL, '2018-04-16 18:27:15.000000', 'admin', '000000', '1', '196', 'CYP', 'Cyprus'),
	('58', 0, '2018-04-16 18:27:15.000000', NULL, '2018-04-16 18:27:15.000000', 'admin', '000000', '1', '203', 'CZE', 'Czech Republic'),
	('59', 0, '2018-04-16 18:27:15.000000', NULL, '2018-04-16 18:27:15.000000', 'admin', '000000', '1', '204', 'BEN', 'Benin'),
	('6', 0, '2018-04-16 18:27:14.000000', NULL, '2018-04-16 18:27:14.000000', 'admin', '000000', '1', '20', 'AND', 'Andorra'),
	('60', 0, '2018-04-16 18:27:15.000000', NULL, '2018-04-16 18:27:15.000000', 'admin', '000000', '1', '208', 'DNK', 'Denmark'),
	('61', 0, '2018-04-16 18:27:15.000000', NULL, '2018-04-16 18:27:15.000000', 'admin', '000000', '1', '212', 'DMA', 'Dominica'),
	('62', 0, '2018-04-16 18:27:15.000000', NULL, '2018-04-16 18:27:15.000000', 'admin', '000000', '1', '214', 'DOM', 'Dominican Republic'),
	('63', 0, '2018-04-16 18:27:15.000000', NULL, '2018-04-16 18:27:15.000000', 'admin', '000000', '1', '218', 'ECU', 'Ecuador'),
	('64', 0, '2018-04-16 18:27:15.000000', NULL, '2018-04-16 18:27:15.000000', 'admin', '000000', '1', '222', 'SLV', 'El Salvador'),
	('65', 0, '2018-04-16 18:27:15.000000', NULL, '2018-04-16 18:27:15.000000', 'admin', '000000', '1', '226', 'GNQ', 'Equatorial Guinea'),
	('66', 0, '2018-04-16 18:27:15.000000', NULL, '2018-04-16 18:27:15.000000', 'admin', '000000', '1', '231', 'ETH', 'Ethiopia'),
	('67', 0, '2018-04-16 18:27:15.000000', NULL, '2018-04-16 18:27:15.000000', 'admin', '000000', '1', '232', 'ERI', 'Eritrea'),
	('68', 0, '2018-04-16 18:27:15.000000', NULL, '2018-04-16 18:27:15.000000', 'admin', '000000', '1', '233', 'EST', 'Estonia'),
	('69', 0, '2018-04-16 18:27:15.000000', NULL, '2018-04-16 18:27:15.000000', 'admin', '000000', '1', '234', 'FRO', 'Faroe Islands'),
	('7', 0, '2018-04-16 18:27:14.000000', NULL, '2018-04-16 18:27:14.000000', 'admin', '000000', '1', '24', 'AGO', 'Angola'),
	('70', 0, '2018-04-16 18:27:15.000000', NULL, '2018-04-16 18:27:15.000000', 'admin', '000000', '1', '238', 'FLK', 'Falkland Islands (Malvinas)'),
	('71', 0, '2018-04-16 18:27:15.000000', NULL, '2018-04-16 18:27:15.000000', 'admin', '000000', '1', '239', 'SGS', 'South Georgia and the South Sandwich Islands'),
	('72', 0, '2018-04-16 18:27:15.000000', NULL, '2018-04-16 18:27:15.000000', 'admin', '000000', '1', '242', 'FJI', 'Fiji'),
	('73', 0, '2018-04-16 18:27:15.000000', NULL, '2018-04-16 18:27:15.000000', 'admin', '000000', '1', '246', 'FIN', 'Finland'),
	('74', 0, '2018-04-16 18:27:15.000000', NULL, '2018-04-16 18:27:15.000000', 'admin', '000000', '1', '248', 'ALA', 'Aland Islands'),
	('75', 0, '2018-04-16 18:27:15.000000', NULL, '2018-04-16 18:27:15.000000', 'admin', '000000', '1', '250', 'FRA', 'France'),
	('76', 0, '2018-04-16 18:27:15.000000', NULL, '2018-04-16 18:27:15.000000', 'admin', '000000', '1', '254', 'GUF', 'French Guiana'),
	('77', 0, '2018-04-16 18:27:15.000000', NULL, '2018-04-16 18:27:15.000000', 'admin', '000000', '1', '258', 'PYF', 'French Polynesia'),
	('78', 0, '2018-04-16 18:27:15.000000', NULL, '2018-04-16 18:27:15.000000', 'admin', '000000', '1', '260', 'ATF', 'French Southern Territories'),
	('79', 0, '2018-04-16 18:27:16.000000', NULL, '2018-04-16 18:27:16.000000', 'admin', '000000', '1', '262', 'DJI', 'Djibouti'),
	('8', 0, '2018-04-16 18:27:14.000000', NULL, '2018-04-16 18:27:14.000000', 'admin', '000000', '1', '28', 'ATG', 'Antigua and Barbuda'),
	('80', 0, '2018-04-16 18:27:16.000000', NULL, '2018-04-16 18:27:16.000000', 'admin', '000000', '1', '266', 'GAB', 'Gabon'),
	('81', 0, '2018-04-16 18:27:16.000000', NULL, '2018-04-16 18:27:16.000000', 'admin', '000000', '1', '268', 'GEO', 'Georgia'),
	('82', 0, '2018-04-16 18:27:16.000000', NULL, '2018-04-16 18:27:16.000000', 'admin', '000000', '1', '270', 'GMB', 'Gambia'),
	('83', 0, '2018-04-16 18:27:16.000000', NULL, '2018-04-16 18:27:16.000000', 'admin', '000000', '1', '275', 'PSE', 'Palestinian Territory'),
	('84', 0, '2018-04-16 18:27:16.000000', NULL, '2018-04-16 18:27:16.000000', 'admin', '000000', '1', '276', 'DEU', 'Germany'),
	('85', 0, '2018-04-16 18:27:16.000000', NULL, '2018-04-16 18:27:16.000000', 'admin', '000000', '1', '288', 'GHA', 'Ghana'),
	('86', 0, '2018-04-16 18:27:16.000000', NULL, '2018-04-16 18:27:16.000000', 'admin', '000000', '1', '292', 'GIB', 'Gibraltar'),
	('87', 0, '2018-04-16 18:27:16.000000', NULL, '2018-04-16 18:27:16.000000', 'admin', '000000', '1', '296', 'KIR', 'Kiribati'),
	('88', 0, '2018-04-16 18:27:16.000000', NULL, '2018-04-16 18:27:16.000000', 'admin', '000000', '1', '300', 'GRC', 'Greece'),
	('89', 0, '2018-04-16 18:27:16.000000', NULL, '2018-04-16 18:27:16.000000', 'admin', '000000', '1', '304', 'GRL', 'Greenland'),
	('9', 0, '2018-04-16 18:27:14.000000', NULL, '2018-04-16 18:27:14.000000', 'admin', '000000', '1', '31', 'AZE', 'Azerbaijan'),
	('90', 0, '2018-04-16 18:27:16.000000', NULL, '2018-04-16 18:27:16.000000', 'admin', '000000', '1', '308', 'GRD', 'Grenada'),
	('91', 0, '2018-04-16 18:27:16.000000', NULL, '2018-04-16 18:27:16.000000', 'admin', '000000', '1', '312', 'GLP', 'Guadeloupe'),
	('92', 0, '2018-04-16 18:27:16.000000', NULL, '2018-04-16 18:27:16.000000', 'admin', '000000', '1', '316', 'GUM', 'Guam'),
	('93', 0, '2018-04-16 18:27:16.000000', NULL, '2018-04-16 18:27:16.000000', 'admin', '000000', '1', '320', 'GTM', 'Guatemala'),
	('94', 0, '2018-04-16 18:27:16.000000', NULL, '2018-04-16 18:27:16.000000', 'admin', '000000', '1', '324', 'GIN', 'Guinea'),
	('95', 0, '2018-04-16 18:27:16.000000', NULL, '2018-04-16 18:27:16.000000', 'admin', '000000', '1', '328', 'GUY', 'Guyana'),
	('96', 0, '2018-04-16 18:27:16.000000', NULL, '2018-04-16 18:27:16.000000', 'admin', '000000', '1', '332', 'HTI', 'Haiti'),
	('97', 0, '2018-04-16 18:27:16.000000', NULL, '2018-04-16 18:27:16.000000', 'admin', '000000', '1', '334', 'HMD', 'Heard and Mcdonald Islands'),
	('98', 0, '2018-04-16 18:27:16.000000', NULL, '2018-04-16 18:27:16.000000', 'admin', '000000', '1', '336', 'VAT', 'Holy SeeÃ‚Â (Vatican City State)'),
	('99', 0, '2018-04-16 18:27:16.000000', NULL, '2018-04-16 18:27:16.000000', 'admin', '000000', '1', '340', 'HND', 'Honduras');
/*!40000 ALTER TABLE `ref_country_iso` ENABLE KEYS */;

-- Dumping structure for table yug_owner.ref_currency
CREATE TABLE IF NOT EXISTS `ref_currency` (
  `ID_PK` varchar(50) COLLATE utf8_bin NOT NULL,
  `VERSION` decimal(22,0) NOT NULL,
  `CREATED_TS` datetime(6) NOT NULL,
  `DELETED_TS` datetime(6) DEFAULT NULL,
  `UPDATED_TS` datetime(6) NOT NULL,
  `UPDATED_BY_USER` varchar(50) COLLATE utf8_bin NOT NULL,
  `UPDATED_BY_TXN_ID` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `CONFIG_LANGUAGE_CODE_KEY` varchar(50) COLLATE utf8_bin NOT NULL,
  `KEY` varchar(50) COLLATE utf8_bin NOT NULL,
  `VALUE` varchar(50) COLLATE utf8_bin NOT NULL,
  `DESCRIPTION` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_PK`),
  UNIQUE KEY `REF_CURRENCY_UNIQUEKEY` (`KEY`,`CONFIG_LANGUAGE_CODE_KEY`),
  KEY `INDX_REF_CURRENCY_1` (`KEY`),
  KEY `INDX_REF_CURRENCY_2` (`VALUE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- Dumping data for table yug_owner.ref_currency: ~133 rows (approximately)
/*!40000 ALTER TABLE `ref_currency` DISABLE KEYS */;
INSERT INTO `ref_currency` (`ID_PK`, `VERSION`, `CREATED_TS`, `DELETED_TS`, `UPDATED_TS`, `UPDATED_BY_USER`, `UPDATED_BY_TXN_ID`, `CONFIG_LANGUAGE_CODE_KEY`, `KEY`, `VALUE`, `DESCRIPTION`) VALUES
	('1', 0, '2018-04-16 17:59:55.000000', NULL, '2018-04-16 17:59:55.000000', 'admin', '000000', '1', '0', '00', 'UNDEFINED '),
	('10', 0, '2018-04-16 17:59:55.000000', NULL, '2018-04-16 17:59:55.000000', 'admin', '000000', '1', '60', 'BMD', 'BERMUDA DOLLAR '),
	('100', 0, '2018-04-16 17:59:57.000000', NULL, '2018-04-16 17:59:57.000000', 'admin', '000000', '1', '784', 'AED', 'U.A.E. DIRHAM '),
	('101', 0, '2018-04-16 17:59:57.000000', NULL, '2018-04-16 17:59:57.000000', 'admin', '000000', '1', '788', 'TND', 'TUNISIAN DINAR '),
	('102', 0, '2018-04-16 17:59:57.000000', NULL, '2018-04-16 17:59:57.000000', 'admin', '000000', '1', '792', 'TRL', 'TURKISH LIRA '),
	('103', 0, '2018-04-16 17:59:57.000000', NULL, '2018-04-16 17:59:57.000000', 'admin', '000000', '1', '800', 'UGX', 'UGANDA SHILLING '),
	('104', 0, '2018-04-16 17:59:57.000000', NULL, '2018-04-16 17:59:57.000000', 'admin', '000000', '1', '818', 'EGP', 'EGYPTIAN POUND '),
	('105', 0, '2018-04-16 17:59:57.000000', NULL, '2018-04-16 17:59:57.000000', 'admin', '000000', '1', '826', 'GBP', 'STERLING '),
	('106', 0, '2018-04-16 17:59:57.000000', NULL, '2018-04-16 17:59:57.000000', 'admin', '000000', '1', '834', 'TZS', 'TANZANIAN SHILLING '),
	('107', 0, '2018-04-16 17:59:57.000000', NULL, '2018-04-16 17:59:57.000000', 'admin', '000000', '1', '840', 'USD', 'UNITED STATES DOLLAR '),
	('108', 0, '2018-04-16 17:59:57.000000', NULL, '2018-04-16 17:59:57.000000', 'admin', '000000', '1', '858', 'UYU', 'URUGUAY PESO '),
	('109', 0, '2018-04-16 17:59:57.000000', NULL, '2018-04-16 17:59:57.000000', 'admin', '000000', '1', '860', 'UZS', 'UZBEKISTAN SUM '),
	('11', 0, '2018-04-16 17:59:55.000000', NULL, '2018-04-16 17:59:55.000000', 'admin', '000000', '1', '72', 'BWP', 'BOTSWANA PULA '),
	('110', 0, '2018-04-16 17:59:57.000000', NULL, '2018-04-16 17:59:57.000000', 'admin', '000000', '1', '862', 'VEB', 'VENEZUELAN BOLIVAR '),
	('111', 0, '2018-04-16 17:59:57.000000', NULL, '2018-04-16 17:59:57.000000', 'admin', '000000', '1', '894', 'ZMK', 'ZAMBIAN KWACHA '),
	('112', 0, '2018-04-16 17:59:57.000000', NULL, '2018-04-16 17:59:57.000000', 'admin', '000000', '1', '901', 'TWD', 'NEW TAIWAN DOLLAR '),
	('113', 0, '2018-04-16 17:59:57.000000', NULL, '2018-04-16 17:59:57.000000', 'admin', '000000', '1', '936', 'GHS', 'GHANA CEDI '),
	('114', 0, '2018-04-16 17:59:57.000000', NULL, '2018-04-16 17:59:57.000000', 'admin', '000000', '1', '941', 'RSD', 'SERBIAN DINAR '),
	('115', 0, '2018-04-16 17:59:57.000000', NULL, '2018-04-16 17:59:57.000000', 'admin', '000000', '1', '943', 'MZN', 'MOZAMBIQUE METICAL'),
	('116', 0, '2018-04-16 17:59:57.000000', NULL, '2018-04-16 17:59:57.000000', 'admin', '000000', '1', '946', 'RON', 'NEW ROMANIAN LEU '),
	('117', 0, '2018-04-16 17:59:57.000000', NULL, '2018-04-16 17:59:57.000000', 'admin', '000000', '1', '949', 'TRY', 'NEW TURKISH LIRA '),
	('118', 0, '2018-04-16 17:59:57.000000', NULL, '2018-04-16 17:59:57.000000', 'admin', '000000', '1', '950', 'XAF', 'CFA FRANC BEAC '),
	('119', 0, '2018-04-16 17:59:57.000000', NULL, '2018-04-16 17:59:57.000000', 'admin', '000000', '1', '951', 'XCD', 'E. CARIBBEAN DOLLAR '),
	('12', 0, '2018-04-16 17:59:55.000000', NULL, '2018-04-16 17:59:55.000000', 'admin', '000000', '1', '84', 'BZD', 'BELIZE DOLLAR '),
	('120', 0, '2018-04-16 17:59:57.000000', NULL, '2018-04-16 17:59:57.000000', 'admin', '000000', '1', '952', 'XOF', 'CFA FRANC BCEAO '),
	('121', 0, '2018-04-16 17:59:57.000000', NULL, '2018-04-16 17:59:57.000000', 'admin', '000000', '1', '959', 'XAU', 'GOLD '),
	('122', 0, '2018-04-16 17:59:57.000000', NULL, '2018-04-16 17:59:57.000000', 'admin', '000000', '1', '960', 'XDR', 'SPEC DRAWING RIGHTS '),
	('123', 0, '2018-04-16 17:59:57.000000', NULL, '2018-04-16 17:59:57.000000', 'admin', '000000', '1', '961', 'XAG', 'SILVER '),
	('124', 0, '2018-04-16 17:59:57.000000', NULL, '2018-04-16 17:59:57.000000', 'admin', '000000', '1', '967', 'ZMW', 'ZAMBIAN KWACHA'),
	('125', 0, '2018-04-16 17:59:57.000000', NULL, '2018-04-16 17:59:57.000000', 'admin', '000000', '1', '969', 'MGA', 'MADAGASGAR NEW FRANC '),
	('126', 0, '2018-04-16 17:59:57.000000', NULL, '2018-04-16 17:59:57.000000', 'admin', '000000', '1', '978', 'EUR', 'EURO '),
	('127', 0, '2018-04-16 17:59:57.000000', NULL, '2018-04-16 17:59:57.000000', 'admin', '000000', '1', '980', 'UAH', 'UKRAINE HRYVNIA '),
	('128', 0, '2018-04-16 17:59:57.000000', NULL, '2018-04-16 17:59:57.000000', 'admin', '000000', '1', '985', 'PLN', 'POLISH ZLOTY '),
	('129', 0, '2018-04-16 17:59:57.000000', NULL, '2018-04-16 17:59:57.000000', 'admin', '000000', '1', '986', 'BRL', 'BRAZILIAN REAL '),
	('13', 0, '2018-04-16 17:59:55.000000', NULL, '2018-04-16 17:59:55.000000', 'admin', '000000', '1', '100', 'BGL', 'BULGARIAN LEV '),
	('130', 0, '2018-04-16 17:59:57.000000', NULL, '2018-04-16 17:59:57.000000', 'admin', '000000', '1', '991', 'ZAL', 'S.A. FINANCIAL RAND '),
	('131', 0, '2018-04-16 17:59:57.000000', NULL, '2018-04-16 17:59:57.000000', 'admin', '000000', '1', '998', 'USS', 'USD SAFES '),
	('132', 0, '2018-04-16 17:59:57.000000', NULL, '2018-04-16 17:59:57.000000', 'admin', '000000', '1', '999', 'CNH', 'CHINESE YUAN RENMINBI'),
	('133', 0, '2018-04-16 17:59:57.000000', NULL, '2018-04-16 17:59:57.000000', 'admin', '000000', '1', '1000', 'SCH', 'AUSTRIAN SHILLING'),
	('14', 0, '2018-04-16 17:59:55.000000', NULL, '2018-04-16 17:59:55.000000', 'admin', '000000', '1', '104', 'MMK', 'MYANMA KYAT '),
	('15', 0, '2018-04-16 17:59:55.000000', NULL, '2018-04-16 17:59:55.000000', 'admin', '000000', '1', '124', 'CAD', 'CANADIAN DOLLAR '),
	('16', 0, '2018-04-16 17:59:55.000000', NULL, '2018-04-16 17:59:55.000000', 'admin', '000000', '1', '136', 'KYD', 'CAYMAN ISLES DOLLAR '),
	('17', 0, '2018-04-16 17:59:55.000000', NULL, '2018-04-16 17:59:55.000000', 'admin', '000000', '1', '144', 'LKR', 'SRI LANKA RUPEE '),
	('18', 0, '2018-04-16 17:59:55.000000', NULL, '2018-04-16 17:59:55.000000', 'admin', '000000', '1', '152', 'CLP', 'CHILEAN PESO '),
	('19', 0, '2018-04-16 17:59:55.000000', NULL, '2018-04-16 17:59:55.000000', 'admin', '000000', '1', '156', 'CNY', 'CHINESE RENMINBI '),
	('2', 0, '2018-04-16 17:59:55.000000', NULL, '2018-04-16 17:59:55.000000', 'admin', '000000', '1', '12', 'DZD', 'ALGERIAN DINAR '),
	('20', 0, '2018-04-16 17:59:55.000000', NULL, '2018-04-16 17:59:55.000000', 'admin', '000000', '1', '170', 'COP', 'COLOMBIAN PESO '),
	('21', 0, '2018-04-16 17:59:55.000000', NULL, '2018-04-16 17:59:55.000000', 'admin', '000000', '1', '188', 'CRC', 'COSTA RICAN COLON '),
	('22', 0, '2018-04-16 17:59:55.000000', NULL, '2018-04-16 17:59:55.000000', 'admin', '000000', '1', '190', 'SDF', 'EURO'),
	('23', 0, '2018-04-16 17:59:55.000000', NULL, '2018-04-16 17:59:55.000000', 'admin', '000000', '1', '191', 'HRK', 'CROATIA KUNA '),
	('24', 0, '2018-04-16 17:59:55.000000', NULL, '2018-04-16 17:59:55.000000', 'admin', '000000', '1', '196', 'CYP', 'CYPRUS POUND '),
	('25', 0, '2018-04-16 17:59:55.000000', NULL, '2018-04-16 17:59:55.000000', 'admin', '000000', '1', '203', 'CZK', 'CZECH KORUNA '),
	('26', 0, '2018-04-16 17:59:55.000000', NULL, '2018-04-16 17:59:55.000000', 'admin', '000000', '1', '208', 'DKK', 'DANISH KRONE '),
	('27', 0, '2018-04-16 17:59:55.000000', NULL, '2018-04-16 17:59:55.000000', 'admin', '000000', '1', '222', 'SVC', 'EL SALVADOR COLON '),
	('28', 0, '2018-04-16 17:59:55.000000', NULL, '2018-04-16 17:59:55.000000', 'admin', '000000', '1', '230', 'ETB', 'ETHIOPIAN BIRR '),
	('29', 0, '2018-04-16 17:59:55.000000', NULL, '2018-04-16 17:59:55.000000', 'admin', '000000', '1', '233', 'EEK', 'ESTONIA KROON '),
	('3', 0, '2018-04-16 17:59:55.000000', NULL, '2018-04-16 17:59:55.000000', 'admin', '000000', '1', '32', 'ARS', 'ARGENTINE PESO '),
	('30', 0, '2018-04-16 17:59:55.000000', NULL, '2018-04-16 17:59:55.000000', 'admin', '000000', '1', '242', 'FJD', 'FIJI DOLLAR '),
	('31', 0, '2018-04-16 17:59:55.000000', NULL, '2018-04-16 17:59:55.000000', 'admin', '000000', '1', '246', 'FIM', 'FINNISH MARKKA '),
	('32', 0, '2018-04-16 17:59:55.000000', NULL, '2018-04-16 17:59:55.000000', 'admin', '000000', '1', '250', 'FRF', 'FRENCH FRANC '),
	('33', 0, '2018-04-16 17:59:55.000000', NULL, '2018-04-16 17:59:55.000000', 'admin', '000000', '1', '262', 'DJF', 'DJIBOUTI FRANC '),
	('34', 0, '2018-04-16 17:59:55.000000', NULL, '2018-04-16 17:59:55.000000', 'admin', '000000', '1', '276', 'DEM', 'DEUTSCHE MARK '),
	('35', 0, '2018-04-16 17:59:55.000000', NULL, '2018-04-16 17:59:55.000000', 'admin', '000000', '1', '288', 'GHC', 'GHANA CEDI-OLD '),
	('36', 0, '2018-04-16 17:59:55.000000', NULL, '2018-04-16 17:59:55.000000', 'admin', '000000', '1', '292', 'GIP', 'GIBRALTAR POUND '),
	('37', 0, '2018-04-16 17:59:55.000000', NULL, '2018-04-16 17:59:55.000000', 'admin', '000000', '1', '300', 'GRD', 'GREEK DRACHMA '),
	('38', 0, '2018-04-16 17:59:55.000000', NULL, '2018-04-16 17:59:55.000000', 'admin', '000000', '1', '320', 'GTQ', 'GUATEMALA QUETZAL '),
	('39', 0, '2018-04-16 17:59:55.000000', NULL, '2018-04-16 17:59:55.000000', 'admin', '000000', '1', '328', 'GYD', 'GUYANA DOLLAR '),
	('4', 0, '2018-04-16 17:59:55.000000', NULL, '2018-04-16 17:59:55.000000', 'admin', '000000', '1', '36', 'AUD', 'AUSTRALIAN DOLLAR '),
	('40', 0, '2018-04-16 17:59:55.000000', NULL, '2018-04-16 17:59:55.000000', 'admin', '000000', '1', '340', 'HNL', 'HONDURAS LEMPIRA '),
	('41', 0, '2018-04-16 17:59:55.000000', NULL, '2018-04-16 17:59:55.000000', 'admin', '000000', '1', '344', 'HKD', 'HONG KONG DOLLAR '),
	('42', 0, '2018-04-16 17:59:55.000000', NULL, '2018-04-16 17:59:55.000000', 'admin', '000000', '1', '348', 'HUF', 'HUNGARIAN FORINT '),
	('43', 0, '2018-04-16 17:59:55.000000', NULL, '2018-04-16 17:59:55.000000', 'admin', '000000', '1', '352', 'ISK', 'ICELANDIC KRONA '),
	('44', 0, '2018-04-16 17:59:55.000000', NULL, '2018-04-16 17:59:55.000000', 'admin', '000000', '1', '356', 'INR', 'INDIAN RUPEE '),
	('45', 0, '2018-04-16 17:59:55.000000', NULL, '2018-04-16 17:59:55.000000', 'admin', '000000', '1', '360', 'IDR', 'INDONESIAN RUPIAH '),
	('46', 0, '2018-04-16 17:59:55.000000', NULL, '2018-04-16 17:59:55.000000', 'admin', '000000', '1', '364', 'IRR', 'IRANIAN RIAL '),
	('47', 0, '2018-04-16 17:59:55.000000', NULL, '2018-04-16 17:59:55.000000', 'admin', '000000', '1', '372', 'IEP', 'IRISH POUND '),
	('48', 0, '2018-04-16 17:59:56.000000', NULL, '2018-04-16 17:59:56.000000', 'admin', '000000', '1', '376', 'ILS', 'ISRAELI SHEQEL '),
	('49', 0, '2018-04-16 17:59:56.000000', NULL, '2018-04-16 17:59:56.000000', 'admin', '000000', '1', '380', 'ITL', 'ITALIAN LIRE '),
	('5', 0, '2018-04-16 17:59:55.000000', NULL, '2018-04-16 17:59:55.000000', 'admin', '000000', '1', '44', 'BSD', 'BAHAMAS DOLLAR '),
	('50', 0, '2018-04-16 17:59:56.000000', NULL, '2018-04-16 17:59:56.000000', 'admin', '000000', '1', '388', 'JMD', 'JAMAICAN DOLLAR '),
	('51', 0, '2018-04-16 17:59:56.000000', NULL, '2018-04-16 17:59:56.000000', 'admin', '000000', '1', '392', 'JPY', 'JAPANESE YEN '),
	('52', 0, '2018-04-16 17:59:56.000000', NULL, '2018-04-16 17:59:56.000000', 'admin', '000000', '1', '398', 'KZT', 'KAZAKHSTAN TENGE '),
	('53', 0, '2018-04-16 17:59:56.000000', NULL, '2018-04-16 17:59:56.000000', 'admin', '000000', '1', '400', 'JOD', 'JORDANIAN DINAR '),
	('54', 0, '2018-04-16 17:59:56.000000', NULL, '2018-04-16 17:59:56.000000', 'admin', '000000', '1', '404', 'KES', 'KENYAN SHILLING '),
	('55', 0, '2018-04-16 17:59:56.000000', NULL, '2018-04-16 17:59:56.000000', 'admin', '000000', '1', '410', 'KRW', 'KOREA WON '),
	('56', 0, '2018-04-16 17:59:56.000000', NULL, '2018-04-16 17:59:56.000000', 'admin', '000000', '1', '414', 'KWD', 'KUWAITI DINAR '),
	('57', 0, '2018-04-16 17:59:56.000000', NULL, '2018-04-16 17:59:56.000000', 'admin', '000000', '1', '426', 'LSL', 'LESOTHO LOTI '),
	('58', 0, '2018-04-16 17:59:56.000000', NULL, '2018-04-16 17:59:56.000000', 'admin', '000000', '1', '428', 'LVL', 'LATVIAN LATS '),
	('59', 0, '2018-04-16 17:59:56.000000', NULL, '2018-04-16 17:59:56.000000', 'admin', '000000', '1', '440', 'LTL', 'LITHUANIAN LITAS '),
	('6', 0, '2018-04-16 17:59:55.000000', NULL, '2018-04-16 17:59:55.000000', 'admin', '000000', '1', '48', 'BHD', 'BAHRAINI DINAR '),
	('60', 0, '2018-04-16 17:59:56.000000', NULL, '2018-04-16 17:59:56.000000', 'admin', '000000', '1', '450', 'MGF', 'MALAGASY FRANC '),
	('61', 0, '2018-04-16 17:59:56.000000', NULL, '2018-04-16 17:59:56.000000', 'admin', '000000', '1', '454', 'MWK', 'MALAWI KWACHA '),
	('62', 0, '2018-04-16 17:59:56.000000', NULL, '2018-04-16 17:59:56.000000', 'admin', '000000', '1', '458', 'MYR', 'MALAYSIAN RINGGIT '),
	('63', 0, '2018-04-16 17:59:56.000000', NULL, '2018-04-16 17:59:56.000000', 'admin', '000000', '1', '470', 'MTL', 'MALTESE LIRE '),
	('64', 0, '2018-04-16 17:59:56.000000', NULL, '2018-04-16 17:59:56.000000', 'admin', '000000', '1', '480', 'MUR', 'MAURITIUS RUPEE '),
	('65', 0, '2018-04-16 17:59:56.000000', NULL, '2018-04-16 17:59:56.000000', 'admin', '000000', '1', '484', 'MXN', 'MEXICAN PESO '),
	('66', 0, '2018-04-16 17:59:56.000000', NULL, '2018-04-16 17:59:56.000000', 'admin', '000000', '1', '504', 'MAD', 'MOROCCAN DIRHAM '),
	('67', 0, '2018-04-16 17:59:56.000000', NULL, '2018-04-16 17:59:56.000000', 'admin', '000000', '1', '508', 'MZM', 'MOZAMBIQUE METICAL-OLD '),
	('68', 0, '2018-04-16 17:59:56.000000', NULL, '2018-04-16 17:59:56.000000', 'admin', '000000', '1', '512', 'OMR', 'OMANI RIAL '),
	('69', 0, '2018-04-16 17:59:56.000000', NULL, '2018-04-16 17:59:56.000000', 'admin', '000000', '1', '516', 'NAD', 'NAMIBIA DOLLAR'),
	('7', 0, '2018-04-16 17:59:55.000000', NULL, '2018-04-16 17:59:55.000000', 'admin', '000000', '1', '50', 'BDT', 'BANGLADESH TAKA '),
	('70', 0, '2018-04-16 17:59:56.000000', NULL, '2018-04-16 17:59:56.000000', 'admin', '000000', '1', '528', 'NLG', 'DUTCH GUILDER '),
	('71', 0, '2018-04-16 17:59:56.000000', NULL, '2018-04-16 17:59:56.000000', 'admin', '000000', '1', '532', 'ANG', 'NETH ANTILLES FLORIN '),
	('72', 0, '2018-04-16 17:59:56.000000', NULL, '2018-04-16 17:59:56.000000', 'admin', '000000', '1', '533', 'AWG', 'ARUBA FLORIN '),
	('73', 0, '2018-04-16 17:59:56.000000', NULL, '2018-04-16 17:59:56.000000', 'admin', '000000', '1', '548', 'VUV', 'VANUATU VATU '),
	('74', 0, '2018-04-16 17:59:56.000000', NULL, '2018-04-16 17:59:56.000000', 'admin', '000000', '1', '554', 'NZD', 'NEW ZEALAND DOLLAR '),
	('75', 0, '2018-04-16 17:59:56.000000', NULL, '2018-04-16 17:59:56.000000', 'admin', '000000', '1', '566', 'NGN', 'NIGERIAN NAIRA '),
	('76', 0, '2018-04-16 17:59:56.000000', NULL, '2018-04-16 17:59:56.000000', 'admin', '000000', '1', '578', 'NOK', 'NORWEGIAN KRONE '),
	('77', 0, '2018-04-16 17:59:56.000000', NULL, '2018-04-16 17:59:56.000000', 'admin', '000000', '1', '586', 'PKR', 'PAKISTAN RUPEE '),
	('78', 0, '2018-04-16 17:59:56.000000', NULL, '2018-04-16 17:59:56.000000', 'admin', '000000', '1', '600', 'PYG', 'PARAGUAY GUARANI '),
	('79', 0, '2018-04-16 17:59:56.000000', NULL, '2018-04-16 17:59:56.000000', 'admin', '000000', '1', '604', 'PEN', 'PERU NUEVO SOL '),
	('8', 0, '2018-04-16 17:59:55.000000', NULL, '2018-04-16 17:59:55.000000', 'admin', '000000', '1', '52', 'BBD', 'BARBADOS DOLLAR '),
	('80', 0, '2018-04-16 17:59:56.000000', NULL, '2018-04-16 17:59:56.000000', 'admin', '000000', '1', '608', 'PHP', 'PHILIPPINE PESO '),
	('81', 0, '2018-04-16 17:59:56.000000', NULL, '2018-04-16 17:59:56.000000', 'admin', '000000', '1', '620', 'PTE', 'PORTUGUESE ESCUDO '),
	('82', 0, '2018-04-16 17:59:56.000000', NULL, '2018-04-16 17:59:56.000000', 'admin', '000000', '1', '634', 'QAR', 'QATAR RIAL '),
	('83', 0, '2018-04-16 17:59:56.000000', NULL, '2018-04-16 17:59:56.000000', 'admin', '000000', '1', '643', 'RUB', 'RUSSIAN ROUBLE '),
	('84', 0, '2018-04-16 17:59:56.000000', NULL, '2018-04-16 17:59:56.000000', 'admin', '000000', '1', '682', 'SAR', 'SAUDI ARABIAN RIYAL '),
	('85', 0, '2018-04-16 17:59:56.000000', NULL, '2018-04-16 17:59:56.000000', 'admin', '000000', '1', '690', 'SCR', 'SEYCHELLES RUPEE '),
	('86', 0, '2018-04-16 17:59:56.000000', NULL, '2018-04-16 17:59:56.000000', 'admin', '000000', '1', '694', 'SLL', 'SIERRA LEONE LEONE '),
	('87', 0, '2018-04-16 17:59:56.000000', NULL, '2018-04-16 17:59:56.000000', 'admin', '000000', '1', '702', 'SGD', 'SINGAPORE DOLLAR '),
	('88', 0, '2018-04-16 17:59:56.000000', NULL, '2018-04-16 17:59:56.000000', 'admin', '000000', '1', '703', 'SKK', 'SLOVAKIAN KORUNA '),
	('89', 0, '2018-04-16 17:59:56.000000', NULL, '2018-04-16 17:59:56.000000', 'admin', '000000', '1', '704', 'VND', 'VIETNAM DONG '),
	('9', 0, '2018-04-16 17:59:55.000000', NULL, '2018-04-16 17:59:55.000000', 'admin', '000000', '1', '56', 'BEF', 'BELGIAN FRANC '),
	('90', 0, '2018-04-16 17:59:56.000000', NULL, '2018-04-16 17:59:56.000000', 'admin', '000000', '1', '705', 'SIT', 'SLOVENIAN TOLAR '),
	('91', 0, '2018-04-16 17:59:56.000000', NULL, '2018-04-16 17:59:56.000000', 'admin', '000000', '1', '710', 'ZAR', 'SOUTH AFRICAN RAND '),
	('92', 0, '2018-04-16 17:59:56.000000', NULL, '2018-04-16 17:59:56.000000', 'admin', '000000', '1', '716', 'ZWD', 'ZIMBABWE DOLLAR '),
	('93', 0, '2018-04-16 17:59:56.000000', NULL, '2018-04-16 17:59:56.000000', 'admin', '000000', '1', '724', 'ESP', 'SPANISH PESETA '),
	('94', 0, '2018-04-16 17:59:56.000000', NULL, '2018-04-16 17:59:56.000000', 'admin', '000000', '1', '748', 'SZL', 'SWAZILAND LILANGENI '),
	('95', 0, '2018-04-16 17:59:56.000000', NULL, '2018-04-16 17:59:56.000000', 'admin', '000000', '1', '752', 'SEK', 'SWEDISH KRONA '),
	('96', 0, '2018-04-16 17:59:57.000000', NULL, '2018-04-16 17:59:57.000000', 'admin', '000000', '1', '756', 'CHF', 'SWISS FRANC '),
	('97', 0, '2018-04-16 17:59:57.000000', NULL, '2018-04-16 17:59:57.000000', 'admin', '000000', '1', '760', 'SYP', 'SYRIAN POUND '),
	('98', 0, '2018-04-16 17:59:57.000000', NULL, '2018-04-16 17:59:57.000000', 'admin', '000000', '1', '764', 'THB', 'THAILAND BAHTS '),
	('99', 0, '2018-04-16 17:59:57.000000', NULL, '2018-04-16 17:59:57.000000', 'admin', '000000', '1', '780', 'TTD', 'TRINIDAD DOLLAR ');
/*!40000 ALTER TABLE `ref_currency` ENABLE KEYS */;

-- Dumping structure for table yug_owner.ref_deactivation_reason
CREATE TABLE IF NOT EXISTS `ref_deactivation_reason` (
  `ID_PK` varchar(50) COLLATE utf8_bin NOT NULL,
  `VERSION` decimal(22,0) NOT NULL,
  `CREATED_TS` datetime(6) NOT NULL,
  `DELETED_TS` datetime(6) DEFAULT NULL,
  `UPDATED_TS` datetime(6) NOT NULL,
  `UPDATED_BY_USER` varchar(50) COLLATE utf8_bin NOT NULL,
  `UPDATED_BY_TXN_ID` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `CONFIG_LANGUAGE_CODE_KEY` varchar(50) COLLATE utf8_bin NOT NULL,
  `KEY` varchar(50) COLLATE utf8_bin NOT NULL,
  `VALUE` varchar(50) COLLATE utf8_bin NOT NULL,
  `DESCRIPTION` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_PK`),
  UNIQUE KEY `REFDEACTREASON_UNIQUEKEY` (`KEY`,`CONFIG_LANGUAGE_CODE_KEY`),
  KEY `INDX_REF_DEACTIVATION_REASON_1` (`KEY`),
  KEY `INDX_REF_DEACTIVATION_REASON_2` (`VALUE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- Dumping data for table yug_owner.ref_deactivation_reason: ~4 rows (approximately)
/*!40000 ALTER TABLE `ref_deactivation_reason` DISABLE KEYS */;
INSERT INTO `ref_deactivation_reason` (`ID_PK`, `VERSION`, `CREATED_TS`, `DELETED_TS`, `UPDATED_TS`, `UPDATED_BY_USER`, `UPDATED_BY_TXN_ID`, `CONFIG_LANGUAGE_CODE_KEY`, `KEY`, `VALUE`, `DESCRIPTION`) VALUES
	('1', 0, '2017-07-04 17:13:34.000000', NULL, '2017-07-04 17:13:34.000000', 'admin', '000000', '1', '0', 'UNSPECIFIED', 'Unspecified or unknown reason'),
	('2', 0, '2017-07-04 17:13:34.000000', NULL, '2017-07-04 17:13:34.000000', 'admin', '000000', '1', '1', 'SUSPENDED', 'Suspended'),
	('3', 0, '2017-07-04 17:13:34.000000', NULL, '2017-07-04 17:13:34.000000', 'admin', '000000', '1', '2', 'DECEASED', 'Person is desceased '),
	('4', 0, '2017-07-04 17:13:34.000000', NULL, '2017-07-04 17:13:34.000000', 'admin', '000000', '1', '3', 'TERMINATED', 'Terminated');
/*!40000 ALTER TABLE `ref_deactivation_reason` ENABLE KEYS */;

-- Dumping structure for table yug_owner.ref_entity_object_type
CREATE TABLE IF NOT EXISTS `ref_entity_object_type` (
  `ID_PK` varchar(50) COLLATE utf8_bin NOT NULL,
  `VERSION` decimal(22,0) NOT NULL,
  `CREATED_TS` datetime(6) NOT NULL,
  `DELETED_TS` datetime(6) DEFAULT NULL,
  `UPDATED_TS` datetime(6) NOT NULL,
  `UPDATED_BY_USER` varchar(50) COLLATE utf8_bin NOT NULL,
  `UPDATED_BY_TXN_ID` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `CONFIG_LANGUAGE_CODE_KEY` varchar(50) COLLATE utf8_bin NOT NULL,
  `KEY` varchar(50) COLLATE utf8_bin NOT NULL,
  `VALUE` varchar(50) COLLATE utf8_bin NOT NULL,
  `DESCRIPTION` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_PK`),
  UNIQUE KEY `REFENTITYOBJECTTYPE_UNIQUEKEY` (`KEY`,`CONFIG_LANGUAGE_CODE_KEY`),
  KEY `INDX_REF_ENTITY_OBJECT_TYPE_1` (`KEY`),
  KEY `INDX_REF_ENTITY_OBJECT_TYPE_2` (`VALUE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- Dumping data for table yug_owner.ref_entity_object_type: ~3 rows (approximately)
/*!40000 ALTER TABLE `ref_entity_object_type` DISABLE KEYS */;
INSERT INTO `ref_entity_object_type` (`ID_PK`, `VERSION`, `CREATED_TS`, `DELETED_TS`, `UPDATED_TS`, `UPDATED_BY_USER`, `UPDATED_BY_TXN_ID`, `CONFIG_LANGUAGE_CODE_KEY`, `KEY`, `VALUE`, `DESCRIPTION`) VALUES
	('1', 0, '2017-06-30 13:20:38.000000', NULL, '2017-06-30 13:24:06.000000', 'admin', '00000000', '1', '1', 'LEPERSON', 'Legal Entity of Person type'),
	('2', 0, '2017-06-30 13:20:38.000000', NULL, '2017-06-30 13:24:06.000000', 'admin', '00000000', '1', '2', 'LECORPORATION', 'Legal Entity of Corporation type'),
	('3', 0, '2017-06-30 13:20:38.000000', NULL, '2017-06-30 13:24:06.000000', 'admin', '00000000', '1', '3', 'LEGALENTITY', 'Legal Entity Generic');
/*!40000 ALTER TABLE `ref_entity_object_type` ENABLE KEYS */;

-- Dumping structure for table yug_owner.ref_gender
CREATE TABLE IF NOT EXISTS `ref_gender` (
  `ID_PK` varchar(50) COLLATE utf8_bin NOT NULL,
  `VERSION` decimal(22,0) NOT NULL,
  `CREATED_TS` datetime(6) NOT NULL,
  `DELETED_TS` datetime(6) DEFAULT NULL,
  `UPDATED_TS` datetime(6) NOT NULL,
  `UPDATED_BY_USER` varchar(50) COLLATE utf8_bin NOT NULL,
  `UPDATED_BY_TXN_ID` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `CONFIG_LANGUAGE_CODE_KEY` varchar(50) COLLATE utf8_bin NOT NULL,
  `KEY` varchar(50) COLLATE utf8_bin NOT NULL,
  `VALUE` varchar(50) COLLATE utf8_bin NOT NULL,
  `DESCRIPTION` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_PK`),
  UNIQUE KEY `REF_GENDER_UNIQUEKEY` (`KEY`,`CONFIG_LANGUAGE_CODE_KEY`),
  KEY `INDX_REF_GENDER_1` (`KEY`),
  KEY `INDX_REF_GENDER_2` (`VALUE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- Dumping data for table yug_owner.ref_gender: ~5 rows (approximately)
/*!40000 ALTER TABLE `ref_gender` DISABLE KEYS */;
INSERT INTO `ref_gender` (`ID_PK`, `VERSION`, `CREATED_TS`, `DELETED_TS`, `UPDATED_TS`, `UPDATED_BY_USER`, `UPDATED_BY_TXN_ID`, `CONFIG_LANGUAGE_CODE_KEY`, `KEY`, `VALUE`, `DESCRIPTION`) VALUES
	('1', 0, '2017-07-04 17:23:00.000000', NULL, '2017-07-04 17:23:00.000000', 'admin', '000000', '1', '1', 'MALE', NULL),
	('2', 0, '2017-07-04 17:23:00.000000', NULL, '2017-07-04 17:23:00.000000', 'admin', '000000', '1', '2', 'FEMALE', NULL),
	('3', 0, '2017-07-04 17:23:00.000000', NULL, '2017-07-04 17:23:00.000000', 'admin', '000000', '1', '3', 'NO DISCLOSE', ' '),
	('f5a4d61a-da51-4430-9718-1dcb2532d94d', 0, '2018-05-02 16:39:27.557000', NULL, '2018-05-02 16:39:27.557000', 'admin', '123456', '1', '3334', 'TEST', 'TETS'),
	('fab0229d-a3a5-41b4-83fc-0969b8fb1012', 0, '2018-05-02 16:25:16.551000', NULL, '2018-05-02 16:25:16.551000', 'admin', '123456', '1', '3333', 'TEST', 'TETS');
/*!40000 ALTER TABLE `ref_gender` ENABLE KEYS */;

-- Dumping structure for table yug_owner.ref_group_subtype
CREATE TABLE IF NOT EXISTS `ref_group_subtype` (
  `ID_PK` varchar(50) COLLATE utf8_bin NOT NULL,
  `VERSION` decimal(22,0) NOT NULL,
  `CREATED_TS` datetime(6) NOT NULL,
  `DELETED_TS` datetime(6) DEFAULT NULL,
  `UPDATED_TS` datetime(6) NOT NULL,
  `UPDATED_BY_USER` varchar(50) COLLATE utf8_bin NOT NULL,
  `UPDATED_BY_TXN_ID` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `CONFIG_LANGUAGE_CODE_KEY` varchar(50) COLLATE utf8_bin NOT NULL,
  `KEY` varchar(50) COLLATE utf8_bin NOT NULL,
  `VALUE` varchar(50) COLLATE utf8_bin NOT NULL,
  `DESCRIPTION` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_PK`),
  UNIQUE KEY `REF_GROUP_SUBTYPE_UNIQUEKEY` (`KEY`,`CONFIG_LANGUAGE_CODE_KEY`),
  KEY `INDX_REF_GROUP_SUBTYPE_1` (`KEY`),
  KEY `INDX_REF_GROUP_SUBTYPE_2` (`VALUE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- Dumping data for table yug_owner.ref_group_subtype: ~3 rows (approximately)
/*!40000 ALTER TABLE `ref_group_subtype` DISABLE KEYS */;
INSERT INTO `ref_group_subtype` (`ID_PK`, `VERSION`, `CREATED_TS`, `DELETED_TS`, `UPDATED_TS`, `UPDATED_BY_USER`, `UPDATED_BY_TXN_ID`, `CONFIG_LANGUAGE_CODE_KEY`, `KEY`, `VALUE`, `DESCRIPTION`) VALUES
	('1', 0, '2017-07-04 17:32:12.000000', NULL, '2017-07-04 17:32:12.000000', 'admin', '000000', '1', '1', 'SPECIAL FOCUS INDIVIDUALS', NULL),
	('2', 0, '2017-07-04 17:32:12.000000', NULL, '2017-07-04 17:32:12.000000', 'admin', '000000', '1', '2', 'SPECIAL FOCUS CORPORATION', NULL),
	('3', 0, '2017-07-04 17:32:12.000000', NULL, '2017-07-04 17:32:12.000000', 'admin', '000000', '1', '3', 'PREMIUM ACCOUNTS', NULL);
/*!40000 ALTER TABLE `ref_group_subtype` ENABLE KEYS */;

-- Dumping structure for table yug_owner.ref_group_type
CREATE TABLE IF NOT EXISTS `ref_group_type` (
  `ID_PK` varchar(50) COLLATE utf8_bin NOT NULL,
  `VERSION` decimal(22,0) NOT NULL,
  `CREATED_TS` datetime(6) NOT NULL,
  `DELETED_TS` datetime(6) DEFAULT NULL,
  `UPDATED_TS` datetime(6) NOT NULL,
  `UPDATED_BY_USER` varchar(50) COLLATE utf8_bin NOT NULL,
  `UPDATED_BY_TXN_ID` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `CONFIG_LANGUAGE_CODE_KEY` varchar(50) COLLATE utf8_bin NOT NULL,
  `KEY` varchar(50) COLLATE utf8_bin NOT NULL,
  `VALUE` varchar(50) COLLATE utf8_bin NOT NULL,
  `DESCRIPTION` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_PK`),
  UNIQUE KEY `REF_GROUPTYPEUNIQUEKEY` (`KEY`,`CONFIG_LANGUAGE_CODE_KEY`),
  KEY `INDX_REF_GROUP_TYPE_1` (`KEY`),
  KEY `INDX_REF_GROUP_TYPE_2` (`VALUE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- Dumping data for table yug_owner.ref_group_type: ~3 rows (approximately)
/*!40000 ALTER TABLE `ref_group_type` DISABLE KEYS */;
INSERT INTO `ref_group_type` (`ID_PK`, `VERSION`, `CREATED_TS`, `DELETED_TS`, `UPDATED_TS`, `UPDATED_BY_USER`, `UPDATED_BY_TXN_ID`, `CONFIG_LANGUAGE_CODE_KEY`, `KEY`, `VALUE`, `DESCRIPTION`) VALUES
	('1', 0, '2017-07-04 17:30:17.000000', NULL, '2017-07-04 17:30:17.000000', 'admin', '000000', '1', '1', 'LEPERSON', 'Grouping of Persons'),
	('2', 0, '2017-07-04 17:30:17.000000', NULL, '2017-07-04 17:30:17.000000', 'admin', '000000', '1', '2', 'LECORPORATION', 'grouping of corporation'),
	('3', 0, '2017-07-04 17:30:17.000000', NULL, '2017-07-04 17:30:17.000000', 'admin', '000000', '1', '3', 'ACCOUNT', 'grouping of account');
/*!40000 ALTER TABLE `ref_group_type` ENABLE KEYS */;

-- Dumping structure for table yug_owner.ref_highest_edu_qual
CREATE TABLE IF NOT EXISTS `ref_highest_edu_qual` (
  `ID_PK` varchar(50) COLLATE utf8_bin NOT NULL,
  `VERSION` decimal(22,0) NOT NULL,
  `CREATED_TS` datetime(6) NOT NULL,
  `DELETED_TS` datetime(6) DEFAULT NULL,
  `UPDATED_TS` datetime(6) NOT NULL,
  `UPDATED_BY_USER` varchar(50) COLLATE utf8_bin NOT NULL,
  `UPDATED_BY_TXN_ID` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `CONFIG_LANGUAGE_CODE_KEY` varchar(50) COLLATE utf8_bin NOT NULL,
  `KEY` varchar(50) COLLATE utf8_bin NOT NULL,
  `VALUE` varchar(50) COLLATE utf8_bin NOT NULL,
  `DESCRIPTION` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_PK`),
  UNIQUE KEY `REF_HIGHEST_EDU_QUAL_UNIQUEKEY` (`KEY`,`CONFIG_LANGUAGE_CODE_KEY`),
  KEY `INDX_REF_HIGHEST_EDU_QUAL_1` (`KEY`),
  KEY `INDX_REF_HIGHEST_EDU_QUAL_2` (`VALUE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- Dumping data for table yug_owner.ref_highest_edu_qual: ~3 rows (approximately)
/*!40000 ALTER TABLE `ref_highest_edu_qual` DISABLE KEYS */;
INSERT INTO `ref_highest_edu_qual` (`ID_PK`, `VERSION`, `CREATED_TS`, `DELETED_TS`, `UPDATED_TS`, `UPDATED_BY_USER`, `UPDATED_BY_TXN_ID`, `CONFIG_LANGUAGE_CODE_KEY`, `KEY`, `VALUE`, `DESCRIPTION`) VALUES
	('1', 0, '2017-07-04 17:40:04.000000', NULL, '2017-07-04 17:40:04.000000', 'admin', '000000', '1', '1', 'HIGH SCHOOL', NULL),
	('2', 0, '2017-07-04 17:40:04.000000', NULL, '2017-07-04 17:40:04.000000', 'admin', '000000', '1', '2', 'BACHELOR', NULL),
	('3', 0, '2017-07-04 17:40:04.000000', NULL, '2017-07-04 17:40:04.000000', 'admin', '000000', '1', '3', 'MASTERS', NULL);
/*!40000 ALTER TABLE `ref_highest_edu_qual` ENABLE KEYS */;

-- Dumping structure for table yug_owner.ref_identification_type
CREATE TABLE IF NOT EXISTS `ref_identification_type` (
  `ID_PK` varchar(50) COLLATE utf8_bin NOT NULL,
  `VERSION` decimal(22,0) NOT NULL,
  `CREATED_TS` datetime(6) NOT NULL,
  `DELETED_TS` datetime(6) DEFAULT NULL,
  `UPDATED_TS` datetime(6) NOT NULL,
  `UPDATED_BY_USER` varchar(50) COLLATE utf8_bin NOT NULL,
  `UPDATED_BY_TXN_ID` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `CONFIG_LANGUAGE_CODE_KEY` varchar(50) COLLATE utf8_bin NOT NULL,
  `KEY` varchar(50) COLLATE utf8_bin NOT NULL,
  `VALUE` varchar(50) COLLATE utf8_bin NOT NULL,
  `DESCRIPTION` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_PK`),
  UNIQUE KEY `REF_IDENTIFTYPE_UNIQUEKEY` (`KEY`,`CONFIG_LANGUAGE_CODE_KEY`),
  KEY `INDX_REF_IDENTIFICATION_TYPE_1` (`KEY`),
  KEY `INDX_REF_IDENTIFICATION_TYPE_2` (`VALUE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- Dumping data for table yug_owner.ref_identification_type: ~3 rows (approximately)
/*!40000 ALTER TABLE `ref_identification_type` DISABLE KEYS */;
INSERT INTO `ref_identification_type` (`ID_PK`, `VERSION`, `CREATED_TS`, `DELETED_TS`, `UPDATED_TS`, `UPDATED_BY_USER`, `UPDATED_BY_TXN_ID`, `CONFIG_LANGUAGE_CODE_KEY`, `KEY`, `VALUE`, `DESCRIPTION`) VALUES
	('1', 0, '2017-07-04 17:42:15.000000', NULL, '2017-07-04 17:42:15.000000', 'admin', '000000', '1', '1', 'SOCIAL SECURITY NUMBER', NULL),
	('2', 0, '2017-07-04 17:42:15.000000', NULL, '2017-07-04 17:42:15.000000', 'admin', '000000', '1', '2', 'DRIVING LICENCE NUMBER', NULL),
	('3', 0, '2017-07-04 17:42:15.000000', NULL, '2017-07-04 17:42:15.000000', 'admin', '000000', '1', '3', 'TAX IDENTIFICATION NUMBER', NULL);
/*!40000 ALTER TABLE `ref_identification_type` ENABLE KEYS */;

-- Dumping structure for table yug_owner.ref_importance_type
CREATE TABLE IF NOT EXISTS `ref_importance_type` (
  `ID_PK` varchar(50) COLLATE utf8_bin NOT NULL,
  `VERSION` decimal(22,0) NOT NULL,
  `CREATED_TS` datetime(6) NOT NULL,
  `DELETED_TS` datetime(6) DEFAULT NULL,
  `UPDATED_TS` datetime(6) NOT NULL,
  `UPDATED_BY_USER` varchar(50) COLLATE utf8_bin NOT NULL,
  `UPDATED_BY_TXN_ID` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `CONFIG_LANGUAGE_CODE_KEY` varchar(50) COLLATE utf8_bin NOT NULL,
  `KEY` varchar(50) COLLATE utf8_bin NOT NULL,
  `VALUE` varchar(50) COLLATE utf8_bin NOT NULL,
  `DESCRIPTION` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_PK`),
  UNIQUE KEY `REF_IMPORTANCE_TYPE_UNIQUEKEY` (`KEY`,`CONFIG_LANGUAGE_CODE_KEY`),
  KEY `INDX_REF_IMPORTANCE_TYPE_1` (`KEY`),
  KEY `INDX_REF_IMPORTANCE_TYPE_2` (`VALUE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- Dumping data for table yug_owner.ref_importance_type: ~3 rows (approximately)
/*!40000 ALTER TABLE `ref_importance_type` DISABLE KEYS */;
INSERT INTO `ref_importance_type` (`ID_PK`, `VERSION`, `CREATED_TS`, `DELETED_TS`, `UPDATED_TS`, `UPDATED_BY_USER`, `UPDATED_BY_TXN_ID`, `CONFIG_LANGUAGE_CODE_KEY`, `KEY`, `VALUE`, `DESCRIPTION`) VALUES
	('1', 0, '2017-07-04 17:43:06.000000', NULL, '2017-07-04 17:43:06.000000', 'admin', '000000', '1', '1', 'HIGH', NULL),
	('2', 0, '2017-07-04 17:43:06.000000', NULL, '2017-07-04 17:43:06.000000', 'admin', '000000', '1', '2', 'MEDIUM', NULL),
	('3', 0, '2017-07-04 17:43:06.000000', NULL, '2017-07-04 17:43:06.000000', 'admin', '000000', '1', '3', 'LOW', NULL);
/*!40000 ALTER TABLE `ref_importance_type` ENABLE KEYS */;

-- Dumping structure for table yug_owner.ref_inactivation_reason
CREATE TABLE IF NOT EXISTS `ref_inactivation_reason` (
  `ID_PK` varchar(50) COLLATE utf8_bin NOT NULL,
  `VERSION` decimal(22,0) NOT NULL,
  `CREATED_TS` datetime(6) NOT NULL,
  `DELETED_TS` datetime(6) DEFAULT NULL,
  `UPDATED_TS` datetime(6) NOT NULL,
  `UPDATED_BY_USER` varchar(50) COLLATE utf8_bin NOT NULL,
  `UPDATED_BY_TXN_ID` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `CONFIG_LANGUAGE_CODE_KEY` varchar(50) COLLATE utf8_bin NOT NULL,
  `KEY` varchar(50) COLLATE utf8_bin NOT NULL,
  `VALUE` varchar(50) COLLATE utf8_bin NOT NULL,
  `DESCRIPTION` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_PK`),
  UNIQUE KEY `REF_INACTIVATION_REASON_UKEY` (`KEY`,`CONFIG_LANGUAGE_CODE_KEY`),
  KEY `INDX_REF_INACTIVATION_REASON_1` (`KEY`),
  KEY `INDX_REF_INACTIVATION_REASON_2` (`VALUE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- Dumping data for table yug_owner.ref_inactivation_reason: ~3 rows (approximately)
/*!40000 ALTER TABLE `ref_inactivation_reason` DISABLE KEYS */;
INSERT INTO `ref_inactivation_reason` (`ID_PK`, `VERSION`, `CREATED_TS`, `DELETED_TS`, `UPDATED_TS`, `UPDATED_BY_USER`, `UPDATED_BY_TXN_ID`, `CONFIG_LANGUAGE_CODE_KEY`, `KEY`, `VALUE`, `DESCRIPTION`) VALUES
	('1', 1, '2017-10-30 15:41:09.000000', NULL, '2017-10-30 15:42:12.000000', 'admin', '00000000', '1', '1', 'MATCH AND MERGED', 'MATCH AND MERGED'),
	('2', 0, '2017-10-30 15:46:03.000000', NULL, '2017-10-30 15:46:03.000000', 'admin', '00000000', '1', '2', 'EXPIRED', 'EXPIRED'),
	('3', 0, '2017-10-30 15:46:14.000000', NULL, '2017-10-30 15:46:14.000000', 'admin', '00000000', '1', '3', 'DECEASED', 'DECEASED');
/*!40000 ALTER TABLE `ref_inactivation_reason` ENABLE KEYS */;

-- Dumping structure for table yug_owner.ref_industry_code
CREATE TABLE IF NOT EXISTS `ref_industry_code` (
  `ID_PK` varchar(50) COLLATE utf8_bin NOT NULL,
  `VERSION` decimal(22,0) NOT NULL,
  `CREATED_TS` datetime(6) NOT NULL,
  `DELETED_TS` datetime(6) DEFAULT NULL,
  `UPDATED_TS` datetime(6) NOT NULL,
  `UPDATED_BY_USER` varchar(50) COLLATE utf8_bin NOT NULL,
  `UPDATED_BY_TXN_ID` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `CONFIG_LANGUAGE_CODE_KEY` varchar(50) COLLATE utf8_bin NOT NULL,
  `KEY` varchar(50) COLLATE utf8_bin NOT NULL,
  `VALUE` varchar(50) COLLATE utf8_bin NOT NULL,
  `DESCRIPTION` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_PK`),
  UNIQUE KEY `REF_INDUSTRY_CODE_UNIQUEKEY` (`KEY`,`CONFIG_LANGUAGE_CODE_KEY`),
  KEY `INDX_REF_INDUSTRY_CODE_1` (`KEY`),
  KEY `INDX_REF_INDUSTRY_CODE_2` (`VALUE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- Dumping data for table yug_owner.ref_industry_code: ~5 rows (approximately)
/*!40000 ALTER TABLE `ref_industry_code` DISABLE KEYS */;
INSERT INTO `ref_industry_code` (`ID_PK`, `VERSION`, `CREATED_TS`, `DELETED_TS`, `UPDATED_TS`, `UPDATED_BY_USER`, `UPDATED_BY_TXN_ID`, `CONFIG_LANGUAGE_CODE_KEY`, `KEY`, `VALUE`, `DESCRIPTION`) VALUES
	('1', 0, '2017-07-04 17:44:56.000000', NULL, '2017-07-04 17:44:56.000000', 'admin', '000000', '1', '1', 'BANKING', NULL),
	('2', 0, '2017-07-04 17:44:56.000000', NULL, '2017-07-04 17:44:56.000000', 'admin', '000000', '1', '2', 'INSURANCE', NULL),
	('3', 0, '2017-07-04 17:44:56.000000', NULL, '2017-07-04 17:44:56.000000', 'admin', '000000', '1', '3', 'AUTOMOBILE', NULL),
	('4', 0, '2017-07-04 17:44:56.000000', NULL, '2017-07-04 17:44:56.000000', 'admin', '000000', '1', '4', 'MANUFACTURING', NULL),
	('5', 0, '2017-07-04 17:44:56.000000', NULL, '2017-07-04 17:44:56.000000', 'admin', '000000', '1', '5', 'EDUCATION', NULL);
/*!40000 ALTER TABLE `ref_industry_code` ENABLE KEYS */;

-- Dumping structure for table yug_owner.ref_language_code
CREATE TABLE IF NOT EXISTS `ref_language_code` (
  `ID_PK` varchar(50) COLLATE utf8_bin NOT NULL,
  `VERSION` decimal(22,0) NOT NULL,
  `CREATED_TS` datetime(6) NOT NULL,
  `DELETED_TS` datetime(6) DEFAULT NULL,
  `UPDATED_TS` datetime(6) NOT NULL,
  `UPDATED_BY_USER` varchar(50) COLLATE utf8_bin NOT NULL,
  `UPDATED_BY_TXN_ID` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `CONFIG_LANGUAGE_CODE_KEY` varchar(50) COLLATE utf8_bin NOT NULL,
  `KEY` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `VALUE` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `DESCRIPTION` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_PK`),
  UNIQUE KEY `REF_LANGUAGE_CODE_UNIQUEKEY` (`KEY`,`CONFIG_LANGUAGE_CODE_KEY`),
  KEY `INDX_REF_LANGUAGE_CODE_1` (`KEY`),
  KEY `INDX_REF_LANGUAGE_CODE_2` (`VALUE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- Dumping data for table yug_owner.ref_language_code: ~1 rows (approximately)
/*!40000 ALTER TABLE `ref_language_code` DISABLE KEYS */;
INSERT INTO `ref_language_code` (`ID_PK`, `VERSION`, `CREATED_TS`, `DELETED_TS`, `UPDATED_TS`, `UPDATED_BY_USER`, `UPDATED_BY_TXN_ID`, `CONFIG_LANGUAGE_CODE_KEY`, `KEY`, `VALUE`, `DESCRIPTION`) VALUES
	('1', 0, '2017-07-04 17:54:20.000000', NULL, '2017-07-04 17:54:20.000000', 'admin', '000000', '1', '1', 'ENGLISH', 'English Language');
/*!40000 ALTER TABLE `ref_language_code` ENABLE KEYS */;

-- Dumping structure for table yug_owner.ref_le_rating
CREATE TABLE IF NOT EXISTS `ref_le_rating` (
  `ID_PK` varchar(50) COLLATE utf8_bin NOT NULL,
  `VERSION` decimal(22,0) NOT NULL,
  `CREATED_TS` datetime(6) NOT NULL,
  `DELETED_TS` datetime(6) DEFAULT NULL,
  `UPDATED_TS` datetime(6) NOT NULL,
  `UPDATED_BY_USER` varchar(50) COLLATE utf8_bin NOT NULL,
  `UPDATED_BY_TXN_ID` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `CONFIG_LANGUAGE_CODE_KEY` varchar(50) COLLATE utf8_bin NOT NULL,
  `KEY` varchar(50) COLLATE utf8_bin NOT NULL,
  `VALUE` varchar(50) COLLATE utf8_bin NOT NULL,
  `DESCRIPTION` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_PK`),
  UNIQUE KEY `REF_LE_RATING_UNIQUEKEY` (`KEY`,`CONFIG_LANGUAGE_CODE_KEY`),
  KEY `INDX_REF_LE_RATING_1` (`KEY`),
  KEY `INDX_REF_LE_RATING_2` (`VALUE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- Dumping data for table yug_owner.ref_le_rating: ~5 rows (approximately)
/*!40000 ALTER TABLE `ref_le_rating` DISABLE KEYS */;
INSERT INTO `ref_le_rating` (`ID_PK`, `VERSION`, `CREATED_TS`, `DELETED_TS`, `UPDATED_TS`, `UPDATED_BY_USER`, `UPDATED_BY_TXN_ID`, `CONFIG_LANGUAGE_CODE_KEY`, `KEY`, `VALUE`, `DESCRIPTION`) VALUES
	('1', 0, '2017-07-04 17:55:20.000000', NULL, '2017-07-04 17:55:20.000000', 'admin', '000000', '1', '1', 'SINGLE STAR', NULL),
	('2', 0, '2017-07-04 17:55:20.000000', NULL, '2017-07-04 17:55:20.000000', 'admin', '000000', '1', '2', 'TWO STAR', NULL),
	('3', 0, '2017-07-04 17:55:21.000000', NULL, '2017-07-04 17:55:21.000000', 'admin', '000000', '1', '3', 'THREE STAR', NULL),
	('4', 0, '2017-07-04 17:55:21.000000', NULL, '2017-07-04 17:55:21.000000', 'admin', '000000', '1', '4', 'FOUR STAR', NULL),
	('5', 0, '2017-07-04 17:55:21.000000', NULL, '2017-07-04 17:55:21.000000', 'admin', '000000', '1', '5', 'FIVE STAR', NULL);
/*!40000 ALTER TABLE `ref_le_rating` ENABLE KEYS */;

-- Dumping structure for table yug_owner.ref_le_relationship_type
CREATE TABLE IF NOT EXISTS `ref_le_relationship_type` (
  `ID_PK` varchar(50) COLLATE utf8_bin NOT NULL,
  `VERSION` decimal(22,0) NOT NULL,
  `CREATED_TS` datetime(6) NOT NULL,
  `DELETED_TS` datetime(6) DEFAULT NULL,
  `UPDATED_TS` datetime(6) NOT NULL,
  `UPDATED_BY_USER` varchar(50) COLLATE utf8_bin NOT NULL,
  `UPDATED_BY_TXN_ID` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `CONFIG_LANGUAGE_CODE_KEY` varchar(50) COLLATE utf8_bin NOT NULL,
  `KEY` varchar(50) COLLATE utf8_bin NOT NULL,
  `VALUE` varchar(50) COLLATE utf8_bin NOT NULL,
  `DESCRIPTION` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_PK`),
  UNIQUE KEY `REF_LERELATIONTYPE_UNIQUEKEY` (`KEY`,`CONFIG_LANGUAGE_CODE_KEY`),
  KEY `INDX_REF_LE_RELATIONSHIP_TYP_1` (`KEY`),
  KEY `INDX_REF_LE_RELATIONSHIP_TYP_2` (`VALUE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- Dumping data for table yug_owner.ref_le_relationship_type: ~5 rows (approximately)
/*!40000 ALTER TABLE `ref_le_relationship_type` DISABLE KEYS */;
INSERT INTO `ref_le_relationship_type` (`ID_PK`, `VERSION`, `CREATED_TS`, `DELETED_TS`, `UPDATED_TS`, `UPDATED_BY_USER`, `UPDATED_BY_TXN_ID`, `CONFIG_LANGUAGE_CODE_KEY`, `KEY`, `VALUE`, `DESCRIPTION`) VALUES
	('1', 0, '2017-07-04 17:57:12.000000', NULL, '2017-07-04 17:57:12.000000', 'admin', '000000', '1', '1', 'PARENT OF', NULL),
	('2', 0, '2017-07-04 17:57:12.000000', NULL, '2017-07-04 17:57:12.000000', 'admin', '000000', '1', '2', 'CHILD OF', NULL),
	('3', 0, '2017-07-04 17:57:12.000000', NULL, '2017-07-04 17:57:12.000000', 'admin', '000000', '1', '3', 'GUARDIAN OF', NULL),
	('4', 0, '2017-07-04 17:57:12.000000', NULL, '2017-07-04 17:57:12.000000', 'admin', '000000', '1', '4', 'HUSBAND OF', NULL),
	('5', 0, '2017-07-04 17:57:12.000000', NULL, '2017-07-04 17:57:12.000000', 'admin', '000000', '1', '5', 'WIFE OF', NULL);
/*!40000 ALTER TABLE `ref_le_relationship_type` ENABLE KEYS */;

-- Dumping structure for table yug_owner.ref_le_roletype
CREATE TABLE IF NOT EXISTS `ref_le_roletype` (
  `ID_PK` varchar(50) COLLATE utf8_bin NOT NULL,
  `VERSION` decimal(22,0) NOT NULL,
  `CREATED_TS` datetime(6) NOT NULL,
  `DELETED_TS` datetime(6) DEFAULT NULL,
  `UPDATED_TS` datetime(6) NOT NULL,
  `UPDATED_BY_USER` varchar(50) COLLATE utf8_bin NOT NULL,
  `UPDATED_BY_TXN_ID` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `CONFIG_LANGUAGE_CODE_KEY` varchar(50) COLLATE utf8_bin NOT NULL,
  `KEY` varchar(50) COLLATE utf8_bin NOT NULL,
  `VALUE` varchar(50) COLLATE utf8_bin NOT NULL,
  `DESCRIPTION` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_PK`),
  UNIQUE KEY `REF_LE_ROLETYPE_UNIQUEKEY` (`KEY`,`CONFIG_LANGUAGE_CODE_KEY`),
  KEY `INDX_REF_LE_ROLETYPE_1` (`KEY`),
  KEY `INDX_REF_LE_ROLETYPE_2` (`VALUE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- Dumping data for table yug_owner.ref_le_roletype: ~5 rows (approximately)
/*!40000 ALTER TABLE `ref_le_roletype` DISABLE KEYS */;
INSERT INTO `ref_le_roletype` (`ID_PK`, `VERSION`, `CREATED_TS`, `DELETED_TS`, `UPDATED_TS`, `UPDATED_BY_USER`, `UPDATED_BY_TXN_ID`, `CONFIG_LANGUAGE_CODE_KEY`, `KEY`, `VALUE`, `DESCRIPTION`) VALUES
	('1', 0, '2017-07-04 21:46:35.000000', NULL, '2017-07-04 21:46:35.000000', 'admin', '000000', '1', '1', 'OWNER', NULL),
	('2', 0, '2017-07-04 21:46:35.000000', NULL, '2017-07-04 21:46:35.000000', 'admin', '000000', '1', '2', 'JOINT HOLDER', NULL),
	('3', 0, '2017-07-04 21:46:36.000000', NULL, '2017-07-04 21:46:36.000000', 'admin', '000000', '1', '3', 'GUARRENTER', NULL),
	('4', 0, '2017-07-04 21:46:36.000000', NULL, '2017-07-04 21:46:36.000000', 'admin', '000000', '1', '4', 'NOMINEE', NULL),
	('5', 0, '2017-07-04 21:46:36.000000', NULL, '2017-07-04 21:46:36.000000', 'admin', '000000', '1', '5', 'POWER OF ATTORNEE', NULL);
/*!40000 ALTER TABLE `ref_le_roletype` ENABLE KEYS */;

-- Dumping structure for table yug_owner.ref_lobtype
CREATE TABLE IF NOT EXISTS `ref_lobtype` (
  `ID_PK` varchar(50) COLLATE utf8_bin NOT NULL,
  `VERSION` decimal(22,0) NOT NULL,
  `CREATED_TS` datetime(6) NOT NULL,
  `DELETED_TS` datetime(6) DEFAULT NULL,
  `UPDATED_TS` datetime(6) NOT NULL,
  `UPDATED_BY_USER` varchar(50) COLLATE utf8_bin NOT NULL,
  `UPDATED_BY_TXN_ID` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `CONFIG_LANGUAGE_CODE_KEY` varchar(50) COLLATE utf8_bin NOT NULL,
  `KEY` varchar(50) COLLATE utf8_bin NOT NULL,
  `VALUE` varchar(50) COLLATE utf8_bin NOT NULL,
  `DESCRIPTION` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_PK`),
  UNIQUE KEY `REF_LOBTYPE_UNIQUEKEY` (`KEY`,`CONFIG_LANGUAGE_CODE_KEY`),
  KEY `INDX_REF_LOBTYPE_1` (`KEY`),
  KEY `INDX_REF_LOBTYPE_2` (`VALUE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- Dumping data for table yug_owner.ref_lobtype: ~5 rows (approximately)
/*!40000 ALTER TABLE `ref_lobtype` DISABLE KEYS */;
INSERT INTO `ref_lobtype` (`ID_PK`, `VERSION`, `CREATED_TS`, `DELETED_TS`, `UPDATED_TS`, `UPDATED_BY_USER`, `UPDATED_BY_TXN_ID`, `CONFIG_LANGUAGE_CODE_KEY`, `KEY`, `VALUE`, `DESCRIPTION`) VALUES
	('1', 0, '2017-07-04 21:49:43.000000', NULL, '2017-07-04 21:49:43.000000', 'admin', '000000', '1', '1', 'RETAIL', NULL),
	('2', 0, '2017-07-04 21:49:43.000000', NULL, '2017-07-04 21:49:43.000000', 'admin', '000000', '1', '2', 'CORPORATE', NULL),
	('3', 0, '2017-07-04 21:49:43.000000', NULL, '2017-07-04 21:49:43.000000', 'admin', '000000', '1', '3', 'INVESTMENT', NULL),
	('4', 0, '2017-07-04 21:49:43.000000', NULL, '2017-07-04 21:49:43.000000', 'admin', '000000', '1', '4', 'FOREX', NULL),
	('5', 0, '2017-07-04 21:49:43.000000', NULL, '2017-07-04 21:49:43.000000', 'admin', '000000', '1', '5', 'INSURANCE', NULL);
/*!40000 ALTER TABLE `ref_lobtype` ENABLE KEYS */;

-- Dumping structure for table yug_owner.ref_match_actionstatus
CREATE TABLE IF NOT EXISTS `ref_match_actionstatus` (
  `ID_PK` varchar(50) COLLATE utf8_bin NOT NULL,
  `VERSION` decimal(22,0) NOT NULL,
  `CREATED_TS` datetime(6) NOT NULL,
  `DELETED_TS` datetime(6) DEFAULT NULL,
  `UPDATED_TS` datetime(6) NOT NULL,
  `UPDATED_BY_USER` varchar(50) COLLATE utf8_bin NOT NULL,
  `UPDATED_BY_TXN_ID` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `CONFIG_LANGUAGE_CODE_KEY` varchar(50) COLLATE utf8_bin NOT NULL,
  `KEY` varchar(50) COLLATE utf8_bin NOT NULL,
  `VALUE` varchar(50) COLLATE utf8_bin NOT NULL,
  `DESCRIPTION` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_PK`),
  UNIQUE KEY `REF_MATCH_ACTIONSTATUS_UKEY` (`KEY`,`CONFIG_LANGUAGE_CODE_KEY`),
  KEY `INDX_REF_MATCH_ACTIONSTATUS_1` (`KEY`),
  KEY `INDX_REF_MATCH_ACTIONSTATUS_2` (`VALUE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- Dumping data for table yug_owner.ref_match_actionstatus: ~5 rows (approximately)
/*!40000 ALTER TABLE `ref_match_actionstatus` DISABLE KEYS */;
INSERT INTO `ref_match_actionstatus` (`ID_PK`, `VERSION`, `CREATED_TS`, `DELETED_TS`, `UPDATED_TS`, `UPDATED_BY_USER`, `UPDATED_BY_TXN_ID`, `CONFIG_LANGUAGE_CODE_KEY`, `KEY`, `VALUE`, `DESCRIPTION`) VALUES
	('1', 1, '2017-10-30 16:59:54.000000', NULL, '2017-10-30 17:01:46.000000', 'admin', '00000000', '1', '1', 'MATCH CANDIDATE CREATED', 'MATCH CANDIDATE CREATED'),
	('2', 0, '2017-10-30 17:08:08.000000', NULL, '2017-10-30 17:08:08.000000', 'admin', '00000000', '1', '2', 'UNDER REVIEW', 'UNDER REVIEW'),
	('3', 0, '2017-10-30 17:08:24.000000', NULL, '2017-10-30 17:08:24.000000', 'admin', '00000000', '1', '3', 'MERGED', 'MERGED'),
	('4', 0, '2017-10-30 17:08:33.000000', NULL, '2017-10-30 17:08:33.000000', 'admin', '00000000', '1', '4', 'REJECTED', 'REJECTED'),
	('5', 0, '2017-10-30 17:08:42.000000', NULL, '2017-10-30 17:08:42.000000', 'admin', '00000000', '1', '5', 'HOLD', 'HOLD');
/*!40000 ALTER TABLE `ref_match_actionstatus` ENABLE KEYS */;

-- Dumping structure for table yug_owner.ref_match_proposed_action
CREATE TABLE IF NOT EXISTS `ref_match_proposed_action` (
  `ID_PK` varchar(50) COLLATE utf8_bin NOT NULL,
  `VERSION` decimal(22,0) NOT NULL,
  `CREATED_TS` datetime(6) NOT NULL,
  `DELETED_TS` datetime(6) DEFAULT NULL,
  `UPDATED_TS` datetime(6) NOT NULL,
  `UPDATED_BY_USER` varchar(50) COLLATE utf8_bin NOT NULL,
  `UPDATED_BY_TXN_ID` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `CONFIG_LANGUAGE_CODE_KEY` varchar(50) COLLATE utf8_bin NOT NULL,
  `KEY` varchar(50) COLLATE utf8_bin NOT NULL,
  `VALUE` varchar(50) COLLATE utf8_bin NOT NULL,
  `DESCRIPTION` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_PK`),
  UNIQUE KEY `REF_MATCH_PROPOSED_ACTION_UKEY` (`KEY`,`CONFIG_LANGUAGE_CODE_KEY`),
  KEY `INDX_REF_MATCH_PROPOSED_ACTI_1` (`KEY`),
  KEY `INDX_REF_MATCH_PROPOSED_ACTI_2` (`VALUE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- Dumping data for table yug_owner.ref_match_proposed_action: ~3 rows (approximately)
/*!40000 ALTER TABLE `ref_match_proposed_action` DISABLE KEYS */;
INSERT INTO `ref_match_proposed_action` (`ID_PK`, `VERSION`, `CREATED_TS`, `DELETED_TS`, `UPDATED_TS`, `UPDATED_BY_USER`, `UPDATED_BY_TXN_ID`, `CONFIG_LANGUAGE_CODE_KEY`, `KEY`, `VALUE`, `DESCRIPTION`) VALUES
	('1', 0, '2017-10-30 17:10:56.000000', NULL, '2017-10-30 17:10:56.000000', 'admin', '00000000', '1', '1', 'AUTO-MERGE', 'AUTO-MERGE'),
	('2', 0, '2017-10-30 17:11:15.000000', NULL, '2017-10-30 17:11:15.000000', 'admin', '00000000', '1', '2', 'MARK-AS-CANDIDATES', 'MARK-AS-CANDIDATES'),
	('3', 1, '2017-10-30 17:11:39.000000', NULL, '2017-10-30 17:12:18.000000', 'admin', '00000000', '1', '3', 'NO-ACTION', 'NO-ACTION');
/*!40000 ALTER TABLE `ref_match_proposed_action` ENABLE KEYS */;

-- Dumping structure for table yug_owner.ref_match_result
CREATE TABLE IF NOT EXISTS `ref_match_result` (
  `ID_PK` varchar(50) COLLATE utf8_bin NOT NULL,
  `VERSION` decimal(22,0) NOT NULL,
  `CREATED_TS` datetime(6) NOT NULL,
  `DELETED_TS` datetime(6) DEFAULT NULL,
  `UPDATED_TS` datetime(6) NOT NULL,
  `UPDATED_BY_USER` varchar(50) COLLATE utf8_bin NOT NULL,
  `UPDATED_BY_TXN_ID` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `CONFIG_LANGUAGE_CODE_KEY` varchar(50) COLLATE utf8_bin NOT NULL,
  `KEY` varchar(50) COLLATE utf8_bin NOT NULL,
  `VALUE` varchar(50) COLLATE utf8_bin NOT NULL,
  `DESCRIPTION` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_PK`),
  UNIQUE KEY `REF_MATCH_RESULT_UKEY` (`KEY`,`CONFIG_LANGUAGE_CODE_KEY`),
  KEY `INDX_REF_MATCH_RESULT_1` (`KEY`),
  KEY `INDX_REF_MATCH_RESULT_2` (`VALUE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- Dumping data for table yug_owner.ref_match_result: ~4 rows (approximately)
/*!40000 ALTER TABLE `ref_match_result` DISABLE KEYS */;
INSERT INTO `ref_match_result` (`ID_PK`, `VERSION`, `CREATED_TS`, `DELETED_TS`, `UPDATED_TS`, `UPDATED_BY_USER`, `UPDATED_BY_TXN_ID`, `CONFIG_LANGUAGE_CODE_KEY`, `KEY`, `VALUE`, `DESCRIPTION`) VALUES
	('1', 0, '2017-10-30 17:15:52.000000', NULL, '2017-10-30 17:15:52.000000', 'admin', '00000000', '1', '1', 'EXACT MATCH', 'EXACT MATCH'),
	('2', 0, '2017-10-30 17:16:22.000000', NULL, '2017-10-30 17:16:22.000000', 'admin', '00000000', '1', '2', 'CLOSE MATCH - REVIEW', 'CLOSE MATCH - REVIEW'),
	('3', 0, '2017-10-30 17:16:46.000000', NULL, '2017-10-30 17:16:46.000000', 'admin', '00000000', '1', '3', 'PROBABLE MATCH - REVIEW', 'PROBABLE MATCH - REVIEW'),
	('4', 1, '2017-10-30 17:17:00.000000', NULL, '2017-10-30 17:17:23.000000', 'admin', '00000000', '1', '4', 'NON MATCH', 'NON MATCH');
/*!40000 ALTER TABLE `ref_match_result` ENABLE KEYS */;

-- Dumping structure for table yug_owner.ref_match_score
CREATE TABLE IF NOT EXISTS `ref_match_score` (
  `ID_PK` varchar(50) COLLATE utf8_bin NOT NULL,
  `VERSION` decimal(22,0) NOT NULL,
  `CREATED_TS` datetime(6) NOT NULL,
  `DELETED_TS` datetime(6) DEFAULT NULL,
  `UPDATED_TS` datetime(6) NOT NULL,
  `UPDATED_BY_USER` varchar(50) COLLATE utf8_bin NOT NULL,
  `UPDATED_BY_TXN_ID` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `MATCH_ENTITY_OBJECT_NAME` varchar(100) COLLATE utf8_bin NOT NULL,
  `MATCH_ATTR_PATTERN` varchar(50) COLLATE utf8_bin NOT NULL,
  `MATCH_RESULT_REFKEY` varchar(50) COLLATE utf8_bin NOT NULL,
  `MATCH_PROPOSED_ACTION_REFKEY` varchar(50) COLLATE utf8_bin NOT NULL,
  `MATCH_ATTR_PATTERN_DESCR` varchar(500) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_PK`),
  UNIQUE KEY `REF_MATCH_SCORE_UKEY` (`MATCH_PROPOSED_ACTION_REFKEY`,`MATCH_RESULT_REFKEY`,`MATCH_ATTR_PATTERN`,`MATCH_ENTITY_OBJECT_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- Dumping data for table yug_owner.ref_match_score: ~30 rows (approximately)
/*!40000 ALTER TABLE `ref_match_score` DISABLE KEYS */;
INSERT INTO `ref_match_score` (`ID_PK`, `VERSION`, `CREATED_TS`, `DELETED_TS`, `UPDATED_TS`, `UPDATED_BY_USER`, `UPDATED_BY_TXN_ID`, `MATCH_ENTITY_OBJECT_NAME`, `MATCH_ATTR_PATTERN`, `MATCH_RESULT_REFKEY`, `MATCH_PROPOSED_ACTION_REFKEY`, `MATCH_ATTR_PATTERN_DESCR`) VALUES
	('1', 0, '2017-10-30 05:27:23.000000', NULL, '2017-10-30 05:27:23.000000', 'admin', '00000000', 'PERSON', 'YYYYYY', '1', '2', 'IDENTIFIER,NAME BLOCK,GENDER,DOB,ADDRESS BLOCK,PHONE'),
	('10', 0, '2017-10-30 05:27:23.000000', NULL, '2017-10-30 05:27:23.000000', 'admin', '00000000', 'PERSON', 'YNNYYY', '2', '2', 'IDENTIFIER,NAME BLOCK,GENDER,DOB,ADDRESS BLOCK,PHONE'),
	('11', 0, '2017-10-30 05:27:23.000000', NULL, '2017-10-30 05:27:23.000000', 'admin', '00000000', 'PERSON', 'YNYNYY', '1', '2', 'IDENTIFIER,NAME BLOCK,GENDER,DOB,ADDRESS BLOCK,PHONE'),
	('12', 0, '2017-10-30 05:27:23.000000', NULL, '2017-10-30 05:27:23.000000', 'admin', '00000000', 'PERSON', 'YNNNYY', '2', '2', 'IDENTIFIER,NAME BLOCK,GENDER,DOB,ADDRESS BLOCK,PHONE'),
	('13', 0, '2017-10-30 05:27:23.000000', NULL, '2017-10-30 05:27:23.000000', 'admin', '00000000', 'PERSON', 'YNYYYN', '2', '2', 'IDENTIFIER,NAME BLOCK,GENDER,DOB,ADDRESS BLOCK,PHONE'),
	('14', 0, '2017-10-30 05:27:23.000000', NULL, '2017-10-30 05:27:23.000000', 'admin', '00000000', 'PERSON', 'YNYYNY', '2', '2', 'IDENTIFIER,NAME BLOCK,GENDER,DOB,ADDRESS BLOCK,PHONE'),
	('15', 0, '2017-10-30 05:27:23.000000', NULL, '2017-10-30 05:27:23.000000', 'admin', '00000000', 'PERSON', 'YNYYNN', '4', '2', 'IDENTIFIER,NAME BLOCK,GENDER,DOB,ADDRESS BLOCK,PHONE'),
	('16', 0, '2017-10-30 05:27:23.000000', NULL, '2017-10-30 05:27:23.000000', 'admin', '00000000', 'PERSON', 'NYYYYY', '1', '2', 'IDENTIFIER,NAME BLOCK,GENDER,DOB,ADDRESS BLOCK,PHONE'),
	('17', 0, '2017-10-30 05:27:23.000000', NULL, '2017-10-30 05:27:23.000000', 'admin', '00000000', 'PERSON', 'NYYYYN', '2', '2', 'IDENTIFIER,NAME BLOCK,GENDER,DOB,ADDRESS BLOCK,PHONE'),
	('18', 0, '2017-10-30 05:27:23.000000', NULL, '2017-10-30 05:27:23.000000', 'admin', '00000000', 'PERSON', 'NYYYNY', '2', '2', 'IDENTIFIER,NAME BLOCK,GENDER,DOB,ADDRESS BLOCK,PHONE'),
	('19', 0, '2017-10-30 05:27:23.000000', NULL, '2017-10-30 05:27:23.000000', 'admin', '00000000', 'PERSON', 'NYYYNN', '2', '2', 'IDENTIFIER,NAME BLOCK,GENDER,DOB,ADDRESS BLOCK,PHONE'),
	('2', 0, '2017-10-30 05:27:23.000000', NULL, '2017-10-30 05:27:23.000000', 'admin', '00000000', 'PERSON', 'YYYYYN', '1', '2', 'IDENTIFIER,NAME BLOCK,GENDER,DOB,ADDRESS BLOCK,PHONE'),
	('20', 0, '2017-10-30 05:27:23.000000', NULL, '2017-10-30 05:27:23.000000', 'admin', '00000000', 'PERSON', 'NYNNYY', '2', '2', 'IDENTIFIER,NAME BLOCK,GENDER,DOB,ADDRESS BLOCK,PHONE'),
	('21', 0, '2017-10-30 05:27:23.000000', NULL, '2017-10-30 05:27:23.000000', 'admin', '00000000', 'PERSON', 'NYNYYN', '2', '2', 'IDENTIFIER,NAME BLOCK,GENDER,DOB,ADDRESS BLOCK,PHONE'),
	('22', 0, '2017-10-30 05:27:23.000000', NULL, '2017-10-30 05:27:23.000000', 'admin', '00000000', 'PERSON', 'NYYNNY', '2', '2', 'IDENTIFIER,NAME BLOCK,GENDER,DOB,ADDRESS BLOCK,PHONE'),
	('23', 0, '2017-10-30 05:27:23.000000', NULL, '2017-10-30 05:27:23.000000', 'admin', '00000000', 'CORPORATION', 'YYYY', '1', '2', 'IDENTIFIER,NAME BLOCK,ADDRESS BLOCK,PHONE'),
	('24', 0, '2017-10-30 05:27:23.000000', NULL, '2017-10-30 05:27:23.000000', 'admin', '00000000', 'CORPORATION', 'YYNY', '1', '2', 'IDENTIFIER,NAME BLOCK,ADDRESS BLOCK,PHONE'),
	('25', 0, '2017-10-30 05:27:23.000000', NULL, '2017-10-30 05:27:23.000000', 'admin', '00000000', 'CORPORATION', 'YYYN', '1', '2', 'IDENTIFIER,NAME BLOCK,ADDRESS BLOCK,PHONE'),
	('26', 0, '2017-10-30 05:27:23.000000', NULL, '2017-10-30 05:27:23.000000', 'admin', '00000000', 'CORPORATION', 'YYNN', '2', '2', 'IDENTIFIER,NAME BLOCK,ADDRESS BLOCK,PHONE'),
	('27', 0, '2017-10-30 05:27:23.000000', NULL, '2017-10-30 05:27:23.000000', 'admin', '00000000', 'ADDRESS', 'YYYYYYYY', '1', '2', 'LINE_ONE,LINE_TWO,LINE_THREE,LINE_FOUR,STREET_NAME,CITY,COUNTRY,POSTAL_CODE'),
	('28', 0, '2017-10-30 05:27:23.000000', NULL, '2017-10-30 05:27:23.000000', 'admin', '00000000', 'ADDRESS', 'YYYYYYYN', '1', '2', 'LINE_ONE,LINE_TWO,LINE_THREE,LINE_FOUR,STREET_NAME,CITY,COUNTRY,POSTAL_CODE'),
	('29', 0, '2017-10-30 05:27:23.000000', NULL, '2017-10-30 05:27:23.000000', 'admin', '00000000', 'ADDRESS', 'YYYYYNYY', '1', '2', 'LINE_ONE,LINE_TWO,LINE_THREE,LINE_FOUR,STREET_NAME,CITY,COUNTRY,POSTAL_CODE'),
	('3', 0, '2017-10-30 05:27:23.000000', NULL, '2017-10-30 05:27:23.000000', 'admin', '00000000', 'PERSON', 'YYYYNY', '1', '2', 'IDENTIFIER,NAME BLOCK,GENDER,DOB,ADDRESS BLOCK,PHONE'),
	('30', 0, '2017-10-30 05:27:23.000000', NULL, '2017-10-30 05:27:23.000000', 'admin', '00000000', 'PERSON', 'YYYNYY', '1', '2', 'IDENTIFIER,NAME BLOCK,GENDER,DOB,ADDRESS BLOCK,PHONE'),
	('4', 0, '2017-10-30 05:27:23.000000', NULL, '2017-10-30 05:27:23.000000', 'admin', '00000000', 'PERSON', 'YYNYYN', '2', '2', 'IDENTIFIER,NAME BLOCK,GENDER,DOB,ADDRESS BLOCK,PHONE'),
	('5', 0, '2017-10-30 05:27:23.000000', NULL, '2017-10-30 05:27:23.000000', 'admin', '00000000', 'PERSON', 'YYYNYN', '1', '2', 'IDENTIFIER,NAME BLOCK,GENDER,DOB,ADDRESS BLOCK,PHONE'),
	('6', 0, '2017-10-30 05:27:23.000000', NULL, '2017-10-30 05:27:23.000000', 'admin', '00000000', 'PERSON', 'YYNYNY', '2', '2', 'IDENTIFIER,NAME BLOCK,GENDER,DOB,ADDRESS BLOCK,PHONE'),
	('7', 0, '2017-10-30 05:27:23.000000', NULL, '2017-10-30 05:27:23.000000', 'admin', '00000000', 'PERSON', 'YYYNNY', '2', '2', 'IDENTIFIER,NAME BLOCK,GENDER,DOB,ADDRESS BLOCK,PHONE'),
	('8', 0, '2017-10-30 05:27:23.000000', NULL, '2017-10-30 05:27:23.000000', 'admin', '00000000', 'PERSON', 'YYYYNN', '2', '2', 'IDENTIFIER,NAME BLOCK,GENDER,DOB,ADDRESS BLOCK,PHONE'),
	('9', 0, '2017-10-30 05:27:23.000000', NULL, '2017-10-30 05:27:23.000000', 'admin', '00000000', 'PERSON', 'YNYYYY', '1', '2', 'IDENTIFIER,NAME BLOCK,GENDER,DOB,ADDRESS BLOCK,PHONE');
/*!40000 ALTER TABLE `ref_match_score` ENABLE KEYS */;

-- Dumping structure for table yug_owner.ref_match_threshold
CREATE TABLE IF NOT EXISTS `ref_match_threshold` (
  `ID_PK` varchar(50) COLLATE utf8_bin NOT NULL,
  `VERSION` decimal(22,0) NOT NULL,
  `CREATED_TS` datetime(6) NOT NULL,
  `DELETED_TS` datetime(6) DEFAULT NULL,
  `UPDATED_TS` datetime(6) NOT NULL,
  `UPDATED_BY_USER` varchar(50) COLLATE utf8_bin NOT NULL,
  `UPDATED_BY_TXN_ID` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `ATTR_BLOCK_NAME` varchar(100) COLLATE utf8_bin NOT NULL,
  `MATCH_THRESHOLD` decimal(22,0) NOT NULL,
  `DECAY_THRESHOLD_IN_DAYS` decimal(22,0) NOT NULL,
  `DECAY_PERCENTAGE` double(11,8) NOT NULL,
  `MAX_DECAY_PERCENTAGE` decimal(22,0) NOT NULL,
  PRIMARY KEY (`ID_PK`),
  UNIQUE KEY `REF_MATCH_THRESHOLD_UKEY` (`ATTR_BLOCK_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- Dumping data for table yug_owner.ref_match_threshold: ~21 rows (approximately)
/*!40000 ALTER TABLE `ref_match_threshold` DISABLE KEYS */;
INSERT INTO `ref_match_threshold` (`ID_PK`, `VERSION`, `CREATED_TS`, `DELETED_TS`, `UPDATED_TS`, `UPDATED_BY_USER`, `UPDATED_BY_TXN_ID`, `ATTR_BLOCK_NAME`, `MATCH_THRESHOLD`, `DECAY_THRESHOLD_IN_DAYS`, `DECAY_PERCENTAGE`, `MAX_DECAY_PERCENTAGE`) VALUES
	('1', 0, '2017-10-30 17:41:06.000000', NULL, '2017-10-30 17:41:06.000000', 'admin', '00000000', 'PERSON.BLOCK.NAME', 80, 0, 0.00000000, 0),
	('10', 0, '2017-11-09 13:40:47.000000', NULL, '2017-11-09 13:40:47.000000', 'admin', '00000000', 'CORPORATION.ATTR.PHONE', 80, 1, 0.02222200, 20),
	('11', 0, '2017-11-09 14:16:03.000000', NULL, '2017-11-09 14:16:03.000000', 'admin', '00000000', 'PERSON.ATTR.NAMEONE', 80, 0, 0.00000000, 0),
	('12', 0, '2017-11-09 14:16:17.000000', NULL, '2017-11-09 14:16:17.000000', 'admin', '00000000', 'PERSON.ATTR.NAMETWO', 80, 0, 0.00000000, 0),
	('13', 0, '2017-11-09 14:16:29.000000', NULL, '2017-11-09 14:16:29.000000', 'admin', '00000000', 'PERSON.ATTR.LASTNAME', 80, 0, 0.00000000, 0),
	('14', 0, '2017-11-09 14:17:47.000000', NULL, '2017-11-09 14:17:47.000000', 'admin', '00000000', 'ADDRESS.ATTR.ADDRESS_LINE_ONE', 80, 1, 0.02222200, 20),
	('15', 0, '2017-11-09 14:17:56.000000', NULL, '2017-11-09 14:17:56.000000', 'admin', '00000000', 'ADDRESS.ATTR.ADDRESS_LINE_TWO', 80, 1, 0.02222200, 20),
	('16', 0, '2017-11-09 14:18:04.000000', NULL, '2017-11-09 14:18:04.000000', 'admin', '00000000', 'ADDRESS.ATTR.ADDRESS_LINE_THREE', 80, 1, 0.02222200, 20),
	('17', 0, '2017-11-09 14:18:14.000000', NULL, '2017-11-09 14:18:14.000000', 'admin', '00000000', 'ADDRESS.ATTR.ADDRESS_LINE_FOUR', 80, 1, 0.02222200, 20),
	('18', 0, '2017-11-09 14:18:23.000000', NULL, '2017-11-09 14:18:23.000000', 'admin', '00000000', 'ADDRESS.ATTR.STREET_NAME', 80, 1, 0.02222200, 20),
	('19', 0, '2017-11-09 14:18:33.000000', NULL, '2017-11-09 14:18:33.000000', 'admin', '00000000', 'ADDRESS.ATTR.CITY', 80, 1, 0.02222200, 20),
	('2', 0, '2017-10-30 17:41:31.000000', NULL, '2017-10-30 17:41:31.000000', 'admin', '00000000', 'PERSON.BLOCK.ADDRESS', 80, 90, 2.00000000, 20),
	('20', 0, '2017-11-09 14:18:41.000000', NULL, '2017-11-09 14:18:41.000000', 'admin', '00000000', 'ADDRESS.ATTR.COUNTRY_REFKEY', 80, 1, 0.02222200, 20),
	('21', 0, '2017-11-09 14:18:50.000000', NULL, '2017-11-09 14:18:50.000000', 'admin', '00000000', 'ADDRESS.ATTR.POSTAL_CODE', 80, 1, 0.02222200, 20),
	('3', 0, '2017-10-30 17:41:38.000000', NULL, '2017-10-30 17:41:38.000000', 'admin', '00000000', 'PERSON.ATTR.GENDER', 80, 90, 2.00000000, 0),
	('4', 0, '2017-10-30 17:41:43.000000', NULL, '2017-10-30 17:41:43.000000', 'admin', '00000000', 'PERSON.BLOCK.IDENTIFIER_KYC', 80, 0, 0.00000000, 0),
	('5', 0, '2017-10-30 17:41:50.000000', NULL, '2017-10-30 17:41:50.000000', 'admin', '00000000', 'PERSON.ATTR.DOB', 80, 0, 0.00000000, 0),
	('6', 0, '2017-10-30 17:41:56.000000', NULL, '2017-10-30 17:41:56.000000', 'admin', '00000000', 'PERSON.BLOCK.PHONE', 80, 90, 2.00000000, 20),
	('7', 0, '2017-11-09 13:40:37.000000', NULL, '2017-11-09 13:40:37.000000', 'admin', '00000000', 'CORPORATION.BLOCK.IDENTIFIER_KYC', 80, 0, 0.00000000, 0),
	('8', 0, '2017-11-09 13:40:12.000000', NULL, '2017-11-09 13:40:12.000000', 'admin', '00000000', 'CORPORATION.ATTR.NAME', 80, 1, 0.02222200, 20),
	('9', 0, '2017-11-09 13:40:26.000000', NULL, '2017-11-09 13:40:26.000000', 'admin', '00000000', 'CORPORATION.BLOCK.ADDRESS', 80, 1, 0.02222200, 20);
/*!40000 ALTER TABLE `ref_match_threshold` ENABLE KEYS */;

-- Dumping structure for table yug_owner.ref_merge_reason
CREATE TABLE IF NOT EXISTS `ref_merge_reason` (
  `ID_PK` varchar(50) COLLATE utf8_bin NOT NULL,
  `VERSION` decimal(22,0) NOT NULL,
  `CREATED_TS` datetime(6) NOT NULL,
  `DELETED_TS` datetime(6) DEFAULT NULL,
  `UPDATED_TS` datetime(6) NOT NULL,
  `UPDATED_BY_USER` varchar(50) COLLATE utf8_bin NOT NULL,
  `UPDATED_BY_TXN_ID` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `CONFIG_LANGUAGE_CODE_KEY` varchar(50) COLLATE utf8_bin NOT NULL,
  `KEY` varchar(50) COLLATE utf8_bin NOT NULL,
  `VALUE` varchar(50) COLLATE utf8_bin NOT NULL,
  `DESCRIPTION` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_PK`),
  UNIQUE KEY `REF_MERGE_REASON_UKEY` (`KEY`,`CONFIG_LANGUAGE_CODE_KEY`),
  KEY `INDX_REF_MERGE_REASON_1` (`KEY`),
  KEY `INDX_REF_MERGE_REASON_2` (`VALUE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- Dumping data for table yug_owner.ref_merge_reason: ~2 rows (approximately)
/*!40000 ALTER TABLE `ref_merge_reason` DISABLE KEYS */;
INSERT INTO `ref_merge_reason` (`ID_PK`, `VERSION`, `CREATED_TS`, `DELETED_TS`, `UPDATED_TS`, `UPDATED_BY_USER`, `UPDATED_BY_TXN_ID`, `CONFIG_LANGUAGE_CODE_KEY`, `KEY`, `VALUE`, `DESCRIPTION`) VALUES
	('1', 0, '2017-10-30 17:20:50.000000', NULL, '2017-10-30 17:20:50.000000', 'admin', '00000000', '1', '1', 'AUTO_MERGED', 'AUTO_MERGED'),
	('2', 1, '2017-10-30 17:21:08.000000', NULL, '2017-10-30 17:21:18.000000', 'admin', '00000000', '1', '2', 'MERGED_AFTER_REVIEW', 'MERGED_AFTER_REVIEW');
/*!40000 ALTER TABLE `ref_merge_reason` ENABLE KEYS */;

-- Dumping structure for table yug_owner.ref_personname_type
CREATE TABLE IF NOT EXISTS `ref_personname_type` (
  `ID_PK` varchar(50) COLLATE utf8_bin NOT NULL,
  `VERSION` decimal(22,0) NOT NULL,
  `CREATED_TS` datetime(6) NOT NULL,
  `DELETED_TS` datetime(6) DEFAULT NULL,
  `UPDATED_TS` datetime(6) NOT NULL,
  `UPDATED_BY_USER` varchar(50) COLLATE utf8_bin NOT NULL,
  `UPDATED_BY_TXN_ID` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `CONFIG_LANGUAGE_CODE_KEY` varchar(50) COLLATE utf8_bin NOT NULL,
  `KEY` varchar(50) COLLATE utf8_bin NOT NULL,
  `VALUE` varchar(50) COLLATE utf8_bin NOT NULL,
  `DESCRIPTION` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_PK`),
  UNIQUE KEY `REF_PERSONNAME_TYPE_UNIQUEKEY` (`KEY`,`CONFIG_LANGUAGE_CODE_KEY`),
  KEY `INDX_REF_PERSONNAME_TYPE_1` (`KEY`),
  KEY `INDX_REF_PERSONNAME_TYPE_2` (`VALUE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- Dumping data for table yug_owner.ref_personname_type: ~5 rows (approximately)
/*!40000 ALTER TABLE `ref_personname_type` DISABLE KEYS */;
INSERT INTO `ref_personname_type` (`ID_PK`, `VERSION`, `CREATED_TS`, `DELETED_TS`, `UPDATED_TS`, `UPDATED_BY_USER`, `UPDATED_BY_TXN_ID`, `CONFIG_LANGUAGE_CODE_KEY`, `KEY`, `VALUE`, `DESCRIPTION`) VALUES
	('1', 0, '2017-07-04 21:55:37.000000', NULL, '2017-07-04 21:55:37.000000', 'admin', '000000', '1', '0', 'UNSPECIFIED', NULL),
	('2', 0, '2017-07-04 21:55:37.000000', NULL, '2017-07-04 21:55:37.000000', 'admin', '000000', '1', '1', 'REGISTERED NAME', NULL),
	('3', 0, '2017-07-04 21:55:37.000000', NULL, '2017-07-04 21:55:37.000000', 'admin', '000000', '1', '2', 'GIVEN PERSONAL NAME', NULL),
	('4', 0, '2017-07-04 21:55:37.000000', NULL, '2017-07-04 21:55:37.000000', 'admin', '000000', '1', '3', 'MAIDEN NAME', NULL),
	('5', 0, '2017-07-04 21:55:37.000000', NULL, '2017-07-04 21:55:37.000000', 'admin', '000000', '1', '4', 'FORMERLY KNOWN AS', NULL);
/*!40000 ALTER TABLE `ref_personname_type` ENABLE KEYS */;

-- Dumping structure for table yug_owner.ref_person_type
CREATE TABLE IF NOT EXISTS `ref_person_type` (
  `ID_PK` varchar(50) COLLATE utf8_bin NOT NULL,
  `VERSION` decimal(22,0) NOT NULL,
  `CREATED_TS` datetime(6) NOT NULL,
  `DELETED_TS` datetime(6) DEFAULT NULL,
  `UPDATED_TS` datetime(6) NOT NULL,
  `UPDATED_BY_USER` varchar(50) COLLATE utf8_bin NOT NULL,
  `UPDATED_BY_TXN_ID` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `CONFIG_LANGUAGE_CODE_KEY` varchar(50) COLLATE utf8_bin NOT NULL,
  `KEY` varchar(50) COLLATE utf8_bin NOT NULL,
  `VALUE` varchar(50) COLLATE utf8_bin NOT NULL,
  `DESCRIPTION` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_PK`),
  UNIQUE KEY `REF_PERSON_TYPE_UNIQUEKEY` (`KEY`,`CONFIG_LANGUAGE_CODE_KEY`),
  KEY `INDX_REF_PERSON_TYPE_1` (`KEY`),
  KEY `INDX_REF_PERSON_TYPE_2` (`VALUE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- Dumping data for table yug_owner.ref_person_type: ~4 rows (approximately)
/*!40000 ALTER TABLE `ref_person_type` DISABLE KEYS */;
INSERT INTO `ref_person_type` (`ID_PK`, `VERSION`, `CREATED_TS`, `DELETED_TS`, `UPDATED_TS`, `UPDATED_BY_USER`, `UPDATED_BY_TXN_ID`, `CONFIG_LANGUAGE_CODE_KEY`, `KEY`, `VALUE`, `DESCRIPTION`) VALUES
	('1', 0, '2017-07-04 21:52:24.000000', NULL, '2017-07-04 21:52:24.000000', 'admin', '000000', '1', '1', 'CUSTOMER', NULL),
	('2', 0, '2017-07-04 21:52:24.000000', NULL, '2017-07-04 21:52:24.000000', 'admin', '000000', '1', '2', 'EMPLOYEE', NULL),
	('3', 0, '2017-07-04 21:52:24.000000', NULL, '2017-07-04 21:52:24.000000', 'admin', '000000', '1', '3', 'MANAGER', NULL),
	('4', 0, '2017-07-04 21:52:25.000000', NULL, '2017-07-04 21:52:25.000000', 'admin', '000000', '1', '4', 'POINT OF CONTACT', NULL);
/*!40000 ALTER TABLE `ref_person_type` ENABLE KEYS */;

-- Dumping structure for table yug_owner.ref_phone_subtype
CREATE TABLE IF NOT EXISTS `ref_phone_subtype` (
  `ID_PK` varchar(50) COLLATE utf8_bin NOT NULL,
  `VERSION` decimal(22,0) NOT NULL,
  `CREATED_TS` datetime(6) NOT NULL,
  `DELETED_TS` datetime(6) DEFAULT NULL,
  `UPDATED_TS` datetime(6) NOT NULL,
  `UPDATED_BY_USER` varchar(50) COLLATE utf8_bin NOT NULL,
  `UPDATED_BY_TXN_ID` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `CONFIG_LANGUAGE_CODE_KEY` varchar(50) COLLATE utf8_bin NOT NULL,
  `KEY` varchar(50) COLLATE utf8_bin NOT NULL,
  `VALUE` varchar(50) COLLATE utf8_bin NOT NULL,
  `DESCRIPTION` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_PK`),
  UNIQUE KEY `REF_PHONE_SUBTYPE_UNIQUEKEY` (`KEY`,`CONFIG_LANGUAGE_CODE_KEY`),
  KEY `INDX_REF_PHONE_SUBTYPE_1` (`KEY`),
  KEY `INDX_REF_PHONE_SUBTYPE_2` (`VALUE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- Dumping data for table yug_owner.ref_phone_subtype: ~9 rows (approximately)
/*!40000 ALTER TABLE `ref_phone_subtype` DISABLE KEYS */;
INSERT INTO `ref_phone_subtype` (`ID_PK`, `VERSION`, `CREATED_TS`, `DELETED_TS`, `UPDATED_TS`, `UPDATED_BY_USER`, `UPDATED_BY_TXN_ID`, `CONFIG_LANGUAGE_CODE_KEY`, `KEY`, `VALUE`, `DESCRIPTION`) VALUES
	('1', 0, '2017-07-04 21:59:52.000000', NULL, '2017-07-04 21:59:52.000000', 'admin', '000000', '1', '0', 'UNSPECIFIED', NULL),
	('2', 0, '2017-07-04 21:59:52.000000', NULL, '2017-07-04 21:59:52.000000', 'admin', '000000', '1', '1', 'PERSONAL MOBILE', NULL),
	('3', 0, '2017-07-04 21:59:52.000000', NULL, '2017-07-04 21:59:52.000000', 'admin', '000000', '1', '2', 'OFFICE PHONE', NULL),
	('4', 0, '2017-07-04 21:59:52.000000', NULL, '2017-07-04 21:59:52.000000', 'admin', '000000', '1', '3', 'HOME PHONE', NULL),
	('5', 0, '2017-07-04 21:59:52.000000', NULL, '2017-07-04 21:59:52.000000', 'admin', '000000', '1', '4', 'PERSONAL EMAIL', NULL),
	('6', 0, '2017-07-04 21:59:52.000000', NULL, '2017-07-04 21:59:52.000000', 'admin', '000000', '1', '5', 'ALTERNATE PERSONAL EMAIL', NULL),
	('7', 0, '2017-07-04 21:59:52.000000', NULL, '2017-07-04 21:59:52.000000', 'admin', '000000', '1', '6', 'OFFICE EMAIL', NULL),
	('8', 0, '2017-07-04 21:59:52.000000', NULL, '2017-07-04 21:59:52.000000', 'admin', '000000', '1', '7', 'HOME FAX', NULL),
	('9', 0, '2017-07-04 21:59:52.000000', NULL, '2017-07-04 21:59:52.000000', 'admin', '000000', '1', '8', 'OFFICE FAX', NULL);
/*!40000 ALTER TABLE `ref_phone_subtype` ENABLE KEYS */;

-- Dumping structure for table yug_owner.ref_phone_type
CREATE TABLE IF NOT EXISTS `ref_phone_type` (
  `ID_PK` varchar(50) COLLATE utf8_bin NOT NULL,
  `VERSION` decimal(22,0) NOT NULL,
  `CREATED_TS` datetime(6) NOT NULL,
  `DELETED_TS` datetime(6) DEFAULT NULL,
  `UPDATED_TS` datetime(6) NOT NULL,
  `UPDATED_BY_USER` varchar(50) COLLATE utf8_bin NOT NULL,
  `UPDATED_BY_TXN_ID` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `CONFIG_LANGUAGE_CODE_KEY` varchar(50) COLLATE utf8_bin NOT NULL,
  `KEY` varchar(50) COLLATE utf8_bin NOT NULL,
  `VALUE` varchar(50) COLLATE utf8_bin NOT NULL,
  `DESCRIPTION` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_PK`),
  UNIQUE KEY `REF_PHONE_TYPE_UNIQUEKEY` (`KEY`,`CONFIG_LANGUAGE_CODE_KEY`),
  KEY `INDX_REF_PHONE_TYPE_1` (`KEY`),
  KEY `INDX_REF_PHONE_TYPE_2` (`VALUE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- Dumping data for table yug_owner.ref_phone_type: ~4 rows (approximately)
/*!40000 ALTER TABLE `ref_phone_type` DISABLE KEYS */;
INSERT INTO `ref_phone_type` (`ID_PK`, `VERSION`, `CREATED_TS`, `DELETED_TS`, `UPDATED_TS`, `UPDATED_BY_USER`, `UPDATED_BY_TXN_ID`, `CONFIG_LANGUAGE_CODE_KEY`, `KEY`, `VALUE`, `DESCRIPTION`) VALUES
	('1', 0, '2017-07-04 22:01:52.000000', NULL, '2017-07-04 22:01:52.000000', 'admin', '000000', '1', '1', 'MOBILE', NULL),
	('2', 0, '2017-07-04 22:01:52.000000', NULL, '2017-07-04 22:01:52.000000', 'admin', '000000', '1', '2', 'PHONE', NULL),
	('3', 0, '2017-07-04 22:01:52.000000', NULL, '2017-07-04 22:01:52.000000', 'admin', '000000', '1', '3', 'EMAIL', NULL),
	('4', 0, '2017-07-04 22:01:52.000000', NULL, '2017-07-04 22:01:52.000000', 'admin', '000000', '1', '4', 'FAX', NULL);
/*!40000 ALTER TABLE `ref_phone_type` ENABLE KEYS */;

-- Dumping structure for table yug_owner.ref_preference_type
CREATE TABLE IF NOT EXISTS `ref_preference_type` (
  `ID_PK` varchar(50) COLLATE utf8_bin NOT NULL,
  `VERSION` decimal(22,0) NOT NULL,
  `CREATED_TS` datetime(6) NOT NULL,
  `DELETED_TS` datetime(6) DEFAULT NULL,
  `UPDATED_TS` datetime(6) NOT NULL,
  `UPDATED_BY_USER` varchar(50) COLLATE utf8_bin NOT NULL,
  `UPDATED_BY_TXN_ID` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `CONFIG_LANGUAGE_CODE_KEY` varchar(50) COLLATE utf8_bin NOT NULL,
  `KEY` varchar(50) COLLATE utf8_bin NOT NULL,
  `VALUE` varchar(50) COLLATE utf8_bin NOT NULL,
  `DESCRIPTION` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_PK`),
  UNIQUE KEY `REF_PREFERENCE_TYPE_UNIQUEKEY` (`KEY`,`CONFIG_LANGUAGE_CODE_KEY`),
  KEY `INDX_REF_PREFERENCE_TYPE_1` (`KEY`),
  KEY `INDX_REF_PREFERENCE_TYPE_2` (`VALUE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- Dumping data for table yug_owner.ref_preference_type: ~5 rows (approximately)
/*!40000 ALTER TABLE `ref_preference_type` DISABLE KEYS */;
INSERT INTO `ref_preference_type` (`ID_PK`, `VERSION`, `CREATED_TS`, `DELETED_TS`, `UPDATED_TS`, `UPDATED_BY_USER`, `UPDATED_BY_TXN_ID`, `CONFIG_LANGUAGE_CODE_KEY`, `KEY`, `VALUE`, `DESCRIPTION`) VALUES
	('1', 0, '2017-07-05 11:16:32.000000', NULL, '2017-07-05 11:16:32.000000', 'admin', '000000', '1', '1', 'Do Not Call Me', NULL),
	('2', 0, '2017-07-05 11:16:32.000000', NULL, '2017-07-05 11:16:32.000000', 'admin', '000000', '1', '2', 'Do Not Mail Me', NULL),
	('3', 0, '2017-07-05 11:16:32.000000', NULL, '2017-07-05 11:16:32.000000', 'admin', '000000', '1', '3', 'Do Not SMS Me', NULL),
	('4', 0, '2017-07-05 11:16:32.000000', NULL, '2017-07-05 11:16:32.000000', 'admin', '000000', '1', '4', 'Do Not Courrier Me', NULL),
	('5', 0, '2017-07-05 11:16:32.000000', NULL, '2017-07-05 11:16:32.000000', 'admin', '000000', '1', '5', 'Do Not Disturb Registered', 'LE is registered for Do Not Disturb registry');
/*!40000 ALTER TABLE `ref_preference_type` ENABLE KEYS */;

-- Dumping structure for table yug_owner.ref_prefix_name
CREATE TABLE IF NOT EXISTS `ref_prefix_name` (
  `ID_PK` varchar(50) COLLATE utf8_bin NOT NULL,
  `VERSION` decimal(22,0) NOT NULL,
  `CREATED_TS` datetime(6) NOT NULL,
  `DELETED_TS` datetime(6) DEFAULT NULL,
  `UPDATED_TS` datetime(6) NOT NULL,
  `UPDATED_BY_USER` varchar(50) COLLATE utf8_bin NOT NULL,
  `UPDATED_BY_TXN_ID` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `CONFIG_LANGUAGE_CODE_KEY` varchar(50) COLLATE utf8_bin NOT NULL,
  `KEY` varchar(50) COLLATE utf8_bin NOT NULL,
  `VALUE` varchar(50) COLLATE utf8_bin NOT NULL,
  `DESCRIPTION` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_PK`),
  UNIQUE KEY `REF_PREFIX_NAME_UNIQUEKEY` (`KEY`,`CONFIG_LANGUAGE_CODE_KEY`),
  KEY `INDX_REF_PREFIX_NAME_1` (`KEY`),
  KEY `INDX_REF_PREFIX_NAME_2` (`VALUE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- Dumping data for table yug_owner.ref_prefix_name: ~11 rows (approximately)
/*!40000 ALTER TABLE `ref_prefix_name` DISABLE KEYS */;
INSERT INTO `ref_prefix_name` (`ID_PK`, `VERSION`, `CREATED_TS`, `DELETED_TS`, `UPDATED_TS`, `UPDATED_BY_USER`, `UPDATED_BY_TXN_ID`, `CONFIG_LANGUAGE_CODE_KEY`, `KEY`, `VALUE`, `DESCRIPTION`) VALUES
	('1', 0, '2017-07-05 11:13:11.000000', NULL, '2017-07-05 11:13:11.000000', 'admin', '000000', '1', '1', 'Ms', 'Ms'),
	('10', 0, '2017-07-05 11:13:11.000000', NULL, '2017-07-05 11:13:11.000000', 'admin', '000000', '1', '10', 'Gov', 'Governer'),
	('11', 0, '2017-07-05 11:13:11.000000', NULL, '2017-07-05 11:13:11.000000', 'admin', '000000', '1', '11', 'Ofc', 'Officer'),
	('2', 0, '2017-07-05 11:13:11.000000', NULL, '2017-07-05 11:13:11.000000', 'admin', '000000', '1', '2', 'Miss', 'Miss'),
	('3', 0, '2017-07-05 11:13:11.000000', NULL, '2017-07-05 11:13:11.000000', 'admin', '000000', '1', '3', 'Mrs', 'Mistress'),
	('4', 0, '2017-07-05 11:13:11.000000', NULL, '2017-07-05 11:13:11.000000', 'admin', '000000', '1', '4', 'Mr', 'Mister'),
	('5', 0, '2017-07-05 11:13:11.000000', NULL, '2017-07-05 11:13:11.000000', 'admin', '000000', '1', '5', 'Master', 'Master'),
	('6', 0, '2017-07-05 11:13:11.000000', NULL, '2017-07-05 11:13:11.000000', 'admin', '000000', '1', '6', 'Dr', 'Doctor / Doctorate'),
	('7', 0, '2017-07-05 11:13:11.000000', NULL, '2017-07-05 11:13:11.000000', 'admin', '000000', '1', '7', 'Sir', 'Sir'),
	('8', 0, '2017-07-05 11:13:11.000000', NULL, '2017-07-05 11:13:11.000000', 'admin', '000000', '1', '8', 'Hon', 'Honorable'),
	('9', 0, '2017-07-05 11:13:11.000000', NULL, '2017-07-05 11:13:11.000000', 'admin', '000000', '1', '9', 'Prof', 'Professor');
/*!40000 ALTER TABLE `ref_prefix_name` ENABLE KEYS */;

-- Dumping structure for table yug_owner.ref_property_le_reltype
CREATE TABLE IF NOT EXISTS `ref_property_le_reltype` (
  `ID_PK` varchar(50) COLLATE utf8_bin NOT NULL,
  `VERSION` decimal(22,0) NOT NULL,
  `CREATED_TS` datetime(6) NOT NULL,
  `DELETED_TS` datetime(6) DEFAULT NULL,
  `UPDATED_TS` datetime(6) NOT NULL,
  `UPDATED_BY_USER` varchar(50) COLLATE utf8_bin NOT NULL,
  `UPDATED_BY_TXN_ID` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `CONFIG_LANGUAGE_CODE_KEY` varchar(50) COLLATE utf8_bin NOT NULL,
  `KEY` varchar(50) COLLATE utf8_bin NOT NULL,
  `VALUE` varchar(50) COLLATE utf8_bin NOT NULL,
  `DESCRIPTION` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  UNIQUE KEY `REFPROLERELTYPE_UNIQUEKEY` (`KEY`,`CONFIG_LANGUAGE_CODE_KEY`),
  UNIQUE KEY `REFPROPLERELTYPE_PK` (`ID_PK`),
  KEY `INDX_REF_PROPERTY_LE_RELTYPE_1` (`KEY`),
  KEY `INDX_REF_PROPERTY_LE_RELTYPE_2` (`VALUE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- Dumping data for table yug_owner.ref_property_le_reltype: ~4 rows (approximately)
/*!40000 ALTER TABLE `ref_property_le_reltype` DISABLE KEYS */;
INSERT INTO `ref_property_le_reltype` (`ID_PK`, `VERSION`, `CREATED_TS`, `DELETED_TS`, `UPDATED_TS`, `UPDATED_BY_USER`, `UPDATED_BY_TXN_ID`, `CONFIG_LANGUAGE_CODE_KEY`, `KEY`, `VALUE`, `DESCRIPTION`) VALUES
	('1', 0, '2017-07-05 11:20:22.000000', NULL, '2017-07-05 11:20:22.000000', 'admin', '000000', '1', '1', 'OWNER', NULL),
	('2', 0, '2017-07-05 11:20:22.000000', NULL, '2017-07-05 11:20:22.000000', 'admin', '000000', '1', '2', 'JOINT OWNER', NULL),
	('3', 0, '2017-07-05 11:20:22.000000', NULL, '2017-07-05 11:20:22.000000', 'admin', '000000', '1', '3', 'TENANT', NULL),
	('4', 0, '2017-07-05 11:20:22.000000', NULL, '2017-07-05 11:20:22.000000', 'admin', '000000', '1', '4', 'LEASE OWNER', NULL);
/*!40000 ALTER TABLE `ref_property_le_reltype` ENABLE KEYS */;

-- Dumping structure for table yug_owner.ref_relationship_status
CREATE TABLE IF NOT EXISTS `ref_relationship_status` (
  `ID_PK` varchar(50) COLLATE utf8_bin NOT NULL,
  `VERSION` decimal(22,0) NOT NULL,
  `CREATED_TS` datetime(6) NOT NULL,
  `DELETED_TS` datetime(6) DEFAULT NULL,
  `UPDATED_TS` datetime(6) NOT NULL,
  `UPDATED_BY_USER` varchar(50) COLLATE utf8_bin NOT NULL,
  `UPDATED_BY_TXN_ID` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `CONFIG_LANGUAGE_CODE_KEY` varchar(50) COLLATE utf8_bin NOT NULL,
  `KEY` varchar(50) COLLATE utf8_bin NOT NULL,
  `VALUE` varchar(50) COLLATE utf8_bin NOT NULL,
  `DESCRIPTION` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_PK`),
  UNIQUE KEY `REFRELATIONSHIPST_UNIQUEKEY` (`KEY`,`CONFIG_LANGUAGE_CODE_KEY`),
  KEY `INDX_REF_RELATIONSHIP_STATUS_1` (`KEY`),
  KEY `INDX_REF_RELATIONSHIP_STATUS_2` (`VALUE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- Dumping data for table yug_owner.ref_relationship_status: ~4 rows (approximately)
/*!40000 ALTER TABLE `ref_relationship_status` DISABLE KEYS */;
INSERT INTO `ref_relationship_status` (`ID_PK`, `VERSION`, `CREATED_TS`, `DELETED_TS`, `UPDATED_TS`, `UPDATED_BY_USER`, `UPDATED_BY_TXN_ID`, `CONFIG_LANGUAGE_CODE_KEY`, `KEY`, `VALUE`, `DESCRIPTION`) VALUES
	('1', 0, '2017-07-05 11:22:35.000000', NULL, '2017-07-05 11:22:35.000000', 'admin', '000000', '1', '1', 'ACTIVE', NULL),
	('2', 0, '2017-07-05 11:22:35.000000', NULL, '2017-07-05 11:22:35.000000', 'admin', '000000', '1', '2', 'INACTIVE', NULL),
	('3', 0, '2017-07-05 11:22:35.000000', NULL, '2017-07-05 11:22:35.000000', 'admin', '000000', '1', '3', 'SUSPENDED', NULL),
	('4', 0, '2017-07-05 11:22:35.000000', NULL, '2017-07-05 11:22:35.000000', 'admin', '000000', '1', '4', 'TERMINATED', NULL);
/*!40000 ALTER TABLE `ref_relationship_status` ENABLE KEYS */;

-- Dumping structure for table yug_owner.ref_source_system
CREATE TABLE IF NOT EXISTS `ref_source_system` (
  `ID_PK` varchar(50) COLLATE utf8_bin NOT NULL,
  `VERSION` decimal(22,0) NOT NULL,
  `CREATED_TS` datetime(6) NOT NULL,
  `DELETED_TS` datetime(6) DEFAULT NULL,
  `UPDATED_TS` datetime(6) NOT NULL,
  `UPDATED_BY_USER` varchar(50) COLLATE utf8_bin NOT NULL,
  `UPDATED_BY_TXN_ID` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `CONFIG_LANGUAGE_CODE_KEY` varchar(50) COLLATE utf8_bin NOT NULL,
  `KEY` varchar(50) COLLATE utf8_bin NOT NULL,
  `VALUE` varchar(50) COLLATE utf8_bin NOT NULL,
  `DESCRIPTION` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_PK`),
  UNIQUE KEY `REF_SOURCE_SYSTEM_UNIQUEKEY` (`KEY`,`CONFIG_LANGUAGE_CODE_KEY`),
  KEY `INDX_REF_SOURCE_SYSTEM_1` (`KEY`),
  KEY `INDX_REF_SOURCE_SYSTEM_2` (`VALUE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- Dumping data for table yug_owner.ref_source_system: ~4 rows (approximately)
/*!40000 ALTER TABLE `ref_source_system` DISABLE KEYS */;
INSERT INTO `ref_source_system` (`ID_PK`, `VERSION`, `CREATED_TS`, `DELETED_TS`, `UPDATED_TS`, `UPDATED_BY_USER`, `UPDATED_BY_TXN_ID`, `CONFIG_LANGUAGE_CODE_KEY`, `KEY`, `VALUE`, `DESCRIPTION`) VALUES
	('1', 0, '2017-07-05 11:23:38.000000', NULL, '2017-07-05 11:23:38.000000', 'admin', '000000', '1', '1', 'LE SOURCE SYSTEM 1', NULL),
	('2', 0, '2017-07-05 11:23:38.000000', NULL, '2017-07-05 11:23:38.000000', 'admin', '000000', '1', '2', 'LE SOURCE SYSTEM 2', NULL),
	('3', 0, '2017-07-05 11:23:38.000000', NULL, '2017-07-05 11:23:38.000000', 'admin', '000000', '1', '3', 'ACCOUNT SOURCE SYSTEM 1', NULL),
	('4', 0, '2017-07-05 11:23:38.000000', NULL, '2017-07-05 11:23:38.000000', 'admin', '000000', '1', '4', 'ACCOUNT SOURCE SYSTEM 2', NULL);
/*!40000 ALTER TABLE `ref_source_system` ENABLE KEYS */;

-- Dumping structure for table yug_owner.ref_state_province
CREATE TABLE IF NOT EXISTS `ref_state_province` (
  `ID_PK` varchar(50) COLLATE utf8_bin NOT NULL,
  `VERSION` decimal(22,0) NOT NULL,
  `CREATED_TS` datetime(6) NOT NULL,
  `DELETED_TS` datetime(6) DEFAULT NULL,
  `UPDATED_TS` datetime(6) NOT NULL,
  `UPDATED_BY_USER` varchar(50) COLLATE utf8_bin NOT NULL,
  `UPDATED_BY_TXN_ID` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `CONFIG_LANGUAGE_CODE_KEY` varchar(50) COLLATE utf8_bin NOT NULL,
  `KEY` varchar(50) COLLATE utf8_bin NOT NULL,
  `VALUE` varchar(50) COLLATE utf8_bin NOT NULL,
  `DESCRIPTION` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `COUNTRY_ISO_REFKEY` varchar(50) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`ID_PK`),
  UNIQUE KEY `REF_STATE_PROVINCE_UNIQUEKEY` (`KEY`,`CONFIG_LANGUAGE_CODE_KEY`),
  KEY `INDX_REF_STATE_PROVINCE_1` (`KEY`),
  KEY `INDX_REF_STATE_PROVINCE_2` (`VALUE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- Dumping data for table yug_owner.ref_state_province: ~65 rows (approximately)
/*!40000 ALTER TABLE `ref_state_province` DISABLE KEYS */;
INSERT INTO `ref_state_province` (`ID_PK`, `VERSION`, `CREATED_TS`, `DELETED_TS`, `UPDATED_TS`, `UPDATED_BY_USER`, `UPDATED_BY_TXN_ID`, `CONFIG_LANGUAGE_CODE_KEY`, `KEY`, `VALUE`, `DESCRIPTION`, `COUNTRY_ISO_REFKEY`) VALUES
	('1', 0, '2018-04-16 18:37:50.000000', NULL, '2018-04-16 18:37:50.000000', 'admin', '000000', '1', 'AL', 'ALABAMA', 'Alabama', '840'),
	('10', 0, '2018-04-16 18:37:50.000000', NULL, '2018-04-16 18:37:50.000000', 'admin', '000000', '1', 'NE', 'NEBRASKA', 'Nebraska', '840'),
	('11', 0, '2018-04-16 18:37:50.000000', NULL, '2018-04-16 18:37:50.000000', 'admin', '000000', '1', 'NJ', 'NEW JERSEY', 'New Jersey', '840'),
	('12', 0, '2018-04-16 18:37:50.000000', NULL, '2018-04-16 18:37:50.000000', 'admin', '000000', '1', 'NC', 'NORTH CAROLINA', 'North Carolina', '840'),
	('13', 0, '2018-04-16 18:37:50.000000', NULL, '2018-04-16 18:37:50.000000', 'admin', '000000', '1', 'OK', 'OKLAHOMA', 'Oklahoma', '840'),
	('14', 0, '2018-04-16 18:37:50.000000', NULL, '2018-04-16 18:37:50.000000', 'admin', '000000', '1', 'RI', 'RHODE ISLAND', 'Rhode Island', '840'),
	('15', 0, '2018-04-16 18:37:50.000000', NULL, '2018-04-16 18:37:50.000000', 'admin', '000000', '1', 'TN', 'TENNESSEE', 'Tennessee', '840'),
	('16', 0, '2018-04-16 18:37:50.000000', NULL, '2018-04-16 18:37:50.000000', 'admin', '000000', '1', 'VT', 'VERMONT', 'Vermont', '840'),
	('17', 0, '2018-04-16 18:37:51.000000', NULL, '2018-04-16 18:37:51.000000', 'admin', '000000', '1', 'WV', 'WEST VIRGINIA', 'West Virginia', '840'),
	('18', 0, '2018-04-16 18:37:51.000000', NULL, '2018-04-16 18:37:51.000000', 'admin', '000000', '1', 'AK', 'ALASKA', 'Alaska', '840'),
	('19', 0, '2018-04-16 18:37:51.000000', NULL, '2018-04-16 18:37:51.000000', 'admin', '000000', '1', 'CA', 'CALIFORNIA', 'California', '840'),
	('2', 0, '2018-04-16 18:37:50.000000', NULL, '2018-04-16 18:37:50.000000', 'admin', '000000', '1', 'AR', 'ARKANSAS', 'Arkansas', '840'),
	('20', 0, '2018-04-16 18:37:51.000000', NULL, '2018-04-16 18:37:51.000000', 'admin', '000000', '1', 'DC', 'DIST. COLUMBIA', 'Dist. Columbia', '840'),
	('21', 0, '2018-04-16 18:37:51.000000', NULL, '2018-04-16 18:37:51.000000', 'admin', '000000', '1', 'GA', 'GEORGIA', 'Georgia', '840'),
	('22', 0, '2018-04-16 18:37:51.000000', NULL, '2018-04-16 18:37:51.000000', 'admin', '000000', '1', 'IL', 'ILLINOIS', 'Illinois', '840'),
	('23', 0, '2018-04-16 18:37:51.000000', NULL, '2018-04-16 18:37:51.000000', 'admin', '000000', '1', 'KS', 'KANSAS', 'Kansas', '840'),
	('24', 0, '2018-04-16 18:37:51.000000', NULL, '2018-04-16 18:37:51.000000', 'admin', '000000', '1', 'ME', 'MAINE', 'Maine', '840'),
	('25', 0, '2018-04-16 18:37:51.000000', NULL, '2018-04-16 18:37:51.000000', 'admin', '000000', '1', 'MI', 'MICHIGAN', 'Michigan', '840'),
	('26', 0, '2018-04-16 18:37:51.000000', NULL, '2018-04-16 18:37:51.000000', 'admin', '000000', '1', 'MO', 'MISSOURI', 'Missouri', '840'),
	('27', 0, '2018-04-16 18:37:51.000000', NULL, '2018-04-16 18:37:51.000000', 'admin', '000000', '1', 'NV', 'NEVADA', 'Nevada', '840'),
	('28', 0, '2018-04-16 18:37:51.000000', NULL, '2018-04-16 18:37:51.000000', 'admin', '000000', '1', 'NM', 'NEW MEXICO', 'New Mexico', '840'),
	('29', 0, '2018-04-16 18:37:51.000000', NULL, '2018-04-16 18:37:51.000000', 'admin', '000000', '1', 'ND', 'NORTH DAKOTA', 'North Dakota', '840'),
	('3', 0, '2018-04-16 18:37:50.000000', NULL, '2018-04-16 18:37:50.000000', 'admin', '000000', '1', 'CT', 'CONNECTICUT', 'Connecticut', '840'),
	('30', 0, '2018-04-16 18:37:51.000000', NULL, '2018-04-16 18:37:51.000000', 'admin', '000000', '1', 'OR', 'OREGON', 'Oregon', '840'),
	('31', 0, '2018-04-16 18:37:51.000000', NULL, '2018-04-16 18:37:51.000000', 'admin', '000000', '1', 'SC', 'SOUTH CAROLINA', 'South Carolina', '840'),
	('32', 0, '2018-04-16 18:37:51.000000', NULL, '2018-04-16 18:37:51.000000', 'admin', '000000', '1', 'TX', 'TEXAS', 'Texas', '840'),
	('33', 0, '2018-04-16 18:37:51.000000', NULL, '2018-04-16 18:37:51.000000', 'admin', '000000', '1', 'VA', 'VIRGINIA', 'Virginia', '840'),
	('34', 0, '2018-04-16 18:37:51.000000', NULL, '2018-04-16 18:37:51.000000', 'admin', '000000', '1', 'WI', 'WISCONSIN', 'Wisconsin', '840'),
	('35', 0, '2018-04-16 18:37:51.000000', NULL, '2018-04-16 18:37:51.000000', 'admin', '000000', '1', 'AZ', 'ARIZONA', 'Arizona', '840'),
	('36', 0, '2018-04-16 18:37:51.000000', NULL, '2018-04-16 18:37:51.000000', 'admin', '000000', '1', 'CO', 'COLORADO', 'Colorado', '840'),
	('37', 0, '2018-04-16 18:37:51.000000', NULL, '2018-04-16 18:37:51.000000', 'admin', '000000', '1', 'DE', 'DELAWARE', 'Delaware', '840'),
	('38', 0, '2018-04-16 18:37:51.000000', NULL, '2018-04-16 18:37:51.000000', 'admin', '000000', '1', 'HI', 'HAWAII', 'Hawaii', '840'),
	('39', 0, '2018-04-16 18:37:51.000000', NULL, '2018-04-16 18:37:51.000000', 'admin', '000000', '1', 'IN', 'INDIANA', 'Indiana', '840'),
	('4', 0, '2018-04-16 18:37:50.000000', NULL, '2018-04-16 18:37:50.000000', 'admin', '000000', '1', 'FL', 'FLORIDA', 'Florida', '840'),
	('40', 0, '2018-04-16 18:37:51.000000', NULL, '2018-04-16 18:37:51.000000', 'admin', '000000', '1', 'KY', 'KENTUCKY', 'Kentucky', '840'),
	('41', 0, '2018-04-16 18:37:51.000000', NULL, '2018-04-16 18:37:51.000000', 'admin', '000000', '1', 'MD', 'MARYLAND', 'Maryland', '840'),
	('42', 0, '2018-04-16 18:37:51.000000', NULL, '2018-04-16 18:37:51.000000', 'admin', '000000', '1', 'MN', 'MINNESOTA', 'Minnesota', '840'),
	('43', 0, '2018-04-16 18:37:51.000000', NULL, '2018-04-16 18:37:51.000000', 'admin', '000000', '1', 'MT', 'MONTANA', 'Montana', '840'),
	('44', 0, '2018-04-16 18:37:51.000000', NULL, '2018-04-16 18:37:51.000000', 'admin', '000000', '1', 'NH', 'NEW HAMPSHIRE', 'New Hampshire', '840'),
	('45', 0, '2018-04-16 18:37:51.000000', NULL, '2018-04-16 18:37:51.000000', 'admin', '000000', '1', 'NY', 'NEW YORK', 'New York', '840'),
	('46', 0, '2018-04-16 18:37:51.000000', NULL, '2018-04-16 18:37:51.000000', 'admin', '000000', '1', 'OH', 'OHIO', 'Ohio', '840'),
	('47', 0, '2018-04-16 18:37:51.000000', NULL, '2018-04-16 18:37:51.000000', 'admin', '000000', '1', 'PA', 'PENNSYLVANIA', 'Pennsylvania', '840'),
	('48', 0, '2018-04-16 18:37:51.000000', NULL, '2018-04-16 18:37:51.000000', 'admin', '000000', '1', 'SD', 'SOUTH DAKOTA', 'South Dakota', '840'),
	('49', 0, '2018-04-16 18:37:51.000000', NULL, '2018-04-16 18:37:51.000000', 'admin', '000000', '1', 'UT', 'UTAH', 'Utah', '840'),
	('5', 0, '2018-04-16 18:37:50.000000', NULL, '2018-04-16 18:37:50.000000', 'admin', '000000', '1', 'ID', 'IDAHO', 'Idaho', '840'),
	('50', 0, '2018-04-16 18:37:51.000000', NULL, '2018-04-16 18:37:51.000000', 'admin', '000000', '1', 'WA', 'WASHINGTON', 'Washington', '840'),
	('51', 0, '2018-04-16 18:37:51.000000', NULL, '2018-04-16 18:37:51.000000', 'admin', '000000', '1', 'WY', 'WYOMING', 'Wyoming', '840'),
	('53', 0, '2018-04-16 18:37:51.000000', NULL, '2018-04-16 18:37:51.000000', 'admin', '000000', '1', 'AB', 'ALBERTA', 'Alberta', '124'),
	('54', 0, '2018-04-16 18:37:51.000000', NULL, '2018-04-16 18:37:51.000000', 'admin', '000000', '1', 'LB', 'LABRADOR', 'Labrador', '124'),
	('55', 0, '2018-04-16 18:37:51.000000', NULL, '2018-04-16 18:37:51.000000', 'admin', '000000', '1', 'NB', 'NEW BRUNSWICK', 'New Brunswick', '124'),
	('56', 0, '2018-04-16 18:37:51.000000', NULL, '2018-04-16 18:37:51.000000', 'admin', '000000', '1', 'NS', 'NOVA SCOTIA', 'Nova Scotia', '124'),
	('57', 0, '2018-04-16 18:37:51.000000', NULL, '2018-04-16 18:37:51.000000', 'admin', '000000', '1', 'NW', 'NORTH WEST TERR.', 'North West Terr.', '124'),
	('58', 0, '2018-04-16 18:37:51.000000', NULL, '2018-04-16 18:37:51.000000', 'admin', '000000', '1', 'PE', 'PRINCE EDWARD IS.', 'Prince Edward Is.', '124'),
	('59', 0, '2018-04-16 18:37:51.000000', NULL, '2018-04-16 18:37:51.000000', 'admin', '000000', '1', 'SK', 'SASKATCHEWEN', 'Saskatchewen', '124'),
	('6', 0, '2018-04-16 18:37:50.000000', NULL, '2018-04-16 18:37:50.000000', 'admin', '000000', '1', 'IA', 'IOWA', 'Iowa', '840'),
	('60', 0, '2018-04-16 18:37:51.000000', NULL, '2018-04-16 18:37:51.000000', 'admin', '000000', '1', 'BC', 'BRITISH COLUMBIA', 'British Columbia', '124'),
	('61', 0, '2018-04-16 18:37:51.000000', NULL, '2018-04-16 18:37:51.000000', 'admin', '000000', '1', 'MB', 'MANITOBA', 'Manitoba', '124'),
	('62', 0, '2018-04-16 18:37:51.000000', NULL, '2018-04-16 18:37:51.000000', 'admin', '000000', '1', 'NF', 'NEWFOUNDLAND', 'Newfoundland', '124'),
	('63', 0, '2018-04-16 18:37:51.000000', NULL, '2018-04-16 18:37:51.000000', 'admin', '000000', '1', 'NU', 'NUNAVUT', 'Nunavut', '124'),
	('64', 0, '2018-04-16 18:37:51.000000', NULL, '2018-04-16 18:37:51.000000', 'admin', '000000', '1', 'ON', 'ONTARIO', 'Ontario', '124'),
	('65', 0, '2018-04-16 18:37:51.000000', NULL, '2018-04-16 18:37:51.000000', 'admin', '000000', '1', 'QC', 'QUEBEC', 'Quebec', '124'),
	('66', 0, '2018-04-16 18:37:51.000000', NULL, '2018-04-16 18:37:51.000000', 'admin', '000000', '1', 'YU', 'YUKON', 'Yukon', '124'),
	('7', 0, '2018-04-16 18:37:50.000000', NULL, '2018-04-16 18:37:50.000000', 'admin', '000000', '1', 'LA', 'LOUISIANA', 'Louisiana', '840'),
	('8', 0, '2018-04-16 18:37:50.000000', NULL, '2018-04-16 18:37:50.000000', 'admin', '000000', '1', 'MA', 'MASSACHUSETTS', 'Massachusetts', '840'),
	('9', 0, '2018-04-16 18:37:50.000000', NULL, '2018-04-16 18:37:50.000000', 'admin', '000000', '1', 'MS', 'MISSISSIPPI', 'Mississippi', '840');
/*!40000 ALTER TABLE `ref_state_province` ENABLE KEYS */;

-- Dumping structure for table yug_owner.ref_status_in_source
CREATE TABLE IF NOT EXISTS `ref_status_in_source` (
  `ID_PK` varchar(50) COLLATE utf8_bin NOT NULL,
  `VERSION` decimal(22,0) NOT NULL,
  `CREATED_TS` datetime(6) NOT NULL,
  `DELETED_TS` datetime(6) DEFAULT NULL,
  `UPDATED_TS` datetime(6) NOT NULL,
  `UPDATED_BY_USER` varchar(50) COLLATE utf8_bin NOT NULL,
  `UPDATED_BY_TXN_ID` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `CONFIG_LANGUAGE_CODE_KEY` varchar(50) COLLATE utf8_bin NOT NULL,
  `KEY` varchar(50) COLLATE utf8_bin NOT NULL,
  `VALUE` varchar(50) COLLATE utf8_bin NOT NULL,
  `DESCRIPTION` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_PK`),
  UNIQUE KEY `REF_STATUSINSOURCE_UNIQUEKEY` (`KEY`,`CONFIG_LANGUAGE_CODE_KEY`),
  KEY `INDX_REF_STATUS_IN_SOURCE_1` (`KEY`),
  KEY `INDX_REF_STATUS_IN_SOURCE_2` (`VALUE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- Dumping data for table yug_owner.ref_status_in_source: ~4 rows (approximately)
/*!40000 ALTER TABLE `ref_status_in_source` DISABLE KEYS */;
INSERT INTO `ref_status_in_source` (`ID_PK`, `VERSION`, `CREATED_TS`, `DELETED_TS`, `UPDATED_TS`, `UPDATED_BY_USER`, `UPDATED_BY_TXN_ID`, `CONFIG_LANGUAGE_CODE_KEY`, `KEY`, `VALUE`, `DESCRIPTION`) VALUES
	('1', 0, '2017-07-05 11:47:40.000000', NULL, '2017-07-05 11:47:40.000000', 'admin', '000000', '1', '1', 'ACTIVE', NULL),
	('2', 0, '2017-07-05 11:47:40.000000', NULL, '2017-07-05 11:47:40.000000', 'admin', '000000', '1', '2', 'INACTIVE', NULL),
	('3', 0, '2017-07-05 11:47:40.000000', NULL, '2017-07-05 11:47:40.000000', 'admin', '000000', '1', '3', 'SUSPENDED', NULL),
	('4', 0, '2017-07-05 11:47:40.000000', NULL, '2017-07-05 11:47:40.000000', 'admin', '000000', '1', '4', 'TERMINATED', NULL);
/*!40000 ALTER TABLE `ref_status_in_source` ENABLE KEYS */;

-- Dumping structure for table yug_owner.ref_status_type
CREATE TABLE IF NOT EXISTS `ref_status_type` (
  `ID_PK` varchar(50) COLLATE utf8_bin NOT NULL,
  `VERSION` decimal(22,0) NOT NULL,
  `CREATED_TS` datetime(6) NOT NULL,
  `DELETED_TS` datetime(6) DEFAULT NULL,
  `UPDATED_TS` datetime(6) NOT NULL,
  `UPDATED_BY_USER` varchar(50) COLLATE utf8_bin NOT NULL,
  `UPDATED_BY_TXN_ID` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `CONFIG_LANGUAGE_CODE_KEY` varchar(50) COLLATE utf8_bin NOT NULL,
  `KEY` varchar(50) COLLATE utf8_bin NOT NULL,
  `VALUE` varchar(50) COLLATE utf8_bin NOT NULL,
  `DESCRIPTION` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_PK`),
  UNIQUE KEY `REF_STATUS_TYPE_UNIQUEKEY` (`KEY`,`CONFIG_LANGUAGE_CODE_KEY`),
  KEY `INDX_REF_STATUS_TYPE_1` (`KEY`),
  KEY `INDX_REF_STATUS_TYPE_2` (`VALUE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- Dumping data for table yug_owner.ref_status_type: ~4 rows (approximately)
/*!40000 ALTER TABLE `ref_status_type` DISABLE KEYS */;
INSERT INTO `ref_status_type` (`ID_PK`, `VERSION`, `CREATED_TS`, `DELETED_TS`, `UPDATED_TS`, `UPDATED_BY_USER`, `UPDATED_BY_TXN_ID`, `CONFIG_LANGUAGE_CODE_KEY`, `KEY`, `VALUE`, `DESCRIPTION`) VALUES
	('1', 0, '2017-07-05 11:48:24.000000', NULL, '2017-07-05 11:48:24.000000', 'admin', '000000', '1', '1', 'ACTIVE', NULL),
	('2', 0, '2017-07-05 11:48:24.000000', NULL, '2017-07-05 11:48:24.000000', 'admin', '000000', '1', '2', 'INACTIVE', NULL),
	('3', 0, '2017-07-05 11:48:24.000000', NULL, '2017-07-05 11:48:24.000000', 'admin', '000000', '1', '3', 'SUSPENDED', NULL),
	('4', 0, '2017-07-05 11:48:24.000000', NULL, '2017-07-05 11:48:24.000000', 'admin', '000000', '1', '4', 'TERMINATED', NULL);
/*!40000 ALTER TABLE `ref_status_type` ENABLE KEYS */;

-- Dumping structure for table yug_owner.ref_suffix_name
CREATE TABLE IF NOT EXISTS `ref_suffix_name` (
  `ID_PK` varchar(50) COLLATE utf8_bin NOT NULL,
  `VERSION` decimal(22,0) NOT NULL,
  `CREATED_TS` datetime(6) NOT NULL,
  `DELETED_TS` datetime(6) DEFAULT NULL,
  `UPDATED_TS` datetime(6) NOT NULL,
  `UPDATED_BY_USER` varchar(50) COLLATE utf8_bin NOT NULL,
  `UPDATED_BY_TXN_ID` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `CONFIG_LANGUAGE_CODE_KEY` varchar(50) COLLATE utf8_bin NOT NULL,
  `KEY` varchar(50) COLLATE utf8_bin NOT NULL,
  `VALUE` varchar(50) COLLATE utf8_bin NOT NULL,
  `DESCRIPTION` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_PK`),
  UNIQUE KEY `REF_SUFFIX_NAME_UNIQUEKEY` (`KEY`,`CONFIG_LANGUAGE_CODE_KEY`),
  KEY `INDX_REF_SUFFIX_NAME_1` (`KEY`),
  KEY `INDX_REF_SUFFIX_NAME_2` (`VALUE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- Dumping data for table yug_owner.ref_suffix_name: ~2 rows (approximately)
/*!40000 ALTER TABLE `ref_suffix_name` DISABLE KEYS */;
INSERT INTO `ref_suffix_name` (`ID_PK`, `VERSION`, `CREATED_TS`, `DELETED_TS`, `UPDATED_TS`, `UPDATED_BY_USER`, `UPDATED_BY_TXN_ID`, `CONFIG_LANGUAGE_CODE_KEY`, `KEY`, `VALUE`, `DESCRIPTION`) VALUES
	('1', 0, '2017-07-05 11:50:58.000000', NULL, '2017-07-05 11:50:58.000000', 'admin', '000000', '1', '1', 'Sr', 'Senior'),
	('2', 0, '2017-07-05 11:50:58.000000', NULL, '2017-07-05 11:50:58.000000', 'admin', '000000', '1', '2', 'Jr', 'Junior');
/*!40000 ALTER TABLE `ref_suffix_name` ENABLE KEYS */;

-- Dumping structure for table yug_owner.ref_termination_reason
CREATE TABLE IF NOT EXISTS `ref_termination_reason` (
  `ID_PK` varchar(50) COLLATE utf8_bin NOT NULL,
  `VERSION` decimal(22,0) NOT NULL,
  `CREATED_TS` datetime(6) NOT NULL,
  `DELETED_TS` datetime(6) DEFAULT NULL,
  `UPDATED_TS` datetime(6) NOT NULL,
  `UPDATED_BY_USER` varchar(50) COLLATE utf8_bin NOT NULL,
  `UPDATED_BY_TXN_ID` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `CONFIG_LANGUAGE_CODE_KEY` varchar(50) COLLATE utf8_bin NOT NULL,
  `KEY` varchar(50) COLLATE utf8_bin NOT NULL,
  `VALUE` varchar(50) COLLATE utf8_bin NOT NULL,
  `DESCRIPTION` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_PK`),
  UNIQUE KEY `REFTERMINATEREASON_UNIQUEKEY` (`KEY`,`CONFIG_LANGUAGE_CODE_KEY`),
  KEY `INDX_REF_TERMINATION_REASON_1` (`KEY`),
  KEY `INDX_REF_TERMINATION_REASON_2` (`VALUE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- Dumping data for table yug_owner.ref_termination_reason: ~5 rows (approximately)
/*!40000 ALTER TABLE `ref_termination_reason` DISABLE KEYS */;
INSERT INTO `ref_termination_reason` (`ID_PK`, `VERSION`, `CREATED_TS`, `DELETED_TS`, `UPDATED_TS`, `UPDATED_BY_USER`, `UPDATED_BY_TXN_ID`, `CONFIG_LANGUAGE_CODE_KEY`, `KEY`, `VALUE`, `DESCRIPTION`) VALUES
	('1', 0, '2017-07-05 11:53:45.000000', NULL, '2017-07-05 11:53:45.000000', 'admin', '000000', '1', '1', 'ACCOUNT CLOSED', 'Account is closed by customer'),
	('2', 0, '2017-07-05 11:53:45.000000', NULL, '2017-07-05 11:53:45.000000', 'admin', '000000', '1', '2', 'OWNER DECEASED', 'Account Owner is no more alive'),
	('3', 0, '2017-07-05 11:53:45.000000', NULL, '2017-07-05 11:53:45.000000', 'admin', '000000', '1', '3', 'LOAN FORECLOSED', 'Loan account foreclosed'),
	('4', 0, '2017-07-05 11:53:45.000000', NULL, '2017-07-05 11:53:45.000000', 'admin', '000000', '1', '4', 'DORMANT', 'Account is dormant for long duration'),
	('5', 0, '2017-07-05 11:53:45.000000', NULL, '2017-07-05 11:53:45.000000', 'admin', '000000', '1', '5', 'MERGED', 'ACCOUNT IS MERGED WITH OTHER ACCOUNT');
/*!40000 ALTER TABLE `ref_termination_reason` ENABLE KEYS */;

-- Dumping structure for table yug_owner.vehicle
CREATE TABLE IF NOT EXISTS `vehicle` (
  `ID_PK` varchar(50) COLLATE utf8_bin NOT NULL,
  `VERSION` decimal(22,0) NOT NULL,
  `CREATED_TS` datetime(6) NOT NULL,
  `DELETED_TS` datetime(6) DEFAULT NULL,
  `UPDATED_TS` datetime(6) NOT NULL,
  `UPDATED_BY_USER` varchar(50) COLLATE utf8_bin NOT NULL,
  `UPDATED_BY_TXN_ID` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `VIN_NUMBER` varchar(100) COLLATE utf8_bin NOT NULL,
  `CHASSIS_NUMBER` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `MAKE` varchar(30) COLLATE utf8_bin DEFAULT NULL,
  `MODEL` varchar(30) COLLATE utf8_bin DEFAULT NULL,
  `YEAR` varchar(30) COLLATE utf8_bin DEFAULT NULL,
  `INTERIOR_COLOR` varchar(30) COLLATE utf8_bin DEFAULT NULL,
  `EXTERIOR_COLOR` varchar(30) COLLATE utf8_bin DEFAULT NULL,
  `REGISTRATION_NUMBER` varchar(30) COLLATE utf8_bin DEFAULT NULL,
  `COUNTRY_OF_REGISTRATION_REFKEY` varchar(30) COLLATE utf8_bin DEFAULT NULL,
  `INSURANCE_ISSUED_BY` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `INSURANCE_ISSUED_DATE` datetime(6) DEFAULT NULL,
  `INSURANCE_EXPIRY_DATE` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`ID_PK`),
  KEY `INDX_VEHICLE_1` (`VIN_NUMBER`),
  KEY `INDX_VEHICLE_2` (`CHASSIS_NUMBER`),
  KEY `INDX_VEHICLE_3` (`REGISTRATION_NUMBER`),
  KEY `INDX_VEHICLE_4` (`COUNTRY_OF_REGISTRATION_REFKEY`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- Dumping data for table yug_owner.vehicle: ~0 rows (approximately)
/*!40000 ALTER TABLE `vehicle` DISABLE KEYS */;
/*!40000 ALTER TABLE `vehicle` ENABLE KEYS */;

-- Dumping structure for trigger yug_owner.D_ACCOUNT
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `D_ACCOUNT` AFTER DELETE ON `account` FOR EACH ROW BEGIN INSERT INTO AL_ACCOUNT ( AUDITLOG_ID_PK, AUDITLOG_CREATED_TS, AUDITLOG_ACTION_CODE ,ID_PK, VERSION, CREATED_TS, DELETED_TS, UPDATED_TS, UPDATED_BY_USER, UPDATED_BY_TXN_ID, CONTRACT_SIGNED_LANG_REFKEY, CURRENCY_REFKEY, BILLING_MODE_TYPE_REFKEY, FREQUENCY_OF_PAYMENT, LOBTYPE_REFKEY, LOB_DESCRIPTION, SOURCE_SYSTEM_REFKEY, SOURCE_ACCOUNT_ID, MANAGEDBY_BU_CODE, MANAGEDBY_BU_ID, BRANCH_CODE_REFKEY, ACCOUNT_NAME, ACCOUNT_NAME2, ACCOUNT_DESCRIPTION, ACCOUNT_SOURCE_STATUS_REFKEY, ACCOUNT_MDM_STATUS_REFKEY, SIGNED_DATE, SIGNED_PLACE, EXECUTED_DATE, TERMINATED_DATE, TERMINATION_REASON_REFKEY ) VALUES( uuid(), CURRENT_TIMESTAMP(6) , 'D',  OLD.ID_PK,  OLD.VERSION,  OLD.CREATED_TS,  OLD.DELETED_TS,  OLD.UPDATED_TS,  OLD.UPDATED_BY_USER,  OLD.UPDATED_BY_TXN_ID,  OLD.CONTRACT_SIGNED_LANG_REFKEY,  OLD.CURRENCY_REFKEY,  OLD.BILLING_MODE_TYPE_REFKEY,  OLD.FREQUENCY_OF_PAYMENT,  OLD.LOBTYPE_REFKEY,  OLD.LOB_DESCRIPTION,  OLD.SOURCE_SYSTEM_REFKEY,  OLD.SOURCE_ACCOUNT_ID,  OLD.MANAGEDBY_BU_CODE,  OLD.MANAGEDBY_BU_ID,  OLD.BRANCH_CODE_REFKEY,  OLD.ACCOUNT_NAME,  OLD.ACCOUNT_NAME2,  OLD.ACCOUNT_DESCRIPTION,  OLD.ACCOUNT_SOURCE_STATUS_REFKEY,  OLD.ACCOUNT_MDM_STATUS_REFKEY,  OLD.SIGNED_DATE,  OLD.SIGNED_PLACE,  OLD.EXECUTED_DATE,  OLD.TERMINATED_DATE,  OLD.TERMINATION_REASON_REFKEY); END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger yug_owner.D_ACCOUNT_ADDRESS_ASSOC
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `D_ACCOUNT_ADDRESS_ASSOC` AFTER DELETE ON `account_address_assoc` FOR EACH ROW BEGIN INSERT INTO AL_ACCOUNT_ADDRESS_ASSOC ( AUDITLOG_ID_PK, AUDITLOG_CREATED_TS, AUDITLOG_ACTION_CODE ,ID_PK, VERSION, CREATED_TS, DELETED_TS, UPDATED_TS, UPDATED_BY_USER, UPDATED_BY_TXN_ID, ACCOUNT_IDPK, ADDRESS_IDPK, ADDRESS_TYPE_REFKEY, ADDRESS_SUBTYPE_REFKEY, PREFERRED_FLAG, SOLICITATION_FLAG ) VALUES( uuid(), CURRENT_TIMESTAMP(6) , 'D',  OLD.ID_PK,  OLD.VERSION,  OLD.CREATED_TS,  OLD.DELETED_TS,  OLD.UPDATED_TS,  OLD.UPDATED_BY_USER,  OLD.UPDATED_BY_TXN_ID,  OLD.ACCOUNT_IDPK,  OLD.ADDRESS_IDPK,  OLD.ADDRESS_TYPE_REFKEY,  OLD.ADDRESS_SUBTYPE_REFKEY,  OLD.PREFERRED_FLAG,  OLD.SOLICITATION_FLAG); END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger yug_owner.D_ACCOUNT_PHONE_ASSOC
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `D_ACCOUNT_PHONE_ASSOC` AFTER DELETE ON `account_phone_assoc` FOR EACH ROW BEGIN INSERT INTO AL_ACCOUNT_PHONE_ASSOC ( AUDITLOG_ID_PK, AUDITLOG_CREATED_TS, AUDITLOG_ACTION_CODE ,ID_PK, VERSION, CREATED_TS, DELETED_TS, UPDATED_TS, UPDATED_BY_USER, UPDATED_BY_TXN_ID, ACCOUNT_IDPK, PHONE_TYPE_REFKEY, PHONE_SUBTYPE_REFKEY, PREFERRED_FLAG, PHONE_NUMBER, PHONE_STANDARDIZED_IDPK ) VALUES( uuid(), CURRENT_TIMESTAMP(6) , 'D',  OLD.ID_PK,  OLD.VERSION,  OLD.CREATED_TS,  OLD.DELETED_TS,  OLD.UPDATED_TS,  OLD.UPDATED_BY_USER,  OLD.UPDATED_BY_TXN_ID,  OLD.ACCOUNT_IDPK,  OLD.PHONE_TYPE_REFKEY,  OLD.PHONE_SUBTYPE_REFKEY,  OLD.PREFERRED_FLAG,  OLD.PHONE_NUMBER,  OLD.PHONE_STANDARDIZED_IDPK); END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger yug_owner.D_ADDRESS
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `D_ADDRESS` AFTER DELETE ON `address` FOR EACH ROW BEGIN INSERT INTO AL_ADDRESS ( AUDITLOG_ID_PK, AUDITLOG_CREATED_TS, AUDITLOG_ACTION_CODE ,ID_PK, VERSION, CREATED_TS, DELETED_TS, UPDATED_TS, UPDATED_BY_USER, UPDATED_BY_TXN_ID, ADDRESS_LINE_ONE, ADDRESS_LINE_TWO, ADDRESS_LINE_THREE, ADDRESS_LINE_FOUR, STREET_NUMBER, STREET_NAME, HOUSE_NUMBER, BUILDING_NUMBER, COUNTY, CITY, DISTRICT_ZONE, STATE_PROVINCE_REFKEY, COUNTRY_REFKEY, POSTAL_CODE, NEAREST_LANDMARK, BOX_DESIGNATOR, BOX_IDENTIFIER, NEAREST_RAILWAY_STATION, NEAREST_AIRPORT, PHONETIC_ADDRESS_LINE_ONE, PHONETIC_ADDRESS_LINE_TWO, PHONETIC_ADDRESS_LINE_THREE, PHONETIC_ADDRESS_LINE_FOUR, PHONETIC_STREET_NAME, PHONETIC_COUNTY, PHONETIC_CITY, PHONETIC_DISTRICT_ZONE ) VALUES( uuid(), CURRENT_TIMESTAMP(6) , 'D',  OLD.ID_PK,  OLD.VERSION,  OLD.CREATED_TS,  OLD.DELETED_TS,  OLD.UPDATED_TS,  OLD.UPDATED_BY_USER,  OLD.UPDATED_BY_TXN_ID,  OLD.ADDRESS_LINE_ONE,  OLD.ADDRESS_LINE_TWO,  OLD.ADDRESS_LINE_THREE,  OLD.ADDRESS_LINE_FOUR,  OLD.STREET_NUMBER,  OLD.STREET_NAME,  OLD.HOUSE_NUMBER,  OLD.BUILDING_NUMBER,  OLD.COUNTY,  OLD.CITY,  OLD.DISTRICT_ZONE,  OLD.STATE_PROVINCE_REFKEY,  OLD.COUNTRY_REFKEY,  OLD.POSTAL_CODE,  OLD.NEAREST_LANDMARK,  OLD.BOX_DESIGNATOR,  OLD.BOX_IDENTIFIER,  OLD.NEAREST_RAILWAY_STATION,  OLD.NEAREST_AIRPORT,  OLD.PHONETIC_ADDRESS_LINE_ONE,  OLD.PHONETIC_ADDRESS_LINE_TWO,  OLD.PHONETIC_ADDRESS_LINE_THREE,  OLD.PHONETIC_ADDRESS_LINE_FOUR,  OLD.PHONETIC_STREET_NAME,  OLD.PHONETIC_COUNTY,  OLD.PHONETIC_CITY,  OLD.PHONETIC_DISTRICT_ZONE); END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger yug_owner.D_AUTH_ROLES_REGISTRY
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `D_AUTH_ROLES_REGISTRY` AFTER DELETE ON `auth_roles_registry` FOR EACH ROW BEGIN INSERT INTO AL_AUTH_ROLES_REGISTRY ( AUDITLOG_ID_PK, AUDITLOG_CREATED_TS, AUDITLOG_ACTION_CODE ,ID_PK, VERSION, CREATED_TS, DELETED_TS, UPDATED_TS, UPDATED_BY_USER, UPDATED_BY_TXN_ID, ROLE_NAME, DESCRIPTION ) VALUES( uuid(), CURRENT_TIMESTAMP(6) , 'D',  OLD.ID_PK,  OLD.VERSION,  OLD.CREATED_TS,  OLD.DELETED_TS,  OLD.UPDATED_TS,  OLD.UPDATED_BY_USER,  OLD.UPDATED_BY_TXN_ID,  OLD.ROLE_NAME,  OLD.DESCRIPTION ); END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger yug_owner.D_AUTH_USERROLE_ACCESSCONTROL
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `D_AUTH_USERROLE_ACCESSCONTROL` AFTER DELETE ON `auth_userrole_accesscontrol` FOR EACH ROW BEGIN INSERT INTO AL_AUTH_USERROLE_ACCESSCONTROL ( AUDITLOG_ID_PK, AUDITLOG_CREATED_TS, AUDITLOG_ACTION_CODE ,ID_PK, VERSION, CREATED_TS, DELETED_TS, UPDATED_TS, UPDATED_BY_USER, UPDATED_BY_TXN_ID, PROFILE_TYPE, AUTH_USER_ROLE_REGISTRY_IDPK, CONFIG_TXN_REGISTRY_IDPK, DESCRIPTION ) VALUES( uuid(), CURRENT_TIMESTAMP(6) , 'D',  OLD.ID_PK,  OLD.VERSION,  OLD.CREATED_TS,  OLD.DELETED_TS,  OLD.UPDATED_TS,  OLD.UPDATED_BY_USER,  OLD.UPDATED_BY_TXN_ID,  OLD.PROFILE_TYPE,  OLD.AUTH_USER_ROLE_REGISTRY_IDPK,  OLD.CONFIG_TXN_REGISTRY_IDPK,  OLD.DESCRIPTION); END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger yug_owner.D_AUTH_USER_REGISTRY
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `D_AUTH_USER_REGISTRY` AFTER DELETE ON `auth_user_registry` FOR EACH ROW BEGIN INSERT INTO AL_AUTH_USER_REGISTRY ( AUDITLOG_ID_PK, AUDITLOG_CREATED_TS, AUDITLOG_ACTION_CODE ,ID_PK, VERSION, CREATED_TS, DELETED_TS, UPDATED_TS, UPDATED_BY_USER, UPDATED_BY_TXN_ID, USER_NAME, DESCRIPTION ) VALUES( uuid(), CURRENT_TIMESTAMP(6) , 'D',  OLD.ID_PK,  OLD.VERSION,  OLD.CREATED_TS,  OLD.DELETED_TS,  OLD.UPDATED_TS,  OLD.UPDATED_BY_USER,  OLD.UPDATED_BY_TXN_ID,  OLD.USER_NAME,  OLD.DESCRIPTION); END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger yug_owner.D_AUTH_USER_ROLE_ASSOC
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `D_AUTH_USER_ROLE_ASSOC` AFTER DELETE ON `auth_user_role_assoc` FOR EACH ROW BEGIN INSERT INTO AL_AUTH_USER_ROLE_ASSOC ( AUDITLOG_ID_PK, AUDITLOG_CREATED_TS, AUDITLOG_ACTION_CODE ,ID_PK, VERSION, CREATED_TS, DELETED_TS, UPDATED_TS, UPDATED_BY_USER, UPDATED_BY_TXN_ID, AUTH_USER_REGISTRY_IDPK, AUTH_ROLES_REGISTRY_IDPK, DESCRIPTION ) VALUES( uuid(), CURRENT_TIMESTAMP(6) , 'D',  OLD.ID_PK,  OLD.VERSION,  OLD.CREATED_TS,  OLD.DELETED_TS,  OLD.UPDATED_TS,  OLD.UPDATED_BY_USER,  OLD.UPDATED_BY_TXN_ID,  OLD.AUTH_USER_REGISTRY_IDPK,  OLD.AUTH_ROLES_REGISTRY_IDPK,  OLD.DESCRIPTION); END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger yug_owner.D_BATCH_ENTITY_TO_PROCESS
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `D_BATCH_ENTITY_TO_PROCESS` AFTER DELETE ON `batch_entity_to_process` FOR EACH ROW BEGIN INSERT INTO AL_BATCH_ENTITY_TO_PROCESS ( AUDITLOG_ID_PK, AUDITLOG_CREATED_TS, AUDITLOG_ACTION_CODE ,ID_PK, VERSION, CREATED_TS, DELETED_TS, UPDATED_TS, UPDATED_BY_USER, UPDATED_BY_TXN_ID, ENTITY_OBJECT_TYPE_REFKEY, ENTITY_IDPK, BATCH_PROPOSED_ACTION_REFKEY, BATCH_ACTION_STATUS_REFKEY, PROCESS_AFTER_TIMESTAMP, PROCESS_BEFORE_TIMESTAMP, ENTRY_MADE_BY_SUBSYSTEM_NAME, COMMENTS ) VALUES( uuid(), CURRENT_TIMESTAMP(6) , 'D',  OLD.ID_PK,  OLD.VERSION,  OLD.CREATED_TS,  OLD.DELETED_TS,  OLD.UPDATED_TS,  OLD.UPDATED_BY_USER,  OLD.UPDATED_BY_TXN_ID,  OLD.ENTITY_OBJECT_TYPE_REFKEY,  OLD.ENTITY_IDPK,  OLD.BATCH_PROPOSED_ACTION_REFKEY,  OLD.BATCH_ACTION_STATUS_REFKEY,  OLD.PROCESS_AFTER_TIMESTAMP,  OLD.PROCESS_BEFORE_TIMESTAMP,  OLD.ENTRY_MADE_BY_SUBSYSTEM_NAME,  OLD.COMMENTS); END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger yug_owner.D_CONFIG_APP_PROPERTIES
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `D_CONFIG_APP_PROPERTIES` AFTER DELETE ON `config_app_properties` FOR EACH ROW BEGIN INSERT INTO AL_CONFIG_APP_PROPERTIES ( AUDITLOG_ID_PK, AUDITLOG_CREATED_TS, AUDITLOG_ACTION_CODE ,ID_PK, VERSION, CREATED_TS, DELETED_TS, UPDATED_TS, UPDATED_BY_USER, UPDATED_BY_TXN_ID, `KEY`, VALUE, VALUE_DEFAULT, DESCRIPTION ) VALUES( uuid(), CURRENT_TIMESTAMP(6) , 'D',  OLD.ID_PK,  OLD.VERSION,  OLD.CREATED_TS,  OLD.DELETED_TS,  OLD.UPDATED_TS,  OLD.UPDATED_BY_USER,  OLD.UPDATED_BY_TXN_ID,  OLD.KEY,  OLD.VALUE,  OLD.VALUE_DEFAULT,  OLD.DESCRIPTION); END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger yug_owner.D_CONFIG_ERRORCODE_REGISTRY
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `D_CONFIG_ERRORCODE_REGISTRY` AFTER DELETE ON `config_errorcode_registry` FOR EACH ROW BEGIN INSERT INTO AL_CONFIG_ERRORCODE_REGISTRY ( AUDITLOG_ID_PK, AUDITLOG_CREATED_TS, AUDITLOG_ACTION_CODE ,ID_PK, VERSION, CONFIG_LANGUAGE_CODE_KEY, ERROR_CODE, ERROR_MESSAGE, DESCRIPTION, CREATED_TS, DELETED_TS, UPDATED_TS, UPDATED_BY_USER, UPDATED_BY_TXN_ID ) VALUES( uuid(), CURRENT_TIMESTAMP(6) , 'D',  OLD.ID_PK,  OLD.VERSION,  OLD.CONFIG_LANGUAGE_CODE_KEY,  OLD.ERROR_CODE,  OLD.ERROR_MESSAGE,  OLD.DESCRIPTION,  OLD.CREATED_TS,  OLD.DELETED_TS,  OLD.UPDATED_TS,  OLD.UPDATED_BY_USER,  OLD.UPDATED_BY_TXN_ID); END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger yug_owner.D_CONFIG_INQUIRY_LEVELS
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `D_CONFIG_INQUIRY_LEVELS` AFTER DELETE ON `config_inquiry_levels` FOR EACH ROW BEGIN INSERT INTO AL_CONFIG_INQUIRY_LEVELS ( AUDITLOG_ID_PK, AUDITLOG_CREATED_TS, AUDITLOG_ACTION_CODE ,ID_PK, VERSION, CREATED_TS, DELETED_TS, UPDATED_TS, UPDATED_BY_USER, UPDATED_BY_TXN_ID, INQUIRY_LEVEL, APPLICABLE_DOBJ, CHILD_DOBJ, DESCRIPTION ) VALUES( uuid(), CURRENT_TIMESTAMP(6) , 'D',  OLD.ID_PK,  OLD.VERSION,  OLD.CREATED_TS,  OLD.DELETED_TS,  OLD.UPDATED_TS,  OLD.UPDATED_BY_USER,  OLD.UPDATED_BY_TXN_ID,  OLD.INQUIRY_LEVEL,  OLD.APPLICABLE_DOBJ,  OLD.CHILD_DOBJ,  OLD.DESCRIPTION); END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger yug_owner.D_CONFIG_LANGUAGE_CODE
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `D_CONFIG_LANGUAGE_CODE` AFTER DELETE ON `config_language_code` FOR EACH ROW BEGIN INSERT INTO AL_CONFIG_LANGUAGE_CODE ( AUDITLOG_ID_PK, AUDITLOG_CREATED_TS, AUDITLOG_ACTION_CODE ,ID_PK, VERSION, CREATED_TS, DELETED_TS, UPDATED_TS, UPDATED_BY_USER, UPDATED_BY_TXN_ID, `KEY`, VALUE, DESCRIPTION ) VALUES( uuid(), CURRENT_TIMESTAMP(6) , 'D',  OLD.ID_PK,  OLD.VERSION,  OLD.CREATED_TS,  OLD.DELETED_TS,  OLD.UPDATED_TS,  OLD.UPDATED_BY_USER,  OLD.UPDATED_BY_TXN_ID,  OLD.KEY,  OLD.VALUE,  OLD.DESCRIPTION); END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger yug_owner.D_CONFIG_TXN_REGISTRY
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `D_CONFIG_TXN_REGISTRY` AFTER DELETE ON `config_txn_registry` FOR EACH ROW BEGIN INSERT INTO AL_CONFIG_TXN_REGISTRY ( AUDITLOG_ID_PK, AUDITLOG_CREATED_TS, AUDITLOG_ACTION_CODE ,ID_PK, VERSION, TXNSERVICE_NAME, TXNSERVICE_CLASS, TXNSERVICE_CLASSMETHOD, DESCRIPTION, CREATED_TS, DELETED_TS, UPDATED_TS, UPDATED_BY_USER, UPDATED_TXN_REF_ID ) VALUES( uuid(), CURRENT_TIMESTAMP(6) , 'D',  OLD.ID_PK,  OLD.VERSION,  OLD.TXNSERVICE_NAME,  OLD.TXNSERVICE_CLASS,  OLD.TXNSERVICE_CLASSMETHOD,  OLD.DESCRIPTION,  OLD.CREATED_TS,  OLD.DELETED_TS,  OLD.UPDATED_TS,  OLD.UPDATED_BY_USER,  OLD.UPDATED_TXN_REF_ID); END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger yug_owner.D_CORPORATIONNAMES
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `D_CORPORATIONNAMES` AFTER DELETE ON `corporationnames` FOR EACH ROW BEGIN INSERT INTO AL_CORPORATIONNAMES ( AUDITLOG_ID_PK, AUDITLOG_CREATED_TS, AUDITLOG_ACTION_CODE ,ID_PK, VERSION, CREATED_TS, DELETED_TS, UPDATED_TS, UPDATED_BY_USER, UPDATED_BY_TXN_ID, LEGALENTITY_IDPK, CORPORATION_NAME_TYPE_REFKEY, CORPORATION_NAME, SOURCE_SYSTEM_REFKEY, PHONETIC_CORPORATION_NAME ) VALUES( uuid(), CURRENT_TIMESTAMP(6) , 'D',  OLD.ID_PK,  OLD.VERSION,  OLD.CREATED_TS,  OLD.DELETED_TS,  OLD.UPDATED_TS,  OLD.UPDATED_BY_USER,  OLD.UPDATED_BY_TXN_ID,  OLD.LEGALENTITY_IDPK,  OLD.CORPORATION_NAME_TYPE_REFKEY,  OLD.CORPORATION_NAME,  OLD.SOURCE_SYSTEM_REFKEY,  OLD.PHONETIC_CORPORATION_NAME); END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger yug_owner.D_ENTITY_GROUP
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `D_ENTITY_GROUP` AFTER DELETE ON `entity_group` FOR EACH ROW BEGIN INSERT INTO AL_ENTITY_GROUP ( AUDITLOG_ID_PK, AUDITLOG_CREATED_TS, AUDITLOG_ACTION_CODE ,ID_PK, VERSION, CREATED_TS, DELETED_TS, UPDATED_TS, UPDATED_BY_USER, UPDATED_BY_TXN_ID, GROUP_TYPE_REFKEY, GROUP_SUBTYPE_REFKEY, GROUP_NAME, DESCRIPTION ) VALUES( uuid(), CURRENT_TIMESTAMP(6) , 'D',  OLD.ID_PK,  OLD.VERSION,  OLD.CREATED_TS,  OLD.DELETED_TS,  OLD.UPDATED_TS,  OLD.UPDATED_BY_USER,  OLD.UPDATED_BY_TXN_ID,  OLD.GROUP_TYPE_REFKEY,  OLD.GROUP_SUBTYPE_REFKEY,  OLD.GROUP_NAME,  OLD.DESCRIPTION); END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger yug_owner.D_ENTITY_GROUP_ASSOC
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `D_ENTITY_GROUP_ASSOC` AFTER DELETE ON `entity_group_assoc` FOR EACH ROW BEGIN INSERT INTO AL_ENTITY_GROUP_ASSOC ( AUDITLOG_ID_PK, AUDITLOG_CREATED_TS, AUDITLOG_ACTION_CODE ,ID_PK, VERSION, CREATED_TS, DELETED_TS, UPDATED_TS, UPDATED_BY_USER, UPDATED_BY_TXN_ID, ENTITY_OBJECT_TYPE_REFKEY, ENTITY_IDPK, ENTITY_GROUP_IDPK, ASSOC_TYPE_REFKEY, DESCRIPTION ) VALUES( uuid(), CURRENT_TIMESTAMP(6) , 'D',  OLD.ID_PK,  OLD.VERSION,  OLD.CREATED_TS,  OLD.DELETED_TS,  OLD.UPDATED_TS,  OLD.UPDATED_BY_USER,  OLD.UPDATED_BY_TXN_ID,  OLD.ENTITY_OBJECT_TYPE_REFKEY,  OLD.ENTITY_IDPK,  OLD.ENTITY_GROUP_IDPK,  OLD.ASSOC_TYPE_REFKEY,  OLD.DESCRIPTION); END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger yug_owner.D_INACTIVE_LE_REGISTRY
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `D_INACTIVE_LE_REGISTRY` AFTER DELETE ON `inactive_le_registry` FOR EACH ROW BEGIN INSERT INTO AL_INACTIVE_LE_REGISTRY ( AUDITLOG_ID_PK, AUDITLOG_CREATED_TS, AUDITLOG_ACTION_CODE ,ID_PK, VERSION, CREATED_TS, DELETED_TS, UPDATED_TS, UPDATED_BY_USER, UPDATED_BY_TXN_ID, LEGALENTITY_IDPK, INACTIVATED_TS, INACTIVATION_REASON_REFKEY, COMMENTS ) VALUES( uuid(), CURRENT_TIMESTAMP(6) , 'D',  OLD.ID_PK,  OLD.VERSION,  OLD.CREATED_TS,  OLD.DELETED_TS,  OLD.UPDATED_TS,  OLD.UPDATED_BY_USER,  OLD.UPDATED_BY_TXN_ID,  OLD.LEGALENTITY_IDPK,  OLD.INACTIVATED_TS,  OLD.INACTIVATION_REASON_REFKEY,  OLD.COMMENTS); END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger yug_owner.D_LEGALENTITY
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `D_LEGALENTITY` AFTER DELETE ON `legalentity` FOR EACH ROW BEGIN INSERT INTO AL_LEGALENTITY ( AUDITLOG_ID_PK, AUDITLOG_CREATED_TS, AUDITLOG_ACTION_CODE ,ID_PK, VERSION, CREATED_TS, DELETED_TS, UPDATED_TS, UPDATED_BY_USER, UPDATED_BY_TXN_ID, DISPLAY_NAME, ENTITY_OBJECT_TYPE_REFKEY, CLASSIFICATION_CODE_REFKEY, IMPORTANCE_TYPE_REFKEY, LE_RATING_REFKEY, STATUS_TYPE_REFKEY, SOURCE_SYSTEM_REFKEY, ONBOARDING_DATE, OFFBOARDING_DATE, KYC_VERIFICATION_FLAG, DESCRIPTION, PHONETIC_DISPLAY_NAME ) VALUES( uuid(), CURRENT_TIMESTAMP(6) , 'D',  OLD.ID_PK,  OLD.VERSION,  OLD.CREATED_TS,  OLD.DELETED_TS,  OLD.UPDATED_TS,  OLD.UPDATED_BY_USER,  OLD.UPDATED_BY_TXN_ID,  OLD.DISPLAY_NAME,  OLD.ENTITY_OBJECT_TYPE_REFKEY,  OLD.CLASSIFICATION_CODE_REFKEY,  OLD.IMPORTANCE_TYPE_REFKEY,  OLD.LE_RATING_REFKEY,  OLD.STATUS_TYPE_REFKEY,  OLD.SOURCE_SYSTEM_REFKEY,  OLD.ONBOARDING_DATE,  OLD.OFFBOARDING_DATE,  OLD.KYC_VERIFICATION_FLAG,  OLD.DESCRIPTION,  OLD.PHONETIC_DISPLAY_NAME); END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger yug_owner.D_LE_ACCOUNT_ASSOC
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `D_LE_ACCOUNT_ASSOC` AFTER DELETE ON `le_account_assoc` FOR EACH ROW BEGIN INSERT INTO AL_LE_ACCOUNT_ASSOC ( AUDITLOG_ID_PK, AUDITLOG_CREATED_TS, AUDITLOG_ACTION_CODE ,ID_PK, VERSION, CREATED_TS, DELETED_TS, UPDATED_TS, UPDATED_BY_USER, UPDATED_BY_TXN_ID, LEGALENTITY_IDPK, LE_ROLETYPE_REFKEY, ACCOUNT_IDPK, ROLE_ACTIVATION_DATE, ROLE_DEACTIVATION_DATE, DEACTIVATION_REASON_REFKEY, AGREEMENT_TYPE_REFKEY, AGREEMENT_TYPE_DESCRIPTION ) VALUES( uuid(), CURRENT_TIMESTAMP(6) , 'D',  OLD.ID_PK,  OLD.VERSION,  OLD.CREATED_TS,  OLD.DELETED_TS,  OLD.UPDATED_TS,  OLD.UPDATED_BY_USER,  OLD.UPDATED_BY_TXN_ID,  OLD.LEGALENTITY_IDPK,  OLD.LE_ROLETYPE_REFKEY,  OLD.ACCOUNT_IDPK,  OLD.ROLE_ACTIVATION_DATE,  OLD.ROLE_DEACTIVATION_DATE,  OLD.DEACTIVATION_REASON_REFKEY,  OLD.AGREEMENT_TYPE_REFKEY,  OLD.AGREEMENT_TYPE_DESCRIPTION); END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger yug_owner.D_LE_ADDRESS_ASSOC
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `D_LE_ADDRESS_ASSOC` AFTER DELETE ON `le_address_assoc` FOR EACH ROW BEGIN INSERT INTO AL_LE_ADDRESS_ASSOC ( AUDITLOG_ID_PK, AUDITLOG_CREATED_TS, AUDITLOG_ACTION_CODE ,ID_PK, VERSION, CREATED_TS, DELETED_TS, UPDATED_TS, UPDATED_BY_USER, UPDATED_BY_TXN_ID, LEGALENTITY_IDPK, ADDRESS_IDPK, ADDRESS_TYPE_REFKEY, ADDRESS_SUBTYPE_REFKEY, PREFERRED_FLAG, SOLICITATION_FLAG ) VALUES( uuid(), CURRENT_TIMESTAMP(6) , 'D',  OLD.ID_PK,  OLD.VERSION,  OLD.CREATED_TS,  OLD.DELETED_TS,  OLD.UPDATED_TS,  OLD.UPDATED_BY_USER,  OLD.UPDATED_BY_TXN_ID,  OLD.LEGALENTITY_IDPK,  OLD.ADDRESS_IDPK,  OLD.ADDRESS_TYPE_REFKEY,  OLD.ADDRESS_SUBTYPE_REFKEY,  OLD.PREFERRED_FLAG,  OLD.SOLICITATION_FLAG); END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger yug_owner.D_LE_CORPORATION
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `D_LE_CORPORATION` AFTER DELETE ON `le_corporation` FOR EACH ROW BEGIN INSERT INTO AL_LE_CORPORATION ( AUDITLOG_ID_PK, AUDITLOG_CREATED_TS, AUDITLOG_ACTION_CODE ,LEGALENTITY_IDPK, VERSION, CREATED_TS, DELETED_TS, UPDATED_TS, UPDATED_BY_USER, UPDATED_BY_TXN_ID, CLASSIFICATION_CODE_REFKEY, INDUSTRY_CODE_REFKEY, GOVT_REGISTRATION_DATE, COUNTRY_REGISTRATION_REFKEY, NOTPROFIT_FLAG ) VALUES( uuid(), CURRENT_TIMESTAMP(6) , 'D',  OLD.LEGALENTITY_IDPK,  OLD.VERSION,  OLD.CREATED_TS,  OLD.DELETED_TS,  OLD.UPDATED_TS,  OLD.UPDATED_BY_USER,  OLD.UPDATED_BY_TXN_ID,  OLD.CLASSIFICATION_CODE_REFKEY,  OLD.INDUSTRY_CODE_REFKEY,  OLD.GOVT_REGISTRATION_DATE,  OLD.COUNTRY_REGISTRATION_REFKEY,  OLD.NOTPROFIT_FLAG); END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger yug_owner.D_LE_IDENTIFIER_KYC_REGISTRY
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `D_LE_IDENTIFIER_KYC_REGISTRY` AFTER DELETE ON `le_identifier_kyc_registry` FOR EACH ROW BEGIN INSERT INTO AL_LE_IDENTIFIER_KYC_REGISTRY ( AUDITLOG_ID_PK, AUDITLOG_CREATED_TS, AUDITLOG_ACTION_CODE ,ID_PK, VERSION, CREATED_TS, DELETED_TS, UPDATED_TS, UPDATED_BY_USER, UPDATED_BY_TXN_ID, IDENTIFICATION_TYPE_REFKEY, IDENTIFICATION_NUMBER, LEGALENTITY_IDPK, DOCUMENT, ISSUED_BY, ID_CONSIDERED_FOR_KYC_FLAG, ISSUED_DATE, SOURCE_SYSTEM_REFKEY, IDENTITY_DESCRIPTION ) VALUES( uuid(), CURRENT_TIMESTAMP(6) , 'D',  OLD.ID_PK,  OLD.VERSION,  OLD.CREATED_TS,  OLD.DELETED_TS,  OLD.UPDATED_TS,  OLD.UPDATED_BY_USER,  OLD.UPDATED_BY_TXN_ID,  OLD.IDENTIFICATION_TYPE_REFKEY,  OLD.IDENTIFICATION_NUMBER,  OLD.LEGALENTITY_IDPK,  OLD.DOCUMENT,  OLD.ISSUED_BY,  OLD.ID_CONSIDERED_FOR_KYC_FLAG,  OLD.ISSUED_DATE,  OLD.SOURCE_SYSTEM_REFKEY,  OLD.IDENTITY_DESCRIPTION); END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger yug_owner.D_LE_PERSON
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `D_LE_PERSON` AFTER DELETE ON `le_person` FOR EACH ROW BEGIN INSERT INTO AL_LE_PERSON ( AUDITLOG_ID_PK, AUDITLOG_CREATED_TS, AUDITLOG_ACTION_CODE ,LEGALENTITY_IDPK, VERSION, CREATED_TS, DELETED_TS, UPDATED_TS, UPDATED_BY_USER, UPDATED_BY_TXN_ID, PERSON_TYPE_REFKEY, GENDER_REFKEY, DATE_OF_BIRTH, COUNTRY_OF_BIRTH__REFKEY, COUNTRY_CITIZENSHIP_REFKEY, COUNTRY_OF_DOMICILE__REFKEY, MARITAL_STATUS, HIGHEST_EDU_QUAL_REFKEY, IS_DECEASED_FLAG, DECEASED_DATE, IS_HANDICAPPED_FLAG, HANDICAPPED_SINCE_DATE, NUMBER_OF_DEPENDENTS, NUMBER_OF_CHILDREN, PREFERRED_LANGUAGE_REFKEY ) VALUES( uuid(), CURRENT_TIMESTAMP(6) , 'D',  OLD.LEGALENTITY_IDPK,  OLD.VERSION,  OLD.CREATED_TS,  OLD.DELETED_TS,  OLD.UPDATED_TS,  OLD.UPDATED_BY_USER,  OLD.UPDATED_BY_TXN_ID,  OLD.PERSON_TYPE_REFKEY,  OLD.GENDER_REFKEY,  OLD.DATE_OF_BIRTH,  OLD.COUNTRY_OF_BIRTH__REFKEY,  OLD.COUNTRY_CITIZENSHIP_REFKEY,  OLD.COUNTRY_OF_DOMICILE__REFKEY,  OLD.MARITAL_STATUS,  OLD.HIGHEST_EDU_QUAL_REFKEY,  OLD.IS_DECEASED_FLAG,  OLD.DECEASED_DATE,  OLD.IS_HANDICAPPED_FLAG,  OLD.HANDICAPPED_SINCE_DATE,  OLD.NUMBER_OF_DEPENDENTS,  OLD.NUMBER_OF_CHILDREN,  OLD.PREFERRED_LANGUAGE_REFKEY); END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger yug_owner.D_LE_PHONE_ASSOC
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `D_LE_PHONE_ASSOC` AFTER DELETE ON `le_phone_assoc` FOR EACH ROW BEGIN INSERT INTO AL_LE_PHONE_ASSOC ( AUDITLOG_ID_PK, AUDITLOG_CREATED_TS, AUDITLOG_ACTION_CODE ,ID_PK, VERSION, CREATED_TS, DELETED_TS, UPDATED_TS, UPDATED_BY_USER, UPDATED_BY_TXN_ID, LEGALENTITY_IDPK, PHONE_TYPE_REFKEY, PHONE_SUBTYPE_REFKEY, PREFERRED_FLAG, PHONE_NUMBER, PHONE_STANDARDIZED_IDPK ) VALUES( uuid(), CURRENT_TIMESTAMP(6) , 'D',  OLD.ID_PK,  OLD.VERSION,  OLD.CREATED_TS,  OLD.DELETED_TS,  OLD.UPDATED_TS,  OLD.UPDATED_BY_USER,  OLD.UPDATED_BY_TXN_ID,  OLD.LEGALENTITY_IDPK,  OLD.PHONE_TYPE_REFKEY,  OLD.PHONE_SUBTYPE_REFKEY,  OLD.PREFERRED_FLAG,  OLD.PHONE_NUMBER,  OLD.PHONE_STANDARDIZED_IDPK); END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger yug_owner.D_LE_PREFERENCES
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `D_LE_PREFERENCES` AFTER DELETE ON `le_preferences` FOR EACH ROW BEGIN INSERT INTO AL_LE_PREFERENCES ( AUDITLOG_ID_PK, AUDITLOG_CREATED_TS, AUDITLOG_ACTION_CODE ,ID_PK, VERSION, CREATED_TS, DELETED_TS, UPDATED_TS, UPDATED_BY_USER, UPDATED_BY_TXN_ID, LEGALENTITY_IDPK, PREFERENCE_TYPE_REFKEY, PREF_FLAG, PREF_DESCRIPTION ) VALUES( uuid(), CURRENT_TIMESTAMP(6) , 'D',  OLD.ID_PK,  OLD.VERSION,  OLD.CREATED_TS,  OLD.DELETED_TS,  OLD.UPDATED_TS,  OLD.UPDATED_BY_USER,  OLD.UPDATED_BY_TXN_ID,  OLD.LEGALENTITY_IDPK,  OLD.PREFERENCE_TYPE_REFKEY,  OLD.PREF_FLAG,  OLD.PREF_DESCRIPTION); END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger yug_owner.D_LE_PROPERTY_ASSOC
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `D_LE_PROPERTY_ASSOC` AFTER DELETE ON `le_property_assoc` FOR EACH ROW BEGIN INSERT INTO AL_LE_PROPERTY_ASSOC ( AUDITLOG_ID_PK, AUDITLOG_CREATED_TS, AUDITLOG_ACTION_CODE ,ID_PK, VERSION, CREATED_TS, DELETED_TS, UPDATED_TS, UPDATED_BY_USER, UPDATED_BY_TXN_ID, PROPERTY_IDPK, LEGALENTITY_IDPK, PROPERTY_LE_RELTYPE_REFKEY, DESCRIPTION ) VALUES( uuid(), CURRENT_TIMESTAMP(6) , 'D',  OLD.ID_PK,  OLD.VERSION,  OLD.CREATED_TS,  OLD.DELETED_TS,  OLD.UPDATED_TS,  OLD.UPDATED_BY_USER,  OLD.UPDATED_BY_TXN_ID,  OLD.PROPERTY_IDPK,  OLD.LEGALENTITY_IDPK,  OLD.PROPERTY_LE_RELTYPE_REFKEY,  OLD.DESCRIPTION); END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger yug_owner.D_LE_SYSTEM_KEYS_REGISTRY
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `D_LE_SYSTEM_KEYS_REGISTRY` AFTER DELETE ON `le_system_keys_registry` FOR EACH ROW BEGIN INSERT INTO AL_LE_SYSTEM_KEYS_REGISTRY ( AUDITLOG_ID_PK, AUDITLOG_CREATED_TS, AUDITLOG_ACTION_CODE ,ID_PK, VERSION, CREATED_TS, DELETED_TS, UPDATED_TS, UPDATED_BY_USER, UPDATED_BY_TXN_ID, SOURCE_SYSTEM_REFKEY, REFERENCE_ID, LEGALENTITY_IDPK, STATUS_IN_SOURCE_REFKEY, DESCRIPTION ) VALUES( uuid(), CURRENT_TIMESTAMP(6) , 'D',  OLD.ID_PK,  OLD.VERSION,  OLD.CREATED_TS,  OLD.DELETED_TS,  OLD.UPDATED_TS,  OLD.UPDATED_BY_USER,  OLD.UPDATED_BY_TXN_ID,  OLD.SOURCE_SYSTEM_REFKEY,  OLD.REFERENCE_ID,  OLD.LEGALENTITY_IDPK,  OLD.STATUS_IN_SOURCE_REFKEY,  OLD.DESCRIPTION); END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger yug_owner.D_LE_TO_LE_RELATIONSHIP
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `D_LE_TO_LE_RELATIONSHIP` AFTER DELETE ON `le_to_le_relationship` FOR EACH ROW BEGIN INSERT INTO AL_LE_TO_LE_RELATIONSHIP ( AUDITLOG_ID_PK, AUDITLOG_CREATED_TS, AUDITLOG_ACTION_CODE ,ID_PK, VERSION, CREATED_TS, DELETED_TS, UPDATED_TS, UPDATED_BY_USER, UPDATED_BY_TXN_ID, FROM_LEGALENTITY_IDPK, TO_LEGALENTITY_IDPK, LE_RELATIONSHIP_TYPE_REFKEY, RELATIONSHIP_STATUS_REFKEY, RELATIONSHIP_DESCRIPTION ) VALUES( uuid(), CURRENT_TIMESTAMP(6) , 'D',  OLD.ID_PK,  OLD.VERSION,  OLD.CREATED_TS,  OLD.DELETED_TS,  OLD.UPDATED_TS,  OLD.UPDATED_BY_USER,  OLD.UPDATED_BY_TXN_ID,  OLD.FROM_LEGALENTITY_IDPK,  OLD.TO_LEGALENTITY_IDPK,  OLD.LE_RELATIONSHIP_TYPE_REFKEY,  OLD.RELATIONSHIP_STATUS_REFKEY,  OLD.RELATIONSHIP_DESCRIPTION); END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger yug_owner.D_LE_VEHICLE_ASSOC
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `D_LE_VEHICLE_ASSOC` AFTER DELETE ON `le_vehicle_assoc` FOR EACH ROW BEGIN INSERT INTO AL_LE_VEHICLE_ASSOC ( AUDITLOG_ID_PK, AUDITLOG_CREATED_TS, AUDITLOG_ACTION_CODE ,ID_PK, VERSION, CREATED_TS, DELETED_TS, UPDATED_TS, UPDATED_BY_USER, UPDATED_BY_TXN_ID, LEGALENTITY_IDPK, LE_ROLETYPE_REFKEY, ACCOUNT_IDPK, ROLE_ACTIVATION_DATE, ROLE_DEACTIVATION_DATE, DEACTIVATION_REASON_REFKEY, AGREEMENT_TYPE_REFKEY, AGREEMENT_TYPE_DESCRIPTION, VEHICLE_IDPK ) VALUES( uuid(), CURRENT_TIMESTAMP(6) , 'D',  OLD.ID_PK,  OLD.VERSION,  OLD.CREATED_TS,  OLD.DELETED_TS,  OLD.UPDATED_TS,  OLD.UPDATED_BY_USER,  OLD.UPDATED_BY_TXN_ID,  OLD.LEGALENTITY_IDPK,  OLD.LE_ROLETYPE_REFKEY,  OLD.ACCOUNT_IDPK,  OLD.ROLE_ACTIVATION_DATE,  OLD.ROLE_DEACTIVATION_DATE,  OLD.DEACTIVATION_REASON_REFKEY,  OLD.AGREEMENT_TYPE_REFKEY,  OLD.AGREEMENT_TYPE_DESCRIPTION,  OLD.VEHICLE_IDPK); END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger yug_owner.D_MATCH_CANDIDATE_LE_REGISTRY
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `D_MATCH_CANDIDATE_LE_REGISTRY` AFTER DELETE ON `match_candidate_le_registry` FOR EACH ROW BEGIN INSERT INTO AL_MATCH_CANDIDATE_LE_REGISTRY ( AUDITLOG_ID_PK, AUDITLOG_CREATED_TS, AUDITLOG_ACTION_CODE ,ID_PK, VERSION, CREATED_TS, DELETED_TS, UPDATED_TS, UPDATED_BY_USER, UPDATED_BY_TXN_ID, LEGALENTITY_IDPK, CANDIDATE_LEGALENTITYIDPK, MATCH_PATTERN, MATCH_PROPOSED_ACTION_REFKEY, MATCH_ACTIONSTATUS_REFKEY, MATCH_PERCENTAGE_DESCRIPTION ) VALUES( uuid(), CURRENT_TIMESTAMP(6) , 'D',  OLD.ID_PK,  OLD.VERSION,  OLD.CREATED_TS,  OLD.DELETED_TS,  OLD.UPDATED_TS,  OLD.UPDATED_BY_USER,  OLD.UPDATED_BY_TXN_ID,  OLD.LEGALENTITY_IDPK,  OLD.CANDIDATE_LEGALENTITYIDPK,  OLD.MATCH_PATTERN,  OLD.MATCH_PROPOSED_ACTION_REFKEY,  OLD.MATCH_ACTIONSTATUS_REFKEY,  OLD.MATCH_PERCENTAGE_DESCRIPTION); END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger yug_owner.D_MATCH_MERGED_LE_ASSOC
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `D_MATCH_MERGED_LE_ASSOC` AFTER DELETE ON `match_merged_le_assoc` FOR EACH ROW BEGIN INSERT INTO AL_MATCH_MERGED_LE_ASSOC ( AUDITLOG_ID_PK, AUDITLOG_CREATED_TS, AUDITLOG_ACTION_CODE ,ID_PK, VERSION, CREATED_TS, DELETED_TS, UPDATED_TS, UPDATED_BY_USER, UPDATED_BY_TXN_ID, SURVIVOR_LEGALENTITY_IDPK, MERGED_LEGALENTITY_IDPK, MERGE_REASON_REFKEY, COMMENTS ) VALUES( uuid(), CURRENT_TIMESTAMP(6) , 'D',  OLD.ID_PK,  OLD.VERSION,  OLD.CREATED_TS,  OLD.DELETED_TS,  OLD.UPDATED_TS,  OLD.UPDATED_BY_USER,  OLD.UPDATED_BY_TXN_ID,  OLD.SURVIVOR_LEGALENTITY_IDPK,  OLD.MERGED_LEGALENTITY_IDPK,  OLD.MERGE_REASON_REFKEY,  OLD.COMMENTS); END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger yug_owner.D_MISCELLANEOUS_INFO
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `D_MISCELLANEOUS_INFO` AFTER DELETE ON `miscellaneous_info` FOR EACH ROW BEGIN INSERT INTO AL_MISCELLANEOUS_INFO ( AUDITLOG_ID_PK, AUDITLOG_CREATED_TS, AUDITLOG_ACTION_CODE ,ID_PK, VERSION, CREATED_TS, DELETED_TS, UPDATED_TS, UPDATED_BY_USER, UPDATED_BY_TXN_ID, ENTITY_OBJECT_TYPE_REFKEY, ENTITY_IDPK, NAME1, VALUE1, NAME2, VALUE2, NAME3, VALUE3, NAME4, VALUE4, NAME5, VALUE5, NAME6, VALUE6, NAME7, VALUE7, NAME8, VALUE8, NAME9, VALUE9, NAME10, VALUE10 ) VALUES( uuid(), CURRENT_TIMESTAMP(6) , 'D',  OLD.ID_PK,  OLD.VERSION,  OLD.CREATED_TS,  OLD.DELETED_TS,  OLD.UPDATED_TS,  OLD.UPDATED_BY_USER,  OLD.UPDATED_BY_TXN_ID,  OLD.ENTITY_OBJECT_TYPE_REFKEY,  OLD.ENTITY_IDPK,  OLD.NAME1,  OLD.VALUE1,  OLD.NAME2,  OLD.VALUE2,  OLD.NAME3,  OLD.VALUE3,  OLD.NAME4,  OLD.VALUE4,  OLD.NAME5,  OLD.VALUE5,  OLD.NAME6,  OLD.VALUE6,  OLD.NAME7,  OLD.VALUE7,  OLD.NAME8,  OLD.VALUE8,  OLD.NAME9,  OLD.VALUE9,  OLD.NAME10,  OLD.VALUE10); END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger yug_owner.D_PERSONNAMES
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `D_PERSONNAMES` AFTER DELETE ON `personnames` FOR EACH ROW BEGIN INSERT INTO AL_PERSONNAMES ( AUDITLOG_ID_PK, AUDITLOG_CREATED_TS, AUDITLOG_ACTION_CODE ,ID_PK, VERSION, CREATED_TS, DELETED_TS, UPDATED_TS, UPDATED_BY_USER, UPDATED_BY_TXN_ID, LEGALENTITY_IDPK, PERSONNAME_TYPE_REFKEY, PREFIX_NAME_REFKEY, PREFIX_MISC, NAME_ONE, NAME_TWO, NAME_THREE, NAME_FOUR, LAST_NAME, NICK_NAME, POPULAR_NAME, SUFFIX_NAME_REFKEY, SUFFIX_MISC, NAME_STANDARDISED_FLAG, SOURCE_SYSTEM_REFKEY, PHONETIC_NAME_ONE, PHONETIC_NAME_TWO, PHONETIC_NAME_THREE, PHONETIC_LAST_NAME ) VALUES( uuid(), CURRENT_TIMESTAMP(6) , 'D',  OLD.ID_PK,  OLD.VERSION,  OLD.CREATED_TS,  OLD.DELETED_TS,  OLD.UPDATED_TS,  OLD.UPDATED_BY_USER,  OLD.UPDATED_BY_TXN_ID,  OLD.LEGALENTITY_IDPK,  OLD.PERSONNAME_TYPE_REFKEY,  OLD.PREFIX_NAME_REFKEY,  OLD.PREFIX_MISC,  OLD.NAME_ONE,  OLD.NAME_TWO,  OLD.NAME_THREE,  OLD.NAME_FOUR,  OLD.LAST_NAME,  OLD.NICK_NAME,  OLD.POPULAR_NAME,  OLD.SUFFIX_NAME_REFKEY,  OLD.SUFFIX_MISC,  OLD.NAME_STANDARDISED_FLAG,  OLD.SOURCE_SYSTEM_REFKEY,  OLD.PHONETIC_NAME_ONE,  OLD.PHONETIC_NAME_TWO,  OLD.PHONETIC_NAME_THREE,  OLD.PHONETIC_LAST_NAME); END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger yug_owner.D_PHONE_STANDARDIZED
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `D_PHONE_STANDARDIZED` AFTER DELETE ON `phone_standardized` FOR EACH ROW BEGIN INSERT INTO AL_PHONE_STANDARDIZED ( AUDITLOG_ID_PK, AUDITLOG_CREATED_TS, AUDITLOG_ACTION_CODE ,ID_PK, VERSION, CREATED_TS, DELETED_TS, UPDATED_TS, UPDATED_BY_USER, UPDATED_BY_TXN_ID, ITU_COUNTRY_CALLING_CODE, AREA_CODE, EXCHANGE, PHONE_NUMBER, EXTENSION ) VALUES( uuid(), CURRENT_TIMESTAMP(6) , 'D',  OLD.ID_PK,  OLD.VERSION,  OLD.CREATED_TS,  OLD.DELETED_TS,  OLD.UPDATED_TS,  OLD.UPDATED_BY_USER,  OLD.UPDATED_BY_TXN_ID,  OLD.ITU_COUNTRY_CALLING_CODE,  OLD.AREA_CODE,  OLD.EXCHANGE,  OLD.PHONE_NUMBER,  OLD.EXTENSION); END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger yug_owner.D_PROPERTY
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `D_PROPERTY` AFTER DELETE ON `property` FOR EACH ROW BEGIN INSERT INTO AL_PROPERTY ( AUDITLOG_ID_PK, AUDITLOG_CREATED_TS, AUDITLOG_ACTION_CODE ,ID_PK, VERSION, CREATED_TS, DELETED_TS, UPDATED_TS, UPDATED_BY_USER, UPDATED_BY_TXN_ID, PROPERTY_NAME, ADDRESS_IDPK ) VALUES( uuid(), CURRENT_TIMESTAMP(6) , 'D',  OLD.ID_PK,  OLD.VERSION,  OLD.CREATED_TS,  OLD.DELETED_TS,  OLD.UPDATED_TS,  OLD.UPDATED_BY_USER,  OLD.UPDATED_BY_TXN_ID,  OLD.PROPERTY_NAME,  OLD.ADDRESS_IDPK); END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger yug_owner.D_REF_ACCOUNT_MDM_STATUS
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `D_REF_ACCOUNT_MDM_STATUS` AFTER DELETE ON `ref_account_mdm_status` FOR EACH ROW BEGIN INSERT INTO AL_REF_ACCOUNT_MDM_STATUS ( AUDITLOG_ID_PK, AUDITLOG_CREATED_TS, AUDITLOG_ACTION_CODE ,ID_PK, VERSION, CREATED_TS, DELETED_TS, UPDATED_TS, UPDATED_BY_USER, UPDATED_BY_TXN_ID, CONFIG_LANGUAGE_CODE_KEY, `KEY`, VALUE, DESCRIPTION ) VALUES( uuid(), CURRENT_TIMESTAMP(6) , 'D',  OLD.ID_PK,  OLD.VERSION,  OLD.CREATED_TS,  OLD.DELETED_TS,  OLD.UPDATED_TS,  OLD.UPDATED_BY_USER,  OLD.UPDATED_BY_TXN_ID,  OLD.CONFIG_LANGUAGE_CODE_KEY,  OLD.KEY,  OLD.VALUE,  OLD.DESCRIPTION); END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger yug_owner.D_REF_ACCOUNT_SOURCE_STATUS
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `D_REF_ACCOUNT_SOURCE_STATUS` AFTER DELETE ON `ref_account_source_status` FOR EACH ROW BEGIN INSERT INTO AL_REF_ACCOUNT_SOURCE_STATUS ( AUDITLOG_ID_PK, AUDITLOG_CREATED_TS, AUDITLOG_ACTION_CODE ,ID_PK, VERSION, CREATED_TS, DELETED_TS, UPDATED_TS, UPDATED_BY_USER, UPDATED_BY_TXN_ID, CONFIG_LANGUAGE_CODE_KEY, `KEY`, VALUE, DESCRIPTION ) VALUES( uuid(), CURRENT_TIMESTAMP(6) , 'D',  OLD.ID_PK,  OLD.VERSION,  OLD.CREATED_TS,  OLD.DELETED_TS,  OLD.UPDATED_TS,  OLD.UPDATED_BY_USER,  OLD.UPDATED_BY_TXN_ID,  OLD.CONFIG_LANGUAGE_CODE_KEY,  OLD.KEY,  OLD.VALUE,  OLD.DESCRIPTION); END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger yug_owner.D_REF_ADDRESS_SUBTYPE
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `D_REF_ADDRESS_SUBTYPE` AFTER DELETE ON `ref_address_subtype` FOR EACH ROW BEGIN INSERT INTO AL_REF_ADDRESS_SUBTYPE ( AUDITLOG_ID_PK, AUDITLOG_CREATED_TS, AUDITLOG_ACTION_CODE ,ID_PK, VERSION, CREATED_TS, DELETED_TS, UPDATED_TS, UPDATED_BY_USER, UPDATED_BY_TXN_ID, CONFIG_LANGUAGE_CODE_KEY, `KEY`, VALUE, DESCRIPTION ) VALUES( uuid(), CURRENT_TIMESTAMP(6) , 'D',  OLD.ID_PK,  OLD.VERSION,  OLD.CREATED_TS,  OLD.DELETED_TS,  OLD.UPDATED_TS,  OLD.UPDATED_BY_USER,  OLD.UPDATED_BY_TXN_ID,  OLD.CONFIG_LANGUAGE_CODE_KEY,  OLD.KEY,  OLD.VALUE,  OLD.DESCRIPTION); END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger yug_owner.D_REF_ADDRESS_TYPE
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `D_REF_ADDRESS_TYPE` AFTER DELETE ON `ref_address_type` FOR EACH ROW BEGIN INSERT INTO AL_REF_ADDRESS_TYPE ( AUDITLOG_ID_PK, AUDITLOG_CREATED_TS, AUDITLOG_ACTION_CODE ,ID_PK, VERSION, CREATED_TS, DELETED_TS, UPDATED_TS, UPDATED_BY_USER, UPDATED_BY_TXN_ID, CONFIG_LANGUAGE_CODE_KEY, `KEY`, VALUE, DESCRIPTION ) VALUES( uuid(), CURRENT_TIMESTAMP(6) , 'D',  OLD.ID_PK,  OLD.VERSION,  OLD.CREATED_TS,  OLD.DELETED_TS,  OLD.UPDATED_TS,  OLD.UPDATED_BY_USER,  OLD.UPDATED_BY_TXN_ID,  OLD.CONFIG_LANGUAGE_CODE_KEY,  OLD.KEY,  OLD.VALUE,  OLD.DESCRIPTION); END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger yug_owner.D_REF_AGREEMENT_TYPE
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `D_REF_AGREEMENT_TYPE` AFTER DELETE ON `ref_agreement_type` FOR EACH ROW BEGIN INSERT INTO AL_REF_AGREEMENT_TYPE ( AUDITLOG_ID_PK, AUDITLOG_CREATED_TS, AUDITLOG_ACTION_CODE ,ID_PK, VERSION, CREATED_TS, DELETED_TS, UPDATED_TS, UPDATED_BY_USER, UPDATED_BY_TXN_ID, CONFIG_LANGUAGE_CODE_KEY, `KEY`, VALUE, DESCRIPTION ) VALUES( uuid(), CURRENT_TIMESTAMP(6) , 'D',  OLD.ID_PK,  OLD.VERSION,  OLD.CREATED_TS,  OLD.DELETED_TS,  OLD.UPDATED_TS,  OLD.UPDATED_BY_USER,  OLD.UPDATED_BY_TXN_ID,  OLD.CONFIG_LANGUAGE_CODE_KEY,  OLD.KEY,  OLD.VALUE,  OLD.DESCRIPTION); END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger yug_owner.D_REF_ASSOC_TYPE
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `D_REF_ASSOC_TYPE` AFTER DELETE ON `ref_assoc_type` FOR EACH ROW BEGIN INSERT INTO AL_REF_ASSOC_TYPE ( AUDITLOG_ID_PK, AUDITLOG_CREATED_TS, AUDITLOG_ACTION_CODE ,ID_PK, VERSION, CREATED_TS, DELETED_TS, UPDATED_TS, UPDATED_BY_USER, UPDATED_BY_TXN_ID, CONFIG_LANGUAGE_CODE_KEY, `KEY`, VALUE, DESCRIPTION ) VALUES( uuid(), CURRENT_TIMESTAMP(6) , 'D',  OLD.ID_PK,  OLD.VERSION,  OLD.CREATED_TS,  OLD.DELETED_TS,  OLD.UPDATED_TS,  OLD.UPDATED_BY_USER,  OLD.UPDATED_BY_TXN_ID,  OLD.CONFIG_LANGUAGE_CODE_KEY,  OLD.KEY,  OLD.VALUE,  OLD.DESCRIPTION); END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger yug_owner.D_REF_BATCH_ACTION_STATUS
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `D_REF_BATCH_ACTION_STATUS` AFTER DELETE ON `ref_batch_action_status` FOR EACH ROW BEGIN INSERT INTO AL_REF_BATCH_ACTION_STATUS ( AUDITLOG_ID_PK, AUDITLOG_CREATED_TS, AUDITLOG_ACTION_CODE ,ID_PK, VERSION, CREATED_TS, DELETED_TS, UPDATED_TS, UPDATED_BY_USER, UPDATED_BY_TXN_ID, CONFIG_LANGUAGE_CODE_KEY, `KEY`, VALUE, DESCRIPTION ) VALUES( uuid(), CURRENT_TIMESTAMP(6) , 'D',  OLD.ID_PK,  OLD.VERSION,  OLD.CREATED_TS,  OLD.DELETED_TS,  OLD.UPDATED_TS,  OLD.UPDATED_BY_USER,  OLD.UPDATED_BY_TXN_ID,  OLD.CONFIG_LANGUAGE_CODE_KEY,  OLD.KEY,  OLD.VALUE,  OLD.DESCRIPTION); END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger yug_owner.D_REF_BATCH_PROPOSED_ACTION
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `D_REF_BATCH_PROPOSED_ACTION` AFTER DELETE ON `ref_batch_proposed_action` FOR EACH ROW BEGIN INSERT INTO AL_REF_BATCH_PROPOSED_ACTION ( AUDITLOG_ID_PK, AUDITLOG_CREATED_TS, AUDITLOG_ACTION_CODE ,ID_PK, VERSION, CREATED_TS, DELETED_TS, UPDATED_TS, UPDATED_BY_USER, UPDATED_BY_TXN_ID, CONFIG_LANGUAGE_CODE_KEY, `KEY`, VALUE, DESCRIPTION ) VALUES( uuid(), CURRENT_TIMESTAMP(6) , 'D',  OLD.ID_PK,  OLD.VERSION,  OLD.CREATED_TS,  OLD.DELETED_TS,  OLD.UPDATED_TS,  OLD.UPDATED_BY_USER,  OLD.UPDATED_BY_TXN_ID,  OLD.CONFIG_LANGUAGE_CODE_KEY,  OLD.KEY,  OLD.VALUE,  OLD.DESCRIPTION); END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger yug_owner.D_REF_BILLING_MODE_TYPE
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `D_REF_BILLING_MODE_TYPE` AFTER DELETE ON `ref_billing_mode_type` FOR EACH ROW BEGIN INSERT INTO AL_REF_BILLING_MODE_TYPE ( AUDITLOG_ID_PK, AUDITLOG_CREATED_TS, AUDITLOG_ACTION_CODE ,ID_PK, VERSION, CREATED_TS, DELETED_TS, UPDATED_TS, UPDATED_BY_USER, UPDATED_BY_TXN_ID, CONFIG_LANGUAGE_CODE_KEY, `KEY`, VALUE, DESCRIPTION ) VALUES( uuid(), CURRENT_TIMESTAMP(6) , 'D',  OLD.ID_PK,  OLD.VERSION,  OLD.CREATED_TS,  OLD.DELETED_TS,  OLD.UPDATED_TS,  OLD.UPDATED_BY_USER,  OLD.UPDATED_BY_TXN_ID,  OLD.CONFIG_LANGUAGE_CODE_KEY,  OLD.KEY,  OLD.VALUE,  OLD.DESCRIPTION); END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger yug_owner.D_REF_BRANCH_CODE
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `D_REF_BRANCH_CODE` AFTER DELETE ON `ref_branch_code` FOR EACH ROW BEGIN INSERT INTO AL_REF_BRANCH_CODE ( AUDITLOG_ID_PK, AUDITLOG_CREATED_TS, AUDITLOG_ACTION_CODE ,ID_PK, VERSION, CREATED_TS, DELETED_TS, UPDATED_TS, UPDATED_BY_USER, UPDATED_BY_TXN_ID, CONFIG_LANGUAGE_CODE_KEY, `KEY`, VALUE, DESCRIPTION ) VALUES( uuid(), CURRENT_TIMESTAMP(6) , 'D',  OLD.ID_PK,  OLD.VERSION,  OLD.CREATED_TS,  OLD.DELETED_TS,  OLD.UPDATED_TS,  OLD.UPDATED_BY_USER,  OLD.UPDATED_BY_TXN_ID,  OLD.CONFIG_LANGUAGE_CODE_KEY,  OLD.KEY,  OLD.VALUE,  OLD.DESCRIPTION); END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger yug_owner.D_REF_CLASSIFICATION_CODE
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `D_REF_CLASSIFICATION_CODE` AFTER DELETE ON `ref_classification_code` FOR EACH ROW BEGIN INSERT INTO AL_REF_CLASSIFICATION_CODE ( AUDITLOG_ID_PK, AUDITLOG_CREATED_TS, AUDITLOG_ACTION_CODE ,ID_PK, VERSION, CREATED_TS, DELETED_TS, UPDATED_TS, UPDATED_BY_USER, UPDATED_BY_TXN_ID, CONFIG_LANGUAGE_CODE_KEY, `KEY`, VALUE, DESCRIPTION ) VALUES( uuid(), CURRENT_TIMESTAMP(6) , 'D',  OLD.ID_PK,  OLD.VERSION,  OLD.CREATED_TS,  OLD.DELETED_TS,  OLD.UPDATED_TS,  OLD.UPDATED_BY_USER,  OLD.UPDATED_BY_TXN_ID,  OLD.CONFIG_LANGUAGE_CODE_KEY,  OLD.KEY,  OLD.VALUE,  OLD.DESCRIPTION); END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger yug_owner.D_REF_CORPORATION_NAME_TYPE
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `D_REF_CORPORATION_NAME_TYPE` AFTER DELETE ON `ref_corporation_name_type` FOR EACH ROW BEGIN INSERT INTO AL_REF_CORPORATION_NAME_TYPE ( AUDITLOG_ID_PK, AUDITLOG_CREATED_TS, AUDITLOG_ACTION_CODE ,ID_PK, VERSION, CREATED_TS, DELETED_TS, UPDATED_TS, UPDATED_BY_USER, UPDATED_BY_TXN_ID, CONFIG_LANGUAGE_CODE_KEY, `KEY`, VALUE, DESCRIPTION ) VALUES( uuid(), CURRENT_TIMESTAMP(6) , 'D',  OLD.ID_PK,  OLD.VERSION,  OLD.CREATED_TS,  OLD.DELETED_TS,  OLD.UPDATED_TS,  OLD.UPDATED_BY_USER,  OLD.UPDATED_BY_TXN_ID,  OLD.CONFIG_LANGUAGE_CODE_KEY,  OLD.KEY,  OLD.VALUE,  OLD.DESCRIPTION); END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger yug_owner.D_REF_CORPORATION_TYPE
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `D_REF_CORPORATION_TYPE` AFTER DELETE ON `ref_corporation_type` FOR EACH ROW BEGIN INSERT INTO AL_REF_CORPORATION_TYPE ( AUDITLOG_ID_PK, AUDITLOG_CREATED_TS, AUDITLOG_ACTION_CODE ,ID_PK, VERSION, CREATED_TS, DELETED_TS, UPDATED_TS, UPDATED_BY_USER, UPDATED_BY_TXN_ID, CONFIG_LANGUAGE_CODE_KEY, `KEY`, VALUE, DESCRIPTION ) VALUES( uuid(), CURRENT_TIMESTAMP(6) , 'D',  OLD.ID_PK,  OLD.VERSION,  OLD.CREATED_TS,  OLD.DELETED_TS,  OLD.UPDATED_TS,  OLD.UPDATED_BY_USER,  OLD.UPDATED_BY_TXN_ID,  OLD.CONFIG_LANGUAGE_CODE_KEY,  OLD.KEY,  OLD.VALUE,  OLD.DESCRIPTION); END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger yug_owner.D_REF_COUNTRY_ISO
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `D_REF_COUNTRY_ISO` AFTER DELETE ON `ref_country_iso` FOR EACH ROW BEGIN INSERT INTO AL_REF_COUNTRY_ISO ( AUDITLOG_ID_PK, AUDITLOG_CREATED_TS, AUDITLOG_ACTION_CODE ,ID_PK, VERSION, CREATED_TS, DELETED_TS, UPDATED_TS, UPDATED_BY_USER, UPDATED_BY_TXN_ID, CONFIG_LANGUAGE_CODE_KEY, `KEY`, VALUE, DESCRIPTION ) VALUES( uuid(), CURRENT_TIMESTAMP(6) , 'D',  OLD.ID_PK,  OLD.VERSION,  OLD.CREATED_TS,  OLD.DELETED_TS,  OLD.UPDATED_TS,  OLD.UPDATED_BY_USER,  OLD.UPDATED_BY_TXN_ID,  OLD.CONFIG_LANGUAGE_CODE_KEY,  OLD.KEY,  OLD.VALUE,  OLD.DESCRIPTION); END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger yug_owner.D_REF_CURRENCY
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `D_REF_CURRENCY` AFTER DELETE ON `ref_currency` FOR EACH ROW BEGIN INSERT INTO AL_REF_CURRENCY ( AUDITLOG_ID_PK, AUDITLOG_CREATED_TS, AUDITLOG_ACTION_CODE ,ID_PK, VERSION, CREATED_TS, DELETED_TS, UPDATED_TS, UPDATED_BY_USER, UPDATED_BY_TXN_ID, CONFIG_LANGUAGE_CODE_KEY, `KEY`, VALUE, DESCRIPTION ) VALUES( uuid(), CURRENT_TIMESTAMP(6) , 'D',  OLD.ID_PK,  OLD.VERSION,  OLD.CREATED_TS,  OLD.DELETED_TS,  OLD.UPDATED_TS,  OLD.UPDATED_BY_USER,  OLD.UPDATED_BY_TXN_ID,  OLD.CONFIG_LANGUAGE_CODE_KEY,  OLD.KEY,  OLD.VALUE,  OLD.DESCRIPTION); END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger yug_owner.D_REF_DEACTIVATION_REASON
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `D_REF_DEACTIVATION_REASON` AFTER DELETE ON `ref_deactivation_reason` FOR EACH ROW BEGIN INSERT INTO AL_REF_DEACTIVATION_REASON ( AUDITLOG_ID_PK, AUDITLOG_CREATED_TS, AUDITLOG_ACTION_CODE ,ID_PK, VERSION, CREATED_TS, DELETED_TS, UPDATED_TS, UPDATED_BY_USER, UPDATED_BY_TXN_ID, CONFIG_LANGUAGE_CODE_KEY, `KEY`, VALUE, DESCRIPTION ) VALUES( uuid(), CURRENT_TIMESTAMP(6) , 'D',  OLD.ID_PK,  OLD.VERSION,  OLD.CREATED_TS,  OLD.DELETED_TS,  OLD.UPDATED_TS,  OLD.UPDATED_BY_USER,  OLD.UPDATED_BY_TXN_ID,  OLD.CONFIG_LANGUAGE_CODE_KEY,  OLD.KEY,  OLD.VALUE,  OLD.DESCRIPTION); END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger yug_owner.D_REF_ENTITY_OBJECT_TYPE
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `D_REF_ENTITY_OBJECT_TYPE` AFTER DELETE ON `ref_entity_object_type` FOR EACH ROW BEGIN INSERT INTO AL_REF_ENTITY_OBJECT_TYPE ( AUDITLOG_ID_PK, AUDITLOG_CREATED_TS, AUDITLOG_ACTION_CODE ,ID_PK, VERSION, CREATED_TS, DELETED_TS, UPDATED_TS, UPDATED_BY_USER, UPDATED_BY_TXN_ID, CONFIG_LANGUAGE_CODE_KEY, `KEY`, VALUE, DESCRIPTION ) VALUES( uuid(), CURRENT_TIMESTAMP(6) , 'D',  OLD.ID_PK,  OLD.VERSION,  OLD.CREATED_TS,  OLD.DELETED_TS,  OLD.UPDATED_TS,  OLD.UPDATED_BY_USER,  OLD.UPDATED_BY_TXN_ID,  OLD.CONFIG_LANGUAGE_CODE_KEY,  OLD.KEY,  OLD.VALUE,  OLD.DESCRIPTION); END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger yug_owner.D_REF_GENDER
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `D_REF_GENDER` AFTER DELETE ON `ref_gender` FOR EACH ROW BEGIN INSERT INTO AL_REF_GENDER ( AUDITLOG_ID_PK, AUDITLOG_CREATED_TS, AUDITLOG_ACTION_CODE ,ID_PK, VERSION, CREATED_TS, DELETED_TS, UPDATED_TS, UPDATED_BY_USER, UPDATED_BY_TXN_ID, CONFIG_LANGUAGE_CODE_KEY, `KEY`, VALUE, DESCRIPTION ) VALUES( uuid(), CURRENT_TIMESTAMP(6) , 'D',  OLD.ID_PK,  OLD.VERSION,  OLD.CREATED_TS,  OLD.DELETED_TS,  OLD.UPDATED_TS,  OLD.UPDATED_BY_USER,  OLD.UPDATED_BY_TXN_ID,  OLD.CONFIG_LANGUAGE_CODE_KEY,  OLD.KEY,  OLD.VALUE,  OLD.DESCRIPTION); END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger yug_owner.D_REF_GROUP_SUBTYPE
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `D_REF_GROUP_SUBTYPE` AFTER DELETE ON `ref_group_subtype` FOR EACH ROW BEGIN INSERT INTO AL_REF_GROUP_SUBTYPE ( AUDITLOG_ID_PK, AUDITLOG_CREATED_TS, AUDITLOG_ACTION_CODE ,ID_PK, VERSION, CREATED_TS, DELETED_TS, UPDATED_TS, UPDATED_BY_USER, UPDATED_BY_TXN_ID, CONFIG_LANGUAGE_CODE_KEY, `KEY`, VALUE, DESCRIPTION ) VALUES( uuid(), CURRENT_TIMESTAMP(6) , 'D',  OLD.ID_PK,  OLD.VERSION,  OLD.CREATED_TS,  OLD.DELETED_TS,  OLD.UPDATED_TS,  OLD.UPDATED_BY_USER,  OLD.UPDATED_BY_TXN_ID,  OLD.CONFIG_LANGUAGE_CODE_KEY,  OLD.KEY,  OLD.VALUE,  OLD.DESCRIPTION); END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger yug_owner.D_REF_GROUP_TYPE
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `D_REF_GROUP_TYPE` AFTER DELETE ON `ref_group_type` FOR EACH ROW BEGIN INSERT INTO AL_REF_GROUP_TYPE ( AUDITLOG_ID_PK, AUDITLOG_CREATED_TS, AUDITLOG_ACTION_CODE ,ID_PK, VERSION, CREATED_TS, DELETED_TS, UPDATED_TS, UPDATED_BY_USER, UPDATED_BY_TXN_ID, CONFIG_LANGUAGE_CODE_KEY, `KEY`, VALUE, DESCRIPTION ) VALUES( uuid(), CURRENT_TIMESTAMP(6) , 'D',  OLD.ID_PK,  OLD.VERSION,  OLD.CREATED_TS,  OLD.DELETED_TS,  OLD.UPDATED_TS,  OLD.UPDATED_BY_USER,  OLD.UPDATED_BY_TXN_ID,  OLD.CONFIG_LANGUAGE_CODE_KEY,  OLD.KEY,  OLD.VALUE,  OLD.DESCRIPTION); END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger yug_owner.D_REF_HIGHEST_EDU_QUAL
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `D_REF_HIGHEST_EDU_QUAL` AFTER DELETE ON `ref_highest_edu_qual` FOR EACH ROW BEGIN INSERT INTO AL_REF_HIGHEST_EDU_QUAL ( AUDITLOG_ID_PK, AUDITLOG_CREATED_TS, AUDITLOG_ACTION_CODE ,ID_PK, VERSION, CREATED_TS, DELETED_TS, UPDATED_TS, UPDATED_BY_USER, UPDATED_BY_TXN_ID, CONFIG_LANGUAGE_CODE_KEY, `KEY`, VALUE, DESCRIPTION ) VALUES( uuid(), CURRENT_TIMESTAMP(6) , 'D',  OLD.ID_PK,  OLD.VERSION,  OLD.CREATED_TS,  OLD.DELETED_TS,  OLD.UPDATED_TS,  OLD.UPDATED_BY_USER,  OLD.UPDATED_BY_TXN_ID,  OLD.CONFIG_LANGUAGE_CODE_KEY,  OLD.KEY,  OLD.VALUE,  OLD.DESCRIPTION); END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger yug_owner.D_REF_IDENTIFICATION_TYPE
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `D_REF_IDENTIFICATION_TYPE` AFTER DELETE ON `ref_identification_type` FOR EACH ROW BEGIN INSERT INTO AL_REF_IDENTIFICATION_TYPE ( AUDITLOG_ID_PK, AUDITLOG_CREATED_TS, AUDITLOG_ACTION_CODE ,ID_PK, VERSION, CREATED_TS, DELETED_TS, UPDATED_TS, UPDATED_BY_USER, UPDATED_BY_TXN_ID, CONFIG_LANGUAGE_CODE_KEY, `KEY`, VALUE, DESCRIPTION ) VALUES( uuid(), CURRENT_TIMESTAMP(6) , 'D',  OLD.ID_PK,  OLD.VERSION,  OLD.CREATED_TS,  OLD.DELETED_TS,  OLD.UPDATED_TS,  OLD.UPDATED_BY_USER,  OLD.UPDATED_BY_TXN_ID,  OLD.CONFIG_LANGUAGE_CODE_KEY,  OLD.KEY,  OLD.VALUE,  OLD.DESCRIPTION); END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger yug_owner.D_REF_IMPORTANCE_TYPE
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `D_REF_IMPORTANCE_TYPE` AFTER DELETE ON `ref_importance_type` FOR EACH ROW BEGIN INSERT INTO AL_REF_IMPORTANCE_TYPE ( AUDITLOG_ID_PK, AUDITLOG_CREATED_TS, AUDITLOG_ACTION_CODE ,ID_PK, VERSION, CREATED_TS, DELETED_TS, UPDATED_TS, UPDATED_BY_USER, UPDATED_BY_TXN_ID, CONFIG_LANGUAGE_CODE_KEY, `KEY`, VALUE, DESCRIPTION ) VALUES( uuid(), CURRENT_TIMESTAMP(6) , 'D',  OLD.ID_PK,  OLD.VERSION,  OLD.CREATED_TS,  OLD.DELETED_TS,  OLD.UPDATED_TS,  OLD.UPDATED_BY_USER,  OLD.UPDATED_BY_TXN_ID,  OLD.CONFIG_LANGUAGE_CODE_KEY,  OLD.KEY,  OLD.VALUE,  OLD.DESCRIPTION); END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger yug_owner.D_REF_INACTIVATION_REASON
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `D_REF_INACTIVATION_REASON` AFTER DELETE ON `ref_inactivation_reason` FOR EACH ROW BEGIN INSERT INTO AL_REF_INACTIVATION_REASON ( AUDITLOG_ID_PK, AUDITLOG_CREATED_TS, AUDITLOG_ACTION_CODE ,ID_PK, VERSION, CREATED_TS, DELETED_TS, UPDATED_TS, UPDATED_BY_USER, UPDATED_BY_TXN_ID, CONFIG_LANGUAGE_CODE_KEY, `KEY`, VALUE, DESCRIPTION ) VALUES( uuid(), CURRENT_TIMESTAMP(6) , 'D',  OLD.ID_PK,  OLD.VERSION,  OLD.CREATED_TS,  OLD.DELETED_TS,  OLD.UPDATED_TS,  OLD.UPDATED_BY_USER,  OLD.UPDATED_BY_TXN_ID,  OLD.CONFIG_LANGUAGE_CODE_KEY,  OLD.KEY,  OLD.VALUE,  OLD.DESCRIPTION); END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger yug_owner.D_REF_INDUSTRY_CODE
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `D_REF_INDUSTRY_CODE` AFTER DELETE ON `ref_industry_code` FOR EACH ROW BEGIN INSERT INTO AL_REF_INDUSTRY_CODE ( AUDITLOG_ID_PK, AUDITLOG_CREATED_TS, AUDITLOG_ACTION_CODE ,ID_PK, VERSION, CREATED_TS, DELETED_TS, UPDATED_TS, UPDATED_BY_USER, UPDATED_BY_TXN_ID, CONFIG_LANGUAGE_CODE_KEY, `KEY`, VALUE, DESCRIPTION ) VALUES( uuid(), CURRENT_TIMESTAMP(6) , 'D',  OLD.ID_PK,  OLD.VERSION,  OLD.CREATED_TS,  OLD.DELETED_TS,  OLD.UPDATED_TS,  OLD.UPDATED_BY_USER,  OLD.UPDATED_BY_TXN_ID,  OLD.CONFIG_LANGUAGE_CODE_KEY,  OLD.KEY,  OLD.VALUE,  OLD.DESCRIPTION); END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger yug_owner.D_REF_LANGUAGE_CODE
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `D_REF_LANGUAGE_CODE` AFTER DELETE ON `ref_language_code` FOR EACH ROW BEGIN INSERT INTO AL_REF_LANGUAGE_CODE ( AUDITLOG_ID_PK, AUDITLOG_CREATED_TS, AUDITLOG_ACTION_CODE ,ID_PK, VERSION, CREATED_TS, DELETED_TS, UPDATED_TS, UPDATED_BY_USER, UPDATED_BY_TXN_ID, CONFIG_LANGUAGE_CODE_KEY, `KEY`, VALUE, DESCRIPTION ) VALUES( uuid(), CURRENT_TIMESTAMP(6) , 'D',  OLD.ID_PK,  OLD.VERSION,  OLD.CREATED_TS,  OLD.DELETED_TS,  OLD.UPDATED_TS,  OLD.UPDATED_BY_USER,  OLD.UPDATED_BY_TXN_ID,  OLD.CONFIG_LANGUAGE_CODE_KEY,  OLD.KEY,  OLD.VALUE,  OLD.DESCRIPTION); END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger yug_owner.D_REF_LE_RATING
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `D_REF_LE_RATING` AFTER DELETE ON `ref_le_rating` FOR EACH ROW BEGIN INSERT INTO AL_REF_LE_RATING ( AUDITLOG_ID_PK, AUDITLOG_CREATED_TS, AUDITLOG_ACTION_CODE ,ID_PK, VERSION, CREATED_TS, DELETED_TS, UPDATED_TS, UPDATED_BY_USER, UPDATED_BY_TXN_ID, CONFIG_LANGUAGE_CODE_KEY, `KEY`, VALUE, DESCRIPTION ) VALUES( uuid(), CURRENT_TIMESTAMP(6) , 'D',  OLD.ID_PK,  OLD.VERSION,  OLD.CREATED_TS,  OLD.DELETED_TS,  OLD.UPDATED_TS,  OLD.UPDATED_BY_USER,  OLD.UPDATED_BY_TXN_ID,  OLD.CONFIG_LANGUAGE_CODE_KEY,  OLD.KEY,  OLD.VALUE,  OLD.DESCRIPTION); END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger yug_owner.D_REF_LE_RELATIONSHIP_TYPE
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `D_REF_LE_RELATIONSHIP_TYPE` AFTER DELETE ON `ref_le_relationship_type` FOR EACH ROW BEGIN INSERT INTO AL_REF_LE_RELATIONSHIP_TYPE ( AUDITLOG_ID_PK, AUDITLOG_CREATED_TS, AUDITLOG_ACTION_CODE ,ID_PK, VERSION, CREATED_TS, DELETED_TS, UPDATED_TS, UPDATED_BY_USER, UPDATED_BY_TXN_ID, CONFIG_LANGUAGE_CODE_KEY, `KEY`, VALUE, DESCRIPTION ) VALUES( uuid(), CURRENT_TIMESTAMP(6) , 'D',  OLD.ID_PK,  OLD.VERSION,  OLD.CREATED_TS,  OLD.DELETED_TS,  OLD.UPDATED_TS,  OLD.UPDATED_BY_USER,  OLD.UPDATED_BY_TXN_ID,  OLD.CONFIG_LANGUAGE_CODE_KEY,  OLD.KEY,  OLD.VALUE,  OLD.DESCRIPTION); END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger yug_owner.D_REF_LE_ROLETYPE
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `D_REF_LE_ROLETYPE` AFTER DELETE ON `ref_le_roletype` FOR EACH ROW BEGIN INSERT INTO AL_REF_LE_ROLETYPE ( AUDITLOG_ID_PK, AUDITLOG_CREATED_TS, AUDITLOG_ACTION_CODE ,ID_PK, VERSION, CREATED_TS, DELETED_TS, UPDATED_TS, UPDATED_BY_USER, UPDATED_BY_TXN_ID, CONFIG_LANGUAGE_CODE_KEY, `KEY`, VALUE, DESCRIPTION ) VALUES( uuid(), CURRENT_TIMESTAMP(6) , 'D',  OLD.ID_PK,  OLD.VERSION,  OLD.CREATED_TS,  OLD.DELETED_TS,  OLD.UPDATED_TS,  OLD.UPDATED_BY_USER,  OLD.UPDATED_BY_TXN_ID,  OLD.CONFIG_LANGUAGE_CODE_KEY,  OLD.KEY,  OLD.VALUE,  OLD.DESCRIPTION); END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger yug_owner.D_REF_LOBTYPE
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `D_REF_LOBTYPE` AFTER DELETE ON `ref_lobtype` FOR EACH ROW BEGIN INSERT INTO AL_REF_LOBTYPE ( AUDITLOG_ID_PK, AUDITLOG_CREATED_TS, AUDITLOG_ACTION_CODE ,ID_PK, VERSION, CREATED_TS, DELETED_TS, UPDATED_TS, UPDATED_BY_USER, UPDATED_BY_TXN_ID, CONFIG_LANGUAGE_CODE_KEY, `KEY`, VALUE, DESCRIPTION ) VALUES( uuid(), CURRENT_TIMESTAMP(6) , 'D',  OLD.ID_PK,  OLD.VERSION,  OLD.CREATED_TS,  OLD.DELETED_TS,  OLD.UPDATED_TS,  OLD.UPDATED_BY_USER,  OLD.UPDATED_BY_TXN_ID,  OLD.CONFIG_LANGUAGE_CODE_KEY,  OLD.KEY,  OLD.VALUE,  OLD.DESCRIPTION); END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger yug_owner.D_REF_MATCH_ACTIONSTATUS
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `D_REF_MATCH_ACTIONSTATUS` AFTER DELETE ON `ref_match_actionstatus` FOR EACH ROW BEGIN INSERT INTO AL_REF_MATCH_ACTIONSTATUS ( AUDITLOG_ID_PK, AUDITLOG_CREATED_TS, AUDITLOG_ACTION_CODE ,ID_PK, VERSION, CREATED_TS, DELETED_TS, UPDATED_TS, UPDATED_BY_USER, UPDATED_BY_TXN_ID, CONFIG_LANGUAGE_CODE_KEY, `KEY`, VALUE, DESCRIPTION ) VALUES( uuid(), CURRENT_TIMESTAMP(6) , 'D',  OLD.ID_PK,  OLD.VERSION,  OLD.CREATED_TS,  OLD.DELETED_TS,  OLD.UPDATED_TS,  OLD.UPDATED_BY_USER,  OLD.UPDATED_BY_TXN_ID,  OLD.CONFIG_LANGUAGE_CODE_KEY,  OLD.KEY,  OLD.VALUE,  OLD.DESCRIPTION); END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger yug_owner.D_REF_MATCH_PROPOSED_ACTION
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `D_REF_MATCH_PROPOSED_ACTION` AFTER DELETE ON `ref_match_proposed_action` FOR EACH ROW BEGIN INSERT INTO AL_REF_MATCH_PROPOSED_ACTION ( AUDITLOG_ID_PK, AUDITLOG_CREATED_TS, AUDITLOG_ACTION_CODE ,ID_PK, VERSION, CREATED_TS, DELETED_TS, UPDATED_TS, UPDATED_BY_USER, UPDATED_BY_TXN_ID, CONFIG_LANGUAGE_CODE_KEY, `KEY`, VALUE, DESCRIPTION ) VALUES( uuid(), CURRENT_TIMESTAMP(6) , 'D',  OLD.ID_PK,  OLD.VERSION,  OLD.CREATED_TS,  OLD.DELETED_TS,  OLD.UPDATED_TS,  OLD.UPDATED_BY_USER,  OLD.UPDATED_BY_TXN_ID,  OLD.CONFIG_LANGUAGE_CODE_KEY,  OLD.KEY,  OLD.VALUE,  OLD.DESCRIPTION); END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger yug_owner.D_REF_MATCH_RESULT
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `D_REF_MATCH_RESULT` AFTER DELETE ON `ref_match_result` FOR EACH ROW BEGIN INSERT INTO AL_REF_MATCH_RESULT ( AUDITLOG_ID_PK, AUDITLOG_CREATED_TS, AUDITLOG_ACTION_CODE ,ID_PK, VERSION, CREATED_TS, DELETED_TS, UPDATED_TS, UPDATED_BY_USER, UPDATED_BY_TXN_ID, CONFIG_LANGUAGE_CODE_KEY, `KEY`, VALUE, DESCRIPTION ) VALUES( uuid(), CURRENT_TIMESTAMP(6) , 'D',  OLD.ID_PK,  OLD.VERSION,  OLD.CREATED_TS,  OLD.DELETED_TS,  OLD.UPDATED_TS,  OLD.UPDATED_BY_USER,  OLD.UPDATED_BY_TXN_ID,  OLD.CONFIG_LANGUAGE_CODE_KEY,  OLD.KEY,  OLD.VALUE,  OLD.DESCRIPTION); END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger yug_owner.D_REF_MATCH_SCORE
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `D_REF_MATCH_SCORE` AFTER DELETE ON `ref_match_score` FOR EACH ROW BEGIN INSERT INTO AL_REF_MATCH_SCORE ( AUDITLOG_ID_PK, AUDITLOG_CREATED_TS, AUDITLOG_ACTION_CODE ,ID_PK, VERSION, CREATED_TS, DELETED_TS, UPDATED_TS, UPDATED_BY_USER, UPDATED_BY_TXN_ID, MATCH_ENTITY_OBJECT_NAME, MATCH_ATTR_PATTERN, MATCH_RESULT_REFKEY, MATCH_PROPOSED_ACTION_REFKEY, MATCH_ATTR_PATTERN_DESCR ) VALUES( uuid(), CURRENT_TIMESTAMP(6) , 'D',  OLD.ID_PK,  OLD.VERSION,  OLD.CREATED_TS,  OLD.DELETED_TS,  OLD.UPDATED_TS,  OLD.UPDATED_BY_USER,  OLD.UPDATED_BY_TXN_ID,  OLD.MATCH_ENTITY_OBJECT_NAME,  OLD.MATCH_ATTR_PATTERN,  OLD.MATCH_RESULT_REFKEY,  OLD.MATCH_PROPOSED_ACTION_REFKEY,  OLD.MATCH_ATTR_PATTERN_DESCR); END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger yug_owner.D_REF_MATCH_THRESHOLD
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `D_REF_MATCH_THRESHOLD` AFTER DELETE ON `ref_match_threshold` FOR EACH ROW BEGIN INSERT INTO AL_REF_MATCH_THRESHOLD ( AUDITLOG_ID_PK, AUDITLOG_CREATED_TS, AUDITLOG_ACTION_CODE ,ID_PK, VERSION, CREATED_TS, DELETED_TS, UPDATED_TS, UPDATED_BY_USER, UPDATED_BY_TXN_ID, ATTR_BLOCK_NAME, MATCH_THRESHOLD, DECAY_THRESHOLD_IN_DAYS, DECAY_PERCENTAGE, MAX_DECAY_PERCENTAGE ) VALUES( uuid(), CURRENT_TIMESTAMP(6) , 'D',  OLD.ID_PK,  OLD.VERSION,  OLD.CREATED_TS,  OLD.DELETED_TS,  OLD.UPDATED_TS,  OLD.UPDATED_BY_USER,  OLD.UPDATED_BY_TXN_ID,  OLD.ATTR_BLOCK_NAME,  OLD.MATCH_THRESHOLD,  OLD.DECAY_THRESHOLD_IN_DAYS,  OLD.DECAY_PERCENTAGE,  OLD.MAX_DECAY_PERCENTAGE); END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger yug_owner.D_REF_MERGE_REASON
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `D_REF_MERGE_REASON` AFTER DELETE ON `ref_merge_reason` FOR EACH ROW BEGIN INSERT INTO AL_REF_MERGE_REASON ( AUDITLOG_ID_PK, AUDITLOG_CREATED_TS, AUDITLOG_ACTION_CODE ,ID_PK, VERSION, CREATED_TS, DELETED_TS, UPDATED_TS, UPDATED_BY_USER, UPDATED_BY_TXN_ID, CONFIG_LANGUAGE_CODE_KEY, `KEY`, VALUE, DESCRIPTION ) VALUES( uuid(), CURRENT_TIMESTAMP(6) , 'D',  OLD.ID_PK,  OLD.VERSION,  OLD.CREATED_TS,  OLD.DELETED_TS,  OLD.UPDATED_TS,  OLD.UPDATED_BY_USER,  OLD.UPDATED_BY_TXN_ID,  OLD.CONFIG_LANGUAGE_CODE_KEY,  OLD.KEY,  OLD.VALUE,  OLD.DESCRIPTION); END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger yug_owner.D_REF_PERSONNAME_TYPE
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `D_REF_PERSONNAME_TYPE` AFTER DELETE ON `ref_personname_type` FOR EACH ROW BEGIN INSERT INTO AL_REF_PERSONNAME_TYPE ( AUDITLOG_ID_PK, AUDITLOG_CREATED_TS, AUDITLOG_ACTION_CODE ,ID_PK, VERSION, CREATED_TS, DELETED_TS, UPDATED_TS, UPDATED_BY_USER, UPDATED_BY_TXN_ID, CONFIG_LANGUAGE_CODE_KEY, `KEY`, VALUE, DESCRIPTION ) VALUES( uuid(), CURRENT_TIMESTAMP(6) , 'D',  OLD.ID_PK,  OLD.VERSION,  OLD.CREATED_TS,  OLD.DELETED_TS,  OLD.UPDATED_TS,  OLD.UPDATED_BY_USER,  OLD.UPDATED_BY_TXN_ID,  OLD.CONFIG_LANGUAGE_CODE_KEY,  OLD.KEY,  OLD.VALUE,  OLD.DESCRIPTION); END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger yug_owner.D_REF_PERSON_TYPE
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `D_REF_PERSON_TYPE` AFTER DELETE ON `ref_person_type` FOR EACH ROW BEGIN INSERT INTO AL_REF_PERSON_TYPE ( AUDITLOG_ID_PK, AUDITLOG_CREATED_TS, AUDITLOG_ACTION_CODE ,ID_PK, VERSION, CREATED_TS, DELETED_TS, UPDATED_TS, UPDATED_BY_USER, UPDATED_BY_TXN_ID, CONFIG_LANGUAGE_CODE_KEY, `KEY`, VALUE, DESCRIPTION ) VALUES( uuid(), CURRENT_TIMESTAMP(6) , 'D',  OLD.ID_PK,  OLD.VERSION,  OLD.CREATED_TS,  OLD.DELETED_TS,  OLD.UPDATED_TS,  OLD.UPDATED_BY_USER,  OLD.UPDATED_BY_TXN_ID,  OLD.CONFIG_LANGUAGE_CODE_KEY,  OLD.KEY,  OLD.VALUE,  OLD.DESCRIPTION); END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger yug_owner.D_REF_PHONE_SUBTYPE
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `D_REF_PHONE_SUBTYPE` AFTER DELETE ON `ref_phone_subtype` FOR EACH ROW BEGIN INSERT INTO AL_REF_PHONE_SUBTYPE ( AUDITLOG_ID_PK, AUDITLOG_CREATED_TS, AUDITLOG_ACTION_CODE ,ID_PK, VERSION, CREATED_TS, DELETED_TS, UPDATED_TS, UPDATED_BY_USER, UPDATED_BY_TXN_ID, CONFIG_LANGUAGE_CODE_KEY, `KEY`, VALUE, DESCRIPTION ) VALUES( uuid(), CURRENT_TIMESTAMP(6) , 'D',  OLD.ID_PK,  OLD.VERSION,  OLD.CREATED_TS,  OLD.DELETED_TS,  OLD.UPDATED_TS,  OLD.UPDATED_BY_USER,  OLD.UPDATED_BY_TXN_ID,  OLD.CONFIG_LANGUAGE_CODE_KEY,  OLD.KEY,  OLD.VALUE,  OLD.DESCRIPTION); END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger yug_owner.D_REF_PHONE_TYPE
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `D_REF_PHONE_TYPE` AFTER DELETE ON `ref_phone_type` FOR EACH ROW BEGIN INSERT INTO AL_REF_PHONE_TYPE ( AUDITLOG_ID_PK, AUDITLOG_CREATED_TS, AUDITLOG_ACTION_CODE ,ID_PK, VERSION, CREATED_TS, DELETED_TS, UPDATED_TS, UPDATED_BY_USER, UPDATED_BY_TXN_ID, CONFIG_LANGUAGE_CODE_KEY, `KEY`, VALUE, DESCRIPTION ) VALUES( uuid(), CURRENT_TIMESTAMP(6) , 'D',  OLD.ID_PK,  OLD.VERSION,  OLD.CREATED_TS,  OLD.DELETED_TS,  OLD.UPDATED_TS,  OLD.UPDATED_BY_USER,  OLD.UPDATED_BY_TXN_ID,  OLD.CONFIG_LANGUAGE_CODE_KEY,  OLD.KEY,  OLD.VALUE,  OLD.DESCRIPTION); END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger yug_owner.D_REF_PREFERENCE_TYPE
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `D_REF_PREFERENCE_TYPE` AFTER DELETE ON `ref_preference_type` FOR EACH ROW BEGIN INSERT INTO AL_REF_PREFERENCE_TYPE ( AUDITLOG_ID_PK, AUDITLOG_CREATED_TS, AUDITLOG_ACTION_CODE ,ID_PK, VERSION, CREATED_TS, DELETED_TS, UPDATED_TS, UPDATED_BY_USER, UPDATED_BY_TXN_ID, CONFIG_LANGUAGE_CODE_KEY, `KEY`, VALUE, DESCRIPTION ) VALUES( uuid(), CURRENT_TIMESTAMP(6) , 'D',  OLD.ID_PK,  OLD.VERSION,  OLD.CREATED_TS,  OLD.DELETED_TS,  OLD.UPDATED_TS,  OLD.UPDATED_BY_USER,  OLD.UPDATED_BY_TXN_ID,  OLD.CONFIG_LANGUAGE_CODE_KEY,  OLD.KEY,  OLD.VALUE,  OLD.DESCRIPTION); END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger yug_owner.D_REF_PREFIX_NAME
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `D_REF_PREFIX_NAME` AFTER DELETE ON `ref_prefix_name` FOR EACH ROW BEGIN INSERT INTO AL_REF_PREFIX_NAME ( AUDITLOG_ID_PK, AUDITLOG_CREATED_TS, AUDITLOG_ACTION_CODE ,ID_PK, VERSION, CREATED_TS, DELETED_TS, UPDATED_TS, UPDATED_BY_USER, UPDATED_BY_TXN_ID, CONFIG_LANGUAGE_CODE_KEY, `KEY`, VALUE, DESCRIPTION ) VALUES( uuid(), CURRENT_TIMESTAMP(6) , 'D',  OLD.ID_PK,  OLD.VERSION,  OLD.CREATED_TS,  OLD.DELETED_TS,  OLD.UPDATED_TS,  OLD.UPDATED_BY_USER,  OLD.UPDATED_BY_TXN_ID,  OLD.CONFIG_LANGUAGE_CODE_KEY,  OLD.KEY,  OLD.VALUE,  OLD.DESCRIPTION); END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger yug_owner.D_REF_PROPERTY_LE_RELTYPE
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `D_REF_PROPERTY_LE_RELTYPE` AFTER DELETE ON `ref_property_le_reltype` FOR EACH ROW BEGIN INSERT INTO AL_REF_PROPERTY_LE_RELTYPE ( AUDITLOG_ID_PK, AUDITLOG_CREATED_TS, AUDITLOG_ACTION_CODE ,ID_PK, VERSION, CREATED_TS, DELETED_TS, UPDATED_TS, UPDATED_BY_USER, UPDATED_BY_TXN_ID, CONFIG_LANGUAGE_CODE_KEY, `KEY`, VALUE, DESCRIPTION ) VALUES( uuid(), CURRENT_TIMESTAMP(6) , 'D',  OLD.ID_PK,  OLD.VERSION,  OLD.CREATED_TS,  OLD.DELETED_TS,  OLD.UPDATED_TS,  OLD.UPDATED_BY_USER,  OLD.UPDATED_BY_TXN_ID,  OLD.CONFIG_LANGUAGE_CODE_KEY,  OLD.KEY,  OLD.VALUE,  OLD.DESCRIPTION); END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger yug_owner.D_REF_RELATIONSHIP_STATUS
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `D_REF_RELATIONSHIP_STATUS` AFTER DELETE ON `ref_relationship_status` FOR EACH ROW BEGIN INSERT INTO AL_REF_RELATIONSHIP_STATUS ( AUDITLOG_ID_PK, AUDITLOG_CREATED_TS, AUDITLOG_ACTION_CODE ,ID_PK, VERSION, CREATED_TS, DELETED_TS, UPDATED_TS, UPDATED_BY_USER, UPDATED_BY_TXN_ID, CONFIG_LANGUAGE_CODE_KEY, `KEY`, VALUE, DESCRIPTION ) VALUES( uuid(), CURRENT_TIMESTAMP(6) , 'D',  OLD.ID_PK,  OLD.VERSION,  OLD.CREATED_TS,  OLD.DELETED_TS,  OLD.UPDATED_TS,  OLD.UPDATED_BY_USER,  OLD.UPDATED_BY_TXN_ID,  OLD.CONFIG_LANGUAGE_CODE_KEY,  OLD.KEY,  OLD.VALUE,  OLD.DESCRIPTION); END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger yug_owner.D_REF_SOURCE_SYSTEM
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `D_REF_SOURCE_SYSTEM` AFTER DELETE ON `ref_source_system` FOR EACH ROW BEGIN INSERT INTO AL_REF_SOURCE_SYSTEM ( AUDITLOG_ID_PK, AUDITLOG_CREATED_TS, AUDITLOG_ACTION_CODE ,ID_PK, VERSION, CREATED_TS, DELETED_TS, UPDATED_TS, UPDATED_BY_USER, UPDATED_BY_TXN_ID, CONFIG_LANGUAGE_CODE_KEY, `KEY`, VALUE, DESCRIPTION ) VALUES( uuid(), CURRENT_TIMESTAMP(6) , 'D',  OLD.ID_PK,  OLD.VERSION,  OLD.CREATED_TS,  OLD.DELETED_TS,  OLD.UPDATED_TS,  OLD.UPDATED_BY_USER,  OLD.UPDATED_BY_TXN_ID,  OLD.CONFIG_LANGUAGE_CODE_KEY,  OLD.KEY,  OLD.VALUE,  OLD.DESCRIPTION); END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger yug_owner.D_REF_STATE_PROVINCE
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `D_REF_STATE_PROVINCE` AFTER DELETE ON `ref_state_province` FOR EACH ROW BEGIN INSERT INTO AL_REF_STATE_PROVINCE ( AUDITLOG_ID_PK, AUDITLOG_CREATED_TS, AUDITLOG_ACTION_CODE ,ID_PK, VERSION, CREATED_TS, DELETED_TS, UPDATED_TS, UPDATED_BY_USER, UPDATED_BY_TXN_ID, CONFIG_LANGUAGE_CODE_KEY, `KEY`, VALUE, DESCRIPTION, COUNTRY_ISO_REFKEY ) VALUES( uuid(), CURRENT_TIMESTAMP(6) , 'D',  OLD.ID_PK,  OLD.VERSION,  OLD.CREATED_TS,  OLD.DELETED_TS,  OLD.UPDATED_TS,  OLD.UPDATED_BY_USER,  OLD.UPDATED_BY_TXN_ID,  OLD.CONFIG_LANGUAGE_CODE_KEY,  OLD.KEY,  OLD.VALUE,  OLD.DESCRIPTION,  OLD.COUNTRY_ISO_REFKEY); END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger yug_owner.D_REF_STATUS_IN_SOURCE
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `D_REF_STATUS_IN_SOURCE` AFTER DELETE ON `ref_status_in_source` FOR EACH ROW BEGIN INSERT INTO AL_REF_STATUS_IN_SOURCE ( AUDITLOG_ID_PK, AUDITLOG_CREATED_TS, AUDITLOG_ACTION_CODE ,ID_PK, VERSION, CREATED_TS, DELETED_TS, UPDATED_TS, UPDATED_BY_USER, UPDATED_BY_TXN_ID, CONFIG_LANGUAGE_CODE_KEY, `KEY`, VALUE, DESCRIPTION ) VALUES( uuid(), CURRENT_TIMESTAMP(6) , 'D',  OLD.ID_PK,  OLD.VERSION,  OLD.CREATED_TS,  OLD.DELETED_TS,  OLD.UPDATED_TS,  OLD.UPDATED_BY_USER,  OLD.UPDATED_BY_TXN_ID,  OLD.CONFIG_LANGUAGE_CODE_KEY,  OLD.KEY,  OLD.VALUE,  OLD.DESCRIPTION); END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger yug_owner.D_REF_STATUS_TYPE
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `D_REF_STATUS_TYPE` AFTER DELETE ON `ref_status_type` FOR EACH ROW BEGIN INSERT INTO AL_REF_STATUS_TYPE ( AUDITLOG_ID_PK, AUDITLOG_CREATED_TS, AUDITLOG_ACTION_CODE ,ID_PK, VERSION, CREATED_TS, DELETED_TS, UPDATED_TS, UPDATED_BY_USER, UPDATED_BY_TXN_ID, CONFIG_LANGUAGE_CODE_KEY, `KEY`, VALUE, DESCRIPTION ) VALUES( uuid(), CURRENT_TIMESTAMP(6) , 'D',  OLD.ID_PK,  OLD.VERSION,  OLD.CREATED_TS,  OLD.DELETED_TS,  OLD.UPDATED_TS,  OLD.UPDATED_BY_USER,  OLD.UPDATED_BY_TXN_ID,  OLD.CONFIG_LANGUAGE_CODE_KEY,  OLD.KEY,  OLD.VALUE,  OLD.DESCRIPTION); END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger yug_owner.D_REF_SUFFIX_NAME
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `D_REF_SUFFIX_NAME` AFTER DELETE ON `ref_suffix_name` FOR EACH ROW BEGIN INSERT INTO AL_REF_SUFFIX_NAME ( AUDITLOG_ID_PK, AUDITLOG_CREATED_TS, AUDITLOG_ACTION_CODE ,ID_PK, VERSION, CREATED_TS, DELETED_TS, UPDATED_TS, UPDATED_BY_USER, UPDATED_BY_TXN_ID, CONFIG_LANGUAGE_CODE_KEY, `KEY`, VALUE, DESCRIPTION ) VALUES( uuid(), CURRENT_TIMESTAMP(6) , 'D',  OLD.ID_PK,  OLD.VERSION,  OLD.CREATED_TS,  OLD.DELETED_TS,  OLD.UPDATED_TS,  OLD.UPDATED_BY_USER,  OLD.UPDATED_BY_TXN_ID,  OLD.CONFIG_LANGUAGE_CODE_KEY,  OLD.KEY,  OLD.VALUE,  OLD.DESCRIPTION); END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger yug_owner.D_REF_TERMINATION_REASON
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `D_REF_TERMINATION_REASON` AFTER DELETE ON `ref_termination_reason` FOR EACH ROW BEGIN INSERT INTO AL_REF_TERMINATION_REASON ( AUDITLOG_ID_PK, AUDITLOG_CREATED_TS, AUDITLOG_ACTION_CODE ,ID_PK, VERSION, CREATED_TS, DELETED_TS, UPDATED_TS, UPDATED_BY_USER, UPDATED_BY_TXN_ID, CONFIG_LANGUAGE_CODE_KEY, `KEY`, VALUE, DESCRIPTION ) VALUES( uuid(), CURRENT_TIMESTAMP(6) , 'D',  OLD.ID_PK,  OLD.VERSION,  OLD.CREATED_TS,  OLD.DELETED_TS,  OLD.UPDATED_TS,  OLD.UPDATED_BY_USER,  OLD.UPDATED_BY_TXN_ID,  OLD.CONFIG_LANGUAGE_CODE_KEY,  OLD.KEY,  OLD.VALUE,  OLD.DESCRIPTION); END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger yug_owner.D_VEHICLE
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `D_VEHICLE` AFTER DELETE ON `vehicle` FOR EACH ROW BEGIN INSERT INTO AL_VEHICLE ( AUDITLOG_ID_PK, AUDITLOG_CREATED_TS, AUDITLOG_ACTION_CODE ,ID_PK, VERSION, CREATED_TS, DELETED_TS, UPDATED_TS, UPDATED_BY_USER, UPDATED_BY_TXN_ID, VIN_NUMBER, CHASSIS_NUMBER, MAKE, MODEL, YEAR, INTERIOR_COLOR, EXTERIOR_COLOR, REGISTRATION_NUMBER, COUNTRY_OF_REGISTRATION_REFKEY, INSURANCE_ISSUED_BY, INSURANCE_ISSUED_DATE, INSURANCE_EXPIRY_DATE ) VALUES( uuid(), CURRENT_TIMESTAMP(6) , 'D',  OLD.ID_PK,  OLD.VERSION,  OLD.CREATED_TS,  OLD.DELETED_TS,  OLD.UPDATED_TS,  OLD.UPDATED_BY_USER,  OLD.UPDATED_BY_TXN_ID,  OLD.VIN_NUMBER,  OLD.CHASSIS_NUMBER,  OLD.MAKE,  OLD.MODEL,  OLD.YEAR,  OLD.INTERIOR_COLOR,  OLD.EXTERIOR_COLOR,  OLD.REGISTRATION_NUMBER,  OLD.COUNTRY_OF_REGISTRATION_REFKEY,  OLD.INSURANCE_ISSUED_BY,  OLD.INSURANCE_ISSUED_DATE,  OLD.INSURANCE_EXPIRY_DATE); END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger yug_owner.I_ACCOUNT
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `I_ACCOUNT` AFTER INSERT ON `account` FOR EACH ROW BEGIN INSERT INTO AL_ACCOUNT ( AUDITLOG_ID_PK, AUDITLOG_CREATED_TS, AUDITLOG_ACTION_CODE ,ID_PK, VERSION, CREATED_TS, DELETED_TS, UPDATED_TS, UPDATED_BY_USER, UPDATED_BY_TXN_ID, CONTRACT_SIGNED_LANG_REFKEY, CURRENCY_REFKEY, BILLING_MODE_TYPE_REFKEY, FREQUENCY_OF_PAYMENT, LOBTYPE_REFKEY, LOB_DESCRIPTION, SOURCE_SYSTEM_REFKEY, SOURCE_ACCOUNT_ID, MANAGEDBY_BU_CODE, MANAGEDBY_BU_ID, BRANCH_CODE_REFKEY, ACCOUNT_NAME, ACCOUNT_NAME2, ACCOUNT_DESCRIPTION, ACCOUNT_SOURCE_STATUS_REFKEY, ACCOUNT_MDM_STATUS_REFKEY, SIGNED_DATE, SIGNED_PLACE, EXECUTED_DATE, TERMINATED_DATE, TERMINATION_REASON_REFKEY ) VALUES( uuid(), CURRENT_TIMESTAMP(6) , 'I', NEW.ID_PK, NEW.VERSION, NEW.CREATED_TS, NEW.DELETED_TS, NEW.UPDATED_TS, NEW.UPDATED_BY_USER, NEW.UPDATED_BY_TXN_ID, NEW.CONTRACT_SIGNED_LANG_REFKEY, NEW.CURRENCY_REFKEY, NEW.BILLING_MODE_TYPE_REFKEY, NEW.FREQUENCY_OF_PAYMENT, NEW.LOBTYPE_REFKEY, NEW.LOB_DESCRIPTION, NEW.SOURCE_SYSTEM_REFKEY, NEW.SOURCE_ACCOUNT_ID, NEW.MANAGEDBY_BU_CODE, NEW.MANAGEDBY_BU_ID, NEW.BRANCH_CODE_REFKEY, NEW.ACCOUNT_NAME, NEW.ACCOUNT_NAME2, NEW.ACCOUNT_DESCRIPTION, NEW.ACCOUNT_SOURCE_STATUS_REFKEY, NEW.ACCOUNT_MDM_STATUS_REFKEY, NEW.SIGNED_DATE, NEW.SIGNED_PLACE, NEW.EXECUTED_DATE, NEW.TERMINATED_DATE, NEW.TERMINATION_REASON_REFKEY); END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger yug_owner.I_ACCOUNT_ADDRESS_ASSOC
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `I_ACCOUNT_ADDRESS_ASSOC` AFTER INSERT ON `account_address_assoc` FOR EACH ROW BEGIN INSERT INTO AL_ACCOUNT_ADDRESS_ASSOC ( AUDITLOG_ID_PK, AUDITLOG_CREATED_TS, AUDITLOG_ACTION_CODE ,ID_PK, VERSION, CREATED_TS, DELETED_TS, UPDATED_TS, UPDATED_BY_USER, UPDATED_BY_TXN_ID, ACCOUNT_IDPK, ADDRESS_IDPK, ADDRESS_TYPE_REFKEY, ADDRESS_SUBTYPE_REFKEY, PREFERRED_FLAG, SOLICITATION_FLAG ) VALUES( uuid(), CURRENT_TIMESTAMP(6) , 'I', NEW.ID_PK, NEW.VERSION, NEW.CREATED_TS, NEW.DELETED_TS, NEW.UPDATED_TS, NEW.UPDATED_BY_USER, NEW.UPDATED_BY_TXN_ID, NEW.ACCOUNT_IDPK, NEW.ADDRESS_IDPK, NEW.ADDRESS_TYPE_REFKEY, NEW.ADDRESS_SUBTYPE_REFKEY, NEW.PREFERRED_FLAG, NEW.SOLICITATION_FLAG); END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger yug_owner.I_ACCOUNT_PHONE_ASSOC
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `I_ACCOUNT_PHONE_ASSOC` AFTER INSERT ON `account_phone_assoc` FOR EACH ROW BEGIN INSERT INTO AL_ACCOUNT_PHONE_ASSOC ( AUDITLOG_ID_PK, AUDITLOG_CREATED_TS, AUDITLOG_ACTION_CODE ,ID_PK, VERSION, CREATED_TS, DELETED_TS, UPDATED_TS, UPDATED_BY_USER, UPDATED_BY_TXN_ID, ACCOUNT_IDPK, PHONE_TYPE_REFKEY, PHONE_SUBTYPE_REFKEY, PREFERRED_FLAG, PHONE_NUMBER, PHONE_STANDARDIZED_IDPK ) VALUES( uuid(), CURRENT_TIMESTAMP(6) , 'I', NEW.ID_PK, NEW.VERSION, NEW.CREATED_TS, NEW.DELETED_TS, NEW.UPDATED_TS, NEW.UPDATED_BY_USER, NEW.UPDATED_BY_TXN_ID, NEW.ACCOUNT_IDPK, NEW.PHONE_TYPE_REFKEY, NEW.PHONE_SUBTYPE_REFKEY, NEW.PREFERRED_FLAG, NEW.PHONE_NUMBER, NEW.PHONE_STANDARDIZED_IDPK); END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger yug_owner.I_ADDRESS
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `I_ADDRESS` AFTER INSERT ON `address` FOR EACH ROW BEGIN INSERT INTO AL_ADDRESS ( AUDITLOG_ID_PK, AUDITLOG_CREATED_TS, AUDITLOG_ACTION_CODE ,ID_PK, VERSION, CREATED_TS, DELETED_TS, UPDATED_TS, UPDATED_BY_USER, UPDATED_BY_TXN_ID, ADDRESS_LINE_ONE, ADDRESS_LINE_TWO, ADDRESS_LINE_THREE, ADDRESS_LINE_FOUR, STREET_NUMBER, STREET_NAME, HOUSE_NUMBER, BUILDING_NUMBER, COUNTY, CITY, DISTRICT_ZONE, STATE_PROVINCE_REFKEY, COUNTRY_REFKEY, POSTAL_CODE, NEAREST_LANDMARK, BOX_DESIGNATOR, BOX_IDENTIFIER, NEAREST_RAILWAY_STATION, NEAREST_AIRPORT, PHONETIC_ADDRESS_LINE_ONE, PHONETIC_ADDRESS_LINE_TWO, PHONETIC_ADDRESS_LINE_THREE, PHONETIC_ADDRESS_LINE_FOUR, PHONETIC_STREET_NAME, PHONETIC_COUNTY, PHONETIC_CITY, PHONETIC_DISTRICT_ZONE ) VALUES( uuid(), CURRENT_TIMESTAMP(6) , 'I', NEW.ID_PK, NEW.VERSION, NEW.CREATED_TS, NEW.DELETED_TS, NEW.UPDATED_TS, NEW.UPDATED_BY_USER, NEW.UPDATED_BY_TXN_ID, NEW.ADDRESS_LINE_ONE, NEW.ADDRESS_LINE_TWO, NEW.ADDRESS_LINE_THREE, NEW.ADDRESS_LINE_FOUR, NEW.STREET_NUMBER, NEW.STREET_NAME, NEW.HOUSE_NUMBER, NEW.BUILDING_NUMBER, NEW.COUNTY, NEW.CITY, NEW.DISTRICT_ZONE, NEW.STATE_PROVINCE_REFKEY, NEW.COUNTRY_REFKEY, NEW.POSTAL_CODE, NEW.NEAREST_LANDMARK, NEW.BOX_DESIGNATOR, NEW.BOX_IDENTIFIER, NEW.NEAREST_RAILWAY_STATION, NEW.NEAREST_AIRPORT, NEW.PHONETIC_ADDRESS_LINE_ONE, NEW.PHONETIC_ADDRESS_LINE_TWO, NEW.PHONETIC_ADDRESS_LINE_THREE, NEW.PHONETIC_ADDRESS_LINE_FOUR, NEW.PHONETIC_STREET_NAME, NEW.PHONETIC_COUNTY, NEW.PHONETIC_CITY, NEW.PHONETIC_DISTRICT_ZONE); END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger yug_owner.I_AUTH_ROLES_REGISTRY
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `I_AUTH_ROLES_REGISTRY` AFTER INSERT ON `auth_roles_registry` FOR EACH ROW BEGIN INSERT INTO AL_AUTH_ROLES_REGISTRY ( AUDITLOG_ID_PK, AUDITLOG_CREATED_TS, AUDITLOG_ACTION_CODE ,ID_PK, VERSION, CREATED_TS, DELETED_TS, UPDATED_TS, UPDATED_BY_USER, UPDATED_BY_TXN_ID, ROLE_NAME, DESCRIPTION ) VALUES( uuid(), CURRENT_TIMESTAMP(6) , 'I', NEW.ID_PK, NEW.VERSION, NEW.CREATED_TS, NEW.DELETED_TS, NEW.UPDATED_TS, NEW.UPDATED_BY_USER, NEW.UPDATED_BY_TXN_ID, NEW.ROLE_NAME, NEW.DESCRIPTION); END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger yug_owner.I_AUTH_USERROLE_ACCESSCONTROL
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `I_AUTH_USERROLE_ACCESSCONTROL` AFTER INSERT ON `auth_userrole_accesscontrol` FOR EACH ROW BEGIN INSERT INTO AL_AUTH_USERROLE_ACCESSCONTROL ( AUDITLOG_ID_PK, AUDITLOG_CREATED_TS, AUDITLOG_ACTION_CODE ,ID_PK, VERSION, CREATED_TS, DELETED_TS, UPDATED_TS, UPDATED_BY_USER, UPDATED_BY_TXN_ID, PROFILE_TYPE, AUTH_USER_ROLE_REGISTRY_IDPK, CONFIG_TXN_REGISTRY_IDPK, DESCRIPTION ) VALUES( uuid(), CURRENT_TIMESTAMP(6) , 'I', NEW.ID_PK, NEW.VERSION, NEW.CREATED_TS, NEW.DELETED_TS, NEW.UPDATED_TS, NEW.UPDATED_BY_USER, NEW.UPDATED_BY_TXN_ID, NEW.PROFILE_TYPE, NEW.AUTH_USER_ROLE_REGISTRY_IDPK, NEW.CONFIG_TXN_REGISTRY_IDPK, NEW.DESCRIPTION); END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger yug_owner.I_AUTH_USER_REGISTRY
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `I_AUTH_USER_REGISTRY` AFTER INSERT ON `auth_user_registry` FOR EACH ROW BEGIN INSERT INTO AL_AUTH_USER_REGISTRY ( AUDITLOG_ID_PK, AUDITLOG_CREATED_TS, AUDITLOG_ACTION_CODE ,ID_PK, VERSION, CREATED_TS, DELETED_TS, UPDATED_TS, UPDATED_BY_USER, UPDATED_BY_TXN_ID, USER_NAME, DESCRIPTION ) VALUES( uuid(), CURRENT_TIMESTAMP(6) , 'I', NEW.ID_PK, NEW.VERSION, NEW.CREATED_TS, NEW.DELETED_TS, NEW.UPDATED_TS, NEW.UPDATED_BY_USER, NEW.UPDATED_BY_TXN_ID, NEW.USER_NAME, NEW.DESCRIPTION); END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger yug_owner.I_AUTH_USER_ROLE_ASSOC
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `I_AUTH_USER_ROLE_ASSOC` AFTER INSERT ON `auth_user_role_assoc` FOR EACH ROW BEGIN INSERT INTO AL_AUTH_USER_ROLE_ASSOC ( AUDITLOG_ID_PK, AUDITLOG_CREATED_TS, AUDITLOG_ACTION_CODE ,ID_PK, VERSION, CREATED_TS, DELETED_TS, UPDATED_TS, UPDATED_BY_USER, UPDATED_BY_TXN_ID, AUTH_USER_REGISTRY_IDPK, AUTH_ROLES_REGISTRY_IDPK, DESCRIPTION ) VALUES( uuid(), CURRENT_TIMESTAMP(6) , 'I', NEW.ID_PK, NEW.VERSION, NEW.CREATED_TS, NEW.DELETED_TS, NEW.UPDATED_TS, NEW.UPDATED_BY_USER, NEW.UPDATED_BY_TXN_ID, NEW.AUTH_USER_REGISTRY_IDPK, NEW.AUTH_ROLES_REGISTRY_IDPK, NEW.DESCRIPTION); END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger yug_owner.I_BATCH_ENTITY_TO_PROCESS
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `I_BATCH_ENTITY_TO_PROCESS` AFTER INSERT ON `batch_entity_to_process` FOR EACH ROW BEGIN INSERT INTO AL_BATCH_ENTITY_TO_PROCESS ( AUDITLOG_ID_PK, AUDITLOG_CREATED_TS, AUDITLOG_ACTION_CODE ,ID_PK, VERSION, CREATED_TS, DELETED_TS, UPDATED_TS, UPDATED_BY_USER, UPDATED_BY_TXN_ID, ENTITY_OBJECT_TYPE_REFKEY, ENTITY_IDPK, BATCH_PROPOSED_ACTION_REFKEY, BATCH_ACTION_STATUS_REFKEY, PROCESS_AFTER_TIMESTAMP, PROCESS_BEFORE_TIMESTAMP, ENTRY_MADE_BY_SUBSYSTEM_NAME, COMMENTS ) VALUES( uuid(), CURRENT_TIMESTAMP(6) , 'I', NEW.ID_PK, NEW.VERSION, NEW.CREATED_TS, NEW.DELETED_TS, NEW.UPDATED_TS, NEW.UPDATED_BY_USER, NEW.UPDATED_BY_TXN_ID, NEW.ENTITY_OBJECT_TYPE_REFKEY, NEW.ENTITY_IDPK, NEW.BATCH_PROPOSED_ACTION_REFKEY, NEW.BATCH_ACTION_STATUS_REFKEY, NEW.PROCESS_AFTER_TIMESTAMP, NEW.PROCESS_BEFORE_TIMESTAMP, NEW.ENTRY_MADE_BY_SUBSYSTEM_NAME, NEW.COMMENTS); END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger yug_owner.I_CONFIG_APP_PROPERTIES
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `I_CONFIG_APP_PROPERTIES` AFTER INSERT ON `config_app_properties` FOR EACH ROW BEGIN INSERT INTO AL_CONFIG_APP_PROPERTIES ( AUDITLOG_ID_PK, AUDITLOG_CREATED_TS, AUDITLOG_ACTION_CODE ,ID_PK, VERSION, CREATED_TS, DELETED_TS, UPDATED_TS, UPDATED_BY_USER, UPDATED_BY_TXN_ID, `KEY`, VALUE, VALUE_DEFAULT, DESCRIPTION ) VALUES( uuid(), CURRENT_TIMESTAMP(6) , 'I', NEW.ID_PK, NEW.VERSION, NEW.CREATED_TS, NEW.DELETED_TS, NEW.UPDATED_TS, NEW.UPDATED_BY_USER, NEW.UPDATED_BY_TXN_ID, NEW.KEY, NEW.VALUE, NEW.VALUE_DEFAULT, NEW.DESCRIPTION); END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger yug_owner.I_CONFIG_ERRORCODE_REGISTRY
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `I_CONFIG_ERRORCODE_REGISTRY` AFTER INSERT ON `config_errorcode_registry` FOR EACH ROW BEGIN INSERT INTO AL_CONFIG_ERRORCODE_REGISTRY ( AUDITLOG_ID_PK, AUDITLOG_CREATED_TS, AUDITLOG_ACTION_CODE ,ID_PK, VERSION, CONFIG_LANGUAGE_CODE_KEY, ERROR_CODE, ERROR_MESSAGE, DESCRIPTION, CREATED_TS, DELETED_TS, UPDATED_TS, UPDATED_BY_USER, UPDATED_BY_TXN_ID ) VALUES( uuid(), CURRENT_TIMESTAMP(6) , 'I', NEW.ID_PK, NEW.VERSION, NEW.CONFIG_LANGUAGE_CODE_KEY, NEW.ERROR_CODE, NEW.ERROR_MESSAGE, NEW.DESCRIPTION, NEW.CREATED_TS, NEW.DELETED_TS, NEW.UPDATED_TS, NEW.UPDATED_BY_USER, NEW.UPDATED_BY_TXN_ID); END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger yug_owner.I_CONFIG_INQUIRY_LEVELS
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `I_CONFIG_INQUIRY_LEVELS` AFTER INSERT ON `config_inquiry_levels` FOR EACH ROW BEGIN INSERT INTO AL_CONFIG_INQUIRY_LEVELS ( AUDITLOG_ID_PK, AUDITLOG_CREATED_TS, AUDITLOG_ACTION_CODE ,ID_PK, VERSION, CREATED_TS, DELETED_TS, UPDATED_TS, UPDATED_BY_USER, UPDATED_BY_TXN_ID, INQUIRY_LEVEL, APPLICABLE_DOBJ, CHILD_DOBJ, DESCRIPTION ) VALUES( uuid(), CURRENT_TIMESTAMP(6) , 'I', NEW.ID_PK, NEW.VERSION, NEW.CREATED_TS, NEW.DELETED_TS, NEW.UPDATED_TS, NEW.UPDATED_BY_USER, NEW.UPDATED_BY_TXN_ID, NEW.INQUIRY_LEVEL, NEW.APPLICABLE_DOBJ, NEW.CHILD_DOBJ, NEW.DESCRIPTION); END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger yug_owner.I_CONFIG_LANGUAGE_CODE
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `I_CONFIG_LANGUAGE_CODE` AFTER INSERT ON `config_language_code` FOR EACH ROW BEGIN INSERT INTO AL_CONFIG_LANGUAGE_CODE ( AUDITLOG_ID_PK, AUDITLOG_CREATED_TS, AUDITLOG_ACTION_CODE ,ID_PK, VERSION, CREATED_TS, DELETED_TS, UPDATED_TS, UPDATED_BY_USER, UPDATED_BY_TXN_ID, `KEY`, VALUE, DESCRIPTION ) VALUES( uuid(), CURRENT_TIMESTAMP(6) , 'I', NEW.ID_PK, NEW.VERSION, NEW.CREATED_TS, NEW.DELETED_TS, NEW.UPDATED_TS, NEW.UPDATED_BY_USER, NEW.UPDATED_BY_TXN_ID, NEW.KEY, NEW.VALUE, NEW.DESCRIPTION); END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger yug_owner.I_CONFIG_TXN_REGISTRY
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `I_CONFIG_TXN_REGISTRY` AFTER INSERT ON `config_txn_registry` FOR EACH ROW BEGIN INSERT INTO AL_CONFIG_TXN_REGISTRY ( AUDITLOG_ID_PK, AUDITLOG_CREATED_TS, AUDITLOG_ACTION_CODE ,ID_PK, VERSION, TXNSERVICE_NAME, TXNSERVICE_CLASS, TXNSERVICE_CLASSMETHOD, DESCRIPTION, CREATED_TS, DELETED_TS, UPDATED_TS, UPDATED_BY_USER, UPDATED_TXN_REF_ID ) VALUES( uuid(), CURRENT_TIMESTAMP(6) , 'I', NEW.ID_PK, NEW.VERSION, NEW.TXNSERVICE_NAME, NEW.TXNSERVICE_CLASS, NEW.TXNSERVICE_CLASSMETHOD, NEW.DESCRIPTION, NEW.CREATED_TS, NEW.DELETED_TS, NEW.UPDATED_TS, NEW.UPDATED_BY_USER, NEW.UPDATED_TXN_REF_ID); END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger yug_owner.I_CORPORATIONNAMES
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `I_CORPORATIONNAMES` AFTER INSERT ON `corporationnames` FOR EACH ROW BEGIN INSERT INTO AL_CORPORATIONNAMES ( AUDITLOG_ID_PK, AUDITLOG_CREATED_TS, AUDITLOG_ACTION_CODE ,ID_PK, VERSION, CREATED_TS, DELETED_TS, UPDATED_TS, UPDATED_BY_USER, UPDATED_BY_TXN_ID, LEGALENTITY_IDPK, CORPORATION_NAME_TYPE_REFKEY, CORPORATION_NAME, SOURCE_SYSTEM_REFKEY, PHONETIC_CORPORATION_NAME ) VALUES( uuid(), CURRENT_TIMESTAMP(6) , 'I', NEW.ID_PK, NEW.VERSION, NEW.CREATED_TS, NEW.DELETED_TS, NEW.UPDATED_TS, NEW.UPDATED_BY_USER, NEW.UPDATED_BY_TXN_ID, NEW.LEGALENTITY_IDPK, NEW.CORPORATION_NAME_TYPE_REFKEY, NEW.CORPORATION_NAME, NEW.SOURCE_SYSTEM_REFKEY, NEW.PHONETIC_CORPORATION_NAME); END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger yug_owner.I_ENTITY_GROUP
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `I_ENTITY_GROUP` AFTER INSERT ON `entity_group` FOR EACH ROW BEGIN INSERT INTO AL_ENTITY_GROUP ( AUDITLOG_ID_PK, AUDITLOG_CREATED_TS, AUDITLOG_ACTION_CODE ,ID_PK, VERSION, CREATED_TS, DELETED_TS, UPDATED_TS, UPDATED_BY_USER, UPDATED_BY_TXN_ID, GROUP_TYPE_REFKEY, GROUP_SUBTYPE_REFKEY, GROUP_NAME, DESCRIPTION ) VALUES( uuid(), CURRENT_TIMESTAMP(6) , 'I', NEW.ID_PK, NEW.VERSION, NEW.CREATED_TS, NEW.DELETED_TS, NEW.UPDATED_TS, NEW.UPDATED_BY_USER, NEW.UPDATED_BY_TXN_ID, NEW.GROUP_TYPE_REFKEY, NEW.GROUP_SUBTYPE_REFKEY, NEW.GROUP_NAME, NEW.DESCRIPTION); END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger yug_owner.I_ENTITY_GROUP_ASSOC
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `I_ENTITY_GROUP_ASSOC` AFTER INSERT ON `entity_group_assoc` FOR EACH ROW BEGIN INSERT INTO AL_ENTITY_GROUP_ASSOC ( AUDITLOG_ID_PK, AUDITLOG_CREATED_TS, AUDITLOG_ACTION_CODE ,ID_PK, VERSION, CREATED_TS, DELETED_TS, UPDATED_TS, UPDATED_BY_USER, UPDATED_BY_TXN_ID, ENTITY_OBJECT_TYPE_REFKEY, ENTITY_IDPK, ENTITY_GROUP_IDPK, ASSOC_TYPE_REFKEY, DESCRIPTION ) VALUES( uuid(), CURRENT_TIMESTAMP(6) , 'I', NEW.ID_PK, NEW.VERSION, NEW.CREATED_TS, NEW.DELETED_TS, NEW.UPDATED_TS, NEW.UPDATED_BY_USER, NEW.UPDATED_BY_TXN_ID, NEW.ENTITY_OBJECT_TYPE_REFKEY, NEW.ENTITY_IDPK, NEW.ENTITY_GROUP_IDPK, NEW.ASSOC_TYPE_REFKEY, NEW.DESCRIPTION); END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger yug_owner.I_INACTIVE_LE_REGISTRY
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `I_INACTIVE_LE_REGISTRY` AFTER INSERT ON `inactive_le_registry` FOR EACH ROW BEGIN INSERT INTO AL_INACTIVE_LE_REGISTRY ( AUDITLOG_ID_PK, AUDITLOG_CREATED_TS, AUDITLOG_ACTION_CODE ,ID_PK, VERSION, CREATED_TS, DELETED_TS, UPDATED_TS, UPDATED_BY_USER, UPDATED_BY_TXN_ID, LEGALENTITY_IDPK, INACTIVATED_TS, INACTIVATION_REASON_REFKEY, COMMENTS ) VALUES( uuid(), CURRENT_TIMESTAMP(6) , 'I', NEW.ID_PK, NEW.VERSION, NEW.CREATED_TS, NEW.DELETED_TS, NEW.UPDATED_TS, NEW.UPDATED_BY_USER, NEW.UPDATED_BY_TXN_ID, NEW.LEGALENTITY_IDPK, NEW.INACTIVATED_TS, NEW.INACTIVATION_REASON_REFKEY, NEW.COMMENTS); END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger yug_owner.I_LEGALENTITY
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `I_LEGALENTITY` AFTER INSERT ON `legalentity` FOR EACH ROW BEGIN INSERT INTO AL_LEGALENTITY ( AUDITLOG_ID_PK, AUDITLOG_CREATED_TS, AUDITLOG_ACTION_CODE ,ID_PK, VERSION, CREATED_TS, DELETED_TS, UPDATED_TS, UPDATED_BY_USER, UPDATED_BY_TXN_ID, DISPLAY_NAME, ENTITY_OBJECT_TYPE_REFKEY, CLASSIFICATION_CODE_REFKEY, IMPORTANCE_TYPE_REFKEY, LE_RATING_REFKEY, STATUS_TYPE_REFKEY, SOURCE_SYSTEM_REFKEY, ONBOARDING_DATE, OFFBOARDING_DATE, KYC_VERIFICATION_FLAG, DESCRIPTION, PHONETIC_DISPLAY_NAME ) VALUES( uuid(), CURRENT_TIMESTAMP(6) , 'I', NEW.ID_PK, NEW.VERSION, NEW.CREATED_TS, NEW.DELETED_TS, NEW.UPDATED_TS, NEW.UPDATED_BY_USER, NEW.UPDATED_BY_TXN_ID, NEW.DISPLAY_NAME, NEW.ENTITY_OBJECT_TYPE_REFKEY, NEW.CLASSIFICATION_CODE_REFKEY, NEW.IMPORTANCE_TYPE_REFKEY, NEW.LE_RATING_REFKEY, NEW.STATUS_TYPE_REFKEY, NEW.SOURCE_SYSTEM_REFKEY, NEW.ONBOARDING_DATE, NEW.OFFBOARDING_DATE, NEW.KYC_VERIFICATION_FLAG, NEW.DESCRIPTION, NEW.PHONETIC_DISPLAY_NAME); END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger yug_owner.I_LE_ACCOUNT_ASSOC
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `I_LE_ACCOUNT_ASSOC` AFTER INSERT ON `le_account_assoc` FOR EACH ROW BEGIN INSERT INTO AL_LE_ACCOUNT_ASSOC ( AUDITLOG_ID_PK, AUDITLOG_CREATED_TS, AUDITLOG_ACTION_CODE ,ID_PK, VERSION, CREATED_TS, DELETED_TS, UPDATED_TS, UPDATED_BY_USER, UPDATED_BY_TXN_ID, LEGALENTITY_IDPK, LE_ROLETYPE_REFKEY, ACCOUNT_IDPK, ROLE_ACTIVATION_DATE, ROLE_DEACTIVATION_DATE, DEACTIVATION_REASON_REFKEY, AGREEMENT_TYPE_REFKEY, AGREEMENT_TYPE_DESCRIPTION ) VALUES( uuid(), CURRENT_TIMESTAMP(6) , 'I', NEW.ID_PK, NEW.VERSION, NEW.CREATED_TS, NEW.DELETED_TS, NEW.UPDATED_TS, NEW.UPDATED_BY_USER, NEW.UPDATED_BY_TXN_ID, NEW.LEGALENTITY_IDPK, NEW.LE_ROLETYPE_REFKEY, NEW.ACCOUNT_IDPK, NEW.ROLE_ACTIVATION_DATE, NEW.ROLE_DEACTIVATION_DATE, NEW.DEACTIVATION_REASON_REFKEY, NEW.AGREEMENT_TYPE_REFKEY, NEW.AGREEMENT_TYPE_DESCRIPTION); END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger yug_owner.I_LE_ADDRESS_ASSOC
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `I_LE_ADDRESS_ASSOC` AFTER INSERT ON `le_address_assoc` FOR EACH ROW BEGIN INSERT INTO AL_LE_ADDRESS_ASSOC ( AUDITLOG_ID_PK, AUDITLOG_CREATED_TS, AUDITLOG_ACTION_CODE ,ID_PK, VERSION, CREATED_TS, DELETED_TS, UPDATED_TS, UPDATED_BY_USER, UPDATED_BY_TXN_ID, LEGALENTITY_IDPK, ADDRESS_IDPK, ADDRESS_TYPE_REFKEY, ADDRESS_SUBTYPE_REFKEY, PREFERRED_FLAG, SOLICITATION_FLAG ) VALUES( uuid(), NOW(), 'I', NEW.ID_PK, NEW.VERSION, NEW.CREATED_TS, NEW.DELETED_TS, NEW.UPDATED_TS, NEW.UPDATED_BY_USER, NEW.UPDATED_BY_TXN_ID, NEW.LEGALENTITY_IDPK, NEW.ADDRESS_IDPK, NEW.ADDRESS_TYPE_REFKEY, NEW.ADDRESS_SUBTYPE_REFKEY, NEW.PREFERRED_FLAG, NEW.SOLICITATION_FLAG); END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger yug_owner.I_LE_CORPORATION
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `I_LE_CORPORATION` AFTER INSERT ON `le_corporation` FOR EACH ROW BEGIN INSERT INTO AL_LE_CORPORATION ( AUDITLOG_ID_PK, AUDITLOG_CREATED_TS, AUDITLOG_ACTION_CODE ,LEGALENTITY_IDPK, VERSION, CREATED_TS, DELETED_TS, UPDATED_TS, UPDATED_BY_USER, UPDATED_BY_TXN_ID, CLASSIFICATION_CODE_REFKEY, INDUSTRY_CODE_REFKEY, GOVT_REGISTRATION_DATE, COUNTRY_REGISTRATION_REFKEY, NOTPROFIT_FLAG ) VALUES( uuid(), CURRENT_TIMESTAMP(6) , 'I', NEW.LEGALENTITY_IDPK, NEW.VERSION, NEW.CREATED_TS, NEW.DELETED_TS, NEW.UPDATED_TS, NEW.UPDATED_BY_USER, NEW.UPDATED_BY_TXN_ID, NEW.CLASSIFICATION_CODE_REFKEY, NEW.INDUSTRY_CODE_REFKEY, NEW.GOVT_REGISTRATION_DATE, NEW.COUNTRY_REGISTRATION_REFKEY, NEW.NOTPROFIT_FLAG); END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger yug_owner.I_LE_IDENTIFIER_KYC_REGISTRY
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `I_LE_IDENTIFIER_KYC_REGISTRY` AFTER INSERT ON `le_identifier_kyc_registry` FOR EACH ROW BEGIN INSERT INTO AL_LE_IDENTIFIER_KYC_REGISTRY ( AUDITLOG_ID_PK, AUDITLOG_CREATED_TS, AUDITLOG_ACTION_CODE ,ID_PK, VERSION, CREATED_TS, DELETED_TS, UPDATED_TS, UPDATED_BY_USER, UPDATED_BY_TXN_ID, IDENTIFICATION_TYPE_REFKEY, IDENTIFICATION_NUMBER, LEGALENTITY_IDPK, DOCUMENT, ISSUED_BY, ID_CONSIDERED_FOR_KYC_FLAG, ISSUED_DATE, SOURCE_SYSTEM_REFKEY, IDENTITY_DESCRIPTION ) VALUES( uuid(), NOW(), 'I', NEW.ID_PK, NEW.VERSION, NEW.CREATED_TS, NEW.DELETED_TS, NEW.UPDATED_TS, NEW.UPDATED_BY_USER, NEW.UPDATED_BY_TXN_ID, NEW.IDENTIFICATION_TYPE_REFKEY, NEW.IDENTIFICATION_NUMBER, NEW.LEGALENTITY_IDPK, NEW.DOCUMENT, NEW.ISSUED_BY, NEW.ID_CONSIDERED_FOR_KYC_FLAG, NEW.ISSUED_DATE, NEW.SOURCE_SYSTEM_REFKEY, NEW.IDENTITY_DESCRIPTION); END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger yug_owner.I_LE_PERSON
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `I_LE_PERSON` AFTER INSERT ON `le_person` FOR EACH ROW BEGIN INSERT INTO AL_LE_PERSON ( AUDITLOG_ID_PK, AUDITLOG_CREATED_TS, AUDITLOG_ACTION_CODE ,LEGALENTITY_IDPK, VERSION, CREATED_TS, DELETED_TS, UPDATED_TS, UPDATED_BY_USER, UPDATED_BY_TXN_ID, PERSON_TYPE_REFKEY, GENDER_REFKEY, DATE_OF_BIRTH, COUNTRY_OF_BIRTH__REFKEY, COUNTRY_CITIZENSHIP_REFKEY, COUNTRY_OF_DOMICILE__REFKEY, MARITAL_STATUS, HIGHEST_EDU_QUAL_REFKEY, IS_DECEASED_FLAG, DECEASED_DATE, IS_HANDICAPPED_FLAG, HANDICAPPED_SINCE_DATE, NUMBER_OF_DEPENDENTS, NUMBER_OF_CHILDREN, PREFERRED_LANGUAGE_REFKEY ) VALUES( uuid(), CURRENT_TIMESTAMP(6) , 'I', NEW.LEGALENTITY_IDPK, NEW.VERSION, NEW.CREATED_TS, NEW.DELETED_TS, NEW.UPDATED_TS, NEW.UPDATED_BY_USER, NEW.UPDATED_BY_TXN_ID, NEW.PERSON_TYPE_REFKEY, NEW.GENDER_REFKEY, NEW.DATE_OF_BIRTH, NEW.COUNTRY_OF_BIRTH__REFKEY, NEW.COUNTRY_CITIZENSHIP_REFKEY, NEW.COUNTRY_OF_DOMICILE__REFKEY, NEW.MARITAL_STATUS, NEW.HIGHEST_EDU_QUAL_REFKEY, NEW.IS_DECEASED_FLAG, NEW.DECEASED_DATE, NEW.IS_HANDICAPPED_FLAG, NEW.HANDICAPPED_SINCE_DATE, NEW.NUMBER_OF_DEPENDENTS, NEW.NUMBER_OF_CHILDREN, NEW.PREFERRED_LANGUAGE_REFKEY); END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger yug_owner.I_LE_PHONE_ASSOC
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `I_LE_PHONE_ASSOC` AFTER INSERT ON `le_phone_assoc` FOR EACH ROW BEGIN INSERT INTO AL_LE_PHONE_ASSOC ( AUDITLOG_ID_PK, AUDITLOG_CREATED_TS, AUDITLOG_ACTION_CODE ,ID_PK, VERSION, CREATED_TS, DELETED_TS, UPDATED_TS, UPDATED_BY_USER, UPDATED_BY_TXN_ID, LEGALENTITY_IDPK, PHONE_TYPE_REFKEY, PHONE_SUBTYPE_REFKEY, PREFERRED_FLAG, PHONE_NUMBER, PHONE_STANDARDIZED_IDPK ) VALUES( uuid(), CURRENT_TIMESTAMP(6) , 'I', NEW.ID_PK, NEW.VERSION, NEW.CREATED_TS, NEW.DELETED_TS, NEW.UPDATED_TS, NEW.UPDATED_BY_USER, NEW.UPDATED_BY_TXN_ID, NEW.LEGALENTITY_IDPK, NEW.PHONE_TYPE_REFKEY, NEW.PHONE_SUBTYPE_REFKEY, NEW.PREFERRED_FLAG, NEW.PHONE_NUMBER, NEW.PHONE_STANDARDIZED_IDPK); END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger yug_owner.I_LE_PREFERENCES
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `I_LE_PREFERENCES` AFTER INSERT ON `le_preferences` FOR EACH ROW BEGIN INSERT INTO AL_LE_PREFERENCES ( AUDITLOG_ID_PK, AUDITLOG_CREATED_TS, AUDITLOG_ACTION_CODE ,ID_PK, VERSION, CREATED_TS, DELETED_TS, UPDATED_TS, UPDATED_BY_USER, UPDATED_BY_TXN_ID, LEGALENTITY_IDPK, PREFERENCE_TYPE_REFKEY, PREF_FLAG, PREF_DESCRIPTION ) VALUES( uuid(), CURRENT_TIMESTAMP(6) , 'I', NEW.ID_PK, NEW.VERSION, NEW.CREATED_TS, NEW.DELETED_TS, NEW.UPDATED_TS, NEW.UPDATED_BY_USER, NEW.UPDATED_BY_TXN_ID, NEW.LEGALENTITY_IDPK, NEW.PREFERENCE_TYPE_REFKEY, NEW.PREF_FLAG, NEW.PREF_DESCRIPTION); END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger yug_owner.I_LE_PROPERTY_ASSOC
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `I_LE_PROPERTY_ASSOC` AFTER INSERT ON `le_property_assoc` FOR EACH ROW BEGIN INSERT INTO AL_LE_PROPERTY_ASSOC ( AUDITLOG_ID_PK, AUDITLOG_CREATED_TS, AUDITLOG_ACTION_CODE ,ID_PK, VERSION, CREATED_TS, DELETED_TS, UPDATED_TS, UPDATED_BY_USER, UPDATED_BY_TXN_ID, PROPERTY_IDPK, LEGALENTITY_IDPK, PROPERTY_LE_RELTYPE_REFKEY, DESCRIPTION ) VALUES( uuid(), CURRENT_TIMESTAMP(6) , 'I', NEW.ID_PK, NEW.VERSION, NEW.CREATED_TS, NEW.DELETED_TS, NEW.UPDATED_TS, NEW.UPDATED_BY_USER, NEW.UPDATED_BY_TXN_ID, NEW.PROPERTY_IDPK, NEW.LEGALENTITY_IDPK, NEW.PROPERTY_LE_RELTYPE_REFKEY, NEW.DESCRIPTION); END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger yug_owner.I_LE_SYSTEM_KEYS_REGISTRY
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `I_LE_SYSTEM_KEYS_REGISTRY` AFTER INSERT ON `le_system_keys_registry` FOR EACH ROW BEGIN INSERT INTO AL_LE_SYSTEM_KEYS_REGISTRY ( AUDITLOG_ID_PK, AUDITLOG_CREATED_TS, AUDITLOG_ACTION_CODE ,ID_PK, VERSION, CREATED_TS, DELETED_TS, UPDATED_TS, UPDATED_BY_USER, UPDATED_BY_TXN_ID, SOURCE_SYSTEM_REFKEY, REFERENCE_ID, LEGALENTITY_IDPK, STATUS_IN_SOURCE_REFKEY, DESCRIPTION ) VALUES( uuid(), CURRENT_TIMESTAMP(6) , 'I', NEW.ID_PK, NEW.VERSION, NEW.CREATED_TS, NEW.DELETED_TS, NEW.UPDATED_TS, NEW.UPDATED_BY_USER, NEW.UPDATED_BY_TXN_ID, NEW.SOURCE_SYSTEM_REFKEY, NEW.REFERENCE_ID, NEW.LEGALENTITY_IDPK, NEW.STATUS_IN_SOURCE_REFKEY, NEW.DESCRIPTION); END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger yug_owner.I_LE_TO_LE_RELATIONSHIP
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `I_LE_TO_LE_RELATIONSHIP` AFTER INSERT ON `le_to_le_relationship` FOR EACH ROW BEGIN INSERT INTO AL_LE_TO_LE_RELATIONSHIP ( AUDITLOG_ID_PK, AUDITLOG_CREATED_TS, AUDITLOG_ACTION_CODE ,ID_PK, VERSION, CREATED_TS, DELETED_TS, UPDATED_TS, UPDATED_BY_USER, UPDATED_BY_TXN_ID, FROM_LEGALENTITY_IDPK, TO_LEGALENTITY_IDPK, LE_RELATIONSHIP_TYPE_REFKEY, RELATIONSHIP_STATUS_REFKEY, RELATIONSHIP_DESCRIPTION ) VALUES( uuid(), CURRENT_TIMESTAMP(6) , 'I', NEW.ID_PK, NEW.VERSION, NEW.CREATED_TS, NEW.DELETED_TS, NEW.UPDATED_TS, NEW.UPDATED_BY_USER, NEW.UPDATED_BY_TXN_ID, NEW.FROM_LEGALENTITY_IDPK, NEW.TO_LEGALENTITY_IDPK, NEW.LE_RELATIONSHIP_TYPE_REFKEY, NEW.RELATIONSHIP_STATUS_REFKEY, NEW.RELATIONSHIP_DESCRIPTION); END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger yug_owner.I_LE_VEHICLE_ASSOC
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `I_LE_VEHICLE_ASSOC` AFTER INSERT ON `le_vehicle_assoc` FOR EACH ROW BEGIN INSERT INTO AL_LE_VEHICLE_ASSOC ( AUDITLOG_ID_PK, AUDITLOG_CREATED_TS, AUDITLOG_ACTION_CODE ,ID_PK, VERSION, CREATED_TS, DELETED_TS, UPDATED_TS, UPDATED_BY_USER, UPDATED_BY_TXN_ID, LEGALENTITY_IDPK, LE_ROLETYPE_REFKEY, ACCOUNT_IDPK, ROLE_ACTIVATION_DATE, ROLE_DEACTIVATION_DATE, DEACTIVATION_REASON_REFKEY, AGREEMENT_TYPE_REFKEY, AGREEMENT_TYPE_DESCRIPTION, VEHICLE_IDPK ) VALUES( uuid(), CURRENT_TIMESTAMP(6) , 'I', NEW.ID_PK, NEW.VERSION, NEW.CREATED_TS, NEW.DELETED_TS, NEW.UPDATED_TS, NEW.UPDATED_BY_USER, NEW.UPDATED_BY_TXN_ID, NEW.LEGALENTITY_IDPK, NEW.LE_ROLETYPE_REFKEY, NEW.ACCOUNT_IDPK, NEW.ROLE_ACTIVATION_DATE, NEW.ROLE_DEACTIVATION_DATE, NEW.DEACTIVATION_REASON_REFKEY, NEW.AGREEMENT_TYPE_REFKEY, NEW.AGREEMENT_TYPE_DESCRIPTION, NEW.VEHICLE_IDPK); END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger yug_owner.I_MATCH_CANDIDATE_LE_REGISTRY
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `I_MATCH_CANDIDATE_LE_REGISTRY` AFTER INSERT ON `match_candidate_le_registry` FOR EACH ROW BEGIN INSERT INTO AL_MATCH_CANDIDATE_LE_REGISTRY ( AUDITLOG_ID_PK, AUDITLOG_CREATED_TS, AUDITLOG_ACTION_CODE ,ID_PK, VERSION, CREATED_TS, DELETED_TS, UPDATED_TS, UPDATED_BY_USER, UPDATED_BY_TXN_ID, LEGALENTITY_IDPK, CANDIDATE_LEGALENTITYIDPK, MATCH_PATTERN, MATCH_PROPOSED_ACTION_REFKEY, MATCH_ACTIONSTATUS_REFKEY, MATCH_PERCENTAGE_DESCRIPTION ) VALUES( uuid(), CURRENT_TIMESTAMP(6) , 'I', NEW.ID_PK, NEW.VERSION, NEW.CREATED_TS, NEW.DELETED_TS, NEW.UPDATED_TS, NEW.UPDATED_BY_USER, NEW.UPDATED_BY_TXN_ID, NEW.LEGALENTITY_IDPK, NEW.CANDIDATE_LEGALENTITYIDPK, NEW.MATCH_PATTERN, NEW.MATCH_PROPOSED_ACTION_REFKEY, NEW.MATCH_ACTIONSTATUS_REFKEY, NEW.MATCH_PERCENTAGE_DESCRIPTION); END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger yug_owner.I_MATCH_MERGED_LE_ASSOC
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `I_MATCH_MERGED_LE_ASSOC` AFTER INSERT ON `match_merged_le_assoc` FOR EACH ROW BEGIN INSERT INTO AL_MATCH_MERGED_LE_ASSOC ( AUDITLOG_ID_PK, AUDITLOG_CREATED_TS, AUDITLOG_ACTION_CODE ,ID_PK, VERSION, CREATED_TS, DELETED_TS, UPDATED_TS, UPDATED_BY_USER, UPDATED_BY_TXN_ID, SURVIVOR_LEGALENTITY_IDPK, MERGED_LEGALENTITY_IDPK, MERGE_REASON_REFKEY, COMMENTS ) VALUES( uuid(), CURRENT_TIMESTAMP(6) , 'I', NEW.ID_PK, NEW.VERSION, NEW.CREATED_TS, NEW.DELETED_TS, NEW.UPDATED_TS, NEW.UPDATED_BY_USER, NEW.UPDATED_BY_TXN_ID, NEW.SURVIVOR_LEGALENTITY_IDPK, NEW.MERGED_LEGALENTITY_IDPK, NEW.MERGE_REASON_REFKEY, NEW.COMMENTS); END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger yug_owner.I_MISCELLANEOUS_INFO
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `I_MISCELLANEOUS_INFO` AFTER INSERT ON `miscellaneous_info` FOR EACH ROW BEGIN INSERT INTO AL_MISCELLANEOUS_INFO ( AUDITLOG_ID_PK, AUDITLOG_CREATED_TS, AUDITLOG_ACTION_CODE ,ID_PK, VERSION, CREATED_TS, DELETED_TS, UPDATED_TS, UPDATED_BY_USER, UPDATED_BY_TXN_ID, ENTITY_OBJECT_TYPE_REFKEY, ENTITY_IDPK, NAME1, VALUE1, NAME2, VALUE2, NAME3, VALUE3, NAME4, VALUE4, NAME5, VALUE5, NAME6, VALUE6, NAME7, VALUE7, NAME8, VALUE8, NAME9, VALUE9, NAME10, VALUE10 ) VALUES( uuid(), CURRENT_TIMESTAMP(6) , 'I', NEW.ID_PK, NEW.VERSION, NEW.CREATED_TS, NEW.DELETED_TS, NEW.UPDATED_TS, NEW.UPDATED_BY_USER, NEW.UPDATED_BY_TXN_ID, NEW.ENTITY_OBJECT_TYPE_REFKEY, NEW.ENTITY_IDPK, NEW.NAME1, NEW.VALUE1, NEW.NAME2, NEW.VALUE2, NEW.NAME3, NEW.VALUE3, NEW.NAME4, NEW.VALUE4, NEW.NAME5, NEW.VALUE5, NEW.NAME6, NEW.VALUE6, NEW.NAME7, NEW.VALUE7, NEW.NAME8, NEW.VALUE8, NEW.NAME9, NEW.VALUE9, NEW.NAME10, NEW.VALUE10); END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger yug_owner.I_PERSONNAMES
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `I_PERSONNAMES` AFTER INSERT ON `personnames` FOR EACH ROW BEGIN INSERT INTO AL_PERSONNAMES ( AUDITLOG_ID_PK, AUDITLOG_CREATED_TS, AUDITLOG_ACTION_CODE ,ID_PK, VERSION, CREATED_TS, DELETED_TS, UPDATED_TS, UPDATED_BY_USER, UPDATED_BY_TXN_ID, LEGALENTITY_IDPK, PERSONNAME_TYPE_REFKEY, PREFIX_NAME_REFKEY, PREFIX_MISC, NAME_ONE, NAME_TWO, NAME_THREE, NAME_FOUR, LAST_NAME, NICK_NAME, POPULAR_NAME, SUFFIX_NAME_REFKEY, SUFFIX_MISC, NAME_STANDARDISED_FLAG, SOURCE_SYSTEM_REFKEY, PHONETIC_NAME_ONE, PHONETIC_NAME_TWO, PHONETIC_NAME_THREE, PHONETIC_LAST_NAME ) VALUES( uuid(), CURRENT_TIMESTAMP(6) , 'I', NEW.ID_PK, NEW.VERSION, NEW.CREATED_TS, NEW.DELETED_TS, NEW.UPDATED_TS, NEW.UPDATED_BY_USER, NEW.UPDATED_BY_TXN_ID, NEW.LEGALENTITY_IDPK, NEW.PERSONNAME_TYPE_REFKEY, NEW.PREFIX_NAME_REFKEY, NEW.PREFIX_MISC, NEW.NAME_ONE, NEW.NAME_TWO, NEW.NAME_THREE, NEW.NAME_FOUR, NEW.LAST_NAME, NEW.NICK_NAME, NEW.POPULAR_NAME, NEW.SUFFIX_NAME_REFKEY, NEW.SUFFIX_MISC, NEW.NAME_STANDARDISED_FLAG, NEW.SOURCE_SYSTEM_REFKEY, NEW.PHONETIC_NAME_ONE, NEW.PHONETIC_NAME_TWO, NEW.PHONETIC_NAME_THREE, NEW.PHONETIC_LAST_NAME); END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger yug_owner.I_PHONE_STANDARDIZED
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `I_PHONE_STANDARDIZED` AFTER INSERT ON `phone_standardized` FOR EACH ROW BEGIN INSERT INTO AL_PHONE_STANDARDIZED ( AUDITLOG_ID_PK, AUDITLOG_CREATED_TS, AUDITLOG_ACTION_CODE ,ID_PK, VERSION, CREATED_TS, DELETED_TS, UPDATED_TS, UPDATED_BY_USER, UPDATED_BY_TXN_ID, ITU_COUNTRY_CALLING_CODE, AREA_CODE, EXCHANGE, PHONE_NUMBER, EXTENSION ) VALUES( uuid(), CURRENT_TIMESTAMP(6) , 'I', NEW.ID_PK, NEW.VERSION, NEW.CREATED_TS, NEW.DELETED_TS, NEW.UPDATED_TS, NEW.UPDATED_BY_USER, NEW.UPDATED_BY_TXN_ID, NEW.ITU_COUNTRY_CALLING_CODE, NEW.AREA_CODE, NEW.EXCHANGE, NEW.PHONE_NUMBER, NEW.EXTENSION); END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger yug_owner.I_PROPERTY
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `I_PROPERTY` AFTER INSERT ON `property` FOR EACH ROW BEGIN INSERT INTO AL_PROPERTY ( AUDITLOG_ID_PK, AUDITLOG_CREATED_TS, AUDITLOG_ACTION_CODE ,ID_PK, VERSION, CREATED_TS, DELETED_TS, UPDATED_TS, UPDATED_BY_USER, UPDATED_BY_TXN_ID, PROPERTY_NAME, ADDRESS_IDPK ) VALUES( uuid(), NOW(), 'I', NEW.ID_PK, NEW.VERSION, NEW.CREATED_TS, NEW.DELETED_TS, NEW.UPDATED_TS, NEW.UPDATED_BY_USER, NEW.UPDATED_BY_TXN_ID, NEW.PROPERTY_NAME, NEW.ADDRESS_IDPK); END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger yug_owner.I_REF_ACCOUNT_MDM_STATUS
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `I_REF_ACCOUNT_MDM_STATUS` AFTER INSERT ON `ref_account_mdm_status` FOR EACH ROW BEGIN INSERT INTO AL_REF_ACCOUNT_MDM_STATUS ( AUDITLOG_ID_PK, AUDITLOG_CREATED_TS, AUDITLOG_ACTION_CODE ,ID_PK, VERSION, CREATED_TS, DELETED_TS, UPDATED_TS, UPDATED_BY_USER, UPDATED_BY_TXN_ID, CONFIG_LANGUAGE_CODE_KEY, `KEY`, VALUE, DESCRIPTION ) VALUES( uuid(), CURRENT_TIMESTAMP(6) , 'I', NEW.ID_PK, NEW.VERSION, NEW.CREATED_TS, NEW.DELETED_TS, NEW.UPDATED_TS, NEW.UPDATED_BY_USER, NEW.UPDATED_BY_TXN_ID, NEW.CONFIG_LANGUAGE_CODE_KEY, NEW.KEY, NEW.VALUE, NEW.DESCRIPTION); END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger yug_owner.I_REF_ACCOUNT_SOURCE_STATUS
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `I_REF_ACCOUNT_SOURCE_STATUS` AFTER INSERT ON `ref_account_source_status` FOR EACH ROW BEGIN INSERT INTO AL_REF_ACCOUNT_SOURCE_STATUS ( AUDITLOG_ID_PK, AUDITLOG_CREATED_TS, AUDITLOG_ACTION_CODE ,ID_PK, VERSION, CREATED_TS, DELETED_TS, UPDATED_TS, UPDATED_BY_USER, UPDATED_BY_TXN_ID, CONFIG_LANGUAGE_CODE_KEY, `KEY`, VALUE, DESCRIPTION ) VALUES( uuid(), CURRENT_TIMESTAMP(6) , 'I', NEW.ID_PK, NEW.VERSION, NEW.CREATED_TS, NEW.DELETED_TS, NEW.UPDATED_TS, NEW.UPDATED_BY_USER, NEW.UPDATED_BY_TXN_ID, NEW.CONFIG_LANGUAGE_CODE_KEY, NEW.KEY, NEW.VALUE, NEW.DESCRIPTION); END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger yug_owner.I_REF_ADDRESS_SUBTYPE
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `I_REF_ADDRESS_SUBTYPE` AFTER INSERT ON `ref_address_subtype` FOR EACH ROW BEGIN INSERT INTO AL_REF_ADDRESS_SUBTYPE ( AUDITLOG_ID_PK, AUDITLOG_CREATED_TS, AUDITLOG_ACTION_CODE ,ID_PK, VERSION, CREATED_TS, DELETED_TS, UPDATED_TS, UPDATED_BY_USER, UPDATED_BY_TXN_ID, CONFIG_LANGUAGE_CODE_KEY, `KEY`, VALUE, DESCRIPTION ) VALUES( uuid(), CURRENT_TIMESTAMP(6) , 'I', NEW.ID_PK, NEW.VERSION, NEW.CREATED_TS, NEW.DELETED_TS, NEW.UPDATED_TS, NEW.UPDATED_BY_USER, NEW.UPDATED_BY_TXN_ID, NEW.CONFIG_LANGUAGE_CODE_KEY, NEW.KEY, NEW.VALUE, NEW.DESCRIPTION); END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger yug_owner.I_REF_ADDRESS_TYPE
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `I_REF_ADDRESS_TYPE` AFTER INSERT ON `ref_address_type` FOR EACH ROW BEGIN INSERT INTO AL_REF_ADDRESS_TYPE ( AUDITLOG_ID_PK, AUDITLOG_CREATED_TS, AUDITLOG_ACTION_CODE ,ID_PK, VERSION, CREATED_TS, DELETED_TS, UPDATED_TS, UPDATED_BY_USER, UPDATED_BY_TXN_ID, CONFIG_LANGUAGE_CODE_KEY, `KEY`, VALUE, DESCRIPTION ) VALUES( uuid(), CURRENT_TIMESTAMP(6) , 'I', NEW.ID_PK, NEW.VERSION, NEW.CREATED_TS, NEW.DELETED_TS, NEW.UPDATED_TS, NEW.UPDATED_BY_USER, NEW.UPDATED_BY_TXN_ID, NEW.CONFIG_LANGUAGE_CODE_KEY, NEW.KEY, NEW.VALUE, NEW.DESCRIPTION); END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger yug_owner.I_REF_AGREEMENT_TYPE
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `I_REF_AGREEMENT_TYPE` AFTER INSERT ON `ref_agreement_type` FOR EACH ROW BEGIN INSERT INTO AL_REF_AGREEMENT_TYPE ( AUDITLOG_ID_PK, AUDITLOG_CREATED_TS, AUDITLOG_ACTION_CODE ,ID_PK, VERSION, CREATED_TS, DELETED_TS, UPDATED_TS, UPDATED_BY_USER, UPDATED_BY_TXN_ID, CONFIG_LANGUAGE_CODE_KEY, `KEY`, VALUE, DESCRIPTION ) VALUES( uuid(), CURRENT_TIMESTAMP(6) , 'I', NEW.ID_PK, NEW.VERSION, NEW.CREATED_TS, NEW.DELETED_TS, NEW.UPDATED_TS, NEW.UPDATED_BY_USER, NEW.UPDATED_BY_TXN_ID, NEW.CONFIG_LANGUAGE_CODE_KEY, NEW.KEY, NEW.VALUE, NEW.DESCRIPTION); END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger yug_owner.I_REF_ASSOC_TYPE
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `I_REF_ASSOC_TYPE` AFTER INSERT ON `ref_assoc_type` FOR EACH ROW BEGIN INSERT INTO AL_REF_ASSOC_TYPE ( AUDITLOG_ID_PK, AUDITLOG_CREATED_TS, AUDITLOG_ACTION_CODE ,ID_PK, VERSION, CREATED_TS, DELETED_TS, UPDATED_TS, UPDATED_BY_USER, UPDATED_BY_TXN_ID, CONFIG_LANGUAGE_CODE_KEY, `KEY`, VALUE, DESCRIPTION ) VALUES( uuid(), CURRENT_TIMESTAMP(6) , 'I', NEW.ID_PK, NEW.VERSION, NEW.CREATED_TS, NEW.DELETED_TS, NEW.UPDATED_TS, NEW.UPDATED_BY_USER, NEW.UPDATED_BY_TXN_ID, NEW.CONFIG_LANGUAGE_CODE_KEY, NEW.KEY, NEW.VALUE, NEW.DESCRIPTION); END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger yug_owner.I_REF_BATCH_ACTION_STATUS
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `I_REF_BATCH_ACTION_STATUS` AFTER INSERT ON `ref_batch_action_status` FOR EACH ROW BEGIN INSERT INTO AL_REF_BATCH_ACTION_STATUS ( AUDITLOG_ID_PK, AUDITLOG_CREATED_TS, AUDITLOG_ACTION_CODE ,ID_PK, VERSION, CREATED_TS, DELETED_TS, UPDATED_TS, UPDATED_BY_USER, UPDATED_BY_TXN_ID, CONFIG_LANGUAGE_CODE_KEY, `KEY`, VALUE, DESCRIPTION ) VALUES( uuid(), CURRENT_TIMESTAMP(6) , 'I', NEW.ID_PK, NEW.VERSION, NEW.CREATED_TS, NEW.DELETED_TS, NEW.UPDATED_TS, NEW.UPDATED_BY_USER, NEW.UPDATED_BY_TXN_ID, NEW.CONFIG_LANGUAGE_CODE_KEY, NEW.KEY, NEW.VALUE, NEW.DESCRIPTION); END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger yug_owner.I_REF_BATCH_PROPOSED_ACTION
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `I_REF_BATCH_PROPOSED_ACTION` AFTER INSERT ON `ref_batch_proposed_action` FOR EACH ROW BEGIN INSERT INTO AL_REF_BATCH_PROPOSED_ACTION ( AUDITLOG_ID_PK, AUDITLOG_CREATED_TS, AUDITLOG_ACTION_CODE ,ID_PK, VERSION, CREATED_TS, DELETED_TS, UPDATED_TS, UPDATED_BY_USER, UPDATED_BY_TXN_ID, CONFIG_LANGUAGE_CODE_KEY, `KEY`, VALUE, DESCRIPTION ) VALUES( uuid(), CURRENT_TIMESTAMP(6) , 'I', NEW.ID_PK, NEW.VERSION, NEW.CREATED_TS, NEW.DELETED_TS, NEW.UPDATED_TS, NEW.UPDATED_BY_USER, NEW.UPDATED_BY_TXN_ID, NEW.CONFIG_LANGUAGE_CODE_KEY, NEW.KEY, NEW.VALUE, NEW.DESCRIPTION); END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger yug_owner.I_REF_BILLING_MODE_TYPE
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `I_REF_BILLING_MODE_TYPE` AFTER INSERT ON `ref_billing_mode_type` FOR EACH ROW BEGIN INSERT INTO AL_REF_BILLING_MODE_TYPE ( AUDITLOG_ID_PK, AUDITLOG_CREATED_TS, AUDITLOG_ACTION_CODE ,ID_PK, VERSION, CREATED_TS, DELETED_TS, UPDATED_TS, UPDATED_BY_USER, UPDATED_BY_TXN_ID, CONFIG_LANGUAGE_CODE_KEY, `KEY`, VALUE, DESCRIPTION ) VALUES( uuid(), CURRENT_TIMESTAMP(6) , 'I', NEW.ID_PK, NEW.VERSION, NEW.CREATED_TS, NEW.DELETED_TS, NEW.UPDATED_TS, NEW.UPDATED_BY_USER, NEW.UPDATED_BY_TXN_ID, NEW.CONFIG_LANGUAGE_CODE_KEY, NEW.KEY, NEW.VALUE, NEW.DESCRIPTION); END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger yug_owner.I_REF_BRANCH_CODE
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `I_REF_BRANCH_CODE` AFTER INSERT ON `ref_branch_code` FOR EACH ROW BEGIN INSERT INTO AL_REF_BRANCH_CODE ( AUDITLOG_ID_PK, AUDITLOG_CREATED_TS, AUDITLOG_ACTION_CODE ,ID_PK, VERSION, CREATED_TS, DELETED_TS, UPDATED_TS, UPDATED_BY_USER, UPDATED_BY_TXN_ID, CONFIG_LANGUAGE_CODE_KEY, `KEY`, VALUE, DESCRIPTION ) VALUES( uuid(), CURRENT_TIMESTAMP(6) , 'I', NEW.ID_PK, NEW.VERSION, NEW.CREATED_TS, NEW.DELETED_TS, NEW.UPDATED_TS, NEW.UPDATED_BY_USER, NEW.UPDATED_BY_TXN_ID, NEW.CONFIG_LANGUAGE_CODE_KEY, NEW.KEY, NEW.VALUE, NEW.DESCRIPTION); END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger yug_owner.I_REF_CLASSIFICATION_CODE
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `I_REF_CLASSIFICATION_CODE` AFTER INSERT ON `ref_classification_code` FOR EACH ROW BEGIN INSERT INTO AL_REF_CLASSIFICATION_CODE ( AUDITLOG_ID_PK, AUDITLOG_CREATED_TS, AUDITLOG_ACTION_CODE ,ID_PK, VERSION, CREATED_TS, DELETED_TS, UPDATED_TS, UPDATED_BY_USER, UPDATED_BY_TXN_ID, CONFIG_LANGUAGE_CODE_KEY, `KEY`, VALUE, DESCRIPTION ) VALUES( uuid(), CURRENT_TIMESTAMP(6) , 'I', NEW.ID_PK, NEW.VERSION, NEW.CREATED_TS, NEW.DELETED_TS, NEW.UPDATED_TS, NEW.UPDATED_BY_USER, NEW.UPDATED_BY_TXN_ID, NEW.CONFIG_LANGUAGE_CODE_KEY, NEW.KEY, NEW.VALUE, NEW.DESCRIPTION); END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger yug_owner.I_REF_CORPORATION_NAME_TYPE
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `I_REF_CORPORATION_NAME_TYPE` AFTER INSERT ON `ref_corporation_name_type` FOR EACH ROW BEGIN INSERT INTO AL_REF_CORPORATION_NAME_TYPE ( AUDITLOG_ID_PK, AUDITLOG_CREATED_TS, AUDITLOG_ACTION_CODE ,ID_PK, VERSION, CREATED_TS, DELETED_TS, UPDATED_TS, UPDATED_BY_USER, UPDATED_BY_TXN_ID, CONFIG_LANGUAGE_CODE_KEY, `KEY`, VALUE, DESCRIPTION ) VALUES( uuid(), CURRENT_TIMESTAMP(6) , 'I', NEW.ID_PK, NEW.VERSION, NEW.CREATED_TS, NEW.DELETED_TS, NEW.UPDATED_TS, NEW.UPDATED_BY_USER, NEW.UPDATED_BY_TXN_ID, NEW.CONFIG_LANGUAGE_CODE_KEY, NEW.KEY, NEW.VALUE, NEW.DESCRIPTION); END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger yug_owner.I_REF_CORPORATION_TYPE
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `I_REF_CORPORATION_TYPE` AFTER INSERT ON `ref_corporation_type` FOR EACH ROW BEGIN INSERT INTO AL_REF_CORPORATION_TYPE ( AUDITLOG_ID_PK, AUDITLOG_CREATED_TS, AUDITLOG_ACTION_CODE ,ID_PK, VERSION, CREATED_TS, DELETED_TS, UPDATED_TS, UPDATED_BY_USER, UPDATED_BY_TXN_ID, CONFIG_LANGUAGE_CODE_KEY, `KEY`, VALUE, DESCRIPTION ) VALUES( uuid(), CURRENT_TIMESTAMP(6) , 'I', NEW.ID_PK, NEW.VERSION, NEW.CREATED_TS, NEW.DELETED_TS, NEW.UPDATED_TS, NEW.UPDATED_BY_USER, NEW.UPDATED_BY_TXN_ID, NEW.CONFIG_LANGUAGE_CODE_KEY, NEW.KEY, NEW.VALUE, NEW.DESCRIPTION); END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger yug_owner.I_REF_COUNTRY_ISO
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `I_REF_COUNTRY_ISO` AFTER INSERT ON `ref_country_iso` FOR EACH ROW BEGIN INSERT INTO AL_REF_COUNTRY_ISO ( AUDITLOG_ID_PK, AUDITLOG_CREATED_TS, AUDITLOG_ACTION_CODE ,ID_PK, VERSION, CREATED_TS, DELETED_TS, UPDATED_TS, UPDATED_BY_USER, UPDATED_BY_TXN_ID, CONFIG_LANGUAGE_CODE_KEY, `KEY`, VALUE, DESCRIPTION ) VALUES( uuid(), CURRENT_TIMESTAMP(6) , 'I', NEW.ID_PK, NEW.VERSION, NEW.CREATED_TS, NEW.DELETED_TS, NEW.UPDATED_TS, NEW.UPDATED_BY_USER, NEW.UPDATED_BY_TXN_ID, NEW.CONFIG_LANGUAGE_CODE_KEY, NEW.KEY, NEW.VALUE, NEW.DESCRIPTION); END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger yug_owner.I_REF_CURRENCY
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `I_REF_CURRENCY` AFTER INSERT ON `ref_currency` FOR EACH ROW BEGIN INSERT INTO AL_REF_CURRENCY ( AUDITLOG_ID_PK, AUDITLOG_CREATED_TS, AUDITLOG_ACTION_CODE ,ID_PK, VERSION, CREATED_TS, DELETED_TS, UPDATED_TS, UPDATED_BY_USER, UPDATED_BY_TXN_ID, CONFIG_LANGUAGE_CODE_KEY, `KEY`, VALUE, DESCRIPTION ) VALUES( uuid(), CURRENT_TIMESTAMP(6) , 'I', NEW.ID_PK, NEW.VERSION, NEW.CREATED_TS, NEW.DELETED_TS, NEW.UPDATED_TS, NEW.UPDATED_BY_USER, NEW.UPDATED_BY_TXN_ID, NEW.CONFIG_LANGUAGE_CODE_KEY, NEW.KEY, NEW.VALUE, NEW.DESCRIPTION); END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger yug_owner.I_REF_DEACTIVATION_REASON
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `I_REF_DEACTIVATION_REASON` AFTER INSERT ON `ref_deactivation_reason` FOR EACH ROW BEGIN INSERT INTO AL_REF_DEACTIVATION_REASON ( AUDITLOG_ID_PK, AUDITLOG_CREATED_TS, AUDITLOG_ACTION_CODE ,ID_PK, VERSION, CREATED_TS, DELETED_TS, UPDATED_TS, UPDATED_BY_USER, UPDATED_BY_TXN_ID, CONFIG_LANGUAGE_CODE_KEY, `KEY`, VALUE, DESCRIPTION ) VALUES( uuid(), CURRENT_TIMESTAMP(6) , 'I', NEW.ID_PK, NEW.VERSION, NEW.CREATED_TS, NEW.DELETED_TS, NEW.UPDATED_TS, NEW.UPDATED_BY_USER, NEW.UPDATED_BY_TXN_ID, NEW.CONFIG_LANGUAGE_CODE_KEY, NEW.KEY, NEW.VALUE, NEW.DESCRIPTION); END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger yug_owner.I_REF_ENTITY_OBJECT_TYPE
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `I_REF_ENTITY_OBJECT_TYPE` AFTER INSERT ON `ref_entity_object_type` FOR EACH ROW BEGIN INSERT INTO AL_REF_ENTITY_OBJECT_TYPE ( AUDITLOG_ID_PK, AUDITLOG_CREATED_TS, AUDITLOG_ACTION_CODE ,ID_PK, VERSION, CREATED_TS, DELETED_TS, UPDATED_TS, UPDATED_BY_USER, UPDATED_BY_TXN_ID, CONFIG_LANGUAGE_CODE_KEY, `KEY`, VALUE, DESCRIPTION ) VALUES( uuid(), CURRENT_TIMESTAMP(6) , 'I', NEW.ID_PK, NEW.VERSION, NEW.CREATED_TS, NEW.DELETED_TS, NEW.UPDATED_TS, NEW.UPDATED_BY_USER, NEW.UPDATED_BY_TXN_ID, NEW.CONFIG_LANGUAGE_CODE_KEY, NEW.KEY, NEW.VALUE, NEW.DESCRIPTION); END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger yug_owner.I_REF_GENDER
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `I_REF_GENDER` AFTER INSERT ON `ref_gender` FOR EACH ROW BEGIN INSERT INTO AL_REF_GENDER ( AUDITLOG_ID_PK, AUDITLOG_CREATED_TS, AUDITLOG_ACTION_CODE ,ID_PK, VERSION, CREATED_TS, DELETED_TS, UPDATED_TS, UPDATED_BY_USER, UPDATED_BY_TXN_ID, CONFIG_LANGUAGE_CODE_KEY, `KEY`, VALUE, DESCRIPTION ) VALUES( uuid(), CURRENT_TIMESTAMP(6) , 'I', NEW.ID_PK, NEW.VERSION, NEW.CREATED_TS, NEW.DELETED_TS, NEW.UPDATED_TS, NEW.UPDATED_BY_USER, NEW.UPDATED_BY_TXN_ID, NEW.CONFIG_LANGUAGE_CODE_KEY, NEW.KEY, NEW.VALUE, NEW.DESCRIPTION); END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger yug_owner.I_REF_GROUP_SUBTYPE
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `I_REF_GROUP_SUBTYPE` AFTER INSERT ON `ref_group_subtype` FOR EACH ROW BEGIN INSERT INTO AL_REF_GROUP_SUBTYPE ( AUDITLOG_ID_PK, AUDITLOG_CREATED_TS, AUDITLOG_ACTION_CODE ,ID_PK, VERSION, CREATED_TS, DELETED_TS, UPDATED_TS, UPDATED_BY_USER, UPDATED_BY_TXN_ID, CONFIG_LANGUAGE_CODE_KEY, `KEY`, VALUE, DESCRIPTION ) VALUES( uuid(), CURRENT_TIMESTAMP(6) , 'I', NEW.ID_PK, NEW.VERSION, NEW.CREATED_TS, NEW.DELETED_TS, NEW.UPDATED_TS, NEW.UPDATED_BY_USER, NEW.UPDATED_BY_TXN_ID, NEW.CONFIG_LANGUAGE_CODE_KEY, NEW.KEY, NEW.VALUE, NEW.DESCRIPTION); END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger yug_owner.I_REF_GROUP_TYPE
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `I_REF_GROUP_TYPE` AFTER INSERT ON `ref_group_type` FOR EACH ROW BEGIN INSERT INTO AL_REF_GROUP_TYPE ( AUDITLOG_ID_PK, AUDITLOG_CREATED_TS, AUDITLOG_ACTION_CODE ,ID_PK, VERSION, CREATED_TS, DELETED_TS, UPDATED_TS, UPDATED_BY_USER, UPDATED_BY_TXN_ID, CONFIG_LANGUAGE_CODE_KEY, `KEY`, VALUE, DESCRIPTION ) VALUES( uuid(), CURRENT_TIMESTAMP(6) , 'I', NEW.ID_PK, NEW.VERSION, NEW.CREATED_TS, NEW.DELETED_TS, NEW.UPDATED_TS, NEW.UPDATED_BY_USER, NEW.UPDATED_BY_TXN_ID, NEW.CONFIG_LANGUAGE_CODE_KEY, NEW.KEY, NEW.VALUE, NEW.DESCRIPTION); END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger yug_owner.I_REF_HIGHEST_EDU_QUAL
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `I_REF_HIGHEST_EDU_QUAL` AFTER INSERT ON `ref_highest_edu_qual` FOR EACH ROW BEGIN INSERT INTO AL_REF_HIGHEST_EDU_QUAL ( AUDITLOG_ID_PK, AUDITLOG_CREATED_TS, AUDITLOG_ACTION_CODE ,ID_PK, VERSION, CREATED_TS, DELETED_TS, UPDATED_TS, UPDATED_BY_USER, UPDATED_BY_TXN_ID, CONFIG_LANGUAGE_CODE_KEY, `KEY`, VALUE, DESCRIPTION ) VALUES( uuid(), CURRENT_TIMESTAMP(6) , 'I', NEW.ID_PK, NEW.VERSION, NEW.CREATED_TS, NEW.DELETED_TS, NEW.UPDATED_TS, NEW.UPDATED_BY_USER, NEW.UPDATED_BY_TXN_ID, NEW.CONFIG_LANGUAGE_CODE_KEY, NEW.KEY, NEW.VALUE, NEW.DESCRIPTION); END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger yug_owner.I_REF_IDENTIFICATION_TYPE
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `I_REF_IDENTIFICATION_TYPE` AFTER INSERT ON `ref_identification_type` FOR EACH ROW BEGIN INSERT INTO AL_REF_IDENTIFICATION_TYPE ( AUDITLOG_ID_PK, AUDITLOG_CREATED_TS, AUDITLOG_ACTION_CODE ,ID_PK, VERSION, CREATED_TS, DELETED_TS, UPDATED_TS, UPDATED_BY_USER, UPDATED_BY_TXN_ID, CONFIG_LANGUAGE_CODE_KEY, `KEY`, VALUE, DESCRIPTION ) VALUES( uuid(), CURRENT_TIMESTAMP(6) , 'I', NEW.ID_PK, NEW.VERSION, NEW.CREATED_TS, NEW.DELETED_TS, NEW.UPDATED_TS, NEW.UPDATED_BY_USER, NEW.UPDATED_BY_TXN_ID, NEW.CONFIG_LANGUAGE_CODE_KEY, NEW.KEY, NEW.VALUE, NEW.DESCRIPTION); END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger yug_owner.I_REF_IMPORTANCE_TYPE
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `I_REF_IMPORTANCE_TYPE` AFTER INSERT ON `ref_importance_type` FOR EACH ROW BEGIN INSERT INTO AL_REF_IMPORTANCE_TYPE ( AUDITLOG_ID_PK, AUDITLOG_CREATED_TS, AUDITLOG_ACTION_CODE ,ID_PK, VERSION, CREATED_TS, DELETED_TS, UPDATED_TS, UPDATED_BY_USER, UPDATED_BY_TXN_ID, CONFIG_LANGUAGE_CODE_KEY, `KEY`, VALUE, DESCRIPTION ) VALUES( uuid(), CURRENT_TIMESTAMP(6) , 'I', NEW.ID_PK, NEW.VERSION, NEW.CREATED_TS, NEW.DELETED_TS, NEW.UPDATED_TS, NEW.UPDATED_BY_USER, NEW.UPDATED_BY_TXN_ID, NEW.CONFIG_LANGUAGE_CODE_KEY, NEW.KEY, NEW.VALUE, NEW.DESCRIPTION); END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger yug_owner.I_REF_INACTIVATION_REASON
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `I_REF_INACTIVATION_REASON` AFTER INSERT ON `ref_inactivation_reason` FOR EACH ROW BEGIN INSERT INTO AL_REF_INACTIVATION_REASON ( AUDITLOG_ID_PK, AUDITLOG_CREATED_TS, AUDITLOG_ACTION_CODE ,ID_PK, VERSION, CREATED_TS, DELETED_TS, UPDATED_TS, UPDATED_BY_USER, UPDATED_BY_TXN_ID, CONFIG_LANGUAGE_CODE_KEY, `KEY`, VALUE, DESCRIPTION ) VALUES( uuid(), CURRENT_TIMESTAMP(6) , 'I', NEW.ID_PK, NEW.VERSION, NEW.CREATED_TS, NEW.DELETED_TS, NEW.UPDATED_TS, NEW.UPDATED_BY_USER, NEW.UPDATED_BY_TXN_ID, NEW.CONFIG_LANGUAGE_CODE_KEY, NEW.KEY, NEW.VALUE, NEW.DESCRIPTION); END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger yug_owner.I_REF_INDUSTRY_CODE
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `I_REF_INDUSTRY_CODE` AFTER INSERT ON `ref_industry_code` FOR EACH ROW BEGIN INSERT INTO AL_REF_INDUSTRY_CODE ( AUDITLOG_ID_PK, AUDITLOG_CREATED_TS, AUDITLOG_ACTION_CODE ,ID_PK, VERSION, CREATED_TS, DELETED_TS, UPDATED_TS, UPDATED_BY_USER, UPDATED_BY_TXN_ID, CONFIG_LANGUAGE_CODE_KEY, `KEY`, VALUE, DESCRIPTION ) VALUES( uuid(), CURRENT_TIMESTAMP(6) , 'I', NEW.ID_PK, NEW.VERSION, NEW.CREATED_TS, NEW.DELETED_TS, NEW.UPDATED_TS, NEW.UPDATED_BY_USER, NEW.UPDATED_BY_TXN_ID, NEW.CONFIG_LANGUAGE_CODE_KEY, NEW.KEY, NEW.VALUE, NEW.DESCRIPTION); END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger yug_owner.I_REF_LANGUAGE_CODE
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `I_REF_LANGUAGE_CODE` AFTER INSERT ON `ref_language_code` FOR EACH ROW BEGIN INSERT INTO AL_REF_LANGUAGE_CODE ( AUDITLOG_ID_PK, AUDITLOG_CREATED_TS, AUDITLOG_ACTION_CODE ,ID_PK, VERSION, CREATED_TS, DELETED_TS, UPDATED_TS, UPDATED_BY_USER, UPDATED_BY_TXN_ID, CONFIG_LANGUAGE_CODE_KEY, `KEY`, VALUE, DESCRIPTION ) VALUES( uuid(), CURRENT_TIMESTAMP(6) , 'I', NEW.ID_PK, NEW.VERSION, NEW.CREATED_TS, NEW.DELETED_TS, NEW.UPDATED_TS, NEW.UPDATED_BY_USER, NEW.UPDATED_BY_TXN_ID, NEW.CONFIG_LANGUAGE_CODE_KEY, NEW.KEY, NEW.VALUE, NEW.DESCRIPTION); END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger yug_owner.I_REF_LE_RATING
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `I_REF_LE_RATING` AFTER INSERT ON `ref_le_rating` FOR EACH ROW BEGIN INSERT INTO AL_REF_LE_RATING ( AUDITLOG_ID_PK, AUDITLOG_CREATED_TS, AUDITLOG_ACTION_CODE ,ID_PK, VERSION, CREATED_TS, DELETED_TS, UPDATED_TS, UPDATED_BY_USER, UPDATED_BY_TXN_ID, CONFIG_LANGUAGE_CODE_KEY, `KEY`, VALUE, DESCRIPTION ) VALUES( uuid(), CURRENT_TIMESTAMP(6) , 'I', NEW.ID_PK, NEW.VERSION, NEW.CREATED_TS, NEW.DELETED_TS, NEW.UPDATED_TS, NEW.UPDATED_BY_USER, NEW.UPDATED_BY_TXN_ID, NEW.CONFIG_LANGUAGE_CODE_KEY, NEW.KEY, NEW.VALUE, NEW.DESCRIPTION); END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger yug_owner.I_REF_LE_RELATIONSHIP_TYPE
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `I_REF_LE_RELATIONSHIP_TYPE` AFTER INSERT ON `ref_le_relationship_type` FOR EACH ROW BEGIN INSERT INTO AL_REF_LE_RELATIONSHIP_TYPE ( AUDITLOG_ID_PK, AUDITLOG_CREATED_TS, AUDITLOG_ACTION_CODE ,ID_PK, VERSION, CREATED_TS, DELETED_TS, UPDATED_TS, UPDATED_BY_USER, UPDATED_BY_TXN_ID, CONFIG_LANGUAGE_CODE_KEY, `KEY`, VALUE, DESCRIPTION ) VALUES( uuid(), CURRENT_TIMESTAMP(6) , 'I', NEW.ID_PK, NEW.VERSION, NEW.CREATED_TS, NEW.DELETED_TS, NEW.UPDATED_TS, NEW.UPDATED_BY_USER, NEW.UPDATED_BY_TXN_ID, NEW.CONFIG_LANGUAGE_CODE_KEY, NEW.KEY, NEW.VALUE, NEW.DESCRIPTION); END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger yug_owner.I_REF_LE_ROLETYPE
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `I_REF_LE_ROLETYPE` AFTER INSERT ON `ref_le_roletype` FOR EACH ROW BEGIN INSERT INTO AL_REF_LE_ROLETYPE ( AUDITLOG_ID_PK, AUDITLOG_CREATED_TS, AUDITLOG_ACTION_CODE ,ID_PK, VERSION, CREATED_TS, DELETED_TS, UPDATED_TS, UPDATED_BY_USER, UPDATED_BY_TXN_ID, CONFIG_LANGUAGE_CODE_KEY, `KEY`, VALUE, DESCRIPTION ) VALUES( uuid(), CURRENT_TIMESTAMP(6) , 'I', NEW.ID_PK, NEW.VERSION, NEW.CREATED_TS, NEW.DELETED_TS, NEW.UPDATED_TS, NEW.UPDATED_BY_USER, NEW.UPDATED_BY_TXN_ID, NEW.CONFIG_LANGUAGE_CODE_KEY, NEW.KEY, NEW.VALUE, NEW.DESCRIPTION); END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger yug_owner.I_REF_LOBTYPE
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `I_REF_LOBTYPE` AFTER INSERT ON `ref_lobtype` FOR EACH ROW BEGIN INSERT INTO AL_REF_LOBTYPE ( AUDITLOG_ID_PK, AUDITLOG_CREATED_TS, AUDITLOG_ACTION_CODE ,ID_PK, VERSION, CREATED_TS, DELETED_TS, UPDATED_TS, UPDATED_BY_USER, UPDATED_BY_TXN_ID, CONFIG_LANGUAGE_CODE_KEY, `KEY`, VALUE, DESCRIPTION ) VALUES( uuid(), CURRENT_TIMESTAMP(6) , 'I', NEW.ID_PK, NEW.VERSION, NEW.CREATED_TS, NEW.DELETED_TS, NEW.UPDATED_TS, NEW.UPDATED_BY_USER, NEW.UPDATED_BY_TXN_ID, NEW.CONFIG_LANGUAGE_CODE_KEY, NEW.KEY, NEW.VALUE, NEW.DESCRIPTION); END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger yug_owner.I_REF_MATCH_ACTIONSTATUS
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `I_REF_MATCH_ACTIONSTATUS` AFTER INSERT ON `ref_match_actionstatus` FOR EACH ROW BEGIN INSERT INTO AL_REF_MATCH_ACTIONSTATUS ( AUDITLOG_ID_PK, AUDITLOG_CREATED_TS, AUDITLOG_ACTION_CODE ,ID_PK, VERSION, CREATED_TS, DELETED_TS, UPDATED_TS, UPDATED_BY_USER, UPDATED_BY_TXN_ID, CONFIG_LANGUAGE_CODE_KEY, `KEY`, VALUE, DESCRIPTION ) VALUES( uuid(), CURRENT_TIMESTAMP(6) , 'I', NEW.ID_PK, NEW.VERSION, NEW.CREATED_TS, NEW.DELETED_TS, NEW.UPDATED_TS, NEW.UPDATED_BY_USER, NEW.UPDATED_BY_TXN_ID, NEW.CONFIG_LANGUAGE_CODE_KEY, NEW.KEY, NEW.VALUE, NEW.DESCRIPTION); END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger yug_owner.I_REF_MATCH_PROPOSED_ACTION
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `I_REF_MATCH_PROPOSED_ACTION` AFTER INSERT ON `ref_match_proposed_action` FOR EACH ROW BEGIN INSERT INTO AL_REF_MATCH_PROPOSED_ACTION ( AUDITLOG_ID_PK, AUDITLOG_CREATED_TS, AUDITLOG_ACTION_CODE ,ID_PK, VERSION, CREATED_TS, DELETED_TS, UPDATED_TS, UPDATED_BY_USER, UPDATED_BY_TXN_ID, CONFIG_LANGUAGE_CODE_KEY, `KEY`, VALUE, DESCRIPTION ) VALUES( uuid(), CURRENT_TIMESTAMP(6) , 'I', NEW.ID_PK, NEW.VERSION, NEW.CREATED_TS, NEW.DELETED_TS, NEW.UPDATED_TS, NEW.UPDATED_BY_USER, NEW.UPDATED_BY_TXN_ID, NEW.CONFIG_LANGUAGE_CODE_KEY, NEW.KEY, NEW.VALUE, NEW.DESCRIPTION); END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger yug_owner.I_REF_MATCH_RESULT
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `I_REF_MATCH_RESULT` AFTER INSERT ON `ref_match_result` FOR EACH ROW BEGIN INSERT INTO AL_REF_MATCH_RESULT ( AUDITLOG_ID_PK, AUDITLOG_CREATED_TS, AUDITLOG_ACTION_CODE ,ID_PK, VERSION, CREATED_TS, DELETED_TS, UPDATED_TS, UPDATED_BY_USER, UPDATED_BY_TXN_ID, CONFIG_LANGUAGE_CODE_KEY, `KEY`, VALUE, DESCRIPTION ) VALUES( uuid(), CURRENT_TIMESTAMP(6) , 'I', NEW.ID_PK, NEW.VERSION, NEW.CREATED_TS, NEW.DELETED_TS, NEW.UPDATED_TS, NEW.UPDATED_BY_USER, NEW.UPDATED_BY_TXN_ID, NEW.CONFIG_LANGUAGE_CODE_KEY, NEW.KEY, NEW.VALUE, NEW.DESCRIPTION); END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger yug_owner.I_REF_MATCH_SCORE
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `I_REF_MATCH_SCORE` AFTER INSERT ON `ref_match_score` FOR EACH ROW BEGIN INSERT INTO AL_REF_MATCH_SCORE ( AUDITLOG_ID_PK, AUDITLOG_CREATED_TS, AUDITLOG_ACTION_CODE ,ID_PK, VERSION, CREATED_TS, DELETED_TS, UPDATED_TS, UPDATED_BY_USER, UPDATED_BY_TXN_ID, MATCH_ENTITY_OBJECT_NAME, MATCH_ATTR_PATTERN, MATCH_RESULT_REFKEY, MATCH_PROPOSED_ACTION_REFKEY, MATCH_ATTR_PATTERN_DESCR ) VALUES( uuid(), CURRENT_TIMESTAMP(6) , 'I', NEW.ID_PK, NEW.VERSION, NEW.CREATED_TS, NEW.DELETED_TS, NEW.UPDATED_TS, NEW.UPDATED_BY_USER, NEW.UPDATED_BY_TXN_ID, NEW.MATCH_ENTITY_OBJECT_NAME, NEW.MATCH_ATTR_PATTERN, NEW.MATCH_RESULT_REFKEY, NEW.MATCH_PROPOSED_ACTION_REFKEY, NEW.MATCH_ATTR_PATTERN_DESCR); END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger yug_owner.I_REF_MATCH_THRESHOLD
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `I_REF_MATCH_THRESHOLD` AFTER INSERT ON `ref_match_threshold` FOR EACH ROW BEGIN INSERT INTO AL_REF_MATCH_THRESHOLD ( AUDITLOG_ID_PK, AUDITLOG_CREATED_TS, AUDITLOG_ACTION_CODE ,ID_PK, VERSION, CREATED_TS, DELETED_TS, UPDATED_TS, UPDATED_BY_USER, UPDATED_BY_TXN_ID, ATTR_BLOCK_NAME, MATCH_THRESHOLD, DECAY_THRESHOLD_IN_DAYS, DECAY_PERCENTAGE, MAX_DECAY_PERCENTAGE ) VALUES( uuid(), CURRENT_TIMESTAMP(6) , 'I', NEW.ID_PK, NEW.VERSION, NEW.CREATED_TS, NEW.DELETED_TS, NEW.UPDATED_TS, NEW.UPDATED_BY_USER, NEW.UPDATED_BY_TXN_ID, NEW.ATTR_BLOCK_NAME, NEW.MATCH_THRESHOLD, NEW.DECAY_THRESHOLD_IN_DAYS, NEW.DECAY_PERCENTAGE, NEW.MAX_DECAY_PERCENTAGE); END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger yug_owner.I_REF_MERGE_REASON
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `I_REF_MERGE_REASON` AFTER INSERT ON `ref_merge_reason` FOR EACH ROW BEGIN INSERT INTO AL_REF_MERGE_REASON ( AUDITLOG_ID_PK, AUDITLOG_CREATED_TS, AUDITLOG_ACTION_CODE ,ID_PK, VERSION, CREATED_TS, DELETED_TS, UPDATED_TS, UPDATED_BY_USER, UPDATED_BY_TXN_ID, CONFIG_LANGUAGE_CODE_KEY, `KEY`, VALUE, DESCRIPTION ) VALUES( uuid(), CURRENT_TIMESTAMP(6) , 'I', NEW.ID_PK, NEW.VERSION, NEW.CREATED_TS, NEW.DELETED_TS, NEW.UPDATED_TS, NEW.UPDATED_BY_USER, NEW.UPDATED_BY_TXN_ID, NEW.CONFIG_LANGUAGE_CODE_KEY, NEW.KEY, NEW.VALUE, NEW.DESCRIPTION); END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger yug_owner.I_REF_PERSONNAME_TYPE
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `I_REF_PERSONNAME_TYPE` AFTER INSERT ON `ref_personname_type` FOR EACH ROW BEGIN INSERT INTO AL_REF_PERSONNAME_TYPE ( AUDITLOG_ID_PK, AUDITLOG_CREATED_TS, AUDITLOG_ACTION_CODE ,ID_PK, VERSION, CREATED_TS, DELETED_TS, UPDATED_TS, UPDATED_BY_USER, UPDATED_BY_TXN_ID, CONFIG_LANGUAGE_CODE_KEY, `KEY`, VALUE, DESCRIPTION ) VALUES( uuid(), CURRENT_TIMESTAMP(6) , 'I', NEW.ID_PK, NEW.VERSION, NEW.CREATED_TS, NEW.DELETED_TS, NEW.UPDATED_TS, NEW.UPDATED_BY_USER, NEW.UPDATED_BY_TXN_ID, NEW.CONFIG_LANGUAGE_CODE_KEY, NEW.KEY, NEW.VALUE, NEW.DESCRIPTION); END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger yug_owner.I_REF_PERSON_TYPE
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `I_REF_PERSON_TYPE` AFTER INSERT ON `ref_person_type` FOR EACH ROW BEGIN INSERT INTO AL_REF_PERSON_TYPE ( AUDITLOG_ID_PK, AUDITLOG_CREATED_TS, AUDITLOG_ACTION_CODE ,ID_PK, VERSION, CREATED_TS, DELETED_TS, UPDATED_TS, UPDATED_BY_USER, UPDATED_BY_TXN_ID, CONFIG_LANGUAGE_CODE_KEY, `KEY`, VALUE, DESCRIPTION ) VALUES( uuid(), CURRENT_TIMESTAMP(6) , 'I', NEW.ID_PK, NEW.VERSION, NEW.CREATED_TS, NEW.DELETED_TS, NEW.UPDATED_TS, NEW.UPDATED_BY_USER, NEW.UPDATED_BY_TXN_ID, NEW.CONFIG_LANGUAGE_CODE_KEY, NEW.KEY, NEW.VALUE, NEW.DESCRIPTION); END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger yug_owner.I_REF_PHONE_SUBTYPE
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `I_REF_PHONE_SUBTYPE` AFTER INSERT ON `ref_phone_subtype` FOR EACH ROW BEGIN INSERT INTO AL_REF_PHONE_SUBTYPE ( AUDITLOG_ID_PK, AUDITLOG_CREATED_TS, AUDITLOG_ACTION_CODE ,ID_PK, VERSION, CREATED_TS, DELETED_TS, UPDATED_TS, UPDATED_BY_USER, UPDATED_BY_TXN_ID, CONFIG_LANGUAGE_CODE_KEY, `KEY`, VALUE, DESCRIPTION ) VALUES( uuid(), CURRENT_TIMESTAMP(6) , 'I', NEW.ID_PK, NEW.VERSION, NEW.CREATED_TS, NEW.DELETED_TS, NEW.UPDATED_TS, NEW.UPDATED_BY_USER, NEW.UPDATED_BY_TXN_ID, NEW.CONFIG_LANGUAGE_CODE_KEY, NEW.KEY, NEW.VALUE, NEW.DESCRIPTION); END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger yug_owner.I_REF_PHONE_TYPE
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `I_REF_PHONE_TYPE` AFTER INSERT ON `ref_phone_type` FOR EACH ROW BEGIN INSERT INTO AL_REF_PHONE_TYPE ( AUDITLOG_ID_PK, AUDITLOG_CREATED_TS, AUDITLOG_ACTION_CODE ,ID_PK, VERSION, CREATED_TS, DELETED_TS, UPDATED_TS, UPDATED_BY_USER, UPDATED_BY_TXN_ID, CONFIG_LANGUAGE_CODE_KEY, `KEY`, VALUE, DESCRIPTION ) VALUES( uuid(), CURRENT_TIMESTAMP(6) , 'I', NEW.ID_PK, NEW.VERSION, NEW.CREATED_TS, NEW.DELETED_TS, NEW.UPDATED_TS, NEW.UPDATED_BY_USER, NEW.UPDATED_BY_TXN_ID, NEW.CONFIG_LANGUAGE_CODE_KEY, NEW.KEY, NEW.VALUE, NEW.DESCRIPTION); END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger yug_owner.I_REF_PREFERENCE_TYPE
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `I_REF_PREFERENCE_TYPE` AFTER INSERT ON `ref_preference_type` FOR EACH ROW BEGIN INSERT INTO AL_REF_PREFERENCE_TYPE ( AUDITLOG_ID_PK, AUDITLOG_CREATED_TS, AUDITLOG_ACTION_CODE ,ID_PK, VERSION, CREATED_TS, DELETED_TS, UPDATED_TS, UPDATED_BY_USER, UPDATED_BY_TXN_ID, CONFIG_LANGUAGE_CODE_KEY, `KEY`, VALUE, DESCRIPTION ) VALUES( uuid(), CURRENT_TIMESTAMP(6) , 'I', NEW.ID_PK, NEW.VERSION, NEW.CREATED_TS, NEW.DELETED_TS, NEW.UPDATED_TS, NEW.UPDATED_BY_USER, NEW.UPDATED_BY_TXN_ID, NEW.CONFIG_LANGUAGE_CODE_KEY, NEW.KEY, NEW.VALUE, NEW.DESCRIPTION); END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger yug_owner.I_REF_PREFIX_NAME
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `I_REF_PREFIX_NAME` AFTER INSERT ON `ref_prefix_name` FOR EACH ROW BEGIN INSERT INTO AL_REF_PREFIX_NAME ( AUDITLOG_ID_PK, AUDITLOG_CREATED_TS, AUDITLOG_ACTION_CODE ,ID_PK, VERSION, CREATED_TS, DELETED_TS, UPDATED_TS, UPDATED_BY_USER, UPDATED_BY_TXN_ID, CONFIG_LANGUAGE_CODE_KEY, `KEY`, VALUE, DESCRIPTION ) VALUES( uuid(), CURRENT_TIMESTAMP(6) , 'I', NEW.ID_PK, NEW.VERSION, NEW.CREATED_TS, NEW.DELETED_TS, NEW.UPDATED_TS, NEW.UPDATED_BY_USER, NEW.UPDATED_BY_TXN_ID, NEW.CONFIG_LANGUAGE_CODE_KEY, NEW.KEY, NEW.VALUE, NEW.DESCRIPTION); END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger yug_owner.I_REF_PROPERTY_LE_RELTYPE
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `I_REF_PROPERTY_LE_RELTYPE` AFTER INSERT ON `ref_property_le_reltype` FOR EACH ROW BEGIN INSERT INTO AL_REF_PROPERTY_LE_RELTYPE ( AUDITLOG_ID_PK, AUDITLOG_CREATED_TS, AUDITLOG_ACTION_CODE ,ID_PK, VERSION, CREATED_TS, DELETED_TS, UPDATED_TS, UPDATED_BY_USER, UPDATED_BY_TXN_ID, CONFIG_LANGUAGE_CODE_KEY, `KEY`, VALUE, DESCRIPTION ) VALUES( uuid(), CURRENT_TIMESTAMP(6) , 'I', NEW.ID_PK, NEW.VERSION, NEW.CREATED_TS, NEW.DELETED_TS, NEW.UPDATED_TS, NEW.UPDATED_BY_USER, NEW.UPDATED_BY_TXN_ID, NEW.CONFIG_LANGUAGE_CODE_KEY, NEW.KEY, NEW.VALUE, NEW.DESCRIPTION); END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger yug_owner.I_REF_RELATIONSHIP_STATUS
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `I_REF_RELATIONSHIP_STATUS` AFTER INSERT ON `ref_relationship_status` FOR EACH ROW BEGIN INSERT INTO AL_REF_RELATIONSHIP_STATUS ( AUDITLOG_ID_PK, AUDITLOG_CREATED_TS, AUDITLOG_ACTION_CODE ,ID_PK, VERSION, CREATED_TS, DELETED_TS, UPDATED_TS, UPDATED_BY_USER, UPDATED_BY_TXN_ID, CONFIG_LANGUAGE_CODE_KEY, `KEY`, VALUE, DESCRIPTION ) VALUES( uuid(), CURRENT_TIMESTAMP(6) , 'I', NEW.ID_PK, NEW.VERSION, NEW.CREATED_TS, NEW.DELETED_TS, NEW.UPDATED_TS, NEW.UPDATED_BY_USER, NEW.UPDATED_BY_TXN_ID, NEW.CONFIG_LANGUAGE_CODE_KEY, NEW.KEY, NEW.VALUE, NEW.DESCRIPTION); END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger yug_owner.I_REF_SOURCE_SYSTEM
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `I_REF_SOURCE_SYSTEM` AFTER INSERT ON `ref_source_system` FOR EACH ROW BEGIN INSERT INTO AL_REF_SOURCE_SYSTEM ( AUDITLOG_ID_PK, AUDITLOG_CREATED_TS, AUDITLOG_ACTION_CODE ,ID_PK, VERSION, CREATED_TS, DELETED_TS, UPDATED_TS, UPDATED_BY_USER, UPDATED_BY_TXN_ID, CONFIG_LANGUAGE_CODE_KEY, `KEY`, VALUE, DESCRIPTION ) VALUES( uuid(), CURRENT_TIMESTAMP(6) , 'I', NEW.ID_PK, NEW.VERSION, NEW.CREATED_TS, NEW.DELETED_TS, NEW.UPDATED_TS, NEW.UPDATED_BY_USER, NEW.UPDATED_BY_TXN_ID, NEW.CONFIG_LANGUAGE_CODE_KEY, NEW.KEY, NEW.VALUE, NEW.DESCRIPTION); END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger yug_owner.I_REF_STATE_PROVINCE
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `I_REF_STATE_PROVINCE` AFTER INSERT ON `ref_state_province` FOR EACH ROW BEGIN INSERT INTO AL_REF_STATE_PROVINCE ( AUDITLOG_ID_PK, AUDITLOG_CREATED_TS, AUDITLOG_ACTION_CODE ,ID_PK, VERSION, CREATED_TS, DELETED_TS, UPDATED_TS, UPDATED_BY_USER, UPDATED_BY_TXN_ID, CONFIG_LANGUAGE_CODE_KEY, `KEY`, VALUE, DESCRIPTION, COUNTRY_ISO_REFKEY ) VALUES( uuid(), CURRENT_TIMESTAMP(6) , 'I', NEW.ID_PK, NEW.VERSION, NEW.CREATED_TS, NEW.DELETED_TS, NEW.UPDATED_TS, NEW.UPDATED_BY_USER, NEW.UPDATED_BY_TXN_ID, NEW.CONFIG_LANGUAGE_CODE_KEY, NEW.KEY, NEW.VALUE, NEW.DESCRIPTION, NEW.COUNTRY_ISO_REFKEY); END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger yug_owner.I_REF_STATUS_IN_SOURCE
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `I_REF_STATUS_IN_SOURCE` AFTER INSERT ON `ref_status_in_source` FOR EACH ROW BEGIN INSERT INTO AL_REF_STATUS_IN_SOURCE ( AUDITLOG_ID_PK, AUDITLOG_CREATED_TS, AUDITLOG_ACTION_CODE ,ID_PK, VERSION, CREATED_TS, DELETED_TS, UPDATED_TS, UPDATED_BY_USER, UPDATED_BY_TXN_ID, CONFIG_LANGUAGE_CODE_KEY, `KEY`, VALUE, DESCRIPTION ) VALUES( uuid(), CURRENT_TIMESTAMP(6) , 'I', NEW.ID_PK, NEW.VERSION, NEW.CREATED_TS, NEW.DELETED_TS, NEW.UPDATED_TS, NEW.UPDATED_BY_USER, NEW.UPDATED_BY_TXN_ID, NEW.CONFIG_LANGUAGE_CODE_KEY, NEW.KEY, NEW.VALUE, NEW.DESCRIPTION); END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger yug_owner.I_REF_STATUS_TYPE
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `I_REF_STATUS_TYPE` AFTER INSERT ON `ref_status_type` FOR EACH ROW BEGIN INSERT INTO AL_REF_STATUS_TYPE ( AUDITLOG_ID_PK, AUDITLOG_CREATED_TS, AUDITLOG_ACTION_CODE ,ID_PK, VERSION, CREATED_TS, DELETED_TS, UPDATED_TS, UPDATED_BY_USER, UPDATED_BY_TXN_ID, CONFIG_LANGUAGE_CODE_KEY, `KEY`, VALUE, DESCRIPTION ) VALUES( uuid(), CURRENT_TIMESTAMP(6) , 'I', NEW.ID_PK, NEW.VERSION, NEW.CREATED_TS, NEW.DELETED_TS, NEW.UPDATED_TS, NEW.UPDATED_BY_USER, NEW.UPDATED_BY_TXN_ID, NEW.CONFIG_LANGUAGE_CODE_KEY, NEW.KEY, NEW.VALUE, NEW.DESCRIPTION); END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger yug_owner.I_REF_SUFFIX_NAME
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `I_REF_SUFFIX_NAME` AFTER INSERT ON `ref_suffix_name` FOR EACH ROW BEGIN INSERT INTO AL_REF_SUFFIX_NAME ( AUDITLOG_ID_PK, AUDITLOG_CREATED_TS, AUDITLOG_ACTION_CODE ,ID_PK, VERSION, CREATED_TS, DELETED_TS, UPDATED_TS, UPDATED_BY_USER, UPDATED_BY_TXN_ID, CONFIG_LANGUAGE_CODE_KEY, `KEY`, VALUE, DESCRIPTION ) VALUES( uuid(), CURRENT_TIMESTAMP(6) , 'I', NEW.ID_PK, NEW.VERSION, NEW.CREATED_TS, NEW.DELETED_TS, NEW.UPDATED_TS, NEW.UPDATED_BY_USER, NEW.UPDATED_BY_TXN_ID, NEW.CONFIG_LANGUAGE_CODE_KEY, NEW.KEY, NEW.VALUE, NEW.DESCRIPTION); END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger yug_owner.I_REF_TERMINATION_REASON
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `I_REF_TERMINATION_REASON` AFTER INSERT ON `ref_termination_reason` FOR EACH ROW BEGIN INSERT INTO AL_REF_TERMINATION_REASON ( AUDITLOG_ID_PK, AUDITLOG_CREATED_TS, AUDITLOG_ACTION_CODE ,ID_PK, VERSION, CREATED_TS, DELETED_TS, UPDATED_TS, UPDATED_BY_USER, UPDATED_BY_TXN_ID, CONFIG_LANGUAGE_CODE_KEY, `KEY`, VALUE, DESCRIPTION ) VALUES( uuid(), CURRENT_TIMESTAMP(6) , 'I', NEW.ID_PK, NEW.VERSION, NEW.CREATED_TS, NEW.DELETED_TS, NEW.UPDATED_TS, NEW.UPDATED_BY_USER, NEW.UPDATED_BY_TXN_ID, NEW.CONFIG_LANGUAGE_CODE_KEY, NEW.KEY, NEW.VALUE, NEW.DESCRIPTION); END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger yug_owner.I_VEHICLE
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `I_VEHICLE` AFTER INSERT ON `vehicle` FOR EACH ROW BEGIN INSERT INTO AL_VEHICLE ( AUDITLOG_ID_PK, AUDITLOG_CREATED_TS, AUDITLOG_ACTION_CODE ,ID_PK, VERSION, CREATED_TS, DELETED_TS, UPDATED_TS, UPDATED_BY_USER, UPDATED_BY_TXN_ID, VIN_NUMBER, CHASSIS_NUMBER, MAKE, MODEL, YEAR, INTERIOR_COLOR, EXTERIOR_COLOR, REGISTRATION_NUMBER, COUNTRY_OF_REGISTRATION_REFKEY, INSURANCE_ISSUED_BY, INSURANCE_ISSUED_DATE, INSURANCE_EXPIRY_DATE ) VALUES( uuid(), CURRENT_TIMESTAMP(6) , 'I', NEW.ID_PK, NEW.VERSION, NEW.CREATED_TS, NEW.DELETED_TS, NEW.UPDATED_TS, NEW.UPDATED_BY_USER, NEW.UPDATED_BY_TXN_ID, NEW.VIN_NUMBER, NEW.CHASSIS_NUMBER, NEW.MAKE, NEW.MODEL, NEW.YEAR, NEW.INTERIOR_COLOR, NEW.EXTERIOR_COLOR, NEW.REGISTRATION_NUMBER, NEW.COUNTRY_OF_REGISTRATION_REFKEY, NEW.INSURANCE_ISSUED_BY, NEW.INSURANCE_ISSUED_DATE, NEW.INSURANCE_EXPIRY_DATE); END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger yug_owner.U_ACCOUNT
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `U_ACCOUNT` AFTER UPDATE ON `account` FOR EACH ROW BEGIN INSERT INTO AL_ACCOUNT ( AUDITLOG_ID_PK, AUDITLOG_CREATED_TS, AUDITLOG_ACTION_CODE ,ID_PK, VERSION, CREATED_TS, DELETED_TS, UPDATED_TS, UPDATED_BY_USER, UPDATED_BY_TXN_ID, CONTRACT_SIGNED_LANG_REFKEY, CURRENCY_REFKEY, BILLING_MODE_TYPE_REFKEY, FREQUENCY_OF_PAYMENT, LOBTYPE_REFKEY, LOB_DESCRIPTION, SOURCE_SYSTEM_REFKEY, SOURCE_ACCOUNT_ID, MANAGEDBY_BU_CODE, MANAGEDBY_BU_ID, BRANCH_CODE_REFKEY, ACCOUNT_NAME, ACCOUNT_NAME2, ACCOUNT_DESCRIPTION, ACCOUNT_SOURCE_STATUS_REFKEY, ACCOUNT_MDM_STATUS_REFKEY, SIGNED_DATE, SIGNED_PLACE, EXECUTED_DATE, TERMINATED_DATE, TERMINATION_REASON_REFKEY ) VALUES( uuid(), CURRENT_TIMESTAMP(6) , 'U', NEW.ID_PK, NEW.VERSION, NEW.CREATED_TS, NEW.DELETED_TS, NEW.UPDATED_TS, NEW.UPDATED_BY_USER, NEW.UPDATED_BY_TXN_ID, NEW.CONTRACT_SIGNED_LANG_REFKEY, NEW.CURRENCY_REFKEY, NEW.BILLING_MODE_TYPE_REFKEY, NEW.FREQUENCY_OF_PAYMENT, NEW.LOBTYPE_REFKEY, NEW.LOB_DESCRIPTION, NEW.SOURCE_SYSTEM_REFKEY, NEW.SOURCE_ACCOUNT_ID, NEW.MANAGEDBY_BU_CODE, NEW.MANAGEDBY_BU_ID, NEW.BRANCH_CODE_REFKEY, NEW.ACCOUNT_NAME, NEW.ACCOUNT_NAME2, NEW.ACCOUNT_DESCRIPTION, NEW.ACCOUNT_SOURCE_STATUS_REFKEY, NEW.ACCOUNT_MDM_STATUS_REFKEY, NEW.SIGNED_DATE, NEW.SIGNED_PLACE, NEW.EXECUTED_DATE, NEW.TERMINATED_DATE, NEW.TERMINATION_REASON_REFKEY); END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger yug_owner.U_ACCOUNT_ADDRESS_ASSOC
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `U_ACCOUNT_ADDRESS_ASSOC` AFTER UPDATE ON `account_address_assoc` FOR EACH ROW BEGIN INSERT INTO AL_ACCOUNT_ADDRESS_ASSOC ( AUDITLOG_ID_PK, AUDITLOG_CREATED_TS, AUDITLOG_ACTION_CODE ,ID_PK, VERSION, CREATED_TS, DELETED_TS, UPDATED_TS, UPDATED_BY_USER, UPDATED_BY_TXN_ID, ACCOUNT_IDPK, ADDRESS_IDPK, ADDRESS_TYPE_REFKEY, ADDRESS_SUBTYPE_REFKEY, PREFERRED_FLAG, SOLICITATION_FLAG ) VALUES( uuid(), CURRENT_TIMESTAMP(6) , 'U', NEW.ID_PK, NEW.VERSION, NEW.CREATED_TS, NEW.DELETED_TS, NEW.UPDATED_TS, NEW.UPDATED_BY_USER, NEW.UPDATED_BY_TXN_ID, NEW.ACCOUNT_IDPK, NEW.ADDRESS_IDPK, NEW.ADDRESS_TYPE_REFKEY, NEW.ADDRESS_SUBTYPE_REFKEY, NEW.PREFERRED_FLAG, NEW.SOLICITATION_FLAG); END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger yug_owner.U_ACCOUNT_PHONE_ASSOC
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `U_ACCOUNT_PHONE_ASSOC` AFTER UPDATE ON `account_phone_assoc` FOR EACH ROW BEGIN INSERT INTO AL_ACCOUNT_PHONE_ASSOC ( AUDITLOG_ID_PK, AUDITLOG_CREATED_TS, AUDITLOG_ACTION_CODE ,ID_PK, VERSION, CREATED_TS, DELETED_TS, UPDATED_TS, UPDATED_BY_USER, UPDATED_BY_TXN_ID, ACCOUNT_IDPK, PHONE_TYPE_REFKEY, PHONE_SUBTYPE_REFKEY, PREFERRED_FLAG, PHONE_NUMBER, PHONE_STANDARDIZED_IDPK ) VALUES( uuid(), CURRENT_TIMESTAMP(6) , 'U', NEW.ID_PK, NEW.VERSION, NEW.CREATED_TS, NEW.DELETED_TS, NEW.UPDATED_TS, NEW.UPDATED_BY_USER, NEW.UPDATED_BY_TXN_ID, NEW.ACCOUNT_IDPK, NEW.PHONE_TYPE_REFKEY, NEW.PHONE_SUBTYPE_REFKEY, NEW.PREFERRED_FLAG, NEW.PHONE_NUMBER, NEW.PHONE_STANDARDIZED_IDPK); END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger yug_owner.U_ADDRESS
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `U_ADDRESS` AFTER UPDATE ON `address` FOR EACH ROW BEGIN INSERT INTO AL_ADDRESS ( AUDITLOG_ID_PK, AUDITLOG_CREATED_TS, AUDITLOG_ACTION_CODE ,ID_PK, VERSION, CREATED_TS, DELETED_TS, UPDATED_TS, UPDATED_BY_USER, UPDATED_BY_TXN_ID, ADDRESS_LINE_ONE, ADDRESS_LINE_TWO, ADDRESS_LINE_THREE, ADDRESS_LINE_FOUR, STREET_NUMBER, STREET_NAME, HOUSE_NUMBER, BUILDING_NUMBER, COUNTY, CITY, DISTRICT_ZONE, STATE_PROVINCE_REFKEY, COUNTRY_REFKEY, POSTAL_CODE, NEAREST_LANDMARK, BOX_DESIGNATOR, BOX_IDENTIFIER, NEAREST_RAILWAY_STATION, NEAREST_AIRPORT, PHONETIC_ADDRESS_LINE_ONE, PHONETIC_ADDRESS_LINE_TWO, PHONETIC_ADDRESS_LINE_THREE, PHONETIC_ADDRESS_LINE_FOUR, PHONETIC_STREET_NAME, PHONETIC_COUNTY, PHONETIC_CITY, PHONETIC_DISTRICT_ZONE ) VALUES( uuid(), CURRENT_TIMESTAMP(6) , 'U', NEW.ID_PK, NEW.VERSION, NEW.CREATED_TS, NEW.DELETED_TS, NEW.UPDATED_TS, NEW.UPDATED_BY_USER, NEW.UPDATED_BY_TXN_ID, NEW.ADDRESS_LINE_ONE, NEW.ADDRESS_LINE_TWO, NEW.ADDRESS_LINE_THREE, NEW.ADDRESS_LINE_FOUR, NEW.STREET_NUMBER, NEW.STREET_NAME, NEW.HOUSE_NUMBER, NEW.BUILDING_NUMBER, NEW.COUNTY, NEW.CITY, NEW.DISTRICT_ZONE, NEW.STATE_PROVINCE_REFKEY, NEW.COUNTRY_REFKEY, NEW.POSTAL_CODE, NEW.NEAREST_LANDMARK, NEW.BOX_DESIGNATOR, NEW.BOX_IDENTIFIER, NEW.NEAREST_RAILWAY_STATION, NEW.NEAREST_AIRPORT, NEW.PHONETIC_ADDRESS_LINE_ONE, NEW.PHONETIC_ADDRESS_LINE_TWO, NEW.PHONETIC_ADDRESS_LINE_THREE, NEW.PHONETIC_ADDRESS_LINE_FOUR, NEW.PHONETIC_STREET_NAME, NEW.PHONETIC_COUNTY, NEW.PHONETIC_CITY, NEW.PHONETIC_DISTRICT_ZONE); END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger yug_owner.U_AUTH_ROLES_REGISTRY
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `U_AUTH_ROLES_REGISTRY` AFTER UPDATE ON `auth_roles_registry` FOR EACH ROW BEGIN INSERT INTO AL_AUTH_ROLES_REGISTRY ( AUDITLOG_ID_PK, AUDITLOG_CREATED_TS, AUDITLOG_ACTION_CODE ,ID_PK, VERSION, CREATED_TS, DELETED_TS, UPDATED_TS, UPDATED_BY_USER, UPDATED_BY_TXN_ID, ROLE_NAME, DESCRIPTION ) VALUES( uuid(), CURRENT_TIMESTAMP(6) , 'U', NEW.ID_PK, NEW.VERSION, NEW.CREATED_TS, NEW.DELETED_TS, NEW.UPDATED_TS, NEW.UPDATED_BY_USER, NEW.UPDATED_BY_TXN_ID, NEW.ROLE_NAME, NEW.DESCRIPTION ); END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger yug_owner.U_AUTH_USERROLE_ACCESSCONTROL
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `U_AUTH_USERROLE_ACCESSCONTROL` AFTER UPDATE ON `auth_userrole_accesscontrol` FOR EACH ROW BEGIN INSERT INTO AL_AUTH_USERROLE_ACCESSCONTROL ( AUDITLOG_ID_PK, AUDITLOG_CREATED_TS, AUDITLOG_ACTION_CODE ,ID_PK, VERSION, CREATED_TS, DELETED_TS, UPDATED_TS, UPDATED_BY_USER, UPDATED_BY_TXN_ID, PROFILE_TYPE, AUTH_USER_ROLE_REGISTRY_IDPK, CONFIG_TXN_REGISTRY_IDPK, DESCRIPTION ) VALUES( uuid(), CURRENT_TIMESTAMP(6) , 'U', NEW.ID_PK, NEW.VERSION, NEW.CREATED_TS, NEW.DELETED_TS, NEW.UPDATED_TS, NEW.UPDATED_BY_USER, NEW.UPDATED_BY_TXN_ID, NEW.PROFILE_TYPE, NEW.AUTH_USER_ROLE_REGISTRY_IDPK, NEW.CONFIG_TXN_REGISTRY_IDPK, NEW.DESCRIPTION); END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger yug_owner.U_AUTH_USER_REGISTRY
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `U_AUTH_USER_REGISTRY` AFTER UPDATE ON `auth_user_registry` FOR EACH ROW BEGIN INSERT INTO AL_AUTH_USER_REGISTRY ( AUDITLOG_ID_PK, AUDITLOG_CREATED_TS, AUDITLOG_ACTION_CODE ,ID_PK, VERSION, CREATED_TS, DELETED_TS, UPDATED_TS, UPDATED_BY_USER, UPDATED_BY_TXN_ID, USER_NAME, DESCRIPTION ) VALUES( uuid(), CURRENT_TIMESTAMP(6) , 'U', NEW.ID_PK, NEW.VERSION, NEW.CREATED_TS, NEW.DELETED_TS, NEW.UPDATED_TS, NEW.UPDATED_BY_USER, NEW.UPDATED_BY_TXN_ID, NEW.USER_NAME, NEW.DESCRIPTION); END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger yug_owner.U_AUTH_USER_ROLE_ASSOC
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `U_AUTH_USER_ROLE_ASSOC` AFTER UPDATE ON `auth_user_role_assoc` FOR EACH ROW BEGIN INSERT INTO AL_AUTH_USER_ROLE_ASSOC ( AUDITLOG_ID_PK, AUDITLOG_CREATED_TS, AUDITLOG_ACTION_CODE ,ID_PK, VERSION, CREATED_TS, DELETED_TS, UPDATED_TS, UPDATED_BY_USER, UPDATED_BY_TXN_ID, AUTH_USER_REGISTRY_IDPK, AUTH_ROLES_REGISTRY_IDPK, DESCRIPTION ) VALUES( uuid(), CURRENT_TIMESTAMP(6) , 'U', NEW.ID_PK, NEW.VERSION, NEW.CREATED_TS, NEW.DELETED_TS, NEW.UPDATED_TS, NEW.UPDATED_BY_USER, NEW.UPDATED_BY_TXN_ID, NEW.AUTH_USER_REGISTRY_IDPK, NEW.AUTH_ROLES_REGISTRY_IDPK, NEW.DESCRIPTION); END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger yug_owner.U_BATCH_ENTITY_TO_PROCESS
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `U_BATCH_ENTITY_TO_PROCESS` AFTER UPDATE ON `batch_entity_to_process` FOR EACH ROW BEGIN INSERT INTO AL_BATCH_ENTITY_TO_PROCESS ( AUDITLOG_ID_PK, AUDITLOG_CREATED_TS, AUDITLOG_ACTION_CODE ,ID_PK, VERSION, CREATED_TS, DELETED_TS, UPDATED_TS, UPDATED_BY_USER, UPDATED_BY_TXN_ID, ENTITY_OBJECT_TYPE_REFKEY, ENTITY_IDPK, BATCH_PROPOSED_ACTION_REFKEY, BATCH_ACTION_STATUS_REFKEY, PROCESS_AFTER_TIMESTAMP, PROCESS_BEFORE_TIMESTAMP, ENTRY_MADE_BY_SUBSYSTEM_NAME, COMMENTS ) VALUES( uuid(), CURRENT_TIMESTAMP(6) , 'U', NEW.ID_PK, NEW.VERSION, NEW.CREATED_TS, NEW.DELETED_TS, NEW.UPDATED_TS, NEW.UPDATED_BY_USER, NEW.UPDATED_BY_TXN_ID, NEW.ENTITY_OBJECT_TYPE_REFKEY, NEW.ENTITY_IDPK, NEW.BATCH_PROPOSED_ACTION_REFKEY, NEW.BATCH_ACTION_STATUS_REFKEY, NEW.PROCESS_AFTER_TIMESTAMP, NEW.PROCESS_BEFORE_TIMESTAMP, NEW.ENTRY_MADE_BY_SUBSYSTEM_NAME, NEW.COMMENTS); END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger yug_owner.U_CONFIG_APP_PROPERTIES
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `U_CONFIG_APP_PROPERTIES` AFTER UPDATE ON `config_app_properties` FOR EACH ROW BEGIN INSERT INTO AL_CONFIG_APP_PROPERTIES ( AUDITLOG_ID_PK, AUDITLOG_CREATED_TS, AUDITLOG_ACTION_CODE ,ID_PK, VERSION, CREATED_TS, DELETED_TS, UPDATED_TS, UPDATED_BY_USER, UPDATED_BY_TXN_ID, `KEY`, VALUE, VALUE_DEFAULT, DESCRIPTION ) VALUES( uuid(), CURRENT_TIMESTAMP(6) , 'U', NEW.ID_PK, NEW.VERSION, NEW.CREATED_TS, NEW.DELETED_TS, NEW.UPDATED_TS, NEW.UPDATED_BY_USER, NEW.UPDATED_BY_TXN_ID, NEW.KEY, NEW.VALUE, NEW.VALUE_DEFAULT, NEW.DESCRIPTION); END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger yug_owner.U_CONFIG_ERRORCODE_REGISTRY
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `U_CONFIG_ERRORCODE_REGISTRY` AFTER UPDATE ON `config_errorcode_registry` FOR EACH ROW BEGIN INSERT INTO AL_CONFIG_ERRORCODE_REGISTRY ( AUDITLOG_ID_PK, AUDITLOG_CREATED_TS, AUDITLOG_ACTION_CODE ,ID_PK, VERSION, CONFIG_LANGUAGE_CODE_KEY, ERROR_CODE, ERROR_MESSAGE, DESCRIPTION, CREATED_TS, DELETED_TS, UPDATED_TS, UPDATED_BY_USER, UPDATED_BY_TXN_ID ) VALUES( uuid(), CURRENT_TIMESTAMP(6) , 'U', NEW.ID_PK, NEW.VERSION, NEW.CONFIG_LANGUAGE_CODE_KEY, NEW.ERROR_CODE, NEW.ERROR_MESSAGE, NEW.DESCRIPTION, NEW.CREATED_TS, NEW.DELETED_TS, NEW.UPDATED_TS, NEW.UPDATED_BY_USER, NEW.UPDATED_BY_TXN_ID); END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger yug_owner.U_CONFIG_INQUIRY_LEVELS
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `U_CONFIG_INQUIRY_LEVELS` AFTER UPDATE ON `config_inquiry_levels` FOR EACH ROW BEGIN INSERT INTO AL_CONFIG_INQUIRY_LEVELS ( AUDITLOG_ID_PK, AUDITLOG_CREATED_TS, AUDITLOG_ACTION_CODE ,ID_PK, VERSION, CREATED_TS, DELETED_TS, UPDATED_TS, UPDATED_BY_USER, UPDATED_BY_TXN_ID, INQUIRY_LEVEL, APPLICABLE_DOBJ, CHILD_DOBJ, DESCRIPTION ) VALUES( uuid(), CURRENT_TIMESTAMP(6) , 'U', NEW.ID_PK, NEW.VERSION, NEW.CREATED_TS, NEW.DELETED_TS, NEW.UPDATED_TS, NEW.UPDATED_BY_USER, NEW.UPDATED_BY_TXN_ID, NEW.INQUIRY_LEVEL, NEW.APPLICABLE_DOBJ, NEW.CHILD_DOBJ, NEW.DESCRIPTION); END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger yug_owner.U_CONFIG_LANGUAGE_CODE
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `U_CONFIG_LANGUAGE_CODE` AFTER UPDATE ON `config_language_code` FOR EACH ROW BEGIN INSERT INTO AL_CONFIG_LANGUAGE_CODE ( AUDITLOG_ID_PK, AUDITLOG_CREATED_TS, AUDITLOG_ACTION_CODE ,ID_PK, VERSION, CREATED_TS, DELETED_TS, UPDATED_TS, UPDATED_BY_USER, UPDATED_BY_TXN_ID, `KEY`, VALUE, DESCRIPTION ) VALUES( uuid(), CURRENT_TIMESTAMP(6) , 'U', NEW.ID_PK, NEW.VERSION, NEW.CREATED_TS, NEW.DELETED_TS, NEW.UPDATED_TS, NEW.UPDATED_BY_USER, NEW.UPDATED_BY_TXN_ID, NEW.KEY, NEW.VALUE, NEW.DESCRIPTION); END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger yug_owner.U_CONFIG_TXN_REGISTRY
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `U_CONFIG_TXN_REGISTRY` AFTER UPDATE ON `config_txn_registry` FOR EACH ROW BEGIN INSERT INTO AL_CONFIG_TXN_REGISTRY ( AUDITLOG_ID_PK, AUDITLOG_CREATED_TS, AUDITLOG_ACTION_CODE ,ID_PK, VERSION, TXNSERVICE_NAME, TXNSERVICE_CLASS, TXNSERVICE_CLASSMETHOD, DESCRIPTION, CREATED_TS, DELETED_TS, UPDATED_TS, UPDATED_BY_USER, UPDATED_TXN_REF_ID ) VALUES( uuid(), CURRENT_TIMESTAMP(6) , 'U', NEW.ID_PK, NEW.VERSION, NEW.TXNSERVICE_NAME, NEW.TXNSERVICE_CLASS, NEW.TXNSERVICE_CLASSMETHOD, NEW.DESCRIPTION, NEW.CREATED_TS, NEW.DELETED_TS, NEW.UPDATED_TS, NEW.UPDATED_BY_USER, NEW.UPDATED_TXN_REF_ID); END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger yug_owner.U_CORPORATIONNAMES
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `U_CORPORATIONNAMES` AFTER UPDATE ON `corporationnames` FOR EACH ROW BEGIN INSERT INTO AL_CORPORATIONNAMES ( AUDITLOG_ID_PK, AUDITLOG_CREATED_TS, AUDITLOG_ACTION_CODE ,ID_PK, VERSION, CREATED_TS, DELETED_TS, UPDATED_TS, UPDATED_BY_USER, UPDATED_BY_TXN_ID, LEGALENTITY_IDPK, CORPORATION_NAME_TYPE_REFKEY, CORPORATION_NAME, SOURCE_SYSTEM_REFKEY, PHONETIC_CORPORATION_NAME ) VALUES( uuid(), CURRENT_TIMESTAMP(6) , 'U', NEW.ID_PK, NEW.VERSION, NEW.CREATED_TS, NEW.DELETED_TS, NEW.UPDATED_TS, NEW.UPDATED_BY_USER, NEW.UPDATED_BY_TXN_ID, NEW.LEGALENTITY_IDPK, NEW.CORPORATION_NAME_TYPE_REFKEY, NEW.CORPORATION_NAME, NEW.SOURCE_SYSTEM_REFKEY, NEW.PHONETIC_CORPORATION_NAME); END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger yug_owner.U_ENTITY_GROUP
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `U_ENTITY_GROUP` AFTER UPDATE ON `entity_group` FOR EACH ROW BEGIN INSERT INTO AL_ENTITY_GROUP ( AUDITLOG_ID_PK, AUDITLOG_CREATED_TS, AUDITLOG_ACTION_CODE ,ID_PK, VERSION, CREATED_TS, DELETED_TS, UPDATED_TS, UPDATED_BY_USER, UPDATED_BY_TXN_ID, GROUP_TYPE_REFKEY, GROUP_SUBTYPE_REFKEY, GROUP_NAME, DESCRIPTION ) VALUES( uuid(), CURRENT_TIMESTAMP(6) , 'U', NEW.ID_PK, NEW.VERSION, NEW.CREATED_TS, NEW.DELETED_TS, NEW.UPDATED_TS, NEW.UPDATED_BY_USER, NEW.UPDATED_BY_TXN_ID, NEW.GROUP_TYPE_REFKEY, NEW.GROUP_SUBTYPE_REFKEY, NEW.GROUP_NAME, NEW.DESCRIPTION); END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger yug_owner.U_ENTITY_GROUP_ASSOC
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `U_ENTITY_GROUP_ASSOC` AFTER UPDATE ON `entity_group_assoc` FOR EACH ROW BEGIN INSERT INTO AL_ENTITY_GROUP_ASSOC ( AUDITLOG_ID_PK, AUDITLOG_CREATED_TS, AUDITLOG_ACTION_CODE ,ID_PK, VERSION, CREATED_TS, DELETED_TS, UPDATED_TS, UPDATED_BY_USER, UPDATED_BY_TXN_ID, ENTITY_OBJECT_TYPE_REFKEY, ENTITY_IDPK, ENTITY_GROUP_IDPK, ASSOC_TYPE_REFKEY, DESCRIPTION ) VALUES( uuid(), CURRENT_TIMESTAMP(6) , 'U', NEW.ID_PK, NEW.VERSION, NEW.CREATED_TS, NEW.DELETED_TS, NEW.UPDATED_TS, NEW.UPDATED_BY_USER, NEW.UPDATED_BY_TXN_ID, NEW.ENTITY_OBJECT_TYPE_REFKEY, NEW.ENTITY_IDPK, NEW.ENTITY_GROUP_IDPK, NEW.ASSOC_TYPE_REFKEY, NEW.DESCRIPTION); END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger yug_owner.U_INACTIVE_LE_REGISTRY
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `U_INACTIVE_LE_REGISTRY` AFTER UPDATE ON `inactive_le_registry` FOR EACH ROW BEGIN INSERT INTO AL_INACTIVE_LE_REGISTRY ( AUDITLOG_ID_PK, AUDITLOG_CREATED_TS, AUDITLOG_ACTION_CODE ,ID_PK, VERSION, CREATED_TS, DELETED_TS, UPDATED_TS, UPDATED_BY_USER, UPDATED_BY_TXN_ID, LEGALENTITY_IDPK, INACTIVATED_TS, INACTIVATION_REASON_REFKEY, COMMENTS ) VALUES( uuid(), CURRENT_TIMESTAMP(6) , 'U', NEW.ID_PK, NEW.VERSION, NEW.CREATED_TS, NEW.DELETED_TS, NEW.UPDATED_TS, NEW.UPDATED_BY_USER, NEW.UPDATED_BY_TXN_ID, NEW.LEGALENTITY_IDPK, NEW.INACTIVATED_TS, NEW.INACTIVATION_REASON_REFKEY, NEW.COMMENTS); END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger yug_owner.U_LEGALENTITY
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `U_LEGALENTITY` AFTER UPDATE ON `legalentity` FOR EACH ROW BEGIN INSERT INTO AL_LEGALENTITY ( AUDITLOG_ID_PK, AUDITLOG_CREATED_TS, AUDITLOG_ACTION_CODE ,ID_PK, VERSION, CREATED_TS, DELETED_TS, UPDATED_TS, UPDATED_BY_USER, UPDATED_BY_TXN_ID, DISPLAY_NAME, ENTITY_OBJECT_TYPE_REFKEY, CLASSIFICATION_CODE_REFKEY, IMPORTANCE_TYPE_REFKEY, LE_RATING_REFKEY, STATUS_TYPE_REFKEY, SOURCE_SYSTEM_REFKEY, ONBOARDING_DATE, OFFBOARDING_DATE, KYC_VERIFICATION_FLAG, DESCRIPTION, PHONETIC_DISPLAY_NAME ) VALUES( uuid(), CURRENT_TIMESTAMP(6) , 'U', NEW.ID_PK, NEW.VERSION, NEW.CREATED_TS, NEW.DELETED_TS, NEW.UPDATED_TS, NEW.UPDATED_BY_USER, NEW.UPDATED_BY_TXN_ID, NEW.DISPLAY_NAME, NEW.ENTITY_OBJECT_TYPE_REFKEY, NEW.CLASSIFICATION_CODE_REFKEY, NEW.IMPORTANCE_TYPE_REFKEY, NEW.LE_RATING_REFKEY, NEW.STATUS_TYPE_REFKEY, NEW.SOURCE_SYSTEM_REFKEY, NEW.ONBOARDING_DATE, NEW.OFFBOARDING_DATE, NEW.KYC_VERIFICATION_FLAG, NEW.DESCRIPTION, NEW.PHONETIC_DISPLAY_NAME); END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger yug_owner.U_LE_ACCOUNT_ASSOC
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `U_LE_ACCOUNT_ASSOC` AFTER UPDATE ON `le_account_assoc` FOR EACH ROW BEGIN INSERT INTO AL_LE_ACCOUNT_ASSOC ( AUDITLOG_ID_PK, AUDITLOG_CREATED_TS, AUDITLOG_ACTION_CODE ,ID_PK, VERSION, CREATED_TS, DELETED_TS, UPDATED_TS, UPDATED_BY_USER, UPDATED_BY_TXN_ID, LEGALENTITY_IDPK, LE_ROLETYPE_REFKEY, ACCOUNT_IDPK, ROLE_ACTIVATION_DATE, ROLE_DEACTIVATION_DATE, DEACTIVATION_REASON_REFKEY, AGREEMENT_TYPE_REFKEY, AGREEMENT_TYPE_DESCRIPTION ) VALUES( uuid(), CURRENT_TIMESTAMP(6) , 'U', NEW.ID_PK, NEW.VERSION, NEW.CREATED_TS, NEW.DELETED_TS, NEW.UPDATED_TS, NEW.UPDATED_BY_USER, NEW.UPDATED_BY_TXN_ID, NEW.LEGALENTITY_IDPK, NEW.LE_ROLETYPE_REFKEY, NEW.ACCOUNT_IDPK, NEW.ROLE_ACTIVATION_DATE, NEW.ROLE_DEACTIVATION_DATE, NEW.DEACTIVATION_REASON_REFKEY, NEW.AGREEMENT_TYPE_REFKEY, NEW.AGREEMENT_TYPE_DESCRIPTION); END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger yug_owner.U_LE_ADDRESS_ASSOC
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `U_LE_ADDRESS_ASSOC` AFTER UPDATE ON `le_address_assoc` FOR EACH ROW BEGIN INSERT INTO AL_LE_ADDRESS_ASSOC ( AUDITLOG_ID_PK, AUDITLOG_CREATED_TS, AUDITLOG_ACTION_CODE ,ID_PK, VERSION, CREATED_TS, DELETED_TS, UPDATED_TS, UPDATED_BY_USER, UPDATED_BY_TXN_ID, LEGALENTITY_IDPK, ADDRESS_IDPK, ADDRESS_TYPE_REFKEY, ADDRESS_SUBTYPE_REFKEY, PREFERRED_FLAG, SOLICITATION_FLAG ) VALUES( uuid(), CURRENT_TIMESTAMP(6) , 'U', NEW.ID_PK, NEW.VERSION, NEW.CREATED_TS, NEW.DELETED_TS, NEW.UPDATED_TS, NEW.UPDATED_BY_USER, NEW.UPDATED_BY_TXN_ID, NEW.LEGALENTITY_IDPK, NEW.ADDRESS_IDPK, NEW.ADDRESS_TYPE_REFKEY, NEW.ADDRESS_SUBTYPE_REFKEY, NEW.PREFERRED_FLAG, NEW.SOLICITATION_FLAG); END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger yug_owner.U_LE_CORPORATION
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `U_LE_CORPORATION` AFTER UPDATE ON `le_corporation` FOR EACH ROW BEGIN INSERT INTO AL_LE_CORPORATION ( AUDITLOG_ID_PK, AUDITLOG_CREATED_TS, AUDITLOG_ACTION_CODE ,LEGALENTITY_IDPK, VERSION, CREATED_TS, DELETED_TS, UPDATED_TS, UPDATED_BY_USER, UPDATED_BY_TXN_ID, CLASSIFICATION_CODE_REFKEY, INDUSTRY_CODE_REFKEY, GOVT_REGISTRATION_DATE, COUNTRY_REGISTRATION_REFKEY, NOTPROFIT_FLAG ) VALUES( uuid(), CURRENT_TIMESTAMP(6) , 'U', NEW.LEGALENTITY_IDPK, NEW.VERSION, NEW.CREATED_TS, NEW.DELETED_TS, NEW.UPDATED_TS, NEW.UPDATED_BY_USER, NEW.UPDATED_BY_TXN_ID, NEW.CLASSIFICATION_CODE_REFKEY, NEW.INDUSTRY_CODE_REFKEY, NEW.GOVT_REGISTRATION_DATE, NEW.COUNTRY_REGISTRATION_REFKEY, NEW.NOTPROFIT_FLAG); END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger yug_owner.U_LE_IDENTIFIER_KYC_REGISTRY
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `U_LE_IDENTIFIER_KYC_REGISTRY` AFTER UPDATE ON `le_identifier_kyc_registry` FOR EACH ROW BEGIN INSERT INTO AL_LE_IDENTIFIER_KYC_REGISTRY ( AUDITLOG_ID_PK, AUDITLOG_CREATED_TS, AUDITLOG_ACTION_CODE ,ID_PK, VERSION, CREATED_TS, DELETED_TS, UPDATED_TS, UPDATED_BY_USER, UPDATED_BY_TXN_ID, IDENTIFICATION_TYPE_REFKEY, IDENTIFICATION_NUMBER, LEGALENTITY_IDPK, DOCUMENT, ISSUED_BY, ID_CONSIDERED_FOR_KYC_FLAG, ISSUED_DATE, SOURCE_SYSTEM_REFKEY, IDENTITY_DESCRIPTION ) VALUES( uuid(), CURRENT_TIMESTAMP(6) , 'U', NEW.ID_PK, NEW.VERSION, NEW.CREATED_TS, NEW.DELETED_TS, NEW.UPDATED_TS, NEW.UPDATED_BY_USER, NEW.UPDATED_BY_TXN_ID, NEW.IDENTIFICATION_TYPE_REFKEY, NEW.IDENTIFICATION_NUMBER, NEW.LEGALENTITY_IDPK, NEW.DOCUMENT, NEW.ISSUED_BY, NEW.ID_CONSIDERED_FOR_KYC_FLAG, NEW.ISSUED_DATE, NEW.SOURCE_SYSTEM_REFKEY, NEW.IDENTITY_DESCRIPTION); END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger yug_owner.U_LE_PERSON
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `U_LE_PERSON` AFTER UPDATE ON `le_person` FOR EACH ROW BEGIN INSERT INTO AL_LE_PERSON ( AUDITLOG_ID_PK, AUDITLOG_CREATED_TS, AUDITLOG_ACTION_CODE ,LEGALENTITY_IDPK, VERSION, CREATED_TS, DELETED_TS, UPDATED_TS, UPDATED_BY_USER, UPDATED_BY_TXN_ID, PERSON_TYPE_REFKEY, GENDER_REFKEY, DATE_OF_BIRTH, COUNTRY_OF_BIRTH__REFKEY, COUNTRY_CITIZENSHIP_REFKEY, COUNTRY_OF_DOMICILE__REFKEY, MARITAL_STATUS, HIGHEST_EDU_QUAL_REFKEY, IS_DECEASED_FLAG, DECEASED_DATE, IS_HANDICAPPED_FLAG, HANDICAPPED_SINCE_DATE, NUMBER_OF_DEPENDENTS, NUMBER_OF_CHILDREN, PREFERRED_LANGUAGE_REFKEY ) VALUES( uuid(), CURRENT_TIMESTAMP(6) , 'U', NEW.LEGALENTITY_IDPK, NEW.VERSION, NEW.CREATED_TS, NEW.DELETED_TS, NEW.UPDATED_TS, NEW.UPDATED_BY_USER, NEW.UPDATED_BY_TXN_ID, NEW.PERSON_TYPE_REFKEY, NEW.GENDER_REFKEY, NEW.DATE_OF_BIRTH, NEW.COUNTRY_OF_BIRTH__REFKEY, NEW.COUNTRY_CITIZENSHIP_REFKEY, NEW.COUNTRY_OF_DOMICILE__REFKEY, NEW.MARITAL_STATUS, NEW.HIGHEST_EDU_QUAL_REFKEY, NEW.IS_DECEASED_FLAG, NEW.DECEASED_DATE, NEW.IS_HANDICAPPED_FLAG, NEW.HANDICAPPED_SINCE_DATE, NEW.NUMBER_OF_DEPENDENTS, NEW.NUMBER_OF_CHILDREN, NEW.PREFERRED_LANGUAGE_REFKEY); END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger yug_owner.U_LE_PHONE_ASSOC
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `U_LE_PHONE_ASSOC` AFTER UPDATE ON `le_phone_assoc` FOR EACH ROW BEGIN INSERT INTO AL_LE_PHONE_ASSOC ( AUDITLOG_ID_PK, AUDITLOG_CREATED_TS, AUDITLOG_ACTION_CODE ,ID_PK, VERSION, CREATED_TS, DELETED_TS, UPDATED_TS, UPDATED_BY_USER, UPDATED_BY_TXN_ID, LEGALENTITY_IDPK, PHONE_TYPE_REFKEY, PHONE_SUBTYPE_REFKEY, PREFERRED_FLAG, PHONE_NUMBER, PHONE_STANDARDIZED_IDPK ) VALUES( uuid(), CURRENT_TIMESTAMP(6) , 'U', NEW.ID_PK, NEW.VERSION, NEW.CREATED_TS, NEW.DELETED_TS, NEW.UPDATED_TS, NEW.UPDATED_BY_USER, NEW.UPDATED_BY_TXN_ID, NEW.LEGALENTITY_IDPK, NEW.PHONE_TYPE_REFKEY, NEW.PHONE_SUBTYPE_REFKEY, NEW.PREFERRED_FLAG, NEW.PHONE_NUMBER, NEW.PHONE_STANDARDIZED_IDPK); END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger yug_owner.U_LE_PREFERENCES
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `U_LE_PREFERENCES` AFTER UPDATE ON `le_preferences` FOR EACH ROW BEGIN INSERT INTO AL_LE_PREFERENCES ( AUDITLOG_ID_PK, AUDITLOG_CREATED_TS, AUDITLOG_ACTION_CODE ,ID_PK, VERSION, CREATED_TS, DELETED_TS, UPDATED_TS, UPDATED_BY_USER, UPDATED_BY_TXN_ID, LEGALENTITY_IDPK, PREFERENCE_TYPE_REFKEY, PREF_FLAG, PREF_DESCRIPTION ) VALUES( uuid(), CURRENT_TIMESTAMP(6) , 'U', NEW.ID_PK, NEW.VERSION, NEW.CREATED_TS, NEW.DELETED_TS, NEW.UPDATED_TS, NEW.UPDATED_BY_USER, NEW.UPDATED_BY_TXN_ID, NEW.LEGALENTITY_IDPK, NEW.PREFERENCE_TYPE_REFKEY, NEW.PREF_FLAG, NEW.PREF_DESCRIPTION); END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger yug_owner.U_LE_PROPERTY_ASSOC
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `U_LE_PROPERTY_ASSOC` AFTER UPDATE ON `le_property_assoc` FOR EACH ROW BEGIN INSERT INTO AL_LE_PROPERTY_ASSOC ( AUDITLOG_ID_PK, AUDITLOG_CREATED_TS, AUDITLOG_ACTION_CODE ,ID_PK, VERSION, CREATED_TS, DELETED_TS, UPDATED_TS, UPDATED_BY_USER, UPDATED_BY_TXN_ID, PROPERTY_IDPK, LEGALENTITY_IDPK, PROPERTY_LE_RELTYPE_REFKEY, DESCRIPTION ) VALUES( uuid(), CURRENT_TIMESTAMP(6) , 'U', NEW.ID_PK, NEW.VERSION, NEW.CREATED_TS, NEW.DELETED_TS, NEW.UPDATED_TS, NEW.UPDATED_BY_USER, NEW.UPDATED_BY_TXN_ID, NEW.PROPERTY_IDPK, NEW.LEGALENTITY_IDPK, NEW.PROPERTY_LE_RELTYPE_REFKEY, NEW.DESCRIPTION); END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger yug_owner.U_LE_SYSTEM_KEYS_REGISTRY
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `U_LE_SYSTEM_KEYS_REGISTRY` AFTER UPDATE ON `le_system_keys_registry` FOR EACH ROW BEGIN INSERT INTO AL_LE_SYSTEM_KEYS_REGISTRY ( AUDITLOG_ID_PK, AUDITLOG_CREATED_TS, AUDITLOG_ACTION_CODE ,ID_PK, VERSION, CREATED_TS, DELETED_TS, UPDATED_TS, UPDATED_BY_USER, UPDATED_BY_TXN_ID, SOURCE_SYSTEM_REFKEY, REFERENCE_ID, LEGALENTITY_IDPK, STATUS_IN_SOURCE_REFKEY, DESCRIPTION ) VALUES( uuid(), CURRENT_TIMESTAMP(6) , 'U', NEW.ID_PK, NEW.VERSION, NEW.CREATED_TS, NEW.DELETED_TS, NEW.UPDATED_TS, NEW.UPDATED_BY_USER, NEW.UPDATED_BY_TXN_ID, NEW.SOURCE_SYSTEM_REFKEY, NEW.REFERENCE_ID, NEW.LEGALENTITY_IDPK, NEW.STATUS_IN_SOURCE_REFKEY, NEW.DESCRIPTION); END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger yug_owner.U_LE_TO_LE_RELATIONSHIP
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `U_LE_TO_LE_RELATIONSHIP` AFTER UPDATE ON `le_to_le_relationship` FOR EACH ROW BEGIN INSERT INTO AL_LE_TO_LE_RELATIONSHIP ( AUDITLOG_ID_PK, AUDITLOG_CREATED_TS, AUDITLOG_ACTION_CODE ,ID_PK, VERSION, CREATED_TS, DELETED_TS, UPDATED_TS, UPDATED_BY_USER, UPDATED_BY_TXN_ID, FROM_LEGALENTITY_IDPK, TO_LEGALENTITY_IDPK, LE_RELATIONSHIP_TYPE_REFKEY, RELATIONSHIP_STATUS_REFKEY, RELATIONSHIP_DESCRIPTION ) VALUES( uuid(), CURRENT_TIMESTAMP(6) , 'U', NEW.ID_PK, NEW.VERSION, NEW.CREATED_TS, NEW.DELETED_TS, NEW.UPDATED_TS, NEW.UPDATED_BY_USER, NEW.UPDATED_BY_TXN_ID, NEW.FROM_LEGALENTITY_IDPK, NEW.TO_LEGALENTITY_IDPK, NEW.LE_RELATIONSHIP_TYPE_REFKEY, NEW.RELATIONSHIP_STATUS_REFKEY, NEW.RELATIONSHIP_DESCRIPTION); END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger yug_owner.U_LE_VEHICLE_ASSOC
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `U_LE_VEHICLE_ASSOC` AFTER UPDATE ON `le_vehicle_assoc` FOR EACH ROW BEGIN INSERT INTO AL_LE_VEHICLE_ASSOC ( AUDITLOG_ID_PK, AUDITLOG_CREATED_TS, AUDITLOG_ACTION_CODE ,ID_PK, VERSION, CREATED_TS, DELETED_TS, UPDATED_TS, UPDATED_BY_USER, UPDATED_BY_TXN_ID, LEGALENTITY_IDPK, LE_ROLETYPE_REFKEY, ACCOUNT_IDPK, ROLE_ACTIVATION_DATE, ROLE_DEACTIVATION_DATE, DEACTIVATION_REASON_REFKEY, AGREEMENT_TYPE_REFKEY, AGREEMENT_TYPE_DESCRIPTION, VEHICLE_IDPK ) VALUES( uuid(), CURRENT_TIMESTAMP(6) , 'U', NEW.ID_PK, NEW.VERSION, NEW.CREATED_TS, NEW.DELETED_TS, NEW.UPDATED_TS, NEW.UPDATED_BY_USER, NEW.UPDATED_BY_TXN_ID, NEW.LEGALENTITY_IDPK, NEW.LE_ROLETYPE_REFKEY, NEW.ACCOUNT_IDPK, NEW.ROLE_ACTIVATION_DATE, NEW.ROLE_DEACTIVATION_DATE, NEW.DEACTIVATION_REASON_REFKEY, NEW.AGREEMENT_TYPE_REFKEY, NEW.AGREEMENT_TYPE_DESCRIPTION, NEW.VEHICLE_IDPK); END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger yug_owner.U_MATCH_CANDIDATE_LE_REGISTRY
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `U_MATCH_CANDIDATE_LE_REGISTRY` AFTER UPDATE ON `match_candidate_le_registry` FOR EACH ROW BEGIN INSERT INTO AL_MATCH_CANDIDATE_LE_REGISTRY ( AUDITLOG_ID_PK, AUDITLOG_CREATED_TS, AUDITLOG_ACTION_CODE ,ID_PK, VERSION, CREATED_TS, DELETED_TS, UPDATED_TS, UPDATED_BY_USER, UPDATED_BY_TXN_ID, LEGALENTITY_IDPK, CANDIDATE_LEGALENTITYIDPK, MATCH_PATTERN, MATCH_PROPOSED_ACTION_REFKEY, MATCH_ACTIONSTATUS_REFKEY, MATCH_PERCENTAGE_DESCRIPTION ) VALUES( uuid(), CURRENT_TIMESTAMP(6) , 'U', NEW.ID_PK, NEW.VERSION, NEW.CREATED_TS, NEW.DELETED_TS, NEW.UPDATED_TS, NEW.UPDATED_BY_USER, NEW.UPDATED_BY_TXN_ID, NEW.LEGALENTITY_IDPK, NEW.CANDIDATE_LEGALENTITYIDPK, NEW.MATCH_PATTERN, NEW.MATCH_PROPOSED_ACTION_REFKEY, NEW.MATCH_ACTIONSTATUS_REFKEY, NEW.MATCH_PERCENTAGE_DESCRIPTION); END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger yug_owner.U_MATCH_MERGED_LE_ASSOC
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `U_MATCH_MERGED_LE_ASSOC` AFTER UPDATE ON `match_merged_le_assoc` FOR EACH ROW BEGIN INSERT INTO AL_MATCH_MERGED_LE_ASSOC ( AUDITLOG_ID_PK, AUDITLOG_CREATED_TS, AUDITLOG_ACTION_CODE ,ID_PK, VERSION, CREATED_TS, DELETED_TS, UPDATED_TS, UPDATED_BY_USER, UPDATED_BY_TXN_ID, SURVIVOR_LEGALENTITY_IDPK, MERGED_LEGALENTITY_IDPK, MERGE_REASON_REFKEY, COMMENTS ) VALUES( uuid(), CURRENT_TIMESTAMP(6) , 'U', NEW.ID_PK, NEW.VERSION, NEW.CREATED_TS, NEW.DELETED_TS, NEW.UPDATED_TS, NEW.UPDATED_BY_USER, NEW.UPDATED_BY_TXN_ID, NEW.SURVIVOR_LEGALENTITY_IDPK, NEW.MERGED_LEGALENTITY_IDPK, NEW.MERGE_REASON_REFKEY, NEW.COMMENTS); END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger yug_owner.U_MISCELLANEOUS_INFO
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `U_MISCELLANEOUS_INFO` AFTER UPDATE ON `miscellaneous_info` FOR EACH ROW BEGIN INSERT INTO AL_MISCELLANEOUS_INFO ( AUDITLOG_ID_PK, AUDITLOG_CREATED_TS, AUDITLOG_ACTION_CODE ,ID_PK, VERSION, CREATED_TS, DELETED_TS, UPDATED_TS, UPDATED_BY_USER, UPDATED_BY_TXN_ID, ENTITY_OBJECT_TYPE_REFKEY, ENTITY_IDPK, NAME1, VALUE1, NAME2, VALUE2, NAME3, VALUE3, NAME4, VALUE4, NAME5, VALUE5, NAME6, VALUE6, NAME7, VALUE7, NAME8, VALUE8, NAME9, VALUE9, NAME10, VALUE10 ) VALUES( uuid(), CURRENT_TIMESTAMP(6) , 'U', NEW.ID_PK, NEW.VERSION, NEW.CREATED_TS, NEW.DELETED_TS, NEW.UPDATED_TS, NEW.UPDATED_BY_USER, NEW.UPDATED_BY_TXN_ID, NEW.ENTITY_OBJECT_TYPE_REFKEY, NEW.ENTITY_IDPK, NEW.NAME1, NEW.VALUE1, NEW.NAME2, NEW.VALUE2, NEW.NAME3, NEW.VALUE3, NEW.NAME4, NEW.VALUE4, NEW.NAME5, NEW.VALUE5, NEW.NAME6, NEW.VALUE6, NEW.NAME7, NEW.VALUE7, NEW.NAME8, NEW.VALUE8, NEW.NAME9, NEW.VALUE9, NEW.NAME10, NEW.VALUE10); END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger yug_owner.U_PERSONNAMES
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `U_PERSONNAMES` AFTER UPDATE ON `personnames` FOR EACH ROW BEGIN INSERT INTO AL_PERSONNAMES ( AUDITLOG_ID_PK, AUDITLOG_CREATED_TS, AUDITLOG_ACTION_CODE ,ID_PK, VERSION, CREATED_TS, DELETED_TS, UPDATED_TS, UPDATED_BY_USER, UPDATED_BY_TXN_ID, LEGALENTITY_IDPK, PERSONNAME_TYPE_REFKEY, PREFIX_NAME_REFKEY, PREFIX_MISC, NAME_ONE, NAME_TWO, NAME_THREE, NAME_FOUR, LAST_NAME, NICK_NAME, POPULAR_NAME, SUFFIX_NAME_REFKEY, SUFFIX_MISC, NAME_STANDARDISED_FLAG, SOURCE_SYSTEM_REFKEY, PHONETIC_NAME_ONE, PHONETIC_NAME_TWO, PHONETIC_NAME_THREE, PHONETIC_LAST_NAME ) VALUES( uuid(), CURRENT_TIMESTAMP(6) , 'U', NEW.ID_PK, NEW.VERSION, NEW.CREATED_TS, NEW.DELETED_TS, NEW.UPDATED_TS, NEW.UPDATED_BY_USER, NEW.UPDATED_BY_TXN_ID, NEW.LEGALENTITY_IDPK, NEW.PERSONNAME_TYPE_REFKEY, NEW.PREFIX_NAME_REFKEY, NEW.PREFIX_MISC, NEW.NAME_ONE, NEW.NAME_TWO, NEW.NAME_THREE, NEW.NAME_FOUR, NEW.LAST_NAME, NEW.NICK_NAME, NEW.POPULAR_NAME, NEW.SUFFIX_NAME_REFKEY, NEW.SUFFIX_MISC, NEW.NAME_STANDARDISED_FLAG, NEW.SOURCE_SYSTEM_REFKEY, NEW.PHONETIC_NAME_ONE, NEW.PHONETIC_NAME_TWO, NEW.PHONETIC_NAME_THREE, NEW.PHONETIC_LAST_NAME); END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger yug_owner.U_PHONE_STANDARDIZED
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `U_PHONE_STANDARDIZED` AFTER UPDATE ON `phone_standardized` FOR EACH ROW BEGIN INSERT INTO AL_PHONE_STANDARDIZED ( AUDITLOG_ID_PK, AUDITLOG_CREATED_TS, AUDITLOG_ACTION_CODE ,ID_PK, VERSION, CREATED_TS, DELETED_TS, UPDATED_TS, UPDATED_BY_USER, UPDATED_BY_TXN_ID, ITU_COUNTRY_CALLING_CODE, AREA_CODE, EXCHANGE, PHONE_NUMBER, EXTENSION ) VALUES( uuid(), CURRENT_TIMESTAMP(6) , 'U', NEW.ID_PK, NEW.VERSION, NEW.CREATED_TS, NEW.DELETED_TS, NEW.UPDATED_TS, NEW.UPDATED_BY_USER, NEW.UPDATED_BY_TXN_ID, NEW.ITU_COUNTRY_CALLING_CODE, NEW.AREA_CODE, NEW.EXCHANGE, NEW.PHONE_NUMBER, NEW.EXTENSION); END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger yug_owner.U_PROPERTY
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `U_PROPERTY` AFTER UPDATE ON `property` FOR EACH ROW BEGIN INSERT INTO AL_PROPERTY ( AUDITLOG_ID_PK, AUDITLOG_CREATED_TS, AUDITLOG_ACTION_CODE ,ID_PK, VERSION, CREATED_TS, DELETED_TS, UPDATED_TS, UPDATED_BY_USER, UPDATED_BY_TXN_ID, PROPERTY_NAME, ADDRESS_IDPK ) VALUES( uuid(), CURRENT_TIMESTAMP(6) , 'U', NEW.ID_PK, NEW.VERSION, NEW.CREATED_TS, NEW.DELETED_TS, NEW.UPDATED_TS, NEW.UPDATED_BY_USER, NEW.UPDATED_BY_TXN_ID, NEW.PROPERTY_NAME, NEW.ADDRESS_IDPK); END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger yug_owner.U_REF_ACCOUNT_MDM_STATUS
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `U_REF_ACCOUNT_MDM_STATUS` AFTER UPDATE ON `ref_account_mdm_status` FOR EACH ROW BEGIN INSERT INTO AL_REF_ACCOUNT_MDM_STATUS ( AUDITLOG_ID_PK, AUDITLOG_CREATED_TS, AUDITLOG_ACTION_CODE ,ID_PK, VERSION, CREATED_TS, DELETED_TS, UPDATED_TS, UPDATED_BY_USER, UPDATED_BY_TXN_ID, CONFIG_LANGUAGE_CODE_KEY, `KEY`, VALUE, DESCRIPTION ) VALUES( uuid(), CURRENT_TIMESTAMP(6) , 'U', NEW.ID_PK, NEW.VERSION, NEW.CREATED_TS, NEW.DELETED_TS, NEW.UPDATED_TS, NEW.UPDATED_BY_USER, NEW.UPDATED_BY_TXN_ID, NEW.CONFIG_LANGUAGE_CODE_KEY, NEW.KEY, NEW.VALUE, NEW.DESCRIPTION); END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger yug_owner.U_REF_ACCOUNT_SOURCE_STATUS
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `U_REF_ACCOUNT_SOURCE_STATUS` AFTER UPDATE ON `ref_account_source_status` FOR EACH ROW BEGIN INSERT INTO AL_REF_ACCOUNT_SOURCE_STATUS ( AUDITLOG_ID_PK, AUDITLOG_CREATED_TS, AUDITLOG_ACTION_CODE ,ID_PK, VERSION, CREATED_TS, DELETED_TS, UPDATED_TS, UPDATED_BY_USER, UPDATED_BY_TXN_ID, CONFIG_LANGUAGE_CODE_KEY, `KEY`, VALUE, DESCRIPTION ) VALUES( uuid(), CURRENT_TIMESTAMP(6) , 'U', NEW.ID_PK, NEW.VERSION, NEW.CREATED_TS, NEW.DELETED_TS, NEW.UPDATED_TS, NEW.UPDATED_BY_USER, NEW.UPDATED_BY_TXN_ID, NEW.CONFIG_LANGUAGE_CODE_KEY, NEW.KEY, NEW.VALUE, NEW.DESCRIPTION); END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger yug_owner.U_REF_ADDRESS_SUBTYPE
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `U_REF_ADDRESS_SUBTYPE` AFTER UPDATE ON `ref_address_subtype` FOR EACH ROW BEGIN INSERT INTO AL_REF_ADDRESS_SUBTYPE ( AUDITLOG_ID_PK, AUDITLOG_CREATED_TS, AUDITLOG_ACTION_CODE ,ID_PK, VERSION, CREATED_TS, DELETED_TS, UPDATED_TS, UPDATED_BY_USER, UPDATED_BY_TXN_ID, CONFIG_LANGUAGE_CODE_KEY, `KEY`, VALUE, DESCRIPTION ) VALUES( uuid(), CURRENT_TIMESTAMP(6) , 'U', NEW.ID_PK, NEW.VERSION, NEW.CREATED_TS, NEW.DELETED_TS, NEW.UPDATED_TS, NEW.UPDATED_BY_USER, NEW.UPDATED_BY_TXN_ID, NEW.CONFIG_LANGUAGE_CODE_KEY, NEW.KEY, NEW.VALUE, NEW.DESCRIPTION); END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger yug_owner.U_REF_ADDRESS_TYPE
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `U_REF_ADDRESS_TYPE` AFTER UPDATE ON `ref_address_type` FOR EACH ROW BEGIN INSERT INTO AL_REF_ADDRESS_TYPE ( AUDITLOG_ID_PK, AUDITLOG_CREATED_TS, AUDITLOG_ACTION_CODE ,ID_PK, VERSION, CREATED_TS, DELETED_TS, UPDATED_TS, UPDATED_BY_USER, UPDATED_BY_TXN_ID, CONFIG_LANGUAGE_CODE_KEY, `KEY`, VALUE, DESCRIPTION ) VALUES( uuid(), CURRENT_TIMESTAMP(6) , 'U', NEW.ID_PK, NEW.VERSION, NEW.CREATED_TS, NEW.DELETED_TS, NEW.UPDATED_TS, NEW.UPDATED_BY_USER, NEW.UPDATED_BY_TXN_ID, NEW.CONFIG_LANGUAGE_CODE_KEY, NEW.KEY, NEW.VALUE, NEW.DESCRIPTION); END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger yug_owner.U_REF_AGREEMENT_TYPE
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `U_REF_AGREEMENT_TYPE` AFTER UPDATE ON `ref_agreement_type` FOR EACH ROW BEGIN INSERT INTO AL_REF_AGREEMENT_TYPE ( AUDITLOG_ID_PK, AUDITLOG_CREATED_TS, AUDITLOG_ACTION_CODE ,ID_PK, VERSION, CREATED_TS, DELETED_TS, UPDATED_TS, UPDATED_BY_USER, UPDATED_BY_TXN_ID, CONFIG_LANGUAGE_CODE_KEY, `KEY`, VALUE, DESCRIPTION ) VALUES( uuid(), CURRENT_TIMESTAMP(6) , 'U', NEW.ID_PK, NEW.VERSION, NEW.CREATED_TS, NEW.DELETED_TS, NEW.UPDATED_TS, NEW.UPDATED_BY_USER, NEW.UPDATED_BY_TXN_ID, NEW.CONFIG_LANGUAGE_CODE_KEY, NEW.KEY, NEW.VALUE, NEW.DESCRIPTION); END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger yug_owner.U_REF_ASSOC_TYPE
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `U_REF_ASSOC_TYPE` AFTER UPDATE ON `ref_assoc_type` FOR EACH ROW BEGIN INSERT INTO AL_REF_ASSOC_TYPE ( AUDITLOG_ID_PK, AUDITLOG_CREATED_TS, AUDITLOG_ACTION_CODE ,ID_PK, VERSION, CREATED_TS, DELETED_TS, UPDATED_TS, UPDATED_BY_USER, UPDATED_BY_TXN_ID, CONFIG_LANGUAGE_CODE_KEY, `KEY`, VALUE, DESCRIPTION ) VALUES( uuid(), CURRENT_TIMESTAMP(6) , 'U', NEW.ID_PK, NEW.VERSION, NEW.CREATED_TS, NEW.DELETED_TS, NEW.UPDATED_TS, NEW.UPDATED_BY_USER, NEW.UPDATED_BY_TXN_ID, NEW.CONFIG_LANGUAGE_CODE_KEY, NEW.KEY, NEW.VALUE, NEW.DESCRIPTION); END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger yug_owner.U_REF_BATCH_ACTION_STATUS
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `U_REF_BATCH_ACTION_STATUS` AFTER UPDATE ON `ref_batch_action_status` FOR EACH ROW BEGIN INSERT INTO AL_REF_BATCH_ACTION_STATUS ( AUDITLOG_ID_PK, AUDITLOG_CREATED_TS, AUDITLOG_ACTION_CODE ,ID_PK, VERSION, CREATED_TS, DELETED_TS, UPDATED_TS, UPDATED_BY_USER, UPDATED_BY_TXN_ID, CONFIG_LANGUAGE_CODE_KEY, `KEY`, VALUE, DESCRIPTION ) VALUES( uuid(), CURRENT_TIMESTAMP(6) , 'U', NEW.ID_PK, NEW.VERSION, NEW.CREATED_TS, NEW.DELETED_TS, NEW.UPDATED_TS, NEW.UPDATED_BY_USER, NEW.UPDATED_BY_TXN_ID, NEW.CONFIG_LANGUAGE_CODE_KEY, NEW.KEY, NEW.VALUE, NEW.DESCRIPTION); END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger yug_owner.U_REF_BATCH_PROPOSED_ACTION
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `U_REF_BATCH_PROPOSED_ACTION` AFTER UPDATE ON `ref_batch_proposed_action` FOR EACH ROW BEGIN INSERT INTO AL_REF_BATCH_PROPOSED_ACTION ( AUDITLOG_ID_PK, AUDITLOG_CREATED_TS, AUDITLOG_ACTION_CODE ,ID_PK, VERSION, CREATED_TS, DELETED_TS, UPDATED_TS, UPDATED_BY_USER, UPDATED_BY_TXN_ID, CONFIG_LANGUAGE_CODE_KEY, `KEY`, VALUE, DESCRIPTION ) VALUES( uuid(), CURRENT_TIMESTAMP(6) , 'U', NEW.ID_PK, NEW.VERSION, NEW.CREATED_TS, NEW.DELETED_TS, NEW.UPDATED_TS, NEW.UPDATED_BY_USER, NEW.UPDATED_BY_TXN_ID, NEW.CONFIG_LANGUAGE_CODE_KEY, NEW.KEY, NEW.VALUE, NEW.DESCRIPTION); END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger yug_owner.U_REF_BILLING_MODE_TYPE
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `U_REF_BILLING_MODE_TYPE` AFTER UPDATE ON `ref_billing_mode_type` FOR EACH ROW BEGIN INSERT INTO AL_REF_BILLING_MODE_TYPE ( AUDITLOG_ID_PK, AUDITLOG_CREATED_TS, AUDITLOG_ACTION_CODE ,ID_PK, VERSION, CREATED_TS, DELETED_TS, UPDATED_TS, UPDATED_BY_USER, UPDATED_BY_TXN_ID, CONFIG_LANGUAGE_CODE_KEY, `KEY`, VALUE, DESCRIPTION ) VALUES( uuid(), CURRENT_TIMESTAMP(6) , 'U', NEW.ID_PK, NEW.VERSION, NEW.CREATED_TS, NEW.DELETED_TS, NEW.UPDATED_TS, NEW.UPDATED_BY_USER, NEW.UPDATED_BY_TXN_ID, NEW.CONFIG_LANGUAGE_CODE_KEY, NEW.KEY, NEW.VALUE, NEW.DESCRIPTION); END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger yug_owner.U_REF_BRANCH_CODE
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `U_REF_BRANCH_CODE` AFTER UPDATE ON `ref_branch_code` FOR EACH ROW BEGIN INSERT INTO AL_REF_BRANCH_CODE ( AUDITLOG_ID_PK, AUDITLOG_CREATED_TS, AUDITLOG_ACTION_CODE ,ID_PK, VERSION, CREATED_TS, DELETED_TS, UPDATED_TS, UPDATED_BY_USER, UPDATED_BY_TXN_ID, CONFIG_LANGUAGE_CODE_KEY, `KEY`, VALUE, DESCRIPTION ) VALUES( uuid(), CURRENT_TIMESTAMP(6) , 'U', NEW.ID_PK, NEW.VERSION, NEW.CREATED_TS, NEW.DELETED_TS, NEW.UPDATED_TS, NEW.UPDATED_BY_USER, NEW.UPDATED_BY_TXN_ID, NEW.CONFIG_LANGUAGE_CODE_KEY, NEW.KEY, NEW.VALUE, NEW.DESCRIPTION); END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger yug_owner.U_REF_CLASSIFICATION_CODE
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `U_REF_CLASSIFICATION_CODE` AFTER UPDATE ON `ref_classification_code` FOR EACH ROW BEGIN INSERT INTO AL_REF_CLASSIFICATION_CODE ( AUDITLOG_ID_PK, AUDITLOG_CREATED_TS, AUDITLOG_ACTION_CODE ,ID_PK, VERSION, CREATED_TS, DELETED_TS, UPDATED_TS, UPDATED_BY_USER, UPDATED_BY_TXN_ID, CONFIG_LANGUAGE_CODE_KEY, `KEY`, VALUE, DESCRIPTION ) VALUES( uuid(), CURRENT_TIMESTAMP(6) , 'U', NEW.ID_PK, NEW.VERSION, NEW.CREATED_TS, NEW.DELETED_TS, NEW.UPDATED_TS, NEW.UPDATED_BY_USER, NEW.UPDATED_BY_TXN_ID, NEW.CONFIG_LANGUAGE_CODE_KEY, NEW.KEY, NEW.VALUE, NEW.DESCRIPTION); END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger yug_owner.U_REF_CORPORATION_NAME_TYPE
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `U_REF_CORPORATION_NAME_TYPE` AFTER UPDATE ON `ref_corporation_name_type` FOR EACH ROW BEGIN INSERT INTO AL_REF_CORPORATION_NAME_TYPE ( AUDITLOG_ID_PK, AUDITLOG_CREATED_TS, AUDITLOG_ACTION_CODE ,ID_PK, VERSION, CREATED_TS, DELETED_TS, UPDATED_TS, UPDATED_BY_USER, UPDATED_BY_TXN_ID, CONFIG_LANGUAGE_CODE_KEY, `KEY`, VALUE, DESCRIPTION ) VALUES( uuid(), CURRENT_TIMESTAMP(6) , 'U', NEW.ID_PK, NEW.VERSION, NEW.CREATED_TS, NEW.DELETED_TS, NEW.UPDATED_TS, NEW.UPDATED_BY_USER, NEW.UPDATED_BY_TXN_ID, NEW.CONFIG_LANGUAGE_CODE_KEY, NEW.KEY, NEW.VALUE, NEW.DESCRIPTION); END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger yug_owner.U_REF_CORPORATION_TYPE
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `U_REF_CORPORATION_TYPE` AFTER UPDATE ON `ref_corporation_type` FOR EACH ROW BEGIN INSERT INTO AL_REF_CORPORATION_TYPE ( AUDITLOG_ID_PK, AUDITLOG_CREATED_TS, AUDITLOG_ACTION_CODE ,ID_PK, VERSION, CREATED_TS, DELETED_TS, UPDATED_TS, UPDATED_BY_USER, UPDATED_BY_TXN_ID, CONFIG_LANGUAGE_CODE_KEY, `KEY`, VALUE, DESCRIPTION ) VALUES( uuid(), CURRENT_TIMESTAMP(6) , 'U', NEW.ID_PK, NEW.VERSION, NEW.CREATED_TS, NEW.DELETED_TS, NEW.UPDATED_TS, NEW.UPDATED_BY_USER, NEW.UPDATED_BY_TXN_ID, NEW.CONFIG_LANGUAGE_CODE_KEY, NEW.KEY, NEW.VALUE, NEW.DESCRIPTION); END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger yug_owner.U_REF_COUNTRY_ISO
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `U_REF_COUNTRY_ISO` AFTER UPDATE ON `ref_country_iso` FOR EACH ROW BEGIN INSERT INTO AL_REF_COUNTRY_ISO ( AUDITLOG_ID_PK, AUDITLOG_CREATED_TS, AUDITLOG_ACTION_CODE ,ID_PK, VERSION, CREATED_TS, DELETED_TS, UPDATED_TS, UPDATED_BY_USER, UPDATED_BY_TXN_ID, CONFIG_LANGUAGE_CODE_KEY, `KEY`, VALUE, DESCRIPTION ) VALUES( uuid(), CURRENT_TIMESTAMP(6) , 'U', NEW.ID_PK, NEW.VERSION, NEW.CREATED_TS, NEW.DELETED_TS, NEW.UPDATED_TS, NEW.UPDATED_BY_USER, NEW.UPDATED_BY_TXN_ID, NEW.CONFIG_LANGUAGE_CODE_KEY, NEW.KEY, NEW.VALUE, NEW.DESCRIPTION); END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger yug_owner.U_REF_CURRENCY
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `U_REF_CURRENCY` AFTER UPDATE ON `ref_currency` FOR EACH ROW BEGIN INSERT INTO AL_REF_CURRENCY ( AUDITLOG_ID_PK, AUDITLOG_CREATED_TS, AUDITLOG_ACTION_CODE ,ID_PK, VERSION, CREATED_TS, DELETED_TS, UPDATED_TS, UPDATED_BY_USER, UPDATED_BY_TXN_ID, CONFIG_LANGUAGE_CODE_KEY, `KEY`, VALUE, DESCRIPTION ) VALUES( uuid(), CURRENT_TIMESTAMP(6) , 'U', NEW.ID_PK, NEW.VERSION, NEW.CREATED_TS, NEW.DELETED_TS, NEW.UPDATED_TS, NEW.UPDATED_BY_USER, NEW.UPDATED_BY_TXN_ID, NEW.CONFIG_LANGUAGE_CODE_KEY, NEW.KEY, NEW.VALUE, NEW.DESCRIPTION); END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger yug_owner.U_REF_DEACTIVATION_REASON
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `U_REF_DEACTIVATION_REASON` AFTER UPDATE ON `ref_deactivation_reason` FOR EACH ROW BEGIN INSERT INTO AL_REF_DEACTIVATION_REASON ( AUDITLOG_ID_PK, AUDITLOG_CREATED_TS, AUDITLOG_ACTION_CODE ,ID_PK, VERSION, CREATED_TS, DELETED_TS, UPDATED_TS, UPDATED_BY_USER, UPDATED_BY_TXN_ID, CONFIG_LANGUAGE_CODE_KEY, `KEY`, VALUE, DESCRIPTION ) VALUES( uuid(), CURRENT_TIMESTAMP(6) , 'U', NEW.ID_PK, NEW.VERSION, NEW.CREATED_TS, NEW.DELETED_TS, NEW.UPDATED_TS, NEW.UPDATED_BY_USER, NEW.UPDATED_BY_TXN_ID, NEW.CONFIG_LANGUAGE_CODE_KEY, NEW.KEY, NEW.VALUE, NEW.DESCRIPTION); END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger yug_owner.U_REF_ENTITY_OBJECT_TYPE
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `U_REF_ENTITY_OBJECT_TYPE` AFTER UPDATE ON `ref_entity_object_type` FOR EACH ROW BEGIN INSERT INTO AL_REF_ENTITY_OBJECT_TYPE ( AUDITLOG_ID_PK, AUDITLOG_CREATED_TS, AUDITLOG_ACTION_CODE ,ID_PK, VERSION, CREATED_TS, DELETED_TS, UPDATED_TS, UPDATED_BY_USER, UPDATED_BY_TXN_ID, CONFIG_LANGUAGE_CODE_KEY, `KEY`, VALUE, DESCRIPTION ) VALUES( uuid(), CURRENT_TIMESTAMP(6) , 'U', NEW.ID_PK, NEW.VERSION, NEW.CREATED_TS, NEW.DELETED_TS, NEW.UPDATED_TS, NEW.UPDATED_BY_USER, NEW.UPDATED_BY_TXN_ID, NEW.CONFIG_LANGUAGE_CODE_KEY, NEW.KEY, NEW.VALUE, NEW.DESCRIPTION); END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger yug_owner.U_REF_GENDER
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `U_REF_GENDER` AFTER UPDATE ON `ref_gender` FOR EACH ROW BEGIN INSERT INTO AL_REF_GENDER ( AUDITLOG_ID_PK, AUDITLOG_CREATED_TS, AUDITLOG_ACTION_CODE ,ID_PK, VERSION, CREATED_TS, DELETED_TS, UPDATED_TS, UPDATED_BY_USER, UPDATED_BY_TXN_ID, CONFIG_LANGUAGE_CODE_KEY, `KEY`, VALUE, DESCRIPTION ) VALUES( uuid(), CURRENT_TIMESTAMP(6) , 'U', NEW.ID_PK, NEW.VERSION, NEW.CREATED_TS, NEW.DELETED_TS, NEW.UPDATED_TS, NEW.UPDATED_BY_USER, NEW.UPDATED_BY_TXN_ID, NEW.CONFIG_LANGUAGE_CODE_KEY, NEW.KEY, NEW.VALUE, NEW.DESCRIPTION); END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger yug_owner.U_REF_GROUP_SUBTYPE
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `U_REF_GROUP_SUBTYPE` AFTER UPDATE ON `ref_group_subtype` FOR EACH ROW BEGIN INSERT INTO AL_REF_GROUP_SUBTYPE ( AUDITLOG_ID_PK, AUDITLOG_CREATED_TS, AUDITLOG_ACTION_CODE ,ID_PK, VERSION, CREATED_TS, DELETED_TS, UPDATED_TS, UPDATED_BY_USER, UPDATED_BY_TXN_ID, CONFIG_LANGUAGE_CODE_KEY, `KEY`, VALUE, DESCRIPTION ) VALUES( uuid(), CURRENT_TIMESTAMP(6) , 'U', NEW.ID_PK, NEW.VERSION, NEW.CREATED_TS, NEW.DELETED_TS, NEW.UPDATED_TS, NEW.UPDATED_BY_USER, NEW.UPDATED_BY_TXN_ID, NEW.CONFIG_LANGUAGE_CODE_KEY, NEW.KEY, NEW.VALUE, NEW.DESCRIPTION); END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger yug_owner.U_REF_GROUP_TYPE
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `U_REF_GROUP_TYPE` AFTER UPDATE ON `ref_group_type` FOR EACH ROW BEGIN INSERT INTO AL_REF_GROUP_TYPE ( AUDITLOG_ID_PK, AUDITLOG_CREATED_TS, AUDITLOG_ACTION_CODE ,ID_PK, VERSION, CREATED_TS, DELETED_TS, UPDATED_TS, UPDATED_BY_USER, UPDATED_BY_TXN_ID, CONFIG_LANGUAGE_CODE_KEY, `KEY`, VALUE, DESCRIPTION ) VALUES( uuid(), CURRENT_TIMESTAMP(6) , 'U', NEW.ID_PK, NEW.VERSION, NEW.CREATED_TS, NEW.DELETED_TS, NEW.UPDATED_TS, NEW.UPDATED_BY_USER, NEW.UPDATED_BY_TXN_ID, NEW.CONFIG_LANGUAGE_CODE_KEY, NEW.KEY, NEW.VALUE, NEW.DESCRIPTION); END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger yug_owner.U_REF_HIGHEST_EDU_QUAL
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `U_REF_HIGHEST_EDU_QUAL` AFTER UPDATE ON `ref_highest_edu_qual` FOR EACH ROW BEGIN INSERT INTO AL_REF_HIGHEST_EDU_QUAL ( AUDITLOG_ID_PK, AUDITLOG_CREATED_TS, AUDITLOG_ACTION_CODE ,ID_PK, VERSION, CREATED_TS, DELETED_TS, UPDATED_TS, UPDATED_BY_USER, UPDATED_BY_TXN_ID, CONFIG_LANGUAGE_CODE_KEY, `KEY`, VALUE, DESCRIPTION ) VALUES( uuid(), CURRENT_TIMESTAMP(6) , 'U', NEW.ID_PK, NEW.VERSION, NEW.CREATED_TS, NEW.DELETED_TS, NEW.UPDATED_TS, NEW.UPDATED_BY_USER, NEW.UPDATED_BY_TXN_ID, NEW.CONFIG_LANGUAGE_CODE_KEY, NEW.KEY, NEW.VALUE, NEW.DESCRIPTION); END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger yug_owner.U_REF_IDENTIFICATION_TYPE
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `U_REF_IDENTIFICATION_TYPE` AFTER UPDATE ON `ref_identification_type` FOR EACH ROW BEGIN INSERT INTO AL_REF_IDENTIFICATION_TYPE ( AUDITLOG_ID_PK, AUDITLOG_CREATED_TS, AUDITLOG_ACTION_CODE ,ID_PK, VERSION, CREATED_TS, DELETED_TS, UPDATED_TS, UPDATED_BY_USER, UPDATED_BY_TXN_ID, CONFIG_LANGUAGE_CODE_KEY, `KEY`, VALUE, DESCRIPTION ) VALUES( uuid(), CURRENT_TIMESTAMP(6) , 'U', NEW.ID_PK, NEW.VERSION, NEW.CREATED_TS, NEW.DELETED_TS, NEW.UPDATED_TS, NEW.UPDATED_BY_USER, NEW.UPDATED_BY_TXN_ID, NEW.CONFIG_LANGUAGE_CODE_KEY, NEW.KEY, NEW.VALUE, NEW.DESCRIPTION); END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger yug_owner.U_REF_IMPORTANCE_TYPE
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `U_REF_IMPORTANCE_TYPE` AFTER UPDATE ON `ref_importance_type` FOR EACH ROW BEGIN INSERT INTO AL_REF_IMPORTANCE_TYPE ( AUDITLOG_ID_PK, AUDITLOG_CREATED_TS, AUDITLOG_ACTION_CODE ,ID_PK, VERSION, CREATED_TS, DELETED_TS, UPDATED_TS, UPDATED_BY_USER, UPDATED_BY_TXN_ID, CONFIG_LANGUAGE_CODE_KEY, `KEY`, VALUE, DESCRIPTION ) VALUES( uuid(), CURRENT_TIMESTAMP(6) , 'U', NEW.ID_PK, NEW.VERSION, NEW.CREATED_TS, NEW.DELETED_TS, NEW.UPDATED_TS, NEW.UPDATED_BY_USER, NEW.UPDATED_BY_TXN_ID, NEW.CONFIG_LANGUAGE_CODE_KEY, NEW.KEY, NEW.VALUE, NEW.DESCRIPTION); END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger yug_owner.U_REF_INACTIVATION_REASON
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `U_REF_INACTIVATION_REASON` AFTER UPDATE ON `ref_inactivation_reason` FOR EACH ROW BEGIN INSERT INTO AL_REF_INACTIVATION_REASON ( AUDITLOG_ID_PK, AUDITLOG_CREATED_TS, AUDITLOG_ACTION_CODE ,ID_PK, VERSION, CREATED_TS, DELETED_TS, UPDATED_TS, UPDATED_BY_USER, UPDATED_BY_TXN_ID, CONFIG_LANGUAGE_CODE_KEY, `KEY`, VALUE, DESCRIPTION ) VALUES( uuid(), CURRENT_TIMESTAMP(6) , 'U', NEW.ID_PK, NEW.VERSION, NEW.CREATED_TS, NEW.DELETED_TS, NEW.UPDATED_TS, NEW.UPDATED_BY_USER, NEW.UPDATED_BY_TXN_ID, NEW.CONFIG_LANGUAGE_CODE_KEY, NEW.KEY, NEW.VALUE, NEW.DESCRIPTION); END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger yug_owner.U_REF_INDUSTRY_CODE
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `U_REF_INDUSTRY_CODE` AFTER UPDATE ON `ref_industry_code` FOR EACH ROW BEGIN INSERT INTO AL_REF_INDUSTRY_CODE ( AUDITLOG_ID_PK, AUDITLOG_CREATED_TS, AUDITLOG_ACTION_CODE ,ID_PK, VERSION, CREATED_TS, DELETED_TS, UPDATED_TS, UPDATED_BY_USER, UPDATED_BY_TXN_ID, CONFIG_LANGUAGE_CODE_KEY, `KEY`, VALUE, DESCRIPTION ) VALUES( uuid(), CURRENT_TIMESTAMP(6) , 'U', NEW.ID_PK, NEW.VERSION, NEW.CREATED_TS, NEW.DELETED_TS, NEW.UPDATED_TS, NEW.UPDATED_BY_USER, NEW.UPDATED_BY_TXN_ID, NEW.CONFIG_LANGUAGE_CODE_KEY, NEW.KEY, NEW.VALUE, NEW.DESCRIPTION); END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger yug_owner.U_REF_LANGUAGE_CODE
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `U_REF_LANGUAGE_CODE` AFTER UPDATE ON `ref_language_code` FOR EACH ROW BEGIN INSERT INTO AL_REF_LANGUAGE_CODE ( AUDITLOG_ID_PK, AUDITLOG_CREATED_TS, AUDITLOG_ACTION_CODE ,ID_PK, VERSION, CREATED_TS, DELETED_TS, UPDATED_TS, UPDATED_BY_USER, UPDATED_BY_TXN_ID, CONFIG_LANGUAGE_CODE_KEY, `KEY`, VALUE, DESCRIPTION ) VALUES( uuid(), CURRENT_TIMESTAMP(6) , 'U', NEW.ID_PK, NEW.VERSION, NEW.CREATED_TS, NEW.DELETED_TS, NEW.UPDATED_TS, NEW.UPDATED_BY_USER, NEW.UPDATED_BY_TXN_ID, NEW.CONFIG_LANGUAGE_CODE_KEY, NEW.KEY, NEW.VALUE, NEW.DESCRIPTION); END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger yug_owner.U_REF_LE_RATING
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `U_REF_LE_RATING` AFTER UPDATE ON `ref_le_rating` FOR EACH ROW BEGIN INSERT INTO AL_REF_LE_RATING ( AUDITLOG_ID_PK, AUDITLOG_CREATED_TS, AUDITLOG_ACTION_CODE ,ID_PK, VERSION, CREATED_TS, DELETED_TS, UPDATED_TS, UPDATED_BY_USER, UPDATED_BY_TXN_ID, CONFIG_LANGUAGE_CODE_KEY, `KEY`, VALUE, DESCRIPTION ) VALUES( uuid(), CURRENT_TIMESTAMP(6) , 'U', NEW.ID_PK, NEW.VERSION, NEW.CREATED_TS, NEW.DELETED_TS, NEW.UPDATED_TS, NEW.UPDATED_BY_USER, NEW.UPDATED_BY_TXN_ID, NEW.CONFIG_LANGUAGE_CODE_KEY, NEW.KEY, NEW.VALUE, NEW.DESCRIPTION); END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger yug_owner.U_REF_LE_RELATIONSHIP_TYPE
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `U_REF_LE_RELATIONSHIP_TYPE` AFTER UPDATE ON `ref_le_relationship_type` FOR EACH ROW BEGIN INSERT INTO AL_REF_LE_RELATIONSHIP_TYPE ( AUDITLOG_ID_PK, AUDITLOG_CREATED_TS, AUDITLOG_ACTION_CODE ,ID_PK, VERSION, CREATED_TS, DELETED_TS, UPDATED_TS, UPDATED_BY_USER, UPDATED_BY_TXN_ID, CONFIG_LANGUAGE_CODE_KEY, `KEY`, VALUE, DESCRIPTION ) VALUES( uuid(), CURRENT_TIMESTAMP(6) , 'U', NEW.ID_PK, NEW.VERSION, NEW.CREATED_TS, NEW.DELETED_TS, NEW.UPDATED_TS, NEW.UPDATED_BY_USER, NEW.UPDATED_BY_TXN_ID, NEW.CONFIG_LANGUAGE_CODE_KEY, NEW.KEY, NEW.VALUE, NEW.DESCRIPTION); END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger yug_owner.U_REF_LE_ROLETYPE
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `U_REF_LE_ROLETYPE` AFTER UPDATE ON `ref_le_roletype` FOR EACH ROW BEGIN INSERT INTO AL_REF_LE_ROLETYPE ( AUDITLOG_ID_PK, AUDITLOG_CREATED_TS, AUDITLOG_ACTION_CODE ,ID_PK, VERSION, CREATED_TS, DELETED_TS, UPDATED_TS, UPDATED_BY_USER, UPDATED_BY_TXN_ID, CONFIG_LANGUAGE_CODE_KEY, `KEY`, VALUE, DESCRIPTION ) VALUES( uuid(), CURRENT_TIMESTAMP(6) , 'U', NEW.ID_PK, NEW.VERSION, NEW.CREATED_TS, NEW.DELETED_TS, NEW.UPDATED_TS, NEW.UPDATED_BY_USER, NEW.UPDATED_BY_TXN_ID, NEW.CONFIG_LANGUAGE_CODE_KEY, NEW.KEY, NEW.VALUE, NEW.DESCRIPTION); END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger yug_owner.U_REF_LOBTYPE
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `U_REF_LOBTYPE` AFTER UPDATE ON `ref_lobtype` FOR EACH ROW BEGIN INSERT INTO AL_REF_LOBTYPE ( AUDITLOG_ID_PK, AUDITLOG_CREATED_TS, AUDITLOG_ACTION_CODE ,ID_PK, VERSION, CREATED_TS, DELETED_TS, UPDATED_TS, UPDATED_BY_USER, UPDATED_BY_TXN_ID, CONFIG_LANGUAGE_CODE_KEY, `KEY`, VALUE, DESCRIPTION ) VALUES( uuid(), CURRENT_TIMESTAMP(6) , 'U', NEW.ID_PK, NEW.VERSION, NEW.CREATED_TS, NEW.DELETED_TS, NEW.UPDATED_TS, NEW.UPDATED_BY_USER, NEW.UPDATED_BY_TXN_ID, NEW.CONFIG_LANGUAGE_CODE_KEY, NEW.KEY, NEW.VALUE, NEW.DESCRIPTION); END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger yug_owner.U_REF_MATCH_ACTIONSTATUS
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `U_REF_MATCH_ACTIONSTATUS` AFTER UPDATE ON `ref_match_actionstatus` FOR EACH ROW BEGIN INSERT INTO AL_REF_MATCH_ACTIONSTATUS ( AUDITLOG_ID_PK, AUDITLOG_CREATED_TS, AUDITLOG_ACTION_CODE ,ID_PK, VERSION, CREATED_TS, DELETED_TS, UPDATED_TS, UPDATED_BY_USER, UPDATED_BY_TXN_ID, CONFIG_LANGUAGE_CODE_KEY, `KEY`, VALUE, DESCRIPTION ) VALUES( uuid(), CURRENT_TIMESTAMP(6) , 'U', NEW.ID_PK, NEW.VERSION, NEW.CREATED_TS, NEW.DELETED_TS, NEW.UPDATED_TS, NEW.UPDATED_BY_USER, NEW.UPDATED_BY_TXN_ID, NEW.CONFIG_LANGUAGE_CODE_KEY, NEW.KEY, NEW.VALUE, NEW.DESCRIPTION); END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger yug_owner.U_REF_MATCH_PROPOSED_ACTION
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `U_REF_MATCH_PROPOSED_ACTION` AFTER UPDATE ON `ref_match_proposed_action` FOR EACH ROW BEGIN INSERT INTO AL_REF_MATCH_PROPOSED_ACTION ( AUDITLOG_ID_PK, AUDITLOG_CREATED_TS, AUDITLOG_ACTION_CODE ,ID_PK, VERSION, CREATED_TS, DELETED_TS, UPDATED_TS, UPDATED_BY_USER, UPDATED_BY_TXN_ID, CONFIG_LANGUAGE_CODE_KEY, `KEY`, VALUE, DESCRIPTION ) VALUES( uuid(), CURRENT_TIMESTAMP(6) , 'U', NEW.ID_PK, NEW.VERSION, NEW.CREATED_TS, NEW.DELETED_TS, NEW.UPDATED_TS, NEW.UPDATED_BY_USER, NEW.UPDATED_BY_TXN_ID, NEW.CONFIG_LANGUAGE_CODE_KEY, NEW.KEY, NEW.VALUE, NEW.DESCRIPTION); END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger yug_owner.U_REF_MATCH_RESULT
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `U_REF_MATCH_RESULT` AFTER UPDATE ON `ref_match_result` FOR EACH ROW BEGIN INSERT INTO AL_REF_MATCH_RESULT ( AUDITLOG_ID_PK, AUDITLOG_CREATED_TS, AUDITLOG_ACTION_CODE ,ID_PK, VERSION, CREATED_TS, DELETED_TS, UPDATED_TS, UPDATED_BY_USER, UPDATED_BY_TXN_ID, CONFIG_LANGUAGE_CODE_KEY, `KEY`, VALUE, DESCRIPTION ) VALUES( uuid(), CURRENT_TIMESTAMP(6) , 'U', NEW.ID_PK, NEW.VERSION, NEW.CREATED_TS, NEW.DELETED_TS, NEW.UPDATED_TS, NEW.UPDATED_BY_USER, NEW.UPDATED_BY_TXN_ID, NEW.CONFIG_LANGUAGE_CODE_KEY, NEW.KEY, NEW.VALUE, NEW.DESCRIPTION); END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger yug_owner.U_REF_MATCH_SCORE
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `U_REF_MATCH_SCORE` AFTER UPDATE ON `ref_match_score` FOR EACH ROW BEGIN INSERT INTO AL_REF_MATCH_SCORE ( AUDITLOG_ID_PK, AUDITLOG_CREATED_TS, AUDITLOG_ACTION_CODE ,ID_PK, VERSION, CREATED_TS, DELETED_TS, UPDATED_TS, UPDATED_BY_USER, UPDATED_BY_TXN_ID, MATCH_ENTITY_OBJECT_NAME, MATCH_ATTR_PATTERN, MATCH_RESULT_REFKEY, MATCH_PROPOSED_ACTION_REFKEY, MATCH_ATTR_PATTERN_DESCR ) VALUES( uuid(), CURRENT_TIMESTAMP(6) , 'U', NEW.ID_PK, NEW.VERSION, NEW.CREATED_TS, NEW.DELETED_TS, NEW.UPDATED_TS, NEW.UPDATED_BY_USER, NEW.UPDATED_BY_TXN_ID, NEW.MATCH_ENTITY_OBJECT_NAME, NEW.MATCH_ATTR_PATTERN, NEW.MATCH_RESULT_REFKEY, NEW.MATCH_PROPOSED_ACTION_REFKEY, NEW.MATCH_ATTR_PATTERN_DESCR); END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger yug_owner.U_REF_MATCH_THRESHOLD
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `U_REF_MATCH_THRESHOLD` AFTER UPDATE ON `ref_match_threshold` FOR EACH ROW BEGIN INSERT INTO AL_REF_MATCH_THRESHOLD ( AUDITLOG_ID_PK, AUDITLOG_CREATED_TS, AUDITLOG_ACTION_CODE ,ID_PK, VERSION, CREATED_TS, DELETED_TS, UPDATED_TS, UPDATED_BY_USER, UPDATED_BY_TXN_ID, ATTR_BLOCK_NAME, MATCH_THRESHOLD, DECAY_THRESHOLD_IN_DAYS, DECAY_PERCENTAGE, MAX_DECAY_PERCENTAGE ) VALUES( uuid(), CURRENT_TIMESTAMP(6) , 'U', NEW.ID_PK, NEW.VERSION, NEW.CREATED_TS, NEW.DELETED_TS, NEW.UPDATED_TS, NEW.UPDATED_BY_USER, NEW.UPDATED_BY_TXN_ID, NEW.ATTR_BLOCK_NAME, NEW.MATCH_THRESHOLD, NEW.DECAY_THRESHOLD_IN_DAYS, NEW.DECAY_PERCENTAGE, NEW.MAX_DECAY_PERCENTAGE); END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger yug_owner.U_REF_MERGE_REASON
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `U_REF_MERGE_REASON` AFTER UPDATE ON `ref_merge_reason` FOR EACH ROW BEGIN INSERT INTO AL_REF_MERGE_REASON ( AUDITLOG_ID_PK, AUDITLOG_CREATED_TS, AUDITLOG_ACTION_CODE ,ID_PK, VERSION, CREATED_TS, DELETED_TS, UPDATED_TS, UPDATED_BY_USER, UPDATED_BY_TXN_ID, CONFIG_LANGUAGE_CODE_KEY, `KEY`, VALUE, DESCRIPTION ) VALUES( uuid(), CURRENT_TIMESTAMP(6) , 'U', NEW.ID_PK, NEW.VERSION, NEW.CREATED_TS, NEW.DELETED_TS, NEW.UPDATED_TS, NEW.UPDATED_BY_USER, NEW.UPDATED_BY_TXN_ID, NEW.CONFIG_LANGUAGE_CODE_KEY, NEW.KEY, NEW.VALUE, NEW.DESCRIPTION); END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger yug_owner.U_REF_PERSONNAME_TYPE
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `U_REF_PERSONNAME_TYPE` AFTER UPDATE ON `ref_personname_type` FOR EACH ROW BEGIN INSERT INTO AL_REF_PERSONNAME_TYPE ( AUDITLOG_ID_PK, AUDITLOG_CREATED_TS, AUDITLOG_ACTION_CODE ,ID_PK, VERSION, CREATED_TS, DELETED_TS, UPDATED_TS, UPDATED_BY_USER, UPDATED_BY_TXN_ID, CONFIG_LANGUAGE_CODE_KEY, `KEY`, VALUE, DESCRIPTION ) VALUES( uuid(), CURRENT_TIMESTAMP(6) , 'U', NEW.ID_PK, NEW.VERSION, NEW.CREATED_TS, NEW.DELETED_TS, NEW.UPDATED_TS, NEW.UPDATED_BY_USER, NEW.UPDATED_BY_TXN_ID, NEW.CONFIG_LANGUAGE_CODE_KEY, NEW.KEY, NEW.VALUE, NEW.DESCRIPTION); END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger yug_owner.U_REF_PERSON_TYPE
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `U_REF_PERSON_TYPE` AFTER UPDATE ON `ref_person_type` FOR EACH ROW BEGIN INSERT INTO AL_REF_PERSON_TYPE ( AUDITLOG_ID_PK, AUDITLOG_CREATED_TS, AUDITLOG_ACTION_CODE ,ID_PK, VERSION, CREATED_TS, DELETED_TS, UPDATED_TS, UPDATED_BY_USER, UPDATED_BY_TXN_ID, CONFIG_LANGUAGE_CODE_KEY, `KEY`, VALUE, DESCRIPTION ) VALUES( uuid(), CURRENT_TIMESTAMP(6) , 'U', NEW.ID_PK, NEW.VERSION, NEW.CREATED_TS, NEW.DELETED_TS, NEW.UPDATED_TS, NEW.UPDATED_BY_USER, NEW.UPDATED_BY_TXN_ID, NEW.CONFIG_LANGUAGE_CODE_KEY, NEW.KEY, NEW.VALUE, NEW.DESCRIPTION); END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger yug_owner.U_REF_PHONE_SUBTYPE
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `U_REF_PHONE_SUBTYPE` AFTER UPDATE ON `ref_phone_subtype` FOR EACH ROW BEGIN INSERT INTO AL_REF_PHONE_SUBTYPE ( AUDITLOG_ID_PK, AUDITLOG_CREATED_TS, AUDITLOG_ACTION_CODE ,ID_PK, VERSION, CREATED_TS, DELETED_TS, UPDATED_TS, UPDATED_BY_USER, UPDATED_BY_TXN_ID, CONFIG_LANGUAGE_CODE_KEY, `KEY`, VALUE, DESCRIPTION ) VALUES( uuid(), CURRENT_TIMESTAMP(6) , 'U', NEW.ID_PK, NEW.VERSION, NEW.CREATED_TS, NEW.DELETED_TS, NEW.UPDATED_TS, NEW.UPDATED_BY_USER, NEW.UPDATED_BY_TXN_ID, NEW.CONFIG_LANGUAGE_CODE_KEY, NEW.KEY, NEW.VALUE, NEW.DESCRIPTION); END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger yug_owner.U_REF_PHONE_TYPE
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `U_REF_PHONE_TYPE` AFTER UPDATE ON `ref_phone_type` FOR EACH ROW BEGIN INSERT INTO AL_REF_PHONE_TYPE ( AUDITLOG_ID_PK, AUDITLOG_CREATED_TS, AUDITLOG_ACTION_CODE ,ID_PK, VERSION, CREATED_TS, DELETED_TS, UPDATED_TS, UPDATED_BY_USER, UPDATED_BY_TXN_ID, CONFIG_LANGUAGE_CODE_KEY, `KEY`, VALUE, DESCRIPTION ) VALUES( uuid(), CURRENT_TIMESTAMP(6) , 'U', NEW.ID_PK, NEW.VERSION, NEW.CREATED_TS, NEW.DELETED_TS, NEW.UPDATED_TS, NEW.UPDATED_BY_USER, NEW.UPDATED_BY_TXN_ID, NEW.CONFIG_LANGUAGE_CODE_KEY, NEW.KEY, NEW.VALUE, NEW.DESCRIPTION); END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger yug_owner.U_REF_PREFERENCE_TYPE
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `U_REF_PREFERENCE_TYPE` AFTER UPDATE ON `ref_preference_type` FOR EACH ROW BEGIN INSERT INTO AL_REF_PREFERENCE_TYPE ( AUDITLOG_ID_PK, AUDITLOG_CREATED_TS, AUDITLOG_ACTION_CODE ,ID_PK, VERSION, CREATED_TS, DELETED_TS, UPDATED_TS, UPDATED_BY_USER, UPDATED_BY_TXN_ID, CONFIG_LANGUAGE_CODE_KEY, `KEY`, VALUE, DESCRIPTION ) VALUES( uuid(), CURRENT_TIMESTAMP(6) , 'U', NEW.ID_PK, NEW.VERSION, NEW.CREATED_TS, NEW.DELETED_TS, NEW.UPDATED_TS, NEW.UPDATED_BY_USER, NEW.UPDATED_BY_TXN_ID, NEW.CONFIG_LANGUAGE_CODE_KEY, NEW.KEY, NEW.VALUE, NEW.DESCRIPTION); END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger yug_owner.U_REF_PREFIX_NAME
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `U_REF_PREFIX_NAME` AFTER UPDATE ON `ref_prefix_name` FOR EACH ROW BEGIN INSERT INTO AL_REF_PREFIX_NAME ( AUDITLOG_ID_PK, AUDITLOG_CREATED_TS, AUDITLOG_ACTION_CODE ,ID_PK, VERSION, CREATED_TS, DELETED_TS, UPDATED_TS, UPDATED_BY_USER, UPDATED_BY_TXN_ID, CONFIG_LANGUAGE_CODE_KEY, `KEY`, VALUE, DESCRIPTION ) VALUES( uuid(), CURRENT_TIMESTAMP(6) , 'U', NEW.ID_PK, NEW.VERSION, NEW.CREATED_TS, NEW.DELETED_TS, NEW.UPDATED_TS, NEW.UPDATED_BY_USER, NEW.UPDATED_BY_TXN_ID, NEW.CONFIG_LANGUAGE_CODE_KEY, NEW.KEY, NEW.VALUE, NEW.DESCRIPTION); END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger yug_owner.U_REF_PROPERTY_LE_RELTYPE
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `U_REF_PROPERTY_LE_RELTYPE` AFTER UPDATE ON `ref_property_le_reltype` FOR EACH ROW BEGIN INSERT INTO AL_REF_PROPERTY_LE_RELTYPE ( AUDITLOG_ID_PK, AUDITLOG_CREATED_TS, AUDITLOG_ACTION_CODE ,ID_PK, VERSION, CREATED_TS, DELETED_TS, UPDATED_TS, UPDATED_BY_USER, UPDATED_BY_TXN_ID, CONFIG_LANGUAGE_CODE_KEY, `KEY`, VALUE, DESCRIPTION ) VALUES( uuid(), CURRENT_TIMESTAMP(6) , 'U', NEW.ID_PK, NEW.VERSION, NEW.CREATED_TS, NEW.DELETED_TS, NEW.UPDATED_TS, NEW.UPDATED_BY_USER, NEW.UPDATED_BY_TXN_ID, NEW.CONFIG_LANGUAGE_CODE_KEY, NEW.KEY, NEW.VALUE, NEW.DESCRIPTION); END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger yug_owner.U_REF_RELATIONSHIP_STATUS
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `U_REF_RELATIONSHIP_STATUS` AFTER UPDATE ON `ref_relationship_status` FOR EACH ROW BEGIN INSERT INTO AL_REF_RELATIONSHIP_STATUS ( AUDITLOG_ID_PK, AUDITLOG_CREATED_TS, AUDITLOG_ACTION_CODE ,ID_PK, VERSION, CREATED_TS, DELETED_TS, UPDATED_TS, UPDATED_BY_USER, UPDATED_BY_TXN_ID, CONFIG_LANGUAGE_CODE_KEY, `KEY`, VALUE, DESCRIPTION ) VALUES( uuid(), CURRENT_TIMESTAMP(6) , 'U', NEW.ID_PK, NEW.VERSION, NEW.CREATED_TS, NEW.DELETED_TS, NEW.UPDATED_TS, NEW.UPDATED_BY_USER, NEW.UPDATED_BY_TXN_ID, NEW.CONFIG_LANGUAGE_CODE_KEY, NEW.KEY, NEW.VALUE, NEW.DESCRIPTION); END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger yug_owner.U_REF_SOURCE_SYSTEM
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `U_REF_SOURCE_SYSTEM` AFTER UPDATE ON `ref_source_system` FOR EACH ROW BEGIN INSERT INTO AL_REF_SOURCE_SYSTEM ( AUDITLOG_ID_PK, AUDITLOG_CREATED_TS, AUDITLOG_ACTION_CODE ,ID_PK, VERSION, CREATED_TS, DELETED_TS, UPDATED_TS, UPDATED_BY_USER, UPDATED_BY_TXN_ID, CONFIG_LANGUAGE_CODE_KEY, `KEY`, VALUE, DESCRIPTION ) VALUES( uuid(), CURRENT_TIMESTAMP(6) , 'U', NEW.ID_PK, NEW.VERSION, NEW.CREATED_TS, NEW.DELETED_TS, NEW.UPDATED_TS, NEW.UPDATED_BY_USER, NEW.UPDATED_BY_TXN_ID, NEW.CONFIG_LANGUAGE_CODE_KEY, NEW.KEY, NEW.VALUE, NEW.DESCRIPTION); END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger yug_owner.U_REF_STATE_PROVINCE
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `U_REF_STATE_PROVINCE` AFTER UPDATE ON `ref_state_province` FOR EACH ROW BEGIN INSERT INTO AL_REF_STATE_PROVINCE ( AUDITLOG_ID_PK, AUDITLOG_CREATED_TS, AUDITLOG_ACTION_CODE ,ID_PK, VERSION, CREATED_TS, DELETED_TS, UPDATED_TS, UPDATED_BY_USER, UPDATED_BY_TXN_ID, CONFIG_LANGUAGE_CODE_KEY, `KEY`, VALUE, DESCRIPTION, COUNTRY_ISO_REFKEY ) VALUES( uuid(), CURRENT_TIMESTAMP(6) , 'U', NEW.ID_PK, NEW.VERSION, NEW.CREATED_TS, NEW.DELETED_TS, NEW.UPDATED_TS, NEW.UPDATED_BY_USER, NEW.UPDATED_BY_TXN_ID, NEW.CONFIG_LANGUAGE_CODE_KEY, NEW.KEY, NEW.VALUE, NEW.DESCRIPTION, NEW.COUNTRY_ISO_REFKEY); END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger yug_owner.U_REF_STATUS_IN_SOURCE
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `U_REF_STATUS_IN_SOURCE` AFTER UPDATE ON `ref_status_in_source` FOR EACH ROW BEGIN INSERT INTO AL_REF_STATUS_IN_SOURCE ( AUDITLOG_ID_PK, AUDITLOG_CREATED_TS, AUDITLOG_ACTION_CODE ,ID_PK, VERSION, CREATED_TS, DELETED_TS, UPDATED_TS, UPDATED_BY_USER, UPDATED_BY_TXN_ID, CONFIG_LANGUAGE_CODE_KEY, `KEY`, VALUE, DESCRIPTION ) VALUES( uuid(), CURRENT_TIMESTAMP(6) , 'U', NEW.ID_PK, NEW.VERSION, NEW.CREATED_TS, NEW.DELETED_TS, NEW.UPDATED_TS, NEW.UPDATED_BY_USER, NEW.UPDATED_BY_TXN_ID, NEW.CONFIG_LANGUAGE_CODE_KEY, NEW.KEY, NEW.VALUE, NEW.DESCRIPTION); END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger yug_owner.U_REF_STATUS_TYPE
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `U_REF_STATUS_TYPE` AFTER UPDATE ON `ref_status_type` FOR EACH ROW BEGIN INSERT INTO AL_REF_STATUS_TYPE ( AUDITLOG_ID_PK, AUDITLOG_CREATED_TS, AUDITLOG_ACTION_CODE ,ID_PK, VERSION, CREATED_TS, DELETED_TS, UPDATED_TS, UPDATED_BY_USER, UPDATED_BY_TXN_ID, CONFIG_LANGUAGE_CODE_KEY, `KEY`, VALUE, DESCRIPTION ) VALUES( uuid(), CURRENT_TIMESTAMP(6) , 'U', NEW.ID_PK, NEW.VERSION, NEW.CREATED_TS, NEW.DELETED_TS, NEW.UPDATED_TS, NEW.UPDATED_BY_USER, NEW.UPDATED_BY_TXN_ID, NEW.CONFIG_LANGUAGE_CODE_KEY, NEW.KEY, NEW.VALUE, NEW.DESCRIPTION); END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger yug_owner.U_REF_SUFFIX_NAME
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `U_REF_SUFFIX_NAME` AFTER UPDATE ON `ref_suffix_name` FOR EACH ROW BEGIN INSERT INTO AL_REF_SUFFIX_NAME ( AUDITLOG_ID_PK, AUDITLOG_CREATED_TS, AUDITLOG_ACTION_CODE ,ID_PK, VERSION, CREATED_TS, DELETED_TS, UPDATED_TS, UPDATED_BY_USER, UPDATED_BY_TXN_ID, CONFIG_LANGUAGE_CODE_KEY, `KEY`, VALUE, DESCRIPTION ) VALUES( uuid(), CURRENT_TIMESTAMP(6) , 'U', NEW.ID_PK, NEW.VERSION, NEW.CREATED_TS, NEW.DELETED_TS, NEW.UPDATED_TS, NEW.UPDATED_BY_USER, NEW.UPDATED_BY_TXN_ID, NEW.CONFIG_LANGUAGE_CODE_KEY, NEW.KEY, NEW.VALUE, NEW.DESCRIPTION); END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger yug_owner.U_REF_TERMINATION_REASON
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `U_REF_TERMINATION_REASON` AFTER UPDATE ON `ref_termination_reason` FOR EACH ROW BEGIN INSERT INTO AL_REF_TERMINATION_REASON ( AUDITLOG_ID_PK, AUDITLOG_CREATED_TS, AUDITLOG_ACTION_CODE ,ID_PK, VERSION, CREATED_TS, DELETED_TS, UPDATED_TS, UPDATED_BY_USER, UPDATED_BY_TXN_ID, CONFIG_LANGUAGE_CODE_KEY, `KEY`, VALUE, DESCRIPTION ) VALUES( uuid(), CURRENT_TIMESTAMP(6) , 'U', NEW.ID_PK, NEW.VERSION, NEW.CREATED_TS, NEW.DELETED_TS, NEW.UPDATED_TS, NEW.UPDATED_BY_USER, NEW.UPDATED_BY_TXN_ID, NEW.CONFIG_LANGUAGE_CODE_KEY, NEW.KEY, NEW.VALUE, NEW.DESCRIPTION); END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger yug_owner.U_VEHICLE
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `U_VEHICLE` AFTER UPDATE ON `vehicle` FOR EACH ROW BEGIN INSERT INTO AL_VEHICLE ( AUDITLOG_ID_PK, AUDITLOG_CREATED_TS, AUDITLOG_ACTION_CODE ,ID_PK, VERSION, CREATED_TS, DELETED_TS, UPDATED_TS, UPDATED_BY_USER, UPDATED_BY_TXN_ID, VIN_NUMBER, CHASSIS_NUMBER, MAKE, MODEL, YEAR, INTERIOR_COLOR, EXTERIOR_COLOR, REGISTRATION_NUMBER, COUNTRY_OF_REGISTRATION_REFKEY, INSURANCE_ISSUED_BY, INSURANCE_ISSUED_DATE, INSURANCE_EXPIRY_DATE ) VALUES( uuid(), CURRENT_TIMESTAMP(6) , 'U', NEW.ID_PK, NEW.VERSION, NEW.CREATED_TS, NEW.DELETED_TS, NEW.UPDATED_TS, NEW.UPDATED_BY_USER, NEW.UPDATED_BY_TXN_ID, NEW.VIN_NUMBER, NEW.CHASSIS_NUMBER, NEW.MAKE, NEW.MODEL, NEW.YEAR, NEW.INTERIOR_COLOR, NEW.EXTERIOR_COLOR, NEW.REGISTRATION_NUMBER, NEW.COUNTRY_OF_REGISTRATION_REFKEY, NEW.INSURANCE_ISSUED_BY, NEW.INSURANCE_ISSUED_DATE, NEW.INSURANCE_EXPIRY_DATE); END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;

CREATE SEQUENCE YUG_ERRORCODE_SEQ
  START WITH 100000
  MAXVALUE 1000000
  MINVALUE 1;


CREATE SEQUENCE YUG_REGISTRY_SEQ
  START WITH 100000
  MAXVALUE 1000000
  MINVALUE 1;
