package info.luckydog.design_pattern.state.FSM.StateCollection.transaction;

import info.luckydog.design_pattern.state.FSM.StateCollection.state.EntranceMachineState;
import info.luckydog.design_pattern.state.FSM.StateCollection.action.Action;
import info.luckydog.design_pattern.state.FSM.StateCollection.event.Event;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EntranceMachineTransaction {

    private EntranceMachineState currentState;// 当前状态

    private Action action;// 执行动作

    private EntranceMachineState nextState;// 执行动作后的下一个状态

    private Event event;// 状态改变后触发的事件
}
