package test.dao;

import test.pojo.student;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class studentimp extends BaseDAO implements studentInterfence{
    Scanner sc = new Scanner(System.in);
    @Override
    public List<student> selectAll() throws SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException {
        String sql = "select student_id studentId,student_name studentName,student_score studentScore from student ";
        List<student> students = executeQuery(student.class, sql, null);
        return students;
    }

    @Override
    public student selectBystundetId(int stundetId) throws SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException {
        String sql = "select student_id studentId,student_name studentName,student_score studentScore from student where stundet_id=?";
        student student = executeQueryBean(student.class, sql, stundetId);
        return student;
    }

    @Override
    public int insert(student student) throws SQLException {
        String sql = "insert into student(student_id,student_name,student_score) values(?,?,?)";
        int studentId = student.getStudentId();
        String studentName = student.getStudentName();
        int studentScore = student.getStudentScore();
        int i = executeUpdate(sql, studentId, studentScore, studentName);
        return i;
    }

    @Override
    public int update(student student) throws SQLException {
        String sql = "update student set student_name=?,student_score=? where student_id=?";
        System.out.println("new name:");
        String new_name = sc.next();
        System.out.println("new score:");
        int new_score = sc.nextInt();
        int i = executeUpdate(sql, new_name, new_score, student.getStudentId());
        return i;
    }

    @Override
    public int delete(student student) throws SQLException {
        String sql = "delete from student where student_id=?";
        int studentId = student.getStudentId();
        int i = executeUpdate(sql, studentId);
        return i;
    }
}
