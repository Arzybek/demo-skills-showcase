package com.demo.showcase.sds.service;

import com.demo.showcase.common.data.UserShowsRepository;
import com.demo.showcase.common.sso.KeycloakUtils;
import com.demo.showcase.common.sso.exceptions.ForbiddenException;
import com.demo.showcase.common.sso.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserShowsFlcService {

    private final UserShowsRepository repository;

    public void checkShowDoesntExist(UUID showId){
        if(repository.exists(showId, KeycloakUtils.getUserId())){
            throw new ForbiddenException();
        }
    }

    public void checkShowExists(UUID showId){
        if(!repository.exists(showId, KeycloakUtils.getUserId())){
            throw new ForbiddenException();
        }
    }

}
