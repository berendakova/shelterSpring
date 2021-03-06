package ru.itis.springbootdemo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.itis.springbootdemo.dto.OwnerDto;
import ru.itis.springbootdemo.dto.ShelterDto;
import ru.itis.springbootdemo.models.Owner;
import ru.itis.springbootdemo.models.Shelter;
import ru.itis.springbootdemo.repositories.OwnerRepository;
import ru.itis.springbootdemo.repositories.ShelterRepository;
import ru.itis.springbootdemo.service.OwnersService;
import ru.itis.springbootdemo.service.ShelterService;

import java.util.ArrayList;
import java.util.List;

@Controller
public class OwnerSheltersController {

    @Autowired
    ShelterService shelterService;
    @Autowired
    OwnersService ownersService;

    @GetMapping("/shelters")
    @PreAuthorize("permitAll()")

    public String getPage(Authentication authentication, Model model) {
        if (authentication != null) {
            model.addAttribute("authentication", authentication);
        }
        List<ShelterDto> shelters = shelterService.getAllShelter();
        List<OwnerDto> owners = ownersService.getAllOwners();

/*
        List<String> response = new ArrayList<>();
        for (Owner ownerItem : ownerRepository.findAll()){
           response.add( ownerItem.getName());

            for (Shelter shelterItem : shelterRepository.findAll()){
                response.add(shelterItem.getName());
                System.out.println("Название: " + shelterItem.getName());
            }

        }*/

        model.addAttribute("shelters", shelters);
        model.addAttribute("owners", owners);
/*        model.addAttribute("response",response);*/
        return "shelterList";

    }
}
