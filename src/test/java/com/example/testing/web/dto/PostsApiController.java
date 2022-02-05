package com.example.testing.web.dto;

import com.example.testing.domain.posts.Posts;
import com.example.testing.domain.posts.PostsRepository;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PostsApiController {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private PostsRepository postsRepository;

    @After
    public void tearDown() throws Exception{
        postsRepository.deleteAll();
    }

    @Test
    public void registPosts(){
        String title = "테스팅";
        String content = "ㄴㅁㅇ룬미루니루린물" +
                "미ㅏㄴㄹ위루님ㄹ우" +
                "ㅣㅏ무리ㅏㄴ륀뤼루니라ㅜㄴ미룬밀눌이ㅜ" +
                "미뤼루니룬리누린ㄹㅇ";
        String author = "아서";

        PostsSaveRequestDto requestDto = PostsSaveRequestDto.builder()
                .title(title)
                .content(content)
                .author(author)
                .build();

        String url = "https://localhost:"+port+"/api/v1/posts";

    }


}
