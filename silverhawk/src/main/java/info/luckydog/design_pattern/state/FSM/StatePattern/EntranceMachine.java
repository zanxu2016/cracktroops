package info.luckydog.design_pattern.state.FSM.StatePattern;

import lombok.Data;

import java.util.Objects;

@Data
public class EntranceMachine {

    private EntranceMachineState locked = new LockedEntranceMachineState();
    private EntranceMachineState unlocked = new UnlockedEntranceMachineState();

    private EntranceMachineState state;

    public EntranceMachine(EntranceMachineState state) {
        this.state = state;
    }

    public String execute(Action action) {
        if (Objects.isNull(action)) {
            throw new InvalidActionException();
        }

        if (Action.PASS.equals(action)) {
            return state.pass(this);
        }
        return state.insertCoin(this);
    }

    public boolean isUnlocked() {
        return state == unlocked;
    }

    public boolean isLocked() {
        return state == locked;
    }

    public String refund() {
        setState(unlocked);
        return "refund";
    }

    public String close() {
        setState(locked);
        return "closed";
    }

    public String alarm() {
        setState(locked);
        return "alarm";
    }

    public String open() {
        setState(unlocked);
        return "opened";
    }

    private void setState(EntranceMachineState state) {
        this.state = state;
    }
}
