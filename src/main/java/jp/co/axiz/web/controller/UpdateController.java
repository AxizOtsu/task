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

import jp.co.axiz.web.entity.SessionInfo;
import jp.co.axiz.web.entity.UpdateForm;
import jp.co.axiz.web.entity.User;
import jp.co.axiz.web.service.UserService;

@Controller
public class UpdateController {

	@Autowired
	private SessionInfo sessionInfo;

	@Autowired
    MessageSource messageSource;

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/update")
	public String update(@ModelAttribute("form") UpdateForm form, Model model) {
		return "update";
	}

	@RequestMapping(value = "/updateInput" , method = RequestMethod.POST)
	public String update(@ModelAttribute("form") UpdateForm form, BindingResult result,Model model) {
		if (result.hasFieldErrors("id")) {
			String errorMsg = messageSource.getMessage("required.error", null, Locale.getDefault());
			model.addAttribute("msg", errorMsg);
			return "update";
		}

		User user = userService.findById(form.getId());
		if(user == null) {
			String errorMsg = messageSource.getMessage("id.not.found.error", null, Locale.getDefault());
			model.addAttribute("msg", errorMsg);
			return "update";
		}
		sessionInfo.setPrevUser(user);

		form.setNewName(user.getUser_name());
		form.setNewTel(user.getTelephone());
		form.setNewPassword(user.getPassword());

		return "updateInput";

	}

	@RequestMapping(value = "/updateConfirm" , method = RequestMethod.POST)
	public String updateConfirm(@Validated @ModelAttribute("form") UpdateForm form, BindingResult result, Model model) {
		if (form.hasRequiredError()) {
			String errorMsg = messageSource.getMessage("required.error", null, Locale.getDefault());
			model.addAttribute("msg", errorMsg);
			return "updateInput";
		}
		User beforeUser = sessionInfo.getPrevUser();
		User afterUser = new User();
		afterUser.setUser_id(beforeUser.getUser_id());
		afterUser.setUser_name(form.getNewName());
		afterUser.setTelephone(form.getNewTel());
		afterUser.setPassword(form.getNewPassword());

		if(afterUser.equals(beforeUser)) {
			String errorMsg = messageSource.getMessage("required.change", null, Locale.getDefault());
			model.addAttribute("msg", errorMsg);
			return "updateInput";
		}
		sessionInfo.setAfterUser(afterUser);

		form.setPrevName(beforeUser.getUser_name());
		form.setPrevTel(beforeUser.getTelephone());
		form.setPrevPassword(beforeUser.getPassword());

		if(beforeUser.getPassword().equals(afterUser.getPassword())) {
			form.setConfirmNewPassword(afterUser.getPassword());
		}

		return "updateConfirm";
	}


	@RequestMapping(value = "/updateInputBack")
	public String updateInputBack(@ModelAttribute("form") UpdateForm form, Model model) {

		User afterUser = sessionInfo.getAfterUser();

		form.setId(afterUser.getUser_id());
		form.setNewName(afterUser.getUser_name());
		form.setNewTel(afterUser.getTelephone());
		form.setNewPassword(afterUser.getPassword());

		return "updateInput";
	}

	@RequestMapping(value = "/update" , method = RequestMethod.POST)
	public String list4(Model model, @ModelAttribute("form") UpdateForm form, BindingResult result) {
		User afterUser = sessionInfo.getAfterUser();

		if(!afterUser.getPassword().equals(form.getConfirmNewPassword())) {
			String errorMsg = messageSource.getMessage("password.not.match.error", null, Locale.getDefault());
			model.addAttribute("msg", errorMsg);

			form.setConfirmNewPassword("");

			User beforeUser = sessionInfo.getPrevUser();
			form.setPrevName(beforeUser.getUser_name());
			form.setPrevTel(beforeUser.getTelephone());
			form.setPrevPassword(beforeUser.getPassword());

			return "updateConfirm";
		}


		userService.update(afterUser);

		sessionInfo.setAfterUser(null);
		sessionInfo.setPrevUser(null);

		model.addAttribute("user", sessionInfo.getLoginUser());

		return "updateResult";
	}
}
