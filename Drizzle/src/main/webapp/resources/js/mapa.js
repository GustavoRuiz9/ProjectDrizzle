cargarmap();
function cargarmap(){
	
	navigator.geolocation.getCurrentPosition(showPosition,showError);
	//var geocoder = new google.maps.Geocoder();
	function showPosition(position)
	  {
	  lat=position.coords.latitude;
	  lon=position.coords.longitude;
	  console.log(lat);
	  console.log(lon);
	  latlon2=new google.maps.LatLng(3.4697714864594627, -76.49712949991226)
	  latlonmarker= new google.maps.LatLng(lat, lon)
	  latlon=new google.maps.LatLng(3.4372200, -76.5225000)
	  mapholder=document.getElementById('world-map')
	  mapholder.style.height='400px';
	  mapholder.style.width='100%';
	  var myOptions={
	  center:latlon,zoom:12,
	  mapTypeId:google.maps.MapTypeId.ROADMAP,
	  mapTypeControl:false,
	  navigationControlOptions:{style:google.maps.NavigationControlStyle.SMALL}
	  };
	  var map=new google.maps.Map(document.getElementById("world-map"),myOptions);
	  
	  
	  
	  var marker=new google.maps.Marker({position:latlonmarker,map:map,animation: google.maps.Animation.BOUNCE,title:"Estas Aqui!"});
	  
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
	   //geocoder.geocode({ 'latLng': latlonmarker },processGeocoder);

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
		  }
		
		
	
	}

	
	
	





	

