package de.sybit.codingcamp2019.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AboutController {
   private static final Logger LOGGER = LoggerFactory.getLogger(AboutController.class);

   @GetMapping(value = "/mastermind/about-us")
   public String showAbout() {
      LOGGER.debug("--> showAbout");

      LOGGER.debug("<-- showAbout");
      return "about";
   }
}
