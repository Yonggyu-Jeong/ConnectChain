<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" pageEncoding="UTF-8" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
	request.setCharacterEncoding("utf-8");
	int filecounter = 0;
	if(request.getParameter("addcnt")!= null) {
		filecounter = Integer.parseInt(request.getParameter("addcnt"));
	}
%>
 
<html>
<head>
	<title>파일 업로드 폼</title>
</head>
<body>
 
<form method="post" action="/nft/uploadMeadiaFile" enctype="multipart/form-data">
	<table border="1px">
		<tr>
			<td>파일업로드</td>
			<td><input type="file" name="files" multiple></td>
		</tr>
		<tr>
			<td><input type="submit" value="업로드"></td>
		</tr>
	</table>
</form>
</body>
</html>
