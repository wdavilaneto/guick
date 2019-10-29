Nearly empty java project with a failing test!
===




### To run / test 
This has been created using gradle; to run the tests, just execute:

```
./gradlew test
```
or (on windows machines):
```
gradlew.bat test
```


### Example Database
Example database to start modeling proposed is an oracle docker image

    # "Script for runnnign oracle docker instance (localhost:<port_exported_below>:ORCLCDB)"
    docker run -d -it --name oracle -P store/oracle/database-enterprise:12.2.0.1  
    # "USer: sys  \nPassword: Oradoc_db1 "
    docker port oracle

. Look what por was forwarded on above command to set it accordingly on guilk.json connection
. if you dont have oracle driver installed on your local maven repo, download and..

    mvn install:install-file -Dfile="ojdbc8.jar" -DgroupId="com.oracle" -DartifactId="ojdbc8" -Dversion="12.2.0.1" -Dpackaging="jar" 
    
### For en example_ddl.sql was generated as quickstart* example     
    