package com.resellerapp.controller;


import com.resellerapp.model.dto.UserDTO;
import com.resellerapp.model.entity.Offer;
import com.resellerapp.service.OfferService;
import com.resellerapp.service.UserService;
import com.resellerapp.util.LoggedUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import java.util.Set;

@Controller
public class HomeControllerImpl implements HomeController {

    private final LoggedUser loggedUser;
    private final UserService userService;
    private final OfferService offerService;

    @Autowired
    public HomeControllerImpl(LoggedUser loggedUser, UserService userService, OfferService offerService) {
        this.loggedUser = loggedUser;
        this.userService = userService;
        this.offerService = offerService;
    }

    @Override
    public String index() {
        if (!loggedUser.isLogged()) {
            return "index";
        }
        return "redirect:/home";
    }

    @Override
    public String home(Model model) {
        if (!loggedUser.isLogged()) {
            return "redirect:/";
        }

        Long userId = loggedUser.getId();

        UserDTO userDTO = userService.findById(userId);
        Set<Offer> userOffers = offerService.findOffersFromUser(userId);
        Set<Offer> notUserOffers = offerService.findOffersNotFromUser(userId);
        Set<Offer> boughtOffers = offerService.findOffersUserBought(userId);

        model.addAttribute("userDTO", userDTO);
        model.addAttribute("userOffers", userOffers);
        model.addAttribute("notUserOffers", notUserOffers);
        model.addAttribute("boughtOffers", boughtOffers);

        return "home";
    }
}
