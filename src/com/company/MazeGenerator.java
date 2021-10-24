package com.company;

import java.util.*;

public class MazeGenerator {
    private static Random ran;
    // 미로의 크기
    private final int size;
    // 2중 배열을 한 면으로 본다면 값이 들어간 곳을 방이라고 생각한다.
    private Room[][] rooms;
    private int[][] maze;
    private static boolean reached = false;


    public MazeGenerator(int size) {
        this.size = size;
        rooms = new Room[size][size];
        for(int row = 0; row < size; row++) {
            for(int col = 0; col < size; col++) {
                rooms[row][col] = new Room(row, col);
            }
        }
        maze = new int[(size * 2) + 1][(size * 2) + 1]; // 미로의 벽까지 생각해서 방을 생성함 => 미로의 벽도 방으로 만든다.
        generateDFS();
    }

    private void generateDFS() {
        // 방 생성 // 첫 시작 지점은 1, 1 // 2중 배열이기 때문에 2중 반복문 사용
        for(int row = 1; row < maze.length; row += 2) {
            for(int col = 1; col < maze.length; col += 2) {
                // 1로 초기화된 방은 " "로 출력
                maze[row][col] = 1;
            }
        }
        rooms[getRandom(size)][getRandom(size)].goNextDFS(null);
    }

    private int getRandom(int size) {
        if(ran == null) {
            ran = new Random();
        }
        ran.setSeed(ran.nextInt(Math.abs((int)System.currentTimeMillis())));
        return ran.nextInt(size - 2) + 1;   // 테두리 부분에서 시작하지 않게 하기 위해
    }

//    // Randomized Kruskal Algorithm - MST
//    private void generateRK() {
//        // 제거할 벽의 갯수
//        // 총 maze가 가지는 값의 개수(=maze.길이 * maze.길이) - 총 Room의 갯수(=size * size) - 겉을 싸는 벽(끝 부분을 제거한 한 면의 길이(size * 2) * 4(사각형))
//        int[] wallArr = new int[((size * 2) + 1)*((size * 2) + 1) - (size * size) - (size * 2 * 4)];
//
//        for(int i = 0; i < maze.length; i++) {
//            for(int j = 0; j < maze.length; j++) {
//                if((i != 0 && j != 0) && (i != maze.length && j != maze.length) && ((i + j) % 2 == 1)) {  // row 또는 col이 0, maze.length가 아닌 위치, Room이 아닌 위치
//
//                }
//            }
//        }
//    }

    public void display() {
        for(int[] tempArr : maze) {
            for(int temp : tempArr) {
                // 1로 초기화된 방은 " "로 출력
                System.out.print(temp >= 1 ? "□" : "■");
            }
            System.out.println();
        }
    }

//    public void displayAnswer() {
//        for(int[] tempArr : maze) {
//            for(int temp : tempArr) {
//                // 1로 초기화된 방은 " "로 출력
//                System.out.print(temp >= 1 ? temp == 2 ? "○" : "□" : "■");
//            }
//            System.out.println();
//        }
//    }

    private void findAnswer() {
        Room start = rooms[0][0];

    }

    public class Room {
        private int row, col;
        private boolean visited = false;    // 초기 값은 당연히 false이다.

        private Room(int row, int col) {
            this.row = row;
            this.col = col;
        }

        private void goNextDFS(Room prev) {
//            display();
//            try {
//                Thread.sleep(200);
//            } catch(InterruptedException e) {
//                e.printStackTrace();
//            }
            visited = true; //  현재 메소드를 호출하는 인스턴스에 도착했음으로 방문되었음을 나타냄

//            if(prev != null && !(row == size -1 && col == size - 1)) {
//                breakWall(this, prev, 1);  // 이전 Room과의 벽을 허무는 작업
//            }
            if(prev != null) {
                breakWall(this, prev, 1);  // 이전 Room과의 벽을 허무는 작업
            }

            int[][] direction = {   // 갈 수 있는 방향을 선택지로 만듬
                    {this.row + 1, this.col},   // 하
                    {this.row - 1, this.col},   // 상
                    {this.row, this.col + 1},   // 우
                    {this.row, this.col - 1}    // 좌
            };

            Collections.shuffle(Arrays.asList(direction));  // 선택지를 무작위로 만들기 위해

            for(int[] temp : direction) {   // 선택지를 모두 소모하기 위해
                // 아래 if문에서 4방향 모두 길이 없다면 재귀호출이 취소된다.
                // visited가 false라면, 방문한 적이 없다면
                // 좌표가 0미만 또는 size와 같은 경우는 제외한다.
                if(temp[0] >= 0 && temp[1] >= 0 && temp[0] < size && temp[1] < size && !(rooms[temp[0]][temp[1]].isVisited())) {
                    rooms[temp[0]][temp[1]].goNextDFS(this);   // 재귀호출 - 끝나면 다음 선택지를 가지고 와서 실행
//                    if(reached) {
//                        rooms[temp[0]][temp[1]].goNextDFS(this);   // 재귀호출 - 끝나면 다음 선택지를 가지고 와서 실행
//                    } else {
//                        if(rooms[temp[0]][temp[1]] == rooms[size - 1][size - 1]) {
//                            reached = true;
//                            breakWall(this, rooms[temp[0]][temp[1]], 2);
//                            maze[temp[0] * 2 + 1][temp[1] * 2 + 1] = 2;
//                        }
//                        rooms[temp[0]][temp[1]].goNextDFS(this);   // 재귀호출 - 끝나면 다음 선택지를 가지고 와서 실행
//                        if(reached) {
//                            if(prev != null) {
//                                breakWall(this, prev, 2);
//                            }
//                            maze[(row * 2) + 1][(col * 2) + 1] = 2;
//                        }
//                    }
                }
            }
        }

        private boolean isVisited() {
            return visited;
        }

        private void breakWall(Room cur, Room prev, int state) {
            maze[((cur.row * 2 + 1) + (prev.row * 2 + 1)) / 2][((cur.col * 2 + 1) + (prev.col * 2 + 1)) / 2] = state;
        }
    }
}
