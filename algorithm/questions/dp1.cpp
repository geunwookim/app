/*
-문제-
어떤 자연수 N은 그보다 작거나 같은 제곱수들의 합으로 나타낼 수 있다.
예를 들어 11=3^2+1^2+1^2(3개 항)이다.
이런 표현방법은 여러 가지가 될 수 있는데, 11의 경우 11=2^2+2^2+1^2+1^2+1^2(5개 항)도 가능하다.
이 경우, 수학자 숌크라테스는 “11은 3개 항의 제곱수 합으로 표현할 수 있다.”라고 말한다.
또한 11은 그보다 적은 항의 제곱수 합으로 표현할 수 없으므로, 11을 그 합으로써 표현할 수 있는 제곱수 항의 최소 개수는 3이다.
주어진 자연수 N을 이렇게 제곱수들의 합으로 표현할 때에 그 항의 최소개수를 구하는 프로그램을 작성하시오.

입력
첫째 줄에 자연수 N이 주어진다. (1 ≤ N ≤ 100,000)

출력
주어진 자연수를 제곱수의 합으로 나타낼 때에 그 제곱수 항의 최소 개수를 출력한다.

예제 입력
7

예제 출력
4
*/

#define _CRT_SECURE_NO_WARNINGS
#include<iostream>
#include<algorithm>

using namespace std;

int n;
int dp[100005];

int bottom_up(int n)
{
    dp[0] = 0;
    
    for (int i=1; i<=n; i++)
    {
        dp[i] = dp[i-1] + 1; // n-1의 경우와 1^2으로 채운 경우
        // dp[n]을 i^2(+1의 의미)과 dp[n-(i^2)]으로 채운 경우
        for (int j=1; j*j<=i; j++) dp[i] = min(dp[i], dp[i - j*j] + 1);
    }
    return dp[n];
}

int top_down(int n)
{
    if (n == 0) return 0;
    if (dp[n] > 0) return dp[n];

    dp[n] = top_down(n-1) + 1; // n-1의 경우와 1^2으로 채운 경우
    for (int i=1; i*i<=n; i++) dp[n] = min(dp[n], top_down(n - i*i) + 1); // dp[n]을 i^2(+1의 의미)과 dp[n-(i^2)]으로 채운 경우
    
    return dp[n];
}

int main()
{
    scanf("%d", &n);

    printf("%d", bottom_up(n));
    //printf("%d", top_down(n));
}
