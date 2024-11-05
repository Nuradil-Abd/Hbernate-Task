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

////
//        User user = new User("elaman", "elaman12343@gmail.com", "qwrerty");
////
//        userService.saveUser(user);
////
        Profile profile = new Profile();
        profile.setBio("Kotlin");
        profile.setDateOfBirth(LocalDate.of(2004, 1, 1));
        profile.setFullName("Atai");
        profile.setGender(Gender.FEMALE);
//        profileService.save(user.getId(),profile);
////
////
//        Post post = new Post();
//        post.setDescription("Elaman comment");
//////
//        postService.savePost(34L,post);//todo user.getId() выдает инкремент
//        Long postId = post.getId();
//        System.out.println("Post id: " + postId);
//
//

        Comment comment = new Comment();
        comment.setText("Atai Example");

        commentService.saveComment(30L, comment);//todo user.getId() выдает инкремент

        // find
//        System.out.println(userService.findUserById(3L));   ------
//
//        System.out.println(profileService.findProfileById(3L));
//
//        System.out.println(postService.getPostByUserId(20L));   -------
//
//        System.out.println(commentService.findCommentByPostId(1L));   //--

        //update

//        userService.updateUserProfile(34L,profile);//todo решил проблему с обновлением изначально привязываю id к тому же id что и user

//        commentService.updateComment(13L,"New text");

        //search

//        System.out.println(postService.searchPost("a"));

        //delete
//
//        userService.deleteUser(25L);

//        profileService.deleteProfileByUserId(25L);


//        postService.deletePostById(26L);//todo при удалении post удаляются все ком и отвязка от пользователя

//        commentService.deleteComment(18L);


    }
}
