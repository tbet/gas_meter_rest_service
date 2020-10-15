package enery;

public class UtilBean {

    private static UtilBean INSTANCE;
    private int trigger_state = 0;

    private UtilBean() {
    }

    public static UtilBean getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new UtilBean();
        }
        return INSTANCE;
    }

    public int getTrigger_state() {
        return trigger_state;
    }

    public void setTrigger_state(int trigger_state) {
        this.trigger_state = trigger_state;
    }
}
