package com.maxem.players;
import com.maxem.game.PlayerType;

public interface Player {
    public class ActionInfo {
        int i;
        int j;
        public enum ActionType { MOVE, CANCEL_MOVE}
        ActionType actionType;
        public ActionInfo(ActionType actionType, int i, int j) {
            this.actionType = actionType;
            this.i = i;
            this.j = j;
        }

        public ActionInfo(ActionType actionType) {
            this.actionType = actionType;
        }

        public int getI() {
            return i;
        }

        public int getJ() {
            return j;
        }

        public ActionType getActionType() {
            return actionType;
        }
    }
    public ActionInfo getNextAction();

    public String getName();

    public PlayerType getPlayerType();

    public void setPlayerType(PlayerType playerType);
}
