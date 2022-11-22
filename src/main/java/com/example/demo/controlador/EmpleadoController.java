package com.example.demo.controlador;

import com.example.demo.Dominio;
import com.example.demo.servicios.EmpleadoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.net.MalformedURLException;

@Controller
public class EmpleadoController {

    @Autowired
    private EmpleadoServicio servicio;


    @GetMapping({"empleado/list"})
    public String listado (Model model) {

        model.addAttribute("mensaje","Ramoncín esto es una prueba");
        model.addAttribute("dominio","dom.getDominio()");
        model.addAttribute("tld","dom.getTld()");
        model.addAttribute("pagina","list");
        model.addAttribute("listaEmpleados",servicio.findAll());
        return "index";
    }

}
