package com.demo.showcase.sds.service;

import com.demo.showcase.common.data.ShowsPicsEntity;
import com.demo.showcase.common.data.ShowsPicturesRepository;
import com.demo.showcase.common.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class ShowsPictureService {

    private final ShowsPicturesRepository showsPicturesRepository;

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
