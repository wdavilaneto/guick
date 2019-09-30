guick 
====
### Project Generation based on Database Model

*  General (g)DSL to artifacts generation
*  Freemarker/Velocity Templating
*  Data inputs from: database  (json file later)

### Main Usage
 
(for awhile...)
Runing directly from Main class, it will genrate an guick.json example on specified folder
(first time only)
on main....

    Main main = new Main()
    main.project.initialize("../sev")  // Specified folder to generate files
    main.setTarget("angular/create-webapp").run()  // creante main archetype withou any database readings
    main.setTarget("angular/crud").run()   // Generate Cruds and Master Details based on database
    
- Configure your guick.json with you modeled database (owner permission user)
- Run main again :/
- It will generate target selected archetype targets on Main (Ex: front end, api, or both(java,groovy, python))
- "Leave now and never come back" - Smeagle



##### Angular template based and thanks to 
https://github.com/ng-matero/ng-matero


  

