package info.luckydog.design_pattern.state.FSM.StatePattern;

public interface EntranceMachineState {

    String insertCoin(EntranceMachine entranceMachine);

    String pass(EntranceMachine entranceMachine);
}
