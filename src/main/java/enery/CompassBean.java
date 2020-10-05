package enery;

import java.io.Serializable;
import java.time.LocalDateTime;

public class CompassBean implements Serializable {
    private static final long serialVersionUID = 1L;

    private int x;
    private int y;
    private int z;
    private LocalDateTime localDateTime;
    private boolean trigger;

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getZ() {
        return z;
    }

    public void setZ(int z) {
        this.z = z;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    public boolean isTrigger() {
        return trigger;
    }

    public void setTrigger(boolean trigger) {
        this.trigger = trigger;
    }

    @Override
    public String toString() {
        return "CompassBean{" +
                "localDateTime=" + localDateTime +
                ", x=" + x +
                ", y=" + y +
                ", z=" + z +
                '}';
    }
}
