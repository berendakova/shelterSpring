package ru.itis.springbootdemo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.itis.springbootdemo.dto.PetDto;
import ru.itis.springbootdemo.models.StatusPet;
import ru.itis.springbootdemo.service.PetsService;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PetsListController {

    @Autowired
    private PetsService petsService;

    @GetMapping("/pets")
    public String getPage(Model model){
        List<PetDto> pets = new ArrayList<>();
        List<PetDto> petsAll = petsService.getAllPets();
        for (int i = 0; i < petsAll.size(); i++) {
            if (petsAll.get(i).getStatus().equals(StatusPet.SHELTER)){
                pets.add(petsAll.get(i));
            }
        }
        model.addAttribute("pets", pets);
        return "petsList";
    }


}
