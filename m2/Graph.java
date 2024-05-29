package m2;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class Graph {
	private Node[][][] nodes;
	private final static Node[] vectors = { new Node(1, 0, 1, false), new Node(0, 1, 1, false),
			new Node(1, 1, 1, false), new Node(2, 0, 1, false), new Node(0, 2, 1, false) };
	private PriorityQueue<Node> pQueue;
	private static final int maxNumOfEdge = 32 * 31;

	////////////////// Constructor //////////////////
	// constructor of BFS and IDDFS
	public Graph() {
		this.nodes = new Node[4][4][2];
		for (int m = 0; m < 4; m++) {
			for (int c = 0; c < 4; c++) {
				for (int s = 0; s < 2; s++) {
					this.nodes[m][c][s] = new Node(m, c, s, false);
				}
			}
		}
		for (int m = 0; m < nodes.length; m++) {
			for (int c = 0; c < nodes[0].length; c++) {
				for (int s = 0; s < nodes[0][0].length; s++) {
					if (s == 1) {
						theDiffNeighbors(nodes[m][c][s]);
					} else {
						theAddNeighbors(nodes[m][c][s]);
					}
				}
			}
		}
	}

	// constructor of GBFS
	// because GFS wants find the shortest path of number of edges
	// WE do DIJKSTRA for finding the sortest path frome source to dest and update
	// it on node.
	// and we can assume every edge in capacity 1.
	// because this is undirected graph and every edges have capacity of 1.
	// after we do DIJKSTRA we can get the shortest path of some node to dest.
	// by do Math.abs(dest.getH() - n.getH()) we get the shortest path from n to
	// dest
	// therefor Heuristics:
	// for all vertex n
	// H1= shortpath from n to dest
	public Graph(Node source, Node dest) {
		this();
		pQueue = new PriorityQueue<Node>();
		Node n, src = nodes[source.getMissionaries()][source.getCannibals()][source.getSide()],
				d = nodes[dest.getMissionaries()][dest.getCannibals()][dest.getSide()];
		DIJKSTRA(src, d);
		int num = d.getH();
		for (int m = 0; m < 4; m++) {
			for (int c = 0; c < 4; c++) {
				for (int s = 0; s < 2; s++) {
					n = this.nodes[m][c][s];
					if (n.getH() != maxNumOfEdge) {
						n.setH((int) Math.abs(num - n.getH()));
					} else {
						n.setH(num + 1);
					}
				}
			}
		}
	}

	// constructor of A*
	// WE do DIJKSTRA for finding the sortest path frome source to dest and update
	// it on node.
	// because this is undirected graph
	// and A* want find the shortest path of number of edges
	// we can assume every edge in capacity 2.
	// after we do DIJKSTRA we can get the shortest path of some node to dest.
	// i is the shortest path from source to dest in n
	// we add to it the Heuristics:
	// for all node in the tree
	// if i> high of tree from source to dest of DIJKSTRA
	// arrayOfH={ 18, 16, 14, 12, 10, 8, 6, 4, 2,0 }
	// H2=i-high
	// else
	// H2=arrayOfH[i]
	public Graph(Node source, Node dest, boolean isA) {
		this();
		pQueue = new PriorityQueue<Node>();
		Node n, src = nodes[source.getMissionaries()][source.getCannibals()][source.getSide()],
				d = nodes[dest.getMissionaries()][dest.getCannibals()][dest.getSide()];
		int[] arrayOfH = { 18, 16, 14, 12, 10, 8, 6, 4, 2, 0 };
		DIJKSTRA(src, d);
		int high = d.getH();
		int i;
		for (int m = 0; m < 4; m++) {
			for (int c = 0; c < 4; c++) {
				for (int s = 0; s < 2; s++) {
					n = this.nodes[m][c][s];
					if (n.getH() != maxNumOfEdge) {
						i = n.getH();
						if (i > high) {
							n.setH(2 * i - high);
						} else {
							n.setH(i + arrayOfH[i]);
						}
					}
				}
			}
		}
	}

	////////////////// Getter and Setter //////////////////
	public Node[][][] getNodes() {
		return nodes;
	}

	public void setNodes(Node[][][] nodes) {
		this.nodes = nodes;
	}

	public PriorityQueue<Node> getpQueue() {
		return pQueue;
	}

	public void setpQueue(PriorityQueue<Node> pQueue) {
		this.pQueue = pQueue;
	}

	public static int getMaxnumofedge() {
		return maxNumOfEdge;
	}

	/**
	 * use in BFS and IDDFS constructor this function update parent n if m>=c of his
	 * child and n.getCannibals() <= n.getMissionaries() it pass all the nodes that
	 * we can get from n and keep the rules of the game.
	 * 
	 * @param n-parent
	 */
	public void theAddNeighbors(Node n) {
		if (n.getCannibals() <= n.getMissionaries()) {
			Node addNode;
			int m, c, s;
			for (int i = 0; i < vectors.length; i++) {
				addNode = vectors[i];
				m = n.getMissionaries() + addNode.getMissionaries();
				if (m > 3) {
					continue;
				}
				c = n.getCannibals() + addNode.getCannibals();
				if (c > m) {
					continue;
				}
				s = n.getSide() + addNode.getSide();
				n.addNeighbor(nodes[m][c][s]);
			}
		}
	}

	public void theDiffNeighbors(Node n) {
		if (n.getCannibals() <= n.getMissionaries()) {
			Node diffNode;
			int m, c, s;
			for (int i = 0; i < vectors.length; i++) {
				diffNode = vectors[i];

				c = n.getCannibals() - diffNode.getCannibals();
				if (c < 0) {
					continue;
				}
				m = n.getMissionaries() - diffNode.getMissionaries();
				if (m < c) {
					continue;
				}
				s = n.getSide() - diffNode.getSide();
				n.addNeighbor(nodes[m][c][s]);
			}
		}
	}

	/**
	 * find the short path from source to dest and update the nodes in h
	 * 
	 * @param source
	 * @param dest
	 */
	public void DIJKSTRA(Node source, Node dest) {
		Node n;
		for (int m = 0; m < 4; m++) {
			for (int c = 0; c < 4; c++) {
				for (int s = 0; s < 2; s++) {
					n = nodes[m][c][s];
					if (source.equals(n)) {
						n.setH(0);
					} else {
						n.setH(maxNumOfEdge);
					}
					pQueue.add(n);
				}
			}
		}
		source.setVisited(true);
		int distance;
		ArrayList<Node> neighbors;
		while (!pQueue.isEmpty()) {
			n = pQueue.remove();
			neighbors = n.getNeightbors();
			for (Node x : neighbors) {
				distance = n.getH() + 1;
				if (distance < x.getH()) {
					pQueue.remove(x);
					x.setH(distance);
					pQueue.add(x);
				}
			}
		}
	}

	/**
	 * clear visited nodes
	 */
	public void clearVisitedNodes() {
		for (int i = 0; i < nodes.length; i++) {
			for (int j = 0; j < nodes[0].length; j++) {
				for (int j2 = 0; j2 < nodes[0][0].length; j2++) {
					nodes[i][j][j2].setVisited(false);
				}
			}
		}
	}

	/**
	 * this functin find all the path from node to his root
	 * 
	 * @param path
	 * @param neightbor-node
	 */
	public void getPath(ArrayList<Node> path, Node neightbor) {
		Node n = neightbor;
		path.clear();
		while (n != null) {
			path.add(0, n);
			n = n.getFather();
		}
	}
}
