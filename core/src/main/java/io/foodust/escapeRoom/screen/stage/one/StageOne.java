package io.foodust.escapeRoom.screen.stage.one;

import io.foodust.escapeRoom.game.EscapeRoom;
import lombok.Getter;

@Getter
public class StageOne {

    private final StageOneNorth north;
    private final StageOneSouth south;
    private final StageOneWest west;
    private final StageOneEast east;

    public StageOne(EscapeRoom escapeRoom) {
        this.north = new StageOneNorth(escapeRoom);
        this.south = new StageOneSouth(escapeRoom);
        this.west = new StageOneWest(escapeRoom);
        this.east = new StageOneEast(escapeRoom);
    }
}
