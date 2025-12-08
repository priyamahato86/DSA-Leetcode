// A square triple (a,b,c) is a triple where a, b, and c are integers and a2 + b2 = c2.

// Given an integer n, return the number of square triples such that 1 <= a, b, c <= n.

##CODE:

class Solution {
    public int countTriples(int n) {
        
        int cnt = 0;

        for(int i=1;i<=n;i++){
            for(int j=1;j<=n;j++){
                long term = 1L*(i*i)+1L*(j*j);
                double num = Math.sqrt(term);
                int k = (int)num;
                if(k*k==term && k<=n) {
                    cnt++;
                }


            }
        }

        return cnt;
    }
}
