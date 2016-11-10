<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<html>
    <title>Crud Application</title>
    <body>
        <div align="center">
            <h3>Make Changes to: </h3>
            <a href="PersonMain.jsp"><button size="20">PERSON</button></a><br>
            <form:form action="roleController" method="GET">
                <input type="submit" size="20" value="ROLE">
            </form:form>
            File Upload:
            <form:form>
            </form:form>
        </div>
    </body>
</html>
