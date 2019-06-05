package cn.stonewise.zerorpc.server;

import java.util.zip.CRC32;

import org.apache.commons.lang3.RandomUtils;

import cn.stonewise.util.MD5;
import cn.stonewise.zerorpc.ZRpcServer;

public class ZeroServer {

    public String randomStr(String str) {
        return RandomUtils.nextInt(1, 10) + "," + str;
    }

    public String doubleStr(String str) {
        return str + "," + str;
    }

    public String tripleStr(String str) {
        return str + "," + str + "," + str;
    }

    public String md5Str(String str) {
        return MD5.getMD5Code(str);
    }

    public String crc32(String str) {
        CRC32 crc = new CRC32();
        crc.update(str.getBytes());
        return String.valueOf(crc.getValue());
    }

    
    // start zeroServer
    public static void main(String[] args) {
        ZRpcServer<ZeroServer> server1 = new ZRpcServer<>("tcp://0.0.0.0:8081", ZeroServer.class);
        new Thread(server1).start();
        ZRpcServer<ZeroServer> server2 = new ZRpcServer<>("tcp://0.0.0.0:8082", ZeroServer.class);
        new Thread(server2).start();
        ZRpcServer<ZeroServer> server3 = new ZRpcServer<>("tcp://0.0.0.0:8083", ZeroServer.class);
        new Thread(server3).start();
        ZRpcServer<ZeroServer> server4 = new ZRpcServer<>("tcp://0.0.0.0:8084", ZeroServer.class);
        new Thread(server4).start();
        ZRpcServer<ZeroServer> server5 = new ZRpcServer<>("tcp://0.0.0.0:8085", ZeroServer.class);
        new Thread(server5).start();
    }
}
