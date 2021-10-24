# IR_Project1
--Building maven package
mvn package

Path to target directory after building maven package
cd ./target
java -jar lucene_project1-0.0.1-SNAPSHOT.jar "../corpus/cran.all.1400" "../corpus/cran.qry"
--This path takes the data file and the query file. Indexing and searching is done and result files are generated.

Path to trec eval directory
cd ../trec_eval-9.0.7

--Scoring using trec eval
TREC eval for English Analyzer without similarity
./trec_eval -m map -m P.5,10 ../corpus/QRelsCorrectedforTRECeval ../results_English.txt

TREC eval for English Analyzer with BM25
./trec_eval -m map -m P.5,10 ../corpus/QRelsCorrectedforTRECeval ../results_EnglishBM25.txt

TREC eval for English Analyzer with VSM
./trec_eval -m map -m P.5,10 ../corpus/QRelsCorrectedforTRECeval ../results_EnglishVSM.txt

Trec eval for English Analyzer with Multiple similarities - BM25 and Classic
./trec_eval -m map -m P.5,10 ../corpus/QRelsCorrectedforTRECeval ../results_EnglishMulti.txt

TREC eval for Standard Analyzer without similarity
./trec_eval -m map -m P.5,10 ../corpus/QRelsCorrectedforTRECeval ../results_Std.txt

TREC eval for Standard Analyzer with BM25
./trec_eval -m map -m P.5,10 ../corpus/QRelsCorrectedforTRECeval ../results_StdBM25.txt

TREC eval for Standard Analyzer with VSM
./trec_eval -m map -m P.5,10 ../corpus/QRelsCorrectedforTRECeval ../results_StdVSM.txt

Trec eval for Standard Analyzer with Multiple similarities - BM25 and Classic 
./trec_eval -m map -m P.5,10 ../corpus/QRelsCorrectedforTRECeval ../results_StdMulti.txt

