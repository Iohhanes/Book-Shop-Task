package com.academia.bookshop.service.impl;

import com.academia.bookshop.properties.AppProperties;
import com.academia.bookshop.service.StorageService;
import com.google.cloud.storage.Acl;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.academia.bookshop.constants.UrlConstants.FORWARD_SLASH;

@Service
@RequiredArgsConstructor
public class StorageServiceImpl implements StorageService {
    private final Storage storage;
    private final AppProperties appProperties;

    @Override
    public String upload(MultipartFile multipartFile) throws IOException {
        String fileName = System.nanoTime() + multipartFile.getOriginalFilename();

        storage.create(
                BlobInfo.newBuilder(appProperties.getStorage().getBucketName(), fileName)
                        .setContentType(multipartFile.getContentType())
                        .setAcl(
                                List.of(
                                        Acl.newBuilder(Acl.User.ofAllUsers(), Acl.Role.READER)
                                                .build()
                                )
                        )
                        .build(),
                multipartFile.getBytes());
        return buildFileUrl(fileName);
    }

    @Override
    public void delete(String... fileUrls) {
        if (fileUrls == null || fileUrls.length == 0) {
            return;
        }

        storage.delete(
                Arrays.stream(fileUrls)
                        .map(fileUrl -> BlobId.of(
                                        appProperties.getStorage().getBucketName(),
                                        fileUrl.substring(buildBucketUrl().length() + 1)
                                )
                        ).collect(Collectors.toList())
        );
    }

    private String buildFileUrl(String fileName) {
        return buildBucketUrl() + FORWARD_SLASH + fileName;
    }

    private String buildBucketUrl() {
        AppProperties.StorageProperties storageProperties = appProperties.getStorage();
        return storageProperties.getDownloadPublicUrl()
                + FORWARD_SLASH
                + storageProperties.getBucketName();
    }
}
