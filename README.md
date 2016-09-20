# SMS reader App

Suppose in your phone inbox there are a lots of SMS. You need to keep backup all SMS of any single sender. You can use this App for this reason.

Input the phone number of your sender. And input your server side API link.
Hit first SUBMIT button. After saving your phone number and API URL you have to hit "SEND SMS DATA" button.

If there are any SMS in your inbox from that sender and the API is correct, all SMS of that number will be sent to your server.

This App sends data using POST method and the key is "message". Sample PHP code: http://ideone.com/cTtFwm
This code returns always a JSON object. That object contains two keys. They are "success" and "message" key. "success" is a boolean type value and "message" is a string.
This type of respose should send to App.

{
"success" : false,
"message" : "Data NOT received by server"
}
