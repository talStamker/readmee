package m2;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class GraphAlgorithms extends Graph {
	////////////////// Constructor //////////////////
	GraphAlgorithms() {
		super();
	}

	GraphAlgorithms(Node source, Node dest) {
		super(source, dest);
	}

	GraphAlgorithms(Node source, Node dest, boolean isA) {
		super(source, dest, isA);
	}

	/**
	 * find the short path frome source to dest
	 * 
	 * @param path- the result path will update heer
	 * @param n-    source
	 * @param dest- target
	 * @return the number of Node that the algorithem check in the way to dest.
	 */
	public int BFS(ArrayList<Node> path, Node n, Node dest) {
		int counter = 0;
		Node x = null, neightbor;
		Queue<Node> queue = new LinkedList<Node>();
		ArrayList<Node> neightbors;
		n.setVisited(true);
		n.setFather(null);
		queue.add(n);
		while (!queue.isEmpty()) {
			x = queue.remove();
			neightbors = x.getNeightbors();
			counter++;
			for (int i = 0; i < neightbors.size(); i++) {
				neightbor = neightbors.get(i);
				if (!queue.contains(neightbor) && !neightbor.isVisited()) {
					neightbor.setVisited(true);
					neightbor.setFather(x);
					if (neightbor.equals(dest)) {
						getPath(path, neightbor);
						return counter;
					}
					queue.add(neightbor);
				}
			}
		}

		return counter;
	}

	private int DLS(ArrayList<Node> path, Node source, Node dest, int limit, int counter) {
		if (source.equals(dest)) {
			getPath(path, source);
			// it num it one and we dont want to num it
			return -2;
		}
		if (limit <= 0) {
			return 0;
		}
		int c = 0;
		for (Node neighbor : source.getNeightbors()) {
			if (path.size() != 0) {
				break;
			}
			if (!neighbor.isVisited()) {
				neighbor.setVisited(true);
				neighbor.setFather(source);
				c += 1 + DLS(path, neighbor, dest, limit - 1, counter + 1);
			}
		}
		return c;
	}

	private int IDDFS(ArrayList<Node> path, Node source, Node dest, int limit) {
		int counter = 0, i;
		for (i = 0; i <= limit; i++) {
			source.setVisited(true);
			counter += DLS(path, source, dest, i, 0);
			if (path.size() != 0) {
				break;
			}
			counter++;
			clearVisitedNodes();
		}
		return counter;
	}

	private int GBFS(ArrayList<Node> path, Node n, Node dest) {
		int counter = 0;
		Node x = null, neightbor;
		PriorityQueue<Node> pQueue = getpQueue();
		ArrayList<Node> neightbors;
		n.setVisited(true);
		n.setFather(null);
		pQueue.add(n);
		while (!pQueue.isEmpty()) {
			x = pQueue.remove();
			neightbors = x.getNeightbors();
			counter++;
			for (int i = 0; i < neightbors.size(); i++) {
				neightbor = neightbors.get(i);
				if (!pQueue.contains(neightbor) && !neightbor.isVisited()) {
					neightbor.setVisited(true);
					neightbor.setFather(x);
					if (neightbor.equals(dest)) {
						getPath(path, neightbor);
						return counter;
					}
					pQueue.add(neightbor);
				}
			}
		}
		return counter;
	}

	private int A(ArrayList<Node> path, Node n, Node dest) {
		int counter = 0;
		Node x = null, neightbor;
		PriorityQueue<Node> pQueue = getpQueue();
		ArrayList<Node> neightbors;
		n.setVisited(true);
		n.setFather(null);
		pQueue.add(n);
		while (!pQueue.isEmpty()) {
			x = pQueue.remove();
			neightbors = x.getNeightbors();
			counter++;
			for (int i = 0; i < neightbors.size(); i++) {
				neightbor = neightbors.get(i);
				if (!pQueue.contains(neightbor) && !neightbor.isVisited()) {
					neightbor.setVisited(true);
					neightbor.setFather(x);
					if (neightbor.equals(dest)) {
						getPath(path, neightbor);
						return counter;
					}
					pQueue.add(neightbor);
				}
			}
		}
		return counter;
	}

	/**
	 * run the free algorithem from <3,1,1> to <0,0,0>
	 */
	public static void run() {
		GraphAlgorithms g = new GraphAlgorithms();
		ArrayList<Node> path = new ArrayList<Node>();
		Node source = new Node(3, 3, 1, false), dest = new Node(0, 0, 0, false);
		System.out.println("The number of nodes were discovered in BFS:\n"
				+ g.BFS(path, g.getNodes()[3][3][1], g.getNodes()[0][0][0]));
		System.out.println("The path from " + source + " to " + dest + " :\n" + path + "\n");
		g.clearVisitedNodes();
		path.clear();
		System.out.println();
		System.out.println("The number of nodes were discovered in IDDFS:\n"
				+ g.IDDFS(path, g.getNodes()[3][3][1], g.getNodes()[0][0][0], 10));
		System.out.println("The path from " + source + " to " + dest + " :\n" + path + "\n");
		g = new GraphAlgorithms(new Node(3, 3, 1, false), new Node(0, 0, 0, false));
		path.clear();
		System.out.println();
		System.out.println("The number of nodes were discovered in GBFS:\n"
				+ g.GBFS(path, g.getNodes()[3][3][1], g.getNodes()[0][0][0]));
		System.out.println("The path from " + source + " to " + dest + " :\n" + path + "\n");
		g = new GraphAlgorithms(new Node(3, 3, 1, false), new Node(0, 0, 0, false), true);
		path.clear();
		System.out.println();
		System.out.println("The number of nodes were discovered in A*:\n"
				+ g.A(path, g.getNodes()[3][3][1], g.getNodes()[0][0][0]));
		System.out.println("The path from " + source + " to " + dest + " :\n" + path);
	}
}
