package com_springcore_core;

public class MessageRenderer {
    

    private MessageProvider provider;

    public void setMessageProvider(MessageProvider provider) {
        this.provider = provider;
    }

    public void render() {
        System.out.println(provider.getMessage());
    }
}
