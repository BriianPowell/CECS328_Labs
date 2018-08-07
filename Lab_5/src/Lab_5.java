import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.Scanner;
import java.util.Stack;

/*
 * Brian Powell 012362894
 * CECS 328 Pouye Sedighan
 * Lab_5
 * 
 * The Lab utilizes both Depth First Search and Breadth First Search to 
 *  traverse a given matrix. It will create an adjacency matrix and then traverse
 *  them in both ways.
 */

public class Lab_5
{
	public static Scanner s;

	private Queue<Node> queue;
	static ArrayList<Node> nodes = new ArrayList<Node>();

	static class Node 
	{
		int data;
		boolean visited;

		Node(int data) 
		{
			this.data = data;
		}
	}

	public Lab_5()
	{
		queue = new LinkedList<Node>();
	}

	// find neighbors of node using adjacency matrix
	// if adjacency_matrix[i][j]==1, then nodes at index i and index j are
	// connected
	public ArrayList<Node> findNeighbours(int adjacency_matrix[][], Node x) {
		int nodeIndex = -1;

		ArrayList<Node> neighbours = new ArrayList<Node>();
		for (int i = 0; i < nodes.size(); i++) {
			if (nodes.get(i).equals(x)) {
				nodeIndex = i;
				break;
			}
		}

		if (nodeIndex != -1) {
			for (int j = 0; j < adjacency_matrix[nodeIndex].length; j++) {
				if (adjacency_matrix[nodeIndex][j] == 1) {
					neighbours.add(nodes.get(j));
				}
			}
		}
		return neighbours;
	}

	public void bfs(int adjacency_matrix[][], Node node) {
		queue.add(node);
		node.visited = true;
		while (!queue.isEmpty()) {

			Node element = queue.remove();
			System.out.print(element.data + "\t");
			ArrayList<Node> neighbours = findNeighbours(adjacency_matrix,
					element);
			for (int i = 0; i < neighbours.size(); i++) {
				Node n = neighbours.get(i);
				if (n != null && !n.visited) {
					queue.add(n);
					n.visited = true;

				}
			}

		}
	}

	public void dfs(int adjacency_matrix[][], Node node) {
		Stack<Node> stack = new Stack<Node>();
		stack.add(node);
		node.visited = true;
		while (!stack.isEmpty()) {
			Node element = stack.pop();
			System.out.print(element.data + "\t");

			ArrayList<Node> neighbours = findNeighbours(adjacency_matrix,
					element);
			for (int i = 0; i < neighbours.size(); i++) {
				Node n = neighbours.get(i);
				if (n != null && !n.visited) {
					stack.add(n);
					n.visited = true;

				}
			}
		}
	}

	public static void main(String arg[]) {
		
		int order = 0, size = 0;
		
		s = new Scanner(System.in);
		
		Random rand = new Random();
		
		int max = 1, min = 0; 
		
		System.out.println("Please determine the order of the graph: ");
		try
		{
			order = Integer.parseInt(s.next());
		}
		catch (Exception e)
		{
			System.out.println("Invalid input.");
		}
		
		System.out.println("Please determine the size of the graph: ");
		try
		{
			size = Integer.parseInt(s.next());
		}
		catch (Exception e)
		{
			System.out.println("Invalid input.");
		}
		
		//instantiate adjacency matrix
		int[][] adj = new int[order][size];
		
		for (int i = 0; i < order; i++) 
		{
			for(int j = 0; j < size; j++)
			{
				adj[i][j] = (rand.nextInt(max + 1 - min) + min);
			}
		}
		
		for (int i = 0; i < order; i++) 
		{
			for(int j = 0; j < size; j++)
			{
				System.out.print(adj[i][j] + " ");
			}
			System.out.println();
		}
		
		
		Node node40 = new Node(40);
		Node node10 = new Node(10);
		Node node20 = new Node(20);
		Node node30 = new Node(30);
		Node node60 = new Node(60);
		Node node50 = new Node(50);
		Node node70 = new Node(70);

		nodes.add(node40);
		nodes.add(node10);
		nodes.add(node20);
		nodes.add(node30);
		nodes.add(node60);
		nodes.add(node50);
		nodes.add(node70);
		int adjacency_matrix[][] = { { 0, 1, 1, 0, 0, 0, 0 }, // Node 1: 40
				{ 0, 0, 0, 1, 0, 0, 0 }, // Node 2 :10
				{ 0, 1, 0, 1, 1, 1, 0 }, // Node 3: 20
				{ 0, 0, 0, 0, 1, 0, 0 }, // Node 4: 30
				{ 0, 0, 0, 0, 0, 0, 1 }, // Node 5: 60
				{ 0, 0, 0, 0, 0, 0, 1 }, // Node 6: 50
				{ 0, 0, 0, 0, 0, 0, 0 }, // Node 7: 70
		};
		System.out.println("The BFS traversal of the graph is ");
		Lab_5 bfsExample = new Lab_5();
		bfsExample.bfs(adjacency_matrix, node40);

	}
}
