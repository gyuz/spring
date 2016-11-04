<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 

<html>
    <head>
        <title>Crud Application</title>
        <script src="<c:url value='/js/tableFunctions.js'/>"></script>
    </head>
    <body>
        <div>
            <div>
                <h1>Roles</h1>
            </div>
            <form:form method="POST" action="/roleSaveController">
                <h3>Create/Update/Delete/List Role:</h3>
                <br/><br/>
                <table id="roles" border="1">
                    <tr>
                      <td>ROLE ID</td>
                      <td>ROLE NAME</td>
                      <td></td>
                    </tr>
                    <c:forEach var="roleId" items="${roleDto.roleIdList}" varStatus="ctr">
                        <tr>
                          <td><input type="text" name="roleId" value="${roleId}" readonly/></td>
                          <td><input type="text" name="roleName" value="${roleDto.roleNameList.get(ctr.index)}"/></td>
                          <td><button type="button" onclick="deleteRow(this, 'roles')">DELETE</button></td>
                        </tr>
                    </c:forEach>
                </table>
                <button type="button" onclick="addRow('roles')">Add Role</button>
                <br/><br/>
                <button type="submit">SAVE</button>
            </form:form>
            <br/><br/>
            <a href="../index.jsp"><button type="button">BACK TO MAIN</button></a>
        </div>
    </body>
</html>
