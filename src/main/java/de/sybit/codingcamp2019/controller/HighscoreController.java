package de.sybit.codingcamp2019.controller;

import de.sybit.codingcamp2019.service.GameService;
import de.sybit.codingcamp2019.service.HighscoreService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
public class HighscoreController {

   private static final Logger LOGGER = LoggerFactory.getLogger(HighscoreController.class);




   @Autowired
   private HighscoreService highscoreService;

   @Autowired
   private GameService gameService;


   @GetMapping("/highscore")
   public ModelAndView getCurrentGameScore(ModelAndView modelAndView, HttpSession httpSession) {
      LOGGER.debug("--> getCurrentGameScore");

      modelAndView.addObject("scores", highscoreService.getHighscores(25));
      modelAndView.setViewName("leaderboard");

      LOGGER.debug("<-- getCurrentGameScore");
      return modelAndView;
   }

}
