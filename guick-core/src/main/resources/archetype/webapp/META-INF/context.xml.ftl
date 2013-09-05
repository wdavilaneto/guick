<Context docBase="${project.name}" path="/${project.name}" reloadable="false">

    <ResourceLink name="${project.name}Ds" global="myappDs" type="javax.sql.DataSource"/>

    <Resource name="${project.name}Ds" type="javax.sql.DataSource" auth="Container"
              driverClassName="org.h2.Driver"
              url="jdbc:h2:mem:${project.name};DB_CLOSE_DELAY=-1"
              username="sa" password="" maxActive="20" maxIdle="10" maxWait="-1" />


</Context>