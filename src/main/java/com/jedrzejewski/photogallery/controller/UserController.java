package com.jedrzejewski.photogallery.controller;

import com.jedrzejewski.photogallery.model.CurrentUser;
import com.jedrzejewski.photogallery.service.GalleryService;
import com.jedrzejewski.photogallery.service.ImageService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {

    private final ImageService imageService;
    private final GalleryService galleryService;

    @GetMapping("/panel")
    public String loadPanelAction(@AuthenticationPrincipal CurrentUser currentUser, Model model) {
        model.addAttribute("user", currentUser.getUser());
        Long id = galleryService.findByUserId(currentUser.getUser().getId()).getId();
        model.addAttribute("images", imageService.findAllByGalleryId(id));
        return "/user/panel";
    }
}
