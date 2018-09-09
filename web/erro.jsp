<%-- 
    Document   : erro
    Created on : 24/03/2018, 17:42:57
    Author     : onurb
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><c:out value="${title}"/></title>
        <c:choose>
            <c:when test="${title != null}">
                <title>Mercad&atilde;o do Aluno <c:out value="| ${title}"/></title>
            </c:when>
            <c:when test="${param.title != null}">
                <title>Mercad&atilde;o do Aluno <c:out value="| ${param.title}"/></title>
            </c:when>
            <c:otherwise>
                <title>Mercad&atilde;o do Aluno | In&iacute;cio</title>
            </c:otherwise>
        </c:choose>
    </head>
    <body>
        <h1>Ocorreu uma falha:</h1>
        <h4>
            <c:choose>
                <c:when test="${(!empty(msg))}">
                    <h4><c:out value="${msg}"/></h4>
                </c:when>
                <c:when test="${(!empty(param.msg))}">
                        <h4><c:out value="${param.msg}"/></h4>
                </c:when>
            </c:choose>
        </h4>
    </body>
</html>
