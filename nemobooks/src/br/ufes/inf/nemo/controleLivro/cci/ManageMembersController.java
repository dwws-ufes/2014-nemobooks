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

import br.ufes.inf.nemo.controleLivro.cdp.AcademicLevel;
import br.ufes.inf.nemo.controleLivro.cdp.Member;
import br.ufes.inf.nemo.controleLivro.cdp.UserType;
import br.ufes.inf.nemo.controleLivro.cdp.Usuario;
import br.ufes.inf.nemo.controleLivro.cgt.ManageMembersService;
import br.ufes.inf.nemo.util.ejb3.application.CrudService;
import br.ufes.inf.nemo.util.ejb3.controller.CrudController;

@SuppressWarnings("serial")
@Named
@SessionScoped
public class ManageMembersController extends CrudController<Member> implements Serializable{
	private List<Member> membros = new ArrayList<Member>();
	private int indiceMembro;
	private Member membroConsulta;
    private Member membroEditado = createNewEntity();
    private Member membroCriado = createNewEntity();
    private int page = 1;
    private String mensagem= "NEMO";
    private String tipoNivel;
    private AcademicLevel tipos[] = new AcademicLevel[8];

	
    @EJB
    private ManageMembersService manageMembersService;
	
    @Override
	protected CrudService<Member> getCrudService() {
		return manageMembersService;
	}

	@Override
	protected Member createNewEntity() {
		return new Member();
	}

	@Override
	protected void initFilters() {
		// TODO Auto-generated method stub
		
	}
	

	public List<Member> getMembros() {
		return membros;
	}

	public void setMembros(List<Member> membros) {
		this.membros = membros;
	}

	public int getIndiceMembro() {
		return indiceMembro;
	}

	public void setIndiceMembro(int indiceMembro) {
		this.indiceMembro = indiceMembro;
	}

	public Member getMembroConsulta() {
		return membroConsulta;
	}

	public void setMembroConsulta(Member membroConsulta) {
		this.membroConsulta = membroConsulta;
	}

	public Member getMembroEditado() {
		return membroEditado;
	}

	public void setMembroEditado(Member membroEditado) {
		this.membroEditado = membroEditado;
	}

	public Member getMembroCriado() {
		return membroCriado;
	}

	public void setMembroCriado(Member membroCriado) {
		this.membroCriado = membroCriado;
	}

	public String getTipoNivel() {
		return tipoNivel;
	}

	public void setTipoNivel(String tipoNivel) {
		this.tipoNivel = tipoNivel;
	}

	

	public AcademicLevel[] getTipos() {
		return tipos;
	}

	public void setTipos(AcademicLevel[] tipos) {
		this.tipos = tipos;
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
				membroCriado = createNewEntity();
				membroEditado = createNewEntity();
				membros = manageMembersService.obterMembros();
				tipos = AcademicLevel.values();
			}
			
			

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void incluirMembro()
	{
		mensagem ="";
		try
		{
			if(!manageMembersService.obterMembro(membroCriado.getMatricula_Siape()))
			{
				membroCriado.setNivel(AcademicLevel.valueOf(tipoNivel));

				membros.add(manageMembersService.incluirMembro(membroCriado));
				
				membroCriado = createNewEntity();
				mensagem = "Membro Cadastrado com sucesso!";
			}
			else
			{
				mensagem = "Membro já cadastrado! A Matricula/SIAPE já consta no sistema";
			}
		}
		catch(Exception e)
		{
			mensagem = "Erro ao cadastrar o membro!";
		}
		atualizar();
	}
	
	public void apagarMembro() {
		mensagem ="";
		
		if(manageMembersService.apagarMembro(membros.get(indiceMembro)))
		{
			membros.remove(membros.get(indiceMembro));
			mensagem = "Membro excluído com sucesso!";
		}
		else
		{
			mensagem = "O membro não pode ser apagado, existem empréstimos ativos!";
		}
		
		atualizar();
	}
    
 
    public void alterarMembro() {
    	mensagem ="";
        try
        {
        	if(!manageMembersService.obterMembro(membroEditado.getMatricula_Siape(), membroEditado.getId()))
        	{
	        	membroEditado.setNivel(AcademicLevel.valueOf(tipoNivel));
	        	manageMembersService.alterarMembro(membroEditado);
	        	membros.set(indiceMembro, membroEditado);
				mensagem = "Membro alterado com sucesso!";
        	}
        	else
        	{
        		mensagem = "Matricula/SIAPE já foi cadastrada!";
        	}
        }
        
		catch(Exception e)
		{
			mensagem = "Erro ao alterar o membro!";
			
		}
        atualizar();
    }
    
    public void atualizar()
    {
    	try
    	{
    		FacesContext.getCurrentInstance().getExternalContext().redirect("PagMembro.jsf");
    	}
    	catch(Exception e)
    	{
    		
    	}
    }
    
    
    


}
