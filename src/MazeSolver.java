/**
 * Solves the given maze using DFS or BFS
 * @author Ms. Namasivayam
 * @version 03/10/2023
 */

import java.util.*;
import java.util.Queue;

public class MazeSolver {
    private Maze maze;

    public MazeSolver() {
        this.maze = null;
    }

    public MazeSolver(Maze maze) {
        this.maze = maze;
    }

    public void setMaze(Maze maze) {
        this.maze = maze;
    }

    /**
     * Starting from the end cell, backtracks through
     * the parents to determine the solution
     * @return An arraylist of MazeCells to visit in order
     */
    public ArrayList<MazeCell> getSolution() {
        // TODO: Get the solution from the maze
        ArrayList<MazeCell> backtrack = new ArrayList<MazeCell>();
        MazeCell currentCell = maze.getEndCell();
        while (currentCell != maze.getStartCell())
        {
            backtrack.add(currentCell.getParent());
            currentCell = currentCell.getParent();

        }
        // Reverses the list and returns.
        ArrayList<MazeCell> temp = new ArrayList<MazeCell>();
        for (int i = backtrack.size() - 1; i >= 0; i--)
        {
            temp.add(backtrack.get(i));
        }
        return temp;
    }

    /**
     * Performs a Depth-First Search to solve the Maze
     * @return An ArrayList of MazeCells in order from the start to end cell
     */
    public ArrayList<MazeCell> solveMazeDFS() {
        // TODO: Use DFS to solve the maze
        Stack<MazeCell>  eligible = new Stack<MazeCell>();
        MazeCell currentCell = maze.getStartCell();

        while (currentCell != maze.getEndCell()) {
            currentCell.setExplored(true);
            // Explore the cells in the order: NORTH, EAST, SOUTH, WEST.
            // If the cell is valud, then add it to the stack of elibigle cells.
            // Also sets the parents appropriately.
            if (maze.isValidCell(currentCell.getRow() -1, currentCell.getCol())) {
                MazeCell temp = maze.getCell(currentCell.getRow() - 1, currentCell.getCol());
                eligible.add(temp);
                temp.setParent(currentCell);
            }
            if (maze.isValidCell(currentCell.getRow(), currentCell.getCol() + 1))
            {
                MazeCell temp = maze.getCell(currentCell.getRow(), currentCell.getCol() + 1);
                eligible.add(temp);
                temp.setParent(currentCell);
            }
            if (maze.isValidCell(currentCell.getRow() + 1, currentCell.getCol()))
            {
                MazeCell temp= maze.getCell(currentCell.getRow() + 1, currentCell.getCol());
                eligible.add(temp);
                temp.setParent(currentCell);
            }
            if (maze.isValidCell(currentCell.getRow(), currentCell.getCol() - 1))
            {
                MazeCell temp = maze.getCell(currentCell.getRow(), currentCell.getCol() - 1);
                eligible.add(temp);
                temp.setParent(currentCell);
            }
            // Increment currentCell
            currentCell = eligible.pop();


        }
        return getSolution();
    }

    /**
     * Performs a Breadth-First Search to solve the Maze
     * @return An ArrayList of MazeCells in order from the start to end cell
     */
    public ArrayList<MazeCell> solveMazeBFS() {


        // TODO: Use BFS to solve the maze
        Queue<MazeCell> eligible = new LinkedList<MazeCell>();
        MazeCell currentCell = maze.getStartCell();
        while (currentCell != maze.getEndCell())
        {
            // Similar checks to DFS, but use of a LinkedList instead.
            currentCell.setExplored(true);
            if (maze.isValidCell(currentCell.getRow() -1, currentCell.getCol())) {
                MazeCell temp = maze.getCell(currentCell.getRow() - 1, currentCell.getCol());
                eligible.add(temp);
                temp.setParent(currentCell);
            }
            if (maze.isValidCell(currentCell.getRow(), currentCell.getCol() + 1))
            {
                MazeCell temp = maze.getCell(currentCell.getRow(), currentCell.getCol() + 1);
                eligible.add(temp);
                temp.setParent(currentCell);
            }
            if (maze.isValidCell(currentCell.getRow() + 1, currentCell.getCol()))
            {
                MazeCell temp= maze.getCell(currentCell.getRow() + 1, currentCell.getCol());
                eligible.add(temp);
                temp.setParent(currentCell);
            }
            if (maze.isValidCell(currentCell.getRow(), currentCell.getCol() - 1))
            {
                MazeCell temp = maze.getCell(currentCell.getRow(), currentCell.getCol() - 1);
                eligible.add(temp);
                temp.setParent(currentCell);
            }
            currentCell = eligible.remove();

        }
        return getSolution();
    }

    public static void main(String[] args) {
        // Create the Maze to be solved
        Maze maze = new Maze("Resources/maze3.txt");

        // Create the MazeSolver object and give it the maze
        MazeSolver ms = new MazeSolver();
        ms.setMaze(maze);

        // Solve the maze using DFS and print the solution
        ArrayList<MazeCell> sol = ms.solveMazeDFS();
        maze.printSolution(sol);

        // Reset the maze
        maze.reset();

        // Solve the maze using BFS and print the solution
        sol = ms.solveMazeBFS();
        maze.printSolution(sol);
    }
}
