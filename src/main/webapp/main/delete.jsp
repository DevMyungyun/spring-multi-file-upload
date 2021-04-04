<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
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
			<form action="delete_ok.do" method="post">
			<table class="table table-hover">
				<tr>
					<td class="text-right" width="30%">비밀번호</td>
					<td class="text-left" width="70%">
						<input type="password" name="pwd" size="12">
						<input type="hidden" name="no" value="${no }">
					</td>
				</tr>
				<tr>
					<td class="text-center" colspan="2">
						<input type="submit" value="삭제" class="btn btn-sm btn-primary">
						<input type="button" value="취소" class="btn btn-sm btn-danger"
								onclick="javascript:history.back()">
					</td>
				</tr>
			</table>
			</form>
		</div>
	</div>

</body>
</html>