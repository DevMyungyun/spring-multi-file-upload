<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
<script type="text/javascript" src="js/jquery-2.1.3.min.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<style type="text/css">
.row{
	margin: 0px auto;
	width:700px;
}
</style>
<script type="text/javascript">
var fileIndex=0;
$(function(){
	/*
		click(function(){}) = onclick
		change(function(){}) = select onchange
		hover(function(){}) = onmouseover
		
		text()
		html()
		attr()
		append()
		remove()
	*/
	$('#add').click(function(){
		$('#fileView').append(
			'<tr id=f'+(fileIndex)+'>'
			+'<td width=20%>파일'+(fileIndex+1)+'</td>'
			+'<td width=80% align=left>'
			+'<input type=file name=files['+fileIndex+'] size=30'
			+'</td></tr>'
		);
		fileIndex=fileIndex+1;
	});
	$('#cancel').click(function(){
		$('#cancel').click(function(){
			$('#f'+(fileIndex-1)).remove();
			fileIndex=fileIndex-1;
			if(fileIndex<0)
				fileIndex=0;
		});
	});
});
</script>
</head>
<body>
	<div class="container">
		<div class="row">
			<center>
				<h3>글쓰기</h3>
			</center>
			<form action="insert_ok.do" method="post"
				enctype="multipart/form-data"
				modelAttribute="uploadForm"
			>
			<table class="table table-hover">
				<tr>
					<td width="20%" class="text-right">이름</td>
					<td width="80%" class="text-left">
						<input type="text" name=name size=15>
					</td>
				</tr>
				<tr>
					<td width="20%" class="text-right">제목</td>
					<td width="80%" class="text-left">
						<input type="text" name=subject size=45>
					</td>
				</tr>
				<tr>
					<td width="20%" class="text-right">내용</td>
					<td width="80%" class="text-left">
						<textarea rows="10" cols="50" name="content"></textarea>
					</td>
				</tr>
				<tr>
					<td width="20%" class="text-right">첨부파일</td>
					<td width="80%" class="text-left">
						<table class="table table-hover">
							<tr>
								<td class="text-right">
									<input type="button" class="btn btn-sm btn-info" id="add" value="추가">
									<input type="button" class="btn btn-sm btn-primary" id="cancel" value="취소">
								</td>
							</tr>
						</table>
						<table class="table table-hover" id="fileView">
						
						</table>
					</td>
				</tr>
				<tr>
					<td width="20%" class="text-right">비밀번호</td>
					<td width="80%" class="text-left">
						<input type="password" name="pwd" size="10">
					</td>
				</tr>
				<tr>
					<td colspan="2" class="text-center">
						<input type="submit" value="글쓰기" class="btn btn-sm btn-primary">
						<input type="button" value="취소" class="btn btn-sm btn-danger"
							onclick="javascript:history.back()" >
					</td>
				</tr>
			</table>
			</form>
		</div>
	</div>



</body>
</html>