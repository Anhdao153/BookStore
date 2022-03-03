package com.bookstore.bookstore.repository.appuser;

import com.bookstore.bookstore.model.user.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRoleRepo extends JpaRepository<Role,Long> {
}
