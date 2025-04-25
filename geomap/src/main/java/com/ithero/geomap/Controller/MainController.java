package com.ithero.geomap.Controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;

@Controller
public class MainController{

    @Value("${yandex.maps.api.key}") 
    private String yandexApiKey;

	@RequestMapping("/")
	public String home(){
		return "index";
	}

}