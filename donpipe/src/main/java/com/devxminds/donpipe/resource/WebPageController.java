package com.devxminds.donpipe.resource;

import com.devxminds.donpipe.dto.UserDto;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.devxminds.donpipe.dto.EmpresaDto;
import com.devxminds.donpipe.service.*;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class WebPageController {

    @Autowired
    private EmpresaService empresaService;
    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public ModelAndView login() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("login"); // Define o nome da view
        mv.addObject("user", new UserDto());
        mv.addObject("userlogin", new UserDto());
        List<EmpresaDto> empresas = empresaService.getAll();
        mv.addObject("empresas", empresas);
        return mv;
    }
    @GetMapping("/index")
    public ModelAndView home() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("index"); // Define o nome da view
        return mv;
    }
}
