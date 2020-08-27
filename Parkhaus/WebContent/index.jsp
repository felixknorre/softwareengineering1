<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Parkhaus</title>
	<style>
	h1 {
    text-align:center;
	}
	a:link, a:visited {
	  background-color: black;
	  color: white;
	  padding: 14px 25px;
	  text-align: center;
	  text-decoration: none;
	  display: inline-block;
	}
	
	a:hover, a:active {
	  background-color: grey;
	}
	
	.pagelinks {
	text-align: center;
	}
	
	.pagelinks a{
     display: inline-block;
     margin-left: 3px;
 	}
	
	</style>
<script src='https://ccmjs.github.io/mkaul-components/parkhaus/versions/ccm.parkhaus-9.1.3.js'></script>
</head>
<body>
	<h1>Parkhaus</h1>
	<hr>
	<div class="pagelinks">
		<!-- http://sepp-test.inf.h-brs.de:8080/mk_se1_ss20_team3/  -->
		<!--  http://localhost:8080/Parkhaus/ -->
		<a href="http://sepp-test.inf.h-brs.de:8080/mk_se1_ss20_team3/">Parkhaus</a>
		<!-- http://sepp-test.inf.h-brs.de:8080/mk_se1_ss20_team3/Preisliste  -->
		<!-- http://localhost:8080/Parkhaus/Preisliste  -->
		<a href="http://sepp-test.inf.h-brs.de:8080/mk_se1_ss20_team3/Preisliste">Preisliste</a>
	</div>
	<hr>
	<!-- dev:  http://localhost:8080/Parkhaus/Parkhaus-->
	<!-- deploy: http://sepp-test.inf.h-brs.de:8080/mk_se1_ss20_team3/Parkhaus -->
	<ccm-parkhaus-9-1-3
		name="P1"
		server_url="http://sepp-test.inf.h-brs.de:8080/mk_se1_ss20_team3/Parkhaus"
		client_categories='["any", "Frau", "Familie"]'
		extra_buttons='["Summe", "AVG", "Min/Max-Price", "Undo", "Redo"]'
		extra_charts='["Chart", "FamilyChart"]'
		hide_table
	></ccm-parkhaus-9-1-3>
</body>
</html>