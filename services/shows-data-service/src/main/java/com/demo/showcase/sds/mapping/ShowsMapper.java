package com.demo.showcase.sds.mapping;

import com.demo.showcase.common.data.ShowEntity;
import com.demo.showcase.common.dto.ShowRequestDto;
import com.demo.showcase.common.dto.ShowView;
import org.mapstruct.Mapper;

import java.util.UUID;

@Mapper(componentModel = "spring")
public interface ShowsMapper {

    ShowEntity showDtoToEntity(UUID id, ShowRequestDto showRequestDto);

    ShowView showEntityToView(ShowEntity showEntity);

}
