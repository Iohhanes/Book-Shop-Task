package com.academia.bookshop.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface StorageService {

    String upload(MultipartFile multipartFile) throws IOException;

    void delete(String... fileUrls);
}
