package br.ufes.inf.nemo.controleLivro.cci;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import br.ufes.inf.nemo.controleLivro.cdp.Author;
import br.ufes.inf.nemo.controleLivro.cdp.Book;
import br.ufes.inf.nemo.controleLivro.cdp.UserType;
import br.ufes.inf.nemo.controleLivro.cdp.Usuario;
import br.ufes.inf.nemo.controleLivro.cgt.ManageAuthorsService;
import br.ufes.inf.nemo.controleLivro.cgt.ManageBooksService;
import br.ufes.inf.nemo.util.ejb3.application.CrudService;
import br.ufes.inf.nemo.util.ejb3.controller.CrudController;

@SuppressWarnings("serial")
@Named
@SessionScoped
public class ManageBooksController extends CrudController<Book> implements Serializable{
	private List<Book> livros;
	private List<Author> autores;
	private List<Long> autoresSelecionados;
	private int indiceLivro;
	private int indiceAutor;
	private Book livroConsulta;
    private Book livroEditado = createNewEntity();
    private Book livroCriado = createNewEntity();
    private Author autorConsulta;
    private String isbnBusca;
    private int page = 1;
    private int pageAutor = 1;
    private int pageAutorSelecionado = 1;
    private String mensagem= "NEMO";

    @EJB
    private ManageBooksService manageBooksService;
    
    @EJB
    private ManageAuthorsService manageAuthorsService;
		
		
	
    @Override
	protected CrudService<Book> getCrudService() {
		return manageBooksService;
	}

	@Override
	protected Book createNewEntity() {
		return new Book();
	}

	@Override
	protected void initFilters() {
		// TODO Auto-generated method stub
		
	}
	
	public String getIsbnBusca() {
		return isbnBusca;
	}

	public void setIsbnBusca(String isbnBusca) {
		this.isbnBusca = isbnBusca;
	}

	public List<Book> getLivros() {
		return livros;
	}

	public void setLivros(List<Book> livros) {
		this.livros = livros;
	}

	public int getIndiceLivro() {
		return indiceLivro;
	}

	public void setIndiceLivro(int indiceLivro) {
		this.indiceLivro = indiceLivro;
	}

	public Book getLivroConsulta() {
		return livroConsulta;
	}

	public void setLivroConsulta(Book livroConsulta) {
		this.livroConsulta = livroConsulta;
	}

	public Book getLivroEditado() {
		return livroEditado;
	}

	public void setLivroEditado(Book livroEditado) {
		this.livroEditado = livroEditado;
	}

	public Book getLivroCriado() {
		return livroCriado;
	}

	public void setLivroCriado(Book livroCriado) {
		this.livroCriado = livroCriado;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
	
	

	public List<Author> getAutores() {
		return autores;
	}

	public void setAutores(List<Author> autores) {
		this.autores = autores;
	}

	
	public List<Long> getAutoresSelecionados() {
		return autoresSelecionados;
	}

	public void setAutoresSelecionados(List<Long> autoresSelecionados) {
		this.autoresSelecionados = autoresSelecionados;
	}

	public Author getAutorConsulta() {
		return autorConsulta;
	}

	public void setAutorConsulta(Author autorConsulta) {
		this.autorConsulta = autorConsulta;
	}

	public int getIndiceAutor() {
		return indiceAutor;
	}

	public void setIndiceAutor(int indiceAutor) {
		this.indiceAutor = indiceAutor;
	}

	public int getPageAutor() {
		return pageAutor;
	}

	public void setPageAutor(int pageAutor) {
		this.pageAutor = pageAutor;
	}
	

	public int getPageAutorSelecionado() {
		return pageAutorSelecionado;
	}

	public void setPageAutorSelecionado(int pageAutorSelecionado) {
		this.pageAutorSelecionado = pageAutorSelecionado;
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
			else if(!u.getTipo().equals(UserType.Administrador))
			{
				FacesContext.getCurrentInstance().getExternalContext().redirect("PagLogin.jsf");
			}
			else
			{
				livroCriado = createNewEntity();
				livroEditado = createNewEntity();
				livros = manageBooksService.obterLivros();
				autores = manageAuthorsService.obterAutores();
				autoresSelecionados = new ArrayList<Long>();
			}
			
			

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void incluirLivro()
	{
		mensagem ="";
		try
		{
			if(!manageBooksService.obterLivro(livroCriado.getIsbn()) && !autoresSelecionados.isEmpty())
			{
				livros.add(manageBooksService.incluirLivro(livroCriado, autoresSelecionados));
				
				livroCriado = createNewEntity();
				mensagem = "Livro Cadastrado com sucesso!";
			}
			else if(autoresSelecionados.isEmpty())
			{
				mensagem = "É necessário selecionar pelo menos 1 autor!";
			}
			else
			{
				mensagem = "ISBN já cadastrado!";
			}
		}
		catch(Exception e)
		{
			mensagem = "Erro ao cadastrar o livro!";
		}
		atualizar();
	}
	
	public void apagarLivro() {
		mensagem ="";
		if(livros.get(indiceLivro).getEmprestado())
		{
			mensagem = "O livro não pode ser apagado, ele está emprestado!";
		}
		else
		{
			manageBooksService.apagarLivro(livros.get(indiceLivro));
			livros.remove(livros.get(indiceLivro));
			mensagem = "Livro excluído com sucesso!";
		}
		atualizar();
		
	}
    
 
    public void alterarLivro() {
    	mensagem ="";
        try
        {
        	if((livroEditado.getAutores().size()>0) && (!manageBooksService.obterLivro(livroEditado.getIsbn(), livroEditado.getId())))
        	{
        		manageBooksService.alterarLivro(livroEditado);
            	livros.set(indiceLivro, livroEditado);
    			mensagem = "Livro alterado com sucesso!";
        	}
        	else if(livroEditado.getAutores().size()==0)
        	{
        		mensagem = "O livro precisa ter pelo menos 1 autor!";
        	}
        	else
        	{
        		mensagem = "ISBN já cadastrado!";
        	}
        	
        }
		catch(Exception e)
		{
			mensagem = "Erro ao alterar o livro!";
			
		}
        atualizar();  
    }
    
    public void atualizarListaAutores(Book l)
    {
    	autores = manageAuthorsService.obterAutores();
    	for (Author a : l.getAutores()) {
			autores.remove(a);
		}
    }
    
    public void atualizarListaEsq(int index)
    {
    	livroEditado.getAutores().add(autores.remove(index));
    }
    
    public void atualizarListaDir(int index)
    {
    	autores.add(livroEditado.getAutores().remove(index));
    }
    
 
    public void atualizar()
    {
    	try
    	{
    		FacesContext.getCurrentInstance().getExternalContext().redirect("PagLivro.jsf");
    	}
    	catch(Exception e)
    	{
    		
    	}
    }

}
