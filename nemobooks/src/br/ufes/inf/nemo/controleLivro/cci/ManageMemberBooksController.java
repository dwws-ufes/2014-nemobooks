package br.ufes.inf.nemo.controleLivro.cci;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import br.ufes.inf.nemo.controleLivro.cdp.Author;
import br.ufes.inf.nemo.controleLivro.cdp.Book;
import br.ufes.inf.nemo.controleLivro.cdp.Emprestimo;
import br.ufes.inf.nemo.controleLivro.cdp.Member;
import br.ufes.inf.nemo.controleLivro.cdp.UserType;
import br.ufes.inf.nemo.controleLivro.cdp.Usuario;
import br.ufes.inf.nemo.controleLivro.cgt.ManageBooksService;
import br.ufes.inf.nemo.controleLivro.cgt.ManageEmprestimosService;
import br.ufes.inf.nemo.controleLivro.cgt.ManageMembersService;
import br.ufes.inf.nemo.util.ejb3.application.CrudService;
import br.ufes.inf.nemo.util.ejb3.controller.CrudController;

@SuppressWarnings("serial")
@Named
@SessionScoped
public class ManageMemberBooksController extends CrudController<Emprestimo> implements Serializable{
    private Member membro;
    private Book livroConsulta;
    private Author autorConsulta;
    private List<Book> livrosCadastrados;
    private List<Emprestimo> emprestimos;
    private int indiceDevolucao;
    private int indiceEmprestimo;
	private int page1 = 1;
	private int page2 = 1;
	private int pageAutor = 1;
    private String mensagem= "NEMO";

    @EJB
    private ManageMembersService manageMembersService;
    
    @EJB
    private ManageBooksService manageBooksService;
    
    @EJB
    private ManageEmprestimosService manageEmprestimosService;
		
	
    @Override
	protected CrudService<Emprestimo> getCrudService() {
		return manageEmprestimosService;
	}

	@Override
	protected Emprestimo createNewEntity() {
		return new Emprestimo();
	}

	@Override
	protected void initFilters() {
		// TODO Auto-generated method stub
		
	}

	public int getIndiceDevolucao() {
		return indiceDevolucao;
	}

	public void setIndiceDevolucao(int indiceDevolucao) {
		this.indiceDevolucao = indiceDevolucao;
	}

	public int getIndiceEmprestimo() {
		return indiceEmprestimo;
	}

	public void setIndiceEmprestimo(int indiceEmprestimo) {
		this.indiceEmprestimo = indiceEmprestimo;
	}

	public int getPage1() {
		return page1;
	}

	public void setPage1(int page1) {
		this.page1 = page1;
	}

	public int getPage2() {
		return page2;
	}

	public void setPage2(int page2) {
		this.page2 = page2;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
	public Member getMembro() {
		return membro;
	}

	public void setMembro(Member membro) {
		this.membro = membro;
	}
	

	public Book getLivroConsulta() {
		return livroConsulta;
	}

	public void setLivroConsulta(Book livroConsulta) {
		this.livroConsulta = livroConsulta;
	}
	

	public List<Book> getLivrosCadastrados() {
		return livrosCadastrados;
	}

	public void setLivrosCadastrados(List<Book> livrosCadastrados) {
		this.livrosCadastrados = livrosCadastrados;
	}

	public Author getAutorConsulta() {
		return autorConsulta;
	}

	public void setAutorConsulta(Author autorConsulta) {
		this.autorConsulta = autorConsulta;
	}
	public List<Emprestimo> getEmprestimos() {
		return emprestimos;
	}

	public void setEmprestimos(List<Emprestimo> emprestimos) {
		this.emprestimos = emprestimos;
	}

	public int getPageAutor() {
		return pageAutor;
	}

	public void setPageAutor(int pageAutor) {
		this.pageAutor = pageAutor;
	}

	public void iniciar()
	{
		try
		{
			HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
			HttpSession session = request.getSession();
			Usuario u = (Usuario) session.getAttribute("usuario");
			if(u==null)
			{
				FacesContext.getCurrentInstance().getExternalContext().redirect("PagLogin.jsf");
			}
			else if(!u.getTipo().equals(UserType.Membro_Nemo))
			{
				FacesContext.getCurrentInstance().getExternalContext().redirect("PagLogin.jsf");
			}
			else
			{
				membro = manageMembersService.obterMembro(u);
				emprestimos = manageEmprestimosService.obterEmprestimos(membro, true);
				livrosCadastrados = manageBooksService.obterLivros(false);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
    
    public void pegarLivroEmprestado()
    {
    	emprestimos.add(manageEmprestimosService.pegarLivroEmprestado(livrosCadastrados.get(indiceEmprestimo),membro));
    	livrosCadastrados.remove(indiceEmprestimo);
    	mensagem = "Livro pego com sucesso!";
    }
    
    public void devolverLivro()
    {
    	Emprestimo e = manageEmprestimosService.devolverLivro(emprestimos.get(indiceDevolucao));
    	livrosCadastrados.add(e.getLivro());
    	emprestimos.remove(indiceDevolucao);
    	mensagem = "Livro devolvido com sucesso!";
    }
    
 
    public void atualizar()
    {
    	try
    	{
    		FacesContext.getCurrentInstance().getExternalContext().redirect("PagLivrosMembro.jsf");
    	}
    	catch(Exception e)
    	{
    		
    	}
    }

}
