package java15.service;

import java15.entities.Comment;

import java.util.List;

public interface CommentService {
    void saveComment(Long postId, Comment comment);
    List<Comment> findCommentByPostId(Long postId);
    void updateComment(Long commentId, String newText);
    void deleteComment(Long commentId);
}
