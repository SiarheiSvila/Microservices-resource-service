package com.epam.resources.api;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class CreateResourceRequest {
    private MultipartFile resource;
}
