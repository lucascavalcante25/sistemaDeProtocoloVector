package com.protocoloApp.config;

import org.springframework.security.config.annotation.web.HttpSecurityBuilder;
import org.springframework.security.config.annotation.web.configurers.AbstractAuthenticationFilterConfigurer;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.ui.DefaultLoginPageGeneratingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import java.util.ArrayList;
import java.util.List;

public final class FormLoginFilterConfigurer<H extends HttpSecurityBuilder<H>> extends AbstractAuthenticationFilterConfigurer<H, FormLoginFilterConfigurer<H>, FormLoginFilter> {

    public FormLoginFilterConfigurer(final H http,
                                     final String homePage, 
                                     final String loginSubmissionUrl,
                                     final String usernameParam, 
                                     final String passwordParam,
                                     final AuthenticationFailureHandler loginErrorHandler,
                                     final AuthenticationSuccessHandler loginSuccessfulHandler) {
        super(new FormLoginFilter(), null);
        setBuilder(http);
        this.usernameParameter(usernameParam)
                .passwordParameter(passwordParam)
                .successHandler(loginSuccessfulHandler)
                .failureHandler(loginErrorHandler)
                .loginProcessingUrl(loginSubmissionUrl)
                .loginPage(homePage);
    }
    
    @Override
    protected RequestMatcher createLoginProcessingUrlMatcher(String loginProcessingUrl) {
        AntPathRequestMatcher loginMatcher = new AntPathRequestMatcher(loginProcessingUrl, "POST");
        List<RequestMatcher> matches = new ArrayList<>();
        matches.add(loginMatcher);
        return new OrRequestMatcher(matches);
    }

    @Override
    public FormLoginFilterConfigurer<H> loginPage(String loginPage) {
        return super.loginPage(loginPage);
    }

    public FormLoginFilterConfigurer<H> usernameParameter(String usernameParameter) {
        getAuthenticationFilter().setUsernameParameter(usernameParameter);
        return this;
    }

    public FormLoginFilterConfigurer<H> passwordParameter(String passwordParameter) {
        getAuthenticationFilter().setPasswordParameter(passwordParameter);
        return this;
    }

    @Override
    public void init(H http) throws Exception {
        super.init(http);
        initDefaultLoginFilter(http);
    }

    private String getUsernameParameter() {
        return getAuthenticationFilter().getUsernameParameter();
    }

    private String getPasswordParameter() {
        return getAuthenticationFilter().getPasswordParameter();
    }

    private void initDefaultLoginFilter(H http) {
        DefaultLoginPageGeneratingFilter loginPageGeneratingFilter = http
                .getSharedObject(DefaultLoginPageGeneratingFilter.class);
        if (loginPageGeneratingFilter != null && !isCustomLoginPage()) {
            loginPageGeneratingFilter.setFormLoginEnabled(true);
            loginPageGeneratingFilter.setUsernameParameter(getUsernameParameter());
            loginPageGeneratingFilter.setPasswordParameter(getPasswordParameter());
            loginPageGeneratingFilter.setLoginPageUrl(getLoginPage());
            loginPageGeneratingFilter.setFailureUrl(getFailureUrl());
            loginPageGeneratingFilter.setAuthenticationUrl(getLoginProcessingUrl());
        }
    }
}
