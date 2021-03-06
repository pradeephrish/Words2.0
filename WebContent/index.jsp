<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style>
#result {
	height:20px;
	font-size:16px;
	font-family:Arial, Helvetica, sans-serif;
	color:#333;
	padding:5px;
	margin-bottom:10px;
	background-color:#FFFF99;
}
#country{
	padding:3px;
	border:1px #CCC solid;
	font-size:17px;
}
.suggestionsBox {
	position: absolute;
	left: 20px;
	top:70px;
	margin: 26px 0px 0px 0px;
	width: 200px;
	padding:0px;
	background-color: #000;
	border-top: 3px solid #000;
	color: #fff;
}
.suggestionList {
	margin: 0px;
	padding: 0px;
	padding-left: 10px;
}
.suggestionList ul li {
	list-style:none;
	margin-left: 10px;
	padding: 6px;
	border-bottom:1px dotted #666;
	cursor: pointer;
}
.suggestionList ul li:hover {
	background-color: #FC3;
	color:#000;
}
ul {
	font-family:Arial, Helvetica, sans-serif;
	font-size:11px;
	color:#FFF;
	padding:0;
	margin:0;
}

.load{
background-image:url(loader.gif);
background-position:right;
background-repeat:no-repeat;
}

#suggest {
	position:relative;
}

</style>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>GREWordsOnline.com</title>
 <script src="jquery.min.js" type="text/javascript"></script>
 <script>
function suggest(inputString){
		if(inputString.length == 0) {
			$('#suggestions').fadeOut();
		} else {
		$('#country').addClass('load');
			$.post("servlet/getWordList", {queryString: ""+inputString+""}, function(data){
				if(data.length >0) {
					$('#suggestions').fadeIn();
					$('#suggestionsList').html(data);
					$('#country').removeClass('load');
				}
			});
		}
	}

	function fill(thisValue) {
		$('#country').val(thisValue);
		setTimeout("$('#suggestions').fadeOut();", 300);
		setTimeout("fetch();", 302);
		
	}

</script>
<script type="text/javascript">
var a1,a2,a3,a4,word ="";
try{
<%
	if(session.getAttribute("1")!=null){
	out.println("a1 = '"+session.getAttribute("1")+"';");
	out.println("a2 = '"+session.getAttribute("2")+"';");
	out.println("a3 = '"+session.getAttribute("3")+"';");
	
	
	}
	
%>
}catch(e){
	
}
</script>
<script type="text/javascript">

function fetch()
{
	var thisValue = $('#country').val();
	$.post("servlet/fetchWord", {queryString: ""+thisValue+""}, function(data){
		window.location.reload();
	});
}

function prev(){
	post("p",1);
}
function next(){
	post("n",1);
}
function callFromBase(data)
{
	post(data,2);	
}

function post(dataString,ch)
{
	if(ch==1)
	dataString = "in="+dataString;
	else
	dataString="base="+dataString;
			
$(document).ready(function(){
$.ajax({
    type: "POST",
    url: "servlet/getWord",
    data: dataString,
    success: function(data) {
    	try{
    		window.location.reload();
    	}catch (e) {
			alert("error");
		}
    }
   });	
});}
</script>
</head>
<body style="background: #EEE; font-size: 20px; margin: 0px;" >
<div id="fb-root"></div>
<script>(function(d, s, id) {
  var js, fjs = d.getElementsByTagName(s)[0];
  if (d.getElementById(id)) return;
  js = d.createElement(s); js.id = id;
  js.src = "//connect.facebook.net/en_US/all.js#xfbml=1";
  fjs.parentNode.insertBefore(js, fjs);
}(document, 'script', 'facebook-jssdk'));</script>
<div style="width: 100%; height: 30px; background: #5EE293; padding-left: 15px; padding-top: 6px;" align="left"  ><font style="font-family: 'Segoe UI', Tahoma, sans-serif; font-weight: bold; ">GREWordsOnline.com</font></div>
<div style="width: 20%; height: 40px; background: #FFF; margin: 20px; float: left; padding: 20px;" ><input type="text" size="25" value="" id="country" onkeyup="suggest(this.value);" onblur="fill();"   class=""  style="width: 100%; height: 100%; font-size: 15px; font-weight: bold;" />
     
      <div class="suggestionsBox" id="suggestions" style="display: none;"> <img src="arrow.png" style="position: relative; top: -12px; left: 30px;" alt="upArrow" />
        <div class="suggestionList" id="suggestionsList"> &nbsp; </div>
      </div>
</div>
<a onclick="prev();" style="text-decoration: none;" ><div style="width: 10%; height: 40px; background: #FFF; margin: 20px; float: left; padding: 20px;" >Prev</div></a>
<a onclick="next();" style="text-decoration: none;"><div style="width: 10%; height: 40px; background: #FFF; margin: 20px; float: left; padding: 20px;">Next</div></a>
<div class="fb-like" style="margin-top: 20px;" data-href="https://www.facebook.com/GreWordsOnline" data-send="true" data-width="450" data-show-faces="true"></div>
<div style="width: 100%; height: 120px;"></div>
<div style="width: 100%; height: 80px; background: #E2D9F7; padding: 5px; padding-top: 10px;" align="center"  id="1"></div>
<div style="width: 100%; height: 80px; background: #DF7F7F; margin-top: 10px; padding: 5px; padding-top: 10px;" align="center"  id="2"></div>
<div style="width: 100%; height: 80px; background: #7CA1FF; margin-top: 10px; padding: 5px; padding-top: 10px;" align="center"  id="3"></div>

<div style="width: 2%; 40px; background: #FFF; margin-top:20px; padding-top:20px; margin: 10px; float: left; padding: 10px;" onclick="callFromBase(this.innerHTML);" onclick="callFromBase(this.innerHTML);">A</div>
<div style="width: 2%; 40px; background: #FFF; margin-top:20px; padding-top:20px; margin: 10px; float: left; padding: 10px;" onclick="callFromBase(this.innerHTML);" onclick="callFromBase(this.innerHTML);">B</div>
<div style="width: 2%; 40px; background: #FFF; margin-top:20px; padding-top:20px; margin: 10px; float: left; padding: 10px;" onclick="callFromBase(this.innerHTML);">C</div>
<div style="width: 2%; 40px; background: #FFF; margin-top:20px; padding-top:20px; margin: 10px; float: left; padding: 10px;" onclick="callFromBase(this.innerHTML);">D</div>
<div style="width: 2%; 40px; background: #FFF; margin-top:20px; padding-top:20px; margin: 10px; float: left; padding: 10px;" onclick="callFromBase(this.innerHTML);">E</div>
<div style="width: 2%; 40px; background: #FFF; margin-top:20px; padding-top:20px; margin: 10px; float: left; padding: 10px;" onclick="callFromBase(this.innerHTML);">F</div>
<div style="width: 2%; 40px; background: #FFF; margin-top:20px; padding-top:20px; margin: 10px; float: left; padding: 10px;" onclick="callFromBase(this.innerHTML);">G</div>
<div style="width: 2%; 40px; background: #FFF; margin-top:20px; padding-top:20px; margin: 10px; float: left; padding: 10px;" onclick="callFromBase(this.innerHTML);">H</div>
<div style="width: 2%; 40px; background: #FFF; margin-top:20px; padding-top:20px; margin: 10px; float: left; padding: 10px;" onclick="callFromBase(this.innerHTML);">I</div>
<div style="width: 2%; 40px; background: #FFF; margin-top:20px; padding-top:20px; margin: 10px; float: left; padding: 10px;" onclick="callFromBase(this.innerHTML);">J</div>
<div style="width: 2%; 40px; background: #FFF; margin-top:20px; padding-top:20px; margin: 10px; float: left; padding: 10px;" onclick="callFromBase(this.innerHTML);">K</div>
<div style="width: 2%; 40px; background: #FFF; margin-top:20px; padding-top:20px; margin: 10px; float: left; padding: 10px;" onclick="callFromBase(this.innerHTML);">L</div>
<div style="width: 2%; 40px; background: #FFF; margin-top:20px; padding-top:20px; margin: 10px; float: left; padding: 10px;" onclick="callFromBase(this.innerHTML);">M</div>
<div style="width: 2%; 40px; background: #FFF; margin-top:20px; padding-top:20px; margin: 10px; float: left; padding: 10px;" onclick="callFromBase(this.innerHTML);">N</div>
<div style="width: 2%; 40px; background: #FFF; margin-top:20px; padding-top:20px; margin: 10px; float: left; padding: 10px;" onclick="callFromBase(this.innerHTML);">O</div>
<div style="width: 2%; 40px; background: #FFF; margin-top:20px; padding-top:20px; margin: 10px; float: left; padding: 10px;" onclick="callFromBase(this.innerHTML);">P</div>
<div style="width: 2%; 40px; background: #FFF; margin-top:20px; padding-top:20px; margin: 10px; float: left; padding: 10px;" onclick="callFromBase(this.innerHTML);">Q</div>
<div style="width: 2%; 40px; background: #FFF; margin-top:20px; padding-top:20px; margin: 10px; float: left; padding: 10px;" onclick="callFromBase(this.innerHTML);">R</div>
<div style="width: 2%; 40px; background: #FFF; margin-top:20px; padding-top:20px; margin: 10px; float: left; padding: 10px;" onclick="callFromBase(this.innerHTML);">S</div>
<div style="width: 2%; 40px; background: #FFF; margin-top:20px; padding-top:20px; margin: 10px; float: left; padding: 10px;" onclick="callFromBase(this.innerHTML);">T</div>
<div style="width: 2%; 40px; background: #FFF; margin-top:20px; padding-top:20px; margin: 10px; float: left; padding: 10px;" onclick="callFromBase(this.innerHTML);">U</div>
<div style="width: 2%; 40px; background: #FFF; margin-top:20px; padding-top:20px; margin: 10px; float: left; padding: 10px;" onclick="callFromBase(this.innerHTML);">V</div>
<div style="width: 2%; 40px; background: #FFF; margin-top:20px; padding-top:20px; margin: 10px; float: left; padding: 10px;" onclick="callFromBase(this.innerHTML);">W</div>
<div style="width: 2%; 40px; background: #FFF; margin-top:20px; padding-top:20px; margin: 10px; float: left; padding: 10px;" onclick="callFromBase(this.innerHTML);">X</div>
<div style="width: 2%; 40px; background: #FFF; margin-top:20px; padding-top:20px; margin: 10px; float: left; padding: 10px;" onclick="callFromBase(this.innerHTML);">Y</div>
<div style="width: 2%; 40px; background: #FFF; margin-top:20px; padding-top:20px; margin: 10px; float: left; padding: 10px;" onclick="callFromBase(this.innerHTML);">Z</div>
</div>
<script type="text/javascript">
document.getElementById("1").innerHTML = a1;
document.getElementById("2").innerHTML = a2;
document.getElementById("3").innerHTML = a3;

$('#country').val(a1);
<%
if(session.getAttribute("1")==null){
	out.println("post(\"n\",1);");
}
%>
</script>
<script type="text/javascript">

  var _gaq = _gaq || [];
  _gaq.push(['_setAccount', 'UA-34086048-1']);
  _gaq.push(['_trackPageview']);

  (function() {
    var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;
    ga.src = ('https:' == document.location.protocol ? 'https://ssl' : 'http://www') + '.google-analytics.com/ga.js';
    var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);
  })();

</script>
<script id="_webengage_script_tag" type="text/javascript">
  window.webengageWidgetInit = window.webengageWidgetInit || function(){
    webengage.init({
      licenseCode:"~71680ab2"
    }).onReady(function(){
      webengage.render();
    });
  };

  (function(d){
    var _we = d.createElement('script');
    _we.type = 'text/javascript';
    _we.async = true;
    _we.src = (d.location.protocol == 'https:' ? "//ssl.widgets.webengage.com" : "//cdn.widgets.webengage.com") + "/js/widget/webengage-min-v-3.0.js";
    var _sNode = d.getElementById('_webengage_script_tag');
    _sNode.parentNode.insertBefore(_we, _sNode);
  })(document);
</script>	
</body>
</html>