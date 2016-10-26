package com.yamap55.lombok.slide;

import java.util.ArrayList;

import lombok.val;

public class ValExample {
	public static void main(String[] args) {

		val str = "hoge";
		val i = 42;
		val list = new ArrayList<String>();

		System.out.println(str);
		System.out.println(i);
		System.out.println(list);
	}

}
