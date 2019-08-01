package controller;

import model.jobs.HHRU;


import model.jobs.JobSite;
import model.jobs.SuperJob;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
public class pageController {
    private static String[] jobsaits;


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView allFilms() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("mainPage");
        return modelAndView;
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ModelAndView allFilmss(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("mainPage");

        jobsaits = request.getParameterValues("websait");

        JobSite hhru = new SuperJob();
        hhru.setProf(request.getParameter("profession"));
        hhru.setEducation(request.getParameter("education"));
        hhru.setKeySkills(request.getParameterValues("skill"));
        hhru.setExperience(request.getParameter("experience"));
        List<String> resultLinks = hhru.organizationLinks();
        modelAndView.addObject("resultList", resultLinks);
        return modelAndView;
    }

    /*@RequestMapping(value = "/postUser", method = RequestMethod.POST)
    public ModelAndView editPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("postuser");
        return modelAndView;
    }*/


}
