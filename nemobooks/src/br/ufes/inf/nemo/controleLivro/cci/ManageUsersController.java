package br.ufes.inf.nemo.controleLivro.cci;

import java.io.IOException;
import java.io.Serializable;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import br.ufes.inf.nemo.controleLivro.cdp.Member;
import br.ufes.inf.nemo.controleLivro.cdp.UserType;
import br.ufes.inf.nemo.controleLivro.cdp.Usuario;
import br.ufes.inf.nemo.controleLivro.cgt.ManageMembersService;
import br.ufes.inf.nemo.controleLivro.cgt.ManageUsersService;
import br.ufes.inf.nemo.util.ejb3.application.CrudService;
import br.ufes.inf.nemo.util.ejb3.controller.CrudController;



@SuppressWarnings("serial")
@Named
@SessionScoped
public class ManageUsersController extends CrudController<Usuario> implements Serializable{
	private String login;
    private String senha;
    private Usuario usuario;
    private String mensagem;
    private Member membro;
    private String senhaAtual;
    private String novaSenha;
    private String confirmarSenha;
    private String mensagemAlterar = "NEMO";
    

    @EJB
    private ManageUsersService manageUsersService;
    
    @EJB
    private ManageMembersService manageMembersService;
	
    @Override
	protected CrudService<Usuario> getCrudService() {
		return manageUsersService;
	}

	@Override
	protected Usuario createNewEntity() {
		return new Usuario();
	}

	@Override
	protected void initFilters() {
		// TODO Auto-generated method stub
		
	}
	
	
	

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	public Member getMembro() {
		return membro;
	}

	public void setMembro(Member membro) {
		this.membro = membro;
	}

	public String getSenhaAtual() {
		return senhaAtual;
	}

	public void setSenhaAtual(String senhaAtual) {
		this.senhaAtual = senhaAtual;
	}

	public String getNovaSenha() {
		return novaSenha;
	}

	public void setNovaSenha(String novaSenha) {
		this.novaSenha = novaSenha;
	}

	public String getConfirmarSenha() {
		return confirmarSenha;
	}

	public void setConfirmarSenha(String confirmarSenha) {
		this.confirmarSenha = confirmarSenha;
	}

	public String getMensagemAlterar() {
		return mensagemAlterar;
	}

	public void setMensagemAlterar(String mensagemAlterar) {
		this.mensagemAlterar = mensagemAlterar;
	}
	

	public String logar()
	{
		usuario = manageUsersService.obterUsuario(login, Usuario.gerarHashCode(senha));
		
		if(usuario!=null)
		{
			HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
			HttpSession session = request.getSession();
			session.setAttribute("usuario", usuario);
			mensagem = "";
		
			if(usuario.getTipo().equals(UserType.Administrador))
			{
				this.abrirPaginaLivro();
			}
			else
			{
				membro = manageMembersService.obterMembro(usuario);
				this.abrirPaginaLivrosMembro();
			}
		}
		else
		{
			mensagem = "Login ou senha inválidos";
		}
		return mensagem;
	}
	
	public void abrirPaginaLivro()
	{
		try {

		    FacesContext.getCurrentInstance().getExternalContext().redirect("PagLivro.jsf");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void abrirPaginaMembro()
	{
		try {

		    FacesContext.getCurrentInstance().getExternalContext().redirect("PagMembro.jsf");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void abrirPaginaLivrosMembro()
	{
		try {

		    FacesContext.getCurrentInstance().getExternalContext().redirect("PagLivrosMembro.jsf");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void abrirPaginaAutor()
	{
		try {

		    FacesContext.getCurrentInstance().getExternalContext().redirect("PagAutor.jsf");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void abrirPaginaAlterarSenha()
	{
		try {

		    FacesContext.getCurrentInstance().getExternalContext().redirect("PagAlterarSenha.jsf");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void abrirPaginaHistorico()
	{
		try {

		    FacesContext.getCurrentInstance().getExternalContext().redirect("PagHistorico.jsf");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void refresh()
	{
		try
		{
			FacesContext.getCurrentInstance().getExternalContext().redirect("PagLogin.jsf");
		}
		catch(Exception e)
		{
			
		}
	}
	
	
	public void logout()
	{
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		HttpSession session = request.getSession();
		session.setAttribute("usuario", null);
		refresh();
	}
	
	
	public void iniciar()
	{
		if((manageUsersService.obterUsuario("admin", Usuario.gerarHashCode("abc123")))==null)
		{
			criarAdmin();
		}
	}
	
	public void alterarSenha()
	{
		if(!usuario.getSenha().equals(Usuario.gerarHashCode(senhaAtual)))
		{
			mensagemAlterar = "Senha atual incorreta!";
		}
		else if(usuario.getSenha().equals(Usuario.gerarHashCode(novaSenha)))
		{
			mensagemAlterar = "Nova senha igual a atual!";
		}
		else if(!novaSenha.equals(confirmarSenha))
		{
			mensagemAlterar = "A confirmação não confere!";
		}
		else
		{
			usuario.setSenhaCriptografar(novaSenha);
			manageUsersService.alterarUsuario(usuario);
			mensagemAlterar = "Senha alterada com sucesso!";
		}
		try
		{
			FacesContext.getCurrentInstance().getExternalContext().redirect("PagAlterarSenha.jsf");
		}
		catch(Exception e)
		{
			
		}
	}
	
	public void criarAdmin()
	{
		Usuario u = createNewEntity();
		u.setUsername("admin");
		u.setSenhaCriptografar("abc123");
		u.setTipo(UserType.Administrador);
		manageUsersService.incluirUsuario(u);
	}

	
	
	
	
}
