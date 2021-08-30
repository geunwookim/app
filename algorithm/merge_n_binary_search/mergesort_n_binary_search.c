#include <stdio.h>

char arrSample[8] = {3,4,5,6,1,2,7,8};

void merge(char * arr, int start, int end)
{
    int i, j, k, a;
    char tempArr[sizeof(arr)];

    int mid = (start+end) / 2;
    i = start;
    j = mid+1;
    k = start;

    while(i<=mid && j<=end)
    {
        if(arr[i]<=arr[j]) tempArr[k++] = arr[i++];
        else tempArr[k++] = arr[j++];
    }

    while(i<=mid) tempArr[k++] = arr[i++];
    while(j<=end) tempArr[k++] = arr[j++];

    for(a=start; a<=end; a++) arr[a] = tempArr[a];
}

void sort(char * arr, int start, int end)
{
    if(start < end)
    {
        int mid = (start + end) / 2;
        sort(arr, start, mid);
        sort(arr, mid+1, end);
        merge(arr, start, end);
    }
}

int findValue(char * arr, int value)
{
    int start, mid, end;
    int sizeArr = sizeof(arr);

    start = 0;
    end = sizeArr-1;

    while(start <= end)
    {
        mid = (start+end) / 2;

        if(arr[mid] == value) return mid;
        else if(arr[mid] > value) end = mid-1;
        else start = mid+1;
    }

    printf("not found\r\n");

    return -1;
}

int main(void)
{
    int sizeArr = sizeof(arrSample);

    sort(arrSample, 0, sizeArr-1);

    printf("Sorted array: ");
    for(char i=0; i<sizeArr; i++) printf("%d ", arrSample[i]);
    printf("\r\n");

    int index = findValue(arrSample, 22);
    if(index != -1) printf("Found index: %d", index);
}