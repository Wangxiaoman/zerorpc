package cn.stonewise.zerorpc;

import java.util.concurrent.LinkedBlockingQueue;

import org.zeromq.SocketType;
import org.zeromq.ZContext;
import org.zeromq.ZFrame;
import org.zeromq.ZMQ.Socket;
import org.zeromq.ZMsg;


public class ZRpcServer<THandler> implements Runnable {
    private static int THREAD_NUM = 1;
    private int numThreads = THREAD_NUM;
    private ZContext context;
    private Socket socket;
    private Class<THandler> handlerClass;
    private LinkedBlockingQueue<ZMessage> requestqueue;
    private LinkedBlockingQueue<ZMessage> replayqueue;
    private String address;

    public ZRpcServer(String address, Class<THandler> handlerClass, int numThreads) {
        this.handlerClass = handlerClass;
        this.numThreads = numThreads;

        this.context = new ZContext();
        this.socket = this.context.createSocket(SocketType.ROUTER);
        socket.bind(address);

        this.requestqueue = new LinkedBlockingQueue<>();
        this.replayqueue = new LinkedBlockingQueue<>();
        this.address = address;
    }

    public ZRpcServer(String address, Class<THandler> handlerClass) {
        this(address, handlerClass, THREAD_NUM);
    }

    public void run() {
        System.out.println("server start:" + this.address);
        
        for (int i = 0; i < this.numThreads; i++) {
            new Thread(new ZRpcWorker<>(this.requestqueue, this.replayqueue, this.handlerClass))
                    .start();
        }
        new Thread(replayThread).start();

        while (!Thread.currentThread().isInterrupted()) {
            ZMsg msg = ZMsg.recvMsg(this.socket);
            ZFrame iden = msg.pop();
            ZFrame data = msg.pop();
            msg.destroy();

            final ZMessage req = new ZMessage(iden.getData(), data.getData());
            this.requestqueue.add(req);
        }
        this.socket.close();
        this.context.destroy();
    }

    public void stop() {
        this.socket.close();
        this.context.destroy();
    }

    private Runnable replayThread = new Runnable() {
        @Override
        public void run() {
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    ZMessage req = replayqueue.take();
                    // if (req == null) {
                    // try {
                    // Thread.sleep(100);
                    // } catch (InterruptedException e) {
                    // e.printStackTrace();
                    // }
                    // continue;
                    // }
                    ZMsg zmg = new ZMsg();
                    zmg.add(req.getIdentity());
                    zmg.add(new byte[] {});
                    zmg.add(req.getData());
                    zmg.send(socket);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }

            }
        }
    };
}
