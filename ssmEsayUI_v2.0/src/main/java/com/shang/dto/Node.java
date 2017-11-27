package com.shang.dto;

import java.util.List;

public class Node<T> {
	private T t;
	private List<Node<T>> children;
	
	public T getT() {
		return t;
	}
	public void setT(T t) {
		this.t = t;
	}
	public List<Node<T>> getChildren() {
		return children;
	}
	public void setChildren(List<Node<T>> children) {
		this.children = children;
	}

	
	
}
