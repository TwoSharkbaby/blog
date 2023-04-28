<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="layout/header.jsp"%>


<div class="container mt-5">

	<c:forEach var="board" items="${boards.content}">

		<div class="card mb-3">
			<div class="card-body">
				<h5 class="card-title">${board.title}</h5>
				<a href="/board/${board.id}" class="btn btn-primary">상세보기</a>
			</div>
		</div>

	</c:forEach>

	<nav aria-label="...">
		<ul class="pagination justify-content-center">
			<c:choose>
				<c:when test="${boards.first}">
					<li class="page-item disabled"><a class="page-link">Previous</a></li>
				</c:when>
				<c:otherwise>
					<li class="page-item"><a class="page-link" href="?page=${boards.number-1}">Previous</a></li>
				</c:otherwise>
			</c:choose>

			<li class="page-item"><a class="page-link" href="#">1</a></li>
			<li class="page-item active" aria-current="page"><a class="page-link" href="#">2</a></li>
			<li class="page-item"><a class="page-link" href="#">3</a></li>

			<c:choose>
				<c:when test="${boards.last}">
					<li class="page-item disabled"><a class="page-link">Next</a></li>
				</c:when>
				<c:otherwise>
					<li class="page-item"><a class="page-link" href="?page=${boards.number+1}">Next</a></li>
				</c:otherwise>
			</c:choose>

			
		</ul>
	</nav>

</div>

<%@ include file="layout/footer.jsp"%>