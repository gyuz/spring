<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %> 

<html>
    <head>
        <title>Crud Application</title>
    </head>
    <body>
        <div>
            <div>
                <h1>Roles</h1>
            </div>
            <form:form commandName="role" method="POST">
                Create/Update/Delete/List Role:<br><br>
                <table border="1" width="100%">
                    <tr>
                      <td>ROLE ID</td>
                      <td>ROLE NAME</td>
                    </tr>
                    <c:forEach var="roleId" items="${role.roleIdList}" varStatus="ctr">
                    <tr>
                      <td>${roleId}</td>
                      <td>${role.roleNameList.get(ctr.index)}</td>
                    </tr>
                    </c:forEach>
                </table>
                <br><br>
                <button type="submit" name="action" value="CREATE">SAVE</button>
                <button type="submit" name="action" value="DELETE">DELETE</button>
                <br><br>
                <button type="submit" name="action" value="BACK">BACK TO MAIN</button>
                <br>
            </form:form>
        </div>
    </body>
</html>
