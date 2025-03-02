package ua.hudyma.maze;

import java.util.ArrayList;

public class RescueOfSirRagnar02 extends Maze {
    public RescueOfSirRagnar02() {
        super.name = "RagnarRescue";
        super.legend = "Rescue Sir Ragnar";
        super.reward = 200;
        super.roomList = new ArrayList<>();
        super.needToFindPerson = true;
    }
}
