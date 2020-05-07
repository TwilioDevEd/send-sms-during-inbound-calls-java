package com.twilio.app;

import static spark.Spark.*;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.twiml.voice.Say;
import com.twilio.twiml.VoiceResponse;
import com.twilio.exception.ApiException;
import com.twilio.type.PhoneNumber;


public class App {
    public static void main(String[] args) {
        post("/answer", (req, res) -> {
            // get the urlencoded form parameters
            String caller = req.queryParams("From");
            String twilioNumber = req.queryParams("To");

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
            Message
                .creator(new PhoneNumber(toNumber),
                         new PhoneNumber(fromNumber),
                        "There's always money in the banana stand.")
                .create();
        } catch (ApiException e) {
            if (e.getCode() == 21614) {
                System.out.println("Uh oh, looks like this caller can't receive SMS messages.");
            }
        }
    }
}
