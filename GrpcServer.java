package zb.hermes.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.grpc.BindableService;
import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;

public class GrpcServer {
    private final Server _server;
    private final int _port;
    private final Logger _log;

    public GrpcServer(int port, BindableService service) {
        _log = LoggerFactory.getLogger(GrpcServer.class);
        _port = port;
        _server = ServerBuilder
                .forPort(_port)
                .addService(service)
                .build();

        start();
        _log.info("Started on thread: " + Thread.currentThread());
    }

    public void start() {
        try {
            _server.start();

            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                _log.info("Received gRPC server shutdown request on " + _port + "...");
                _server.shutdown();
                _log.info("Successfully stopped gRPC server on " + _port + " !");
            }));

            _server.awaitTermination();
        } catch (IOException | InterruptedException e) {
            _log.error("gRPC server exception on port " + _port + ": " + e);
        }
    }
}
