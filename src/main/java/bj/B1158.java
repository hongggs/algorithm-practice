package bj;

import java.io.*;
import java.util.*;

class LinkedList<T> {
    Node<T> head;
    Node<T> tail;

    int size;

    LinkedList() {
        head = null;
        tail = null;
        size = 0;
    }

    private static class Node<T> {
        T value;
        Node<T> next;

        public Node(T value, Node<T> next) {
            this.value = value;
            this.next = next;
        }
    }
    Node<T> search(int index) {
        Node<T> returnNode = head;
        for(int i = 0; i < index; i++) {
            returnNode = returnNode.next;
        }
        return returnNode;
    }
    void add(T value) {
        Node<T> last = tail;
        Node<T> newNode = new Node(value, null);

        size++;

        tail = newNode;

        if(head == null) {
            head = newNode;
        } else {
            last.next = newNode;
        }
    }

    T removeFirst() {
        T returnValue = head.value;

        Node<T> first = head.next;

        size--;
        head.next = null;
        head.value = null;

        head = first;

        if(head == null) {
            tail = null;
        }

        return returnValue;
    }

    T remove(int index) {
        if(index == 0) {
            return removeFirst();
        }
        Node<T> prevNode = search(index - 1);
        Node<T> target = prevNode.next;
        Node<T> nextNode = target.next;

        T returnValue = target.value;

        size--;
        target.value = null;
        target.next = null;

        prevNode.next = nextNode;
        if(nextNode == null) {
            tail = prevNode;
        }

        return returnValue;
    }

    boolean isEmpty() {
        return size == 0;
    }
}
public class B1158 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        sb.append("<");
        LinkedList<Integer> list = new LinkedList<>();
        for(int i = 1; i <= N; i++) {
            list.add(i);
        }

        int cnt = 1;
        int index = 0;
        while(list.size > 1) {
            if(cnt == K) {
                cnt = 1;
                sb.append(list.remove(index)).append(", ");
            } else {
                cnt++;
                index = (index + 1) % list.size;
            }

        }
        sb.append(list.remove(0));
        sb.append(">");
        System.out.println(sb);
    }
}
