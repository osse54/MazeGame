package com.company;

import java.util.Arrays;
import java.util.Collections;

public class MazeGenerator {
    // 미로의 크기
    private final int size;
    // 2중 배열을 한 면으로 본다면 값이 들어간 곳을 방이라고 생각한다.
    private Room[][] rooms;
    private int[][] maze;

    public MazeGenerator(int size) {
        this.size = size;
        rooms = new Room[size][size];
        for(int row = 0; row < size; row++) {
            for(int col = 0; col < size; col++) {
                rooms[row][col] = new Room(row, col);
            }
        }
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
        rooms[0][0].goNext(null);
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

        private Room(int row, int col) {
            this.row = row;
            this.col = col;
            this.prev = null;
        }

        public void goNext(Room prev) {
            visited = true;
            this.prev = prev;
            if(prev != null) {
                breakWall(this, prev);
            }
            int[][] direction = {
                {this.row + 1, this.col},   // 하
                {this.row - 1, this.col},   // 상
                {this.row, this.col + 1},   // 우
                {this.row, this.col - 1}    // 좌
            };
            Collections.shuffle(Arrays.asList(direction));
            for(int[] temp : direction) {
                if(temp[0] >= 0 && temp[1] >= 0 && temp[0] < size && temp[1] < size) {  // 좌표가 0미만 또는 size와 같은 경우는 제외한다.
                    if(!(rooms[temp[0]][temp[1]].isVisited())) {    // visited가 false라면, 방문한 적이 없다면
                        rooms[temp[0]][temp[1]].goNext(this);
                    }
                }
            }
        }

        public boolean isVisited() {
            return visited;
        }

        private void breakWall(Room cur, Room prev) {
            maze[((cur.row * 2 + 1) + (prev.row * 2 + 1)) / 2][((cur.col * 2 + 1) + (prev.col * 2 + 1)) / 2] = 1;
        }
    }
}
