package com.weathair.service;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;

import org.springframework.boot.test.context.SpringBootTest;
import com.weathair.dto.forum.PostDto;
import com.weathair.dto.forum.PostResponseDto;
import com.weathair.entities.forum.Post;
import com.weathair.exceptions.PostException;
import com.weathair.exceptions.TopicException;
import com.weathair.exceptions.UserException;
import com.weathair.services.PostService;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class PostServiceTest {

	@Autowired
	private PostService postService;
	
	@Test
	@Order(1)
	public void testFindAllPosts() throws PostException, TopicException {
		List<PostResponseDto> posts = postService.findAllPosts(1);
		assertThat(!posts.isEmpty());
	}
	
	@Test
	@Order(2)
	public void testFindPostById() throws PostException, TopicException {
		Post post = postService.findPostById(1, 1);
		assertThat(post.getTitle()).isEqualTo("Mon premier post sur la météo");
	}
	
	@Test 
	@Order(3)
	public void testCreatePost() throws UserException, TopicException, PostException {
		PostDto post1 = new PostDto();
		post1.setTitle("new titre");
		post1.setText("new text");
		post1.setTopicId(1);
		post1.setUserId(45);
		postService.createPost(1, 45, post1);
		List<PostResponseDto> posts = postService.findAllPosts(1);
		int lastIndex = posts.get(posts.size() - 1).getId();
		Post post2 = postService.findPostById(1, lastIndex);
		assertThat(post2.getId()).isEqualTo(lastIndex);
	}
	
	@Test
	@Order(4)
	public void testUpdatePost() throws PostException, TopicException, UserException {
		List<PostResponseDto> posts = postService.findAllPosts(1);
		int lastIndex = posts.get(posts.size() - 1).getId();
		Post post = postService.findPostById(1,lastIndex);
		post.setText("Modification texte test");
		
		PostDto postDto = new PostDto();
		postDto.setText(post.getText());
		postDto.setUserId(45);
		postDto.setTopicId(1);
		postService.updatePost(1, lastIndex, postDto);
		assertThat(post.getText()).isEqualTo(postDto.getText());
	}
	
	@Test
	@Order(5)
	public void testDeletePost() throws PostException, TopicException {
		List<PostResponseDto> posts = postService.findAllPosts(1);
		int lastIndex = posts.get(posts.size() - 1).getId();
		int initialSize = postService.findAllPosts(1).size();
		postService.deletePost(1, lastIndex);
		assertThat(postService.findAllPosts(1).size()).isEqualTo(initialSize-1);
	}
}
