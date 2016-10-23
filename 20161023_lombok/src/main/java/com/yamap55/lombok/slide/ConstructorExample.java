package com.yamap55.lombok.slide;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

public class ConstructorExample {
	Hoge hoge = new Hoge(10); // 明示的に作成
	Hoge hoge1 = new Hoge(); // @@NoArgsConstructor
	Hoge hoge2 = new Hoge(10, "hoge", new ArrayList<String>()); // @AllArgsConstructor
}

@NoArgsConstructor
@AllArgsConstructor
class Hoge {
	private int id;
	private String name;
	private List<String> list;

	Hoge(int id) {
	}
}
