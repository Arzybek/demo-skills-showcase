package com.demo.showcase.sds.service;

import com.demo.showcase.common.data.ShowsRepository;
import com.demo.showcase.common.dto.ShowsShortInfo;
import com.demo.showcase.common.dto.ShowsView;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class ShowsDataService {

    private final ShowsRepository showsRepository;

    public List<ShowsShortInfo> getAll() {
        return showsRepository.findAll();
    }

    public List<ShowsShortInfo> find(String title) {
        return showsRepository.find(title);
    }

    public ShowsView getFullInfoById(UUID id) {
        return showsRepository.getFullInfoById(id);
    }
}
