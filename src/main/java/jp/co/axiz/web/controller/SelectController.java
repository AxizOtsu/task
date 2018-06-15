package jp.co.axiz.web.controller;

import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jp.co.axiz.web.entity.SelectForm;
import jp.co.axiz.web.entity.User;
import jp.co.axiz.web.service.impl.PgUserService;


@Controller
public class SelectController {

	@Autowired
	private PgUserService PgUserService;
	@Autowired
    MessageSource messageSource;


	@RequestMapping("/select")
	public String login(Model model, @ModelAttribute("form")  SelectForm form) {
		return "select";
	}


	@RequestMapping(value = "/list",method = RequestMethod.GET  )
	public String list(@ModelAttribute("form") SelectForm form, BindingResult result,Model model) {


		String errorMsg = messageSource.getMessage("select.error", null, Locale.getDefault());

		if (result.hasErrors()) {
			model.addAttribute("msg", errorMsg);
			return "select";
		}

		User condition = new User();
		condition.setUser_id(form.getId());
		condition.setUser_name(form.getName());
		condition.setTelephone(form.getTel());

		List<User> resultList = PgUserService.find(condition);

		if(resultList.isEmpty()) {
			model.addAttribute("msg", errorMsg);
			return "select";
		}else {
			model.addAttribute("userlist", resultList);
			return "selectResult";
		}
	}
}


