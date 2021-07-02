/*
-문제-
강남역에서 붕어빵 장사를 하고 있는 해빈이는 지금 붕어빵이 N개 남았다.
해빈이는 적절히 붕어빵 세트 메뉴를 구성해서 붕어빵을 팔아서 얻을 수 있는 수익을 최대로 만드려고 한다.
붕어빵 세트 메뉴는 붕어빵을 묶어서 파는 것을 의미하고, 세트 메뉴의 가격은 이미 정해져 있다.
붕어빵 i개로 이루어진 세트 메뉴의 가격은 Pi 원이다.
붕어빵이 4개 남아 있고, 1개 팔 때의 가격이 1, 2개는 5, 3개는 6, 4개는 7인 경우에 해빈이가 얻을 수 있는 최대 수익은 10원이다.
2개, 2개로 붕어빵을 팔면 되기 때문이다.
1개 팔 때의 가격이 5, 2개는 2, 3개는 8, 4개는 10 인 경우에는 20이 된다.
1개, 1개, 1개, 1개로 붕어빵을 팔면 되기 때문이다.
마지막으로, 1개 팔 때의 가격이 3, 2개는 5, 3개는 15, 4개는 16인 경우에는 정답은 18이다.
붕어빵을 3개, 1개로 팔면 되기 때문이다.
세트 메뉴의 가격이 주어졌을 때, 해빈이가 얻을 수 있는 최대 수익을 구하는 프로그램을 작성하시오.

입력
첫째 줄에 해빈이가 가지고 있는 붕어빵의 개수 N이 주어진다. (1 ≤ N ≤ 1,000)
둘째 줄에는 Pi가 P1부터 PN까지 순서대로 주어진다. (1 ≤ Pi ≤ 10,000)

출력
해빈이가 얻을 수 있는 최대 수익을 출력한다.

예제 입력
4
1 5 6 7

예제 출력
10
*/

#define _CRT_SECURE_NO_WARNINGS
#include<iostream>
#include<algorithm>
#include<string.h>

using namespace std;


int N, P[1000];
int dp[10000];


int bottom_up(int n)
{
    for (int i=1; i<=n; i++)
    {
        for(int j=1; j<=i; j++) 
            dp[i] = max(dp[i], dp[i-j] + P[j-1]);
    }

    return dp[n];
}

int top_down(int n)
{
    if(n==0) return 0;
    if(dp[n] != 0) return dp[n];

    for(int i=1; i<=n; i++)
        dp[n] = max(dp[n], top_down(n-i)+P[i-1]);

    return dp[n];
}

int main()
{
    scanf("%d", &N);
    for(int i=0; i<N; i++)
        scanf("%d", &P[i]);

    memset(dp,0,sizeof(dp));

    //printf("%d\r\n", top_down(N));
    printf("%d\r\n", bottom_up(N));
}