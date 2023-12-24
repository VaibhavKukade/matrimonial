package com.app.matrimonial.service;

import com.app.matrimonial.model.Notification;
import org.aspectj.weaver.ast.Not;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface NotificationService {

    Notification save(Notification notification);

    List<Notification>  getAllNotifications();
}
