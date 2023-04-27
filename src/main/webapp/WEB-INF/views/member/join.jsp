<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
    <meta name="description" content="" />
    <meta name="author" content="" />
    <title>Modern Business - Start Bootstrap Template</title>
    <!-- css/favicon -->
       <c:import url="../temp/style.jsp"></c:import>
    <!-- css/favicon -->
</head>
<body class="d-flex flex-column h-100">
    <main class="flex-shrink-0">
       <!-- Navigation-->
         <c:import url="../temp/header.jsp"></c:import>
      <!-- Nav 끝 -->
      
            <!-- Page content-->
            <section class="py-5">
                <div class="container px-5">
                    <!-- Contact form-->
                    <div class="bg-light rounded-3 py-5 px-4 px-md-5 mb-5">
                        <div class="text-center mb-5">
                            <div class="feature bg-primary bg-gradient text-white rounded-3 mb-3"><i class="bi bi-envelope"></i></div>
                            <h1 class="fw-bolder">Member Join</h1>
                        </div>
                        <div class="row gx-5 justify-content-center">
                            <div class="col-lg-8 col-xl-6">
                                <!-- * * * * * * * * * * * * * * *-->
                                <!-- * * SB Forms Contact Form * *-->
                                <!-- * * * * * * * * * * * * * * *-->
                                <!-- This form is pre-integrated with SB Forms.-->
                                <!-- To make this form functional, sign up at-->
                                <!-- https://startbootstrap.com/solution/contact-forms-->
                                <!-- to get an API token!-->
                                <form:form action="./join" id="joinForm" method="post" modelAttribute="memberVO">
                                <%-- <form action="./join" id="joinForm" method="post" data-sb-form-api-token="API_TOKEN"> --%>
                                    <!-- username input-->
                                    <div class="form-floating mb-3">
                                    	<form:input path="username" cssClass="form-control" id="username"/>                                        
                                        <form:label path="username">USERNAME</form:label>                            
                                        <form:errors path="username"></form:errors>                                        
                                    </div>
                                    <!-- passWord input-->
                                    <div class="form-floating mb-3">
                                        <form:password path="password" cssClass="form-control" id="password"/>
                                        <form:label path="password">PASSWORD</form:label>
                                        <form:errors path="password"></form:errors>                                        
                                    </div>
                                    
                                    <div class="form-floating mb-3">
                                        <form:password path="passwordCk" cssClass="form-control" id="passwordCk"/>
                                        <form:label path="passwordCk">PASSWORD_CHECK</form:label>
                                        <form:errors path="passwordCk"></form:errors>                                        
                                    </div>
                                    
                                    <div class="form-floating mb-3">
                                        <form:input path="name" cssClass="form-control" id="name"/>
                                        <form:label path="name">NAME</form:label>
                                        <form:errors path="name"></form:errors>                                       
                                    </div>
                                    
                                    <div class="form-floating mb-3">
                                        <form:input path="email" cssClass="form-control" id="email"/>
                                        <form:label path="email">EMAIL</form:label>
                                        <form:errors path="email"></form:errors>                                       
                                    </div>
                                    
                                    <div class="form-floating mb-3">
                                        <input type="date" id="birth" name="birth" class="form-control">
                                        <label for="birth">BIRTH</label>
                                        <form:errors path="birth"></form:errors>                                                                               
                                    </div>
                                    
                                    <!-- Login btn -->
                                    <div class="d-flex justify-content-center form-floating mb-3">                                    	
                                        <button type="submit" id="joinBtn" class="btn btn-primary btn-lg px-4 me-sm-3">회원가입</button>
                                    </div>
                                <%-- </form> --%>
                                </form:form>
                            </div>
                        </div>
                    </div>
                </div>
            </section>
    
    </main>
    <!-- Footer 적용 -->
        <c:import url="../temp/footer.jsp"></c:import>
     <!-- Footer 끝 -->
     <script type="text/javascript" src="../js/joinFormCheck.js"></script>
</body>
</html>