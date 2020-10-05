package enery;

import java.time.Instant;

public final class JsonValues {

    private static JsonValues INSTANCE;
    private Instant timestamp;
    private double power;
    private String serverID;

    private JsonValues() {
    }
    public static JsonValues getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new JsonValues();
        }
        return INSTANCE;
    }

    public String getServerID() {
        return serverID;
    }

    public void setServerID(String serverID) {
        this.serverID = serverID;
    }

    public double getPower() {
        return power;
    }

    public void setPower(double power) {
        this.power = power;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }

}
