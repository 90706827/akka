package com.jangni.akka.actor;

import akka.actor.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @ClassName StarActor
 * @Description
 * @Author Mr.Jangni
 * @Date 2019/4/17 15:17
 * @Version 1.0
 **/
public class StarActor extends AbstractLoggingActor {

    @Override
    public Receive createReceive() {
        return receiveBuilder().matchEquals("123",s->{
            log().info(s);
            getSender().tell("456",getSelf());
        }).build();
    }

    public static void main(String[] args) {
        ActorSystem system = ActorSystem.create("actor-system");
        ActorRef starActor = system.actorOf(Props.create(StarActor.class),"star");
        starActor.tell("123",starActor);
    }
}
