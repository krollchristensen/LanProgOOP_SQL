package com.springdemo.lanprogoop_sql.Controller;

import com.springdemo.lanprogoop_sql.Entity.Language;
import com.springdemo.lanprogoop_sql.Service.LanguageService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * Controller for handling requests related to the Language entity.
 */
@Controller
public class LanguageController {
    private final LanguageService languageService;

    /**
     * Constructs a LanguageController with the specified LanguageService.
     *
     * @param languageService The service used for handling language data.
     */
    public LanguageController(LanguageService languageService) {
        this.languageService = languageService;
    }

    /**
     * Displays a list of all languages.
     *
     * @param model The model to which language data is added.
     * @return The name of the view to render.
     */
    @GetMapping("/")
    public String showLanguages(Model model) {
        model.addAttribute("languages", languageService.findAll());
        return "index";
    }

    /**
     * Displays a form to add a new language.
     *
     * @param model The model to which a new Language object is added.
     * @return The name of the view to render.
     */
    @GetMapping("/language/add")
    public String showAddForm(Model model) {
        model.addAttribute("language", new Language());
        return "add-language";
    }

    /**
     * Displays a form to edit an existing language.
     *
     * @param id The ID of the language to edit.
     * @param model The model to which language data is added.
     * @return The name of the view to render.
     */
    @GetMapping("/language/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        languageService.findById(id).ifPresent(language -> model.addAttribute("language", language));
        return "edit-language";
    }

    /**
     * Saves a language to the database. It can handle both new language creation and existing language update.
     *
     * @param language The language to be saved.
     * @return Redirects to the home page.
     */
    @PostMapping("/language/save")
    public String saveLanguage(@ModelAttribute Language language) {
        languageService.save(language);
        return "redirect:/";
    }

    /**
     * Deletes a language from the database.
     *
     * @param id The ID of the language to delete.
     * @return Redirects to the home page.
     */
    @GetMapping("/language/delete/{id}")
    public String deleteLanguage(@PathVariable Long id) {
        languageService.delete(id);
        return "redirect:/";
    }
}
