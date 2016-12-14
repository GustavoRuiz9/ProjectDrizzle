document.getElementById("BtnPub").onclick = ValPublication;
document.getElementById("BtnModal").onclick = Geolocation;
//cambios
document.getElementById("BtnComment").onclick = regComment;

function Geolocation(){
	navigator.geolocation.getCurrentPosition(showPosition);
	function showPosition(position){
		var geocoder = new google.maps.Geocoder();
		lat=position.coords.latitude;
		lon=position.coords.longitude;
		//lat = 3.46797234440776;
		//lon = -76.50285601615906 Sena Comuan5
		
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
	}else{
		document.getElementById("BtnPub").disabled=true;
	}
	$('#Barrio').html(datos[0]);
}


function ValPublication() {
        
        var formData = new FormData(document.getElementById("formPublication"));
        
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
    
    }

//cambios
function regComment() {
    
	var formData = new FormData();
    var descriptionComentary = document.getElementById("textarearDescription").value;
    
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
		 		'<button id="'+dato[cont].id_publication+'" class="btn btn-default confirmation-callback" onclick="AlertDrop(this.id)">'+
				'<span class="glyphicon glyphicon-trash"></span>'+
				'</button>'+
		 		'<button id="'+dato[cont].id_publication+'" class="btn btn-default" data-toggle="modal" onclick="DisplayComments(this.id)">'+
				'<span class="glyphicon glyphicon-pencil"></span>'+
				'</button>'+
				'</div>';
		 		//cambios
		 	}else{
		 		if(dato[cont].author=="false"){
		 			Btn='<div class="attachment">'+
		        	'<button id="'+dato[cont].id_publication+'" class="btn btn-default" onclick="Like(this.id)" >'+
					'<span class="glyphicon glyphicon-heart-empty"></span>'+
					'</button>'+
			 		'<button id="'+dato[cont].id_publication+'" class="btn btn-default" data-toggle="modal" onclick="DisplayComments(this.id)">'+
					'<span class="glyphicon glyphicon-pencil"></span>'+
					'</button>'+
					'</div>';	
		 			//cambios
		 		}else{
		 			Btn='<div class="attachment">'+
		        	'<button id="'+dato[cont].id_publication+'" class="btn btn-default" onclick="Like(this.id)" >'+
					'<span class="glyphicon glyphicon-heart"></span>'+
					'</button>'+
			 		'<button id="'+dato[cont].id_publication+'" class="btn btn-default" data-toggle="modal" onclick="DisplayComments(this.id)">'+
					'<span class="glyphicon glyphicon-pencil"></span>'+
					'</button>'+
					'</div>';
		 			//cambios

		 			
		 		}
		 		
		 		
		 	}
		 	
		  onclick="consultaPerfil(this.name,this.id)"
		   newTextBoxDiv.before().html('<img src="data:image/png;base64,'+ dato[cont].profile + '"  alt="user image" class="online" style="cursor: pointer" id="popoveUser'+dato[cont].id_publication+'" name="'+ dato[cont].authorperfil + '" onclick="consultaPerfil(this.name,this.id)" >'+
				    '<p class="message">'+
					'<a href="#" class="name">'+
					'<small class="text-muted pull-right"><i class="fa fa-clock-o"></i>'+dato[cont].date.substring(13, 17)+'</small>'+ dato[cont].nombre_author +
					'</a>'+
					dato[cont].weather+
					'<br>'+
					dato[cont].Descripcion+
					'</p> <div class="attachment">'+
					'<img src="data:image/png;base64,'+dato[cont].photo+'" class="img-responsive">'+
					'</div>'+Btn);

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
		
		
		var divPublicacion = $(document.createElement('div')).attr("id", "PubBoxDiv"+dato[0].id_publication).attr("class", 'box-body chat');
		
		divPublicacion.before().html('<div class="item"> <img src="data:image/png;base64,'+ dato[0].profile + '" alt="user image" class="online">'+
				    '<p class="message">'+
					'<a href="#" class="name">'+
					'<small class="text-muted pull-right"><i class="fa fa-clock-o"></i>'+dato[0].date.substring(13, 17)+'</small>'+ dato[0].nombre_author +
					'</a>'+
					dato[0].weather+
					'<br>'+
					dato[0].Descripcion + "</div>");
		 
	
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
	alert("pinte comentarios");
	var divComment;
	cont = 0;
	if ((dato[0].comments.length)==0){
		divComment = $(document.createElement('div')).attr("id", "CommBoxDiv-1");
		$("#myModalLabelComments").prepend(divComment);
	}else{
		while (cont <= (dato[0].comments.length-1)){
			divComment = $(document.createElement('div')).attr("id", "CommBoxDiv"+dato[0].comments[cont].id_commentary).attr("class", 'box-body chat');
			
			divComment.before().html('<div class="item"> <img src="data:image/png;base64,'+ dato[0].comments[cont].profile + '" alt="user image" class="online">'+
				    '<p class="message">'+
					'<a href="#" class="name">'+
					'<small class="text-muted pull-right"><i class="fa fa-clock-o"></i>'+dato[0].comments[cont].date + 	'</small>'+ dato[0].comments[cont].nombre_author +
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
	
	$('.confirmation-callback').confirmation({
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
	});
}


function Like(clicked_id) {
	var parent = document.querySelector('#TextBoxDiv'+clicked_id);
    // Cantidad de div
    var Span = parent.querySelectorAll('span');
    	
		$.ajax({
	            url: "LikePublicacion.html",
	            type: "GET",
	            dataType: "html",
	            data:"Lk_Pbl="+clicked_id +"&SpanCls="+Span[0].className,
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
	if(dato.length==0 && divs.length==0 ){
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
		if(Span[0].className=="glyphicon glyphicon-heart"){
			Span[0].className="glyphicon glyphicon-heart-empty";
			console.log("No me gusta!");
			
		}else{
			Span[0].className="glyphicon glyphicon-heart";
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
				ShowPublication(response);
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

	alert("Entra y trae" + id_publication);
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


	
	
	





	

