package de.sybit.codingcamp2019.service;

import de.sybit.codingcamp2019.objects.Game;
import de.sybit.codingcamp2019.objects.Highscore;
import de.sybit.codingcamp2019.objects.User;

import java.util.List;

public interface HighscoreService {

   /**
    * Returns a gameScore
    *
    * @param game
    * @return
    */

   double gameScore(Game game, int amountOfColors);

   /**
    * Returns a gameSessionHighScore
    *
    * @return
    */

   double gameSessionHighScore(User user, int amountOfColor);

   List<Highscore> getHighscores(int maxEntries);
}
