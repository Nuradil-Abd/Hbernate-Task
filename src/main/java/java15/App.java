package java15;

import java15.config.HibernateConfig;
import java15.dao.UserDao;
import java15.dao.impl.UserDaoImpl;
import java15.entities.Comment;
import java15.entities.Post;
import java15.entities.Profile;
import java15.entities.User;
import java15.enums.Gender;
import java15.service.*;
import java15.service.UserServiceImpl;
import java15.service.impl.CommentServiceImpl;
import java15.service.impl.PostServiceImpl;
import java15.service.impl.ProfileServiceImpl;

import java.time.LocalDate;

public class App {
    public static void main(String[] args) {

        HibernateConfig.getEntityManagerFactory();
        PostService postService = new PostServiceImpl();
        UserDao userDao = new UserDaoImpl();
        UserService userService = new UserServiceImpl(userDao);
        ProfileService profileService = new ProfileServiceImpl();
        CommentService commentService = new CommentServiceImpl();

//
//        User user = new User("Kira", "hkdgdsadgs@gmail.com", "123456");
//
//        userService.saveUser(user);
//
        Profile profile = new Profile();
        profile.setBio("ui/ux");
        profile.setDateOfBirth(LocalDate.of(2004, 1, 1));
        profile.setFullName("Katar");
        profile.setGender(Gender.FEMALE);
//        profileService.save(user.getId(),profile);
//
//
//        Post post = new Post();
//        post.setDescription("Second comment");
//        post.setUser(user);
//        postService.savePost(post);
//        Long postId = post.getId();
//        System.out.println("Post id: " + postId);


//
//        Comment comment = new Comment();
//        comment.setText("Exemple text");

//        commentService.saveComment(14L, comment);
//
//        System.out.println(profileService.findProfileById(12L));
//
//        System.out.println(postService.getPostByUserId(18L));
//
//        System.out.println(commentService.findCommentByPostId(13L));

//        userService.updateUserProfile(18L,profile);

//        commentService.updateComment(7L,"New text");

//        System.out.println(postService.searchPost("jaky"));
//
//        userService.deleteUser(11L);

//        profileService.deleteProfileByUserId(28L);


//        postService.deletePostById(16L);//todo при удалении post удаляются все ком и отвязка от пользователя

        commentService.deleteComment(6L);


    }
}
