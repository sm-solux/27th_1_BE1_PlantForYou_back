package com.be1.plant4you.auth.oauth.handler;

import com.be1.plant4you.auth.domain.RefreshToken;
import com.be1.plant4you.auth.repository.RefreshTokenRepository;
import com.be1.plant4you.auth.token.AuthToken;
import com.be1.plant4you.auth.token.AuthTokenProvider;
import com.be1.plant4you.auth.UserPrincipal;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
@Component
public class OAuth2SuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final AuthTokenProvider tokenProvider;
    private final RefreshTokenRepository refreshTokenRepository;

    @Value("${app.auth.redirectUri}")
    private String redirectUri;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        String targetUrl = determineTargetUrl(request, response, authentication);

        if (response.isCommitted()) {
            log.debug("Response has already been committed. Unable to redirect to " + targetUrl);
            return;
        }

        clearAuthenticationAttributes(request);
        getRedirectStrategy().sendRedirect(request, response, targetUrl);
    }

    @Override
    protected String determineTargetUrl(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        //4. Generate Access Token
        UserPrincipal principal = (UserPrincipal) authentication.getPrincipal();
        AuthToken accessToken = tokenProvider.createAccessToken(principal.getProviderId(), principal.getUserId());
        RefreshToken refreshToken = refreshTokenRepository.findByKey(principal.getUserId()).orElse(new RefreshToken());

        //5. Redirect redirect_uri?accessToken={access_token}&refreshToken={refreshToken}
        return UriComponentsBuilder.fromUriString(redirectUri)
                .queryParam("accessToken", accessToken.getToken())
                .queryParam("refreshToken", refreshToken.getValue())
                .build().toUriString();
    }
}
