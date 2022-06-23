package com.epam.resources.api;

import com.epam.resources.services.ResourceService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/resources")
@AllArgsConstructor
public class ResourceController {
    private final ResourceService resourceService;

    @GetMapping("/{id}")
    public byte[] getResourceById(@PathVariable String id) {
        return resourceService.getResource(id);
    }

    @PostMapping
    public String createResource(@RequestBody CreateResourceRequest resourceRequest) {
        return resourceService.createResource(resourceRequest);
    }

    @DeleteMapping("/{id}")
    public String deleteResourceById(@PathVariable String id) {
        return resourceService.deleteResource(id);
    }
}
