package spring.jsp.hibernate1.service.impl;

import org.springframework.stereotype.Service;
import spring.jsp.hibernate1.service.api.DemoService;

@Service
public class DemoServiceImpl implements DemoService {
    @Override
    public String getHelloMessage(String user) {
        return "Hello " + user;
    }

    @Override
    public String getWelcomeMessage() {
        return "Welcome to this Demo application.";
    }
}