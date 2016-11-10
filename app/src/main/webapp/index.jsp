<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<html>
    <head>
        <title>Crud Application</title>
        <style>
        .errorblock{
	        color: #000;
	        background-color: #ffEEEE;
	        border: 2px solid #ff0000;
	        padding:6px;
	        margin:12px;
        }
        </style>
    </head>
    <body>
        <div>
            <c:if test="${not empty fileName}">
                    <span><strong>${fileName}</strong> successfully uploaded. </span>
            </c:if>
            <div align="center">
                <h3>Make Changes to: </h3>
                <a href="PersonMain.jsp"><button size="20">PERSON</button></a><br>
                <form:form action="roleController" method="GET">
                    <input type="submit" size="20" value="ROLE">
                </form:form>
            </div>
            <div>
                File Upload:
                <form:form action="fileUpload" method="POST" commandName="fileDto" enctype="multipart/form-data">
                    <form:errors path="file" cssClass="errorblock" element="div"/>
                    Select a file to upload : <input type="file" name="file" /> 
                    <br/> 
                    <input type="submit" value="upload" />
                </form:form>
            </div>
        </div>
    </body>
</html>
