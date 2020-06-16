package info.luckydog.design_pattern.state.FSM.StateEnum;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.BDDAssertions.then;


class EntranceMachineTest {

    @Test
    void should_unlocked_when_insert_coin_given_a_entrance_machine_with_locked_state() {
        EntranceMachine entranceMachine = new EntranceMachine(EntranceMachineState.LOCKED);

        String result = entranceMachine.execute(Action.INSERT_COIN);

        then(result).isEqualTo("opened");
        then(entranceMachine.getState()).isEqualTo(EntranceMachineState.UNLOCKED);
    }

    @Test
    void should_alarm_when_pass_given_a_entrance_machine_with_locked_state() {
        EntranceMachine entranceMachine = new EntranceMachine(EntranceMachineState.LOCKED);

        String result = entranceMachine.execute(Action.PASS);

        then(result).isEqualTo("alarm");
        then(entranceMachine.getState()).isEqualTo(EntranceMachineState.LOCKED);
    }

    @Test
    void should_fail_when_execute_invalid_action_given_a_entrance_machine() {
        EntranceMachine entranceMachine = new EntranceMachine(EntranceMachineState.LOCKED);

        assertThatThrownBy(() -> entranceMachine.execute(null))
                .isInstanceOf(InvalidActionException.class);
    }

    @Test
    void should_refund_when_insert_coin_given_a_entrance_machine_with_unlocked_state() {
        EntranceMachine entranceMachine = new EntranceMachine(EntranceMachineState.UNLOCKED);

        String result = entranceMachine.execute(Action.INSERT_COIN);

        then(result).isEqualTo("refund");
        then(entranceMachine.getState()).isEqualTo(EntranceMachineState.UNLOCKED);
    }

    @Test
    void should_closed_when_pass_given_a_entrance_machine_with_unlocked_state() {
        EntranceMachine entranceMachine = new EntranceMachine(EntranceMachineState.UNLOCKED);

        String result = entranceMachine.execute(Action.PASS);

        then(result).isEqualTo("closed");
        then(entranceMachine.getState()).isEqualTo(EntranceMachineState.LOCKED);

    }
}