package com.app.matrimonial.service.impl;

import com.app.matrimonial.model.Notification;
import com.app.matrimonial.repository.NotificationRepository;
import com.app.matrimonial.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationServiceImpl implements NotificationService {

   @Autowired
    NotificationRepository notificationRepository;

    @Override
    public Notification save(Notification notification){
        return notificationRepository.save(notification);
    }

    @Override
    public List<Notification> getAllNotifications(){
        return notificationRepository.getAllNotifications();
    }
}
