package database;

import java.io.File;
import java.io.IOException;

import com.aliasi.classify.Classification;
import com.aliasi.classify.Classified;
import com.aliasi.classify.DynamicLMClassifier;
import com.aliasi.classify.LMClassifier;
import com.aliasi.corpus.ObjectHandler;
import com.aliasi.util.AbstractExternalizable;
import com.aliasi.util.Compilable;
import com.aliasi.util.Files;

public class train {

public void train() throws Exception {
		File trainDir;
		String[] categories;
		LMClassifier classifier;
		trainDir = new File("/Users/rahur/Documents/workspace/nlp/nlp_ml_senti/resources/trainDirectory");
		categories = trainDir.list();
		int nGram = 7; //the nGram level, any value between 7 and 12 works
		classifier = DynamicLMClassifier.createNGramProcess(categories, nGram);

		for (int i = 0; i < categories.length; ++i) {
			String category = categories[i];
			Classification classification = new Classification(category);
			//System.out.println("abt ot list cate "+categories[i]);
			File file = new File(trainDir, categories[i]);
			File[] trainFiles = file.listFiles();
			for (int j = 0; j < trainFiles.length; ++j) {
				File trainFile = trainFiles[j];
				String review = Files.readFromFile(trainFile, "ISO-8859-1");
				Classified classified = new Classified(review, classification);
				((ObjectHandler) classifier).handle(classified);  
			}
	 	}
		AbstractExternalizable.compileTo((Compilable) classifier, new File("classifier.txt"));
	}
}
