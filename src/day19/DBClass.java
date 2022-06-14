package day19;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DBClass {
	Connection con; //������ �̷���� ��ü
	PreparedStatement ps; //�������� �����ϱ� ���� ��ü
	ResultSet rs;  //select�� ������� �ޱ� ���� ��ü
	
	public DBClass() {
		System.out.println("������ ����");
		try {//�����ͺ��̽��� �����Ϸ��� �� �κ��� �� �־����
			//�ڹٿ��� ����Ŭ ��ɾ �����ϱ� ���� ����� ����ϴ� ����
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//����Ŭ �����ϴ� ����
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
			ps = con.prepareStatement(sql); //����� ��ü�� �̿��ؼ� ��ɾ� ���� ��ü�� ����
			
			ps.setString(1, info.getId()); //����ǥ �ڸ��� �� ���� �־���
			ps.setString(2, info.getName());
			ps.setInt(3, info.getAge());
			
			result = ps.executeUpdate(); //��ɾ� ����
			// ���� executeUpdate�� select�� ������ �������� ���
			// return ������ int ��ȯ(�����̸� 1, ���и� 0 �Ǵ� ����)
			
		} catch (Exception e) {
			e.printStackTrace(); //���� ���� ��¿�
		} 
		return result;
	}
	
	public Info selectOne(String id) {
		String sql = "select * from newst where id='"+id+"'";
		Info info = null;
		
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			
			if(rs.next()){ //rs.next() -> rs ���� ������ true ������ false �� ��ȯ
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
			rs = ps.executeQuery(); //select�� ������ executeQuery
			
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