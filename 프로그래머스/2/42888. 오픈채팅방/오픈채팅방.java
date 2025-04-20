import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
    static class Mes {
        String uuid;
        int type;

        public Mes(int type, String uuid) {
            this.type = type;
            this.uuid = uuid;
        }
    }

    public String[] solution(String[] record) {
        List<Mes> list = new ArrayList<>();
        Map<String, String> hm = new HashMap<>();
        List<String> ans = new ArrayList<>();
        String[] message = new String[2];
        message[0] = "님이 나갔습니다.";
        message[1] = "님이 들어왔습니다.";
        String[] answer = {};
        for (String str : record) {
            String[] s = str.split(" ");
            if(s[0].equals("Enter")) {
                hm.put(s[1], s[2]);
                list.add(new Mes(1, s[1]));
            } else if (s[0].equals("Leave")) {
                list.add(new Mes(0, s[1]));
            } else {
                hm.put(s[1], s[2]);
            }
        }

        for (Mes mes : list) {
            String str = hm.get(mes.uuid) + message[mes.type];
            ans.add(str);
        }
        answer = ans.toArray(new String[0]);
        return answer;
    }
}