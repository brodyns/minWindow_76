import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        String s = "ADOBECODEBANC";
        String t = "ABC";
        System.out.println((new Solution()).minWindow(s, t));
    }
}

class Solution{
    public String minWindow(String s, String t){
        HashMap<Character, Integer> need = new HashMap<>();
        HashMap<Character, Integer> window = new HashMap<>();
        for (int i = 0; i < t.length(); i++) {
            need.put(t.charAt(i), need.getOrDefault(t.charAt(i), 0)+1);
        }
        int right = 0, left = 0;
        int minLen = Integer.MAX_VALUE;
        int start = 0;
        int valid = 0;
        while(right < s.length()){
            char cur = s.charAt(right);
            if(need.containsKey(cur)){
                window.put(cur, window.getOrDefault(cur, 0)+1);
                if(window.getOrDefault(cur,0).equals(need.get(cur))){
                    valid++;
                }
            }
            right++;
            while(valid == need.size()){
                if(right - left < minLen){
                    start = left;
                    minLen = right - left;
                }
                char d = s.charAt(left);
                left++;
                if(window.containsKey(d)){
                    if(window.get(d).equals(need.get(d))) {
                        valid--;
                    }
                    window.put(d, window.get(d) - 1);
                }
            }
        }
        return minLen == Integer.MAX_VALUE ? "":s.substring(start, start+minLen);
    }
}
