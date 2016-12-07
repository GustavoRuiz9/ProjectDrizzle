
function displayLineChart(Data) {
							
							var myChart = new JSChart('graph', 'line');
							myChart.setDataArray([[10, 10],[15, 25],[20, 20],[25, 10]], 'blue');
							myChart.setDataArray([[10, 10],[10, 20],[15, 25],[20, 10]], 'green');
							myChart.setDataArray([[10, 25],[10, 10],[15, 15],[20, 10]], 'gray');
							myChart.setSize(600, 400);
							myChart.setAxisValuesNumberY(17);
							myChart.setAxisValuesNumberX(17);
							myChart.setIntervalStartX(8);
							myChart.setIntervalStartY(8);
							//myChart.setIntervalEndY(200);
							myChart.setLabelY([10, 'Rain']);
							myChart.setLabelY([15, 'Storm']);
							myChart.setLabelY([20, 'Sunny']);
							myChart.setLabelY([25, 'Temper']);
							myChart.setGrid(false);
							myChart.setTitle("Clima");

							//myChart.setLabelX([0, ' ']);
							myChart.setLabelX([10, 'Madrugada']);
							myChart.setLabelX([15, 'Mañana']);
							myChart.setLabelX([20, 'Tarde']);
							myChart.setLabelX([25, 'Noche']);
							
							myChart.setShowXValues(false);
							myChart.setTitleColor('#454545');
							myChart.setAxisValuesColor('#454545');
							myChart.setLineColor('#A4D314', 'green');
							myChart.setLineColor('#BBBBBB', 'gray');
							myChart.setLineColor('#FFFFFF', 'white');
							myChart.setShowYValues(false);
							myChart.setShowXValues(false);
							myChart.setTooltip([1,' ']);
							myChart.setTooltip([2,' ']);
							myChart.setTooltip([3,' ']);
							myChart.setTooltip([4,' ']);
							myChart.setTooltip([5,' ']);
							myChart.setTooltip([6,' ']);
							myChart.setTooltip([7,' ']);
							myChart.setTooltip([8,' ']);
							myChart.setTooltip([9,' ']);
							myChart.setTooltip([10,' ']);
							myChart.setTooltip([11,' ']);
							myChart.setTooltip([12,' ']);
							myChart.setTooltip([13,' ']);
							myChart.setTooltip([14,' ']);
							myChart.setTooltip([15,' ']);
							myChart.setTooltip([16,' ']);
							myChart.setTooltip([17,' ']);
							myChart.setTooltip([18,' ']);
							myChart.setTooltip([19,' ']);
							myChart.setTooltip([20,' ']);
							myChart.setTooltip([21,' ']);
							myChart.setTooltip([22,' ']);
							myChart.setTooltip([23,' ']);
							myChart.setTooltip([24,' ']);
							myChart.setTooltip([25,' ']);
							myChart.setFlagColor('#9D16FC');
							myChart.setFlagRadius(4);
							myChart.setAxisPaddingRight(100);
							myChart.setLegendShow(true);
							myChart.setAxisNameX(' ');
							myChart.setAxisNameY(' ');
							myChart.setLegendPosition(490, 80);
							myChart.setLegendForLine('blue', 'Comuna1');
							myChart.setLegendForLine('green', 'Comuna2');
							myChart.setLegendForLine('gray', 'Comuna3');
							
							myChart.draw();
					    
					  }

function ConsultaEstadiscticas(){
	$.ajax({
        url: "constaGrafica.html",
        type: "POST",
        dataType: "html",
        data:data,
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