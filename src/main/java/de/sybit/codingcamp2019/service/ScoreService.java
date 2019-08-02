package de.sybit.codingcamp2019.service;

import de.sybit.codingcamp2019.objects.Game;
import de.sybit.codingcamp2019.objects.User;

public interface ScoreService {

   /**
    * Returns a gameScore
    *
    * @param game
    * @return
    */

   int gameScore(Game game);

   /**
    * Returns a gameSessionHighScore
    *
    * @return
    */

   int gameSessionHighScore(User user);
}
