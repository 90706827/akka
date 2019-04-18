package com.jangni.akka.actor;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import com.jangni.akka.module.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @ClassName MsgActor
 * @Description 消息actor
 * 自定义Actor继承AbstractActor类
 * @Author Mr.Jangni
 * @Date 2019/4/17 21:20
 * @Version 1.0
 **/
public class MsgActor extends AbstractActor {
    static final Logger logger = LoggerFactory.getLogger(MsgActor.class);
    /**
     * Actor类型中使用参数
     */
    private final String name;

    MsgActor(String name) {
        this.name = name;
    }

    /**
     * 通过此方法传递Actor中所使用的对象参数
     */
    private static Props props(String name) {
        return Props.create(MsgActor.class, name);
    }

    @Override
    public Receive createReceive() {
        //match具有顺序性
        return receiveBuilder()
                //匹配 User类型
                .match(User.class, u -> {
                    logger.info("{}符合用户类型的数据{}:{}", name, u.getUsername(), u.getPassword());
                })
                //匹配字符串相等
                .matchEquals("张三", name -> {
                    logger.info("{}于用户姓名匹配数据{}", name, name);
                })
                //匹配字符串类型
                .match(String.class, s -> {
                    logger.info("{}符合字符串类型的数据{}", name, s);
                })
                //匹配Int类型
                .match(Integer.class, i -> {
                    logger.info("{}符合Int类型的数据{}", name, i);
                })
                //都不匹配处理
                .matchAny(oth -> {
                    logger.info("{}其他情况{}", name, oth.toString());
                }).build();
    }

    public static void main(String[] args) {
        ActorSystem as = ActorSystem.create("Actor-System");
        ActorRef msgActor = as.actorOf(MsgActor.props("张三"), "Msg-Actor");
        msgActor.tell(new User("zhangsan", "88888888"), ActorRef.noSender());
        msgActor.tell("张三", ActorRef.noSender());
        msgActor.tell(123, ActorRef.noSender());
        msgActor.tell("msg", ActorRef.noSender());
        msgActor.tell(true, ActorRef.noSender());
    }
}
