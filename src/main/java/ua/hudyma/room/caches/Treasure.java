package ua.hudyma.room.caches;

import java.util.Random;

public class Treasure extends Cache {

    public Treasure() {
        this.cache = generateCacheSum();
    }

    int generateCacheSum (){
        int rand = new Random().nextInt(5) * 10;
        return rand == 0 ? generateCacheSum() : rand;
    }
}
