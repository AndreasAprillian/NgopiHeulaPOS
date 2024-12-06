package com.example.NgopiHeula.controller;

import com.example.NgopiHeula.model.dto.account.AccountUpsertDTO;
import com.example.NgopiHeula.model.dto.dropdown.DropdownDTO;
import com.example.NgopiHeula.service.abstraction.AccountService;
import com.example.NgopiHeula.utility.DropdownHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping("/index")
    public String index(Model model, @RequestParam(defaultValue = "") String username) {
        model.addAttribute("account",accountService.getAllAccount(username));
        model.addAttribute("breadCrumbs", "Account");
        model.addAttribute("username",username);
        return ("account/index");
    }
    @GetMapping("/registerForm")
    public String registerForm(Model model){
        model.addAttribute("dto", new AccountUpsertDTO());
        List<DropdownDTO> dataRoles = DropdownHelper.roles();
        model.addAttribute("breadCrumbs", "Register new Account");
        model.addAttribute("Dropdown",dataRoles);
        return ("account/register-Form");
    }
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(@Valid @ModelAttribute("dto") AccountUpsertDTO dto,
                           BindingResult bindingResult, Model model){
        List<DropdownDTO> dataRoles = DropdownHelper.roles();
        if (bindingResult.hasErrors()){
            model.addAttribute("dto",dto);
            model.addAttribute("breadCrumbs", "Register new Account");
            model.addAttribute("Dropdown",dataRoles);

            return ("account/register-Form");
        }else {
            accountService.register(dto);
            return ("redirect:/account/index");
        }
    }
}
