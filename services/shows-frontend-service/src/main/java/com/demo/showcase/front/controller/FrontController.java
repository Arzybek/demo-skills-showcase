package com.demo.showcase.front.controller;

import com.demo.showcase.common.dto.ShowsView;
import com.demo.showcase.common.feign.ShowsDataFeignClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class FrontController {

    private final ShowsDataFeignClient showsDataFeignClient;

    @GetMapping("/")
    public String index(Model model) {
        List<ShowsView> shows = showsDataFeignClient.getShows();
        model.addAttribute("shows", shows);
        return "index";
    }
}
