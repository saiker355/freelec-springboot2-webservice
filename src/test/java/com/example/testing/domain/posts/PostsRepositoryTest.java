package com.example.testing.domain.posts;

import org.assertj.core.api.Assertions;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;



@RunWith(SpringRunner.class)
@SpringBootApplication
public class PostsRepositoryTest {

    @Autowired
    PostsRepository postsRepository;

    @After
    public void cleanUp(){
        postsRepository.deleteAll();
    }

    @Test
    public void saveAndReadPosts(){
        String title = "Test입니다";
        String content = "본문 내용 테스트 " +
                "입니다";

        postsRepository.save(
                Posts.builder()
                .title(title)
                .content(content)
                .author("dffd@naver.com")
                .build()
        );

        List<Posts> postsList = postsRepository.findAll();

        Posts posts= postsList.get(0);

        assertThat(posts.getTitle()).isEqualTo(title);
        assertThat(posts.getContent()).isEqualTo(content);
    }

    @Test
    public void BaseTimeEntityRegist(){
        //given
        LocalDateTime now = LocalDateTime.now();
        postsRepository.save(Posts.builder()
                .title("제목1")
                .content("내ㅛㅇㅇㅇ")
                .author("ㅇㅇ")
                .build());

        //when
        List<Posts> postsList = postsRepository.findAll();

        //then
        Posts posts = postsList.get(0);

        System.out.println("dfdfdf >>>>>>>"+ now);

        System.out.println("dfdf >>>>>>>"+ posts.getCreatedDate()+" >>>>>>>>>" +posts.getModifiedDate());

        assertThat(posts.getCreatedDate()).isAfter(now);
        assertThat(posts.getModifiedDate()).isAfter(now);
    }

}
