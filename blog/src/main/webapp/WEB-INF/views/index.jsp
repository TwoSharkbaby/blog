<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="layout/header.jsp"%>

<div class="container mb-3">

	<c:forEach var="board" items="${board.content}">
		<div class="card mb-3">
			<div class="card-body">
				<h5 class="card-title">
					<c:out value="${board.title}"></c:out>
				</h5>
				<a href="/board/${board.id}" class="btn btn-primary">상세보기</a>
			</div>
		</div>
	</c:forEach>

	<nav aria-label="...">
		<ul class="pagination justify-content-center">
			<c:choose>
				<c:when test="${board.first}">
					<li class="page-item disabled"><a class="page-link"
						href="?page=<c:out value='${board.number-1}'/>">Previous</a></li>
				</c:when>
				<c:otherwise>
					<li class="page-item"><a class="page-link"
						href="?page=<c:out value='${board.number-1}'/>">Previous</a></li>
				</c:otherwise>
			</c:choose>

			<c:choose>
				<c:when test="${board.last}">
					<li class="page-item disabled"><a class="page-link"
						href="?page=<c:out value='${board.number+1}'/>">Next</a></li>
				</c:when>
				<c:otherwise>
					<li class="page-item"><a class="page-link"
						href="?page=<c:out value='${board.number+1}'/>">Next</a></li>
				</c:otherwise>
			</c:choose>
		</ul>
	</nav>

</div>

<%@ include file="layout/footer.jsp"%>