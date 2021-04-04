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
				<h3>게시판 보기</h3>
			</center>
			<table class="table table-hover">
				<tr>
					<td class="text-center info" width="20%">번호</td>
					<td class="text-center" width="30%">${vo.no }</td>
					<td class="text-center info" width="20%">작성일</td>
					<td class="text-center" width="30%">
						<fmt:formatDate value="${vo.regdate }" pattern="yyyy-MM-dd"/>
					</td>
				</tr>
				<tr>
					<td class="text-center info" width="20%">이름</td>
					<td class="text-center" width="30%">${vo.name }</td>
					<td class="text-center info" width="20%">조회수</td>
					<td class="text-center" width="30%">${vo.hit }</td>
				</tr>
				<tr>
					<td class="text-center info" width="20%">제목</td>
					<td class="text-left" width="30%" colspan="3">${vo.subject }</td>
				</tr>
				<c:if test="${vo.filecount>0 }">
					<tr>
						<td class="text-center info" width="20%">첨부파일</td>
						<td class="text-left" width="30%" colspan="3">
							<ul>
							 	<c:forEach var="f" items="${files }">
									<li><a href="download.do?fn=${f }">${f }</a></li>
								</c:forEach>
							</ul>
					</tr>
				</c:if>
				<tr>
					<td colspan="4" align="left" valign="top" height="100">${vo.content }</td>
				</tr>
				<tr>
					<td colspan="4" class="text-right">
						<a href="#" class="btn btn-sm btn-info">수정</a>
						<a href="delete.do?no=${vo.no }" class="btn btn-sm btn-danger">삭제</a>
						<a href="list.do" class="btn btn-sm btn-success">목록</a>
					</td>
				</tr>
			</table>
		</div>
	</div>
		


















</body>
</html>