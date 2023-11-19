package com.example.intervenction.controllers;

import com.example.intervenction.entities.*;
import com.example.intervenction.services.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
@CrossOrigin("*")
public class DemandeCont {
    private final AuthenticationService authenticationService;
    private final PersonnelServ personnelServ;
    private final DemandeServ demandeServ;
    private final CategorieServ categorieServ;
    private final SouscategorieServ souscategorieServ;
    @Autowired
    public DemandeCont(AuthenticationService authenticationService, DemandeServ demandeServ, PersonnelServ personnelServ, CategorieServ categorieServ, SouscategorieServ souscategorieServ) {
        this.authenticationService = authenticationService;
        this.demandeServ = demandeServ;
        this.categorieServ = categorieServ;
        this.souscategorieServ = souscategorieServ;
        this.personnelServ = personnelServ;
    }

    @GetMapping("/demandes/{id}")
    public String detailDemande(@PathVariable Long id, Model model) {
        Demande demande = demandeServ.getOne(id);
        model.addAttribute("demande", demande);
        return "detail_demande";
    }

   /* @GetMapping("/demandesParDepartement")
    public String demandesParDepartement(Model model, HttpSession session) {
        // Obtenez l'ID du département à partir de la session
        Long departementId = (Long) session.getAttribute("departementId");

        // Récupérez la liste des demandes spécifiques au département
        List<Demande> demandesParDepartement = demandeServ.getDemandesByDepartement(departementId);

        // Ajoutez la liste des demandes à l'objet Model pour qu'elle soit disponible dans la vue
        model.addAttribute("demandesParDepartement", demandesParDepartement);

        return "demandesParDepartement";
    }*/

    @GetMapping("/login")
    public String login() {
        return "login";
    }
    @PostMapping("/login")
    public String login(@RequestParam Long id, @RequestParam String motDePasse, Model model, HttpSession session) {
        // Authentifiez l'utilisateur
        Utilisateur utilisateur = authenticationService.authentifier(id, motDePasse);

        if (utilisateur != null) {
           // session.setAttribute("departementId", utilisateur.getPersonnel().getDepartement().getId());

           session.setAttribute("etudiantId", utilisateur.getId());
            // En fonction du rôle de l'utilisateur, redirigez vers différentes pages
            switch (utilisateur.getRole()) {
                case "Etudiant":
                    return "redirect:/etudiantPage";
                case "Personnel":
                    return "personnelPage";
                default:
                    return "redirect:/defaultPage";
            }
        } else {
            model.addAttribute("error", "Identifiants incorrects");
            return "login";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        // Effectuez toute logique de déconnexion personnalisée ici

        // Invalidez la session
        session.invalidate();

        return "redirect:/login";
    }

    @GetMapping("/etudiantPage")
    public String etudiantPage(Model model,   HttpSession session) {
        // Obtenez l'ID de l'étudiant à partir de l'utilisateur connecté
        Long etudiantId = (Long) session.getAttribute("etudiantId");

        // Vérifiez si l'ID de l'étudiant est récupéré avec succès
        if (etudiantId != null) {
            // Récupérez la liste des demandes spécifiques à l'étudiant
            List<Demande> demandesEtudiant = demandeServ.getDemandesByEtudiantId(etudiantId);

            // Ajoutez la liste des demandes à l'objet Model pour qu'elle soit disponible dans la vue
            model.addAttribute("demandesEtudiant", demandesEtudiant);
        } else {
            // Gérez le cas où l'ID de l'étudiant n'est pas récupéré avec succès
            model.addAttribute("error", "Impossible de récupérer l'ID de l'étudiant.");
        }

        return "etudiantPage";
    }


    @GetMapping("/demandes")
    public String showDemandes(Model model) {
        // Obtenez la liste des demandes à partir du service
        List<Demande> demandes = demandeServ.getAll();

        // Ajoutez la liste des demandes à l'objet Model pour qu'elle soit disponible dans la vue
        model.addAttribute("demandes", demandes);

        // Obtenez la liste des départements, des catégories, et des sous-catégories
        List<Departement> departements = demandeServ.getAllDepartements();
        List<Categorie> categories = categorieServ.getAllCategories();
        List<SousCategorie> sousCategories = souscategorieServ.getAllSousCategories();

        // Ajoutez ces listes à l'objet Model
        model.addAttribute("departements", departements);
        model.addAttribute("categories", categories);
        model.addAttribute("sousCategories", sousCategories);

        // Renvoyez le nom de la vue Thymeleaf
        return "demandes";
    }

   /* @PostMapping("/login")
    public String login(@RequestParam String nomUtilisateur, @RequestParam String motDePasse, HttpSession session) {
        Utilisateur utilisateur = authenticationService.authentifier(nomUtilisateur, motDePasse);

        if (utilisateur != null) {
            // Stockez l'utilisateur dans la session
            session.setAttribute("utilisateurConnecte", utilisateur);

            // Redirigez vers la page d'accueil ou une autre page appropriée
            return "redirect:/accueil";
        } else {
            // Gérez l'échec de l'authentification (peut-être rediriger vers une page de connexion avec un message d'erreur)
            return "redirect:/login?erreur";
        }
    }*/
    @GetMapping("/deconnexion")
    public String deconnexion(HttpSession session) {
        // Supprimez l'utilisateur de la session
        session.removeAttribute("utilisateurConnecte");

        // Redirigez vers la page de connexion ou une autre page appropriée
        return "redirect:/login";
    }
    @GetMapping("/accueil")
    public String accueil(HttpSession session) {
        // Obtenez l'utilisateur de la session
        Utilisateur utilisateur = (Utilisateur) session.getAttribute("utilisateurConnecte");

        if (utilisateur != null) {
            // Votre logique pour la page d'accueil
            return "accueil";
        } else {
            // Redirigez vers la page de connexion si l'utilisateur n'est pas connecté
            return "redirect:/login";
        }
    }

    @GetMapping("/demandes/{id}/prise-en-charge")
    public String prendreEnCharge(@PathVariable Long id, Model model) {
        Demande demande = demandeServ.getOne(id);
      //  List<Personnel> personnelDuDepartement = personnelServ.getPersonnelByDepartementId(demande.getDepartement().getId());

        model.addAttribute("demande", demande);
       // model.addAttribute("personnelDuDepartement", personnelDuDepartement);

        return "priseEnCharge";
    }

    @PostMapping("/priseEnCharge")
    public String priseEnCharge(@RequestParam Long demandeId,
                                @RequestParam Long personnel_id,
                                @RequestParam String commentaire) {
        // Implémentez la logique pour prendre en charge la demande, mettez à jour le statut, enregistrez le commentaire, etc.
        // Utilisez les services nécessaires pour effectuer ces opérations

        return "redirect:/demandes"; // Redirigez vers la page des demandes après la prise en charge
    }

    @PostMapping("/addDem")
    public String addDem(@RequestParam Long etudiant_id,
                         @RequestParam Long departement_id,
                         @RequestParam Long categorie_id,
                         @RequestParam String description,
                         @RequestParam Long sous_categorie_id,
                         @ModelAttribute("demande") Demande demande) {
        try {
            // Retrieve the selected department, category, and sub-category from their IDs
            Departement selectedDepartement = demandeServ.getDepartementById(departement_id);
            Categorie selectedCategorie = categorieServ.getCategorieById(categorie_id);
            SousCategorie selectedSousCategorie = souscategorieServ.getSousCategorieById(sous_categorie_id);

            // Set the selected department, category, and sub-category in the demande
            demande.setDepartement(selectedDepartement);
            demande.setCategorie(selectedCategorie);
            demande.setSousCategorie(selectedSousCategorie);
            demande.setDescription(description);

            // Call the service to add the demande
            String resultat = demandeServ.send(etudiant_id, categorie_id,  departement_id,  sous_categorie_id, description, demande);

            // You can use the result to customize the success/error message in your view
            System.out.println(resultat);

            // Redirect to the demands page after addition
            return "redirect:/demandes";
        } catch (Exception e) {
            // Handle errors and redirect to an error page if necessary
            return "redirect:/demandes";
        }
    }
   /* @GetMapping("/intervention")
    public String getPage(Model model, Principal principal) {
        // Obtenez le nom d'utilisateur de l'utilisateur connecté
        String nomUtilisateur = principal.getName();

        // Obtenez l'utilisateur à partir du service d'authentification
        Utilisateur utilisateur = authenticationService.authentifier(id, "");

        // En fonction du rôle de l'utilisateur, redirigez vers différentes pages
        if (utilisateur != null) {
            switch (utilisateur.getRole()) {
                case "Etudiant":
                    return "redirect:/etudiantPage";
                case "Personnel":
                    return "redirect:/personnelPage";
                default:
                    return "redirect:/defaultPage";
            }
        } else {
            return "redirect:/login"; // Redirigez vers la page de connexion en cas d'échec de l'authentification
        }
    }*/

}
