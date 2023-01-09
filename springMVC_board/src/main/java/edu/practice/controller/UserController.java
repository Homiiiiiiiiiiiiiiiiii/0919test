package edu.practice.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.practice.service.UserService;
import edu.practice.vo.UserVO;

@RequestMapping(value="/user")
@Controller
public class UserController {
	
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/join.do",method=RequestMethod.GET)
	public String join() {
		return "user/join";
	}
	
	@RequestMapping(value="/join.do",method=RequestMethod.POST)
	public String join(UserVO vo) {
		userService.insert(vo);
		return "redirect:/";
	}
	
	
	@ResponseBody
	@RequestMapping(value="/checkId.do",method=RequestMethod.POST)
	public String checkId(String id) {
		
		int result = userService.selectById(id);
		//System.out.println("result?"+result);
		
		if(result > 0 ) {
			return "1"; 
		}else {
			return "0";
		}
	}
	
	@RequestMapping(value="/login.do",method=RequestMethod.GET)
	public String main() {
		return "user/login";
	}
	
	@RequestMapping(value="/login.do",method=RequestMethod.POST)
	public String login(UserVO vo,HttpSession session) {
		
		UserVO loginVO = userService.selectByUidx(vo);
		
		if(loginVO != null) {
			session.setAttribute("login",loginVO);
			System.out.println(loginVO.toString());
		}
		
		return "redirect:/board/list.do";
	}
	
	@RequestMapping(value="/logout.do",method=RequestMethod.GET)
	public String logout(HttpSession session) {
		
		session.invalidate();
		
		return "redirect:/";
	}
	
	

}
