//document.getElementById("myBtn").onclick = displayfile;
document.getElementById('files').addEventListener('change', archivo, false);

//pulido
var changeprofile1 = document.getElementById('changeprofile1');
var files = document.getElementById('files');

//pulido
files.onchange = function (e){ 
    var ext = this.value.match(/\.(.+)$/)[1];
    //alert("Valor " + ext);
    switch(ext)
    {
        case 'jpg':
        case 'gif':
        case 'png':
        case 'ico':
            break;
        case '':
        	setTimeout(function() {
                $.bootstrapGrowl("Tipos de imagenes validos (.ico .gif .jpg .png)", {
                    type: 'danger',
                    align: 'center',
                    stackup_spacing: 30
                });
            }, 10);
            this.value='';
            
        default:
        	setTimeout(function() {
                $.bootstrapGrowl("Tipos de imagenes validas (.ico .gif .jpg .png)", {
                    type: 'danger',
                    align: 'center',
                    stackup_spacing: 30
                });
            }, 10);
            this.value='';
    }
};

//pulido2
function checkExt(element) {//use in a form event or ina input
    if( !element.value.match(/\.(jpg)|(gif)|(png)|(bmp)|(ico)$/) ){//here your extensions
    	setTimeout(function() {
            $.bootstrapGrowl("Tipos de imagenes validos (.ico .gif .jpg .png)", {
                type: 'danger',
                align: 'center',
                stackup_spacing: 30
            });
        }, 10);
    	element.value='';
    	document.getElementById("list").value=" ";
    }
}

//pulido
changeprofile1.onchange = function (e){ 
    var ext = this.value.match(/\.(.+)$/)[1];
    switch(ext)
    {
        case 'jpg':
        case 'gif':
        case 'png':
        case 'ico':
            break;
        case '':
        	setTimeout(function() {
                $.bootstrapGrowl("Tipos de imagenes validos (.ico .gif .jpg .png)", {
                    type: 'danger',
                    align: 'center',
                    stackup_spacing: 30
                });
            }, 10);
            this.value='';
        default:
        	setTimeout(function() {
                $.bootstrapGrowl("Tipos de imagenes validos (.ico .gif .jpg .png)", {
                    type: 'danger',
                    align: 'center',
                    stackup_spacing: 30
                });
            }, 10);
            this.value='';
    }
};




function ImageAjax(formData){
	//alert("entro al ajax");
	$.ajax({
        url: "AjaxImage.html",
        type: "post",
        dataType: "html",
        data: formData,
        cache: false,
        contentType: false,
        processData: false
    	});
}


function validarPasswords(pass1,pass2) {
	if(pass1 != pass2){
		alert("password's difrentes");
		return false;
	}else{
		return true;
	}
	
}

function myFunction() {
	if(document.getElementById("password").value != (document.getElementById("password2")).value){
		alert("password's difrentes");
		return false;
	}else{
		return true;
	}
	
}

function displayfile(id_usuario) {
    document.getElementById("changeprofile1").click();
    $('#changeprofile1').change( function(event) {
        var tmppath = URL.createObjectURL(event.target.files[0]);
        document.getElementById("Btn").click();
        
        //obtener div
        var parent = document.querySelector('#chat-box');
        // Cantidad de div
	     var divs = parent.querySelectorAll('div');
	     firtsdiv=(divs[0].id).split('v');
	     if(firtsdiv[1]=="-1"){
	    	 console.log("entro al if no habia nada!");
	     }else{
	    	 //cont=0;
	    	 var image=parent.querySelectorAll('img');
	    	 for (var int = 0; int < image.length-1; int++) {
	    		 	console.log("entro "+int);
					console.log("archivo: "+image[int]);
					//console.log("usuario: "+image[int].id+" id_usuario: "+id_usuario);
					if(image[int].name==id_usuario){
						console.log("este es de el");
						image[int].src=tmppath;
					}
				
			}
	     }
        
        
        
        for (var int = 0; int < 4; int++) {
        	$("#image"+int).fadeIn("fast").attr('src',URL.createObjectURL(event.target.files[0]));	
		}
        var formData = new FormData(document.getElementById("formuploadajax"));
        ImageAjax(formData);
        
        });
    
    }


//Publicacion
function archivo(evt) {
    var files = evt.target.files; // FileList object

    // Obtenemos la imagen del campo "file".
    for (var i = 0, f; f = files[i]; i++) {
      //Solo admitimos imágenes.
      if (!f.type.match('image.*')) {
          continue;
      }

      var reader = new FileReader();

      reader.onload = (function(theFile) {
          return function(e) {
            // Insertamos la imagen
           document.getElementById("list").innerHTML = ['<img id="imagenPublicacion" name="imagenPublicacion" class="img-responsive center-block" src="', e.target.result,'" title="', escape(theFile.name), '"/>'].join('');
          };
      })(f);

      reader.readAsDataURL(f);
    }
}


$('.modal').on('hidden.bs.modal', function () {
    $(this).find('form').trigger('reset');
    document.getElementById("list").value=" ";
    document.getElementById("files").value=" ";
    //var now = new Date().toJSON().slice(0,10);
    /*$("#myModal").find(".modal-title").text("Publicacion del Clima " + now.getDate()+"/"+(now.getMonth()+1)+"/"+now.getFullYear()
    		+ " " + now.getHours()+":"+ now.getMinutes());*/
});


$("#files").fileinput({
	showCaption: false,
	browseClass: "btn btn-primary btn-lg",
	fileType: "any"
	});








	
	
	





	

