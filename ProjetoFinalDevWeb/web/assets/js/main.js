/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

console.log("Main.js working.");

$(document).ready(function(){
    var login = findGetParameter("login");
    if (login) $("#login_modal").modal("show");
    
    // Ajeitar o javascript dps!
    var access_action = $("#accessAction");
    var form_submit = $("#formSubmit");
    var login_question = $("#formLoginQuestion");
    var register_question = $("#formRegisterQuestion");
    var name_form_group = $("#name-form-group");
    
    $("#registerLink").click(function(){
        login_question.css({'display':'none'});
        register_question.css({'display':'block'});
        name_form_group.css({'display':'block'});
        access_action.val("register");
        form_submit.text("Register");
    });

    $("#loginLink").click(function() {
        register_question.css({'display':'none'});
        login_question.css({'display':'block'});
        name_form_group.css({'display':'none'});
        access_action.val("login");
        form_submit.text("Login");
    });
    
    $("#add_to_cart").click(function(e) {
        var product_id = $(this).data("product_id");
        var temp_quantity = $("#temp_quantity").val();
        e.preventDefault();
        
        var ajax = new XMLHttpRequest();
        ajax.open("GET", "add_to_cart.jsp?product_id=" + product_id + "&temp_quantity=" + temp_quantity +"&action=add", true);
        ajax.send();
        ajax.onreadystatechange = function() {
            if (ajax.readyState == 4 && ajax.status == 200) {
                var data = ajax.responseText;
                var response = JSON.parse(data);
                if (response.response == false) {
                    window.location = window.location.href + "&login=true&alert=Realize o login para continuar.";
                } else if (response.response == true) {
                    $("#cart_modal").modal("show");
                } else {
                    window.location = window.location.href + "&notify_mode=success&notify_message=Item já adicionado ao carrinho.";
                }
            }
        }
    });
    
    $("#stars_group > [data-star_value!=\"\"]").each(function(){
        $(this).click(function(){
            var current_value = $(this).data("star_value");
            $("#stars_group > [data-star_value!=\"\"]").each(function(){
                if ($(this).data("star_value") > current_value) $(this).removeClass("checked");
                else $(this).addClass("checked");
            });
            $("#rating_input").val(current_value);
            $("#rating_value").text(current_value);
        });
        if ($(this).data("star_value") == $("#rating_value").text())
            $(this).click();
    });
   
    var notify_mode = findGetParameter("notify_mode");
    var notify_message = findGetParameter("notify_message");
    
    if (notify_mode && notify_message) {
        console.log("dsokdasoks");
        $("#notifier > #message").text(notify_message);
        $("#notifier").addClass("alert-" + notify_mode);
        $("#notifier").show();
        setTimeout(function(){
            $("#notifier").hide("slow");
        }, 3000);
    }
});


function findGetParameter(parameterName) {
    var result = null,
        tmp = [];
    location.search
        .substr(1)
        .split("&")
        .forEach(function (item) {
          tmp = item.split("=");
          if (tmp[0] === parameterName) result = decodeURIComponent(tmp[1]);
        });
    return result;
}

  