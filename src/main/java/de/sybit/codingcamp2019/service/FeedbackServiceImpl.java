package de.sybit.codingcamp2019.service;

import de.sybit.codingcamp2019.exception.GameNotFoundException;
import de.sybit.codingcamp2019.objects.Game;
import de.sybit.codingcamp2019.objects.PinPlacement;
import de.sybit.codingcamp2019.objects.ResponseObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.*;

@Service
public class FeedbackServiceImpl implements FeedbackService {

   private static final Logger LOGGER = LoggerFactory.getLogger(FeedbackServiceImpl.class);

   @Autowired
   private GameService gameService;

   @Override
   public ResponseObject getFeedbackFor(final HttpSession session, final PinPlacement requestPlacement) {
      LOGGER.debug("--> getFeedbackFor");
      final ResponseObject responseObject = new ResponseObject();
      //TODO 

      LOGGER.debug("<-- getFeedbackFor");
      return responseObject;
   }

 
}
