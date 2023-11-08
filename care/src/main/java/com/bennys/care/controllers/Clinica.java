package com.bennys.care.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bennys.care.factories.CachorroFactory;
import com.bennys.care.factories.GatoFactory;
import com.bennys.care.models.Animal;
import com.bennys.care.models.Cachorro;
import com.bennys.care.models.Gato;
import com.bennys.care.models.RelatorioMedico;

@Controller
@RequestMapping("/")
public class Clinica {

    List<Animal> animais;
    List<RelatorioMedico> relatorios;

    public Clinica() {
        this.animais = new ArrayList<Animal>();

        List<Gato> gatos = GatoFactory.gerar(25);
        List<Cachorro> cachorros = CachorroFactory.gerar(25);

        this.animais.addAll(gatos);
        this.animais.addAll(cachorros);

        this.relatorios = new ArrayList<RelatorioMedico>();
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/animais")
    public String listarAnimais(@RequestParam(required = false) Integer offset,
            @RequestParam(required = false) String q, Model model) {

        if (offset == null || offset < 0)
            offset = 0;

        Integer limit = 25;

        List<Animal> totalAnimais = new ArrayList<Animal>();
        List<Animal> animais = new ArrayList<Animal>();

        if (q != null && !q.isBlank()) {
            totalAnimais = this.animais.stream().filter(a -> a.getNome().toLowerCase().contains(q.toLowerCase()))
                    .toList();
            animais = totalAnimais.stream().skip(offset).limit(limit).toList();
        } else {
            totalAnimais = this.animais;
            animais = this.animais.stream().skip(offset).limit(limit).toList();
        }

        model.addAttribute("animais", animais);
        model.addAttribute("offset", offset);
        model.addAttribute("limit", limit);
        model.addAttribute("total", totalAnimais.size());
        model.addAttribute("back", offset - limit < 0 ? 0 : offset - limit);
        model.addAttribute("next", offset + limit >= totalAnimais.size() ? offset : offset + limit);
        model.addAttribute("q", q);

        return "animais";
    }

    @GetMapping("/animais/{id}")
    public String detalhesAnimal(@PathVariable String id, Model model) {
        Animal animal = this.animais.stream().filter(a -> a.getId().equals(id)).findFirst().get();

        List<RelatorioMedico> relatorios = this.relatorios.stream().filter(r -> r.getAnimalId().equals(id))
                .sorted((a, b) -> b.getDataHora().compareTo(a.getDataHora()))
                .toList();

        model.addAttribute("animal", animal);
        model.addAttribute("relatorios", relatorios);
        return "animal";
    }

    @GetMapping("/form-animal")
    public String formAnimal(@RequestParam(required = false) String id, Model model) {

        Animal animal = this.animais.stream().filter(a -> a.getId().equals(id)).findFirst().orElse(null);

        if (animal != null) {
            model.addAttribute("nome", animal.getNome());
            model.addAttribute("dono", animal.getDono());
            model.addAttribute("especie", animal.getEspecie());
            model.addAttribute("action", "/animais/" + id);
        } else {
            model.addAttribute("nome", "");
            model.addAttribute("dono", "");
            model.addAttribute("especie", "");
            model.addAttribute("action", "/animais");
        }

        return "animal-form";
    }

    @PostMapping("/animais")
    public String cadastrarAnimal(Animal animal) {
        this.animais.add(animal);
        return "redirect:animais";
    }

    @PostMapping("/animais/{id}")
    public String atualizarAnimal(@PathVariable String id, Animal animal) {
        Animal animalAtualizado = this.animais.stream().filter(a -> a.getId().equals(id)).findFirst().get();

        animalAtualizado.setNome(animal.getNome());
        animalAtualizado.setDono(animal.getDono());
        animalAtualizado.setEspecie(animal.getEspecie());

        return "redirect:/animais/" + id;
    }

    @PostMapping("/excluir-animal")
    public String removerAnimal(String id) {
        Animal animal = this.animais.stream().filter(a -> a.getId().equals(id)).findFirst().get();
        this.animais.remove(animal);
        return "redirect:animais";
    }

    @PostMapping("/relatorios")
    public String cadastrarRelatorio(
            @RequestHeader(value = HttpHeaders.REFERER, required = false) final String referrer,
            RelatorioMedico relatorio) {
        this.relatorios.add(relatorio);

        return "redirect:" + referrer;
    }

}