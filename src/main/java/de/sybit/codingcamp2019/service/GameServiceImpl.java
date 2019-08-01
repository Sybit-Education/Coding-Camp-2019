package de.sybit.codingcamp2019.service;

import de.sybit.codingcamp2019.exception.GameNotFoundException;
import de.sybit.codingcamp2019.objects.Game;
import de.sybit.codingcamp2019.objects.GameStateEnum;
import de.sybit.codingcamp2019.objects.PinPlacement;
import de.sybit.codingcamp2019.objects.User;
import de.sybit.codingcamp2019.repository.GameRepository;
import de.sybit.codingcamp2019.objects.SessionKeys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class GameServiceImpl implements GameService {

   public static final int MAX_TRIES = 10;

   @Autowired
   private GameRepository gameRepository;

   @Autowired
   private ColorService colorService;

   private static final Logger LOGGER = LoggerFactory.getLogger(GameServiceImpl.class);


   @Override
   public Game checkExistingGameForSession(HttpSession session) {
      LOGGER.debug("--> checkExistingGameForSession");
      Game game;

      try {
         game = getCurrentGameOf(session);
      } catch (GameNotFoundException e) {
         game = createGameFor(session);
      }
      LOGGER.debug("<-- checkExistingGameForSession");
      return game;
   }

   @Override
   public Game getCurrentGameOf(final HttpSession session) throws GameNotFoundException {
      Game game = (Game) session.getAttribute(SessionKeys.SESSION_GAME.toString());
      if(game == null) {
         throw new GameNotFoundException("Game not found in session. Session killed?");
      }
      return game;
   }

   @Override
   public List<Game> getAllGamesOfUser(@NotNull final User user) {
      LOGGER.debug("--> getAllGamesOfUser");
      final List<Game> allGames = gameRepository.findByUser(user);
      if (allGames.isEmpty()) {
         throw new IllegalStateException("User has no games");
      }
      LOGGER.debug("<-- getAllGamesOfUser");
      return allGames;
   }

   @Override
   public Game createGameFor(@NotNull final HttpSession session) {
      LOGGER.debug("--> createGameFor");
      final Game newGame = new Game();

      List<String> colors = colorService.getAmountOfRandomColor(4);
      Map<Integer, String> solution = new HashMap<>();

      for (int i = 0; i < 4; i++) {
         solution.put(i ,colors.get(i));
      }

      PinPlacement pinPlacement = new PinPlacement();

      pinPlacement.setColors(solution);
      newGame.setPinSolution(pinPlacement);
      session.setAttribute(SessionKeys.SESSION_GAME.toString(), newGame);

      LOGGER.debug("<-- createGameFor");
      return newGame;
   }

   @Override
   public GameStateEnum checkGameStatus(@NotNull HttpSession session, @NotNull PinPlacement currentPinPlacement) {
      LOGGER.debug("--> checkGameStatus");
      Game game = null;
      try {
         game = getCurrentGameOf(session);
         PinPlacement pinPlacementSolution = game.getPinSolution();
         if (pinPlacementSolution ==  currentPinPlacement){
            game.setStatus(GameStateEnum.WON);

         }
      }
      catch(GameNotFoundException e){

      }
      LOGGER.debug("<-- checkGameStatus");
      return game.getStatus();
   }

   @Override
   public void restartGame(HttpSession session) {
      LOGGER.debug("--> restartGame");

      //TODO
      LOGGER.debug("<-- restartGame");
   }

}
