guick
====

### Project Generation based on database Model

*  General (g)DSL to artifacts generation
*  Freemarker/Velocity Templating
*  Data inputs from: database  (json file later)

### Main Usage

Runing directly from Main class, it will genrate an guick.json example on specified folder

    Main main = new Main()
    main.project.initialize("../sev")  // Specified folder to generate files
    main.setTarget("stage/create-webapp").run()  // creante main archetype withou any database readings
    main.setTarget("stage/crud").run()   // Generate Cruds and Master Details based on database

