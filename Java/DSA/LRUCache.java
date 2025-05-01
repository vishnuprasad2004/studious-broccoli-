import java.util.HashMap;


/*  
	Implementaion of LRU (Least Recently Used) Cache Data Structure using a Doubly Linked List and a HashMap. 
	Main Functions: get(int key), and put(int key, int val)
*/

public class LRUCache {
	public class Node {
		Node next;
		Node prev;
		int key,val; 
		public Node(int key, int val) {
			this.key = key;
			this.val = val;
			next = prev = null;
		}
	}

	private final Node head = new Node(-1, -1);
	private final Node tail = new Node(-1,-1);
	private final HashMap<Integer, Node> map = new HashMap<>();
	int capacity;

    public LRUCache(int capacity) {
		this.capacity = capacity;
		head.next = tail;
		tail.prev = head;
    }

	public void addNode(Node newNode) { // O(1)
		Node oldNode = head.next;
		head.next = newNode;
		oldNode.prev = newNode;
		newNode.next = oldNode;
		newNode.prev = head;
	}

	public void deleteNode(Node oldNode) { // O(1)
		Node prevNode = oldNode.prev;
		Node nextNode = oldNode.next;
		prevNode.next = oldNode.next;
		nextNode.prev = oldNode.prev;
	}

	public void put(int key, int value) { // O(1)
		//if capacity is reached then we remove the LRU node
		if(map.size() >= capacity) { // NOTE: no need for a size variable
			Node oldNode = tail.prev;
			deleteNode(oldNode);
			map.remove(oldNode.key);
		}
		// if a new key with different value is added, we remove the old one, instead of updating its value n place
		if(map.containsKey(key)) {
			Node oldNode = map.get(key);
			deleteNode(oldNode);
			map.remove(key);
		}
		Node newNode = new Node(key, value);
		addNode(newNode);
		
		map.put(key, newNode);
	}


	public int get(int key) {
		// if the key doesn't exist, then return -1
		if(!map.containsKey(key)) return -1;

		Node currNode = map.get(key);
		int ans = currNode.val;

		// now move the MRU node to the front
		deleteNode(currNode);
		map.remove(key);
		addNode(currNode);
		map.put(key, currNode);
		return ans;
	}

	public static void main(String[] args) {
		LRUCache lru = new LRUCache(2);
		lru.put(1, 1);
		lru.put(2, 2);
		System.out.println(lru.get(1));
		lru.put(3,3);
		System.out.println(lru.get(2));
	}
}
