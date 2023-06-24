package com.example.chatbot;

public class chatsmodal {

    private String Message;
    private String sender;

    public String getMessage() {
        return Message;
    }

   public void setmessage(String message) {
        this.Message = message;
   }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public chatsmodal(String message, String sender) {
        this.Message = message;
        this.sender = sender;
    }
}

