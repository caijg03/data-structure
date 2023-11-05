#include "UnionFindSet.h"
#include "Graph.h"
#include <bits/stdc++.h>
using namespace std;

void TestGraphMinTree() {
    const char *str = "abcdefghi";
    Graph<char, int> g(str, strlen(str));
    g.addEdge('a', 'b', 4);
    g.addEdge('a', 'h', 8);
    //g.AddEdge('a', 'h', 9);
    g.addEdge('b', 'c', 8);
    g.addEdge('b', 'h', 11);
    g.addEdge('c', 'i', 2);
    g.addEdge('c', 'f', 4);
    g.addEdge('c', 'd', 7);
    g.addEdge('d', 'f', 14);
    g.addEdge('d', 'e', 9);
    g.addEdge('e', 'f', 10);
    g.addEdge('f', 'g', 2);
    g.addEdge('g', 'h', 1);
    g.addEdge('g', 'i', 6);
    g.addEdge('h', 'i', 7);

    //最小生成树
    Graph<char, int> minTree;

    cout << g.kruskal(minTree) << endl;

    //cout << g.prim(minTree, 'a') << endl;

    minTree.print();
}



class Student {
public:

    int age;
    string name;

    Student(int age, string name) {
        this->age = age;
        this->name = name;
    }

    bool operator==(const Student& s2) const {
        return age == s2.age && name == s2.name;
    }

    bool operator<(const Student& s2) const {
        if (age != s2.age) {
            return s2.age - age;
        } else {
            return name > s2.name;
        }
    }
};

//按照指定方式计算哈希值
struct StudentHash {
    size_t operator()(const Student& student) const {
        return hash<int>()(student.age);
    }
};

int main() {
//    Student s1(1, "tom");
//    unordered_map<Student, int, StudentHash> m;
//    m.insert({s1, 2});

    set<pair<int, int>> s;

    s.insert({2, 2});
    s.insert({17, 3});
    s.insert({17, 2});

    for (auto item:s) {
        cout << item.first << ":" << item.second << endl;
    }

    return 0;
}

