package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.mysql.cj.protocol.Resultset;

public class DAO {
	
	private PreparedStatement pstmt;
	private Statement st;
	private ResultSet rs;
	private Connection conn;
	
	
	/* Módulo de conexão */
	private String driver = "com.mysql.cj.jdbc.Driver";
	private String url = "jdbc:mysql://127.0.0.1:3306/db_agenda?"
			+ "useTimezone=true&serverTimezone=UTC";
	private String user = "root";
	private String password = "";
	
	/* Método de conexão */
	private Connection conectar() {
		Connection conexao = null;
		try {
			Class.forName(driver);
			conexao = DriverManager.getConnection(url, user, password);
			return conexao;
		}catch(Exception e) {
			System.out.println(e);
			return null;
		}
		
	}
	// Metodo inserir Contatos
	public void inserir(JavaBeans contato) {
		String sql = "INSERT INTO contatos (nome, telefone,email) VALUES (?,?,?)";
			try {
				conn = conectar();
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, contato.getNome());
				pstmt.setString(2, contato.getFone());
				pstmt.setString(3, contato.getEmail());
				pstmt.execute();
				conn.close();
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}	
	}
	//Metodo Listar Contatos
	public List<JavaBeans> listarContatos(){
		List<JavaBeans> lista = new ArrayList<>();
		String sql = "SELECT * FROM contatos ORDER BY nome";
		try {
			conn = conectar();
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()) {
				JavaBeans contato = new JavaBeans();
				contato.setIdCod(rs.getInt("id_contat"));
				contato.setNome(rs.getString("nome"));
				contato.setFone(rs.getString("telefone"));
				contato.setEmail(rs.getString("email"));
				lista.add(contato);
			}
			conn.close();
			return lista;
			
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	//encontrar por id
	public JavaBeans encontarPorId(JavaBeans contato) {
		String sql = "SELECT * FROM contatos WHERE id_contat = ?";
		try {
			conn = conectar();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, contato.getIdCod());
			rs = pstmt.executeQuery();
			if(rs.next()) {
				JavaBeans cont = new JavaBeans();
				cont.setIdCod(rs.getInt("id_contat"));
				cont.setNome(rs.getString("nome"));
				cont.setFone(rs.getString("telefone"));
				cont.setEmail(rs.getString("email"));
				
				return cont;
			}
			else {
				return null;
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	//Metodo Editar Contatos
	public void editarContato(JavaBeans contato) {
		String sql = "UPDATE contatos SET nome=?, telefone=?, email=? WHERE id_contat=?";
		try {
			conn = conectar();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, contato.getNome());
			pstmt.setString(2, contato.getFone());
			pstmt.setString(3, contato.getEmail());
			pstmt.setInt(4, contato.getIdCod());
			pstmt.execute();
			conn.close();
			
		}  catch (SQLException e) {
			throw new RuntimeException(e);
		}		
	}
	//Metodo Excluir Contato
	public void deletarContato(JavaBeans contato) {
		String sql = "DELETE FROM contatos WHERE id_contat = ?";
		try {
			conn = conectar();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, contato.getIdCod());
			pstmt.execute();
			conn.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}	
		
	}
	
	/*	public static void main(String[] args) {
			JavaBeans cont = new JavaBeans();
			DAO dao = new DAO();
			//listar os contatos
			List<JavaBeans> lista = dao.listarContatos();
			for(JavaBeans contato: lista) {
				System.out.println(contato);
			}
			//inserir um contato
			cont.setNome("Douglas");
			cont.setFone("(85)997959857");
			cont.setEmail("douglas.cauan@aluno.senai-ce.org.br");
			dao.inserir(cont);
			System.out.println("contato inserido");

			//editar
			cont.setIdCod(2);
			cont.setNome("Jucy");
			cont.setFone("(85)997998899");
			cont.setEmail("jucy.medeiros@professora.senai-ce.org.br");
			dao.editarContato(cont);
			System.out.println("contato editado");
			
			//excluir um contato
			cont.setIdCod(3);
			dao.deletarContato(cont);
			System.out.println("contato excluido");
			
			//listar os contato
			for(JavaBeans contato: lista) {
				System.out.println(contato);
			}
			
		}
		public void testeConexao() {
		try {
			Connection con = conectar();
			if(con!=null) {
			System.out.println("deu certo");
			}
			con.close();
			
		}catch(Exception e) {
			System.out.println(e);
		}
	}*/
	
	
}