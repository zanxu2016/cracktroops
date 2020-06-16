package info.luckydog.design_pattern.state.FSM.StatePattern;

public class UnlockedEntranceMachineState implements EntranceMachineState {
    @Override
    public String insertCoin(EntranceMachine entranceMachine) {
        return entranceMachine.refund();
    }

    @Override
    public String pass(EntranceMachine entranceMachine) {
        return entranceMachine.close();
    }
}
