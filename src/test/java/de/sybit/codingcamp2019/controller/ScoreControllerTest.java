package de.sybit.codingcamp2019.controller;

import de.sybit.codingcamp2019.exception.GameNotFoundException;
import de.sybit.codingcamp2019.objects.Game;
import de.sybit.codingcamp2019.service.GameService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;

import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.junit.Assert.assertEquals;

public class ScoreControllerTest {

   @InjectMocks
   private ScoreController scoreController;

   @Mock
   private Model model;

   @Mock
   private HttpSession httpSession;

   @Mock
   private GameService gameService;

   @Mock
   private Game game;

   @Before
   public void setUp() {
      initMocks(this);
   }

   @Test
   public void getCurrentGameScore_returnScorePage() throws GameNotFoundException {
      when(gameService.getCurrentGameOf(httpSession)).thenReturn(game);

      String page = scoreController.getCurrentGameScore(model, httpSession);

      assertEquals("score", page);
   }
}
