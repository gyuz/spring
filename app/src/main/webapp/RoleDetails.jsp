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
            <div class="errorblock">
                <c:if test="${!errMsgs.isEmpty()}">
                    <c:forEach items="${errMsgs}" var="err">
                        ${err} <br/>
                    </c:forEach>
                </c:if>
            </div>
            <div class="succuess">
                <c:if test="${!successMsgs.isEmpty()}">
                    <c:forEach items="${successMsgs}" var="msg">
                        ${msg} <br/>
                    </c:forEach>
                </c:if>
            </div>
            <div>
                <div>
                <form:form action="/locale" method="GET">
                    <input type="hidden" name="page" value="RoleDetails"/>
                    Language : <button type="submit" class="lang" name="lang" value="en"><spring:message code="lbl.en" text="ENGLISH" /></button> | 
                              <button type ="submit" class="lang" name="lang" value="ch"><spring:message code="lbl.ch" text="CHINESE" /></button>
                </form:form>
            </div>
                <h1><spring:message code="lbl.role" text="ROLE" /></h1>
            </div>
            <div>
                <form:form name="roleDetails" action="/roleSaveController" method="POST" commandName="roleDto">
                    <h3><spring:message code="lbl.rolehdr" text="ADD/UPDATE/DELETE ROLE:" /></h3>
                    <br/><br/>
                    <table id="roles" border="1">
                        <tr>
                          <td><spring:message code="lbl.id" text="ID" /></td>
                          <td><spring:message code="lbl.rolename" text="ROLE NAME" /></td>
                          <td>
                        </tr>
                        <c:forEach var="roleId" items="${roleDto.roleIdList}" varStatus="ctr">
                            <tr>
                              <td style="display:none;"><input type="text" id="${roleId}" name="roleIdList" value="${roleId}" readonly/></td>
                              <td>${roleId}</td>
                              <td><input type="text" name="roleNameList" value="${roleDto.roleNameList.get(ctr.index)}"/></td>
                              <td><button type="button" onclick="deleteRow(this, 'roles', 'roleDetails', '${roleId}')"><spring:message code="lbl.delete" text="DELETE" /></button></td>
                            </tr>
                        </c:forEach>
                    </table>
                    <button type="button" onclick="addRow('roles', '1', 'role')"><spring:message code="lbl.add.role" text="ADD ROLE" /></button>
                    <br/><br/>
                    <button type="submit" value="SAVE"><spring:message code="lbl.save" text="SAVE" /></button>
                </form:form>
                <br/><br/>
            </div>
            <div>
                <form:form action="/redirect" method="GET">
                    <button type="submit" name="view" value="index"><spring:message code="lbl.back.main" text="BACK TO MAIN" /></button>
                </form:form> 
            </div>
        </div>
    </body>
</html>
