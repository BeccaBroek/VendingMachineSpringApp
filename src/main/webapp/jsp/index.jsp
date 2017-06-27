<%-- 
    Document   : index
    Created on : Jun 26, 2017, 9:39:10 AM
    Author     : Becca
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Vending Machine</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">    

        <style>
            body{
                background-color:#BFC9CA;
            }
            #itemDiv{
                background-color: #F2F4F4;
                margin:2px;
            }
            .btn{
                background-color: #95A5A6;  
                margin:1px;
            }
            h1{
                text-align: center;
            }

        </style>
    <h1>Vending Machine</h1>
    <hr/>
    </head>
    <body>
        <div class="container">
            <div class="row">
                <div class="col-md-7">
                    <div class="col-md-offset-2 col-md-8">
                        <c:forEach items="${allFruit}" var="fruit"> 
                            <div class="col-md-5 well" id="itemDiv">
                                <div class="row">
                                    <c:out value="${fruit.getName()}"></c:out><br>
                                    Quantity:<c:out value="${fruit.getQuantity()}"></c:out><br>
                                    $<c:out value="${fruit.getCost()}"></c:out>

                                    </div>
                                    <div class="row">
                                        <div class="col-md-12">
                                            <form method="post" action="selectItem">
                                                <center><button type="submit"class="btn" name="item" value="${fruit.getName()}">Select Item</button></center>
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </div>
                <div class="col-md-5">
                    <div class="row">
                        <form>
                            <div class="form-group">
                                <div class="col-md-2">
                                    <label for="messages">Messages:</label>
                                </div>
                                <div class="col-md-10">
                                    <textarea type="text" id="messages"class="form-control"><c:out value="${message}"></c:out></textarea>
                                    </div>
                                </div>
                            </form>
                        </div>
                                    <hr/>
                        <form method="post" action="addMoney">
                            <div class="form-group">

                                <div class="col-md-2">
                                    <label for="total">Total:</label>
                                </div>
                                <div class="col-md-10">
                                    <input type="text"class="form-control" name="total" value="<c:out value="${total}"></c:out>">
                                </div>
                                
                                <div class="row">     
                                    <div class="col-md-offset-4 col-md-4">
                                        <button type="submit"class="btn" name="money" value="1">1.00</button>
                                        <button type="submit"class="btn" name="money" value=".25">0.25</button>
                                        <button type="submit"class="btn" name="money" value=".10">0.10</button>
                                        <button type="submit"class="btn" name="money" value=".05">0.05</button>
                                    </div>
                                </div>
                            </div>
                                <hr/>
                        </form>


                        <div class="row">          
                            <form method="post" action="selectItem">
                                <div class="form-group">
                                    <div class="col-md-2">
                                        <label for="currentItem">Item Selected:</label>
                                    </div>
                                    <div class="col-md-10">
                                        <input type="text"class="form-control" name="currentItem" value="<c:out value="${itemSelected}"></c:out>">
                                    </div>
                                </div>
                            </form> 
                        </div>
                                    

                        <div class="row">
                            <center><form method="post" action="purchaseItem">
                                    <button type="submit"class="btn" name="purchase">Purchase <c:out value="${itemSelected}"></c:out></button></center>
                            </form>
                        </div>
                            <hr/>
                        <div class="row">
                            <form method="get" action="getChange">
                                <div class="col-md-2"><label for="quarters">Quarters:</label></div>
                                <div class="col-md-2"><input type="text"class="form-control" name="quarters" value="<c:out value="${quarters}"></c:out>"></div>
                                <div class="col-md-2"><label for="dimes">Dimes:</label></div>
                                <div class="col-md-2"><input type="text"class="form-control" name="dimes" value="<c:out value="${dimes}"></c:out>"></div>
                                <div class="col-md-2"><label for="nickels">Nickels:</label></div>
                                <div class="col-md-2"><input type="text"class="form-control" name="nickels" value="<c:out value="${nickels}"></c:out>"></div>
                                </div>
                                <center><button type="submit"class="btn" name="getChange">Get Change</button></center>
                            </form>
                        

                    </div>
                </div>
            </div>







            <!-- Placed at the end of the document so the pages load faster -->
            <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

    </body>
</html>
