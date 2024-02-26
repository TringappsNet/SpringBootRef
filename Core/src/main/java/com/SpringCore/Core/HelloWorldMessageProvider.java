package com.SpringCore.Core;

public class HelloWorldMessageProvider implements MessageProvider {

    @Override
    public String getMessage() {
        return "Hello, Core!";
    }
}
