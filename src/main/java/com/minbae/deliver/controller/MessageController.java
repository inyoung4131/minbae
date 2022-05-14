package com.minbae.deliver.controller;

import org.springframework.stereotype.Controller;

import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;


@Controller
@ServerEndpoint(value="/ws/deliver")
public class MessageController extends Socket {
    private static final List<Session> session = new ArrayList<>();

    @OnOpen
    public void open(Session newDeliver){
        System.out.println("con");
        session.add(newDeliver);
        System.out.println(newDeliver.getId());
    }
}
