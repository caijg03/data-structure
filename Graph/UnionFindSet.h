//
// Created by Administrator on 2023/10/21.
//
#pragma once

#include <vector>
#include <iostream>

using namespace std;

class UnionFindSet {
public:

    UnionFindSet(int size)
            : _set(size, -1) {}

    /**
     * 查找根
     * @param x 待查找元素
     * @return
     */
    size_t FindRoot(int x) {
        int root = x;
        while (_set[root] >= 0) {
            root = _set[root];
        }

        //路径压缩
        while (_set[x] >= 0) {
            int parent = _set[x];
            _set[x] = root;
            x = parent;
        }

        return root;
    }

    /**
     * 合并两个元素所在的集合
     * @param x1
     * @param x2
     */
    void Union(int x1, int x2) {
        int root1 = FindRoot(x1);
        int root2 = FindRoot(x2);

        if (root1 == root2) return;

        //把数量少的往数量大的合并
        if (_set[root1] > _set[root2]) {
            swap(root1, root2);
        }

        if (root1 != root2) {
            _set[root1] += _set[root2];
            _set[root2] = root1;
        }
    }

    /**
     * 统计有几个集合
     * @return
     */
    size_t SetCount() {
        size_t count = 0;
        for (int i : _set) {
            if (i < 0)
                count++;
        }

        return count;
    }

private:
    vector<int> _set;
};

void TestUFS() {
    UnionFindSet u(10);

    u.Union(8, 9);
    u.Union(7, 8);
    u.Union(6, 7);
    u.Union(5, 6);

    cout << u.SetCount() << endl;
}