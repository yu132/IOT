package cn.edu.nju.software.iot.cloud.netty.server;

import java.util.LinkedList;
import java.util.Queue;

public class MessageBuffer {
    private static Queue<String> queue = new LinkedList<>();

    public static String read() {
        return queue.poll();
    }

    public static void write(String s) {
        queue.add(s);
    }
}
