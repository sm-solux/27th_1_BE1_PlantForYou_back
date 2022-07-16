package com.be1.plant4you.service;

import com.be1.plant4you.common.LikesId;
import com.be1.plant4you.common.ScrapId;
import com.be1.plant4you.domain.Likes;
import com.be1.plant4you.domain.Post;
import com.be1.plant4you.domain.Scrap;
import com.be1.plant4you.domain.User;
import com.be1.plant4you.dto.request.PostRequest;
import com.be1.plant4you.dto.request.PostUpdateRequest;
import com.be1.plant4you.dto.response.PostResponse;
import com.be1.plant4you.dto.response.PostShortResponse;
import com.be1.plant4you.enumerate.PostCat;
import com.be1.plant4you.repository.LikesRepository;
import com.be1.plant4you.repository.PostRepository;
import com.be1.plant4you.repository.ScrapRepository;
import com.be1.plant4you.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class PostService {

    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final LikesRepository likesRepository;
    private final ScrapRepository scrapRepository;

    public List<PostShortResponse> getPostsByCat(PostCat cat) {
        return postRepository.findAllByCat(cat);
    }

    public List<PostShortResponse> getPostsOrderByNew() {
        return postRepository.findAllOrderByCreatedDate();
    }

    public List<PostShortResponse> getPostsOrderByLikes() {
        return postRepository.findAllOrderByLikes();
    }

    public List<PostShortResponse> getMyPosts(Long userId) {
        return postRepository.findAllByWriterId(userId);
    }

    public PostResponse getPost(Long userId, Long postId) {
        return postRepository.findDtoById(userId, postId);
    }

    @Transactional
    public void upload(Long userId, PostRequest postRequest) {
        Optional<User> userOptional = userRepository.findById(userId);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            Post post = Post.builder()
                    .cat(postRequest.getCat())
                    .title(postRequest.getTitle())
                    .content(postRequest.getContent())
                    .build();
            post.changeUser(user); //글 작성자

            postRepository.save(post);
        }
    }

    @Transactional
    public void modify(Long userId, Long postId, PostUpdateRequest postUpdateRequest) {
        Optional<Post> postOptional = postRepository.findById(postId);

        //글이 존재하면서, 해당 글이 현재 로그인한 이용자가 쓴 글일 경우에만 수정 가능
        if (postOptional.isPresent()) {
            Post post = postOptional.get();
            if (Objects.equals(post.getUser().getId(), userId)) {
                post.update(postUpdateRequest.getTitle(), postUpdateRequest.getContent());
            }
        }
    }

    @Transactional
    public void delete(Long userId, Long postId) {
        Optional<Post> postOptional = postRepository.findById(postId);

        //글이 존재하면서, 해당 글이 현재 로그인한 이용자가 쓴 글일 경우에만 삭제 가능
        if (postOptional.isPresent()) {
            Post post = postOptional.get();
            if (Objects.equals(post.getUser().getId(), userId)) {
                //Likes, Scrap 테이블에서 해당 글 삭제
                List<Likes> likesList = likesRepository.findAllByPostId(postId);
                List<Scrap> scrapList = scrapRepository.findAllByPostId(postId);

                likesRepository.deleteAll(likesList);
                scrapRepository.deleteAll(scrapList);
                postRepository.delete(post);
            }
        }
    }

    public List<PostShortResponse> getMyCmtPosts(Long userId) {
        return postRepository.findAllByUserCmt(userId);
    }

    public List<PostShortResponse> getMyLikesPosts(Long userId) {
        return postRepository.findAllByUserLikes(userId);
    }

    public List<PostShortResponse> getMyScrapPosts(Long userId) {
        return postRepository.findAllByUserScrap(userId);
    }

    @Transactional
    public void saveLikesPost(Long userId, Long postId) {
        Optional<User> userOptional = userRepository.findById(userId);
        Optional<Post> postOptional = postRepository.findById(postId);

        if(userOptional.isPresent() && postOptional.isPresent()) {
            User user = userOptional.get();
            Post post = postOptional.get();
            post.plusLikes();

            Likes likes = Likes.builder()
                    .user(user)
                    .post(post)
                    .build();
            likesRepository.save(likes);
        }
    }

    @Transactional
    public void saveScrapPost(Long userId, Long postId) {
        Optional<User> userOptional = userRepository.findById(userId);
        Optional<Post> postOptional = postRepository.findById(postId);

        if(userOptional.isPresent() && postOptional.isPresent()) {
            User user = userOptional.get();
            Post post = postOptional.get();
            post.plusScraps();

            Scrap scrap = Scrap.builder()
                    .user(user)
                    .post(post)
                    .build();
            scrapRepository.save(scrap);
        }
    }

    @Transactional
    public void deleteLikesPost(Long userId, Long postId) {
        Optional<Likes> likesOptional = likesRepository.findById(
                LikesId.builder()
                        .userId(userId)
                        .postId(postId)
                        .build()
        );
        if (likesOptional.isPresent()) {
            Likes likes = likesOptional.get();
            likesRepository.delete(likes);
        }

        Optional<Post> postOptional = postRepository.findById(postId);
        if (postOptional.isPresent()) {
            Post post = postOptional.get();
            post.minusLikes();
        }
    }

    @Transactional
    public void deleteScrapPost(Long userId, Long postId) {
        Optional<Scrap> scrapOptional = scrapRepository.findById(
                ScrapId.builder()
                        .userId(userId)
                        .postId(postId)
                        .build()
        );
        if (scrapOptional.isPresent()) {
            Scrap scrap = scrapOptional.get();
            scrapRepository.delete(scrap);
        }

        Optional<Post> postOptional = postRepository.findById(postId);
        if (postOptional.isPresent()) {
            Post post = postOptional.get();
            post.minusScraps();
        }
    }
}