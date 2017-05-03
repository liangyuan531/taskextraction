<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<link href="//maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css" rel="stylesheet">

<link rel="stylesheet" href="css/login.css" />

<title>Login</title>
</head>
<body>
<form action="login" method="post">
<div class="container">
    <div class="row">
        <div class="col-md-offset-5 col-md-3">
            <div class="form-login">
            <h4>User Login</h4>
            <input type="text" name="username" id="userName" class="form-control input-sm chat-input" placeholder="username" required="required"/>
            <br>
            <input type="password" name="password" id="userPassword" class="form-control input-sm chat-input" placeholder="password" required="required" />
            <br>
            <div class="wrapper">
            <span class="group-btn">     
                <input type="submit" class="btn btn-primary btn-md"/>
            </span>
            </div>
            </div>
        </div>
    </div>
</div>
</form>
</body>
</html>