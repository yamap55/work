package com.yamap55.lombok.slide;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

public class DataExample {
	public static void main(String[] args) {
		Bean bean = new Bean();
		bean.setId(10);
		int id = bean.getId(); // 10
		bean.setName("ほげ");
		String name = bean.getName(); // ほげ
		bean.setList(new ArrayList<String>());
		List<String> list = bean.getList(); // ArrayList
	}
}

@Data
class Bean {
	private int id;
	private String name;
	private List<String> list;
}
