package boguszGroup.Blog.config;

import boguszGroup.Blog.repository.DBPostRepository;
import boguszGroup.Blog.repository.InMemoryPostRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class MainConfig {

  @Bean(name="inmemoryrepository")
  @Profile("dev")
  public InMemoryPostRepository createPostRepoInMem(){
    InMemoryPostRepository postRepo = new InMemoryPostRepository();
    return postRepo;
  }

  @Bean(name="DBRepository")
  @Profile("prod")
  public DBPostRepository createPostRepoInDB(){
    DBPostRepository postRepo = new DBPostRepository();
    return postRepo;
  }
}
