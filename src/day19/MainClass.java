package day19;

import java.util.ArrayList;
import java.util.Scanner;

public class MainClass {
	public static void main(String[] args) {
		DBClass db = new DBClass();
		Scanner input = new Scanner(System.in);
		
		String id, name; //DB형식에 맞춰서 변수 생성
		int age, num, result = 0;
		
		Info info = new Info();
		
		while(true) {
			System.out.println("1. 등록");
			System.out.println("2. 조회");
			System.out.println("3. 모든 항목");
			System.out.println("4. 종료");
			num = input.nextInt();
			
			switch(num) {
			case 1: 
				System.out.print("ID: ");
				id = input.next();
				System.out.print("Name: ");
				name = input.next();
				System.out.println("Age: ");
				age = input.nextInt();
				
				info.setId(id);
				info.setName(name);
				info.setAge(age);
				
				result = db.insert(info);
				
				if(result==1) {
					System.out.println("Save Complete!");
				}else {
					System.out.println("Save Error!");
				}
				break;
			case 2: 
				System.out.print("Search ID: ");
				id = input.next();
				info = db.selectOne(id);
				if(info==null) {
					System.out.println("해당 ID가 존재하지 않습니다.");
				}else {
					System.out.println("ID: " + info.getId());
					System.out.println("Name: " + info.getName());
					System.out.println("Age: " + info.getAge());
				}
				break;
			case 3: 
				ArrayList<Info> list = db.select();
				if(list.size()==0) {
					System.out.println("저장 정보 없음!");
				}else {
//					for(int i=0 ; i<list.size() ; i++) {
//						info = list.get(i);
//						System.out.println("ID: " + info.getId());
//						System.out.println("Name: " + info.getName());
//						System.out.println("Age: " + info.getAge());
//						System.out.println("-------------------------");
//					}
					for(Info in : list) {
						System.out.println("ID: " + in.getId());
						System.out.println("Name: " + in.getName());
						System.out.println("Age: " + in.getAge());
						System.out.println("-----------------------");
					}
					
				}
				break;
			case 4: break;
			}break;
		}
	}
}
