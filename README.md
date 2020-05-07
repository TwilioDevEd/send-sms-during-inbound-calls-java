<a href="https://www.twilio.com">
  <img src="https://static0.twilio.com/marketing/bundles/marketing/img/logos/wordmark-red.svg" alt="Twilio" width="250" />
</a>

# Send an SMS during a phone call. Powered by Twilio and Java/Spark

![](https://github.com/TwilioDevEd/send-sms-during-inbound-calls-java/workflows/Java/badge.svg)

> We are currently in the process of updating this sample template. If you are encountering any issues with the sample, please open an issue at [github.com/twilio-labs/code-exchange/issues](https://github.com/twilio-labs/code-exchange/issues) and we'll try to help you.

Learn how to send an SMS to someone who's called your Twilio phone number while they're on the call.

This small sample application will say a short message to an inbound caller and, at the same time, send them an SMS.

[Read the full tutorial here!](https://www.twilio.com/docs/sms/tutorials/send-sms-during-phone-call-java)


## Local Development

This project is built using the [Spark](http://sparkjava.com/) web framework, Java 1.8, and the [Twilio Java Helper Library](https://www.twilio.com/docs/libraries/java).

1. First clone this repository and `cd` into it.

   ```bash
   git clone https://github.com/TwilioDevEd/send-sms-during-inbound-calls-java.git
   cd send-sms-during-inbound-calls-java
   ```

1. Create an environment file (`.env`) and define your Twilio Account SID and Auth Token. Both of these can be found in your [Twilio console](https://www.twilio.com/console).

   ```bash
   cp .env.example .env
   ```

1. Load the created file into your environment.

    ```bash
    source .env
    ```

1. Start the server.

    ```bash
    mvn compile exec:java -Dexec.mainClass=com.twilio.app.App
    ```

1. Expose the application to the wider Internet using [ngrok](https://ngrok.com/).

    ```bash
    ngrok http 4567 -host-header="localhost:4567"
    ```

1. Configure Twilio to call your webhooks

  You will need to configure Twilio to call your application when calls are
  received in your [*Twilio Number*](https://www.twilio.com/user/account/messaging/phone-numbers).
  The voice url should look something like this:

  ```
  https://<your-ngrok-subdomain>.ngrok.io/answer
  ```


## Meta

* No warranty expressed or implied. Software is as is. Diggity.
* The CodeExchange repository can be found [here](https://github.com/twilio-labs/code-exchange/).
* [MIT License](http://www.opensource.org/licenses/mit-license.html)
* Lovingly crafted by Twilio Developer Education.
