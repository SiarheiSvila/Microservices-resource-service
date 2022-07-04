package com.epam.resources.api;

import com.epam.resources.services.ResourceService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/resources")
@AllArgsConstructor
@Slf4j
public class ResourceController {
    private final ResourceService resourceService;

    @GetMapping("/{id}")
    public byte[] getResourceById(@PathVariable long id) {
        return resourceService.getResource(id);
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public long createResource(@ModelAttribute CreateResourceRequest resourceRequest) throws IOException {
        log.debug("Create request received %s", resourceRequest.getResource().getOriginalFilename());
        return resourceService.createResource(resourceRequest);
    }

    @DeleteMapping("/{id}")
    public long deleteResourceById(@PathVariable long id) {
        return resourceService.deleteResource(id);
    }
}
