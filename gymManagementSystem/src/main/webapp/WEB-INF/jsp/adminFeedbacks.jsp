<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Customer Feedback</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-image: url('images/forregister.jpg');
            background-size: cover;
            background-position: center;
            background-attachment: fixed;
            background-color: #f0f0f0;
            margin: 0;
            padding: 20px;
            color: #333;
        }

        h2 {
            color: #333;
            text-align: center;
            margin-bottom: 20px;
        }

        table {
            width: 80%;
            margin: 0 auto;
            border-collapse: collapse;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            background-color: #fff;
            border-radius: 8px;
            overflow: hidden;
        }

        th, td {
            padding: 12px;
            text-align: left;
            border: 1px solid #ddd;
        }

        th {
            background-color: #f2f2f2;
            color: #333;
        }

        tr:nth-child(even) {
            background-color: #f9f9f9;
        }

        tr:hover {
            background-color: #f1f1f1;
        }

        .return-button {
            text-align: center;
            margin-top: 20px;
        }

        .return-button button {
            background-color: #007bff;
            color: #fff;
            border: none;
            padding: 10px 20px;
            border-radius: 4px;
            cursor: pointer;
            font-size: 16px;
        }

        .return-button button:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
    <h2>Customer Feedbacks</h2>
    <table>
        <tr>
            <th>Id</th>
            <th>Name</th>
            <th>Email</th>
            <th>Message</th>
        </tr>
        <c:forEach var="feedback" items="${feedbackList}">
            <tr>
                <td>${feedback.id}</td>
                <td>${feedback.name}</td>
                <td>${feedback.email}</td>
                <td>${feedback.message}</td>
            </tr>
        </c:forEach>
    </table>
    <div class="return-button">
        <button onclick="window.location.href='/index';">Return</button>
    </div>
</body>
</html>
