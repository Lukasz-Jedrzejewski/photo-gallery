package com.jedrzejewski.photogallery.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
public class Data {
    @Email
    @NotBlank
    private String userEmail;
    @NotBlank
    private String galleryName;
}
