/*document.getElementById("BtnAjustes").onclick = establecerAjustes;
document.getElementById("ControlAjustes").onclick = showSettings;
*/

function displayLineChart(Data) {
	
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
								myChart.setLineColor('#0000FF', 'azul');
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
				displayLineChart(response);
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
	
	
	dst='<img src="data:image/jpg;base64,'+dato[0].profile+'" class="online img-circle" alt="User Image" style="width: 90px; height: 90px;">'+
		'<p align="center">' +dato[0].nombre_author+'</p>'+
		'<medium align="center">'+ new Date(fecha).toLocaleDateString("es-ES", options)+'</medium>&nbsp;'+
		'<span class="glyphicon glyphicon-star" style="color:' +rp +'"></span>';
	
	
		$('#popoveUser'+idPb[1]).popover({
		        title:dst,
		        content : "<strom>Tel: </strom><p>"+dato[0].number_phone+"</p>",
		        html: true
		    }); 
}

$('html').on('click', function(e) {
	  if (typeof $(e.target).data('original-title') == 'undefined' &&
	     !$(e.target).parents().is('.popover.in')) {
	    $('[data-original-title]').popover('hide');
	  }
	});

	$(document).ready(function(){

	    $('.btn').popover();

	    $('.btn').on('click', function (e) {
	        $('.btn').not(this).popover('hide');
	    });


	});
	
	$(document).on('click', function (e) {
	    $('[data-toggle="popover"],[data-original-title]').each(function () {
	        //the 'is' for buttons that trigger popups
	        //the 'has' for icons within a button that triggers a popup
	        if (!$(this).is(e.target) && $(this).has(e.target).length === 0 && $('.popover').has(e.target).length === 0) {                
	            (($(this).popover('hide').data('bs.popover')||{}).inState||{}).click = false  // fix for BS 3.3.6

	        }     
	    });
	});

