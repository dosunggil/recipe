package com.cho.recipe.controller;

import java.util.List;
import java.util.Locale;


import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import com.cho.recipe.model.UserVO;
import com.cho.recipe.service.DosungUserService;


/**
 * Handles requests for the application home page.
 */
@Slf4j
@Controller
public class HomeController {

	private final ContentsService contentsService;
	public HomeController(@Qualifier("contentsServicev1") ContentsService contentsService) {
		this.contentsService=contentsService;
	}
	
	@Autowired
	private DosungUserService dosungUserService;
	
	@RequestMapping(value = {"","/"}, method = RequestMethod.GET)
	public String home(Locale locale, Model model, HttpSession session) {
		UserVO userVO = (UserVO) session.getAttribute("USER");
		
		if(userVO != null) {
			UserVO realUserVO = dosungUserService.findById(userVO.getUsername());
			session.setAttribute("USER", realUserVO);
		}
		return "home";
	}
	@RequestMapping(value = "/detail/{postseq}", method=RequestMethod.GET)
	public String home(@PathVariable("postseq") String rcp_seq, Model model) {
		DosungPostVO postVO  = contentsService.findByPostSeq(rcp_seq);
		List<DosungManualVO> manuals = contentsService.findByRecipeID(rcp_seq);
	
		model.addAttribute("postVO", postVO);
		model.addAttribute("MANUAL", manuals);
		log.debug(manuals.toString());
		
		return "ahn/log/detail";
	}

	
}
