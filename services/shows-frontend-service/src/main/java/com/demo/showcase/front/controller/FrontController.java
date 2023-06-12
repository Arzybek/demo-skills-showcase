package com.demo.showcase.front.controller;

import com.demo.showcase.common.dto.ShowRequestDto;
import com.demo.showcase.common.dto.ShowShortInfo;
import com.demo.showcase.common.dto.ShowView;
import com.demo.showcase.common.dto.mapper.ShowsDtoMapper;
import com.demo.showcase.common.feign.ShowsDataFeignClient;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class FrontController {

    private final ShowsDataFeignClient showsDataFeignClient;

    private final ShowsDtoMapper showsDtoMapper;

    @GetMapping("/")
    public String index(Model model) {
        List<ShowShortInfo> shows = showsDataFeignClient.getShows();
        model.addAttribute("shows", shows);
        return "index";
    }

    @GetMapping("/search")
    public String search(Model model, String searchTitle) {
        List<ShowShortInfo> shows = showsDataFeignClient.findShows(searchTitle);
        model.addAttribute("shows", shows);
        return "index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") UUID id, Model model) {
        ShowView show = showsDataFeignClient.findShowInfoById(id);
        model.addAttribute("show", show);
        return "show";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") UUID id, Model model) {
        showsDataFeignClient.deleteShowInfoById(id);
        return "redirect:/";
    }

    @GetMapping("/pictures/{id}")
    public ResponseEntity<Resource> getPoster(@PathVariable("id") UUID id) {
        ResponseEntity<Resource> poster = showsDataFeignClient.findPosterByShowId(id);
        return poster;
    }

    @GetMapping("/edit/{id}")
    public String getEditPage(@PathVariable("id") UUID id, Model model) {
        ShowView show = showsDataFeignClient.findShowInfoById(id);
        model.addAttribute("show", show);
        return "showEdit";
    }

    @PutMapping("/{id}")
    public String showEdit(@PathVariable("id") UUID id, ShowView show, Model model) {
        showsDataFeignClient.editShowInfoById(id, showsDtoMapper.map(show));
        model.addAttribute("show", show);
        return "show";
    }

    @PostMapping("/")
    public String addShow(ShowRequestDto show, Model model) {
        UUID id = showsDataFeignClient.addShow(show);
        return "redirect:/" + id.toString();
    }

    @GetMapping("/create")
    public String getCreatePage(Model model) {
        return "addShow";
    }
}
