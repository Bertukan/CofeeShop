$(document).ready(function(){
    
    $.ajax({
        method: "GET",
        url: "http://localhost:9898/api/products"
      }).done(function( data  ) {
            var products = JSON.parse(JSON.stringify(data));
            products.forEach(element => {
                var el = `<div class="card" class="prodElement">
                <img  class="card-img-top" src="injera.jpg" style="width:180px;"/>
                <div class="card-body"><h5 >${element.productName}</h5>
                    <p class="card-text" >${element.description}</p><button type="button" class="btn btn-default btn-sm">
                    <span class="glyphicon glyphicon-shopping-cart"></span> Add to Cart
                    
                </div>`
                $('#prd').append(el);
            });
        });
});