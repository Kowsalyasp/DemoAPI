# SQL MODULE



## TABLE OF CONTENTS

- [Abstract](#abstract)
- [Requirements](#requirements)
- [BundleListener](#bundlelistener)
- [SQLDevListener](#sqldevlistener)
- [MetaData](#metadata)
  * [*meta.xml file tags precedent*](#metaxml-file-tags-precedent)
  * [*meta.xml precedent*](#metaxml-precedent)
- [Data.xml](#dataxml)
  * [*data.xml file precedent*](#dataxml-file-precedent)
- [Packages](#packages)
  * [api package](#api-package)
  * [internal package](#internal-package)






## Abstract:
* A module can be thought of as an object library that is linked to the application code. 
* The procedures could be compiled into object code and linked directly to the application code, they could be compiled 
  and stored on the DBMS and calls to access plan identifiers placed in the application code, or they could be interpreted at run time.
* The SQL module allows you to execute custom queries against an SQL database and store the results in Elasticsearch.
  This module supports the databases
* The SQL module explains how to use simple DataBase Query implementations.
* This module explains the statically available methods for executing DML, DDL, DQL related database actions.

## Requirements:
This module requires the following maven dependencies.
	
1. javatuples - A tuple is a collection of several elements that may or may not be related to each other. 
	               In other words, tuples can be considered anonymous objects.
2. HikariCP - HikariCP is a solid high-performance JDBC connection pool. 
		     A connection pool is a cache of database connections maintained so that the connections can be reused 
		     when future requests to the database are required.
3. PostgreSQL - PostgreSQL is an advanced, enterprise-class open-source relational database that 
	               supports both SQL (relational) and JSON (non-relational) querying.	
	
## BundleListener:
	A BundleEvent listener. When a BundleEvent is fired, it is asynchronously delivered to a BundleListener. BundleListeners are 
	called with a BundleEvent object when a bundle has been installed, resolved, started, stopped, updated, unresolved, or uninstalled.
	
## SQLDevListener:
	Utilizing the bundle lifecycle, it will perform the actions like metadata parsing, etc.
	The class explains the loading, processing, updating and populating the meta and data xml files. 
	Make use of this we can write meta file history and fetch meta file history. We can see this in snapshots location of our system. 
	
## MetaData:
	The meta.xml file to load and populate the metadata to fetch and configure the table definitions, and initialize the sequence 
	generation of primary keys. We set up the metadata with all the essential things like table name, columns, and constraints 
	to configure the table in the database. 
	
### *meta.xml file tags precedent*
	
Table:

Table name and type of the table like common or org dependent table are provided here.
> ``<table name="TableName" type="11">...
	  ...
	</table>``
		 
Column:

Coulmn name of the table, its data type, whether its nullable or not, maximum length, default value for the column are provided here. 
> ``<columns>
        <column name="NAME" data-type="CHAR" max-length="30" nullable="false" default-value="1"/>
    </columns>``

Primary key:

Column to be set as primary key of the table should be here. 
> ``<primary-key name="Table_PK" column="ID" sequence-generator="TablePk.ID" />``
            
Foreign key:

Column to be set as foreign-key of the table, reference table and its reference column are provided here with constraints. 
> ``<foreign-keys> 
        <foreign-key name="Table_FK" reference-table="Table1" local-column="Table1_ID" 
		reference-column="ID" constraint="ON-DELETE-CASCADE" /> 
    </foreign-keys>``
			
Index key:	

Column to be set as index key of the table should be here. 
> ``<indexes> 
        <index name="Index_Id">
            <index-column>TABLE_NAME</index-column>
        </index> 
    </indexes>``
			
Unique key:

Column to be set as unique key of the table should be here. 
> ``<unique-keys> 
        <unique-key name="UniqueKey_UK">
            <unique-key-column>ID</unique-key-column>
        </unique-key> 
    </unique-keys``
		
### *meta.xml precedent*
* A sample meta.xml is shown for reference 
	

	    <table name="Sample" type="11">
            <columns>
                <column name="ID" data-type="BIGINT"/>
                <column name="NAME" data-type="CHAR" nullable="false" />
		        <column name="COMPANY_ID" data-type="BIGINT" default-value="1">
                <column name="PROVIDER_ID" data-type="CHAR" nullable="false" />
            </columns>
            <primary-key name="SAMPLE_PK" column="ID" sequence-generator="SAMPLE.ID" />
	        <foreign-keys>
                <foreign-key name="SAMPLE_ID_FK" reference-table="COMPANY"
                             local-column="COMPANY_ID" reference-column="ID" constraint="ON-DELETE-CASCADE"/>
            </foreign-keys>
	        <unique-keys>
                <unique-key name="SAMPLE_UK">
                    <unique-key-column>PROVIDER_ID</unique-key-column>
                </unique-key>
            </unique-keys>
            <indexes>
                <index name="SAMPLE_Idx">
                    <index-column>NAME</index-column>
                </index>
            </indexes>
        </table>
	
## Data.xml:
	The data.xml file to load, process, update and populate the data to fetch and configure the column of the table.
	In this, we can statically enter an entity for the table.
	Using this data file we could be able to manipulate the data in the user's preferable way.
	With the attributes like searchable, hidden, order, primary, and sort we can decide whether the column of 
	the table is searchable, primary, and hidden or not, which column is shown to the user.
	 
Example to enter a record statically: 
> ``<MCMFieldSQLTable ID="MCMFieldSQLTable:ID:CO:1" TABLE_NAME="SampleTable"/>``
	
Column to be hidden, primary, searchable or not are provided here:
> ``<MCMFields ID="MCMFields:ID:CO:50" NAME="id" DISPLAY_NAME="i18n.contact.company.id.display.name" 
		       PRIMARY="true" SORT="1" SEARCHABLE="false" ORDER="5" HIDDEN="true">``


### *data.xml file precedent*

 <MCMFieldSQLTable ID="MCMFieldSQLTable:ID:CO:6" TABLE_NAME="MCOLifeCycleStage"/>

        <MCMDataProvider ID="MCMDataProvider:ID:CO:50" TYPE="1" NAME="COMPANY_LIST">
            <MCMFields ID="MCMFields:ID:CO:50" NAME="id" DISPLAY_NAME="i18n.contact.company.id.display.name" 
		       PRIMARY="true" SORT="1" SEARCHABLE="false" ORDER="5" HIDDEN="true">
                <MCMFieldSQLSource ID="MCMFieldSQLSource:ID:CO:50" TABLE_ID="MCMFieldSQLTable:ID:CO:1" COLUMN="ID" TYPE="" FLAG=""/>
            </MCMFields>
        </MCMDataProvider>
       
	
## Packages:
	- api - It provides the services for data containers, queries, constraints, and clauses. 
	- internal - It provides the services of api package.
	- resource - Auto-generated table content class file. 
	- update dll - The interface for DDL-related actions.

### api package:
* dml - The DML commands in Structured Query Language change the data present in the SQL database. We can easily access, store, modify,   update and delete the existing records from the database using DML commands, Here it provides the services for all related dml queries.
* ds - A data structure is a special way of organizing and storing data in a database so that it can be used efficiently. Provides  services for admindatastore, orgdatastore, readable and writable datastore.
* listener - Provides services for listeners like row added, row updated and row deleted listeners.
* meta - Meta-SQL is a great way to abstract SQL logic and ensure consistency in SQL definitions. Also it tells about SQL statements text with key fields such as tabletype and datatype.
* sequence - Sequence is a set of integers that it allows the automatic generation of values and supported by some database systems to produce unique values on demand. provides the service for sequence generator.
### internal package:
* data - Data is a information that can be organized and stored in a database. For that it provides the datacontainer and here it can be also filtered the datacontainer according to our needs.
* dml - Provides the services for dml queries in api package. And lay out the implementation for all servies here.  
* ds - Provides implementations for readable and writable datastore, admindatastore, and orgdatastore.
* handler - When an SQL procedure executes, if unfortunately error occurs then the procedure ends unless. And tell the procedure to perform some other action. These Handler Statements are abstract methods for all DML and DDL-related queries.  
* listener - Listen the Row it records the cache for add, update and delete process.
* meta - Provides and maintain the cache process in datacontainer and also executes the procedure for handling meta data.
* parser - Provides implementations for metadata parser, constraints resolver, table definition loader, etc.
* pgsql - PostgreSQL is an object-relational database used as the primary data store.provides services for dml and ddl related functionalities.
* sequence - Generate the sequence automatically, it loads the series.
* status - error code is a numeric or alphanumeric code that is used to determine the nature of an error and why it occurred.when they attempt to do something or fail to do and they can be passed off to error handlers that determine what action to take. And here, they provides the status for the error code occured by this module. 
* update - Modify or revert the existing records in a table it provides the pre action type(create_table, drop_unique_key, create_index, update_index, delete_index,...) and post action type(create_unique_key, update_unique_key, create_foreign_key, update_foreign_key, drop_foreign_key,...).


	
