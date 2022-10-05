package com.board.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.board.service.BoardService;
import com.board.service.ReplyService;
import com.board.vo.ReplyVO;

@Controller
@RequestMapping("/reply")
public class ReplyController {
	 
	@Inject 
	private ReplyService replyService;

	
	// 댓글 작성
	@RequestMapping(value = "/write", method = RequestMethod.POST)
	public String posttWirte(ReplyVO vo) throws Exception {
		
		 replyService.write(vo);
		
		return "redirect:/board/view?no=" + vo.getBno();
	}

}
