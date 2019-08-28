<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<form id="music" action="/music/modify" method="post" accept-charset="utf-8">
		아이디 : <input type="text" name="id" value="${music.id}"/> <br>
		제목 : <input type="text" name="title" placeholder="${music.title}"/><br>
		재생시간 : <input type="text" name="playtime" placeholder="${music.playtime}"/><br>
		소개글 : <input type="text" name="description" placeholder="${music.description}"/><br>
		공개 여부 : <input type="text" name="visibility" placeholder="${music.visibility}"/><br>
		다운로드 허용 여부 : <input type="text" name="downloadable" placeholder="${music.downloadable}"/><br>
		재생횟수 : <input type="text" name="playCount" placeholder="${music.playCount}"/><br>
		카테고리 아이디 : <input type="text" name="categoryId" placeholder="${music.categoryId}"/><br>
		회원 아이디 : <input type="text" name="memberId" placeholder="${music.memberId}"/><br>
		회원 닉네임 : <input type="text" name="memberNickname" placeholder="${music.memberNickname}"/><br>
		<button type="submit">추가하기</button>
	</form>
</body>
</html>