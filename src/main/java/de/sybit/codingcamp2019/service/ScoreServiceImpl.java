package de.sybit.codingcamp2019.service;

import de.sybit.codingcamp2019.objects.Game;
import de.sybit.codingcamp2019.objects.User;
import de.sybit.codingcamp2019.repository.ScoreRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class ScoreServiceImpl implements ScoreService {

   private static final Logger LOGGER = LoggerFactory.getLogger(ScoreServiceImpl.class);

   @Autowired
   private ScoreRepository scoreRepository;

   @Autowired
   private GameService gameService;

   @Override
   public double gameScore(Game game) {
      LOGGER.debug("--> gameScore");
      double gameScore = 1000;
      LocalDateTime startTime = game.getStartTime();
      LocalDateTime endTime = game.getEndTime();
      double duration = ChronoUnit.SECONDS.between(startTime, endTime);
      double attempt = game.getAttemptCount();
      if (duration < 3000) {
         gameScore = gameScore - (duration/3) + ((13 - attempt) * 100);
      }
      else {
         gameScore = (13 - attempt) * 100;
      }
      LOGGER.debug("<-- gameScore");
      return gameScore;
   }

   @Override
   public double gameSessionHighScore(User user) {
      LOGGER.debug("--> gameSessionHighScore");
      double gameScoreSum = 0;
      double gameSessionHighScore = 0;
      List<Game> gameList = scoreRepository.findAllByUser(user);
      for(Game currentGame : gameList){
         double score = gameScore(currentGame);
         gameScoreSum = gameScoreSum + score;
      }
      gameSessionHighScore = gameScoreSum * (gameList.size()/2);

      LOGGER.debug("<-- gameSessionHighScore");
      return gameSessionHighScore;
   }
}
