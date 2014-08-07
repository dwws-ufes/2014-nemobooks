package br.ufes.inf.nemo.controleLivro.cci;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import br.ufes.inf.nemo.controleLivro.cdp.Emprestimo;
import br.ufes.inf.nemo.controleLivro.cdp.Member;
import br.ufes.inf.nemo.controleLivro.cdp.UserType;
import br.ufes.inf.nemo.controleLivro.cdp.Usuario;
import br.ufes.inf.nemo.controleLivro.cgt.ManageEmprestimosService;
import br.ufes.inf.nemo.controleLivro.cgt.ManageMembersService;
import br.ufes.inf.nemo.util.ejb3.application.CrudService;
import br.ufes.inf.nemo.util.ejb3.controller.CrudController;

@SuppressWarnings("serial")
@Named
@SessionScoped
public class ManageHistoricoController extends CrudController<Emprestimo> implements Serializable{
    private Member membro;
    private List<Emprestimo> historico;
	private int page = 1;

    @EJB
    private ManageEmprestimosService manageEmprestimosService;
    
    @EJB
    private ManageMembersService manageMembersService;
		
	
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
	
	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public Member getMembro() {
		return membro;
	}

	public void setMembro(Member membro) {
		this.membro = membro;
	}
	
	public List<Emprestimo> getHistorico() {
		return historico;
	}

	public void setHistorico(List<Emprestimo> historico) {
		this.historico = historico;
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
				historico = manageEmprestimosService.obterEmprestimos(membro, false);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
