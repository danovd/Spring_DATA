package hiberspring.web.controllers;

import org.springframework.web.servlet.ModelAndView;

public abstract class BaseController {

    public ModelAndView view(String viewName) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("fragments/base-layout");
        modelAndView.addObject("view", viewName);

        return modelAndView;
    }

    public ModelAndView view(String viewName, String objectName, Object object) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("fragments/base-layout");
        modelAndView.addObject("view", viewName);
        modelAndView.addObject(objectName, object);

        return modelAndView;
    }

    public ModelAndView redirect(String url) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:" + url);

        return modelAndView;
    }
}
