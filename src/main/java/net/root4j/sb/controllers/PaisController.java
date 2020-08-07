/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.root4j.sb.controllers;

import net.root4j.sb.entities.Pais;
import net.root4j.sb.repositories.PaisRepository;
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
public class PaisController {

    static final String ROUTE_URL = "/pais";
    static String ROUTE_STR = "pais";

    private final PaisRepository repository;

    @Autowired
    public PaisController(PaisRepository repository) {
        this.repository = repository;
    }

    @RequestMapping(ROUTE_URL)
    public String index(Model model) {
        model.addAttribute("objeto", new Pais());
        model.addAttribute("listado", repository.findAll());
        return ROUTE_STR;
    }

    @PostMapping(ROUTE_URL + "/save")
    public String save(Pais objeto, Model model) {
        if (objeto != null) {
            repository.save(objeto);
        }
        model.addAttribute("objeto", new Pais());
        model.addAttribute("listado", repository.findAll());
        return "redirect:" + ROUTE_URL;
    }

    @GetMapping(ROUTE_URL + "/del/{id}")
    public String delete(@PathVariable("id") String id, Model model) {
        repository.deleteById(id);
        model.addAttribute("objeto", new Pais());
        model.addAttribute("listado", repository.findAll());
        return "redirect:" + ROUTE_URL;
    }

    @GetMapping(ROUTE_URL + "/edit/{id}")
    public String edit(@PathVariable("id") String id, Model model) {
        model.addAttribute("objeto", repository.findById(id));
        model.addAttribute("listado", repository.findAll());
        return ROUTE_STR;
    }

}
