package t8_ejercicio05;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;




//clase singleton
class ConnectionSingleton{
	private static Connection con;
	
	public static Connection getConnection() throws SQLException{
		String url= "jdbc:mysql://127.0.0.1:3307/tienda";
		String user= "alumno";
		String password="alumno";
		
		if (con==null || con.isClosed()) {
			con=DriverManager.getConnection(url, user, password);
		}
		return con;
	}
}


public class PruebaWBStienda {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PruebaWBStienda window = new PruebaWBStienda();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public PruebaWBStienda() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		
		
		
		
		
		/**
		 * CREAR LA TABLA, CON SUS 3 PARTES, EL MODELO, LA TABLA, Y LA SCROLLBAR
		 */
		//crea el modelo de la tabla
		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("Código");
		model.addColumn("Nombre");
		model.addColumn("Precio");
		model.addColumn("Unidades");
		Connection con;
		try {
			con = ConnectionSingleton.getConnection();
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM persona");
			while (rs.next()) {
			 Object[] row = new Object[3];
			 row[0] = rs.getInt("cod_producto");
			 row[1] = rs.getString("nombre");
			 row[2] = rs.getInt("precio");
			 row[3] = rs.getInt("unidades");
			 model.addRow(row);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//crea la tabla
		JTable table = new JTable(model);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		//para poder deslizarse por la tabla
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(98, 61, 186, 117);
		frame.getContentPane().add(scrollPane);
		
	}

}
