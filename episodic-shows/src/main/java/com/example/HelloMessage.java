package com.example;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor(force = true)
@AllArgsConstructor
public class HelloMessage {

    private String hello;

    @Override
    public String toString() {
        return "HelloMessage{" +
                "hello='" + this.getHello() + '\'' +
                '}';
    }
}
