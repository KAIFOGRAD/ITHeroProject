package com.ithero.geomap.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Value;


@Controller
public class MapController{

    @Value("${yandex.maps.api.key}") 
    private String yandexApiKey;


    @GetMapping("/map")
    public String showMap(Model model) {
        model.addAttribute("defaultLat", 56.838011);
        model.addAttribute("defaultLon", 60.597465);
        model.addAttribute("yandexApiKey", yandexApiKey); 
        return "map";
    }

}