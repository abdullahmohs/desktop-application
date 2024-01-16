package cashier;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class database {

    protected final static String user = "root";
    protected final static String passwordDB = "";
    protected final static String add = "jdbc:mysql://localhost/cashier";
    protected final static String add_1 = "jdbc:mysql://localhost/am";
    protected final static String add_2 = "jdbc:mysql://localhost/pm";
    protected final static String add_3 = "jdbc:mysql://localhost/replace";
    protected final static String add_4 = "jdbc:mysql://localhost/cash";
    protected static Connection connect;
    protected static PreparedStatement s;
    private static String re, re_par = null, re_open, re_code;
    private static String user_name;
    private static String pass;
    private static String num_user;
    private static String kind;
    private static String per;
    private static int re_pone, re_qun;
    protected static ResultSet r;
    private final static  DateTimeFormatter date = DateTimeFormatter.ofPattern("yyyy/MM/dd");
    private final static DateTimeFormatter time = DateTimeFormatter.ofPattern("HH:mm");
    private final static LocalDateTime now = LocalDateTime.now();

    database() {

    }

    public void add_user(String user_name, String password, int num_user, String kind, String per) {
        try {
            connect = (Connection) DriverManager.getConnection(add, user, passwordDB);
            if (per == "الوردية الصباحية") {
                per = "AM";
            } else if (per == "الوردية المسائية") {
                per = "PM";
            }
            s = connect.prepareStatement("insert into accounts values(?,?,?,?,?)");
            s.setString(1, user_name);
            s.setString(2, password);
            s.setInt(3, num_user);
            s.setString(4, kind);
            s.setString(5, per);
            s.execute();
            close_database();
        } catch (SQLException ex) {
        }
    }

    public String get_user(String num_user) {
        try {
            connect = (Connection) DriverManager.getConnection(add, user, passwordDB);
            s = connect.prepareStatement("select user_name from accounts where num_user = ?");
            s.setString(1, num_user);
            r = s.executeQuery();
            r.next();
            user_name = r.getString("user_name");
            close_database();
        } catch (SQLException ex) {
        }
        return user_name;
    }

    public String get_password(String num_user) {
        try {
            connect = (Connection) DriverManager.getConnection(add, user, passwordDB);
            s = connect.prepareStatement("select password from accounts where num_user = ?");
            s.setString(1, num_user);
            r = s.executeQuery();
            while (r.next()) {
                pass = r.getString("password");
            }
            close_database();
        } catch (SQLException ex) {
        }
        return pass;
    }

    public String get_condition(String num_user) {
        try {
            connect = (Connection) DriverManager.getConnection(add, user, passwordDB);
            s = connect.prepareStatement("select * from accounts where num_user = ? ");
            s.setString(1, num_user);
            r = s.executeQuery();
            while (r.next()) {
                kind = r.getString("kind");
            }
            close_database();
        } catch (SQLException ex) {
        }
        return kind;
    }

    public String get_perid(String num_user) {
        try {
            connect = (Connection) DriverManager.getConnection(add, user, passwordDB);
            s = connect.prepareStatement("select * from accounts where num_user = ? ");
            s.setString(1, num_user);
            r = s.executeQuery();
            while (r.next()) {
                per = r.getString("perid");
            }
            close_database();
        } catch (SQLException ex) {
        }
        return per;
    }

    public Boolean check(String num_user) {
        try {
            connect = (Connection) DriverManager.getConnection(add, user, passwordDB);
            s = connect.prepareStatement("select * from accounts where num_user like ? ");
            s.setString(1, num_user);
            r = s.executeQuery();
            r.next();
            re = r.getString("num_user");
            close_database();
            if (re == null) {
                return false;
            } else {
                return true;
            }
        } catch (SQLException ex) {
        }
        return false;
    }

    public void update_user(String user_name, String password, int num_user, String kind, String per) {
        try {
            connect = (Connection) DriverManager.getConnection(add, user, passwordDB);
            s = connect.prepareStatement("update accounts set password = ? , user_name = ?, kind = ?,perid =? where num_user = ? ");
            s.setString(1, password);
            s.setString(2, user_name);
            s.setString(3, kind);
            s.setString(4, per);
            s.setInt(5, num_user);
            s.execute();
            close_database();
        } catch (SQLException ex) {
        }
    }

    public void delete_user(String num_user) {
        try {
            connect = (Connection) DriverManager.getConnection(add, user, passwordDB);
            s = connect.prepareStatement("delete from accounts where num_user = ?");
            s.setString(1, num_user);
            s.execute();
            close_database();
        } catch (SQLException ex) {
        }
    }

    public void add_parcode(String parcode, String products, String price, String quntity) {
        try {
            connect = (Connection) DriverManager.getConnection(add, user, passwordDB);
            s = connect.prepareStatement("insert into products(parcode ,products, price, quntity)values(?, ?, ?, ?)");
            s.setString(1, parcode);
            s.setString(2, products);
            s.setString(3, price);
            s.setString(4, quntity);
            s.execute();
            close_database();
        } catch (SQLException ex) {
        }
    }

    public String get_name(String parcode) {
        String name_product = null;
        try {
            connect = (Connection) DriverManager.getConnection(add, user, passwordDB);
            s = connect.prepareStatement("select * from products where parcode like ?");
            s.setString(1, parcode);
            r = s.executeQuery();
            while (r.next()) {
                name_product = r.getString("products");
            }
            close_database();
        } catch (SQLException ex) {
        }
        return name_product;
    }

    public static String getName(String code) {
        String name_product = null;
        try {
            connect = (Connection) DriverManager.getConnection(add, user, passwordDB);
            s = connect.prepareStatement("select * from products where code like ?");
            s.setString(1, code);
            r = s.executeQuery();
            while (r.next()) {
                name_product = r.getString("products");
            }
            close_database();
        } catch (SQLException ex) {
        }
        return name_product;
    }

    public static String get_price(String parcode) {
        String price = null;
        try {
            connect = (Connection) DriverManager.getConnection(add, user, passwordDB);
            s = connect.prepareStatement("select * from products where parcode like ?");
            s.setString(1, parcode);
            r = s.executeQuery();
            while (r.next()) {
                price = r.getString("price");
            }
            close_database();
        } catch (SQLException ex) {
        }
        return price;
    }

    public static String getPrice(String code) {
        String price = null;
        try {
            connect = (Connection) DriverManager.getConnection(add, user, passwordDB);
            s = connect.prepareStatement("select * from products where code like ?");
            s.setString(1, code);
            r = s.executeQuery();
            while (r.next()) {
                price = r.getString("price");
            }
            close_database();
        } catch (SQLException ex) {
        }
        return price;
    }

    public static String getQuntity(String code) {
        String quntity = null;
        try {
            connect = (Connection) DriverManager.getConnection(add, user, passwordDB);
            s = connect.prepareStatement("select * from products where code like ?");
            s.setString(1, code);
            r = s.executeQuery();
            while (r.next()) {
                quntity = r.getString("quntity");
            }
            close_database();
        } catch (SQLException ex) {
        }
        return quntity;
    }
    
    public static String get_quntity(String code) {
        String quntity = null;
        try {
            connect = (Connection) DriverManager.getConnection(add, user, passwordDB);
            s = connect.prepareStatement("select * from products where parcode like ?");
            s.setString(1, code);
            r = s.executeQuery();
            while (r.next()) {
                quntity = r.getString("quntity");
            }
            close_database();
        } catch (SQLException ex) {
        }
        return quntity;
    }

    public static Boolean check_parcode(String parcode) {
        try {
            connect = (Connection) DriverManager.getConnection(add, user, passwordDB);
            s = connect.prepareStatement("select * from products where parcode like ? ");
            s.setString(1, parcode);
            r = s.executeQuery();
            r.next();
            re_par = r.getString("parcode");
            close_database();
            if (re_par == null) {
                return false;
            } else {
                return true;
            }
        } catch (SQLException ex) {
        }
        return false;
    }

    public static Boolean check_code(String code) {
        try {
            connect = (Connection) DriverManager.getConnection(add, user, passwordDB);
            s = connect.prepareStatement("select * from products where code like ? ");
            s.setString(1, code);
            r = s.executeQuery();
            r.next();
            re_par = r.getString("code");
            close_database();
            if (re_par == null) {
                return false;
            } else {
                return true;
            }
        } catch (SQLException ex) {
        }
        return false;
    }

    public static void close_database() throws SQLException {
        if (s != null) {
            s.close();
        }
    }

    public static String get_code(String parcode) {
        try {
            connect = (Connection) DriverManager.getConnection(add, user, passwordDB);
            s = connect.prepareStatement("select * from products where parcode like ? ");
            s.setString(1, parcode);
            r = s.executeQuery();
            r.next();
            re_code = Integer.toString(r.getInt("code"));
            close_database();
        } catch (SQLException ex) {
        }
        return re_code;
    }

    public void craete_table(int num_pone) {
        try {
            connect = (Connection) DriverManager.getConnection(add_1, user, passwordDB);
            s = connect.prepareStatement("create table pone_?(name_products nvarchar(50),price nvarchar(50),quntity nvarchar(50),code nvarchar(50),total nvarchar(50))");
            s.setInt(1, num_pone);
            s.execute();
            close_database();
        } catch (SQLException ex) {
        }
    }
    
    public void craete_table_replace(int num_pone) {
        try {
            connect = (Connection) DriverManager.getConnection(add_3, user, passwordDB);
            s = connect.prepareStatement("create table replace_?(name_products nvarchar(50),price nvarchar(50),quntity nvarchar(50),code nvarchar(50),total nvarchar(50))");
            s.setInt(1, num_pone);
            s.execute();
            close_database();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void detail_pone(int num_pone) {
        try {
            connect = (Connection) DriverManager.getConnection(add, user, passwordDB);
            s = connect.prepareStatement("insert into pone(num_pone) values(?)");
            s.setInt(1, num_pone);
            s.execute();
            close_database();
        } catch (SQLException ex) {
        }
    }
    
    public void detail_pone_replace(int num_pone) {
        try {
            connect = (Connection) DriverManager.getConnection(add_4, user, passwordDB);
            s = connect.prepareStatement("insert into pone(num_pone) values(?)");
            s.setInt(1, num_pone);
            s.execute();
            close_database();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void add_pone(int num_pone, String total, String price, String quntity, String name_products, String code) {
        try {
            connect = (Connection) DriverManager.getConnection(add_1, user, passwordDB);
            s = connect.prepareStatement("insert into pone_? values(?,?,?,?,?)");
            s.setInt(1, num_pone);
            s.setString(2, name_products);
            s.setString(3, price);
            s.setString(4, quntity);
            s.setString(5, code);
            s.setString(6, total);
            s.execute();
            close_database();
        } catch (SQLException ex) {
        }
    }
    
    public void add_pone_replace(int num_pone, String total, String price, String quntity, String name_products, String code) {
        try {
            connect = (Connection) DriverManager.getConnection(add_3, user, passwordDB);
            s = connect.prepareStatement("insert into replace_? values(?,?,?,?,?)");
            s.setInt(1, num_pone);
            s.setString(2, name_products);
            s.setString(3, price);
            s.setString(4, quntity);
            s.setString(5, code);
            s.setString(6, total);
            s.execute();
            close_database();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public int get_num_pone() {
        try {
            connect = (Connection) DriverManager.getConnection(add, user, passwordDB);
            s = connect.prepareStatement("select * from pone");
            r = s.executeQuery();
            while (r.next()) {
                re_pone = r.getInt("num_pone");
            }
            if (re_pone == 0) {
                detail_pone(1);
                re_pone = 1;
            }
            close_database();

        } catch (SQLException ex) {
        }
        return re_pone;
    }
    
    public int get_num_pone_replace() {
        try {
            connect = (Connection) DriverManager.getConnection(add_4, user, passwordDB);
            s = connect.prepareStatement("select * from pone");
            r = s.executeQuery();
            while (r.next()) {
                re_pone = r.getInt("num_pone");
            }
            if (re_pone == 0) {
                detail_pone_replace(1);
                re_pone = 1;
            }
            close_database();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return re_pone;
    }

    public void update_details(int num_pone, String total, String cash, String mins, String number, String history) {
        try {
            connect = (Connection) DriverManager.getConnection(add, user, passwordDB);
            s = connect.prepareStatement("update pone set total = ? , cash = ? , mins = ? , number = ? , history = ? where num_pone = ?");
            s.setString(1, total);
            s.setString(2, cash);
            s.setString(3, mins);
            s.setString(4, number);
            s.setString(5, history);
            s.setInt(6, num_pone);
            s.execute();
            close_database();
        } catch (SQLException ex) {
        }
    }
    
    public void update_details_replace(int num_pone, String total, String history) {
        try {
            connect = (Connection) DriverManager.getConnection(add_4, user, passwordDB);
            s = connect.prepareStatement("update pone set total = ? , history = ? where num_pone = ?");
            s.setString(1, total);
            s.setString(2, history);
            s.setInt(3, num_pone);
            s.execute();
            close_database();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void plus_quntity(int code , String quntity) {
        try {
            connect = (Connection) DriverManager.getConnection(add, user, passwordDB);
            s = connect.prepareStatement("select quntity from products where code = ?");
            s.setInt(1, code);
            r = s.executeQuery();
            while (r.next()) {
                re_qun = r.getInt("quntity");
            }
            close_database();
            connect = (Connection) DriverManager.getConnection(add, user, passwordDB);
            s = connect.prepareStatement("update products set quntity = ? where code = ?");
            s.setString(1, Integer.toString(re_qun + Integer.parseInt(quntity)));
            s.setInt(2, code);
            s.execute();
            close_database();
        } catch (SQLException ex) {
        }
    }

    public void mins_quntity(int code, String quntity) {
        try {
            connect = (Connection) DriverManager.getConnection(add, user, passwordDB);
            s = connect.prepareStatement("select quntity from products where code = ?");
            s.setInt(1, code);
            r = s.executeQuery();
            while (r.next()) {
                re_qun = r.getInt("quntity");
            }
            close_database();
            connect = (Connection) DriverManager.getConnection(add, user, passwordDB);
            s = connect.prepareStatement("update products set quntity = ? where code = ?");
            s.setString(1, Integer.toString(re_qun - Integer.parseInt(quntity)));
            s.setInt(2, code);
            s.execute();
            close_database();
        } catch (SQLException ex) {
        }
    }

}
