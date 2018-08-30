/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package students.jdbc;

import java.sql.SQLException;
import java.util.List;
import com.dto.Student;

/**
 *
 * @author christian
 */
public interface IDao {
    public int insert(Student student) throws SQLException;

    public int update(Student student) throws SQLException;
    
    public int delete(Student student) throws SQLException;
    
    public List<Student> select() throws SQLException;
}
