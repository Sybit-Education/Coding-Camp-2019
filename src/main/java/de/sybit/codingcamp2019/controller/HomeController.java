package de.sybit.codingcamp2019.controller;

import de.sybit.codingcamp2019.objects.PinPlacement;
import de.sybit.codingcamp2019.objects.ResponseObject;
import de.sybit.codingcamp2019.objects.RowObject;
import de.sybit.codingcamp2019.service.ColorService;
import de.sybit.codingcamp2019.service.FeedbackService;
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
import java.util.ArrayList;
import java.util.List;


@Controller
public class HomeController {

   private List<RowObject> rowObjectList = new ArrayList<>();

   private static final Logger LOGGER = LoggerFactory.getLogger(HomeController.class);

   @Autowired
   private ColorService colorService;

   @Autowired
   private GameService gameService;

   @Autowired
   private FeedbackService feedbackService;

   @GetMapping(value = "/")
   public String newGame(Model model, HttpSession session) {
      LOGGER.debug("--> newGame");
      gameService.checkExistingGameForSession(session);
      PinPlacement pinPlacement = new PinPlacement();
      model.addAttribute(pinPlacement);
      model.addAttribute("allPossibleColors", colorService.getAllPossibleColorsForPicker());
      clearAttempts();
      LOGGER.debug("<-- newGame");
      return "index";
   }

   @PostMapping(value = "/")
   public ModelAndView attempt(HttpSession session, @ModelAttribute PinPlacement pinPlacement, ModelAndView modelAndView) {
      LOGGER.debug("--> attempt");
      ResponseObject responseObject = feedbackService.getFeedbackFor(session, pinPlacement);
      if (checkMaxAmounts(12)) {
         addColorPosition(pinPlacement, responseObject);
      } else {
         modelAndView.addObject("buttonDisable", true);
      }
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

   private boolean checkMaxAmounts(int amount) {
      LOGGER.debug("--> checkMaxAmounts");
      boolean reached = false;
      if (rowObjectList.size() <= amount) {
         reached = true;
      }
      LOGGER.debug("<-- checkMaxAmounts");
      return reached;
   }

   private void addColorPosition(PinPlacement pinPlacement, ResponseObject responseObject) {
      LOGGER.debug("--> addColorPosition");
      RowObject rowObject = new RowObject();
      pinPlacement.getColors().forEach(rowObject::addColor);
      rowObject.addFeedback(responseObject.getCorrectPositions(), responseObject.getCorrectColors());
      rowObjectList.add(rowObject);
      LOGGER.debug("<-- addColorPosition");
   }

   private void clearAttempts() {
      LOGGER.debug("--> clearAttempts");
      rowObjectList.removeAll(rowObjectList);
      LOGGER.debug("<-- clearAttempts");
   }
}
