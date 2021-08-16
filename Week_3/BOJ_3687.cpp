#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

int num[9] = { 0,0,1,7,4,2,0,8,10 };
long long d[101] = { 0, };

void calculateMin() {
	for (int i = 0; i < 10; i++) {
		d[i] = num[i];
	}
	d[6] = 6;

	for (int i = 9; i <= 100; i++) {
		d[i] = d[i - 2] * 10 + num[2];
		for (int j = 3; j < 8; j++) {
			d[i] = min(d[i], d[i - j] * 10 + num[j]);
		}
	}
}

int main()
{
	int t, n;
	cin >> t;
	vector<int> v(t);
	calculateMin();

	for (int i = 0; i < t; i++) {
		cin >> v[i];
	}
	
	for (int i = 0; i < t; i++) {
		int n = v[i];
		cout << d[n] << " ";

		while (n) {
			if (n % 2 == 0) {
				cout << "1";
				n -= 2;
			}
			else {
				cout << "7";
				n -= 3;
			}
		}
		cout << endl;
	}

	return 0;
}