package com.yamap55.lombok.slide;

import java.util.Arrays;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;

public class EqualsToHashCodeExample {

	public static void main(String[] args) {
		Bean4 bean1 = new Bean4(10, "ほげ", Arrays.asList("a", "b", "c"));
		Bean4 bean2 = new Bean4(10, "ほげ", Arrays.asList("a", "b", "c"));

		System.out.println(bean1.equals(bean2)); // true
	}
}

@AllArgsConstructor
@EqualsAndHashCode
class Bean4 {
	private int id;
	private String name;
	private List<String> list;
}