class NumberOfDigitOne {
    public int countDigitOne(int n) {
        int res = 0;
        for (long m = 1; m <= n; m *= 10) {
            res += (n / m + 8) / 10 * m;
            if (n / m % 10 == 1) res += n % m + 1;  
        }
        return res;
    }
}
/*
if only consider the leftmost digit 
10 = 1 ones
100 = 10 ones  
1000 = 100 ones
...

after understand this basic pattern, let us deal with all cases

we will encounter 3 cases, and let us take an example for counting '1' on only hundred's,
assume: m = 100, a = N / m, b = N % m

case 1:  hundreds=0. N = 3141092, a = 31410, b = 92 => number of '1'= 3141 * 100
case 2:  hundreds=1. N = 3141192, a = 31411, b = 92 => number of '1'= 3141 * 100 + (92 + 1)
case 3: hundreas>=2. N = 3141592, a = 31415, b = 92 => number of '1'= 3141 * 100 + (99 + 1) = (3141 + 1) * 100


*/