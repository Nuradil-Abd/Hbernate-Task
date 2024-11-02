package java15.service.impl;

import java15.dao.CommentDao;
import java15.dao.impl.CommentDaoImpl;
import java15.entities.Comment;
import java15.service.CommentService;

import java.util.List;

public class CommentServiceImpl implements CommentService {

    private final CommentDao commentDao = new CommentDaoImpl();
    @Override
    public void saveComment(Long postId, Comment comment) {

        commentDao.saveComment(postId, comment);

    }

    @Override
    public List<Comment> findCommentByPostId(Long postId) {
        return commentDao.findCommentByPostId(postId);
    }

    @Override
    public void updateComment(Long commentId, String newText) {
        commentDao.updateComment(commentId, newText);
    }

    @Override
    public void deleteComment(Long commentId) {
        commentDao.deleteComment(commentId);
    }
}
