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
import br.ufes.inf.nemo.controleLivro.cdp.UserType;
import br.ufes.inf.nemo.controleLivro.cdp.Usuario;
import br.ufes.inf.nemo.controleLivro.cgt.ManageAuthorsService;
import br.ufes.inf.nemo.util.ejb3.application.CrudService;
import br.ufes.inf.nemo.util.ejb3.controller.CrudController;

@SuppressWarnings("serial")
@Named
@SessionScoped
public class ManageAuthorsController extends CrudController<Author> implements Serializable{
	private List<Author> autores = new ArrayList<Author>();
	private int indiceAutor;
	private Author autorConsulta;
    private Author autorEditado = new Author();
    private Author autorCriado = new Author();
    private int page = 1;
    private String mensagem= "NEMO";
    private String nomeConsulta;
    private String mensagemBusca = "É necessário inserir apenas o nome do autor para busca automática.";

	
    @EJB
    private ManageAuthorsService manageAuthorsService;
	
    @Override
	protected CrudService<Author> getCrudService() {
		return manageAuthorsService;
	}

	@Override
	protected Author createNewEntity() {
		return new Author();
	}

	@Override
	protected void initFilters() {
		// TODO Auto-generated method stub
		
	}
	

	

	public String getMensagemBusca() {
		return mensagemBusca;
	}

	public void setMensagemBusca(String mensagemBusca) {
		this.mensagemBusca = mensagemBusca;
	}

	public List<Author> getAutores() {
		return autores;
	}

	public void setAutores(List<Author> autores) {
		this.autores = autores;
	}

	public int getIndiceAutor() {
		return indiceAutor;
	}

	public void setIndiceAutor(int indiceAutor) {
		this.indiceAutor = indiceAutor;
	}

	public Author getAutorConsulta() {
		return autorConsulta;
	}

	public void setAutorConsulta(Author autorConsulta) {
		this.autorConsulta = autorConsulta;
	}

	public Author getAutorEditado() {
		return autorEditado;
	}

	public void setAutorEditado(Author autorEditado) {
		this.autorEditado = autorEditado;
	}

	public Author getAutorCriado() {
		return autorCriado;
	}

	public void setAutorCriado(Author autorCriado) {
		this.autorCriado = autorCriado;
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

	public String getNomeConsulta() {
		return nomeConsulta;
	}

	public void setNomeConsulta(String nomeConsulta) {
		this.nomeConsulta = nomeConsulta;
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
				autorCriado = createNewEntity();
				autorEditado = createNewEntity();
				autores = manageAuthorsService.obterAutores();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void incluirAutor()
	{
		mensagem ="";
		try
		{
			if(!manageAuthorsService.obterAutor(autorCriado.getNome()))
			{
	
				autores.add(manageAuthorsService.incluirAutor(autorCriado));
				
				autorCriado = createNewEntity();
				mensagem = "Autor Cadastrado com sucesso!";
			}
			else
			{
				mensagem = "Autor já cadastrado! O nome já consta no sistema";
			}
			
		}
		catch(Exception e)
		{
			mensagem = "Erro ao cadastrar o autor!";
			e.printStackTrace();
		}
		atualizar();
	}
	
	public void apagarAutor() {
		mensagem ="";
		if(manageAuthorsService.apagarAutor(autores.get(indiceAutor)))
		{
			autores.remove(autores.get(indiceAutor));
			mensagem = "Autor excluído com sucesso!";
		}
		else
		{
			mensagem = "O autor não pode ser apagado, ele já está cadastrado em um livro!";
		}
		
		atualizar();
		
	}
    
 
    public void alterarAutor() {
    	mensagem ="";
        try
        {
        	manageAuthorsService.alterarAutor(autorEditado);
        	autores.set(indiceAutor, autorEditado);
			mensagem = "Autor alterado com sucesso!";
        }
        
		catch(Exception e)
		{
			mensagem = "Erro ao alterar o autor!";
			
		}
        
        atualizar();
        
    }
    
    public void buscaSparqlAutor()
    {
    	Author a = manageAuthorsService.buscaSparqlAutor(autorCriado.getNome());
    	if(a==null)
    	{
    		mensagemBusca = "Autor não encontrado, efetue o cadastro manual!";
    	}
    	else
    	{
    		autorCriado.setNomeReferencia(a.getNomeReferencia());
    		autorCriado.setResumo(a.getResumo());
    		mensagemBusca = "Autor encontrado, os dados foram preenchidos!";
    	}
    	
    }
    
    public void atualizar()
    {
    	try
    	{
    		FacesContext.getCurrentInstance().getExternalContext().redirect("PagAutor.jsf");
    	}
    	catch(Exception e)
    	{
    		
    	}
    }
    
    
    


}
