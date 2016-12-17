/*document.getElementById("BtnAjustes").onclick = establecerAjustes;
document.getElementById("ControlAjustes").onclick = showSettings;
*/

function cargarmap(Data){
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
	  map=new google.maps.Map(document.getElementById("world-map"),myOptions);
	  
	  var marker=new google.maps.Marker({position:latlonmarker,map:map,animation: google.maps.Animation.BOUNCE, title:"Estas Aqui!"});
	  
	  
	  var LatLog=new Array();
	  LatLog=[[0,0],[3.452861,-76.565703],[3.464947, -76.514994],[3.449831, -76.533557],[3.471180, -76.511211],[3.473621, -76.495619],
	          [3.484927, -76.486556],[3.456655, -76.486385],[3.446677, -76.504087],[3.437151, -76.525403],[3.418130, -76.529480],
	          [3.423699, -76.514888],[3.435994, -76.501928],[3.429568, -76.490255],[3.424942, -76.477338],[3.410505, -76.499439],
	          [3.401037, -76.515146],[3.391784, -76.536346],[3.378191, -76.556769],[3.411019, -76.548577],[3.421900, -76.557074],
	          [3.417723, -76.466400],[3.360003, -76.538194]];
	  
	 
							
	  						dato = JSON.parse(Data);
							
							var matriz=new Array();
							
							for (var int = 0; int < dato[0].registro.length; int++) {
								matriz[int]=new Array();
								
								
								for (var int2 = 0; int2 < dato[0].registro[int].length; int2++) {
	
									console.log("matriz["+int+"]" + "["+int2+"] = "+ dato[0].registro[int][int2].tipo +","+dato[0].registro[int][int2].clima);
									matriz[int][int2] = [dato[0].registro[int][int2].tipo,dato[0].registro[int][int2].clima];
									
									
								}
								

							}
							
							
							var myChart = new JSChart('graph', 'line');
							
							myChart.setSize(600, 400);
							myChart.setAxisValuesNumberY(17);
							myChart.setAxisValuesNumberX(17);
							myChart.setIntervalStartX(8);
							myChart.setIntervalStartY(8);
							myChart.setLabelY([10, 'Rain']);
							myChart.setLabelY([15, 'Storm']);
							myChart.setLabelY([20, 'Sunny']);
							myChart.setLabelY([25, 'Temper']);
							myChart.setGrid(false);
							myChart.setTitle("Clima");

							myChart.setLabelX([10, 'Madrugada']);
							myChart.setLabelX([15, 'Mañana']);
							myChart.setLabelX([20, 'Tarde']);
							myChart.setLabelX([25, 'Noche']);
							
							myChart.setShowXValues(false);
							myChart.setTitleColor('#454545');
							myChart.setShowYValues(false);
							myChart.setShowXValues(false);
							
							myChart.setTooltip([10,' ']);
							myChart.setTooltip([15,' ']);
							myChart.setTooltip([20,' ']);
							myChart.setTooltip([25,' ']);
							myChart.setFlagColor('#9D16FC');
							myChart.setFlagRadius(4);
							myChart.setAxisPaddingRight(100);
							myChart.setLegendShow(true);
							myChart.setAxisNameX(' ');
							myChart.setAxisNameY(' ');
							myChart.setLegendPosition(490, 80);
							
							console.log("M:"+matriz.length);
							
							var colores = new Array('azul', 'rojo', 'gris','verde','negro','amarillo',
									'violeta','cafe','rosado','carmin','naranja','azulclaro','verdeclaro',
									'morado','Aquamarine','Teal','SpringGreen','DarkViolet','DarkKhaki',
									'Gold','LightSalmon','IndianRed','Coral','Moccasin','MintCream');
						
							if(matriz.length > 0){
								val = false;
								for (var int3 = 0; int3 < matriz.length; int3++) {
									
										console.log("pnte de color " + colores[int3]);
										
										console.log("M:"+matriz[int3].length);
										
										if(matriz[int3].length==1){											
											console.log("entra de a uno " + matriz[int3][0]);
											myChart.setDataArray([matriz[int3][0],matriz[int3][0]],colores[int3]);
										}else{
											console.log("entra mas de uno " + matriz[int3]);
											myChart.setDataArray(matriz[int3],colores[int3]);

										}
										
										myChart.setLegendForLine(colores[int3], 'comuna'+dato[0].registro[int3][0].comuna);
										//alert("clima " + matriz[int3][matriz[int3].length-1][1]);
										var image = '././resources/img/IconsMap/'+matriz[int3][matriz[int3].length-1][1]+'.png';	
										latlonmarker1= new google.maps.LatLng(LatLog[dato[0].registro[int3][0].comuna][0],LatLog[dato[0].registro[int3][0].comuna][1]);
										var marker=new google.maps.Marker({position:latlonmarker1,map:map,icon: image, title:"Comuna "+dato[0].registro[int3][0].comuna});
									
								}
								
								myChart.setLineColor('#000000', 'negro');
								myChart.setLineColor('#008000', 'verde');
								myChart.setLineColor('#BBBBBB', 'gris');
								myChart.setLineColor('#660000', 'carmin');
								myChart.setLineColor('#FF0000', 'rojo');
								myChart.setLineColor('#FFFF00', 'amarillo');
								myChart.setLineColor('#EE82EE', 'violeta');
								myChart.setLineColor('#8B4513', 'cafe');
								myChart.setLineColor('#FFC0CB', 'rosado');
								myChart.setLineColor('#FFA500', 'naranja');
								myChart.setLineColor('#ADD8E6', 'azulclaro');
								myChart.setLineColor('#90EE90', 'verdeclaro');
								myChart.setLineColor('#C71585', 'morado');
								myChart.setLineColor('#7FFFD4', 'Aquamarine');
								myChart.setLineColor('#008080', 'Teal');
								myChart.setLineColor('#00FF7F', 'SpringGreen');
								myChart.setLineColor('#9400D3', 'DarkViolet');
								myChart.setLineColor('#BDB76B', 'DarkKhaki');
								myChart.setLineColor('#FFD700', 'Gold');
								myChart.setLineColor('#FFA07A', 'LightSalmon');
								myChart.setLineColor('#CD5C5C', 'IndianRed');
								myChart.setLineColor('#FF7F50', 'Coral');
								myChart.setLineColor('#FFE4B5', 'Moccasin');
								myChart.setLineColor('#F5FFFA', 'MintCream');
								myChart.setLineColor('#0000FF', 'azul');
								
								
								myChart.draw();
								
								
							}
	  
	  
	  
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




function displayLineChart(Data) {
	//setTimeout(cargarmap, 0 );
	  
	  var LatLog=new Array();
	  LatLog=[[0,0],[3.452861,-76.565703],[3.464947, -76.514994],[3.449831, -76.533557],[3.471180, -76.511211],[3.473621, -76.495619],
	          [3.484927, -76.486556],[3.456655, -76.486385],[3.446677, -76.504087],[3.437151, -76.525403],[3.418130, -76.529480],
	          [3.423699, -76.514888],[3.435994, -76.501928],[3.429568, -76.490255],[3.424942, -76.477338],[3.410505, -76.499439],
	          [3.401037, -76.515146],[3.391784, -76.536346],[3.378191, -76.556769],[3.411019, -76.548577],[3.421900, -76.557074],
	          [3.417723, -76.466400],[3.360003, -76.538194]];
	  
	  var image = '././resources/img/IconsMap/10.png';	
	  latlonmarker1= new google.maps.LatLng(3.4503904999999997,-76.501415);
	  var marker=new google.maps.Marker({position:latlonmarker1,map:map,animation: google.maps.Animation.BOUNCE, title:"Comuna!"});
	  alert(map);
							dato = JSON.parse(Data);
							
							var matriz=new Array();
							
							for (var int = 0; int < dato[0].registro.length; int++) {
								matriz[int]=new Array();
								
								
								for (var int2 = 0; int2 < dato[0].registro[int].length; int2++) {
	
									console.log("matriz["+int+"]" + "["+int2+"] = "+ dato[0].registro[int][int2].tipo +","+dato[0].registro[int][int2].clima);
									matriz[int][int2] = [dato[0].registro[int][int2].tipo,dato[0].registro[int][int2].clima];
	
								}
								

							}
							
							
							var myChart = new JSChart('graph', 'line');
							
							myChart.setSize(600, 400);
							myChart.setAxisValuesNumberY(17);
							myChart.setAxisValuesNumberX(17);
							myChart.setIntervalStartX(8);
							myChart.setIntervalStartY(8);
							myChart.setLabelY([10, 'Rain']);
							myChart.setLabelY([15, 'Storm']);
							myChart.setLabelY([20, 'Sunny']);
							myChart.setLabelY([25, 'Temper']);
							myChart.setGrid(false);
							myChart.setTitle("Clima");

							myChart.setLabelX([10, 'Madrugada']);
							myChart.setLabelX([15, 'Mañana']);
							myChart.setLabelX([20, 'Tarde']);
							myChart.setLabelX([25, 'Noche']);
							
							myChart.setShowXValues(false);
							myChart.setTitleColor('#454545');
							myChart.setShowYValues(false);
							myChart.setShowXValues(false);
							
							myChart.setTooltip([10,' ']);
							myChart.setTooltip([15,' ']);
							myChart.setTooltip([20,' ']);
							myChart.setTooltip([25,' ']);
							myChart.setFlagColor('#9D16FC');
							myChart.setFlagRadius(4);
							myChart.setAxisPaddingRight(100);
							myChart.setLegendShow(true);
							myChart.setAxisNameX(' ');
							myChart.setAxisNameY(' ');
							myChart.setLegendPosition(490, 80);
							
							console.log("M:"+matriz.length);
							
							var colores = new Array('azul', 'rojo', 'gris','verde','negro','amarillo',
									'violeta','cafe','rosado','carmin','naranja','azulclaro','verdeclaro',
									'morado','Aquamarine','Teal','SpringGreen','DarkViolet','DarkKhaki',
									'Gold','LightSalmon','IndianRed','Coral','Moccasin','MintCream');
						
							if(matriz.length > 0){
								val = false;
								for (var int3 = 0; int3 < matriz.length; int3++) {
									
										console.log("pnte de color " + colores[int3]);
										
										console.log("M:"+matriz[int3].length);
										
										if(matriz[int3].length==1){											
											console.log("entra de a uno " + matriz[int3][0]);
											myChart.setDataArray([matriz[int3][0],matriz[int3][0]],colores[int3]);
										}else{
											console.log("entra mas de uno " + matriz[int3]);
											myChart.setDataArray(matriz[int3],colores[int3]);

										}
										
										myChart.setLegendForLine(colores[int3], 'comuna'+dato[0].registro[int3][0].comuna);
									
								}
								
								myChart.setLineColor('#000000', 'negro');
								myChart.setLineColor('#008000', 'verde');
								myChart.setLineColor('#BBBBBB', 'gris');
								myChart.setLineColor('#660000', 'carmin');
								myChart.setLineColor('#FF0000', 'rojo');
								myChart.setLineColor('#FFFF00', 'amarillo');
								myChart.setLineColor('#EE82EE', 'violeta');
								myChart.setLineColor('#8B4513', 'cafe');
								myChart.setLineColor('#FFC0CB', 'rosado');
								myChart.setLineColor('#FFA500', 'naranja');
								myChart.setLineColor('#ADD8E6', 'azulclaro');
								myChart.setLineColor('#90EE90', 'verdeclaro');
								myChart.setLineColor('#C71585', 'morado');
								myChart.setLineColor('#7FFFD4', 'Aquamarine');
								myChart.setLineColor('#008080', 'Teal');
								myChart.setLineColor('#00FF7F', 'SpringGreen');
								myChart.setLineColor('#9400D3', 'DarkViolet');
								myChart.setLineColor('#BDB76B', 'DarkKhaki');
								myChart.setLineColor('#FFD700', 'Gold');
								myChart.setLineColor('#FFA07A', 'LightSalmon');
								myChart.setLineColor('#CD5C5C', 'IndianRed');
								myChart.setLineColor('#FF7F50', 'Coral');
								myChart.setLineColor('#FFE4B5', 'Moccasin');
								myChart.setLineColor('#F5FFFA', 'MintCream');
								myChart.setLineColor('#0000FF', 'azul');
								
								
								myChart.draw();
								
								
							}
					  }

function ConsultaEstadiscticas(comuna){
	//alert(comuna);
	$.ajax({
        url: "constaGrafica.html",
        type: "GET",
        dataType: "html",
        data:"comuna="+comuna,
        cache: false,
        contentType: false,
        processData: false,
			success : function(response) {
				console.log("SUCCESS: ", response);
				cargarmap(response);
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

function consultaPerfil(id_author,id_Pb){
	$.ajax({
        url: "consultarDatosPerfilAuthor.html",
        type: "GET",
        dataType: "html",
        data:"id_author="+id_author,
        cache: false,
        contentType: false,
        processData: false,
			success : function(response) {
				console.log("SUCCESS: ", response);
				PrintPerfil(response,id_Pb);
			   
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



function establecerAjustes(form){
	var formData = new FormData(document.getElementById("formularioAjustes"));
	
	swal({
		  title: "Actualizar Ajustes",
		  text: "Seguro Deseas Actuzalizar Tus Ajustes",
		  type: "warning",
		  showCancelButton: true,
		  closeOnConfirm: false,
		  showLoaderOnConfirm: true
		}, function () {
		  setTimeout(function () {
			  document.getElementById("ControlAjustes").click();
			  $.ajax({
			        url: "obtenerAjustes.html",
			        type: "POST",
			        dataType: "html",
			        data:formData,
			        cache: false,
			        contentType: false,
			        processData: false,
						success : function(response) {
							console.log("SUCCESS: ", response);
							//sweetAlert('Oops...', 'Publicacion Fue Eliminada!', 'error');
							swal("Ajustes Actualizados!");
						   
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

function showSettings(){
	$.ajax({
        url: "showSettings.html",
        type: "POST",
        dataType: "html",
        cache: false,
        contentType: false,
        processData: false,
			success : function(response) {
				console.log("SUCCESS: ", response);
				PrintSettings(response);
			   
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

function PrintSettings(data){
	dato=JSON.parse(data);
	
	document.getElementById("nombreModificar").value=dato[0].nombre;
	document.getElementById("apellidoModificar").value=dato[0].apellido;
	document.getElementById("contrasenaModificar").value=dato[0].contrasena;
	document.getElementById("telefonoModificar").value=dato[0].number_phone;
	document.getElementById("correoModificar").value=dato[0].email;
	document.getElementById("checkTelefono").checked=dato[0].telefono;
	document.getElementById("checkCorreo").checked=dato[0].correo;
	
	//alert("Print");
	
}



function PrintPerfil(data,Id_Pb){
	idPb=Id_Pb.split('r');
	dato=JSON.parse(data);
	
	var options = {year: "numeric", month: "short", day: "numeric"};
    var fecha = dato[0].birth_date.replace("-","/");
    
	var Rpt=dato[0].reputation; 
		rp = "";
	if(Rpt<=50){
		rp="black";
	}else if(Rpt>50 && Rpt<70){
		//alert(Rpt);
		rp="blue";
		
	}else if(Rpt>=70 && Rpt<100){
		rp="silver";
		
	}else{
		rp="gold";
	}
	var conten="";
	dst='<img src="data:image/jpg;base64,'+dato[0].profile+'" class="online img-circle" alt="User Image" style="width: 90px; height: 90px;">'+
		'<p align="center">' +dato[0].nombre_author+'</p>'+
		'<medium align="center">'+ new Date(fecha).toLocaleDateString("es-ES", options)+'</medium>&nbsp;'+
		'<span class="glyphicon glyphicon-star" style="color:' +rp +'"></span>';
	
	if(dato[0].correo==true){
		conten+="<strom>Email: </strom><p>"+dato[0].email+"</p>";
	}
	if(dato[0].telefono==true){
		conten+="<strom>Tel: </strom><p>"+dato[0].number_phone+"</p>";
	}
	
	
		$('#popoveUser'+idPb[1]).popover({
				trigger:'manual',
				title:dst,
		        content : conten,
		        html: true
		    });
		$('#popoveUser'+idPb[1]).popover('show');
		var conten="";
}

$('html').on('click', function(e) {
	  if (typeof $(e.target).data('original-title') == 'undefined' &&
	     !$(e.target).parents().is('.popover.in')) {
	    $('[data-original-title]').popover('destroy');
	  }
	});

	$(document).ready(function(){

	    $('.btn').popover();

	    $('.btn').on('click', function (e) {
	        $('.btn').not(this).popover('destroy');
	    });


	});
	
	$(document).on('click', function (e) {
	    $('[data-toggle="popover"],[data-original-title]').each(function () {
	        //the 'is' for buttons that trigger popups
	        //the 'has' for icons within a button that triggers a popup
	        if (!$(this).is(e.target) && $(this).has(e.target).length === 0 && $('.popover').has(e.target).length === 0) {                
	            (($(this).popover('destroy').data('bs.popover')||{}).inState||{}).click = false  // fix for BS 3.3.6

	        }     
	    });
	});
	
	

