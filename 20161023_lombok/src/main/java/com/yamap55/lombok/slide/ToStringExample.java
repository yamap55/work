package com.yamap55.lombok.slide;

import java.util.Arrays;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.ToString;

public class ToStringExample {
	public static void main(String[] args) {
		Bean2 bean = new Bean2(10, "hoge", Arrays.asList("a","b"));
		System.out.println(bean.toString()); // Bean2(id=10, name=hoge, list=[a, b])
	}
}

@ToString
@AllArgsConstructor
class Bean2 {
	private int id;
	private String name;
	private List<String> list;
}
