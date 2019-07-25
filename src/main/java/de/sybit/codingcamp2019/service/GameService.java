package de.sybit.codingcamp2019.service;

import de.sybit.codingcamp2019.exception.GameNotFoundException;
import de.sybit.codingcamp2019.objects.Game;
import de.sybit.codingcamp2019.objects.GameStateEnum;
import de.sybit.codingcamp2019.objects.PinPlacement;
import de.sybit.codingcamp2019.objects.User;

import javax.servlet.http.HttpSession;
import javax.validation.constraints.NotNull;
import java.util.List;

public interface GameService {

   /**
    * Checks if session has an existing game and creates it
    *
    * @param session
    * @return the new created or existing game
    */

   Game checkExistingGameForSession(HttpSession session);

   /**
    * Get the current game from the session
    *
    * @param session
    * @return the current Game
    */
   Game getCurrentGameOf(HttpSession session) throws GameNotFoundException;

   /**
    * Finds all existing games by given User
    *
    * @param user
    * @return all Games for specified User
    */

   List<Game> getAllGamesOfUser(User user);

   /**
    * Creates a new Game and saves it in the current Session
    *
    * @param session
    * @return the new Game
    */
   Game createGameFor(HttpSession session);

   /**
    * Checks the state of the game
    *
    * @param session
    * @param currentPinPlacement
    * @return
    */
   GameStateEnum checkGameStatus(@NotNull HttpSession session, @NotNull PinPlacement currentPinPlacement);

   /**
    * Restart the current game
    */
   void restartGame(HttpSession session);

}
