<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<jsp:include page="../common/menubar.jsp"/>
	
	<div class="outer">

        <br>
        <h2 align="center">마이페이지</h2>

        <form id="myPage-form" action="update.me" method="post"> <!-- 마이페이지 : POST -->
            <table align="center">
				<tr>
					<td>* ID</td>
					<td><input type="text" name="userId" value="${ loginMember.userId }" readonly></td> <!-- 수정 못하도록 -->
				</tr>
				<!-- 
				<tr>
					<td>* PWD</td>
					<td><input type="password" name="userPwd" value="${ loginMember.userPwd }" required></td>
				</tr>
				 -->
				<tr>
					<td>* NAME</td>
					<td><input type="text" name="userName" value="${ loginMember.userName }" required></td>
				</tr>
				<tr>
					<td>&nbsp;&nbsp;EMAIL</td>
					<td><input type="email" name="email" value="${ loginMember.email }"></td>
				</tr>
				<tr>
					<td>&nbsp;&nbsp;BIRTHDAY</td>
					<td><input type="text" name="birthday" value="${ loginMember.birthday }"></td>
				</tr>
				<tr>
					<td>&nbsp;&nbsp;GENDER</td>
					<td align="center">
						<c:choose>
							<c:when test="${ loginMember.gender eq 'M' }">
								<input type="radio" name="gender" value="M" checked>남
								<input type="radio" name="gender" value="F">여
							</c:when>
							<c:otherwise>
								<input type="radio" name="gender" value="M">남
								<input type="radio" name="gender" value="F" checked>여
							</c:otherwise>
						</c:choose>
						<!-- 
						<input type="radio" name="gender" value="M">남
						<input type="radio" name="gender" value="F">여
						 -->
					</td>
				</tr>
				<tr>
					<td>&nbsp;&nbsp;PHONE</td>
					<td><input type="text" name="phone" value="${ loginMember.phone }"></td>
				</tr>
				<tr>
					<td>&nbsp;&nbsp;ADDRESS</td>
					<td><input type="text" name="address" value="${ loginMember.address }"></td>
				</tr>
			</table>
            
            <br><br>
            
            <!-- * 기존 radio값 처리 * 왜안되누..!!
			<script>
			 	$(function(){
					const gender = "${ loginMember.gender }"
					$("input[type=radio]").each(function() {
						if ($(this).val() == gender) {
				            $(this).attr("checked", true);
				        }
					})
		        })
			</script>
			 -->

            <div align="center">
                <button type="submit">정보변경</button>
                <!-- 
                <button type="button" class="btn btn-sm btn-warning" data-toggle="modal" data-target="#updatePwdModal">비밀번호변경</button>
                <button type="button" class="btn btn-sm btn-danger" data-toggle="modal" data-target="#deleteModal">회원탈퇴</button>
            	 -->
            </div>
        </form>

    </div>
	
</body>
</html>