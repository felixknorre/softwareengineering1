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
	
	</style>
<script src='https://ccmjs.github.io/mkaul-components/parkhaus/versions/ccm.parkhaus-9.0.0.js'></script>
</head>
<body>
	<h1>Parkhaus</h1>
	<hr>
	<ccm-parkhaus-9-0-0
		name="P1"
		server_url="http://localhost:8080/mk_se1_ss20_team3/Parkhaus"
		client_categories='["any", "Frau", "Familie"]'
		extra_buttons='["Summe", "AVG", "Min/Max-Price"]'
		extra_charts='["Chart", "FamilyChart"]'
	></ccm-parkhaus-9-0-0>
</body>
</html>