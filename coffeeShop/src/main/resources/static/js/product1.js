$(function(){
    $('#mybtn').click(
        function(){
            var prodType = $("#selectProductType").val();
            $.ajax({
                url : "http://localhost:9898/api/productType/"+ prodType,
                type : "GET",
                success : process,
                fail : failure
            });
        }
    );

});

alert("hey");

	function process(data){
		alert("hey");
        //console.log(data);
        //ajax call again for post
		var prodType = $("#selectProductType").val();
        $("<div>")
            .html("Id : " + data.id + "<br> Name :" + data.productnName
            + "<br> Description: " + data.description + "<br> price: " + data.price)
            .appendTo("#dataDiv");
}

function failure(){
    console.log("Error loading page");
}


//$(document).ready(function() {
//	var producttype= = $('#selectProductType').val();
//    $.ajax({
//        url: "http://localhost:9898/api/productsByType/producttype"
//    }).then(function(data) {
//       $('.product-id').append(data.id);
//       $('.product-name').append(data.productName);
//       $('.product-description').append(data.description);
//       $('.product-name').append(data.productName);
//       $('.product-price').append(data.price);
//       $('.product-type').append(data.prodcutType);
//    });
//   
//});
