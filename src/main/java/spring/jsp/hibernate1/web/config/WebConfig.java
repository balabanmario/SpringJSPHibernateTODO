package spring.jsp.hibernate1.web.config;

import org.springframework.context.annotation.*;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.annotation.SessionScope;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.UrlBasedViewResolver;
import spring.jsp.hibernate1.model.Inbox;

@EnableWebMvc // Enables @Controller, @RequestMapping, @NumberFormat, @DateTimeFormat
@Configuration
@ComponentScan(basePackages = "spring.jsp.hibernate1")
@PropertySource("classpath:application.properties")
public class WebConfig extends WebMvcConfigurerAdapter {

  public static final String RESOLVER_PREFIX = "/WEB-INF/view/";
  public static final String RESOLVER_SUFFIX = ".jsp";

  @Bean
  public ViewResolver viewResolver() {
    UrlBasedViewResolver viewResolver = new InternalResourceViewResolver();
    viewResolver.setPrefix(RESOLVER_PREFIX);
    viewResolver.setSuffix(RESOLVER_SUFFIX);
    return viewResolver;
  }

  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
    registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
  }

  @Bean
  @SessionScope
  public Inbox inbox() {
    return new Inbox();
  }

}

