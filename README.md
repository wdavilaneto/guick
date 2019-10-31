guick 
====
### Project Generation based on Database Model

*  General (g)DSL to artifacts generation
*  Freemarker/Velocity Templating
*  Data inputs from: database  (json file later)

### Main Usage

. run application 
 
    Run ./gradlew bootRun 

. if its the first time it will generate the guick.json file where you configure your (databse/json) 
 source for code generation Generate (run main for awhile || spring boot run (on guick)) 
- Configure your guick.json with you modeled database (owner permission user) accordinly.
 Then you can generate the project. (runnning again)

. or Runing directly from Main class
. it will genrate an guick.json example on "specified" folder; Ex 

    Main main = new Main()
    main.project.initialize("../generated")  // Specified folder to generate files
    main.setTarget("angular/create-webapp").run()  // creante main archetype withou any database readings
    main.setTarget("angular/crud").run()   // Generate Cruds and Master Details based on database
- It will generate target selected archetype targets on Main (Ex: front end, api, or both(java,groovy, python))
- "Leave now and never come back" - Smeagle

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
    



##### Angular template based and thanks to 
https://github.com/ng-matero/ng-matero


  

