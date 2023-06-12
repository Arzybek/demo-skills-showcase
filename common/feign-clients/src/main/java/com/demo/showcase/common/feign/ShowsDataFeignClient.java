package com.demo.showcase.common.feign;

import com.demo.showcase.common.dto.ShowShortInfo;
import com.demo.showcase.common.dto.ShowView;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.UUID;

@FeignClient(value = "showsDataClient", url = "http://localhost:8080")
public interface ShowsDataFeignClient {

    @RequestMapping(method = RequestMethod.GET, value = "/shows")
    List<ShowShortInfo> getShows();

    @RequestMapping(method = RequestMethod.GET, value = "/shows/search")
    List<ShowShortInfo> findShows(@RequestParam(value="title") String title);

    @RequestMapping(method = RequestMethod.GET, value = "/shows/{id}")
    ShowView findShowInfoById(@PathVariable UUID id);

    @RequestMapping(method = RequestMethod.DELETE, value = "/shows/{id}")
    void deleteShowInfoById(@PathVariable UUID id);

    @RequestMapping(method = RequestMethod.GET, value = "/shows/{id}/image")
    ResponseEntity<Resource> findPosterByShowId(@PathVariable UUID id);

}
