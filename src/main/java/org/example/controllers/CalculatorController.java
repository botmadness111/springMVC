package org.example.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/calculator")
public class CalculatorController {
    @GetMapping("/calculate")
    public String getCalculatePage(@RequestParam(value = "a", required = false) Integer a,
                                   @RequestParam(value = "b", required = false) Integer b,
                                   @RequestParam(value = "action", required = false) String action,
                                   Model model) {

        String message;
        if (b == null) b = 0;
        if (a == null) a = 0;
        if (action.equals("multiplication")) {
            message = String.valueOf(a * b);
        } else if (action.equals("addition")) {
            message = String.valueOf(a + b);
        } else if (action.equals("divison")) {
            message = String.valueOf(a - b);
        } else {
            if (b == 0) {
                message = "Error";
            } else {
                message = String.valueOf(a / b);
            }
        }

        model.addAttribute("message", message);

        return "/calculatir/calculator";
    }
}
