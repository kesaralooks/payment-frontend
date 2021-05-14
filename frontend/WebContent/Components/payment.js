$(document).ready(function()
{ 
if ($("#alertSuccess").text().trim() == "") 
 { 
 $("#alertSuccess").hide(); 
 } 
 $("#alertError").hide(); 
}); 
// SAVE ============================================
$(document).on("click", "#btnSave", function(event) 
{ 
// Clear alerts---------------------
 $("#alertSuccess").text(""); 
 $("#alertSuccess").hide(); 
 $("#alertError").text(""); 
 $("#alertError").hide(); 
// Form validation-------------------
var status = validatePaymentForm(); 
if (status != true) 
 { 
 $("#alertError").text(status); 
 $("#alertError").show(); 
 return; 
 } 
// If valid------------------------
var type = ($("#hidPaymentIDSave").val() == "") ? "POST" : "PUT"; 
$.ajax( 
{ 
		url : "PaymentAPI", 
		type : type, 
		data : $("#formPayment").serialize(), 
		dataType : "text", 
		complete : function(response, status) 
		{ 
			onPaymentSaveComplete(response.responseText, status); 
		} 
	}); 
});

function onPaymentSaveComplete(response, status)
{ 
if (status == "success") 
 { 
	 var resultSet = JSON.parse(response); 
	 if (resultSet.status.trim() == "success") 
	 { 
		 $("#alertSuccess").text("Successfully saved."); 
		 $("#alertSuccess").show();
		 
		 $("#divPaymentGrid").html(resultSet.data); 
	 } else if (resultSet.status.trim() == "error") 
	 { 
		 $("#alertError").text(resultSet.data); 
		 $("#alertError").show(); 
	 } 
 } else if (status == "error") 
 { 
	 $("#alertError").text("Error while saving."); 
	 $("#alertError").show(); 
 } else
 { 
	 $("#alertError").text("Unknown error while saving.."); 
	 $("#alertError").show(); 
 }

	$("#hidPaymentIDSave").val(""); 
	$("#formPayment")[0].reset(); 
}


// UPDATE==========================================
$(document).on("click", ".btnUpdate", function(event) 
{ 
 $("#hidPaymentIDSave").val($(this).closest("tr").find('#hidPaymentIDUpdate').val()); 
 $("#amount").val($(this).closest("tr").find('td:eq(0)').text()); 
 $("#date").val($(this).closest("tr").find('td:eq(1)').text()); 
 $("#paymentType").val($(this).closest("tr").find('td:eq(2)').text()); 
 $("#accountNumber").val($(this).closest("tr").find('td:eq(3)').text()); 
}); 

//REMOVE=======================================================

$(document).on("click", ".btnRemove", function(event)
{ 
	$.ajax( 
	{ 
		 url : "PaymentAPI", 
		 type : "DELETE", 
		 data : "paymentID=" + $(this).data("paymentid"),
		 dataType : "text", 
		 complete : function(response, status) 
		 { 
			 onPaymentDeleteComplete(response.responseText, status); 
		 } 
	}); 
});

function onPaymentDeleteComplete(response, status)
{ 
	if (status == "success") 
	{ 
		var resultSet = JSON.parse(response); 
		if (resultSet.status.trim() == "success") 
		{ 
		 $("#alertSuccess").text("Successfully deleted."); 
		 $("#alertSuccess").show(); 
		 
		 $("#divPaymentGrid").html(resultSet.data); 
		} else if (resultSet.status.trim() == "error") 
		{ 
			 $("#alertError").text(resultSet.data); 
			 $("#alertError").show(); 
		} 
		
 } else if (status == "error") 
 { 
	 $("#alertError").text("Error while deleting."); 
	 $("#alertError").show(); 
 } else
 { 
	 $("#alertError").text("Unknown error while deleting.."); 
	 $("#alertError").show(); 
 } 

}

// CLIENT-MODEL================================================================
function validatePaymentForm() 
{ 
// CODE
if ($("#paymentID").val().trim() == "") 
 { 
 return "Insert Payment ID."; 
 } 
// NAME
if ($("#amount").val().trim() == "") 
 { 
 return "Insert amount."; 
 }

//PRICE-------------------------------
if ($("#date").val().trim() == "") 
 { 
 return "Insert date."; 
 } 
// is numerical value
var tmpPrice = $("#date").val().trim(); 
if (!$.isNumeric(tmpdate)) 
 { 
 return "Insert a numerical value for date."; 
 } 
// convert to decimal date
 $("#date").val(parseFloat(tmpdate).toFixed(2)); 
// DESCRIPTION------------------------
if ($("#paymentType").val().trim() == "") 
 { 
 return "Insert Payment Type."; 
 } 
return true; 
}