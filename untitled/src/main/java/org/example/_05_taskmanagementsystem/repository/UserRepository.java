package org.example._05_taskmanagementsystem.repository;

import org.example._05_taskmanagementsystem.domain.User;

public interface UserRepository {
    User findById(int userId);
}
