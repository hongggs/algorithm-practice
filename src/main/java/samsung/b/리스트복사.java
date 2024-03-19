package samsung.b;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

class 리스트복사 {
    private static class UserSolution
    {
        final int MAX_MAKE_LIST = 10;
        final int MAX_LENGTH = 200000;
        final int MAX_ADDRESS = 6000;
        final int MAX_CHANGE = 110000;
        int initNumber;     // 원본 배열의 번호
        int[][] initValue;  // 원본 배열(makeList 당시) 정보를 그대로 저장하는 배열
        int addressNumber; // 새로운 배열의 번호(마지막 배열 번호 + 1)
        HashMap<String, Integer> address; // 배열 이름 -> 원본 배열 번호 mapping
        int changeNumber; // 현재까지 발생한 "변화 이벤트" 개수 (makeList, copyList, updateElement 모두 포함)
        Pair[] changeLog; //현재까지 발생한 "변화 이벤트" 정보
        // makeList         : (-1, 원본 배열 번호)
        // copyList         : (-1, -1) 정보 추가하기. (deepcopy를 의미함)
        // updateElement    : (인덱스, 값) ; 인덱스의 값을 변경했다.
        int[] lastChange;
        int[] prevChange;
        private static class Pair {
            int first;
            int second;
            public Pair(int first, int second) {
                this.first = first;
                this.second = second;
            }
        }
        public void init() {
            //원본 배열 초기화
            initNumber = 0;
            initValue = new int[MAX_MAKE_LIST + 1][MAX_LENGTH + 1];

            //전체 배열 초기화
            addressNumber = 0;
            address = new HashMap<>();

            //변화 이벤트 정보 초기화
            changeNumber = 0;
            changeLog = new Pair[MAX_CHANGE + 1];
            lastChange = new int[MAX_ADDRESS + 1];
            prevChange = new int[MAX_CHANGE + 1];
        }

        String getName(char[] name) { //char -> String
            String x = "";
            for(int i = 0; name[i] != 0; i++) {
                x += name[i];
            }
            return x;
        }
        public void makeList(char _mName[], int mLength, int mListValue[]) {
            String mName = getName(_mName);
            // initNumber 위치에 원본 배열 저장하기
            System.arraycopy(mListValue, 0, initValue[initNumber], 0, mLength);
            initNumber++;

            address.put(mName, addressNumber); //mName 이름의 배열을 생성
            addressNumber++;

            changeLog[changeNumber] = new Pair(-1, initNumber - 1);
            prevChange[changeNumber] = -1;
            lastChange[address.get(mName)] = changeNumber;
            changeNumber++;
        }

        public void copyList(char _mDest[], char _mSrc[], boolean mCopy) {
            String mDest = getName(_mDest);
            String mSrc = getName(_mSrc);
            if(mCopy) {
                address.put(mDest, addressNumber);
                addressNumber++;

                changeLog[changeNumber] = new Pair(-1, -1);
                prevChange[changeNumber] = lastChange[address.get(mSrc)];
                lastChange[address.get(mDest)] = changeNumber;
                changeNumber++;
            } else {
                address.put(mDest, address.get(mSrc));
            }
        }

        public void updateElement(char _mName[], int mIndex, int mValue) {
            String mName = getName(_mName);
            changeLog[changeNumber] = new Pair(mIndex, mValue);
            prevChange[changeNumber] = lastChange[address.get(mName)];
            lastChange[address.get(mName)] = changeNumber;
            changeNumber++;
        }

        public int element(char _mName[], int mIndex) {
            String mName = getName(_mName);
            int c = lastChange[address.get(mName)];
            while(true) {
                if(prevChange[c] == -1) {
                    return initValue[changeLog[c].second][mIndex];
                }
                if(changeLog[c].first == mIndex) {
                    return changeLog[c].second;
                }
                c = prevChange[c];
            }
        }
    }

    private final static int CMD_INIT			= 100;
    private final static int CMD_MAKE_LIST		= 200;
    private final static int CMD_COPY_LIST		= 300;
    private final static int CMD_UNDATE_ELEMENT	= 400;
    private final static int CMD_ELEMENT		= 500;

    private final static UserSolution usersolution = new UserSolution();

    private static int mSeed;
    private static int pseudo_rand()
    {
        mSeed = mSeed * 214013 + 2531011;
        return (mSeed >> 16) & 0x7FFF;
    }

    private static char[] mName = new char[21];
    private static char[] mDest = new char[21];
    private static char[] mSrc = new char[21];
    private static int[] mListValue = new int[200000];

    private static void generateName(char[] name, int seed)
    {
        mSeed = seed;
        int name_len = pseudo_rand() % 20 + 1;
        for (int i = 0; i < name_len; ++i)
        {
            name[i] = (char)(pseudo_rand() % 26 + 'a');
        }
        name[name_len] = '\0';
    }

    private static int generateList(int[] listValue, int seed)
    {
        mSeed = seed;
        int length = pseudo_rand() << 15;
        length = (length + pseudo_rand()) % 200000 + 1;
        for (int i = 0; i < length; ++i)
        {
            listValue[i] = pseudo_rand();
        }
        return length;
    }

    private static boolean run(BufferedReader br) throws Exception
    {
        StringTokenizer st;

        int numQuery;

        int seed;
        int mIndex, mValue, mCopy, mLength;
        int userAns, ans;
        boolean isCorrect = false;

        numQuery = Integer.parseInt(br.readLine());

        for (int q = 0; q < numQuery; ++q)
        {
            st = new StringTokenizer(br.readLine(), " ");

            int cmd;
            cmd = Integer.parseInt(st.nextToken());

            switch (cmd)
            {
                case CMD_INIT:
                    usersolution.init();
                    isCorrect = true;
                    break;
                case CMD_MAKE_LIST:
                    seed = Integer.parseInt((st.nextToken()));
                    generateName(mName, seed);
                    seed = Integer.parseInt((st.nextToken()));
                    mLength = generateList(mListValue, seed);
                    usersolution.makeList(mName, mLength, mListValue);
                    break;
                case CMD_COPY_LIST:
                    seed = Integer.parseInt((st.nextToken()));
                    generateName(mDest, seed);
                    seed = Integer.parseInt((st.nextToken()));
                    generateName(mSrc, seed);
                    mCopy = Integer.parseInt((st.nextToken()));
                    usersolution.copyList(mDest, mSrc, (mCopy != 0));
                    break;
                case CMD_UNDATE_ELEMENT:
                    seed = Integer.parseInt((st.nextToken()));
                    generateName(mName, seed);
                    mIndex = Integer.parseInt((st.nextToken()));
                    mValue = Integer.parseInt((st.nextToken()));
                    usersolution.updateElement(mName, mIndex, mValue);
                    break;
                case CMD_ELEMENT:
                    seed = Integer.parseInt((st.nextToken()));
                    generateName(mName, seed);
                    mIndex = Integer.parseInt((st.nextToken()));
                    userAns = usersolution.element(mName, mIndex);
                    ans = Integer.parseInt((st.nextToken()));
                    if (userAns != ans)
                    {
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

        //System.setIn(new java.io.FileInputStream("res/sample_input.txt"));

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
