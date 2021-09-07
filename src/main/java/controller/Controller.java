package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import model.DAO;
import model.JavaBeans;

/**
 * Servlet implementation class Controller
 */
@WebServlet(urlPatterns = { "/Controller", "/main", "/insert", "/select", "/delete","/report" })
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DAO dao = new DAO();
	JavaBeans contato = new JavaBeans();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Controller() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		// dao.testeConexao();
		String action = request.getServletPath();
		System.out.println(action);
		if (action.equals("/main")) {
			listacontato(request, response);
		} else if (action.equals("/insert")) {
			novoContato(request, response);
		} else if (action.equals("/select")) {
			editarContato(request, response);
		} else if (action.equals("/delete")) {
			removerContato(request, response);
		} else if (action.equals("/report")) {
			relatorioContato(request, response);
		} else {
			response.sendRedirect("index.html");
		}
	}

	protected void novoContato(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		contato.setIdCod(Integer.parseInt(request.getParameter("idcon")));
		contato.setNome(request.getParameter("nome"));
		contato.setFone(request.getParameter("telefone"));
		contato.setEmail(request.getParameter("email"));

		if (contato.getIdCod() == 0) {
			dao.inserir(contato);
		} else {
			dao.editarContato(contato);
		}
		response.sendRedirect("agenda.jsp");
	}

	protected void listacontato(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<JavaBeans> lista = dao.listarContatos();
		request.setAttribute("contatos", lista);
		RequestDispatcher rd = request.getRequestDispatcher("agenda.jsp");
		rd.forward(request, response);

	}

	protected void editarContato(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int idcon = Integer.parseInt(request.getParameter("idcon"));
		contato.setIdCod(idcon);
		JavaBeans contat = new JavaBeans();
		contat = dao.encontarPorId(contato);
		request.setAttribute("idcon", contat.getIdCod());
		request.setAttribute("nome", contat.getNome());
		request.setAttribute("telefone", contat.getFone());
		request.setAttribute("email", contat.getEmail());

		RequestDispatcher rd = request.getRequestDispatcher("formulario.jsp");
		rd.forward(request, response);
	}

	protected void removerContato(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int idcon = Integer.parseInt(request.getParameter("idcon"));
		contato.setIdCod(idcon);
		dao.deletarContato(contato);
		response.sendRedirect("main");
	}
	
	protected void relatorioContato(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Document documento = new Document();
		try {
			//tipo do documento
			response.setContentType("apllication/pdf");
			//nome do documento
			response.addHeader("Content-Disposition", "inline; filename="+"contatos.pdf");
			//criar documento
			PdfWriter.getInstance(documento, response.getOutputStream());
			//abrir documento para gerar o conteudo
			documento.open();
			documento.add(new Paragraph("Lista de Contatos:"));
			documento.add(new Paragraph(" "));
			//criando a tabela
			PdfPTable tabela = new PdfPTable(3);
			PdfPCell col1 = new PdfPCell(new Paragraph("Nome"));
			PdfPCell col2 = new PdfPCell(new Paragraph("Telefone"));
			PdfPCell col3 = new PdfPCell(new Paragraph("Email"));
			tabela.addCell(col1);
			tabela.addCell(col2);
			tabela.addCell(col3);
			documento.add(tabela);
			documento.close();
		} catch (Exception e) {
			System.out.println(e);
			documento.close();
		}
	}
}
