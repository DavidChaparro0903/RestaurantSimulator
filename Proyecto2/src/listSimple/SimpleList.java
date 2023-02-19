package listSimple;

import java.util.Comparator;
import java.util.Iterator;

import node.Node;

public class SimpleList<T> implements Iterable<T>{

	private Node<T> head;
	private Comparator<T> comparator;

	public SimpleList(Comparator<T> comparator) {
		this.head = null;
		this.comparator = comparator;
	}
	
	
	public SimpleList() {
		this.head = null;
	}
	
	
	/*Inserta un dato en la lista simple*/

	public void insert(T data) {
		if(data != null) {
			if (this.head == null) {
				head = new Node<T>(data);
			} else {
				Node<T> temp = head;
				while (temp.getNext() != null) {
					temp = temp.getNext();
				}
				Node<T> nuevo = new Node<T>(data);
				temp.setNext(nuevo);
			}
		}
	
	}

	
	/*Comprueba si existe ese determinado elemento en la lista*/
	public boolean exist(T data) {
		Node<T> var = head;
		while (var != null) {
			if(comparator.compare(var.getValue(), data) == 0) {
				return true;
			}else {
				var = var.getNext();
			}
		}
		return false;
	}

	
	
	/*Observa si la lista esta vacia o no, si lo esta retorna verdadero*/


	public boolean isEmpty() {
		return (head == null) ? true : false;
	}
	

	
	/*Metodo encargado de borrar un dato de una lista simple*/

	public void delete(T data) {
		if(!isEmpty()) {
			Node<T> anterior = head;
			Node<T> adelante = head.getNext();
			if(comparator.compare(anterior.getValue(), data) == 0 && adelante == null) {
				anterior = adelante;
				head = anterior;
			}else if(comparator.compare(anterior.getValue(), data) == 0) {
				anterior = adelante;
				head = adelante;
				adelante = adelante.getNext();
			}else {
				while (adelante != null && comparator.compare(adelante.getValue(), data) != 0) {
					anterior = anterior.getNext();
					adelante = adelante.getNext();
				}
				if(adelante != null) {
					adelante = adelante.getNext();
					anterior.setNext(adelante);
					
				}else {
					System.err.println("No se encuentra el dato");
					return;
				}
			}
		}else {
			System.err.println("La lista esta vacia");
		}
	}

	
	
	/*Genera un iterador para recorrer la lista simple*/
	@Override
	public Iterator<T> iterator() {
		Iterator<T> myIterator = new Iterator<T>() {
			Node<T> actual = head;
			/*
			 * Devuelve si puede seguir iterando
			 * */
			@Override
			public boolean hasNext() {
				return actual != null;
			}
			
			/*
			 * Itera puede acceder a los objetos
			 * */

			@Override
			public T next() {
				T aux = actual.getValue();
				actual = actual.getNext();
				return aux;
			}
			
		};
		return myIterator;
	}



	
}
	
