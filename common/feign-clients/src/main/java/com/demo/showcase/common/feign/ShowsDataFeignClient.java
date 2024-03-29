package com.demo.showcase.common.feign;

import com.demo.showcase.common.dto.ShowRequestDto;
import com.demo.showcase.common.dto.ShowShortInfo;
import com.demo.showcase.common.dto.ShowView;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.UUID;

@FeignClient(value = "showsDataClient", url = "${http-clients.backend-url}")
public interface ShowsDataFeignClient {

    @RequestMapping(method = RequestMethod.GET, value = "/api/shows")
    List<ShowShortInfo> getShows(@RequestHeader("Authorization") String bearerToken);

    @RequestMapping(method = RequestMethod.GET, value = "/api/shows/search")
    List<ShowShortInfo> findShows(@RequestHeader("Authorization") String bearerToken, @RequestParam(value = "title") String title);

    @RequestMapping(method = RequestMethod.GET, value = "/api/shows/{id}")
    ShowView findShowInfoById(@RequestHeader("Authorization") String bearerToken, @PathVariable UUID id);

    @RequestMapping(method = RequestMethod.DELETE, value = "/api/shows/{id}")
    void deleteShowInfoById(@RequestHeader("Authorization") String bearerToken, @PathVariable UUID id);

    @RequestMapping(method = RequestMethod.GET, value = "/api/shows/{id}/image")
    ResponseEntity<Resource> findPosterByShowId(@RequestHeader("Authorization") String bearerToken, @PathVariable UUID id);

    @RequestMapping(method = RequestMethod.PUT, value = "/api/shows/{id}")
    void editShowInfoById(@RequestHeader("Authorization") String bearerToken, @PathVariable UUID id, ShowRequestDto showRequestDto);

    @RequestMapping(method = RequestMethod.POST, value = "/api/shows")
    UUID addShow(@RequestHeader("Authorization") String bearerToken, ShowRequestDto show);
}
