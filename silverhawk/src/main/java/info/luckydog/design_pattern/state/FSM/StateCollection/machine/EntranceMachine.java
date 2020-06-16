package info.luckydog.design_pattern.state.FSM.StateCollection.machine;

import info.luckydog.design_pattern.state.FSM.StateCollection.state.EntranceMachineState;
import info.luckydog.design_pattern.state.FSM.StateCollection.transaction.EntranceMachineTransaction;
import info.luckydog.design_pattern.state.FSM.StateCollection.exception.InvalidActionException;
import info.luckydog.design_pattern.state.FSM.StateCollection.action.Action;
import info.luckydog.design_pattern.state.FSM.StateCollection.event.AlarmEvent;
import info.luckydog.design_pattern.state.FSM.StateCollection.event.CloseEvent;
import info.luckydog.design_pattern.state.FSM.StateCollection.event.OpenEvent;
import info.luckydog.design_pattern.state.FSM.StateCollection.event.RefundEvent;
import lombok.Data;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Data
public class EntranceMachine {

    List<EntranceMachineTransaction> entranceMachineTransactionList = Arrays.asList(
            EntranceMachineTransaction.builder()
                    .currentState(EntranceMachineState.LOCKED)
                    .action(Action.INSERT_COIN)
                    .nextState(EntranceMachineState.UNLOCKED)
                    .event(new OpenEvent())
                    .build(),
            EntranceMachineTransaction.builder()
                    .currentState(EntranceMachineState.LOCKED)
                    .action(Action.PASS)
                    .nextState(EntranceMachineState.LOCKED)
                    .event(new AlarmEvent())
                    .build(),
            EntranceMachineTransaction.builder()
                    .currentState(EntranceMachineState.UNLOCKED)
                    .action(Action.PASS)
                    .nextState(EntranceMachineState.LOCKED)
                    .event(new CloseEvent())
                    .build(),
            EntranceMachineTransaction.builder()
                    .currentState(EntranceMachineState.UNLOCKED)
                    .action(Action.INSERT_COIN)
                    .nextState(EntranceMachineState.UNLOCKED)
                    .event(new RefundEvent())
                    .build()
    );

    private EntranceMachineState state;

    public EntranceMachine(EntranceMachineState state) {
        this.setState(state);
    }

    public String execute(Action action) {
        Optional<EntranceMachineTransaction> transactionOptional = entranceMachineTransactionList
                .stream()
                .filter(transaction -> transaction.getAction().equals(action) && transaction.getCurrentState().equals(state))
                .findFirst();

        if (!transactionOptional.isPresent()) {
            throw new InvalidActionException();
        }

        EntranceMachineTransaction transaction = transactionOptional.get();
        this.setState(transaction.getNextState());
        return transaction.getEvent().execute();
    }

    private void setState(EntranceMachineState state) {
        this.state = state;
    }
}