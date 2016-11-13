document.getElementById("BtnPub").onclick = ValPublication;
document.getElementById("BtnModal").onclick = Geolocation;

function Geolocation(){
	navigator.geolocation.getCurrentPosition(showPosition);
	function showPosition(position){
		var geocoder = new google.maps.Geocoder();
		lat=position.coords.latitude;
		lon=position.coords.longitude;
		//lat = 3.470392618039112;
		//lon = -76.52765974402428;
		
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
	
	var json = "<h4>Ajax Response</h4><pre>"
			+ datos[0] + "</pre>";
	$('#Barrio').html(datos[0]);
}


function ValPublication() {
        
        var formData = new FormData(document.getElementById("formPublication"));
        
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
	alert(data);
	
	$('#').html(data);
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



	
	
	





	

