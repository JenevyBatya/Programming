package org.lab_5;

import java.util.ArrayList;
import java.util.List;

import java.util.ArrayList;
import java.util.List;

public class GatewayLBService {
    private List<ServerInstance> serverInstances;
    private int currentIndex;

    public GatewayLBService() {
        serverInstances = new ArrayList<>();
        currentIndex = 0;
    }

    public void addServer(ServerInstance serverInstance) {
        serverInstances.add(serverInstance);
    }

    public void removeServer(ServerInstance serverInstance) {
        serverInstances.remove(serverInstance);
    }

    public ServerInstance getNextServer() {
        if (serverInstances.isEmpty()) {
            throw new IllegalStateException("No servers available");
        }

        ServerInstance nextServer = serverInstances.get(currentIndex);
        currentIndex = (currentIndex + 1) % serverInstances.size();
        return nextServer;
    }
}

