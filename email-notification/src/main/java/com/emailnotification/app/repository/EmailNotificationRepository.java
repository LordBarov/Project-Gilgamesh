package com.emailnotification.app.repository;

import com.emailnotification.app.model.EmailNotification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailNotificationRepository extends JpaRepository<EmailNotification, Long> {
}
