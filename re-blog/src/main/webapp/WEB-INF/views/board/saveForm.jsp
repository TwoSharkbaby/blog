<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp" %>


<div class="container mt-5">

	<form>
		<div class="mb-3">
			<label for="title" class="form-label">Title</label> <input type="text" class="form-control" id="title">
		</div>
		<div class="mb-3">
			<label for="content" class="form-label">Content</label> <textarea rows="5" class="form-control summernote" id="content"></textarea>
		</div>
	</form>
	<button id="btn-save" class="btn btn-primary">글쓰기 완료</button>

</div>

<script>
      $('.summernote').summernote({
        tabsize: 2,
        height: 300,
        toolbar: [
          ['style', ['style']],
          ['font', ['bold', 'underline', 'clear']],
          ['color', ['color']],
          ['para', ['ul', 'ol', 'paragraph']],
          ['table', ['table']],
          ['insert', ['link', 'picture', 'video']],
          ['view', ['fullscreen', 'codeview', 'help']]
        ]
      });
</script>

<script src="/js/board.js"></script>
<%@ include file="../layout/footer.jsp" %>