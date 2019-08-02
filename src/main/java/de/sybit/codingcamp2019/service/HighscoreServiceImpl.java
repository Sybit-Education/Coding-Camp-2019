package de.sybit.codingcamp2019.service;

import de.sybit.codingcamp2019.objects.Game;
import de.sybit.codingcamp2019.objects.Highscore;
import de.sybit.codingcamp2019.objects.User;
import de.sybit.codingcamp2019.repository.HighscoreRepository;
import de.sybit.codingcamp2019.repository.ScoreRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class HighscoreServiceImpl implements HighscoreService {

   private static final Logger LOGGER = LoggerFactory.getLogger(HighscoreServiceImpl.class);

   @Autowired
   private HighscoreRepository highscoreRepository;

   @Autowired
   private ScoreRepository scoreRepository;


   @Override
   public int gameScore(Game game) {
      LOGGER.debug("--> gameScore");
      double gameScore = 1000;
      double duration = getDuration(game);
      double attempt = game.getAttemptCount();

      if (game.getAttemptCount() < 12){
         if (duration < 3000) {
            gameScore = gameScore - (duration/3) + ((13 - attempt) * 100);
         }
         else {
            gameScore = (13 - attempt) * 100;
         }
      }else{
         gameScore = gameScore - 1000;
      }

      int gameScoreInt = (int) gameScore;
      LOGGER.debug("<-- gameScore");
      return gameScoreInt;
   }

   private double getDuration(Game game) {
      LocalDateTime startTime = game.getStartTime();
      LocalDateTime endTime = game.getEndTime();
      return (double) ChronoUnit.SECONDS.between(startTime, endTime);
   }

   @Override
   public Highscore gameSessionHighScore(User user) {
         LOGGER.debug("--> gameSessionHighScore");
         double gameScoreSum = 0;
         double gameSessionHighScore;
         List<Game> gameList = user.getGames();
         for(Game currentGame : gameList){
            double score = gameScore(currentGame);
            gameScoreSum = gameScoreSum + score;
         }
         gameSessionHighScore = gameScoreSum * (gameList.size() * 0.5);
         String gameSessionHighScoreSt = String.valueOf(gameSessionHighScore);
         Highscore highscore = new Highscore();
         highscore.setScore(Long.parseLong(gameSessionHighScoreSt));
         highscore.setUser(user);
         highscoreRepository.save(highscore);
         LOGGER.debug("<-- gameSessionHighScore");
         return highscore;
   }
}
