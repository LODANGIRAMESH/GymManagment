<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Book Slot</title>
    <style>
        .container {
            background: linear-gradient(to left, #434343, #000000);
            padding: 20px;
            border-radius: 10px;
            width: 70%;
            margin: auto;
            margin-top: 10px;
            position: relative;
            top: 60px;
            border: solid 1px #313131;
            color: white;
        }

        .form-group {
            margin: 20px 0;
        }

        .form-group input, .form-group select, .form-group button {
            width: 40%;
            padding: 10px;
            font-size: 16px;
            border-radius: 5px;
            border: none;
            margin-top: 10px;
        }

        .form-group button {
            background-color: #28a745;
            color: white;
            cursor: pointer;
            transition: background-color 0.3s;
            width: fit-content;
        }

        .form-group button:hover {
            background-color: #218838;
        }

        .form-title {
            color: white;
            font-size: 28px;
        }

        .gym-item-container {
            display: flex;
            justify-content: center;
            gap: 10px;
            margin-top: 10px;
        }

        .gym-item {
            background: #2f2f2f;
            color: white;
            padding: 4px 16px;
            border-radius: 5px;
            font-size: large;
            cursor: pointer;
            border: solid 1px #2f2f2f;
        }

        .gym-item:hover {
            border: solid 1px #28a745;
        }

        table {
            width: 100%;
            border-collapse: collapse;
        }

        table, th, td {
            border: 1px solid white;
            color: white;
        }

        th, td {
            padding: 15px;
            text-align: left;
        }

        th {
            background-color: #333333;
        }
    </style>
    <script>
        function goBack() {
            window.location.href = "/slots";
        }
    </script>
</head>
<body>
<div class="container">
    <h2 class="form-title">Book Your Gym Slot</h2>
    <h3>Slot Details</h3>
    <h4>Slot Id: ${slot.slotId}</h4>
    <h4>Slot Timing: ${slot.slotTime}</h4>
    <h4>Slot Price: ${slot.pricing}</h4>

    <form action="/slot-book" method="post">
        <input type="hidden" value="${slot.slotId}" name="slotId"/>
        <input type="hidden" value="0" name="userId"/>
              <table>
            <thead>
                <tr>
                    <th>Item No</th>
                    <th>Item Name</th>
                    <th>Total Seat</th>
                    <th>Available Seat</th>
                    <th>Select</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${itemList}" var="item">
                    <tr>
                        <td>${item.itemId}</td>
                        <td>${item.itemName}</td>
                        <td>${item.totalSeat}</td>
                        <td>${item.seatVacant}</td>
                        <td><input type="radio" name="itemId" value="${item.itemId}" /></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <br/>
        <div class="form-group">
            <button type="button" onclick="goBack();" class="tab">Back</button>
        </div>
        <div class="form-group">
            <button type="submit">Book Slot</button>
        </div>
    </form>
</div>
</body>
</html>
