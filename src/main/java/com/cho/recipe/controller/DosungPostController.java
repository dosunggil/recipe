package com.cho.recipe.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cho.recipe.model.DosungDetailVO;
import com.cho.recipe.model.DosungPostVO;
import com.cho.recipe.service.DosungPostService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping(value = "/cho/post")
@Controller
public class DosungPostController {

	@Autowired
	private DosungPostService post;

	@RequestMapping(value = "/search_result", method = RequestMethod.GET)
	public String getRecipes() {
		return "cho/post/search_result";
	}

	@RequestMapping(value = "/search_result", method = RequestMethod.POST , produces = "application/json;charset=UTF-8")
	public String getRecipes(String title,String dtls, Model model) {

		String quString = post.queryString("LIST", title);
		List<DosungPostVO> recipeList = post.getRecipes(quString);
		
		model.addAttribute("RECIPES", recipeList);

		return "cho/post/search_result";
	}

	@RequestMapping(value = "/{seq}/{nm}/detail", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public String detail(@PathVariable("nm") String nm, @PathVariable("seq")  String seq, Model model) {

		
	/*
	 * String queryString = naver.queryString("LIST", isbn);
	 * String queryString = naver.queryString("DETAIL", isbn);
	 * 
	 * 이렇게 해서 두 경우로 나누자!
	 * NaverServiceImplV1 getNaver 참고 !!
	 */
		String quString = post.queryString("LIST", nm);
		List<DosungPostVO> recipeList = post.getRecipes(quString);
		List<DosungDetailVO> detailList = post.getDetail(quString);
//		log.debug("받아온 디테일 내용입니다2. " + detailList.toString());
		
		DosungPostVO postVO = null;
		for(DosungPostVO dVO : recipeList) {
			if(dVO.getRCP_SEQ().equals(seq)){
				postVO = dVO;
				break;
			}
		}
		DosungDetailVO dVO  = null;
		dVO = detailList.get(0);
		log.debug("디테일브이오" + dVO.toString());
		
//		DosungDetailVO detailVO = null;
//		detailVO = detaill.splitDetail(dVO);
		
		model.addAttribute("RECIPE", postVO);
		model.addAttribute("DETAIL",dVO);
		
//		@ResponseBody
//		@RequestMapping(value="/{isbn}/book",method=RequestMethod.GET)
//		public BookVO book(@PathVariable("isbn") String isbn) {
//			
//			String queryString = naver.queryString("BOOK", isbn);
//			List<Object> bookList = naver.getNaver(queryString);
//			
//			BookVO bookVO = (BookVO) bookList.get(0);
//			return bookVO;
//			
//		}
		
		
		
		/*
		 * open api 에서는 findbyid 가 아닌 다른 방법을 찾아야한다.
		 */
		/*
		 * DosungPostVO postVO = post.findById(seq);
		 * model.addAttribute("RECIPE",postVO);
		 */
		//log.debug("결과는? " + vo.toString());

		return "cho/post/detail";
	}
	
	@RequestMapping(value="/insert",method=RequestMethod.GET)
	public String insert() {
		
		return null;
	}


}
