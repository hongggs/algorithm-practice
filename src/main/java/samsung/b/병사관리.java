package samsung.b;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * B형은 주로 자료를 담는 방식을 이용해서 최적화
 *
 * 1. 문제 대충 구현해보고 최적화가 필요한 어려운 부분 찾기
 * O(1) O(logN) 정도의 시간복잡도로 모든 함수 구현해야됨
 * update 같은 경우 O(N) 시간복잡도가 나와서 어려움
 * -> update를 최적화하는 것이 가장 중요한 문제
 *
 *
 * 2. update 개선 방법
 *  1) 링크드 리스트가 update가 쉽다!!
 *      근데 링크드 리스트는 랜덤엑세스가 오래 걸려 삭제할 때 힘들다
 *      그래서 삭제한 척 하자! -> 버전관리
 *      특정 id의 update된 버전을 바탕으로 해당 노드를 지우지 않고도 최신 노드가 뭔지 찾을 수 있다.
 *  2) 점수를 update할 때 같은 점수는 같은 수로 update 됨
 *      같은 팀의 같은 점수로 관리하면 더 쉽게 점수 관리 가능
 */
class UserSolution
{
    private static class Node {
        int id;
        int value;
        Node nxt;

        public Node() {}

        public Node(int id, int value, Node nxt) {
            this.id = id;
            this.value = value;
            this.nxt = nxt;
        }
    }

    public Node[] node = new Node[200055]; //노드 미리 생성해놓기
    public int cnt = 0;
    public int[] version = new int[100055]; //version[i] := ID가 i인 사람의 최신 버전
    public int[] num = new int[100055]; //num[i] := ID가 i인 사람의 team 번호

    public Node getNewNode(int id, Node nxt) {
        Node ret = node[cnt++]; //생성한 노드 가져다 붙이기
        ret.id = id;
        ret.nxt = nxt;
        ret.value = ++version[id];
        return ret;
    }

    private static class Team {
        Node[] head = new Node[6];
        Node[] tail = new Node[6];
    }

    public Team[] t = new Team[6];

    public void init()
    {
        cnt = 0;
        for(int i = 0; i < 200055; i++) {
            if(node[i] == null) {
                node[i] = new Node();
            }
        }

        for(int i = 1; i <= 5; i++) {
            t[i] = new Team();
            for(int j = 1; j <= 5; j++) {
                t[i].tail[j] = t[i].head[j] = getNewNode(0, null);
            }
        }
    }

    public void hire(int mID, int mTeam, int mScore)
    {
        Node newNode = getNewNode(mID, null);
        t[mTeam].tail[mScore].nxt = newNode;
        t[mTeam].tail[mScore] = newNode;
        num[mID] = mTeam;
    }

    public void fire(int mID)
    {
        version[mID] = -1;
    }

    public void updateSoldier(int mID, int mScore)
    {
        hire(mID, num[mID], mScore);
    }

    public void updateTeam(int mTeam, int mChangeScore)
    {
        if(mChangeScore < 0) {
            for(int j = 1; j <= 5; j++) {
                int k = j + mChangeScore;
                k = k < 1 ? 1 : (k > 5 ? 5 : k);
                if(j == k) {
                    continue;
                }

                if(t[mTeam].head[j].nxt == null) continue;
                t[mTeam].tail[k].nxt = t[mTeam].head[j].nxt;
                t[mTeam].tail[k] = t[mTeam].tail[j];
                t[mTeam].head[j].nxt = null;
                t[mTeam].tail[j] = t[mTeam].head[j];
            }
        }
        if(mChangeScore > 0) {
            for(int j = 5; j >= 1; j--) {
                int k = j + mChangeScore;
                k = k < 1 ? 1 : (k > 5 ? 5 : k);
                if(j == k) {
                    continue;
                }

                if(t[mTeam].head[j].nxt == null) continue;
                t[mTeam].tail[k].nxt = t[mTeam].head[j].nxt;
                t[mTeam].tail[k] = t[mTeam].tail[j];
                t[mTeam].head[j].nxt = null;
                t[mTeam].tail[j] = t[mTeam].head[j];
            }
        }
    }

    public int bestSoldier(int mTeam)
    {
        for(int i = 5; i >= 1; i--) {
            Node node = t[mTeam].head[i].nxt;
            if(node == null) continue;
            int ans = 0;
            while(node != null) {
                if(node.value == version[node.id]) {
                    ans = ans < node.id ? node.id : ans;
                }
                node = node.nxt;
            }
            if(ans != 0) {
                return ans;
            }

        }
        return 0;
    }
}

class 병사관리
{
    private final static int CMD_INIT				= 1;
    private final static int CMD_HIRE				= 2;
    private final static int CMD_FIRE				= 3;
    private final static int CMD_UPDATE_SOLDIER		= 4;
    private final static int CMD_UPDATE_TEAM		= 5;
    private final static int CMD_BEST_SOLDIER		= 6;

    private final static UserSolution usersolution = new UserSolution();

    private static boolean run(BufferedReader br) throws Exception
    {
        StringTokenizer st;

        int numQuery;

        int mID, mTeam, mScore, mChangeScore;

        int userAns, ans;

        boolean isCorrect = false;

        numQuery = Integer.parseInt(br.readLine());

        for (int q = 0; q < numQuery; ++q)
        {
            st = new StringTokenizer(br.readLine(), " ");

            int cmd;
            cmd = Integer.parseInt(st.nextToken());

            switch(cmd)
            {
                case CMD_INIT:
                    usersolution.init();
                    isCorrect = true;
                    break;
                case CMD_HIRE:
                    mID = Integer.parseInt(st.nextToken());
                    mTeam = Integer.parseInt(st.nextToken());
                    mScore = Integer.parseInt(st.nextToken());
                    usersolution.hire(mID, mTeam, mScore);
                    break;
                case CMD_FIRE:
                    mID = Integer.parseInt(st.nextToken());
                    usersolution.fire(mID);
                    break;
                case CMD_UPDATE_SOLDIER:
                    mID = Integer.parseInt(st.nextToken());
                    mScore = Integer.parseInt(st.nextToken());
                    usersolution.updateSoldier(mID, mScore);
                    break;
                case CMD_UPDATE_TEAM:
                    mTeam = Integer.parseInt(st.nextToken());
                    mChangeScore = Integer.parseInt(st.nextToken());
                    usersolution.updateTeam(mTeam, mChangeScore);
                    break;
                case CMD_BEST_SOLDIER:
                    mTeam = Integer.parseInt(st.nextToken());
                    userAns = usersolution.bestSoldier(mTeam);
                    ans = Integer.parseInt(st.nextToken());
                    if (userAns != ans) {
                        isCorrect = false;
                    }
                    break;
                default:
                    isCorrect = false;
                    break;
            }
        }

        return isCorrect;
    }

    public static void main(String[] args) throws Exception
    {
        int TC, MARK;

//        System.setIn(new java.io.FileInputStream("input.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        TC = Integer.parseInt(st.nextToken());
        MARK = Integer.parseInt(st.nextToken());

        for (int testcase = 1; testcase <= TC; ++testcase)
        {
            int score = run(br) ? MARK : 0;
            System.out.println("#" + testcase + " " + score);
        }

        br.close();
    }
}