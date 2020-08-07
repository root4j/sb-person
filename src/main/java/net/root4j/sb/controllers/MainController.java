/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.root4j.sb.controllers;

import net.root4j.sb.entities.Ciudad;
import net.root4j.sb.repositories.CiudadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author rjay
 */
@Controller
public class MainController {
    
    static String ROUTE_URL = "index";

    private final CiudadRepository repository;

    @Autowired
    public MainController(CiudadRepository repository) {
        this.repository = repository;
    }

    @RequestMapping("/")
    public String index(Model model) {
        //model.addAttribute("objeto", new Ciudad());
        //model.addAttribute("listado", repository.findAll());
        return "home";
    }

    @PostMapping("/save")
    public String save(Ciudad objeto, Model model) {
        if (objeto != null) {
            repository.save(objeto);
        }
        model.addAttribute("objeto", new Ciudad());
        model.addAttribute("listado", repository.findAll());
        return "redirect:/";
    }

    @GetMapping("/del/{id}")
    public String delete(@PathVariable("id") String id, Model model) {
        repository.deleteById(id);
        model.addAttribute("objeto", new Ciudad());
        model.addAttribute("listado", repository.findAll());
        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") String id, Model model) {
        model.addAttribute("objeto", repository.findById(id));
        model.addAttribute("listado", repository.findAll());
        return ROUTE_URL;
    }
}
