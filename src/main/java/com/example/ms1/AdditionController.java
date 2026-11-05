package com.example.ms1;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdditionController {
    private final AdditionService additionService;

    public AdditionController(AdditionService additionService) {
        this.additionService = additionService;
    }

    @GetMapping("/add")
    public long add(@RequestParam("a") int a, @RequestParam("b") int b) {
        return additionService.add(a, b);
    }
}
