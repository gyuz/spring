<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
    <head>
        <title>Crud Application</title>
        <script src="<c:url value='/js/tableFunctions.js'/>"></script>
    </head>
    <body>
        <div>
            <div style="color:red;">
                <c:if test="${!errMsgs.isEmpty()}">
                    <c:forEach items="${errMsgs}" var="err">
                        ${err} <br/>
                    </c:forEach>
                </c:if>
            </div>
            <div style="color:blue;">
                <c:if test="${!successMsgs.isEmpty()}">
                    <c:forEach items="${successMsgs}" var="msg">
                        ${msg} <br/>
                    </c:forEach>
                </c:if>
            </div>
            <div>
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
                    <input type="submit" value="SAVE">
                </form:form>
                <br/><br/>
            </div>
            <div>
                 <a href="../index.jsp"><button type="button"><spring:message code="lbl.bacl.main" text="BACK TO MAIN" /></button></a>
            </div>
        </div>
    </body>
</html>
