package com.bookstore.bookstore.repository.appuser;

import com.bookstore.bookstore.model.user.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AppUserRepo extends JpaRepository<AppUser, String> {
}
