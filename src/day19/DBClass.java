package day19;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DBClass {
	Connection con; //연결이 이루어진 객체
	PreparedStatement ps; //쿼리문을 전송하기 위한 객체
	ResultSet rs;  //select의 결과값을 받기 위한 객체
	
	public DBClass() {
		System.out.println("생성자 실행");
		try {//데이터베이스에 연결하려면 이 부분이 꼭 있어야함
			//자바에서 오라클 명령어를 수행하기 위한 기능을 등록하는 과정
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//오라클 연결하는 과정
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			con = DriverManager.getConnection(url,"melonar","0820");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public int insert(Info info) {
		String sql = "insert into newst values(?,?,?)";
		int result=0;
		try {
			ps = con.prepareStatement(sql); //연결된 객체를 이용해서 명령어 전송 객체를 얻어옴
			
			ps.setString(1, info.getId()); //물음표 자리에 각 값을 넣어줌
			ps.setString(2, info.getName());
			ps.setInt(3, info.getAge());
			
			result = ps.executeUpdate(); //명령어 전송
			// 보통 executeUpdate는 select를 제외한 나머지에 사용
			// return 값으로 int 반환(성공이면 1, 실패면 0 또는 에러)
			
		} catch (Exception e) {
			e.printStackTrace(); //오류 내용 출력용
		} 
		return result;
	}
	
	public Info selectOne(String id) {
		String sql = "select * from newst where id='"+id+"'";
		Info info = null;
		
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			
			if(rs.next()){ //rs.next() -> rs 값이 있으면 true 없으면 false 값 반환
				info = new Info();
				info.setId(rs.getString("id"));
				info.setName(rs.getString("name"));
				info.setAge(rs.getInt("age"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return info;
		
	}
	
	public ArrayList<Info> select() {
		String sql = "select * from newst";
		ArrayList<Info> list = new ArrayList<Info>();

		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery(); //select는 무조건 executeQuery
			
			while(rs.next()) {
				Info info = new Info();
				info.setId(rs.getString("id"));
				info.setName(rs.getString("name"));
				info.setAge(rs.getInt("age"));
				list.add(info);
				
//				System.out.println(rs.getString("id"));
//				System.out.println(rs.getString("name"));
//				System.out.println(rs.getString("age"));
//				System.out.println("-------------------");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
}