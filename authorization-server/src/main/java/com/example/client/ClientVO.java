package com.example.client;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.json.JSONUtil;
import com.example.generator.model.Client;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@EqualsAndHashCode(callSuper = true)
@Data
public class ClientVO extends Client implements ClientDetails {
    public static ClientVO from(Client client) {
        ClientVO clientVO = new ClientVO();
        BeanUtil.copyProperties(client, clientVO);
        return clientVO;
    }

    @Override
    public Set<String> getResourceIds() {
        return StringUtils.commaDelimitedListToSet(this.getResourceIdsStr());
    }

    @Override
    public boolean isSecretRequired() {
        return !ObjectUtils.isEmpty(this.getClientSecret());
    }

    @Override
    public boolean isScoped() {
        return !ObjectUtils.isEmpty(this.getScope());
    }

    @Override
    public Set<String> getScope() {
        return StringUtils.commaDelimitedListToSet(this.getScopeStr());
    }

    @Override
    public Set<String> getAuthorizedGrantTypes() {
        return StringUtils.commaDelimitedListToSet(this.getAuthorizedGrantTypesStr());
    }

    @Override
    public Set<String> getRegisteredRedirectUri() {
        return StringUtils.commaDelimitedListToSet(this.getRegisteredRedirectUriStr());
    }

    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        return StringUtils.commaDelimitedListToSet(this.getAuthoritiesStr())
                .stream()
                .map(s -> (GrantedAuthority) () -> s)
                .collect(Collectors.toList());
    }

    @Override
    public boolean isAutoApprove(String scope) {
        return !ObjectUtils.isEmpty(this.getAutoApproveScope())
                && StringUtils.commaDelimitedListToSet(this.getAutoApproveScope()).containsAll(this.getScope());
    }

    @Override
    public Map<String, Object> getAdditionalInformation() {
        if (ObjectUtils.isEmpty(this.getAdditionalInformationStr())) {
            return null;
        }
        return JSONUtil.parseObj(this.getAdditionalInformationStr());
    }
}
