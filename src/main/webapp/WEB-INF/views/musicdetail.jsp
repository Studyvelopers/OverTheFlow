<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script>
	$(document).ready(function(){
		$("#delete-btn").click(function(){
			$('#data').attr('action','/music/delete');
			$('#data').attr('method','post');
			$('#data').submit();
		});
		
		$("#likelist-btn").click(function(){
			$('#searchInfo').attr('action','/music/like/list/1');
			$('#searchInfo').attr('method','get');
			$('#searchInfo').submit();
		});
	});
</script>
<body>
	<h2>ID : ${music.id}</h2>
	<h2>TITLE : ${music.title}</h2>
	<h2>PLAYTIME : ${music.playtime}</h2>
	<h2>REGISTERDATE : ${music.registerDate}</h2>
	<h2>DESCRIPTION : ${music.description}</h2>
	<h2>VISIBILITY : ${music.visibility}</h2>
	<h2>DOWNLOADABLE : ${music.downloadable}</h2>
	<h2>PLAYCOUNT : ${music.playCount}</h2>
	<h2>CATEGORYID : ${music.categoryId}</h2>
	<h2>MEMBERID : ${music.memberId}</h2>
	<h2>MEMBERNICKNAME : ${music.memberNickname}</h2>
	<c:forEach var="tag" items="${music.tags}" varStatus="status">
		<h2>TAG : ${tag}</h2>
	</c:forEach>
	
	<form id="data" action="" method="post" accept-charset="utf-8">
		<input type="text" name="id" value="${music.id}"/> <br>
		<input type="text" name="title" value="${music.title}"/><br>
		<input type="text" name="playtime" value="${music.playtime}"/><br>
		<input type="text" name="description" value="${music.description}"/><br>
		<input type="text" name="visibility" value="${music.visibility}"/><br>
		<input type="text" name="downloadable" value="${music.downloadable}"/><br>
		<input type="text" name="playCount" value="${music.playCount}"/><br>
		<input type="text" name="categoryId" value="${music.categoryId}"/><br>
		<input type="text" name="memberId" value="${music.memberId}"/><br>
		<input type="text" name="memberNickname" value="${music.memberNickname}"/><br>	
	</form>
	
	<br>
	<h2>서치인포</h2>
	
	<form id="searchInfo" action="" method="get" accept-charset="utf-8">
		<input type="text" name="perPageCount" placeholder="perPageCount"/> <br>
		<input type="text" name="sortionOption" placeholder="sortionOption"/> <br>
		<input type="text" name="ordering" placeholder="ordering"/> <br>
	</form>
	
	<button onClick="location.href='/music/create'">음악 정보 추가</button>
	<button onClick="location.href='/music/modify/${music.id}'">음악 정보 수정</button>
	<button id="delete-btn" type="button">음악 정보 삭제</button>
	<button id="likelist-btn">좋아요 목록 보기</button>
</body>
</html>