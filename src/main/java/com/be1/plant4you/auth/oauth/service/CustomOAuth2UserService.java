package com.be1.plant4you.auth.oauth.service;

import com.be1.plant4you.auth.domain.RefreshToken;
import com.be1.plant4you.auth.domain.User;
import com.be1.plant4you.auth.oauth.enumerate.Provider;
import com.be1.plant4you.auth.oauth.info.OAuth2UserInfo;
import com.be1.plant4you.auth.oauth.info.OAuth2UserInfoFactory;
import com.be1.plant4you.auth.repository.RefreshTokenRepository;
import com.be1.plant4you.auth.repository.UserRepository;
import com.be1.plant4you.auth.token.AuthToken;
import com.be1.plant4you.auth.token.AuthTokenProvider;
import com.be1.plant4you.auth.UserPrincipal;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    private final AuthTokenProvider tokenProvider;
    private final UserRepository userRepository;
    private final RefreshTokenRepository refreshTokenRepository;

    @Override
    @Transactional
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2UserService<OAuth2UserRequest, OAuth2User> oAuth2UserService = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = oAuth2UserService.loadUser(userRequest);

        Provider provider = Provider.valueOf(userRequest.getClientRegistration().getRegistrationId().toUpperCase());
        OAuth2UserInfo oAuth2UserInfo = OAuth2UserInfoFactory.getOAuth2UserInfo(provider, oAuth2User.getAttributes());

        /**
         * 1. Save or Update User Info in DB
         * 2. Generate Refresh Token
         * 3. Save or Update Refresh Token in DB
         */
        Optional<User> userOptional = userRepository.findByProviderAndProviderId(provider, oAuth2UserInfo.getProviderId());
        User user;
        RefreshToken dbRefreshToken = null;
        boolean isNewUser;

        //?????? ?????????
        if (userOptional.isPresent()) {
            user = userOptional.get();
            user.update(
                    oAuth2UserInfo.getEmail(),
                    oAuth2UserInfo.getName(),
                    oAuth2UserInfo.getImageUrl(),
                    oAuth2UserInfo.getProviderId()
            );
            isNewUser = false;

            dbRefreshToken  = refreshTokenRepository.findByKey(user.getId()).orElse(null);
        }
        //????????? ?????????
        else {
            User newUser = User.builder()
                    .email(oAuth2UserInfo.getEmail())
                    .name(oAuth2UserInfo.getName())
                    .nickName(oAuth2UserInfo.getName())
                    .imageUrl(oAuth2UserInfo.getImageUrl())
                    .provider(provider)
                    .providerId(oAuth2UserInfo.getProviderId())
                    .build();
            user = userRepository.save(newUser);
            isNewUser = true;
        }

        AuthToken refreshToken = tokenProvider.createRefreshToken();
        if (!isNewUser && dbRefreshToken != null) {
            dbRefreshToken.updateValue(refreshToken.getToken());
        } else {
            refreshTokenRepository.save(RefreshToken.builder()
                    .user(user)
                    .value(refreshToken.getToken())
                    .build());
        }

        /**
         * OAuth2SuccessHandler??? onAuthenticationSuccess ????????? ????????? ???????????? Authentication ?????? ?????????
         * -> access Token(??????), refresh Token query str??? ???????????? ???????????? redirect
         */
        return UserPrincipal.create(user);
    }
}
