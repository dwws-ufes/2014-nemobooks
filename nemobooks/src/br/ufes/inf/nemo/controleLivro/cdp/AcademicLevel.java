package br.ufes.inf.nemo.controleLivro.cdp;

public enum AcademicLevel {
	EnsinoSuperiorIncompleto, EnsinoSuperiorCompleto, Mestrando, Mestre, Doutorando, Doutor,PosDoutorando, PosDoutor; 

	public String toString() {
		if(this.equals(EnsinoSuperiorIncompleto)) return "Ensino Superior Incompleto";
		else if(this.equals(EnsinoSuperiorCompleto)) return "Ensino Superior Completo";
		else if(this.equals(PosDoutor)) return "P�s Doutor";
		else if(this.equals(PosDoutorando)) return "P�s Doutorando";
		else return this.name();
	};
}

