package com.example.generator.model;

import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;

/**
 * 表名：t_client
 * 表注释：client表
*/
@Getter
@Setter
@Table(name = "`t_client`")
public class Client {
    @Id
    @Column(name = "`id`")
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    @Column(name = "`client_id`")
    private String clientId;

    @Column(name = "`client_secret`")
    private String clientSecret;

    /**
     * access_token 有效期，单位：秒，默认7200秒，即2小时。
     */
    @Column(name = "`access_token_validity_seconds`")
    private Integer accessTokenValiditySeconds;

    /**
     * refresh_token 有效期，单位：秒。默认172800秒，即2天。
     */
    @Column(name = "`refresh_token_validity_seconds`")
    private Integer refreshTokenValiditySeconds;

    /**
     * 授权类型
     */
    @Column(name = "`authorized_grant_types`")
    private String authorizedGrantTypes;

    /**
     * 跳转 uri
     */
    @Column(name = "`registered_redirect_uri`")
    private String registeredRedirectUri;

    @Column(name = "`scope`")
    private String scope;

    @Column(name = "`auto_approve_scope`")
    private String autoApproveScope;

    @Column(name = "`authorities`")
    private String authorities;

    @Column(name = "`additional_information`")
    private String additionalInformation;

    @Column(name = "`resource_ids`")
    private String resourceIds;
}