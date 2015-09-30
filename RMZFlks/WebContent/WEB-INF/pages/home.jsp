<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html lang="us">
<head>
	<meta charset="utf-8">
	<title>jQuery UI Example Page</title>
	<link href="/RMZFlks/resources/css/jquery-ui.css" rel="stylesheet">
	 <link href="/RMZFlks/resources/css/dataTables.jqueryui.min.css" rel="stylesheet"> 
	
	
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
 
<script src="/RMZFlks/resources/js/jquery.dataTables.min.js"></script>
<script src="/RMZFlks/resources/js/dataTables.jqueryui.min.js"></script>



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
	//var chartdata = jQuery.parseJSON('[{\"label\":\"Usage Date\",\"data\":[[1442786400000,0.50], [1442872800000,0.25], [1442959200000,0.67], [1443045600000,0.86], [1443132000000,0.43], [1443218400000,0.76], [1443304800000,0.23], [1443391200000,0.33] ],\"stack\":false,\"color\":\"RGB(52,149,253)\",\"bars\":{\"show\":true,\"barWidth\":3600000,\"horizontal\":false,\"fill\":true,\"fillColor\":\"RGB(52,149,253)\"}}]');
	$("#tabs").tabs({
	    activate: function (event, ui) {
	        var active = $('#tabs').tabs('option', 'active');
	        if('#Soban' == $("#tabs ul>li a").eq(active).attr("href")){
	        	 
	        	 window.location.href ="soban.htm";
	        	 
	        }if('#Rama' == $("#tabs ul>li a").eq(active).attr("href")){
	        	 
	        	 window.location.href ="rama.htm";
	        	 
	        }

	    }
	}

	);
	//$( "#tabs" ).tabs({ event: "click"})  
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
	 $.plot($('#datausageholder'), chartdata, options);
	 
	 var previousPoint = null;
     $("#datausageholder").bind("plothover", function (event, pos, item) 
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
		 parentNode:"gaugeholder",
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
	 $("#divdeps").dialog({
		    autoOpen: false,
		    show: 'pop',
		    resizable: false,
		    stack: true,
		    height: 'auto',
		    width: 'auto',
		    modal: true
		}); 
		 $("#offers").dialog({
			    autoOpen: false,
			    show: 'pop',
			    resizable: false,
			    stack: true,
			    height: 'auto',
			    width: 'auto',
			    modal: true
			}); 
		 
		 $("#confirm").dialog({
			    autoOpen: false,
			    show: 'pop',
			    resizable: false,
			    stack: true,
			    height: 'auto',
			    width: 'auto',
			    modal: true
			});
		 $("#chounkbutton").click(function(){
			 $("#offers").dialog('close');
			 $("#divdeps").dialog('open');
			 $('#chunk').DataTable();
		 })	
		  $("#Offersbutton").click(function(){
			  $("#divdeps").dialog('close');
			 $("#offers").dialog('open');
			 $('#offerTab').DataTable();
		 })
		 $("#sendOfferId").click(function(){
			 var tst = $('input[type=radio][name=radiobutton]:checked').attr('id');
			
			 $("#offers").dialog('close');
			 $("#confirm").dialog('close');
			 $("#confirm").dialog('open');
			 $("#confirm").html(tst +" is sent to the customer!");
			 
		 })
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
.chunkbuttonClass{
background:#980000;
color:#FFF;
} 
</style>
<script>


</script>
</head>
<body>
<div id="header">
	
	<div style="width:45%;float:left;text-align:left;"><img src="/RMZFlks/resources/css/images/verizonnewicon.png"></div>
	<div style="width:50%;float:left;"><h2 class="demoHeaders">Potencial Chunk Customers</h2></div>
</div>
<div style="clear:both;"></div>
<div id="tabs">
	<ul>
	
	
		<li><a href="#Babu">956-600-4545</a></li>
		 
		
	
		<li><a href="#Soban">944-349-3932</a></li>
		 
		
	
		<li><a href="#Rama">805-671-0965</a></li>
		 
		
	</ul>
	
	<div id="Babu"  style="width:100%;height:350px;float:left;"> Babu
	
	<div id="datausageholder" style="width:50%;height:100%;float:left;"></div>
	<div id="gaugeholder" style="width:50%;height:350px;float:left;"></div>
	</div>
	<!-- <div id="Soban123"  style="width:44%;height:350px;float:left;"> Soban
	
	<div id="datausageholder" style="width:90%;height:100%;"></div>
	<div id="gaugeholder" style="margin-left:1200px;margin-top:-380px;width:40%;height:40%;"></div>
	</div> -->
 	
	<div id="Soban"> Soban</div>
 	
	<div id="Rama"> Rama</div>
 	
</div>
<div style="clear:both;"></div>
<div style="float:left;width:50%;text-align:right;padding-top:10px;"><input id="chounkbutton" class="chunkbuttonClass" type="button" value="View Details"> </div>
<div style="float:left;width:50%;text-align:left;padding-top:10px;"><input id="Offersbutton" class="chunkbuttonClass" type="button" value="Offers"> </div>
<div id="divdeps" style="display:none" title="Churn Data">
<table id='chunk'><thead><tr><th>Date</th> <th>Search String</th></tr></thead>
<tfoot><tr><th>Date</th><th>Search String</th></tr></tfoot>
<tbody>
<c:forEach items="${churesponse}" var="chrunk">
<tr><td width="20%">${chrunk.insertedDate}</td><td width="80%">${chrunk.searchString}</td></tr>
	</c:forEach>
	</tbody></table>
</div>
<div id="offers" style="display:none" title="Churn Data">
<table id='offerTab'><thead><tr><th>Offers</th> <th>Select</th></tr></thead>
<tfoot><tr><th>Offers</th><th>Select</th></tr></tfoot>
<tbody>
<c:forEach items="${offers}" var="offerBean">
<tr><td width="70%">${ offerBean.offer}</td><td width="30%"><input id="${ offerBean.offer}" name="radiobutton" class="chunkbuttonClass" type="radio" > </td></tr>
	</c:forEach>
	</tbody></table>
	<div><input id="sendOfferId" class="chunkbuttonClass" type="button" value="Send Offers"> </div>
</div>
<div id="confirm" style="display:none" title="Offer Sent"></div>
</body>
</html>