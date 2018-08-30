/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package students.jdbc;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import com.dto.Student;
/**
 *
 * @author christian
 */
public class DaoJDBC implements IDao{
    private Connection userConn;
    private final String SQL_INSERT = "INSERT INTO student(name, age, cohort) VALUES(?,?,?)";
    private final String SQL_UPDATE = "UPDATE student SET name=?, age=?, cohort=? WHERE id_student=?";
    private final String SQL_DELETE = "DELETE FROM student WHERE id_student = ?";
    private final String SQL_SELECT = "SELECT id_student, name, age, cohort FROM student";
    
    public DaoJDBC(){}
    
    public DaoJDBC(Connection userConn){
        this.userConn = userConn; //Si se desea instancear el objeto con una conexion
    }
    
    @Override
    public int insert(Student student) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try{
            //Verificar si ya existe una conexion
            conn = (this.userConn != null) ? this.userConn : DBConnection.getConnection();
            
            //Precompilacion del SQL statement, hacer cache del Query
            stmt = conn.prepareStatement(SQL_INSERT); 
            int index = 1;//Número del parametro
            stmt.setString(index++, student.getName()); //param 1 => ? 
            stmt.setInt(index++, student.getAge());     //param 2 => ?
            stmt.setInt(index++, student.getCohort());  //param 3 => ?
            System.out.println("Executing query: "+SQL_INSERT);
            rows = stmt.executeUpdate();
            System.out.println("Affected records: "+rows);
        }finally{
            //Cerramos los objetos PraparedStatement y Connection
            DBConnection.close(stmt);
            if(this.userConn == null){
                DBConnection.close(conn);
            }
        }
        return rows;
    }

    @Override
    public int update(Student student) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;

        try{
            conn = (this.userConn != null) ? this.userConn : DBConnection.getConnection();
            System.out.println("Executing query: "+SQL_UPDATE);
            stmt = conn.prepareStatement(SQL_UPDATE);
            int index = 1;
            stmt.setString(index++, student.getName());
            stmt.setInt(index++, student.getAge());
            stmt.setInt(index++, student.getCohort());
            stmt.setInt(index, student.getId_student());
            rows = stmt.executeUpdate();
            System.out.println("Updated records: "+rows);
        }finally{
            DBConnection.close(stmt);
            if(this.userConn == null){
                DBConnection.close(conn);
            }
        }
        return rows;
   }

    @Override
    public int delete(Student student) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try{
           conn = (this.userConn != null) ? this.userConn : DBConnection.getConnection();
            System.out.println("Executing query: "+ SQL_DELETE);
            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setInt(1, student.getId_student());
            rows = stmt.executeUpdate();
            System.out.println("Deleted records: "+rows);
        }finally{
           DBConnection.close(stmt);
           if(this.userConn == null){
               DBConnection.close(conn);
           }
        }
        return rows;
    }

    @Override
    public List<Student> select() throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        Student student = new Student();
        ResultSet rs = null;
        List<Student> students = new ArrayList<Student>();
        try{
            conn = (this.userConn != null) ? this.userConn : DBConnection.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            while(rs.next()){
                //Recuperamos cada atributo del student
                int idStudentTemp = rs.getInt(1);
                String nameTemp = rs.getString(2);
                int ageTemp = rs.getInt(3);
                int cohortTemp = rs.getInt(4);
                //Instanceamos un objeto por cada registro
                student = new Student();
                student.setId_student(idStudentTemp);
                student.setName(nameTemp);
                student.setAge(ageTemp);
                student.setCohort(cohortTemp);
                //El objeto creado lo agregamos a la lista
                students.add(student);
            }
        }finally{
            DBConnection.close(rs);
            DBConnection.close(stmt);
            if(this.userConn == null){
                DBConnection.close(conn);
            }
        }
        return students;//Regresamos la lista
    }  
}
