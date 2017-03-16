//document.getElementById("BtnPub").onclick = ValPublication;
//document.getElementById("BtnModal").onclick = Geolocation;
//cambios
//document.getElementById("BtnComment").onclick = regComment;

function Geolocation(){
	navigator.geolocation.getCurrentPosition(showPosition);
	function showPosition(position){
		var geocoder = new google.maps.Geocoder();
		lat=position.coords.latitude;
		lon=position.coords.longitude;
		//Salomia
		//lat = 3.4686148955336327;
		//lon = -76.50039911270142;
		
		//nueva salomia
		//lat = 3.4686148955336327;
		//lon = -76.50039911270142;
		
		//caney 
		//lat=3.383752;
		//lon=-76.520274;
		
		//versalles
		//lat=3.4614557;
		//lon=-76.52931389999998;
		
		//Guayaquil
		//lat=3.439808;
		//lon=-76.527334;
		//3.439808, -76.527334
		
		//san fernando
		//lat=3.431957;
		//lon=-76.543497;
		//3.439808, -76.527334
		
		//san vicente / Uniajc
		lat=3.465830504168119;
		lon=-76.52755379676819;
		
		
		
		
		
		latlonmarker= new google.maps.LatLng(lat, lon);
		geocoder.geocode({ 'latLng': latlonmarker },geocoderact);
		
		}
 }
function geocoderact(results, status){
	if (status == google.maps.GeocoderStatus.OK) {
		if (results[0]) {
			
		//var codigoPostal= results[0].address_components[7].long_name;
		//var Direccion=results[0].address_components[1].long_name;
		var BarrioSplt=(results[1].formatted_address).split(',');
		Barrio1=BarrioSplt[0];
		Barrio2=results[0].address_components[2].long_name;
		
		} else {
		error('Google no retorno resultado alguno.');
		}
		} else {
		error("Geocoding fallo debido a : " + status);
		}
	setTimeout( PrintBarrio, 0 );
}

function PrintBarrio(){
	
	$.ajax({
        url: "Public.html",
        type: "GET",
        dataType: "html",
        data:"Barrio1="+tildes_unicode(Barrio1)+"&Barrio2="+tildes_unicode(Barrio2),
        cache: false,
        contentType: false,
        processData: false,
			success : function(response) {
				console.log("SUCCESS: ", response);
				display(response);
			},
			error : function(e) {
				console.log("ERROR: ", e);
				display(e);
			},
			done : function(e) {
				console.log("DONE");
			}
		});
}


function enableSearchButton(flag) {
	$("#btn-search").prop("disabled", flag);
}

function display(data) {
	var datos=data.split(',');
	if(datos[1]!=""){
		document.getElementById("Id_").value=datos[1];
		document.getElementById("BtnPub").disabled=false;
	}else{
		document.getElementById("BtnPub").disabled=true;
	}
	$('#Barrio').html(datos[0]);
} 

//pulido
function ValPublication() {
        
        var formData = new FormData(document.getElementById("formPublication"));
        if(formData.get('weather')){
	         //obtener div
	         var parent = document.querySelector('#chat-box');
	         // Cantidad de div
		     var divs = parent.querySelectorAll('div');
		     firtsdiv=(divs[0].id).split('v');
		     if(firtsdiv[1]=="-1"){
		    	 
		    	 document.getElementById(divs[0].id).remove();
		     }
		     formData.append("Id_Pb",firtsdiv[1]);
		     $.ajax({
	            url: "registrarPublicacion.html",
	            type: "POST",
	            dataType: "html",
	            data:formData,
	            cache: false,
	            contentType: false,
	            processData: false,
	    			success : function(response) {
	    				console.log("SUCCESS: ", response);
	    				ShowPublication(response);
	    				ConsultaEstadiscticas(document.getElementById("comuna").value);
	    				$('#myModal').modal('hide');
	    				//pulido3
	    				document.getElementById("comuna").selectedIndex = 0;
	    				document.getElementById("comuna").onchange();
	    				//llamar el ShowPublication
	    			},
	    			error : function(e) {
	    				console.log("ERROR: ", e);
	    				display(e);
	    			},
	    			done : function(e) {
	    				console.log("DONE");
	    			}
	    		});
	     
        }else{
        	/*$(function() {
        	    $.bootstrapGrowl("This is a test.");
        	});*/
        	
        	setTimeout(function() {
                $.bootstrapGrowl("Seleccione un tipo de Clima!", {
                    type: 'danger',
                    align: 'center',
                    stackup_spacing: 30
                });
            }, 10);
        

        }
    
    }

//pulido
function regComment() {
    
	var formData = new FormData();
    var descriptionComentary = document.getElementById("textarearDescription").value;
    
    if(descriptionComentary.trim() != ""){
	     //obtener div
	     var parent = document.querySelector('#myModalLabelPublication');
	     // Cantidad de div
	     var divs = parent.querySelectorAll('div');
	     firtsdiv=(divs[0].id).split('v');
	     
	     //obtener div
	     var parent2 = document.querySelector('#myModalLabelComments');
	     // Cantidad de div
	     var divs2 = parent2.querySelectorAll('div');
	     firtsdiv2=(divs2[0].id).split('v');
	     if(firtsdiv2[1]=="-1"){
	    	 document.getElementById(divs2[0].id).remove();
	     }
	     
	     formData.append("descriptionComentary",descriptionComentary);
	     formData.append("id_publication",firtsdiv[1]);
	     formData.append("UltDivCommentary",firtsdiv2[1]);
	     
	     $.ajax({
	        url: "registrarCommentary.html",
	        type: "POST",
	        dataType: "html",
	        data:formData,
	        cache: false,
	        contentType: false,
	        processData: false,
				success : function(response) {
					console.log("SUCCESS: ", response);
					paintComments(JSON.parse(response));
					//pulido3
					document.getElementById("textarearDescription").value = "";
					//ShowPublication(response); dividir esto en 2 metodos uno para comentarios y otro para publi q llame a ....
					//llamar el ShowPublication
				},
				error : function(e) {
					console.log("ERROR: ", e);
					display(e);
				},
				done : function(e) {
					console.log("DONE");
				}
			});
    }else{
    	setTimeout(function() {
            $.bootstrapGrowl("Comenta con algo!", {
                type: 'info',
                align: 'center',
                stackup_spacing: 30
            });
        }, 10);
    }

}

function ShowPublication(data) {
	dato=JSON.parse(data);
	cont = 0;
	
	while (cont < dato.length){
		//console.log(dato[cont].Descripcion);
		
		//var ImgBin=dato[cont].photo;
		//var base64String = btoa(String.fromCharCode.apply(null, new Uint8Array(ImgBin)));
		//var otro= btoa([].reduce.call(new Uint8Array(ImgBin),function(p,c){return p+String.fromCharCode(c)},''));
		
		
		 var newTextBoxDiv = $(document.createElement('div'))
		    .attr("id", 'TextBoxDiv'+dato[cont].id_publication).attr("class", 'item');
		 

		 /*var att = document.createAttribute("class");
		    att.value = "item";
		    newTextBoxDiv.setAttributeNode(att);*/
		 	var Btn="";
		 	if(dato[cont].author=="true"){
		 		Btn='<div class="attachment">'+
		 		'<button id="'+dato[cont].id_publication+'" class="btn btn-default" onclick="AlertDrop(this.id)">'+
				'<span class="glyphicon glyphicon-trash"></span>'+
				'</button>'+
		 		'<button id="'+dato[cont].id_publication+'" class="btn btn-default" data-toggle="modal" onclick="DisplayComments(this.id)">'+
				'<span class="glyphicon glyphicon-pencil"></span>'+
				'</button>'+
				'<small> <b> <i>'+dato[cont].barrio+' (comuna '+ dato[cont].comuna+ ')'+'</i></b></small>'+
				'</div>';
		 		//pulido
		 	}else{
		 		if(dato[cont].author=="false"){
		 			Btn='<div class="attachment">'+
		        	'<button id="'+dato[cont].id_publication+'" class="btn btn-default" onclick="Like(this.id)" >'+
					'<span class="glyphicon glyphicon-heart-empty"></span>'+
					'</button>'+
			 		'<button id="'+dato[cont].id_publication+'" class="btn btn-default" data-toggle="modal" onclick="DisplayComments(this.id)">'+
					'<span class="glyphicon glyphicon-pencil"></span>'+
					'</button>'+
					'<small> <b> <i>'+dato[cont].barrio+' (comuna '+ dato[cont].comuna+ ')'+'</i></b></small>'+
					'</div>';	
		 			//pulido
		 		}else{
		 			Btn='<div class="attachment">'+
		        	'<button id="'+dato[cont].id_publication+'" class="btn btn-default" onclick="Like(this.id)" >'+
					'<span class="glyphicon glyphicon-heart"></span>'+
					'</button>'+
			 		'<button id="'+dato[cont].id_publication+'" class="btn btn-default" data-toggle="modal" onclick="DisplayComments(this.id)">'+
					'<span class="glyphicon glyphicon-pencil"></span>'+
					'</button>'+
					'<small> <b> <i>'+dato[cont].barrio+' (comuna '+ dato[cont].comuna+ ')'+'</i></b></small>'+
					'</div>';
		 			//pulido

		 			
		 		}
		 		
		 		
		 	}
		 	
		 	//pulido
		 	var imagen = "";
		 	if(dato[cont].photo){
		 		imagen ='</p> <div class="attachment">'+ 
		 			'<img src="data:image/png;base64,'+dato[cont].photo+'" class="img-responsive">'+
		 			'</div>';
		 	}
		 	var descripcion = "";
		 	if(dato[cont].Descripcion){
		 		descripcion = dato[cont].Descripcion; 
		 	}
		 	
		  onclick="consultaPerfil(this.name,this.id)"
			   newTextBoxDiv.before().html('<img src="data:image/png;base64,'+ dato[cont].profile + '"  alt="user image" class="online" style="cursor: pointer" id="popoveUser'+dato[cont].id_publication+'" name="'+ dato[cont].authorperfil + '" onclick="consultaPerfil(this.name,this.id)" >'+
					    '<p class="message">'+
						'<a href="#" class="name">'+
						'<small class="text-muted pull-right"><i class="fa fa-clock-o"></i>'+dato[cont].date.split(' ')[3]+'</small>'+ 
						'<span  id="'+dato[cont].authorperfil+'">'+
					     dato[cont].nombre_author+
					     '</span>'+
						'</a>'+ 
						'<img src="././resources/img/perfil/'+ dato[cont].weather+ '-weather.png" class="img-responsive"/>'+
			 			//Pulido
						descripcion
						+imagen
						+Btn);

		   $("#chat-box").prepend(newTextBoxDiv);
		   cont = cont + 1;
	}
		
	}

//cambios
function ShowComments(data,id_publicacion) {
	
	if(data=="false"){
		$("#myModalLabelPublication").empty();
		$("#myModalLabelComments").empty();
		sweetAlert('Oops...', 'Publicacion Fue Eliminada!', 'error');
		document.getElementById("TextBoxDiv"+id_publicacion).remove();
	}else{	
		dato=JSON.parse(data);
		
		document.querySelector('#myModalLabelTitle').querySelectorAll('h3')[0].innerHTML = 'Publicacion de ' +  
			dato[0].barrio + ' - ' + dato[0].pto_cardinal + '(Comuna ' + dato[0].comuna +')';
		
		var descripcion="";
		if(dato[0].Descripcion){
			descripcion = dato[0].Descripcion;
		}
		
		var divPublicacion = $(document.createElement('div')).attr("id", "PubBoxDiv"+dato[0].id_publication).attr("class", 'box-body chat');
	 	//pulido
		divPublicacion.before().html('<div class="item"> <img src="data:image/png;base64,'+ dato[0].profile + '" alt="user image" class="online">'+
				    '<p class="message">'+
					'<a href="#" class="name">'+
					'<small class="text-muted pull-right"><i class="fa fa-clock-o"></i>'+dato[0].date.split(' ')[3]+'</small>'+ dato[0].nombre_author +
					'</a>'+
					'<img src="././resources/img/perfil/'+ dato[0].weather+ '-weather.png" class="img-responsive"/>'+
		 			//Pulido
					descripcion + "</div>");

	
		$("#myModalLabelPublication").empty();
		$("#myModalLabelComments").empty();
		$("#myModalLabelPublication").prepend(divPublicacion);
		
		/*
		var divComment;
		cont = 0;
		$("#myModalLabelComments").empty();
		while (cont <= (dato[0].comments.length-1)){
			divComment = $(document.createElement('div')).attr("id", "TextBoxDiv"+dato[0].comments[cont].id_commentary).attr("class", 'box-body chat');
			
			divComment.before().html('<div class="item"> <img src="data:image/png;base64,'+ dato[0].comments[cont].profile + '" alt="user image" class="online">'+
				    '<p class="message">'+
					'<a href="#" class="name">'+
					'<small class="text-muted pull-right"><i class="fa fa-clock-o"></i>'+dato[0].comments[cont].date + 	'</small>'+ dato[0].comments[cont].nombre_author +
					'</a>'+
					dato[0].comments[cont].description + 
					"</div>");
			cont = cont + 1;
			$("#myModalLabelComments").prepend(divComment);
		}*/
		paintComments(dato);
		$("#modalComment").modal();
	}
}


//cambios
function paintComments(dato) {
	var divComment;
	cont = 0;
	if ((dato[0].comments.length)==0){
		divComment = $(document.createElement('div')).attr("id", "CommBoxDiv-1");
		$("#myModalLabelComments").prepend(divComment);
	}else{
		while (cont <= (dato[0].comments.length-1)){
			divComment = $(document.createElement('div')).attr("id", "CommBoxDiv"+dato[0].comments[cont].id_commentary).attr("class", 'box-body chat');
		 	//pulido
			divComment.before().html('<div class="item"> <img src="data:image/png;base64,'+ dato[0].comments[cont].profile + '" alt="user image" class="online">'+
				    '<p class="message">'+
					'<a href="#" class="name">'+
					'<small class="text-muted pull-right"><i class="fa fa-clock-o"></i>'+dato[0].comments[cont].date.split(' ')[3] + 	'</small>'+ dato[0].comments[cont].nombre_author +
					'</a>'+
					dato[0].comments[cont].description + 
					"</div>");
			cont = cont + 1;
			$("#myModalLabelComments").prepend(divComment);
		}
	}
}


function AlertDrop(clicked_id) {
	div="TextBoxDiv"+clicked_id;
	//obtener div
    var parent = document.querySelector('#chat-box');
    // Cantidad de div
    var divs = parent.querySelectorAll('div');
    firtsdiv=(divs[0].id).split('v');    
    
    $('#'+clicked_id).on('click', function () {
        var jc= $.confirm({
             title: 'Deseas Eliminar?',
             autoClose: 'cancelAction|10000',
             escapeKey: 'cancelAction',
             buttons: {
                 confirm: {
                     btnClass: 'btn-danger',
                     text: 'Eliminar',
                     action: function () {
                        
                         $.ajax({
         		            url: "DeletePublicacion.html",
         		            type: "GET",
         		            dataType: "html",
         		            data:"id_Pbl="+clicked_id+"&UltDiv="+firtsdiv[1],
         		            cache: false,
         		            contentType: false,
         		            processData: false,
         		    			success : function(response) {
         		    				console.log("SUCCESS: ", response);
         		    				document.getElementById(div).remove();
         		    				BarraSnPb(response);
         		    				ConsultaEstadiscticas(document.getElementById("comuna").value);
         		    			},
         		    			error : function(e) {
         		    				console.log("ERROR: ", e);
         		    				display(e);
         		    			},
         		    			done : function(e) {
         		    				console.log("DONE");
         		    			}
         		    		});
                     }
                 },
                 cancelAction: {
                     text: 'Cancelar',
                     action: function () {
                     }
                 }
             }
         });
        jc.close();
        	
     });
     
	
	/*$('.confirmation-callback').confirmation({
		onConfirm: function() { 
				$.ajax({
		            url: "DeletePublicacion.html",
		            type: "GET",
		            dataType: "html",
		            data:"id_Pbl="+clicked_id+"&UltDiv="+firtsdiv[1],
		            cache: false,
		            contentType: false,
		            processData: false,
		    			success : function(response) {
		    				console.log("SUCCESS: ", response);
		    				document.getElementById(div).remove();
		    				BarraSnPb(response);
		    				ConsultaEstadiscticas(document.getElementById("comuna").value);
		    			},
		    			error : function(e) {
		    				console.log("ERROR: ", e);
		    				display(e);
		    			},
		    			done : function(e) {
		    				console.log("DONE");
		    			}
		    		});
				
			
		},
		onCancel: function() { }
	});*/
  	
}


function Like(clicked_id) {
	var parent = document.querySelector('#TextBoxDiv'+clicked_id);
    // Cantidad de div
    var Span = parent.querySelectorAll('span');
    	
		$.ajax({
	            url: "LikePublicacion.html",
	            type: "GET",
	            dataType: "html",
	            data:"Lk_Pbl="+clicked_id +"&SpanCls="+Span[1].className, 
	            cache: false,
	            contentType: false,
	            processData: false,
	    			success : function(response) {
	    				console.log("SUCCESS: ", response);
	    				 AlertLike(response ,clicked_id);
	    			},
	    			error : function(e) {
	    				console.log("ERROR: ", e);
	    				display(e);
	    			},
	    			done : function(e) {
	    				console.log("DONE");
	    			}
	    		});
			
		
	
}


function BarraSnPb(response){
	dato=JSON.parse(response);
	 //obtener div
    var parent = document.querySelector('#chat-box');
    // Cantidad de div
    var divs = parent.querySelectorAll('div');

	console.log('datos.legt: '+dato.length+'divs.legt: '+divs.length);
	//si el json esta vacio y en chat-box no hay ningun div pongalo
	if(dato.length==0 && divs.length==0){
		var newTextBoxDiv = $(document.createElement('div'))
	    .attr("id", 'TextBoxDiv-1').attr("class", 'alert alert-info alert-dismissible fade in').attr("role", 'alert');
		newTextBoxDiv.before().html('<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true"></span></button> <i class="fa fa-files-o"></i> <strong>Aun no hay publicaciones por aqui..</strong>');
		$("#chat-box").prepend(newTextBoxDiv);
		}else{
			ShowPublication(response);
		}
}

function  AlertLike(varLk ,idPl){
	if(varLk=="false"){
		sweetAlert('Oops...', 'Publicacion Fue Eliminada!', 'error');
		document.getElementById("TextBoxDiv"+idPl).remove();
		//alert("Esta Publicacion a sido eliminada");
		var parent = document.querySelector('#chat-box');
	    // Cantidad de div
	    var divs = parent.querySelectorAll('div');
		//si en chat-box no hay ningun div pongalo
		if(divs.length==0){
			var newTextBoxDiv = $(document.createElement('div'))
		    .attr("id", 'TextBoxDiv-1').attr("class", 'alert alert-info alert-dismissible fade in').attr("role", 'alert');
			newTextBoxDiv.before().html('<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true"></span></button> <i class="fa fa-files-o"></i> <strong>Aun no hay publicaciones por aqui..</strong>');
			$("#chat-box").prepend(newTextBoxDiv);
			}
		
	}else{
		 //obtener div
	    var parent = document.querySelector('#TextBoxDiv'+idPl);
		var Span = parent.querySelectorAll('span');
		if(Span[1].className=="glyphicon glyphicon-heart"){
			Span[1].className="glyphicon glyphicon-heart-empty";
			console.log("No me gusta!");
			
		}else{
			Span[1].className="glyphicon glyphicon-heart";
			console.log("Gracias por darle Me gusta!");
		}

	    	
	}
}

function Filtro(Vr){
	$.ajax({
        url: "FiltroPb.html",
        type: "GET",
        dataType: "html",
        data:"Comuna="+Vr,
        cache: false,
        contentType: false,
        processData: false,
			success : function(response) {
				console.log("SUCCESS: ", response);
				$("div").remove(".item");
				//control de error para remover barraAzul!
				try {
					//obtener div
			         var parent = document.querySelector('#chat-box');
			         // Cantidad de div
				     var divs = parent.querySelectorAll('div');
				     firtsdiv=(divs[0].id).split('v');
				     if(firtsdiv[1]=="-1"){
				    	 
				    	 document.getElementById(divs[0].id).remove();
				     }
				}
				catch(err) {
					console.log(err.message);
				
				}
				BarraSnPb(response);
				ConsultaEstadiscticas(Vr);
			},
			error : function(e) {
				console.log("ERROR: ", e);
				display(e);
			},
			done : function(e) {
				console.log("DONE");
			}
		});
}


function tildes_unicode(str){
	str = str.replace('á','a');
	str = str.replace('é','e');
	str = str.replace('í','i');
	str = str.replace('ó','o');
	str = str.replace('ú','u');

	str = str.replace('Á','A');
	str = str.replace('É','E');
	str = str.replace('Í','I');
	str = str.replace('Ó','O');
	str = str.replace('Ú','U');

	str = str.replace('ñ','n');
	str = str.replace('Ñ','N');
	return str;
}

//cambios
function DisplayComments(id_publication) {

	
		$.ajax({
	            url: "loadComments.html",
	            type: "GET",
	            dataType: "html",
	            data:"id_pub="+id_publication,
	            cache: false,
	            contentType: false,
	            processData: false,
	    			success : function(response) {
	    				console.log("SUCCESS: ", response);
	    				ShowComments(response,id_publication);
	    			},
	    			error : function(e) {
	    				console.log("ERROR: ", e);
	    				display(e);
	    			},
	    			done : function(e) {
	    				console.log("DONE");
	    			}
	    		});
	
}

//cambios3
function getPublicationRecent(){
	
	$.ajax({
        url: "getPublicationsRecent.html",
        type: "GET",
        dataType: "html",
        cache: false,
        contentType: false,
        processData: false,
			success : function(response) {
				console.log("SUCCESS: ", response);				
				changeImg(response);
			},
			error : function(e) {
				console.log("ERROR: ", e);
				display(e);
			},
			done : function(e) {
				console.log("DONE");
			}
		});
}

//cambios3
function changeImg(response) {
	
	dato=JSON.parse(response);
	
	for (var cont = 0; cont < dato.length; cont++) {

		document.getElementById("imagenIndex"+cont).src='data:image/png;base64,'+ dato[cont].photo;
		
		var parent = document.querySelector('#modalIndexImagen'+cont);
		parent.querySelectorAll('img')[0].src='data:image/png;base64,'+ dato[cont].photo;
		parent.querySelector('h2').innerHTML = 'Publicacion de ' +  
		dato[cont].barrio + ' - ' + dato[cont].pto_cardinal + '(Comuna ' + dato[cont].comuna +')';
	    
		parent.querySelector('p').innerHTML=dato[cont].Descripcion;
		//pulido
		parent.querySelectorAll('img')[1].src= '././resources/img/perfil/'+ dato[cont].weather+ '-weather2.png';
		parent.querySelectorAll('li')[0].innerHTML=dato[cont].nombre_author;
		fechaModal = dato[cont].date.split(' ');
		parent.querySelectorAll('li')[1].innerHTML=fechaModal[2]+'/'+fechaModal[1]+'/'+fechaModal[5]+' - '+fechaModal[3];
		
	}
	
	
 
}

function registroUsuario() {
	
	$('#formularioReg').bootstrapValidator({
		 
		 message: 'Este valor no es valido',

		 feedbackIcons: {

			 valid: 'glyphicon glyphicon-ok',

			 invalid: 'glyphicon glyphicon-remove',

			 validating: 'glyphicon glyphicon-refresh'

		 },

		 fields: {

			 name: {

				 validators: {

					 notEmpty: {

						 message: 'El nombre es requerido'

					 }

				 }

			 },

			 lastname: {

				 validators: {

					 notEmpty: {

						 message: 'el apellido es requerido'

					 }

				 }

			 },
			 
			  email: {

				 validators: {

					 notEmpty: {

						 message: 'el email es requerido'

					 },
					 
					 emailAddress: {
						 
						 message: 'El correo electronico no es valido'
	 
					 }
			 
			 		

				 }

			 },
			 
			 birth: {

				 validators: {

					 notEmpty: {

						 message: 'la fecha es requerida'

					 }
			 
			 		

				 }

			 },
			 
			 phone: {

				 validators: {

					 notEmpty: {

						 message: 'el telefono es requerido'

					 },
					 regexp: {
						 
						 regexp: /^(?=[0-9]*$)(?:.{7}|.{10})$/, 
						 //pulido3
						 message: 'Nro. de telefono(7) o celular(10)'
	 
					 }
			 
			 		

				 }

			 },
			 
			 password: {

				 validators: {

					 notEmpty: {

						 message: 'el password es requerido'

					 },
					 
					 stringLength: {
						 
						 min: 8,
						 
						 max: 12,
						//pulido3
						 message: 'El password debe contener entre 8 y 12 caracteres'
	 
					 },
					//pulido3
					 identical: {
		                    field: 'password2',
		                    message: 'los campos password y confirm password deben de ser iguales'
		             }
			 
			 		

				 }

			 },
			 
			 password2: {

				 validators: {

					 notEmpty: {
						//pulido3
						 message: 'confirmar password es requerido'

					 },
					 identical: {
		                    field: 'password',
		                    //pulido3
		                    message: 'los campos password y confirm password deben de ser iguales'
		             }

				 }

			 },
			 
			 
			 
			 

		 }
		 
	});
	$('#formularioReg').bootstrapValidator().on('submit', function (e) {
		  if (e.isDefaultPrevented()) {
		    // handle the invalid form...
			  console.log('hay problema');
			  return true;
		  } else {
		    // everything looks good!
			  
			  console.log('todo melo');
			  var formData = new FormData(document.getElementById("formularioReg"));
			  swal({
				  title: "Verificar Cuenta",
				  text: "Te enviaremos un enlace a tu cuenta de correo",
				  type: "info",
				  closeOnConfirm: false,
				  showLoaderOnConfirm: true
				}, function () {
				  setTimeout(function () {
					  $.ajax({
					        url: "registrar.html",
					        type: "POST",
					        dataType: "html",
					        data:formData,
					        cache: false,
					        contentType: false,
					        processData: false,
								success : function(response) {
									console.log("SUCCESS: ", response);
									
									if(response=="success"){
										setTimeout(function() {
									        swal({
									            title: "Correcto!",
									            text: "Enlace Enviado!",
									            type: "success"
									        }, function() {
									        	//pulido2
									        	var url = window.location.origin+"/"+window.location.pathname.split('/')[1];
									        	window.location.replace(url); 
									        });
									    }, 1000);	
									}else{
										setTimeout(function() {
									        swal({
									            title: "Invalido!",
									            text: "Correo ya registrado!",
									            type: "error"
									        });
									    }, 1000);	
									}
									 
								},
								error : function(e) {
									console.log("ERROR: ", e);
								},
								done : function(e) {
									console.log("DONE");
								}
							});
					  
				  }, 1000);
				});
			  return false;
			  
		  }
		});
		
}

//pulido2
function Login() {
	
	//document.getElementById("span").text="hola mario";
	var formData = new FormData(document.getElementById("logger"));
	$.ajax({
        url: "login.html",
        type: "POST",
        dataType: "html",
        data:formData,
        cache: false,
        contentType: false,
        processData: false,
			success : function(response) {
				console.log("SUCCESS: ", response);
				
				if(response=="success"){
					//pulido2
					var url = window.location.origin+"/"+window.location.pathname.split('/')[1];
					window.location.replace(url+"/Registrado.html");
				}else{
					if(response=="warning"){
						swal("Datos invalidos!");
					}else{
						if(response=="invalidate"){
							swal("Usuario no verificado!");

						}else{
								if(response=="error"){
									swal("Usuario no encontrado!");
								//pulido2
								}else{
										swal({
											  title: "Cuenta Desactivada!",
											  text: "Deseas reactivar tu Cuenta?",
											  type: "info",
											  showCancelButton: true,
											  closeOnConfirm: false,
											  showLoaderOnConfirm: true
											}, function () {
											  setTimeout(function () {
												  var formData = new FormData();
												  formData.append("email",response.split(' ')[0]);
												  formData.append("id_account",response.split(' ')[1]);
												  $.ajax({
												        url: "reactivarCuenta.html", //correo
												        type: "POST",
												        dataType: "html",
												        data: formData,
												        cache: false,
												        contentType: false,
												        processData: false,
															success : function(response) {
																console.log("SUCCESS reactivar: ", response);
																setTimeout(function() {
															        swal({
															            title: "Correcto!",
															            text: "Se ha enviado un enlace a tu correo para reactivar tu cuenta",
															            type: "success"
															        }, function() {
															        	var url = window.location.origin+"/"+window.location.pathname.split('/')[1];
																		window.location.replace(url);
															        });
															    }, 1000);
															   
															},
															error : function(e) {
																console.log("ERROR: ", e);
																display(e);
															},
															done : function(e) {
																console.log("DONE");
															}
														});
											    
											  }, 1000);
											});
									
							}
							
						}
						
					}
					
				}
				 
			},
			error : function(e) {
				console.log("ERROR: ", e);
			},
			done : function(e) {
				console.log("DONE");
			}
		});
	
 
}

function olvCont() {
	
	$('#modal1').modal('hide');
	
	swal({
		  title: "Recuperar Password!",
		  text: "Te enviaremos el password a tu cuenta de correo",
		  type: "input",
		  showCancelButton: true,
		  closeOnConfirm: false,
		  showLoaderOnConfirm: true,
		  inputPlaceholder: "Cuenta de correo"
		}, function (inputValue) {
		  if (inputValue === false) return false;
		  
		  if (inputValue === "") {
			  
		    swal.showInputError("Digita por favor el correo!");
		    return false
		  }
		  $.ajax({
		        url: "olvCont.html",
		        type: "GET",
		        dataType: "html",
		        data:"correo="+inputValue,
		        cache: false,
		        contentType: false,
		        processData: false,
					success : function(response) {
						console.log("SUCCESS: ", response);
						
						if(response=="success"){
							 swal("Nice!", "Password enviado!", "success");
						}else{
							swal.showInputError("Cuenta de Correo Erronea!");
						}
						 
					},
					error : function(e) {
						console.log("ERROR: ", e);
					},
					done : function(e) {
						console.log("DONE");
					}
				});
		 
		});
	
	/*	$.confirm({
        title: 'Recuperar Password',
        content:'url:form.html',
        buttons: {
            sayMyName: {
                text: 'Say my name',
                btnClass: 'btn-warning',
                action: function () {
                    var input = this.$content.find('input#Cc');
                    var errorText = this.$content.find('.text-danger');
                    if (input.val() == '') {
                        errorText.html('Por favor digita el correo electronico!').slideDown(200);
                        return false;
                    } else {
                        $.alert('Hello ' + input.val() + ', i hope you have a great day!');
                    }
                }
            },
            later: function () {
                // do nothing.
            }
        }
    });
	
	
	$.ajax({
        url: "olvCont.html",
        type: "POST",
        dataType: "html",
        data:data,
        cache: false,
        contentType: false,
        processData: false,
			success : function(response) {
				console.log("SUCCESS: ", response);
				
				if(response=="success"){
					window.location.replace("http://localhost:8080/proyect/Registrado.html");
				}else{
					
				}
				 
			},
			error : function(e) {
				console.log("ERROR: ", e);
			},
			done : function(e) {
				console.log("DONE");
			}
		});*/

}
function Manual(archivo) {

	//document.location = archivo;
	window.open(archivo, 'resizable,scrollbars'); 
 
}


	
	
	





	

