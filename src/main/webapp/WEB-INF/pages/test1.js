$(document).ready(function() {
    $.ajax({
        url: "http://localhost:8080/server/getVendors"
    }).then(function(data, status, jqxhr) {
		alert(data[0].vendorId);
    });
});