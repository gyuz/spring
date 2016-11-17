<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
    <head>
        <title>Crud Application</title>
        <link href="<c:url value='/css/styles.css' />" rel="stylesheet"/>
    </head>
    <body>
        <div>
            <div clas="header">
                <div>
                    <form:form action="/locale" method="GET">
                        <input type="hidden" name="page" value="index"/>
                        <spring:message code="lbl.lang" text="LANGUAGE" /> : 
                        <button type="submit" class="lang" name="lang" value="en">
                            <spring:message code="lbl.en" text="ENGLISH" />
                        </button> | 
                        <button type ="submit" class="lang" name="lang" value="ch">
                            <spring:message code="lbl.ch" text="CHINESE" />
                        </button>
                    </form:form>
                </div>
            </div>
            <br/><br/><br/>
            <div align="center">
                 <h3><spring:message code="lbl.index.hdr" text="Make Changes to:" /></h3>
                 <form:form action="/redirect" method="GET">
                    <button type=submit name="view" value="PersonMain" size="20"><spring:message code="lbl.person" text="PERSON" /></button>
                </form:form> 
                <a href="<c:url value='/role' />"><button type="button" size="20"><spring:message code="lbl.role" text="ROLE" /></button></a>
            </div> 
            <div>
                <h3><spring:message code="lbl.fileupload" text="File Upload:" /></h3>
                <form:form action="fileUpload" method="POST" commandName="fileDto" enctype="multipart/form-data" class="upload">
                <form:errors path="*" cssClass="errorblock" element="div"/>
                <div>
                    <c:if test="${not empty fileName}">
                            <div class="success"><strong>${fileName}</strong> successfully uploaded. </div>
                    </c:if>
                </div>
                &nbsp&nbsp&nbsp<spring:message code="lbl.selectfile" text="Select a file to upload" /> : <input name="file"  type="file" />
                <br/><br/>
                &nbsp&nbsp&nbsp<button type="submit"><spring:message code="lbl.upload" text="Upload" /></button> 
                </form:form>
            </div>  
        </div>
    </body>
</html>
