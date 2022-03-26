package com.example.generator.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 表名：t_client
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
    @Column(name = "`authorized_grant_types_str`")
    private String authorizedGrantTypesStr;

    /**
     * 跳转 uri
     */
    @Column(name = "`registered_redirect_uri_str`")
    private String registeredRedirectUriStr;

    @Column(name = "`scope_str`")
    private String scopeStr;

    @Column(name = "`auto_approve_scope`")
    private String autoApproveScope;

    @Column(name = "`authorities_str`")
    private String authoritiesStr;

    @Column(name = "`additional_information_str`")
    private String additionalInformationStr;

    @Column(name = "`resource_ids_str`")
    private String resourceIdsStr;
}