package info.luckydog.design_pattern.state.FSM;
/*
参考：https://zhuanlan.zhihu.com/p/97442825

有限状态机
四种实现方式：
1、switch
2、状态集合
3、State模式
4、枚举

场景
地铁进站闸口的状态有两个：已经关闭、已经开启两个状态。刷卡后闸口从已关闭变为已开启，人通过后闸口状态从已开启变为已关闭。
| Index | State             | Event | NextState       | Action          |
| 1     | 闸机口 LOCKED     | 投币  | 闸机口 UN_LOCKED | 闸机口打开闸门   |
| 2     | 闸机口 LOCKED     | 通过  | 闸机口 LOCKED    | 闸机口警告      |
| 3     | 闸机口 UN_LOCKED  | 投币 | 闸机口 UN _LOCKED | 闸机口退币      |
| 4     | 闸机口 UN_LOCKED  | 通过 | 闸机口 LOCKED     | 闸机口关闭闸门  |

5个Test Case

T01
Given：一个Locked的进站闸口
When: 投入硬币
Then：打开闸口

T02
Given：一个Locked的进站闸口
When: 通过闸口
Then：警告提示

T03
Given：一个Unocked的进站闸口
When: 通过闸口
Then：闸口关闭

T04
Given：一个Unlocked的进站闸口
When: 投入硬币
Then：退还硬币

T05
Given：一个闸机口
When: 非法操作
Then：操作失败

两点建议：

1、遵循Simple Design。
如果没有一个外部参考，那么用哪一种都不为过。所以引入一个原则作为参考，可以更好的帮助我们做决定。
这里日常工作中我们经常使用Simple Design：通过测试、揭示意图、消除重复、最少元素。并在实现过程中不断重构，代码是重构出来的，而不是一次性的设计出来的。

2、在状态机的实现上多做尝试。
例子只是一个简单的场景，所以只能看到简单场景下的实现效果，实际业务线上的状态会非常丰富，而且每种状态中可真行的动作也是不同的。
所以针对特定场景遇到的问题，多尝试练习思考，练习思考后的经验才是最重要的。
 */