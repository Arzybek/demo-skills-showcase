package com.demo.showcase.common.feign;

import com.demo.showcase.common.dto.ShowShortInfo;
import com.demo.showcase.common.enums.ShowGenre;
import com.demo.showcase.common.enums.ShowStage;
import com.demo.showcase.common.enums.WatchState;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(value = "dictionariesClient", url = "${http-clients.backend-url}")
public interface DictionariesFeignClient {

    @RequestMapping(method = RequestMethod.GET, value = "/api/dictionaries/scores")
    List<Integer> getScores(@RequestHeader("Authorization") String bearerToken);

    @RequestMapping(method = RequestMethod.GET, value = "/api/dictionaries/watchStates")
    List<WatchState> getStates(@RequestHeader("Authorization") String bearerToken);

    @RequestMapping(method = RequestMethod.GET, value = "/api/dictionaries/genres")
    List<ShowGenre> getGenres(@RequestHeader("Authorization") String bearerToken);

    @RequestMapping(method = RequestMethod.GET, value = "/api/dictionaries/stages")
    List<ShowStage> getShowsStages(@RequestHeader("Authorization") String bearerToken);

}
