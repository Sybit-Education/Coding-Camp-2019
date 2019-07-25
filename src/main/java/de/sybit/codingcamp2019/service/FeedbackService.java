package de.sybit.codingcamp2019.service;

import de.sybit.codingcamp2019.objects.PinPlacement;
import de.sybit.codingcamp2019.objects.ResponseObject;

import javax.servlet.http.HttpSession;

public interface FeedbackService {

   /**
    * Calculates the Feedback for the Request
    *
    * @return
    */
   ResponseObject getFeedbackFor(HttpSession session, PinPlacement requestPlacement);
}
