package com.mnghia.dts.service;

import org.springframework.web.multipart.MultipartFile;

public interface IImageService {
    String uploadImage(MultipartFile file);
}
