
document.getElementById("myBtn").onclick = displayfile;
cargarmap();
     
function myFunction() {
	if(document.getElementById("password").value != (document.getElementById("password2")).value){
		alert("password's difrentes");
		return false;
	}else{
		return true;
	}
	
}
     
    
function displayfile() {
    document.getElementById("changeprofile1").click();
    $('#changeprofile1').change( function(event) {
        var tmppath = URL.createObjectURL(event.target.files[0]);
        document.getElementById("Btn").click();
        
        //$("#imagen1").fadeIn("fast").attr('src',URL.createObjectURL(event.target.files[0]));
        //alert(document.getElementById("Imagen").value);
        
        });
    }


$('.modal').on('hidden.bs.modal', function () {
    $(this).find('form').trigger('reset');
    removeAllOptions(document.getElementById("comboboxTipoClima"));
    document.getElementById("list").value=" ";
    //var now = new Date().toJSON().slice(0,10);
    /*$("#myModal").find(".modal-title").text("Publicacion del Clima " + now.getDate()+"/"+(now.getMonth()+1)+"/"+now.getFullYear()
    		+ " " + now.getHours()+":"+ now.getMinutes());*/
});

function removeAllOptions(selectbox)
{
	var i;
	for(i=selectbox.options.length-1;i>=0;i--)
	{
		selectbox.remove(i);
	}
}

function cargar(type) {
    var x = document.getElementById("comboboxTipoClima");
    var y = document.getElementById("formPublication");
    
    if(type == "1"){
    	y.clima2.checked=false;
    	y.clima3.checked=false;
    	removeAllOptions(x);
    	var opc = ["...","Lluvia Debil","Lluvia Moderada","Lluvia Fuerte"];
    	for (val in opc){
	    	var option = document.createElement("option");
	    	option.text = opc[val];
	    	option.value = opc[val];
	    	option.id = "opt";
	        x.add(option);	
	    }
    }else{
    	if(type == "2"){
    		y.clima1.checked=false;
        	y.clima3.checked=false;
        	removeAllOptions(x);
        	var opc = ["...","Soleado","Sol Abrasador"];
        	for (val in opc){
    	    	var option = document.createElement("option");
    	    	option.text = opc[val];
    	        x.add(option);	
    	    }	
    	}else{
    		y.clima1.checked=false;
        	y.clima2.checked=false;
        	removeAllOptions(x);
        	var opc = ["...","Promedio"];
        	for (val in opc){
    	    	var option = document.createElement("option");
    	    	option.text = opc[val];
    	        x.add(option);	
    	    }
    	}
    }
    
}

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

document.getElementById('files').addEventListener('change', archivo, false);

$("#files").fileinput({
	showCaption: false,
	browseClass: "btn btn-primary btn-lg",
	fileType: "any"
	});



function cargarmap(){
	
	navigator.geolocation.getCurrentPosition(showPosition,showError);
	function showPosition(position)
	  {
	  lat=position.coords.latitude;
	  lon=position.coords.longitude;
	  latlonmarker= new google.maps.LatLng(lat, lon)
	  latlon=new google.maps.LatLng(3.4372200, -76.5225000)
	  mapholder=document.getElementById('world-map')
	  mapholder.style.height='400px';
	  mapholder.style.width='100%';
	  var myOptions={
	  center:latlon,zoom:12,
	  //draggable: false,
	  mapTypeId:google.maps.MapTypeId.ROADMAP,
	  mapTypeControl:false,
	  navigationControlOptions:{style:google.maps.NavigationControlStyle.SMALL}
	  };
	  var map=new google.maps.Map(document.getElementById("world-map"),myOptions);
	  
	  var marker=new google.maps.Marker({position:latlonmarker,map:map,animation: google.maps.Animation.BOUNCE,title:"You are here!"});
	  
	  var strictBounds = new google.maps.LatLngBounds(
			     new google.maps.LatLng(3.503107202733066, -76.46993048095703), 
			     new google.maps.LatLng(3.35763979284503, -76.56468121337889)
			   );
	  
	  // Listen for the dragend event
	   google.maps.event.addListener(map, 'dragend', function() {
	     if (strictBounds.contains(map.getCenter())) return;

	     // We're out of bounds - Move the map back within the bounds

	     var c = map.getCenter(),
	         x = c.lng(),
	         y = c.lat(),
	         maxX = strictBounds.getNorthEast().lng(),
	         maxY = strictBounds.getNorthEast().lat(),
	         minX = strictBounds.getSouthWest().lng(),
	         minY = strictBounds.getSouthWest().lat();

	     if (x < minX) x = minX;
	     if (x > maxX) x = maxX;
	     if (y < minY) y = minY;
	     if (y > maxY) y = maxY;
			//alert(y);
	     map.setCenter(new google.maps.LatLng(3.4372200,-76.5225000));
	   });
	  
	   var opt = { minZoom: 11, maxZoom: 14 };
	   map.setOptions(opt);
	  
	  }
	function showError(error)
	  {
	  switch(error.code) 
	    {
	    case error.PERMISSION_DENIED:
	      x.innerHTML="Denegada la peticion de Geolocalización en el navegador."
	      break;
	    case error.POSITION_UNAVAILABLE:
	      x.innerHTML="La información de la localización no esta disponible."
	      break;
	    case error.TIMEOUT:
	      x.innerHTML="El tiempo de petición ha expirado."
	      break;
	    case error.UNKNOWN_ERROR:
	      x.innerHTML="Ha ocurrido un error desconocido."
	      break;
	    }
	  }}