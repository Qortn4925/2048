package com.example.game2048;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Game2048Test {
    @Test
    void startsWithDemoTilesAndZeroScore() {
        Game2048 game = new Game2048();
        assertEquals(2, game.boardSnapshot()[0][1]);
        assertEquals(4, game.boardSnapshot()[2][2]);
        assertEquals(0, game.score());
    }

    @Test
    void snapshotDoesNotAllowExternalMutation() {
        Game2048 game = new Game2048();
        int[][] board = game.boardSnapshot();
        board[0][1] = 999;
        assertEquals(2, game.boardSnapshot()[0][1]);
    }

    @Disabled("이동 로직 구현 후, 병합 테스트를 직접 작성하고 활성화하세요.")
    @Test
    void mergeEachTileAtMostOnce() {
        // TODO: [2,2,2,2]를 왼쪽으로 이동하면 [4,4,0,0]인지 검증
        // 이 테스트를 쉽게 작성하려면 임의 보드를 주입할 수 있는 방법도 설계해 보세요.
        fail("테스트 구현 필요");
    }
}
