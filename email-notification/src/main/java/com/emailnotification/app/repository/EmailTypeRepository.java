package com.emailnotification.app.repository;

import com.emailnotification.app.model.EmailType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailTypeRepository extends JpaRepository<EmailType, Long> {
}
