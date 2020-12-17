package com.jedrzejewski.photogallery.controller;

import com.jedrzejewski.photogallery.entity.Gallery;
import com.jedrzejewski.photogallery.entity.Image;
import com.jedrzejewski.photogallery.entity.User;
import com.jedrzejewski.photogallery.model.CurrentUser;
import com.jedrzejewski.photogallery.model.Data;
import com.jedrzejewski.photogallery.service.GalleryService;
import com.jedrzejewski.photogallery.service.ImageService;
import com.jedrzejewski.photogallery.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/admin")
@AllArgsConstructor
public class AdminController {

    private final UserService userService;
    private final GalleryService galleryService;
    private final ImageService imageService;

    @GetMapping("/panel")
    public String loadPanelAction(@AuthenticationPrincipal CurrentUser currentUser, Model model) {
        model.addAttribute("user", currentUser.getUser());
        return "/admin/panel";
    }

    @GetMapping("/add-user")
    public String addUserGetAction(@AuthenticationPrincipal CurrentUser currentUser, Model model) {
        model.addAttribute("data", new Data());
        return "/admin/add-user-form";
    }

    @PostMapping("/add-user")
    public String addUserPostAction(@AuthenticationPrincipal CurrentUser currentUser,
                                    @Valid @ModelAttribute Data data, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/admin/add-user-form";
        } else {
            userService.saveUser(data.getUserEmail());
            galleryService.saveGallery(data.getGalleryName(), userService.findByEmail(data.getUserEmail()));
        }
        return "redirect:/admin/panel";
    }

    @GetMapping("add-photos/{id}")
    public String addImagesGetAction(@AuthenticationPrincipal CurrentUser currentUser,
                                     @PathVariable long id, Model model) {
        model.addAttribute("gallery", galleryService.findByUserId(id));
        return "/admin/upload-form";
    }

    @PostMapping("/add-photos")
    public String addImagesPostAction(@AuthenticationPrincipal CurrentUser currentUser,
                                      @RequestParam MultipartFile[] files, @ModelAttribute Gallery gallery) throws IOException {
        String email = gallery.getUser().getEmail();
        for (MultipartFile file : files) {
            imageService.saveImage(file, email, gallery);
        }
        return "redirect:/admin/panel";
    }

    @GetMapping("/user-details/{id}")
    public String userDetailsAction(@AuthenticationPrincipal CurrentUser currentUser,
                                    @PathVariable long id, Model model) {
        model.addAttribute("currentUser", userService.findUserById(id));
        model.addAttribute("user", currentUser.getUser());
        return "/admin/user-details";
    }

    @ModelAttribute("users")
    public List<User> listOfUsers () {
        return userService.findAllUsers();
    }
}
