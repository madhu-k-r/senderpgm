package com.madhu;

import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.web.HttpMediaTypeException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.databind.util.JSONPObject;

@RestController
public class SenderpgmController {

	static Logger log=Logger.getLogger(SenderpgmController.class);
	private String msg;
	@Autowired
	private RestTemplate restTemplate;
	
	@RequestMapping("/send/{message}")
	public String sender(@PathVariable String message)
	{
		msg=message;
		log.info(message);
		return message;
	}
	
	@RequestMapping("/sender")
	public String send() throws JSONException
	{
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("sender","message");
		log.info(jsonObject+" "+msg);
		return jsonObject.toString()+" "+msg;
	}
	@RequestMapping("/message")
	public String receiver() throws JSONException
	{

		JSONObject jsonObject = new JSONObject();
		jsonObject.put(" ", restTemplate.exchange("http://receiverpgm-git-receiver-and-sender.apps.lab.tayana.in/sent", HttpMethod.GET,null,String.class).getBody());
		log.info("Receiver message "+jsonObject);
		System.out.println(jsonObject);
		return jsonObject.toString();
	}
}

