package pl.dmcs.iwamzelazko.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.dmcs.iwamzelazko.model.QuadraticEquation;

import javax.validation.Valid;

@Controller
public class QuadraticEquationController {
    @RequestMapping("/quadraticEquation")
    public String quadraticEquation(Model model) {
        model.addAttribute("message", "Simple string from QuadraticEquationController.");
        QuadraticEquation newQuadraticEquation = new QuadraticEquation();
        model.addAttribute("quadraticEquation", newQuadraticEquation);
        return "quadraticEquation";
    }

    @RequestMapping(value = "/solveQuadraticEquation.html", method = RequestMethod.POST)
    public String solveQuadraticEquation(@Valid @ModelAttribute("quadraticEquation") QuadraticEquation quadraticEquation, BindingResult result) {
        if (result.hasErrors()) {
            return "quadraticEquation";
        }
        else {
            try{
                quadraticEquation.printRoots();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return "redirect:quadraticEquation";
    }
}
