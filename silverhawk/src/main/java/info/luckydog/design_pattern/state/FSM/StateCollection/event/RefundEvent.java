package info.luckydog.design_pattern.state.FSM.StateCollection.event;

public class RefundEvent implements Event {
    @Override
    public String execute() {
        return "refund";
    }
}