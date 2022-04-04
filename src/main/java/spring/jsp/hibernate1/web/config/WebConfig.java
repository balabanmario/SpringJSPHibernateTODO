package spring.jsp.hibernate1.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.UrlBasedViewResolver;

@EnableWebMvc
@Configuration
@ComponentScan(basePackages = "spring.jsp.hibernate1")
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
  // Non shown code
}

