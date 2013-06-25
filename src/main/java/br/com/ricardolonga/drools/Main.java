package br.com.ricardolonga.drools;

import br.com.ricardolonga.drools.domain.Pessoa;
import br.com.ricardolonga.drools.domain.Pessoa.EstadoCivil;
import br.com.ricardolonga.drools.domain.Pessoa.Sexo;

/**
 * 
 * A regra de negócio externalizada pelo Drools encontra-se no método pegar(Pessoa) da classe Pessoa.
 * Observe que no primeiro teste o custo é maior pois é o momento onde o Drools lê os arquivos .drl e .bpmn e carrega a base de conhecimento, 
 * que será reaproveitada nos outros dois testes.
 * 
 * Qualquer dúvida é só entrar em contato: ricardo.longa@gmail.com / @ricardolonga / www.ricardolonga.com.br
 * 
 * @author Ricardo Longa
 *
 */
public class Main {

	public static void main(String[] args) {
		// ========== //
		// FIRST TEST //
		// ========== //

		Pessoa ricardo = new Pessoa(Sexo.MASCULINO);

		ricardo.setNome("Ricardo");
		ricardo.setIdade(26);
		ricardo.setBonito(false);
		ricardo.setRico(true);
		ricardo.setTemCarro(true);

		Pessoa angelinaJolie = new Pessoa(Sexo.FEMININO);

		angelinaJolie.setNome("Angelina Jolie");
		angelinaJolie.setEstadoCivil(EstadoCivil.NAMORANDO);
		angelinaJolie.setBebado(true);

		long tempoInicial = System.currentTimeMillis(); 
		ricardo.pegar(angelinaJolie);
		long tempoFinal = System.currentTimeMillis(); 
		System.out.printf("%.3f ms%n", (tempoFinal - tempoInicial) / 1000d);
		System.out.println();

		// =========== //
		// SECOND TEST //
		// =========== //

		Pessoa joao = new Pessoa(Sexo.MASCULINO);

		joao.setNome("João");
		joao.setIdade(22);
		joao.setBonito(false);
		joao.setRico(true);
		joao.setTemCarro(false);

		Pessoa ellenRoche = new Pessoa(Sexo.FEMININO);

		ellenRoche.setNome("Ellen Roche");
		ellenRoche.setEstadoCivil(EstadoCivil.SOLTEIRO);
		ellenRoche.setBebado(true);

		tempoInicial = System.currentTimeMillis(); 
		joao.pegar(ellenRoche);
		tempoFinal = System.currentTimeMillis(); 
		System.out.printf("%.3f ms%n", (tempoFinal - tempoInicial) / 1000d);
		System.out.println();
		
		// ========== //
		// THIRD TEST //
		// ========== //

		Pessoa carlos = new Pessoa(Sexo.MASCULINO);

		carlos.setNome("Carlos");
		carlos.setIdade(24);
		carlos.setBonito(true);
		carlos.setRico(true);
		carlos.setTemCarro(true);

		Pessoa julianaPaes = new Pessoa(Sexo.FEMININO);

		julianaPaes.setNome("Juliana Paes");
		julianaPaes.setEstadoCivil(EstadoCivil.NAMORANDO);
		julianaPaes.setBebado(false);

		tempoInicial = System.currentTimeMillis(); 
		carlos.pegar(julianaPaes);
		tempoFinal = System.currentTimeMillis(); 
		System.out.printf("%.3f ms%n", (tempoFinal - tempoInicial) / 1000d);
	}

}
