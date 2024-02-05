package swea.linkedlist;

import java.io.*;
import java.util.*;

class LinkedList<E> {

    private static class Node<E> {
        E value;
        Node<E> next;

        Node(E value, Node<E> next){
            this.value = value;
            this.next = next;
        }
    }

    Node<E> head;
    Node<E> tail;
    int size;

    LinkedList() {
        head = null;
        tail = null;
        size = 0;
    }

    Node<E> search(int index) {
        Node<E> returnNode = head;
        for(int i = 0; i < index; i++) {
            returnNode = returnNode.next;
        }
        return returnNode;
    }

    void addFirst(E value) {
        Node<E> first = head;
        Node<E> newNode = new Node<>(value, first);
        size++;
        head = newNode;

        if(first == null) {
            tail = newNode;
        }

    }

    void add(E value) {
        Node<E> last = tail;
        Node<E> newNode = new Node<>(value, null);
        size++;
        tail = newNode;

        if(last == null) {
            head = newNode;
        } else {
            last.next = newNode;
        }
    }

    void addArray(E[] values, int index) {
        if(head == null || index == size - 1) {
            for(int i = 0; i < values.length; i++) {
                add(values[i]);
            }
            return;
        }

        if(index == 0) {
            for(int i = values.length - 1; i >= 0; i--) {
                addFirst(values[i]);
            }
            return;
        }

        Node<E> prevNode = search(index - 1);
        Node<E> nextNode = prevNode.next;
        for(int i = values.length - 1; i >= 0; i--) {
            Node<E> newNode = new Node(values[i], nextNode);
            size++;
            nextNode = newNode;
        }
        prevNode.next = nextNode;
    }

    String getTopN(int n) {
        StringBuilder sb = new StringBuilder();
        Node<E> node = head;
        for(int i = 0; i < n; i++) {
            sb.append(node.value).append(" ");
            node = node.next;
        }
        return sb.toString().trim();
    }

}

public class S1228 {

    static int N;
    static LinkedList<Integer> list;
    public static void main(String[] args) throws Exception {
//        System.setIn(new FileInputStream("res/input_d3_1228.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = 10;
        for(int t = 1; t <= T; t++) {
            sb.append("#").append(t).append(" ");
            N = Integer.parseInt(br.readLine());
            list = new LinkedList<>();
            st = new StringTokenizer(br.readLine());
            for(int i = 0; i < N; i++) {
                list.add(Integer.parseInt(st.nextToken()));
            }

            N = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());
            for(int i = 0; i < N; i++) {
                st.nextToken();
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                Integer[] values = new Integer[y];
                for(int j = 0; j < y; j++) {
                    values[j] = Integer.parseInt(st.nextToken());
                }
                list.addArray(values, x);
            }

            sb.append(list.getTopN(10)).append("\n");
        }
        System.out.println(sb);
    }
}