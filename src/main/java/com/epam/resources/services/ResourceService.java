package com.epam.resources.services;

import com.epam.resources.api.CreateResourceRequest;
import com.epam.resources.configs.StorageProperties;
import com.epam.resources.services.exceptions.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.core.ResponseBytes;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.core.sync.ResponseTransformer;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest;
import software.amazon.awssdk.services.s3.model.DeleteObjectResponse;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectResponse;
import software.amazon.awssdk.services.s3.model.ListObjectsV2Request;
import software.amazon.awssdk.services.s3.model.ListObjectsV2Response;
import software.amazon.awssdk.services.s3.model.NoSuchKeyException;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.S3Object;

import java.util.List;

@Service
@AllArgsConstructor
public class ResourceService {

    private final S3Client s3Client;
    private final StorageProperties storageProperties;
    private final KeyGenerator keyGenerator;

    public List<S3Object> getResources() {
        ListObjectsV2Request listObjectsRequest = ListObjectsV2Request.builder()
                .bucket(storageProperties.getBucketName())
                .build();
        ListObjectsV2Response listObjectsResponse = s3Client.listObjectsV2(listObjectsRequest);
        return listObjectsResponse.contents();
    }

    public byte[] getResource(String id) {
        GetObjectRequest request = GetObjectRequest.builder()
                .bucket(storageProperties.getBucketName())
                .key(id)
                .build();
        ResponseBytes<GetObjectResponse> response;
        try {
            response = s3Client.getObject(request, ResponseTransformer.toBytes());
        } catch (NoSuchKeyException e) {
            throw new EntityNotFoundException(e.getMessage());
        }
        return response.asByteArray();
    }

    public String createResource(CreateResourceRequest createRequest) {
        String key = keyGenerator.generateKey();
        PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                .bucket(storageProperties.getBucketName())
                .key(key)
                .build();
        s3Client.putObject(putObjectRequest, RequestBody.fromString(createRequest.getResource()));
        return key;
    }

    public String deleteResource(String key) {
        DeleteObjectRequest deleteRequest = DeleteObjectRequest.builder()
                .bucket(storageProperties.getBucketName())
                .key(key)
                .build();
        s3Client.deleteObject(deleteRequest);
        return key;
    }
}
