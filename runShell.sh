mvn package
echo "Running Indexing and Searching.."
cd ./target
java -jar lucene_project1-0.0.1-SNAPSHOT.jar "../corpus/cran.all.1400" "../corpus/cran.qry"

cd ../trec_eval-9.0.7

echo "TREC eval for English Analyzer without similarity"
./trec_eval -m map -m P.5,10 ../corpus/QRelsCorrectedforTRECeval ../results_English.txt
echo "TREC eval for English Analyzer with BM25" 
./trec_eval -m map -m P.5,10 ../corpus/QRelsCorrectedforTRECeval ../results_EnglishBM25.txt
echo "TREC eval for English Analyzer with VSM"
./trec_eval -m map -m P.5,10 ../corpus/QRelsCorrectedforTRECeval ../results_EnglishVSM.txt
echo "Trec eval for English Analyzer with Multiple similarities - BM25 and Classic "
./trec_eval -m map -m P.5,10 ../corpus/QRelsCorrectedforTRECeval ../results_EnglishMulti.txt
echo "TREC eval for Standard Analyzer without similarity"
./trec_eval -m map -m P.5,10 ../corpus/QRelsCorrectedforTRECeval ../results_Std.txt
echo "TREC eval for Standard Analyzer with BM25"
./trec_eval -m map -m P.5,10 ../corpus/QRelsCorrectedforTRECeval ../results_StdBM25.txt
echo "TREC eval for Standard Analyzer with VSM"
./trec_eval -m map -m P.5,10 ../corpus/QRelsCorrectedforTRECeval ../results_StdVSM.txt
echo "Trec eval for Standard Analyzer with Multiple similarities - BM25 and Classic "
./trec_eval -m map -m P.5,10 ../corpus/QRelsCorrectedforTRECeval ../results_StdMulti.txt









