package com.example.googleplay.base;

public class BaseData<E> {
	private String success;
	private Error error;
	private E e;

	public boolean isSuccess() {
		return success != null && success.compareTo("true") == 0;
	}

	public String getError() {
		return error.getMessage();
	}

	public String getResponse() {
		return null;
	}

	public E getData() {
		return e;
	}

	public void setE(E e) {
		this.e = e;
	}
}
