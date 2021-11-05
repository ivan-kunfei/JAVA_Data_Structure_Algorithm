package unionFind;

import java.util.Random;

public class Main {

    private static double testUF(UF uf, int m){
        int size = uf.getSize();
        Random random = new Random();
        long startTime = System.nanoTime();
        // 执行 m 次合并操作 和 m 次查找操作
        for (int i = 0; i<m; i++){
            int a = random.nextInt(size);
            int b = random.nextInt(size);
            uf.unionElements(a,b);
        }

        for (int i = 0; i<m; i++){
            int a = random.nextInt(size);
            int b = random.nextInt(size);
            uf.isConnected(a,b);
        }

        long endTime = System.nanoTime();

        return (endTime-startTime) / 1000000000.0;
    }

        public static void main(String[] args){
            int size = 100000;
            int m = 100000;

            UnionFind1 fu1 = new UnionFind1(size);
            System.out.println("UnionFind1 : "+testUF(fu1,m) + " s");

            UnionFind2 fu2 = new UnionFind2(size);
            System.out.println("UnionFind2 : "+testUF(fu2,m) + " s");

            UnionFind3 fu3 = new UnionFind3(size);
            System.out.println("UnionFind3 : "+testUF(fu3,m) + " s");

            UnionFind4 fu4 = new UnionFind4(size);
            System.out.println("UnionFind4 : "+testUF(fu4,m) + " s");

            System.out.println("=================================");
            size *= 100;
            m *= 100;
            fu3 = new UnionFind3(size);
            System.out.println("UnionFind3 : "+testUF(fu3,m) + " s");

            fu4 = new UnionFind4(size);
            System.out.println("UnionFind4 : "+testUF(fu4,m) + " s");

            UnionFind5 fu5 = new UnionFind5(size);
            System.out.println("UnionFind5 : "+testUF(fu5,m) + " s");

            UnionFind6 fu6 = new UnionFind6(size);
            System.out.println("UnionFind6 : "+testUF(fu6,m) + " s");
        }
}
