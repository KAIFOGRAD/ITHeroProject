package com.ithero.geomap.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserProfileController {

    /**
     * Заглушка для страницы профиля
     * @param model - модель для передачи данных в шаблон
     * @return имя шаблона страницы профиля
     */
    @GetMapping("/profile")
    public String profileStub(Model model) {
        // Минимальный набор тестовых данных
        model.addAttribute("title", "Профиль");
        model.addAttribute("message", "Раздел в разработке");
        model.addAttribute("status", "Доступно с версии 2.0");
        
        return "profile";  // Возвращает шаблон profile.html
    }
}