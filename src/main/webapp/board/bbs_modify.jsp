<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file = "../include/header.jsp" %>
     <section>
        <div class="container">
            <div class="row">
                <div class="col-lg-8 col-md-10 col-sm-12 join-form">
                    <h2>게시판 수정<small>(디자인이궁금하세요?)</small></h2>

                    <form action="update.bbs">
                        <div class="form-group">
                            <label>글번호</label>
                            <input type="text" name="bno" class="form-control" value="${vo.bno}" readonly>
                        </div>
                        <div class="form-group">
                            <label>글쓴이</label>
                            <input type="text" name="writer" class="form-control" value="${vo.writer}" readonly >
                        </div>
                        <div class="form-group">
                            <label>제목</label>
                            <input type="text" name="title"class="form-control" value="${vo.title}">
                        </div>
                        <div class="form-group">
                            <label>내용</label>
                            <textarea class="form-control" rows="5" name="content">${vo.content}</textarea>
                        </div>

                        <!--구현로직: 버튼은 온클릭을 사용하던 자바스크립트를 이용해야 합니다-->
                        <div class="form-group">
                            <button type="button" class="btn btn-success" onclick="location.href='board.bbs'">목록</button>
                            <button type="submit" class="btn btn-info">수정완료</button>
                            <button type="button" class="btn btn-default" onclick="location.href='delete.bbs?bno=${vo.bno}'">삭제</button>
                        </div>

                    </form>
                </div>
            </div>
        </div>


        </section>
 <%@ include file = "../include/footer.jsp" %>