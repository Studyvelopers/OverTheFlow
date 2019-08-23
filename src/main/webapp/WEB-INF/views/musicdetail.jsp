<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
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
	
	<button onClick="location.href='/music/create'">음악 정보 추가</button>
	<button>음악 정보 수정</button>
	<button>음악 정보 삭제</button>
	<button>좋아요 목록 보기</button>
</body>
</html>