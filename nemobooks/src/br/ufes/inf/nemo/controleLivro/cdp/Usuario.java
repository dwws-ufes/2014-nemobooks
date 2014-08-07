package br.ufes.inf.nemo.controleLivro.cdp;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import br.ufes.inf.nemo.util.ejb3.persistence.PersistentObject;
import br.ufes.inf.nemo.util.ejb3.persistence.PersistentObjectSupport;

@SuppressWarnings("serial")
@Entity
public class Usuario extends PersistentObjectSupport implements PersistentObject{
	private String username;
	private String senha;
	private Boolean ativo;
	private UserType tipo;
	
	public Usuario()
	{
		ativo = true;
	}
	
	
	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public void setSenhaCriptografar(String senha) {
        this.senha = gerarHashCode(senha);
    }
	
	
	
	public Boolean isAtivo() {
		return ativo;
	}


	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}


	@Enumerated(EnumType.STRING)
	public UserType getTipo() {
		return tipo;
	}


	public void setTipo(UserType tipo) {
		this.tipo = tipo;
	}


	public static String gerarHashCode(String s) {
	        //difine o tipo de hash a ser gerado
	        MessageDigest hash = null;
	        try {
	            hash = MessageDigest.getInstance("MD5");
	        } catch (NoSuchAlgorithmException ex) {
	            Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
	        }
	        //passa os bytes da senha
	        hash.update(s.getBytes());
	        //transforma o hash em bytes gerado para hexadecimal
	        String hashCode = byte2StringHexa(hash.digest());
	        hash.reset();

	        return hashCode;
	    }


		public static String byte2StringHexa(byte[] bytes) {
	        StringBuilder s = new StringBuilder();
	        for (int i = 0; i < bytes.length; i++) {
	            int parteAlta = ((bytes[i] >> 4) & 0xf) << 4;
	            int parteBaixa = bytes[i] & 0xf;
	            if (parteAlta == 0) {
	                s.append('0');
	            }
	            s.append(Integer.toHexString(parteAlta | parteBaixa));
	        }
	        return s.toString();
	    }
	    
	    @Override
	    public String toString() {
	    	return username;
	    }
	
}
