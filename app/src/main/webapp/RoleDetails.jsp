<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
    <head>
        <title>Crud Application</title>
        <script src="<c:url value='/js/tableFunctions.js'/>"></script>
        <link href="<c:url value='/css/styles.css' />" rel="stylesheet"/>
    </head>
    <body>
        <div>
            <div>
                <c:if test="${!errMsgs.isEmpty()}">
                    <div>
                        <c:forEach items="${errMsgs}" var="err">
                            <div class="errorblock">${err}</div>
                        </c:forEach>
                    </div>
                </c:if>
            </div>
            <div>
                <c:if test="${!successMsgs.isEmpty()}">
                    <c:forEach items="${successMsgs}" var="msg">
                        <div  class="success">${msg}</div>
                    </c:forEach>
                </c:if>
            </div>
            <div clas="header">
                <div>
                    <form:form action="/locale" method="GET">
                        <input type="hidden" name="page" value="role"/>
                        <spring:message code="lbl.lang" text="LANGUAGE" /> : 
                        <button type="submit" class="lang" name="lang" value="en">
                            <spring:message code="lbl.en" text="ENGLISH" />
                        </button> | 
                        <button type ="submit" class="lang" name="lang" value="ch">
                            <spring:message code="lbl.ch" text="CHINESE" />
                        </button>
                    </form:form>
                </div>
                <div class="topright">
	                <form action="/logout" method="post" id="logoutForm">
		                <input type="hidden" name="${_csrf.parameterName}"
			                value="${_csrf.token}" />
			            <button type="submit" name="logout" class="lang"><spring:message code="lbl.logout" text="LOGOUT" /></button> 
	                </form>
                </div>
                <h1><spring:message code="lbl.role" text="ROLE" /></h1>
            </div>
            <div class="content">
                <form:form name="roleDetails" action="/roleSave" method="POST" commandName="roleDto">
                    <h3><spring:message code="lbl.rolehdr" text="ADD/UPDATE/DELETE ROLE:" /></h3>
                    <br/><br/>
                    <table id="roles">
                        <tr>
                          <th><spring:message code="lbl.id" text="ID" /></th>
                          <th><spring:message code="lbl.rolename" text="ROLE NAME" /></th>
                          <th></th>
                        </tr>
                        <c:forEach var="roleId" items="${roleDto.roleIdList}" varStatus="ctr">
                            <tr>
                              <td style="display:none;"><input type="text" id="${roleId}" name="roleIdList" value="${roleId}" readonly/></td>
                              <td style="width:150px">${roleId}</td>
                              <td><input type="text" name="roleNameList" value="${roleDto.roleNameList.get(ctr.index)}"/></td>
                              <td>
                                <button type="button" onclick="deleteRow(this, 'roles', 'roleDetails', '${roleId}')"><spring:message code="lbl.delete" text="DELETE" />
                                </button>
                              </td>
                            </tr>
                        </c:forEach>
                    </table>
                    <br/>
                    <button type="button" onclick="addRow('roles', '1', 'role')"><spring:message code="lbl.add.role" text="ADD ROLE" /></button>
                    <br/><br/>
                    <button type="submit" value="SAVE"><spring:message code="lbl.save" text="SAVE" /></button>
                </form:form>
                <br/><br/>
                <div>
                    <form:form action="/redirect" method="GET">
                        <button type="submit" name="view" value="index"><spring:message code="lbl.back.main" text="BACK TO MAIN" /></button>
                    </form:form> 
                </div>
            </div>
        </div>
    </body>
</html>
