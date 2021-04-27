package com.eomcs.pms.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import com.eomcs.pms.domain.Board;
import com.eomcs.pms.service.BoardService;

@SuppressWarnings("serial")
@WebServlet("/board/detail")
public class BoardDetailHandler extends GenericServlet {

  SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

  @Override
  public void service(ServletRequest request, ServletResponse response)
      throws ServletException, IOException {

    BoardService boardService = (BoardService) request.getServletContext().getAttribute("boardService");

    response.setContentType("text/plain;charset=UTF-8");
    PrintWriter out = response.getWriter();

    out.println("[게시글 상세보기]");

    try {
      int no = Integer.parseInt(request.getParameter("no"));

      Board b = boardService.get(no);
      if (b == null) {
        out.println("해당 번호의 게시글이 없습니다.");
        return;
      }

      out.printf("제목: %s\n", b.getTitle());
      out.printf("내용: %s\n", b.getContent());
      out.printf("작성자: %s\n", b.getWriter().getName());
      out.printf("등록일: %s\n", formatter.format(b.getRegisteredDate()));
      out.printf("조회수: %s\n", b.getViewCount());
      out.printf("좋아요: %s\n", b.getLike());

    } catch (Exception e) {
      // 상세 오류 내용을 StringWriter로 출력한다.
      StringWriter strWriter = new StringWriter();
      PrintWriter printWriter = new PrintWriter(strWriter);
      e.printStackTrace(printWriter);

      // StringWriter 에 들어있는 출력 내용을 꺼내 클라이언트로 보낸다.
      out.println(strWriter.toString());
    }
  }
}






