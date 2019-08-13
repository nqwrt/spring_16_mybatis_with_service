package edu.bit.ex.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import edu.bit.ex.page.Criteria;
import edu.bit.ex.page.PageMaker;
import edu.bit.ex.service.BoardService;
import edu.bit.ex.vo.BoardVO;

/**
 * Servlet implementation class BoardFrontController
 */

@Controller
public class BController {

	
	@Inject
	BoardService boardService;
	
	@RequestMapping("/list")
	public String list(Model model) throws Exception{
		System.out.println("list()");

		//IBDao dao = sqlSession.getMapper(IBDao.class);
		//model.addAttribute("list", dao.list());
		
		model.addAttribute("list", boardService.selectBoardList());
		return "list";
	}
	
	@RequestMapping("/write_view")
	public String write_view(Model model) {
		System.out.println("write_view()");
		
		return "write_view";
	}
	
	@RequestMapping("/write")
	public String write(BoardVO boardVO, Model model) throws Exception {
		System.out.println("write()");

		boardService.insertBoard(boardVO);
		
		return "redirect:list";
	}
	
	@RequestMapping("/content_view")
	public String content_view(HttpServletRequest request, Model model) throws Exception{
		System.out.println("content_view()");
		
/*		model.addAttribute("request", request);
		command = new BContentCommand();
		command.execute(model);*/
		String bId = request.getParameter("bId");		
		model.addAttribute("content_view", boardService.selectBoardOne(bId));
		
		return "content_view";
	}
	
	@RequestMapping(value="/modify", method=RequestMethod.POST )
	public String modify(HttpServletRequest request, Model model){
		System.out.println("modify()");
		
/*		model.addAttribute("request", request);
		command = new BModifyCommand();
		command.execute(model);*/
		
		return "redirect:list";
	}
	
	@RequestMapping("/reply_view")
	public String reply_view(HttpServletRequest request, Model model) throws Exception{
		System.out.println("reply_view()");
		
/*		model.addAttribute("request", request);
		command = new BReplyViewCommand();
		command.execute(model);*/
		String bId = request.getParameter("bId");		
		model.addAttribute("reply_view", boardService.selectBoardOne(bId));
		return "reply_view";
	}
	
	@RequestMapping("/reply")
	public String reply(BoardVO boardVO,HttpServletRequest request, Model model) throws Exception {
		System.out.println("reply()");
		
/*		model.addAttribute("request", request);		
		command = new BReplyCommand();
		command.execute(model);*/
		boardService.updateShape(boardVO); 
		boardService.insertReply(boardVO);	
		
		return "redirect:list";
	}
	
	@RequestMapping("/delete")
	public String delete(HttpServletRequest request, Model model) throws Exception {
		System.out.println("delete()");
		
/*		model.addAttribute("request", request);
		command = new BDeleteCommand();
		command.execute(model);*/
		String bId = request.getParameter("bId");
		boardService.deleteBoardOne(bId); 
		
		return "redirect:list";
	}
	
	@RequestMapping("/list3")
	public void list3(Criteria criteria, Model model) throws Exception {
		System.out.println("list2()");
		
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(criteria);
		
		System.out.println(criteria.getPerPageNum());
		System.out.println(criteria.getPage());
		
		 //모델에 추가
		
		List<BoardVO> boardList = boardService.selectBoardList();
		
		//전체 게시물 수를 구함
	    int totalCount = boardList.size();
	    System.out.println("전체 게시물 수를 구함:" + totalCount);
		pageMaker.setTotalCount(totalCount);
       
		boardList = boardService.selectBoardListPage(criteria); 
		
		model.addAttribute("list", boardList);
		model.addAttribute("pageMaker", pageMaker);

		//return "redirect:list";
	}
	
}
