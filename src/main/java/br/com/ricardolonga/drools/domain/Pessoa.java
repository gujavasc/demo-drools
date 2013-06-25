package br.com.ricardolonga.drools.domain;

import org.drools.KnowledgeBase;
import org.drools.runtime.StatefulKnowledgeSession;

import br.com.ricardolonga.drools.infra.KBaseCache;

public class Pessoa {

	private static final String REGRAS_PEGAR_PESSOA = "pegarpessoa";

	public enum Sexo { MASCULINO, FEMININO }
	public enum EstadoCivil { SOLTEIRO, NAMORANDO }

	private Sexo sexo;
	private String nome;
	private int idade;
	private EstadoCivil estadoCivil;
	private boolean bonito;
	private boolean bebado;
	private boolean rico;
	private boolean temCarro;

	public Pessoa(Sexo sexo) {
		this.sexo = sexo;
	}

	public Sexo getSexo() {
		return this.sexo;
	}

	public void setSexo(Sexo sexo) {
		this.sexo = sexo;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getIdade() {
		return this.idade;
	}

	public void setIdade(int idade) {
		this.idade = idade;
	}

	public EstadoCivil getEstadoCivil() {
		return this.estadoCivil;
	}

	public void setEstadoCivil(EstadoCivil estadoCivil) {
		this.estadoCivil = estadoCivil;
	}

	public boolean isBonito() {
		return this.bonito;
	}

	public void setBonito(boolean bonito) {
		this.bonito = bonito;
	}

	public boolean isBebado() {
		return this.bebado;
	}

	public void setBebado(boolean bebado) {
		this.bebado = bebado;
	}

	public boolean isRico() {
		return this.rico;
	}

	public void setRico(boolean rico) {
		this.rico = rico;
	}

	public boolean isTemCarro() {
		return this.temCarro;
	}

	public void setTemCarro(boolean temCarro) {
		this.temCarro = temCarro;
	}

	// ================ //
	// BUSINESS METHODS //
	// ================ //

	public void pegar(Pessoa outraPessoa) {
        KnowledgeBase kbase = KBaseCache.getInstance().getBaseConhecimento(REGRAS_PEGAR_PESSOA);
        StatefulKnowledgeSession ksession = kbase.newStatefulKnowledgeSession();
        
        // Inserindo os fatos na working memory.
        ksession.insert(this);
        ksession.insert(outraPessoa);
        
        // Prepara as regras do fluxo.
        ksession.startProcess("pegarPessoaId");
        
        // Dispara as regras.
        ksession.fireAllRules();
        
        // Libera os fatos da working memory.
        ksession.dispose();
	}

}
