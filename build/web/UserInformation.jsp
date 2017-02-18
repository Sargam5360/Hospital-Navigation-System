<%-- 
    Document   : UserInformation
    Created on : Apr 15, 2016, 2:07:54 PM
    Author     : Nisarg
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <title>User Input</title>
        <c:choose>
            <c:when test='${msg != null}'>
                <link rel="stylesheet" type="text/css" href="../css/LayoutMain.css" />
                <link rel="stylesheet" type="text/css" href="../css/style.css" />
            </c:when>
            <c:otherwise>
                <link rel="stylesheet" type="text/css" href="css/LayoutMain.css" />
                <link rel="stylesheet" type="text/css" href="css/style.css" />
            </c:otherwise>
        </c:choose>

    </head>
    <body style="overflow:hidden;">
        <div id="header">
            <c:choose>
                <c:when test='${msg != null}'>
                    <img id="widh" src="../Images/Login Page Header.png" alt="header">
                </c:when>
                <c:otherwise>
                    <img id="widh" src="Images/Login Page Header.png" alt="header">
                </c:otherwise>
            </c:choose>
            <div style="margin-top: -144px">
                <c:choose>
                    <c:when test='${msg != null}'>
                        <img id="symbolScot" src="../Images/hospital1.png" alt="symbol">
                    </c:when>
                    <c:otherwise>
                        <img id="symbolScot" src="Images/hospital1.png" alt="symbol">
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
        <div id="body" style="margin-top: 190px">		
            <div id="wrapper">
                <div id="login" class="animate form">
                    <form  name="user-data" action="${pageContext.request.contextPath}/UserDataController/userData" method="POST" autocomplete="on">
                        <h1 id="h1Size">Hospital Admission System</h1>
                        <p>
                            <label for="email" class="uname" data-icon="">Age:</label> 
                            <input type="text" name="age" id="age"></br>
                        </p>
                        <p> 
                            <label for="password" class="youpasswd" data-icon=""> Diseases code:  </label>
                            <input type="text" name="d_code" id="d_code" >
                        </p>
                        </br>
                        <p class="login button"> 
                            <input type="submit" value="Fetch Data" />                            
                        </p>
                    </form>
                </div>
            </div>
        </div>
        <div>
            <c:import url="footer.jsp"/>
        </div>
    </body>
</html>
