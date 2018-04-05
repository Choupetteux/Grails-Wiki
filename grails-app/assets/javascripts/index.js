$(document).ready(function() {

    $("#btn-ctg").click(function(){
        if($("#form-categorie").is(":visible")) {
            $("#form-categorie").removeClass("fadeIn").addClass("fadeOut");
            setTimeout(function(){
                $("#form-categorie").toggle();
            }, 1000);
        }
        else{
            $("#form-categorie").removeClass("fadeOut").addClass("fadeIn").toggle();
        }
    });

    $("#btn-login").click(function(){
        if($("#loginForm").is(":visible")) {
            $("#loginForm").removeClass("fadeInUp").addClass("fadeOutDown");
            setTimeout(function(){
                $("#loginForm").toggle();
            }, 1000);
        }
        else{
            $("#loginForm").removeClass("fadeOutDown").addClass("fadeInUp").toggle();
        }
    });


})