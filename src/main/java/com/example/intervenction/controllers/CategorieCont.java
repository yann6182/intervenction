package com.example.intervenction.controllers;



import org.springframework.ui.Model;
import com.example.intervenction.entities.Categorie;
import com.example.intervenction.entities.Demande;
import com.example.intervenction.entities.Departement;
import com.example.intervenction.entities.SousCategorie;
import com.example.intervenction.services.CategorieServ;
import com.example.intervenction.services.DemandeServ;
import com.example.intervenction.services.SouscategorieServ;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@CrossOrigin("*")
@RequestMapping("/categories")
public class CategorieCont {

    private final CategorieServ categorieServ;
    private final SouscategorieServ souscategorieServ;

    public CategorieCont(CategorieServ categorieServ,SouscategorieServ souscategorieServ) {
        this.categorieServ = categorieServ;
        this.souscategorieServ = souscategorieServ;
    }

    @GetMapping("/demandes")
    public String showDemandes(Model model) {
        // Obtenez la liste des catégories à partir du service
        List<Categorie> categories = categorieServ.getAllCategories();

        // Ajoutez la liste des catégories à l'objet Model pour qu'elle soit disponible dans la vue
        model.addAttribute("categories", categories);

        // Obtenez la liste des sous-catégories
        List<SousCategorie> sousCategories = souscategorieServ.getAllSousCategories();

        // Ajoutez ces listes à l'objet Model
        model.addAttribute("sousCategories", sousCategories);

        // Renvoyez le nom de la vue Thymeleaf
        return "demandes";
    }
    @GetMapping("/all")
    public List<Categorie> getAllCategories() {
        return categorieServ.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Categorie> getOneCategory(@PathVariable Long id) {
        Categorie categorie = categorieServ.getOne(id);
        return ResponseEntity.ok(categorie);
    }

    @PostMapping("/add")
    public ResponseEntity<String> addCategory(@RequestBody Categorie categorie) {
        String result = categorieServ.add(categorie);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateCategory(@PathVariable Long id, @RequestBody Categorie categorie) {
        String result = categorieServ.update(id, categorie);
        return ResponseEntity.ok(result);
    }
}
