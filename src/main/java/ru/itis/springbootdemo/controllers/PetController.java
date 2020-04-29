package ru.itis.springbootdemo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.util.UriComponentsBuilder;
import ru.itis.springbootdemo.dto.CreatedPetDto;
import ru.itis.springbootdemo.dto.PetDto;
import ru.itis.springbootdemo.dto.SignUpDto;
import ru.itis.springbootdemo.exceptions.NotCorrectSamePassword;
import ru.itis.springbootdemo.models.Pet;
import ru.itis.springbootdemo.repositories.PetsRepository;
import ru.itis.springbootdemo.service.CreatePetService;
import ru.itis.springbootdemo.service.PetsService;
import ru.itis.springbootdemo.service.UsersService;

import java.util.Optional;

@Controller
public class PetController {
    @Autowired
    private CreatePetService createPetService;

    @Autowired
    private PetsService petsService;


    @GetMapping("/pets/created")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String getCreatedPage(Authentication authentication) {
        return "petFormCreate";

    }

    @PostMapping("/pets/created")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String createdHandler(CreatedPetDto form, Model model, Authentication authentication) {
        String nullField = "Fields can't be null";
        if(form.getAge().equals("")||form.getBreed().equals("")||form.getDescription().equals("")||
                form.getName().equals("")||form.getImg().equals("")||form.getDisease().equals("")||form.getSex().equals("")
        ||form.getSex().equals("Choosing...")){

                model.addAttribute("nullEx",nullField);
                return "petFormCreate";
        }
        if(authentication!=null){
            model.addAttribute("authentication",authentication);
        }
        createPetService.created(form);
        return "shelter";
    }

    @PostMapping("/pets/{pet-id}/delete")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String deletePet(@PathVariable("pet-id") Integer petId) {
        petsService.deletePet(petId);
        return "redirect:/shelter";
    }

    @GetMapping("/pets/{pet-id}/delete")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String deletePetG(@PathVariable("pet-id") Integer petId) {
        petsService.deletePet(petId);
        return "redirect:/shelter";
    }


}
