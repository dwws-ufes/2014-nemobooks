package br.ufes.inf.nemo.controleLivro.cgt;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.ufes.inf.nemo.controleLivro.cdp.Author;
import br.ufes.inf.nemo.controleLivro.cgd.AuthorDAO;
import br.ufes.inf.nemo.util.ejb3.application.CrudServiceBean;
import br.ufes.inf.nemo.util.ejb3.persistence.BaseDAO;

import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.rdf.model.Literal;

@SuppressWarnings("serial")
@Stateless
public class ManageAuthorsServiceBean extends CrudServiceBean<Author> implements ManageAuthorsService{
	
	@EJB
    private AuthorDAO authorDAO;
	
  
	@Override
	public BaseDAO<Author> getDAO() {		
		return authorDAO;
	}

	@Override
	protected Author createNewEntity() {
		return new Author();
	}
	
	@Override
	public Author incluirAutor(Author a) throws Exception {
		authorDAO.save(a);
		return a;
    }

	@Override
    public void alterarAutor(Author a) throws Exception {	
    	authorDAO.save(a);
    }
	
	@Override
    public boolean apagarAutor(Author autor) {
        if(!verificarVinculos(autor.getId()))
        {
        	authorDAO.delete(autor);
			return true;
        }
        else
        {
        	return false;
        }
        	
		 

    }
 
	@Override
	public List<Author> obterAutores() {
		try {
			return authorDAO.retrieveAll();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}


	@Override
	public Author buscaSparqlAutor(String nome) {

		String query = " 	PREFIX foaf: <http://xmlns.com/foaf/0.1/>"
				+ " PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>"
				+ " PREFIX dbpprop: <http://dbpedia.org/property/>"
				+ " SELECT ?Concept ?comment ?name "
				+ " WHERE "
				+ " { "
				+ " ?Concept foaf:name \""+nome+"\" @en . "
				+ " ?Concept rdfs:comment ?comment ."
				+ " ?Concept dbpprop:name ?name . "
				+ " FILTER ( lang(?comment) = \"en\")"
				+ " FILTER ( lang(?name) = \"en\" )" + " }";
	
		QueryExecution queryExecution = QueryExecutionFactory.sparqlService("http://dbpedia.org/sparql", query);
		
		ResultSet results = queryExecution.execSelect();
	
		Author a = null;
		while (results.hasNext()) {
	
			QuerySolution soln = results.next();
	
			Literal name = soln.getLiteral("name");
			Literal comment = soln.getLiteral("comment");

			a = new Author();
			a.setNome(nome);
			a.setNomeReferencia(name.getString());
			a.setResumo(comment.getString());
			
			break;
		}
		
		return a;

	}

	@Override
	public boolean obterAutor(String nome) {
		return authorDAO.obterAutor(nome);
	}
	
	@Override
	public boolean verificarVinculos(Long id)
	{
		return authorDAO.verificarVinculos(id);
	}
		
}
	
	

	

