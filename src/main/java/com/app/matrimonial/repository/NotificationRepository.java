package com.app.matrimonial.repository;

import com.app.matrimonial.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification,Long> {

    @Query("Select n from Notification n where n.status=true")
    List<Notification> getAllNotifications();
}
