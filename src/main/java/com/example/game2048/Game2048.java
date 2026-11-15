package com.example.game2048;

import java.util.Objects;

/**
 * 게임 규칙과 상태를 담당합니다. Swing에 의존하지 않습니다.
 * 이 파일의 이동/병합/새 타일 생성 로직을 직접 완성하는 것이 이번 과제입니다.
 */
public class Game2048 {
    private static final int SIZE = 4;
    private final int[][] board = new int[SIZE][SIZE];
    private int score;

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
        board[0][3] = 2;
        board[0][2] = 2;
        board[0][1] = 4;

        board[0][0] = 4;
    }

    public void move(Direction direction) {
        Objects.requireNonNull(direction);
        // TODO 1: 방향에 따라 각 행/열을 왼쪽 기준으로 읽기
        if (direction == Direction.LEFT) {

            for (int row = 0; row < SIZE; row++) {
                int write = 0;
                int lastMergedIndex = -1;

                for (int col = 0; col < SIZE; col++) {

                    if (board[row][col] == 0) continue;
                    int num = board[row][col];
                    board[row][col] = 0;
                    if (write > 0
                            && board[row][write - 1] == num
                            && lastMergedIndex != write - 1) {
                        board[row][write - 1] *= 2;
                        score += board[row][write - 1];

                        lastMergedIndex = write - 1;

                    } else {
                        board[row][write] = num;
                        write++;
                    }
                }
            }
        }
        else if(direction==Direction.RIGHT){
            for(int row =0; row<SIZE; row++){
                int write =SIZE-1;
                int lastMergedIndex = -1;

                for (int col=SIZE-1; col>-1; col--) {
                    if(board[row][col] ==0) continue;
                    int num = board[row][col];
                    board[row][col] = 0;
                    if(write<3 &&
                     board[row][write+1]==num&&
                     lastMergedIndex != write +1)   {
                        board[row][write+1]*=2;
                        score += board[row][write+1];
                        lastMergedIndex=write+1;
                    }else{
                        board[row][write]=num;
                        write--;
                    }
                }
            }
        }
        else if(direction==Direction.UP){
            for (int col = 0; col < SIZE; col++) {
                int write = 0;
                for (int row = 0; row < SIZE; row++) {
                    if (board[row][col] != 0) {
                        int num = board[row][col];
                        board[row][col] = 0;
                        board[write][col] = num;
                        write++;
                    }
                }
            }
        }
        else if(direction==Direction.DOWN){
            for (int col = 0; col < SIZE; col++) {
                int write = SIZE-1;
                for (int row = SIZE-1; row > 0; row--) {
                    if (board[row][col] != 0) {
                        int num = board[row][col];
                        board[row][col] = 0;
                        board[write][col] = num;
                        write--;
                    }
                }
            }
        }
        // TODO 2: 0 제거 -> 같은 숫자 한 번만 병합 -> 빈 칸 채우기
        // TODO 3: 바뀐 경우에만 새 타일 생성
        // TODO 4: 병합하면서 점수 갱신, 승리/패배 검사
        System.out.println("Move requested: " + direction);
    }
//    public int[][] a (Direction direction) {
//
//        switch (direction){
//            case UP -> {
//
//            }
//            case DOWN -> {
//            }
//            case LEFT -> {
//            }
//            case RIGHT -> {
//            }
//        }
//
//    }

    public int[][] boardSnapshot() {
        int[][] copy = new int[SIZE][SIZE];
        for (int row = 0; row < SIZE; row++) {
            System.arraycopy(board[row], 0, copy[row], 0, SIZE);
        }
        return copy;
    }

    public int score() {
        return score;
    }
}
