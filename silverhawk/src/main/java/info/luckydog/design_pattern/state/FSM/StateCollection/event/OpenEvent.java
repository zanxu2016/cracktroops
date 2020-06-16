package info.luckydog.design_pattern.state.FSM.StateCollection.event;

public class OpenEvent implements Event {
    @Override
    public String execute() {
        return "opened";
    }
}