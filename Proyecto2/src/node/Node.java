package node;

public class Node<T> {
	
	/*Dato que guarda el nodo*/
	private T data;
	
	/*Referencia al siguiente nodo*/
	private Node<T> next;
	
	public Node(T data) {
		this.data = data;
		this.next = null;
	}

	public T getValue() {
		return data;
	}

	public Node<T> getNext() {
		return next;
	}
	
	public void setNext(Node<T> next) {
		this.next = next;
	}
	
}
