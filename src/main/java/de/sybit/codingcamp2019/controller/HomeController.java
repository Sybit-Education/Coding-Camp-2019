package de.sybit.codingcamp2019.controller;

import de.sybit.codingcamp2019.objects.Game;
import de.sybit.codingcamp2019.objects.PinPlacement;
import de.sybit.codingcamp2019.service.ColorService;
import de.sybit.codingcamp2019.service.GameService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;


@Controller
public class HomeController {

   private static final Logger LOGGER = LoggerFactory.getLogger(HomeController.class);

   @Autowired
   private GameService gameService;

   @GetMapping(value = "/")
   public String newGame(Model model, HttpSession session) {
      LOGGER.debug("--> newGame");
      gameService.checkExistingGameForSession(session);
      LOGGER.debug("<-- newGame");
      return "index";
   }

   @PostMapping(value = "/")
   public ModelAndView attempt(HttpSession session, @ModelAttribute PinPlacement pinPlacement, ModelAndView modelAndView) {
      LOGGER.debug("--> attempt");

      modelAndView.setViewName("index");
      LOGGER.debug("<-- attempt");
      return modelAndView;
   }



   @GetMapping("/restart")
   public String restartCurrentGame(HttpSession session) {
      LOGGER.debug("--> restartCurrentGame");
      gameService.restartGame(session);
      LOGGER.debug("--> restartCurrentGame");
      return "redirect:/";
   }

}
