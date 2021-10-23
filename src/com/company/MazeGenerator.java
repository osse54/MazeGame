package com.company;

public class MazeGenerator {
    // 미로의 크기
    private final int size;
    // 2중 배열을 한 면으로 본다면 값이 들어간 곳을 방이라고 생각한다.
    private int[][] maze;

    public MazeGenerator(int size) {
        this.size = size;
        maze = new int[(size * 2) + 1][(size * 2) + 1]; // 미로의 벽까지 생각해서 방을 생성함 => 미로의 벽도 방으로 만든다.
        generate();
    }

    private void generate() {
        // 방 생성 // 첫 시작 지점은 1, 1 // 2중 배열이기 때문에 2중 반복문 사용
        for(int row = 1; row < maze.length; row += 2) {
            for(int col = 1; col < maze.length; col += 2) {
                // 1로 초기화된 방은 " "로 출력
                maze[row][col] = 1;
            }
        }
    }

    public void display() {
        for(int[] tempArr : maze) {
            for(int temp : tempArr) {
                // 1로 초기화된 방은 " "로 출력
                System.out.print(temp == 1 ? " " : temp);
            }
            System.out.println();
        }
    }

    private class Room {
        private int row, col;
        private boolean visited = false;    // 초기 값은 당연히 false이다.
        private Room prev;  // 현재 Room instance를 지정한 Room으로 초기화

        private Room(int row, int col, Room prev) {
            this.row = row;
            this.col = col;
            this.prev = prev;
        }
    }
}
