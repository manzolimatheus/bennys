package com.bennys.care.controllers;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.tomcat.util.json.JSONParser;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bennys.care.factories.CachorroFactory;
import com.bennys.care.factories.GatoFactory;
import com.bennys.care.models.Animal;
import com.bennys.care.models.AnimalFormDTO;
import com.bennys.care.models.Cachorro;
import com.bennys.care.models.Gato;
import com.bennys.care.models.RelatorioMedico;
import com.fasterxml.jackson.databind.ObjectMapper;

import ch.qos.logback.classic.encoder.JsonEncoder;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

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
    public String index(Model model) {
        List<Animal> animais = this.animais;
        Integer qtdDog = 0;
        Integer qtdCat = 0;
        List<String> meses = new ArrayList<String>(Arrays.asList("Setembro", "Outubro", "Novembro", "Dezembro"));
        List<retornoPerMonth> retornoList = new ArrayList<retornoPerMonth>();
        String maisAtendidos = "";

        for (Animal animal : animais) {
            if (animal instanceof Cachorro) {
                qtdDog++;
                continue;
            } else if (animal instanceof Gato) {
                qtdCat++;
                continue;
            }
        }
        if(qtdDog > qtdCat){
            maisAtendidos = "Cachorros 🐶";
        }else if(qtdCat > qtdDog){
            maisAtendidos = "Gatos 🐈";
        }else{
            maisAtendidos = "Gatos 🐈 e Cachorros 🐶";
        }

        for (Integer i = 0; i <= 3; i++) {
            retornoPerMonth parcial = new retornoPerMonth();
            parcial.mes = meses.get(i);
            parcial.qtdConsulta = i * 50; 
            retornoList.add(parcial);
        }
        retornoToGraph retorno = new retornoToGraph();
        retorno.consultas = retornoList;

        model.addAttribute("consultasMonth", retorno);
        model.addAttribute("qtdDog", qtdDog);
        model.addAttribute("qtdCat", qtdCat);
        model.addAttribute("qtdAtendimentos", this.animais.size() - 50);
        model.addAttribute("maisAtendidos", maisAtendidos);
        
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
            System.out.println(animal.toString());
            model.addAttribute("nome", animal.getNome());
            model.addAttribute("dono", animal.getDono());
            model.addAttribute("especie", animal.getEspecie());
            model.addAttribute("action", "/animais/" + id);
            model.addAttribute("tipoPelagem", animal instanceof Gato ? ((Gato) animal).getTipoPelagem() : "");
            model.addAttribute("raca", animal instanceof Cachorro ? ((Cachorro) animal).getRaca() : "");
            model.addAttribute("id", id);
        } else {
            model.addAttribute("nome", "");
            model.addAttribute("dono", "");
            model.addAttribute("especie", "");
            model.addAttribute("action", "/animais");
            model.addAttribute("tipoPelagem", "");
            model.addAttribute("raca", "");
            model.addAttribute("id", "");
        }

        return "animal-form";
    }

    @PostMapping("/animais")
    public String cadastrarAnimal(AnimalFormDTO animal) {

        switch (animal.getEspecie()) {
            case "Gato":
                Gato gato = new Gato(animal.getNome(), animal.getDono(), animal.getTipoPelagem());
                this.animais.add(gato);
                break;
            case "Cachorro":
                Cachorro cachorro = new Cachorro(animal.getNome(), animal.getDono(), animal.getRaca());
                this.animais.add(cachorro);
                break;
            default:
                Animal animalRaw = new Animal(animal.getNome(), animal.getDono(), animal.getEspecie());
                this.animais.add(animalRaw);
                break;
        }

        return "redirect:animais";
    }

    @PostMapping("/animais/{id}")
    public String atualizarAnimal(@PathVariable String id, AnimalFormDTO animal) {
        Animal animalAtualizado = this.animais.stream().filter(a -> a.getId().equals(id)).findFirst().get();

        animalAtualizado.setNome(animal.getNome());
        animalAtualizado.setDono(animal.getDono());
        animalAtualizado.setEspecie(animal.getEspecie());

        switch (animal.getEspecie()) {
            case "Gato":
                ((Gato) animalAtualizado).setTipoPelagem(animal.getTipoPelagem());
                break;
            case "Cachorro":
                ((Cachorro) animalAtualizado).setRaca(animal.getRaca());
                break;
            default:
                break;
        }

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

    public class retornoToGraph {
        public List<retornoPerMonth> consultas;
    }

    public class retornoPerMonth {
        public String mes;
        public Integer qtdConsulta;
    }
}