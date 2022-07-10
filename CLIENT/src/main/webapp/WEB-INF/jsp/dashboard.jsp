<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style>
    body {
        background-color: white;
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

    table {
      font-family: arial, sans-serif;
      border-collapse: collapse;
      width: 100%;
    }

    td, th {
      border: 1px solid #dddddd;
      text-align: left;
      padding: 8px;
    }

    tr:nth-child(even) {
      background-color: #dddddd;
    }

    h4{
        text-align: center;
    }

</style>
<title>Welcome page is here</title>
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Poppins:ital,wght@0,100;0,200;0,300;0,400;0,500;0,600;0,700;0,800;0,900;1,100;1,200;1,300;1,400;1,500;1,600;1,700;1,800;1,900&display=swap" rel="stylesheet">

</head>
<body>
    <h1>HMS dashboard</h1>

    <h4>List of patients </h4>
    <a href="add-patient">Add new patient</a>
   <table>
     <tr>
       <th>Full Name</th>
       <th>Age</th>
       <th>Phone number</th>
       <th>ACTIONS</th>
     </tr>

    <c:forEach items="${patients}" var="patient">
            <tr>
               <td>${patient.fullName}</td>
               <td>${patient.age}</td>
               <td>${patient.phoneNumber}</td>
               <td>
                    <a href="edit-patient?id=${patient.id}">Edit</a>
                    <a href="delete-patient?id=${patient.id}">Delete</a>
               </td>
            </tr>
    </c:forEach>
    </table>
</body>


</html>