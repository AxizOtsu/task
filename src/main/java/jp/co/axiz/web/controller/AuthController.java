package jp.co.axiz.web.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jp.co.axiz.web.entity.Admin;
import jp.co.axiz.web.entity.LoginForm;
import jp.co.axiz.web.entity.SessionInfo;
import jp.co.axiz.web.service.LoginService;

@Controller
public class AuthController {



	@Autowired
	private LoginService loginService;
	@Autowired
	private SessionInfo sessionInfo;
	@Autowired
	private HttpSession session;

	@RequestMapping(value = "/login" , method = RequestMethod.GET)
	public String login(@ModelAttribute("form") LoginForm loginForm, Model model) {
		//		List<User> list = userService.findAll();
		//		model.addAttribute("userlist", list);

		return "login";
	}

	@RequestMapping(value = "/login" , method = RequestMethod.POST)
	public String login(@ModelAttribute("form")  LoginForm loginForm, BindingResult result,Model model) {
		List<Admin> list = loginService.findByIdAndPass(loginForm.getId() , loginForm.getPass());

		if (!(list.size() ==0)){
			// メッセージ設定

			String adminName = list.get(0).getAdmin_name();
			session.setAttribute("admin_name",adminName);
			// 次画面指定
			return "menu";
		} else {
			// メッセージ設定
			model.addAttribute("msg", "IDまたはPASSが間違っています。");

			// 次画面指定
			return "login";
		}
	}



	@RequestMapping(value = "/logout" , method = RequestMethod.POST)
	public String logout(Model model) {
		sessionInfo.invalidate();

		return "logout";
	}
}

