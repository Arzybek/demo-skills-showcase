package com.demo.showcase.common.dto.mapper;

import com.demo.showcase.common.dto.ShowFrontDto;
import com.demo.showcase.common.dto.ShowRequestDto;
import com.demo.showcase.common.dto.ShowView;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ShowsDtoMapper {

    ShowRequestDto map(ShowView showView);

    ShowRequestDto map(ShowFrontDto showDto);

}