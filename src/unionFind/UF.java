package unionFind;
// 并查集
public interface UF {

    int getSize();
    // 合并
    void unionElements(int p, int q);
    // 查找
    boolean isConnected(int p, int q);
}
