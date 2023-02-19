package stack;

import node.Node;

public class Stack<T>{
	
	
	private Node<T> top;
	


	/*Metodo para agregar un dato a la pila*/
	public void push(T data) {
		if (top == null) {
			top = new Node<T>(data);
		} else {
			Node<T> newNode = new Node<T>(data);
			newNode.setNext(top);
			top = newNode;
		}

	}

	/*Metodo para poder eliminar un dato de la pila
	 * */

	public T pop() {
		if (top != null) {
			T data = top.getValue();
			top = top.getNext();
			return data;
		}
		return null;
	}

	
	
	/*Se obtiene el dato que se encuentra en la cima de la pila*/
	public T top() {
		if (top != null) {
			T data = top.getValue();
			return data;
		}
		return null;
	}
	
	/*Metodo que nos dice si la cima esta vacia o no*/
	
	public boolean isEmpty() {
		return top == null ? true : false;
	}
	
	/*Longitud de la pila*/
	
	public int size() {
		Node<T> nodeAux = top;
		int count = 0;
		while (nodeAux != null) {
			nodeAux = nodeAux.getNext();
			count++;
		}
		return count;
	}
	




}
