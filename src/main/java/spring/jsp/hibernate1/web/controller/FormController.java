package spring.jsp.hibernate1.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import spring.jsp.hibernate1.model.Todo;

import java.util.Arrays;
import java.util.List;

@Slf4j
@Controller
public class FormController {

  @RequestMapping(value = "/forms/simple/{myObjId}", method = RequestMethod.GET)
  public String myActionGet(
      @PathVariable(name = "myObjId") long myObjId, @RequestParam(required = false) String title, Model model) {
    log.info("Get myObjId = " + myObjId + ", title = " + title + ", model = " + model);
    Todo todo = new Todo();
    todo.setDescription(title + " from GET");
    todo.setId(myObjId);
    model.addAttribute(todo);
    return "forms/simple";
  }

  @RequestMapping(value = "/forms/simple/{myObjId}", method = RequestMethod.POST)
  public String myActionPost(
      @PathVariable(name = "myObjId") long myObjId, Todo todo, Model model) {
    log.info("Post myObjId = " + myObjId + ", todo = " + todo + ", model = " + model);
    todo.setDescription("new Description from Post");
    todo.setId(myObjId);
    model.addAttribute(todo);
    return "forms/simple";
  }

  @RequestMapping(value = "/forms/simple-add", method = RequestMethod.GET)
  public ModelAndView myActionAddNew() {
    log.info("Get myActionAddNew");
    Todo todo = new Todo();
    todo.setDescription(" from myActionAddNew");
    todo.setId(-1);
    ModelAndView mv = new ModelAndView("forms/simple", "brandNew", todo);
    return mv;
  }


  @RequestMapping(value = "/forms/anAction", method = RequestMethod.POST)
  public String  anAction2(@Validated @ModelAttribute("brandNew") Todo todo, Model model, BindingResult result) {
    log.info("POST anAction: " + todo);
    if (todo.getDescription().equals("")) {
      log.error("Error for object "+todo);
      result.rejectValue("description","error.user", "Please select a value");
    }
    todo.setId(-1);
    model.addAttribute(todo);
    model.addAttribute("brandNew", todo);

    if (result.hasErrors()) {
      return "forms/simple";
    }
    return "forms/simple";
  }

  @ModelAttribute("criteriaList")
  public List<TodSearchCriteria> getCriteria() {
    return Arrays.asList(new TodSearchCriteria(1, "First"),
        new TodSearchCriteria(2, "Second")
    );
  }

  @ModelAttribute("tags")
  public List<Tag> getCategories() {
    return Arrays.asList(new Tag("tagValue1"),
        new Tag("tagValue2"),
        new Tag("exists")
    );
  }

//  @InitBinder
//  public void initBinder(WebDataBinder binder) {
////    binder.setRequiredFields("description");
//    binder.setValidator(new TodoValidator());
//  }

   public class Tag {
    private final String value;

    public Tag(String val) {
      this.value = val;
    }

    public String getValue() {
      return value;
    }
  }

  public class TodSearchCriteria {
    private long id;
    private String name;

    public long getId() {
      return id;
    }

    public void setId(long id) {
      this.id = id;
    }

    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }

    public TodSearchCriteria() {
    }

    public TodSearchCriteria(long id, String name) {
      this.id = id;
      this.name = name;
    }
  }

  public class TodoValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
      return (Todo.class).isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
      if (target != null) {
        Todo todo = (Todo) target;
        if ("exists".equals(todo.getDescription())) {
          errors.rejectValue("description", "alreadyExists","This description exists" );
        }

      }
    }
  }


}
