package cn.edu.hcnu.Servlet;

import cn.edu.hcnu.model.Emp;
import org.apache.commons.dbutils.QueryRunner;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmpServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri=null;
        uri = request.getRequestURI();
        if("/selectEmp".equals(uri)){
            doSelectEmp(request,response);
        }else if("/delectEmp".equals(uri)){
            doDelectEmp(request,response);
        }else if("/addEmp".equals((uri))){
            doAddEmp(request,response);
        }else if("/choseEmp".equals(uri)){
            doChoseEmp(request,response);
        }else if("/changeEmp".equals(uri)){
            doChangeEmp(request,response);
        }
    }
    protected  void doSelectEmp(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String page = request.getParameter("page");
        int selectpage;
        if(page==null) {
            selectpage = 1;
        }else if(Integer.valueOf(page)>0){

            selectpage=Integer.valueOf(page);
        }else{
            selectpage = 1;
        }
        int pagesize=5;
        int rownum=pagesize*selectpage;
        int rn=rownum-pagesize+1;
        String url="jdbc:oracle:thin:@localhost:1521:orcl";
        String username="scott";
        String password="tiger";
//        String sql="select * from emp";

        String sql="select * from (select rownum rn,t.* from (select * from emp) t where rownum<=?)where rn>=?";
        Connection conn = null;
        PreparedStatement pstmt=null;
        ResultSet rs =null;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            conn= DriverManager.getConnection(url,username,password);
            conn.setAutoCommit(false);
            pstmt=conn.prepareStatement(sql);
            List empList = new ArrayList();
            pstmt=conn.prepareStatement(sql);
            pstmt.setInt(1,rownum);
            pstmt.setInt(2,rn);
            rs=pstmt.executeQuery();
            while(rs.next()){
                Emp emp =new Emp();
                emp.setEmpno(rs.getInt("EMPNO"));
                emp.setEname(rs.getString("ENAME"));
                emp.setJob(rs.getString("JOB"));
                emp.setMgr(rs.getInt("MGR"));
                emp.setHiredate(rs.getDate("HIREDATE"));
                emp.setSal(rs.getFloat("SAL"));
                emp.setComm(rs.getFloat("COMM"));
                emp.setDeptno(rs.getInt("DEPTNO"));
                empList.add(emp);
            }
            request.setAttribute("empDT",empList);
            request.setAttribute("page",selectpage);

            int totalpage=0;
            int count1=0;
            sql="select count(*) from emp";
            pstmt=conn.prepareStatement(sql);
            rs=pstmt.executeQuery();
            while(rs.next()){
                count1=rs.getInt(1);
            }
            totalpage=count1%pagesize==0?count1/pagesize:(count1/pagesize)+1;

            System.out.println("总页数"+totalpage);
            request.setAttribute("totalpage",totalpage);
            request.getRequestDispatcher("index.jsp").forward(request,response);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                if(rs!= null){
                    rs.close();
                }
                if (pstmt!= null) {
                    pstmt.close();
                }
                if(conn!=null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    protected  void doDelectEmp(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String empno=request.getParameter("empno");
        System.out.println("删除"+empno);
        String url="jdbc:oracle:thin:@localhost:1521:orcl";
        String username="scott";
        String password="tiger";
        String sql="delete from emp where empno=?";

        Connection conn = null;
        PreparedStatement pstmt=null;

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            conn= DriverManager.getConnection(url,username,password);
            conn.setAutoCommit(false);
            pstmt=conn.prepareStatement(sql);
            pstmt.setInt(1,Integer.parseInt(empno));
            pstmt.executeUpdate();
            conn.commit();
            request.getRequestDispatcher("selectEmp").forward(request,response);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                if (pstmt!= null) {
                    pstmt.close();
                }
                if(conn!=null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    protected void doAddEmp(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String ename = null;
        String empno = null;
        String hiredate = null;
        ename = request.getParameter("ename");
        empno = request.getParameter("empno");
        hiredate = request.getParameter("hiredate");

        String url="jdbc:oracle:thin:@localhost:1521:orcl";
        String username="scott";
        String password="tiger";
        String sql="insert into emp(empno,ename,hiredate) values(?,?,?)";
        Connection conn = null;
        PreparedStatement pstmt=null;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            conn= DriverManager.getConnection(url,username,password);
            conn.setAutoCommit(false);
            pstmt=conn.prepareStatement(sql);
            pstmt.setInt(1,Integer.parseInt(empno));
            pstmt.setString(2,ename);
            pstmt.setDate(3,Date.valueOf(hiredate));
            pstmt.executeUpdate();
            conn.commit();
            request.getRequestDispatcher("selectEmp").forward(request,response);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                if (pstmt!= null) {
                    pstmt.close();
                }
                if(conn!=null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    protected  void doChoseEmp(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String empno = request.getParameter("empno");

        String url="jdbc:oracle:thin:@localhost:1521:orcl";
        String username="scott";
        String password="tiger";
        String sql="select * from emp where empno="+empno;

        Connection conn = null;
        PreparedStatement pstmt=null;
        ResultSet rs =null;

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Emp emp =new Emp();
            conn= DriverManager.getConnection(url,username,password);
            conn.setAutoCommit(false);
            pstmt=conn.prepareStatement(sql);
//            pstmt.setInt(1,Integer.valueOf(empno));
            pstmt=conn.prepareStatement(sql);
            rs=pstmt.executeQuery();
            while(rs.next()){
                emp.setEmpno(rs.getInt("EMPNO"));
                emp.setEname(rs.getString("ENAME"));
                emp.setJob(rs.getString("JOB"));
                emp.setMgr(rs.getInt("MGR"));
                emp.setHiredate(rs.getDate("HIREDATE"));
                emp.setSal(rs.getFloat("SAL"));
                emp.setComm(rs.getFloat("COMM"));
                emp.setDeptno(rs.getInt("DEPTNO"));
            }
            request.setAttribute("emp",emp);
            request.getRequestDispatcher("modifyEmp.jsp").forward(request,response);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                if(rs!= null){
                    rs.close();
                }
                if (pstmt!= null) {
                    pstmt.close();
                }
                if(conn!=null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    protected void doChangeEmp(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String ename = null;
        String empno = null;
        String hiredate = null;
        ename = request.getParameter("ename");
        empno = request.getParameter("empno");
        hiredate = request.getParameter("hiredate");

        String url="jdbc:oracle:thin:@localhost:1521:orcl";
        String username="scott";
        String password="tiger";
        String sql="update emp set ename=?,hiredate=? where empno=?";
        Connection conn = null;
        PreparedStatement pstmt=null;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            conn= DriverManager.getConnection(url,username,password);
            conn.setAutoCommit(false);
            pstmt=conn.prepareStatement(sql);
            pstmt.setString(1,ename);
            pstmt.setDate(2,Date.valueOf(hiredate));
            pstmt.setInt(3,Integer.parseInt(empno));
            pstmt.executeUpdate();
            conn.commit();
            request.getRequestDispatcher("selectEmp").forward(request,response);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                if (pstmt!= null) {
                    pstmt.close();
                }
                if(conn!=null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
