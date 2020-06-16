package info.luckydog.design_pattern.state.FSM.StatePattern;

public class LockedEntranceMachineState implements EntranceMachineState {
    @Override
    public String insertCoin(EntranceMachine entranceMachine) {
        return entranceMachine.open();
    }

    @Override
    public String pass(EntranceMachine entranceMachine) {
        return entranceMachine.alarm();
    }
}
