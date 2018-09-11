<%-- 
    Document   : head
    Created on : 03/04/2018, 21:01:22
    Author     : Marcos
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="google-signin-client_id" content="416664861929-ceaen1jvonlg3vecdhpmnks7029a2gnd.apps.googleusercontent.com">
        <link rel="shortcut icon" href="assets/images/favicon.ico" type="image/x-icon">
        <!--script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script-->
        <!-- Adicionando jQuery para ser utilizado nas funções -->
        <script src="assets/jquery/jquery.min.js" type="text/javascript"></script>
        <script src="https://apis.google.com/js/platform.js" async defer></script>
        <script
            src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"
            integrity="sha256-T0Vest3yCU7pafRw9r+settMBX6JkKN06dqBnpQ8d30="
            crossorigin="anonymous">           
        </script>

        <c:choose>
            <c:when test="${title != null}">
                <title>Mercadão do Aluno <c:out value="| ${title}"/></title>
            </c:when>
            <c:otherwise>
                <title>Mercadão do Aluno | Início</title>
            </c:otherwise>
        </c:choose>


        <link href="assets/bootstrap/css/bootstrap.min.css" rel="stylesheet">
        <link href="assets/css/shop-homepage.css" rel="stylesheet">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
        <link href="assets/css/mainCss.css" rel="stylesheet">

    </head>
    <body>
        <nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
            <div class="container">
                <c:choose>
                    <c:when test="${(empty(user))}">
                        <a class="navbar-brand" href="index.jsp">Mercad&atilde;o do Aluno</a>
                    </c:when>
                    <c:otherwise>
                        <a class="navbar-brand" href="home.jsp">Mercad&atilde;o do Aluno</a>
                    </c:otherwise>
                </c:choose>
                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarResponsive">
                    <ul class="navbar-nav ml-auto">
                        <li class="nav-item">
                            <c:choose>
                                <c:when test="${user != null}">
                                    <a class="nav-link" href="home.jsp">Home</a>                                    
                                </c:when>
                                <c:otherwise>
                                    <a class="nav-link" href="index.jsp">Home</a>                                    
                                </c:otherwise>
                            </c:choose>
                        </li>

                        <li class="nav-item">
                            <c:if test="${user != null}">
                                <c:choose>
                                    <c:when test="${user.senha.equals('')}">
                                        <script>
                                            function onLoad() {
                                                gapi.load('auth2', function () {
                                                    gapi.auth2.init();
                                                });
                                            }
                                            function signOut() {
                                                var auth2 = gapi.auth2.getAuthInstance();
                                                auth2.signOut().then(function () {
                                                    console.log('User signed out.');
                                                });
                                            }
                                        </script>

                                        <a class="nav-link" href="LoginServlet?action=LOGOUT" onclick="signOut();">Logout Google</a>
                                        <script src="https://apis.google.com/js/platform.js?onload=onLoad" async defer></script>

                                    </c:when>
                                    <c:otherwise>
                                        <a class="nav-link" href="LoginServlet?action=LOGOUT">Logout</a>
                                    </c:otherwise>
                                </c:choose>   
                            </c:if>

                            <c:if test="${user == null}">
                                <a class="nav-link" href="login.jsp">Login</a>
                            </c:if>

                        </li>
                        <li class="nav-item">
                            <c:choose>
                                <c:when test="${user != null}">
                                    <a class="nav-link" href="MainPageServlet?action=ANUNCIO">Anunciar</a>
                                </c:when>
                                <c:otherwise>
                                    <a class="nav-link" href="MainPageServlet?action=CLIENTE">Cadastrar</a>
                                </c:otherwise>
                            </c:choose>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>

        
        <!-- Adicionando Bootstrap -->
        <script src="assets/bootstrap/js/bootstrap.bundle.js" type="text/javascript"></script>
        <!-- Importa funções padrão -->
        <script src="assets/js/helperFunctions.js" type="text/javascript"></script>


        <div class="container">
            <div class="row">