package com.chang.recmv.config.oauth.provider;

public interface OAuth2UserInfo {
	public String getProvider();
	public String getProviderId();
	public String getEmail();
	public String getName();
}