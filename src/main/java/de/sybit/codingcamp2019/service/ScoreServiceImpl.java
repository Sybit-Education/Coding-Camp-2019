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


   @Override
   public double gameScore(Game game, int amountOfColor) {
      LOGGER.debug("--> gameScore");
      double gameScore = 0;
      
      //TODO
      
      LOGGER.debug("<-- gameScore");
      return gameScore;

   }

   @Override
   public double gameSessionHighScore(User user, int amountOfColor) {
      LOGGER.debug("--> gameSessionHighScore");
     

      double gameSessionHighScore = 0;
      //TODO

 
      LOGGER.debug("<-- gameSessionHighScore");
      return gameSessionHighScore;
   }
}
