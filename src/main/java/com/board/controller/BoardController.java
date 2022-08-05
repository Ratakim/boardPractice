
package com.board.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.board.service.BoardService;
import com.board.service.ReplyService;
import com.board.vo.BoardVO;
import com.board.vo.ReplyVO;

@Controller
@RequestMapping("/board")
public class BoardController {

	@Inject
	private BoardService service;
	@Inject
	private ReplyService replyservice;
	
    //게시물 목록
	@RequestMapping("/list")
	public ModelAndView list() throws Exception {

		List<BoardVO> list = service.list();

		ModelAndView mav = new ModelAndView();
		mav.setViewName("/board/list");
		mav.addObject("list", list);

		return mav;
	}
	//게시물 작성 get
		@GetMapping("/write")
		public ModelAndView getwrite(BoardVO vo, HttpServletRequest request, HttpServletResponse response ) throws Exception {
						
			ModelAndView mav = new ModelAndView();
			mav.setViewName("/board/write");
			
			return mav;
	}
	//게시물 작성 post
	@PostMapping("/write")
	public ModelAndView postwrite(BoardVO vo, HttpServletRequest request, HttpServletResponse response ) throws Exception {
		
		service.write(vo);
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:/board/list");
		
		return mav;
		
	}
	//게시물 조회
	@RequestMapping("/view")
	public ModelAndView getView(@RequestParam("no") int no) throws Exception {
		
		BoardVO view = service.view(no);
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/board/view");
		mav.addObject("view", view );
		
		// 댓글 조회
		List<ReplyVO> reply = null;
		reply = replyservice.list(no);
		mav.addObject("reply", reply);
		
		return mav;
	}
	// 게시물 수정 get
	@GetMapping("/modify")
	public ModelAndView getmodify(int no) throws Exception {
		
		BoardVO view = service.view(no);
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/board/modify");
		mav.addObject("view", view );
		
		return mav;
		
	}

	// 게시물 수정 post
	@PostMapping("/modify")
	public ModelAndView postModify(BoardVO mo) throws Exception {

	   service.modify(mo);
	   	   
		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:/board/view?no="+mo.getNo());			
		
		return mav;
			
	 }
	
	// 게시물 삭제
	@RequestMapping("/delete")
	public ModelAndView getDelete(int no) throws Exception {

	    service.delete(no);
		   
		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:/board/list");	
				
		return mav;
			
	 }
	
	 //게시물 목록 + 페이징 추가
		@RequestMapping("/listPage")
		public ModelAndView getListPage(int num) throws Exception {
			
					
			//게시물 총 개수
			int count = service.count();
			
			//한 페이지에 출력할 게시물 개수
			int postNum = 10;
			
			//하단 페이징 번호 ([ 게시물 총 갯수 ÷ 한 페이지에 출력할 갯수 ]의 올림)
			int pageNum = (int)Math.ceil((double)count/postNum);
			
			// 출력할 게시물
		    int displayPost = (num - 1) * postNum + 1;
		    
		    //한번에 표시할 페이징 번호의 개수
		    int pageNum_cnt = 5;
		    
		    //표시되는 페이지 번호 중 마지막 번호
		    int endPageNum = (int)(Math.ceil((double)num / (double)pageNum_cnt) * pageNum_cnt);
		    
		    // 표시되는 페이지 번호 중 첫번째 번호
		    int startPageNum = endPageNum - (pageNum_cnt - 1);
		    
		    // 마지막 번호 재계산
		    int endPageNum_tmp = (int)(Math.ceil((double)count / (double)(pageNum_cnt*2)));
		     
		    if(endPageNum > endPageNum_tmp) {
		     endPageNum = endPageNum_tmp;
		    }
		    
		    boolean prev = startPageNum == 1 ? false : true;
		    boolean next = endPageNum * pageNum_cnt >= count ? false : true;

		   
		    
			List<BoardVO> list = service.listPage(displayPost,postNum * num);

			ModelAndView mav = new ModelAndView();
			mav.setViewName("/board/listPage");
			mav.addObject("list", list);
			mav.addObject("pageNum",pageNum);
			
			// 시작 및 끝 번호
			mav.addObject("startPageNum", startPageNum);
			mav.addObject("endPageNum", endPageNum);

			// 이전 및 다음 
			mav.addObject("prev", prev);
			mav.addObject("next", next);
			
			// 현재 페이지
			mav.addObject("select", num);
			
			return mav;
       }
		
		 //게시물 목록 +페이징 추가 + 검색
		@RequestMapping("/listPageSearch")
		public ModelAndView getListPageSearch(int num,
				@RequestParam(value = "searchType",required = false, defaultValue = "title") String searchType,
			    @RequestParam(value = "keyword",required = false, defaultValue = "") String keyword) 
						   throws Exception {
			
							
			//게시물 총 개수
			int count = service.searchCount(searchType, keyword);
			System.out.println(count);		
			//한 페이지에 출력할 게시물 개수
			int postNum = 10;
			
			//하단 페이징 번호 ([ 게시물 총 갯수 ÷ 한 페이지에 출력할 갯수 ]의 올림)
			int pageNum = (int)Math.ceil((double)count/postNum);
			
			// 출력할 게시물
		    int displayPost = (num - 1) * postNum + 1;
		    
		    //한번에 표시할 페이징 번호의 개수
		    int pageNum_cnt = 5;
		    
		    //표시되는 페이지 번호 중 마지막 번호
		    int endPageNum = (int)(Math.ceil((double)num / (double)pageNum_cnt) * pageNum_cnt);
		    
		    // 표시되는 페이지 번호 중 첫번째 번호
		    int startPageNum = endPageNum - (pageNum_cnt - 1);
		    
		    // 마지막 번호 재계산
		    int endPageNum_tmp = (int)(Math.ceil((double)count / (double)(pageNum_cnt*2)));
		     
		    if(endPageNum > endPageNum_tmp) {
		     endPageNum = endPageNum_tmp;
		    }
		    
		    boolean prev = startPageNum == 1 ? false : true;
		    boolean next = endPageNum * pageNum_cnt >= count ? false : true;

		   
		    
			List<BoardVO> list = service.listPageSearch(displayPost,postNum * num, searchType, keyword);

			ModelAndView mav = new ModelAndView();
			mav.setViewName("/board/listPageSearch");
			mav.addObject("list", list);
			mav.addObject("pageNum",pageNum);
			
			// 시작 및 끝 번호
			mav.addObject("startPageNum", startPageNum);
			mav.addObject("endPageNum", endPageNum);

			// 이전 및 다음 
			mav.addObject("prev", prev);
			mav.addObject("next", next);
			
			// 현재 페이지
			mav.addObject("select", num);
			mav.addObject("count", count);
			
			
			
			return mav;
  }
		
}
  

  