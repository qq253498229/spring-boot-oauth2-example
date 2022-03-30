package com.example.client;

import com.example.generator.mapper.ClientMapper;
import com.example.generator.model.Client;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;

@Service
public class ClientService implements ClientDetailsService {
    @Resource
    ClientMapper clientMapper;

    @Override
    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
        Example example = new Example(Client.class);
        example.createCriteria().andEqualTo("clientId", clientId);
        Client client = clientMapper.selectOneByExample(example);
        return ClientVO.from(client);
    }
}
