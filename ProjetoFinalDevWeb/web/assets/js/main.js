/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

console.log("Main.js working.");

$(document).ready(function(){
    // Ajeitar o javascript dps!
    var login_form = $("#loginForm");
    var access_action = $("#accessAction");
    var form_question = $("#formQuestion");
    var form_submit = $("#formSubmit");
    var login_question = $("#formLoginQuestion");
    var register_question = $("#formRegisterQuestion");
    
    $("#registerLink").click(function(){
        login_question.css({'display':'none'});
        register_question.css({'display':'display'});

        access_action.val("register");

        if (!$("#nameGroup").length) {
            login_form.prepend(`
            <div class="form-group" id="nameGroup">
                <label for="inputName">Name</label>
                <input type="text" name="name" class="form-control" id="inputName" placeholder="Enter name">
            </div>
            `);
        }

        form_question.empty();
        form_question.append(register_string);
        form_submit.text("Register");
    });

    $("#loginLink").click(function() {
        register_question.css({'display':'none'});
        login_question.css({'display':'display'});
        access_action.val("login");
        $("#nameGroup").remove();
        form_submit.text("Login");
    });
});

  