package com.uragiljay.spring052401.Controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.uragiljay.spring052401.Command.BCommand;
import com.uragiljay.spring052401.Command.BContentCommand;
import com.uragiljay.spring052401.Command.BDeleteCommand;
import com.uragiljay.spring052401.Command.BListCommand;
import com.uragiljay.spring052401.Command.BModifyCommand;
import com.uragiljay.spring052401.Command.BReplyCommand;
import com.uragiljay.spring052401.Command.BReplyViewCommand;
import com.uragiljay.spring052401.Command.BWriteCommand;

@Controller
public class BController {

	BCommand command = null;
	
	@RequestMapping("/")
		public String root(Model model) {
		return "redirect:list";
	}
	
	@RequestMapping("/list")
	public String list(Model model) {
		
		command = new BListCommand();
		command.excute(model);
		
		return "list";
	}
	
	@RequestMapping("/write")
	public String write(HttpServletRequest request, Model model) {
		
		model.addAttribute("request", request);
		
		command = new BWriteCommand();
		command.excute(model);
		
		return "redirect:list";
	}
	
	@RequestMapping("/write_form")
	public String write_form() {
		return "write_form";
	}	
	
	@RequestMapping("/content_view")
	public String content_view(HttpServletRequest request, Model model) {
		model.addAttribute("request", request);
		
		command = new BContentCommand();
		command.excute(model);
		
		return "content_view";
	}
	
	@RequestMapping("/modify_view")
	public String modify_view(HttpServletRequest request, Model model) {
		
		model.addAttribute("request", request);
	
		command = new BContentCommand();
		command.excute(model);	
		
		return "modify_view";
				
	}
	
	@RequestMapping("/modify")
	public String modify(HttpServletRequest request, Model model) {
		
		model.addAttribute("request", request);
		
		command = new BModifyCommand();
		command.excute(model);	
		
		
		return "redirect:list";
	}
	
	
	@RequestMapping("/delete")
	public String delete(HttpServletRequest request, Model model) {
		
		model.addAttribute("request", request);
		
		command = new BDeleteCommand();
		command.excute(model);	
		
		
		return "redirect:list";
	}
	
	@RequestMapping("/reply_view")
	public String reply_view(HttpServletRequest request, Model model) {
		
		model.addAttribute("request", request);
		
		command = new BReplyViewCommand();
		command.excute(model);		
		
		return "reply_view";
	}
	
	@RequestMapping("/reply")
	public String reply(HttpServletRequest request, Model model) {
		
		model.addAttribute("request", request);
		
		command = new BReplyCommand();
		command.excute(model);
		
		return "redirect:list";		
	}

}
