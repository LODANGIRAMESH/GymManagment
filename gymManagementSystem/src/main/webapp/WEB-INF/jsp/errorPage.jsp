<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Seat Not Available</title>
    <style>
        body {
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
            font-family: Arial, sans-serif;
            background-color: #f8f9fa;
        }
        .alert-box {
            background-color: #f8d7da;
            color: #721c24;
            border: 1px solid #f5c6cb;
            padding: 20px;
            border-radius: 5px;
            text-align: center;
            max-width: 400px;
            width: 100%;
        }
        .alert-box h1 {
            margin: 0 0 10px 0;
        }
        .alert-box p {
            margin: 0;
        }
    </style>
</head>
<body>
    <div class="alert-box">
        <h1>Seat Not Available</h1>
        <p>Oh no! The seat has been filled. Please book another slot.</p>
    </div>
</body>
</html>
