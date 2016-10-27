package com.yamap55.lombok.slide;

import java.util.Arrays;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

public class GetterSetterExample {

	public static void main(String[] args) {
		Bean1 bean = new Bean1();
		bean.setId(10);
		bean.setName("ほげ");
		bean.setList(Arrays.asList("a", "b", "c"));

		System.out.println(bean.getId()); // 10
		System.out.println(bean.getName()); // ほげ
		System.out.println(bean.getList()); // [a, b, c]
	}
}

@Getter
@Setter
class Bean1 {
	private int id;
	private String name;
	private List<String> list;
}
