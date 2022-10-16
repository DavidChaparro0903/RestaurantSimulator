package queu;

import java.util.Comparator;
import java.util.Iterator;

import model.Student;
import node.Node;

public class Queu<T> implements Iterable<T>{
	
	private Node<T> top;
	
	
	/*Metodo para agregar a la cola*/

	public void push(T data) {
		if(data != null) {
			if (top == null) {
				top = new Node<T>(data);
			} else {
				Node<T> nodeAux = new Node<T>(data);
				nodeAux.setNext(top);
				top = nodeAux;
			}
		}
		
	}
	
	
	/*Metodo encargardo de observar el top de la cola
	 * este es top se refiere al ultimo elemento agregado
	 * */
	
	public T getLast() {
		return top.getValue();
	}

	/*El proximo elemento a salir de la cola*/
	public T getFirst() {
		Node<T> nodeAux = top;
		while (nodeAux.getNext() != null) {
			nodeAux = nodeAux.getNext();
		}
		return nodeAux.getValue();
	}
	
	
	/*Se remueve el primer elemento de la cola y retorna su valor
	 * */

	public T remove() {
		Node<T> previous = top;
		Node<T> actual = top.getNext();
		if (size() == 1) {
			top = null;
			return previous.getValue();
		} else {
			while (actual.getNext() != null) {
				previous = previous.getNext();
				actual = actual.getNext();
			}
			previous.setNext(null);
			return actual.getValue();
		}
	}
	
	@Override
	public Iterator<T> iterator() {
		Iterator<T> myIterator = new Iterator<T>() {
			Node<T> actual = top;

			/*
			 * Devuelve si puede seguir iterando
			 */
			@Override
			public boolean hasNext() {
				return actual != null;
			}

			/*
			 * Itera puede acceder a los objetos
			 */

			@Override
			public T next() {
				T aux = actual.getValue();
				actual = actual.getNext();
				return aux;
			}

		};
		return myIterator;
	}
	
	

	public int size() {
		Node<T> nodeAux = top;
		int count = 0;
		while (nodeAux != null) {
			nodeAux = nodeAux.getNext();
			count++;
		}
		return count;
	}
	
	public boolean isEmpty() {
		return top == null ? true : false;
	}

	
	
}
