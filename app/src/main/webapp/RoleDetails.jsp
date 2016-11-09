<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 

<html>
    <head>
        <title>Crud Application</title>
        <script src="<c:url value='/js/formValidations.js'/>"></script>
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
                <h1>Roles</h1>
            </div>
            <div>
                <form:form name="roleDetails" action="/roleSaveController" method="POST" commandName="roleDto">
                    <h3>Add/Update/Delete Roles:</h3>
                    <br/><br/>
                    <table id="roles" border="1">
                        <tr>
                          <td>ROLE ID</td>
                          <td>ROLE NAME</td>
                          <td>
                        </tr>
                        <c:forEach var="roleId" items="${roleDto.roleIdList}" varStatus="ctr">
                            <tr>
                              <td style="display:none;"><input type="text" id="${roleId}" name="roleIdList" value="${roleId}" readonly/></td>
                              <td>${roleId}</td>
                              <td><input type="text" name="roleNameList" value="${roleDto.roleNameList.get(ctr.index)}"/></td>
                              <td><button type="button" onclick="deleteRow(this, 'roles', 'roleDetails', '${roleId}')">DELETE</button></td>
                            </tr>
                        </c:forEach>
                    </table>
                    <button type="button" onclick="addRow('roles', '1', 'role')">Add Role</button>
                    <br/><br/>
                    <input type="submit" value="SAVE">
                </form:form>
                <br/><br/>
            </div>
            <div>
                 <a href="../index.jsp"><button type="button">BACK TO MAIN</button></a>
            </div>
        </div>
    </body>
</html>
