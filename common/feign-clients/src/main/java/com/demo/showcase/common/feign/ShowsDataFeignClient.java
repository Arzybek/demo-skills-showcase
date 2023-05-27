package com.demo.showcase.common.feign;

import com.demo.showcase.common.dto.ShowsView;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(value = "showsDataClient", url = "http://localhost:8080")
public interface ShowsDataFeignClient {

    @RequestMapping(method = RequestMethod.GET, value = "/shows")
    List<ShowsView> getShows();

}
