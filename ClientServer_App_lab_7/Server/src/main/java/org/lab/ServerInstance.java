package org.lab;

import java.net.InetAddress;

public class ServerInstance {
    private InetAddress address;
    private boolean available;
    private int port;

    public ServerInstance(InetAddress address, int port) {
        this.address = address;
        this.port = port;
        this.available = true;
    }

    public InetAddress getAddress() {
        return address;
    }

    public int getPort() {
        return port;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}