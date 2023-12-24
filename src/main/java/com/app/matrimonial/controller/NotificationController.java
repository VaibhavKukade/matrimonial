package com.app.matrimonial.controller;


import com.app.matrimonial.model.Notification;
import com.app.matrimonial.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.w3c.dom.traversal.NodeIterator;

import java.util.List;

@RestController
@RequestMapping("/api/v1/notification/")
public class NotificationController {

    @Autowired
    NotificationService notificationService;

    @PostMapping("save")
    public ResponseEntity<Notification> save(@RequestBody Notification notification) {
        try {
            Notification notification1 = notificationService.save(notification);
            if (notification1 != null) {
                return ResponseEntity.ok(notification1);
            } else {
                return ResponseEntity.badRequest().build();
            }
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PutMapping("update")
    public ResponseEntity<Notification> update(@RequestBody Notification notification) {
        try {
            Notification notification1 = notificationService.save(notification);
            if (notification1 != null) {
                return ResponseEntity.ok(notification1);
            } else {
                return ResponseEntity.badRequest().build();
            }
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("getAll")
    public ResponseEntity<List<Notification>> getAllNotifications() {
        try {
            List<Notification> notification = notificationService.getAllNotifications();
            if (notification != null && notification.size() > 0) {
                return ResponseEntity.ok(notification);
            } else {
                return ResponseEntity.noContent().build();
            }
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
