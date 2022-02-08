package com.example.testing.service.posts;

import com.example.testing.domain.posts.Posts;
import com.example.testing.domain.posts.PostsRepository;
import com.example.testing.web.dto.PostsListResponseDto;
import com.example.testing.web.dto.PostsResponseDto;
import com.example.testing.web.dto.PostsSaveRequestDto;
import com.example.testing.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostsService {
    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto){
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto){
        Posts posts = postsRepository.findById(id).orElseThrow(
                ()->new IllegalArgumentException("해당 게시글이 없습니다. id ="+ id));
            posts.update(requestDto.getTitle(), requestDto.getContent());

            return id;

    }

    public PostsResponseDto findById(Long id){
        Posts entity = postsRepository.findById(id).orElseThrow(()->new IllegalArgumentException("해당 게시글이 없습니다 id = "+id));
        return new PostsResponseDto(entity);
    }

    @Transactional(readOnly = true)
    public List<PostsListResponseDto> findAllDesc(){
        return postsRepository.findAllDesc()
                .stream()
                .map(PostsListResponseDto::new) //.map(item->new PostsListResponseDto(item))과 동일하다
                .collect(Collectors.toList());
    }

    @Transactional
    public void delete(Long id){
        Posts posts = postsRepository.findById(id).orElseThrow(
                ()->new IllegalArgumentException("해당 게시글이 없습니다. id ="+ id));
        postsRepository.delete(posts);

    }
}
