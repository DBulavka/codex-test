package com.example.demo.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/client")
public class ClientInfoController {

    @GetMapping("/info")
    public Map<String, String> getClientInfo(HttpServletRequest request) {
        String ip = extractClientIp(request);
        log.debug("Resolved client ip: {}", ip);

        Map<String, String> response = new LinkedHashMap<>();
        response.put("ip", ip);
        return response;
    }

    private String extractClientIp(HttpServletRequest request) {
        String xForwardedFor = request.getHeader("X-Forwarded-For");
        if (xForwardedFor != null && !xForwardedFor.isBlank()) {
            return xForwardedFor.split(",")[0].trim();
        }
        return request.getRemoteAddr();
    }
}
