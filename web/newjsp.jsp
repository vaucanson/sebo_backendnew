<%-- 
    Document   : newjsp
    Created on : 13 juin 2018, 10:41:14
    Author     : mattar
--%>

<%@page import="java.io.InputStreamReader"%>
<%@page import="java.io.BufferedReader"%>
<%@page import="java.net.HttpURLConnection"%>
<%@page import="java.net.URL"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
     <%   URL url = new URL("http://172.16.153.29:8080/sebo_backendnew/api/ordermanager/getordersbystate/31");
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Accept", "application/json");
            
                
		if (conn.getResponseCode() != 200) {
                          
			throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());     
		}

		BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

                StringBuilder output = null;
                
                while ((output.append(br.readLine())) != null) {
			System.out.println(output);
		}
			
		conn.disconnect();
                %>
    </body>
</html>
