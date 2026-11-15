package com.example.game2048;

import java.util.*;

/**
 * 게임 규칙과 상태를 담당합니다. Swing에 의존하지 않습니다.
 * 이 파일의 이동/병합/새 타일 생성 로직을 직접 완성하는 것이 이번 과제입니다.
 */
public class Game2048 {
    private static final int SIZE = 4;
    private final int[][] board = new int[SIZE][SIZE];
    private int score;
    private final Random random = new Random();
    public Game2048() {
        reset();
    }

    public void reset() {
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                board[row][col] = 0;
            }
        }
        score = 0;


        // 화면 확인용 고정 타일. 완성 단계에서 무작위 타일 생성으로 교체하세요.
        spawnRandomTile();
        spawnRandomTile();
    }

    public void move(Direction direction) {
        Objects.requireNonNull(direction);
        merge(direction);
        spawnRandomTile();
        System.out.println("Move requested: " + direction);
    }
    //위치
    private int index(int position, Direction direction) {
        boolean reversed =
                direction == Direction.RIGHT || direction == Direction.DOWN;

        return reversed ? SIZE - 1 - position : position;
    }

    // 방향에 맞는 칸 읽기
    private int get(int line, int position, Direction direction) {
        int p = index(position, direction);

        boolean horizontal =
                direction == Direction.LEFT || direction == Direction.RIGHT;

        return horizontal ? board[line][p] : board[p][line];
    }

    // 방향에 맞는 칸에 쓰기
    private void set(int line, int position, Direction direction, int value) {
        int p = index(position, direction);

        boolean horizontal =
                direction == Direction.LEFT || direction == Direction.RIGHT;

        if (horizontal) {
            board[line][p] = value;
        } else {
            board[p][line] = value;
        }
    }

    // 네 방향 공통 이동·병합
    private void merge(Direction direction) {
        for (int line = 0; line < SIZE; line++) {
            int write = 0;
            int lastMergedIndex = -1;

            for (int position = 0; position < SIZE; position++) {
                int num = get(line, position, direction);

                if (num == 0) continue;

                set(line, position, direction, 0);

                if (write > 0
                        && get(line, write - 1, direction) == num
                        && lastMergedIndex != write - 1) {

                    int mergedValue = num * 2;
                    set(line, write - 1, direction, mergedValue);
                    score += mergedValue;
                    lastMergedIndex = write - 1;
                } else {
                    set(line, write, direction, num);
                    write++;
                }
            }
        }
    }

    public int[][] boardSnapshot() {
        int[][] copy = new int[SIZE][SIZE];
        for (int row = 0; row < SIZE; row++) {
            System.arraycopy(board[row], 0, copy[row], 0, SIZE);
        }
        return copy;
    }
    public void spawnRandomTile(){
        //빈자리 찾기.
        List<int[]> emptyCells = findEmptyCells();
        if (emptyCells.isEmpty()) return;
        //빈 자리 중,  n개를 골라. 생성?
        int[] selectedCell = pickRandomCell(emptyCells);
        board[selectedCell[0]][selectedCell[1]] = random.nextInt(10) == 0 ? 4 : 2;
    }

    private int[] pickRandomCell(List<int[]> cells) {
        return cells.get(random.nextInt(cells.size()));
    }

    private List<int[]> findEmptyCells() {
        List<int[]>  emptyCells = new ArrayList<>();
        for(int i=0; i<SIZE;i++){
            for(int j=0; j<SIZE; j++){
                if (board[i][j]==0) emptyCells.add(new int[]{i,j});
            }
        }

        return  emptyCells;
    }

    public int score() {
        return score;
    }
}
