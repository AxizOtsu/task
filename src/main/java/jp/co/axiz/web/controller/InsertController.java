package jp.co.axiz.web.controller;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jp.co.axiz.web.entity.InsertForm;
import jp.co.axiz.web.entity.SessionInfo;
import jp.co.axiz.web.entity.User;
import jp.co.axiz.web.service.UserService;

@Controller
public class InsertController {

	@Autowired
	private UserService userService;

	@Autowired
	MessageSource messageSource;

	@Autowired
	private SessionInfo sessionInfo;


	@RequestMapping(value = "/insert" )
	public String insert( @ModelAttribute("form") InsertForm form, BindingResult result,Model model) {
		return "insert";
	}

	@RequestMapping(value = "/insertConfirm" , method = RequestMethod.POST)
	public String insertConfirm(@Validated  @ModelAttribute("form")  InsertForm form, BindingResult result,Model model) {

		if (result.hasErrors()) {
			String errorMsg = messageSource.getMessage("required.error", null, Locale.getDefault());
			model.addAttribute("msg", errorMsg);
			return "insert";
		}

		User user = new User();
		user.setUser_name(form.getName());
		user.setTelephone(form.getTel());
		user.setPassword(form.getPassword());

		sessionInfo.setNewUser(user);

		return "insertConfirm";
	}


	@RequestMapping(value = "/insertBack")
	public String insertBack(@ModelAttribute("form") InsertForm form, Model model) {

		User user = sessionInfo.getNewUser();

		form.setName(user.getUser_name());
		form.setTel(user.getTelephone());
		form.setPassword(user.getPassword());

		return "insert";
	}

	@RequestMapping(value = "/insert" , method = RequestMethod.POST)
	public String insertExecute( @ModelAttribute("form")  InsertForm form, BindingResult result,Model model) {
		User user = sessionInfo.getNewUser();

		if(!user.getPassword().equals(form.getConfirmPassword())) {
			String errorMsg = messageSource.getMessage("password.not.match.error", null, Locale.getDefault());
			model.addAttribute("msg", errorMsg);
			form.setConfirmPassword("");

			return "insertConfirm";
		}
		int id = userService.insert(user);

		sessionInfo.setNewUser(null);

		form.setId(id);

		model.addAttribute("user", sessionInfo.getLoginUser());

		return "insertResult";

	}
}
