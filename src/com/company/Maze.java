package com.company;

public class Maze {
    private int[][] maze;
    private MazeGenerator.Room[][] rooms;
    private MazeGenerator.Room[] answer;

    public Maze(int[][] maze, MazeGenerator.Room[][] rooms) {
        this.maze = maze;
        this.rooms = rooms;
    }

    public Maze(int[][] maze, MazeGenerator.Room[][] rooms, MazeGenerator.Room[] answer) {
        this.maze = maze;
        this.rooms = rooms;
        this.answer = answer;
    }

    public String[][] getMaze() {
        String[][] strs = new String[maze.length][maze.length];
        for(int i = 0; i < maze.length; i++) {
            for(int j = 0; j < maze.length; j++) {
                strs[i][j] = maze[i][j] == 1 ? " " : String.valueOf(maze[i][j]);
            }
        }
        return strs;
    }

    public String[][] getAnswer() {
        String[][] strs = new String[maze.length][maze.length];
        if(answer == null) {
            // 정답 찾기
        }
        for(int i = 0; i < maze.length; i++) {
            for(int j = 0; j < maze.length; j++) {
                strs[i][j] = maze[i][j] == 1 ? " " : String.valueOf(maze[i][j]);
            }
        }
        return strs;
    }
}
