package com.jedrzejewski.photogallery.controller;

import com.jedrzejewski.photogallery.entity.Gallery;
import com.jedrzejewski.photogallery.entity.User;
import com.jedrzejewski.photogallery.model.CurrentUser;
import com.jedrzejewski.photogallery.model.Data;
import com.jedrzejewski.photogallery.service.GalleryService;
import com.jedrzejewski.photogallery.service.ImageService;
import com.jedrzejewski.photogallery.service.MailService;
import com.jedrzejewski.photogallery.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/admin")
@AllArgsConstructor
public class AdminController {

    private final UserService userService;
    private final GalleryService galleryService;
    private final ImageService imageService;
    private final MailService mailService;

    @GetMapping("/panel")
    public String loadPanelAction(@AuthenticationPrincipal CurrentUser currentUser, Model model) {
        model.addAttribute("user", currentUser.getUser());
        return "/admin/panel";
    }

    @GetMapping("/add-user")
    public String addUserGetAction(@AuthenticationPrincipal CurrentUser currentUser, Model model) {
        model.addAttribute("data", new Data());
        model.addAttribute("user", currentUser.getUser());
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
        model.addAttribute("user", currentUser.getUser());
        return "/admin/upload-form";
    }

    @PostMapping("/add-photos")
    public String addImagesPostAction(@AuthenticationPrincipal CurrentUser currentUser,
                                      @RequestParam MultipartFile[] files, @ModelAttribute Gallery gallery) throws IOException {
        String email = gallery.getUser().getEmail();
        imageService.saveImage(files, email, gallery);
        return "redirect:/admin/panel";
    }

    @GetMapping("/user-details/{id}")
    public String userDetailsAction(@AuthenticationPrincipal CurrentUser currentUser,
                                    @PathVariable long id, Model model) {
        Gallery currentGallery = galleryService.findByUserId(id);
        model.addAttribute("currentUser", userService.findUserById(id));
        model.addAttribute("imagesSize", imageService.findAllByGalleryId(currentGallery.getId()).size());
        model.addAttribute("user", currentUser.getUser());
        return "/admin/user-details";
    }

    @GetMapping("/show-photos/{id}")
    public String showImagesInAdminPanelAction(@AuthenticationPrincipal CurrentUser currentUser,
                                               @PathVariable long id, Model model) {
        model.addAttribute("user", currentUser.getUser());
        Gallery currentGallery = galleryService.findByUserId(id);
        model.addAttribute("images", imageService.findAllByGalleryId(currentGallery.getId()));
        return "/admin/images";
    }

    @GetMapping("/generate-pass/{id}")
    public String generatePasswordForUserAction(@PathVariable long id) throws MessagingException {
        User user = userService.findUserById(id);
        String password = userService.generatePassword();
        userService.editPassword(id, password);
        mailService.sendGeneratedPassword(user.getEmail(), "Hasło do Twojej galerii",
                "<h3>Twoje hasło:</h3><h2> " + password +
                        " </h2><h5>Aby obejrzeć swoją galerię przy logowaniu podaj swój adres email oraz powyższe hasło</h5>");
        return "redirect:/admin/panel";
    }

    @ModelAttribute("users")
    public List<User> listOfUsers() {
        return userService.findSorted();
    }
}
