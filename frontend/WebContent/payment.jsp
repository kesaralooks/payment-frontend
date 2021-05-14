<%@page import="com.payment"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Payment Management</title>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.2.1.min.js"></script>
<script src="Components/payment.js"></script>
</head>
<body> 
<div class="container"><div class="row"><div class="col-6"> 
<h1>Payment Management V10.1</h1>
<form id="formPayment" name="formPayment">
 Payment code: 
 <input id="paymentCode" name="paymentCode" type="text" 
 class="form-control form-control-sm">
 <br> Payment name: 
 <input id="paymentName" name="paymentName" type="text" 
 class="form-control form-control-sm">
 <br> Payment price: 
 <input id="paymentPrice" name="paymentPrice" type="text" 
 class="form-control form-control-sm">
 <br> Payment description: 
 <input id="paymentDesc" name="paymentDesc" type="text" 
 class="form-control form-control-sm">
 <br>
 <input id="btnSave" name="btnSave" type="button" value="Save" 
 class="btn btn-primary">
 <input type="hidden" id="hidPaymentIDSave" 
 name="hidPaymentIDSave" value="">
</form>
<div id="alertSuccess" class="alert alert-success"></div>
<div id="alertError" class="alert alert-danger"></div>
<br>
<div id="divPaymentGrid">
 <%
 	payment paymentObj = new payment(); 
  out.print(paymentObj.readPayment());
 %>
</div>
</div> </div> </div> 
</body>
</html>