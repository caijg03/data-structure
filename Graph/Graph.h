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
 * @tparam V ���������
 * @tparam W ��Ȩ�ص���������
 * @tparam MAX_W ���ڱ�ʾ������֮��������
 * @tparam Direction ����or����
 */
template<class V, class W, W MAX_W = INT_MAX, bool Direction = false>
class Graph {
public:

    typedef Graph<V, W, Direction> Self;

    Graph() = default;

    /**
     * ͼ�ĳ�ʼ��
     * @param vertexs ͼ�Ķ��㼯��
     * @param n ����
     */
    Graph(const V *vertexs, int n) {
        _vertexs.reserve(n);
        for (int i = 0; i < n; i++) {
            _vertexs.push_back(vertexs[i]);   //�����ʼ��
            _vIndexMap[vertexs[i]] = i;       //�±�ӳ���ʼ��
        }

        //�ߵĳ�ʼ��
        _matrix.resize(n);
        for (auto &e: _matrix) {
            e.resize(n, MAX_W);
        }
    }

    /**
     * �õ������Ӧ���±�
     * @param v ����
     * @return
     */
    int getVertexIndex(const V &v) {
        auto ret = _vIndexMap.find(v); //�ҵ����ض�Ӧ�ĵ�����,û�ҵ�����end
        if (ret != _vIndexMap.end()) {
            return ret->second;
        } else {
            return -1;
        }
    }

    /**
     *
     * @param src ���
     * @param dst �յ�
     * @param w �ߵ�Ȩ��
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
        //��ӡ������±�ӳ���ϵ
//        for (int i = 0; i < _vertexs.size(); ++i) {
//            cout << _vertexs[i] << "->" << i << endl;
//        }
//        cout << endl;

        //��ӡ�ڽӾ���
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
     * ������ȱ���
     * @param src �������
     */
    void BFS(const V &src) {
        int n = _vertexs.size();
        //�õ�����Ӧ���±�
        int srci = getVertexIndex(src);
        //��¼�ѷ��ʶ��������, ȫ����ʼ��Ϊδ���ʹ�
        vector<bool> visited(n, false);

        //�����������
        queue<int> q;
        q.push(srci);
        visited[srci] = true;

        while (!q.empty()) {
            //��¼��ǰ��ͷԪ��
            int front = q.front();
            //��ͷ������
            q.pop();

            for (int i = 0; i < n; ++i) {
                //��������������û�з��ʹ��������
                if (_matrix[front][i] != MAX_W && !visited[i]) {
                    q.push(i);
                    visited[i] = true;
                }
            }
            cout << _vertexs[front] << " ";
        }
    }

    /**
     * �Ӻ���
     * @param srci ����±�
     * @param visited �������
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
     * ������ȱ���
     * @param src �������
     */
    void DFS(const V &src) {
        //��¼�����Ƿ񱻷��ʹ�
        vector<bool> visited(_vertexs.size(), false);
        int srci = getVertexIndex(src);

        _DFS(srci, visited);
    }

    class Edge {
    public:
        int srci;  //����±�
        int dsti;  //�յ��±�

        W w;       //Ȩ��

        Edge(int srci, int dsti, W w) : srci(srci), dsti(dsti), w(w) {}

        bool operator>(const Edge &e) const {
            return w > e.w;
        }
    };

    /**
     * ��³˹������С�������㷨
     * @param minTree
     * @return ����Ȩֵ�ܺ�
     */
    W kruskal(Graph<V, W, MAX_W> &minTree) {
        //��ʼ������ֵ
        minTree._vertexs = _vertexs;
        minTree._vIndexMap = _vIndexMap;

        //�ڽӾ���
        int n = _vertexs.size();
        minTree._matrix.resize(n);

        for (int i = 0; i < n; i++) {
            minTree._matrix[i].resize(n, MAX_W);
        }

        //С����  �Ѷ���Ȩ����С��
        priority_queue<Edge, vector<Edge>, greater<Edge>> minq;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; ++j) {
                if (i < j && _matrix[i][j] != MAX_W) {
                    minq.push(Edge(i, j, _matrix[i][j]));
                }
            }
        }

        //��¼Ȩֵ�ܺ�
        W total = 0;
        int size = 0;  //�ߵ�����

        UnionFindSet set = UnionFindSet(n);
        //�п���û�б��������еı�, �Ѿ���������С������
        while (size < n - 1 && !minq.empty()) {

            Edge min = minq.top();
            minq.pop();
            //�������ߵ������յ�û����һ��������,�����ɻ�,�������
            if (set.FindRoot(min.srci) != set.FindRoot(min.dsti)) {
                minTree.addEdge(_vertexs[min.srci], _vertexs[min.dsti],
                                _matrix[min.srci][min.dsti]);
                set.Union(min.srci, min.dsti);
                size++;
                total += min.w;
            }
        }

        //��n < n - 1ʱ,�����������еı�,û����С������
        if (size == n - 1)
            return total;
        return -1;
    }

    /**
     * ����ķ��С�������㷨
     * ������+����Ȩֵ֮��
     */
    W prim(Graph<V, W, MAX_W> &minTree, const V &src) {

        //��С�������Ķ����ʼ��
        minTree._vertexs = _vertexs;
        //��С�������Ķ������Ӧ��ʼ��
        minTree._vIndexMap = _vIndexMap;

        //��С���������ڽӾ����ʼ��
        int n = _vertexs.size();
        minTree._matrix.resize(n);

        for (int i = 0; i < n; i++) {
            minTree._matrix[i].resize(n, MAX_W);
        }

        int size = 0; //��С�������ı���
        W total = 0; //Ȩֵ�ܺͳ�ʼ��
        vector<bool> visited(n, false); //��¼�Ƿ��Ѿ����뼯��

        priority_queue<Edge, vector<Edge>, greater<Edge>> minq;  //��,�洢��
        //������ӵ����б����
        int srci = getVertexIndex(src);  //����±�
        visited[srci] = true;
        for (int i = 0; i < n; ++i) {
            if (_matrix[srci][i] != MAX_W) {
                minq.push(Edge(srci, i, _matrix[srci][i]));
            }
        }

        //�Ѷ����������������̵ı�
        while (size < n - 1 && !minq.empty()) {
            Edge mine = minq.top();
            minq.pop();

            //�ж��Ƿ񹹳ɻ�
            if (visited[mine.dsti] == 0) {
                //�ѱ���ӽ���С������
                minTree.addEdge(_vertexs[mine.srci], _vertexs[mine.dsti],
                                _matrix[mine.srci][mine.dsti]);
                //Ȩֵ����
                total += _matrix[mine.srci][mine.dsti];
                //���Ϊ�Ѿ��ڼ�������
                visited[mine.dsti] = true;
                //������1
                size++;

                //�½ڵ����ӵı���ӽ�ȥ
                for (int i = 0; i < n; ++i) {
                    if (_matrix[mine.dsti][i] != MAX_W && !visited[i]) {
                        minq.push(Edge(mine.dsti, i, _matrix[mine.dsti][i]));
                    }
                }
            }
        }

        //���û�й�����
        if (size == n - 1) return total;
        return -1;
    }

private:
    vector<V> _vertexs;         //���ڴ洢����
    map<V, int> _vIndexMap;  //�����Ӧ�±�  ���ڸ����������±�
    vector<vector<W>> _matrix;  //�洢�߼��ϵľ���
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

    cout << "������ȱ���:";
    g.BFS('A');
    cout << endl << "������ȱ���:";
    g.DFS('A');

}