package de.sybit.codingcamp2019.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RulesController {
   private static final Logger LOGGER = LoggerFactory.getLogger(RulesController.class);

   @GetMapping(value = "/rules")
   public String showRules() {
      LOGGER.debug("--> showRules");

      LOGGER.debug("<-- showRules");
      return "rules";
   }
}
