package com.yamap55.lombok.slide;

import java.util.Arrays;
import java.util.List;

import lombok.Data;

public class DataExample {
	public static void main(String[] args) {
		Bean bean = new Bean();
		bean.setId(10);
		bean.setName("ほげ");
		bean.setList(Arrays.asList("a", "b", "c"));

		System.out.println(bean.getId()); // 10
		System.out.println(bean.getName()); // ほげ
		System.out.println(bean.getList()); // [a, b, c]
		System.out.println(bean); // Bean(id=10, name=ほげ, list=[a, b, c])

		Bean bean2 = new Bean();
		bean2.setId(10);
		bean2.setName("ほげ");
		bean2.setList(Arrays.asList("a", "b", "c"));

		System.out.println(bean.equals(bean2)); // true
	}
}

@Data
class Bean {
	private int id;
	private String name;
	private List<String> list;
}
