//
// Created by Administrator on 2023/10/21.
//
#include <iostream>
#include <vector>
#include <map>
#include <queue>
#include "UnionFindSet.h"

using namespace std;

/**
 *
 * @tparam V 顶点的类型
 * @tparam W 边权重的数据类型
 * @tparam MAX_W 用于表示两顶点之间无连接
 * @tparam Direction 有向or无向
 */
template<class V, class W, W MAX_W = INT_MAX, bool Direction = false>
class Graph {
public:

    typedef Graph<V, W, Direction> Self;

    Graph() = default;

    /**
     * 图的初始化
     * @param vertexs 图的顶点集合
     * @param n 个数
     */
    Graph(const V *vertexs, int n) {
        _vertexs.reserve(n);
        for (int i = 0; i < n; i++) {
            _vertexs.push_back(vertexs[i]);   //顶点初始化
            _vIndexMap[vertexs[i]] = i;       //下标映射初始化
        }

        //边的初始化
        _matrix.resize(n);
        for (auto &e: _matrix) {
            e.resize(n, MAX_W);
        }
    }

    /**
     * 得到顶点对应的下标
     * @param v 顶点
     * @return
     */
    int getVertexIndex(const V &v) {
        auto ret = _vIndexMap.find(v); //找到返回对应的迭代器,没找到返回end
        if (ret != _vIndexMap.end()) {
            return ret->second;
        } else {
            return -1;
        }
    }

    /**
     *
     * @param src 起点
     * @param dst 终点
     * @param w 边的权重
     */
    void addEdge(const V &src, const V &dst, const W &w) {
        int srci = getVertexIndex(src);
        int dsti = getVertexIndex(dst);

        _matrix[srci][dsti] = w;
        if (!Direction) {
            _matrix[dsti][srci] = w;
        }
    }

    void print() {
        //打印顶点和下标映射关系
//        for (int i = 0; i < _vertexs.size(); ++i) {
//            cout << _vertexs[i] << "->" << i << endl;
//        }
//        cout << endl;

        //打印邻接矩阵
        cout << "  ";
        for (int i = 0; i < _matrix.size(); ++i) {
            cout << _vertexs[i] << " ";
        }
        cout << endl;

        for (int i = 0; i < _matrix.size(); ++i) {

            cout << _vertexs[i] << " ";

            for (int j = 0; j < _matrix[i].size(); ++j) {
                if (i == j) {
                    cout << "0 ";
                } else if (_matrix[i][j] == MAX_W) {
                    cout << "#" << " ";
                } else {
                    cout << _matrix[i][j] << " ";
                }
            }
            cout << endl;
        }
        cout << endl;
    }

    /**
     * 广度优先遍历
     * @param src 遍历起点
     */
    void BFS(const V &src) {
        int n = _vertexs.size();
        //得到起点对应的下标
        int srci = getVertexIndex(src);
        //记录已访问顶点的数组, 全部初始化为未访问过
        vector<bool> visited(n, false);

        //将顶点入队列
        queue<int> q;
        q.push(srci);
        visited[srci] = true;

        while (!q.empty()) {
            //记录当前队头元素
            int front = q.front();
            //队头出队列
            q.pop();

            for (int i = 0; i < n; ++i) {
                //两个顶点相连且没有访问过就入队列
                if (_matrix[front][i] != MAX_W && !visited[i]) {
                    q.push(i);
                    visited[i] = true;
                }
            }
            cout << _vertexs[front] << " ";
        }
    }

    /**
     * 子函数
     * @param srci 起点下标
     * @param visited 标记数组
     */
    void _DFS(int srci, vector<bool> &visited) {
        if (!visited[srci]) {
            cout << _vertexs[srci] << " ";
        }
        visited[srci] = true;

        for (int i = 0; i < _vertexs.size(); ++i) {
            if (_matrix[srci][i] != MAX_W && !visited[i]) {
                _DFS(i, visited);
            }
        }
    }

    /**
     * 深度优先遍历
     * @param src 遍历起点
     */
    void DFS(const V &src) {
        //记录顶点是否被访问过
        vector<bool> visited(_vertexs.size(), false);
        int srci = getVertexIndex(src);

        _DFS(srci, visited);
    }

    class Edge {
    public:
        int srci;  //起点下标
        int dsti;  //终点下标

        W w;       //权重

        Edge(int srci, int dsti, W w) : srci(srci), dsti(dsti), w(w) {}

        bool operator>(const Edge &e) const {
            return w > e.w;
        }
    };

    /**
     * 克鲁斯卡尔最小生成树算法
     * @param minTree
     * @return 返回权值总和
     */
    W kruskal(Graph<V, W, MAX_W> &minTree) {
        //初始化属性值
        minTree._vertexs = _vertexs;
        minTree._vIndexMap = _vIndexMap;

        //邻接矩阵
        int n = _vertexs.size();
        minTree._matrix.resize(n);

        for (int i = 0; i < n; i++) {
            minTree._matrix[i].resize(n, MAX_W);
        }

        //小根堆  堆顶是权重最小的
        priority_queue<Edge, vector<Edge>, greater<Edge>> minq;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; ++j) {
                if (i < j && _matrix[i][j] != MAX_W) {
                    minq.push(Edge(i, j, _matrix[i][j]));
                }
            }
        }

        //记录权值总和
        W total = 0;
        int size = 0;  //边的总数

        UnionFindSet set = UnionFindSet(n);
        //有可能没有遍历完所有的边, 已经构成了最小生成树
        while (size < n - 1 && !minq.empty()) {

            Edge min = minq.top();
            minq.pop();
            //如果如果边的起点和终点没有在一个集合里,不构成环,可以添加
            if (set.FindRoot(min.srci) != set.FindRoot(min.dsti)) {
                minTree.addEdge(_vertexs[min.srci], _vertexs[min.dsti],
                                _matrix[min.srci][min.dsti]);
                set.Union(min.srci, min.dsti);
                size++;
                total += min.w;
            }
        }

        //当n < n - 1时,遍历完了所有的边,没有最小生成树
        if (size == n - 1)
            return total;
        return -1;
    }

    /**
     * 普莱姆最小生成树算法
     * 构造树+返回权值之和
     */
    W prim(Graph<V, W, MAX_W> &minTree, const V &src) {

        //最小生成树的顶点初始化
        minTree._vertexs = _vertexs;
        //最小生成树的顶点与对应初始化
        minTree._vIndexMap = _vIndexMap;

        //最小生成树的邻接矩阵初始化
        int n = _vertexs.size();
        minTree._matrix.resize(n);

        for (int i = 0; i < n; i++) {
            minTree._matrix[i].resize(n, MAX_W);
        }

        int size = 0; //最小生成树的边数
        W total = 0; //权值总和初始化
        vector<bool> visited(n, false); //记录是否已经进入集合

        priority_queue<Edge, vector<Edge>, greater<Edge>> minq;  //堆,存储边
        //起点连接的所有边入堆
        int srci = getVertexIndex(src);  //起点下标
        visited[srci] = true;
        for (int i = 0; i < n; ++i) {
            if (_matrix[srci][i] != MAX_W) {
                minq.push(Edge(srci, i, _matrix[srci][i]));
            }
        }

        //堆顶是与起点相连的最短的边
        while (size < n - 1 && !minq.empty()) {
            Edge mine = minq.top();
            minq.pop();

            //判断是否构成环
            if (visited[mine.dsti] == 0) {
                //把边添加进最小生成树
                minTree.addEdge(_vertexs[mine.srci], _vertexs[mine.dsti],
                                _matrix[mine.srci][mine.dsti]);
                //权值更新
                total += _matrix[mine.srci][mine.dsti];
                //标记为已经在集合中了
                visited[mine.dsti] = true;
                //边数加1
                size++;

                //新节点连接的边添加进去
                for (int i = 0; i < n; ++i) {
                    if (_matrix[mine.dsti][i] != MAX_W && !visited[i]) {
                        minq.push(Edge(mine.dsti, i, _matrix[mine.dsti][i]));
                    }
                }
            }
        }

        //如果没有够成树
        if (size == n - 1) return total;
        return -1;
    }

private:
    vector<V> _vertexs;         //用于存储顶点
    map<V, int> _vIndexMap;  //顶点对应下标  用于根据名字找下标
    vector<vector<W>> _matrix;  //存储边集合的矩阵
};

void Test01() {
    Graph<char, int, INT_MAX, true> g("ABCDEFGHI", 9);
    g.addEdge('A', 'B', 1);
    g.addEdge('A', 'D', 1);
    g.addEdge('A', 'C', 1);
    g.addEdge('B', 'E', 1);
    g.addEdge('B', 'C', 1);
    g.addEdge('C', 'F', 1);
    g.addEdge('D', 'F', 1);
    g.addEdge('E', 'G', 1);
    g.addEdge('F', 'H', 1);
    g.addEdge('H', 'I', 1);

    g.print();

    cout << "广度优先遍历:";
    g.BFS('A');
    cout << endl << "深度优先遍历:";
    g.DFS('A');

}