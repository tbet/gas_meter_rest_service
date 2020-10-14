package enery;

public class CompassToCounter {

    private static double counter;
    private static final int trigger_level = 9600; //each channel saturates at -32768 and 32767
    private static final int trigger_hyst = 110;
    private static int trigger_state = 0;
    private static final double trigger_step = 0.01;

    public static void calculate(CompassBean compassBean) {
        int old_state = trigger_state;
        double b = Math.sqrt((compassBean.getX() * compassBean.getX()) + (compassBean.getY() * compassBean.getY()) + (compassBean.getZ() * compassBean.getZ()));
        if (b > (trigger_level + trigger_hyst)) {
            trigger_state = 1;
        } else if (b < (trigger_level - trigger_hyst)) {
            trigger_state = 0;
        }
        if (old_state == 0 && trigger_state == 1) {
            counter = JsonValues.getInstance().getConsumption() + trigger_step;
            GasMeterLog.write(compassBean.getLocalDateTime(), counter);
            JsonValues.getInstance().setConsumption(counter);;
            System.out.println("gas meter counter is = " + counter + " / b = "+b+" " + compassBean);
        }
    }

    public static double getCurrentCounter() {
        double counter = JsonValues.getInstance().getConsumption();
        return  counter;
    }
}
