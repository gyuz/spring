<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

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
        .lang{
          background:none;
          border:none;
        }
        </style>
    </head>
    <body>
        <div>
            <c:if test="${not empty fileName}">
                    <span><strong>${fileName}</strong> successfully uploaded. </span>
            </c:if>
            <div>
                <form:form action="index" method="GET">
                    Language : <button type="submit" class="lang" name="lang" value="en"><spring:message code="lbl.en" text="ENGLISH" /></button></a> | 
                              <button type ="submit" class="lang" name="lang" value="ch"><spring:message code="lbl.ch" text="CHINESE" /></button></a>
                    Current Locale : ${pageContext.response.locale}
                </form:form>
            </div>
            <div align="center">
                <h3><spring:message code="lbl.index.hdr" text="Make Changes to:" /></h3>
                <a href="PersonMain.jsp"><button size="20"><spring:message code="lbl.person" text="PERSON" /></button></a><br>
                <form:form action="roleController" method="GET">
                    <button type="submit" size="20"><spring:message code="lbl.role" text="ROLE" /></button>
                </form:form>
            </div>
            <div>
                <spring:message code="lbl.fileupload" text="File Upload:" />
                <form:form action="fileUpload" method="POST" commandName="fileDto" enctype="multipart/form-data">
                    <form:errors path="*" cssClass="errorblock" element="div"/>
                    <spring:message code="lbl.selectfile" text="Select a file to upload:" /> <input name="file"  type="file" /> 
                    <br/> 
                    <button type="submit"><spring:message code="lbl.upload" text="Upload" /></button> 
                </form:form>
            </div>
        </div>
    </body>
</html>
