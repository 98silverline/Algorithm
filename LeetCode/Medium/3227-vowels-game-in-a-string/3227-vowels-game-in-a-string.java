class Solution {
    public boolean doesAliceWin(String s) {
        int cnt = 0;
        for(int i = 0; i < s.length(); i++) {
            switch(s.charAt(i)) {
                case 'a':
                    cnt++;
                    break;
                case 'e':
                    cnt++;
                    break;
                case 'i':
                    cnt++;
                    break;
                case 'o':
                    cnt++;
                    break;
                case 'u':
                    cnt++;
                    break;
                default:
                    break;
            }
        }
        if(cnt == 0) return false;
        else return true;
        }
    }
