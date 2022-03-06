<%@ include file="common/header.jspf" %>
<%@ include file="common/navigation.jspf" %>
	<div class="container">
		<table class="table table-bordered table-striped table-hover table-condensed">
			<caption>Your todos are</caption>
			<thead>
				<tr>
					<th class="info">Description</th>
					<th class="info">Target Date</th>
					<th class="info">Is it Done?</th>
					<th class="info">Update</th>
					<th class="info">Delete</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${todos}" var="todo">
					<tr>
						<td>${todo.desc}</td>
						<td><fmt:formatDate value="${todo.targetDate}" pattern="dd/MM/yyyy"/></td>
						<td>${todo.done}</td>
						<td><a type="button" class="btn btn-success"
							href="/update-todo?id=${todo.id}">Update</a></td>
						<td><a type="button" class="btn btn-danger"
							href="/delete-todo?id=${todo.id}">Delete</a></td>

					</tr> 	
				</c:forEach>
			</tbody>
		</table>
		<div>
			<a class="btn btn-success" href="/add-todo">Add a Todo</a>
		</div>

		
	</div>
<%@ include file="common/footer.jspf" %>