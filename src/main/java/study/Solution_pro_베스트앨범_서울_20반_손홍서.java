package study;

import java.util.*;

class Solution_pro_베스트앨범_서울_20반_손홍서 {
    static class Music implements Comparable<Music> {
        int index, playCount;
        String genre;
        int genrePlayCount;
        Music(int index, int playCount, String genre, int genrePlayCount) {
            this.index = index;
            this.playCount = playCount;
            this.genre = genre;
            this.genrePlayCount = genrePlayCount;
        }
        public int compareTo(Music m) {
            if(m.genrePlayCount > genrePlayCount) {
                return 1;
            } else if(m.genrePlayCount == genrePlayCount) {
                if(m.playCount > playCount) {
                    return 1;
                } else if(m.playCount == playCount) {
                    if(m.index < index) {
                        return 1;
                    }
                }
            }
            return -1;
        }

    }
    HashMap<String, Integer> genreMap;
    PriorityQueue<Music> pq;
    public int[] solution(String[] genres, int[] plays) {
        genreMap = new HashMap<>();
        pq = new PriorityQueue<>();
        ArrayList<Integer> ansList = new ArrayList<>();
        for(int i = 0; i < genres.length; i++) {
            genreMap.put(genres[i],genreMap.getOrDefault(genres[i], 0) + plays[i]);
        }
        for(int i = 0; i < genres.length; i++) {
            pq.offer(new Music(i, plays[i], genres[i], genreMap.get(genres[i])));
        }
        int k = 0;
        String old = pq.peek().genre;
        while(!pq.isEmpty()) {
            Music now = pq.poll();
            if(k < 2 && old.equals(now.genre)) {
                k++;
                ansList.add(now.index);
            } else if(!old.equals(now.genre)) {
                old = now.genre;
                k = 1;
                ansList.add(now.index);
            }
        }

        int[] ans = new int[ansList.size()];
        for(int i = 0; i < ansList.size(); i++) {
            ans[i] = ansList.get(i);
        }
        return ans;
    }
}