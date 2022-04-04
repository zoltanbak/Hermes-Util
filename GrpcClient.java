package zb.hermes.util;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GrpcClient {
    protected ManagedChannel _channel;
    protected String _ip;
    protected int _port;
    protected final Logger _log;

    public GrpcClient() {
        _log = LoggerFactory.getLogger(GrpcClient.class);
        _log.info("Created on thread: " + Thread.currentThread());
    }

    protected void start(final String ip, final int port) {
        _ip = ip;
        _port = port;
        _channel = ManagedChannelBuilder
                .forAddress(_ip, _port)
                .usePlaintext()
                .build();
        _log.info("Channel started on thread: " + Thread.currentThread()
                + ", address: " + _ip + ":" + _port);
    }

    protected void shutdown() {
        _channel.shutdown();
        _log.info("Shutting down on: " + _ip + ":" + _port);
    }
}
