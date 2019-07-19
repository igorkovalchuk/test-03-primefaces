
Works locally and on Heroku.

How to run it locally:

 * for Oracle Database XE: copy ucp.jar and ojdbc8.jar into WebContent/WEB-INF/lib
 * for Oracle Database XE: mvn clean package -f pom-oracle.xml
 * for PostgreSQL: mvn clean package
 * specify the DATABASE_URL environment variable, for example: export DATABASE_URL=postgres://my_user:my_password@127.0.0.1/my_database 
 * Oracle DATABASE_URL, for example: export DATABASE_URL=oracle://c##dev1:12345@127.0.0.1:1521
 * cp target/test3.war ~/java/apache-tomcat-9.0.21/webapps
 * ~/java/apache-tomcat-9.0.21/bin$ ./catalina.sh run
 * P.S. don't forget to call COMMIT (after an INSERT) in Oracle Database XE;

How to run it on Heroku:

 * specify the DATABASE_URL environment variable to use an existing database (or use the PostgreSQL add-on to create a new one);
 * create tables and insert some data into the PostgreSQL database;
 * for Oracle Database XE: copy ucp.jar and ojdbc8.jar into WebContent/WEB-INF/lib
 * rename pom-oracle.xml to pom.xml if you want to use UCP + PostgreSQL;
 * push the changed and new files into the local git repo;
 * git push heroku master;

TO DO:

 * remove redundant database data loading calls;
 * catch the ajax errors - show error messages;
 * when it loses the http connection the PrimeFaces may have an issue when selecting rows when we restore the connection;
 * shutdown UCP gracefully;
 * configure Maven to deploy the war locally instead of cp;
 * use Spring Data instead of JDBC;

CONFIGURATION TASKS:

  * installed Oracle Database Express Edition on Debian, using Vagrant;
  * configured CDI using Apache OpenWebBeans according to the native documentation;
  * configured MyFaces + PrimeFaces;
  * using UCP + Oracle Database Express Edition;

Other tasks that I had, some of them at my own discretion:

  * Oracle Database Express Edition installation on Debian using Docker - unsuccessfully this time;
  * configured this project to use on Heroku;
  * looking how to use CDI in ServletContextListener/Tomcat - unsuccessfully (except CDI.current().select(Class).get());
  * looking how to use Oracle Maven Repository;
  * configured Tomcat Database Connection Pool (DBCP 2) instead of Oracle Universal Connection Pool (UCP);
  * looking how to remove the database name and password from source codes for Heroku deployment;
  * database configuration - using ServletContextListener instead of context.xml;
  * get the problem with the UTF white space in the provided documentation, like this one: create table group1 (id integer NOT NULL); create table group2 (id integer NOT NULL);
  * having a strange local webapp runner local issue (Heroku);
  * had a strange tomcat-dbcp class not found issue, so I need to set the scope = compile (Heroku);
  * using UCP + PostgreSQL;

LINKS:

  * https://community.oracle.com/thread/4203240
