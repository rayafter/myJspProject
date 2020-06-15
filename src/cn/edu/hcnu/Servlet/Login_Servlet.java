package cn.edu.hcnu.Servlet;

import cn.edu.hcnu.model.Emp;
import com.sun.org.apache.xerces.internal.util.HTTPInputSource;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Login_Servlet extends javax.servlet.http.HttpServlet {
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        String uri=null;
        uri=request.getRequestURI();
        System.out.println(uri);
        if("/loginServlet".equals(uri)){
            doLogin(request,response);
        }else if("/exit".equals(uri)){
            HttpSession session = request.getSession();
            session.invalidate();
            response.sendRedirect("login.html");
        }
    }

    protected void doLogin(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        //        jdbc:oracle:thin:@192.168.1.118:1521:orcl

        String id=request.getParameter("username");
        String pw=request.getParameter("userPW");

        System.out.println(id);
        System.out.println(pw);

        String url="jdbc:oracle:thin:@localhost:1521:orcl";
        String username="scott";
        String password="tiger";
        String sql="select * from users where user_id= ? and password = ?";

        Connection conn = null;
        PreparedStatement pstmt=null;
        ResultSet rs=null;

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            conn = DriverManager.getConnection(url,username,password);

            System.out.println(conn);

            pstmt=conn.prepareStatement(sql);
            pstmt.setString(1,id);
            pstmt.setString(2,pw);
            rs=pstmt.executeQuery();

            if(rs.next()){
                System.out.println("登录成功");
//                List empList = new ArrayList();
//                sql="select * from emp";
//                pstmt=conn.prepareStatement(sql);
//                rs=pstmt.executeQuery();
//                while(rs.next()){
//                    Emp emp = new Emp();
//                    emp.setEmpno(rs.getInt("EMPNO"));
//                    emp.setEname(rs.getString("ENAME"));
//                    emp.setJob(rs.getString("JOB"));
//                    emp.setMgr(rs.getInt("MGR"));
//                    emp.setHiredate(rs.getDate("HIREDATE"));
//                    emp.setSal(rs.getFloat("SAL"));
//                    emp.setComm(rs.getFloat("COMM"));
//                    emp.setDeptno(rs.getInt("DEPTNO"));
//                    empList.add(emp);
//                }
                HttpSession session = request.getSession();
                session.setAttribute("username",id);
//                request.setAttribute("empDT",empList);
                request.getRequestDispatcher("newindex.jsp").forward(request,response);
//                response.sendRedirect("index.jsp");
            }else{
                System.out.println("登录失败");
                response.sendRedirect("login.html");
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                if(rs!=null){ rs.close();
                }
                if(pstmt!=null) { pstmt.close();
                }
                if(conn!=null) { conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}