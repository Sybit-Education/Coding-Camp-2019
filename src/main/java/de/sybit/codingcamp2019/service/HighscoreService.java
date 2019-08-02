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

   int gameScore(Game game);

   /**
    * Returns a gameSessionHighScore
    *
    * @return
    */

   Highscore gameSessionHighScore(User user);

   List<Highscore> getHighscores(int maxEntries);
}
