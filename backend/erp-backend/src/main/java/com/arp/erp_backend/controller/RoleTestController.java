package com.arp.erp_backend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RoleTestController {

    @GetMapping("/api/user/dashboard")
    public String userDashboard() {
        return "User Dashboard";
    }

    @GetMapping("api/admin/dashboard")
    public String adminDashboard() {
        return "Admin Dashboard";
    }
}
