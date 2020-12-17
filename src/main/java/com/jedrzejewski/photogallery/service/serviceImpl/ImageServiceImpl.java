package com.jedrzejewski.photogallery.service.serviceImpl;

import com.jedrzejewski.photogallery.entity.Gallery;
import com.jedrzejewski.photogallery.entity.Image;
import com.jedrzejewski.photogallery.repository.ImageRepository;
import com.jedrzejewski.photogallery.service.ImageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;

@Service
public class ImageServiceImpl implements ImageService {

    @Value("${images.path}")
    private String finalPath;

    private final ImageRepository imageRepository;

    public ImageServiceImpl(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    @Override
    public void saveImage(MultipartFile[] files, String email, Gallery gallery) throws IOException {
        Path path = null;
        /**
         * the list below should be much longer
         */
        for (MultipartFile file : files) {
            if (Objects.equals(file.getContentType(), "image/jpg")
                    || Objects.equals(file.getContentType(), "image/jpeg")
                    || Objects.equals(file.getContentType(), "image/tiff")
                    || Objects.equals(file.getContentType(), "image/bmp")
                    || Objects.equals(file.getContentType(), "image/png")) {
                try {
                    path = Paths.get(finalPath + email + File.separator + gallery.getName());
                    if (!Files.exists(path)) {
                        Files.createDirectories(path);
                    }
                    Files.write(Paths.get(path + File.separator + file.getOriginalFilename()), file.getBytes());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Path DbPath = Paths.get(email + File.separator + gallery.getName() + File.separator + file.getOriginalFilename());
                Image image = new Image();
                image.setGallery(gallery);
                image.setPath(DbPath.toString());
                imageRepository.save(image);
            }
        }
    }

    @Override
    public List<Image> findAllByGalleryId(Long id) {
        return imageRepository.findAllByGalleryId(id);
    }
}
