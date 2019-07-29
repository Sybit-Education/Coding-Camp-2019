package de.sybit.codingcamp2019.controller;

import de.sybit.codingcamp2019.exception.GameNotFoundException;
import de.sybit.codingcamp2019.objects.*;
import de.sybit.codingcamp2019.service.ColorService;
import de.sybit.codingcamp2019.service.FeedbackService;
import de.sybit.codingcamp2019.service.GameService;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

import java.util.Collections;

import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class HomeControllerTest {

   @InjectMocks
   private HomeController homeController;

   @Mock
   private HttpSession httpSession;

   @Mock
   private GameService gameService;

   @Mock
   private ColorService colorService;

   @Mock
   private Model model;

   @Mock
   private ColorSelectionObject colorSelectionObject;

   @Mock
   private FeedbackService feedbackService;

   @Mock
   private ResponseObject responseObject;

   @Mock
   private PinPlacement pinPlacement;

   @Mock
   private ModelAndView modelAndView;

   @Mock
   private Game game;

   @Before
   public void setUp() {
      initMocks(this);
   }

   @Test
   public void restartCurrentGame_returnToHomePage() {
      String redirectValue = homeController.restartCurrentGame(httpSession);

      verify(gameService).restartGame(httpSession);
      assertEquals("redirect:/", redirectValue);
   }

   @Ignore
   @Test
   public void newGame_returnIndexPage() {
      when(colorService.getAllPossibleColorsForPicker()).thenReturn(Collections.singletonList(colorSelectionObject));

      String page = homeController.newGame(model, httpSession);

      verify(model).addAttribute("allPossibleColors", Collections.singletonList(colorSelectionObject));
      assertEquals("index", page);
   }

   @Ignore
   @Test
   public void attempt_gameStateIsPlaying_setCorrectObjects() {
      when(feedbackService.getFeedbackFor(httpSession, pinPlacement)).thenReturn(responseObject);
      when(gameService.checkGameStatus(httpSession, pinPlacement)).thenReturn(GameStateEnum.PLAYING);
      when(gameService.checkExistingGameForSession(httpSession)).thenReturn(game);

      homeController.attempt(httpSession, pinPlacement, modelAndView);

      verify(modelAndView).addObject("attemptCount", gameService.checkExistingGameForSession(httpSession).getAttemptCount());
      verify(modelAndView).addObject("correctColors", responseObject.getCorrectColors());
      verify(modelAndView).addObject("correctPositions", responseObject.getCorrectPositions());
   }

   @Ignore
   @Test
   public void attempt_gameStateIsWon_setCorrectObjects() throws GameNotFoundException {
      when(feedbackService.getFeedbackFor(httpSession, pinPlacement)).thenReturn(responseObject);
      when(gameService.checkGameStatus(httpSession, pinPlacement)).thenReturn(GameStateEnum.WON);
      when(gameService.getCurrentGameOf(httpSession)).thenReturn(game);

      homeController.attempt(httpSession, pinPlacement, modelAndView);

      verify(modelAndView).addObject("pinSolution", game.getPinSolution());
      verify(modelAndView).addObject("successfullEnd", true);
   }

   @Ignore
   @Test
   public void attempt_gameIsLost_setCorrectObjects() throws GameNotFoundException {
      when(feedbackService.getFeedbackFor(httpSession, pinPlacement)).thenReturn(responseObject);
      when(gameService.checkGameStatus(httpSession, pinPlacement)).thenReturn(GameStateEnum.LOOSE);
      when(gameService.getCurrentGameOf(httpSession)).thenReturn(game);

      homeController.attempt(httpSession, pinPlacement, modelAndView);

      verify(modelAndView).addObject("pinSolution", game.getPinSolution());
      verify(modelAndView).addObject("successfullEnd", false);
   }
}
