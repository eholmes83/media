package com.echapps.social.media.services;

import com.echapps.social.media.models.SocialUser;
import com.echapps.social.media.repositories.SocialUserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SocialService {

    private final SocialUserRepository socialUserRepository;

        public SocialService(SocialUserRepository socialUserRepository) {
            this.socialUserRepository = socialUserRepository;
        }

        public SocialUser saveUser(SocialUser socialUser) {
            if (socialUser.getSocialProfile() != null) {
                socialUser.getSocialProfile().setSocialUser(socialUser);
            }
            return socialUserRepository.save(socialUser);
        }

        public List<SocialUser> getAllUsers() {
            return socialUserRepository.findAll();
        }

    public SocialUser deleteUser(Long id) {
            SocialUser socialUser = socialUserRepository
                    .findById(id)
                    .orElseThrow(() -> new RuntimeException("User not found"));
            socialUserRepository.delete(socialUser);
            return socialUser;

    }
}
