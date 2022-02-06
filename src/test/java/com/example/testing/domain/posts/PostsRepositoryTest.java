package com.example.testing.domain.posts;

import org.assertj.core.api.Assertions;
import org.hamcrest.MatcherAssert;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.test.context.junit4.SpringRunner;

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
        Assertions.assertThat(posts.getTitle()).isEqualTo(title);
        Assertions.assertThat(posts.getContent()).isEqualTo(content);
    }

}
