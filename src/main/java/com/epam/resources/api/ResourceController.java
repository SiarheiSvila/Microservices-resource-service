package com.epam.resources.api;

import com.epam.resources.services.ResourceService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/resources")
public class ResourceController {
    private ResourceService resourceService;

    @GetMapping
    public String getResources() {
        return "Hello";
    }

    @GetMapping("/{id}")
    public String getResourceById(@PathVariable String id) {
        return resourceService.getResource(id);
    }
}
