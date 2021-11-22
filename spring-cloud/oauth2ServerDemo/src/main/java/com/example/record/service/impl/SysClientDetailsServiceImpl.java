package com.example.record.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.record.entity.SysClientDetails;
import com.example.record.mapper.SysClientDetailsMapper;
import com.example.record.service.SysClientDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.ClientAlreadyExistsException;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.security.oauth2.provider.NoSuchClientException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author KPQ
 * @date 2021/11/17
 */
@Service
@RequiredArgsConstructor
public class SysClientDetailsServiceImpl extends ServiceImpl<SysClientDetailsMapper, SysClientDetails> implements SysClientDetailsService {

    private final PasswordEncoder passwordEncoder;

    /**
     * 根据客户端id从数据库当中获取客户端信息
     */
    @Override
    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
        return Optional.ofNullable(findByClientId(clientId)).orElseThrow(() -> new ClientRegistrationException("Loading client error..."));
    }

    @Override
    public SysClientDetails findByClientId(String clientId) {
        SysClientDetails client = this.getOne(Wrappers.<SysClientDetails>lambdaQuery()
                .eq(SysClientDetails::getClientId, clientId));
        return client;
    }

    @Override
    public void addClientDetails(SysClientDetails clientDetails) throws ClientAlreadyExistsException {
        Optional<SysClientDetails> client = Optional.ofNullable(findByClientId(clientDetails.getClientId()));
        if (client.isPresent()) {
            throw new ClientAlreadyExistsException(String.format("Client id %s already exist.", clientDetails.getClientId()));
        }
        this.save(clientDetails);
    }

    @Override
    public void updateClientDetails(SysClientDetails clientDetails) throws NoSuchClientException {
        Optional.ofNullable(findByClientId(clientDetails.getClientId()))
                .orElseThrow(() -> new NoSuchClientException("no such client..."));
        this.updateById(clientDetails);
    }

    @Override
    public void updateClientSecret(String clientId, String clientSecret) throws NoSuchClientException {
        SysClientDetails client = Optional.ofNullable(findByClientId(clientId)).orElseThrow(() -> new NoSuchClientException("no such client..."));
        client.setClientSecret(passwordEncoder.encode(clientSecret));
        this.updateById(client);
    }

    @Override
    public void removeClientDetails(String clientId) throws NoSuchClientException {
        Optional.ofNullable(findByClientId(clientId)).orElseThrow(() -> new NoSuchClientException("no such client..."));
        this.remove(Wrappers.<SysClientDetails>lambdaQuery().eq(SysClientDetails::getClientId, clientId));
    }

    @Override
    public List<SysClientDetails> findAll() {
        return this.list();
    }

    public static void main(String[] args) {
        System.out.println(new BCryptPasswordEncoder().encode("oauth2"));
    }
}
