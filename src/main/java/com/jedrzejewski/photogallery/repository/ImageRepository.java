package com.jedrzejewski.photogallery.repository;

import com.jedrzejewski.photogallery.entity.Gallery;
import com.jedrzejewski.photogallery.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {
    List<Image> findAllByGalleryId(long id);
}
