<%--
  Created by IntelliJ IDEA.
  User: panda
  Date: 28.1.18
  Time: 1.40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${lang}" scope="session"/>
<fmt:setBundle basename="text" var="rb"/>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <title><fmt:message key="lot_full.title" bundle="${rb}"/></title>

    <!-- Bootstrap -->
    <link href="css/bootstrap.min.css" rel="stylesheet">

    <link href="css/dopstyle.css" rel="stylesheet">
    <script src="js/main.js"></script>
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>
<c:if test="${ (empty lot) && (empty errorLotIsEmpty) }">
    <script language="JavaScript" type="text/javascript">
        location = "auction?command=lot_full"
    </script>
</c:if>
<c:import url="../header.jsp"/>
<div class="container">
    <div class="row">

        <div class="col-md-offset-1 col-md-10 col-sm-offset-2 col-sm-8 col-xs-12 main">
            <c:import url="../navbar.jsp"/>
            <h2><fmt:message key="lot_full.header" bundle="${rb}"/></h2>
            <br>
            <c:if test="${ not empty errorLotIsEmpty}">
                <div class="alert-danger alert">${errorLotIsEmpty}</div>
            </c:if>

            <c:if test="${not empty lot}">
                <div class="row">

                    <c:if test='${(lot.state.name() == "DENIED"||lot.state.name() == "ADDED")&&lot.owner.id==userId}'>
                        <div class="col-md-12">
                            <div class="form-group">
                                <a href="auction?command=delete_lot&id=${lot.id}" class="btn btn-primary"
                                   role="button"><fmt:message key="lot_full.deleteButton" bundle="${rb}"/></a>
                            </div>
                        </div>
                    </c:if>
                    <c:if test='${(lot.state.name() == "ACCEPTED")&&lot.owner.id!=userId}'>
                        <form action="auction" method="post">
                            <input type="hidden" name="command" value="buy_lot">
                            <input type="hidden" name="id" value="${lot.id}">
                            <div class="col-md-6 col-xs-12">
                                <div class="form-group">
                                    <label for="price"><fmt:message key="lot_full.newPrice" bundle="${rb}"/> </label>
                                    <input type="number" step="0.01" min="0" class="form-inline" id="price" name="price"  required>
                                </div>
                            </div>
                            <div class="col-md-6 col-xs-12">
                                <button class="btn btn-primary btn-block" type="submit"><fmt:message
                                        key="lot_full.buyButton"
                                        bundle="${rb}"/></button>
                            </div>
                        </form>
                    </c:if>

                </div>
                <div class="list-group">
                    <div class="list-group-item">
                        <div class="row">
                            <div class="col-md-6 col-xs-12">
                                <label><fmt:message key="lot_full.state" bundle="${rb}"/></label>
                            </div>
                            <div class="col-md-6 col-xs-12">
                                <c:if test='${lot.state.name() == "ACCEPTED"||lot.state.name() == "SOLD"}'>
                                    <label class="label label-success"><c:out value="${lot.state}"/></label>
                                </c:if>
                                <c:if test='${lot.state.name() == "DENIED"}'>
                                    <label class="label label-danger"><c:out value="${lot.state}"/></label>
                                </c:if>
                                <c:if test='${lot.state.name() == "ADDED"}'>
                                    <label class="label label-default"><c:out value="${lot.state}"/></label>
                                </c:if>
                            </div>
                        </div>
                    </div>
                    <div class="list-group-item">
                        <div class="row">
                            <div class="col-md-6 col-xs-12">
                                <label><fmt:message key="lot_full.owner" bundle="${rb}"/></label>
                            </div>
                            <div class="col-md-6 col-xs-12">
                                <label><c:out value="${lot.owner.login}"/></label>
                            </div>
                        </div>
                    </div>
                    <div class="list-group-item">
                        <div class="row">
                            <div class="col-md-6 col-xs-12">
                                <label><fmt:message key="lot_full.buyer" bundle="${rb}"/></label>
                            </div>
                            <div class="col-md-6 col-xs-12">
                                <label><c:out value="${lot.buyer.login}"/></label>
                            </div>
                        </div>
                    </div>
                    <div class="list-group-item">
                        <div class="row">
                            <div class="col-md-6 col-xs-12">
                                <label><fmt:message key="lot_full.flower" bundle="${rb}"/></label>
                            </div>
                            <div class="col-md-6 col-xs-12">
                                <label><c:out value="${lot.flower.name}"/></label>
                            </div>
                        </div>
                    </div>
                    <div class="list-group-item">
                        <div class="row">
                            <div class="col-md-6 col-xs-12">
                                <label><fmt:message key="lot_full.address" bundle="${rb}"/></label>
                            </div>
                            <div class="col-md-6 col-xs-12">
                                <label><c:out
                                        value="${lot.address.countryName}, ${lot.address.cityName}, ${lot.address.street} - ${lot.address.houseNumber}"/></label>
                            </div>
                        </div>
                    </div>
                    <div class="list-group-item">
                        <div class="row">
                            <div class="col-md-6 col-xs-12">
                                <label><fmt:message key="lot_full.currentPrice" bundle="${rb}"/></label>
                            </div>
                            <div class="col-md-6 col-xs-12">
                                <label><c:out value="${lot.currentPrice}"/></label>
                            </div>
                        </div>
                    </div>
                    <div class="list-group-item">
                        <div class="row">
                            <div class="col-md-6 col-xs-12">
                                <label><fmt:message key="lot_full.count" bundle="${rb}"/></label>
                            </div>
                            <div class="col-md-6 col-xs-12">
                                <label><c:out value="${lot.count}"/></label>
                            </div>
                        </div>
                    </div>
                    <div class="list-group-item">
                        <div class="row">
                            <div class="col-md-6 col-xs-12">
                                <label><fmt:message key="lot_full.end" bundle="${rb}"/></label>
                            </div>
                            <div class="col-md-6 col-xs-12">
                                <label><c:out value="${lot.end}"/></label>
                            </div>
                        </div>
                    </div>
                    <div class="list-group-item">
                        <div class="row">
                            <div class="col-md-6 col-xs-12">
                                <label><fmt:message key="lot_full.description" bundle="${rb}"/></label>
                            </div>
                            <div class="col-md-6 col-xs-12">
                                <label><c:out value="${lot.description}"/></label>
                            </div>
                        </div>
                    </div>
                </div>

                <!--<c:if test="${userInfo.id == userId}">
                <div class="form-group">
                <a href="auction?command=user_info_for_change" class="btn btn-primary pull-right" role="button"><fmt:message
                    key="user_info.changeButton" bundle="${rb}"/></a>
                </div>
            </c:if>-->
            </c:if>
        </div>
    </div>
</div>
<c:import url="../footer.jsp"/>

<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="js/bootstrap.min.js"></script>
</body>
</html>


