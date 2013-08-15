<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
    <div class="menu">
        <ul>
            <li>
                <a href="<c:url value='/home.do' />"><s:message code="br.com.petrobras.menu.start" /></a>
                   <ul>
                    <li><a href="<c:url value='/aluno/list.do' />"><s:message code="aluno" /></a></li>
                    <li><a href="<c:url value='/curso/list.do' />"><s:message code="curso" /></a></li>
                    <li><a href="<c:url value='/disciplina/list.do' />"><s:message code="disciplina" /></a></li>
                    <li><a href="<c:url value='/frequencia/list.do' />"><s:message code="frequencia" /></a></li>
                    <li><a href="<c:url value='/inscricao/list.do' />"><s:message code="inscricao" /></a></li>
                    <li><a href="<c:url value='/materialDidatico/list.do' />"><s:message code="materialDidatico" /></a></li>
                    <li><a href="<c:url value='/pessoa/list.do' />"><s:message code="pessoa" /></a></li>
                    <li><a href="<c:url value='/professor/list.do' />"><s:message code="professor" /></a></li>
                    <li><a href="<c:url value='/prova/list.do' />"><s:message code="prova" /></a></li>
                    <li><a href="<c:url value='/provaHistorico/list.do' />"><s:message code="provaHistorico" /></a></li>
                    <li><a href="<c:url value='/questao/list.do' />"><s:message code="questao" /></a></li>
                    <li><a href="<c:url value='/resposta/list.do' />"><s:message code="resposta" /></a></li>
                    <li><a href="<c:url value='/turma/list.do' />"><s:message code="turma" /></a></li>
                   </ul>
            </li>
           <li><a href="<c:url value='/home.do' />"><s:message code="br.com.petrobras.menu.report" /></a></li>
            <li><a href="<c:url value='/home.do' />"><s:message code="br.com.petrobras.menu.security" /></a></li>
        </ul>
    </div>
    <!-- menu -->