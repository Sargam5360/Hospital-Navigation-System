<%-- 
    Document   : cost.jsp
    Created on : Apr 23, 2016, 8:05:20 PM
    Author     : Nisarg
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Imformation Page</title>
        <link rel="stylesheet" type="text/css" href="../css/bootstrap.css"/>
        <link rel="stylesheet" type="text/css" href="css/bootstrap.css"/>
        <link rel="stylesheet" type="text/css" href="css/AdminHomeCSS.css"/>
        <link rel="stylesheet" type="text/css" href="css/style8.css"/>
        <link rel="stylesheet" type="text/css" href="css/simpletable.css"/>
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
    <body>
        <div class="panel panel-primary panel-semesterContent">
            <div class="panel-heading" align="center"> Rating by Cost</div>

            <table border='0' align='center' class="table table-striped">
                <tr class="tab-content">
                    <th style="text-align: left"><font style="font-family: cursive;font-size: medium">National Provider id</font></th>
                    <th style="text-align: left"><font style="font-family: cursive;font-size: medium;">Avg Total Cost</font></th>    
                    <th style="text-align: center"><font style="font-family: cursive;font-size: medium">Rating</font  ></th>
                </tr>
                <c:forEach var="content2" items="${content2}">

                    <tr>
                        <td style="text-align: center"><input type="text" class="form-control" value="${content2.national_provider_id}" readonly="true"></td>
                        <td style="text-align: center"><input type="text" class="form-control" value="${content2.avg_cost}" readonly="true" style="text-align: center"></td>                    
                        <td style="text-align: center"><span class="stars"><c:forEach var='i' begin="1" end="${content2.rating}"><img src="../Images/star-full-icon.png" height="15"></c:forEach></td>

                    </tr>   

                </c:forEach>    
            </table>
        </div>
    </body>
</html>
