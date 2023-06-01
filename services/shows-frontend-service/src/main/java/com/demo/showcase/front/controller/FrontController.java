package com.demo.showcase.front.controller;

import com.demo.showcase.common.dto.ShowsShortInfo;
import com.demo.showcase.common.dto.ShowsView;
import com.demo.showcase.common.feign.ShowsDataFeignClient;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class FrontController {

    private final ShowsDataFeignClient showsDataFeignClient;

    @GetMapping("/")
    public String index(Model model) {
        List<ShowsShortInfo> shows = showsDataFeignClient.getShows();
        model.addAttribute("shows", shows);
        return "index";
    }

    @GetMapping("/search")
    public String search(Model model, String searchTitle) {
        List<ShowsShortInfo> shows = showsDataFeignClient.findShows(searchTitle);
        model.addAttribute("shows", shows);
        return "index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") UUID id, Model model) {
        ShowsView shows = showsDataFeignClient.findShowInfoById(id);
        model.addAttribute("shows", shows);
        return "show";
    }

    @GetMapping("/pictures/{id}")
    public ResponseEntity<Resource> getPoster(@PathVariable("id") UUID id) {
        ResponseEntity<Resource> poster = showsDataFeignClient.findPosterByShowId(id);
        return poster;
    }
}
