package br.com.petrobras.ppgi.ca.facade.mock

import br.com.petrobras.ppgi.ca.domain.UserProfile
import br.com.petrobras.security.model.*
import br.com.petrobras.security.model.authorization.access.UserRoleAuthorization
import com.thoughtworks.xstream.XStream

class MocksObjectRepo {

    private XStream xstream = new XStream()

    public Map getRepo(){
        return xstream.fromXML(MocksObjectRepo.class.getClassLoader().getResourceAsStream("mocksObjectRepo.xml"))
    }

    /**
     * Metodo que configura alias para a representacao xml da pesistencia de objetos com Xstream
     *
     * @param xstream
     * @return xstream
     */
    private configureAlias() {
        xstream.alias("informationValue", InformationValue.class)
        xstream.alias("resource", Resource.class)
        xstream.alias("role", Role.class)
        xstream.alias("user", User.class)
        xstream.alias("userProfile", UserProfile.class)
        return xstream
    }


    public static void main(String[] args) {
        MocksObjectRepo repo = new MocksObjectRepo()
        repo.oneTimeSetup()

    }

    // only to "change" initial data object repository
    def oneTimeSetup() {
        def roles = ["VALIDADOR": new Role(id: "VALIDADOR", name: "VALIDADOR"),
                "EDITOR": new Role(id: "EDITOR", name: "EDITOR"),
                "ADMINISTRADOR": new Role(id: "ADMINISTRADOR", name: "ADMINISTRADOR"),
                "VISUALIZADOR": new Role(id: "VISUALIZADOR", name: "VISUALIZADOR")
        ]
        def users = ["Y1Z5": new User(login: "Y1Z5"), "MDCO": new User(login: "MDCO")]

        def resources = ["RECURSO1": new Resource(id: "ALGO", name: "ALGO"), "RECURSO2": new Resource(id: "OUTRACOISA", name: "OUTRACOISA")]
        def info = new Information(id: 'AREA_PROJETO_SUB_CONTRATO');
        def infovalues = [
                "AN_AB_CA_PR1": new InformationValue(id: "AN_AB_CA_PR1", information: info),
                "AN_AB_CA_PR1_SUB1": new InformationValue(id: "AN_AB_CA_PR1_SUB1", information: info),
                "AN_AB_CA_PR1_SUB1_CONTRATO1": new InformationValue(id: "AN_AB_CA_PR1_SUB1_CONTRATO1", information: info),
                "AN_AB_CA_PR1_SUB1_CONTRATO1": new InformationValue(id: "AN_AB_CA_PR1_SUB1_CONTRATO2", information: info),
                "AN_AB_CA_PR1_SUB1_CONTRATO1": new InformationValue(id: "AN_AB_CA_PR1_SUB1_CONTRATO3", information: info),
                "AN_AB_CA_PR1_SUB1_CONTRATO1": new InformationValue(id: "AN_AB_CA_PR1_SUB1_CONTRATO3", information: info),
                "AN_GE_CA_PR1": new InformationValue(id: "AN_GE_CA_PR1", information: info),
                "AN_GE_CA_PR1_SUB1": new InformationValue(id: "AN_GE_CA_PR1_SUB1", information: info),
                "AN_GE_CA_PR1_SUB1_CONTRATO1": new InformationValue(id: "AN_GE_CA_PR1_SUB1_CONTRATO1", information: info),
                "AN_GE_CA_PR1_SUB1_CONTRATO1": new InformationValue(id: "AN_GE_CA_PR1_SUB1_CONTRATO2", information: info),
                "AN_GE_CA_PR1_SUB1_CONTRATO1": new InformationValue(id: "AN_GE_CA_PR1_SUB1_CONTRATO3", information: info)
        ]

        roles.ADMINISTRADOR.getResources().add(resources.RECURSO1)
        roles.ADMINISTRADOR.getResources().add(resources.RECURSO2)

        def userRoles = []

        UserRoleAuthorization authorization = new UserRoleAuthorization()
        authorization.user = users.Y1Z5
        authorization.role = roles.VALIDADOR
        userRoles.add(authorization)


        authorization = new UserRoleAuthorization()
        authorization.user = users.Y1Z5
        authorization.role = roles.ADMINISTRADOR
        userRoles.add(authorization)


        authorization = new UserRoleAuthorization()
        authorization.user = users.MDCO
        authorization.role = roles.ADMINISTRADOR
        userRoles.add(authorization)

        //
        def root = [roles: roles, users: users, resources: resources, infovalues: infovalues, userRoles:userRoles]

        FileWriter fw = null;
        try {

            fw = new FileWriter("src/test/resources/mocksObjectRepo.xml");
            def writer = configureAlias()
            def xml = writer.toXML(root);
            fw.write(xml);

        } finally {
            if (fw != null) {
                fw.close();
            }
        }
    }


}
