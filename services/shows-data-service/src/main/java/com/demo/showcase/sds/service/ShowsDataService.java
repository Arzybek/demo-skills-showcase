package com.demo.showcase.sds.service;

import com.demo.showcase.common.data.ShowEntity;
import com.demo.showcase.common.data.ShowsRepository;
import com.demo.showcase.common.dto.ShowRequestDto;
import com.demo.showcase.common.dto.ShowShortInfo;
import com.demo.showcase.common.dto.ShowView;
import com.demo.showcase.sds.mapping.ShowsMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class ShowsDataService {

    private final ShowsRepository showsRepository;

    private final ShowsMapper showsMapper;

    public List<ShowShortInfo> getAll() {
        return showsRepository.findAll();
    }

    public List<ShowShortInfo> find(String title) {
        return showsRepository.findCaseInsensitiveBy(title);
    }

    public ShowView getFullInfoById(UUID id) {
        return showsRepository.getFullInfoById(id);
    }

    @Transactional
    public void updateShowInfo(UUID id, ShowRequestDto showRequestDto) {
        showsRepository.updateShowInfo(id, showRequestDto);
    }

    @Transactional
    public UUID createShow(ShowRequestDto showRequestDto) {
        UUID id = UUID.randomUUID();
        ShowEntity showEntity = showsMapper.showDtoToEntity(id, showRequestDto);
        showsRepository.persist(showEntity);
        return id;
    }

    @Transactional
    public void deactualizeShowInfo(UUID id) {
        showsRepository.deactualizeById(id);
    }
}
