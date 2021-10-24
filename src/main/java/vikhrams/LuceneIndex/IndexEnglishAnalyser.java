package vikhrams.LuceneIndex;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.nio.file.Paths;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.CharArraySet;
import org.apache.lucene.analysis.en.EnglishAnalyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.document.StringField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.tartarus.snowball.ext.PorterStemmer;

import vikhrams.SplitFileText.*;


public class IndexEnglishAnalyser {
    // Directory where the search index will be saved
    private static String INDEX_DIRECTORY = "../indexEnglish";

    public void IndexEnglish(String arg) throws IOException {

        //stop words sets
        CharArraySet stopSet = CharArraySet.copy(EnglishAnalyzer.ENGLISH_STOP_WORDS_SET);
        List < String > stopWordList = Arrays.asList("a", "an", "and", "are", "as", "at", "be", "but", "by", "for", "however", "if", "in", "into", "is", "it", "no", "not", "of", "on", "or", "such", "that", "the", "their", "then", "there", "these", "they", "this", "to", "was", "will", "with", "without", "also", "some", "have", "has", "had", "do", "because", "been", "from", "p", "so", "than", "were", "whatever", "whether", "which", "would");
        int i = 0;
        while (i < stopWordList.size()) {
            stopSet.add(stopWordList.get(i));
            i++;
        }

        // English Analyzer used by the query parser.
        // Must be the same as the one used when creating the index
        Analyzer analyzer = new EnglishAnalyzer(stopSet);

        // ArrayList of documents in the corpus
        ArrayList < Document > documents = new ArrayList < Document > ();

        // Open the directory that contains the search index
        Directory directory = FSDirectory.open(Paths.get(INDEX_DIRECTORY));

        // Set up an index writer to add process and save documents to the index
        IndexWriterConfig config = new IndexWriterConfig(analyzer);
        config.setOpenMode(IndexWriterConfig.OpenMode.CREATE);
        IndexWriter iwriter = new IndexWriter(directory, config);

        // Load the contents of the file
        System.out.printf("Indexing using English Analyzer \"%s\"\n", arg);
        try {
            // Reading filename from argument 
            File file = new File(arg);
            FileReader fr = new FileReader(file);
            // Set up buffer reader to read the file line by line
            BufferedReader br = new BufferedReader(fr);

            String line;
            SplitDoc split = new SplitDoc();
            Document doc = new Document();

            while ((line = br.readLine()) != null) {
                //adding different fields for one iteration in cran file
                if (line.contains(".I") && split.getIndex() != null) {
                    doc.add(new StringField("index", split.getIndex(), Field.Store.YES));
                    doc.add(new TextField("title", split.getTitle(), Field.Store.YES));
                    doc.add(new TextField("author", split.getAuthor(), Field.Store.YES));
                    doc.add(new TextField("bibliography", split.getBibliography(), Field.Store.YES));
                    doc.add(new TextField("work", split.getWork(), Field.Store.YES));
                    doc = new Document();
                    split = new SplitDoc();
                    // Add the file to our linked list
                    documents.add(doc);
                }
                split.setFlag(line);
                split.setValue(line);
            }

            //adding for last iteration in cran file
            if (split.getIndex() != null) {

                doc.add(new StringField("index", split.getIndex(), Field.Store.YES));
                doc.add(new TextField("title", split.getTitle(), Field.Store.YES));
                doc.add(new TextField("author", split.getAuthor(), Field.Store.YES));
                doc.add(new TextField("bibliography", split.getBibliography(), Field.Store.YES));
                doc.add(new TextField("work", split.getWork(), Field.Store.YES));
            }
            fr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Write all the documents in the linked list to the search index
        iwriter.addDocuments(documents);

        // Commit everything and close
        iwriter.close();
        directory.close();

    }
}