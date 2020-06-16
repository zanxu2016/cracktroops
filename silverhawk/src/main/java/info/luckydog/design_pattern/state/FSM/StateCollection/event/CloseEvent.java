package info.luckydog.design_pattern.state.FSM.StateCollection.event;

public class CloseEvent implements Event {
    @Override
    public String execute() {
        return "closed";
    }
}
