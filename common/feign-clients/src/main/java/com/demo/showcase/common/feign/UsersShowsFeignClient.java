package com.demo.showcase.common.feign;

import com.demo.showcase.common.dto.AddShowRequest;
import com.demo.showcase.common.dto.GetUserShowsResponse;
import com.demo.showcase.common.dto.ShowShortInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.UUID;

@FeignClient(value = "usersShowsClient", url = "http://localhost:8080")
public interface UsersShowsFeignClient {

    @RequestMapping(method = RequestMethod.POST, value = "/api/users/shows")
    UUID addShow(@RequestHeader("Authorization") String bearerToken, AddShowRequest addShowRequest);

    @RequestMapping(method = RequestMethod.GET, value = "/api/users/shows")
    List<GetUserShowsResponse> getUserShows(@RequestHeader("Authorization") String bearerToken);

    @RequestMapping(method = RequestMethod.DELETE, value = "/api/users/shows/{id}")
    void deleteShowRecordById(@RequestHeader("Authorization") String bearerToken, @PathVariable UUID id);

}
