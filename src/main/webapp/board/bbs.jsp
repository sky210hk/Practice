<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 
<%@ include file = "../include/header.jsp" %>
  <!--게시판만 적용되는 css-->            
        <style>

            .table-striped > tbody > tr {
                background-color: rgba(255, 255, 255)
            }
            .row h2 {
                color: aliceblue;
                
            }
            .pagination-sm {
                margin: 0;
            }
            
        </style>
    <section>
        
        <div class="container">
            <div class="row">
                
                <h2>게시판 목록</h2>
                <div>
                	<select onchange = "handleChange(this)">
                		<option value = "10" ${pageVO.amount eq 10? 'selected' : '' }>10개씩 보기</option>
                		<option value = "20" ${pageVO.amount eq 20? 'selected' : '' }>20개씩 보기</option>
                		<option value = "50" ${pageVO.amount eq 50? 'selected' : '' }>50개씩 보기</option>
                		<option value = "100" ${pageVO.amount eq 100? 'selected' : '' }>100개씩 보기</option>
                	</select>
                
                </div>
                <table class="table table-striped" style="text-align: center; border: 2px solid #737373">
                    <thead>
                        <tr>
                            <th style="background-color: #9DCAFF; text-align: center;">번호</th>
                            <th style="background-color: #9DCAFF; text-align: center;">제목</th>
                            <th style="background-color: #9DCAFF; text-align: center;">작성자</th>
                            <th style="background-color: #9DCAFF; text-align: center;">작성일</th>
                        </tr>
                    </thead>
                    <c:forEach var="vo" items="${list}" >
                    <tbody>
                        <tr>
                            <td>${vo.bno}</td>
                            <td><a href="content.bbs?bno=${vo.bno}">${vo.title}</a></td>
                            <td>${vo.writer }</td>
                            <td><fmt:formatDate value="${vo.regdate}" pattern = "yyyy-MM-dd HH-mm" /></td>
                        </tr>
                       
                    </tbody>
                    </c:forEach>
                </table>

                <div class="text-center">
                    <ul class="pagination pagination-sm">

						<!-- 처음버튼 활성화 하기 -->
                    		<li>
                    			<a href="board.bbs?pageNum=1&amount=${pageVO.amount}">처음</a>
                    		</li>
						
						<!-- 이전버튼 활성화 하기 -->
                        <c:if test="${pageVO.prev }">
                        	<li>
                        		<a href="board.bbs?pageNum=${pageVO.startPage - 1 }&amount=${pageVO.amount }">이전</a>
                        	</li>
                        </c:if>
                        
                        <!-- 페이지 번호 처리  -->
                        <c:forEach var = "num" begin="${pageVO.startPage }" end ="${pageVO.endPage }">
                        	<li class="${pageVO.pageNum eq num ? 'active' : '' }">
                        		<a href="board.bbs?pageNum=${num}&amount=${pageVO.amount }">${num }</a>
                        	</li>
                        </c:forEach>
                        
                        <!-- 다음버튼 활성화 하기 -->
                        <c:if test="${pageVO.next }">
	                        <li>
	                        	<a href="board.bbs?pageNum=${pageVO.endPage + 1 }&amount=${pageVO.amount}">다음</a>
	                        </li>
                        </c:if>
                        
                        <!-- 마지막버튼 활성화 하기 -->
                        <li>
                        	<a href="board.bbs?pageNum=${pageVO.realEnd }&amount=${pageVO.amount}">마지막</a>
                        </li>
                    </ul>
                    <button class="btn btn-info pull-right" onclick="location.href='write.bbs'">글쓰기</button>
                </div>
                
            </div>
        </div>
    </section>
	
	<script>
	function handleChange(a){
		location.href = 'board.bbs?pageNum=1&amount=' + a.value;
	}
	</script>
<%@ include file = "../include/footer.jsp" %>
