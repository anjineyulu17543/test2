package controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import dao.StudentDao;
import model.Student;

@Controller
public class SmsController {
	@Autowired
	StudentDao dao;
	
	@RequestMapping("/studentform")
	public String showform(Model m) {
		m.addAttribute("command", new Student());
		return "studentform";
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(@ModelAttribute("std") Student std) {
		dao.save(std);
		return "redirect:/viewstudent";
	}

	@RequestMapping("/viewstudent")
	public String viewemp(Model m) {
		List<Student> list = dao.getAllStudents();
		m.addAttribute("list", list);
		return "viewstudent";
	}

	@RequestMapping(value = "/editstudent/{id}")
	public String edit(@PathVariable int id, Model m) {
		Student std = dao.getEmpById(id);
		m.addAttribute("command", std);
		return "studenteditform";
	}

	@RequestMapping(value = "/editsave", method = RequestMethod.POST)
	public String editsave(@ModelAttribute("std") Student std) {
		dao.update(std);
		return "redirect:/viewstudent";
	}

	@RequestMapping(value = "/deletestudent/{id}", method = RequestMethod.GET)
	public String delete(@PathVariable int id) {
		dao.delete(id);
		return "redirect:/viewstudent";
	}
}