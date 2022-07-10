<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style>
    body {
        background-color: #C4E5FF;
        padding: 2em;
        font-family: 'Poppins', sans-serif;
    }

    form {
        text-align: center;
        width: 50vw;
        margin: auto;
        background: #ABD9FF;
        padding: 20px;
        color: #004681;
    }
    form div{
        text-align: left !important;
    }

    form input {
        padding: 5px 10px;
        border: 1px solid #abd9ff;
        outline: none;
        border-radius: 3px;
    }

    input[type="submit"] {
        background: #004681;
        color: white;
        margin-top: 24px;
        width: 263px;
        height: 43px;
    }


    h1 {
        background: #185359;
        padding: 16px;
        margin-top: -45px;
        width: 97vw;
        margin-left: -42px;
        height: 5vh;
        font-size: 17px;
        margin-bottom: 60px;
        position: sticky;
        top: 0px;
        color: white;
    }

</style>
<title>Welcome page is here</title>
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Poppins:ital,wght@0,100;0,200;0,300;0,400;0,500;0,600;0,700;0,800;0,900;1,100;1,200;1,300;1,400;1,500;1,600;1,700;1,800;1,900&display=swap" rel="stylesheet">

</head>
<body>
    <h1>Login</h1>
    <div>${error}</div>
    <form action="logInRequest">
		<div>SignUp :</div><br>
		<div>User name :</div> <input type="text" name="username"><br>
		<div>Password :</div> <input type="password" name="password"><br>

		<input type="submit">
	</form><br><br><br>



</body>


</html>