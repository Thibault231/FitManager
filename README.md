# FitManager
A plateform for easy creating fitness web sites. 

Welcome on FitManager APP.

##Versions

FitManager need use for optimal performance:
+ Java:  11
+ Wildfly: 18.1 
+ MySQL:


##Install the APP:

###Standalone.xml
In the file standalone.xml add the following datasources in <datasources>/

>_<datasource jndi-name="java:jboss/datasources/mysql-datasource" pool-name="MySQLDSPool" enabled="true" use-java-context="true" statistics-enabled="${wildfly.datasources.statistics-enabled:${wildfly.statistics-enabled:false}}">_
    _<connection-url>jdbc:mysql://localhost:3306/fit_manager_db?createDatabaseIfNotExist=true&amp;useUnicode=true</connection-url>_
>    _<driver>mysql</driver>_
>    _<security>_
>        _<user-name>_ __"LOGIN"__ _</user-name>_
>        _<password>_ __"YOUR__ __PASSWORD"__  _</password>_
>    _</security>_
>_</datasource>_

###Mysql

Create in MySql the database __"fit_manager_db"__ , for widfly being able to publish the app inside. 

Note: You can change the name of the database, if needed. 
In that case also change the name in the _"<datasource>"_ tag above, as following:
> _<connection-url>jdbc:mysql://localhost:3306/_ __NEW DATABASE NAME__ ?createDatabaseIfNotExist=true&amp;useUnicode=true</connection-url>_


###Wildfly
Install Widfly serveur then add the app inside.
__e.g with Eclipse__
 _1-Display serveur view:_
 Window > Show View > Server
 
 _2-Add server:_
 Click on "No serveur available..." then select WildFly 18
 
 _3-Add app:_
 Right click on "Wildfly 18"
 Select "Add and remove"
 Add the app.


### Launch Server with Eclipse
Before launching proceed to:
* Maven Build as "clean install"
* Maven Update
* Start Wildfly Server
* Full publish the App.
* Go at the url : **http://localhost:9990/console/index.html#deployments;path=deployment~dply-fitmanagerwar**
* Select /FitManager at the line _Context Root:_

You should access the following view in navigator:


##Try demo version

Following users have been created with their login:
* Manager   Login: titou@gmail.com
* Coach     Login: nene@gmail.com
* Member    Login: charef@gmail.com

NB: In demo version all password are : "31500"

Following space link to the manager have been created with lessons inside:
+ PowerFit  "TOULOUSE 20 rue du Taure,31500"

>NB: All lessons are linked to the user "Coach".



#ENJOY FITMANAGER EXPERIENCE


