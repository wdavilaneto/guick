package br.com.petrobras.ppgi.ca.facade

import br.com.petrobras.security.ISecurityContext
import br.com.petrobras.security.model.IContext
import br.com.petrobras.security.model.Information

class Bla {

    ISecurityContext securityContext;

    def bla () {
        ISecurityContext context = ISecurityContext.getContext();
    }

    public IContext get(String id) {
        def information = securityContext.informationManager.find(id)
        if (information.isContext()){
            return information
        }
        return null;
    }

    public IContext find(List<String> ids) {
        return resecurityContext.informationManager.find(ids).findAll { it ->
            it.isContext()
        }
    }

    public List<IContext> findAll() {
        securityContext.getContextManager().findAll()
    }

    public void create(IContext context) {
        ((Information) context).setContext(true);
        securityContext.informationManager.save(context);
    }

    public void delete(IContext context) {
        securityContext.informationManager.remove(context.getId());
    }

}
