/*
 _____  __  __  ____                __                   
/\___ \/\ \/\ \/\  _`\             /\ \                  
\/__/\ \ \ \_\ \ \ \/\_\    ___    \_\ \     __    ____  
   _\ \ \ \  _  \ \ \/_/_  / __`\  /'_` \  /'__`\ /',__\ 
  /\ \_\ \ \ \ \ \ \ \L\ \/\ \L\ \/\ \L\ \/\  __//\__, `\
  \ \____/\ \_\ \_\ \____/\ \____/\ \___,_\ \____\/\____/
   \/___/  \/_/\/_/\/___/  \/___/  \/__,_ /\/____/\/___/ 

     http://www.jhcodes.com/ - SAU 3
               jessus.herrera@hotmail.com
*/


$('.collapse-block').on('click', function(){
     var parentdiv = $(this).parent().parent();
     var icone = $(this).find('i');
     var body = $('.panel-body');
     if (icone.hasClass('fa fa-chevron-up')){
          icone.removeClass('fa fa-chevron-up').addClass('fa fa-chevron-down');
          parentdiv.find(body).fadeOut(200);
     }else{
            icone.removeClass('fa fa-chevron-down').addClass('fa fa-chevron-up');
            parentdiv.find(body).fadeIn(200);
     }
});


$("#loginform").validate({
  rules: {
    saumail:{
      required: true,
      email: true
    },
    saupassword: "required",
  },
  messages: {
    saumail: messageerror1,
    saupassword: messageerror1,
  } 
});

$(".changenowimg1").on("click",function(){
$("#changeprofile1").click()
});

$('#changeprofile1').change( function(event) {
    var tmppath = URL.createObjectURL(event.target.files[0]);
    $("img").fadeIn("fast").attr('src',URL.createObjectURL(event.target.files[0]));

    $("#disp_tmp_path").html("Temporary Path(Copy it and try pasting it in browser address bar) --> <strong>["+tmppath+"]</strong>");
    alert(tmppath+"");
});




$("#register").validate({
  rules: {
    nombre: "required",
    apellido: "required",
    mail:{
      required: true,
      email: true
    },
    password: "required",
  },
  messages: {
    nombre: messageerror1,
    apellido: messageerror1,
    mail: messageerror1,
    password: messageerror1,
  } 
});


$(function (){
    $('#calendar-now').datetimepicker({
        inline: true,
        sideBySide: true,
        format: 'DD/MM/YYYY'
    });
});