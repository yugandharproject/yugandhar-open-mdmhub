# About Yugandhar Open Master Data Management (MDM) Hub Project
 

Master Data Management came a long way in last decade or so. There are currently more than 20 MDM solutions catering to various specializations of MDM like Customer Data Integration (CDI), Product Information Management (PIM), vendor and supplier management etc. However most of these solutions come with licensing costs amounting to thousands of dollar. To offer a completely free solution which would be made available through Apache 2.0 license, A Project is started in 2017 under the name ‘Yugandhar Open MDM Project’ to build Open Source MDM solutions catering to CDI, PIM and Data Governance Capabilities. Yugandhar in Sanskrit means Ever Lasting and the strongest of its time. Our vision is to build the strongest, Open Source, Multi Domain, Cross Industry and completely free MDM Solution.

We are happy to announce that the second release of the Yugandhar MDM Hub catering to CDI solution is built with Open source technologies like Spring and Hibernate etc, inbuilt data Model, 400+ ready to use services and having incredible Out of the Box capabilities is currently being distributed. We aim to make the current CDI offering the strongest and Planning to bring Data Stewardship and PIM solutions in upcoming years.


# Silent Features 
 - Providing Industry standard MDM solution for harmonization of
   Customer Master Data.
 - In built and Extendable Customer Master Data Model for maintaining
   Person and Organization master data along with Account, Address,
   Phone Number, Identifiers, Grouping, Vehicle, Preference, Property
   and party relationships etc.
 - Built using Open Source technologies – Spring  boot, Hibernate and
   JBoss Built on Service Oriented Architecture (SOA) having 450+
   prebuilt ready to use services
 - RESTful and MQ enabled
 - Rapid Development using Hibernate Reverse Engineering Tool and
   Yugandhar Code generation Templates (Freemarker).

# Capabilities
- Free and Open Data Model
- 450+ prebuilt ready to use services, 50+ Composite and 400 Base table services
- Supports embedded Web Server as well as JEE container web servers.
-- MDM Hub for EWS: The Yugandhar Open MDM Hub for EWS runs with Springboot embedded web Server like tomcat.
-- MDM Hub for JEEC: The Yugandhar Open MDM Hub for JEEC is built to be run on a Java Enterprise Edition Container (Web Server) like Red Hat Jboss.
- Preconfigured for Maria DB and Oracle, can be extended to support other databases like MySQL, DB2, NoSQL, PostgreSQL etc.
- Fully configured JTA transaction manager, ORM framework and activeMQ integration.
- Embedded and extendable data validations and business rules
- Multilingual Reference data management Capability
- Comprehensive application and performance logging using logback.
- User and role based Authorization
- Inquiry level and Pagination for retrieve and search services
- Audit Log for maintaining database row level transaction history
- Pluggable and UUID based Primary Key generator
- jsr107 compliant Ehcache based caching framework
- Integrated with Maven
- Event Manager
- Match and Merge framework
 

# Getting Started
We have provided comprehensive documentation about the project. Refer the documentation section at below github link

https://github.com/yugandharproject/yugandhar-open-mdmhub/tree/master/resources/documentation

You may start with the Architectural overview to understand the architectural components and capabilities of the MDM Hub. APIAndTransactionRefrenceGuide, DataModelGuide and DevelopementAndCustomizationGuide should follow the next. To take a dip dive on having hands on of running the applications refer the DevelopementEnvironmetSetup and CodeGenerationGuide.

 

# Prerequisites
## System Requirement
The supported configuration for hosting Yugandhar Open MDM Hub primarily depends on redhat jboss, Oracle, MariaDB and Springboot Framework configuration
Refer Springboot System Requirements here
https://docs.spring.io/spring-boot/docs/2.0.0.RELEASE/reference/htmlsingle/#getting-started-system-requirements

### Production Environment
We are summarizing the requirements for ease of access however for any further clarification refer the SpringBoot framework Documentation

#### Yugandhar Open MDM for embedded Web Server (EWS)
- OS: Supports most Linux distributions and is tested on CentOS and Ubuntu.
- Database
-- Oracle Database 11g Release 11.2.0.2.0 or later  O
-- Oracle 12c OR
-- MariaDB v10.3.x or later
- Java jdk 1.8 (jdk1.8.0_121)
- Tomcat embeeded ActiveMQ or External Red Hat JBoss AMQ 7.0.2.

#### Yugandhar MSP for JEE Container Web Server (JEEC)
- OS: Supports most Linux distributions and is tested on CentOS and Ubuntu.
- Oracle Database 11g Enterprise Edition Release 11.2.0.3.0 - 64bit Production or Later
- Java jdk 1.8 (jdk1.8.0_121)
- JBoss EAP 7.1.0 or later Refer jboss System requirements at https://access.redhat.com/articles/2026253
- JBoss Integrated Artemis Messaging broker or External Red Hat JBoss AMQ 7.0.2.

### Development Environment
Below are the System Requirements for setting up Development Environment
- OS – Windows 7 enterprise addition, Service Pack 1 or later
- 8GB RAM and 100 GB Storage
- Eclipse Java EE IDE for Web Developers. Oxygen.3a Release (4.7.3a) or later
- JBoss EAP 7.1.0 full runtime or later (Need only for Yugandhar MSP for JEEC )
- apache-maven-3.5.0 or later
- Java jdk 1.8 (jdk1.8.0_121) or later
- Spring Boot 2.0.2.RELEASE
- Jboss (Hibernate) Tools
- Databases
-- Oracle Database 11g Release 11.2.0.2.0 or later  OR
-- Oracle 12c  OR
-- MariaDB v10.3.x
- Oracle SQL Developer/HeidiSQL
- SOAPUI /postman or any other tool to test REST services

Note - Internet connectivity is needed to setup workspace. If internet is not available because of any reason then all the software and Maven jars needs to be manually downloaded which might be tedious task.

# Documentation
## Project documentation
https://github.com/yugandharproject/yugandhar-open-mdmhub/tree/master/resources/yugmdm-documentation

## Javadoc
https://yugandharproject.github.io/yugandhar-open-mdmhub/resources/yugmdm-javadocs/ews/index.html

## dbdocs
https://yugandharproject.github.io/YugandharMDMHub/resources/dbdocs/index.html

## github repository

https://github.com/yugandharproject/yugandhar-open-mdmhub

 

# Contacts
contact.yugandharproject@gmail.com


# How to Contribute
If you have adequate knowledge of Open Source technologies and you are fit to contribute to this project then please feel free to send a mail to us at below email id mentioning the area in which you can contribute. We will respond to you at the earliest

contact.yugandharproject@gmail.com

The Name of the contributors will be listed in either ‘Team’ or ‘Contributors’ or in both the section based on level of contribution.

 

# Authors
Sneha Borkar: Having 4+ year’s experience in software development and research.

Rakesh Vikhar : Having 12+ years of industry experience in working with various Master data Management projects across Banking, manufacturing, automotive and  retail domain.
 

# License
This project is licensed under the Apache 2.0 License - see the LICENSE.md file for details
