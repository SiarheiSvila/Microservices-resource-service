package com.epam.resources.services;

import com.epam.resources.api.CreateResourceRequest;
import com.epam.resources.configs.StorageProperties;
import com.epam.resources.entities.Resource;
import com.epam.resources.repositories.ResourceJpaRepository;
import com.epam.resources.services.exceptions.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.core.ResponseBytes;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.core.sync.ResponseTransformer;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectResponse;
import software.amazon.awssdk.services.s3.model.NoSuchKeyException;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.IOException;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ResourceService {

    private final S3Client s3Client;
    private final ResourceJpaRepository resourceJpaRepository;
    private final StorageProperties storageProperties;
    private final KeyGenerator keyGenerator;

    public byte[] getResource(long id) {
        Optional<Resource> resource = resourceJpaRepository.findById(id);
        String key = resource.orElseThrow(EntityNotFoundException::new).getKey();
        GetObjectRequest request = GetObjectRequest.builder()
                .bucket(storageProperties.getBucketName())
                .key(key)
                .build();
        ResponseBytes<GetObjectResponse> response;
        try {
            response = s3Client.getObject(request, ResponseTransformer.toBytes());
        } catch (NoSuchKeyException e) {
            throw new EntityNotFoundException(e.getMessage());
        }
        return response.asByteArray();
    }

    public long createResource(CreateResourceRequest createRequest) throws IOException {
        String key = keyGenerator.generateKey();
        PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                .bucket(storageProperties.getBucketName())
                .key(key)
                .build();
        s3Client.putObject(putObjectRequest, RequestBody.fromBytes(createRequest.getResource().getBytes()));
        Resource resource = resourceJpaRepository.save(new Resource(key));
        return resource.getId();
    }

    public long deleteResource(long id) {
        resourceJpaRepository.findById(id).ifPresent(
                resource -> {
                    DeleteObjectRequest deleteRequest = DeleteObjectRequest.builder()
                            .bucket(storageProperties.getBucketName())
                            .key(resource.getKey())
                            .build();
                    s3Client.deleteObject(deleteRequest);
                }
        );
        resourceJpaRepository.deleteById(id);
        return id;
    }
}
