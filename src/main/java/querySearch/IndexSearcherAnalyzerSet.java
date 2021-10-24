package querySearch;

import java.io.IOException;
import java.nio.file.Paths;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.similarities.BM25Similarity;
import org.apache.lucene.search.similarities.ClassicSimilarity;
import org.apache.lucene.search.similarities.MultiSimilarity;
import org.apache.lucene.search.similarities.Similarity;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;


public class IndexSearcherAnalyzerSet {
	public void SetIndexSearcher(String Query_path, String INDEX_DIRECTORY_ENG, String INDEX_DIRECTORY_STD)  throws IOException, ParseException  {
		
		Directory directory = FSDirectory.open(Paths.get(INDEX_DIRECTORY_ENG));
	    
		// create objects to read and search across the index
	    DirectoryReader ireader = DirectoryReader.open(directory);
		
	    IndexSearcher isearcher = new IndexSearcher(ireader);
		//running without similarity
		System.out.println("Querying using English Analyser without similarity");
	    //path for result
	    String result_path = "./results_English.txt";
	    querySearchScore qs = new querySearchScore();
	    qs.querySearch(Query_path, isearcher, result_path, 1);
	    
	    
	    //adding BM25 similarity
	    isearcher.setSimilarity(new BM25Similarity());
	    System.out.println("Querying using English Analyser using BM25 similarity");
	    //path for result
	    result_path = "./results_EnglishBM25.txt";
	    qs = new querySearchScore();
	    qs.querySearch(Query_path, isearcher, result_path, 1 );
	    
	    
	    //adding VSM similarity
	    isearcher.setSimilarity(new BM25Similarity());
	    System.out.println("Querying using English Analyser using VSM similarity");
	    //path for result
	    result_path = "./results_EnglishVSM.txt";
	    qs.querySearch(Query_path, isearcher, result_path, 1);
	    
	    //adding Multiple similarities- BM25 and Classic
	    isearcher.setSimilarity(new MultiSimilarity(new Similarity[]{new BM25Similarity(),new ClassicSimilarity()}));
	    System.out.println("Querying using English Analyser using Multiple similarities- BM25 and Classic");
	    //path for result
	    result_path = "./results_EnglishMulti.txt";
	    qs.querySearch(Query_path, isearcher, result_path, 1);
	    
	    
	    //new directory for Standard Analyzer Index
	    directory = FSDirectory.open(Paths.get(INDEX_DIRECTORY_STD));
	    // create objects to read and search across the index
	    ireader = DirectoryReader.open(directory);
	    isearcher = new IndexSearcher(ireader);
	    
		//running without similarity
		System.out.println("Querying using Standard Analyser without similarity");
	    //path for result
	    result_path = "./results_Std.txt";
	    qs = new querySearchScore();
	    qs.querySearch(Query_path, isearcher, result_path, 2);
	    
	    
	    //adding BM25 similarity
	    isearcher.setSimilarity(new BM25Similarity());
	    System.out.println("Querying using Standard Analyser using BM25 similarity");
	    //path for result
	    result_path = "./results_StdBM25.txt";
	    qs = new querySearchScore();
	    qs.querySearch(Query_path, isearcher, result_path, 2 );
	    
	    
	    //adding VSM similarity
	    isearcher.setSimilarity(new BM25Similarity());
	    System.out.println("Querying using Standard Analyser using VSM similarity");
	    //path for result
	    result_path = "./results_StdVSM.txt";
	    qs.querySearch(Query_path, isearcher, result_path, 2);
	    
	    //adding Multiple similarities- BM25 and Classic
	    isearcher.setSimilarity(new MultiSimilarity(new Similarity[]{new BM25Similarity(),new ClassicSimilarity()}));
	    System.out.println("Querying using Standard Analyser using Multiple similarities- BM25 and Classic");
	    //path for result
	    result_path = "./results_StdMulti.txt";
	    qs.querySearch(Query_path, isearcher, result_path, 2);
	    
	    
	    ireader.close();
	    directory.close();
	}
	
}
