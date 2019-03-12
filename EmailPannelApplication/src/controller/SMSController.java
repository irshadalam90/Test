package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


import javax.servlet.http.HttpServletRequest;
@Controller
public class SMSController {
	
	
	@RequestMapping("smspage")
	public ModelAndView emailPannelPage(){
		ModelAndView view = new ModelAndView("sms");
		return view;
	}

	@RequestMapping(value="/sendsms", method=RequestMethod.POST)
	public String sendSms(HttpServletRequest req){
		//ModelAndView view = new ModelAndView("sms");
		
		String number = req.getParameter("number");
		String senderMessage = req.getParameter("sms");
			try {
				// Construct data
				/*String apiKey = "apikey=" + "78ee61f2-8e7f-4564-8acd-8caf68ec80e5";*/
				/*String apiKey = "apikey=" + "ACd37c05775d19074be5781181ca3f1c73";*/
				/*String apiKey = "apikey=" + "nCccNVA25Sk-LyTRPm0H7QBU6jdmBV845WX3XW2w48";*/
				String apiKey = "apikey=" + "e215398a8820abd2c7a11a6cd5b1009d";
				
				String message = "&message=" + senderMessage;
				String sender = "&sender=" + "TXTLCL";
				String numbers = "&numbers=" + number;
				
				// Send data
				HttpURLConnection conn = (HttpURLConnection) new URL("https://api.txtlocal.com/send/").openConnection();
				/*HttpURLConnection conn = (HttpURLConnection) new URL("https://api.karix.io/message/").openConnection();*/
				String data = apiKey + numbers + message + sender;
				conn.setDoOutput(true);
				conn.setRequestMethod("POST");
				conn.setRequestProperty("Content-Length", Integer.toString(data.length()));
				conn.getOutputStream().write(data.getBytes("UTF-8"));
				final BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
				final StringBuffer stringBuffer = new StringBuffer();
				String line;
				while ((line = rd.readLine()) != null) {
					stringBuffer.append(line);
				}
				rd.close();
				
				return stringBuffer.toString();
			} catch (Exception e) {
				System.out.println("Error SMS "+e);
				return "Error "+e;
			}
		
	}
}
