package com.thoughtworks.quiz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// TODO GTB-4: * 分包比较合理
// TODO GTB-3: * 使用了三层架构
// TODO GTB-3: * 可以加强下Java8 Optional的掌握
// TODO GTB-3: * 使用了Lombok
// TODO GTB-3: * 对Java8 Stream有不错的使用
// TODO GTB-1: * 创建用户和教育经历时没有进行校验
// TODO GTB-2: * 有Controller层的测试，但只覆盖的Happy Path
@SpringBootApplication
public class QuizApplication {

	public static void main(String[] args) {
		SpringApplication.run(QuizApplication.class, args);
	}

}
