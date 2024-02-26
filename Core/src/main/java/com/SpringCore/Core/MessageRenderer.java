package com.SpringCore.Core;

public class MessageRenderer {
    

    private MessageProvider provider;

    public void setMessageProvider(MessageProvider provider) {
        this.provider = provider;
    }

    public void render() {
        System.out.println(provider.getMessage());
    }
}
