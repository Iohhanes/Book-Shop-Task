package com.academia.bookshop.serive.impl;

import com.academia.bookshop.properties.AppProperties;
import com.academia.bookshop.serive.StorageService;
import com.google.cloud.storage.Acl;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StorageServiceImpl implements StorageService {
    private final Storage storage;
    private final AppProperties appProperties;

    @Override
    public String uploadFile(MultipartFile multipartFile) {
        AppProperties.StorageProperties storageProperties = appProperties.getStorage();
        String bucketName = storageProperties.getBucketName();

        String fileName = System.nanoTime() + "_" + multipartFile.getOriginalFilename();

        try {
            storage.create(
                    BlobInfo.newBuilder(bucketName, fileName)
                            .setContentType(multipartFile.getContentType())
                            .setAcl(
                                    List.of(
                                            Acl.newBuilder(Acl.User.ofAllUsers(), Acl.Role.READER)
                                                    .build()
                                    )
                            )
                            .build(),
                    multipartFile.getBytes());
            return storageProperties.getDownloadPublicUrl() + "/" + bucketName + "/" + fileName;
        } catch (IOException e) {

        }
        return null;
    }
}
