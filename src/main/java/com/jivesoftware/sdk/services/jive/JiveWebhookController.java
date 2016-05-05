package com.jivesoftware.sdk.services.jive;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jivesoftware.sdk.dao.WebhookInstanceDAO;

@RestController
@RequestMapping("/jive/webhook")
public class JiveWebhookController {
	private static final Logger logger = Logger.getLogger(JiveWebhookController.class.getName());

	@Autowired
	private WebhookInstanceDAO webhookInstanceDAO;	

//	
//	private void saveInstance(WebhookInstance webhook) {
//		if (webhook != null) {
//			logger.log(Level.FINE,"Saving Webhook["+webhook.getId()+"]...");		
//			webhookInstanceDAO.save(webhook);
//		} // end if
//	} // end saveInstance
//	
//	private void removeInstance(WebhookInstance webhook) {
//		if (webhook != null) {
//			logger.log(Level.FINE,"Deleting Webhook["+webhook.getId()+"]...");
//			webhookInstanceDAO.delete(webhook);			
//		} // end if
//	} // end removeInstance
	
} // end class
