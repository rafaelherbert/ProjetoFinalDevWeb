/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

console.log("Main.js working.");

$(document).ready(function(){
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
        console.log("daskodasko");
        var product_id = $(this).data("product_id");
        
        e.preventDefault();
        
        var ajax = new XMLHttpRequest();
        ajax.open("GET", "add_to_cart.jsp?product_id=" + product_id + "&action=add", true);
        ajax.send();
        ajax.onreadystatechange = function() {
            if (ajax.readyState == 4 && ajax.status == 200) {
                var data = ajax.responseText;
                console.log(data);
            }
        }
    });
});

  