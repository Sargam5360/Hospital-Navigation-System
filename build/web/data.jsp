<%-- 
    Document   : data.jsp
    Created on : Apr 23, 2016, 7:27:15 PM
    Author     : Nisarg
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://big.faceless.org/products/graph" prefix="bfg" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="../css/AdminHomeCSS.css">
        <link rel="stylesheet" type="text/css" href="../css/jquery.ui.theme.css">
        <link rel="stylesheet" type="text/css" href="../css/containerCss.css"/>


        <script src="../jQuery/jquery-1.11.0.js"></script>
        <script src="../jQuery/jquery-ui-1.10.4.js"></script>
        <script src="../jQuery/bfograph.js"></script>

        <link rel="stylesheet" type="text/css" href="css/AdminHomeCSS.css">
        <link rel="stylesheet" type="text/css" href="css/jquery.ui.theme.css">
        <link rel="stylesheet" type="text/css" href="css/containerCss.css"/>

        <script src="jQuery/jquery-1.11.0.js"></script>
        <script src="jQuery/jquery-ui-1.10.4.js"></script>
        <script src="jQuery/bfograph.js"></script>

        <script>
            function middleDiv() {
                var heightDiv = (document.documentElement.clientHeight) - 154 + "px";
                document.getElementById('middleDiv').style.height = heightDiv;
                document.getElementById('middleDiv2').style.height = heightDiv;
            }

        </script>
        <script>
            $(function () {
                $("#accordion").accordion({
                    heightStyle: "content"
                });
            });
        </script>
        <style>
            .overlay {
                height: 100%;
                width: 0;
                position: fixed;
                z-index: 1;
                top: 0;
                left: 0;
                background-color: rgb(0,0,0);
                background-color: rgba(0,153,204, 0.9);
                overflow-x: hidden;
                transition: 0.5s;
            }

            .overlay-content {
                position: relative;
                top: 25%;
                width: 100%;
                text-align: center;
                margin-top: 30px;
            }

            .overlay a {
                padding: 8px;
                text-decoration: none;
                font-size: 36px;
                color: #818181;
                display: block;
                transition: 0.3s;
            }

            .overlay a:hover, .overlay a:focus {
                color: #f1f1f1;
            }

            .closebtn {
                position: absolute;
                top: 10px;
                right: 30px;
                font-size: 40px !important;
            }

            @media screen and (max-height: 450px) {
                .overlay a {font-size: 20px}
                .closebtn {
                    font-size: 40px !important;
                    top: 15px;
                    right: 35px;
                }
            }
        </style>
    </head>
    <body style="overflow: hidden; background-color: #F1F4F9;" onload="middleDiv();">
        <div>
            <c:choose>
                <c:when test = "${msg != null}">
                    <c:import url="header_1.jsp"/>
                </c:when>
                <c:otherwise>
                    <c:import url="header.jsp"/>
                </c:otherwise>                  

            </c:choose>
        </div>
    </form>   
    <div class="container">
        <div class="row">
            <div class="col-md-4" style="height: 35px; background-color: #018fbe; font-size: 20px; font-family: 'Bebas Neue';  color:#FFFFFF;"><div style="text-align: center; padding-top: 8px">TABLES</div></div>
            <div class="col-md-8" style="height: 35px; background-color: #018fbe; font-size: 20px; font-family: 'Bebas Neue';  color:#FFFFFF;"><div style="text-align: center; padding-top: 8px">DETAILS</div></div>
        </div>
        <div class="row">
            <div class="col-md-4" style="overflow: auto; background-color: white" id="middleDiv">
                <div id="accordion">
                    <h5>Overall Stats</h5>
                    <div>
                        <ul style="list-style-type: disc">
                            <form id="form1" method="POST" action="${pageContext.request.contextPath}/UserDataController/overall"> 
                                <input type="hidden" name="age" id="age" value="${age}">
                                <input type="hidden" name="d_code" id="d_code" value="${d_code}">
                                <li><a href="#" onclick="document.getElementById('form1').submit();" style="text-decoration: none;font-size:16px">
                                        Overall Stats
                                    </a></li>
                            </form>
                        </ul>
                    </div>
                    <h5>Particular Stats</h5>
                    <div>
                        <ul style="list-style-type:disc">
                            <form id="form2" method="POST" action="${pageContext.request.contextPath}/UserDataController/stay"> 
                                <input type="hidden" name="age1" id="age1" value="${age}">
                                <input type="hidden" name="d_code1" id="d_code1" value="${d_code}">
                                <li><a href="#" onclick="document.getElementById('form2').submit();" style="text-decoration: none;font-size:16px">
                                        Length of Stay
                                    </a></li>
                            </form>
                            <form id="form3" method="POST" action="${pageContext.request.contextPath}/UserDataController/cost"> 
                                <input type="hidden" name="age2" id="age2" value="${age}">
                                <input type="hidden" name="d_code2" id="d_code2" value="${d_code}">
                                <li><a href="#" onclick="document.getElementById('form3').submit();" style="text-decoration: none;font-size:16px;">
                                        Total Cost
                                    </a></li>
                            </form>
                        </ul>                        
                    </div>
                    <h5 onclick="openNav();">Graphical Presentation</h5>

                </div>
            </div>

            <div class="col-md-8 " style="background-color: white;overflow: auto;" id="middleDiv2">

                <c:if test="${tableName eq 'overall'}">
                    <c:import url="Retrived.jsp"/>
                </c:if>
                <c:if test="${tableName eq 'stay'}">
                    <c:import url="stay.jsp"/>
                </c:if>
                <c:if test="${tableName eq 'cost'}">
                    <c:import url="cost.jsp"/>
                </c:if>
            </div>
        </div>
    </div>
</div>
</div>
<div>
    <c:import url="footer.jsp"/>
</div>
<div id="mynav" class="overlay">
    <a href="javascript:void(0)" class="closebtn" onclick="closeNav()">×</a>
    <div class="overlay-content">
        <div>,

            <bfg:axesgraph width="1000" height="300" id="mygraph" border="1" defaultcolors="transparent(blue,0.8)">
                <bfg:axis pos="bottom" type="bar" rotate="90" paddingleft="5" align="left top">
                    <bfg:label font="14pt bold Arial">National Provider</bfg:label>
                </bfg:axis>
                
                <c:if test="${tableName eq 'stay'}">
                    <bfg:label font="14pt bold Arial">Avg Length of Stay by National Provider</bfg:label>
                                        <bfg:barseries name="stay"  barwidth="0.5" >
                        <c:forEach items="${content1}" var="content1">
                            <bfg:data x="${content1.national_provider_id}" y="${content1.avg_stay}" />
                        </c:forEach>
                    </bfg:barseries>
                </c:if>
                <c:if test="${tableName eq 'cost'}">
                    <bfg:label font="14pt bold Arial">Avg Total Cost by National Provider</bfg:label>
                                    
                    <bfg:barseries name="cost" barwidth="0.5" >
                        <c:forEach items="${content2}" var="content2">
                            <bfg:data x="${content2.national_provider_id}" y="${content2.avg_cost}" />
                        </c:forEach>
                    </bfg:barseries>
                </c:if>
            </bfg:axesgraph>

        </div>
    </div>
</div>
<script>
    function openNav() {
        document.getElementById("mynav").style.width = "100%";
    }

    function closeNav() {
        document.getElementById("mynav").style.width = "0%";
    }
</script>
</body>









</html>