package jp.co.axiz.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class IndexController {



	@RequestMapping(value = "/index")
	public String list(Model model) {
//		List<User> list = userService.findAll();
//		model.addAttribute("userlist", list);

		return "index";
	}

	@RequestMapping(value = "/menu" ,method = RequestMethod.GET)
	public String list2(Model model) {
//		List<User> list = userService.findAll();
//		model.addAttribute("userlist", list);

		return "menu";
	}
}