package com.jedrzejewski.photogallery.controller;

import com.jedrzejewski.photogallery.entity.Gallery;
import com.jedrzejewski.photogallery.entity.User;
import com.jedrzejewski.photogallery.model.CurrentUser;
import com.jedrzejewski.photogallery.model.Data;
import com.jedrzejewski.photogallery.service.GalleryService;
import com.jedrzejewski.photogallery.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/admin")
@AllArgsConstructor
public class AdminController {

    private final UserService userService;
    private final GalleryService galleryService;

    @GetMapping("/panel")
    public String loadPanelAction(@AuthenticationPrincipal CurrentUser currentUser, Model model) {
        model.addAttribute("user", currentUser.getUser());
        return "/admin/panel";
    }

    @GetMapping("/add-user")
    public String addUserGetAction(Model model) {
        model.addAttribute("data", new Data());
        return "/admin/add-user-form";
    }

    @PostMapping("/add-user")
    public String addUserPostAction(@Valid @ModelAttribute Data data, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/admin/add-user-form";
        } else {
            userService.saveUser(data.getUserEmail());
            galleryService.saveGallery(data.getGalleryName(), userService.findByEmail(data.getUserEmail()));
        }
        return "redirect:/admin/panel";
    }
}
