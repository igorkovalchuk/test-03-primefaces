
Works locally and on Heroku.

Locally:

 * mvn package
 * cp target/test3.war ~/java/apache-tomcat-9.0.21/webapps
 * ~/java/apache-tomcat-9.0.21/bin$ ./catalina.sh run

TO DO:

 * use Oracle Database Express Edition instead of PostgreSQL;
 * remove redundant database data loading calls;
 * catch the ajax errors - show error messages;
 * configure Maven to deploy the war locally instead of cp;
 * use Spring Data instead of JDBC;

CONFIGURATION TASKS:

  * installed Oracle Database Express Edition on Debian, using Vagrant;
  * configured CDI using Apache OpenWebBeans according to the native documentation;
  * configured MyFaces + PrimeFaces;
  * configured Oracle UCP;

Other tasks that I had, some of them at my own discretion:

  * Oracle Database Express Edition installation on Debian using Docker - unsuccessfully;
  * configured this project to use on Heroku;
  * looking how to use CDI in ServletContextListener/Tomcat - unsuccessfully (except CDI.current().select(Class).get());
  * looking how to use Oracle Maven Repository;
  * configured Tomcat Database Connection Pool (DBCP 2) instead of Oracle Universal Connection Pool (UCP);
  * looking how to remove the database name and password from source codes for Heroku deployment;
  * database configuration - using ServletContextListener instead of context.xml;
  * get the problem with the UTF white space in the provided documentation, like this one: create table group1 (id integer NOT NULL); create table group2 (id integer NOT NULL);
  * having a strange local webapp runner local issue (Heroku);
  * had a strange tomcat-dbcp class not found issue, so I need to set the scope = compile (Heroku);

LINKS:

  * https://community.oracle.com/thread/4203240
