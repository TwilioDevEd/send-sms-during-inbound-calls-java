package com.twilio.app;

import static spark.Spark.*;

import java.util.Map;
import java.util.HashMap;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.twiml.voice.Say;
import com.twilio.twiml.VoiceResponse;
import com.twilio.exception.ApiException;
import com.twilio.type.PhoneNumber;


public class App {
    public static void main(String[] args) {
        post("/answer", (req, res) -> {
            Map<String, String> parameters = parseBody(req.body());

            String caller = parameters.get("From");
            String twilioNumber = parameters.get("To");
            sendSms(caller, twilioNumber);

            VoiceResponse twiml = new VoiceResponse.Builder()
                .say(new Say.Builder("Thanks for calling! We just sent you a text with a clue.")
                      .voice(Say.Voice.ALICE)
                      .build())
                .build();

            return twiml.toXml();
        });
    }

    public static void sendSms(String toNumber, String fromNumber) {
        String accountSid = System.getenv("ACCOUNT_SID");
        String authToken = System.getenv("AUTH_TOKEN");

        try {
            Twilio.init(accountSid, authToken);
            Message message = Message
                .creator(new PhoneNumber(toNumber),
                         new PhoneNumber(fromNumber),
                        "Uh oh, looks like this caller can't receive SMS messages.")
                .create();
        } catch (ApiException e) {
            if (e.getCode() == 21614) {
                System.out.println("Uh oh, looks like this caller can't receive SMS messages.");
            }
        }
    }

    // Body parser help
    public static Map<String, String> parseBody(String body) throws UnsupportedEncodingException {
      String[] unparsedParams = body.split("&");
      Map<String, String> parsedParams = new HashMap<String, String>();
      for (int i = 0; i < unparsedParams.length; i++) {
        String[] param = unparsedParams[i].split("=");
        if (param.length == 2) {
          parsedParams.put(urlDecode(param[0]), urlDecode(param[1]));
        } else if (param.length == 1) {
          parsedParams.put(urlDecode(param[0]), "");
        }
      }
      return parsedParams;
    }

    public static String urlDecode(String s) throws UnsupportedEncodingException {
      return URLDecoder.decode(s, "utf-8");
    }
}
