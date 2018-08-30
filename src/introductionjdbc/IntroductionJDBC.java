/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package introductionjdbc;

import com.dto.Student;
import java.sql.SQLException;
import java.util.List;
import students.jdbc.DaoJDBC;
import students.jdbc.IDao;
/**
 *
 * @author christian
 */
public class IntroductionJDBC {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        //-----------Patrones de Diseño-------------
        //DAO: Data Access Object (Capa de persistencia)
        //DTO: Data Transfer Object
        
        //DaoJDBC      - Realiza todos los querys CRUD a la base de datos 
        //Student      - Modelo del objeto que puede persistirse en la base de datos (DTO)
        //DBConnection - Conexion a la base de datos
        //IDao         - Interfaz que implementa DaoJDBC
        
        //Utilizamos el tipo interface como referencia
        //a una clase concreta
        IDao daoJdbc = new DaoJDBC();
        
        //-----NUEVO ESTUDIATE-----
        //Hacemos uso de la clase Student la cual se usa 
        //para transferir la informacion entre las capas,
        //no es necesario especificar la llave primaria
        //ya que en este caso es de tipo autonumerico y la BD se encarga
        //de asignarle un nuevo valor        
        
        Student student = new Student();
        student.setName("Benito Camelo");
        student.setAge(23);
        student.setCohort(10);
        
        try{
            //-----AGREGAR UN REGISTRO-----
            daoJdbc.insert(student);
            
            //-----ELIMINAR UN REGISTRO----- 
            //daoJdbc.delete(new Student(2));
            
            //-----ACTUALIZAR UN REGISTRO------
            /*Student studentTmp = new Student();
            studentTmp.setId_student(1);//Actualizamos el registro N
            studentTmp.setName("Christian Lara");
            studentTmp.setAge(26);
            studentTmp.setCohort(10);
            daoJdbc.update(studentTmp); */
         
            //-----MOSTRAR TODOS LOS REGISTROS------
            List<Student> students = daoJdbc.select();
            for (Student s : students) {
                System.out.println("------------------------------------");
                System.out.println(" id_student: "+s.getId_student() );
                System.out.println(" Student:    "+s.getName());
                System.out.println(" Age:        "+s.getAge());
                System.out.println(" Cohort:     "+s.getCohort());
                System.out.println();
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    
}
