package controller;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.Vector;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.Mensajeria_Model;
import model.Usuario;

	public class VerMensajes implements Initializable {
		
		static final String USER = "pri_easyliving";
	    static final String PASS = "MixiMixi";

	    @FXML
	    private Label nombreUser;

	    @FXML
	    private Label rolUser;

	    @FXML
	    private Button buttonSubMenu;

	    @FXML
	    private VBox vBoxMsg;

	    @FXML
	    private Button responderButton;

	    @FXML
	    private Label labelMensaje;
	    
	    @FXML
	    private Button NuevoMsg;
	    
	    @FXML
	    private Button MsgRecibidos;
	    
	    @FXML
	    private Button MsgEnviados;
	    
	    private Vector<Mensajeria_Model> v;
	    
	    private Usuario userLogged;
	    
	    public void setUserLogged(Usuario userLogged) {
	        this.userLogged = userLogged;
	    }

	    public void initialize(URL location, ResourceBundle resources) {
	     nombreUser.setText(userLogged.nombre);
	     rolUser.setText(userLogged.rol_tipo);
	     
	     Connection conn = null;
	     Statement stmt = null;
	     String sql;
	     
	     try {
	        	Class.forName("org.mariadb.jdbc.Driver");

	            System.out.println("Connecting to a selected database...");

	            conn = DriverManager.getConnection(
	                    "jdbc:mariadb://195.235.211.197\r\n"
	                    + "/prieasyliving", USER, PASS);
	            
	            System.out.println("Connectado a la Base de Datos...");
	            
	            Vector<Mensajeria_Model> v = new Vector<Mensajeria_Model>();
	            sql = "SELECT m.id_emitido, u1.nombre AS nombre_emitido, u1.apellidos AS apellidos_emitido, m.id_recibido, u2.nombre AS nombre_recibido, u2.apellidos AS apellidos_recibido, m.mensaje "
	            		+ "FROM mensajes AS m "
	            		+ "JOIN usuarios AS u1 ON m.id_emitido = u1.id "
	            		+ "JOIN usuarios AS u2 ON m.id_recibido = u2.id ";
	            stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery( sql );
				while ( rs.next() ) {
					int id_emitido= rs.getInt("id_emitido");
					String  nombre_emitido = rs.getString("nombre_emitido");
					String  nombre_recibido = rs.getString("nombre_recibido");
					String  apellido_emitido = rs.getString("apellidos_emitido");
					String  apellido_recibido = rs.getString("apellidos_recibido");
					String mensaje = rs.getString("mensaje");
					Mensajeria_Model emitido = new Mensajeria_Model(nombre_emitido,apellido_emitido, nombre_recibido, apellido_recibido, mensaje);
					v.add(emitido);	
					
				if (id_emitido!=userLogged.id) {
	                System.out.println("Nombre : " + nombre_emitido);
	                System.out.println("Apellidos : " + apellido_emitido);
	                System.out.println("Nombre : " + nombre_recibido);
	                System.out.println("Apellidos : " + apellido_recibido);
	                System.out.println("Mensaje: " + mensaje);
	                System.out.println("-------------------------------------");
					
				
		            Label bMensajeria = new Label();
					vBoxMsg.getChildren().add(bMensajeria);
		            bMensajeria.setText(nombre_emitido + " " + apellido_emitido);
		            bMensajeria.setTextFill(Color.WHITE);
		            bMensajeria.setOnMouseClicked(new EventHandler<MouseEvent>() {
		          @Override
		          public void handle(MouseEvent event) {
		           labelMensaje.setText("\n" + emitido.getMensaje());
		                }
		            });
		        
				}
				}
				rs.close();
				stmt.close();
				conn.close();

	        } catch (SQLException se) {
	            se.printStackTrace();
	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	            try {
	                if (stmt != null) {
	                    conn.close();
	                }
	            } catch (SQLException se) {
	            }
	            try {
	                if (conn != null) {
	                    conn.close();
	                }
	            } catch (SQLException se) {
	                se.printStackTrace();
	            }
		}
	  
  }
	    
	    @FXML
	    void MsgRecibidos(ActionEvent event) {

	    	Connection conn = null;
		     Statement stmt = null;
		     String sql;
		     
		     try {
		        	Class.forName("org.mariadb.jdbc.Driver");

		            System.out.println("Connecting to a selected database...");

		            conn = DriverManager.getConnection(
		                    "jdbc:mariadb://195.235.211.197\r\n"
		                    + "/prieasyliving", USER, PASS);
		            
		            System.out.println("Connectado a la Base de Datos...");
		            
		            Vector<Mensajeria_Model> v = new Vector<Mensajeria_Model>();
		            sql = "SELECT m.id_emitido, u1.nombre AS nombre_emitido, u1.apellidos AS apellidos_emitido, m.id_recibido, u2.nombre AS nombre_recibido, u2.apellidos AS apellidos_recibido, m.mensaje "
		            		+ "FROM mensajes AS m "
		            		+ "JOIN usuarios AS u1 ON m.id_emitido = u1.id "
		            		+ "JOIN usuarios AS u2 ON m.id_recibido = u2.id ";
		            stmt = conn.createStatement();
					ResultSet rs = stmt.executeQuery( sql );
					vBoxMsg.getChildren().clear();

					while ( rs.next() ) {
						int id_emitido = rs.getInt("id_emitido");
						String  nombre_emitido = rs.getString("nombre_emitido");
						String  nombre_recibido = rs.getString("nombre_recibido");
						String  apellido_emitido = rs.getString("apellidos_emitido");
						String  apellido_recibido = rs.getString("apellidos_recibido");
						String mensaje = rs.getString("mensaje");
						Mensajeria_Model emitido = new Mensajeria_Model(nombre_emitido,apellido_emitido, nombre_recibido, apellido_recibido, mensaje);
						v.add(emitido);	
						
						if (id_emitido!=userLogged.id) {
			            Label bMensajeria = new Label();
			            vBoxMsg.getChildren().add(bMensajeria);
			            bMensajeria.setText(nombre_emitido + " " + apellido_emitido);
			            bMensajeria.setTextFill(Color.WHITE);
			            bMensajeria.setOnMouseClicked(new EventHandler<MouseEvent>() {
			          @Override
			          public void handle(MouseEvent event) {
			           labelMensaje.setText("\n" + emitido.getMensaje());
			                }
			            });
			        }
					}
					rs.close();
					stmt.close();
					conn.close();

		        } catch (SQLException se) {
		            se.printStackTrace();
		        } catch (Exception e) {
		            e.printStackTrace();
		        } finally {
		            try {
		                if (stmt != null) {
		                    conn.close();
		                }
		            } catch (SQLException se) {
		            }
		            try {
		                if (conn != null) {
		                    conn.close();
		                }
		            } catch (SQLException se) {
		                se.printStackTrace();
		            }
			}
	    }
	   
	    @FXML
	    void NuevoMsg(ActionEvent event) {
	    	try {
				FXMLLoader Loader = new FXMLLoader(getClass().getResource("/view/Mensajeria_View.fxml"));

				EnviarMsg controler = new EnviarMsg();

				Loader.setController(controler);

				controler.setUserLogged(userLogged);

				Parent root = Loader.load();

				Stage stage = new Stage();

				stage.setScene(new Scene(root));
				stage.show();
			} catch (Exception e) {
				e.printStackTrace();
			}
	    }
	    
	    @FXML
	    void MsgEnviados(ActionEvent event) {

	    	Connection conn = null;
		    Statement stmt = null;
		    String sql;
		     
		     try {
		    	 Class.forName("org.mariadb.jdbc.Driver");

		            System.out.println("Connecting to a selected database...");

		            conn = DriverManager.getConnection(
		                    "jdbc:mariadb://195.235.211.197\r\n"
		                    + "/prieasyliving", USER, PASS);
		            
		            System.out.println("Connectado a la Base de Datos...");
		            
		            Vector<Mensajeria_Model> v = new Vector<Mensajeria_Model>();
		            sql = "SELECT m.id_emitido, u1.nombre AS nombre_emitido, u1.apellidos AS apellidos_emitido, m.id_recibido, u2.nombre AS nombre_recibido, u2.apellidos AS apellidos_recibido, m.mensaje "
		            		+ "FROM mensajes AS m "
		            		+ "JOIN usuarios AS u1 ON m.id_emitido = u1.id "
		            		+ "JOIN usuarios AS u2 ON m.id_recibido = u2.id ";
		            stmt = conn.createStatement();
					ResultSet rs = stmt.executeQuery( sql );
					vBoxMsg.getChildren().clear();

					while ( rs.next() ) {
						int id_emitido = rs.getInt("id_emitido");
						String  nombre_emitido = rs.getString("nombre_emitido");
						String  nombre_recibido = rs.getString("nombre_recibido");
						String  apellido_emitido = rs.getString("apellidos_emitido");
						String  apellido_recibido = rs.getString("apellidos_recibido");
						String mensaje = rs.getString("mensaje");
						Mensajeria_Model emitido = new Mensajeria_Model(nombre_emitido,apellido_emitido, nombre_recibido, apellido_recibido, mensaje);
						v.add(emitido);	
						
						if (id_emitido==userLogged.id) {
			            Label bMensajeria = new Label();
			            vBoxMsg.getChildren().add(bMensajeria);
			            bMensajeria.setText(nombre_recibido + " " + apellido_recibido);
			            bMensajeria.setTextFill(Color.WHITE);
			            bMensajeria.setOnMouseClicked(new EventHandler<MouseEvent>() {
			          @Override
			          public void handle(MouseEvent event) {
			           labelMensaje.setText("\n" + emitido.getMensaje());
			                }
			            });
			        }
					}
					rs.close();
					stmt.close();
					conn.close();

		        } catch (SQLException se) {
		            se.printStackTrace();
		        } catch (Exception e) {
		            e.printStackTrace();
		        } finally {
		            try {
		                if (stmt != null) {
		                    conn.close();
		                }
		            } catch (SQLException se) {
		            }
		            try {
		                if (conn != null) {
		                    conn.close();
		                }
		            } catch (SQLException se) {
		                se.printStackTrace();
		            }
			}
	    }
	    
	    
	    @FXML
	    void llevaSubMenu(ActionEvent event) {
	    	try {
				FXMLLoader LoaderSubmenu = new FXMLLoader(getClass().getResource("/view/Submenu_View.fxml"));

				Submenu controlerSubmenu = new Submenu();

				LoaderSubmenu.setController(controlerSubmenu);

				controlerSubmenu.setUserLogged(userLogged);

				Parent rootSubmenu = LoaderSubmenu.load();

				Stage stage = new Stage();

				stage.setScene(new Scene(rootSubmenu));
				stage.show();
				Stage stage1 = (Stage) buttonSubMenu.getScene().getWindow();
				stage1.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
	    }

	}


