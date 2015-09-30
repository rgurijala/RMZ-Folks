<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html lang="us">
<head>
	<meta charset="utf-8">
	<title>jQuery UI Example Page</title>
	<link href="/RMZFlks/resources/css/jquery-ui.css" rel="stylesheet">
	  
<script src="/RMZFlks/resources/js/jquery-1.9.1.js"></script>
<script src="/RMZFlks/resources/js/jquery-migrate-1.2.1.js"></script>	  
<script src="/RMZFlks/resources/js/moment.min.js"></script>

<script src="/RMZFlks/resources/js/jquery.flot.js"></script>
<script src="/RMZFlks/resources/js/jquery.flot.time.js"></script>

<script src="/RMZFlks/resources/js/jquery-ui.js"></script>






<script src="/RMZFlks/resources/js/jquery.flot.axislabels.js"></script>
<script src="/RMZFlks/resources/js/jquery.flot.canvas.js"></script>
<script src="/RMZFlks/resources/js/justgage-1.1.0.js"></script>
<script src="/RMZFlks/resources/js/raphael-2.1.4.min.js"></script>
<script>
$(document).ready(function(){
	var data = '${customerList}';
	var from = new Date();
	var fmnumdate = from.getDate();
	fmnumdate = fmnumdate - 3;
	var fmnummonth = from.getDate();
	var fmnumyear = from.getFullYear();
	var loadfromdt = new Date();
	loadfromdt.setDate(fmnumdate);
	loadfromdt.setFullYear(fmnumyear, fmnummonth+1, fmnumdate);
	console.log(loadfromdt);
	
	var loadtodt = new Date();
	
	var datas = data.split("&");
	console.log("response-"+datas[0]);
	console.log("response1-"+datas[1]);
	//var chartdata = datas[0];
	var chartdata = jQuery.parseJSON(datas[0]);
	$( "#tabs" ).tabs();
	$("#tabs").tabs({
	    activate: function (event, ui) {
	        var active = $('#tabs').tabs('option', 'active');
	        if('#Soban' == $("#tabs ul>li a").eq(active).attr("href")){
	        	 
	        	 window.location.href ="soban.htm";
	        	 
	        }if('#Babu' == $("#tabs ul>li a").eq(active).attr("href")){
	        	 
	        	 window.location.href ="babu.htm";
	        	 
	        }

	    }
	}

	);
	$("#tabs").tabs({
		active :2
		
		});
	
	var options = {
			series: {
	            bars: {
	                show: true,
	                barWidth: 12*24*60*60*25,
	                lineWidth: 0,
	                order: 1,
	                color:'#4A55F1',
	                fillColor: '#4A55F1'
	            }
	        },
			
		     
			xaxis: {
				mode: "time",
				minTickSize: [1, "day"],
				min: (new Date(2015, 8, 15)).getTime(),
				max: (new Date(2015, 8, 30)).getTime(),
				timeformat: "%d-%b"
			},
		     grid:{
		    	 margin:{
		    		 bottom:50
		    	 },
		    	 hoverable:true,
		    	 clickable:false,
		    	 borderWidth:2,
		    	 backgroundColor:{colors:["#FFFFFF","#EDF5FF"]}
		     }
		 };
	 $.plot($('#datausageholderrama'), chartdata, options);
	 
	 var previousPoint = null;
	 $("#datausageholderrama").bind("plothover", function (event, pos, item) 
	 {

	     if (item) 
	     {
	         if (previousPoint != item.dataIndex) 
	         {

	             previousPoint = item.dataIndex;

	             $("#tooltip").remove();
	             var x = item.datapoint[0],
	             y = item.datapoint[1],
	             d = new Date(x),
	             td = d.getFullYear() + "-" + d.getMonth() + "-" + d.getDay();
	            // document.getElementById("debug").innerHTML=x;
	             showTooltip(item.pageX, item.pageY, td + ": " + y);
	         }
	     } 
	     else 
	     {
	         $("#tooltip").remove();
	         previousPoint = null;            
	     }
	 });
	 
	 function showTooltip(x, y, contents) {
	     $("<div id='tooltip'>" + contents + "</div>").css({
	         position: "absolute",
	         display: "none",
	         top: y + 5,
	         left: x + 5,
	         border: "1px solid #fdd",
	         padding: "2px",
	         "background-color": "#fee",
	         opacity: 0.80
	     }).appendTo("body").fadeIn(200);
	 };
	 
	 var gaugecomponent = new JustGage({
		 parentNode:"gaugeholderrama",
		 width:400,
		 height:300,
		 title:"Usage %",
		 value : datas[1],
		 showInnerShadow:true,
		 showOpacity:0.1,
		 min:0,
		 max:100,
		 decimals:2,
		 gaugeWidthScale:0.5,
		 label:"Usage/Allowance",
		 customSectors:[{
			 color : "#91FF6C",
			 lo:0,
			 hi:74.99
		 },{
			 color : "#ED8A47",
			 lo:75,
			 hi:89.99 
		 },{
			 color : "#FF0D05",
			 lo:90,
			 hi:500
		 }],
		 startAnimationTime:3000,
		 refreshAnimationTime:3000
	 });
});
</script>
<style>
.ui-widget-header {
    border: 1px solid #b00000;
    background: #b00000;
    background-image:none;
} 

/* tab heading text color */
.ui-state-active a, .ui-state-active a:link, .ui-state-active a:visited {
    color: #b00000;
}   
</style>
</head>
<body>
<div id="header">
	
	<div style="width:45%;float:left;text-align:left;"><img src="/RMZFlks/resources/css/images/verizonnewicon.png"></div>
	<div style="width:50%;float:left;"><h2 class="demoHeaders">Potential Churn Customers</h2></div>
</div>
<div style="clear:both;"></div>
<div id="tabs">
	<ul>
	
	
		<li><a href="#Babu">956-600-4545</a></li>
		 
		
	
		<li><a href="#Soban">944-349-3932</a></li>
		 
		
	
		<li><a href="#Rama">805-671-0965</a></li>
		 
		
	</ul>
	
	<div id="Babu"> Customer Name: Babu</div>
 	
	<div id="Soban"> Customer Name: Soban</div>
 	
	<div id="Rama" style="width:1800px;height:800px;"> Customer Name: Rama
	<div id="datausageholderrama" style="width:50%;height:50%;"></div>
	<div id="gaugeholderrama" style="margin-left:1200px;margin-top:-380px;width:40%;height:40%;"></div>
	</div>
 	
</div>

</body>
</html>