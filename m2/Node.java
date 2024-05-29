package m2;

import java.util.ArrayList;

public class Node implements Comparable<Node> {
	private int missionaries, cannibals, side;
	private boolean isVisited;
	private ArrayList<Node> neightbors;
	private Node father;
	private int h;
	////////////////// Constructor //////////////////
	//Every node reprecent 
	//the num of missionaries and cannibals in the left side
	//and the side the boat is locate.
	public Node(int missionaries, int cannibals, int side, boolean isVisited) {
		super();
		this.missionaries = missionaries;
		this.cannibals = cannibals;
		this.side = side;
		this.isVisited = isVisited;
		neightbors = new ArrayList<Node>();
		this.setFather(null);
	}

	////////////////// Getter and Setter //////////////////
	public ArrayList<Node> getNeightbors() {
		return neightbors;
	}

	public void setNeightbors(ArrayList<Node> neightbors) {
		this.neightbors = neightbors;
	}

	public boolean isVisited() {
		return isVisited;
	}

	public void setVisited(boolean isVisited) {
		this.isVisited = isVisited;
	}

	public int getCannibals() {
		return cannibals;
	}

	public void setCannibals(int cannibals) {
		this.cannibals = cannibals;
	}

	public int getMissionaries() {
		return missionaries;
	}

	public void setMissionaries(int missionaries) {
		this.missionaries = missionaries;
	}

	public int getSide() {
		return side;
	}

	public void setSide(int side) {
		this.side = side;
	}

	public Node getFather() {
		return father;
	}

	public void setFather(Node father) {
		this.father = father;
	}

	public int getH() {
		return h;
	}

	public void setH(int h) {
		this.h = h;
	}
	
	///Add son to the node
	public void addNeighbor(Node n) {
		this.neightbors.add(n);
	}
	
	///for printing
	@Override
	public String toString() {
		return "Node [missionaries=" + missionaries + ", cannibals=" + cannibals + ", side=" + side + ", isVisited="
				+ isVisited + "]";
	}

	//for Comparison nodes
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Node other = (Node) obj;
		if (cannibals != other.cannibals)
			return false;
		if (missionaries != other.missionaries)
			return false;
		if (side != other.side)
			return false;
		return true;
	}
	
	//for Comparison heuristics of nodes
	@Override
	public int compareTo(Node n) {
		if (this.getH() == n.getH()) {
			return 0;
		} else if (this.getH() < n.getH()) {
			return -1;
		} else {
			return 1;
		}
	}
}
