<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Parkhaus</title>
<script src='https://ccmjs.github.io/mkaul-components/parkhaus/versions/ccm.parkhaus-9.0.0.js'></script>
</head>
<body>
	<h1>Parkhaus</h1>
	<hr>
	<ccm-parkhaus-9-0-0
		name="P1"
		server_url="http://localhost:8080/Parkhaus/Parkhaus"
		client_categories='["any", "Frau", "Familie"]'
		extra_buttons='["Summe", "AVG", "Min/Max-Price"]'
	></ccm-parkhaus-9-0-0>
</body>
</html>