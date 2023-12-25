//package com.app.matrimonial.controller;
//
//
//import com.app.matrimonial.Config.NotificationService;
//import com.app.matrimonial.model.NotificationRequest;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//import java.util.concurrent.ExecutionException;
//
//@RestController
//@RequestMapping("/api/v1/notification/")
//public class NotificationController {
//
//    @Autowired
//    NotificationService notificationService;
//
//    @PostMapping("/notification")
//    public ResponseEntity sendNotification(@RequestBody NotificationRequest request) {
//        try {
//            notificationService.sendMessageToToken(request);
//        } catch (Exception e) {
//            return ResponseEntity.internalServerError().build();
//        }
//        return ResponseEntity.ok("Notification sent successfully");
//    }
//
//}
