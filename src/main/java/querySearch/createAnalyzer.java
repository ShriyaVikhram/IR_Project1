package querySearch;

import java.util.Arrays;
import java.util.List;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.CharArraySet;
import org.apache.lucene.analysis.en.EnglishAnalyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;

public class createAnalyzer {
	public Analyzer createEnglishAnalyser() {
        //stop words set
        CharArraySet stopSet = CharArraySet.copy(EnglishAnalyzer.ENGLISH_STOP_WORDS_SET);
        List < String > stopWordList = Arrays.asList("a", "an", "and", "are", "as", "at", "be", "but", "by", "for", "however", "if", "in", "into", "is", "it", "no", "not", "of", "on", "or", "such", "that", "the", "their", "then", "there", "these", "they", "this", "to", "was", "will", "with", "without", "also", "some", "have", "has", "had", "do", "because", "been", "from", "so", "than", "were", "whatever", "whether", "which", "would");
        int j = 0;
        while (j < stopWordList.size()) {
            stopSet.add(stopWordList.get(j));
            j++;
        }
        // Analyzer used by the query parser.
        // Must be the same as the one used when creating the index
        Analyzer analyzer = new EnglishAnalyzer(stopSet);
        return(analyzer);
	}

	public Analyzer createStandardAnalyser() {
		List < String > stopWordList = Arrays.asList("a", "an", "and", "are", "as", "at", "be", "but", "by", "for", "however", "if", "in", "into", "is", "it", "no", "not", "of", "on", "or", "such", "that", "the", "their", "then", "there", "these", "they", "this", "to", "was", "will", "with", "without", "also", "some", "have", "has", "had", "do", "because", "been", "from", "so", "than", "were", "whatever", "whether", "which", "would");
        CharArraySet stopSet = new CharArraySet(stopWordList, false);
        // Analyzer used by the query parser.
        // Must be the same as the one used when creating the index
        Analyzer analyzer = new StandardAnalyzer(stopSet);
        return(analyzer);
	}
}
