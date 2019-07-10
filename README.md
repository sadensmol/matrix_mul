# matrix_mul

Matrix multiply code

requirement : Java 8 


For the sake of simplicity matrices are square.

*Matrix1* - slow matrix multiply implementation
*Matrix2* - multithreaded matrix multiply implementation.

Multithreaded matrix mul calculator is implemented in an old (java 6) way with a standard Executor pool 
( no ForkJoinPool is used), and standard queue for futures results (no CompletionService is used).

The first time I worked with manual implemented matrix code.
I skipped JavaDocs and most tests.

Test/dev machine:
6 cores CPU 5.0Ghz
32 GB RAM

Speed results for matrix with size = 10000
Matrix1 :
Matrix2


Plan:
+ add simple test multiplier (to understand the area and raise an exp)
- add multithreaded multiplier (the main test requirement)
- add tests for big matrix calculations and provide a result data on speed for both ways
- refactor Matrix1, Matrix2 classes with BaseClass to remove code duplication