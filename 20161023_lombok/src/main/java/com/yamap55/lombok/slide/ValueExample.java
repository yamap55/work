package com.yamap55.lombok.slide;

import java.util.Arrays;
import java.util.List;

import lombok.Value;

public class ValueExample {
	public static void main(String[] args) {
		Bean5 bean = new Bean5(10, "ほげ", Arrays.asList("a", "b", "c"));
		// new Bean5(); 未定義
		// bean.setId(10); 未定義

		System.out.println(bean.getId()); // 10
		System.out.println(bean.getName()); // ほげ
		System.out.println(bean.getList()); // [a, b, c]
		System.out.println(bean); // Bean(id=10, name=ほげ, list=[a, b, c])

		Bean5 bean2 = new Bean5(10, "ほげ", Arrays.asList("a", "b", "c"));

		System.out.println(bean.equals(bean2)); // true
	}
}

@Value
class Bean5 {
	private int id;
	private String name;
	private List<String> list;
}