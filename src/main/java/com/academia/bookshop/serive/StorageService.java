package com.academia.bookshop.serive;

import org.springframework.web.multipart.MultipartFile;

public interface StorageService {

    String uploadFile(MultipartFile multipartFile);
}
