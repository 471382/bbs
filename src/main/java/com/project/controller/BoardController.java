package com.project.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.project.dto.BoardDto;
import com.project.service.IBoardService;
import com.project.vo.PageMaker;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping("/board/*")
public class BoardController {
	@Autowired
	private IBoardService bs;
	
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public void list(PageMaker pm, Model model) throws Exception{
		logger.info("listPage");
		System.out.println("--list pm"+pm);
		model.addAttribute("list",bs.listSearchCriteria(pm));
		pm.setTotalCount(bs.listSearchCount(pm));//1
	}
	@RequestMapping(value = "/write", method = RequestMethod.GET)
	public void write() throws Exception{
		System.out.println("write Page");
	}
	@RequestMapping(value = "/write", method = RequestMethod.POST)
	public String writeDB(BoardDto board,RedirectAttributes ra) throws Exception{
		System.out.println("writeDB");
		System.out.println(board);
		bs.write(board);
		ra.addFlashAttribute("msg","success");
		return "redirect:/board/list";
	}
	@RequestMapping(value = "/read", method = RequestMethod.GET)
	public void read(@RequestParam("bno") int bno, Model model) throws Exception{
		BoardDto dto = bs.read(bno);
		System.out.println(dto);
		model.addAttribute(bs.read(bno));
	}
	@RequestMapping(value = "/modify", method = RequestMethod.GET)
	public void modify(@RequestParam("bno") int bno,PageMaker pm,Model model) throws Exception{
		model.addAttribute(bs.read(bno));
	}
	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	public String modifyDB(BoardDto board,PageMaker pm,Model model, RedirectAttributes ra) throws Exception{
		bs.modify(board);
		ra.addAttribute("page",pm.getPage());
		ra.addAttribute("perPageNum", pm.getPerPageNum());
		ra.addAttribute("searchType", pm.getSearchType());
		ra.addAttribute("keyword", pm.getKeyword());
		ra.addFlashAttribute("msg","success");
		return "redirect:/board/list";
	}
}
