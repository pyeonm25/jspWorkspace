<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../../part/header.jsp"%>

<div class="inner">
	<h1>회원가입</h1>
	<form action="${ctx}/memberAdd.do" method="post" enctype="multipart/form-data" >
		<table>
		<tr>
				<td>아이디</td>
				<td class="input-group"><input type="text" name="id" id="id"
					autofocus required /> <input type="button" value="중복체크"
					id="checkId" class="btn-submit" /></td>
			</tr>
	     	<tr>
				<td>패스워드</td>
				<td><input type="password" name="pass" id="pass" required /></td>
			</tr>
			<tr>
				<td>이름</td>
				<td><input type="text" name="name" id="name" required /></td>
			</tr>
			<tr>
				<td>나이</td>
				<td><input type="number" name="age" id="age" required /></td>
			</tr>
			<tr>
				<td>이메일</td>
				<td><input type="text" name="email" id="email" required /></td>
			</tr>
			<tr>
				<td>전화번호</td>
				<td><input type="text" name="phone" id="phone" required /></td>
			</tr>
			<tr>
				<td>프로필 사진</td> <td> <input type="file" name="uploadFile" accept="image/*"></td>
			</tr>
		<tr>
				<td></td>
				<td class="btn-group">
					<button class="btn-submit" onclick="validCheck(form)">가입</button>
					<button class="btn-cancel">취소</button>
				</td>
			</tr>
		</table>

	</form>
	
</div>

<script src="${ctx}/js/insert.js"> </script>
<%@ include file="../../part/footer.jsp"%>
