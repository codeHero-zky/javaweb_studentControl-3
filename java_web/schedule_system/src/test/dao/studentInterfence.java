package test.dao;

import test.pojo.student;

import java.sql.SQLException;
import java.util.List;

public interface studentInterfence {
      List<student> selectAll() throws SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException;
      student selectBystundetId(int stundetId) throws SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException;
      int insert(student student) throws SQLException;
      int update(student student) throws SQLException;
      int delete(student student) throws SQLException;

}
