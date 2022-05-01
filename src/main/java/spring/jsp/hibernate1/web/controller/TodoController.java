package spring.jsp.hibernate1.web.controller;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
import spring.jsp.hibernate1.model.Inbox;
import spring.jsp.hibernate1.model.SubTask;
import spring.jsp.hibernate1.model.Tag;
import spring.jsp.hibernate1.model.Todo;
import spring.jsp.hibernate1.service.api.ITodoService;

import javax.annotation.Resource;

@Slf4j
@Controller
@SessionAttributes( types = { Todo.class }) // The Todo is stored in the session in between requests; this due to the use of SessionAttributes.
public class TodoController {

	@Autowired
	private ITodoService todoService;

	@Resource(name = "inbox")
	private Inbox inbox;


	@InitBinder
	public void initBinder(WebDataBinder binder) {
		// Date - dd/MM/yyyy
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}

	@RequestMapping(value = "/list-todos", method = RequestMethod.GET)
	public String showTodos(ModelMap model) {
		String name = getLoggedInUserName(model);
		model.put("todos", todoService.getTodosByUser(name));
		// model.put("todos", service.retrieveTodos(name));
		return "list-todos";
	}

	private String getLoggedInUserName(ModelMap model) {
//		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//
//		if (principal instanceof UserDetails) {
//			return ((UserDetails) principal).getUsername();
//		}

		return "StaticUser";
	}

	@RequestMapping(value = "/add-todo", method = RequestMethod.GET)
	public String showAddTodoPage(ModelMap model) {
		model.addAttribute("todo", new Todo());
		return "todo";
	}

	@RequestMapping(value = "/add-todo-with-subtask", method = RequestMethod.GET)
	public String showAddTodoPageWithSubtask(ModelMap model) {
		Todo todo = new Todo();
		todo.setDescription("todo with subtask");
		model.addAttribute("todo", todo);
		return "todo-super";
	}

	@RequestMapping(value = "/add-subtask", method = RequestMethod.GET)
	public String addSubtaskGet(@ModelAttribute (name = "todo") Todo todo) {
		log.info("add-subtask");
		SubTask task = new SubTask();
		task.setId(todo.getSubTasks().size());
		task.setName("Task "+task.getId());
		todo.getSubTasks().add(task);
		return "todo-super";
	}


	@RequestMapping(value = "/add-todo-with-subtask", method = RequestMethod.POST, params = "add-sbtsk")
	public String addSubtask(@ModelAttribute (name = "todo") Todo todo) {
		log.info("add-subtask");
		SubTask task = new SubTask();
		task.setId(todo.getSubTasks().size());
		task.setName("Task "+task.getId());
		todo.getSubTasks().add(task);
		return "todo-super";
	}

	@RequestMapping(value = "/add-todo-with-subtask", method = RequestMethod.POST)
	public String saveSuperTodo(SessionStatus status, ModelMap model,  Todo todo) {
		log.info("saveSuperTodo: "+todo);
		todo.setUserName(getLoggedInUserName(model));
		todoService.saveTodo(todo);
		status.setComplete(); //When we are finished with the Todo, we need to remove it from the session (see SessionAttributes above) ,
		// which we accomplish by calling the setComplete method on the org.springframework.web.bind.support.SessionStatus object
		return "redirect:/list-todos";
	}

	@RequestMapping(value = "/delete-subtask", method = RequestMethod.GET)
	public String deleteSubtTask(@ModelAttribute (name = "todo") Todo todo, @RequestParam Integer index) {
		log.info("deleteSubtTask");
		todo.getSubTasks().remove(todo.getSubTasks().get(index));
		return "todo-super";
	}



	@ModelAttribute("tags")
	public List<Tag> getCategories() {
		return Arrays.asList(new Tag("tagValue1"),
				new Tag("tagValue2"),
				new Tag("exists")
		);
	}

	@ModelAttribute("inbox")
	public Inbox getInbox() {
		return inbox;
	}


	@RequestMapping(value = "/add-todo-to-inbox", method = RequestMethod.GET)
	public String addTodoToInboxk(@RequestParam long id, ModelMap model) {
		Todo todoById = todoService.getTodoById(id);
		inbox.addTodo(todoById);
		model.addAttribute("inbox", inbox);
		return "redirect:/list-todos";
	}

	@RequestMapping(value = "/clear-inbox", method = RequestMethod.GET)
	public String clearInbox() {
		inbox.clear();
		return "redirect:/list-todos";
	}



	@RequestMapping(value = "/delete-todo", method = RequestMethod.GET)
	public String deleteTodo(@RequestParam long id) {
		todoService.deleteTodo(id);
		// service.deleteTodo(id);
		return "redirect:/list-todos";
	}

	@RequestMapping(value = "/update-todo", method = RequestMethod.GET)
	public String showUpdateTodoPage(@RequestParam long id, ModelMap model) {
		Todo todo = todoService.getTodoById(id);
		model.put("todo", todo);
		return "todo";
	}

	@RequestMapping(value = "/update-super-todo", method = RequestMethod.GET)
	public String showUpdateSuperTodoPage(@RequestParam long id, ModelMap model) {
		Todo todo = todoService.getTodoById(id);
		model.put("todo", todo);
		return "todo-super";
	}


	@RequestMapping(value = "/update-todo", method = RequestMethod.POST)
	public String updateTodo(ModelMap model,  Todo todo, BindingResult result) {

		if (result.hasErrors()) {
			return "todo";
		}

		todo.setUserName(getLoggedInUserName(model));
		todoService.updateTodo(todo);
		return "redirect:/list-todos";
	}

	@RequestMapping(value = "/add-todo", method = RequestMethod.POST)
	public String addTodo(ModelMap model,  Todo todo, BindingResult result) {

		if (result.hasErrors()) {
			System.out.println("Request has errors");
			return "todo";
		}

		todo.setUserName(getLoggedInUserName(model));
		todoService.saveTodo(todo);
		return "redirect:/list-todos";
	}
}