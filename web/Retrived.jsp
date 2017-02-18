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
            <div class="panel-heading" align="center"> Overall Stats</div>

            <table border='0' align='center' class="table table-striped">
                <tr class="tab-content">
                    <th style="text-align: left"><font style="font-family: cursive;font-size: medium">Avg Length of Stay</font></th>
                    <th style="text-align: left"><font style="font-family: cursive;font-size: medium">Avg Cost</font></th>
                    <th style="text-align: left"><font style="font-family: cursive;font-size: medium">Mortality Ratio(%)</font></th>
                    <th style="text-align: left"><font style="font-family: cursive;font-size: medium">Admission Diagonosis code</font></th>
                </tr>
                <c:forEach var="content" items="${content}">
                    <tr>
                        <td style="text-align: center"><input type="text" class="form-control" value="${content.avg_stay}" readonly="true"></td>
                        <td style="text-align: center"><input type="text" class="form-control" value="${content.avg_cost}" readonly="true"></td>
                        <td style="text-align: center"><input type="text" class="form-control" value="${content.mortality_ratio}" readonly="true"></td>
                        <td style="text-align: center"><input type="text" class="form-control" value="${content.d_code}" readonly="true"></td>                    
                    </tr>    
                </c:forEach>    
            </table>
        </div>        
    </body>
</html>
