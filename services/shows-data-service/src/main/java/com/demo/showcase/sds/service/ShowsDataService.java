package com.demo.showcase.sds.service;

import com.demo.showcase.common.data.ShowsPicsEntity;
import com.demo.showcase.common.data.ShowsPicturesRepository;
import com.demo.showcase.common.data.ShowsRepository;
import com.demo.showcase.common.dto.ShowsShortInfo;
import com.demo.showcase.common.dto.ShowsView;
import com.demo.showcase.common.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.zalando.problem.Problem;
import org.zalando.problem.Status;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;
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
        Optional<ShowsPicsEntity> picsEntityOptional = showsPicturesRepository.getPictureById(id);
        if (picsEntityOptional.isEmpty()) {
            throw new NotFoundException();
        }
        ShowsPicsEntity picsEntity = picsEntityOptional.get();
        return ResponseEntity.ok()
                             .contentLength(picsEntity.getImage().length)
                             .contentType(MediaType.parseMediaType(picsEntity.getImageContentType()))
                             .body(new InputStreamResource(new ByteArrayInputStream(picsEntity.getImage())));
    }
}
