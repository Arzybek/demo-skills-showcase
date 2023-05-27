package com.demo.showcase.sds.service;

import com.demo.showcase.common.data.ShowsRepository;
import com.demo.showcase.common.dto.ShowsView;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ShowsDataService {

    private final ShowsRepository showsRepository;

    public List<ShowsView> getAll() {
        return showsRepository.findAll();
    }
}
