package boguszGroup.Blog.config;

import boguszGroup.Blog.repository.PostRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MainConfig {

  @Bean
  public PostRepository createPostRepo(){
    PostRepository postRepo = new PostRepository();
    return postRepo;
  }
}
