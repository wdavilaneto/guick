<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>

<c:set var="flag_BR" value="bandeira_br_off.gif"/>
<c:set var="flag_EN" value="bandeira_en_off.gif"/>
<c:set var="flag_ES" value="bandeira_es_off.gif"/>

<c:if test="${pageContext.response.locale.country == 'BR'}">
    <c:set var="flag_BR" value="bandeira_br_on.gif"/>
</c:if>

<c:if test="${pageContext.response.locale.country == 'US'}">
    <c:set var="flag_EN" value="bandeira_en_on.gif"/>
</c:if>

<c:if test="${pageContext.response.locale.country == 'ES'}">
    <c:set var="flag_ES" value="bandeira_es_on.gif"/>
</c:if>

<div class="headerBar">
    <ul class="userInfo">
        <li class="label"><s:message code="br.com.petrobras.user" />:</li>
        <li class="data">Annonymous</li>
        <li class="label"><s:message code="br.com.petrobras.profile" />:</li>
        <li class="data">Usuario</li>
    </ul>

    <ul class="language">
        <li class="label"><s:message code="br.com.petrobras.languages" />:</li>
        <li class="flag">
           <a href="<c:url value='?lang=pt_BR' />">
              <img src="<c:url value="/imgs/icones/${flag_BR}" />"  alt="<s:message code='br.com.petrobras.language.portuguese' />" width="22" height="14"/>
           </a>
        </li>
        <li class="flag">
           <a href="<c:url value='?lang=en_US' />">
              <img src="<c:url value="/imgs/icones/${flag_EN}" />" alt="<s:message code='br.com.petrobras.language.english' />" width="22" height="14"/>
           </a>
        </li>
        <li class="flag">
           <a href="<c:url value='?lang=es_ES' />">
              <img src="<c:url value="/imgs/icones/${flag_ES}" />" alt="<s:message code='br.com.petrobras.language.spanish' />" width="22" height="14"/>
           </a>
        </li>
    </ul>

    <ul class="shortcutLinks">
        <li><a href="<c:url value="/home.do" />"><s:message code="br.com.petrobras.menu.start" /></a></li>
        <li><a href="#"><s:message code="br.com.petrobras.help" /></a></li>
        <li><a href="#"><s:message code="br.com.petrobras.menu.exit" /></a></li>
    </ul>
</div>
<!-- headerBar -->

<div class="logoBar">
    <div class="applicationLogo">
        <a href="#"><img src="<c:url value="/imgs/transparente.gif" />" alt="Transp" width="400" height="80"/></a>
    </div>
    <div class="petrobrasLogo">
        <a href="#"><img src="<c:url value="/imgs/transparente.gif" />" alt="Transp" width="120" height="44"/></a>
    </div>
    <div class="applicationVesion"><s:message code="br.com.petrobras.app.version" />: 10.10.10 - 01/01/2011</div>
</div>
<!-- logoBar -->