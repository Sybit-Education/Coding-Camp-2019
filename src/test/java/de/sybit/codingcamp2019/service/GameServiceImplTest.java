package de.sybit.codingcamp2019.service;

import de.sybit.codingcamp2019.exception.GameNotFoundException;
import de.sybit.codingcamp2019.objects.Game;
import de.sybit.codingcamp2019.objects.GameStateEnum;
import de.sybit.codingcamp2019.objects.PinPlacement;
import de.sybit.codingcamp2019.objects.User;
import de.sybit.codingcamp2019.repository.GameRepository;
import de.sybit.codingcamp2019.objects.SessionKeys;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class GameServiceImplTest {

   @Mock
   private HttpSession session;

   @Mock
   private User user;

   @Mock
   private Game game;

   @Mock
   private GameRepository gameRepository;

   @Mock
   private ColorService colorService;

   @InjectMocks
   private GameServiceImpl gameService;

   @Mock
   private PinPlacement pinPlacement;

   @Before
   public void initMocks() {
      MockitoAnnotations.initMocks(this);
   }

   @Ignore
   @Test
   public void checkExistingGameForSession_gameDoesNotExistsInSession_createNewGame() {
      Game game = gameService.checkExistingGameForSession(session);
      Assert.assertNotNull(game.getPinSolution());
      verify(session).setAttribute(eq(SessionKeys.SESSION_GAME.toString()), any(Game.class));
   }

   @Ignore
   @Test
   public void checkForExistingGame_gameExistsInSession() {
      game.setAttemptCount(1);
      when((Game) session.getAttribute(SessionKeys.SESSION_GAME.toString())).thenReturn(game);
      assertEquals(game.getAttemptCount(), gameService.checkExistingGameForSession(session).getAttemptCount());
   }

   @Ignore
   @Test
   public void createGameFor() {
      ArrayList<String> colorList = getColorList(3);
      when(colorService.getAmountOfRandomColor(anyInt())).thenReturn(colorList);
      Game game = gameService.createGameFor(session);
      Assert.assertNotNull(game.getPinSolution());
      verify(session).setAttribute(eq(SessionKeys.SESSION_GAME.toString()), any(Game.class));
   }

   @Ignore
   @Test
   public void createGameFor_WithRandomColors() {
      ArrayList<String> colorList = getColorList(4);
      when(colorService.getAmountOfRandomColor(anyInt())).thenReturn(colorList);
      Game game = gameService.createGameFor(session);
      PinPlacement pinSolution = game.getPinSolution();
      Assert.assertNotNull(pinSolution.getColors().get(0));
      Assert.assertNotNull(pinSolution.getColors().get(1));
      Assert.assertNotNull(pinSolution.getColors().get(2));
      Assert.assertNotNull(pinSolution.getColors().get(3));
   }

   @Ignore
   @Test
   public void getAllGamesOfUser() {
      List<Game> gameList = getGameList(2);

      when(gameRepository.findByUser(user)).thenReturn(gameList);
      assertEquals(2, gameService.getAllGamesOfUser(user).size());

   }

   @Ignore
   @Test
   public void restartGame_sessionRemoved() {
      gameService.restartGame(session);

      verify(session).removeAttribute(SessionKeys.SESSION_GAME.toString());
   }

   @Test
   public void getCurrentGameOf_gameFound_returnGame() throws GameNotFoundException {
      when(session.getAttribute(SessionKeys.SESSION_GAME.toString())).thenReturn(game);

      Game currentGame = gameService.getCurrentGameOf(session);

      assertEquals(game, currentGame);
   }

   @Test(expected = GameNotFoundException.class)
   public void getCurrentGameOf_gameNotFound_throwException() throws GameNotFoundException {
      when(session.getAttribute(SessionKeys.SESSION_GAME.toString())).thenReturn(null);

      Game currentGame = gameService.getCurrentGameOf(session);

      assertNull(currentGame);
   }

   @Test
   public void checkGameStatus_attemptsReachedMaxTries_setGameStateToLoose() {
      when(session.getAttribute(SessionKeys.SESSION_GAME.toString())).thenReturn(game);
      when(game.getAttemptCount()).thenReturn(9);

      gameService.checkGameStatus(session, pinPlacement);

      verify(game).setStatus(GameStateEnum.LOOSE);
   }

   private List<Game> getGameList(int count) {
      List<Game> gameArrayList = new ArrayList<>();
      for (int i = 0; i < count; i++) {
         gameArrayList.add(game);
      }
      return gameArrayList;
   }

   private ArrayList<String> getColorList(int amount) {
      ArrayList<String> colorArrayList = new ArrayList<>();
      for (int i = 0; i < amount; i++) {
         colorArrayList.add("color");
      }
      return colorArrayList;
   }
}
