package com.demo.showcase.sds.service;

import com.demo.showcase.common.data.ShowEntity;
import com.demo.showcase.common.data.ShowsPicsEntity;
import com.demo.showcase.common.data.ShowsPicturesRepository;
import com.demo.showcase.common.data.ShowsRepository;
import com.demo.showcase.common.dto.ShowRequestDto;
import com.demo.showcase.common.dto.ShowShortInfo;
import com.demo.showcase.common.dto.ShowView;
import com.demo.showcase.common.exceptions.NotFoundException;
import com.demo.showcase.sds.mapping.ShowsMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.ByteArrayInputStream;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class ShowsDataService {

    private final ShowsRepository showsRepository;

    private final ShowsPicturesRepository showsPicturesRepository;

    private final ShowsMapper showsMapper;

    public List<ShowShortInfo> getAll() {
        return showsRepository.findAll();
    }

    public List<ShowShortInfo> find(String title) {
        return showsRepository.find(title);
    }

    public ShowView getFullInfoById(UUID id) {
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

    @Transactional
    public void updateShowInfo(UUID id, ShowRequestDto showRequestDto) {
        showsRepository.updateShowInfo(id, showRequestDto);
    }

    @Transactional
    public ShowView createShow(ShowRequestDto showRequestDto) {
        UUID id = UUID.randomUUID();
        ShowEntity showEntity = showsMapper.showDtoToEntity(id, showRequestDto);
        showsRepository.persist(showEntity);
        return showsMapper.showEntityToView(showEntity);
    }
}
