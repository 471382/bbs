package com.project.controller;

import java.io.File;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.project.dto.BoardDto;
import com.project.service.IBoardService;
import com.project.util.FileUtils;
import com.project.vo.PageMaker;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping("/board/*")
public class BoardController {
	@Autowired
	private IBoardService bs;
	@Resource(name = "uploadPath")
	private String uploadPath;
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public void list(PageMaker pm, Model model) throws Exception{
		List<BoardDto> dtos = bs.listSearchCriteria(pm);
		for(BoardDto dto: dtos) {
			System.out.println(dto);
		}
		model.addAttribute("list",bs.listSearchCriteria(pm));
		pm.setTotalCount(bs.listSearchCount(pm));
	}
	
	@RequestMapping(value = "/write", method = RequestMethod.GET)
	public void write() throws Exception{
	}
	
	@RequestMapping(value = "/write", method = RequestMethod.POST)
	public String writeDB(MultipartHttpServletRequest request, RedirectAttributes ra, Model model) throws Exception {
	    BoardDto board = new BoardDto();
	    System.out.println(request.getParameter("title"));
	    board.setTitle(request.getParameter("title"));
	    board.setWriter(request.getParameter("writer"));
	    board.setContent(request.getParameter("content"));
	    MultipartFile file = request.getFile("file");
	    if (file != null && !file.isEmpty()) {
	        String savedFileName = uploadFile(file);
	        model.addAttribute("savedFileName", savedFileName);
	        board.setFile(savedFileName);
	    }
	    bs.write(board);
	    ra.addFlashAttribute("msg", "success");
	    return "redirect:/board/list";
	}

	private String uploadFile(MultipartFile file) throws Exception {
	    String filename = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
	    File target = new File(uploadPath, filename);
	    FileCopyUtils.copy(file.getBytes(), target);
	    return filename;
	}

	@RequestMapping(value = "/read", method = RequestMethod.GET)
	public void read(@RequestParam("bno") int bno, Model model) throws Exception{
		BoardDto dto = bs.read(bno);
		dto.setFile(bs.readAttach(bno));
		System.out.println(dto);
		
		model.addAttribute(dto);
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
	
	@RequestMapping(value = "/remove", method = RequestMethod.POST)
	public String removeDB(@RequestParam("bno") int bno,PageMaker pm,Model model, RedirectAttributes ra)  throws Exception{
		bs.remove(bno);
		ra.addAttribute("page",pm.getPage());
		ra.addAttribute("perPageNum", pm.getPerPageNum());
		ra.addAttribute("searchType", pm.getSearchType());
		ra.addAttribute("keyword", pm.getKeyword());
		ra.addFlashAttribute("msg","success");
		return "redirect:/board/list";
	}
}
