package com.weathair.service;
import static org.assertj.core.api.Assertions.assertThat;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.weathair.dto.forum.PostDto;
import com.weathair.entities.forum.Post;
import com.weathair.exceptions.PostException;
import com.weathair.exceptions.TopicException;
import com.weathair.exceptions.UserException;
import com.weathair.services.PostService;

@RunWith(SpringJUnit4ClassRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class PostServiceTest {

	@Autowired
	private PostService postService;
	
	@Test
	public void testFindAllPosts() throws PostException, TopicException {
		int initialSize = postService.findAllPosts(1).size();
		assertThat(initialSize).isEqualTo(3);
	}
	
	@Test
	public void testFindPostById() throws PostException, TopicException {
		Post post = postService.findPostById(1, 1);
		assertThat(post.getTitle()).isEqualTo("Mon premier post sur la météo");
	}
	
	@Test 
	public void testCreatePost() throws UserException, TopicException, PostException {
		PostDto post1 = new PostDto();
		post1.setTitle("new titre");
		post1.setText("new text");
		postService.createPost(1, 46, post1);
		Post post2 = postService.findPostById(1, 25);
		assertThat(post2.getId()).isEqualTo(25);
	}
	
	@Test
	public void testUpdatPost() throws PostException {
		Post post = postService.findPostById(1,25);
		post.setText("modif du message ici");
		Post postUpdate = postService.findPostById(1, 25);
		assertThat(postUpdate.getText()).isEqualTo("modif du message ici");
	}
	
	@Test
	public void testDeletePost() throws PostException, TopicException {
		int initialSize = postService.findAllPosts(1).size();
		postService.deletePost(1, 14);
		assertThat(postService.findAllPosts(1).size()+1).isEqualTo(initialSize);
	}
}
