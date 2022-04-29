package com.chang.recmv.config.oauth;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.chang.recmv.config.auth.PrincipalDetails;
import com.chang.recmv.config.oauth.provider.GoogleUserInfo;
import com.chang.recmv.config.oauth.provider.NaverUserInfo;
import com.chang.recmv.config.oauth.provider.OAuth2UserInfo;
import com.chang.recmv.model.RoleType;
import com.chang.recmv.model.User;
import com.chang.recmv.repository.UserRepository;

@Service
public class PrincipalOauth2UserService extends DefaultOAuth2UserService {

	private final UserRepository userRepository;
	
	public PrincipalOauth2UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	// OAuth2 인증 후 받는 권한 정보(이름, 이메일 등) 처리 함수
	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
		System.out.println("getClientRegistration: " + userRequest.getClientRegistration());
		System.out.println("getAccessToken: " + userRequest.getAccessToken());
		
		OAuth2User oAuth2User = super.loadUser(userRequest);
		System.out.println("getAttributes: " + super.loadUser(userRequest).getAttributes());
		
		OAuth2UserInfo oAuth2UserInfo = null;
		if(userRequest.getClientRegistration().getRegistrationId().equals("google")) {
			oAuth2UserInfo = new GoogleUserInfo(oAuth2User.getAttributes());
		}
		else {
			oAuth2UserInfo = new NaverUserInfo(oAuth2User.getAttribute("response"));
		}
		
		String provider = oAuth2UserInfo.getProvider();
		String providerId = oAuth2UserInfo.getProviderId();
		String username = provider + "_" + providerId;
		String password = new BCryptPasswordEncoder().encode("remcv");
		String email = oAuth2UserInfo.getEmail();
		RoleType role = RoleType.USER;

		User entity = userRepository.findByUsername(username);
		
		if(entity == null) {
			entity = User.builder()
				.username(username)
				.password(password)
				.email(email)
				.role(role)
				.build();
			
			entity.setProvider(provider);
			entity.setProviderId(providerId);
			
			userRepository.save(entity);
		}
		
		return new PrincipalDetails(entity, oAuth2User.getAttributes());
	}
}