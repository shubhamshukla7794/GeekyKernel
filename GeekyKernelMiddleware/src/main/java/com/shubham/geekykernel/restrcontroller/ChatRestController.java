package com.shubham.geekykernel.restrcontroller;

import java.util.Date;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RestController;

import com.shubham.geekykernel.domain.Message;
import com.shubham.geekykernel.domain.OutputMessage;

@RestController
public class ChatRestController 
{
	@MessageMapping("/chat")
	@SendTo("/topic/message")
	public OutputMessage sendMessage(Message message)
	{
		return new OutputMessage(message,new Date());
	}
}