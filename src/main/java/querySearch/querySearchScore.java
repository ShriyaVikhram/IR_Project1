package querySearch;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;

import java.nio.file.Paths;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.CharArraySet;
import org.apache.lucene.analysis.en.EnglishAnalyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;

import org.apache.lucene.document.Document;

import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.tartarus.snowball.ext.PorterStemmer;

import querySplit.querySplit;

import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.queryparser.classic.ParseException;

public class querySearchScore {

    // Limit the number of search results we get. Since we have 
    //a small data set, we can set max results to total number of files.
    // For max efficiency and best results, taking the max hit documents to 999. 
    private static int MAX_RESULTS = 999;

    public void querySearch(String args, IndexSearcher isearcher, String result, Integer AnalyzerSelect) throws IOException, ParseException {
    	
    	Analyzer analyzer = null;
    	if(AnalyzerSelect==1) {
			createAnalyzer engAnalyser = new createAnalyzer(); 
	        analyzer = engAnalyser.createEnglishAnalyser();
		}
		else if (AnalyzerSelect == 2) {
			createAnalyzer engAnalyser = new createAnalyzer(); 
	        analyzer = engAnalyser.createStandardAnalyser();
		}
    	
		// Create the query parser. The default search field is "work".
        QueryParser parser = new QueryParser("work", analyzer);

        try {
            //reading query file 
            File file = new File(args);
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            int queryId = 1;
            String line;
            querySplit split = new querySplit(); 

            // Create a new file output file.
            PrintStream fileOut = new PrintStream(result);
            //storing current console output
            PrintStream console = System.out;
            // Redirect standard out to file.
            System.setOut(fileOut);

            String queryString = "";
            while ((line = br.readLine()) != null) {
                if (line.contains(".I") && split.getQIndex() != null) {
                    // replace special characters in work from query
                    queryString = split.getQWork().replaceAll("[?*]", "");

                    //stemming
                    PorterStemmer stem = new PorterStemmer();
                    stem.setCurrent(queryString);
                    stem.stem();
                    queryString = stem.getCurrent();

                    //reading queryString using querySplit
                    if (queryString.length() > 0) {
                        // parse the query with the parser
                        Query query = parser.parse(queryString);

                        // Get the set of results
                        ScoreDoc[] hits = isearcher.search(query, MAX_RESULTS).scoreDocs;

                        // Print the results
                        for (int i = 0; i < hits.length; i++) {
                            Document hitDoc = isearcher.doc(hits[i].doc);
                            System.out.println(queryId + " 0 " + hitDoc.get("index") + " 1 " + hits[i].score + " 1");
                        }
                    }
                    split = new querySplit();
                    queryId++;
                }
                split.setQFlag(line);
                split.setValue(line);
            }
            //final query run
            queryString = split.getQWork().replace("?", "");

            //stemming for final query
            PorterStemmer stem = new PorterStemmer();
            stem.setCurrent(queryString);
            stem.stem();
            queryString = stem.getCurrent();

            if (queryString.length() > 0) {
                // parse the query with the parser
                Query query = parser.parse(queryString);

                // Get the set of results
                ScoreDoc[] hits = isearcher.search(query, MAX_RESULTS).scoreDocs;

                // Print the results
                for (int i = 0; i < hits.length; i++) {
                    Document hitDoc = isearcher.doc(hits[i].doc);
                    //adding extra fields to file to match trec eval requirement
                    System.out.println(queryId + " 0 " + hitDoc.get("index") + " 1 " + hits[i].score + " 1");
                }
            }
            // close everything and quit
            br.close();
            System.setOut(console);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}