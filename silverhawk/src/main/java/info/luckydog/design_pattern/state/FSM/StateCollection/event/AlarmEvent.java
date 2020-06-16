package info.luckydog.design_pattern.state.FSM.StateCollection.event;

public class AlarmEvent implements Event {
    @Override
    public String execute() {
        return "alarm";
    }
}

