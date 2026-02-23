package com.echapps.social.media;


import com.echapps.social.media.models.Post;
import com.echapps.social.media.models.SocialGroup;
import com.echapps.social.media.models.SocialProfile;
import com.echapps.social.media.models.SocialUser;
import com.echapps.social.media.repositories.PostRepository;
import com.echapps.social.media.repositories.SocialGroupRepository;
import com.echapps.social.media.repositories.SocialProfileRepository;
import com.echapps.social.media.repositories.SocialUserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataInit {

    private final SocialUserRepository socialUserRepository;
    private final SocialGroupRepository socialGroupRepository;
    private final SocialProfileRepository socialProfileRepository;
    private final PostRepository postRepository;

    public DataInit(SocialUserRepository socialUserRepository, SocialGroupRepository socialGroupRepository, SocialProfileRepository socialProfileRepository, PostRepository postRepository) {
        this.socialUserRepository = socialUserRepository;
        this.socialGroupRepository = socialGroupRepository;
        this.socialProfileRepository = socialProfileRepository;
        this.postRepository = postRepository;
    }

    @Bean
    public CommandLineRunner initData() {
        return args -> {
            // Create some users
            SocialUser user1 = new SocialUser();
            SocialUser user2 = new SocialUser();
            SocialUser user3 = new SocialUser();

            // Save users to the database
            socialUserRepository.save(user1);
            socialUserRepository.save(user2);
            socialUserRepository.save(user3);

            // Create some groups
            SocialGroup group1 = new SocialGroup();
            SocialGroup group2 = new SocialGroup();

            // Add users to groups
            group1.getSocialUsers().add(user1);
            group1.getSocialUsers().add(user2);

            group2.getSocialUsers().add(user2);
            group2.getSocialUsers().add(user3);

            // Save groups to the database
            socialGroupRepository.save(group1);
            socialGroupRepository.save(group2);

            // Associate users with groups
            user1.getGroups().add(group1);
            user2.getGroups().add(group1);
            user2.getGroups().add(group2);
            user3.getGroups().add(group2);

            // Save users back to database to update associations
            socialUserRepository.save(user1);
            socialUserRepository.save(user2);
            socialUserRepository.save(user3);


            // Create some posts
            Post post1 = new Post();
            Post post2 = new Post();
            Post post3 = new Post();

            // Associate posts with users
            post1.setSocialUser(user1);
            post2.setSocialUser(user2);
            post3.setSocialUser(user3);

            // Save posts to the database (assuming you have a PostRepository)
            postRepository.save(post1);
            postRepository.save(post2);
            postRepository.save(post3);

            // Create some social profiles
            SocialProfile profile1 = new SocialProfile();
            SocialProfile profile2 = new SocialProfile();
            SocialProfile profile3 = new SocialProfile();

            // Associate profiles with users
            profile1.setUser(user1);
            profile2.setUser(user2);
            profile3.setUser(user3);

            // Save profiles to the database (assuming you have a SocialProfileRepository)
            socialProfileRepository.save(profile1);
            socialProfileRepository.save(profile2);
            socialProfileRepository.save(profile3);

            // FETCH TYPES
            System.out.println("Fetching SOCIAL USER");
            socialUserRepository.findById(1L);
        };
    }
}
