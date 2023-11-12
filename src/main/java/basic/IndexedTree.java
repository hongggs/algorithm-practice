package basic;

public class IndexedTree {
    long[] tree;
    int offset;
    int n;

    //offset 구하기
    public IndexedTree(int n) {
        for (offset = 1; offset < n; offset *= 2) ;
        this.n = n;
    }

    //입력 초기화
    public void init(long[] input) {
        for (int i = 0; i < n; i++) {
            tree[offset + i] = input[i];
        }
        tree = new long[offset * 2 + 1];
    }

    //트리 초기화(구간합으로)
    public void fill() {
        for (int i = offset - 1; i >= 1; i--) {
            tree[i] = tree[i * 2] + tree[i * 2 + 1];
        }
    }

    //update
    public void update(int id, long value) {
        int treeId = offset + id - 1;
        tree[treeId] = value;

        while (treeId > 0) {
            treeId /= 2;
            tree[treeId] = tree[treeId * 2] + tree[treeId * 2 + 1];
        }
    }

    //query: 구간합
    public long query(int start, int end) {
        int l = start + offset - 1;
        int r = end + offset - 1;
        int ans = 0;
        while(l <= r) {
            if(l % 2 == 1) {
                ans += tree[l++];
            }
            if(r % 2 == 0) {
                ans += tree[r--];
            }
            l /= 2;
            r /= 2;
        }
        return ans;
    }
}
