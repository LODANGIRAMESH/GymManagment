<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Slot Record</title>

<style>
.container {
	/* background-color: rgba(0, 0, 0, 0.7); */
	background: linear-gradient(to left, #434343, #000000);
	padding: 20px;
	border-radius: 10px;
	width: 70%;
	margin: auto;
	margin-top: 10px;
	position: relative;
	top: 60px;
	border: solid 1px #313131;
}
    a.enquiry-link {
        color: red;
        text-decoration: none;
    }

    a.enquiry-link:hover {
        color: blue;
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

table {
	width: 70%;
	border-collapse: collapse;
	margin-top: 20px;
	color: white;
}

table, th, td {
	border: 1px solid white;
}

th, td {
	padding: 10px;
	text-align: left;
}

th {
	background-color: #333;
}

 .form-title {
        	color: white;
        	font-size: 28px;
   }
</style>
<script>
function goBack() {
    window.location.href = "/index";
}

function setActive(element) {
    // Assuming setActive is defined elsewhere in your code to handle the active state
}
</script>

</head>
<body>
  <!-- Available Slots  -->
        <div class="container">
            <h2 class="form-title">Available Slots</h2>
            <div style="display: flex; align-items: center; justify-content: center;">
                <table>
    <thead>
        <tr>
            <th>Slot Number</th>
            <th>Slot Time</th>
            <th>Slot Price</th>
            <th>Enquiry</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach items="${slotList}" var="slot">
            <tr>
                <td>${slot.slotId}</td>
                <td>${slot.slotTime}</td>
                <td>${slot.pricing}</td>
<td><a href="<c:url value='/slot-show/${slot.slotId}'/>" class="enquiry-link">Slot_Enquiry&_Book</a></td>
            </tr>
        </c:forEach>
    </tbody>
</table>
     </div>
     <div class="form-group">
            <button type="button" onclick="goBack(); setActive(this);" class="tab">Back</button>
        </div>
        </div>
        </body>
</html>