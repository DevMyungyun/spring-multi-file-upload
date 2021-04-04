<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
      <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
<style type="text/css">
.row{
	margin: 0px auto;
	width:700px;
}
</style>
</head>
<body>
	<div class="container">
		<div class="row">
			<center>
				<h3>게시판 목록</h3>
			</center>
			<table class="table table-hover">
				<tr>
					<td>
						<a href="insert.do" class="btn btn-sm btn-success">새글</a>
					</td>
				</tr>
			</table>
			<table class="table table-hover">
				<tr class="warning">
					<th width="10%" class="text-center">번호</th>
					<th width="45%" class="text-center">제목</th>
					<th width="15%" class="text-center">이름</th>
					<th width="20%" class="text-center">작성일</th>
					<th width="10%" class="text-center">조회수</th>
				</tr>
				<c:forEach var="vo" items="${list }">
					<tr>
						<td width="10%" class="text-center">${vo.no }</td>
						<td width="45%" class="text-left">
							<a href="content.do?no=${vo.no }">${vo.subject }</a>
						</td>
						<td width="15%" class="text-center">${vo.name }</td>
						<td width="20%" class="text-center">
							<fmt:formatDate value="${vo.regdate }" pattern="yyyy-mm-dd"/>
						</td>
						<td width="10%" class="text-center">${vo.hit }</td>
					</tr>
				</c:forEach>
			</table>
			
		</div>
	</div>

</body>
</html>