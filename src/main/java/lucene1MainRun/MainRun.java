package lucene1MainRun;

import java.io.IOException;
import java.nio.file.Paths;

import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.similarities.BM25Similarity;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import querySearch.*;
import vikhrams.LuceneIndex.*;
import org.apache.lucene.search.IndexSearcher;

public class MainRun {

	public static void main(String[] args) throws IOException, ParseException 
	{
		if (args.length <= 0)
		{
            System.out.println("Expected corpus and query as input");
            System.exit(1);            
        }
		IndexEnglishAnalyser index1 = new IndexEnglishAnalyser();
		IndexStandardAnalyser index2 = new IndexStandardAnalyser();
		
		try {
			index1.IndexEnglish(args[0]);
			index2.Index_standard(args[0]);
			// the location of the search index
		    String INDEX_DIRECTORY_ENG = "../indexEnglish";
		    String INDEX_DIRECTORY_STD = "../indexStandard";

	        IndexSearcherAnalyzerSet is = new IndexSearcherAnalyzerSet();
	        is.SetIndexSearcher(args[1],INDEX_DIRECTORY_ENG,INDEX_DIRECTORY_STD);
			
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
