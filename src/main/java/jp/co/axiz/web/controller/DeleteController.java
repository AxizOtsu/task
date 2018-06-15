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

import jp.co.axiz.web.entity.DeleteForm;
import jp.co.axiz.web.entity.SessionInfo;
import jp.co.axiz.web.entity.User;
import jp.co.axiz.web.service.impl.PgUserService;

@Controller
public class DeleteController {

	@Autowired
	private SessionInfo sessionInfo;

	@Autowired
	MessageSource messageSource;

	@Autowired
	private PgUserService userService;

	@RequestMapping(value = "/delete")
	public String delete(@ModelAttribute("form") DeleteForm form,BindingResult result, Model model) {
		return "delete";
	}

	@RequestMapping(value = "/deleteConfirm", method = RequestMethod.POST)
	public String deleteConfirm(@Validated @ModelAttribute("form") DeleteForm form, BindingResult result,
			Model model) {
		if (result.hasErrors()) {
			String errorMsg = messageSource.getMessage("required.error", null, Locale.getDefault());
			model.addAttribute("msg", errorMsg);
			return "delete";
		}
		User user = userService.findById(form.getId());

		if(user == null) {
			String errorMsg = messageSource.getMessage("id.not.found.error", null, Locale.getDefault());
			model.addAttribute("msg", errorMsg);
			return "delete";
		}

		form.setName(user.getUser_name());
		form.setTel(user.getTelephone());

		return "deleteConfirm";
	}


	@RequestMapping(value = "/delete" , method = RequestMethod.POST)
	public String deleteExecute(@ModelAttribute("form") DeleteForm form, BindingResult result,Model model) {
		int id = form.getId();
		userService.delete(id);
		model.addAttribute("user", sessionInfo.getLoginUser());

		return "deleteResult";
	}
}
