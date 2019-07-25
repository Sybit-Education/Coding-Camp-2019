package de.sybit.codingcamp2019.controller;

import de.sybit.codingcamp2019.exception.GameNotFoundException;
import de.sybit.codingcamp2019.objects.Game;
import de.sybit.codingcamp2019.objects.GameStateEnum;
import de.sybit.codingcamp2019.service.GameService;
import de.sybit.codingcamp2019.service.ScoreService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
public class ScoreController {

   private static final Logger LOGGER = LoggerFactory.getLogger(ScoreController.class);

   @Autowired
   private ScoreService scoreService;

   @Autowired
   private GameService gameService;


   @GetMapping("/highscore")
   public String getCurrentGameScore(Model model, HttpSession httpSession) {
      LOGGER.debug("--> getCurrentGameScore");
      
      LOGGER.debug("<-- getCurrentGameScore");
      return "score";
   }
}
