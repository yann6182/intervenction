package com.example.intervenction.controllers;

import com.example.intervenction.entities.Departement;
import com.example.intervenction.services.DepartementServ;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/departements")
public class DepartementCont {

    private final DepartementServ departementServ;

    public DepartementCont(DepartementServ departementServ) {
        this.departementServ = departementServ;
    }

    @GetMapping("/all")
    public String showDepartements(Model model) {
        // Obtenez la liste des départements à partir du service
        List<Departement> departements = departementServ.getAll();

        // Ajoutez la liste des départements à l'objet Model pour qu'elle soit disponible dans la vue
        model.addAttribute("departements", departements);

        // Renvoyez le nom de la vue Thymeleaf
        return "departements";
    }


    @GetMapping("/{id}")
    public ResponseEntity<Departement> getOneDepartement(@PathVariable Long id) {
        Departement departement = departementServ.getOne(id);
        return ResponseEntity.ok(departement);
    }

    @PostMapping("/add")
    public ResponseEntity<String> addDepartement(@RequestBody Departement departement) {
        String result = departementServ.add(departement);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateDepartement(@PathVariable Long id, @RequestBody Departement departement) {
        String result = departementServ.update(id, departement);
        return ResponseEntity.ok(result);
    }
}
