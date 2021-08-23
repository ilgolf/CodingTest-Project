#include <string>
#include <vector>
#include <queue>
using namespace std;

bool _check[201] = { 0, };

void bfs(int v, vector<vector<int>> &computers) {
	queue<int> q;
	q.push(v);
	_check[v] = true;
	while (!q.empty()) {
		int num = q.front(); q.pop();
		for (int i = 0; i < computers.size(); i++) {
			if (computers[num][i] && !_check[i]) {
				_check[i] = true;
				q.push(i);
			}
		}
	}
}

int solution(int n, vector<vector<int>> computers) {
    int answer = 0;
    	for (int i = 0; i < computers.size(); i++) {
		if (!_check[i]) {
			bfs(i, computers);
			answer++;
		}
	}
    return answer;
}