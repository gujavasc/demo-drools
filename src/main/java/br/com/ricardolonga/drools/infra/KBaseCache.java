package br.com.ricardolonga.drools.infra;

import java.util.HashMap;
import java.util.Map;

import org.drools.KnowledgeBase;
import org.drools.KnowledgeBaseFactory;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderError;
import org.drools.builder.KnowledgeBuilderErrors;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.io.ResourceFactory;

public class KBaseCache {

	private Map<String, KnowledgeBase> bases = new HashMap<String, KnowledgeBase>();

	private static KBaseCache instance;
	
	private KBaseCache() {}
	
	public static KBaseCache getInstance() {
		if (instance == null) {
			instance = new KBaseCache();
		}
		
		return instance;
	}
	
	public KnowledgeBase getBaseConhecimento(String changeSetNames) {
		if (!bases.containsKey(changeSetNames)) {
			carregarBaseConhecimento(changeSetNames);
		}

		return bases.get(changeSetNames);
	}
	
	private void carregarBaseConhecimento(String changeSetNames) {
		KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
		kbuilder.add(ResourceFactory.newClassPathResource(changeSetNames + ".xml"), ResourceType.CHANGE_SET);
		KnowledgeBuilderErrors errors = kbuilder.getErrors();
		
		if (errors.size() > 0) {
			for (KnowledgeBuilderError error: errors) {
				System.err.println(error);
			}
			throw new IllegalArgumentException("Could not parse knowledge.");
		}
		
		KnowledgeBase kbase = KnowledgeBaseFactory.newKnowledgeBase();
		kbase.addKnowledgePackages(kbuilder.getKnowledgePackages());
		bases.put(changeSetNames, kbase);
	}

}
