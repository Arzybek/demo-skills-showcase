package com.demo.showcase.sds.service;

import com.demo.showcase.common.data.ShowsPicsEntity;
import com.demo.showcase.common.data.ShowsPicturesRepository;
import com.demo.showcase.common.data.ShowsRepository;
import com.demo.showcase.common.dto.ShowsShortInfo;
import com.demo.showcase.common.dto.ShowsView;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class ShowsDataService {

    private final ShowsRepository showsRepository;

    private final ShowsPicturesRepository showsPicturesRepository;

    public List<ShowsShortInfo> getAll() {
        return showsRepository.findAll();
    }

    public List<ShowsShortInfo> find(String title) {
        return showsRepository.find(title);
    }

    public ShowsView getFullInfoById(UUID id) {
        return showsRepository.getFullInfoById(id);
    }

    public ResponseEntity<InputStreamResource> getPictureByShowId(UUID id) {
        ShowsPicsEntity picsEntity = showsPicturesRepository.getPictureById(id);
        return ResponseEntity.ok()
                             .contentLength(picsEntity.getImage().length)
                             .contentType(MediaType.parseMediaType(picsEntity.getImageContentType()))
                             .body(new InputStreamResource(new ByteArrayInputStream(picsEntity.getImage())));
    }
}
