Nearly empty java project with a failing test!
===
Quick Intro 
 -> guick.json - where you configure your (databse/json) source for code generation
   |->  Generate (run main for awhile || spring boot run (on guick)) 
 -> api = java project = open and run (spring application)
 -> frontent = angular (on development)
 
 PS:Old Archetypes avaible (not parametrized yet changes on main)

### Example Database
If you dont have a database to generate your project user a Docker image.

Example database to start modeling proposed is an oracle docker image

    # "Script for runnnign oracle docker instance (localhost:<port_exported_below>:ORCLCDB)"
    docker run -d -it --name oracle -P store/oracle/database-enterprise:12.2.0.1  
    # "USer: sys  \nPassword: Oradoc_db1 "
    docker port oracle


. Look on result of the above comand for what port 
   the tcp oracle connection port was forwarded and set it accordingly on "guilk.json" connection string 
. if you dont have oracle driver installed on your local maven repo, download and..

    mvn install:install-file -Dfile="ojdbc8.jar" -DgroupId="com.oracle" -DartifactId="ojdbc8" -Dversion="12.2.0.1" -Dpackaging="jar"
     
. if you want a quickstart use a the exemple_dll.sql as base model 
. This script will create the USER needed to (also) configure "guilk.json" accordingly
. For en example_ddl.sql was generated as quickstart* example     
    