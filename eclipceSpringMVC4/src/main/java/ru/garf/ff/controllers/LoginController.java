package ru.garf.ff.controllers;

import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

import ru.garf.ff.objects.User;

@Controller
@SessionAttributes("user")
public class LoginController {

	private static final int WEAK_STRENGTH = 1;
	private static final int FEAR_STRENGTH = 5;
	private static final int STRONG_STRENGTH = 7;

	private static final String WEAK_COLOR = "#F34120";
	private static final String FEAR_COLOR = "#ECAE51";
	private static final String STRONG_COLOR = "#4BFF3D";

	@Autowired
	private MessageSource messageSourse;

	@ModelAttribute
	public User createNewUser() {
		return new User("userNameValue");
	}

	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

	@RequestMapping(value = "/checkStrength", method = RequestMethod.GET, produces = { "text/html; charset=UTF-8" })
	@ResponseBody
	public String checkStrength(@RequestParam String password, Locale locale) {

		String result = "<span style=\"color:%s\">%s</span>";

		if (password.length() >= WEAK_STRENGTH && password.length() < FEAR_STRENGTH)
			return String.format(result, WEAK_COLOR, messageSourse.getMessage("strengthValue.weak", null, locale));
		if (password.length() >= FEAR_STRENGTH && password.length() < STRONG_STRENGTH)
			return String.format(result, FEAR_COLOR, messageSourse.getMessage("strengthValue.fear", null, locale));
		if (password.length() >= STRONG_STRENGTH)
			return String.format(result, STRONG_COLOR, messageSourse.getMessage("strengthValue.strong", null, locale));
		return "";

	}

	@RequestMapping(value = "/downloadPdf", method = RequestMethod.GET)
	public ModelAndView downloadPdf() {

		return new ModelAndView("pdfView");
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView main(@ModelAttribute User user, HttpSession session, Locale locale) {
		System.out.println(locale.getDisplayLanguage());
		System.out.println(messageSourse.getMessage("locale", new String[] { locale.getDisplayName(locale) }, locale));
		return new ModelAndView("login", "user", user);
	}

	@RequestMapping(value = "/check-user", method = RequestMethod.POST)
	public String checkUser(Locale locale, @Valid @ModelAttribute("user") User user, BindingResult bindingResult, ModelMap modelMap, RedirectAttributes redirectAttributes) {

		if (!bindingResult.hasErrors()) {
			redirectAttributes.addFlashAttribute("redirect", true);
			redirectAttributes.addFlashAttribute("locale", messageSourse.getMessage("locale", new String[] { locale.getDisplayName(locale) }, locale));
			return "redirect:/mainpage";
		}
		return "login";

	}

	@RequestMapping(value = "/mainpage", method = RequestMethod.GET)
	public String goMainPage(HttpServletRequest request) {
		Map<String, ?> map = RequestContextUtils.getInputFlashMap(request);
		if (map != null) {
			logger.info("redirect!");
		} else {
			logger.info("update!");
		}
		return "main";
	}

	@RequestMapping(value = "/failed", method = RequestMethod.GET)
	public ModelAndView failed() {
		return new ModelAndView("login-failed", "message", "Login failed!");
	}

	@RequestMapping(value = "/get-json-user/{name}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public User getJsonUser(@PathVariable("name") String name) {
		User user = new User();
		user.setName(name);
		return user;
	}

	@RequestMapping(value = "/put-json-user", method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<String> setJsonUser(@RequestBody User user) {
		System.out.println("in");
		logger.info(user.getName());
		return new ResponseEntity<String>(HttpStatus.OK);
	}

}
