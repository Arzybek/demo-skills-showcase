package com.demo.showcase.common.feign;

import com.demo.showcase.common.dto.ShowsShortInfo;
import com.demo.showcase.common.dto.ShowsView;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.UUID;

@FeignClient(value = "showsDataClient", url = "http://localhost:8080")
public interface ShowsDataFeignClient {

    @RequestMapping(method = RequestMethod.GET, value = "/shows")
    List<ShowsShortInfo> getShows();

    @RequestMapping(method = RequestMethod.GET, value = "/shows/search")
    List<ShowsShortInfo> findShows(@RequestParam(value="title") String title);

    @RequestMapping(method = RequestMethod.GET, value = "/shows/{id}")
    ShowsView findShowInfoById(@PathVariable UUID id);

}
