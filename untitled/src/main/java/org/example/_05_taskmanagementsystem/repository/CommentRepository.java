package org.example._05_taskmanagementsystem.repository;

import org.example._05_taskmanagementsystem.domain.Comment;

import java.util.List;

public interface CommentRepository {
    Comment save(Comment comment);
    List<Comment> findByTaskId(int taskId);
}
