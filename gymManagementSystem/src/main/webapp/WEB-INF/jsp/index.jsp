<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>DecodersGym - Home</title>
    <style>
        body, html {
            height: 100%;
            margin: 0;
            font-family: Arial, sans-serif;
        }
        .bg {
            background-image: url('https://t3.ftcdn.net/jpg/00/95/66/36/360_F_95663690_w21ZAFRitPFHL28BYyk6v5JZB0Vu7jQu.jpg'); 
            height: 100%;
            background-position: center;
            background-repeat: no-repeat;
            background-size: cover;
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: center;
            color: white;
            text-align: center;
        }
        .navbar {
            display: flex;
            justify-content: space-between;
            width: 100%;
            background-color: rgba(0, 0, 0, 0.7);
            padding: 10px 0;
            position: fixed;
            top: 0;
            left: 0;
            z-index: 1000;
        }
        .navbar h1 {
            color: #fff;
            margin: 0;
            padding-left: 20px;
            font-size: 24px;
            text-align: left;
        }
        .navbar ul {
            list-style: none;
            display: flex;
            margin: 0;
            padding: 0;
        }
        .navbar li {
            padding: 0 20px;
            position: relative;
        }
        .navbar a {
            color: white;
            text-decoration: none;
            font-size: 16px;
        }
        .navbar .dropdown {
            display: flex;
            align-items: center;
        }
        .navbar .dropdown:after {
            content: '\25BC'; /* Unicode for down arrow */
            margin-left: 5px;
            font-size: 12px;
            color: white;
        }
        .navbar .dropdown-content {
            display: none;
            position: absolute;
            background-color: rgba(0, 0, 0, 0.8);
            min-width: 160px;
            z-index: 1;
            margin-top: 150px;
        }
        .navbar .dropdown-content a {
            padding: 12px 16px;
            display: block;
        }
        .navbar .dropdown:hover .dropdown-content {
            display: block;
        }
        .container {
            position: relative;
            top: 60px;
        }
        .welcome-message {
            font-size: 48px;
            margin-bottom: 60px;
            background: black;
            padding: 10px 15px;
        }
        .btn-container {
            margin-top: 20px;
        }
        .btn {
            background-color: #28a745;
            color: white;
            padding: 15px 30px;
            margin: 10px;
            border: none;
            border-radius: 5px;
            text-decoration: none;
            font-size: 18px;
            cursor: pointer;
            transition: background-color 0.3s;
        }
        .btn:hover {
            background-color: #218838;
        }
    </style>
</head>
<body>
    <div class="bg">
        <div class="navbar">
            <h1>DecodersGym</h1>
            <ul>
                <li><a href="/">Home</a></li>
                <li><a href="/contact">Contact</a></li>
                <li><a href="/feedback">Feedback</a></li>
            </ul>
        </div>
        <div class="container">
            <div class="welcome-message">
                Welcome to DecodersGym
            </div>
            <div class="btn-container">
                <c:if test="${empty sessionScope.user}">
                    <a href="/register" class="btn">Register</a>
                    <a href="/loginpage" class="btn">Login</a>
                </c:if>
                <c:if test="${not empty sessionScope.user}">
                    <p>"The only bad workout is the one that didn't happen."</p>
                </c:if>
            </div>
        </div>
    </div>
</body>
</html>
