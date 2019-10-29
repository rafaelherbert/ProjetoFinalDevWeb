$(document).ready(function(){
   
   
   var plus = $(this).find('a[data-class="plus"]');
   
   plus.click(function(e){
       e.preventDefault();
       var quantity_span = $(this).next();
       var quantity = quantity_span.text();
       var price_span = $(this).parent().next().children();
       var price = quantity_span.data("price");
       quantity_span.text(++quantity);
       price_span.text(quantity_span.text() * price);
       calculate_total_cart_price();
   });
   
   var minus = $(this).find('a[data-class="minus"]');
   
   minus.click(function(e){
       e.preventDefault();
       var quantity_span = $(this).prev();
       var quantity = quantity_span.text();
       var price_span = $(this).parent().next().children();
       var price = quantity_span.data("price");
       if (quantity > 1) {
           quantity_span.text(--quantity);
           price_span.text(quantity_span.text() * price);
       }
       calculate_total_cart_price();
   });
    
   $('div[data-class="product"]').each(function() {
      var quantity_span = $(this).find('span[data-class="product_quantity"]');
      var quantity = quantity_span.text();
      var price = quantity_span.data("price");
      var price_span = $(this).find('span[data-class="product_total_price"]');
      price_span.text(price * quantity);
   });
   
   // First price calculation
   calculate_total_cart_price();
   
});

function calculate_total_cart_price() {
    var cart_total_price = $("#cart_total_price");
    var total_price = 0;
    $('span[data-class="product_total_price"]').each(function() {
       total_price += parseFloat($(this).text()); 
    });
    cart_total_price.text(total_price);
}