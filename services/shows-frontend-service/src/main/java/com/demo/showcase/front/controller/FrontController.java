package com.demo.showcase.front.controller;

import com.demo.showcase.common.dto.GetUserShowsResponse;
import com.demo.showcase.common.dto.ShowFrontDto;
import com.demo.showcase.common.dto.ShowShortInfo;
import com.demo.showcase.common.dto.ShowView;
import com.demo.showcase.common.dto.UsersShowRequest;
import com.demo.showcase.common.dto.mapper.ShowsDtoMapper;
import com.demo.showcase.common.enums.ShowGenre;
import com.demo.showcase.common.enums.ShowStage;
import com.demo.showcase.common.enums.WatchState;
import com.demo.showcase.common.feign.DictionariesFeignClient;
import com.demo.showcase.common.feign.ShowsDataFeignClient;
import com.demo.showcase.common.feign.UsersShowsFeignClient;
import com.demo.showcase.common.sso.KeycloakUtils;
import com.demo.showcase.common.sso.Role;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class FrontController {

    private final static String BASE_URL = "/front";

    private final ShowsDataFeignClient showsDataFeignClient;

    private final UsersShowsFeignClient usersShowsFeignClient;

    private final DictionariesFeignClient dictionariesFeignClient;

    private final ShowsDtoMapper showsDtoMapper;

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping("/")
    public String index(Model model) {
        if (KeycloakUtils.getRole() == Role.ADMIN) {
            List<ShowShortInfo> shows = showsDataFeignClient.getShows(KeycloakUtils.getBearerToken());
            model.addAttribute("shows", shows);
            return "index";
        } else {
            List<ShowShortInfo> shows = showsDataFeignClient.getShows(KeycloakUtils.getBearerToken());
            model.addAttribute("shows", shows);
            return "indexUser";
        }
    }

    @GetMapping(BASE_URL + "/search")
    public String search(Model model, String searchTitle) {
        List<ShowShortInfo> shows = showsDataFeignClient.findShows(KeycloakUtils.getBearerToken(), searchTitle);
        model.addAttribute("shows", shows);
        return "index";
    }

    @GetMapping(BASE_URL + "/{id}")
    public String show(@PathVariable("id") UUID id, Model model) {
        ShowView show = showsDataFeignClient.findShowInfoById(KeycloakUtils.getBearerToken(), id);
        model.addAttribute("show", show);
        return "show";
    }

    @GetMapping(BASE_URL + "/users/shows/{id}")
    public String addShowUserPage(@PathVariable("id") UUID id, Model model) {
        try {
            ShowView show = showsDataFeignClient.findShowInfoById(KeycloakUtils.getBearerToken(), id);
            List<WatchState> states = dictionariesFeignClient.getStates(KeycloakUtils.getBearerToken());
            List<Integer> scores = dictionariesFeignClient.getScores(KeycloakUtils.getBearerToken());
            model.addAttribute("scores", scores);
            model.addAttribute("states", states);
            model.addAttribute("show", show);
            return "addShowUser";
        } catch (FeignException e) {
            return handleFeignException(model, e);
        }
    }

    @PostMapping(BASE_URL + "/users/shows/{id}")
    public String addShowUser(@PathVariable("id") UUID id,
                              @ModelAttribute("request") UsersShowRequest usersShowRequest,
                              Model model) {
        try {
            UUID recordId = usersShowsFeignClient.addShow(KeycloakUtils.getBearerToken(), usersShowRequest);
            return "redirect:/";
        } catch (FeignException e) {
            return handleFeignException(model, e);
        }
    }

    @PutMapping(BASE_URL + "/users/shows/{id}")
    public String updateShowUser(@PathVariable("id") UUID id,
                                 @ModelAttribute("request") UsersShowRequest usersShowRequest,
                                 Model model) {
        try {
            usersShowsFeignClient.updateShow(KeycloakUtils.getBearerToken(), usersShowRequest.getShowId(), usersShowRequest);
            return "redirect:/";
        } catch (FeignException e) {
            return handleFeignException(model, e);
        }
    }

    @PreAuthorize("hasAnyRole('USER')")
    @GetMapping(BASE_URL + "/myShows")
    public String myShows(Model model) {
        try {
            List<GetUserShowsResponse> records = usersShowsFeignClient.getUserShows(KeycloakUtils.getBearerToken());
            List<WatchState> states = dictionariesFeignClient.getStates(KeycloakUtils.getBearerToken());
            List<Integer> scores = dictionariesFeignClient.getScores(KeycloakUtils.getBearerToken());
            model.addAttribute("scores", scores);
            model.addAttribute("states", states);
            model.addAttribute("records", records);
            return "myShows";
        } catch (FeignException e) {
            return handleFeignException(model, e);
        }
    }

    @DeleteMapping(BASE_URL + "/myShows/{id}")
    @PreAuthorize("hasAnyRole('USER')")
    public String deleteUserShow(@PathVariable("id") UUID id, Model model) {
        usersShowsFeignClient.deleteShowRecordById(KeycloakUtils.getBearerToken(), id);
        return "redirect:/front/myShows";
    }

    @DeleteMapping(BASE_URL + "/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public String delete(@PathVariable("id") UUID id, Model model) {
        showsDataFeignClient.deleteShowInfoById(KeycloakUtils.getBearerToken(), id);
        return "redirect:/";
    }

    @GetMapping(BASE_URL + "/pictures/{id}")
    public ResponseEntity<Resource> getPoster(@PathVariable("id") UUID id) {
        ResponseEntity<Resource> poster = showsDataFeignClient.findPosterByShowId(KeycloakUtils.getBearerToken(), id);
        return poster;
    }

    @GetMapping(BASE_URL + "/edit/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public String getEditPage(@PathVariable("id") UUID id, Model model) {
        try {
            ShowView show = showsDataFeignClient.findShowInfoById(KeycloakUtils.getBearerToken(), id);
            List<ShowGenre> genres = dictionariesFeignClient.getGenres(KeycloakUtils.getBearerToken());
            List<ShowStage> showsStages = dictionariesFeignClient.getShowsStages(KeycloakUtils.getBearerToken());
            model.addAttribute("show", show)
                 .addAttribute("genres", genres)
                 .addAttribute("stages", showsStages);
            return "showEdit";
        } catch (FeignException e) {
            return handleFeignException(model, e);
        }
    }

    @PutMapping(BASE_URL + "/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public String editShow(@PathVariable("id") UUID id, @ModelAttribute("show") ShowFrontDto show, Model model) {
        showsDataFeignClient.editShowInfoById(KeycloakUtils.getBearerToken(), id, showsDtoMapper.map(show));
        return "redirect:/front/" + id.toString();
    }

    @PostMapping(BASE_URL)
    @PreAuthorize("hasAnyRole('ADMIN')")
    public String addShow(@ModelAttribute("show") ShowFrontDto show, Model model) {
        UUID id = showsDataFeignClient.addShow(KeycloakUtils.getBearerToken(), showsDtoMapper.map(show));
        return "redirect:/front/" + id.toString();
    }

    @GetMapping(BASE_URL + "/create")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public String getCreatePage(Model model) {
        try {
            List<ShowGenre> genres = dictionariesFeignClient.getGenres(KeycloakUtils.getBearerToken());
            List<ShowStage> showsStages = dictionariesFeignClient.getShowsStages(KeycloakUtils.getBearerToken());
            model.addAttribute("genres", genres)
                 .addAttribute("stages", showsStages);
            return "addShow";
        } catch (FeignException e) {
            return handleFeignException(model, e);
        }
    }

    private String handleFeignException(Model model, FeignException e) {
        model.addAttribute("error", e.status());
        model.addAttribute("details", e.contentUTF8());
        return "error";
    }

}
