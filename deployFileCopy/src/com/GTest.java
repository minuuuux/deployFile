package com;

import java.io.File;


public class GTest {

	public static void main(String[] args) {
		//생성할 파일경로 지정
        String path = "c:\\test\\test\\test\\file.txt";
		//파일 객체 생성
        File file = new File(path);
        //!표를 붙여주어 파일이 존재하지 않는 경우의 조건을 걸어줌
        if(!file.exists()){
            //디렉토리 생성 메서드
            file.mkdirs();
            System.out.println("created directory successfully!");
        }
	}

}
