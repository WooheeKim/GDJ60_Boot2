<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
                                <form action="./join" id="joinForm" method="post" data-sb-form-api-token="API_TOKEN">
                                    <!-- userName input-->
                                    <div class="form-floating mb-3">
                                        <input name="userName" class="form-control" id="userName" type="text" data-sb-validations="required" required="required"/>
                                        <label for="userName">USERNAME</label>
                                        <div class="invalid-feedback" data-sb-feedback="name:required">username is required.</div>
                                    </div>
                                    <!-- passWord input-->
                                    <div class="form-floating mb-3">
                                        <input name="password" class="form-control" id="password" type="password" data-sb-validations="required" required="required" />
                                        <label for="password">PASSWORD</label>
                                        <div class="invalid-feedback" data-sb-feedback="phone:required">password is required.</div>
                                    </div>
                                    
                                    <div class="form-floating mb-3">
                                        <input name="passwordck" class="form-control" id="passwordck" type="password" data-sb-validations="required" required="required" />
                                        <label for="passwordck">PASSWORD_CHECK</label>
                                        <div class="invalid-feedback" data-sb-feedback="phone:required">password_Check is required.</div>
                                    </div>
                                    
                                    <div class="form-floating mb-3">
                                        <input name="name" class="form-control" id="name" type="text" data-sb-validations="required" required="required"/>
                                        <label for="name">NAME</label>
                                        <div class="invalid-feedback" data-sb-feedback="phone:required">name is required.</div>
                                    </div>
                                    
                                    <div class="form-floating mb-3">
                                        <input name="email" class="form-control" id="email" type="email" data-sb-validations="required" />
                                        <label for="email">EMAIL</label>
                                        <div class="invalid-feedback" data-sb-feedback="phone:required">email is required.</div>
                                    </div>
                                    
                                    <div class="form-floating mb-3">
                                        <input name="birth" class="form-control" id="birth" type="date" data-sb-validations="required" required="required"/>
                                        <label for="birth">BIRTH</label>
                                        <div class="invalid-feedback" data-sb-feedback="phone:required">birthday is required.</div>
                                    </div>
                                    
                                    <!-- Login btn -->
                                    <div class="d-flex justify-content-center form-floating mb-3">                                        
                                        <button type="submit" id="joinBtn" class="btn btn-primary btn-lg px-4 me-sm-3">회원가입</button>
                                    </div>
                                </form>
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