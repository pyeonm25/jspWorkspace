<%@page import="test.MyFile"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
   String ctx = request.getContextPath();
%>
    <h2>Image Upload</h2>
    <form action="<%=ctx%>/upload.do" method="post" enctype="multipart/form-data">
        Select image to upload:
        <input type="file" name="uploadFile">
        <input type="submit" value="Upload Image" name="submit">
    </form>
    
    
    <% if(request.getAttribute("file")!= null) {
	MyFile file = (MyFile)request.getAttribute("file");
    
    %>
      <h2> file = <%= file %> </h2>
      
         <img src="uploads/<%= file.getSfileName() %>" id="photo"/>
      
    <% } %>
</body>
</html>