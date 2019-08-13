<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page session="false"%>
<!DOCTYPE html>
<html>
<head>
<title>게시판 목록</title>
</head>

<form action="regist" method="get">
	<body>
		<table border="1" width="880">
			<tr>
				<td width="77">
				<p align="center">글번호</p>
				</td>
				<td width="327">
					<p align="center">제목</p>
				</td>

				<td width="197">
					<p align="center">작성자</p>
				</td>

				<td width="155">
					<p align="center">작성일</p>
				</td>

				<td width="90">
					<p align="center">조회수</p>
				</td>

			</tr>
			<c:forEach items="${list}" var="boardVO">
				<tr>
					<td>${boardVO.bId}</td>
					<td><a href='/read?bId=${boardVO.bId}'>${boardVO.bTitle}</a></td>
					<td>${boardVO.bName}</td>
					<td><fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${boardVO.bDate}" /></td>
					<td><span class="badge bg-red">${boardVO.bHit}</span></td>
				</tr>
			</c:forEach>
		</table>

		<c:if test="${pageMaker.prev}">
			<a href="list2${pageMaker.makeQuery(pageMaker.startPage - 1) }">«</a>
		</c:if>

		<c:forEach begin="${pageMaker.startPage }" end="${pageMaker.endPage }" var="idx">
			<c:out value="${pageMaker.cri.page == idx?'':''}" />
			<a href="list2${pageMaker.makeQuery(idx)}">${idx}</a>
			</c:forEach>
			<c:if test="${pageMaker.next && pageMaker.endPage > 0}">
				<a href="list2${pageMaker.makeQuery(pageMaker.endPage +1) }"> » </a>
			</c:if> <br>
			<button type="submit">글쓰기</button>
	</body>
</form>
</html>